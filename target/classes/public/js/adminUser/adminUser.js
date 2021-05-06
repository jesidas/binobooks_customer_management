layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    /**
     * reload data to table
     */
    var tableIns = table.render({
        id:'userTable'
        //The ID attribute value of the container element
        ,elem: '#userList'
        // Full difference of container height
        ,height: 'full-125'
        // Minimum cell width
        ,cellMinWidth:95
        // URL to access data (data interface in the background)
        ,url: ctx + '/adminUser/list'
        // page pagination
        ,page: true
        //Default number per page
        ,limit:10
        //  Optional number of pages per page
        ,limits:[10,20,30,40,50]
        // Head Toolbar
        ,toolbar:'#toolbarDemo'
        // Table Heads
        ,cols: [[
            // field: The field property value is required to be consistent with the corresponding property field name in the returned data
            // title：column title
            // sort： Is sorting allowed （默认：false）
            // fixed: Fixed column
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


    /**
     * click button event
     */
    $(".search_btn").click(function () {

        /**
         * reload table
         *  multicondition Query
         */
        tableIns.reload({
            // params to backend
            where: { //Set additional parameters of asynchronous data interface, and set them arbitrarily
                // set params via textbox
                name: $("[name='name']").val() // name
                ,email: $("[name='email']").val() // email
                ,phone:$("[name='phone']").val() // phone
            }
            ,page: {
                curr: 1 // restart from page 1
            }
        });

    });

    /**
     * Head event listener
     */
    table.on('toolbar(users)', function (data) {

        if (data.event == "add") { // Add

            openAddOrUpdateUserDialog();

        } else if (data.event == "del") {

            var checkStatus = table.checkStatus(data.config.id);
            console.log(checkStatus);

            deleteUsers(checkStatus.data);
        }

    });


    function deleteUsers(userData) {

        if (userData.length == 0) {
            layer.msg("Please select the multiple records to be deleted！", {icon:5});
            return;
        }

        layer.confirm('ARe you sure for deletion？',{icon:3, title:'User Management'}, function (index) {

            layer.close(index);
            // The params to be transferred is an Array   ids=1&ids=2&ids=3
            var ids = "ids=";
            for(var i = 0; i < userData.length; i++) {
                if(i < userData.length -1) {
                    ids = ids + userData[i].id + "&ids="
                } else {
                    ids = ids + userData[i].id;
                }
            }
            console.log(ids);

            $.ajax({
                type:"post",
                url:ctx + "/adminUser/delete",
                data:ids, // The params to be transferred is an Array   ids=1&ids=2&ids=3
                success:function (result) {

                    if (result.code == 200) {

                        layer.msg("Delete Success！",{icon:6});

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

        layer.confirm('ARe you sure for deletion？',{icon:3, title:"User Management"}, function (index) {

            layer.close(index);

            $.ajax({
                type:"post",
                url:ctx + "/adminUser/delete",
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
        var title = "<h3>User Management - Addition</h3>";
        var url = ctx + "/adminUser/toAddOrUpdateUserPage";

        if (id != null && id != '') {
            title = "<h3>User Management - Upodate User</h3>";
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