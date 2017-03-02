$(function(){
	var URL = {
			mainUrl:"/trfc/quality/sales/report/main",
			getMschemeData:"/trfc/quality/sales/file/MaterialScheme/findOne",
			findOneUrl:"/trfc/quality/sales/report/selectOne",
			getMsgUrl:"/trfc/quality/sales/report/auditMsg",
			saveUrl:"/trfc/quality/sales/report/update",
			getDetailAndVal:"/trfc/quality/sales/report/getDetailAndVal"
	};

	$('#goBack').click(function(){
		window.location.href=URL.mainUrl;
	});
	$('#fresh').click(function(){window.location.reload();});
	//获取用户名
	var userid = $('.user').attr('userid');
	var id = getId();
	var DATA = {}; 
	function getId(){
		var href = window.location.href;
		var trs = href.split('?id=');
		return trs[1];
	};
	//对新增页面进行初始化
	initPage();

	function initPage(){
		$.post(URL.findOneUrl,{id:id},function(result){
			if('000000'==result.code){
				var obj = result.data;
				DATA.obj = obj;
				$('#detail_code').val(obj.code);
				$('#detail_pstate')[0].checked=true;
				if(obj.pstate==0){
					$('#detail_pstate')[0].checked=false;
				}
				$('#detail_batchcode').val(obj.batchcode).attr('batchnumid',obj.batchnumid);
				$('#detail_producedtime').val(getNowFormatDate(false, obj.producetime));
				$('#detail_testtime').val(getNowFormatDate(false, obj.testtime));
				$('#detail_materialtype').val(obj.materialtype).attr('mschemeid',obj.mschemeid);
				getMschemeData(obj.mschemeid);
				$('#detail_reportorg').val(obj.reportorg);
				$('#detail_creattime').val(getNowFormatDate(true,obj.createtime));
				$('#detail_reporter').val(obj.reporter);
				$('#detail_creator').val(obj.creator);
				$('#detail_addr').val(obj.addr);
				$('#detail_remark').val(obj.remark);
				$('#detail_selldate').val(getNowFormatDate(false,obj.selldate));
				$('#detail_qscheme').val(obj.qschemename).attr('qschemeid',obj.qschemeid);
				showDetail(obj.qschemeid,id);
				$('#sureBtn').click(auditAction);
				$('#notSureBtn').click(notAuditAction);
				$('#constraintBtn').click(constraintAction);
				showAuditMsg();
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	//强制审核
	function constraintAction(){
		var obj = DATA.obj;
		if(obj.auditstate=='0'){
			var param = {
					id:obj.id,
					audit:'3',
					auditstate:'2',
					user:userid,
					record:$('#record').val()
			};
			$.post(URL.saveUrl,param,function(result){
				if('000000'==result.code){
					$('#fresh').click();
				}else{
					layer.msg(result.error,{icon:5});
				}
			});
		}else{
			layer.alert('只能对未审核的数据进行操作');
		}
	}
	//不同意
	function notAuditAction(){
		var obj = DATA.obj;
		if(obj.auditstate=='1'){
			var param = {
					id:obj.id,
					audit:'4',
					auditstate:'0',
					user:userid,
					record:$('#record').val()
			};
			$.post(URL.saveUrl,param,function(result){
				if('000000'==result.code){
					$('#fresh').click();
				}else{
					layer.msg(result.error,{icon:5});
				}
			});
		}else{
			layer.alert('只能对审核中的数据进行操作');
		}
	}
	//审核
	function auditAction(){
		var obj = DATA.obj;
		var audit = '1';
		var auditstate ='1';
		var record = $('#record').val();
		if(obj.auditstate=='1'){
			audit = '2';
			auditstate = '2';
		}
		var param = {
				id:obj.id,
				audit:audit,
				auditstate:auditstate,
				user:userid,
				record:record
		};
		$.post(URL.saveUrl,param,function(result){
			if('000000'==result.code){
				$('#fresh').click();
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	//获取mscheme的详细信息
	function getMschemeData(id){
		$.post(URL.getMschemeData,{id:id},function(result){
			if('000000'==result.code){
				fillMSDetail(result.data);
			}else{
				layer.msg(result.error,{icon:5});
			}
		})
	}

	function showAuditMsg(){
		$.post(URL.getMsgUrl,{id:id},function(result){
			if('000000'==result.code){
				var list = result.data;
				var ul = $('#recordlist').empty();
				if(list && list.length>0){
					for(var i=0;i<list.length;i++){
						var li = '<li>'+(i+1)
						+'、 '+((list[i].audit=='2'||list[i].audit=='4')?'第二步':'第一步')+' '+(list[i].creator || '')
						+' 在 '+(getNowFormatDate(true, list[i].createtime) || '')
						+'审批 ('+(list[i].audit=='4'?'不同意':(list[i].audit=='3'?'同意 并签发合同':'同意')) 
						+'):'+(list[i].remark || '')
						+'</li>';
						ul.append(li);
					}
					if(DATA.obj.auditstate=='2'){
						$('#auditDiv').hide();
					}
				}
			}
		});
	}



	//当物料方案改变时 填充数据
	function fillMSDetail(obj){
		$('#detail_strength').val(obj.strength);
		$('#detail_admixture').val(obj.admixture);
		$('#detail_admixtureadd').val(obj.admixtureadd);
		$('#detail_gypsum').val(obj.gypsum);
		$('#detail_gypsumadd').val(obj.gypsumadd);
		$('#detail_aid').val(obj.aid);
		$('#detail_aidadd').val(obj.aidadd);
	}
	//获取详情数据,并展示列表
	function showDetail(id,assayid){
		$.post(URL.getDetailAndVal,{schemeid:id,assayid:assayid},function(result){
			if('000000'==result.code){
				fillQSDetail(result.data);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	//展示详情
	function fillQSDetail(list){
		var tbody = $('#detail_list').empty();
		if(list){
			for(var i=0;i<list.length;i++){
				var obj = list[i];
				var assaytime = getNowFormatDate(false,obj.assaytime);
				var starttime = getNowFormatDate(false,obj.starttime);
				var endtime = getNowFormatDate(false,obj.endtime);
				var audittime = null;
				if(obj.audittime){
					audittime = getNowFormatDate(true,obj.audittime);
				}
				var tr = '<tr>'
					+'<td>'+(i+1)+'</td>'
					+'<td>'+(obj.itemname || '')+'</td>'
					+'<td>'+(obj.units || '')+'</td>'
					+'<td>'+(obj.standardval || '')+'</td>'
					+'<td><input type="text" readonly="true" value="'+(obj.testval || '0')+'"></td>'
					+'</tr>';
				tr = $(tr);
				//追加
				tbody.append(tr);
				tr.data('obj',obj);
			}
		}
	}
	//获取时间 param(true:返回yyyy-MM-dd hh:mm:ss fasle:返回yyyy-MM-dd)
//	time(获取指定时间的字符串) 默认返回当前时间
	function getNowFormatDate(param,time) {
		var date ;
//		判断time参数是否存在
		if(time){
			date = new Date(time);
		}else{
			date = new Date();
		}
		var seperator1 = "-";
		var seperator2 = ":";
//		获取月份
		var month = date.getMonth() + 1;
//		获取日期
		var strDate = date.getDate();
//		月或者日 为个位数时前面加'0'
		if (month >= 1 && month <= 9) {
			month = "0" + month;
		}
		if (strDate >= 0 && strDate <= 9) {
			strDate = "0" + strDate;
		}
		var hours = date.getHours();
		var minutes = date.getMinutes();
		var seconds = date.getSeconds();
		if (hours >= 0 && hours <= 9) {
			hours = "0" + hours;
		}
		if (minutes >= 0 && minutes <= 9) {
			minutes = "0" + minutes;
		}
		if (seconds >= 0 && seconds <= 9) {
			seconds = "0" + seconds;
		}
//		判断返回结果
		if(param){
			var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
			+ " " + hours + seperator2 + minutes
			+ seperator2 + seconds;
		}else{
			var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
		}
		return currentdate;
	}

});