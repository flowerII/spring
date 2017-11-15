$(function(){
	
	/**
	 *  wiper 滚动
	 **/
	var mySwiper = new Swiper('.swiper-container',{
		//分页
		//pagination : '.pagination',
		pagination : '.swiper-pagination',
		paginationClickable :true,
		DOMAnimation : false,	  	
		//自动滚动 
		autoplay : 2000,
		prevButton:'.swiper-button-prev',
		nextButton:'.swiper-button-next',
		loop: true
		//其他设置
	}); 
	
	$('.swiper-button-prev').hide();
	$('.swiper-button-next').hide();
		  
	 $('.swiper-container').mouseenter(function(){
	 	$('.swiper-button-prev').show();
	 	$('.swiper-button-next').show();
		mySwiper.stopAutoplay();
	})
	$('.swiper-container').mouseleave(function(){
		$('.swiper-button-prev').hide();
	 	$('.swiper-button-next').hide();
		mySwiper.startAutoplay();
	})
	
	

	/**
	 *     ajax  获取最新消息 、 征稿活动 
	 * */

	$._ajax({
		url: 'news_get_home_5',
	}).then(function(data){
		//5条最新消息
		//news = JSON.parse(data.newslist);
		news = data.newslist;
		var $content = $(".news").find("ol");
		var html = "";
		news.forEach(function(value){
			var item = '<li class="list-group-item "><a href="news_content?news='+value.new_id+'">'+value.new_name+'</a></li>';
			html += item;
		});
		$content.html(html);
	});
	
	
	
	$._ajax({
		url: 'activity_get_home_5',
	}).then(function(data){
		//5条最新征稿活动
		//activities = JSON.parse(data.activities);
		activities = data.activities
		console.log(activities)
		var $content = $(".activities").find("ol");
		var html = "";
		activities.forEach(function(activity){
			var item = '<li class="list-group-item "><a href="activity_content?activity='+activity.activity_id+'">'+activity.activity_name+'</a></li>';
			html += item;
		});
		$content.html(html);
	});
	
	
	
})
