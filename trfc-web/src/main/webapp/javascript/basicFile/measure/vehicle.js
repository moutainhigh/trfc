;(function($, win){
	init();
	function init(){
		bindEvent();
		queryData(1);
	}
	function bindEvent(){
		$('#refreshBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#searchBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#updateBtn').off('click').on('click',function(){
			if($('#editView').is(':visible')){
				var id = $('#vehicleid').val();id = $.trim(id);
				var transporttype = $('#_transporttype').val();transporttype = $.trim(transporttype);
				var vehicleno = $('#_vehicleno').val();vehicleno = $.trim(vehicleno);
				var vehicletype = $('#_vehicletype').val();vehicletype = $.trim(vehicletype);
				var transportunit = $('#_transportunit').val();transportunit = $.trim(transportunit);
				var maxweight = $('#_maxweight').val();maxweight = $.trim(maxweight);
				var tareweight = $('#_tareweight').val();tareweight = $.trim(tareweight);
				var ownername = $('#_ownername').val();ownername = $.trim(ownername);
				var telephone = $('#_telephone').val();telephone = $.trim(telephone);
				var address = $('#_address').val();address = $.trim(address);
				var isvalid = '0';
				if($('#_isvalid')[0].checked){
					isvalid = '1';
				}
				var remarks = $('#_remarks').val();remarks = $.trim(remarks);
				operation('/vehicle/editVehicle', {
					id:id,
					transporttype:transporttype,
					vehicleno:vehicleno,
					vehicletype:vehicletype,
					transportunit:transportunit,
					maxweight:maxweight,
					tareweight:tareweight,
					ownername:ownername,
					telephone:telephone,
					address:address,
					isvalid:isvalid,
					remarks:remarks
				})
			}
		});
		$('#addvehicle').off('click').on('click',function(){
			$.ajax({
				url:'/common/code/vehicleCode',
				data:{},
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						$('#add_code').val(result.data);
						$('#addView').modal();
					}else{
						layer.msg(result.error, {icon: 5});
					}
				}
			});
		});
		$('#addBtn').off('click').on('click',function(){
			if($('#addView').is(':visible')){
				var code = $('#add_code').val();code = $.trim(code);
				var transporttype = $('#add_transporttype').val();transporttype = $.trim(transporttype);
				var vehicleno = $('#add_vehicleno').val();vehicleno = $.trim(vehicleno);
				var vehicletype = $('#add_vehicletype').val();vehicletype = $.trim(vehicletype);
				var transportunit = $('#add_transportunit').val();transportunit = $.trim(transportunit);
				var maxweight = $('#add_maxweight').val();maxweight = $.trim(maxweight);
				var tareweight = $('#add_tareweight').val();tareweight = $.trim(tareweight);
				var ownername = $('#add_ownername').val();ownername = $.trim(ownername);
				var telephone = $('#add_telephone').val();telephone = $.trim(telephone);
				var address = $('#add_address').val();address = $.trim(address);
				var orgname = $('#add_orgname').val();orgname = $.trim(orgname);
				var orgid = $('#add_orgid').attr('orgid');orgid = $.trim(orgid);
				var isvalid = '0';
				if($('#_isvalid')[0].checked){
					isvalid = '1';
				}
				var remarks = $('#add_remarks').val();remarks = $.trim(remarks);
				operation('/vehicle/addVehicle', {
					code:code,
					transporttype:transporttype,
					vehicleno:vehicleno,
					vehicletype:vehicletype,
					transportunit:transportunit,
					maxweight:maxweight,
					tareweight:tareweight,
					ownername:ownername,
					telephone:telephone,
					address:address,
					orgname:orgname,
					orgid:orgid,
					isvalid:isvalid,
					remarks:remarks
				})
			}
		});
		$('#delvehicle').off('click').on('click',function(){
			if($('#vehicleBody tr.active').length == 1){
				vConfirm('提示', '注：删除操作不可恢复，您确定要继续么？', function(){
					var id = $('#vehicleBody tr.active').attr('vid'); id = $.trim(id);
					operation('/vehicle/deleteVehicle', {id:id});
				});
			}else{
				layer.msg('必须选中一行才行哦！');
			}
		});
		$('#addblacklist').off('click').on('click',function(){
			if($('#vehicleBody tr.active').length == 1){
				var obj = $('#vehicleBody tr.active').data();
				$('#b_vehicleno').val(obj.vehicleno);
				var date = new Date();
				$('#b_createtime').attr('b_createtime',date.getTime());
				$('#b_createtime').val(date.format('yyyy-MM-dd HH:mm:ss'));
				$('#addBlacklistView').modal();
			}else{
				layer.msg('必须选中一行才行哦！');
			}
		});
		$('#addBlacklistBtn').off('click').on('click',function(){
			var id = $('#vehicleBody tr.active').attr('vid'); id = $.trim(id);
			if($('#addBlacklistView').is(':visible')){
				var vehicleno = $('#b_vehicleno').val();vehicleno = $.trim(vehicleno);
				var remarks = $('#b_remarks').val();remarks = $.trim(remarks);
				var creator = $('#b_creator').val();creator = $.trim(creator);
				var createtime = $('#b_createtime').attr('b_createtime');createtime = $.trim(createtime);
				operation('/vehicle/addblacklist', {
					id:id,
					blackVno:vehicleno,
					blackRemarks:remarks,
					blackCreator:creator,
					blackCreatetime:createtime
				})
			}
		});
		$('#delblacklist').off('click').on('click',function(){
			if($('#vehicleBody tr.active').length == 1){
				vConfirm('提示', '注：确定要从黑名单中删除吗？', function(){
					var id = $('#vehicleBody tr.active').attr('vid'); id = $.trim(id);
					operation('/vehicle/delblacklist', {id:id});
				});
			}else{
				layer.msg('必须选中一行才行哦！');
			}
		});
		$('#jumpPageNoBtn').off('click').on('click',function(){
			var pageNo = $('input#jumpPageNo').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
			var pageMaxNo = $('input#jumpPageNo').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
			if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
				alert('此处必须为1-'+pageMaxNo+'的数字');
				$('input#jumpPageNo').val('');
			}else{
				$('input#jumpPageNo').val(pageNo);
				queryData(pageNo);
			}
		});
		$('#pageSize').change(function(){
			queryData(1);
		});
	}
	function getParams(){
		var params = {};
		var qtp = $('#qtp').val();qtp = $.trim(qtp);
		var keyword = $('#keyword').val();keyword = $.trim(keyword);
		var orgid = $('#orgid').val();orgid = $.trim(orgid);
		var transporttype = $('#transporttype').val();transporttype = $.trim(transporttype);
		var isblacklist = $('#isblacklist').val();isblacklist = $.trim(isblacklist);
		if(qtp == 'mc'){
			params.vehicleno = keyword;
		}
		if(qtp == 'nm'){
			params.internalcode = keyword;
		}
		params.orgid = orgid;
		params.transporttype = transporttype;
		params.isblacklist = isblacklist;
		var pageSize = $('#pageSize').val();pageSize = $.trim(pageSize);
		params.pageSize = pageSize;
		return params;
	}
	
	function pageCallback(pageNo){
		queryData(pageNo+1);
	}
	
	function queryData(pageNo){
		var index = layer.load(2, {
		  shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var params = getParams();
		params.pageNo = pageNo;
		$.ajax({
			url:'/vehicle/page',
			data:params,
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					renderHtml(result.data);
					var total = result.data.total;
					var pageNo = result.data.pageNo;
					var pageSize = result.data.pageSize;
					$('#total').html(total);
					$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
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
				}else{
					layer.msg(result.error, {icon: 5});
				}
				layer.close(index);
			}
		});
	}
	
	function renderHtml(data){
		$('#vehicleBody').empty();
		var list = data.list;
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				var obj = list[i] || '';
				var code = obj.code || '';
				var rfid = obj.rfid || '';
				var vehicleno = obj.vehicleno || '';
				var orgname = obj.orgname || '';
				var transportunit = obj.transportunit || '';
				var isblacklist = obj.isblacklist || '';
				switch (isblacklist) {
				case '0':
					isblacklist = '否';
					break;
				case '1':
					isblacklist = '是';
					break;
				default:
					isblacklist = '';
					break;
				}
				var remarks = obj.remarks || '';
				$('<tr>').append('<td>'+(i+1)+'</td>')
						.append('<td>'+code+'</td>')
						.append('<td>'+rfid+'</td>')
						.append('<td>'+vehicleno+'</td>')
						.append('<td>'+orgname+'</td>')
						.append('<td>'+transportunit+'</td>')
						.append('<td '+(obj.isblacklist=='1'?'style="color:red;"':'')+'>'+isblacklist+'</td>')
						.append('<td>'+remarks+'</td>')
						.append('<td><span><a class="editVehicle"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a></span></td>')
						.attr('vid',obj.id)
						.data(obj)
						.appendTo('#vehicleBody');
			}
			$('#vehicleBody tr').off('click').on('click',function(){
				$(this).addClass('active').siblings().removeClass('active');
			});
			$('#vehicleBody .editVehicle').off('click').on('click',function(){
				var obj = $(this).closest('tr').data();
				$('#vehicleid').val(obj.id);
				$('#_code').val(obj.code);
				$('#_transporttype').val(obj.transporttype);
				$('#_vehicleno').val(obj.vehicleno);
				$('#_vehicletype').val(obj.vehicletype);
				$('#_transportunit').val(obj.transportunit);
				$('#_maxweight').val(obj.maxweight);
				$('#_tareweight').val(obj.tareweight);
				$('#_ownername').val(obj.ownername);
				$('#_telephone').val(obj.telephone);
				$('#_address').val(obj.address);
				$('#_orgname').val(obj.orgname);
				if(obj.isvalid == '1'){
					$('#_isvalid')[0].checked = true;
				}else{
					$('#_isvalid')[0].checked = false;
				}
				$('#_remarks').val(obj.remarks);
				$('#editView').modal();
			});
		}else{
			layer.msg('暂无数据...');
		}
	}
	function vConfirm(title, content, callback){
		$('#modal-title').html(title);
		$('#modal-content').html(content);
		if(callback){
			$('#deleteBtn').off('cilck').on('click',function(){
				callback();
			});
		}
		$('#delView').modal();
	}
	function operation(url, params){
		$.ajax({
			url:url,
			data:params,
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					win.location.reload();
				}else{
					layer.msg(result.error, {icon: 5});
				}
			}
		});
	}
})(jQuery, window);