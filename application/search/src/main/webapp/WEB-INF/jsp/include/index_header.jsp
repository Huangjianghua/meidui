<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.meiduimall.service.search.result.SearchWidget"%>
<%@page import="com.meiduimall.service.search.result.WidgetResult"%>
	<div class="index_header_con">
		<h1><!--  <div class="logo"><a href="http://www.meiduimall.com">
        <img src="http://www.meiduimall.com/themes/newfirst/images/logo.png" alt=""></a>
    </div> -->
<a href="http://www.meiduimall.com/" style="background:url(http://img01.1gw.com/212af8ad6aff58abfa4e617ca5b986752c41adf8.png) no-repeat left center;">
</a>
</h1>
		<h2 class="ad_part">
			<c:if test="${!empty widget and !empty widget.topAdWidget}">
				<a href="${widget.topAdWidget.adPicLink}" title="${widget.topAdWidget.adPicDesc}" target="_blank" style="background: url(${widget.topAdWidget.adPic}) no-repeat center center;"></a>
			</c:if>
		</h2>

		<div class="search_part">
<div class="search_part1">
	<form action="search" method="get" id="goods_search">
		<div class="input_txt"><input type="text" name="keyword" class="inputstyle" value="${keyword}" placeholder="${widget.searchWidget.hotkey}" autocomplete="off"></div>
		<input type="button" class="inputbtn" id="btn-search-new" value="搜索">
	</form>
	<div class="search_part3" style="display: none;">
	<ul></ul>
	<p><span>关闭</span></p>
</div>
</div>
<div class="search_part2">
<p>
<%
	WidgetResult widget = (WidgetResult)request.getAttribute("widget");
	if(widget != null && widget.getSearchWidget() != null) {
		List list = widget.getSearchWidget().getTopLinkTitle();
		if(list!= null && list.size() > 0) {
			for(int i = 0; i < list.size(); i++) {
				String key = java.net.URLEncoder.encode(list.get(i).toString(), "UTF-8");
				if(i%2 == 0) {
					out.print("<a href=\"search?keyword=" + key + "\" class=\"current\" target=\"_blank\">" + list.get(i) + "</a>");
				} else {
					out.print("<a href=\"search?keyword=" + key + "\" target=\"_blank\">" + list.get(i) + "</a>");
				}
			}
		}
	}
 %>
</p>
</div>
<style type="text/css">
	.search_part1{position: relative;}
	.search_part3{width: 421px;border: 1px solid #CCC;border-top: none;background: #fff;position:absolute;top: 36px;left: 0px;z-index: 99999999;}
	.search_part3 li{text-align: left;line-height: 30px;height: 30px;padding: 0 10px;}
	/*.search_part3 li:hover{ background: rgb(255,233,198); }*/
	.search_part3 .current{background: rgb(255,233,198);}
	.search_part3 li a{width: 100%;}
	.search_part3 li span{float: right;}
	.search_part3 p{text-align: right;padding:5px 10px;font-size: 14px;border-top: 1px solid #ccc;}
	.search_part3 p span {cursor: pointer;}
	.search_part3 p span:hover {color: #df0007;}
</style>

<script>
  $('#btn-search-new').click(function(){
  	var def = $(".search_part1 input[name='keyword']").attr("placeholder");
    $(".search_part1 input[name='keyword']").val( $(".search_part1 input[name='keyword']").val() === "" ? def : $(".search_part1 input[name='keyword']").val() );
    $('#goods_search').submit();
  });

  var closeSearch ;
  $(".search_part1 input[name='keyword']").keyup(function(event){
  	if ($(this).val()!=""){
  		var code = event.keyCode;
  		if(code != 38 && code != 40) {
  			getData();
  		}
  	}else{
  		$(".search_part3").hide();
  	}
  }).mouseover(function(){
  	clearTimeout(closeSearch);
  }).mouseout(function(){
  	closeSearch = 	setTimeout(function(){
  		$(".search_part3").hide();
  	},1000);
  });
  
  $(".search_part1 input[name='keyword']").focus(function() {
  	getData();
  });
  
  function getData() {
  	$.ajax({
 			url: "suggest",
 			type: "post",
 			data: "keyword=" + $(".search_part1 input[name='keyword']").val().trim(),
 			success: function(result) {
 				$(".search_part3 ul").empty();
 				$.each(result, function(i) {
 					var li;
 					if(i == 0) {
	  				li = '<li class="current"><a href="search?keyword=' + result[i] + '" data-word="' + result[i] +'">' + result[i] +'</a></li>';
 					} else {
	  				li = '<li class=""><a href="search?keyword=' + result[i] + '" data-word="' + result[i] +'">' + result[i] +'</a></li>';
 					}
		  		$(".search_part3 ul").append(li);
 				});
 				if(result.length > 0) {
		  		$(".search_part3").show();
 				} else {
 					$(".search_part3").hide();
 				}
 			}
 		});
  }

  $(".search_part3 p span").click(function(){
  	$(".search_part3").hide();
  });

  $(".search_part3").mouseover(function(){
  	clearTimeout(closeSearch);
  }).mouseout(function(){
  	closeSearch = 	setTimeout(function(){
  		$(".search_part3").hide();
  	},1000);
  });


  $(document).on("mouseover",".search_part3 li",function(){
    $(this).addClass('current').siblings().removeClass('current');
  });

  //上下箭头事件
  $(".search_part1 input[name='keyword']").keydown(function(event){  
  		var id = event.keyCode;
  		var count = $(".search_part3 li").length -1 ; //总条数
  		var current = $(".search_part3 li").index($(".search_part3 .current")) ? $(".search_part3 li").index($(".search_part3 .current")) : 0; //当前选择项
  		if (id == 38 && $(this).val()!='' && $(".search_part3").is(":visible")){
  			$(".search_part3 li").eq(current-1 < 0 ? count : current-1).addClass('current').siblings().removeClass('current');
  			$(this).val($(".search_part3 .current a").data('word'));
  		}
  		if (id == 40 && $(this).val()!= "" && $(".search_part3").is(":visible")){
  			$(".search_part3 li").eq( current+1 >  count ? 0 : current+1 ).addClass('current').siblings().removeClass('current');
  			$(this).val($(".search_part3 .current a").data('word'));
  		}	
  });
</script>
	</div>
		<div class="tel_kf">
			<div class="tel_box">
				<h2>400-622-6555</h2>
				<p>周一至周日 9:00-19:30</p>
			</div>
		</div>
	</div>
