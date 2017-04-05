// JavaScript Document
$(function(){
	//根据总页数判断，如果小于5页，则显示所有页数，如果大于5页，则显示5页。根据当前点击的页数生成
	 $("#checkAll").click(function() {
         $('input[name="cb_oil"]').attr("checked",this.checked); 
     });
	 
	//生成分页按钮
	if(pageCount>5){
		page_icon(1,5,0);
	}else{
		page_icon(1,pageCount,0);
	}
	
	$("#btn_go").click("click",function(){
		console.log($("#txt_page_custom").val())
		var t = $("#txt_page_custom").val();
		if(pageCount > 5){
			var pageNum = parseInt(t);//获取当前页数
			pageGroup(pageNum,pageCount);
			
		}else{
			$(this).addClass("on");
			$(this).siblings("li").removeClass("on");
		}
		jump(pageNum);
		pageGroup(pageNum,pageCount);
	});
	
	//点击分页按钮触发
	$("#pageGro li").live("click",function(){
		
		if(pageCount > 5){
			var pageNum = parseInt($(this).html());//获取当前页数
			pageGroup(pageNum,pageCount);
		}else{
			$(this).addClass("on");
			$(this).siblings("li").removeClass("on");
		}
		jump(parseInt($(this).html()))
	});
	
	function jump(i) {
		$.get(pageLink+i,{}, function(data,status) {
			if(status == 'success') {
				loadData(data);
			} else {
				alert('加载数据失败!')
			}
		});
		//location.href = pageLink + i;
	}
});


//点击跳转页面
function pageGroup(pageNum,pageCount){
	switch(pageNum){
		case 1:
			page_icon(1,5,0);
		break;
		case 2:
			page_icon(1,5,1);
		break;
		case pageCount-1:
			page_icon(pageCount-4,pageCount,3);
		break;
		case pageCount:
			page_icon(pageCount-4,pageCount,4);
		break;
		default:
			page_icon(pageNum-2,pageNum+2,2);
		break;
	}
}

//根据当前选中页生成页面点击按钮
function page_icon(page,count,eq){
	var ul_html = "";
	for(var i=page; i<=count; i++){
		ul_html += "<li>"+i+"</li>";
	}
	$("#pageGro ul").html(ul_html);
	$("#pageGro ul li").eq(eq).addClass("on");
}