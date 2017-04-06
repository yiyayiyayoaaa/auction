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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/jquery.fileupload.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/jquery.fileupload-ui.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/vendor/jquery.ui.widget.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery.iframe-transport.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery.fileupload.js"></script>

    <style>
        .f1{
            width:200px;
        }
    </style>
    <script>
        function statusFormatter(value) {
            switch (value){
                case 0:
                    return "已登记";
                case 1:
                    return "待拍卖";
                case 2:
                    return "正在拍卖";
                case 3:
                    return "成交";
                case 4:
                    return "流拍";
                case 5:
                    return "其他";
                case 6:
                    return "下架";
            }
        }

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
            return date.toLocaleString()
        }
        function genderFormatter(value) {
            if(value == 0){
                return "男";
            }else{
                return "女";
            }
        }

        function remove(value) {
            $(value).remove();
        }

        <%--$(function () {--%>
            <%--$('#fileupload').fileupload({--%>
                <%--url:'${pageContext.request.contextPath}/upload.do',--%>
                <%--dataType: 'json',--%>
                <%--sequentialUploads: true--%>
            <%--}).bind('fileuploadprogress', function (e, data) {--%>
                <%--var progress = parseInt(data.loaded / data.total * 100, 10);--%>
                <%--$("#uploadProgress").css('width',progress + '%');--%>
                <%--$("#uploadProgress").html(progress + '%');--%>
            <%--}).bind('fileuploaddone', function (e, data) {--%>
                <%--var img = "<img src='${pageContext.request.contextPath}/" + data.result.result +"'onclick='remove(this)' style='height: 60px;width: 50px'/>";--%>
                <%--$("#imgList").append(img);--%>
            <%--});--%>
        <%--});--%>
    </script>
