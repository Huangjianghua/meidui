<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${ctx}/js/jquery-1.7.2.min.js" charset="UTF-8"></script>

<link rel="stylesheet" href="${ctx}/js/bootstrap/css/bootstrap.min.css"  type="text/css"/>
<script type="text/javascript" src="${ctx}/js/bootstrap/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="${ctx}/js/validator-0.7.0/jquery.validator.css" />
<link rel="stylesheet" href="${ctx}/css/site.css" />
<link rel="stylesheet" href="${ctx}/css/all.css" />
<script type="text/javascript" src="${ctx}/js/validator-0.7.0/jquery.validator.js"></script>
<script type="text/javascript" src="${ctx}/js/validator-0.7.0/local/zh_CN.js"></script>
<script type="text/javascript">
    var ctx = '<%=request.getContextPath()%>';  
	//到指定的分页页面
	function topage(page){
		var form = document.forms[0];
		if(page==undefined){
			var gopage=$("#gopage").val();
			page =gopage;
			if(gopage==""){
				return ;
			}
			var count = $("#pagecount").val();
			if(parseInt(gopage)<=0){
				gopage=1;
				page = gopage;
			}
			if(parseInt(gopage)>parseInt(count)){
				page = count;
			}
		}
		$("#currentPage").val(page);
		form.submit();
	}
</script>