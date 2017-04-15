$(function(){
	//请求路径
	var URL = {
			loginUrl:"/login",
			redirectUrl:"/trfc/system/base/code/main"
	};
	//登陆按钮 绑定点击时间
	$('#login_button').click(loginAction);
	
	//登陆验证
	function loginAction(){
		var url = URL.loginUrl;
		var account = $('#user_account').val();
		var pswd = $('#user_password').val();
		var params = {account:account,pswd:pswd};
		
		
		$.post(url,params,function(result){
			if(result.code=='000000'){
				location.href=URL.redirectUrl;
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	
	
	
});