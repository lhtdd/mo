$(function() {
	// aside.jsp 加载完成后，自动请求所登录用户的数据
	loadurl();
	function loadurl(){
		var cusID = $("#moci").val();
		var url = 'navigation/url';
		if (cusID){
			url = url + '?cus=' + cusID;
		}
		$.ajax({
		    url:url,
		    type:'GET',
		    async:true,
		    timeout:5000,
		    dataType:'json',
		    success:function(data){
		        if (data.flag == 'yes'){
		        	$.each(data.navFolder,function(key,values){ 
			        		var str = "<li class='aside-bar-item'>";
					        		str += "<input type='hidden' name='cur_folder_id' value="+values.folderID+">";
									str += "<i class='iconfont "+values.folderIcon+"'></i>";
									str += "<span>"+values.folderName+"</span>";
									str += "<div class='aside-bar-content scrollbar-aside'>";
										str += "<div class='scrollbar-wrap'>";
											str += "<ul class='ul"+values.folderID+"'>";
											if (values.URLS != null){
														$.each(values.URLS,function(index,url){
															str += "<li class='url-item'>";
															// 如果url前端图片为空则展示系统默认的icon
															if (url.urlimage == null || url.urlimage == ''){
																str += "<div class='url-content' style='background:url(/mo/staticSource/image/defaulticon.ico) no-repeat left center;'>";
															}else {
																str += "<div class='url-content' style='background:url(navigation/urlIcon/"+url.id+") no-repeat left center;'>";
															}
															// 如果是不是系统预设的url的在点击量统计及功能编辑上有所区分
															if (url.type == '2') {
																str += "<a class='url-info' onclick='visitURL("+url.id+")' href='"+url.url+"' target='_blank'><em title='"+url.urlname+"'>"+url.urlname+"</em></a>";
															} else {
																str += "<a class='url-info' href='"+url.url+"' target='_blank'><em title='"+url.urlname+"'>"+url.urlname+"</em></a>";
															}
																	if (url.type == '2'){
																		str += "<div class='url-edit'>";
																			str += "<i class='iconfont icon-xiugai fs12'></i>";
																			str += "<div class='url-edit-tip'>";
																				str += "<ul>";
																					str += "<li onclick='updateURL("+url.id+",\""+url.url+"\",\""+url.urlname+"\","+url.navigationid+")'>编辑</li>";
																					str += "<li onclick='deleteURL("+url.id+","+url.navigationid+");'>删除</li>";
																				str += "</ul>";
																			str += "</div>";
																		str += "</div>";
																	}
																str += "</div>";
															str += "</li>";
														})
											}
											str += "</ul>";
										str += "</div>";
									str += "</div>";
								str += "</li>";
						$(".aside-bar-other").before(str);
		        	})
		        	//重新绘制滚动条
		        	$('.scrollbar-aside').niceScroll(".scrollbar-wrap", {cursorcolor : "#FF6600"}).resize();
		        }else{
		        	layer.msg(data.errorMsg);
		        }
		    }
		});
	}
	//添加url
	$(".btn-url-add").click(function(){
		var cusID = $("#moci").val();
		if (cusID){
			//首先查询当前系统内预设的导航夹
			prepareNavFolderOption(null);
			//打开添加窗口
			var collect_pop_index = openCollectForm();
			//提交保存信息
			submitNavigationForm(collect_pop_index, null, null,'add');
		}else {
			// 要求登录先
			var index = layer.open({
				type : 1,
				content : $('#login-pop'),
				title:false,
				area: ['500px', '340px'],
				shadeClose : true,
			})
		}
	})
	
});
//添加或更新URL信息时，动态添加收藏夹内容
//folderID如果为空则是更新添加，否则是新增
function prepareNavFolderOption(folderID){
	$.ajax({
	    url:'navigation/folderName',
	    type:'GET',
	    async:true,
	    timeout:5000,
	    dataType:'json',
	    success:function(data){
	    	if (data.flag == 'yes'){
	    		//查询收藏夹
	    		var $select_folder = $("#navigation-add-pop select[name='targetFolder']");
	    		$select_folder.empty();
	    		$select_folder.append("<option value='' class='cl75'>请选择分类收藏夹</option>");
	        	$.each(data.navFolders,function(key,values){ 
	        		//过滤掉常用导航夹
	        		if (values.id != '1'){
	        			var option = "<option value='"+values.id+"'";
	        			if (folderID != null && folderID == values.id){
	        				option += "selected= 'selected'";
	        			}
	        			option += ">"+values.navigationname+"</option>";
	        			$select_folder.append(option);
	        		}
	        	})
	        }else{
	        	layer.msg(data.errorMsg);
	        }
	    }
	})
}
//打开添加窗口
function openCollectForm(){
	var collect_pop_index = layer.open({
		type : 1,
		content : $('#navigation-add-pop'),
		title:'收藏地址',
		area: ['600px', '330px'],
		shadeClose : true,
	})
	return collect_pop_index;
}
//提交保存信息
// formID 是弹出窗的index,用于提交完成后关闭图层
// urlID 是修改URL的ID值，如果是新增则可以为空
// oldID 是修改URL的之前所在导航夹的ID，用于刷新之前的导航夹
// flag 是新增或者修改的标识
function submitNavigationForm(formID, urlID, oldID, flag){
	layui.use('form', function() {
		var form = layui.form;
		form.on('submit(navigation_add_form)', function(data) {
			var targent_Folder_id = data.field.targetFolder;
			var tipMsg = '添加成功';
			if (flag == 'update'){
				data.field._method = 'PUT';
				data.field.urlid = urlID;
				tipMsg = '修改成功';
			}
			$.ajax({
			    url:'navigation/urloperation/authc',
			    type:'POST',
			    async:true,
			    data:data.field,
			    timeout:5000,
			    dataType:'json',
			    success:function(data){
			        if (data.flag == 'yes'){
			        	layer.close(formID);
			        	refreshURL(targent_Folder_id);
			        	clearNavigationAddForm();
			        	layer.msg(tipMsg);
			        }else{
			        	layer.msg(data.errorMsg);
			        }
			    }
			});
			// 如果是更新URL的所在位置，需要刷新一下该url之前所在的导航夹
			if (flag == 'update' && oldID != null && oldID != targent_Folder_id){
				refreshURL(oldID);
			}
			return false;
		});
	})
}

