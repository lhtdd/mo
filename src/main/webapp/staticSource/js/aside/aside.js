$(function() {
	// aside.jsp 加载完成后，自动请求所登录用户的数据
	/*$.ajax({
	    url:'member/login',
	    type:'POST',
	    async:true,
	    data:data.field,
	    timeout:5000,
	    dataType:'json',
	    success:function(data){
	        if (data.flag == 'yes'){
	        	window.location.href = data.go_url;
	        }else{
	        	layer.msg(data.errorMsg);
	        }
	    }
	});*/
	$(".btn-url-add").click(function(){
		layer.msg("1");
	})
});