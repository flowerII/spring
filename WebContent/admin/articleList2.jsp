<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title></title>
		<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css" />
		<style type="text/css">
			html,
			body {
				height: 100%;
				min-height: 500px;
			}
			div{
				position: relative;
				line-height: 30px;
			}
			p, .right{
				position: absolute;
				right: 15px;
			}
			.photo{
				position: absolute;
				top: 10px;
				height: 0px;
				width: 0px;
				border-top: 10px solid #000000;
				border-left: 6px solid transparent;
				border-right: 6px solid transparent;
			}
			li{
				min-height: 36px;
				line-height: 36px;
			}
			.selection{
				vertical-align: middle;
				padding: 5px;
			}
			.dropdown-menu {
				max-height: 60px;
				overflow-y: auto;
			}
		</style>
		<script src="../js/jquery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../js/plugs.js" type="text/javascript" charset="utf-8"></script>
		
	</head>
	<body>
		<div class="panel-body body-main">
		<ol class="list-group-item-text" class="main">
	    	<c:forEach var="article" items="${articles}" varStatus="status">
	    		<li id="${article.article_id}">
					<div>
						<a href="javascript:;" onclick="top.location.href='/spring/article_content?article=${article.article_id}'">${article.article_name}</a>
						<a class="right photo" href="#list${status.count}" data-toggle="collapse"></a>
					</div>
					<div class="panel collapse" id="list${status.count}">
						奖项：
						<select class="selection prize">
							<option value="0">无</option>
							<option value="1">一等奖</option>
							<option value="2">二等奖</option>
							<option value="3">三等奖</option>
							<option value="4">优胜等奖</option>
						</select>
						<button class="btn btn-danger delete">删除</button>
					</div>
				</li>
				<script type="text/javascript">	
				$("#${article.article_id}").find(".prize").val("${article.article_prize}");
			</script>
	    	</c:forEach>
		</ol>
		</div>
		<script type="text/javascript">
			$(function(){
				var _a=location.search.substring(1).split("&");
				var _b=_a.map(function(value){return value.split("=")});
				var _c=_b.map(function(value){return '"'+value[0]+'":'+value[1]});
				var _d="{"+_c.join(",")+"}";
				var req = JSON.parse(_d);
				var i =req.page;
				var num ='${num}';
				//生成分页导航栏
				$('.page').remove();
				$.page(".body-main",i,num,"activity="+req.activity);
				
				//奖项设置
				$('.prize').on('change',function(){
					var $id = $(this).parent().parent().attr('id');
					if(confirm("你确定要设置该奖项吗？")){
						$._ajax({
							url: "article_setPrize",
							data: {
								article_id : $id,
								article_prize : $(this).val()
							}
						}).done(function(data){
							if(data.success){
								alert("奖项设置成功");
							}else{
								alert("奖项设置失败，请稍后再试");
							}
						});
					}
				});
				
				//删除书籍
				$('.delete').on('click',function() {
					var $id = $(this).parent().parent().attr('id');
					if(confirm("你确定要删除改稿件吗？")){
						$._ajax({
							url: "article_delete",
							data: {
								article_id : $id
							}
						}).done(function(data){
							console.log(data)
							if(data.success){
								alert("稿件删除成功");
								location.reload();
							}else{
								alert("稿件删除失败，请稍后再试");
							}
						});
					}
			
				});
			})
		</script>
	</body>
</html>
