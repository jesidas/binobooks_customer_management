<!DOCTYPE html>
<html>
    <head>
        <title>User Management</title>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
<#--        <form class="layui-form" >-->
<#--            <blockquote class="layui-elem-quote quoteBox">-->
<#--                <form class="layui-form">-->
<#--                    <div class="layui-inline">-->
<#--                        <div class="layui-input-inline">-->
<#--                            <input type="text" name="name" class="layui-input searchVal" placeholder="AdminUser Name" />-->
<#--                        </div>-->
<#--                        <div class="layui-input-inline">-->
<#--                            <input type="text" name="email" class="layui-input searchVal" placeholder="Email" />-->
<#--                        </div>-->
<#--                        <div class="layui-input-inline">-->
<#--                            <input type="text" name="phone" class="layui-input searchVal" placeholder="Phone Number" />-->
<#--                        </div>-->
<#--                        <a class="layui-btn search_btn" data-type="reload">-->
<#--                            <i class="layui-icon">&#xe615;</i>-->
<#--                            Search-->
<#--                        </a>-->
<#--                    </div>-->
<#--                </form>-->
<#--            </blockquote>-->

            <table id="userInfo" class="layui-table"  lay-filter="users"></table>

            <script type="text/html" id="toolbarDemo">
                <div class="layui-btn-container">
<#--                    <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">-->
<#--                        <i class="layui-icon">&#xe608;</i>-->
<#--                        Add AdminUser-->
<#--                    </a>-->
<#--                    <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del" ><!--class="login-out del"&ndash;&gt;-->
<#--                        <i class="layui-icon">&#xe608;</i>-->
<#--                        Cancel Account-->
<#--                    </a>-->
                </div>
            </script>

            <!--Operation-->
            <script id="userListBar" type="text/html">
                <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">Edit</a>
                <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">Delete Account</a>
            </script>
        </form>

    <script type="text/javascript" src="${ctx}/js/mallUser/basicInfo.js"></script>
    </body>
</html>