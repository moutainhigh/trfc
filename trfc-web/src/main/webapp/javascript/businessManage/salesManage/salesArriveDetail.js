	//请求路径
	var URL = {
			mainUrl:"/trfc/salesArrive/main",
	};
var PAGE;
if(PAGE){
	PAGE = null;
}
PAGE = {};
PAGE.mod = {
	main:{},
	util:{}
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
				window.location.reload();
			});
			$('#readCardBtn').off('click').on('click',function(){
				//读卡
				layer.msg('待开发.');
			});
			$('#backBtn').off('click').on('click',function(){
				window.location.href = URL.mainUrl;
			});
		}
}
$(function(){
	PAGE.mod.main.init();
});