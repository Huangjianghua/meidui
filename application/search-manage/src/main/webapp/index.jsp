<%@page import="java.net.URLDecoder"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>壹购物-搜索管理系统</title>
<meta name="keywords" content="壹购物-搜索管理系统" />
<meta name="description" content="壹购物-搜索管理系统" />
<link rel="shortcut icon" href="/favicon.ico" />
<meta name="robots" content="index,follow" />
<link rel="stylesheet" type="text/css" href="css/log.css"/>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/site.js"></script>
<%@include file="/WEB-INF/common/js.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/login.js" charset="UTF-8"></script>
<script language="JavaScript"> 
       //session 失效时退出整个框架页
      if (window != top){
     	 top.location.href = location.href; 
      }
</script>
</head>

<body>
<form name="from1" id="from1" method="post"  action="${ctx}/user/login.do" >
<div class="wrap">
  <div class="log_t"><img src="images/service.png" alt="" /></div>
  <div class="log_c">
    <div class="log_b">
      <div class="log_l"><img src="images/log_1.jpg" alt="" /></div>
      <div class="log_r">
        <div class="log_p" id="loginErro" style="display: none">账号不能为空！</div>
        <h4><img src="images/log_man.png" alt="" /><input type="text" name="userName" maxlength="15" mouseleaver="validate();"  /></h4>
        <h4><img src="images/log_lock.png" alt="" /><input type="password" maxlength="20" name="password"  /></h4>
        
        <dl>
          <dt><input type="text" name="vcode" id="vcode" maxlength="4" onkeydown="javascript:keyDown(this.form,event)"/></dt>
          <dd ><a href="javascript:void(0)" onclick="changeCode()"><img src="servlet/getValidateCode" alt="" id="imgVcode" /></a></dd>
        </dl>
       <div class="log_pwd">
          <h6><input type="radio" name="rad_pwd" id="rad_pwd" value="rad_pwd" />记住密码</h6>
          <p><a href="#" title="">忘记密码？</a></p>
        </div>

        <h5><input type="button" name="btn_log" id="btn_log" onclick="loginsub(this.form)"  value="登录" /></h5>
      </div>
      </div>
    </div>
  </div>
  <div class="log_f">Copyright&copy;2014-2015 福宝（深圳）电子商务有限公司 版权所有 粤ICP备15073572号</div>
</div>
</form>
</body>
</html>