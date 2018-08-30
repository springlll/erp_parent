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
<script>
$(function() {
	
	$("#grid").datagrid({
		url: '${request.contextPath}/storealert/getData.do', 
		columns: [[
	       {field:'uuid', title:'商品编号', width:100}, 
	       {field:'name', title:'商品名称', width:100}, 
	       {field:'storenum', title:'库存数量', width:100}, 
	       {field:'outnum', title:'待发货数量', width:100} 
		]],
		singleSelect: true
	});
	
});
</script>
</head>
<body>
	<table id="grid"></table>
</body>
</html>
