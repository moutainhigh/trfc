$(function(){
	//整合url
	var URL = {
			materialAutoCompleteSearch:"/trfc/materiel/autoCompleteSearch",
			pageUrl:"/trfc/quality/sales/file/certification/page",
			deleteUrl:"/trfc/quality/sales/file/certification/delete",
			updateUrl:"/trfc/quality/sales/file/certification/update",
			saveUrl:"/trfc/quality/sales/file/certification/add",
	};
	//设置一个公共变量,当点击编辑按钮时,将原数据存入该变量中
	var editOD = {};
	//加载列表
	ShowAction(1);
	//加载下拉框
	materialSelect();
	
	//绑定刷新按钮
	$('#fresh').click(function(){ShowAction(1);});
	//绑定新增按钮
	$('#addBtn').click(initAddData);
	//绑定新增界面确定按钮
	$('#add_sure').click(saveAction);
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

	

	//初始化新增数据
	function initAddData(){
		//等待下拉框加载完成后,执行
		
		$('#add_material').val('').removeAttr('materialid');
		$('#add_trademark').val('');
		$('#add_norm').val('');
		$('#add_certificate').val('');
		$('#add_factorysite').val('');
		$('#add_salestel').val('');
		$('#add_invalid').removeAttr('checked')
		$('#add_intro').val('');

	}
	//初始化编辑数据
	function initEditData(){
		//获取数据
		var obj = $(this).closest('tr').data('certification_obj')
		editOD.obj = obj;
		//设置等下拉框数据加载完成后 执行
		
		$('#edit_material').val(obj.materialname).attr('materialid',obj.materialid)
		$('#edit_id').val(obj.id);
		$('#edit_trademark').val(obj.trademark);
		$('#edit_norm').val(obj.norm);
		$('#edit_certificate').val(obj.certificate);
		$('#edit_factorysite').val(obj.factorysite);
		$('#edit_salestel').val(obj.salestel);
		//为checkbox赋值
		$('#edit_invalid')[0].checked=true;
		if('1'==obj.invalid){
			$('#edit_invalid')[0].checked=false;
		}
		$('#edit_intro').val(obj.intro);
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
//	获取新增数据
	function getAddData(){
		var materialid = $('#add_material').attr('materialid');
		if(!materialid){
			layer.msg("物料不能为空!");
			return null;
		};
		var trademark = $('#add_trademark').val();
		if(!trademark){
			layer.msg("商标不能为空!");
			return null;
		}
		var norm = $('#add_norm').val();
		if(!norm){
			layer.msg("执行标准不能为空");
			return null;
		}
		var certificate = $('#add_certificate').val();
		if(!certificate){
			layer.msg("生产许可证号不能为空");
			return null;
		}
		var factorysite = $('#add_factorysite').val();
		var salestel = $('#add_salestel').val();
		
		var invalid = '1';
		if($('#add_invalid').prop('checked')){
			invalid='0';
		}
		var userid = $('.user').attr('userid');
		var intro = $('#add_intro').val();
		var param = {
				materialid:materialid,
				trademark:trademark,
				norm:norm,
				certificate:certificate,
				factorysite:factorysite,
				salestel:salestel,
				invalid:invalid,
				intro:intro,
				user:userid
		};
		return param;
	}
//	获取修改数据
	function getEditData(){
		var id = $('#edit_id').val();
		var materialid = $('#edit_material').attr('materialid');
		if(!materialid){
			layer.msg("物料不能为空!");
			return null;
		};
		var trademark = $('#edit_trademark').val();
		if(!trademark){
			layer.msg("商标不能为空!");
			return null;
		}
		var norm = $('#edit_norm').val();
		if(!norm){
			layer.msg("执行标准不能为空");
			return null;
		}
		var certificate = $('#edit_certificate').val();
		if(!certificate){
			layer.msg("生产许可证号不能为空");
			return null;
		}
		var factorysite = $('#edit_factorysite').val();
		var salestel = $('#edit_salestel').val();
		
		var invalid = '1';
		if($('#edit_invalid').prop('checked')){
			invalid='0';
		}
		var userid = $('.user').attr('userid');
		var intro = $('#edit_intro').val();
		var param = {
				id:id,
				materialid:materialid,
				trademark:trademark,
				norm:norm,
				certificate:certificate,
				factorysite:factorysite,
				salestel:salestel,
				invalid:invalid,
				intro:intro,
				user:userid
		};
		return param;
	}
	//对比两个对象 如果相同则返回true
	function contrast(obj1,obj2){
		if(obj1.materialid==obj2.materialid &&
				obj1.trademark==obj2.trademark &&
				obj1.norm==obj2.norm &&
				obj1.certificate==obj2.certificate &&
				obj1.factorysite==obj2.factorysite &&
				obj1.salestel==obj2.salestel &&
				obj1.invalid==obj2.invalid &&
				obj1.intro==obj2.intro
		){
			return true;
		}
		return false;
	}
	function deleteAction(){
		var id = $(this).closest('tr').data('certification_obj').id;
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
					material[ term ] = data;
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
		}).off('input keydown').on('input keydown',function(){
	    	$(this).removeAttr('materialid');
	    }).change(function(){
    		if(!$(this).attr('materialid')){
    			$(this).val('');
    		}
	    });
	};

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
	function aditAction(){
		console.log(1);
	}


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
		var params = {
				pageSize:pageSize,
				materialid:materialid
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
				layer.msg(result.error,{icon:5});
			}
		});
	}
//	展示列表
	function showPageData(list,pageSize,pageNo){
		//加载时清空列表和跳转值
		$('#jumpPageNo').val('');
		var tbody = $('#list').empty();
		for(var i=0;i<list.length;i++){
			var obj = list[i];
			var invalid = obj.invalid;
			var tr = '<tr>'
				+'<td>'+((pageNo-1)*pageSize+i+1)+'</td>'
				+'<td>'+(obj.materialname || '')+'</td>'
				+'<td>'+(obj.trademark || '')+'</td>'
				+'<td>'+(obj.norm || '')+'</td>'
				+'<td>'+(obj.certificate || '')+'</td>'
				+'<td>'+(obj.factorysite || '')+'</td>'
				+'<td>'+(obj.salestel || '')+'</td>'
				+'<td><input type="checkbox" '+("0"==invalid?'checked="true"':"")+' disabled="true"></td>'
				+'<td>'+(obj.intro || '')+'</td>'
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
			tr.data('certification_obj',obj);
		}
	}


});