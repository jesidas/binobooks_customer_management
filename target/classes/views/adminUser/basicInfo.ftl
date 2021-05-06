<!DOCTYPE html>
<html>
    <head>
        <title>User Management</title>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" >


            <table id="userInfo" class="layui-table"  lay-filter="users"></table>

            <script type="text/html" id="toolbarDemo">
                <div class="layui-btn-container">

                </div>
            </script>

            <script id="userListBar" type="text/html">
                <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">Edit</a>
                <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">Delete Account</a>
            </script>
        </form>

    <script type="text/javascript" src="${ctx}/js/adminUser/basicInfo.js"></script>
    </body
</html>