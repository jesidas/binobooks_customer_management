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
                   lay-verify="required" name="moduleName" id="moduleName" value="${(module.moduleName)!}"   placeholder="Please Enter Menu Name">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">Menu Style</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   name="moduleStyle" id="moduleStyle" value="${(module.moduleStyle)!""}" placeholder="Please Enter Menu Style">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">Ordering</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                    name="orders" id="orders" placeholder="Please Enter Order Value" value="${(module.orders)!""}">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">Auhtorization Code</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="optValue" id="optValue" placeholder="Please Enter Menu Authorization Code" value="${(module.optValue)!}">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">Menu Level</label>
        <div class="layui-input-block">
            <#if module.grade??>
                <select name="grade" >
                    <option value="0" <#if module.grade==0>selected="selected"</#if> >Level 1 Menu</option>
                    <option value="1" <#if module.grade==1>selected="selected"</#if>>Level 2 Menu</option>
                    <option value="2" <#if module.grade==2>selected="selected"</#if>>Level 3 Menu</option>
                </select>
            </#if>
        </div>
    </div>

    <#if module.grade?? && module.grade==1>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">Menu url</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input userName"
                       lay-verify="required" name="url" id="url" placeholder="Please Enter Menu url" value="${(module.url)!""}">
            </div>
        </div>
    </#if>


    <!--
       Add Root Level Menu
    -->
    <input name="parentId" type="hidden" value="${(module.parentId)!}"/>
    <input name="id" type="hidden" value="${(module.id)!}"/>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="updateModule">Confirm
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">Cancel</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/module/update.js"></script>
</body>
</html>