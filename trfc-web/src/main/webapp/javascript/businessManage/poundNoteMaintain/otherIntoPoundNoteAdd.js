;(function($){
	var URL = {
			main: '/trfc/poundNote/otherInto/main',
			add: '/trfc/poundNote/otherInto/add',
			supplierAutoCompleteSearch: "/trfc/supplier/autoCompleteSearch",
			materielAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch",
			vehicleAutoCompleteSearch: "/trfc/vehicle/autoCompleteSearch",
			driverAutoCompleteSearch: "/trfc/driver/autoCompleteSearch",
			warehouseAutoCompleteSearch: "/trfc/warehouse/autoCompleteSearch"
	};
	//初始化
	init();
	//初始化方法
	function init(){
		//初始化autocomplete
		initAutoComplete();
		//初始化按钮事件
		initBindEvent();
	}
	//初始化autocomplete
	function initAutoComplete(){
		var cache = {};
	    $("#supplier").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		var supplier = cache['supplier'] || {};
	    		if ( term in supplier ) {
	    			response( supplier[ term ] );
	    			return;
	    		}
	    		$.post( URL.supplierAutoCompleteSearch, request, function( data, status, xhr ) {
	    			supplier[ term ] = data;
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
	    	$(this).autocomplete('search',' ');
	    }).on('input keydown',function(){
	    	$(this).removeAttr('supplierid');
	    }).change(function(){
    		if(!$(this).attr('supplierid')){
    			$(this).val('');
    		}
	    });
	    $("#materiel").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		var materiel = cache['materiel'] || {};
	    		if ( term in materiel ) {
	    			response( materiel[ term ] );
	    			return;
	    		}
	    		$.post( URL.materielAutoCompleteSearch, request, function( data, status, xhr ) {
	    			materiel[ term ] = data;
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
	    		$(this).val(ui.item.name).attr('materielid', ui.item.id);
	    		return false;
	    	}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input keydown',function(){
	    	$(this).removeAttr('materielid');
	    }).change(function(){
    		if(!$(this).attr('materielid')){
    			$(this).val('');
    		}
	    });
	    $("#vehicle").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		var vehicle = cache['vehicle'] || {};
	    		if ( term in vehicle ) {
	    			response( vehicle[ term ] );
	    			return;
	    		}
	    		$.post( URL.vehicleAutoCompleteSearch, request, function( data, status, xhr ) {
	    			vehicle[ term ] = data;
	    			response( data );
	    		});
	    	},
	    	response: function( event, ui ) {
	    		if(ui.content && ui.content.length > 0){
	    			ui.content.forEach(function(x,i,a){
	    				x.label = x.vehicleno;
	    				x.value = x.id;
	    			});
	    		}
	    	},
	    	select: function( event, ui ) {
	    		$(this).val(ui.item.vehicleno).attr('vehicleid', ui.item.id);
	    		return false;
	    	}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input keydown',function(){
	    	$(this).removeAttr('vehicleid');
	    }).change(function(){
    		if(!$(this).attr('vehicleid')){
    			$(this).val('');
    		}
	    });
		$("#driver").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		var driver = cache['driver'] || {};
	    		if ( term in driver ) {
	    			response( driver[ term ] );
	    			return;
	    		}
	    		$.post( URL.driverAutoCompleteSearch, request, function( data, status, xhr ) {
	    			driver[ term ] = data;
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
	    		$(this).val(ui.item.name).attr('driverid', ui.item.id);
	    		$('#driveridentityno').val(ui.item.identityno);
	    		return false;
    		}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input keydown',function(){
	    	$(this).removeAttr('driverid');
	    }).change(function(){
    		if(!$(this).attr('driverid')){
    			$(this).val('');
    		}
	    });
		$("#warehouse").autocomplete({
			source: function( request, response ) {
				var term = request.term;
				var warehouse = cache['warehouse'] || {};
				if ( term in warehouse ) {
					response( warehouse[ term ] );
					return;
				}
				$.post( URL.warehouseAutoCompleteSearch, request, function( data, status, xhr ) {
					warehouse[ term ] = data;
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
				$(this).val(ui.item.name).attr('warehouseid', ui.item.id);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input keydown',function(){
			$(this).removeAttr('warehouseid');
		}).change(function(){
			if(!$(this).attr('warehouseid')){
				$(this).val('');
			}
		});
	}
	//绑定按钮事件
	function initBindEvent(){
		$('#refreshBtn').off('click').on('click', function(){
			window.location.reload();
		});
		$('#addBtn').off('click').on('click', function(){
			if(!$(this).hasClass('disabled')){
				$(this).addClass('disabled');
				addCutover();
			}
		});
		/**
		 * 自动计算净重
		 */
		$('#grossweight').off('keyup').on('keyup',function(){
			var grossweight = $(this).val();
			if(!grossweight || !$.isNumeric(grossweight)){
				layer.tips('必须为数字，且不能为空！', this, {
					  tips: [1, '#3595CC'],
					  time: 2000
					});
				$(this).val('');
				$('#netweight').val('');
			}else{
				layer.closeAll('tips');
				var tareweight = $('#tareweight').val();
				if(tareweight && $.isNumeric(tareweight)){
					var rs =grossweight-tareweight;
					$('#netweight').val(rs.toFixed(2));
				}
			}
		});
		$('#tareweight').off('keyup').on('keyup',function(){
			var tareweight = $(this).val();
			if(!tareweight || !$.isNumeric(tareweight)){
				layer.tips('必须为数字，且不能为空！', this, {
					  tips: [1, '#3595CC'],
					  time: 2000
					});
				$(this).val('');
				$('#netweight').val('');
			}else{
				layer.closeAll('tips');
				var grossweight = $('#grossweight').val();
				if(grossweight && $.isNumeric(grossweight)){
					var rs =grossweight-tareweight;
					$('#netweight').val(rs.toFixed(2));
				}
			}
		});
	}
	//GET新增参数
	function getParams(){
		var supplierid = $('#supplier').attr('supplierid'); supplierid = $.trim(supplierid);
		var department = $('#department').val(); department = $.trim(department);
		var materielid = $('#materiel').attr('materielid'); materielid = $.trim(materielid);
		var cargo = $('#cargo').val(); cargo = $.trim(cargo);
		var grossweight = $('#grossweight').val(); grossweight = $.trim(grossweight);
		var tareweight = $('#tareweight').val(); tareweight = $.trim(tareweight);
		var vehicleid = $('#vehicle').attr('vehicleid'); vehicleid = $.trim(vehicleid);
		var driverid = $('#driver').attr('driverid'); driverid = $.trim(driverid);
		var weighttime = $('#weighttime').val(); weighttime = $.trim(weighttime);
		var lighttime = $('#lighttime').val(); lighttime = $.trim(lighttime);
		var warehouseid = $('#warehouse').attr('warehouseid'); warehouseid = $.trim(warehouseid);
		var makebilltime = $('#makebilltime').val(); makebilltime = $.trim(makebilltime);
		return {
			supplierid: supplierid,
			department: department,
			materialid: materielid,
			cargo: cargo,
			grossweight: grossweight,
			tareweight: tareweight,
			vehicleid: vehicleid,
			driverid: driverid,
			weighttime: Date.parseTime_YMD_HMS(weighttime),
			lighttime: Date.parseTime_YMD_HMS(lighttime),
			warehouseid: warehouseid,
			makebilltime: Date.parseTime_YMD_HMS(makebilltime)
		}
	}
	//校验参数合法性
	function validate(params){
		if(!params.supplierid){
			layer.msg('请选择供应商！', {icon: 5}); return false;
		}
		if(!params.materialid){
			layer.msg('请选择物料！', {icon: 5}); return false;
		}
		if(!params.grossweight){
			layer.msg('请选择毛重！', {icon: 5}); return false;
		}
		if(!params.tareweight){
			layer.msg('请选择皮重！', {icon: 5}); return false;
		}
		if(!params.vehicleid){
			layer.msg('请选择车号！', {icon: 5}); return false;
		}
		if(!params.driverid){
			layer.msg('请选择司机！', {icon: 5}); return false;
		}
		if(!params.weighttime){
			layer.msg('请选择重车日期！', {icon: 5}); return false;
		}
		if(!params.lighttime){
			layer.msg('请选择轻车日期！', {icon: 5}); return false;
		}
		if(!params.makebilltime){
			layer.msg('请选择制单日期！', {icon: 5}); return false;
		}
		return params;
	}
	//新增厂内倒运
	function addCutover(){
		var params = getParams();
		if(validate(params)){
			$.ajax({
				url:URL.add,
				data:params,
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						window.location.href = URL.main;
					}else{
						layer.msg(result.error, {icon: 5});
						$('#addBtn').removeClass('disabled');
					}
				}
			});
		}else{
			$('#addBtn').removeClass('disabled');
		}
	}
})(jQuery);