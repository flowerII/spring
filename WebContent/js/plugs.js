/*
 *   表单验证
 */

~function(){
	$.fn.extend({
		isEmpty: function(){
			var reg = /^\S+$/;
			return reg.test($(this).val());
		},
		isEmail: function(){
			var reg = /^\S+@\S+\.\S+$/;
			return reg.test($(this).val());
		}
	})
}()


/*
 * 对ajax的二次封装
 * */

~function(){
	$.extend({
		'_ajax': function(obj){
			var _option = extend(obj,{
				type : "post"
			});
			
			return $.ajax(_option).fail(function(err){
				if(err.status == 404){
					window.location.href = "404.html";
				}
				if(err.status == 500){
					window.location.href = "500.html";
				}
			});
		}
	});
	
	function extend(obj,source){
		for(var key in obj){
			source[key] = obj[key];
		}
		return source;
	}
}()


/**
 * 	分页 封装
 * */
// element 添加位置 ，   i 当前页数，   all 总页数
~function(){
	$.page = function(element,i,all,req){
		var all = all;
		all = Number.parseInt(all)
		if(all==0) all=1;
		var req = req || null;
		if(req!=null) req += '&';
		i = Number.parseInt(i);
		var html = '<div class="page">'+
					'<div>'+
						'<nav aria-label="Page navigation">'+
						  '<ul class="pagination" style="vertical-align: middle;">'+
						    '<li>'+
						      '<a href="?'+req+'page='+(i-1)+'" aria-label="Previous">'+
						        '<span aria-hidden="true">&laquo;</span>'+
						      '</a>'+
						    '</li>'+
						    '${page}'+
						    '<li>'+
						      '<a href="?'+req+'page='+(i+1)+'" aria-label="Next">'+
						        '<span aria-hidden="true">&raquo;</span>'+
						      '</a>'+
						    '</li>'+
						  '</ul>'+
						  '<div style="display: inline-block; vertical-align: middle;">'+
						  	'<input type="number" id="pagenum" style="border-radius: 3px; border: 1px solid #dddddd; text-align: center; width: 80px;"/>'+
						  	'<button class="btn btn-info" id="gopage">跳转</button>'+
						  '</div>'+
						'</nav>'+
					'</div>'+
					'<div>当前'+i+'页，共'+all+'页</div>'+
				'</div>';
					
		var lis = '';
		if(i>all||i<1){
			html = "";
			$(element).append(html);
			return;
		}
		if(all>=3){
			if(i==1){
				for(var k=i;k<=i+2;k++){
					lis += '<li><a href="?'+req+'page='+k+'">'+k+'</a></li>';
				}
				html = html.replace("${page}",lis);
				$(element).append(html);
				$(element).find(".page").find('li').eq(i).addClass("active");
				$(element).find(".page").find('li').eq(0).click(function(){return false});	
			}else if(i==all){
				for(var k=all-2;k<=all;k++){
					lis += '<li><a href="?'+req+'page='+k+'">'+k+'</a></li>';
				}
				html = html.replace("${page}",lis);
				$(element).append(html);
				$(element).find(".page").find('li').eq(3).addClass("active");
				$(element).find(".page").find('li').eq(4).click(function(){return false});
			}else{
				for(var k=i-1;k<=i+1;k++){
					lis += '<li><a href="?'+req+'page='+k+'">'+k+'</a></li>';
				}
				html = html.replace("${page}",lis);
				$(element).append(html);
				$(element).find(".page").find('li').eq(2).addClass("active");
			}		 
		}else{
			for(var k=1;k<=all;k++){
				lis += '<li><a href="?'+req+'page='+k+'">'+k+'</a></li>';
			}
			html = html.replace("${page}",lis);
			$(element).append(html);
			$(element).find(".page").find('li').eq(i).addClass("active");
			if(i==1){
				$(element).find(".page").find('li').eq(0).click(function(){return false});
			}
			if(i==all){
				$(element).find(".page").find('li').eq(all+1).click(function(){return false});
			}		
		}
		
		//检测最大值与最小值
		$(element).find("#pagenum").get(0).onkeyup = function(event){
			if(window.event) event=window.event;
			if($(this).val() == "") return;
			if(Number.parseInt($(this).val())>all){
				$(this).val(all);
			}
			if(Number.parseInt($(this).val())<1){
				$(this).val(1);
			}
		}
		
		
		$(element).find("#gopage").click(function(){
			location.search = "?"+req+"page="+$("#pagenum").val();
		})
	}
}();



/**
 * 	wangEditor 封装
 * */
~function(){
	$.fn.extend({
		Editor: function(height,width,model,length){
			var height = height||'100%';
			var width = width||'100%';
			var model = model||null;
			var length = length||null;
			var $text = $("<textarea></textarea>");
			$text.height(height);
			$text.width(width);
			if(length){
				$text.attr("maxlength",length);
			}
			$(this).append($text);
			
			var editor = new wangEditor($text);
			
			 
			 
			 var lis = [];
			 var num = 1;
			 for(var i=0;i<50;i++){
			 	var emotion={
				 	icon: '../fonts/static/emotions/default/'+num+'.gif',
				 	value: '['+num+']' 
				 }
			 	lis.push(emotion);
			 	num++;
			 }
			 

		    // 表情
		    editor.config.emotions = {
		        'default': {
		            title: '默认',
		            data: lis
		        }
		    };
		    
		    // 普通的自定义菜单
		    if(!model){
		    	editor.config.uploadImgUrl = 'http://120.77.168.12:8080/spring/upload';
			 	editor.config.uploadImgFileName = 'image';
		    	editor.config.menus = [
			        'source',
			        '|',
			        'bold',
			        'underline',
			        'italic',
			        'strikethrough',
			        'eraser',
			        'forecolor',
			        'bgcolor',
			        '|',
			        'quote',
			        'fontfamily',
			        'fontsize',
			        'head',
			        'unorderlist',
			        'orderlist',
			        'alignleft',
			        'aligncenter',
			        'alignright',
			        '|',
			        'link',
			        'unlink',
			        'table',
			        'emotion',
			        '|',
			        'img',
			        'video',
			        'location',
			        'insertcode',
			        '|',
			        'undo',
			        'redo',
			        'fullscreen'
			     ];
		    }else if(model=="user"){
		    	editor.config.menus = [
			        'emotion',
			        'fullscreen'
			     ];
		    }
		    
		    
			
			editor.create();
			$(".menu-item>a").click(function(){
				$(".wangEditor-drop-panel.clearfix").css({'max-width':'100%','margin-left':'0','left':'auto'}).addClass("col-xs-12");
				$('.tip-triangle').hide();
			});
			
			return editor;
		},
	})
}()