<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
  .bread-crumbs{font-size: 14px; margin-top: 10px; line-height: 25px; height: 25px;}
</style>
<div class="content">
	<div class="site_wrap15">
		<div class="site_wrap15_con">
	
	    <!--推荐店铺信息-->
	      <%@ include file="/WEB-INF/jsp/include/shop.jsp" %>
	
	    <!--筛选条件-->
	      <%@ include file="/WEB-INF/jsp/include/filter.jsp" %>
	
	    <!--排序-->
	      <%@ include file="/WEB-INF/jsp/include/sortbar.jsp" %>
	
	    <!--显示搜索商品-->
	      <%@ include file="/WEB-INF/jsp/include/item.jsp" %>
	
	    <!--分页-->
	      <%@ include file="/WEB-INF/jsp/include/pager.jsp" %>
		</div>
	</div>
</div>
