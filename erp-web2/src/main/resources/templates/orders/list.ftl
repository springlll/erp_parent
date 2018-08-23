<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${request.contextPath}/easyui/themes/icon.css">
<script type="text/javascript" src="${request.contextPath}/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/form.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/date.js"></script>
<script type="text/javascript">
	var stats =  new Array();
	stats[0] = '未审核';
	stats[1] = '已审核';
	stats[2] = '已确认';
	stats[3] = '已入库';
	$(function() {
	
	$("#grid").datagrid({
		title: '订单列表', //设置表格的标题
		url: '${request.contextPath}/orders/getData.do', //获取数据的URL地址
		columns: [[
			{field: 'uuid', title: '订单编号', width: 80},
			{field: 'createtime', title: '生成日期', width: 80},
			{field: 'checktime', title: '检查日期', width: 80},
			{field: 'starttime', title: '开始日期', width: 80},
			{field: 'endtime', title: '结束日期', width: 80},
			{field: 'type', title: '订单类型', width: 80},
			{field: 'creater', title: '下单员', width: 80},
			{field: 'checker', title: '审查员', width: 80},
			{field: 'starter', title: '采购员', width: 80},
			{field: 'ender', title: '库管员', width: 80},
			{field: 'supplieruuid', title: '供应商', width: 80},
			{field: 'totalMoney', title: '总金额', width: 80},
			{field: 'state', title: '订单状态'}
		]],
		singleSelect: true,
		pagination: true, //显示分页栏
		pageSize: '5', //每页的记录数
		pageList: [5, 10, 15, 20]
	});
	
});
	
</script>
</head>
<body>
	<table id="grid"></table>
</body>
</html>