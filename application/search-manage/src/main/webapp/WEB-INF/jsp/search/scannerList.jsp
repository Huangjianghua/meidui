<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/common/js.jsp"%>
<%@include file="/WEB-INF/common/taglib.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.min.css"  type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap/js/bootstrap.min.js"></script>
<title>待更新索引列表</title>
</head>
<body>
<form action="${ctx}/scanner/queryWaitScanner.do"  method="post">
    <input type="hidden"  id="currentPage" name="currentPage" value="1"/>
	<table class="table table-bordered table-condensed">
		<tr>
			<td colspan="11">
					<a class="btn btn-danger" href="${ctx}/scanner/manualUpdateAll.do">
						<i class="icon-plus-sign icon-white"></i> 全部更新
				    </a>
				<div style="float: right;vertical-align: middle;bottom: 0px;top: 10px;">
				</div>
			</td>
		</tr>
	</table>
	
	<div class="sale_c">
    <table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="#ffffff">
      <tbody>
      	<tr class="even">
	      	<td style="display: none;">id</td>
			<td>已更新表名</td>
			<td>更新ID</td>
			<td>操作类型</td>
			<td>更新时间</td>
			<td nowrap="nowrap">操作</td>
        </tr>
       </tbody>
       <tbody id="tbody">
          <c:forEach items="${pageView.dateList}" var="item" varStatus="temp">
           <tr class="${temp.index % 2 == 0 ?'':'even' }">
				<td>&nbsp;${item.tableName}</td>
				<td>&nbsp;${item.updateId}</td>
				<td>&nbsp;${item.operateType}</td>
				<td>&nbsp;${item.modifyTime}</td>
				<td>
				  <a href="${ctx}/scanner/manualUpdate.do?id=${item.scanId}">手动更新</a>
				</td>
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