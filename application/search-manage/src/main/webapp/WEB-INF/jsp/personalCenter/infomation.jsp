<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/site.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/site.css"/>

<title></title>

</head>
<body>
	<form action="${pageContext.request.contextPath}/personalCenter/saveUserInformation.do"  method="post">
		<input name="id" type="hidden" value="${user.id }">
		<input name="userName" type="hidden" value="${user.enUserName }">
		<div class="data" id="deal_1">
	        <div class="h_60"></div>
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <th width="130">账号：</th>
	            <td>${user.enUserName }</td>
	          </tr>
	          <tr>
	            <th>名称：</th>
	            <td><input type="text" name="nickName" id="nickName" class="txt" value="${user.nickName }"/></td>
	          </tr>
	          <!-- <tr>
	            <th>所属部门：</th>
	            <td><input type="text" name="department" id="department" class="txt"  /></td>
	          </tr> -->
	          <tr>
	            <th>联系电话：</th>
	            <td><input type="text" name="phone" id="phone" class="txt" value="${user.phone }"/><span id="phoneMsg"></span></td>
	          </tr>
	          <tr>
	            <th valign="top">其他说明：</th>
	            <td><textarea maxlength="250" name="explanation" id="explanation">${user.explanation }</textarea></td>
	          </tr>
	          <tr>
	            <td colspan="2"><h4><input type="reset" name="btn_set" id="btn_set" value="重置" /><input onclick="return checkedPhone();" type="submit" name="btn_up" id="btn_up" value="修改" /></h4></td>
	          </tr>
	        </table>
      	</div>
	</form>
</body>
<script>
	$("#btn_set").click(function(){
		$("#nickName").val("");
		$("#department").val("");
		$("#phone").val("");
		$("#explanation").val("");
	});
	
	  $("#phone").blur(function(){ 
  			checkedPhone();
      }); 
    
    function checkedPhone(){
    	if($("#phone").val()==""){ 
		    $("#phoneMsg").html("<font color='red'>手机号码不能为空！</font>"); 
		    /* $("#phone").focus();  */
		    return false; 
      	}else if (!($("#phone").val()).match(/^1([38]\d|4[57]|5[0-35-9]|7[06-8]|8[89])\d{8}$/)) {
		    $("#phoneMsg").html("<font color='red'>手机号码格式不正确！请重新输入！</font>"); 
		    /* $("#phone").focus();  */
		    return false; 
		  }else{
		  	$("#phoneMsg").html("");
		  	return true;
		  } 
		  
    }
</script>
</html>