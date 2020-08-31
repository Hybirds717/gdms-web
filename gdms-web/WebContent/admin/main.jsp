<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<base href="${href}" target="right">
<title>后台管理</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/dialog-min.css">
<link rel="stylesheet" href="css/admin.css">
<link href="css/top.css" type="text/css" rel="stylesheet" />

<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/dialog-min.js"></script>
<script src="js/pintuer.js"></script>

<script type="text/javascript">
		$(document).ready(function() {
			/***左侧菜单控制***/
			$(".leftnav h2").click(function() {
				$(this).next().slideToggle(200);
				$(this).toggleClass("on");
			})
			$(".leftnav ul li a").click(function() {
				$("#a_leader_txt").text($(this).text());
				$(".leftnav ul li a").removeClass("on");
				$(this).addClass("on");
			})
			
			/***顶部菜单控制***/
			$(".hmain").hover(function(){			//选择到所有.hmain类样式元素注册hover事件
				var $ul = $(this).children("ul");	//选择到a的下一个ul元素
				$ul.css({"left":$(this).offset().left,"top":$(this).offset().top + $(this).height()});
				$ul.show(100);						//设置此ul元素切换显示或隐藏
			}, function(){
				var $ul = $(this).children("ul");	//选择到a的下一个ul元素
				$ul.hide(300);						//设置此ul元素切换显示或隐藏
			});
			
			/***子菜单点击时隐藏菜单项***/
			$(".subs a").click(function(){
				$(".hmain ul").hide();
			});
			
		});
		
	</script>
</head>

