;(function($, win){
	var URL = {
			main: '/trfc/poundNote/purchase/main',
			updatePn: '/trfc/poundNote/purchase/updatePn',
			yardAutoCompleteSearch: "/trfc/yard/autoCompleteSearch",
			userAutoCompleteSearch: "/trfc/system/auth/user/autoCompleteSearch"
	};
	init();
	function init(){
		//初始化autocomplete
		initAutoComplete();
		//初始化按钮事件
		initBindEvent();
	}
	function initAutoComplete(){
		var cache = {};
	    $("#yard").autocomplete({
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
	    $("#signPerson").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		var yard = cache['user'] || {};
	    		if ( term in yard ) {
	    			response( yard[ term ] );
	    			return;
	    		}
	    		$.post( URL.userAutoCompleteSearch, request, function( data, status, xhr ) {
	    			yard[ term ] = data.data;
	    			response( data.data );
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
	    		$(this).val(ui.item.name).attr('userId', ui.item.id);
	    		return false;
	    	}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input keydown',function(){
	    	$(this).removeAttr('userId');
	    }).change(function(){
	    	if(!$(this).attr('userId')){
	    		$(this).val('');
	    	}
	    });
	}
	//绑定按钮事件
	function initBindEvent(){
		//刷新
		$('#refresh').off('click').on('click',function(){
			window.location.reload(true);
		});
		//保存
		$('#save').off('click').on('click',function(){
			save(this.dataset.id);
		});
	}
	
	var save = id => {
		var yardid = $('#yard').attr('yardid');
		var signPerson = $('#signPerson').attr('userId');
		$.post(URL.updatePn, {id: id, yardid: yardid, signPerson: signPerson}, function(result) {
			if (result != null) {
				if (result.code == '000000') {
					window.location.href= URL.main;
				} else {
					layer.msg(result.error, {icon: 5});
				}
			} else {
				layer.msg('请求失败，请稍候重试！', {icon: 5});
			}
		});
	}
	
})(jQuery, window);