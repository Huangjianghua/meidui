/**
 * 后台登入
 * @param frm
 */
function  loginsub(frm){
	frm = $(frm)[0];
	var data  = getFormPara(frm);
	if(!validate(data)){
		return ;
	}
	$("#loginErro").css('display','none'); 
	$.ajax({
		url:frm.action,
		type:"post",
		data:data,
		success:function(data){
			//显示错误信息
			if(data==1){
				$("#loginErro").html("<font color='red'>帐号或密码错误!</font>");
				$("#loginErro").css('display','block'); 
			}else if(data==4){
				$("#loginErro").html("<font color='red'>验证码输入错误!</font>");
				$("#loginErro").css('display','block'); 
			}else if(data==2){
				$("#loginErro").html("<font color='red'>帐号被冻结请联系管理员!</font>");
				$("#loginErro").css('display','block');
			}else{
				window.location.href=ctx+'/user/indexadmin.do';
			}
			changeCode();
		}
	})
}

function  validate(data){
	if(data.userName==""){
		$("#loginErro").html("<font color='red'>帐号不能为空!</font>");
		$("#loginErro").css('display','block'); 
		return false ;
	}
	if(data.password==""){
		$("#loginErro").html("<font color='red'>密码不能为空!</font>");
		$("#loginErro").css('display','block'); 
		return false;
	}
	if(data.vcode==""){
		$("#loginErro").html("<font color='red'>验证码不能为空!</font>");
		$("#loginErro").css('display','block'); 
		return false;
	}
	return true;
}

//
//$(document).ready(function(){
//	//接收表单验证通过的事件
//	$('#from1').bind('valid.form', function(){
//	 	var frm = this;
//		var data  = getFormPara(frm);
//		$.ajax({
//			url:ctx+"/user/login.do",
//			type:"post",
//			data:data,
//			success:function(data){
//				//显示错误信息
//				if(data==1){
//					$("#loginErro").html("<font color='red'>帐号或密码错误!</font>");
//					$("#loginErro").css('display','block'); 
//				}else if(data==4){
//					$("#loginErro").html("<font color='red'>验证码输入错误!</font>");
//				}else if(data==2){
//					$("#loginErro").html("<font color='red'>帐号被冻结请联系管理员!</font>");
//				}else{
//					window.location.href=ctx+'/user/indexadmin.do';
//				}
//				changeCode();
//			}
//		})
//	});
//});

	

/**
 * 改变验证码
 */
function changeCode() {
	//流量器会缓存之前的验证码 加上 new Date().getTime()  改变让浏览器不缓存
	$("#imgVcode").attr("src", "servlet/getValidateCode?ts=" + new Date().getTime());
}



/**
 * ajax 封装表单域中的参数
 */
function getFormPara (oForm){
	oForm = $(oForm)[0]
	var len = oForm.elements.length;
	var para ={};
	for(var i=0 ;i<len;i++){
		para[oForm[i].name]=oForm[i].value;
	}
	return para;
}
