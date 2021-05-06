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
                    <h1>BinoBook New Customer Register</h1><br/>
                    <h3 style="color:red">Warning!!! We are giving you a default password which is </h3>
                    <h3 style="color:red">123456 after login please change it immediately!!!</h3>
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-username" for="name">Name</label>
                    <input type="text" name="name" lay-verify="required|account" autocomplete="off" class="layui-input" >
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-username" for="email">Email</label>
                    <input type="email" name="email" lay-verify="required"  autocomplete="off" class="layui-input" >
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-password" for="phone">Phone</label>
                    <input type="phone" name="phone" lay-verify="required" autocomplete="off" class="layui-input" >
                </div>
                <#-- Remember Me :Convert to Session Access Control so NO need to use this function-->
                <#--                <div class="layui-form-item">-->
                <#--                    <input type="checkbox" name="rememberMe" id="rememberMe" value="true" lay-skin="primary" title="记住密码">-->
                <#--                </div>-->
                <div class="layui-form-item">
                    <button class="layui-btn layui-btn-fluid" lay-submit="" lay-filter="register">Register</button>
                </div>
                <br/>
                <h3 style="color:green;font-weight:bold;"><a href="${ctx}/adminLogin">Admin Backend Login</a></h3>
                <br/>
                <h3 style="color:green;font-weight:bold;"><a href="${ctx}/mallUserLogin">MallUser Self-Info Login</a></h3>
            </form>
        </div>
    </div>
</div>
<script src="${ctx}/js/mallUserRegister.js" charset="utf-8"></script>
</body>
</html>