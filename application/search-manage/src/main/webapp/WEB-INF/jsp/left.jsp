<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<HTML>
 <HEAD>
  <TITLE> ZTREE DEMO </TITLE>
  <%@include file="/WEB-INF/common/js.jsp"%>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" type="text/css" href="${ctx}/css/site.css"/>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/site.js"></script>
<%@page import="com.first.system.domain.User"%>
<script type="text/javascript">
<%
HttpSession s = request.getSession();
User user = (User)s.getAttribute("userInfo");
String userName = user.getEnUserName();
String userId = user.getId();
%>

$('#edit-pass').live('click', function(e){
	  var frames=window.parent; 
	  frames.modifyPWD(<%= userId%>); 
    });

$(function(){
  var $li=$(".head_r li").eq(2);
  $li.addClass("hover");
  $li.siblings().hover(
	function(){$li.removeClass("hover")},
	function(){$li.addClass("hover")}
  );
})
function getUrlParam(name)
{
var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
var r = window.location.search.substr(1).match(reg); 
if (r!=null) return unescape(r[2]); return null; 
} 

  $(document).ready(function(){
		//根据用户id 加载菜单
		var pid = getUrlParam('pid');
		$.ajax({
			url:ctx+"/menu/selectJosnMenu.do?pid="+pid,
			type:"post",
			dataType:"json",
			success:function(data){
				parent.document.getElementById("rightFrame").src=data[0].url;
				var html = '';
			      for (var i = 0; i < data.length; i ++) {
			        html += "<li id='objid"+i+"'  onclick='javascript:setLiclassName(\"objid\","+data.length+","+i+")' ><a href='javascript:void(0)' onclick='javascript:parent.document.getElementById(\"rightFrame\").src=\"" + data[i].url +"\"' title=" + data[i].name + "><img src='${ctx}/images/sub_2.png'  />" + data[i].name + "</a></li>";
			      }
			      $("#mymenu").append(html);
			      $("#objid0").attr("class","cur");
				
			},
		      error: function(XMLHttpRequest, error, errorThrown){  
		      
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
  			t.className = "cur";
  		}
  		else
  		{
  			c.className = "";
  		}
  		
  	}
  }
</script>
</HEAD>

<body>
      <div class="l_cont">
        <h3>便民服务后台</h3>
        <h4><%=userName %></h4>
        <h5><a id="edit-pass" style="cursor: pointer;" title="">修改密码</a><a href="${ctx}/user/logout.do" title="">[退出]</a></h5>
        <ul id="mymenu" class="sub sub_a">
        </ul>
      </div>
</body>
</html>