</head>
<body>
    <div style="margin:5px 0;"></div>

    <table id="dg" class="easyui-datagrid" title="商品列表" style="width:100%;height:100%"
           toolbar="#toolbar" data-options="pageSize:20,rownumbers:true,fitColumns:true,pagination:true,singleSelect:true,collapsible:true,
           url:'${pageContext.request.contextPath}/admin/findCommodity.do',method:'get'">
        <thead>
        <tr>
            <th data-options="field:'commodityName',align:'center'">名称</th>
            <th data-options="field:'typeName',align:'center'">分类</th>
            <th data-options="field:'appraisedPrice',align:'center'">估价</th>
            <th data-options="field:'reservePrice',align:'center'">保留价</th>
            <th data-options="field:'startingPrice',align:'center'">起拍价</th>
            <th data-options="field:'bidIncrements',align:'center'">竞价增幅</th>
            <th data-options="field:'biddingDeposit',align:'center'">保证金</th>
            <th data-options="field:'hammerPrice',align:'center'">成交价</th>
            <th data-options="field:'customerName',align:'center'">来源</th>
            <th data-options="field:'status',align:'center',formatter:statusFormatter">状态</th>
            <th data-options="field:'startTime',align:'center',formatter:DateTimeFormatter">开始时间</th>
            <th data-options="field:'endTime',align:'center',formatter:DateTimeFormatter">结束时间</th>
        </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">商品登记</a>
        <%--<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>--%>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toAuction()">进入拍卖</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="commodityOn()">上架</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="commodityOff()">下架</a>
        <div style="width: 220px;float: right;margin-right: 10px">
            <input id="search" class="easyui-textbox"  style="width: 100%" data-options="buttonText:'查询',onClickButton:doSearch,buttonAlign:'left',buttonIcon:'icon-search'"/>
        </div>
    </div>

    <div id="dlg" class="easyui-dialog" style="width:600px" closed="true" buttons="#dlg-buttons">
        <form id="fm" style="width:100%;max-width: 400px;padding:30px 60px;" >
            <div style="margin-bottom:10px">
                <input id="name" name="commodityName" class="f1 easyui-textbox"
                       data-options="label: '商品名:', labelPosition: 'top'"/>
            </div>
            <div style="margin-bottom:10px;">
                <input class="easyui-combobox" name="typeName" id="type"
                       data-options="
                             label:'所属分类:',
                             labelPosition: 'top',
                             url:'${pageContext.request.contextPath}/admin/findAllType.do',
                             method:'get',
                             valueField:'id',
                             textField:'typeName',
                             panelHeight:'auto'
                        ">
            </div>
            <div style="margin-bottom:10px;float: left">
                <input id="provider" name="customerName" class="easyui-combobox"
                       data-options="
                             label:'来源:',
                             labelPosition: 'top',
                             url:'${pageContext.request.contextPath}/admin/findAllCustomer.do',
                             method:'get',
                             valueField:'id',
                             textField:'cname',
                             panelHeight:'auto'
                        ">
            </div>
            <div style="margin-bottom:10px;float: left;margin-left: 10px">
                            <input id="reservePrice" name="reservePrice" class="easyui-textbox"
                                   data-options="label: '保留价:', labelPosition: 'top'" >(¥/元)
            </div>
            <div style="margin-bottom:10px;">
                <input id="fileupload" type="file"  name="files[]" multiple>
            </div>

            <div id="imgList">
                <div class="progress progress-striped active" role="progressbar" aria-valuemin="10" aria-valuemax="100" aria-valuenow="0">
                    <div id="uploadProgress" class="progress-bar progress-bar-success" style="width:0;background-color: #00bbee"></div>
                </div>
            </div>
            <div style="margin-bottom:10px">
                <input id="description" name="description" class="easyui-textbox" style="width:100%"
               data-options="label: '商品描述:', labelPosition: 'top',multiline:true" >
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a id="save" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok"  onclick="submitData()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeDialog()" style="width:90px">取消</a>
    </div>

    <div id="dlg2" class="easyui-dialog" style="width:600px" closed="true" buttons="#dlg2-buttons">
        <form id="fm2" style="width:100%;max-width: 400px;padding:30px 60px;">
            <div style="margin-bottom:10px">
                <input name="commodityName" class="f1 easyui-textbox" readonly
                       data-options="label: '商品名:', labelPosition: 'top'"/>
            </div>
            <div style="margin-bottom:10px;float: left;">
                <input id="appraisedPrice" name="appraisedPrice" class="easyui-textbox"
                       data-options="label: '估价:', labelPosition: 'top'" >(¥/元)
            </div>
            <div style="margin-bottom:10px;margin-left: 10px">
                <input id="startingPrice" name="startingPrice" class="easyui-textbox"
                       data-options="label: '起拍价:', labelPosition: 'top'" >(¥/元)
            </div>
            <div style="margin-bottom:10px;float: left;">
                <input id="bidIncrements" name="bidIncrements" class="easyui-textbox"
                       data-options="label: '竞价增幅:', labelPosition: 'top'" >(¥/元)
            </div>
            <div style="margin-bottom:10px;margin-left: 10px">
                <input id="biddingDeposit" name="biddingDeposit" class="easyui-textbox"
                       data-options="label: '保证金:', labelPosition: 'top'" >(¥/元)
            </div>
            <div style="margin-bottom:10px;float: left;">
                <input id="startTime" name="startTime" class="easyui-datetimebox"
                       data-options="label: '开始时间:', labelPosition: 'top'" >
            </div>
            <div style="margin-bottom:10px;margin-left: 10px">
                <input id="endTime" name="endTime" class="easyui-datetimebox"
                       data-options="label: '结束时间:', labelPosition: 'top'" >
            </div>
        </form>
    </div>
    <div id="dlg2-buttons">
        <a id="set" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok"  onclick="auction()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeDialog2()" style="width:90px">取消</a>
    </div>


    <script type="text/javascript">
        var fm = $('#fm');
        var fm2 = $('#fm2');
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

        function initFileUpload() {
            $('#fileupload').fileupload({
                url:'${pageContext.request.contextPath}/upload.do',
                dataType: 'json',
                sequentialUploads: true
            }).bind('fileuploadprogress', function (e, data) {
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $("#uploadProgress").css('width',progress + '%');
                $("#uploadProgress").html(progress + '%');
            }).bind('fileuploaddone', function (e, data) {
                var img = "<img src='${pageContext.request.contextPath}/" + data.result.result +"'onclick='remove(this)' style='height: 60px;width: 50px'/>";
                $("#imgList").append(img);
            });
        }
        function doSearch(){
            var key = $('#search').val();
            $('#dg').datagrid('load',{
                key:key
            });
        }

        var url;

        function clearImgStatus() {
            $("#imgList").children("img").each(function (i) {
                $(this).remove();
            });
//            $("#uploadProgress").css('width',0);
//            $("#uploadProgress").html('');
        }

        function toAuction() {
            var row = $('#dg').datagrid('getSelected');
            if(row){
                $("#dlg2").dialog('open').dialog("center").dialog("setTitle","设置拍卖信息");
                fm2.form('load',row);
            }
            url = "${pageContext.request.contextPath}/admin/auction.do?id=" + row.id;
        }

        function newUser(){
            clearImgStatus();
            initFileUpload();
            //fm.form('clear');
            $('#dlg').dialog('open').dialog('center').dialog('setTitle','信息录入');
            url =  "${pageContext.request.contextPath}/admin/addCommodity.do";
        }
        function editUser(){
            clearImgStatus();
            fm.form('reset');
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('center').dialog('setTitle','信息编辑');

                url = "${pageContext.request.contextPath}/admin/updateCustomer.do?id=" + row.id;
            }
        }
        function commodityOn() {
            url = "${pageContext.request.contextPath}/admin/commodityOn.do";
            var row = $('#dg').datagrid('getSelected');
            if (row.status == 6){
                $.messager.confirm('警告','你确定要上架该商品?',function(r){
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

        function showMessage(title,msg) {
            $.messager.show({    // show error message
                title: title,
                msg: msg
            });
        }

        function commodityOff(){
            url = "${pageContext.request.contextPath}/admin/commodityOff.do";
            var row = $('#dg').datagrid('getSelected');
            if (row.status == 2){
                showMessage("ERROR","拍卖中，无法下架！");
                return;
            }
            if (row.status != 6){
                $.messager.confirm('警告','你确定要下架该商品?',function(r){
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
            $('#dlg').dialog('close');       // close the dialog
            fm.form('clear');
        }

        function closeDialog2() {
            $('#dlg2').dialog('close');       // close the dialog
            fm2.form('clear');
        }
        function auction() {
            var appraisedPrice = $("#appraisedPrice").val();
            var startingPrice = $("#startingPrice").val();
            var bidIncrements = $("#bidIncrements").val();
            var biddingDeposit = $("#biddingDeposit").val();
            var startTime = $("#startTime").val();
            var endTime = $("#endTime").val();
            $.post(
                url,
                {
                    appraisedPrice:appraisedPrice,
                    startingPrice:startingPrice,
                    bidIncrements:bidIncrements,
                    biddingDeposit:biddingDeposit,
                    startTime:startTime,
                    endTime:endTime
                },function (data) {
                    if (data.resultCode == 0){
                        $.messager.show({    // show error message
                            title: 'Error',
                            msg: data.data
                        });
                    } else {
                        closeDialog2();
                        $('#dg').datagrid('reload');    // reload the user data
                    }
                },"json"
            );
        }

        function submitData() {
            var name = $("#name").val();
            var type = $("#type").val();
            var provider = $("#provider").val();
            var reservePrice = $("#reservePrice").val();
            var images = "";
            var $images = $("#imgList");
            $images.find("img").each(function(i){
                images = images + $(this).attr("src") + ",";
            });
            images = images.substring(0,images.length-1);
            var description = $("#description").val();
            $.post(
               url,
                {
                    name:name,
                    typeId:type,
                    customerId:provider,
                    reservePrice:reservePrice,
                    images:images,
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
