<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="list">
	<c:if test="${!empty results}">
	<ul>
	<c:forEach var="item" items="${results.dataList }">
		<li>
			<div class="box9">
				<div class="pic">
					<a href="http://www.meiduimall.com/item.html?item_id=${item.itemId }" target="blank">
						<img src="${item.image}" alt="${item.imgTitle}" title="${item.imgTitle}"/>
					</a>	
				</div>
				<div class="item-info">
					<h2>
						<a href="http://www.meiduimall.com/item.html?item_id=${item.itemId }" title="${item.imgTitle}" target="blank">${item.title }</a>
					</h2>
					<h3>
      				   美兑价：<strong><fmt:formatNumber pattern="#0.00" value="${item.price}"/></strong>
					</h3>
					<h3><em class="text-danger">最多可用<fmt:formatNumber pattern="#0" value="${item.point}"/>积分</em>(1积分抵1元)</h3>
					<h4>店铺：${item.shopName }</h4>
				</div>
			</div>
		</li>
	 </c:forEach>
	</ul>
	</c:if>
	<c:if test="${empty results}">
		<div style="color:red; text-align: center;">
			抱歉，没有搜索到“${keyword}”相关商品
		</div>
	</c:if>
</div>
