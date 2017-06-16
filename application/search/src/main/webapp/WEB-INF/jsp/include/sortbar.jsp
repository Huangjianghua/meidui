<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="search_condition">
	<div class="search_tj">
		<ul>
			<li class='<c:if test="${empty orderBy or orderBy eq 'buy_count_desc'}">current</c:if>'>
				<a href="${url}&r_sort=buy_count_desc" class="btn btn-tab is-desc ">综合</a>
			</li>
			
			<li class='<c:if test="${orderBy eq 'sold_quantity_desc' or orderBy eq 'sold_quantity_asc'}">current</c:if>'>
				<c:if test="${orderBy eq 'sold_quantity_desc'}">
					<a href="${url}&r_sort=sold_quantity_asc" class="btn btn-tab is-desc ${fn:contains(orderBy, 'sold_quantity') ? 'on':''}">销量<img src="http://www.meiduimall.com/themes/newfirst/images/images/icon_up.png" /></a>
				</c:if>
				<c:if test="${orderBy ne 'sold_quantity_desc'}">
					<a href="${url}&r_sort=sold_quantity_desc" class="btn btn-tab is-desc">销量<img src="http://www.meiduimall.com/themes/newfirst/images/images/icon_down.png" /></a>
				</c:if>
			</li>
			
			<li class='<c:if test="${orderBy eq 'list_time_desc' or orderBy eq 'list_time_asc'}">current</c:if>'>
				<c:if test="${orderBy eq 'list_time_desc'}">
					<a href="${url}&r_sort=list_time_asc" class="btn btn-tab is-desc <c:if test="${orderBy == 'list_time_desc'}">on</c:if>">新品<img src="http://www.meiduimall.com/themes/newfirst/images/images/icon_down.png" /></a>
				</c:if>
				<c:if test="${orderBy ne 'list_time_desc'}">
					<a href="${url}&r_sort=list_time_desc" class="btn btn-tab is-asc <c:if test="${orderBy == 'list_time_asc'}">on</c:if>">新品<img src="http://www.meiduimall.com/themes/newfirst/images/images/icon_up.png" /></a>
				</c:if>
			</li>
			
			<li class='<c:if test="${orderBy eq 'rate_count_desc' or orderBy eq 'rate_count_asc'}">current</c:if>'>
				<c:if test="${orderBy eq 'rate_count_desc'}">
					<a href="${url}&r_sort=rate_count_asc" class="btn btn-tab is-desc <c:if test="${orderBy == 'rate_count_asc'}">on</c:if>">评论<img src="http://www.meiduimall.com/themes/newfirst/images/images/icon_down.png" /></a>
				</c:if>
				<c:if test="${orderBy ne 'rate_count_desc'}">
					<a href="${url}&r_sort=rate_count_desc" class="btn btn-tab is-desc <c:if test="${orderBy == 'rate_count_desc'}">on</c:if>">评论<img src="http://www.meiduimall.com/themes/newfirst/images/images/icon_up.png" /></a>
				</c:if>
			</li>
			
			<li class='<c:if test="${orderBy eq 'price_desc' or orderBy eq 'price_asc'}">current</c:if>'>
				<c:if test="${orderBy eq 'price_asc'}">
					<a href="${url}&r_sort=price_desc" class="btn btn-tab is-desc <c:if test="${orderBy == 'price_desc'}">on</c:if>">价格<img src="http://www.meiduimall.com/themes/newfirst/images/images/icon_up.png" /></a>
				</c:if>
				<c:if test="${orderBy ne 'price_asc'}">
					<a href="${url}&r_sort=price_asc" class="btn btn-tab is-desc <c:if test="${orderBy == 'price_asc'}">on</c:if>">价格<img src="http://www.meiduimall.com/themes/newfirst/images/images/icon_down.png" /></a>
				</c:if>
			</li>
		</ul>
	</div>
	<div class="search_result">
		<strong><i>${empty results ? 0:results.totalCount}</i>件商品  </strong>
	</div>
	
	<%@ include file="/WEB-INF/jsp/include/pager.jsp" %>
</div>
