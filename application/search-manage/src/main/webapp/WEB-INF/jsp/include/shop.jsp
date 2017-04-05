<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <c:if test="${!empty results.shopInfos and results.status eq 's'}">
<style type="text/css">
  .bread-crumbs{font-size: 14px; margin-top: 10px; line-height: 25px; height: 25px;}
  .search-shop{border: 1px solid #E6E6E6;margin: 10px auto;height: 100px;padding: 0 30px;}
  .search-shop dl{float: right;width: 250px;margin-top: 20px;height: 80px}
  .search-shop dl dt{float: left;}
  .search-shop dl dd{float: right;}
  .search-shop dl dd a{display: block;    margin-top: 15px;}
  .search-shop dl dd a span{height: 30px;line-height: 30px;}

  .search-shop .shop-img{float: left;display: block;width: 80px;height:80px;margin-top: 10px;}
  .search-shop h1{padding-left:90px;padding-top:25px;font-size: 16px;font-weight: bold;line-height: 30px;height: 30px;}
  .search-shop h2{padding-left:10px;color: #ccc;font-size: 12px;white-space: nowrap;width: 700px;text-overflow: ellipsis;overflow: hidden;line-height: 20px;}
  .search-shop p{line-height:20px;height:20px;color: #ccc;font-size: 12px;}
</style>
<c:forEach var="shopInfo" items="${results.shopInfos }" varStatus="status">
	<c:if test="${status.index == 0 }">
	<div class="search-shop">
		<a class="shop-img" href="http://www.1gw.com/search.html?shop_id=${shopInfo.shopId}"><img src="${shopInfo.shopLogo}" alt="${shopInfo.shopName}" height="80" width="80"></a>
		<dl>
			<dt>
				<p>店铺评分：${shopInfo.tallyScore}分</p>
				<p>服务评分：${shopInfo.attitudeScore}分</p>
				<p>配送评分：${shopInfo.deliverySpeedScore}分</p>
			</dt>
			<dd>
				<!-- <a class="btn btn-simple btn-rounded" href="#"><span><span>收藏店铺</span></span></a> -->
				<a class="btn btn-danger btn-rounded" href="http://www.1gw.com/search.html?shop_id=${shopInfo.shopId}"><span><span>进入店铺</span></span></a>
			</dd>
		</dl>
		<h1>${shopInfo.shopName}</h1>
		<h2>简介:${shopInfo.shopDescript}</h2>
	</div>
	</c:if>
</c:forEach>
</c:if> --%>
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
  .more {position: relative;height:20px;text-align: center;margin-bottom: 10px;}
  .more span{margin: 0 auto; font-size: 20px;background: #E6E6E6;width: 60px;border-radius: 0px 0px 3px 3px;display: block;cursor:pointer;}
  .more span:hover{background: #d9534f;color: #fff;}
</style>
<script type="text/javascript">
$(function(){
	var slide= false;
	$(".more span").click(function(){
		if (!slide){
			var h = $(".search-shop").length *110;
			$(".shop-box").animate({height: h + "px"},1000, function() {
				$(".more span").removeClass("icon-arrow-down").addClass("icon-arrow-up");
			});
			slide = true;
		}else{
			$(".shop-box").animate({height: "110px"},1000, function() {
				$(".more span").removeClass("icon-arrow-up").addClass("icon-arrow-down")
			});
			slide = false;
		}
	});
});
</script>
<div class="content">
	<div class="site_wrap15">
		<div class="site_wrap15_con">
	    <!-- 	      根据搜索条件推荐的店铺信息
	      <div class="search-shop">
  <a class="search-shop-pic" href="http://192.168.5.180/shopcenter.html?shop_id=163"><img src="" alt="恒泽手机专营店"></a>
  <div class="search-shop-txt">
    <div class="search-shop-title">恒泽手机专营店</div>
    <div></div>
  </div>
  <a href="http://192.168.5.180/shopcenter.html?shop_id=163" class="btn btn-link">进入店铺<i class="icon icon-chevron-right" data-icon="\2844"></i></a>
</div>

<c:forEach var="shopInfo" items="${results.shopInfos }" varStatus="status">
	<c:if test="${status.index == 0 }">
	<div class="search-shop">
		<a class="shop-img" href="http://www.1gw.com/search.html?shop_id=${shopInfo.shopId}"><img src="${shopInfo.shopLogo}" alt="${shopInfo.shopName}" height="80" width="80"></a>
		<dl>
			<dt>
				<p>店铺评分：${shopInfo.tallyScore}分</p>
				<p>服务评分：${shopInfo.attitudeScore}分</p>
				<p>配送评分：${shopInfo.deliverySpeedScore}分</p>
			</dt>
			<dd>
				<a class="btn btn-danger btn-rounded" href="http://www.1gw.com/search.html?shop_id=${shopInfo.shopId}"><span><span>进入店铺</span></span></a>
			</dd>
		</dl>
		<h1>${shopInfo.shopName}</h1>
		<h2>简介:${shopInfo.shopDescript}</h2>
	</div>
	</c:if>
</c:forEach>

 -->

    <c:if test="${!empty results}">
    <!--推荐店铺开始-->
    <div class="shop-box">
    	<c:forEach var="shopInfo" items="${results.shopInfos }" varStatus="status">
			<c:if test="${status.index < 3 }">
				<div class="search-shop">
					<a class="shop-img" href="http://www.1gw.com/search.html?shop_id=${shopInfo.shopId}"><img src="${shopInfo.shopLogo}" alt="${shopInfo.shopName}" height="80" width="80"></a>
					<dl>
						<dt>
							<p>店铺评分：${shopInfo.tallyScore}分</p>
							<p>服务评分：${shopInfo.attitudeScore}分</p>
							<p>配送评分：${shopInfo.deliverySpeedScore}分</p>
						</dt>
						<dd>
							<a class="btn btn-danger btn-rounded" href="http://www.1gw.com/search.html?shop_id=${shopInfo.shopId}"><span><span>进入店铺</span></span></a>
						</dd>
					</dl>
					<h1>${shopInfo.shopName}</h1>
					<h2>简介:${shopInfo.shopDescript}</h2>
				</div>
				</c:if>
			</c:forEach>
    </div>
		<div class="more">
			<span class="icon-arrow-down"></span>
		</div>
		<!--推荐店铺结束-->
		</c:if>
