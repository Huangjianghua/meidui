<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/common/js.jsp"%>
<%@include file="/WEB-INF/common/taglib.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.min.css"  type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
function query() {
	var key = $("#suggestWord").val();
	if(key.trim().length == 0) {
		alert("请输入要查询的词");
		return;
	}
	var url = "${ctx}/suggestWord/queryWord.do?key=" + key;
	$("#query").attr("href", url);
}

function add() {
	var key = $("#suggestWord").val();
	if(key.trim().length == 0) {
		alert("请输入要添加的词");
		return;
	}
	var url = "${ctx}/suggestWord/updateSuggestWord.do?key=" + key;
	$("#add").attr("href", url);
	alert(("#add").attr("href"));
}

function updateIndex(key) {
	var url = "${ctx}/index/suggestIndex.do";
	$.ajax({
		url: url,
		data: "opt=ui&q=" + key,
		type: "post",
		success: function(data) {
			if(typeof data == 'string') {
				data = eval("(" + data + ")");
			}
			alert(data.result_msg);
		}
	});
}

$(function() {
	var msg = "${msg}";
	if(msg == 1) {
		alert("添加成功！");
	}
});
</script>
<title>提示词列表</title>
</head>
<body>
<form action="${ctx}/suggestWord/querySuggestWord.do"  method="post">
    <input type="hidden"  id="currentPage" name="currentPage" value="1"/>
	<table class="table table-bordered table-condensed">
		<tr>
			<td colspan="11">
				关键词:<input type="text" id="suggestWord" name="suggestWord" autocomplete="off" />
				<a class="btn btn-danger" href="" id="query" onclick="return query();" >
						<i class="icon-search icon-white"></i> 查询
					</a>
					
					<a class="btn btn-danger" href="" id="add" onclick="return add();" >
						<i class="icon-plus-sign icon-white"></i> 添加
				    </a>
			</td>
		</tr>
		<tr>
			<td colspan="11">
			    <%-- <a class="btn btn-danger" href="${ctx}/index/manualUpdateAll.do">
						<i class="icon-plus-sign icon-white"></i> 全部更新
			    </a> --%>
					
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
					<td>提示词</td>
					<td>拼音</td>
					<td>简拼</td>
					<td>词频</td>
					<td>更新时间</td>
					<td nowrap="nowrap">操作</td>
        </tr>
       </tbody>
       <tbody id="tbody">
          <c:forEach items="${pageView.dateList}" var="item" varStatus="temp">
	        <tr class="${temp.index % 2 == 0 ?'':'even' }">
						<td>&nbsp;${item.kw}</td>
						<td><div style="width:300px;line-height:22px;margin:0 auto;word-wrap:break-word;">&nbsp;${item.pinyin}</div></td>
						<td>&nbsp;${item.abbre}</td>
						<td>&nbsp;${item.kwfreq}</td>
						<td>&nbsp;${item.updateTime}</td>
						<td>
						  <a href="javascript:void(0);" onclick="updateIndex(${item.keyId})">更新索引库</a>
						  &nbsp;&nbsp;&nbsp;&nbsp;
						  <a href="${ctx}/suggestWord/deleteSuggestWord.do?keyId=${item.keyId}">删除</a>
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