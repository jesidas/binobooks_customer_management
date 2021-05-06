<!DOCTYPE html>
<html>
<head>
	<title>User Order Display</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">
	<div class="layui-col-md12">
		<div class="layui-card">
			<div class="layui-card-body">
				<form class="layui-form" >
					<input name="id" type="hidden" value="${(mallUser.id)!}"/>
<#--					<div class="layui-form-item layui-row">
						<div class="layui-col-xs6">
							<label class="layui-form-label">MallUser Name</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="name" id="name" type="hidden"  value="${(mallUser.name)!}" readonly="readonly">
							</div>
						</div>
						<div class="layui-col-xs6">
							<label class="layui-form-label">MallUser Email</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="email" id="email" type="hidden" value="${(mallUser.email)!}" readonly="readonly">
							</div>
						</div>
					</div>-->

<#--					<div class="layui-form-item layui-row">
						<div class="layui-col-xs6">
							<label class="layui-form-label">MallUser Phone</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									    name="phone" value="${(mallUser.phone)!}" type="hidden" id="phone" readonly="readonly">
							</div>
						</div>
					</div>-->
				</form>
			</div>
		</div>
	</div>

	<div class="layui-col-md12">
		<table id="mallUserOrderList" class="layui-table"  lay-filter="mallUserOrders"></table>
	</div>



	<script id="customerOrderListBar" type="text/html">
		<a class="layui-btn layui-btn-xs"  lay-event="info">OrderDetails</a>
	</script>
	<script type="text/javascript" src="${ctx}/js/mallUser/mallUser.order.js"></script>
</body>
</html>