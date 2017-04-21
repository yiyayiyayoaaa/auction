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

        function StatusFormatter(value) {
            if (value == 0) {
                return "待支付";
            }
            if (value == 1) {
                return "已支付";
            }
            if (value == 2) {
                return "已发出";
            }
            if (value == 3) {
                return "已完成";
            }
            if (value == 4) {
                return "已取消";
            }
        }
    </script>
</head>
<body>
<div style="margin:5px 0;"></div>

<table id="dg" class="easyui-datagrid" title="分类管理" style="width:100%;height:600px"
       toolbar="#toolbar" data-options="pageSize:20,rownumbers:true,fitColumns:true,pagination:true,singleSelect:true,collapsible:true,
           url:'${pageContext.request.contextPath}/admin/findAllOrders.do',method:'get'">
    <thead>
    <tr>
        <th data-options="field:'orderNum',align:'center'">订单号</th>
        <th data-options="field:'commodityName',align:'center'">商品名</th>
        <th data-options="field:'address',align:'center'">收货地址</th>
        <th data-options="field:'price',align:'center'">金额</th>
        <th data-options="field:'username',align:'center'">收货人</th>
        <th data-options="field:'phone',align:'center'">收货人联系电话</th>
        <th data-options="field:'status',align:'center',formatter:StatusFormatter">订单状态</th>
        <th data-options="field:'payTime',align:'center',formatter:DateTimeFormatter">支付时间</th>
        <th data-options="field:'endTime',align:'center',formatter:DateTimeFormatter">结束时间</th>
        <th data-options="field:'startTime',align:'center',formatter:DateTimeFormatter">创建时间</th>
        <th data-options="field:'updateTime',align:'center',formatter:DateTimeFormatter">修改时间</th>
    </tr>
    </thead>
</table>
<div id="toolbar">
    <%--<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新建分类</a>--%>
    <%--<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>--%>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="send()">发货</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">取消订单</a>
    <div style="width: 220px;float: right;margin-right: 10px">
        <input id="search" class="easyui-textbox"  style="width: 100%" data-options="buttonText:'查询',onClickButton:doSearch,buttonAlign:'left',buttonIcon:'icon-search'"/>
    </div>
</div>

<script type="text/javascript">
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
    var fm = $('#fm');
    var url;
    function destroyUser(){
        url = "${pageContext.request.contextPath}/admin/cancelOrder.do";
        var row = $('#dg').datagrid('getSelected');
        if (row.status == 4){
            showMessage("ERROR","该订单已取消！");
            return;
        }
        if (row){
            $.messager.confirm('警告','你确定要取消该订单?',function(r){
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

    function send() {
        url = "${pageContext.request.contextPath}/admin/send.do";
        var row = $('#dg').datagrid('getSelected');
        if (row.status != 1){
            showMessage("ERROR","该订单还未支付！");
            return;
        }
        if (row){
            $.messager.confirm('警告','是否确定发货?',function(r){
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

    function doSearch(){
        var key = $('#search').val();
        $('#dg').datagrid('load',{
            key:key
        });
    }

</script>

</body>
</html>
