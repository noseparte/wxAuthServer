<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/WEB-INF/tlds/Functions" prefix="func"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN" style="height:100%;">
<%@ include file = "./inc/version.jsp" %>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<title>微信登录服务管理</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="keywords" content="">
	<meta name="author" content="">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" href="<%=basePath%>css/bootstrap/bootstrap.3.3.5.min.css">
	<link rel="stylesheet" href="<%=basePath%>css/common_${ CSS_COMMON_VERSION }.css">
	<link rel="shortcut icon" href="<%=basePath%>image/logo/favicon.ico">
	<style type="text/css">
		body {
			background: #474f57;
		}
		.index-navbar {
			background: #404952;
			border-bottom: none;
		}
		.index-navbar .navbar-nav {
			width: 100%;
		}
		.index-navbar .navbar-nav>li>a.menu-btn {
			padding: 0 11px 0 1px;
			font-size: 21px;
			color: #ddd;
			line-height: 43px;
			border-right: 1px solid #333;
		}
		.index-navbar .navbar-nav>li>a.navbar-brand{
			padding: 12px 0 10px 15px;
			font-size: 22px;
			color: #f1f1f1;
			height: auto;
			margin-left: 0px;
		}
		.index-navbar .navbar-nav>li>a.dropdown-toggle {
			padding: 11px 10px 8px;
		}
		.index-navbar .navbar-nav>.open>a, .index-navbar .navbar-nav>.open>a:hover {
			background-color: #59646e;
		}
		.main-icon {
			display: inline-block;
			width: 24px;
			height: 24px;
			background: url(<%=basePath%>image/home/teacher.png);
			background-size: cover;
			vertical-align: middle;
			margin-right: 5px;
		}
		.index-container {
			padding-top: 50px
		}
		.ui-sidebar {
			width: 230px;
			float: left;
		}
		.index-enlarge .ui-sidebar {
			display: none;
		}
		.ui-nav {
			margin-bottom: 37px;
		}
		.ui-nav .ui-nav-1>a {
			background: #474f57;
		}
		.ui-nav .ui-nav-1.unfold>a{
			background: #3c434a;
		}
		.nav-stacked>li+li {
			margin-top: 0;
		}
		.ui-nav li>a {
			background: #2e3338;
			color: #c5ced6;
			border-top: 1px solid #515a63;
			border-bottom: 1px solid #424a52;
		}
		.ui-nav li>a:hover, .ui-nav li>a:focus, .ui-nav li.unfold>a{
			background: #59646e;
			color: #fff;
		}
		.ui-nav li>a>i {
			width: 40px;
			line-height: 40px;
			float: left;
			border-right: 1px solid rgba(255,255,255,0.05);
			margin: -11px 10px -11px -15px;
			text-align: center;
		}
		.ui-nav li>a .glyphicon:before {
			content: "\e079"; /*chevron-down*/
		}
		.ui-nav li.unfold>a .glyphicon:before {
			content: "\e114"; /*chevron-up*/
		}
		.nav>.ui-nav-3>a {
			padding-left: 35px;
		}
		.nav>.ui-nav-4>a {
			padding-left: 55px;
		}
		.index-article {
			margin: 0 0 38px 230px;
			width: auto;
			overflow: hidden;
			border-left: 1px solid #ccc;
			background: #eeeff2;
			height: 1000px;
		}
		.index-enlarge .index-article {
			margin-left: 0;
			border-left: none;
		}
		.index-footer {
			position: fixed;
			bottom: 0;
			width: 100%;
			z-index: 100;
			background: #333;
			padding: 10px 0;
			color: #777;
			border-top: 1px solid #333;
		}
		.index-footer .copyright {
			margin: 0 15px;
			font-size: 11px;
		}
		.index-footer .copyright a{
			color: inherit;
		}
		.ui-frame {
			border: none;
			width: 100%;
			height: 100%;
		}
	</style>
</head>
<body>
	<header class="navbar navbar-default navbar-fixed-top index-navbar">
		<nav class="container-fluid">
			<ul class="nav navbar-nav">
				<li>
					<a class="menu-btn" href="" id="menu-btn">
						<span class="glyphicon glyphicon-menu-hamburger"></span>
					</a>
				</li>
				<li>
					<a class="navbar-brand" href="">小米菠萝<span class="bold">-微信登录管理</span></a>
				</li>
			</ul>
		</nav>
	</header>
	<div style="margin:40px 20px 10px; padding:10px 10px 10px; background:#fff;min-height:600px;">
			<acticle class="container-fluid" >
			</acticle>
	</div>
	<footer class="index-footer">
		<div class="copyright">小米菠萝平台系统&nbsp;©2016&nbsp;|&nbsp;<a href="#"></a></div>
	</footer>
</body>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" >
</script>
</html>