<body style="background-color: #f2f9fd;">
<!-- 顶部菜单栏 start-->
	<div class="header bg-main" >
		<table width="100%" border="0" cellspacing="0" cellpadding="0" id="header">
		  <tr>
		    <td rowspan="3" align="left" valign="top" id="logo"><img src="images/main/logo.jpg" width="74" height="60"></td>
		    <td align="left" valign="bottom">
		    <table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td align="left" valign="bottom" id="header-name">商品后台管理系统</td>
		        <td align="right" valign="top" id="header-right">
		        	<a href="admin/user/logout.php" target="topFrame" onFocus="this.blur()" class="admin-out">注销</a>
		            <a href="index.html" target="top" onFocus="this.blur()" class="admin-home">管理首页</a>
		        	<a href="index.html" target="_blank" onFocus="this.blur()" class="admin-index">网站首页</a>       	
		            <span>
						<!-- 日历 -->
						<SCRIPT type=text/javascript src="js/clock.js"></SCRIPT>
						<SCRIPT type=text/javascript>showcal();</SCRIPT>
		            </span>
		        </td>
		      </tr>
		    </table></td>
		  </tr>
		  <tr>
		    <td align="left" valign="bottom">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" >
		      <tr>
		        <td align="left" valign="bottom" id="header-admin">
		        	后台管理系统
		        </td>
		        <td align="left" valign="bottom" id="header-menu">
				    <ul >
				    	<li class="hmain">
				 			<a href="#" >系统管理</a>
				            <ul class="subs">
				                <li><a href="http://www.script-tutorials.com/category/html-css/">会员管理</a></li>
				                <li><a href="http://www.script-tutorials.com/category/jquery/">增加会员</a></li>
				                <li><a href="info.html" >上传文件</a></li>
				                <li><a href="http://www.script-tutorials.com/category/mysql/">Test</a></li>
				                <li><a href="http://www.script-tutorials.com/category/xslt/">ABCDEF</a></li>
				                <li><a href="http://www.script-tutorials.com/category/ajax/">AAABBB</a></li>
				                <li><a href="http://www.script-tutorials.com/category/html-css/">子菜单二级</a></li>
				            </ul>
				        </li>
				 	</ul>
				 	
				 	<ul >
				    	<li class="hmain">
				 			<a href="#" >用户管理</a>
				            <ul class="subs">
				                <li><a href="http://www.script-tutorials.com/category/html-css/">用户管理</a></li>
				                <li><a href="http://www.script-tutorials.com/category/jquery/">增加用户</a></li>
				                <li><a href="info.html"  >上传文件</a></li>
				                <li><a href="http://www.script-tutorials.com/category/mysql/">JAVA</a></li>
				                <li><a href="http://www.script-tutorials.com/category/xslt/">MySql</a></li>
				                <li><a href="http://www.script-tutorials.com/category/ajax/">HTML</a></li>
				                <li><a href="http://www.script-tutorials.com/category/html-css/">软件工程课程</a></li>
				            </ul>
				        </li>
				 	</ul>
				 	
				 	<ul >
				    	<li class="hmain">
				 			<a href="#" >用户管理</a>
				            <ul class="subs">
				                <li><a href="http://www.script-tutorials.com/category/html-css/">用户管理</a></li>
				                <li><a href="http://www.script-tutorials.com/category/jquery/">增加用户</a></li>
				                <li><a href="info.html"  >上传文件</a></li>
				                <li><a href="http://www.script-tutorials.com/category/mysql/">JAVA</a></li>
				                <li><a href="http://www.script-tutorials.com/category/xslt/">MySql</a></li>
				                <li><a href="http://www.script-tutorials.com/category/ajax/">HTML</a></li>
				                <li><a href="http://www.script-tutorials.com/category/html-css/">软件工程课程</a></li>
				            </ul>
				        </li>
				 	</ul>
				 	
				 	<ul >
				    	<li class="hmain">
				 			<a href="#" >用户管理</a>
				            <ul class="subs">
				                <li><a href="http://www.script-tutorials.com/category/html-css/">用户管理</a></li>
				                <li><a href="http://www.script-tutorials.com/category/jquery/">增加用户</a></li>
				                <li><a href="http://www.script-tutorials.com/category/php/">上传文件用户</a></li>
				                <li><a href="http://www.script-tutorials.com/category/mysql/">JAVA</a></li>
				                <li><a href="http://www.script-tutorials.com/category/xslt/">MySql</a></li>
				                <li><a href="http://www.script-tutorials.com/category/ajax/">HTML</a></li>
				                <li><a href="http://www.script-tutorials.com/category/html-css/">软件工程课程</a></li>
				            </ul>
				        </li>
				 	</ul>
		        </td>
		      </tr>
		    </table></td>
		  </tr>
		</table>
	</div>
	<!-- 顶部菜单栏 end-->
	
	
	
	<!-------------- 左边菜单栏 start-------------->
	<div class="leftnav">
		<div class="leftnav-title">
			<strong><span class="icon-list"></span>欢迎【${loginUser.realname}】</strong>
		</div>
		
		<h2>
			<span class="icon-user"></span>商品管理
		</h2>
		<ul style="display: block">
			<li><a href="admin/goods/list.php" ><span class="icon-caret-right"></span>商品浏览</a></li>
			<li><a href="pass.html" ><span class="icon-caret-right"></span>修改密码</a></li>
			<li><a href="page.html" ><span class="icon-caret-right"></span>单页管理</a></li>
			<li><a href="adv.html" ><span class="icon-caret-right"></span>首页轮播</a></li>
			<li><a href="book.html" ><span class="icon-caret-right"></span>留言管理</a></li>
			<li><a href="column.html" ><span class="icon-caret-right"></span>栏目管理</a></li>
		</ul>
		<h2>
			<span class="icon-pencil-square-o"></span>角色管理
		</h2>
		<ul>
			<li><a href="list.html"  ><span
					class="icon-caret-right"></span>内容管理</a></li>
			<li><a href="add.html"  ><span
					class="icon-caret-right"></span>添加内容</a></li>
			<li><a href="cate.html"  ><span
					class="icon-caret-right"></span>分类管理</a></li>
		</ul>
		
		<h2>
			<span class="icon-pencil-square-o"></span>用户管理
		</h2>
		<ul>
			<li><a href="list.html"  ><span
					class="icon-caret-right"></span>内容管理</a></li>
			<li><a href="add.html"  ><span
					class="icon-caret-right"></span>添加内容</a></li>
			<li><a href="cate.html"  ><span
					class="icon-caret-right"></span>分类管理</a></li>
		</ul>
	</div>
	<!-- ------------左边菜单栏 end---------------->
	
	<!-- ------------右边的上部导肮文字 start---------------->
	<ul class="bread">
		<li><a href="info.html"   class="icon-home">首页</a></li>
		<li><a href="##" id="a_leader_txt">网站信息</a></li>
	</ul>
	<!-- ------------右边的上部导肮文字 end---------------->
	
	<!-- ------------右边内容显示区 start---------------->
	<div class="admin">
		<iframe scrolling="auto" frameborder="0" src="admin/jsp/welcome.jsp" name="right" width="100%" height="99%"></iframe>
	</div>
	<!-- ------------右边内容显示区 end---------------->
	
</body>
</html>
