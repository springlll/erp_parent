
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
	var requestUrl;
		
	$(function(){ //页面加载完后执行function函数
		//初始化easyui表格
		$("#grid").datagrid({ 
			url: '${request.contextPath}/dep/getData.do', //请求路径
			columns: [[ //指定表格的列
				{field: 'uuid', title: '部门编号', width:100},
				{field: 'name', title: '部门名称', width:100},
				{field: 'tele', title: '部门电话', width:100}
			]],
			toolbar: [{ //定义一个工具栏
				iconCls: 'icon-add',
				text: '添加',
				handler: function() {
					//打开对话框
					$("#editWindow").window('open');
					//设置请求路径
					requestUrl = "${request.contextPath}/dep/add.do";
				}
			}, { 
				iconCls: 'icon-edit',
				text: '修改',
				handler: function() {
					//获取选中的行
					var row = $('#grid').datagrid('getSelected');
					if (row == null) {
						$.messager.alert('提示', '请选择要修改的部门！');
					} else {
						//弹出对话框
						$("#editWindow").window('open');
						//清空表单
						$("#editForm").form('clear');
						//加载表单数据
						$("#editForm").form('load', '${request.contextPath}/dep/get.do?uuid=' + row.uuid);
						//设置请求路径
						requestUrl = "${request.contextPath}/dep/update.do";
					}
				}
			}, { 
				iconCls: 'icon-remove',
				text: '删除',
				handler: function() {
					var row = $('#grid').datagrid('getSelected'); //获取选中的行
					if (row == null) {
						$.messager.alert('提示', '请选择要删除的部门！');
					} else {
						$.post('${request.contextPath}/dep/del.do', {uuid: row.uuid}, function(rt) {
							if (rt.status) {
								//刷新表格数据
								$("#grid").datagrid('reload');
							}
							$.messager.alert('提示', rt.message);
						}, 'json');
					}
				}
			}],
			singleSelect: true, //是否单选
			pagination: true, //显示分页栏
			pageSize: '10', //每页显示记录数
			pageList: [5, 10, 15, 20] //选择每页显示记录数
		});
		
		//初始化对话框
		$('#editWindow').dialog({
			title:'编辑部门', //对话框的标题
			width:290, //宽度
		    height:150, //高度
			closed:true, //是否显示关闭按钮
			cache:false, //是否启用缓存
			modal:true, //是否添加背影
			buttons:[{ //定义按钮
			      text:'保存', 
			      handler: function(){
			      	  //验证表单，如果isValid为true，就代表验证成功，可以提交表单
			      	  //否则，验证失败，提示用户。
			      	  var isValid = $('#editWindow').form('validate');
			      	  if (!isValid) {
			      	  	  $.messager.alert('提示', '录入信息有误！');
			      	  } else {
			      	  	  //提交表单数据(异步请求)
				    	  var data = getFormData('editForm'); //获取表单参数，并封装到json对象中
						  $.post(requestUrl, data, function(rt) {
								if (rt.status) {
									//关闭对话框
									$("#editWindow").window('close');
									//刷新表格的数据
									$("#grid").datagrid('reload');
									//清空表单
									$('#editWindow').form('clear');
								} 
								//显示提示框
								$.messager.alert('提示', rt.message);
						  }, 'json');
			      	  }
			   	  }
			},{
			      text:'取消', 
			      handler:function(){
			    	 //清空表单
					 $('#editWindow').form('clear');
					 //关闭对话框
					 $("#editWindow").window('close');
			      }
			}]
		});
		
	});
	
	//查询
	function search() {
		//获取表单参数
		var data = getFormData("searchForm"); //获取表单数据，并返回json对象
		//让表格重新加载数据
		$("#grid").datagrid('load', data);
	}
</script>
</head>
<body>
	<!-- 构造查询表单 -->	
	<form id="searchForm">
		部门名称：<input name="name"/>
		部门电话：<input name="tele"/>
		<input type="button" value="搜索" onclick="search()"/>	
	</form><br/>
	<table id="grid"></table>
	<!-- 添加或修改部门的对话框 -->
	<div id="editWindow">
		<form id="editForm">
			<input type="hidden" name="uuid"/>
			<table style="margin:10px;">
				<tr>
					<td>部门名称：</td><td><input name="name" class="easyui-validatebox" 
						data-options="prompt:'请输入部门名称', required:true"/></td>
				</tr>
				<tr>
					<td>部门电话：</td><td><input name="tele"/></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>