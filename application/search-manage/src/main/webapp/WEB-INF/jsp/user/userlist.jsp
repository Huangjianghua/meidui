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
  <div class="type">您当前的位置：<a href="javascript:void(0)" title="">系统管理</a> &gt; <a href="${ctx}/user/showListPage.do" title="">用户管理</a></div>
   <div class="sale" style="height:30px">
	<table class="table table-bordered table-condensed">
		<!-- <tr>
			<td style="text-align: right;">状态</td>
			<td style="text-align: left;" >
			</td>
		</tr> -->
		<tr>
			
		</tr>
		<tr>
			<td colspan="11">
				   账号:<input type="text" id="userName" name="userName" autocomplete="off">
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
						<td>&nbsp;${item.enUserName}</td>
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
						<c:if test="${ item.enUserName!='admin'}">
							   <a href="${ctx}/user/toeditPage.do?userId=${item.id}">编辑</a>
							  <c:if test="${item.status=='y'}">
							     <a href="${ctx}/user/deleteUser.do?userId=${item.id}&status=n">禁用</a>
							   </c:if>
							   <c:if test="${item.status=='n'}">
							     <a href="${ctx}/user/deleteUser.do?userId=${item.id}&status=y">启用</a>
							  </c:if>
						  </c:if> 
						</td>
		              </tr>
	              </c:if>
	              <c:if test="${temp.index % 2 != 0}">
	                 <tr class="even">
						<td>&nbsp;${item.enUserName}</td>
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
						 <c:if test="${ item.enUserName!='admin'}">
							  <a href="${ctx}/user/toeditPage.do?userId=${item.id}">编辑</a>
							 
							  <c:if test="${item.status=='y'}">
							    <a href="${ctx}/user/deleteUser.do?userId=${item.id}&status=n">禁用</a>
							  </c:if> 
							   <c:if test="${item.status=='n'}">
							   <a href="${ctx}/user/deleteUser.do?userId=${item.id}&status=y">启用</a>
							  </c:if> 
						  </c:if>
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
       </div> 
</form>
</body>
</html>