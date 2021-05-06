<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>BackEnd-Login</title>
    <#include "common.ftl">
    <link rel="stylesheet" href="${ctx}/css/index.css" media="all">
</head>
<body>
<div class="layui-container">
    <div class="admin-login-background">
        <div class="layui-form login-form">
            <form class="layui-form" action="">
                <div class="layui-form-item logo-title">
                    <h1>BinoBook Admin Login</h1>
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-username" for="name"></label>
                    <input type="text" name="name" lay-verify="required|account" placeholder="User Name" autocomplete="off" class="layui-input" >
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-password" for="password"></label>
                    <input type="password" name="password" lay-verify="required|password" placeholder="Password" autocomplete="off" class="layui-input" >
                </div>
                <#-- Remember Me :Convert to Session Access Control so NO need to use this function-->
<#--                <div class="layui-form-item">-->
<#--                    <input type="checkbox" name="rememberMe" id="rememberMe" value="true" lay-skin="primary" title="记住密码">-->
<#--                </div>-->
                <div class="layui-form-item">
                    <button class="layui-btn layui-btn-fluid" lay-submit="" lay-filter="login">Loggin in</button>
                </div>
            </form>
            <br/>
            <h3 style="color:green;font-weight:bold;"><a href="${ctx}/mallUserLogin">MallUser Self-Info Login</a></h3>
        </div>
    </div>
</div>
<script src="${ctx}/js/adminLogin.js" charset="utf-8"></script>
</body>
</html>