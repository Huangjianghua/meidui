<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="bread-crumbs">
	您当前的位置： 
	<span>
		<c:choose>
			<c:when test="${!empty results and list ne 'list'}">
				<a href="search.do?keyword=${keyword}&sc_type=all" alt="" title="">全部结果</a>
			</c:when>
			<c:otherwise>
				<a href="http://www.meiduimall.com" alt="" title="">首页</a>
			</c:otherwise>
		</c:choose>
	</span>
	<span>&raquo;</span>
	
	<c:choose>
		<c:when test="${results.status eq 'l' and !empty results.catModel}">
			<c:set var="cat" value="${results.catModel}"></c:set>
			<c:if test="${cat.level > 1}">
			<span class="now"><a href="search.do?keyword=${cat.parentName}&cid=${cat.parentId}" alt="" title="">${cat.parentName}</a></span>
			<span>&raquo;</span>
			</c:if>
			<span class="now"><a href="search.do?keyword=${cat.catName}&cid=${cat.catId}" alt="" title="">${cat.catName}</a></span>
			<!-- <span>&raquo;</span> -->
		</c:when>
		<c:when test="${results.status eq 'b'}">
			<c:set var="brands" value="${results.brands}" />
			<c:forEach var="brand" items="${brands}" varStatus="status">
				<c:if test="${status.first}">
					<span class="now"><a href="${url}&brand=${brand.brandId}" alt="" title="">品牌：${brand.brand}</a></span>
					<span>&raquo;</span>
				</c:if>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<c:if test="${!empty cat}">
				<span class="now"><a href="${url}" alt="" title="">${cat.catName}</a></span>
				<span>&raquo;</span>
			</c:if>
		</c:otherwise>
	</c:choose>
	<c:if test="${!empty brand}">
		<span class="now"><a href="${url}" alt="" title="">品牌：${brand.brand}</a></span>
		<span>&raquo;</span>
	</c:if>
	<span class="now"></span>
</div>
