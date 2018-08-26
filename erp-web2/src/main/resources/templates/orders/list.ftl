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
<script type="text/javascript" src="${request.contextPath}/easyui/datagrid-detailview.js"></script>
<script type="text/javascript" src="${request.contextPath}/adminjs/index.js"></script>
<script  type="text/javascript" src="${request.contextPath}/js/order.js"></script>
<script type="text/javascript">
	var basePath = '${request.contextPath}';
	var url = '${request.contextPath}/orders/getData.do';
	var oper;
	var isFilter = false; //是否过已入库的商品，false代表不需要过滤

</script>

</head>
<body>

	<form id="searchForm">
		订单状态：<input name = "state" class="easyui-combobox" data-options="data:
		[
		{uuid:'',name:'全部'},
		{uuid:'0',name:'未审核'}, 
		{uuid:'1',name:'已审核'},
		{uuid:'2',name:'已确认'},
		{uuid:'3',name:'已入库'} ],
		valueField:'uuid',textField:'name'"
		/>
		
		<input type ="button" id="searchBtn" value="开始表演"/>
	</form>
	
	<table id="grid"></table>
</body>
</html>
