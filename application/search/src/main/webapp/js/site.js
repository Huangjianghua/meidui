// JavaScript Document By Aring QQ:838278506 http://www.divsz.com/

////选项卡(导航)
function setTab(name,cursel,n){
  for(i=1;i<=n;i++){
    var menu=document.getElementById(name+i);
	var con=document.getElementById(name+"_"+i);
	menu.className=i==cursel?"hover":"";
	con.style.display=i==cursel?"block":"none";
  }
};

$(function(){
  ////预留
  //$(".test li:first").addClass("first");
  $(".theme li:last").addClass("last");
  $(".fine_l dl:last").addClass("last");
  $(".order_c tr:even").addClass("even");
  $(".sale_c tr:even").addClass("even");
  ////左侧高度跟右侧保持一致
  //$(".main_l").height($(".main_r").height());
  ////头部导航背景图片
  $(".fine_l dt:lt(3)").addClass("top");
  ////左侧隐藏
  $(".turn").click(function(){
    $(".main_l").toggle();
    $(this).hasClass("open")? $(this).removeClass("open") : $(this).addClass("open") ;
  });
  
});

////window.onerror=function(){return true;}