<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css">
  .bread-crumbs{font-size: 14px; margin-top: 10px; line-height: 25px; height: 25px;}
  .search-shop{border: 1px solid #E6E6E6;margin: 0px auto;height: 98px;padding: 0 30px;margin-top: 10px;}
  .search-shop dl{float: right;width: 250px;margin-top: 20px;height: 80px}
  .search-shop dl dt{float: left;}
  .search-shop dl dd{float: right;}
  .search-shop dl dd a{display: block;    margin-top: 15px;}
  .search-shop dl dd a span{height: 30px;line-height: 30px;}
  .search-shop .shop-img{float: left;display: block;width: 80px;height:80px;margin-top: 10px;}
  .search-shop h1{padding-left:90px;padding-top:25px;font-size: 16px;font-weight: bold;line-height: 30px;height: 30px;}
  .search-shop h2{padding-left:10px;color: #ccc;font-size: 12px;white-space: nowrap;width: 700px;text-overflow: ellipsis;overflow: hidden;line-height: 20px;}
  .search-shop p{line-height:20px;height:20px;color: #ccc;font-size: 12px;}
  .shop-box { position: relative; height: 110px; overflow: hidden;}
  .more {position: relative;height:20px;text-align: center;margin-bottom: 10px;visibility: hidden;}
  .more span{margin: 0 auto; font-size: 12px;background: #E6E6E6;width: 60px;border-radius: 0px 0px 3px 3px;display: block;cursor:pointer;padding:2px 0px;}
  .more span:hover{background: #122a45;color: #fff;}
  .btn-danger:hover>span {background-color: #022a45;}
  .btn-danger>span {border-color: #122a45; background-color: #122a45; color: #fedb6a;}
</style>
<script type="text/javascript">
$(function(){
	var slide= false;
	if ( $(".search-shop").length >= 2 ){
		$(".more").css({visibility:"visible"});
    }		
	$(".more span").click(function(){
		if (!slide){
			var h = $(".search-shop").length *110;
			$(".shop-box").animate({height: h + "px"},1000, function() {
				$(".more span").html("&#9650");
			});
			slide = true;
		}else{
			$(".shop-box").animate({height: "110px"},1000, function() {
				$(".more span").html("&#9660");
			});
			slide = false;
		}
	});
});
</script>
<div class="content">
	<div class="site_wrap15">
		<div class="site_wrap15_con">
    <c:if test="${!empty results}">
    <!--推荐店铺开始-->
    <div class="shop-box">
    	<c:forEach var="shopInfo" items="${results.shopInfos }" varStatus="status">
			<c:if test="${status.index < 3 }">
				<div class="search-shop">
					<a class="shop-img" href="http://www.meiduimall.com/search.html?shop_id=${shopInfo.shopId}"><img src="${shopInfo.shopLogo!='default'?shopInfo.shopLogo:'http://www.meiduimall.com/themes/newfirst/images/images/shop_def.jpg'}" alt="${shopInfo.shopName}" height="80" width="80"></a>
					<dl>
						<dt>
							<p>店铺评分：${shopInfo.tallyScore}分</p>
							<p>服务评分：${shopInfo.attitudeScore}分</p>
							<p>配送评分：${shopInfo.deliverySpeedScore}分</p>
						</dt>
						<dd>
							<a class="btn btn-danger btn-rounded" href="http://www.meiduimall.com/search.html?shop_id=${shopInfo.shopId}"><span><span>进入店铺</span></span></a>
						</dd>
					</dl>
					<h1>${shopInfo.shopName}</h1>
					<h2>简介:${shopInfo.shopDescript}</h2>
				</div>
				</c:if>
			</c:forEach>
    </div>
		<div class="more">
			<span>&#9660</span>
		</div>
		<!--推荐店铺结束-->
		</c:if>
