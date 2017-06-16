<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/common/js.jsp"%>
<%@include file="/WEB-INF/common/taglib.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.min.css"  type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap/js/bootstrap.min.js"></script>
<title>用户列表</title>
</head>
<body>
<form action="${ctx}/user/showListPage.do"  method="post">
    <input type="hidden"  id="currentPage" name="currentPage" value="1"/>
	<table class="table table-bordered table-condensed">
		<!-- <tr>
			<td style="text-align: right;">状态</td>
			<td style="text-align: left;" >
			</td>
		</tr> -->
		<tr>
			<td colspan="11">
				账号:<input type="text" id="userName" name="userName">
			</td>
		</tr>
		<tr>
			<td colspan="11">
					<button  class="btn btn-danger" type="submit" >
						<i class="icon-search icon-white"></i> 查询
					</button>
					
					<a class="btn btn-danger" href="${ctx}/user/toaddPage.do">
						<i class="icon-plus-sign icon-white"></i> 添加
				    </a>
				<div style="float: right;vertical-align: middle;bottom: 0px;top: 10px;">
				</div>
			</td>
		</tr>
	</table>
	
	
	<%-- <table class="table table-bordered table-hover">
		<thead>
		<tr style="background-color: #dff0d8">
			<!-- <th width="20"><input type="checkbox" id="firstCheckbox"/></th> -->
			<th style="display: none;">id</th>
			<th>帐号</th>
			<th>昵称</th>
			<th>创建时间</th>
			<th>角色</th>
			<th>状态</th>
			<th nowrap="nowrap">操作</th>
		</tr></thead>
		<c:forEach items="${pageView.dateList}" var="item">
			<tr >
				<td  style="display: none;"></td>
				<td>&nbsp;${item.id}</td>
				<td>&nbsp;${item.userName}</td>
				<td>&nbsp;${item.nickName}</td>
				<td>&nbsp;${item.createTime}</td>
				<td>&nbsp;${item.role_name}</td>
				<c:if test="${item.status=='y'}">
				  <td>&nbsp;启用</td>
				</c:if>
				<c:if test="${item.status=='n'}">
				   <td>&nbsp;禁用</td>
				</c:if>
				<td>&nbsp;${item.status}</td>
				<td>
				  <a href="${ctx}/user/toeditPage.do?userId=${item.id}">编辑</a>
				   <a href="${ctx}/user/deleteUser.do?userId=${item.id}">禁用</a>
				</td>
			</tr>
		</c:forEach>
         <th><td colspan="16" style="text-align:center;"><%@include file="/WEB-INF/common/page.jsp"%></td>
		</th>
	</table>
	 --%>
	
	<div class="sale_c">
          <table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="#ffffff">
            <tbody><tr class="even">
            <td style="display: none;">id</td>
			<td>帐号</td>
			<td>昵称</td>
			<td>创建时间</td>
			<td>角色</td>
			<td>状态</td>
			<td nowrap="nowrap">操作</td>
            </tr>
             	</tbody><tbody id="tbody">
             <c:forEach items="${pageView.dateList}" var="item" varStatus="temp">
               <c:if test="${temp.index % 2 == 0}">
		            <tr>
						<td>&nbsp;${item.userName}</td>
						<td>&nbsp;${item.nickName}</td>
						<td>&nbsp;${item.createTime}</td>
						<td>&nbsp;${item.role_name}</td>
						<c:if test="${item.status=='y'}">
						  <td>&nbsp;启用</td>
						</c:if>
						<c:if test="${item.status=='n'}">
						   <td>&nbsp;禁用</td>
						</c:if>
						<td>
						  <a href="${ctx}/user/toeditPage.do?userId=${item.id}">编辑</a>
						   <a href="${ctx}/user/deleteUser.do?userId=${item.id}">禁用</a>
						</td>
		              </tr>
	              </c:if>
	              <c:if test="${temp.index % 2 != 0}">
	                 <tr class="even">
						<td>&nbsp;${item.userName}</td>
						<td>&nbsp;${item.nickName}</td>
						<td>&nbsp;${item.createTime}</td>
						<td>&nbsp;${item.role_name}</td>
						<c:if test="${item.status=='y'}">
						  <td>&nbsp;启用</td>
						</c:if>
						<c:if test="${item.status=='n'}">
						   <td>&nbsp;禁用</td>
						</c:if>
						<td>
						  <a href="${ctx}/user/toeditPage.do?userId=${item.id}">编辑</a>
						   <a href="${ctx}/user/deleteUser.do?userId=${item.id}">禁用</a>
						</td>
		              </tr>
	              </c:if>
	              
	            </c:forEach>
	            <%-- <th><td colspan="16" style="text-align:center;"><%@include file="/WEB-INF/common/page.jsp"%></td>
		        </th> --%>
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