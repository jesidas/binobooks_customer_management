layui.use(['table', 'treetable'], function () {
    var $ = layui.jquery;
    var table = layui.table;
    var treeTable = layui.treetable;

    treeTable.render({
        treeColIndex: 1,
        treeSpid: -1,
        treeIdName: 'id',
        treePidName: 'parentId',
        elem: '#munu-table',
        url: ctx+'/module/list',
        toolbar: "#toolbarDemo",
        treeDefaultClose:true,
        page: true,
        cols: [[
            {type: 'numbers'},
            {field: 'moduleName', maxWidth: 20, title: 'Menu Name'},
            {field: 'optValue',  maxWidth: 20, title: 'Authorization Code'},
            {field: 'url', maxWidth: 20,  title: 'Menu url'},
            {field: 'createDate',  maxWidth: 20, title: 'Create Time'},
            {field: 'updateDate',  maxWidth: 20, title: 'Update Time'},
            {
                field: 'grade', width: 100, align: 'center', templet: function (d) {
                    if (d.grade == 0) {
                        return '<span class="layui-badge layui-bg-blue">Index</span>';
                    }
                    if(d.grade==1){
                        return '<span class="layui-badge-rim">Menu</span>';
                    }
                    if (d.grade == 2) {
                        return '<span class="layui-badge layui-bg-gray">Button</span>';
                    }
                }, title: 'Category'
            },
            {templet: '#auth-state', width: 10, align: 'center', title: 'Operation'}
        ]],
        done: function () {
            layer.closeAll('loading');
        }
    });

    table.on('toolbar(munu-table)', function (data) {

        if (data.event == "expand") {

            treeTable.expandAll("#munu-table");

        } else if (data.event == "fold") {

            treeTable.foldAll("#munu-table");

        } else if (data.event == "add") {

            openAddModuleDialog(0, -1)
        }
    });

    table.on('tool(munu-table)',function (data) {

        if (data.event == "add") {

            if (data.data.grade == 2) {
                layer.msg("The Level 4 Menu is Not Supported！",{icon:5});
                return;
            }

            openAddModuleDialog(data.data.grade+1, data.data.id);

        } else if (data.event == "edit") {

            openUpdateModuleDialog(data.data.id);

        } else if (data.event == "del") {

            deleteModule(data.data.id);
        }
    });


    function openAddModuleDialog(grade, parentId) {
        var title = "<h3>Resource Management - Add Resources</h3>";
        var url = ctx + "/module/toAddModulePage?grade=" + grade + "&parentId=" + parentId;

        layui.layer.open({
            type:2,
            title:title,
            content:url,
            area:["700px","450px"],
            maxmin:true
        });
    }

    function openUpdateModuleDialog(id) {
        var title = "<h3>Resource Management - Modify Resources</h3>";
        var url = ctx + "/module/toUpdateModulePage?id=" + id;

        layui.layer.open({
            type:2,
            title:title,
            content:url,
            area:["700px","450px"],
            maxmin:true
        });
    }

    function deleteModule(id) {

        layer.confirm('Are you sure for Deletiion？',{icon:3, title:"Resource Management"}, function (data) {

            $.post(ctx+ "/module/delete",{id:id},function (result) {

                if (result.code == 200) {
                    layer.msg("Delete Success！",{icon:6});

                    window.location.reload();
                } else {
                    layer.msg(result.msg,{icon:5});
                }
            });
        });
    }
    
});