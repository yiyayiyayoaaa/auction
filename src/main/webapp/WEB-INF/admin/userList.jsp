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

    <table id="dg" class="easyui-datagrid" title="客户信息" style="width:100%;height:500px"
           toolbar="#toolbar" data-options="pageSize:20,rownumbers:true,fitColumns:true,pagination:true,singleSelect:true,collapsible:true,
           url:'${pageContext.request.contextPath}/admin/findUser.do',method:'get'">
        <thead>
        <tr>
            <th data-options="field:'username',align:'center'">用户名</th>
            <th data-options="field:'name',align:'center'">姓名</th>
            <th data-options="field:'nickname',align:'center'">昵称</th>
            <th data-options="field:'gender',align:'center',formatter:genderFormatter">性别</th>
            <th data-options="field:'phone',align:'center'">手机</th>
            <th data-options="field:'idcard',align:'center'">身份证号</th>
            <th data-options="field:'email',align:'center'">邮箱</th>
            <th data-options="field:'birth',align:'center',formatter:DateFormatter">出生日期</th>
            <th data-options="field:'registrationTime',align:'center',formatter:DateTimeFormatter">登记日期</th>
            <th data-options="field:'updateTime',align:'center',formatter:DateTimeFormatter">修改日期</th>
        </tr>
        </thead>
    </table>
    <div id="toolbar">
        <div style="width: 220px;">
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

        function doSearch(){
            var key = $('#search').val();
            $('#dg').datagrid('load',{
                key:key
            });
        }

    </script>

</body>
</html>
