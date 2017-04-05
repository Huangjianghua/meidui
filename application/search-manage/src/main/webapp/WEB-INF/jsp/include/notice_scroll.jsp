<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="notice_scroll" style="display:none;">
  <div class="">
    <img src="http://www.1gw.com/images/9d/5f/b0/34660b0afc2e1b53fa0bbd3a00392bce0eba459d.png">
    <div id="notice_scroll_box" class="scroll_box" onmouseover="" onmouseout="">
        <span id="notice_text1" class="text1">
          尊敬的会员：5月30日9:00--11:00系统将进行更新，该时间段内将影响您在壹购物商城进行登录及购物等功能，请广大会员周知，谢谢。        </span>
        <span id="notice_text2" class="text2"></span>
    </div>
  </div>
</div>
<script>
    $(function(){
       
      var visable="on";
      if (visable=='on'){
          var box=document.getElementById("notice_scroll_box");
          var txt1=document.getElementById("notice_text1");
          var txt2=document.getElementById("notice_text2");
          var w=txt1.scrollWidth; //公告长度
          var i=0;
          var j=0;

          var space=100;  //公告间隔

          if (w >= 1175)
          {
            j=w+space;    //设置第二个公告left值
            txt2.innerHTML= txt1.innerHTML;

            timer=setInterval(function(){ loop();},50);

            $("#notice_scroll_box").on('mouseover',function(){
              clearInterval(timer);
            });

            $("#notice_scroll_box").on('mouseout',function(){
              timer=setInterval(function(){ loop();},50);
            });

          }

          function loop(){
            if(i<=(-(w+space))){  //第一个公告滚出范围后重置位置。
              i=0;
              j=w+space;

              txt1.style.left=i+"px";
              txt2.style.left=j+"px";
            }

            txt1.style.left=--i+'px';
            txt2.style.left=--j+'px';
          }
      }
    });
</script>
