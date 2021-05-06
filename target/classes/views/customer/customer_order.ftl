<!DOCTYPE html>
<html>
<head>
	<title>User Order Display</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">

	<div class="layui-col-md12">
		<table id="customerOrderList" class="layui-table"  lay-filter="customerOrders"></table>
	</div>


	<script id="customerOrderListBar" type="text/html">
		<a class="layui-btn layui-btn-xs"  lay-event="info">Order Details</a>
	</script>
	<script type="text/javascript" src="${ctx}/js/customer/customer.order.js"></script>
</body>
</html>