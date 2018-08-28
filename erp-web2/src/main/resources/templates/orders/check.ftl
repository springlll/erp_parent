<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单审核</title>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${request.contextPath}/easyui/themes/icon.css">
<script type="text/javascript" src="${request.contextPath}/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${request.contextPath}/easyui/datagrid-detailview.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/form.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/date.js"></script>
<script  type="text/javascript" src="${request.contextPath}/js/order.js"></script>
<script type="text/javascript">
	var basePath = '${request.contextPath}';
	var url = '${request.contextPath}/orders/getData.do?state=0';
	var oper = {field: '-', title: '操作', formatter: function(value, row, rowIndex) {
		return "<a href='javascript:doCheck(" + row.uuid + ")'>审核</a>"
	}};
	
	var isFilter = false; //是否过已入库的商品，false代表不需要过滤
</script>
</head>
<body>
	<table id="grid"></table>
</body>
</html>
