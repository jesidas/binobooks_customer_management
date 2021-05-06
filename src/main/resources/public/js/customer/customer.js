layui.use(['table','layer',"form"],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
        form = layui.form;

    var  tableIns = table.render({
        elem: '#customerList',
        url : ctx+'/customer/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "customerTable",
        cols: [[

            {type:'checkbox', fixed:'center'}
            ,{field: 'id', title: 'id',  sort: true, fixed: 'left'}
            ,{field: 'name', title: 'name', align:'center'}
            ,{field: 'trueName', title: 'trueName', align:'center'}
            ,{field: 'email', title: 'email', align:'center'}
            ,{field: 'phone', title: 'phone', align:'center'}
            ,{field: 'createDate', title: 'createDate', align:'center'}
            ,{field: 'updateDate', title: 'updateDate', align:'center'}
            ,{title:'operation',templet:'#customerListBar', fixed: 'right', align:'center', minWidth:150}
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


    table.on('toolbar(customers)', function (data) {

        if (data.event == "add") { // Add

            openAddOrUpdateCustomerDialog();

        } else if (data.event == "del") {

            var checkStatus = table.checkStatus(data.config.id);
            console.log(checkStatus);

            deleteCustomers(checkStatus.data);

        }

    });



    table.on('tool(customers)', function (data) {

        if (data.event == "edit") {

            openAddOrUpdateCustomerDialog(data.data.id);

        } else if (data.event == "del") {

            deleteCustomer(data.data.id);
        }

    });



    function openAddOrUpdateCustomerDialog(id) {
        var title = "<h3>Customer Management - Add Customer Info</h3>";
        var url = ctx + "/customer/toAddOrUpdateCustomerPage";

        if (id != null && id != '') {
            title = "<h3>Customer Management - Update Customer Info</h3>";
            url = ctx + "/customer/toAddOrUpdateCustomerPage?id="+id;
        }

        layui.layer.open({

            type: 2,

            title: title,

            area: ['700px', '500px'],

            content: url,

            maxmin:true
        });
    }

    function deleteCustomer(ids) {
        layer.confirm('Are you sure for deletion?',{icon:3, title:"Customer Management"}, function (index) {
            layer.close(index);

            $.ajax({
                type:"post",
                url:ctx + "/customer/delete",
                data:{
                    ids:ids
                },
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

    function deleteCustomers(userData) {

        if (userData.length == 0) {
            layer.msg("Please select the multiple records to be deleted！", {icon:5});
            return;
        }

        layer.confirm('ARe you sure for deletion？',{icon:3, title:'Customer Management'}, function (index) {

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
                url:ctx + "/customer/delete",
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

    function openCustomerOrderDialog(data) {
        if (data.length == 0) {
            layer.msg("Please Select the customer that you want to view the ids！",{icon:5});
            return;
        }
        if (data.length > 1) {
            layer.msg("Batch look up is not supported！",{icon:5});
            return;
        }

        layui.layer.open({
            type: 2,
            title: "<h3>Customer management - View Orders</h3>",
            area: ['700px', '500px'],
            content: ctx + "/customer/toCustomerOrderPage?customerId=" + data[0].id,
            maxmin:true
        });

    }


});
