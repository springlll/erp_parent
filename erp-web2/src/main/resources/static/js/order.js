var supplierName;

var stats =  new Array();
if(Request['type' == 1]){
	stats[0] = '未审核';
	stats[1] = '已审核';
	stats[2] = '已确认';
	stats[3] = '已入库';
	supplierName ='供应商';
}
	if(Request['type'] == 2){
			stats[0]='未出库';
			stats[3]='已出库';
			supplierName='客户';
	}
$(function(){
	//绑定按钮
	$("#searchBtn").bind('click',function(){
		var data = getFormData("searchForm");
		if (data['state'] == '') {
			delete data.state; //删除对象属性
		}
	$("#grid").datagrid('reload', data);
	});
});
var g_index;   //主表格的行索引
var g_index2;  //子表格的行索引
	$(function() {
	
	$("#grid").datagrid({
		title: '订单列表', //设置表格的标题
		url: url, //获取数据的URL地址
		columns: [[
			{field: 'uuid', title: '订单编号', width: 80},
			{field: 'createtime', title: '生成日期', width: 80, formatter: function(value) {
				return to_date(value);
			}},
			{field: 'checktime', title: '检查日期', width: 80, formatter: function(value) {
				return to_date(value);
			}},
			{field: 'starttime', title: '开始日期', width: 80, formatter: function(value) {
				return to_date(value);
			}},
			{field: 'endtime', title: '结束日期', width: 80, formatter: function(value) {
				return to_date(value);
			}},
			{field: 'type', title: '订单类型', width: 80, formatter: function(value)
			{return value==1 ? '采购订单':(value==2?'销售订单':'未下单');}
			},
				{field: 'creater', title: '下单员', width: 80, formatter: function(value, row, rowIndex) {
					if (value != null) {
						$.post(basePath+ {uuid: value}, function(rt) {
							$("#creater_" + rowIndex).html(rt.name);
						}, 'json');
					}
					return "<span id='creater_" + rowIndex + "'></span>";
					
				}},
			{field: 'checker', title: '审查员', width: 80,formatter: function(value, row, rowIndex) {
					if (value != null) {
						$.post(basePath+ {uuid: value}, function(rt) {
							$("#checker_" + rowIndex).html(rt.name);
						}, 'json');
					}
					return "<span id='checker_" + rowIndex + "'></span>";
					
				}},
			{field: 'starter', title: '采购员', width: 80,formatter: function(value, row, rowIndex) {
					if (value != null) {
						$.post(basePath+ {uuid: value}, function(rt) {
							$("#starter_" + rowIndex).html(rt.name);
						}, 'json');
					}
					return "<span id='starter_" + rowIndex + "'></span>";
					
				}},
			{field: 'ender', title: '库管员', width: 80,
			formatter: function(value, row, rowIndex) {
					if (value != null) {
						$.post(basePath+ {uuid: value}, 
						function(rt) {
							$("#ender_" + rowIndex).html(rt.name);
						}, 'json');
					}
					return "<span id='ender_" + rowIndex + "'></span>";
					
				}},

			{field: 'totalMoney', title: '总金额', width: 80},
			{field: 'state', title: '订单状态', formatter: function(value) {
					return stats[value];
				}
			
			},
			oper
			]],
			
		singleSelect: true,
		pagination: true, //显示分页栏
		pageSize: '5', //每页的记录数
		pageList: [5, 10, 15, 20],
		height:400,
		view:detailview,
		detailFormatter:function(index,row){
			return "<table id='ddv_"+index+"'></table>";
			return "<table id='ddv_" + index + "'></table>";
		},
		onExpandRow: function(index,row){
		
			$('#ddv_'+index).datagrid({
			    url: basePath + row.uuid,
					columns: [[
						{field: 'uuid', title: '编号', width: 100},
						{field: 'goodsuuid', title: '商品编号', width: 100},
						{field: 'goodsname', title: '商品名称', width: 100},
						{field: 'price', title: '价格', width: 100},
						{field: 'num', title: '数量', width: 100},
						{field: 'money', title: '金额', width: 100},
						{field: 'endtime', title: '结束日期', width: 100, formatter: function(value) {
							return to_date(value);
						}},
						{field: 'ender', title: '仓管员', width: 100, formatter: function(value, row, rowIndex) {
							if (value != null) {
								$.post(basePath+{uuid: value}, function(rt) {
									$("#starter_" + rowIndex).html(rt.name);
								}, 'json');
							}
							return "<span id='starter_" + rowIndex + "'></span>";
						}},
						{field: 'storeuuid', title: '仓库编号', width: 100},
						{field: 'state', title: '状态', width: 100, formatter: function(value) {
							if(Request['type' == 1]){
							return value == 0 ? '未入库' : ((value == 1) ? '已入库' : '');
						}
						if(Request['type' == 2]){
						return value == 0 ? '未入库' : ((value == 1) ? '已出库' : '');
					}
						return '';
						}}
					]],
					singleSelect: true,
					onDblClickRow: function(rowIndex, rowData) {
						g_index = index;
						g_index2 = rowIndex;
						//打开窗口
						$('#orderWindow').window('open');
						//初始化表单的数据
						$('#goodsuuid').html(rowData.goodsuuid);
						$('#goodsname').html(rowData.goodsname);
						$('#num').html(rowData.num);
						$('#id').val(rowData.uuid);
					},
					loadFilter: function(data) {
						//子表格的数据
						var value = {total: 0, rows: []};
						//循环每一行
						for (var i = 0; i < data.length; i++) {
							//如果状态为“未入库”, 那么就把该行数据保存在rows数组中
							if ((isFilter && data[i].state == '0') || !isFilter) {
								value.rows.push(data[i]);
							}
						}
						return value;
					}
				});
				
			}
		});
	if(Request['type'] == 2){
		$('#grid').datagrid('hideColumn','checktime');
		$('#grid').datagrid('hideColumn','starttime');
		$('#grid').datagrid('hideColumn','checker');
		
		$('#grid').datagrid('hideColumn','starter');
	}

	
	});

