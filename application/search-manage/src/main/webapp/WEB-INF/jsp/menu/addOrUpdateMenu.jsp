<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增修改菜单</title>
<%@include file="/WEB-INF/common/js.jsp"%>
<%@include file="/WEB-INF/common/taglib.jsp" %>
<style>
.leftnav {
	float: left;
	width: 420px;
}
.w-add-form {
	margin-left: 120px;
	padding: 30px 45px;
	min-height: 500px;
}
.add-table {
	min-width: 500px;
}
.add-table th {
	background-color: #ded;
}
.add-table td, .add-table th{
	height: 40px;
	border: 1px solid  #ccc;
	padding: 5px;
}
</style>
<script type="text/javascript">
$(function(){
$('input:radio[name="parentRadio"]').click(function(){
		$("#result_table1").hide();
		$("#id").val("");
		$("#name").val("");
		$("#url").val("");
		$("#pic").val("");
	});
})
	
</script>
</head>
<body>
   <form action="${ctx}/menu/addOrUpdateMenu.do" class="w-add-form" method="post">
	<table class="add-table">
		<tr  class="tc" style="display: none;">
			<td>id</td>
			<td>
				<input id="id" name="id" readonly="readonly" value="${menu.id}"/>
			</td>
		</tr>
			<tr>
				<th colspan="2" class="tc">当前选中的菜单</th>
			</tr>
			<tr>
				<td width="50" class="tc">名称</td>
				<td><input type="text" name="name" id="name" value="${menu.name}" data-rule="名称:required;"></td>
			</tr>
			<tr>
				<td class="tc">url</td>
				<td><input type="text" name="url" id="url" value="${menu.url}" ></td>
			</tr>
			<c:if test="${menu.pid==0}">
				<tr>
					<td class="tc">图标</td>
					<td><input type="text" name="pic" id="pic" value="${menu.pic}" " data-rule="图标:required;" ></td>
				</tr>
			</c:if>
			
		</table>
		
	 <!-- 子菜单项 -->	
	 <table class="add-table" id="result_table1">
			<tr>
				<th colspan="2" class="tc">添加子菜单  顶级模块<input type="radio" name="parentRadio" value="0"/></th>
			</tr>
			<tr>
				<td width="50" class="tc">名称</td>
				<td><input type="text" name="subItem.name" id="subItem.name"  class="w-c-text"></td>
			</tr>
			<tr>
				<td class="tc">url</td>
				<td><input type="text" name="subItem.url" id="subItem.url"  class="w-c-text"></td>
			</tr>
			<%-- <tr>
				<td class="tc">图标</td>
				<td><input type="text" name="pic" id="pic" value="${menu.pic}" ></td>
			</tr> --%>
			<!-- <tr>
				<td colspan="2">
				   <button type="submit" class="btn btn-success">
							<i class="icon-ok icon-white"></i> 保存
				   </button>
				</td>
			</tr> -->
		</table>
		
		<table>
		   <tr>
				<td colspan="2">
				   <button type="submit" class="btn btn-success">
							<i class="icon-ok icon-white"></i> 保存
				   </button>
				</td>
			</tr>
		</table>
		
	</form>

</body>
</html>