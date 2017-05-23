;(function($){
	var URL = {
			main: '/trfc/poundNote/cutover/main',
			add: '/trfc/poundNote/cutover/add',
			yardAutoCompleteSearch: "/trfc/yard/autoCompleteSearch",
			vehicleAutoCompleteSearch: "/trfc/vehicle/autoCompleteSearch",
			driverAutoCompleteSearch: "/trfc/driver/autoCompleteSearch",
			materielAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch"
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
	    $("#leaveyardid, #enteryardid").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		var yard = cache['yard'] || {};
	    		if ( term in yard ) {
	    			response( yard[ term ] );
	    			return;
	    		}
	    		$.post( URL.yardAutoCompleteSearch, request, function( data, status, xhr ) {
	    			yard[ term ] = data;
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
	    		$(this).val(ui.item.name).attr('yardid', ui.item.id);
	    		return false;
	    	}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input keydown',function(){
	    	$(this).removeAttr('yardid');
	    }).change(function(){
    		if(!$(this).attr('yardid')){
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
					$('#netweight').val(grossweight-tareweight);
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
					$('#netweight').val(grossweight-tareweight);
				}
			}
		});
	}
	//GET新增参数
	function getParams(){
		var leaveyardid = $('#leaveyardid').attr('yardid'); leaveyardid = $.trim(leaveyardid);
		var enteryardid = $('#enteryardid').attr('yardid'); enteryardid = $.trim(enteryardid);
		var materielid = $('#materiel').attr('materielid'); materielid = $.trim(materielid);
		var vehicleid = $('#vehicle').attr('vehicleid'); vehicleid = $.trim(vehicleid);
		var grossweight = $('#grossweight').val(); grossweight = $.trim(grossweight);
		var tareweight = $('#tareweight').val(); tareweight = $.trim(tareweight);
		var driverid = $('#driver').attr('driverid'); driverid = $.trim(driverid);
		var makebilltime = $('#makebilltime').val(); makebilltime = $.trim(makebilltime);
		var weighttime = $('#weighttime').val(); weighttime = $.trim(weighttime);
		var lighttime = $('#lighttime').val(); lighttime = $.trim(lighttime);
		return {
			leaveyardid: leaveyardid,
			enteryardid: enteryardid,
			materialid: materielid,
			vehicleid: vehicleid,
			grossweight: grossweight,
			tareweight: tareweight,
			driverid: driverid,
			makebilltime: Date.parseTime_YMD_HMS(makebilltime),
			weighttime: Date.parseTime_YMD_HMS(weighttime),
			lighttime: Date.parseTime_YMD_HMS(lighttime)
		}
	}
	//校验参数合法性
	function validate(params){
		if(!params.leaveyardid){
			layer.msg('请选择调出堆场！', {icon: 5}); return false;
		}
		if(!params.enteryardid){
			layer.msg('请选择调入堆场！', {icon: 5}); return false;
		}
		if(!params.materialid){
			layer.msg('请选择物料！', {icon: 5}); return false;
		}
		if(!params.vehicleid){
			layer.msg('请选择车号！', {icon: 5}); return false;
		}
		if(!params.grossweight){
			layer.msg('请选择毛重！', {icon: 5}); return false;
		}
		if(!params.tareweight){
			layer.msg('请选择皮重！', {icon: 5}); return false;
		}
		if(!params.driverid){
			layer.msg('请选择司机！', {icon: 5}); return false;
		}
		if(!params.makebilltime){
			layer.msg('请选择制单日期！', {icon: 5}); return false;
		}
		if(!params.weighttime){
			layer.msg('请选择重车日期！', {icon: 5}); return false;
		}
		if(!params.lighttime){
			layer.msg('请选择轻车日期！', {icon: 5}); return false;
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