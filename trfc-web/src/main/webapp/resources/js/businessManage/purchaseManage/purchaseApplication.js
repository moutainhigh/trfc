;(function($, win){
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
	}
	
	function str2Long(dateStr){
		var time = '';
		if(dateStr){
			var date = new Date(dateStr);
			time = date.getTime();
		}
		return time;
	}
	
	function getParams(){
		var params = {};
		var billno = $('#billno').val();billno = $.trim(billno);
		var supplierid = $('#supplierid').val();supplierid = $.trim(supplierid);
		var source = $('#source').val();source = $.trim(source);
		var starttime = $('#starttime').val();starttime = $.trim(starttime);
		var endtime = $('#endtime').val();endtime = $.trim(endtime);
		var pageSize = $('#pageSize').val();pageSize = $.trim(pageSize);
		return {
			billno:billno,
			supplierid:supplierid,
			source:source,
			starttime:str2Long(starttime),
			endtime:str2Long(endtime),
			pageSize:pageSize
		};
	}
	
	function pageCallback(pageNo){
		queryData(pageNo+1);
	}
	
	function queryData(pageNo){
		var index = layer.load(2, {
		  shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var params = getParams();
		params.pageNo = pageNo;
		$.ajax({
			url:'/purchaseApplication/page',
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
					alert(result.error);
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
				var billno = obj.billno || '';
				var status = obj.status || '';
				switch (status) {
				case '0':
					status = '未审核';
					break;
				case '1':
					status = '已审核';
					break;
				default:
					status = '';
					break;
				}
				var source = obj.source || '';
				switch (source) {
				case '0':
					source = '联机';
					break;
				case '1':
					source = '脱机';
					break;
				default:
					source = '';
					break;
				}
				var typename = obj.typename || '';
				var suppliername = obj.suppliername || '';
				var applytimeStr = obj.applytimeStr || '';
				var deptname = obj.deptname || '';
				var buyername = obj.buyername || '';
				var creator = obj.creator || '';
				var creatortimeStr = obj.creatortimeStr || '';
				var auditname = obj.auditname || '';
				var audittimeStr = obj.audittimeStr || '';
				var remark = obj.remark || '';
				$('<tr>').append('<td>'+(i+1)+'</td>')
						.append('<td>'+billno+'</td>')
						.append('<td class="colorred">'+status+'</td>')
						.append('<td>'+source+'</td>')
						.append('<td>'+typename+'</td>')
						.append('<td>'+suppliername+'</td>')
						.append('<td>'+applytimeStr+'</td>')
						.append('<td>'+deptname+'</td>')
						.append('<td>'+buyername+'</td>')
						.append('<td>'+creator+'</td>')
						.append('<td>'+creatortimeStr+'</td>')
						.append('<td>'+auditname+'</td>')
						.append('<td>'+audittimeStr+'</td>')
						.append('<td>'+remark+'</td>')
						.data(obj)
						.appendTo('#dataBody');
			}
			$('#dataBody tr').off('click').on('click',function(){
				$('#moreBody').empty();
				var obj = $(this).data();
				var objDetail = obj.detailResp;
				var orgname = objDetail.orgname || '';
				var materielname = objDetail.materielname || '';
				var materielspec = objDetail.materielspec || '';
				var materieltype = objDetail.materieltype || '';
				var purchasesum = objDetail.purchasesum;
				var receiptsum = objDetail.receiptsum;
				var unreceiptsum = objDetail.unreceiptsum;
				var advancesum = objDetail.advancesum;
				var remainsum = objDetail.remainsum;
				var remark = objDetail.remark || '';
				$('<tr>').append('<td>'+orgname+'</td>')
				.append('<td>'+materielname+'</td>')
				.append('<td>'+materielspec+'</td>')
				.append('<td>'+materieltype+'</td>')
				.append('<td>'+purchasesum+'</td>')
				.append('<td>'+receiptsum+'</td>')
				.append('<td>'+unreceiptsum+'</td>')
				.append('<td>'+advancesum+'</td>')
				.append('<td>'+remainsum+'</td>')
				.append('<td>'+remark+'</td>')
				.appendTo('#moreBody');
				$('#dataMore').show();
			});
			$('#dataBody tr').off('dblclick').on('dblclick',function(){
				$('#detailtab1').empty();
				var obj = $(this).data();
				var objDetail = obj.detailResp;
				var billno = obj.billno || '';
				var source = obj.source || '';
				switch (source) {
				case '0':
					source = '脱机';
					break;
				case '1':
					source = '联机';
					break;
				default:
					source = '';
					break;
				}
				var typename = obj.typename || '';
				var applytimeStr = obj.applytimeStr || '';
				var suppliername = obj.suppliername || '';
				var sumnum = obj.sumnum;
				var buyername = obj.buyername || '';
				var creator = obj.creator || '';
				var creatortimeStr = obj.creatortimeStr || '';
				var remark = obj.remark || '';
				$('#_billno').val(billno);
				$('#_source').val(source);
				$('#_typename').val(typename);
				$('#_applytime').val(applytimeStr);
				$('#_suppliername').val(suppliername);
				$('#_sumnum').val(sumnum);
				$('#_buyername').val(buyername);
				$('#_creator').val(creator);
				$('#_creatortime').val(creatortimeStr);
				$('#_remark').val(remark);
				var orgname = objDetail.orgname || '';
				var materielname = objDetail.materielname || '';
				var checkprogramid = objDetail.checkprogramid || '';
				var purchasesum = objDetail.purchasesum;
				var remark = objDetail.remark || '';
				$('<tr>').append('<td>'+orgname+'</td>')
				.append('<td>'+materielname+'</td>')
				.append('<td>'+checkprogramid+'</td>')
				.append('<td>'+purchasesum+'</td>')
				.append('<td>'+remark+'</td>')
				.appendTo('#detailtab1');
				switch (obj.status) {
				case '0':
					$('#shImg').attr('src','/resources/images/un_sh.png');
					break;
				case '1':
					$('#shImg').attr('src','/resources/images/sh.png');
					break;
				default:
					break;
				}
				$('#dataDetail').modal();
			});
		}else{
			alert('暂无数据');
			$('#dataMore').hide();
		}
	}
	
})(jQuery, window);