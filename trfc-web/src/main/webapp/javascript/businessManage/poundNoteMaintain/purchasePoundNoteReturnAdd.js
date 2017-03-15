;(function($, win){
	var URL = {
			purchasePoundNoteMain: '/trfc/poundNote/purchase/main',
			returnAddPoundNote: '/trfc/poundNote/purchase/returnAddPoundNote',
			vehicleAutoCompleteSearch: "/trfc/vehicle/autoCompleteSearch",
			driverAutoCompleteSearch: "/trfc/driver/autoCompleteSearch",
			warehouseAutoCompleteSearch: "/trfc/warehouse/autoCompleteSearch"
	};
	//日期字符串转为时间戳
	function str2Long(dateStr){
		var time = '';
		if(dateStr){
			var date = new Date(dateStr);
			time = date.getTime();
		}
		return time;
	}
	init();
	function init(){
		//初始化autocomplete
		initAutoComplete();
		//初始化按钮事件
		initBindEvent();
	}
	function initAutoComplete(){
		var cache = {};
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
	    		$('#rfid').val(ui.item.rfid);
	    		return false;
    		}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input propertychange',function(){
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
	    }).on('input propertychange',function(){
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
	    }).on('input propertychange',function(){
	    	$(this).removeAttr('warehouseid');
	    }).change(function(){
	    	if(!$(this).attr('warehouseid')){
	    		$(this).val('');
	    	}
	    });
	}
	//绑定按钮事件
	function initBindEvent(){
		//刷新
		$('#refreshBtn').off('click').on('click',function(){
			window.location.reload();
		});
		//保存
		$('#addBtn').off('click').on('click',function(){
			if(!$(this).hasClass('disabled')){
				$(this).addClass('disabled');
				addPurchasePoundNote();
			}
		});
		/**
		 * 自动计算净重
		 */
		$('#grossweight').off('input propertychange').on('input propertychange',function(){
			var grossweight = $(this).val();
			if(!grossweight || !$.isNumeric(grossweight)){
				layer.tips('必须为数字，且不能为空！', this, {
					  tips: [1, '#3595CC'],
					  time: 2000
					});
				$(this).val('');
				$('#netweight').val('');
			}else{
				var tareweight = $('#tareweight').val();
				if(tareweight && $.isNumeric(tareweight)){
					$('#netweight').val(grossweight-tareweight);
				}
			}
		});
		$('#tareweight').off('input propertychange').on('input propertychange',function(){
			var tareweight = $(this).val();
			if(!tareweight || !$.isNumeric(tareweight)){
				layer.tips('必须为数字，且不能为空！', this, {
					  tips: [1, '#3595CC'],
					  time: 2000
					});
				$(this).val('');
				$('#netweight').val('');
			}else{
				var grossweight = $('#grossweight').val();
				if(grossweight && $.isNumeric(grossweight)){
					$('#netweight').val(grossweight-tareweight);
				}
			}
		});
	}
	//日期字符串转为时间戳
	function str2Long(dateStr){
		var time = '';
		if(dateStr){
			var date = new Date(dateStr);
			time = date.getTime();
		}
		return time;
	}
	//获取采购计量参数
	function getPurchasePoundNoteParams(){
		var id = $('#poundNoteId').val();
		var grossweight = $('#grossweight').val();
		var tareweight = $('#tareweight').val();
		var netweight = $('#netweight').val();
		var vehicleid = $('#vehicle').attr('vehicleid');
		var driverid = $('#driver').attr('driverid');
		var warehouseid = $('#warehouse').attr('warehouseid');
		var weighttime = $('#weighttime').val();
		var lighttime = $('#lighttime').val();
		var makebilltime = $('#makebilltime').val();
		return {
			id: id,
			grossweight: grossweight,
			tareweight: tareweight,
			netweight: netweight,
			vehicleid: vehicleid,
			driverid: driverid,
			warehouseid: warehouseid,
			weighttime: str2Long(weighttime),
			lighttime: str2Long(lighttime),
			makebilltime: str2Long(makebilltime)
		}
	}
	//校验参数是否合法
	function validate(params){
		if(!params.grossweight || !$.isNumeric(params.grossweight)){
			layer.msg('毛重不能为空且必须为数字！', {icon: 5});return false;
		}
		if(!params.tareweight || !$.isNumeric(params.tareweight)){
			layer.msg('皮重不能为空且必须为数字！', {icon: 5});return false;
		}
		if(!params.vehicleid){
			layer.msg('车号不能为空！', {icon: 5});return false;
		}
		if(!params.weighttime){
			layer.msg('重车时间不能为空！', {icon: 5});return false;
		}
		if(!params.lighttime){
			layer.msg('轻车时间不能为空！', {icon: 5});return false;
		}
		if(!params.makebilltime){
			layer.msg('制单日期不能为空！', {icon: 5});return false;
		}
		return params;
	}
	//新增采购计量
	function addPurchasePoundNote(){
		var params = getPurchasePoundNoteParams();
		if(validate(params)){
			$.ajax({
				url:URL.returnAddPoundNote,
				data:params,
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						window.location.href = URL.purchasePoundNoteMain;
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
	
})(jQuery, window);