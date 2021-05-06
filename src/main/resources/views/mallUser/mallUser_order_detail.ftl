<!DOCTYPE html>
<html>
<head>
    <title>View Order Detail</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<div class="layui-col-md12">
    <div class="layui-card">
        <div class="layui-card-body">
            <form class="layui-form" >
                <input name="id" type="hidden" value="${(order.id)!}"/>
                <div class="layui-form-item layui-row">
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">Order Id</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input"
                                   name="order_id" id="orderNo" type="hidden"  value="${(orderitems.order_id)!}" readonly="readonly">
                        </div>
                    </div>
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">Book Id</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input"
                                   name="book_id" id="total" type="hidden" value="${(orderitems.book_id)!}" readonly="readonly">
                        </div>
                    </div>
                </div>

                <div class="layui-form-item layui-row">
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">Number Ordered</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input"
                                   name="number_ordered" type="hidden" value="${(orderitems.number_ordered)!}" readonly="readonly">
                        </div>
                    </div>
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">Price</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input"
                                    name="price" type="hidden" value="${(orderitems.price)!}"  readonly="readonly">
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="layui-col-md12">
    <table id="OrderItemsList" class="layui-table"  lay-filter="orderDetails"></table>
</div>


<script type="text/javascript" src="${ctx}/js/mallUser/mallUser.order.details.js"></script>
</body>
</html>