<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>弗尔斯特</title>
<meta name="keywords" content="弗尔斯特" />
<meta name="description" content="弗尔斯特" />
<link rel="shortcut icon" href="/favicon.ico" >
<meta name="robots" content="index,follow" />
<%@include file="/WEB-INF/common/js.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/css/site.css"/>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/site.js"></script>
<script type="text/javascript">
$(function(){
  var $li=$(".head_r li").eq(2);
  $li.addClass("hover");
  $li.siblings().hover(
	function(){$li.removeClass("hover")},
	function(){$li.addClass("hover")}
  );
})

$(document).ready(function(){
		//根据用户id 加载菜单
		$.ajax({
			url:ctx+"/menu/selectMenuFirst.do",
			type:"post",
			dataType:"json",
			success:function(data){
				var html = '';
			      for (var i = 0; i < data.length; i ++) {
			        html += "<li id='objid"+i+"'  onclick='javascript:setLiclassName(\"objid\","+data.length+","+i+")' class=''><a href='javascript:void(0)' onclick='javascript:parent.document.getElementById(\"leftFrame\").src=\"${ctx}/user/left.do?pid=" + data[i].id +"\";' title=" + data[i].name + "><img src="+data[i].pic+" />" + data[i].name + "</a></li>";
			      }
			      $("#firstmenu").append(html);
			},
		      error: function(XMLHttpRequest, error, errorThrown){  
		      alert(error);  
		      alert(errorThrown);  
		      } 
		})
		
	});    
function setLiclassName(objid,count,num)
{
	var t = document.getElementById(objid+num);
	for(var i=0;i<count;i++)
	{
		var c = document.getElementById(objid+i);		
		if(i == num)
		{
			t.className = "hover";
		}
		else
		{
			c.className = "";
		}
		
	}
}
</script>
</head>

<body>

  <div class="head">
    <div class="head_c">
      <h1><a href="#" title=""><img src="${ctx}/images/logo.png" alt="" /></a></h1>
      <div class="head_r">
        <ul id="firstmenu">       
        </ul>
      </div>
    </div>
  </div>
</body>
</html>