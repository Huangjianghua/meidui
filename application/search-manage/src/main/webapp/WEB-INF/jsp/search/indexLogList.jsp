<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<title>索引日志列表</title>
</head>
<body>
<form action="${ctx}/indexLog/queryIndexLogs.do"  method="post">
    <input type="hidden"  id="currentPage" name="currentPage" value="1"/>
	<table class="table table-bordered table-condensed">
		<tr>
			<td colspan="11">
				<%-- 关键词:<input type="text" id="keyword" name="keyword" autocomplete="off" >
					
				    从：<input type="text" name="startTime" id="start_time" class="Wdate txt txt_date" value="${startTime}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss',firstDayOfWeek:1})"  placeholder="请选择日期" /> 
	    		至 <input type="text" name="endTime" id="end_time" class="Wdate txt txt_date" value="${endTime}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss',firstDayOfWeek:1})"  placeholder="请选择日期"/>
				<button class="btn btn-danger" type="submit" id="query" onclick="return query();" >
						<i class="icon-search icon-white"></i> 查询
					</button> --%>
			</td>
		</tr>
		<tr>
			<td colspan="11">
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
					<td>备注</td>
					<td>记录时间</td>
					<!-- <td nowrap="nowrap">操作</td> -->
        </tr>
       </tbody>
       <tbody id="tbody">
          <c:forEach items="${pageView.dateList}" var="item" varStatus="temp">
	        <tr class="${temp.index % 2 == 0 ?'':'even' }">
						<td>&nbsp;${item.remark}</td>
						<td>&nbsp;<fmt:formatDate value="${item.logTime}" type="both"/></td>
						<%-- <td>
						  <a href="${ctx}/indexLog/deleteIndexLog.do?logId=${item.logId}">删除</a>
						</td> --%>
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