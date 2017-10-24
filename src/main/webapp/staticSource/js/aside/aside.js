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
									if (values.URLS != null){
										str += "<div class='aside-bar-content scrollbar-aside'>";
											str += "<div class='scrollbar-wrap'>";
												str += "<ul>";
												$.each(values.URLS,function(index,url){
													str += "<li class='url-item'>";
														str += "<div class='url-content' style='background:url(/mo/staticSource/image/"+url.urlimage+") no-repeat left center;'>";
															str += "<a class='url-info' href='"+url.url+"' target='_blank'><em title='"+url.urlname+"'>"+url.urlname+"</em></a>";
															str += "<input type='hidden' name='cur_url_id' value="+url.id+">";
															str += "<div class='url-edit'>";
																	str += "<i class='iconfont icon-xiugai fs12'></i>";
																	str += "<div class='url-edit-tip'>";
																		str += "<ul>";
																			str += "<li>编辑</li>";
																			str += "<li>删除</li>";
																		str += "</ul>";
																	str += "</div>";
															str += "</div>";
														str += "</div>";
													str += "</li>";
												})
												str += "</ul>";
											str += "</div>";
										str += "</div>";
									}
									
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
			    		$select_folder.append("<option value='' class='cl75' selected = 'selected'>请选择分类收藏夹</option>");
			        	$.each(data.navFolders,function(key,values){ 
			        		var option = "<option value='"+values.id+"'>"+values.navigationname+"</option>"
			        		$select_folder.append(option);
			        	})
			        	//打开添加窗口
			        	var collect_pop_index = layer.open({
			        		type : 1,
			        		content : $('#navigation-add-pop'),
			        		title:'收藏地址',
			        		area: ['600px', '330px'],
			        		shadeClose : true,
			        	});
			        	
			        	// 点击添加按钮
			        	layui.use('form', function() {
			        		var form = layui.form;
			        		form.on('submit(navigation_add_form)', function(data) {
			        			$.ajax({
			        			    url:'navigation/urloperation/authc',
			        			    type:'POST',
			        			    async:true,
			        			    data:data.field,
			        			    timeout:5000,
			        			    dataType:'json',
			        			    success:function(data){
			        			        if (data.flag == 'yes'){
			        			        	layer.close(collect_pop_index);
			        			        	layer.msg("添加完成");
			        			        }else{
			        			        	layer.msg(data.errorMsg);
			        			        }
			        			    }
			        			});
			        			return false;
			        		});
			        	})
			        }else{
			        	layer.msg(data.errorMsg);
			        }
			    }
			})
		}else {
			// 要求登录先
			var index = layer.open({
				type : 1,
				content : $('#login-pop'),
				title:false,
				area: ['500px', '340px'],
				shadeClose : true,
			});
		}
	})
	//刷新用户下的某个收藏夹
	function refreshURL(cusid,navid){
		$.ajax({
		    url:'',
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
									if (values.URLS != null){
										str += "<div class='aside-bar-content scrollbar-aside'>";
											str += "<div class='scrollbar-wrap'>";
												str += "<ul>";
												$.each(values.URLS,function(index,url){
													str += "<li class='url-item'>";
														str += "<div class='url-content' style='background:url(/mo/staticSource/image/"+url.urlimage+") no-repeat left center;'>";
															str += "<a class='url-info' href='"+url.url+"' target='_blank'><em title='"+url.urlname+"'>"+url.urlname+"</em></a>";
															str += "<input type='hidden' name='cur_url_id' value="+url.id+">";
															str += "<div class='url-edit'>";
																	str += "<i class='iconfont icon-xiugai fs12'></i>";
																	str += "<div class='url-edit-tip'>";
																		str += "<ul>";
																			str += "<li>编辑</li>";
																			str += "<li>删除</li>";
																		str += "</ul>";
																	str += "</div>";
															str += "</div>";
														str += "</div>";
													str += "</li>";
												})
												str += "</ul>";
											str += "</div>";
										str += "</div>";
									}
									
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
});