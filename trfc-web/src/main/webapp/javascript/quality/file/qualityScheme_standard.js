$(function(){
	//加载运算符拉框框
	var options = '<option></option><option value="1">小于</option><option value="3">'
		+'小于等于</option><option value="0">大于</option><option value="2">大于等于</option><option value="4">等于</option>';
	$('.comparison').html(options);
	
	//整合url
	var schemeid = getSchemeId();
	var URL = {
			pageUrl:"/trfc/quality/sales/file/qualityScheme/inquire",
			updateUrl:"/trfc/quality/sales/file/qualityScheme/updateItem",
			schemeData:"/trfc/quality/sales/file/qualityScheme/getSchemeData",
			updateBatchUrl:"/trfc/quality/sales/file/qualityScheme/updateBatchItem",
			itemUrl:"/trfc/quality/sales/file/qualityScheme/item"
	};
	initPage();
	//设置一个公共变量,当点击编辑按钮时,将原数据存入该变量中
	var DATA = {};
	//加载列表
	ShowAction(1);
	//绑定刷新按钮
	$('#fresh').click(function(){ShowAction(1);});
	//绑定新增按钮
	$('#addBtn').click(initAddData);
//	//绑定新增界面确定按钮
	$('#add_sure').click(saveAction);
//	//绑定修改页面确定按钮
	$('#edit_sure').click(editAction);
	//绑定删除按钮
	$('#list').on('click','tr [title="删除"]',deleteAction);
	//绑定编辑按钮
	$('#list').on('click','tr [title="编辑"]',initEditData);
	$('ul li:contains(质检方案-项目)').click(function(){
	window.location.replace(URL.itemUrl+"?id="+schemeid);
});
	//获取用户id
	var userid = $('.user').attr('userid');

	//初始化新增数据
	function initAddData(){
		
	
		$('#add_schemename').val(DATA.schemeobj.name);
		$('#add_materialname').val(DATA.schemeobj.materialname);
		$('#add_invalid').attr('checked','checked');
		$.post(URL.pageUrl,{schemeid:schemeid,status:'0'},function(result){
			if(result.code=='000000'){
				var list = result.data;
				//清空列表
				var tbody = $('#add_list').empty();
				for(var i=0;i<list.length;i++){
					var obj = list[i];
					var invlid = obj.invlid;
					var tr = '<tr>'
                       +' <td><input type="text" class="w80" >'+(i+1)+'</td>'
                       +' <td><input type="text" class="w80" >'+(DATA.schemeobj.name || '')+'</td>'
                       +' <td><input type="text" class="w80" >'+(obj.materialname || '')+'</td>'
                       +' <td><input type="text" class="w80" >'+(obj.itemcode || '')+'</td>'
                       +' <td><input type="text" class="w80" >'+(obj.itemname || '')+'</td>'
                       +' <td><input type="text" class="w80" >'+(obj.units || '')+'</td>'
                       +' <td><select class="comparison form-control" style="width:100px;"></select></td>'
                       +' <td><input type="text" class="w80" ></td>'
                       +' <td><select class="comparison form-control" style="width:100px;"></select></td>'
                       +' <td><input type="text" class="w80" ></td>'
                       +' <td><select class="comparison form-control" style="width:100px;"></select></td>'
                       +' <td><input type="text" class="w80" ></td>'
                       +' <td><input type="text" class="w80" ></td>'
                       +' <td><input type="text" class="w80" ></td>'
                       +' <td><input type="text" class="w80" ></td>'
                       +' <td><input type="text" class="w80" ></td>'
                       +'</tr>';
					//转换为jquery对象
					tr=$(tr);
					//追加
					tbody.append(tr);
					//将数据绑定到tr上
					tr.data('obj',obj);
				}
				$('.comparison').empty().html(options);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
		
	}
////获取新增数据
	function getAddData(){
		//获取user的id
		var user = $('.user').attr("userid");
		var data = {
				user:user
		};
		//获取表格数据
		var trs = $('#add_list tr');
		var arr = new Array();
		//通过循环吧数据存到arr中
		for(var i=0;i<trs.length;i++){
			var tr = $(trs[i]);
			var obj = tr.data('obj');
			//获取子元素
			var inputs = tr.find('td input');
			var selects = tr.find('td select');
			var id = obj.id;
			var comparison1 = selects.eq(0).val();
			var comparison2 = selects.eq(1).val();
			var comparison3 = selects.eq(2).val();
			var standardval = inputs.eq(0).val();
			var lowlimit = inputs.eq(1).val();
			var upperlimit = inputs.eq(2).val();
			var baseval = inputs.eq(3).val();
			var floatval = inputs.eq(4).val();
			var charge = inputs.eq(5).val();
			var deduct = inputs.eq(6).val();
			
			var mater = {
					id:id,
					comparison1:comparison1,
					comparison2:comparison2,
					comparison3:comparison3,
					standardval:standardval,
					lowlimit:lowlimit,
					upperlimit:upperlimit,
					baseval:baseval,
					floatval:floatval,
					charge:charge,
					deduct:deduct
			}
			//将结果放入数组
			arr[i]=mater;
		}
		//讲数组转换为JSON字符串
		data.arrStr = JSON.stringify(arr);
		//判断是否为空,为空则返回null
		if('[]'==data.arrStr){
			layer.msg('质检项目详细不能为空!');
			data = null;
		}
		return data;
	}
//	//初始化编辑数据
	function initEditData(){
		//获取数据
		var obj = $(this).closest('tr').data('obj')
		DATA.obj = obj;
		$('#edit_id').val(obj.id);
		$('#edit_schemename').val(DATA.schemeobj.name);
		$('#edit_itemmaterial').val(obj.materialname);
		$('#edit_item').val(obj.itemname);
		$('#edit_units').val(obj.units);
		$('#edit_comparison1').val(obj.comparison1);
		$('#edit_standardval').val(obj.standardval);
		$('#edit_comparison2').val(obj.comparison2);
		$('#edit_lowlimit').val(obj.lowlimit);
		$('#edit_comparison3').val(obj.comparison3);
		$('#edit_upperlimit').val(obj.upperlimit);
		$('#edit_baseval').val(obj.baseval);
		$('#edit_floatval').val(obj.floatval);
		$('#edit_charge').val(obj.charge);
		$('#edit_deduct').val(obj.deduct);
		
		//为checkbox赋值
		$('#edit_invalid')[0].checked=true;
		if('1'==obj.invalid){
			$('#edit_invalid')[0].checked=false;
		}
	}
//
////	新增数据
	function saveAction(){
		//获取新增页面的数据
		var param = getAddData();
		//若无参数,则不执行
		if(param){
			$.post(URL.updateBatchUrl,param,function(result){
				if(result.code=='000000'){
					//加载列表
					ShowAction(1);
					//关闭新增页面
					$("#add_cancel").click();
				}else{
					layer.msg(result.error,{icon:5});
				}
			});
		}
	}
////	修改数据
	function editAction(){
		var param = getEditData();
		if(param){
			//修改后的和原数据相同的情况,不执行
//			if(contrast(DATA.obj,param)){
//				//关闭编辑块
//				$("#edit_cancel").click();
//			}else{
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
//			}
		}
	}


////	获取修改数据
	function getEditData(){
		var id = $('#edit_id').val();
		var comparison1 = $('#edit_comparison1').val();
		var standardval = $('#edit_standardval').val();
		
		var comparison2 = $('#edit_comparison2').val();
		
		var lowlimit = $('#edit_lowlimit').val();
		var comparison3 = $('#edit_comparison3').val();
		var upperlimit = $('#edit_upperlimit').val();
		var baseval = $('#edit_baseval').val();
		var floatval = $('#edit_floatval').val();
		var charge = $('#edit_charge').val();
		var deduct = $('#edit_deduct').val();
		var user = $('.user').attr('userid');
		var invalid = '1';
		if($('#edit_invalid').prop('checked')){
			invalid='0';
		}
		
		var param = {
				id:id,
				comparison1:comparison1,
				standardval:standardval,
				comparison2:comparison2,
				lowlimit:lowlimit,
				comparison3:comparison3,
				upperlimit:upperlimit,
				baseval:baseval,
				floatval:floatval,
				charge:charge,
				deduct:deduct,
				invalid:invalid,
				user:user
		};
		return param;
	}

//	//删除数据
	function deleteAction(){
		//获取id
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
		$.post(URL.updateUrl,{id:id,status:'0'},function(result){
			if(result.code=='000000'){
				//重新加载当前页面
				ShowAction(1);
			}else{
				layer.msg(result.error, {icon:5});
			}
		});
	}
//

	//初始化页面
	function initPage(){
		
		$.post(URL.schemeData,{id:schemeid},function(result){
			if(result.code=='000000'){
				var obj = result.data;
				$('#headline').html('质检方案质量标准 - '+obj.name);
				DATA.schemeobj = obj;
			}else{
				layer.msg(result.error,{icon:5});
			}
		});

	}


//	展示数据列表
	function ShowAction(pageNo){
		//启动缓冲动画
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var url = URL.pageUrl;
		
		//获取查询条件
		var params = {schemeid:schemeid,status:'1'};
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
				layer.msg(result.error,{icon:5});
			}
		});
	}
//	展示列表
	function showPageData(list,pageSize,pageNo){
		//设置TYPE变量 ,作用为 显示结果
		var COMP = {
				0:' >',
				1:' <',
				2:' >=',
				3:' <=',
				4:' ='
		};
		
		//清空列表
		var tbody = $('#list').empty();
		for(var i=0;i<list.length;i++){
			var obj = list[i];
			var invlid = obj.invlid;
			var tr = '<tr>'
				+'<td>'+(i+1)+'</td>'
				+'<td>'+(DATA.schemeobj.name || '')+'</td>'
				+'<td>'+(obj.materialname || '')+'</td>'
				+'<td>'+(obj.itemcode || '')+'</td>'
				+'<td>'+(obj.itemname || '')+'</td>'
				+'<td>'+(obj.units || '')+'</td>'
				+'<td>'+(COMP[obj.comparison1] || '')+'</td>'
				+'<td>'+(obj.standardval || '')+'</td>'
				+'<td>'+(COMP[obj.comparison2] || '')+'</td>'
				+'<td>'+(obj.lowlimit || '')+'</td>'
				+'<td>'+(COMP[obj.comparison3] || '')+'</td>'
				+'<td>'+(obj.upperlimit || '')+'</td>'
				+'<td>'+(obj.baseval || '')+'</td>'
				+'<td>'+(obj.floatval || '')+'</td>'
				+'<td>'+(obj.charge || '')+'</td>'
				+'<td>'+(obj.deduct || '')+'</td>'
				+'<td><input type="checkbox" '+("0"==obj.invalid?'checked="true"':"")+' disabled="true"></td>'
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
	function getSchemeId(){
		//获取地址栏数据
		var href = window.location.href;
		//将字符串拆分
		var strs = href.split('id=');
		return strs[1];
		
	};
	
	
});