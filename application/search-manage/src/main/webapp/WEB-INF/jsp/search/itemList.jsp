<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/common/js.jsp"%>
<%@include file="/WEB-INF/common/taglib.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.min.css"  type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/site.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/my97/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/site.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/dateformat.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
<style type="text/css">
	#queryType {width:100px; margin-right: 10px;}
	ul {display: inline;}
	ul li {display:none;}
	.active{display: inline;}
	input{margin: 0 10px;}
	.txt{width:80px;}
</style>
<script type="text/javascript">
$(function() {
	$("#queryType").change(function() {
		$(".active>input[type='text']").val('');
		var i = $(this).val();
		$("ul>li").eq(i).addClass('active').siblings().removeClass('active');
	});
	
	$("#clearParams").click(function() {
		$(':input','form').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	});
});
</script>
<title>商品信息列表</title>
</head>
<body>
<form action="${ctx}/item/showListPage.do"  method="post">
    <input type="hidden"  id="currentPage" name="currentPage" value="1"/>
	<table class="table table-bordered table-condensed">
		<tr>
			<td colspan="11">
			  <select id="queryType">
			  	<option value="0">商品查询</option>
			  	<option value="1">类目查询</option>
			  	<option value="2">品牌查询</option>
			  	<option value="3">店铺查询</option>
			  </select>
			  <ul>
				  <li class="active">
					商品ID:<input type="text" id="itemId" class="txt" name="itemId" value="${itemId}" autocomplete="off" />
					商品名:<input type="text" id="title" name="title" value="${title}" autocomplete="off" />
				  </li>
				  <li>
					类目ID:<input type="text" id="catId" class="txt" name="catId" value="${catId}" autocomplete="off" />
					类目名:<input type="text" id="cat" name="cat" value="${cat}" autocomplete="off" />
				  </li>
				  <li class="brand">
					品牌ID:<input type="text" id="brandId" class="txt" name="brandId" value="${brandId}" autocomplete="off" />
					品牌名:<input type="text" id="brand" name="brand" value="${brand}" autocomplete="off" />
				  </li>
				  <li>
					店铺ID:<input type="text" id="shopId" class="txt" name="shopId" value="${shopId}" autocomplete="off" />
					店铺名:<input type="text" id="shopName" name="shopName" value="${shopName}" autocomplete="off" />
				  </li>
			  </ul>
				<button class="btn btn-danger" type="button" id="clearParams" >
					<i class="icon-search icon-white"></i> 清除条件
				</button>&nbsp;&nbsp;
				<button class="btn btn-danger" type="submit" id="query" >
					<i class="icon-search icon-white"></i> 查询
				</button>
			</td>
		</tr>
	</table>
	
	<div class="sale_c">
    <table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="#ffffff">
      <tbody>
      	<tr class="even">
	      <td style="display: none;">id</td>
			<td>商品ID</td>
			<td>商品名</td>
			<td>价格</td>
			<td>成本价</td>
			<td>父级类目</td>
			<td>类目ID</td>
			<td>类目</td>
			<td>品牌ID</td>
			<td>品牌</td>
			<td>店铺ID</td>
			<td>店铺名</td>
        </tr>
       </tbody>
       <tbody id="tbody">
          <c:forEach items="${pageView.dateList}" var="item" varStatus="temp">
	        <tr class="${temp.index % 2 == 0 ?'':'even' }">
				<td>&nbsp;${item.itemId}</td>
				<td>&nbsp;${item.title}</td>
				<td>&nbsp;${item.price}</td>
				<td>&nbsp;${item.costPrice}</td>
				<td>&nbsp;${item.catPath}</td>
				<td>&nbsp;${item.catId}</td>
				<td>&nbsp;${item.cat}</td>
				<td>&nbsp;${item.brandId}</td>
				<td>&nbsp;${item.brand}</td>
				<td>&nbsp;${item.shopId}</td>
				<td>&nbsp;${item.shopName}</td>
		      </tr>
          </c:forEach>
          </tbody>
          </table>
        </div>
        <dl class="page">
         <dt>
         <dd><%@include file="/WEB-INF/common/page.jsp"%></dd>
        </dl>
</form>
</body>
</html>