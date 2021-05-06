<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">

    <input name="id" type="hidden" value="${(customer.id)!}"/>

    <input name="id" type="hidden" value="${(customer.id)!}"/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">User Name</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input name"
                   lay-verify="required" name="name" id="name" value="${(customer.name)!}" placeholder="Please Enter User Name">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">Real Name</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input name"
                   lay-verify="required" name="trueName" id="trueName" value="${(customer.trueName)!}" placeholder="Please Enter True Name">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">Email</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userEmail"
                   lay-verify="email" name="email" value="${(customer.email)!}"
                   id="email"
                   placeholder="Please Enetr Email Address">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">Phone Number</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userEmail"
                   lay-verify="phone" name="phone" value="${(customer.phone)!}" id="phone" placeholder="Please Enter Phone Number">
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateCustomer">Confirm
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">Cancel</button>
        </div>
    </div>
</form>


<script type="text/javascript" src="${ctx}/js/customer/add.update.customer.js"></script>
</body>
</html>