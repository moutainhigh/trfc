;(function($, win){
	//请求路径
	var URL = {
			addViewUrl:"/trfc/vehicle/addView",
			addUrl:"/trfc/vehicle/add",
			pageUrl:"/trfc/vehicle/page",
			updateUrl:"/trfc/vehicle/update",
			addblacklistViewUrl:"/trfc/vehicle/addblacklistView",
			addblacklistUrl:"/trfc/vehicle/addblacklist",
			delblacklistUrl:"/trfc/vehicle/delblacklist",
			deleteUrl:"/trfc/vehicle/delete",
	};
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
			var _up_this = this;
			if($('#editView').is(':visible')){
				_up_this.disabled = true;
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
				operation(URL.updateUrl, {
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
				},function(){
					_up_this.disabled = false;
				});
			}
		});
		$('#addvehicle').off('click').on('click',function(){
			$.ajax({
				url:URL.addViewUrl,
				data:{},
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						$('#add_code').val(result.data.code);
						$('#addView').modal();
					}else{
						layer.msg(result.error, {icon: 5});
					}
				}
			});
		});
		$('#addBtn').off('click').on('click',function(){
			var _add_this = this;
			if($('#addView').is(':visible')){
				_add_this.disabled = true;
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
				operation(URL.addUrl, {
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
				},function(){
					_add_this.disabled = false;
				});
			}
		});
		$('#addBlacklistBtn').off('click').on('click',function(){
			var _add_this = this;
			var id = $('#b_vehicleid').val(); id = $.trim(id);
			if($('#addBlacklistView').is(':visible')){
				_add_this.disabled = true;
				var vehicleno = $('#b_vehicleno').val();vehicleno = $.trim(vehicleno);
				var remarks = $('#b_remarks').val();remarks = $.trim(remarks);
				var createtime = $('#b_createtime').attr('b_createtime');createtime = $.trim(createtime);
				operation(URL.addblacklistUrl, {
					id:id,
					blackVno:vehicleno,
					blackRemarks:remarks,
					blackCreatetime:createtime
				},function(){
					_add_this.disabled = false;
				});
			}
		});
		$('#jumpPageNoBtn').off('click').on('click',function(){
			var pageNo = $('input#jumpPageNo').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
			var pageMaxNo = $('input#jumpPageNo').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
			if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
				layer.msg('此处必须为1-'+pageMaxNo+'的数字');
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
		var orgid = $('#orgid').attr("orgid");orgid = $.trim(orgid);
		var orgname = $('#orgid').val();orgname = $.trim(orgname);
		var transporttype = $('#transporttype').val();transporttype = $.trim(transporttype);
		var isblacklist = $('#isblacklist').val();isblacklist = $.trim(isblacklist);
		if(qtp == 'mc'){
			params.vehicleno = keyword;
		}
		if(qtp == 'nm'){
			params.internalcode = keyword;
		}
		params.orgid = orgid;
		params.orgname = orgname;
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
			url:URL.pageUrl,
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
						.append('<td><span><a class="update"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a></span>'
								+'<span><a class="delete"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a></span>'
								+'<span><a class="black"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="黑名单">&#xe717;</i></a></span>'
								+'<span><a class="white"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="白名单">&#xe637;</i></a></span>'
								+'</td>')
						.attr('vid',obj.id)
						.data(obj)
						.appendTo('#vehicleBody');
			}
			$('#vehicleBody .update').off('click').on('click',function(){
				var obj = $(this).closest('tr').data();
				updateShowView(obj);
			});
			$('#vehicleBody .delete').off('click').on('click',function(){
				var obj = $(this).closest('tr').data();
				deleteVehicle(obj.id);
			});
			$('#vehicleBody .black').off('click').on('click',function(){
				var obj = $(this).closest('tr').data();
				if(obj.isblacklist == '1'){
					layer.msg('该车辆已被添加进黑名单中，不能重复添加！', {icon: 5});
					return;
				}
				addblacklistShowView(obj);
			});
			$('#vehicleBody .white').off('click').on('click',function(){
				var obj = $(this).closest('tr').data();
				if(obj.isblacklist == '0'){
					layer.msg('该车辆没有被列入黑名单，无需移除！', {icon: 5});
					return;
				}
				delblacklist(obj.id);
			});
		}else{
			layer.msg('暂无数据...');
		}
	}
	function updateShowView(obj){
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
	}
	function deleteVehicle(id){
		var bn=layer.open({
			content: '注：删除操作不可恢复，您确定要继续吗？',
			area: '600px',
			closeBtn:1,
			shadeClose:true,
			btn: ['确定', '取消'],
			yes: function(index, layero){
				//按钮【确定】的回调
				//数据存到服务器
				$.ajax({
					url:URL.deleteUrl,
					data:{id: id},
					async:true,
					cache:false,
					dataType:'json',
					type:'post',
					success:function(result){
						if(result.code == '000000'){
							window.location.reload(true);
						}else{
							layer.msg(result.error, {icon: 5});
						}
					}
				});
				layer.close(bn);
			},btn2: function(index, layero){
				//按钮【取消】的回调
			}
			,cancel: function(){
				//右上角关闭回调
			}
		});
	}
	function addblacklistShowView(obj){
		$('#b_vehicleid').val(obj.id);
		$('#b_vehicleno').val(obj.vehicleno);
		$.ajax({
			url:URL.addblacklistViewUrl,
			data:null,
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					var data = result.data;
					if(data){
						$('#b_creator').val(data.b_creatorname).attr('b_creator',data.b_creator);
						$('#b_createtime').val(data.b_createtimeStr).attr('b_createtime',data.b_createtime);
					}
				}else{
					layer.msg(result.error, {icon: 5});
				}
			}
		});
		$('#addBlacklistView').modal();
	}
	function delblacklist(id){
		layer.confirm('注：确定要从黑名单中删除吗？', {
			area: '600px',
			btn: ['确认','取消'] //按钮
		}, function(){
			$.ajax({
				url:URL.delblacklistUrl,
				data:{id: id},
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						window.location.reload(true);
					}else{
						layer.msg(result.error, {icon: 5});
					}
				}
			});
		});
	}
	
	function operation(url, params, successFun){
		$.ajax({
			url:url,
			data:params,
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					win.location.reload(true);
				}else{
					layer.msg(result.error, {icon: 5});
				}
				if(successFun){
					successFun();
				}
			}
		});
	}
})(jQuery, window);