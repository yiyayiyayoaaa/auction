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
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/js/PCASClass.js"></script>

    <script>
        function submitData() {
            var name = $("#name").val();
            var gender = $('input:radio:checked').val();
            var birth = $("#birth").val();
            var IDCard = $("#IDCard").val();
            var phone = $("#phone").val();
            var email = $("#email").val();
            var address = $("#province").val() + " " + $("#city").val() + " " + $("#area").val() + " " + $("#address").val()
            $.post(
                "${pageContext.request.contextPath}/admin/addCustomer.do",
                {
                    name:name,
                    gender:gender,
                    birth:birth,
                    IDCard:IDCard,
                    phone:phone,
                    email:email,
                    address:address
                } ,
                function (data) {
                    //alert(data)
                   $("#result").append(data.data)
                },
                "json"
            );
        }
    </script>
</head>
<body>
<div style="float: left;width: 50%;margin-left: 20px;margin-top: 20px;text-align: center">
    <div class="easyui-panel" title="客户信息" style="width:80%;padding:10px;">
            <table>
                <tr>
                    <td>姓名:</td>
                    <td><input id="name" name="name" class="f1 easyui-textbox"/></td>
                </tr>
                <tr>
                    <td>性别:</td>
                    <td>
                        <input name="gender" type="radio" value="0" checked="checked"/>男
                        <input name="gender" type="radio" value="1" >女
                    </td>
                </tr>
                <tr>
                    <td>出生日期:</td>
                    <td>
                        <input id="birth" name="birth" class="easyui-datebox"   style="width:50%;">
                    </td>
                </tr>
                <tr>
                    <td>身份证号:</td>
                    <td><input id="IDCard" name="IDCard" class="f1 easyui-textbox"/></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input id="email" name="email" class="f1 easyui-textbox"/></td>
                </tr>
                <tr>
                    <td>手机:</td>
                    <td><input id="phone" name="phone" class="f1 easyui-textbox"/></td>
                </tr>
                <tr>
                    <td>地址:</td>
                    <td>
                        <select id="province" name="province"></select>
                        <select id="city" name="city"></select>
                        <select id="area" name="area"></select><br>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input id="address" class="easyui-textbox"  multiline="true"  style="width:100%;height:120px">
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <a href="javascript:submitData()" class="easyui-linkbutton" data-options="toggle:true,plain:true">提交</a>
                        <span id="result"></span>
                    </td>
                </tr>
            </table>
    </div>
</div>
<style scoped>
    .f1{
        width:200px;
    }
</style>
<script type="text/javascript">
    new PCAS("province","city","area")
</script>

</body>
</html>
