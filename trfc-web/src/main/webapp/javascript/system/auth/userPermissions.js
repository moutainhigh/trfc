;(function($){
	var URL = {
			queryAllUser: '/trfc/system/auth/user/queryAllUser'
	}
	
	init();
	
	function init(){
		loadUserList();
	}
	
	function loadUserList(){
		$.ajax({
			url:URL.queryAllUser,
			data:{},
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					console.info(result.data);
					readerUserList(result.data);
				}else{
					layer.msg(result.error, {icon: 5});
				}
			}
		});
	}
	//解析加载用户列表
	function readerUserList(data){
		if(data && data.length > 0){
			for(var i=0;i<data.length;i++){
				var obj = data[i];
				$('<li><i class="iconfont">&#xe620;</i><label>'+(obj.name || '')+'</label></li>').data(obj).appendTo('#userList');
			}
		}
		$('#userList li').off('click').on('click', function(){
			$(this).addClass('active').siblings().removeClass('active');
			var user = $(this).data() || {};
			$('.juese_title').html('用户授权 - ' + user.name);
			//异步加载右侧内容
			loadRightBody(user);
		});
	}
	
	//异步加载右侧内容
	function loadRightBody(id){
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
})(jQuery);