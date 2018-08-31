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
	var empId;

	$(function(){ //页面加载完后执行function函数
		//初始化easyui表格
		$("#grid").datagrid({ 
			url: '${request.contextPath}/emp/getData.do', //请求路径
			columns: [[ //指定表格的列
				{field: 'uuid', title: '员工编号', width:80},
				{field: 'name', title: '姓名', width:80},
				{field: 'gender', title: '性别', width:80},
				{field: 'email', title: '邮箱', width:120},
				{field: 'tele', title: '电话', width:100},
				{field: 'address', title: '联系地址', width:200}
			]],
			onClickRow: function(rowIndex, rowData) {
				//初始化菜单树
				$("#tree").tree({
					url: '${request.contextPath}/emp/tree.do?empuuid=' + rowData.uuid, //请求的URL
					checkbox: true, //添加复选框 
					animate: true //动画效果
				});
				empId = rowData.uuid;
			},
			singleSelect: true, //是否单选
		});
	});
	
	//保存员工角色
	function save() {
		//把角色id以及选中菜单id发送给后台
		if (empId == null) {
			$.messager.alert('提示', '请选择员工！');	
			return;
		}
		//得到选中的节点集合
		var nodes = $("#tree").tree('getChecked');
		var ids = '';
		for (var i = 0; i < nodes.length; i++) {
		if (i > 0) {
				ids += ',';
			}
			ids += nodes[i].id;
		}
		//alert(ids);
		//提交异步请求执行保存
		$.post('${request.contextPath}/emp/saveEmpRole.do'
			, {empId: empId, roleIds: ids}, function(rt) {
			$.messager.alert('提示', rt.message);
		}, 'json');
		
	}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west', title:'选择员工', split:true" style="width:700px;">
		<table id="grid"></table>
	</div>
	<div data-options="region:'center', title:'角色设置'" style="padding:5px;background:#eee;">
		<ul id="tree"></ul>
		<input type="button" value="保存" onclick="save()"/>
	</div>
</body>
</html>