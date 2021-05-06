<!DOCTYPE html>
<html>
<head>
    <title>Resource Management</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

    <table id="munu-table" class="layui-table" lay-filter="munu-table"></table>

    <!-- Operation Column -->
    <script type="text/html" id="auth-state">
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="add">Add SubCategory</a>
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">Modify</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">Delete</a>
    </script>



    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="expand">
                <i class="layui-icon">&#xe608;</i>
                Expand ALL
            </a>
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="fold">
                <i class="layui-icon">&#xe608;</i>
                Fold ALL
            </a>
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                Add Menu
            </a>
        </div>
    </script>

    <script type="text/javascript" src="${ctx}/js/module/module.js"></script>
</body>
</html>