layui.use(['table','layer'],function(){
       var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


       var tableIns = table.render({
              id:'roleTable'

              ,elem: '#roleList'

              ,height: 'full-125'

              ,cellMinWidth:95

              ,url: ctx + '/role/list'

              ,page: true

              ,limit:10

              ,limits:[10,20,30,40,50]

              ,toolbar:'#toolbarDemo'

              ,cols: [[

                     {type:'checkbox', fixed:'center'}
                     ,{field: 'id', title: 'id',  sort: true, fixed: 'left'}
                     ,{field: 'roleName', title: 'roleName', align:'center'}
                     ,{field: 'roleRemark', title: 'roleRemark', align:'center'}
                     ,{field: 'createDate', title: 'createDate', align:'center'}
                     ,{field: 'updateDate', title: 'updateDate', align:'center'}
                     ,{title:'Operation',templet:'#roleListBar', fixed: 'right', align:'center', minWidth:150}
              ]]
       });

       $(".search_btn").click(function () {

              tableIns.reload({
                     where: {

                            roleName: $("[name='roleName']").val()
                     }
                     ,page: {
                            curr: 1
                     }
              });
       });


       table.on('toolbar(roles)',function (data) {

            if (data.event == "add") {

                openAddOrUpdateRoleDialog();

            } else if (data.event == "grant") {

                var checkStatus = table.checkStatus(data.config.id);

                openAddGrantDialog(checkStatus.data);
            }
       });


    table.on('tool(roles)',function (data) {

        if (data.event == "edit") {

            openAddOrUpdateRoleDialog(data.data.id);
        } else if (data.event == "del") {

            deleteRole(data.data.id);
        }
    });


   function openAddOrUpdateRoleDialog(roleId) {
        var title = "<h3>Role Managemnet - Role Addition</h3>"
        var url = ctx + "/role/toAddOrUpdateRolePage";

        if (roleId != null && roleId != '') {
            title = "<h3>Role Management - Role Update</h3>";
            url += "?roleId=" + roleId;
        }

        layui.layer.open({
             title:title,
             content:url,
             area:["500px","400px"],
             type:2,
             maxmin:true
        });
   }

    function deleteRole(roleId) {

        layer.confirm('Are you sure for the deletion of this role？',{icon:3, title:"User Management"}, function (index) {

            layer.close(index);

            $.ajax({
                type:"post",
                url:ctx + "/role/delete",
                data:{
                    roleId:roleId
                },
                success:function (result) {

                    if (result.code == 200) {

                        layer.msg("deletion success！",{icon:6});

                        tableIns.reload();
                    } else {

                        layer.msg(result.msg, {icon:5});
                    }
                }
            });
        });
    }

    function openAddGrantDialog(data) {

        if (data.length == 0) {
            layer.msg("Please select role to grant permission！",{icon:5});
            return;
        }

        if (data.length > 1) {
            layer.msg("Batch Grant not allowed！",{icon:5});
            return;
        }

        var url = ctx + "/module/toAddGrantPage?roleId="+data[0].id;
        var title = "<h3>Role Management - Grant Permission</h3>";
        layui.layer.open({
            title:title,
            content:url,
            type:2,
            area:["600px","600px"],
            maxmin: true
        });

    }


});
