<!DOCTYPE html>
<html>
<head>
	<title>Customers List</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
	<blockquote class="layui-elem-quote quoteBox">
		<form class="layui-form">
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" name="name"
						   class="layui-input
					searchVal" placeholder="User Name" />
				</div>
				<div class="layui-input-inline">
					<input type="text" name="email" class="layui-input
					searchVal" placeholder="User Email" />
				</div>
				<div class="layui-input-inline">
					<input type="text" name="phone" class="layui-input
					searchVal" placeholder="User Phone" />
				</div>
				<a class="layui-btn search_btn" data-type="reload"><i
							class="layui-icon">&#xe615;</i> Search</a>
			</div>
		</form>
	</blockquote>

	<table id="customerList" class="layui-table"  lay-filter="customers"></table>


	<script type="text/html" id="toolbarDemo">
		<div class="layui-btn-container">
			<a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
				<i class="layui-icon">&#xe608;</i>
				Add Customer
			</a>
			<a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
				<i class="layui-icon">&#xe608;</i>
				Delete User
			</a>
		</div>
	</script>

	<script id="customerListBar" type="text/html">
		<a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">Edit</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">Delete</a>
	</script>


</form>
<script type="text/javascript" src="${ctx}/js/customer/customer.js"></script>

</body>
</html>