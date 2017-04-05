<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<div class="index_header_con">
		<h1><!--  <div class="logo"><a href="http://www.1gw.com">
        <img src="http://www.1gw.com/themes/newfirst/images/logo.png" alt=""></a>
    </div> -->
<a href="http://www.1gw.com">
<img src="http://img01.1gw.com/images/1c/35/84/feae16c2b01350088664f9ba9562bd0c035c11b1.png">
</a>

</h1>
		<h2 class="ad_part">
			

<a href="http://www.1gw.com/list.html?n=%E7%B2%BD%E5%AD%90" title="粽子" target="_blank"> <img src="http://img01.1gw.com/0457a927466e4030fc605f13c1642224046871eb.jpg" alt="粽子"> </a>		</h2>
		<div class="search_part">
<div class="search_part1">
	<form action="search.do" method="get" id="goods_search">
		<div class="input_txt"><input type="text" name="keyword" class="inputstyle" value="${empty keyword ? '女神节':keyword}" placeholder="女神节" autocomplete="off"></div>
		<input type="hidden" id="sg" name="sg" value="">
		<input type="button" class="inputbtn" id="btn-search-new" value="搜索">
	</form>
	<div class="search_part3" style="display: none;">
	<ul></ul>
	<p><span>关闭</span></p>
</div>
</div>
<div class="search_part2">
	<p>
				<a href="http://www.1gw.com/list.html?n=%E5%84%BF%E7%AB%A5%E8%8A%82" class="current" target="_blank">儿童节</a>
					<a href="http://www.1gw.com/list.html?n=%E5%8D%8E%E4%B8%BA" target="_blank">华为</a>
					<a href="http://www.1gw.com/list.html?n=%E7%B2%BD%E5%AD%90" class="current" target="_blank">粽子</a>
					<a href="http://www.1gw.com/list.html?n=%E7%A9%BA%E8%B0%83" target="_blank">空调</a>
					<a href="http://www.1gw.com/list.html?n=%E9%9D%A2%E8%86%9C" class="current" target="_blank">面膜</a>
					<a href="http://www.1gw.com/list.html?n=%E5%87%89%E9%9E%8B" target="_blank">凉鞋</a>
					<a href="http://www.1gw.com/list.html?n=%E7%AC%94%E8%AE%B0%E6%9C%AC" class="current" target="_blank">笔记本</a>
					<a href="http://www.1gw.com/list.html?n=%E7%A9%BA%E8%B0%83%E8%A2%AB" target="_blank">空调被</a>
					<a href="http://www.1gw.com/list.html?n=%E7%BA%A2%E9%85%92" class="current" target="_blank">红酒</a>
					<a href="http://www.1gw.com/list.html?n=OPPO" target="_blank">OPPO</a>
			</p>
</div>
<style type="text/css">
	.search_part1{position: relative;}
	.search_part3{width: 406px;border: 1px solid #CCC;border-top: none;background: #fff;position:absolute;top: 40px;left: 0px;z-index: 99999999;}
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
  	$(".search_part3").show();
  });
  
  function getData() {
  	$.ajax({
 			url: "suggest.do",
 			type: "post",
 			data: "keyword=" + $(".search_part1 input[name='keyword']").val(),
 			success: function(data) {
 				var result = eval("(" + data + ")");
 				$(".search_part3 ul").empty();
 				$.each(result, function(i) {
 					var li;
 					if(i == 0) {
	  				li = '<li class="current"><a href="search.do?keyword=' + result[i] + '" data-word="' + result[i] +'">' + result[i] +'</a></li>';
 						$("#sg").val(result[i]);
 					} else {
	  				li = '<li class=""><a href="search.do?keyword=' + result[i] + '" data-word="' + result[i] +'">' + result[i] +'</a></li>';
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


  $(".search_part3 li").mouseover(function(e){
  	$(this).addClass('current').siblings().removeClass('current');
  	console.log($(this).attr("class"));
  	 $(".search_part1 input[name='keyword']").val($(".search_part3 .current a").data('word'));
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
			<p>400 822 0333</p>
		</div>
	</div>
