layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    var  tableIns = table.render({
        elem: '#mallUserOrderList',
        url : ctx+"/mallUser/mallUserOrderlist/",
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "mallUserOrderList",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "order_id", title:'order_id',fixed:"true"},
            {field: 'customer_id', title: 'customer_id',align:"center"},
            {field: 'order_date', title: 'order_date',align:"center"},
            {field: 'sub_total', title: 'sub_total',align:"center"},
            {title: 'Order Detail',fixed:"right",align:"center", minWidth:150,templet:"#customerOrderListBar"}
        ]]
    });


    table.on('tool(mallUserOrders)',function (data) {


        if (data.event == "info") { // OrderDetails
            openOrderItemsDialog(data.data.id);
        }
    });

    function openOrderItemsDialog(id) {
        var title = "<h3>MallUser Management - View Order Items</h3>";
        var url = ctx + "/mallUser/toOrderItemsPage";

        if (id != null && id != '') {
            title = "<h3>MallUser Management - Update User</h3>";
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
