<%@ page language="java"  pageEncoding="UTF-8"%>
<script type="text/javascript">
		// 顶部tab切换菜单
		var $tab_li = $('.intel_menu li');
		$tab_li.click(function() {
			$(this).addClass('select').siblings().removeClass('select');
			var index = $tab_li.index(this);
			$('.intel_tabbox > .intel_tabcont').eq(index).show().siblings()
					.hide();
		});
		// 表格内容每行单击出来下面的详细信息
		var tabledata = $('.intel_table table tbody tr');
		tabledata.on("click", function() {
			$(".intel_result").css("display", "block");
		})
		// 表格内容每行双击出来下面的详细信息
		tabledata.on("dblclick", function() {
			$('#caigoubill').modal('show');
		})
		// 左侧宽度改变 右边改变
		var leftall = $(".left");
		var leftmini = $(".leftmini");
		leftall.on("click",".menu", function() {
			leftall.css("display", "none");
			leftmini.css("display", "block");
			$(".right").css("margin-left", "100px");
		});
		leftmini.on("click", ".menu2",function() {
			leftmini.css("display", "none");
			leftall.css("display", "block");
			$(".right").css("margin-left", "200px");
		});
		// 首页底部的tab切换菜单
		var ind_li = $('#ind_tab ul li');
		ind_li.click(function() {
			$(this).addClass('select').siblings().removeClass('select');
			var index_li = cg_li.index(this);
			$('#ind_tab .cg_tabbox > .cg_tabcont').eq(index_li).show()
					.siblings().hide();
		});

		// 弹出信息的tab切换菜单
		var alt_li = $('.cg_tabtit ul li');
		alt_li.click(function() {
			$(this).addClass('select').siblings().removeClass('select');
			var index_alt = alt_li.index(this);
			$('.cg_tabbox .cg_tabcont').eq(index_alt).show().siblings().hide();
		});
</script>