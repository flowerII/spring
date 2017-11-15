$(function(){
	
	//获取活动年份
	$._ajax({
		url : 'activity_yearslist_notpublicsh'
	}).done(function(data){
		console.log(data);
		var html = "";
		html = '<option value="-1">请选择活动年份</option>';
		list = data.list;
		list=JSON.parse(list);
		if(list.length!=0){
			list.forEach(function(year,i){
				html += '<option value="'+year.activity_year+'">'+year.activity_year+'</option>';
			});
		}
		$("#years").append(html);
	})
	
	//获取活动
	$("#years").change(function(){
		if($("#years").val()=='-1') return;
		$._ajax({
			url : 'activity_get_by_year',
			data:{
				activity_year:$("#years").val()
			}
				
		}).done(function(data){
			var html = "";
			html = '<option value="-1">请选择相应的活动</option>';
			console.log(data)
			activities = data.activitylist;
			activities = JSON.parse(activities);
			console.log(activities)
			if(activities.length!=0){
				activities.forEach(function(activity,i){
					html += '<option value="'+activity.activity_id+'">'+activity.activity_name+'</option>';
				});
			}
			$("#activities").append(html);
		})
	})
	
	//获取活动稿件
	$("#activities").change(function(){
		if($("#activities").val()=='-1') return;
		var src = "article_list?page=1&activity="+$("#activities").val();
		$("#articlesIframe").attr('src',src);
		$("#articlesIframe").get(0).onload=function(){
			$("#articlesIframe").height($("#articlesIframe").contents().find("html").height());
		}
	})
	
})