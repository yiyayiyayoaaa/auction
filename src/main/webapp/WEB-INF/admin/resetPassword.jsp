<%--
  Created by IntelliJ IDEA.
  User: AMOBBS
  Date: 2017/2/3
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui/themes/color.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/js/PCASClass.js"></script>
    <style>
        .f1{
            width:200px;
        }
        body {text-align: center;}
    </style>
</head>
<body>
<div class="easyui-draggable">
    <div class="easyui-panel" title="修改密码" style="width:400px;padding:30px 60px;margin: 0 auto">
        <div style="margin-bottom:20px">
            <div>旧密码:</div>
            <input class="easyui-textbox" style="width:100%;height:32px">
        </div>
        <div style="margin-bottom:20px">
            <div>新密码:</div>
            <input class="easyui-textbox" style="width:100%;height:32px">
        </div>
        <div style="margin-bottom:20px">
            <div>重复密码:</div>
            <input class="easyui-textbox" style="width:100%;height:32px">
        </div>

        <div>
            <a href="#" class="easyui-linkbutton" iconCls="icon-ok" style="width:100%;height:32px">提交</a>
        </div>
    </div>
</div>
</body>
</html>
