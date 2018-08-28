<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>销售订单出库</title>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${request.contextPath}/easyui/themes/icon.css">
<script type="text/javascript" src="${request.contextPath}/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${request.contextPath}/easyui/datagrid-detailview.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/form.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/date.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/request.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/order.js"></script>
<script type="text/javascript">
	var basePath = '${request.contextPath}';
	var url = '${request.contextPath}/orders/getData.do?state=0&type=' + Request['type'];
	var oper;
	var isFilter = true; //true代表把不是“待出库”的订单明细过滤掉
</script>
</head>
<body>
	<table id="grid"></table>
	<div id="orderWindow" class="easyui-window" title="订单明细"
		style="width:300px;height:200px;" data-options="closed:true">
		<form id="orderForm">
			<input type="hidden" id="id" name="ordersdetailuuid"/>
			<table width="100%">
				<tr>
					<td>商品编号：</td><td id="goodsuuid"></td>
				</tr>
				<tr>
					<td>商品名称：</td><td id="goodsname"></td>
				</tr>
				<tr>
					<td>商品数量：</td><td id="num"></td>
				</tr>
				<tr>
					<td>选择仓库：</td><td>
						<input class="easyui-combobox" name="storeuuid"
					data-options="url:'${request.contextPath}/store/getComboData.do', valueField:'uuid', textField:'name'">
					</td> 
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" onclick="doOutStore()" value="出库"/>
					</td> 
				</tr>
			</table>
		</form>
	</div>
</body>
</html>