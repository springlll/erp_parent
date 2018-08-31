<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>角色菜单设置</title>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${request.contextPath}/easyui/themes/icon.css">
<script type="text/javascript" src="${request.contextPath}/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${request.contextPath}/easyui/jquery.min.js"></script>

<script type="text/javascript" src="${request.contextPath}/js/request.js"></script>
<script>
	$(function(){
		$("#tree").tree({
			url: '${request.contextPath}/menu/getDate.do?roleuuid' +Request['roleuuid'],
			checkbox:true,
			animate:true
		});
	});
	
	
</script>	
</head>
<body>
	<ul id="tree"></ul>
</body>
</html>
