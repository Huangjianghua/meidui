<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/common/js.jsp"%>
<%@include file="/WEB-INF/common/taglib.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.min.css"  type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="${ctx}/js/zTree3.1/jquery.ztree.all-3.1.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="${ctx}/js/system/role.js"></script>
<link rel="stylesheet" href="${ctx}/js/zTree3.1/css/zTreeStyle/zTreeStyle.css"  type="text/css">
<title>新增角色</title>
</head>
<body>
   <form action="${ctx}/role/addRole.do" name="rolefrom" id="rolefrom" method="post" autocomplete="off" >
		<input id="insertOrUpdate" type="hidden"
			value='<s:property value="role.insertOrUpdate"/>' />
		
				<table class="table table-bordered" style="width: 500px;margin: auto;">
					<tr>
						<td colspan="2" style="background-color: #dff0d8;text-align: center;">
							<strong>角色编辑</strong>
						</td>
					</tr>
					<tr style="display: none;">
						<th>id</th>
						<td><!-- <s:hidden name="role.id" id="id" /> --></td>
					</tr>
					<tr>
						<th style="background-color: #dff0d8;text-align: center;">角色名称</th>
						<td style="text-align: left;">
						<!--<s:if test="role.id==null">
						 <s:textfield name="role.role_name" id="role_name"
									readonly="false" />
							</s:if> <s:else>
								<s:textfield name="role.role_name" id="role_name" />
							</s:else> -->
							<input type="text" id="role_name" name="role_name" data-rule="名称:required;userName;length[2~10];remote[${ctx}/role/checkRoleName.do]""  />
						</td>
					</tr>
					<tr>
						<th style="background-color: #dff0d8;text-align: center;">角色描述</th>
						<td style="text-align: left;">
							<!-- <s:textfield
							name="role.role_desc" id="role_desc" /> -->
							<input type="text" id="role_desc" name="role_desc">
					  </td>
					</tr>
					<%-- <tr>
						<th style="background-color: #dff0d8;text-align: center;">数据库权限</th>
						<td style="text-align: left;">
							<s:select list="#{'select':'select','select,insert':'select,insert','select,insert,update':'select,insert,update','select,insert,update,delete':'select,insert,update,delete'}" 
							name="role.role_dbPrivilege" id="role_dbPrivilege"/>
						</td>
					</tr> --%>
					<tr>
						<th style="background-color: #dff0d8;text-align: center;">状态</th>
						<td style="text-align: left;" >
							<select id="status" name="status"  class="input-small">
				              <option value="y">启用</option>
				              <option value="n">禁用</option>
				           </select>
						</td>
					</tr>
					<tr>
						<th style="background-color: #dff0d8;text-align: center;">角色权限</th>
						<td>
							<div id="optionDiv">
								<!-- [<a id="expandOrCollapseAllBtn" href="#" title="展开/折叠全部资源" onclick="return false;">展开/折叠</a>] -->
								[<a id="checkAllTrueOrFalse" href="javascript:void(0)" title="全选/全不选" onclick="checkAllNodes();">全选</a>]
								[<a id="checkAllTrueOrFalse" href="javascript:void(0)" title="全选/全不选" onclick="cancelAllNodes();">全不选</a>]
<!-- 								[<a id="expandAllBtn" href="#" title="全部节点展开" onclick="return false;">展开</a>] -->
<!-- 								[<a id="collapseAllBtn" href="#" title="全部节点折叠" onclick="return false;">折叠</a>] -->
							</div>
							<ul id="tree" class="ztree" style="width:260px; overflow:auto;"></ul>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;" colspan="2">
					       <button type="submit" class="btn btn-danger" id="savaRole" >
							  <i class="icon-ok icon-white"></i>保存
						    </button>
						</td>
					</tr>
				</table>
	</form>
</body>
</html>