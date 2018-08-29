<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>销售统计报表</title>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${request.contextPath}/easyui/themes/icon.css">
<script type="text/javascript" src="${request.contextPath}/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/form.js"></script>
<script>
	$(function() {
	
		$("#grid").datagrid({
			url: '${request.contextPath}/orders/report.do',
			columns: [[
				{field: 'name', title: '商品类型', width: 200},
				{field: 'money', title: '销售额', width: 200}
			]],
			singleSelect: true
		});
		
		$("#countBtn").bind ('click',function(){
		var data = getFormData('searchForm');
		$('#grid').datagrid('load',data);
		});
	});
</script>


</head>
<body>
	<form id="searchForm">
	
		开始日期：<input name="startDate" class="easyui-datebox"/>
		开始日期：<input name="endDate" class="easyui-datebox"/>	
		<input type="button" id = "countBtn" value ="统计"/>
	</form>
	<table id="grid"></table>
</body>
</html>
