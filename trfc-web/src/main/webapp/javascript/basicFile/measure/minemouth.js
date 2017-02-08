;(function($, win){
	//请求路径
	var URL = {
			addMinemouthUrl:"/trfc/minemouth/addMinemouth",
			addBtnUrl:"/trfc/common/code/minemouthCode",
			pageUrl:"/trfc/minemouth/page",
			updateMinemouthUrl:"/trfc/minemouth/updateMinemouth",
			delMinemouthUrl:"/trfc/minemouth/delMinemouth"
	};
	
	init();
	function init(){
		bindEvent();
		queryData(1);
		$('#show_isvalid').click();
	}
	
	var minemouthData={};
	
	function bindEvent(){
		$('#refreshBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#searshBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#addBtn').off('click').on('click',function(){
			addMinemouthAction();
		});
		$('#addMinemouth').off('click').on('click',function(){
			addMinemouth();
		});
		//跳转按钮绑定 点击事件
		$('#jumpButton').click(jumpPageAction);
		$('#pageSize').change(function(){
			queryData(1);
		});
		$('#show_isvalid').off('click').on('click',function(){
			queryData(1);
		});
		//监听编辑按钮点击事件
		$('#minemouths').on('click','tr .update_minemouth',function(){
			var minemouth=$(this).closest('tr').data();
//			console.log(minemouth);
			$('#update_minemouth_code').val(minemouth.code);
			$('#update_minemouth_name').val(minemouth.name);
			$('#update_minemouth_remarks').val(minemouth.remarks);
			$('#update_minemouth_isvalid')[0].checked=false;
			if(minemouth.isvalid==1){
				$('#update_minemouth_isvalid')[0].checked=true;
			}
			minemouthData.id=minemouth.id;
		});
		//修改矿口管理信息
		$('#update_minemouth').off('click').on('click',function(){
			updateMinemouth();
		});
		//监听删除按钮点击事件
		$('#minemouths').on('click','tr .delete_minemouth',function(){
			var minemouth=$(this).closest('tr').data();
			minemouthData.id=minemouth.id;
			var bn=layer.open({
		        content: '您确定要删除吗？',
		        area: '600px',
		        closeBtn:1,
		        shadeClose:true,
		        btn: ['确定', '取消'],
		        yes: function(index, layero){
		            //按钮【确定】的回调
					deleteMinemouth();
					layer.close(bn);
		        },btn2: function(index, layero){
		            //按钮【取消】的回调
		        }
		        ,cancel: function(){
		            //右上角关闭回调
		        }
		    });
			
		});
	}
	
	function queryData(pageNo) {
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var url=URL.pageUrl;
		//获取当前页面记录数
		var pageSize=$('#pageSize').val();
		var name='';
		var code='';
		//获取查询条件
		var keyword = $('#minemouth_keyword').val();keyword = $.trim(keyword);
		if($('#minemouth_query').val()=='name'){
			name=keyword;
		}else{
			code=keyword;
		}
		
		var isvalid = 0;
		if($('#show_isvalid')[0].checked){
			isvalid = 1;
		}
		var params={
				pageSize:pageSize,
				name:name,
				code:code,
				isvalid:isvalid
		};
		//获取当前页数
		params.pageNo=pageNo;
//		console.log(params);
		$.post(url,params,function(result){
			if(result.code == '000000'){
//				console.log(result.data);
				var data=result.data;
				var list=data.list;
//				console.log(list);
				var pageNo=data.pageNo;
				var pageSize=data.pageSize;
				var total=data.total;
				var tbody=$('#minemouths').empty();
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
					return;
				}
				for(var i=0;i<list.length;i++){
					var minemouth=list[i];
					var tr=$('<tr><td>'+((pageNo-1)*pageSize+i+1)+'</td><td>'+minemouth.code+'</td><td>'+minemouth.name+'</td><td>'+minemouth.pinyincode+'</td><td><input type="checkbox" disabled id="list_isvalid'+i+
							'" ></td><td>'+minemouth.remarks+'</td><td>'+'<span class="update_minemouth"><a data-toggle="modal" data-target="#edit" ><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>'+
							'</span><span class="delete_minemouth">'+'<a><i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>'+'</span></td></tr>');
					tr.data(minemouth);
					tbody.append(tr);
					$('#list_isvalid'+i)[0].checked=false;
					if(minemouth.isvalid==1){
						$('#list_isvalid'+i)[0].checked=true;
					}
				}
				
			}else{
				layer.msg(result.error, {icon: 5});
			}
		});
		layer.close(index);
	}
	
	function pageCallback(pageNo){
		queryData(pageNo+1);
	}
	
	//页面跳转
	function jumpPageAction() {
		var maxpageno = $('#jumpPageNo').attr('maxpageno');
		var pageno = $('#jumpPageNo').val();
		if(!pageno || !$.isNumeric(pageno) || pageno<=0 || pageno>maxpageno){
			alert('输入的数字必须在1~'+maxpageno+'之间');
		}else{
			queryData(pageno);
			$('#jumpPageNo').val('');
		}
	} 
	
	function addMinemouthAction() {
//		console.log('addMinemouthAction');
		var url=URL.addBtnUrl;
		var param={};
		$.post(url,param,function(result){
			if(result.code='000000'){
				var data=result.data;
//				console.log(data);
				$('#minemouth_code').val(data.code);
				$('#minemouth_name').val('');
				$('#minemouth_isvalid').attr('checked','checked');
				$('#minemouth_remarks').val('');
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	
	function addMinemouth() {
//		console.log('addMinemouth');
		if($('#add').is(':visible')){
			var url=URL.addMinemouthUrl;
			var code = $('#minemouth_code').val();code = $.trim(code);
			var name = $('#minemouth_name').val();name = $.trim(name);
			if(!name){
				layer.msg('名称不能为空哦',{icon:5});
				$('#add .btn-primary').removeAttr('data-dismiss');
				return;
			}
			var isvalid = 0;
			if($('#minemouth_isvalid')[0].checked){
				isvalid = 1;
			}
			var remarks = $('#minemouth_remarks').val();remarks = $.trim(remarks);
			var pinyincode=pinyin.getCamelChars(name).toUpperCase();
			var params={
					code:code,
					name:name,
					pinyincode:pinyincode,
					isvalid:isvalid,
					remarks:remarks
			};
//			console.log(params);
			$('#addMinemouth').attr('data-dismiss','modal');
			$.post(url,params,function(result){
				if(result.code='000000'){
					queryData(1);
				}else{
					layer.msg(result.error,{icon:5});
				}
			});
		}
		
	}
	
	function updateMinemouth() {
		if($('#edit').is(':visible')){
			var id = minemouthData.id;
			var name = $('#update_minemouth_name').val();name = $.trim(name);
			if(!name){
				layer.msg('名称不能为空哦',{icon:5});
				$('#edit .btn-primary').removeAttr('data-dismiss');
				return;
			}
			var isvalid = 0;
			if($('#update_minemouth_isvalid')[0].checked){
				isvalid = 1;
			}
			var remarks = $('#update_minemouth_remarks').val();remarks = $.trim(remarks);
			var pinyincode=pinyin.getCamelChars(name).toUpperCase();
			var url=URL.updateMinemouthUrl;
			var params={
					id:id,
					name:name,
					pinyincode:pinyincode,
					isvalid:isvalid,
					remarks:remarks
			};
			$('#update_minemouth').attr('data-dismiss','modal');
			$.post(url,params,function(result){
				if(result.code='000000'){
					queryData(1);
				}else{
					layer.msg(result.error,{icon:5});
				}
			});
		}
	}
	
	function deleteMinemouth() {
		var url=URL.delMinemouthUrl;
		var param={id:minemouthData.id};
		$.post(url,param,function(result){
			if(result.code='000000'){
				queryData(1);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	
})(jQuery, window);