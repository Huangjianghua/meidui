<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/common/js.jsp"%>
<%@include file="/WEB-INF/common/taglib.jsp" %>
<script type="text/javascript" charset="UTF-8" >
/**
 * 角色禁用
 */
function  disabled(rid){
	  $.ajax({
			url:ctx+"/role/checkRoleUser.do?rid="+rid,
			type:"post",
			//data:data,
			dataType:"json",
			success : function(rt) {
				if(rt){
					alert("该角色下有用户不能禁用")
				}else{
					window.location.href=ctx+'/role/deleteRole.do?rid='+rid+'&status=n';
				}
			}
		})
}
</script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.min.css"  type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap/js/bootstrap.min.js"></script>
<title>用户列表</title>
</head>
<body>
<form action="${ctx}/role/showRoleList.do"  method="post">
<input type="hidden"  id="currentPage" name="currentPage" value="1"/>
 <div class="type">您当前的位置：<a href="javascript:void(0)" title="">系统管理</a> &gt; <a href="${ctx}/role/showRoleList.do" title="">角色管理</a></div>
   <div class="sale" style="height:30px">
	<table class="table table-bordered table-condensed">
		<!-- <tr>
			<td style="text-align: right;">状态</td>
			<td style="text-align: left;" >
			</td>
		</tr> -->
		<tr>
			<td colspan="11">
			      角色名称:<input type="text" id="role_name" name="role_name">
					<button  class="btn btn-danger" type="submit">
						<i class="icon-search icon-white"></i> 查询
					</button>
					<a class="btn btn-danger" href="${ctx}/role/toAddRolePage.do">
						<i class="icon-plus-sign icon-white"></i> 添加
				    </a>
				<div style="float: right;vertical-align: middle;bottom: 0px;top: 10px;">
				</div>
			</td>
		</tr>
	</table>
<%-- 	
	<table class="table table-bordered table-hover">
		<thead>
		<tr style="background-color: #dff0d8">
			<!-- <th width="20"><input type="checkbox" id="firstCheckbox"/></th> -->
			<th  style="display: none;">rid</th>
			<th>角色名称</th>
			<th>角色描述</th>
			<th>状态</th>
			<th width="80px">操作</th>
		</tr></thead>
		<c:forEach items="${pageView.dateList}" var="item">
			<tr >
				<td  style="display: none;"></td>
				<td>&nbsp;${item.id}</td>
				<td>&nbsp;${item.role_name}</td>
				<td>&nbsp;${item.role_desc}</td>
				<c:if test="${item.status=='y'}">
				  <td>&nbsp;启用</td>
				</c:if>
				<c:if test="${item.status=='n'}">
				   <td>&nbsp;禁用</td>
				</c:if>
				<td>
				<a href="${ctx}/role/toEditRolePage.do?rid=${item.id}">编辑</a>
				<a href="${ctx}/role/deleteRole.do?rid=${item.id}">禁用</a>
				</td>
			</tr>
		</c:forEach>
          <th><td colspan="16" style="text-align:center;"><%@include file="/WEB-INF/common/page.jsp"%></td>
		</tr>
	</table> --%>
	
	
	<div class="sale_c">
          <table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="#ffffff">
            <tbody><tr class="even">
           <td  style="display: none;">rid</td>
			<td>角色名称</td>
			<td>角色描述</td>
			<td>状态</td>
			<th width="80px">操作</th>
            </tr>
             	</tbody><tbody id="tbody">
            <c:forEach items="${pageView.dateList}" var="item" varStatus="temp">
	             <c:if test="${temp.index % 2 == 0}">
		            <tr>
						<td>&nbsp;${item.role_name}</td>
						<td>&nbsp;${item.role_desc}</td>
						<c:if test="${item.status=='y'}">
						  <td>&nbsp;启用</td>
						</c:if>
						<c:if test="${item.status=='n'}">
						   <td>&nbsp;禁用</td>
						</c:if>
						<td>
						<a href="${ctx}/role/toEditRolePage.do?rid=${item.id}">编辑</a>
						<c:if test="${item.status=='y'}">
						 <%--  <a href="${ctx}/role/deleteRole.do?rid=${item.id}&status=n">禁用</a> --%>
						  <a href='javascript:void(0)' onclick="disabled('${item.id}')">禁用</a>
						</c:if>
						 <c:if test="${item.status=='n'}">
						   <a href="${ctx}/role/deleteRole.do?rid=${item.id}&status=y">启用</a>
						 </c:if>
						</td>
		            </tr>
		           </c:if> 
		           
		            <c:if test="${temp.index % 2 != 0}">
			            <tr class="even">
							<td>&nbsp;${item.role_name}</td>
							<td>&nbsp;${item.role_desc}</td>
							<c:if test="${item.status=='y'}">
							  <td>&nbsp;启用</td>
							</c:if>
							<c:if test="${item.status=='n'}">
							   <td>&nbsp;禁用</td>
							</c:if>
							<td>
							<a href="${ctx}/role/toEditRolePage.do?rid=${item.id}">编辑</a>
							<c:if test="${item.status=='y'}">
						     <%--  <a href="${ctx}/role/deleteRole.do?rid=${item.id}&status=n">禁用</a> --%>
						      <a href='javascript:void(0)' onclick="disabled('${item.id}')">禁用</a>
					    	</c:if>
						   <c:if test="${item.status=='n'}">
						     <a href="${ctx}/role/deleteRole.do?rid=${item.id}&status=y">启用</a>
						  </c:if>
							</td>
			            </tr>
		           </c:if> 
		           
	           </c:forEach>
	           <%--  <th><td colspan="16" style="text-align:center;"><%@include file="/WEB-INF/common/page.jsp"%></td>
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