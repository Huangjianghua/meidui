<%@ page language="java" pageEncoding="UTF-8"%>

<c:if test="${pageView.currentPage>=10}">
   <a href="javascript:topage(1)" class="a03">第1页.....</a>
	<c:forEach begin="${pageView.pageindex.startindex}" end="${pageView.pageindex.endindex}" var="wp">
	    <c:if test="${pageView.currentPage==wp}"><b><a href="javascript:void(0)" class="cur">第${wp}页</a></b></c:if>
	    <c:if test="${pageView.currentPage!=wp}"><a href="javascript:topage('${wp}')" class="a03">第${wp}页</a></c:if>
	</c:forEach>
</c:if>
<c:if test="${pageView.currentPage<10}">
  <c:forEach begin="${pageView.pageindex.startindex}" end="${pageView.pageindex.endindex}" var="wp">
    <c:if test="${pageView.currentPage==wp}"><b><a href="javascript:void(0)" class="cur">第${wp}页</a></b></c:if>
    <c:if test="${pageView.currentPage!=wp}"><a href="javascript:topage('${wp}')" class="a03">第${wp}页</a></c:if>
</c:forEach>
</c:if>
<font color="red">
   到 <input type="text" name="gopage" id="gopage" class="txt">页 <a href="javascript:topage()" class="btn" >前往</a>
   | 当前页:第${pageView.currentPage}页 | 总记录数:${pageView.totalCount}条 <input type="hidden" id="pagecount" value="${pageView.totalPage}"> 
   | 每页显示:${pageView.pageSize}条 
   | 总页数:${pageView.totalPage}页
 </font>　