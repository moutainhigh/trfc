$(function(){
	//整合url
	var URL = {
			materialAutoCompleteSearch:"/trfc/materiel/autoCompleteSearch",
			billsUrl:"/trfc/quality/sales/file/qualityScheme/billsData",
			pageUrl:"/trfc/quality/sales/file/qualityScheme/page",
			deleteUrl:"/trfc/quality/sales/file/qualityScheme/delete",
			updateUrl:"/trfc/quality/sales/file/qualityScheme/update",
			saveUrl:"/trfc/quality/sales/file/qualityScheme/add",
			codeUrl:"/trfc/system/base/code/getCode",
			updateCodeUrl:"/trfc/system/base/code/updateCode",
			itemUrl:"/trfc/quality/sales/file/qualityScheme/item",
			standardUrl:"/trfc/quality/sales/file/qualityScheme/standard"
	};
	//设置一个公共变量,当点击编辑按钮时,将原数据存入该变量中
	var editOD = {};
	//加载列表
	ShowAction(1);
	//加载下拉框
	materialSelect();
	billsSelect();
	
	//绑定刷新按钮
	$('#fresh').click(function(){ShowAction(1);});
	//绑定新增按钮
	$('#addBtn').click(initAddData);
	//绑定新增界面确定按钮
	$('#add_sure').click(saveAction);
	//绑定只显示有效
	$('#seek_invalid').change(function(){ShowAction(1);});
	//绑定修改页面确定按钮
	$('#edit_sure').click(editAction);
	//绑定跳转按钮
	$("#jumpButton").click(jumpPageAction);
	//监听每页记录 事件
	$('#pageSize').change(function(){ShowAction(1);});
	//绑定搜索按钮
	$('#seek').click(function(){ShowAction(1);});
	//绑定删除按钮
	$('#list').on('click','tr [title="删除"]',deleteAction);
	//绑定编辑按钮
	$('#list').on('click','tr [title="编辑"]',initEditData);
	//绑定跳转页面
	$('#list').on('click','tr [title="方案项目"]',function(){
		var id = $(this).closest('tr').data('obj').id;
		window.location.replace(URL.itemUrl+"?id="+id);
	});
	$('#list').on('click','tr [title="质量标准"]',function(){
		var id = $(this).closest('tr').data('obj').id;
		window.location.replace(URL.standardUrl+"?id="+id);
	});
	//获取用户id
	var userid = $('.user').attr('userid');

	//初始化新增数据
	function initAddData(){
		$('#add_material').val('').removeAttr('materialid');
		$('#add_bills').val('').removeAttr('billsid');
		
		//设置编码代号为FA
		var code = 'FA';
		//设置类型为编码
		var codeType = true;
		var param = {
				userid:userid,
				code:code,
				codeType:codeType
		};
		//获取编号,并赋值
		$.post(URL.codeUrl,param,function(result){
			if(result.code=='000000'){
				$('#add_code').val(result.data);
			}else{
				layer.msg(result.error, {icon:5});
			}
		});

		$('#add_name').val('');
		$('#add_type').val('');
		$('#add_invalid').removeAttr('checked');
		$('#add_def').removeAttr('checked');
		$('#add_standard').removeAttr('checked');
		$('#add_describe').val('');
	}
	
	//获取下拉框数据并填充
	function materialSelect(){
		var cache={};
		$(".materialSelect").autocomplete({
			//数据源
			source: function( request, response ) {
				var term = request.term;
				var material = cache['material'] || {};
				if ( term in material ) {
					response( material[ term ] );
					return;
				}
				$.post( URL.materialAutoCompleteSearch, request, function( data, status, xhr ) {
					material[ term ] = data.data;
					response( data );
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
				$(this).val(ui.item.name).attr('materialid', ui.item.id);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input keydown',function(){
	    	$(this).removeAttr('materialid');
	    }).change(function(){
    		if(!$(this).attr('materialid')){
    			$(this).val('');
    		}
	    });
	};
	//获取下拉框数据并填充
	function billsSelect(){
		var cache={};
		$(".billsSelect").autocomplete({
			//数据源
			source: function( request, response ) {
				var term = request.term;
				var material = cache['material'] || {};
				if ( term in material ) {
					response( material[ term ] );
					return;
				}
				$.post( URL.billsUrl, request, function( result ) {
					material[ term ] = result.data;
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
				$(this).val(ui.item.name).attr('billsid', ui.item.id);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input keydown',function(){
	    	$(this).removeAttr('billsid');
	    }).change(function(){
    		if(!$(this).attr('billsid')){
    			$(this).val('');
    		}
	    });
	};

	
	//初始化编辑数据
	function initEditData(){
		//获取数据
		var obj = $(this).closest('tr').data('obj')
		editOD.obj = obj;
		//设置等下拉框数据加载完成后 执行
		
		$('#edit_material').val(obj.materialname).attr('materialid',obj.materialid);
		$('#edit_bills').val(obj.billsname).attr('billsid',obj.bills);
		$('#edit_id').val(obj.id);
		$('#edit_code').val(obj.code);
		$('#edit_name').val(obj.name);
		$('#edit_type').val(obj.type);

		//为checkbox赋值
		$('#edit_invalid')[0].checked=true;
		if('1'==obj.invalid){
			$('#edit_invalid')[0].checked=false;
		}
		$('#edit_def')[0].checked=true;
		if('1'==obj.def){
			$('#edit_def')[0].checked=false;
		}
		$('#edit_standard')[0].checked=true;
		if('1'==obj.standard){
			$('#edit_standard')[0].checked=false;
		}
		$('#edit_describe').val(obj.describe);
	}

//	新增数据
	function saveAction(){
		//获取新增页面的数据
		var param = getAddData();
		//若无参数,则不执行
		if(param){
			$.post(URL.saveUrl,param,function(result){
				if(result.code=='000000'){
					//添加成功后,刷新服务器编号(增1)
					updateCode();
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
			if(contrast(editOD.obj,param)){
				//关闭编辑块
				$("#edit_cancel").click();
			}else{
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
	}
	//添加成功后,刷新标号(增1)
	function updateCode(){
		//设置编码代号
		var code = 'FA';
		//编制编号
		var codeType = true;
		var param = {
				userid:userid,
				code:code,
				codeType:codeType
		}; 
		//更新编码
		$.post(URL.updateCodeUrl,param,function(result){
			if(result.code=='000000'){
				//加载列表
				ShowAction(1);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
//	获取新增数据
	function getAddData(){
		var code = $('#add_code').val();
		var materialid = $('#add_material').attr('materialid');
		if(!materialid){
			layer.msg("物料不能为空!");
			return null;
		};
		var bills = $('#add_bills').attr('billsid');
		if(!bills){
			layer.msg("单据类型不能为空!");
			return null;
		};
		var name = $('#add_name').val();
		if(!name){
			layer.msg("名称不能为空!");
			return null;
		}
		var type = $('#add_type').val();
		if(!type){
			layer.msg("类型不能为空");
			return null;
		}
		var invalid = '1';
		if($('#add_invalid').prop('checked')){
			invalid='0';
		}
		var def = '1';
		if($('#add_def').prop('checked')){
			def='0';
		}
		var standard = '1';
		if($('#add_standard').prop('checked')){
			standard='0';
		}
		var describe = $('#add_describe').val();
		var param = {
				code:code,
				materialid:materialid,
				bills:bills,
				name:name,
				type:type,
				invalid:invalid,
				def:def,
				standard:standard,
				describe:describe,
				user:userid
		};
		return param;
	}
//	获取修改数据
	function getEditData(){
		var id = $('#edit_id').val();
		var code = $('#edit_code').val();
		var materialid = $('#edit_material').attr('materialid');
		if(!materialid){
			layer.msg("物料不能为空!");
			return null;
		};
		var bills = $('#edit_bills').attr('billsid');
		if(!bills){
			layer.msg("单据类型不能为空!");
			return null;
		};
		var name = $('#edit_name').val();
		if(!name){
			layer.msg("名称不能为空!");
			return null;
		}
		var type = $('#edit_type').val();
		if(!type){
			layer.msg("类型不能为空");
			return null;
		}
		var invalid = '1';
		if($('#edit_invalid').prop('checked')){
			invalid='0';
		}
		var def = '1';
		if($('#edit_def').prop('checked')){
			def='0';
		}
		var standard = '1';
		if($('#edit_standard').prop('checked')){
			standard='0';
		}
		var describe = $('#edit_describe').val();
		var param = {
				id:id,
				code:code,
				materialid:materialid,
				bills:bills,
				name:name,
				type:type,
				invalid:invalid,
				def:def,
				standard:standard,
				describe:describe,
				user:userid
		};
		return param;
	}
	//对比两个对象 如果相同则返回true
	function contrast(obj1,obj2){
		if(obj1.materialid==obj2.materialid &&
				obj1.bills==obj2.bills &&
				obj1.name==obj2.name &&
				obj1.type==obj2.type &&
				obj1.invalid==obj2.invalid &&
				obj1.def==obj2.def &&
				obj1.standard==obj2.standard &&
				obj1.describe==obj2.describe
		){
			return true;
		}else{
			return false;
		}
	}
	//删除数据
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
		$.post(URL.deleteUrl,{id:id},function(result){
			if(result.code=='000000'){
				//重新加载当前页面
				ShowAction(1);
			}else{
				layer.msg(result.error, {icon:5});
			}
		});
	}

////	获取下拉框数据并填充
//	function materialSelect(){
//		//获取数据
//		$selector = $.post(URL.selectorUrl,{},function(result){
//			if(result.code=='000000'){
//				//填充数据
//				fillContent(result.data);
//			}else{
//				layer.msg(result.error, {icon:5});
//			}
//		});
//	}
////	填充数据
//	function fillContent(list){
//		var select = $('.materialSelect');
//		//设置默认值
//		select.append("<option></option>");
//		if(list){
//			for(var i=0;i<list.length;i++){
//				var obj = list[i];
//				var msg = obj.name;
//				if(obj.spec){
//					msg = obj.name+' | '+obj.spec;
//				}
//				var option = '<option value='+obj.id+'>'+msg+'</option>';
//				//追加数据
//				select.append(option);
//			}
//		}
//	}
////	获取下拉框数据并填充
//	function billsSelect(){
//		//获取数据
//		$selector = $.post(URL.billsUrl,{},function(result){
//			if(result.code=='000000'){
//				//填充数据
//				fillBillsContent(result.data);
//			}else{
//				layer.msg(result.error, {icon:5});
//			}
//		});
//	}
////	填充数据
//	function fillBillsContent(list){
//		var select = $('.billsSelect');
//		//设置默认值
//		select.append("<option></option>");
//		if(list){
//			for(var i=0;i<list.length;i++){
//				var obj = list[i];
//				var msg = obj.name;
//				if(obj.spec){
//					msg = obj.name+' | '+obj.spec;
//				}
//				var option = '<option value='+obj.id+'>'+msg+'</option>';
//				//追加数据
//				select.append(option);
//			}
//		}
//	}



//	页面跳转
	function jumpPageAction(){
		//获取总页数
		var maxpageno = $('#jumpPageNo').attr('maxpageno');
		//获取当前页
		var pageno = $('#jumpPageNo').val();
		//判断跳转值是否在符合规范
		if(!pageno || !$.isNumeric(pageno) || pageno<=0 || pageno>maxpageno){
			layer.msg('输入的数字必须在1~'+maxpageno+'之间');
		}else{
			//加载指定的列表数据
			ShowAction(pageno);
		}
	}
//	前进一页
	function pageCallback(pageNo){
		ShowAction(pageNo+1);
	}

//	展示数据列表
	function ShowAction(pageNo){
		//启动缓冲动画
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var url = URL.pageUrl;
		//获取当前页面记录数
		var pageSize = $('#pageSize').val();
		//获取查询条件
		var materialid = $('#seek_material').attr('materialid');
		var namelike = $('#seek_namelike').val();
		var type = $('#seek_type').val();
		var invalid = "1";
		if($('#seek_invalid').prop('checked')){
			invalid = "0";
		}

		var params = {
				pageSize:pageSize,
				materialid:materialid,
				namelike:namelike,
				type:type,
				invalid:invalid
		};
		//获得当前页面标记
		params.pageNo = pageNo;
		$.post(url,params,function(result){
			if(result.code=='000000'){
				var list = result.data.list;
				var pageNo = result.data.pageNo;
				var pageSize = result.data.pageSize;
				var total = result.data.total;
				//添加数据总数
				$('#total').html(result.data.total);
				//绑定 一个maxpageno属性
				$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
				//分页插件
				$("#pagination").pagination(total, {
					callback: pageCallback,
					prev_text: '上一页',
					next_text: '下一页',
					items_per_page:pageSize,
					num_display_entries:4,
					current_page:pageNo-1,
					num_edge_entries:1,
					maxentries:total,
					link_to:"javascript:void(0)"
				});
				if(list){
					showPageData(list,pageSize,pageNo);
				}else{
					layer.msg('暂无数据');
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
		var TYPE = {
				0:'采购项目',
				1:'销售项目'
		};
		//加载时清空列表和跳转值
		$('#jumpPageNo').val('');
		//清空列表
		var tbody = $('#list').empty();
		for(var i=0;i<list.length;i++){
			var obj = list[i];
			var invalid = obj.invalid;
			var tr = '<tr>'
				+'<td>'+((pageNo-1)*pageSize+i+1)+'</td>'
				+'<td>'+(obj.code || '')+'</td>'
				+'<td>'+(obj.name || '')+'</td>'
				+'<td>'+(obj.materialname || '')+'</td>'
				+'<td>'+(obj.billsname || '')+'</td>'
				+'<td><input type="checkbox" '+("0"==invalid?'checked="true"':"")+' disabled="true"></td>'
				+'<td><input type="checkbox" '+("0"==obj.def?'checked="true"':"")+' disabled="true"></td>'
				+'<td>'+(TYPE[obj.type] || '')+'</td>'
				+'<td><input type="checkbox" '+("0"==obj.standard?'checked="true"':"")+' disabled="true"></td>'
				+'<td>'+(obj.describe || '')+'</td>'
				+'<td><span> <a data-toggle="modal"'
				+'		data-target="#edit"><i class="iconfont"'
				+'			data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>'
				+'</span> <span> <a data-toggle="modal" data-target="#dele"><i'
				+'			class="iconfont" data-toggle="tooltip" data-placement="left"'
				+'			title="删除">&#xe63d;</i></a>'
				+'</span></td>'
				+'<td><a title="方案项目">方案项目</a> <a title="质量标准">质量标准</a></td></tr>';
			//转换为jquery对象
			tr=$(tr);
			//追加
			tbody.append(tr);
			//将数据绑定到tr上
			tr.data('obj',obj);
		}
	}


});