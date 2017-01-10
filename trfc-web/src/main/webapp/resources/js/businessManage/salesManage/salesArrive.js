var PAGE;
if(PAGE){
	PAGE = null;
}
PAGE = {};
PAGE.mod = {
	main:{},
	util:{}
}
PAGE.mod.main = {
		init:function(){
			this.bindEvent();
			this.getData($.trim($('#jumpPageNo').val()) || 1);
		},
		bindEvent:function(){
			$('#refreshBtn').off('click').on('click',function(){
				queryData(1);
			});
			$('#searchBtn').off('click').on('click',function(){
				queryData(1);
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
		},
		getParams:function(){
			
		},
		getData:function(pageNo){
			
		},
		renderHtml:function(){
			
		}
}
$(function(){
	PAGE.mod.main.init();
});