$(function(){
	//整合url
	var URL = {
			materialAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch",
			checkUrl:"/trfc/quality/sales/file/MaterialScheme/check",
			pageUrl:"/trfc/quality/sales/file/MaterialScheme/page",
			deleteUrl:"/trfc/quality/sales/file/MaterialScheme/delete",
			updateUrl:"/trfc/quality/sales/file/MaterialScheme/update",
			saveUrl:"/trfc/quality/sales/file/MaterialScheme/add",
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

	//物料品种检测
	function checkMaterialType(type){
		if(!type){
			alert("物料品种不能为空!");
			return false;
		}
		var bl = false;
		//通过同步加载 获取检测结果
		$.ajax({url:URL.checkUrl,
			data:{materialtype:type},
			async:false,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code=='000000'){
					//true为不重复
					if(result.data){
						bl=true;
					}else{
						alert("物料品种已存在!");
					}
				}else{
					layer.msg(result.error,{icon:5});
				}
			}});

		return bl;
	}

	//初始化新增数据
	function initAddData(){
		//等待下拉框加载完成后,执行
		
		$('#add_material').val('').removeAttr('materialid')
		$('#add_materialtype').val('');
		$('#add_strength').val('');
		$('#add_admixture').val('');
		$('#add_admixtureadd').val('');
		$('#add_gypsum').val('');
		$('#add_gypsumadd').val('');
		$('#add_aid').val('');
		$('#add_aidadd').val('');

		$('#add_shows').val('0');
		$('#add_invalid').removeAttr('checked')
		$('#add_verdict').val('');
	}
	//初始化编辑数据
	function initEditData(){
		//获取数据
		var obj = $(this).closest('tr').data('material_obj')
		editOD.obj = obj;
		//设置等下拉框数据加载完成后 执行
		$('#edit_material').val(obj.materialname).attr('materialid',obj.materialid);
		$('#edit_id').val(obj.id);
		$('#edit_materialtype').val(obj.materialtype);
		$('#edit_strength').val(obj.strength);
		$('#edit_admixture').val(obj.admixture);
		$('#edit_admixtureadd').val(obj.admixtureadd);
		$('#edit_gypsum').val(obj.gypsum);
		$('#edit_gypsumadd').val(obj.gypsumadd);
		$('#edit_aid').val(obj.aid);
		$('#edit_aidadd').val(obj.aidadd);

		$('#edit_shows').val(obj.shows);
		//为checkbox赋值
		$('#edit_invalid')[0].checked=true;
		if('1'==obj.invalid){
			$('#edit_invalid')[0].checked=false;
		}
		$('#edit_verdict').val(obj.verdict);
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
			alert("物料不能为空!");
			return null;
		};
		var materialtype = $('#add_materialtype').val();
		if(!checkMaterialType(materialtype)){
			return null;
		}
		var strength = $('#add_strength').val();
		if(!strength){
			alert("强度不能为空");
			return null;
		}
		var admixture = $('#add_admixture').val();
		var admixtureadd = $('#add_admixtureadd').val();
		var gypsum = $('#add_gypsum').val();
		var gypsumadd = $('#add_gypsumadd').val();
		var aid = $('#add_aid').val();
		var aidadd = $('#add_aidadd').val();
		var invalid = '1';
		var shows = $('#add_shows').val();
		if($('#add_invalid').prop('checked')){
			invalid='0';
		}
		var userid = $('.user').attr('userid');
		var verdict = $('#add_verdict').val();
		var param = {
				materialid:materialid,
				materialtype:materialtype,
				strength:strength,
				admixture:admixture,
				admixtureadd:admixtureadd,
				gypsum:gypsum,
				gypsumadd:gypsumadd,
				aid:aid,
				aidadd:aidadd,
				invalid:invalid,
				shows:shows,
				verdict:verdict,
				user:userid
		};
		return param;
	}
