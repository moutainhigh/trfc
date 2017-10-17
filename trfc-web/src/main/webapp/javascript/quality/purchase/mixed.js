;(function($){
	var URL = {
			page:"/trfc/quality/purchase/mixed/page",
			saveMixed:"/trfc/quality/purchase/assay/add",
			detail:"/trfc/quality/purchase/sampling/getDetailData",
			supplierAutoComplate:"/trfc/quality/purchase/mixed/supplierAutoComplate",
			minemouthAutoComplate:"/trfc/quality/purchase/mixed/minemouthAutoComplate",
			materialAutoComplate:"/trfc/quality/purchase/mixed/materialAutoComplate"
	};
	//初始化
	init();
	//初始化方法
	function init(){
		//初始化autocomplete
		initAutoComplete();
		//初始化按钮事件
		initBindEvent();
		//初始化列表
		getDataFormAjax();
	}
	//初始化autocomplete
	function initAutoComplete(){
	    $("#supplier").autocomplete({
	    	source: function( request, response ) {
	    		$.post( URL.supplierAutoComplate, request, function( data, status, xhr ) {
	    			response( data );
	    		});
	    	},
	    	response: function( event, ui ) {
	    		if(ui.content && ui.content.length > 0){
	    			ui.content.forEach(function(x,i,a){
	    				x.label = x.name;
	    				x.value = x.id;
	    			});
	    		}
	    	},
	    	select: function( event, ui ) {
	    		$(this).val(ui.item.name).attr('supplierid', ui.item.id);
	    		return false;
	    	}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',$('#date').val());
	    }).on('input keydown',function(){
	    	$(this).removeAttr('supplierid');
	    }).change(function(){
    		if(!$(this).attr('supplierid')){
    			$(this).val('');
    		}
	    });
	    $("#material").autocomplete({
	    	source: function( request, response ) {
	    		$.post( URL.materialAutoComplate, request, function( data, status, xhr ) {
	    			response( data );
	    		});
	    	},
	    	response: function( event, ui ) {
	    		if(ui.content && ui.content.length > 0){
	    			ui.content.forEach(function(x,i,a){
	    				x.label = x.name;
	    				x.value = x.id;
	    			});
	    		}
	    	},
	    	select: function( event, ui ) {
	    		$(this).val(ui.item.name).attr('materialid', ui.item.id);
	    		return false;
	    	}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',$('#date').val());
	    }).on('input keydown',function(){
	    	$(this).removeAttr('materialid');
	    }).change(function(){
	    	if(!$(this).attr('materialid')){
	    		$(this).val('');
	    	}
	    });
	    $("#minemouth").autocomplete({
	    	source: function( request, response ) {
	    		$.post( URL.minemouthAutoComplate, request, function( data, status, xhr ) {
	    			response( data );
	    		});
	    	},
	    	response: function( event, ui ) {
	    		if(ui.content && ui.content.length > 0){
	    			ui.content.forEach(function(x,i,a){
	    				x.label = x.name;
	    				x.value = x.id;
	    			});
	    		}
	    	},
	    	select: function( event, ui ) {
	    		$(this).val(ui.item.name).attr('minemouthid', ui.item.id);
	    		return false;
	    	}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',$('#date').val());
	    }).on('input keydown',function(){
	    	$(this).removeAttr('minemouthid');
	    }).change(function(){
	    	if(!$(this).attr('minemouthid')){
	    		$(this).val('');
	    	}
	    });
	}
	
	function initBindEvent() {
		$('#search').off('click').on('click', function() {
			getDataFormAjax();
		});
		$('#refresh').off('click').on('click', function() {
			getDataFormAjax();
		});
		$('#mixed').off('click').on('click', function() {
			mixed();
		});
	}
	function getParams() {
		var date = $('#date').val();
		var supplier = $('#supplier').attr('supplierid');
		var material = $('#material').attr('materialid');
		var minemouth = $('#minemouth').attr('minemouthid');
		return {
			date: date,
			supplier: supplier,
			material: material,
			minemouth: minemouth
		}
	}
	function getDataFormAjax() {
		var index = layer.load(2, {
			  shade: [0.3,'#fff'] //0.1透明度的白色背景
			});
		var params = getParams();
		$.post(URL.page, params, function(result){
			if(result.code == '000000'){
				renderHtml(result.data);
			}else{
				layer.msg(result.error, {icon: 5});
			}
			layer.close(index);
		});
	}
    //展示列表
	function renderHtml(list){
		var tbody = $('#list').empty();
		if (list) {
			for(var i=0;i<list.length;i++){
				var obj = list[i];
				$('<tr>').append('<td><input type="checkbox"/></td>')
						.append('<td>'+(obj.code || '')+'</td>')
						.append('<td>'+new Date(obj.samplingtime).format('yyyy-MM-dd HH:mm:ss')+'</td>')
						.append('<td>'+(obj.assayname || '')+'</td>')
						.append('<td>'+(obj.creator || '')+'</td>')
						.append('<td>'+new Date(obj.createtime).format('yyyy-MM-dd HH:mm:ss')+'</td>')
						.append('<td>'+(obj.remark || '')+'</td>')
						.data('obj',obj).appendTo(tbody);
			}
			tbody.find('tr').off('click').on('click', function(e) {
				e.stopPropagation();
				$(this).find('td:eq(0)>input').trigger('click');
			});
			tbody.find('tr').find('td:eq(0)>input[type="checkbox"]').off('click').on('click',function(e){
				e.stopPropagation();
				if(this.checked == true){
					$(this).closest('tr').addClass('active');
				}else{
					$(this).closest('tr').removeClass('active');
				}
				var id = $(this).closest('tr').data('obj').id;
				loadDetailData(id);
			});
		};
		//加载列表后隐藏详情
		$(".intel_result").hide();
	}
	//加载详情
	function loadDetailData(id) {
		$(".intel_result").show();
		var tbody = $('#detail_list').empty();
		$.post(URL.detail, {id: id}, function(result){
			if('000000'==result.code){
				var list = result.data;
				for(var i=0;i<list.length;i++){
					var obj = list[i];
					var tr = '<tr>'
						+'<td>'+(obj.samplingcode || '')+'</td>'
						+'<td>'+(obj.samplingcar || '')+'</td>'
						+'<td>'+(obj.supplier || '')+'</td>'
						+'<td>'+(obj.material || '')+'</td>'
						+'<td>'+(obj.mine || '')+'</td>'
						+'<td>'+(obj.vehicle || '')+'</td>'
						+'<td>'+(obj.remark || '')+'</td>'
						+'</tr>';
					tr = $(tr);
					tbody.append(tr);
					tr.data('obj',obj);
				}
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	//混样
	function mixed() {
		var trs = $('#list').find('tr.active');
		var samplingid = '';
		if (trs.length > 0) {
			for (var i=0;i<trs.length;i++) {
				var tr = trs[i];
				var data = $(tr).data('obj');
				samplingid += ',' + data.code;
			}
			samplingid = samplingid.slice(1, samplingid.length);
			saveAction({samplingid: samplingid});
		} else {
			alert('请选择采样单！');
		}
	}

	function saveAction(param){
		if(param){
			$.post(URL.saveMixed, param, function(result){
				if('000000'==result.code){
					alert('生成化验单成功！');
				}else{
					layer.msg(result.error,{icon:5});
				}
			});
		}
	}

})(jQuery);