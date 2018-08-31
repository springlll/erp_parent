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
<script type="text/javascript">
	
		$(function(){
			$("#grid").datagrid({
				url: '${request.contextPath}/role/getData.do',
				columns: [[
					{filed:'uuid', title:'角色编号', width:100},
					{filed:'name',title:'角色名称',width:100},
					
				]],
				singleSelect : true
			});
		})	;
</script>
</head>
<body>
	<table id="grid"></table>
</body>
</html>
