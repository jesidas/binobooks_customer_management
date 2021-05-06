layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    var  tableIns = table.render({
        elem: '#customerOrderList',
        url : ctx+"/customer/listAllCustomerOrders",
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "customerOrderList",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "order_id", title:'order_id',fixed:"true"},
            {field: 'customer_id', title: 'customer_id',align:"center"},
            {field: 'order_date', title: 'order_date',align:"center"},
            {field: 'sub_total', title: 'sub_total',align:"center"},
            {title: 'Operation',fixed:"right",align:"center", minWidth:150,templet:"#customerOrderListBar"}
        ]]
    });

    table.on('tool(customerOrders)',function (data) {
        if (data.event == "info") {
            var title = "<h3>User Management - Order Details</h3>";
            var url = ctx + "/customer/toOrderDetailPage?orderId=" + data.configs.id;

            layui.layer.open({

                type: 2,

                title: title,

                area: ['700px', '400px'],

                content: url,

                maxmin:true
            });
        }
    });



});
