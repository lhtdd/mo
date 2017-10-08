$(function() {
	//fixDiv("header");
	fixDiv("happy-mine-operation","10");
	// 点击我的趣事
	$(".btn-write-happy").click(function(){
		var target_url = 'leisure/myHappyDetail/authc';
		layer.msg("ddd");
		$.ajax({
		    url:target_url,
		    type:'GET',
		    async:true,
		    success:function(data){
		    	// 返回yes 表示已经登陆，则需要重新请求
		        if (data == 'yes'){
		        	window.location.href = target_url;
		        }else if (data == 'no'){
		        	layer.open({
		        		  type: 1,
		        		  title: false,
		        		  closeBtn: 0,
		        		  shadeClose: true,
		        		  skin: 'yourclass',
		        		  content: $('#login_pop')
		        		});
		        }
		    }
		});
	});
});