//提交审核

function doCheck(orderuuid) {
	$.messager.confirm('提示', '确定要更改此订单为已审核吗？', function(flag) {
		if (flag) {
			$.post(basePath + '/orders/doCheck.do', {uuid: orderuuid}, function(rt) {
				if (rt.success) {
					
					$('#grid').datagrid('reload'); //刷新表格
				}
				$.messager.alert('提示', rt.message);
			});
		}
	});
}
	
	
	//提交订单确认
function doStart(orderuuid) {
	$.messager.confirm('提示', '确定要更改此订单为已确认吗？', function(flag) {
		if (flag) {
			$.post(basePath + '/orders/doStart.do', {uuid: orderuuid}, function(rt) {
				if (rt.success) {
					$('#grid').datagrid('reload'); //刷新表格
				}
				$.messager.alert('提示', rt.message);
			});
		}
	});
}
//入库操作
function doInStore() {
	//获取表单数据
	var data = getFormData("orderForm");
	//异步提交数据
	$.post(basePath + '/orders/doInStore.do', data, function(rt) {
		if (rt.success) {
			alert("aas");
			//$('#grid').datagrid('reload'); 
			$("#orderWindow").window('close');
			$('#ddv_' + g_index).datagrid('deleteRow', g_index2);
			if ($('#ddv_' + g_index).datagrid('getRows').length == 0) {
				$('#grid').datagrid('deleteRow', g_index);
			} 			
		}
		$.messager.alert('提示', rt.message);
		
	}, 'json');
	}
	//出库
	function doOutStore(){
		var data = getFormData('orderForm');
		$.post(basePath+'orders/doOutStore.do',data,function(rt){
			
			if(rt.success){
				$("#ordersWindow").window('close');
				$('#ddv_' + g_index).datagrid('deleteRow', g_index2);
			if($("ddv_"+g_index).datagrid('getRows').length == 0)n{
				$("#grid").datagrid('deleteRow', g_index);
			}
			}
			$.messager.alert('提示', rt.message);
		},'json');
	}
	
