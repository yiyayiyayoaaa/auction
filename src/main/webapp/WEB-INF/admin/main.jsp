<%--
  Created by IntelliJ IDEA.
  User: AMOBBS
  Date: 2016/12/15
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>拍卖系统后台</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui/themes/color.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">

        //打开新的tab
        function openTab(text,url){
            //如果已经打开了则直接显示已打开的
            var tab = $("#tabs");
            if (tab.tabs("exists", text)) {
                tab.tabs("select", text)
            } else {
                var content = "<iframe frameborder='0' style='width:100%;height:100%' scorlling='auto' src=" + url + "></iframe>";
                tab.tabs("add", {
                    title: text,
                    closable: true,
                    content: content
                })
            }
        }

    </script>
</head>
    <body>
        <div class="easyui-layout" style="width:100%;height:100%;">

            <div data-options="region:'north'" style="height:25px">
                <span style="width: 80%;float: left; align-content: center">xxxx拍卖系统后台管理</span>
                <span style="width: 20%;float: right;align-content: center" >当前登录：${sessionScope.loginAdmin.name} &nbsp;&nbsp;&nbsp;上次登录时间:<fmt:formatDate value="${sessionScope.loginAdmin.last_login_time}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
            </div>

            <div data-options="region:'south',split:true" style="height:25px;"></div>

            <div data-options="region:'west',split:true" title="导航菜单" style="width:250px;">
                <div class="easyui-accordion" data-options="fit:false,border:false">
                    <div title="商品管理" style="padding:10px;">
                        <a href="#" class="easyui-linkbutton" data-options="plain:true">商品列表</a><br>
                        <a href="#" class="easyui-linkbutton" data-options="plain:true">分类管理</a><br>
                    </div>
                    <div title="交易管理"  style="padding:10px;">
                        <a href="#" class="easyui-linkbutton" data-options="plain:true">交易信息</a><br>
                        <a href="#" class="easyui-linkbutton" data-options="plain:true">订单管理</a>
                    </div>
                    <div title="用户管理"  style="padding:10px;">
                        <a href="javascript:openTab('客户列表','${pageContext.request.contextPath}/admin/customerList.do')" class="easyui-linkbutton" data-options="plain:true">客户列表</a><br>
                        <a href="javascript:openTab('用户列表','${pageContext.request.contextPath}/admin/findCustomerPage.do')" class="easyui-linkbutton" data-options="plain:true">用户列表</a><br>
                        <a href="javascript:openTab('账户管理','${pageContext.request.contextPath}/admin/findCustomerPage.do')" class="easyui-linkbutton" data-options="plain:true">账户管理</a>
                    </div>
                    <div title="消息管理" style="padding:10px">
                        <a href="#" class="easyui-linkbutton" data-options="plain:true">留言列表</a><br>
                        <a href="#" class="easyui-linkbutton" data-options="plain:true">意见反馈</a><br>
                    </div>
                    <div title="管理员管理" style="padding:10px">
                        <a href="#" class="easyui-linkbutton" data-options="plain:true">管理员列表</a><br>
                        <a href="#" class="easyui-linkbutton" data-options="plain:true">个人信息</a><br>
                    </div>
                    <div title="系统管理" style="padding:10px">
                        <a href="#" class="easyui-linkbutton" data-options="plain:true">系统设置</a><br>
                    </div>
                </div>
            </div>

            <div data-options="region:'center'">
               <div id="tabs" class="easyui-tabs" style="width:100%;height:100%;">
                    <div title="首页" style="padding:10px;display:none;">
                        tab1
                    </div>
                </div>
            </div>

        </div>
    </body>
</html>
