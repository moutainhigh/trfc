var PAGE;
if(PAGE){
	PAGE = null;
}
PAGE = {};
PAGE.mod = {
	main:{},
	util:{},
	URL: {
		mainUrl:"/trfc/purchaseArrive/main",
	}
}
PAGE.mod.util = {
}
PAGE.mod.main = {
		top: PAGE.mod,
		init:function(){
			this.bindEvent();
		},
		bindEvent:function(){
			var _this = this;
			$('#refreshBtn').off('click').on('click',function(){
				window.location.reload(true);
			});
			$('#readCardBtn').off('click').on('click',function(){
				//读卡
				layer.msg('待开发.');
			});
			$('#backBtn').off('click').on('click',function(){
				window.location.href = _this.top.URL.mainUrl;
			});
		}
}
$(function(){
	PAGE.mod.main.init();
});