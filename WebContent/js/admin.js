$(function(){
	/**
	 *  用户信息
	 * */

	var $user = $("#user");	
	var userName = sessionStorage.userName||"管理员";	
	$user.text(userName);
		
	/**
	 * 点击选修显示标题
	 * */
	var $li_a = $(".nav-click").find("li>a");
	$li_a.click(function(){
		var title = $(this).text();
		$(".content-title").text(title);
		
		window.onhashchange = function(){
			$("#content-main").attr('src',location.hash.substring(8).concat('.html'));
			//iframe高度
			$("#content-main").get(0).onload=function(){
				$("#content-main").height($("#content-main").contents().find("html").height());
			}
		}
		
		
	});
	
	
	/**
	 * 	hash ajax
	 */
	window.onhashchange = hash;
	function hash(){
		if(window.location.hash!=""){
			//化为    admin/users这类形式
			var url = location.hash;
			url = url.substring(2);
			$._ajax({
				url: url
			}).done(function(data){
				$("#content-main").attr('src',data);
				$("#content-main").height($("#content-main").contents().find("body").height());
			});
		}
	}

})
