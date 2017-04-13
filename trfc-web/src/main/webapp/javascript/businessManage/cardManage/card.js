;(function($, win){
	//请求路径
	var URL = {
			addCodeUrl:"/trfc/card/addCard",
			addBtnUrl:"/trfc/common/code/driverCode",
			pageUrl:"/trfc/card/page",
			updateCodeUrl:"/trfc/card/updateCard",
			findOneUrl:"/trfc/card/findOne",
			deleteCodeUrl:"/trfc/card/delCard",
			cardCodeUrl:"/trfc/common/code/cardCode"
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
		$('#update_reset').off('click').on('click',function(){
			if($('#updateCardView').is(':visible')){
				var index = layer.load(2, {
				  shade: [0.3,'#fff'] //0.1透明度的白色背景
				});
				$.ajax({
					url:URL.findOneUrl,
					data:{
						id:$('#update_id').val()
					},
					async:true,
					cache:false,
					dataType:'json',
					type:'post',
					success:function(result){
						if(result.code == '000000'){
							if(result.data){
								setUpdateValue(result.data);
							}else{
								layer.msg('刷新失败.', {icon: 5});
							}
						}else{
							layer.msg(result.error, {icon: 5});
						}
						layer.close(index);
					}
				});
			}
		});
		$('#update_save').off('click').on('click',function(){
			if($('#updateCardView').is(':visible')){
				var id = $('#update_id').val();
				var cardcode = $('#update_cardcode').val() || '';
				var cardtype = $('#update_cardtype').val() || '';
				var cardstatus = 0;
				if($('#update_cardstatus')[0].checked){
					cardstatus = 1;
				}
				var remarks = $('#update_remarks').val() || '';
				$.ajax({
					url:URL.updateCodeUrl,
					data:{
						id:id,
						cardcode:cardcode,
						cardtype:cardtype,
						cardstatus:cardstatus,
						remarks:remarks
					},
					async:true,
					cache:false,
					dataType:'json',
					type:'post',
					success:function(result){
						if(result.code == '000000'){
							$('#updateCardView').modal('hide');
							$('#refreshBtn').trigger('click');
						}else{
							layer.msg(result.error, {icon: 5});
						}
					}
				});
			}
		});
		$('#update_read').off('click').on('click',function(){
			if($('#updateCardView').is(':visible')){
				if(initCardReader()) {
					//打开读卡器
					readerOpen();
					//开打卡片获取卡号
					var cardno = openCard();
					if(cardno){
						$('#update_cardno').val(cardno);
						$('#update_cardcode').val('');
						//蜂鸣
						readerBeep();
					}
					//关闭读卡器
					readerClose();
				}else{
					layer.msg('当前游览器不支持!(只兼容IE游览器)');
				}
			}
		});
		$('#deteleCardBtn').off('click').on('click',function(){
			if($('#delCardView').is(':visible')){
				var id = $(this).attr('cardid') || '';
				$.ajax({
					url:URL.deleteCodeUrl,
					data:{
						id:$.trim(id)
					},
					async:true,
					cache:false,
					dataType:'json',
					type:'post',
					success:function(result){
						if(result.code == '000000'){
							$('#delCardView').modal('hide');
							$('#refreshBtn').trigger('click');
						}else{
							layer.msg(result.error, {icon: 5});
						}
					}
				});
			}
		});
		$('#addBtn').off('click').on('click',function(){
			//初始化数值
			setAddValue();
			$('#addCardView').modal();
		});
		$('#add_reset').off('click').on('click',function(){
			if($('#addCardView').is(':visible')){
				setAddValue();
			}
		});
		$('#add_save').off('click').on('click',function(){
			if($('#addCardView').is(':visible')){
				var code = $('#add_code').val() || ''; code = $.trim(code)
				var cardno = $('#add_cardno').val() || ''; cardno = $.trim(cardno)
				var cardcode = $('#add_cardcode').val() || ''; cardcode = $.trim(cardcode)
				var registrar = $('#add_registrar').val() || ''; registrar = $.trim(registrar)
				var createtime = $('#add_createtime').attr('timestamp') || ''; createtime = $.trim(createtime)
				var cardtype = $('#add_cardtype').val() || ''; cardtype = $.trim(cardtype)
				var cardstatus = 0;
				if($('#add_cardstatus')[0].checked){
					cardstatus = 1;
				}
				var remarks = $('#add_remarks').val() || ''; remarks = $.trim(remarks)
				if(!code){
					layer.msg('单据编号不能为空值 .', {icon: 5});return;
				}
				if(!cardno){
					layer.msg('IC卡序号不能为空值 .', {icon: 5});return;
				}
				if(!cardcode){
					layer.msg('卡面编号不能为空值 .', {icon: 5});return;
				}
				if(!registrar){
					layer.msg('登记人不能为空值 .', {icon: 5});return;
				}
				if(!createtime){
					layer.msg('注册时间不能为空值 .', {icon: 5});return;
				}
				if(!cardtype){
					layer.msg('卡类型不能为空值 .', {icon: 5});return;
				}
				$.ajax({
					url:URL.addCodeUrl,
					data:{
						code:code,
						cardno:cardno,
						cardcode:cardcode,
						registrar:registrar,
						createtime:createtime,
						cardtype:cardtype,
						cardstatus:cardstatus,
						remarks:remarks
					},
					async:true,
					cache:false,
					dataType:'json',
					type:'post',
					success:function(result){
						if(result.code == '000000'){
							$('#delCardView').modal('hide');
							win.location.reload(true);
						}else{
							layer.msg(result.error, {icon: 5});
						}
					}
				});
			}
		});
		$('#add_read').off('click').on('click',function(){
			if($('#addCardView').is(':visible')){
				if(initCardReader()) {
					//打开读卡器
					readerOpen();
					//开打卡片获取卡号
					var cardno = openCard();
					if(cardno){
						$('#add_cardno').val(cardno);
						//蜂鸣
						readerBeep();
					}
					//关闭读卡器
					readerClose();
				}else{
					layer.msg('当前游览器不支持!(只兼容IE游览器)');
				}
			}
		});
	}
	
	function getParams(){
		var cardcode = $('#cardcode').val();cardcode = $.trim(cardcode);
		var cardstatus = $('#cardstatus').val();cardstatus = $.trim(cardstatus);
		var cardtype = $('#cardtype').val();cardtype = $.trim(cardtype);
		var pageSize = $('#pageSize').val();pageSize = $.trim(pageSize);
		return {
			cardcode:cardcode,
			cardstatus:cardstatus,
			cardtype:cardtype,
			pageSize:pageSize
		};
	}
	
	function pageCallback(pageNo){
		queryData(pageNo+1);
	}
	
	function queryData(pageNo){
		layer.closeAll();
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
					layer.msg(result.error,{icon:5});
				}
				layer.close(index);
			}
		});
	}
	
	function renderHtml(data){
		$('#dataBody').empty();
		var list = data.list;
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				var obj = list[i] || '';
				var code = obj.code || '';
				var cardno = obj.cardno || '';
				var cardcode = obj.cardcode || '';
				var cardstatus = obj.cardstatus || '';
				switch (cardstatus) {
				case '0':
					cardstatus = '无效';
					break;
				case '1':
					cardstatus = '有效';
					break;
				default:
					cardstatus = '';
					break;
				}
				var cardtype = obj.cardtype || '';
				switch (cardtype) {
				case '0':
					cardtype = 'IC采样卡';
					break;
				case '1':
					cardtype = 'IC过磅卡';
					break;
				default:
					cardtype = '';
					break;
				}
				var registrar = obj.registrar || '';
				var createtimeStr = obj.createtimeStr || '';
				var remarks = obj.remarks || '';
				$('<tr>').append('<td>'+(i+1)+'</td>')
						.append('<td>'+code+'</td>')
						.append('<td>'+cardno+'</td>')
						.append('<td>'+cardcode+'</td>')
						.append('<td>'+cardstatus+'</td>')
						.append('<td>'+cardtype+'</td>')
						.append('<td>'+createtimeStr+'</td>')
						.append('<td>'+registrar+'</td>')
						.append('<td>'+remarks+'</td>')
						.append('<td><span ><a class="updateCard"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a></span>'
				                +'<span ><a class="delCard"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a></span></td>')
						.data(obj)
						.appendTo('#dataBody');
			}
			$('#dataBody tr a.updateCard').off('click').on('click',function(){
				var obj = $(this).closest('tr').data();
				setUpdateValue(obj);
				$('#updateCardView').modal();
			});
			$('#dataBody tr a.delCard').off('click').on('click',function(){
				var obj = $(this).closest('tr').data();
				$('#deteleCardBtn').attr('cardid',obj.id);
				$('#delCardView').modal();
			});
		}else{
			layer.msg('暂无数据');
		}
	}
	
	function setUpdateValue(obj){
		var id = obj.id || '';
		var code = obj.code || '';
		var cardno = obj.cardno || '';
		var cardcode = obj.cardcode || '';
		var registrar = obj.registrar || '';
		var createtimeStr = obj.createtimeStr || '';
		var cardtype = obj.cardtype || '';
		var cardstatus = obj.cardstatus || '';
		var remarks = obj.remarks || '';
		$('#update_id').val(id);
		$('#update_code').val(code);
		$('#update_cardno').val(cardno);
		$('#update_cardcode').val(cardcode);
		$('#update_registrar').val(registrar);
		$('#update_createtime').val(createtimeStr);
		$('#update_cardtype').val(cardtype);
		if(cardstatus == '0'){
			$('#update_cardstatus')[0].checked = false;
		}else{
			$('#update_cardstatus')[0].checked = true;
		}
		$('#update_remarks').val(remarks);
	}
	
	function setAddValue(){
		$.ajax({
			url:URL.cardCodeUrl,
			data:{},
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					$('#add_code').val(result.data.code);
					var date = new Date(result.data.nowtime);
					$('#add_createtime').attr('timestamp',result.data.nowtime).val(date.format('yyyy-MM-dd HH:mm:ss'));
				}else{
					layer.msg(result.error, {icon: 5});
				}
			}
		});
	}
	
})(jQuery, window);