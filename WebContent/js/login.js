$(function() {
	var top = $("body").height()/2-$(".box").height()/2;
	$(".box").css("top",top);
	
	var $form = $("form");
	var $account = $("#useraccount");
	var $password = $("#password");

	// localStorage 存储
	$account.val(localStorage.getItem('user_account'));
	$password.val(localStorage.getItem('user_password'));
	
	$(".login-in").click(function(){
		
		//localStorage存储账号密码
		localStorage.setItem('user_account', $account.val());
		localStorage.setItem('user_password', $password.val());
		
		//表单验证
		if(!$account.isEmpty()){
			alert("账号不能为空！");
			$account.focus();
			return;
		}
		if(!$password.isEmpty()){
			alert("密码不能为空！");
			$password.focus();
			return;
		}
		if(!$account.isEmail()){
			alert("请输入正确邮箱！");
			$account.focus();
			return;
		}
		
		$._ajax({
			url: "user_login",
			data: {
				user_account:$account.val(),
				user_password:$password.val()
			},
			dataType:"json"
		}).done(function(data){
			console.log(data)
			if(data.success){
				var user=data.user
				console.log(user)
				sessionStorage.userName = user.user_name;
				sessionStorage.role = user.user_role;
				console.log(user.user_name)
				console.log(user.user_role)
				alert(data.message);
				window.location.href="index.html";
			} else {
				console.log(data.message);
				alert(data.message);
			}
		});
		return ;
	});
})
