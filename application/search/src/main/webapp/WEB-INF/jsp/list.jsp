<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta content="zh-cn" http-equiv="content-language" />
    <meta charset="utf-8" />
<!--↑↑模板中请务必使用HTML5的标准head结构↑↑-->
<meta name="generator" content="1GW-b2b2c" />
<meta name="author" content="1GW" />
<title>${keyword}</title><meta name="keywords" content="${keyword}" /><meta name="description" content="壹购物综合网上购物商城笔记本__频道涵盖等商品的最新导购、报价、图片、型号等相关信息，为您提供最优质的网购生活！" />
<link rel="icon" href="${ctx}/statics/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="${ctx}/statics/favicon.ico" type="image/x-icon" />
<script src="${ctx}/statics/js/zh_CN/lang.js?static"></script>
<script src="${ctx}/statics/js/first_header.js?static"></script>

<link href="${ctx}/statics/css/topc_all.css?static" rel="stylesheet" media="screen, projection" />
<script src="${ctx}/statics/js/topc_header.js?static"></script>
<!-- <link href="http://www.meiduimall.com//app/topc/statics/stylesheets/ui-dialog.css?static" rel="stylesheet" media="screen, projection" />
<script src="http://www.meiduimall.com//app/topc/statics/scripts/dialog-min.js?static"></script> -->
<link rel="stylesheet" type="text/css" href="${ctx}/statics/css/style.css?v=424" />
<link rel="stylesheet" type="text/css" href="${ctx}/statics/css/index.css"/>
<!-- <script type="text/javascript" src="http://www.meiduimall.com//themes/newfirst/images/home/js/site.js"></script>
<script type="text/javascript" src="http://www.meiduimall.com//themes/newfirst/images/home/js/jquery.lazyload.js"></script> -->
<!-- 百度主动推送工具 -->
<script>
 (function(){ 
     var bp = document.createElement('script'); 
     bp.src = '//push.zhanzhang.baidu.com/push.js'; 
     var s = document.getElementsByTagName("script")[0]; 
     s.parentNode.insertBefore(bp, s); 
 })(); 
</script>


</head>
<!--右侧工具栏开始-->
<div class="fixedbox">
	<div class="rightbar">
		<div class="rightbar_con">
			

<div class="cartbox">
<div  class="cart_box">
	<div class="hd">
		<h3>我的购物车</h3>
	</div>
<div class="bd" id="cart_main_min_min">
<div class="neirong">
 <form action="http://www.meiduimall.com//cart-checkout.html" method="post" class="cart-submit-form">
		<div class="fmbox">
	</div>
</form>
</div>
	<div class="total_cart">
		<div class="total_cart1">
			<div class="left">
				<p>
					<a href="http://www.meiduimall.com//cart.html">去购物车</a>
				</p>
			</div>
			<div class="right">
				<p><i id="cart_total_num" style="color:#e13f4a;">0</i>件商品</p>
				<p>共计：<u id="goods_price">￥0.00</u></p>
			</div>
		</div>
		<div class="total_btn">
			<p>
				<a href="http://www.meiduimall.com//cart.html" id="cart_counts">立即结算</a>
			</p>
		</div>
	</div>
	<div class="total_cart_empty" style="display: none;">
		<div class="total_cart1" style="margin-top: 70%;">
			<p style="text-align: center;">购物车空空的，赶快去挑选心仪的商品吧~</p><p style="text-align: center;color:red;margin-top:5px;"><a href="./" style="color:red;">去首页看看</a></p>
		</div>
	</div>
</div>
</div>
</div>
<ul id="navs">
	<li class="hasdrop">
		<a href="#"><i class="icon_1"></i>
		</a>
		<div class="drop swx"><strong></strong>
			<div class="pic"><img src="http://www.meiduimall.com//themes/first/images/images/ewm_pic1.png" /><u>扫一扫关注公众号</u></div>
			
		</div>
	</li>
	<li class="hasdrop">
		<a href="#"><i class="icon_2"></i>
		</a>
		<div class="drop"><strong></strong>
		<div class="txt">
							<a href="http://www.meiduimall.com//member-index.html">会员中心</a>
		    		</div>
		</div>
	</li>
	<li class="hasdrop">
		<a href="#"><i class="icon_3"></i>
		</a>
		<div class="drop"><strong></strong>
		<div class="txt">
							<a href="http://www.meiduimall.com//member-collectitems.html">我的收藏</a>
		    		</div>
		</div>
	</li>
		<li class="hasdrop cart_part">
		<a href="javascript:void(0);" class="icon_cart" data-cart-num="0"><i class="icon_4"></i><span>购物车</span><b id="total_cart_number_new">0</b></a>
	</li>
		<!-- <li class="hasdrop">
		<a href="#"><i class="icon_5"></i>
		</a>
		<div class="drop"><strong></strong>
		<div class="txt"><a href="#">在线客服</a></div>
		</div>
	</li> -->
	<li class="hasdrop">
		<a href="#"><i class="icon_6"></i><em>回顶</em>
		</a>
	</li>
