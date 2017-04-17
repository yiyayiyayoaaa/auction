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
    </style>
    <script>
        function DateFormatter(value){
            if(value == null || value.trim() == ""){
                return "";
            }
            var date = new Date(value);
            var y = date.getFullYear();
            var M = date.getMonth() + 1;
            var d = date.getDay();
            return  y + "-" + (M<10?'0'+M:M) + "-" + (d<10?'0'+d:d);
        }

        function DateTimeFormatter(value){
            if(value == null || value.trim() == ""){
                return "";
            }
            var date = new Date(value);
            return date.toLocaleString();
        }
        function genderFormatter(value) {
            if(value == 0){
                return "男";
            }else{
                return "女";
            }
        }
    </script>
</head>
<body>
    <div style="margin:5px 0;"></div>

    <table id="dg" class="easyui-datagrid" title="客户信息" style="width:100%;height:600px"
           toolbar="#toolbar" data-options="pageSize:20,rownumbers:true,fitColumns:true,pagination:true,singleSelect:true,collapsible:true,
           url:'${pageContext.request.contextPath}/admin/findCustomer.do',method:'get'">
        <thead>
        <tr>
            <th data-options="field:'name',align:'center'">姓名</th>
            <th data-options="field:'gender',align:'center',formatter:genderFormatter">性别</th>
            <th data-options="field:'phone',align:'center'">手机</th>
            <th data-options="field:'idcard',align:'center'">身份证号</th>
            <th data-options="field:'email',align:'center'">邮箱</th>
            <th data-options="field:'address',align:'center'">地址</th>
            <th data-options="field:'birth',align:'center',formatter:DateFormatter">出生日期</th>
            <th data-options="field:'registrationTime',align:'center',formatter:DateTimeFormatter">登记日期</th>
            <th data-options="field:'updateTime',align:'center',formatter:DateTimeFormatter">修改日期</th>
        </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新客户登记</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
        <div style="width: 220px;float: right;margin-right: 10px">
            <input id="search" class="easyui-textbox"  style="width: 100%" data-options="buttonText:'查询',onClickButton:doSearch,buttonAlign:'left',buttonIcon:'icon-search'"/>
        </div>
    </div>
    <div id="dlg" class="easyui-dialog" style="width:600px" closed="true" buttons="#dlg-buttons">
        <form id="fm">
            <table style="margin:0;padding:20px 50px">
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
                    <td><input id="IDCard" name="idcard" class="f1 easyui-textbox"/></td>
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
                        <input id="address" name="address" class="easyui-textbox"  multiline="true"  style="width:100%;height:120px">
                    </td>
                </tr>
                <%--<tr>--%>
                    <%--<td></td>--%>
                    <%--<td>--%>
                        <%--&lt;%&ndash;<a href="javascript:submitData()" class="easyui-linkbutton" data-options="toggle:true,plain:true">提交</a>&ndash;%&gt;--%>
                        <%--<span id="result"></span>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a id="save" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok"  onclick="submitData()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeDialog()" style="width:90px">取消</a>
    </div>

    <script type="text/javascript">
        new PCAS("province","city","area");
        var fm = $('#fm');
        $.extend($.fn.textbox.methods, {
            addClearBtn: function(jq, iconCls){
                return jq.each(function(){
                    var t = $(this);
                    var opts = t.textbox('options');
                    opts.icons = opts.icons || [];
                    opts.icons.unshift({
                        iconCls: iconCls,
                        handler: function(e){
                            $(e.data.target).textbox('clear').textbox('textbox').focus();
                            $(this).css('visibility','hidden');
                            doSearch();
                        }
                    });
                    t.textbox();
                    if (!t.textbox('getText')){
                        t.textbox('getIcon',0).css('visibility','hidden');
                    }
                    t.textbox('textbox').bind('keyup', function(){
                        var icon = t.textbox('getIcon',0);
                        if ($(this).val()){
                            icon.css('visibility','visible');
                        } else {
                            icon.css('visibility','hidden');
                        }
                    });
                });
            }
        });

        $(function(){
            $('#search').textbox().textbox('addClearBtn', 'icon-clear');
        });

        function doSearch(){
            var key = $('#search').val();
            $('#dg').datagrid('load',{
                key:key
            });
        }

        var url;
        function newUser(){
            fm.form('reset');
            $('#dlg').dialog('open').dialog('center').dialog('setTitle','信息录入');
            url =  "${pageContext.request.contextPath}/admin/addCustomer.do";
        }
        function editUser(){
            fm.form('reset');
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('center').dialog('setTitle','信息编辑');
                fm.form('load',row);
                url = "${pageContext.request.contextPath}/admin/updateCustomer.do?id=" + row.id;
            }
        }
        function destroyUser(){
            url = "${pageContext.request.contextPath}/admin/deleteCustomer.do";
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('警告','你确定要删除该数据?',function(r){
                    if (r){
                        $.post(url,{id:row.id},function(data){
                            if (data.resultCode == 1){
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                                    msg: data.data
                                });
                            }
                        },'json');
                    }
                });
            }
        }

        function closeDialog() {
            $('#dlg').dialog('close');        // close the dialog
            fm.form('clear');
        }

        function submitData() {
            var name = $("#name").val();
            var gender = $('input:radio:checked').val();
            var birth = $("#birth").val();
            var IDCard = $("#IDCard").val();
            var phone = $("#phone").val();
            var email = $("#email").val();
            var province = $("#province").val();
            var city = $("#city").val();
            var area = $("#area").val();
            var address = province==null?"":province
                + " " + city==null?"":city
                + " " + area==null?"":area
                + " " + $("#address").val();
            $.post(
               url,
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
                    if (data.resultCode == 0){
                        $.messager.show({    // show error message
                            title: 'Error',
                            msg: data.data
                        });
                    } else {
                        closeDialog();
                        $('#dg').datagrid('reload');    // reload the user data
                    }
                },
                "json"
            );
        }
    </script>

</body>
</html>
