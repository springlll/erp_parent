<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>采购申请</title>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${request.contextPath}/easyui/themes/icon.css">
<script type="text/javascript" src="${request.contextPath}/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/request.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/form.js"></script>

<script type="text/javascript">

	var lastRowIndex;

$(function() {
	if(Request['type'] == 1){
	document.title='采购订单录入';
		
	}
	if(Request['type' == 2]){
	document.title = '销售订单录入';
	}
		//供应商下拉框
		$("#supplier").combogrid({
			url: '${request.contextPath}/supplier/getComboData.do', //后台返回json数组对象
			idField: 'uuid', //<input name="uuid" value="name"/>
			textField: 'name',
			panelWidth: 700, //设置下拉表格的宽度
			columns: [[
			   {field: 'uuid', title: 'ID', width: 100},
			   {field: 'name', title: '名称', width: 100},
			   {field: 'address', title: '地址', width: 100},
			   {field: 'contact', title: '联系人', width: 100},
			   {field: 'tele', title: '电话', width: 100}
			]],
			mode:'remote'
		});	
	$("#grid").datagrid({
		columns: [[
			{field: 'goodsuuid', title: '商品编号', width: 100, editor: {
					type: 'numberbox',
					options: {disabled: true}
				}},
			{field: 'goodsname', title: '商品名称', width: 100, editor: {
					type: 'combobox',
					options: {url: '${request.contextPath}/goods/getComboData.do', //请求的URL地址，后台返回一个json数组
						valueField: 'name', textField: 'name', //valueField代表option标签的value属性，textField代表option标签的文本内容
						onSelect: function(record) {
							//得到编号的编辑器
							var uuidEditor = $("#grid").datagrid('getEditor', {index: lastRowIndex, field: 'goodsuuid'});
							//设置编号编辑器的值
							$(uuidEditor.target).val(record.uuid);
							//得到price的编辑器
							var priceEditor = $("#grid").datagrid('getEditor', {index: lastRowIndex, field: 'price'});
							//设置price编辑器的值
							$(priceEditor.target).val(record.inprice);
							//重新计算总金额
							cal();
							//计算总金额
							sum();
						}
					}
				}},
			{field: 'price', title: '价格', width: 100, editor: {
					type: 'numberbox',
					options: {precision: 2, disabled: true}
				}},
			{field: 'num', title: '数量', width: 100,editor: 'numberbox'},
			{field: 'money', title: '金额', width: 100, editor: {
					type: 'numberbox',
					options: {precision: 2, disabled: true}
				}}

			
		]],
	
		singleSelect: true,
		toolbar: [{
			iconCls: 'icon-add',
			text: '添加',
			handler: function() {
				//加一行
				$("#grid").datagrid('appendRow', {num: '0', money: '0'});
				//关闭行的编辑状态
				$("#grid").datagrid('endEdit', lastRowIndex);
				//设置指定行的可编辑状态，0代表第一行
				lastRowIndex = $("#grid").datagrid('getRows').length - 1;
				$("#grid").datagrid('beginEdit', lastRowIndex);
				//绑定价格、数量的keyup事件
				bindGridEvent();
				}
			},
			{
			iconCls:'icon-remove',
			text:'删掉',
			handler:function(){
			
				var selectedRow = $('#grid').datagrid('getSelected');
				if(selectedRow == null){
				deleteRow(lastRowIndex);
				} else{
				var index = $('#grid').datagrid('getRowIndex',selectedRow);
				deleteRow(index);
				}
				lastRowIndex = $("#grid").datagrid('getRows').length - 1;
			}
		}],
	onClickRow: function(rowIndex, rowData) { //单击行触发的事件，rowIndex代表点击行的索引，rowData代表行对象
				//关闭上一次编辑行
				$("#grid").datagrid('endEdit', 0);
				//得到当前点击的行索引
				lastRowIndex = rowIndex;
				//设置可编辑状态
				$('#grid').datagrid('beginEdit', lastRowIndex);
				//绑定价格、数量的keyup事件
				bindGridEvent();
			}
	});	
	});

		//计算金额
	function cal() {
		//得到数量编辑框
		var numEditor = $("#grid").datagrid('getEditor', {index: lastRowIndex, field: 'num'});
		//得到编辑框的数据
		var num = $(numEditor.target).val();
		//得到价格编辑框
		var priceEditor = $("#grid").datagrid('getEditor', {index: lastRowIndex, field: 'price'});
		//得到价格编辑框的数据
		var price = $(priceEditor.target).val();
		//计算金额
		var money = price * num; 
		//得到金额的编辑器
		var moneyEditor = $("#grid").datagrid('getEditor', {index: lastRowIndex, field: 'money'});
		//设置金额编辑器的值
		$(moneyEditor.target).val(money.toFixed(2));
		//重新把金额设置到金额列中
		$("#grid").datagrid('getRows')[lastRowIndex].money = money.toFixed(2);
	}
	
	//绑定事件
	function bindGridEvent() {
		//获取数量编辑框
		var numEditor = $("#grid").datagrid('getEditor', {index: lastRowIndex, field: 'num'});
		//绑定keyup事件
		$(numEditor.target).bind('keyup', function() {
			cal();
			sum();
		});

		//获取价格编辑框
		var priceEditor = $("#grid").datagrid('getEditor', {index: lastRowIndex, field: 'price'});
		//绑定keyup事件
		$(priceEditor.target).bind('keyup', function() {
			cal();
		});
	}	
	//删除行
	function deleteRow(index) {
		//结束编辑
		$("#grid").datagrid('endEdit', lastRowIndex);
		//删除一行
		$("#grid").datagrid('deleteRow', index);
	}	
	//计算总金额
	function sum() {
		var rows = $("#grid").datagrid('getRows');
		var totalMoney = 0;
		for (var i = 0; i < rows.length; i++) {
			totalMoney += parseFloat(rows[i].money);
		}
		//alert(totalMoney);
		$("#sum").html(totalMoney);
		$("#totalmoney").val(totalMoney);
	}	
	//保存按钮
	function saveBtn() {
		$('#grid').datagrid('endEdit', lastRowIndex);
		//提取表单数据
		var data = getFormData('orderForm');
		//提取表格数据
		var rows = $('#grid').datagrid('getRows');
		//向data追加属性json
		data['json'] = JSON.stringify(rows);
		//alert(JSON.stringify(data));
		//异步提交表单
		$.post('${request.contextPath}/orders/addOrder.do?type='+Request['type'],data function(rt) {
			$.messager.alert('提示', rt.message);
		}, 'json');

}

</script>
</head>
<body>
	<form id="orderForm">
	供应商： <input id ="supplier" name="uuid"/></br>
	<input type="hidden" id="totalmoney" name="totalmoney"/>
	</form>
	<table id="grid"></table><br/>
	<input type="button" onclick="saveBtn()" value="马上申请" style="background-color:#CCC" />
	合计：<span id="sum">0</span></br>
</body>
</html>
