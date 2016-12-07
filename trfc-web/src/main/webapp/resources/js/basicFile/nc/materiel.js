;(function($, win){
	init();
	function init(){
		bindEvent();
		queryData();
	}
	function bindEvent(){
		$('#refreshMater').off('click').on('click',function(){
			queryData();
		});
		$('#searchMater').off('click').on('click',function(){
			queryData();
		});
		$('#updateMarter').off('click').on('click',function(){
			if($('#editMater').is(':visible')){
				var id = $('#materId').val();
				var name = $('#name').val();
				var abbrname = $('#abbrname').val();
				var pinyincode = $('#pinyincode').val();
				var effective = '0';
				if($('#effective')[0].checked){
					effective = '1';
				}
				var remarks = $('#remarks').val();
				var businesstype = $('#businesstype').val();
				var packagetype = $('#packagetype').val();
				var queuingprefix = $('#queuingprefix').val();
				var receiptstatus = '0';
				if($('#receiptstatus')[0].checked){
					receiptstatus = '1'
				}
				var factorycode = '0';
				if($('#factorycode')[0].checked){
					factorycode = '1';
				}
				var bulkwritecard = '0';
				if($('#bulkwritecard')[0].checked){
					bulkwritecard = '1';
				}
				$.ajax({
					url:'/materiel/updateMater',
					data:{
						id:id,
						name:name,
						abbrname:abbrname,
						pinyincode:pinyincode,
						effective:effective,
						remarks:remarks,
						businesstype:businesstype,
						packagetype:packagetype,
						queuingprefix:queuingprefix,
						receiptstatus:receiptstatus,
						factorycode:factorycode,
						bulkwritecard:bulkwritecard
					},
					async:true,
					cache:false,
					dataType:'json',
					type:'post',
					success:function(result){
						if(result.code == '000000'){
							win.location.reload();
						}else{
							alert(result.error);
						}
					}
				});
			}
		});
	}
	function getParams(){
		var params = {};
		var qtp = $('#qtp').val();qtp = $.trim(qtp);
		var keyword = $('#keyword').val();keyword = $.trim(keyword);
		var orgid = $('#orgid').val();orgid = $.trim(orgid);
		var businesstype = $('#type').val();businesstype = $.trim(businesstype);
		if(qtp == 'mc'){
			params.name = keyword;
		}
		if(qtp == 'nm'){
			params.internalcode = keyword;
		}
		if(qtp == 'py'){
			params.pinyincode = keyword;
		}
		params.orgid = orgid;
		params.businesstype = businesstype;
		return params;
	}
	
	function queryData(){
		var params = getParams();
		$.ajax({
			url:'/materiel/page',
			data:params,
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					renderHtml(result.data);
				}else{
					alert(result.error);
				}
			}
		});
	}
	
	function renderHtml(data){
		$('#materBody').empty();
		var list = data.list;
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				var obj = list[i] || '';
				var internalcode = obj.internalcode || '';
				var name = obj.name || '';
				var spec = obj.spec || '';
				var model = obj.model || '';
				var pinyincode = obj.pinyincode || '';
				var receiptstatus = obj.receiptstatus || '';
				var factorycode = obj.factorycode || '';
				var packagetype = obj.packagetype || '';
				switch (packagetype) {
				case '0':
					packagetype = '袋装';
					break;
				case '1':
					packagetype = '水泥散装';
					break;
				case '0':
					packagetype = '其他散装';
					break;
				default:
					packagetype = '';
					break;
				}
				var businesstype = obj.businesstype || '';
				switch (businesstype) {
				case '0':
					businesstype = '采购';
					break;
				case '1':
					businesstype = '销售';
					break;
				case '2':
					businesstype = '共有';
					break;
				default:
					businesstype = '';
					break;
				}
				var effective = obj.effective || '';
				var bulkwritecard = obj.bulkwritecard || '';
				var orgid = obj.orgid || '';
				var remarks = obj.remarks || '';
				$('<tr>').append('<td>'+(i+1)+'</td>')
						.append('<td>'+internalcode+'</td>')
						.append('<td>'+name+'</td>')
						.append('<td>'+spec+'</td>')
						.append('<td>'+model+'</td>')
						.append('<td>'+pinyincode+'</td>')
						.append('<td><input type="checkbox" '+(receiptstatus == '0' ? '' : 'checked')+' disabled></td>')
						.append('<td><input type="checkbox" '+(factorycode == '0' ? '' : 'checked')+' disabled></td>')
						.append('<td>'+packagetype+'</td>')
						.append('<td>'+businesstype+'</td>')
						.append('<td><input type="checkbox" '+(effective == '0' ? '' : 'checked')+' disabled></td>')
						.append('<td><input type="checkbox" '+(bulkwritecard == '0' ? '' : 'checked')+' disabled></td>')
						.append('<td>'+orgid+'</td>')
						.append('<td>'+remarks+'</td>')
						.append('<td><span><a class="editMater"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a></span></td>')
						.data(obj)
						.appendTo('#materBody');
			}
			$('#materBody .editMater').off('click').on('click',function(){
				var obj = $(this).closest('tr').data();
				$('#materId').val(obj.id);
				$('#code').val(obj.code);
				$('#internalcode').val(obj.internalcode);
				$('#name').val(obj.name);
				$('#abbrname').val(obj.abbrname);
				$('#pinyincode').val(obj.pinyincode);
				if(obj.effective == '1'){
					$('#effective')[0].checked = true;
				}else{
					$('#effective')[0].checked = false;
				}
				$('#remarks').val(obj.remarks);
				$('#businesstype').val(obj.businesstype);
				$('#packagetype').val(obj.packagetype);
				$('#queuingprefix').val(obj.queuingprefix);
				if(obj.receiptstatus == '1'){
					$('#receiptstatus')[0].checked = true;
				}else{
					$('#receiptstatus')[0].checked = false;
				}
				if(obj.factorycode == '1'){
					$('#factorycode')[0].checked = true;
				}else{
					$('#factorycode')[0].checked = false;
				}
				if(obj.bulkwritecard == '1'){
					$('#bulkwritecard')[0].checked = true;
				}else{
					$('#bulkwritecard')[0].checked = false;
				}
				$('#editMater').modal();
			});
		}else{
			alert('暂无数据');
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
})(jQuery, window);