<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增用户</title>
<%@include file="/WEB-INF/common/js.jsp"%>
<%@include file="/WEB-INF/common/taglib.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.min.css"  type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
  <form action="${ctx}/user/addUser.do" method="post">
		<table class="table table-bordered" style="width: 700px;margin: auto;">
			<tr>
				<td colspan="2" style="text-align: center;">
					<strong>帐号编辑</strong>
				</td>
			</tr>
			<tr style="display:none;">
				<th>id</th>
				<td><input type="hidden"></td>
			</tr>
			<tr>
				<th class="td_right" style="text-align:right;">帐号</th>
				<td style="text-align: left;">
					<input type="text" id="userName" name="userName"  data-rule="帐号:required;username;length[2~10];remote[${ctx}/user/checkUsername.do]" />
				</td>
			</tr>
			<tr>
				<th class="td_right" style="text-align: right;">昵称</th>
				<td style="text-align: left;">
				<input type="text" id="nickName" name="nickName" data-rule="昵称:required;nickname;length[1~20]">
			  </td>
			</tr>
			
			<!-- <s:if test="e.id=='' or e.id==null"> -->
				<tr>
					<th class="td_right" style="text-align: right;">密码</th>
					<td style="text-align: left;">
					   <input type="password" id="password" name="password" data-rule="密码:required;password;length[6~20];">
					</td>
				</tr>
				<!-- <tr>
					<th class="td_right">确认密码</th>
					<td style="text-align: left;">
					  <input type="text" id="password1" name="password1" data-rule="密码:required;password;length[6~20];">
					</td>
				</tr> -->
		<!-- 	</s:if> -->
			<!-- <s:else>
				<tr>
					<th class="td_right">密码</th>
					<td style="text-align: left;"><s:password name="e.password" data-rule="密码:password;length[6~20];" 
							id="password" />
							<br>(不输入表示不修改密码)
					</td>
				</tr>
				<tr>
					<th class="td_right">确认密码</th>
					<td style="text-align: left;"><s:password name="e.newpassword2" data-rule="确认密码:match(e.password)" 
							id="newpassword2" />
					</td>
				</tr>
			</s:else> -->
			
			<tr>
				<th class="td_right" style="text-align: right;">选择角色</th>
				<td style="text-align: left;">
					<!-- <s:select name="rid" value="e.rid" list="roleList" id="rid" 
						listKey="id" listValue="role_name"></s:select> -->
				 <select id="rid" name="rid"  class="input-small">
				   <c:forEach items="${roleList}" var="item">
				     <c:if test="${item.status=='y'}">
				      <option value="${item.id}">${item.role_name}</option>
				     </c:if>
				 </c:forEach>
				</select>
				</td> 
				
			</tr>
			<!-- <s:if test="e.username==null or e.username==''  or !e.username.equals(\"admin\")"> -->
				<tr>
					<th class="td_right" style="text-align: right;">状态</th>
					<td style="text-align: left;" >
						<select id="status" name="status"  class="input-small">
				         <option value="y">启用</option>
				         <option value="n">禁用</option>
				       </select>
					</td>
				</tr>
			<!-- </s:if> -->
			<tr >
				<td colspan="2" style="text-align: center;">
					<%-- 	<a class="btn btn-success" href="${ctx}/user/addUser.do">
						  <i class="icon-ok icon-white"></i>保存
				       </a> --%>
				       <!-- <button class="icon-ok icon-white">保存</button> -->
<!-- 						<button class="btn btn-success" type="submit" > -->
<!-- 							<i class="icon-plus-sign icon-white"></i> 新增 -->
<!-- 						</button> -->
<%-- 						<s:a method="insert" cssClass="btn btn-success"> --%>
<!-- 							<i class="icon-plus-sign icon-white"></i> 新增 -->
<%-- 						</s:a> --%>
<!-- 						<a href="javascript:void(0)" onclick="$('#form').submit()" class="btn btn-success"> -->
<!-- 							<i class="icon-plus-sign icon-white"></i> 新增 -->
<!-- 						</a> -->
						<button type="submit" class="btn btn-danger">
							<i class="icon-ok icon-white"></i> 保存
						</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>