//清空form的值
function clearNavigationAddForm(){
	$(".navigation-add-form").find("input[name='urlName']").val("");
	$(".navigation-add-form").find("input[name='webLocation']").val("");
}

//刷新用户下的某个收藏夹
function refreshURL(navid){
	$.ajax({
		url:"navigation/commonurl/"+navid+"/authc",
		type:'GET',
		async:true,
		timeout:5000,
		dataType:'json',
		success:function(data){
			if (data.flag == 'yes'){
				if (data.newURLS.commonURLs != null){
					$.each(data.newURLS,function(key,values){ 
						if (values != null){
							var $current_ul = null;
							if (key == 'commonURLs'){
								$current_ul = $(".aside-bar-item .ul1");
							}else {
								$current_ul = $(".aside-bar-item .ul"+navid);
							}
							$current_ul.empty();	
							$.each(values,function(key,url){ 
								var str = "";
								str += "<li class='url-item'>";
									str += "<div class='url-content' style='background:url(/mo/staticSource/image/"+url.urlimage+") no-repeat left center;'>";
									str += "<a class='url-info' onclick='visitURL("+url.id+")' href='"+url.url+"' target='_blank'><em title='"+url.urlname+"'>"+url.urlname+"</em></a>";
									if (url.type == '2'){
										str += "<div class='url-edit'>";
											str += "<i class='iconfont icon-xiugai fs12'></i>";
											str += "<div class='url-edit-tip'>";
												str += "<ul>";
													str += "<li onclick='updateURL("+url.id+",\""+url.url+"\",\""+url.urlname+"\","+url.navigationid+")'>编辑</li>";
													str += "<li onclick='deleteURL("+url.id+","+url.navigationid+");'>删除</li>";
												str += "</ul>";
											str += "</div>";
										str += "</div>";
									}
									str += "</div>";
								str += "</li>";
								$current_ul.append(str);
							})
						}
					})
					// 如果目标导航夹为空则需要清空
					if (data.newURLS.targetNavURLs == null && navid != 1){
						$current_ul = $(".aside-bar-item .ul"+navid).empty();
					}
				}else {
					$(".aside-bar-item .ul1").empty();
					$current_ul = $(".aside-bar-item .ul"+navid).empty();
				}
				//重新绘制滚动条
				$('.scrollbar-aside').niceScroll(".scrollbar-wrap", {cursorcolor : "#FF6600"}).resize();
			}else{
				layer.msg(data.errorMsg);
			}
		}
	});
}
//更新URL
function updateURL(urlId,url,urlName,folderId){
	prepareNavFolderOption(folderId);
	$(".navigation-add-form").find("input[name='urlName']").val(urlName);
	$(".navigation-add-form").find("input[name='webLocation']").val(url);
	var collect_pop_index = openCollectForm();
	submitNavigationForm(collect_pop_index, urlId, folderId, 'update');
}
//删除URL
function deleteURL(urlid,navid){
	$.ajax({
	    url:'navigation/urloperation/authc',
	    type:'POST',
	    async:true,
	    data:{
	    	_method:'DELETE',
	    	'urlid':urlid
	    },
	    timeout:5000,
	    dataType:'json',
	    success:function(data){
	        if (data.flag == 'yes'){
	        	refreshURL(navid);
	        	layer.msg("已删除");
	        }else{
	        	layer.msg(data.errorMsg);
	        }
	    }
	});
}

function visitURL(urlid){
	$.ajax({
	    url:"navigation/visiturl/" + urlid,
	    type:'GET',
	    async:true,
	    timeout:5000,
	    dataType:'json',
	    success:function(data){
	    	//刷新常用导航夹
	    	refreshURL(1);
	    }
	})
}