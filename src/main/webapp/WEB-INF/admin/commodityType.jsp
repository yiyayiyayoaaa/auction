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

        function DateTimeFormatter(value) {
            if (value == null || value.trim() == "") {
                return "";
            }
            var date = new Date(value);
            return date.toLocaleString();
        }
    </script>
</head>
<body>
<div style="margin:5px 0;"></div>

<table id="dg" class="easyui-datagrid" title="分类管理" style="width:100%;height:500px"
       toolbar="#toolbar" data-options="pageSize:20,rownumbers:true,fitColumns:true,pagination:false,singleSelect:true,collapsible:true,
           url:'${pageContext.request.contextPath}/admin/findTypeList.do',method:'get'">
    <thead>
    <tr>
        <th data-options="field:'typeName',align:'center'">分类名称</th>
        <th data-options="field:'description',align:'center'">分类描述</th>
        <th data-options="field:'createTime',align:'center',formatter:DateTimeFormatter">创建日期</th>
        <th data-options="field:'updateTime',align:'center',formatter:DateTimeFormatter">修改日期</th>
    </tr>
    </thead>
</table>
<div id="toolbar">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新建分类</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
    <%--<div style="width: 220px;float: right;margin-right: 10px">--%>
        <%--<input id="search" class="easyui-textbox"  style="width: 100%" data-options="buttonText:'查询',onClickButton:doSearch,buttonAlign:'left',buttonIcon:'icon-search'"/>--%>
    <%--</div>--%>
</div>
<div id="dlg" class="easyui-dialog" style="width:400px" closed="true" buttons="#dlg-buttons">
    <form id="fm">
        <table style="margin:0;padding:20px 50px">
            <tr>
                <td>分类名称:</td>
                <td><input id="typeName" name="typeName" class="f1 easyui-textbox"/></td>
            </tr>
            <tr>
                <td>分类描述:</td>
                <td><input id="description" name="description" class="f1 easyui-textbox" data-options="multiline:true"/></td>
            </tr>
        </table>
    </form>
</div>
<div id="dlg-buttons">
    <a id="save" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok"  onclick="submitData()" style="width:90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeDialog()" style="width:90px">取消</a>
</div>

<script type="text/javascript">
    var fm = $('#fm');
    var url;
    function newUser(){
        fm.form('reset');
        $('#dlg').dialog('open').dialog('center').dialog('setTitle','信息录入');
        url =  "${pageContext.request.contextPath}/admin/addType.do";
    }
    function editUser(){
        fm.form('reset');
        var row = $('#dg').datagrid('getSelected');
        if (row){
            $('#dlg').dialog('open').dialog('center').dialog('setTitle','信息编辑');
            fm.form('load',row);
            url = "${pageContext.request.contextPath}/admin/updateType.do?id=" + row.id;
        }
    }
    function destroyUser(){
        url = "${pageContext.request.contextPath}/admin/deleteType.do";
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
        var typeName = $("#typeName").val();
        var description = $("#description").val();
        $.post(
            url,
            {
                typeName:typeName,
                description:description
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
