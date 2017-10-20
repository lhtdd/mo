$(function() {
	// aside.jsp 加载完成后，自动请求所登录用户的数据
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
	//点击收藏按钮
	$(".btn-url-add").click(function(){
		if (cusID){
			layer.msg("dd");
		}else {
			var index = layer.open({
				type : 1,
				content : $('#login-pop'),
				title:false,
				area: ['500px', '340px'],
				shadeClose : true,
			});

		}
	})
});