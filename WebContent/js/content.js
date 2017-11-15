$(function(){
	//控制评论样式
	var comment_color = function(value){
		$('.comment-type').find(".border-red").removeClass("border-red");
		value.addClass("border-red");
	}
	
	//获取评论
	var type =location.search.substring(1).split("&")[0].split("=");
	var type_number = -1;
	// 获取评论请求次数
	var comment_ajaxnum = 0;
	//评论总数
	var num = 0;
	switch (type[0]){
		case "article":  type_number = 0;break;
		case "activity":  type_number = 1;break;
		case "news":  type_number = 2;break;
		default: type_number = -1;
	}
	window.showmore = function(){
		//是否显示“显示更多”按键
		if(comment_ajaxnum*10>=num){
			$(".more").hide();
		}else{
			$(".more").show();
		}
	}
	//获取更多评论
	$(".more").click(function(){
		getcomments(type_number,'newest',comment_ajaxnum);
	});
	
	var getcomments = function(type_number,method,ajaxnum){
		// method 为 newest/hot
		var method = method||'newest';
		var ajaxnum = ajaxnum || 0;
		if(type_number!=-1){
			//获取10条评论
			$._ajax({
				url : "getComment10",
				data:{
					'comment_type' : type_number,
					'all_id' : type[1],
					'method' : method,
					'ajaxnum': ajaxnum
				},
				cache:false
			}).done(function(list){
				if(ajaxnum==0) comment_ajaxnum=1;
				else comment_ajaxnum++;
				list = JSON.parse(list);
				//10条评论的内容
				var comments = list.comments;
				//评论的总条数
				num = list.num;
				//已点赞评论
				var zanovers = list.zans;
				var html="";
				//显示最新10条评论
				comments.forEach(function(value,i){
					var time =new Date(value.comment_time);
					var timestr = time.getFullYear()+"/"+(time.getMonth()+1)+"/"+time.getDate()+"  "+time.getHours()+":"+time.getMinutes();
					html += '<div class="comment" id="'+value.comment_id+'">'+
								'<hr />'+
								'<h5>'+value.user_name+'<span>'+timestr+'</span></h5>'+
								'<div>'+
									value.comment_content+
								'</div>'+
								'<div class="zan">'+
									'<i></i>'+
									'<span class="zan-num">'+value.zan_count+'</span>'+
								'</div>'+
							'</div>';
				});
				$(".comment-all").append(html);
				
				//已点赞的评论增加zan-end类
				zanovers.forEach(function(zan,i){
					var $id = "#"+zan.comment_id;
					if($($id).length!=0){
						$($id).find("i").addClass("zan-end");
					}
				});
				showmore();
				
			})
		}
	}
	
	
	window.newest = $(".comment-type>a").eq(0);
	window.hot = $(".comment-type>a").eq(1);
	newest.click(function(){
		comment_color(newest);
		$(".comment-all").html("");
		getcomments(type_number,'newest')
	});
	hot.click(function(){
		comment_color(hot);
		$(".comment-all").html("");
		getcomments(type_number,'hot');
	});
	
	//界面加载完毕自动获取最新评论
	newest.click();
	showmore();
	
	
	//点赞
	$(".comment-all").on('click',".zan>i",function(){
		if($(this).hasClass("zan-end")) return;
		var that = $(this);
		var comment_id = $(this).parent().parent().attr("id");
		$._ajax({
			url: "addZan",
			data : {
				comment_id : comment_id 
			}
		}).done(function(back){
			if(back == "ok"){
				that.addClass("zan-end");
				that.next("span").text(Number.parseInt(that.next("span").text())+1);
			}
		});
	});
	
	
})
