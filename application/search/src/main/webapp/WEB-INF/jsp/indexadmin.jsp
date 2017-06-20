<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<HTML>
 <HEAD>
  <TITLE>便民系统管理</TITLE>
  <%@include file="/WEB-INF/common/js.jsp"%>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <script type="text/javascript" charset="UTF-8" src="${ctx}/js/zTree3.1/jquery.ztree.all-3.1.min.js"></script>
  <!--<link rel="stylesheet" href="${ctx}/js/zTree3.1/css/zTreeStyle/zTreeStyle.css"  type="text/css">-->
  <link rel="stylesheet" type="text/css" href="${ctx}/css/site.css"/>
  <script type="text/javascript" src="${ctx}/js/jquery-ui.min.js"></script>
  <link rel="stylesheet" href="${ctx}/css/jquery-ui.css"  type="text/css"/> 
  <script type="text/javascript" src="${ctx}/js/mgsbox/new-msgbox.js"></script> 
  <script type="text/javascript" src="${ctx}/js/mgsbox/hotkeys.js"></script> 
  <link rel="stylesheet" type="text/css" href="${ctx}/js/mgsbox/msgbox.css">
  <script type="text/javascript" src="${ctx}/js/site.js"></script>
  
  <script type="text/javascript">
  		function modifyPWD(userId){
  			$update_pass_dialog = $('#update-pass-dialog').dialog({
	        width:450,
	        autoOpen:false,
	        modal:true,
	        buttons:{
	          '保存':function(){
	            $that = $(this);
	            if( $('#update-pass-onepwd').val() == $('#update-pass-pwd-again').val() ){
	              $.ajax({
	                type:"POST",
	                url :'${ctx}/personalCenter/modifyPWD.do',
	                data:{"userId":userId,
	                	  "oldPwd":$("#update-pass-oldpwd").val(),
	                	  "onePwd":$("#update-pass-onepwd").val(),
	                	  "twoPwd":$("#update-pass-pwd-again").val()
	                	 },
	                dataType:"json",
	                success:function(ret){
	                	var da = eval("("+ret+")");
	                	$.msgbox.show({
		                  message: da.CUSTMSG,
		                  icon: da.status_code == 0 ? 'ok' : 'no',
		                  timeOut: 1500,
		                  modal: true,
		                  beforeHide: function(){
		                    if( da.status_code == 0 ){
		                      $that.find('input').val('');
		                      $that.dialog("close");
		                    }
		                  }
                		});
	                }
	              });
	              
	            }else{
	             	$.msgbox.show({
		                message: '密码和确认密码不一致',
		                icon: 'no',
		                timeOut: 1500,
		                modal: true,
		                beforeHide: function(){

                		}
	            	});
	            }
	          },
	          '取消':function(){
	            $(this).dialog("close");
	          }
	        }
	      }),

      $update_pass_dialog.dialog("open");
  		
  	}
  </script>
  
  <style>

  </style>
 </HEAD>

<BODY>
<div class="wrap">
  <IFRAME ID="topFrame" Name="topFrame" FRAMEBORDER=0 SCROLLING="no" width=100%  height=109 SRC="${ctx}/user/top.do"></IFRAME>
  <div class="main">
   <div class="turn"></div>
    <div class="main_l">
      <IFRAME ID="leftFrame" Name="leftFrame" FRAMEBORDER=0 SCROLLING="no" width=100%  height=852 SRC="${ctx}/user/left.do"></IFRAME>
    </div>
    <div class="main_r">
      <IFRAME ID="rightFrame" Name="rightFrame" FRAMEBORDER=0 SCROLLING=AUTO width=100%  height=850 SRC="${ctx}/user/right.do">
      </IFRAME>
    </div>
    <div id="update-pass-dialog" class="pop" title="修改密码" style="display:none;">
	  <form id="update-pass-form">
	    <table>
	      <tr>
	        <td> 原始密码：</td>
	        <td><input type="password" id="update-pass-oldpwd" name="oldPwd" class="txt_a" /></td>
	      </tr>
	      <tr>
	        <td> 新密码：</td>
	        <td><input type="password" id="update-pass-onepwd" name="onePwd" class="txt_a" style="margin-top:10px;" /></td>
	      </tr>
	      <tr>
	        <td> 确认密码：</td>
	        <td><input type="password" id="update-pass-pwd-again" name="twoPwd" class="txt_a" style="margin-top:10px;" /></td>
	      </tr>
	    </table>
	  </form>
	</div>
  </div>
  <div class="foot">
    <h3><a href="#" title="">关于我们</a>|<a href="#" title="">联系我们</a>|<a href="#" title="">帮助中心</a>|<a href="#" title="">意见反馈</a></h3>
    <p>Copyright&copy;2014-2015 福宝（深圳）电子商务有限公司 版权所有 粤ICP备15073572号</p>
  </div>
</div>
</BODY>
</HTML>

