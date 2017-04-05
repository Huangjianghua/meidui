<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

	<div class="nav_con">
			<!-- 顶部工具栏-->
		<div class="nav_con">
			<div class="left">
				<p id="login_5" class="small-signin">欢迎来到壹购物，请先
<a href="http://www.1gw.com/passport-signin.html" rel="nofollow" >登录</a>，或
<a href="http://vip.firstkg.com/trunk/vip/register.html" rel="nofollow" >免费注册</a>
</p>
<p id="member_5" class="small-member">
  您好<span id="uname_5">
  </span>！
  <a href="http://www.1gw.com/passport-logout.html"  rel="nofollow">退出</a>
</p>
<script>
var name = decodeURIComponent($.cookie('UNAME') || '');
if(name){
    //$("#uname_5").html(name);
    $("#member_5").css('display','');
    $("#login_5").css('display','none');
}
else{
    $("#login_5").css('display','');
    $("#member_5").css('display','none');
}
</script>
			</div>
			<div style="float: left;margin:4px 0 0 20px;">
				<script charset="utf-8" type="text/javascript" src="http://wpa.b.qq.com/cgi/wpa.php?key=XzkzODAwMTQxNV8zNTI5NzBfNDAwODIyMDMzM18"></script>
			</div>
			<div class="right">
				<ul id="nav">

		<!-- <li class="hasdrop">
							<a href="http://www.1gw.com/member-index.html"><i class="icon_1"></i>个人中心<em></em></a>
					<div class="drop">
						<a href="http://www.1gw.com/trade-list.html">我的订单</a>
						<a  target="_blank" href="http://vip.firstkg.com//trunk/vip/sso.html?user_id=&token=">积分中心</a>
					</div>
		    		</li> -->



		<!-- 上面，将下拉的菜单分开 -->
		<li>
					    	<a href="http://www.1gw.com/member-index.html"  rel="nofollow">
		    	<!-- <i class="icon_1"></i> -->
		    	我的壹购物</a>
		    		</li>
		<li>
					    	<a href="http://www.1gw.com/trade-list.html"  rel="nofollow">我的订单</a>
		    		</li>
		<li>
					    	<a  target="_blank" href="http://vip.firstkg.com//trunk/vip/sso.html?user_id=&token="  rel="nofollow">积分中心</a>
		    		</li>
		<li>
					    	<a href="http://www.1gw.com/cart.html"  rel="nofollow">
		    	<!-- <i class="icon_5"></i> -->
		    	购物车</a>
		    		</li>
		<li>
							<a href="http://www.1gw.com/member-collectitems.html"  rel="nofollow">
				<!-- <i class="icon_2"></i> -->
				我的收藏</a>
		    		</li>
		<li>
			<a href="http://www.1gw.com/content-index.html"  rel="nofollow" >
			<!-- <i class="icon_3"></i> -->
			帮助中心</a>
		</li>
		<li class="hasdrop">
			<a href="#"  rel="nofollow"><i class="icon_4"></i>关注我们<em></em></a>
			<div class="drop">
				<div class="ewm_pic">
				<img src="http://www.1gw.com/themes/first/images/images/ewm_pic.png" />
				</div>
			</div>
		</li>
</ul>
			</div>
		</div>
	</div>
