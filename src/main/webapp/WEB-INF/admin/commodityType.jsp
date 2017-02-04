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
</head>
<body>
<div style="float: left;width: 50%;margin-left: 20px;margin-top: 20px">
    <div class="easyui-panel" title="Ajax Form" style="width:300px;padding:10px;">
        <form id="ff" action="form1_proc.php" method="post" >
            <table>
                <tr>
                    <td>姓名:</td>
                    <td><input name="name" class="f1 easyui-textbox"></input></td>
                </tr>
                <tr>
                    <td>性别:</td>
                    <td>
                        <input name="gender" type="radio" value="0" checked="checked">男</input>
                        <input name="gender" type="radio" value="1" >女</input>
                    </td>
                </tr>
                <tr>
                    <td>出生日期:</td>
                    <td>
                        <input name="gender" type="radio" value="0" checked="checked">男</input>
                        <input name="gender" type="radio" value="1" >女</input>
                    </td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input name="email" class="f1 easyui-textbox"></input></td>
                </tr>
                <tr>
                    <td>手机:</td>
                    <td><input name="phone" class="f1 easyui-textbox"></input></td>
                </tr>
                <tr>
                    <td>地址:</td>
                    <td><input name="file" class="f1 easyui-filebox"></input></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Submit"></input></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div style="float: left;width: 50%"></div>
<style scoped>
    .f1{
        width:200px;
    }
</style>
<script>
    $(function(){
        $('#ff').form({
            success:function(data){
                $.messager.alert('Info', data, 'info');
            }
        });
    });
</script>
</body>
</html>