</ul>
<script>
//function cartMsg(){
   	$(".icon_del2").click(function(e){
        e.preventDefault();
        if(confirm('你确定要删除吗？')){
            var el = $(this),
                cartId = el.data('cart-id');
            checkCart.deleteItem(cartId);
            checkCart.updateItem(el);
        }
    });
   	
    var checkCart = {
            updateItem : function(el){
                var data = $('#cart_main_min .cart-submit-form').serialize();
                $.ajax({
                    url:'http://www.meiduimall.com//cart-update.html',
                    type:'POST',
                    data: data,
                    success:function(re) {
                        if(re){
                            if(re.success){
                            	var totalPrice = $("#goods_price").text();
                                totalPrice = totalPrice.substring(1,totalPrice.length);
                                var price = el.data('cart-price');
                                $("#goods_price").text("￥"+parseFloat(parseFloat(totalPrice)-price).toFixed(2))
                                $("#goods_price").attr("data-price",(parseFloat(totalPrice)-price));
                                
                                var delNum = parseInt(el.data('cart-num'));
                                var num = parseInt($("#cart_total_num").text());
                                $("#cart_total_num").text(num - delNum);
                                $("#total_cart_number_new").text(num - delNum);
                            }else{
                              $(el).val($(el).attr('value'));
                              Message.error(re.message);
                            }
                        }
                    }
                });
            },
            updateAll : function(){
                var data = $('#cart_main_min .cart-submit-form').serialize();
                $.ajax({
                    url:'http://www.meiduimall.com//cart.html',
                    type:'POST',
                    data:data,
                    success:function(rs){
                        //$('#cart_main_min').html(rs.message);
                    }
                });
            },
            deleteItem : function(id){
                if(id){
                    $.ajax({
                        url:'http://www.meiduimall.com//cart-remove.html',
                        type:'POST',
                        data:'cart_id['+id+']=1',
                        success:function(rs){
                            if(rs.error) Message.error(rs.message);
                            if(rs.success){
                            	var liNum = $('.cart-row-new[data-cart-id='+id+']').parent("ul").children("li").length;
                            	var ulNum = $('.cart-row-new[data-cart-id='+id+']').parent("ul").length;
                            	if(liNum==1) {
                            		 $('.cart-row-new[data-cart-id='+id+']').parents("div[class='fl']").remove();
                            	} else {
                            		 $('.cart-row-new[data-cart-id='+id+']').remove();
                            	}
                            	if(ulNum==0) {
                            		$(".total_cart").hide();
                            		$(".total_cart_empty").show();
                            	}
                            }
                        }
                    });
                }
            }
    }
    
    $('#cart_main_min').on('click','.decrease',function(e){
        var el = $(this),
            min = el.data('min'),
            input = $(el.next('s').children('input'))
            cart_id = el.data('cart-id'),
            value = input.val() - 0;
        if(value > min){
            input.val(value - 1);
            checkCart.updateItem(el);
            el.removeClass('disabled');
        }else{
            el.addClass('disabled');
        }
    }).on('click','.increase',function(e){
        var el = $(this),
            max = el.data('max'),
            input = $(el.prev('s').children('input')),
            cart_id = el.data('cart-id'),
            value = input.val() - 0;
        if(value < max){
            input.val(value + 1);
            checkCart.updateItem(el);
            el.removeClass('disabled');
        }else{
            el.addClass('disabled');
        }
    }).on('change','.cart-num',function(e){
      checkCart.updateItem(this);
    }).on('click','.submit-cart',function(e){
        var checked = $('.cart-checked-item').filter(function(){
            var tmp = $(this);
            if(tmp.prop('checked')){
                return this;
            }
        });
        if(checked.length == 0){
            return Message.error('请先勾选需要购买的商品');
        }
    });
    
//}
function getCounts(){
	var counts = 0;
	 $(".cart-num").each(function(){
		 counts += parseInt($(this).val());
	 });
	 $("#total_cart_number_new").text(counts);
}
</script>		</div>
	</div>
</div>
<!---右侧工具栏结算-->

  <body>
    <input type="hidden" id="current_xfc" value="0.121"/>
<script type="text/javascript">
	function getMyXFX(){
		var currentXFC = $("#current_xfc").val();
	}
</script>

<div class="nav">

	<c:set var="url" value="list?cat_id=${catId}" />
		<c:if test="${!empty brands}">
			<c:set var="url" value="${url}&brand=${brands}" />
		</c:if>
		<c:if test="${!empty prop}">
			<c:set var="url" value="${url}&prop=${prop}" />
		</c:if>
	<%@ include file="/WEB-INF/jsp/include/nav.jsp" %>
</div>
<!-- 公告挂件开始 -->
<%@ include file="/WEB-INF/jsp/include/notice_scroll.jsp" %>
<!-- 公告挂件结束 -->

<div class="index_header">
	<%@ include file="/WEB-INF/jsp/include/index_header.jsp" %>
</div>
<div class="index_nav">
	<%@ include file="/WEB-INF/jsp/include/index_nav.jsp" %>
</div>    
<div id="main" class="main clearfix">
  <div class="wrap-lg">
    
    <%@ include file="/WEB-INF/jsp/include/list_bread_crumbs.jsp" %>
    
    <%@ include file="/WEB-INF/jsp/include/index.jsp" %>
	</div>
</div>
<div class="footer">
	<%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
</div>
<!---网站底部结束-->
<script charset="utf-8" language="javascript" type="text/javascript" src="${ctx}/statics/js/first.js"></script>
<script>
	//图片懒加载
	/* $(function() {
		$("img.lazy").lazyload({threshold : 200}); 
	}); */

	var _hmt = _hmt || [];
	(function() {
	  var hm = document.createElement("script");
	  hm.src = "//hm.baidu.com/hm.js?b0873fb6f9590626b25444f6092651fb";
	  var s = document.getElementsByTagName("script")[0];
	  s.parentNode.insertBefore(hm, s);
	})();
</script>
  </body>
</html>

