<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="filter">
<!--   <div class="filter-title">
    <span class="highlight"><{$screen.keyword}></span>-商品筛选
  </div> -->
  <div class="filter-list">
    <c:if test="${!empty results.brands}">
    <!-- 品牌 -->
    <%@ include file="/WEB-INF/jsp/include/filter/brand.jsp" %>
    </c:if>

    <c:if test="${!empty results.cats}">
    <!-- 分类 -->
    <%@ include file="/WEB-INF/jsp/include/filter/cat.jsp" %>
    </c:if>

    <c:if test="${!empty results.properties}">
    <!-- 自然属性 -->
    <%@ include file="/WEB-INF/jsp/include/filter/props.jsp" %>
    </c:if>
	
  </div>
</div>
