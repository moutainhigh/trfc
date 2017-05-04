;(function($){
	var URL = {
			queryAllRole: '/trfc/system/auth/role/queryAllRole',
			queryUserByRole: '/trfc/system/auth/rolePermissions/queryUserByRole',
			queryAllUserByRole: '/trfc/system/auth/rolePermissions/queryAllUserByRole',
			addUserToRole: '/trfc/system/auth/rolePermissions/addUserToRole'
	}
	
	init();
	
	function init(){
		loadRoleList();
		bindEvent();
	}
	
	function loadRoleList(){
		$.ajax({
			url:URL.queryAllRole,
			data:{},
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					readerRoleList(result.data);
				}else{
					layer.msg(result.error, {icon: 5});
				}
			}
		});
	}
	//解析加载用户列表
	function readerRoleList(data){
		if(data && data.length > 0){
			for(var i=0;i<data.length;i++){
				var obj = data[i];
				$('<li><i class="iconfont">&#xe620;</i><label>'+(obj.name || '')+'</label></li>').data(obj).appendTo('#roleList');
			}
		}
		$('#roleList li').off('click').on('click', function(){
			$(this).addClass('active').siblings().removeClass('active');
			layer.closeAll();
			var user = $(this).data() || {};
			$('.juese_title').html('角色授权 - ' + user.name);
			//异步加载右侧内容
			loadRightBody(user);
		});
		$('#roleList li:eq(0)').trigger('click');
	}
	
	//异步加载右侧内容
	function loadRightBody(id){
		clearQueryParams();
		queryData();
	}
	
	//clear查询条件
	function clearQueryParams(){
		$('#keyword').val("");
		$('#qtp').val('bh');
	}
		
	//获取查询条件
	function getQueryParams(){
		var params = {};
		var qtp = $('#qtp').val();qtp = $.trim(qtp);
		var keyword = $('#keyword').val();keyword = $.trim(keyword);
		if(qtp == 'bh'){
			params.codeLike = keyword;
		}
		if(qtp == 'zh'){
			params.nameLike = keyword;
		}
		/*if(qtp == 'sf'){
			params.internalcode = keyword;
		}*/
		var role = $('#roleList li.active').data() || {};
		params.roleid = role.id;
		return params;
	}
	
	function queryData(){
		var index = layer.load(2, {
			shade: [0.3,'#fff'], //0.1透明度的白色背景
			time: 10000
		});
		var params = getQueryParams();
		$.ajax({
			url:URL.queryUserByRole,
			data:params,
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					loadUserTable(result.data)
				}else{
					layer.msg(result.error, {icon: 5});
				}
				layer.close(index);
			}
		});
	}

	function loadUserTable(data){
		$('#userBody').empty();
		if(data && data.length > 0){
			for(var i=0;i<data.length;i++){
				var obj = data[i];
				$('<tr><td style="max-width: 20px;">'+(i+1)+'</td>'
						+'<td style="max-width: 20px;"><input type="checkbox"></td>'
						+'<td>'+(obj.usercode || '')+'</td>'
						+'<td>'+(obj.username || '')+'</td>'
						+'<td>'+(obj.sf || '')+'</td>'
						+'<td>'+(obj.gs || '')+'</td>'
						+'<td>'+(obj.userremark || '')+'</td>'
						+'</tr>').data(obj).appendTo('#userBody');
			}
		}else{
			layer.msg("暂无数据.");
		}
	}
	
	function bindEvent(){
		$('#addUserToRole').off('click').on('click', function(){
			$('#key').val('');
			queryUserToRole();
		});
		$('#deleteUserToRole').off('click').on('click', function(){
			
		});
		$('#searchUser').off('click').on('click', function(){
			queryData();
		});
		$('#searchKeyUser').off('click').on('click', function(){
			queryUserToRole();
		});
		$('#addUserBtn').off('click').on('click', function(){
			if($('#addUserView').is(':visible')){
				this.disabled = true;
				addUserToRole(this);
			}
		});
	}
	
	function queryUserToRole(){
		var key = $('#key').val(); key = $.trim(key);
		var role = $('#roleList li.active').data() || {};
		$.ajax({
			url:URL.queryAllUserByRole,
			data:{
				key: key,
				roleid: role.id
			},
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					console.info(result.data);
					loadUserToRole(result.data)
				}else{
					layer.msg(result.error, {icon: 5});
				}
			}
		});
	}
	
	function loadUserToRole(data){
		$('.juese_altuser_list ul').empty();
		if(data && data.length > 0){
			for(var i=0;i<data.length;i++){
				var obj = data[i] || {};
				$('<li '+(obj.userHasRole == '1' ? 'class="select"' : '')+'><i class="iconfont">&#xe620;</i>'+(obj.username)+'</li>').data(obj).appendTo('.juese_altuser_list ul');
			}
		}
//		角色权限、用户工作平台权限每项点击的选中取消效果
		function toggle_select(options){
			options.on("click", function () {
				$(this).toggleClass("select");
				if($(this).hasClass('select')){
					
				}else{
					
				}
			});
		}
		var juese_altuser_list = $('.jsqx_select .juese_altuser_list ul li');
		toggle_select(juese_altuser_list);

//		角色模块权限全选取消效果
		var sys_selectall = $('.juese_altuser .qx_seltall');
		var sys_cancelall = $('.juese_altuser .qx_cancelall');
		var sys_selectinp = $('.juese_altuser .qx_seltall input');
		var sys_cancelinp = $('.juese_altuser .qx_cancelall input');

		sys_selectinp.on("click", function () {
			$(juese_altuser_list).addClass("select");
			$(sys_selectall).hide();
			$(sys_cancelall).show();
		});
		sys_cancelinp.on("click", function () {
			$(juese_altuser_list).removeClass("select");
			$(sys_selectall).show();
			$(sys_cancelall).hide();
		});
		$('#addUserView').modal('show');
	}
	
	function addUserToRole(_this){
		$.ajax({
			url:URL.addUserToRole,
			data:{},
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					$('#addUserView').modal('hide');
					$('#roleList li.active').trigger('click');
				}else{
					layer.msg(result.error, {icon: 5});
				}
				_this.disabled = false;
			}
		});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
})(jQuery);