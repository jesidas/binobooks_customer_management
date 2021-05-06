layui.use(['table','layer',"form"],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    var  tableIns = table.render({
        elem: '#orderDetailsList',
        url : ctx+'/customer/toOrderDetailPage?orderId='+$("input[name='id']").val(),
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "orderDetailsList",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "order_id", title:'order_id',fixed:"true"},
            {field: 'book_id', title: 'book_id',align:"center"},
            {field: 'number_ordered', title: 'number_ordered',align:"center"},
            {field: 'price', title: 'price($)',align:"center"},
       ]]
    });
});
