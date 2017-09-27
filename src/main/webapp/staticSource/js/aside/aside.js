$(function() {
	$(".url-add").click(function(){
		$.ajax({
		    url:'navigation/url/authc',
		    type:'GET',
		    async:true,
		    success:function(data){
		        layer.alert(data);
		    }
		});
	})
});