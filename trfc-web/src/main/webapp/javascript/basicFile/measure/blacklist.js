;(function($, win){
	//请求路径
	var URL = {
			addBlacklistUrl:"/trfc/blacklist/add",
			addBtnUrl:"/trfc/blacklist/addCarBlacklist",
			pageUrl:"/trfc/blacklist/page",
			delblacklistUrl:"/trfc/blacklist/delblacklist",
			autoCompleteSearch:"/trfc/vehicle/autoCompleteSearch"
			
	};
	
	init();
	function init(){
		bindEvent();
		queryData(1);	
		initAutoComplete();
	}
	
	// 获取当前用户id
	var userid=$('.user').attr('userid');
	//公共变量，用于存储数据
	var blacklistData={};
	
	
	function initAutoComplete(){
		var cache = {};
	    $("#vehicleno, #a_vehicle").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		var vehicleno = cache['vehicleno'] || {};
	    		if ( term in vehicleno ) {
	    			response( vehicleno[ term ] );
	    			return;
	    		}
	    		$.post( URL.autoCompleteSearch, request, function( data, status, xhr ) {
	    			vehicleno[ term ] = data;
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
	    		$(this).val(ui.item.vehicleno).attr('vehiclenoid', ui.item.id).attr('select',true);
	    		return false;
    		}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input keydown',function(){
	    	$(this).removeAttr('vehiclenoid');
	    }).change(function(){
    		if(!$(this).attr('vehiclenoid')){
    			$(this).val('');
    		}
	    });
	}
	
	
	
	function bindEvent(){
		$('#refreshBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#searshBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#addBtn').off('click').on('click',function(){
			addBlacklistAction();
		});
		$('#addCommit').off('click').on('click', function(){
			addBlacklist();
		});
		//跳转按钮绑定 点击事件
		$('#jumpButton').click(jumpPageAction);
		$('#pageSize').change(function(){
			queryData(1);
		});
		//监听删除按钮点击事件
		$('#blacklists').on('click','tr .delete_blacklist',function(){
			var blacklist=$(this).closest('tr').data();
			blacklistData.id=blacklist.id;
			var bn=layer.open({
		        content: '您确定要删除吗？',
		        area: '600px',
		        closeBtn:1,
		        shadeClose:true,
		        btn: ['确定', '取消'],
		        yes: function(index, layero){
		            //按钮【确定】的回调
					deleteblacklist();
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
	//分页查询数据
	function queryData(pageNo){
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var url=URL.pageUrl;
		//获取当前页面记录数
		var pageSize=10;
		var creator='';
		var vehicleno='';
		//获取查询条件
		var carsearch = $('#carsearch').val();carsearch = $.trim(carsearch);

		var params={
				pageSize:pageSize,
				creator:creator,
				vehicleno:vehicleno
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
				var tbody=$('#blacklists').empty();
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
					var blacklist=list[i];
					var tr=$('<tr><td>'+((pageNo-1)*pageSize+i+1)+'</td><td>'+blacklist.vehicleno+'</td><td>'+blacklist.createtime+'</td><td>'+blacklist.creatorName+'</td><td>'+blacklist.remarks+'</td><td>'+'</span><span class="delete_blacklist">'+'<a><i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>'+'</span></td></tr>');
					tr.data(blacklist);
					tbody.append(tr);
				}
				
			}else{
				layer.msg(result.error, {icon: 5});
			}
		});
		layer.close(index);
	}
	
	//回调函数
	function pageCallback(pageNo){
		queryData(pageNo+1);
	}
	
	function jumpPageAction(){
		var maxpageno = $('#jumpPageNo').attr('maxpageno');
		var pageno = $('#jumpPageNo').val();
		if(!pageno || !$.isNumeric(pageno) || pageno<=0 || pageno>maxpageno){
			layer.msg('输入的数字必须在1~'+maxpageno+'之间');
		}else{
			queryData(pageno);
			$('#jumpPageNo').val('');
		}
	}
	
	
	function addBlacklistAction(){
		var url=URL.addBtnUrl;
		$.post(url,null,function(result){
			if(result.code='000000'){
				$('#vehicleno').val('').removeClass('vehiclenoid');
				$('#creator').val(result.data.creator);
				$('#createtime').val(result.data.createtime);
				$('#remarks').val('');
			}else{
				layer.msg(result.error,{icon:5})
			}
		});
		$('#addView').modal('show');
	}
	
	function addBlacklist() {
//		console.log('addMinemouth');
		if($('#addView').is(':visible')){
			var url=URL.addBlacklistUrl;
			var vehicleno = $('#vehicleno').attr('vehiclenoid'); vehiclenoid = $.trim(vehiclenoid);
			var creator = $('#creator').val();creator = $.trim(creator);
			var createtime = $('#createtime').val();createtime = $.trim(createtime);
			var remarks = $('#remarks').val();remarks = $.trim(remarks);
			var params={
					vehiclenoid:vehiclenoid,
					creator:creator,
					createtime:createtime,
					remarks:remarks,
			};
//			console.log(params);
			$('#addCommit').attr('data-dismiss','modal');
			$.post(url,params,function(result){
				if(result.code='000000'){
					queryData(1);
				}else{
					layer.msg(result.error,{icon:5});
				}
			});
		}
		
	}
	
	/*function addBlacklist(){
		if($('#add').is(':visible')){
			var url=URL.addBlacklistUrl;
			var code = $('#vehicleno').val();code = $.trim(code);
			var operator = $('#operator').val();operator = $.trim(operator);
			var describe = $('#describe').val();describe = $.trim(describe);
			var pinyincode=pinyin.getCamelChars(name).toUpperCase();
			var params={
					vehicleid:vehicleid,
					vehicleno:vehicleno,
					createtime:createtime,
					creator:creator,
					remarks:remarks
			};
//			console.log(params);
			$('#addblacklist').attr('data-dismiss','modal');
			$.post(url,params,function(result){
				if(result.code='000000'){
					queryData(1);
				}else{
					layer.msg(result.error,{icon:5});
				}
			});
		}
	}*/
	
	function deleteblacklist(){
		var url=URL.delblacklistUrl;
		var params={
				id:blacklistData.id,
				user:userid
		};
		$.post(url,params,function(result){
			if(result.code='000000'){
				queryData(1);
			}else{
				layer.msg(result.error,{icon:5})
			}
		});
	}
})(jQuery, window);