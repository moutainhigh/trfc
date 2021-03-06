;(function($, win){
	
	var URL = {
			initAddUrl:"/trfc/customerbegin/initAdd",
			addBtnUrl:"/trfc/customerbegin/add",
			pageUrl:"/trfc/customerbegin/page",
			auditUrl:"/trfc/customerbegin/audit",
			deleteUrl:"/trfc/customerbegin/delete",
			customerAutoCompleteSearch: "/trfc/customer/autoCompleteSearch"
	};
	
	
	init();
	function init(){s
		//初始化autocomplete
		initAutoComplete();
		bindEvent();
		queryData(1);
	}
	
	// 获取当前用户id
	var userid=$('.user').attr('userid');
	
	
	function str2Long(dateStr){
		var time = '';
		if(dateStr){
			var date = new Date(dateStr);
			time = date.getTime();
		}
		return time;
	}
	
	function initAutoComplete(){
		var cache = {};
	   
	    $("#s_customer").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		var customer = cache['customer'] || {};
	    		if ( term in customer ) {
	    			response( customer[ term ] );
	    			return;
	    		}
	    		$.post( URL.customerAutoCompleteSearch, request, function( data, status, xhr ) {
	    			customer[ term ] = data;
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
	    		$(this).val(ui.item.name).attr('customerid', ui.item.id);
	    		return false;
	    	}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input keydown',function(){
	    	$(this).removeAttr('customerid');
	    }).change(function(){
    		if(!$(this).attr('customerid')){
    			$(this).val('');
    		}
	    });
	    $("#a_customer").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		var customer = cache['customer'] || {};
	    		if ( term in customer ) {
	    			response( customer[ term ] );
	    			return;
	    		}
	    		$.post( URL.customerAutoCompleteSearch, request, function( data, status, xhr ) {
	    			customer[ term ] = data;
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
	    		$(this).val(ui.item.name).attr('customerid', ui.item.id);
	    		$('#a_channelcode').val(ui.item.channelcode);
				$('#a_salesmanname').val(ui.item.salesmanname).attr('salesmanid',ui.item.salesmanid);
				$('#a_transportcompanyname').val(ui.item.transportcompanyname).attr('transportcompanyid',ui.item.transportcompanyid);
	    		return false;
	    	}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input keydown',function(){
	    	$(this).removeAttr('customerid');
	    }).change(function(){
    		if(!$(this).attr('customerid')){
    			$(this).val('');
        		$('#a_channelcode').val('');
        		$('#a_salesmanname').val('').removeAttr('salesmanid');
        		$('#a_transportcompanyname').val('').removeAttr('transportcompanyid');
    		}
	    });
	}
	
	function bindEvent(){
		$('#refreshBtn').off('click').on('click',function(){
			queryData(1);
			layer.closeAll('dialog');
		});
		$('#searchBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#jumpButton').off('click').on('click',function(){
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
		$('#initAdd').off('click').on('click',function(){
			initAdd();
		});
		$('#audit').off('click').on('click',function(e){
			e.stopPropagation();
			var obj = $('table.maintable tbody tr.active').data();
			if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
			audit(obj);	
		});
		$('#delete').off('click').on('click',function(e){
			e.stopPropagation();
			var obj = $('table.maintable tbody tr.active').data();
			if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
			deleteBegin(obj);	
		});
		$('#ensureAdd').off('click').on('click', function(){
			if($('#add').is(':visible')){
				this.disabled = true;
				addCustomerBegin(this);
			}
		});
		// 表格内容每行单击出来下面的详细信息
		$('#begins').on('dblclick','tr',function(){
			$('#caigoubill').modal('show');
			var begin=$(this).closest('tr').data();
//			console.log(begin);
			$('#detail_code').val(begin.code);
			$('#detail_customer').val(begin.customername);
			$('#detail_date').val(begin.billdate);
			$('#detail_method').val(begin.paymentmethod);
			$('#detail_payer').val(begin.payer);
			$('#detail_unit').val(begin.collectionunit);
			$('#detail_money').val(begin.money);
			$('#detail_capital').val(begin.moneybig);
			$('#detail_maker').val(begin.makebillname);
			$('#detail_makebilltime').val(begin.billdate.substring(0,10));
			$('#detail_remark').val(begin.remark);
			if(begin.auditstatus=='0'){
				$('#un_sh').show();
				$('#sh').hide();
			}else{
				$('#sh').show();
				$('#un_sh').hide();
			}
		});
		
		//新增页面金额输入框键盘按下事件
		$('#money').focus(function(){
			if(eval($(this).val())==0){
				$(this).val('');
			}
			
		}).keyup(function(){
			
			 var regStrs = [  
			        ['^0(\\d+)$', '$1'], //禁止录入整数部分两位以上，但首位为0  
			        ['[^\\d\\.]+$', ''], //禁止录入任何非数字和点  
			        ['\\.(\\d?)\\.+', '.$1'], //禁止录入两个以上的点  
			        ['^(\\d+\\.\\d{3}).+', '$1'] //禁止录入小数点后两位以上  
			    ];  
			    for(var i=0; i<regStrs.length; i++){  
			        var reg = new RegExp(regStrs[i][0]);  
			        this.value = this.value.replace(reg, regStrs[i][1]);  
			    }  
			
			var num=$(this).val();
			if(num==null  || eval(num)==0 || num==''){
					$('#money_capital').val('零元整');
			}else{
				var money_capital=DX(eval(''+num+''));
				$('#money_capital').val(money_capital);
			}
         }).change(function () {
                $(this).keypress();
                var v = $(this).val();
                if (/\.$/.test(v))
                {
                    $(this).val(v.substr(0, v.length - 1));
                }
		}).blur(function(){
			var v=$(this).val();
			if(v === ''){  
		        v = '0.000';  
		    }else if(v === '0'){  
		        v = '0.000';  
		    }else if(v === '0.'){  
		        v = '0.000';  
		    }else if(/^0+\d+\.?\d*.*$/.test(v)){  
		        v = v.replace(/^0+(\d+\.?\d*).*$/, '$1');  
		        v = inp.getRightPriceFormat(v).val;  
		    }else if(/^0\.\d$/.test(v)){  
		        v = v + '00';  
		    }else if(!/^\d+\.\d{3}$/.test(v)){  
		        if(/^\d+\.\d{3}.+/.test(v)){  
		            v = v.replace(/^(\d+\.\d{2}).*$/, '$1');  
		        }else if(/^\d+$/.test(v)){  
		            v = v + '.000';  
		        }else if(/^\d+\.$/.test(v)){  
		            v = v + '000';  
		        }else if(/^\d+\.\d$/.test(v)){  
		            v = v + '00';  
		        }else if(/^\d+\.\d{2}$/.test(v)){  
		            v = v + '0'; 
		        }else if(/^[^\d]+\d+\.?\d*$/.test(v)){  
		            v = v.replace(/^[^\d]+(\d+\.?\d*)$/, '$1');  
		        }else if(/\d+/.test(v)){  
		            v = v.replace(/^[^\d]*(\d+\.?\d*).*$/, '$1');  
		            ty = false;  
		        }else if(/^0+\d+\.?\d*$/.test(v)){  
		            v = v.replace(/^0+(\d+\.?\d*)$/, '$1');  
		            ty = false;  
		        }else{  
		            v = '0.000';  
		        }  
		    }  
			this.value=v;
			var num=$(this).val();
			if(num==null  || eval(num)==0 || num==''){
					$('#money_capital').val('零元整');
			}else{
				var money_capital=DX(eval(''+num+''));
				$('#money_capital').val(money_capital);
			}
		});
	}
	
	
	function queryData(pageNo){
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var url=URL.pageUrl;
		var starttime=$('#s_starttime').val();starttime=$.trim(starttime);
		var endtime=$('#s_endtime').val();endtime=$.trim(endtime);
		var customername=$('#s_customer').val();customername=$.trim(customername);
		var orgname=$('#branch option:checked').text();
		var makebillname=$('#makebillname').val();makebillname=$.trim(makebillname);
		var pageNo=pageNo;
		var pageSize = $('#pageSize').val();pageSize = $.trim(pageSize);
		
		var params={
				starttime:str2Long(starttime),
				endtime:str2Long(endtime),
				customername:customername,
				orgname:orgname,
				makebillname:makebillname,
				pageNo:pageNo,
				pageSize:pageSize
		};
		
		$.post(url,params,function(result){
			if(result.code=='000000'){
				var data=result.data;
//				console.log(data);
				var list=data.list;
				var pageNo=data.pageNo;
				var pageSize=data.pageSize;
				var total=data.total;
				var tbody=$('#begins').empty();
				//添加数据总数
				$('.colorred').html(data.total);
				//绑定 一个maxpageno属性
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
				if(!list){
					layer.msg('暂无数据.');
					return;
				}
				for(var i=0;i<list.length;i++){
					var begin=list[i];
					var status=begin.auditstatus;
					switch(status){
						case '0':
							status='未审核';
							break;
						case '1':
							status='已审核'
							break;
						default:
							status='';
							break;
					}
					var tr=$('<tr><td>'+((pageNo-1)*pageSize+i+1)+'</td><td>'+begin.code+'</td><td id="if_audit'+i+'">'+status+'</td><td>'+
							begin.billdate+'</td><td>'+begin.customername+'</td><td>'+begin.paymentmethod+'</td><td>'+
							begin.money+'</td><td>'+begin.collectionunit+'</td><td>'+begin.payer+'</td><td>'+
							begin.makebillname+'</td><td>'+begin.billdate.substring(0,10)+'</td><td>'+begin.remark+'</td>'     
					);
					tbody.append(tr);
					if(status=='未审核'){
						$('#if_audit'+i).addClass('colorred');
					}
					tr.data(begin);
				}
				
				
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
		layer.close(index);
	}
	
	function pageCallback(pageNo){
		queryData(pageNo+1);
	}
	
	function initAdd(){
		var url=URL.initAddUrl;
		var param={};
		$.post(url,param,function(result){
			if(result.code=='000000'){
				var data=result.data;
//				console.log(data);
				$('#code').val(data.code);
				$('#a_customer').val('');
				$('#a_billtimeStr').val(data.nowDate);
				$('#paymentmethod').val('');
				$('#payer').val('');
				$('#collectionunit').val(data.collectionunit);
				$('#money').val('0.00');
				$('#money_capital').val('');
				$('#maker').val(data.makebillname);
				$('#maketime').val(data.nowDate.substring(0,10));
				$('#remark').val('');
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	
	function getAddCustomerBegin(){
		var code=$('#code').val();code=$.trim(code);
		var customerid = $('#a_customer').attr('customerid');customerid = $.trim(customerid);
		var customername=$('#a_customer').val();customername=$.trim(customername);
		var billdate = $('#a_billtimeStr').val();billdate = $.trim(billdate);
		var paymentmethod=$('#paymentmethod option:checked').text();paymentmethod=$.trim(paymentmethod);
		var payer=$('#payer').val();payer=$.trim(payer);
		var money=$('#money').val();
		var moneybig=$('#money_capital').val();moneybig=$.trim(moneybig);
		var collectionunit=$('#collectionunit').val();collectionunit=$.trim(collectionunit);
		var makebillname=$('#maker').val();makebillname=$.trim(makebillname);
		var remark=$('#remark').val();remark=$.trim(remark);
		return{
			code:code,
			customerid:customerid,
			customername:customername,
			billdate:billdate,
			paymentmethod:paymentmethod,
			payer:payer,
			money:money,
			moneybig:moneybig,
			user:userid,
			collectionunit:collectionunit,
			makebillname:makebillname,
			remark:remark
		};
	}
	
	
	
	function addCustomerBegin(_this){
//		console.log('addCustomerBegin');
		var url=URL.addBtnUrl;
		var params=getAddCustomerBegin();
		console.log(params);
		if(validate(params)){
			var index = layer.load(2, {
				shade: [0.3,'#fff'] //0.1透明度的白色背景
			});
			$.post(url,params,function(result){
				if(result.code=='000000'){
					_this.disabled = false;
					win.location.reload(true);
				}else{
					layer.msg('该客户的期初已经录入!',{icon: 5});
					_this.disabled = false;
				}
				layer.close(index);
			});
		}else{
			_this.disabled = false;
		}
		
	}	
	
	
	function validate(params){
		if(!params.customerid){
			layer.msg('请选择客户', {icon: 5});return false;
		}
		if(!params.paymentmethod){
			layer.msg('请选择收款方式', {icon: 5});return false;
		}
		if(eval(params.money)==0){
			layer.tips('金额不能为零!', $('#money'), {
				  tips: [1, '#3595CC'],
				  time: 2000
				});
			$('#money').focus();
			return false;
		}
		return true;
	}
	
	
	function DX(num) {
		var strOutput = "",
		strUnit = '仟佰拾亿仟佰拾万仟佰拾元角分';
		num += "00";
		var intPos = num.indexOf('.');
		if (intPos >= 0){
		num = num.substring(0, intPos) + num.substr(intPos + 1, 2);
		}
		strUnit = strUnit.substr(strUnit.length - num.length);
		for (var i=0; i < num.length; i++){
		strOutput += '零壹贰叁肆伍陆柒捌玖'.substr(num.substr(i,1),1) + strUnit.substr(i,1);
		}
		return strOutput.replace(/零角零分$/, '整').replace(/零[仟佰拾]/g, '零').replace(/零{2,}/g, '零').replace(/零([亿|万])/g, '$1').replace(/零+元/, '元').replace(/亿零{0,3}万/, '亿').replace(/^元/, "零元")
	}
	

	
	
	
	function audit(obj){
		if(obj.auditstatus == '1'){
			layer.msg('已审核的单据，不能继续审核！', {icon: 5});
			return;
		}
		confirmOperation('您确定要审核么？', URL.auditUrl, {
			id:obj.id
		});
	}
	
	function confirmOperation(confirmContent, url, params){
		var bn=layer.open({
			content: confirmContent,
			area: '600px',
			closeBtn:1,
			shadeClose:true,
			btn: ['确定', '取消'],
			yes: function(index, layero){
				//按钮【确定】的回调
				//数据存到服务器
				$.ajax({
					url:url,
					data:params,
					async:true,
					cache:false,
					dataType:'json',
					type:'post',
					success:function(result){
						if(result.code == '000000'){
							win.location.reload(true);
						}else{
							layer.msg(result.error, {icon: 5});
						}
					}
				});
				layer.close(bn);
			},btn2: function(index, layero){
				//按钮【取消】的回调
			}
			,cancel: function(){
				//右上角关闭回调
			}
		});
		
	}
	
	
	function deleteBegin(begin){
		if(begin.auditstatus=='1'){
			layer.msg('已审核的单据，不能删除！', {icon: 5});
			return;
		}
		
		var bn=layer.open({
	        content: '您确定要删除吗？',
	        area: '600px',
	        closeBtn:1,
	        shadeClose:true,
	        btn: ['确定', '取消'],
	        yes: function(index, layero){
	            //按钮【确定】的回调
	        	confirmDelete(begin);
				layer.close(bn);
	        },btn2: function(index, layero){
	            //按钮【取消】的回调
	        }
	        ,cancel: function(){
	            //右上角关闭回调
	        }
	    });
	}
	
	function confirmDelete(begin){
		var url=URL.deleteUrl;
		var param={
			id:begin.id	
		};
		$.post(url,param,function(result){
			if(result.code=='000000'){
				queryData(1);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	
	
	
	
})(jQuery, window);