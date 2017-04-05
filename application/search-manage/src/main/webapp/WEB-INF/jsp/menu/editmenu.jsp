<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单资源</title>
<%@include file="/WEB-INF/common/js.jsp"%>
<%@include file="/WEB-INF/common/taglib.jsp" %>
<script type="text/javascript" charset="UTF-8" src="${ctx}/js/zTree3.1/jquery.ztree.all-3.1.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="${ctx}/js/system/menu.js"></script>
<link rel="stylesheet" href="${ctx}/js/zTree3.1/css/zTreeStyle/zTreeStyle.css"  type="text/css">
</head>
<body>
<form action="" name="form1" style="display: none;">
	<!-- <s:hidden name="id" id="dfsfsf"></s:hidden> -->
</form>

<div class="type">您当前的位置：<a href="javascript:void(0)" title="">系统管理</a> &gt; <a href="${ctx}/menu/editMenu.do" title="">菜单管理</a></div>
<table class="table table-bordered" >
	<tr class="warning">
		<td colspan="2">
			<div>
				<!-- <font color="red">在父菜单下的所有子菜单全部勾选的情况下，是否级联删除父菜单：<input type="checkbox" id="deleteParent"></font><br>
				提示：点击菜单项，此处则能编辑该菜单项或增加顶级菜单或子菜单项。<br>  -->
				<input type="button" id="deleteMenus" value="删除选择的菜单" class="btn btn-danger"/>
				<font color="red">(删除父节点先删除子节点)</font>
				[<a id="expandOrCollapseAllBtn" href="javascript:void(0)" title="展开/折叠全部资源" onclick="expandAll(true)">展开</a>]
				[<a id="expandOrCollapseAllBtn" href="javascript:void(0)" title="展开/折叠全部资源" onclick="expandAll(false)">折叠</a>]
			</div>
		</td>
	</tr>
	<tr>
		<td>
			<div style="min-width: 300px;">
				<ul id="tree" class="ztree" style="width:260px; overflow:auto;"></ul>
			</div>
		</td>
		<td>
			<iframe src="" width="800" id="iframeMenuEdit" height="800">
				点击菜单项，此处则能编辑该菜单项或增加顶级菜单或子菜单项。
			</iframe>
		</td>
	</tr>
</table>
</body>
</html>