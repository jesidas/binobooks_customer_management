<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">Menu Name</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="moduleName" id="moduleName"   placeholder="Please Enter Menu Name">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">Menu Style</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   name="moduleStyle" id="moduleStyle" placeholder="Please Enter Menu Style">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">Ordering</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                    name="orders" id="orders" placeholder="Please Enter Order Value">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">Authorization Code</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="optValue" id="optValue" placeholder="Please Enter Menu Authorization Code">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">Menu Level</label>
        <div class="layui-input-block">
            <#if grade??>
                <select name="grade" >
                    <option value="0" <#if grade==0>selected="selected"</#if> >Level 1 Menu</option>
                    <option value="1" <#if grade==1>selected="selected"</#if>>Level 2 Menu</option>
                    <option value="2" <#if grade==2>selected="selected"</#if>>Level 3 Menu</option>
                </select>
            </#if>
        </div>
    </div>

    <#if grade==1>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">Menu url</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input userName"
                       lay-verify="required" name="url" id="url" placeholder="Please Enter Menu url">
            </div>
        </div>
    </#if>

    <input name="parentId" type="hidden" value="${parentId}"/>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addModule">Confirm
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">Cancel</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/module/add.js"></script>
</body>
</html>