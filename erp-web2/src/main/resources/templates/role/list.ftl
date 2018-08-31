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
	var roleId;
		$(function(){
			$("#grid").datagrid({
				url: '${request.contextPath}/role/getData.do',
				columns: [[
					{field: 'uuid', title:'角色编号',  width:100},
					
					{field: 'name',title:'角色名称', width:100}
					
				]],
				onClickRow: function (rowIndex,rowData){
					$("#tree").tree({
						url: '${request.contextPath}/menu/getRoleMenus.do?roleuuid='+rowData.uuid,
						checkbox: true,
						animate: true
					});
					roleId = rowData.uuid;
				},
				singleSelect: true,
			});
		});
		function save(){
			if(roleId == null){
				$.messager.alert('提示', '请选择角色' );
				return ;
			}
			var nodes = $("#tree").tree('getChecked');
			var ids = '';
			for (var i = 0; i < nodes.length;i++){
				if(i>0){
					ids += ',';
					
				}
				ids += nodes[i].id;
				
			}
			$.post('${request.contextPath}/role/saveRoleMenu.do',{roleId,
				menuIds : ids } ,function(rt){
				$.messager.alert('使用', rt.messager);
				}, 'json');
		}
		
</script>
</head>
<body>
	<table id="grid"></table>
</body>
</html>
