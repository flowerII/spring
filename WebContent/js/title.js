$(function(){
	/*
	 *    user 用户信息
	 * */
	var $user = $("#user");
	var $login_about = $("#login-about");
	var $admin = $("#admin");
	
	var userName = sessionStorage.userName|| "读者" ;
	$user.text(userName);
	if(sessionStorage.userName){
		var login_out = '<a id="login_out" href="javascript:void(0);">退出</a>';
		$login_about.html(login_out);
	}
	if(sessionStorage.role>0){
		$admin.show();
	}
	
	/**
	 *    退出登录 、清空数据
	 * */
	$("#login_out").click(function(){
		$._ajax({
			url: 'user_logout',
		}).then(function(data){
			console.log(data)
			localStorage.user_password = null;
			sessionStorage.removeItem("userName");
			sessionStorage.role = null;
			top.location.href="login.html";
		});
	});
})
