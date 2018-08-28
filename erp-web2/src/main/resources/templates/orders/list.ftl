<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单查询</title>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${request.contextPath}/easyui/themes/icon.css">
<script type="text/javascript" src="${request.contextPath}/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/form.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/date.js"></script>
<script type="text/javascript" src="${request.contextPath}/easyui/datagrid-detailview.js"></script>
<script type="text/javascript" src="${request.contextPath}/adminjs/index.js"></script>
<script  type="text/javascript" src="${request.contextPath}/js/request.js"></script>

<script  type="text/javascript" src="${request.contextPath}/js/order.js"></script>
<script type="text/javascript">
	var basePath = '${request.contextPath}';
	var url = '${request.contextPath}/orders/getData.do?type='+Request['type'];
	var oper;
	var isFilter = false; //是否过已入库的商品，false代表不需要过滤
	$(function(){
		$('#state').combobox({
		valueField:'uuid',
		textField:'name',
		editable:false
		});
		
		var data =[];
		if(Request['type'] == 1){
			data.push({"uuid":"","name":"全部" });
			data.push({"uuid":"0","name":"未审核" });
			data.push({"uuid":"1","name":"已审核" });
			data.push({"uuid":"2","name":"已确认" });
			data.push({"uuid":"3","name":"已入库" });
		}
			if(Request['type'] == 2){
			data.push({"uuid":"","name":"全部" });
			data.push({"uuid":"0","name":"未入库" });
			data.push({"uuid":"1","name":"已入库" });

		}
		$("#state").combobox("loadData",data);
	});

</script>

</head>
<body>	

	<form id="searchForm">
	
		订单状态：	<input id="state" name="state"/>
		<input type ="button" id="searchBtn" value="开始表演"/>
	</form><br/>
	
	<table id="grid"></table>
</body>
</html>
