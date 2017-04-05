<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${!empty results}">    
<div class="pager">
<c:if test="${pageView.currentPage<=1}"><span class="flip prev over"><i class="ico prev">‹</i></span></c:if>
<c:if test="${pageView.currentPage>1}"><a href="${url}&page=${pageView.currentPage-1}" class="flip prev"><i class="ico prev">‹</i></a></c:if>
<c:if test="${pageView.totalPage>pageView.pagecode}">
	<c:if test="${pageView.currentPage > pageView.pagecode/2+1}">
		<a href="${url}&page=1" class="flip">1</a>
		<a href="${url}&page=2" class="flip">2</a>
		<span class="ellipsis">...</span>
	</c:if>
	<c:forEach begin="${pageView.pageindex.startindex}" end="${pageView.pageindex.endindex}" var="wp">
	    <c:if test="${pageView.currentPage==wp}"><a href="${url}&page=${wp}" class="flip active">${wp}</a></c:if>
	    <c:if test="${pageView.currentPage!=wp}"><a href="${url}&page=${wp}" class="flip">${wp}</a></c:if>
	</c:forEach>
	<c:if test="${pageView.currentPage < pageView.totalPage - pageView.pagecode/2}">
		<span class="ellipsis">...</span>
		<a href="${url}&page=${pageView.totalPage-1}" class="flip">${pageView.totalPage-1}</a>
		<a href="${url}&page=${pageView.totalPage}" class="flip">${pageView.totalPage}</a>
	</c:if>
</c:if>
<c:if test="${pageView.totalPage<=pageView.pagecode}">
  <c:forEach begin="${pageView.pageindex.startindex}" end="${pageView.pageindex.endindex}" var="wp">
    <c:if test="${pageView.currentPage==wp}"><a href="${url}&page=${wp}" class="flip active">${wp}</a></c:if>
    <c:if test="${pageView.currentPage!=wp}"><a href="${url}&page=${wp}" class="flip">${wp}</a></c:if>
	</c:forEach>
</c:if>
<c:if test="${pageView.currentPage>=pageView.totalPage}"><span class="flip next over"><i class="ico next">›</i></span></c:if>
<c:if test="${pageView.currentPage<pageView.totalPage}"><a href="${url}&page=${pageView.currentPage+1}" class="flip next"><i class="ico next">›</i></a></c:if>
</div>
</c:if>

