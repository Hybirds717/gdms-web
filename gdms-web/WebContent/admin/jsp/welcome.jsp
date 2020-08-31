<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>

<jsp:useBean id="date" class="java.util.Date" scope="page"/>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="${href}">
<title>系统信息</title>
<link rel="stylesheet" href="css/pintuer.css">
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

.error-container {
	background: #fff;
	border: 1px solid #0ae;
	text-align: center;
	width: 450px;
	margin: 5% auto;
	font-family: Microsoft Yahei;
	padding-bottom: 30px;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
}

.error-container h1 {
	font-size: 16px;
	padding: 12px 0;
	background: #0ae;
	color: #fff;
}

.errorcon {
	padding: 35px 0;
	text-align: center;
	color: #0ae;
	font-size: 24px;
}

.errorcon i {
	display: block;
	margin: 12px auto;
	font-size: 60px;
}

.errorcon span {
	color: red;
}

h4 {
	font-size: 14px;
	color: #666;
}

a {
	color: #0ae;
}
</style>
</head>
<body class="no-skin">
	<div class="error-container">
		<h1>后台管理-欢迎页</h1>
		<div class="errorcon">
			<i class="icon-smile-o"></i>欢迎${loginUser.realname}登录，今天是<f:formatDate value="${ date }"/>
		</div>

	</div>


</body>
</html>

