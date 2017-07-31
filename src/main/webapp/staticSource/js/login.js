//点击切换验证码
function changeVerifyCode(){
	$("#yzmImg").attr("src","Kaptcha.jpg?"+Math.floor(Math.random()*100));
} 
 
//提交
function doSubmit() {
	/*var verifyCodeValue = $("#verifyCode").val();
	if(verifyCodeValue.replace(/\s/g,"") == "") {
		alert("请输入验证码");
	}else {
	     //提交前先异步检查验证码是否输入正确
	     var verifyUrl = "${pageContext.request.contextPath}/servlet/VerifyServlet?verifyCode="+verifyCodeValue;
	     $.ajax({
	        type:"GET",
	        url:verifyUrl,
	         success:function(returnData){
	             if(returnData!="Y") {
	                 alert("请输入正确的验证码！");
	             }else {
	                 //验证码正确，进行提交操作
	                 alert("验证码输入正确，提交表单");
	                 }
	             },
	             error:function(e){
	                 alert(e);
	             }                 
	        });
	 }*/
 }