//	获取修改数据
	function getEditData(){
		var id = $('#edit_id').val();
		var materialid = $('#edit_material').attr('materialid');
		if(!materialid){
			alert("物料不能为空!");
			return null;
		};
		var materialtype = $('#edit_materialtype').val();
		if(editOD.obj.materialtype!=materialtype && !checkMaterialType(materialtype)){
			return null;
		}
		var strength = $('#edit_strength').val();
		if(!strength){
			alert("强度不能为空");
			return null;
		}
		var admixture = $('#edit_admixture').val();
		var admixtureadd = $('#edit_admixtureadd').val();
		var gypsum = $('#edit_gypsum').val();
		var gypsumadd = $('#edit_gypsumadd').val();
		var aid = $('#edit_aid').val();
		var aidadd = $('#edit_aidadd').val();
		var invalid = '1';
		var shows = $('#edit_shows').val();
		//获取有效 值
		if($('#edit_invalid').prop('checked')){
			invalid='0';
		}
		//获取userid
		var userid = $('.user').attr('userid');
		var verdict = $('#edit_verdict').val();
		var param = {
				id:id,
				materialid:materialid,
				materialtype:materialtype,
				strength:strength,
				admixture:admixture,
				admixtureadd:admixtureadd,
				gypsum:gypsum,
				gypsumadd:gypsumadd,
				aid:aid,
				aidadd:aidadd,
				invalid:invalid,
				shows:shows,
				verdict:verdict,
				user:userid
		};
		return param;
	}
	//对比两个对象 如果相同则返回true
	function contrast(obj1,obj2){
		if(obj1.materialid==obj2.materialid &&
				obj1.materialtype==obj2.materialtype &&
				obj1.strength==obj2.strength &&
				obj1.admixture==obj2.admixture &&
				obj1.admixtureadd==obj2.admixtureadd &&
				obj1.gypsum==obj2.gypsum &&
				obj1.gypsumadd==obj2.gypsumadd &&
				obj1.aid==obj2.aid &&
				obj1.aidadd==obj2.aidadd &&
				obj1.invalid==obj2.invalid &&
				obj1.shows==obj2.shows &&
				obj1.verdict==obj2.verdict
		){
			return true;
		}
		return false;
	}
	function deleteAction(){
		var id = $(this).closest('tr').data('material_obj').id;
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
	function aditAction(){
		console.log(1);
	}
	
	function materialSelect(){
		var cache = {};
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
		}).on('input propertychange',function(){
	    	$(this).removeAttr('materialid');
	    }).change(function(){
    		if(!$(this).attr('materialid')){
    			$(this).val('');
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
		var materialid = $('#seek_material').val();
		var materialtype = $('#seek_materialtype').val();
		var params = {
				materialid:materialid,
				materialtype:materialtype
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
		//加载时清空列表和跳转值
		$('#jumpPageNo').val('');
		var tbody = $('#list').empty();
		for(var i=0;i<list.length;i++){
			var obj = list[i];
			var invalid = obj.invalid;
			var tr = '<tr>'
				+'<td>'+((pageNo-1)*pageSize+i+1)+'</td>'
				+'<td class="colorred">'+(obj.materialname || '')+'</td>'
				+'<td>'+(obj.materialtype || '')+'</td>'
				+'<td>'+(obj.strength || '')+'</td>'
				+'<td>'+(obj.admixture || '')+'</td>'
				+'<td>'+(obj.admixtureadd || '')+'</td>'
				+'<td>'+(obj.gypsum || '')+'</td>'
				+'<td>'+(obj.gypsumadd || '')+'</td>'
				+'<td>'+(obj.aid || '')+'</td>'
				+'<td>'+(obj.aidadd || '')+'</td>'
				+'<td><input type="checkbox" '+("0"==invalid?'checked="true"':"")+' disabled="true"></td>'
				+'<td>'+(obj.verdict || '')+'</td>'
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
			tr.data('material_obj',obj);
		}
	}


});