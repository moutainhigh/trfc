$(function(){
	//整合url
	var URL = {
			qItemAutoCompleteSearch: "/trfc/quality/sales/file/qualityItem/autoCompleteSearch",
			inquireUrl:"/trfc/quality/sales/file/qualityScheme/inquire",
			deleteUrl:"/trfc/quality/sales/file/qualityScheme/deleteItem",
			updateUrl:"/trfc/quality/sales/file/qualityScheme/updateItem",
			saveUrl:"/trfc/quality/sales/file/qualityScheme/addItem",
			schemeData:"/trfc/quality/sales/file/qualityScheme/getSchemeData",
			addBatchUrl:"/trfc/quality/sales/file/qualityScheme/addBatchItem",
			standardUrl:"/trfc/quality/sales/file/qualityScheme/standard",
			getOldItemUrl:"/trfc/quality/sales/file/qualityScheme/getOldItem"
	};
	var TYPE = {0:'采购项目',1:'销售项目'};
	//设置一个公共变量
	var editOD = {};
	//获取方案id
	var schemeid = getId();
	//初始化页面
	initPage();
	qschemeSelect();
	getOldItem();
	//绑定刷新按钮
	$('#fresh').click(function(){ShowAction(1);});
	//绑定新增按钮
	$('#addBtn').click(initAddData);
	$('#addBatch').click(initAddBatchData);
	//绑定新增界面确定按钮
	$('#add_sure').click(saveAction);
	//绑定新增界面确定按钮
	$('#addBatch_sure').click(saveAddBatch);
	//绑定修改页面确定按钮
	$('#edit_sure').click(editAction);
	//绑定删除按钮
	$('#list').on('click','tr [title="删除"]',deleteAction);
	//绑定编辑按钮
	$('#list').on('click','tr [title="编辑"]',initEditData);
	$('ul li:contains(质检方案-质检标准)').click(function(){
		window.location.replace(URL.standardUrl+"?id="+schemeid);
	});
	$('.itemSelect').focus(getOldItem);
	
	function getOldItem(){
		$.post(URL.getOldItemUrl,{schemeid:schemeid},function(result){
			if('000000'==result.code){
				editOD.items = result.data;
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
		
	}
	//提交保存数据
	function saveAddBatch(){
		var param = getAddBatchData();
		//判断 param有没有值,无值则不处理
		if(param){
			if(confirm('确定要保存吗?')){
				//数据存到服务器
				$.post(URL.addBatchUrl,param,function(result){
					if(result.code=='000000'){
						//加载列表
						ShowAction(1);
						//关闭新增模块
						$("#addBatch_cancel").click();
					}else{
						layer.msg(result.error,{icon:5});
					}
				});
			}
		}
	}
	//添加一列
	function addTR(index){
		
		var tbody = $('#addBatch_list');
		var trs = tbody.find('tr');
		//只有最后一行的改变事件 才能触发添加
		if(trs.length>=index){
			return;
		}
		var tr = '<tr>'
           +' <td> '+index+'</td>'
           +' <td><div class="selct2_alt_div">'
           +' <input class="itemSelect" type="text" style="width:220px;text-align:left;"'
           +' >'
           +'  </div>'
           +'  </td>'
           +'  <td><input type="text" style="width:350px;text-align:left;"></td>'
           +'</tr>';
		tr = $(tr);
		//将tr追加到tbody中
		tbody.append(tr);
		
		qschemeSelect();
		tr.find('td input:first').click(function(){addTR(index+1)});
	}
	
	//初始化页面
	function initPage(){
		$.post(URL.schemeData,{id:schemeid},function(result){
			if(result.code=='000000'){
				var obj = result.data;
				$('#headline').html('质检方案项目 - '+obj.name);
				editOD.obj = obj;
				//加载列表
				ShowAction(1);
//				initAddData();
//				initAddBatchData();
//				$('#addBatch_list').on('click','input:first',function(){
//					var index = $(this).closest('tr').find('td:first').html();
//					addTR(eval(index)+1);});
			}else{
				layer.msg(result.error,{icon:5});
			}
		});

	}
	
	//初始化批量新增数据
	function initAddBatchData(){

		$('#addBatch_name').val(editOD.obj.name);
		$('#addBatch_schemetype').val(TYPE[editOD.obj.type]);
		$('#addBatch_invalid').attr('checked','checked');
		$('#addBatch_list').html('');
		addTR(1);
	}
	//获取批量新增数据
	function getAddBatchData(){
		var invalid = '1';
		if($('#addBatch_invalid').prop('checked')){
			invalid='0';
		}
		//获取user的id
		var user = $('.user').attr("userid");
		var data = {
				schemeid:getId(),
				invalid:invalid,
				user:user
		};
		//获取表格数据
		var trs = $('#addBatch_list tr');
		var arr = new Array();
		//通过循环吧数据存到arr中
		for(var i=0;i<trs.length-1;i++){
			//获取子元素
			var inputs = trs.eq(i).find('input');
			var itemid = inputs.eq(0).attr('itemid');
			//物料名称不能为空
			if(!schemeid){
				return null;
			}
			var remark = inputs.eq(1).val();
			var mater = {
					itemid:itemid,
					remark:remark
			}
			//将结果放入数组
			arr[i]=mater;
		}
		//讲数组转换为JSON字符串
		data.arrStr = JSON.stringify(arr);
		//判断是否为空,为空则返回null
		if('[]'==data.arrStr){
			alert('物料详细不能为空!');
			data = null;
		}
		return data;
	}

	//获取下拉框数据并填充
	function qschemeSelect(){
		var cache={};
		$(".itemSelect").autocomplete({
			//数据源
			source: function( request, response ) {
				var term = request.term;
				var material = cache['material'] || {};
				if ( term in material ) {
					response( material[ term ] );
					return;
				}
				$.post( URL.qItemAutoCompleteSearch, request, function( result, status, xhr ) {
					var objs = result.data;
					for(var i=0;i<objs.length;i++){
						if(editOD.items.indexOf(objs[i].id)>=0){
							objs.splice(i,1);
							i--;
						}
						
					}
					material[ term ] = objs;
					response( result.data );
				});
			},
			//显示下拉框
			response: function( event, ui ) {
				if(ui.content && ui.content.length > 0){
					//展示下拉框
					ui.content.forEach(function(x,i,a){
						x.label = x.name;
						x.value = x.id;
					});
				}
			},
			//选定,显示结果到输入框
			select: function( event, ui ) {
				$(this).val(ui.item.name).attr('itemid', ui.item.id);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input propertychange',function(){
	    	$(this).removeAttr('itemid');
	    }).change(function(){
    		if(!$(this).attr('itemid')){
    			$(this).val('');
    		}
	    });
	};
	
	//初始化新增数据
	function initAddData(){
		//等待下拉框加载完成后,执行
		$('#add_item').val('').removeAttr("itemid");
		
		$('#add_name').val(editOD.obj.code);
		$('#add_schemetype').val(TYPE[editOD.obj.type]);


		$('#add_invalid').attr('checked','checked')
		$('#add_remark').val('');
	}
	//初始化编辑数据
	function initEditData(){
		//获取数据
		var obj = $(this).closest('tr').data('obj')
		editOD.data = obj;
		//设置等下拉框数据加载完成后 执行
		
		$('#edit_item').val(obj.itemname).attr('itemid',obj.itemid)
		$('#edit_id').val(obj.id);
		$('#edit_name').val(editOD.obj.name);
		$('#edit_schemetype').val(TYPE[editOD.obj.type]);

		//为checkbox赋值
		$('#edit_invalid')[0].checked=true;
		if('1'==obj.invalid){
			$('#edit_invalid')[0].checked=false;
		}
		$('#edit_remark').val(obj.remark);
	}

//	新增数据
	function saveAction(){
		//获取新增页面的数据
		var param = getAddData();
		//若无参数,则不执行
		if(param){
			$.post(URL.saveUrl,param,function(result){
				if(result.code=='000000'){
					//重新加载列表
					ShowAction(1);
					//关闭新增页面
					$("#add_cancel").click();
				}else{
					layer.msg(result.error,{icon:5});
				}
			});
		}
	}
//	修改数据
	function editAction(){
		var param = getEditData();
		if(param){
			//修改后的和原数据相同的情况,不执行

			$.post(URL.updateUrl,param,function(result){
				if(result.code=='000000'){
					//加载列表
					ShowAction(1);
					//关闭编辑块
					$("#edit_cancel").click();
				}else{
					layer.msg(result.error,{icon:5});
				}
			});
		}
	}
//	获取新增数据
	function getAddData(){

		var itemid = $('#add_item').attr('itemid');
		if(!itemid){
			alert("项目明细不能为空");
			return null;
		}


		var invalid = '1';
		if($('#add_invalid').prop('checked')){
			invalid='0';
		}
		var remark = $('#add_remark').val();
		var userid = $('.user').attr('userid');
		var param = {
				schemeid:schemeid,
				itemid:itemid,
				invalid:invalid,
				remark:remark,
				user:userid
		};
		return param;
	}
//	获取修改数据
	function getEditData(){
		var id = $('#edit_id').val();
		var itemid = $('#edit_item').attr('itemid');
		if(!itemid){
			alert("项目明细不能为空");
			return null;
		}


		var invalid = '1';
		if($('#edit_invalid').prop('checked')){
			invalid='0';
		}
		var userid = $('.user').attr('userid');
		var remark = $('#edit_remark').val();
		var userid = $('.user').attr('userid');
		var param = {
				id:id,
				schemeid:schemeid,
				itemid:itemid,
				invalid:invalid,
				remark:remark,
				user:userid
		};
		return param;
	}

	function deleteAction(){
		var id = $(this).closest('tr').data('obj').id;
		//弹出删除确认框
		var index = layer.confirm('你确定要删除吗?', {
			area: '600px', 
			btn: ['确定','取消'] //按钮
		}, function(){
			//提交删除的数据
			submitDelete(id);
			//关闭对话框
			layer.close(index);
		}, function(){
		});
	}
	function submitDelete(id){
		$.post(URL.deleteUrl,{id:id},function(result){
			if(result.code=='000000'){
				//重新加载当前页面
				ShowAction(1);
			}else{
				layer.msg(result.error, {icon:5});
			}
		});
	}

//
//	展示数据列表
	function ShowAction(pageNo){
		//启动缓冲动画
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var url = URL.inquireUrl;
		//获取查询条件
		var params = {schemeid:schemeid };
		//获得当前页面标记
		$.post(url,params,function(result){
			if(result.code=='000000'){
				var list = result.data;
				if(list){
					showPageData(list);
				}
				//关闭缓冲图标
				layer.close(index);
			}else{
				alert(result.error);
			}
		});
	}
//	展示列表
	function showPageData(list){

		var tbody = $('#list').empty();
		for(var i=0;i<list.length;i++){
			var obj = list[i];
			var invalid = obj.invalid;
			var tr = '<tr>'
				+'<td>'+(i+1)+'</td>'
				+'<td>'+(editOD.obj.code || '')+'</td>'
				+'<td>'+(editOD.obj.name || '')+'</td>'
				+'<td>'+(obj.itemcode || '')+'</td>'
				+'<td>'+(obj.itemname || '')+'</td>'
				+'<td><input type="checkbox" '+("0"==invalid?'checked="true"':"")+' disabled="true"></td>'
				+'<td>'+(TYPE[editOD.obj.type] || '')+'</td>'
				+'<td>'+(obj.remark || '')+'</td>'
				+'<td><span> <a data-toggle="modal"'
				+'		data-target="#edit"><i class="iconfont"'
				+'			data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>'
				+'</span> <span> <a data-toggle="modal" data-target="#dele"><i'
				+'			class="iconfont" data-toggle="tooltip" data-placement="left"'
				+'			title="删除">&#xe63d;</i></a>'
				+'</span></td>'
				+'</tr>';
			//转换为jquery对象
			tr=$(tr);
			//追加
			tbody.append(tr);
			//将数据绑定到tr上
			tr.data('obj',obj);
		}
	}
	//获取id
	function getId(){
		var href = window.location.href;
		//获取id
		var strs = href.split('id=');
		var id = strs[1];
		return id;
	}

});

