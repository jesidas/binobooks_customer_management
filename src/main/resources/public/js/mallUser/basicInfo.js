layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    var tableIns = table.render({
        id:'userInfo'

        ,elem: '#userInfo'

        ,height: 'full-125'

        ,cellMinWidth:95

        ,url: ctx + '/mallUser/basicInfo/'

        ,page: true

        ,limit:10

        ,limits:[10,20,30,40,50]

        ,toolbar:'#toolbarDemo'

        ,cols: [[

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


    table.on('tool(users)', function (data) {

        if (data.event == "edit") { //
            openEditBasicInfoDialog(data.data.id);
        } else if (data.event == "del") {
            deleteUser(data.data.id);
        }

    });

    function deleteUser(id) {
        layer.confirm('Are you sure for cancel your account?',{icon:3, title:"User Management"}, function (index) {

            layer.close(index);
            var url2 = ctx + "/mallUser/logout";

            $.ajax({
                type:"post",
                url:ctx + "/mallUser/deleteSelf",
                data:{
                    ids:id
                },
                success:function (result) {
                    if (result.code == 200) {
                        layer.msg("Cancellation Successful！",{icon:6});
                        // tableIns.reload();
                        parent.localStorage.clear();
                        parent.location.replace(url2)
                    } else {
                        layer.msg("User is deleted！",{icon:6});//
                        parent.localStorage.clear();
                        parent.location.replace(url2);
                    }
                    parent.$.removeCookie("userIdStr",{domain:"localhost",path:"/bnb"});

                }
            });
        });

    }



    function openEditBasicInfoDialog(id) {
        var title = "<h3>User Management - Update User</h3>";
        var url = ctx + "/mallUser/toEditBasicInfoPage";

        if (id != null && id != '') {
            title = "<h3>User management - Update User</h3>";
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