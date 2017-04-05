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
<%-- <%@page import="com.first.system.domain.User"%> --%>
<script type="text/javascript">
<%-- <%
HttpSession s = request.getSession();
User user = (User)s.getAttribute("userInfo");
String userName = user.getUserName();
String userId = user.getId();
%> --%>

$('#edit-pass').live('click', function(e){
	  var frames=window.parent; 
	  <%-- frames.modifyPWD(<%= userId%>);  --%>
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
	
	function updateIndex(pid) {
		$.ajax({
			url:"${ctx}/index/updateInex.do?pid="+pid,
			type:"post",
			dataType:"json",
			success:function(data){
				alert(data);				
			},
      error: function(XMLHttpRequest, error, errorThrown){  
	      alert(error);  
	      alert(errorThrown);  
      }
		});
	}
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
     <h3>搜索管理后台</h3>
     <h4>${userName}</h4>
     <h5><a id="edit-pass" style="cursor: pointer;" title="">修改密码</a><a href="${ctx}/user/logout.do" title="">[退出]</a></h5>
     <ul id="mymenu" class="sub sub_a">
     	<li id="objid" onclick="javascript:setLiclassName('objid1')" ><a href='javascript:void(0)' onclick="javascript:parent.document.getElementById('rightFrame').src='${ctx}/index/updateProductIndex.do?number=5000'" title=""><img src='${ctx}/images/sub_2.png'  />更新商品索引</a></li>
     	<li id="objid" onclick="javascript:setLiclassName('objid2')" ><a href='javascript:void(0)' onclick="javascript:parent.document.getElementById('rightFrame').src='${ctx}/index/updateSuggestIndex.do?number=5000'" title=""><img src='${ctx}/images/sub_2.png'  />更新提示索引</a></li>
     	<li id="objid" onclick="javascript:setLiclassName('objid3')" ><a href='javascript:void(0)' onclick="javascript:parent.document.getElementById('rightFrame').src='${ctx}/index/updateCatlogIndex.do?number=5000'" title=""><img src='${ctx}/images/sub_2.png'  />更新类目索引</a></li>
     	<li id="objid" onclick="javascript:setLiclassName('objid4')" ><a href='javascript:void(0)' onclick="javascript:parent.document.getElementById('rightFrame').src='${ctx}/index/updateIndex.do'" title=""><img src='${ctx}/images/sub_2.png'  />更新全部</a></li>
     </ul>
   </div>
</body>
</html>