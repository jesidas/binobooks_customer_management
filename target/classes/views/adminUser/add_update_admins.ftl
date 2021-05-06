<!DOCTYPE html>
<html>
    <head>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:80%;">
            <#-- UserID -->
            <input name="id" type="hidden" value="${(userInfo.id)!}"/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">User Name</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input name"
                           lay-verify="required" name="name" id="name" value="${(userInfo.name)!}" placeholder="Please Enter User Name">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">Real Name</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input name"
                           lay-verify="required" name="trueName" id="trueName" value="${(userInfo.trueName)!}" placeholder="Please Enter Real Name">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">Email</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userEmail"
                           lay-verify="email" name="email" value="${(userInfo.email)!}"
                           id="email"
                           placeholder="Please Enetr Email Address">
                </div>
            </div>

            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">Phone Number</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userEmail"
                           lay-verify="phone" name="phone" value="${(userInfo.phone)!}" id="phone" placeholder="Please Enter Phone Number">
                </div>
            </div>

            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">Role</label>
                <div class="layui-input-block">
                    <select name="roleIds"  xm-select="selectId">
                    </select>
                </div>
            </div>


            <br/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-lg" lay-submit=""
                            lay-filter="addOrUpdateUser">Confirm
                    </button>
                    <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">Cancel</button>
                </div>
            </div>
        </form>

    <script type="text/javascript" src="${ctx}/js/adminUser/add.update.admin.js"></script>
    </body>
</html>