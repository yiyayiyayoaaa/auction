<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/bootstrap/css/bootstrap.min.css">

	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->/
	<script src="${pageContext.request.contextPath }/static/js/jquery.min.js"></script>

	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="${pageContext.request.contextPath }/static/bootstrap/js/bootstrap.min.js"></script>
	<style type="text/css">
		.pageSide{width: 40%;height:100%;float: left;}
		.pageCenter{width:20%;height:100%;float: left}
	</style>
  </head>
  <body style="background-image: url('${pageContext.request.contextPath }/static/images/bg2.jpg');">
  	<div class="page-header">
  <h1 align="center">登录</h1>
</div>
  	
  	<div class="pageSide"></div>
  	
  	<div class="pageCenter">
  		<form role="form" action="${pageContext.request.contextPath }/admin/login.html" method="post">
			<div class="form-group">
				<span class="glyphicon glyphicon-user"></span><label for="username">账号</label><font color="red">${loginFail}</font>
				<input type="text" class="form-control" id="username" name="username" placeholder="请输入账号。。。">
			</div>
			<div class="form-group">
				<span class="glyphicon glyphicon-lock"></span><label for="password">密码</label>
				<input type="password" class="form-control" id="password" name="password" placeholder="请输入密码。。。">
			</div>
			<button type="submit" class="btn btn-default">登录</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<!--         	<input type="radio" id="user" name="userType" value="user" checked="checked">&nbsp; -->
<!--         	<label for="user">普通用户</label>&nbsp;&nbsp; -->
<!--         	<input type="radio" id="admin" name="userType" value="admin">&nbsp; -->
<!--         	<label for="admin">管理员</label> -->
		</form>
  	</div>
  	
  	<div class="pageSide"></div>
</body>
</html>
