$(function(){
	//整合url
	var URL = {
			pageUrl:"/trfc/quality/sales/file/qualityItem/page",
			getLineUrl:"/trfc/quality/sales/file/qualityItem/getColumns",
			deleteUrl:"/trfc/quality/sales/file/qualityItem/delete",
			updateUrl:"/trfc/quality/sales/file/qualityItem/update",
			saveUrl:"/trfc/quality/sales/file/qualityItem/add",
			codeUrl:"/trfc/system/base/code/getCode",
			updateCodeUrl:"/trfc/system/base/code/updateCode",
			columnAutoCompleteSearch:"/trfc/quality/sales/file/qualityColumn/autoCompleteSearch"
	};
	//设置一个公共变量,当点击编辑按钮时,将原数据存入该变量中
	var editOD = {};
	//加载列表
	ShowAction(1);
	//绑定刷新按钮
	$('#fresh').click(function(){ShowAction(1);});
	//绑定新增按钮
	$('#addBtn').click(initAddData);
	//绑定新增界面确定按钮
	$('#add_sure').click(saveAction);
	//绑定只显示有效
	$('#seek_invlid').change(function(){ShowAction(1);});
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
	$('.columnSel').focus(getLines);
	//获取用户id
	var userid = $('.user').attr('userid');
	function getLines(){
		$.post(URL.getLineUrl,{},function(result){
			if('000000'==result.code){
				editOD.lines = result.data;
				initSelect();
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	//初始化新增数据
	function initAddData(){
		
		//设置编码代号为FA
		var code = 'ZL';
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
		$('#add_ename').val('');
		$('#add_units').val('');
		$('#add_type').val('');
		$('#add_line').val('');
		$('#add_formula').val('');
		$('#add_invlid').removeAttr('checked');
		$('#add_vgroups').val('');
		$('#add_vdays').val('');
		$('#add_vtype').val('');
		$('#add_remark').val('');
	}
	
	
	//初始化编辑数据
	function initEditData(){
		//获取数据
		var obj = $(this).closest('tr').data('obj')
		editOD.obj = obj;
		$('#edit_id').val(obj.id);
		$('#edit_code').val(obj.code);
		$('#edit_name').val(obj.name);
		$('#edit_ename').val(obj.ename);
		$('#edit_units').val(obj.units);
		$('#edit_type').val(obj.type);
		$('#edit_line').attr('columnid',obj.line).val(obj.lineval);
		$('#edit_formula').val(obj.formula);
		//为checkbox赋值
		$('#edit_invlid')[0].checked=true;
		if('1'==obj.invlid){
			$('#edit_invlid')[0].checked=false;
		}
		$('#edit_vgroups').val(obj.vgroups);
		$('#edit_vdays').val(obj.vdays);
		$('#edit_vtype').val(obj.vtype);
		$('#edit_remark').val(obj.remark);
	
		
	}
	function initSelect(){
		var cache = {};
		$("#add_line").autocomplete({
			//数据源
			source: function( request, response ) {
				var term = request.term;
				var column = cache['column'] || {};
				if ( term in column ) {
					response( column[ term ] );
					return;
				}
				$.post( URL.columnAutoCompleteSearch, {type:$('#add_type').val()}, function( result ) {
					var list = editOD.lines;
					//将使用过的列 剔除出去
					for(var i = 0;i<result.data.length;i++){
						var index = list.indexOf(result.data[i].id);
						if(index>=0){
							result.data.splice(i,1);
							i=i-1;
						}
					}
					column[ term ] = result.data;
					response( result.data );
				});
			},
			//显示下拉框
			response: function( event, ui ) {
				if(ui.content && ui.content.length > 0){
					//展示下拉框
					ui.content.forEach(function(x,i,a){
							x.label = x.val;
							x.value = x.id;
					});
				}
			},
			//选定,显示结果到输入框
			select: function( event, ui ) {
				$(this).val(ui.item.val).attr('columnid', ui.item.id);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input propertychange',function(){
	    	$(this).removeAttr('columnid');
	    }).change(function(){
    		if(!$(this).attr('columnid')){
    			$(this).val('');
    		}
	    });
		$("#edit_line").autocomplete({
			//数据源
			source: function( request, response ) {
				var term = request.term;
				var column = cache['column'] || {};
				if ( term in column ) {
					response( column[ term ] );
					return;
				}
				$.post( URL.columnAutoCompleteSearch, {type:$('#edit_type').val()}, function( result ) {
					var list = editOD.lines;
					//将使用过的列 剔除出去
					for(var i = 0;i<result.data.length;i++){
						var index = list.indexOf(result.data[i].id);
						if(index>=0){
							result.data.splice(i,1);
							i=i-1;
						}
					}
					column[ term ] = result.data;
					response( result.data );
				});
			},
			//显示下拉框
			response: function( event, ui ) {
				if(ui.content && ui.content.length > 0){
					//展示下拉框
					ui.content.forEach(function(x,i,a){
							x.label = x.val;
							x.value = x.id;
					});
				}
			},
			//选定,显示结果到输入框
			select: function( event, ui ) {
				$(this).val(ui.item.val).attr('columnid', ui.item.id);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input propertychange',function(){
	    	$(this).removeAttr('columnid');
	    }).change(function(){
    		if(!$(this).attr('columnid')){
    			$(this).val('');
    		}
	    });
		
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
		var code = 'ZL';
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
		var ename = $('#add_ename').val();
		if(!ename){
			alert("英文代号不能为空!");
			return null;
		};
		var name = $('#add_name').val();
		if(!name){
			alert("名称不能为空!");
			return null;
		}
		var type = $('#add_type').val();
		if(!type){
			alert("类型不能为空");
			return null;
		}
		var invlid = '1';
		if($('#add_invlid').prop('checked')){
			invlid='0';
		}
		var line = $('#add_line').attr('columnid');
		if(!line){
			alert("对应行不能为空");
			return null;
		}
		var units = $('#add_units').val();
		var formula = $('#add_formula').val();
		var vgroups = $('#add_vgroups').val();
		if(!vgroups){
			alert("值分组不能为空");
			return null;
		}
		var vtype = $('#add_vtype').val();
		if(!vtype){
			alert("值类型不能为空");
			return null;
		}
		var vdays = $('#add_vdays').val();
		if(!vdays){
			alert("值天数不能为空");
			return null;
		}
		var remark = $('#add_remark').val();
		var param = {
				code:code,
				ename:ename,
				line:line,
				units:units,
				name:name,
				type:type,
				invlid:invlid,
				formula:formula,
				vgroups:vgroups,
				vtype:vtype,
				vdays:vdays,
				remark:remark,
				user:userid
		};
		return param;
	}
//	获取修改数据
	function getEditData(){
		var id = $('#edit_id').val();
		var code = $('#edit_code').val();
		var ename = $('#edit_ename').val();
		if(!ename){
			alert("英文代号不能为空!");
			return null;
		};
		var name = $('#edit_name').val();
		if(!name){
			alert("名称不能为空!");
			return null;
		}
		var type = $('#edit_type').val();
		if(!type){
			alert("类型不能为空");
			return null;
		}
		var invlid = '1';
		if($('#edit_invlid').prop('checked')){
			invlid='0';
		}
		var line = $('#edit_line').attr('columnid');
		if(!line){
			alert("对应行不能为空");
			return null;
		}
		var units = $('#edit_units').val();
		var formula = $('#edit_formula').val();
		var vgroups = $('#edit_vgroups').val();
		if(!vgroups){
			alert("值分组不能为空");
			return null;
		}
		var vtype = $('#edit_vtype').val();
		if(!vtype){
			alert("值类型不能为空");
			return null;
		}
		var vdays = $('#edit_vdays').val();
		if(!vdays){
			alert("值天数不能为空");
			return null;
		}
		var remark = $('#edit_remark').val();
		var param = {
				id:id,
				code:code,
				ename:ename,
				line:line,
				units:units,
				name:name,
				type:type,
				invlid:invlid,
				formula:formula,
				vgroups:vgroups,
				vtype:vtype,
				vdays:vdays,
				remark:remark,
				user:userid
		};
		return param;
	}
	//对比两个对象 如果相同则返回true
	function contrast(obj1,obj2){
		if(obj1.ename==obj2.ename &&
				obj1.remark==obj2.remark &&
				obj1.name==obj2.name &&
				obj1.type==obj2.type &&
				obj1.invlid==obj2.invlid &&
				obj1.units==obj2.units &&
				obj1.line==obj2.line &&
				obj1.formula==obj2.formula &&
				obj1.vgroups==obj2.vgroups &&
				obj1.vdays==obj2.vdays &&
				obj1.vtype==obj2.vtype
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




//	页面跳转
	function jumpPageAction(){
		//获取总页数
		var maxpageno = $('#jumpPageNo').attr('maxpageno');
		//获取当前页
		var pageno = $('#jumpPageNo').val();
		//判断跳转值是否在符合规范
		if(!pageno || !$.isNumeric(pageno) || pageno<=0 || pageno>maxpageno){
			alert('输入的数字必须在1~'+maxpageno+'之间');
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
		var condition = $('#seek_condition').val();
		var namelike = '';
		var codelike = '';
		var key = $('#seek_key').val();
		if('name'==condition){
			namelike = key;
		}else{
			codelike = key;
		}
		
		var type = $('#seek_type').val();
		var invlid = "1";
		if($('#seek_invlid').prop('checked')){
			invlid = "0";
		}

		var params = {
				pageSize:pageSize,
				codelike:codelike,
				namelike:namelike,
				type:type,
				invlid:invlid
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
				}
				//关闭缓冲图标
				layer.close(index);
			}else{
				alert(result.error);
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
		var VTYPE = {
				0:'单值',
				1:'平均',
				2:'其他'
		};
		//加载时清空列表和跳转值
		$('#jumpPageNo').val('');
		//清空列表
		var tbody = $('#list').empty();
		for(var i=0;i<list.length;i++){
			var obj = list[i];
			var invlid = obj.invlid;
			var tr = '<tr>'
				+'<td>'+((pageNo-1)*pageSize+i+1)+'</td>'
				+'<td class="colorred">'+(obj.code || '')+'</td>'
				+'<td>'+(obj.name || '')+'</td>'
				+'<td>'+(obj.ename || '')+'</td>'
				+'<td>'+(obj.units || '')+'</td>'
				+'<td>'+(obj.lineval || '')+'</td>'
				+'<td class="colorblue">'+(TYPE[obj.type] || '')+'</td>'
				+'<td>'+(obj.formula || '')+'</td>'
				+'<td>'+(obj.vgroups || '')+'</td>'
				+'<td class="colorblue">'+(VTYPE[obj.vtype] || '')+'</td>'
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


});