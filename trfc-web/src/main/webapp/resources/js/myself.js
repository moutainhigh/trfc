/**
 * Created by wfl on 2016/12/29
 */
;
//网站整体布局，左侧菜单宽度改变 右边自动补上
$(function(){


	var menu_ctrl = $(".left .menu");
	var menu_ctrlmini = $(".leftmini .menu2");
	var leftall = $(".left");
	var leftmini = $(".leftmini");
	menu_ctrl.on("click", function () {
		$(leftall).css("display", "none");
		leftmini.css("display", "block");
		$(".right").css("margin-left", "100px");
	});
	menu_ctrlmini.on("click", function () {
		$(leftmini).css("display", "none");
		leftall.css("display", "block");
		$(".right").css("margin-left", "200px");
	});


//	根据屏幕高度，用户角色模块的高度固定
	var htotal = $(window).height();
	$(".intel_tabbox .intel_tabcont").css("min-height",htotal-80);
	$(".juese_company").css("min-height",htotal-150);
	$(".juese_user").css("min-height",htotal-150);
	$(".juese_sys").css("min-height",htotal-300);
	$(".juese_sysplat").css("min-height",htotal-300);
	$(".person_panel").css("min-height",htotal-150);

//	角色权限、用户工作平台权限每项点击的选中取消效果
	function toggle_select(options){
		options.on("click", function () {
			$(this).toggleClass("select");
		});
	}
	var user_platlist = $('.juese_sysplat .juese_altuser_list ul li');
	var juese_altuser_list = $('.jsqx_select .juese_altuser_list ul li');
	toggle_select(user_platlist);
	toggle_select(juese_altuser_list);


//	用户模块工作平台权限全选取消效果
	var user_selectall = $('.juese_plattit .qx_seltall');
	var user_cancelall = $('.juese_plattit .qx_cancelall');
	var user_selectinp = $('.juese_plattit .qx_seltall input');
	var user_cancelinp = $('.juese_plattit .qx_cancelall input');

	user_selectinp.on("click", function () {
		$(juese_altuser_list).addClass("select");
		$(user_selectall).hide();
		$(user_cancelall).show();
		$(".juese_plattit input[type='checkbox']").prop("checked");
	});
	user_cancelinp.on("click", function () {
		$(juese_altuser_list).removeClass("select");
		$(user_selectall).show();
		$(user_cancelall).hide();
		$(".juese_plattit input[type='checkbox']").prop("checked");
	});

//	角色模块权限全选取消效果
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

//	页面所有表格每行tr选中背景变白
	var tabletr = $('.intel_table table tr');
	tabletr.on("click", function () {
		$(this).addClass("select").siblings().removeClass("select");
	});

//	角色权限树形表格中的全选取消按钮js
	$(function(){
		$(".intel_table table").find("input[type='checkbox']").on("click",function(){
//			console.log($(this).parentsUntil(".tree","li").html());
			var childInput=$(this).parentsUntil("tr").find("input[type='checkbox']");
			var current_id = $(this).parentsUntil("tbody");
			alert(current_id.html());
			if($(this).is(":checked")){
				childInput.prop("checked",true);
			}
			else{
				childInput.prop("checked",false);
			}
		});
	});

//	左侧通用二级折叠菜单的加减号显示隐藏
	function collapse_left(options){
		options.on("click", function () {
			if ($(this).hasClass("collapsed")) {
				$(this).find("span i").html("&#xe604;")
			}
			else {
				$(this).find("span i").html("&#xe61f;")
			}
		});
	}
	var menu_a = $('.left .menu_collap a');
	collapse_left(menu_a);

//	内页二级折叠菜单的加减号显示隐藏
	function collapse_qx(options){
		options.on("click", function () {
			if ($(this).hasClass("collapsed")) {
				$(this).find("span i").html("&#xe6c8;")//减号
			}
			else {
				$(this).find("span i").html("&#xe66f;")//加号
			}
		});
	}
	var sys_a = $('.sys_collap a');
	collapse_qx(sys_a);

//	二级折叠菜单内容选中添加背景,未被选中的除去背景
	function collap_bg(options){
		options.on("click", function () {
			$(this).parent().parent("ul").find("li").removeClass("active");
			$(this).addClass("active");
		});
	}
//	二级折叠菜单内容选中添加背景,未被选中的除去背景
	var menu_li = $('.left .menu_collap ul li');
	collap_bg(menu_li);
	var juese_li = $('.sys_collap ul li');
	collap_bg(juese_li);
	
	


});



