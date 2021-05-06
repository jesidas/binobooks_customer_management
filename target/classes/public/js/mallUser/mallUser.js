layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    var tableIns = table.render({
        id:'userTable'

        ,elem: '#userList'

        ,height: 'full-125'

        ,cellMinWidth:95

        ,url: ctx + '/mallUser/list'

        ,page: true

        ,limit:10

        ,limits:[10,20,30,40,50]

        ,toolbar:'#toolbarDemo'

        ,cols: [[

            {type:'checkbox', fixed:'center'}
            ,{field: 'id', title: 'id',  sort: true, fixed: 'left'}
            ,{field: 'name', title: 'name', align:'center'}
            ,{field: 'trueName', title: 'trueName', align:'center'}
            ,{field: 'email', title: 'email', align:'center'}
            ,{field: 'phone', title: 'phone', align:'center'}
            ,{field: 'createDate', title: 'createDate', align:'center'}
            ,{field: 'updateDate', title: 'updateDate', align:'center'}
            ,{title:'operation',templet:'#userListBar', fixed: 'right', align:'center', minWidth:150}
        ]]
    });


    $(".search_btn").click(function () {

        tableIns.reload({

            where: {

                name: $("[name='name']").val()
                ,email: $("[name='email']").val()
                ,phone:$("[name='phone']").val()
            }
            ,page: {
                curr: 1
            }
        });

    });

    table.on('toolbar(users)', function (data) {

        if (data.event == "add") {

            openAddOrUpdateUserDialog();

        } else if (data.event == "del") {

            var checkStatus = table.checkStatus(data.config.id);
            console.log(checkStatus);

            deleteUsers(checkStatus.data);
        }

    });


    function deleteUsers(userData) {

        if (userData.length == 0) {
            layer.msg("Please select the record to be deleted", {icon:5});
            return;
        }

        layer.confirm('Are you sure to cancel your account?',{icon:3, title:'User Management'}, function (index) {

            layer.close(index);

            var ids = "ids=";

            for(var i = 0; i < userData.length; i++) {
                if(i < userData.length -1) {
                    ids = ids + userData[i].id + "&ids="
                } else {
                    ids = ids + userData[i].id;
                }
            }

            $.ajax({
                type:"post",
                url:ctx + "/adminUser/delete",
                data:ids,
                success:function (result) {

                    if (result.code == 200) {

                        layer.msg("Deletion Success！",{icon:6});

                        tableIns.reload();
                    } else {

                        layer.msg(result.msg, {icon:5});
                    }
                }
            });
        });

    }


    table.on('tool(users)', function (data) {

        if (data.event == "edit") {

            openAddOrUpdateUserDialog(data.data.id);

        } else if (data.event == "del") {

            deleteUser(data.data.id);
        }

    });

    function deleteUser(id) {

        layer.confirm('Are you sure for deletion of that record？',{icon:3, title:"User Management"}, function (index) {

            layer.close(index);

            $.ajax({
                type:"post",
                url:ctx + "/mallUser/delete",
                data:{
                    ids:id
                },
                success:function (result) {

                    if (result.code == 200) {

                        layer.msg("Deletion success！",{icon:6});

                        tableIns.reload();
                    } else {

                        layer.msg(result.msg, {icon:5});
                    }
                }
            });
        });
    }

    function openAddOrUpdateUserDialog(id) {
        var title = "<h3>User Management - Add</h3>";
        var url = ctx + "/mallUser/toAddOrUpdateUserPage";

        if (id != null && id != '') {
            title = "<h3>User Management - Update</h3>";
            url+= "?id="+id;
        }

        layui.layer.open({

            type: 2,

            title: title,

            area: ['650px', '400px'],

            content: url,

            maxmin:true
        });
    }






});