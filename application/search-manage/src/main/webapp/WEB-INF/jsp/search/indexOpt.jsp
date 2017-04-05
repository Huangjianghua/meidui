<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/common/js.jsp"%>
<%@include file="/WEB-INF/common/taglib.jsp" %>
<title>索引操作</title>
</head>

<div class="main_r">
	<div class="type">
		您当前的位置：<a href="#" title="">索引操作</a> 
	</div>
	
	<div class="sale">
		<input type="hidden"  id="currentPage" name="currentPage" value="1"/>
		<input id="data_type" type="hidden" value="flow">
		
		<dl class="sale_t">
	    	<dt>
	    		<select name="req_type" id="req_type" class="sel sel_b">
	        	<option value="-1">--请选择--</option>
	        	<option value="productIndex.do">商品索引操作</option>
	        	<option value="catlogIndex.do">类目索引操作</option>
            <option value="suggestIndex.do">提示词索引操作</option>
	        </select>
	    		<select name="opt_type" id="opt_type" class="sel sel_b">
	        	<option value="-1">--请选择--</option>
	        	<option value="a">增加</option>
	        	<option value="u">更新</option>
            <option value="d">ID删除</option>
            <option value="dq">条件删除</option>
	        </select>
	    		<select name="number" id="number" class="sel sel_b" style="display:none;">
	        	<option value="1000">1000</option>
	        	<option value="3000">3000</option>
            <option value="5000" selected="selected">5000</option>
            <option value="10000">10000</option>
	        </select>
	        <input type="text" name="q" id="q" placeholder="请输入操作参数" style="display:none;">
        	<input type="button" name="btn_find" id="btn_find" class="btn btn_a" value="操作" onclick="indexOpt()" />
	    	</dt>
	    </dl>
	  <select name="number" id="number" class="sel sel_b" style="display:none;">
    	<option value="1000">1000</option>
    	<option value="3000">3000</option>
      <option value="5000" selected="selected">5000</option>
      <option value="10000">10000</option>
    </select>
		<input type="button" name="btn_find" id="btn_find" class="btn btn_a" value="一键初始化索引" onclick="updateAll();" />
		<div class="sale_c">
			
		</div>
	</div>
</div>
<script type="text/javascript">
$(function() {
	$("#opt_type").change(function() {
		if($(this).val() == 'a') {
			$("#number").show();
			$("#q").hide();
		} else {
			$("#number").hide();
			$("#q").val('');
			$("#q").show();
		}
	});
	
	$("#q").blur(function() {
		if($(this).val().trim() == '') {
			alert("请输入操作参数");
			$(this).focus();
		};
	});
});
function indexOpt() {
	var req = $("#req_type").val();
	if(req == -1) {
		alert("请选择要操作索引");
		return;
	}
	var param = $("#opt_type").val();
	if(param == -1) {
		alert("请选择操作类型");
		return;
	}
	var url = "${ctx}/index/" + req;
	var data = "opt=" + $("#opt_type").val();
	var number = $("#number").val();
	var q = $("#q").val();
	data += "&number=" + number;
	if($("#q").is(":visible") && q.trim() == '') {
		alert("缺少必要参数");
		return;
	} else {
		data += "&q=" + $("#q").val();
	}
	$.ajax({
		url: url,
		data: data,
		type: 'post',
		success: function(data) {
			if(typeof data == 'string') {
				data = eval("(" + data + ")");
			}
			alert(data.result_msg);
		}
	});
}
function updateAll() {
	var a = confirm("确定要全部更新索引吗？");
	if(a) {
		var url = "${ctx}/index/updateIndex.do";
		var data = "number=" + $("#number").val();
		$.ajax({
			url: url,
			data: data,
			type: 'post',
			success: function(data) {
				var result = data;
				if(typeof data == 'string') {
					result = eval("(" + data + ")");
					alert(result.result_msg);
				}
			}
		});
	}
}
</script>
</html>