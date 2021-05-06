layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    /**
     * reload data to table
     */
    var tableIns = table.render({
        id:'userInfo'
        //The ID attribute value of the container element
        ,elem: '#userInfo'
        // Full difference of container height
        ,height: 'full-125'
        // Minimum cell width
        ,cellMinWidth:95
        // URL to access data (data interface in the background)
        ,url: ctx + '/adminUser/basicInfo/'
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
            // {type:'checkbox', fixed:'center'}
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
     * Listen inline Tool Bar
     */
    table.on('tool(users)', function (data) {

        if (data.event == "edit") {

            openEditBasicInfoDialog(data.data.id);

        } else if (data.event == "del") {

            deleteUser(data.data.id);
        }

    });


    function deleteUser(id) {
        layer.confirm('ARe you sure for cancel your account？',{icon:3, title:"User Management"}, function (index) {
            layer.close(index);
            var url2 = ctx + "/adminUser/logout";
            $.ajax({
                type:"post",
                url:ctx + "/adminUser/deleteSelf",
                data:{
                    ids:id
                },
                success:function (result) {
                    if (result.code == 200) {
                        layer.msg("Cancellation Account Success！",{icon:6});
                        // tableIns.reload();
                        parent.localStorage.clear();
                        parent.location.replace(url2)
                        $.removeCookie("userIdStr",{domain:"localhost",path:"/bnb"});
                    } else {
                        layer.msg("Successfully Cancelled！",{icon:6});// layer.msg(result.msg, {icon:5});
                        parent.localStorage.clear();
                        parent.location.replace(url2);
                    }
                    parent.$.removeCookie("userIdStr",{domain:"localhost",path:"/bnb"});

                }
            });
        });

    }

    function openEditBasicInfoDialog(id) {
        var title = "<h3>User Management - Addition</h3>";
        var url = ctx + "/adminUser/toEditBasicInfoPage";

        if (id != null && id != '') {
            title = "<h3>User Management - Upodate User</h3>";
            url+= "?id="+id; // pass primary key and search for values
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