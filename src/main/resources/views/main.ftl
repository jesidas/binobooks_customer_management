<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>BinoBook</title>
    <#include "common.ftl">
</head>
<body class="layui-layout-body layuimini-all">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header header">
        <div class="layui-logo">
            <a href="">
                <img src="images/logo.png" alt="logo">
                <h1>BinoBook</h1>
            </a>
        </div>
        <a>
            <div class="layuimini-tool"><i title="Expand ALL" class="fa fa-outdent" data-side-fold="1"></i></div>
        </a>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;" data-check-screen="full"><i class="fa fa-arrows-alt"></i></a>
            </li>
            <li class="layui-nav-item layuimini-setting">
                <a href="javascript:;">${(adminUser.userName)!""}</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="javascript:;" data-iframe-tab="${ctx}/adminUser/toSettingPage" data-title="Basic Information" data-icon="fa fa-gears">Basic Information</a>
                    </dd>
                    <dd>
                        <a href="javascript:;" data-iframe-tab="${ctx}/adminUser/toPasswordPage" data-title="Change Password" data-icon="fa fa-gears">Change Password</a>
                    </dd>
                    <dd>
<#--                        <a href="javascript:;" data-iframe-tab="${ctx}/adminUser/logout" data-title="Logging Out" class="login-out">Logging Out</a>-->
                        <a href="javascript:;"  data-title="Logging Out" class="login-out">Logging Out</a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item layuimini-select-bgcolor mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;"></a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll layui-left-menu">
            <#-- Verify if the current adminUser has this permission -->
            <#if permissions??>
            <ul class="layui-nav layui-nav-tree layui-left-nav-tree layui-this" id="currency">
                <#-- Use the seq_contains of freemaker to verify whether the menu is needed to display -->
                <#if permissions?seq_contains("20") >
                <li class="layui-nav-item">
                    <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-flag"></i><span class="layui-left-nav"> Customer Management</span> <span class="layui-nav-more"></span></a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-3" data-tab="customer/index" target="_self"><i class="fa fa-exchange"></i><span class="layui-left-nav"> Customer Information Management</span></a>
                        </dd>
                        <dd>
                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-4" data-tab="customer/order" target="_self"><i class="fa fa-adminUser-times"></i><span class="layui-left-nav">Customer Orders</span></a>
                        </dd>
                    </dl>
                </li>
                </#if>
                <#if permissions?seq_contains("50") >
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-desktop"></i><span class="layui-left-nav"> IT Management</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-5" data-tab="adminUser/swagger" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> Swagger-UI</span></a>
                            </dd>
                        </dl>
                    </li>
                </#if>
                <#if permissions?seq_contains("60") >

                <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-gears"></i><span class="layui-left-nav"> Staff & RBAC</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
<#--                            <#if permissions?seq_contains("6040") >
                                <dd>
                                    <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-10" data-tab="adminUser/swagger" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> Swagger-UI</span></a>
                                </dd>
                            </#if>-->
                            <#if permissions?seq_contains("6010") >
                                <dd>
                                    <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="adminUser/index" target="_self"><i class="fa fa-adminUser"></i><span class="layui-left-nav"> Staff Management</span></a>
                                </dd>
                            </#if>
                            <#if permissions?seq_contains("6020") >
                                <dd class="">
                                    <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-12" data-tab="role/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> Role Management</span></a>
                                </dd>
                            </#if>
                            <#if permissions?seq_contains("6030") >
                                <dd class="">
                                    <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="module/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> Menu Management</span></a>
                                </dd>
                            </#if>
                        </dl>
                    </li>
                </#if>
                <span class="layui-nav-bar" style="top: 201px; height: 0px; opacity: 0;"></span>
            </ul>
            </#if>
        </div>
    </div>

    <div class="layui-body">
        <div class="layui-tab" lay-filter="layuiminiTab" id="top_tabs_box">
            <ul class="layui-tab-title" id="top_tabs">
                <li class="layui-this" id="layuiminiHomeTabId" lay-id="welcome"><i class="fa fa-home"></i> <span>Front Page</span></li>
            </ul>

            <ul class="layui-nav closeBox">
                <li class="layui-nav-item">
                    <a href="javascript:;"> <i class="fa fa-dot-circle-o"></i> Page Operation</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-page-close="other"><i class="fa fa-window-close"></i> Close Others</a></dd>
                        <dd><a href="javascript:;" data-page-close="all"><i class="fa fa-window-close-o"></i> Close ALL</a></dd>
                    </dl>
                </li>
            </ul>
            <div class="layui-tab-content clildFrame">
                <div id="layuiminiHomeTabIframe" class="layui-tab-item layui-show">
                </div>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript" src="${ctx}/js/main.js"></script>
</body>
</html>
