<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
<!-- 引用公共header部分 -->
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>
</head>
<body>
	<div class="it_admin">
		<!-- 引用公共left部分 -->
		<jsp:include page="../../common/base/left_busi.jsp"></jsp:include>
		<div class="right">
			<!-- 引用公共right部分 -->
			<jsp:include page="../../common/base/right_head_busi.jsp"></jsp:include>
			<div class="intel_tab">
				<!--tab切换标题-->
				<ul class="intel_menu">
					<li class="select"><a href="/trfc/poundNote/purchase/main">采购磅单维护</a></li>
					<li><a href="/trfc/poundNote/sales/main">销售磅单维护</a></li>
				</ul>
			</div>
			<!--tab切换的内容-->
			<div class="intel_tabbox">
				<!--到货通知单begin-->
				<div class="intel_tabcont ">
					<div class="intel_search">
						<div class="intel_bggray">
							<div class="intel_bgblue"></div>
						</div>
						<div class="alt_opera">
							<ul>
								<li onclick="window.location.reload();"><i
									class="iconfont colorlv">&#xe61b;</i>
									<h5>刷新</h5></li>
							</ul>
						</div>
					</div>
					<div class="daohuo_add">
						<h5>销售计量单详细信息</h5>
						<div class="daohuo_add_div">
							<div class="daohuo_add_solo">
								<label>过磅单号：</label> <input type="text" value="${poundNote.code }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>发货单位：</label> <input type="text" value="${poundNote.senddepartmentname }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>客户：</label> <input type="text" value="${poundNote.getMainApplication().customername }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>物料：</label> <input type="text" value="${poundNote.getMainApplicationDetail().materielname }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>出厂编号：</label> <input type="text" value="${poundNote.serialnumber }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>车号：</label> <input type="text" value="${poundNote.vehicleno }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>毛重：</label> <input type="text" value="${poundNote.grossweight }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>皮重：</label> <input type="text" value="${poundNote.tareweight }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>净重：</label> <input type="text" value="${poundNote.netweight }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>司机：</label> <input type="text" value="${poundNote.drivername }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>身份证：</label> <input type="text" value="${poundNote.driveridentityno }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>过磅员：</label> <input type="text" value="${poundNote.weighername }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>轻车时间：</label> <input type="text" value="${poundNote.lighttimeStr }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>重车时间：</label> <input type="text" value="${poundNote.weighttimeStr }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>提货单号：</label> <input type="text" value="${poundNote.noticecode }"
									readonly="true">
							</div>
						</div>
					</div>
					<div class="clear"></div>
					<div>
						<div class="cg_dhadd">
							<div class="cg_tabtit">
								<ul>
									<li class="select">订单信息</li>
									<li>门禁图片</li>
									<li>过磅图片</li>
								</ul>
							</div>
						</div>
						<div class="cg_tabbox">
							<!--tab切换的内容-->
							<div class="cg_tabcont">
								<div class="daohuo_add">
									<div class="daohuo_add_div">
										<div class="daohuo_add_solo">
											<label>订单一：</label> <input type="text" readOnly="true"
												value="${poundNote.getOneApplication().code }">
										</div>
										<div class="daohuo_add_solo">
											<label>订单二：</label> <input type="text" readOnly="true"
												value="${poundNote.getTwoApplication().code }">
										</div>
										<div class="daohuo_add_solo">
											<label>订单三：</label> <input type="text" readOnly="true"
												value="${poundNote.getThreeApplication().code }">
										</div>
										<div class="daohuo_add_solo">
											<label>订单一量：</label> <input type="text" readOnly="true"
												value="${poundNote.getOneApplication().list[0].margin }">
										</div>
										<div class="daohuo_add_solo">
											<label>订单二量：</label> <input type="text" readOnly="true"
												value="${poundNote.getTwoApplication().list[0].margin }">
										</div>
										<div class="daohuo_add_solo">
											<label>订单三量：</label> <input type="text" readOnly="true"
												value="${poundNote.getThreeApplication().list[0].margin }">
										</div>
										<div class="daohuo_add_solo">
											<label>订单一单价：</label> <input type="text" readOnly="true"
												value="${poundNote.getOneApplication().list[0].taxprice }">
										</div>
										<div class="daohuo_add_solo">
											<label>订单二单价：</label> <input type="text" readOnly="true"
												value="${poundNote.getTwoApplication().list[0].taxprice }">
										</div>
										<div class="daohuo_add_solo">
											<label>订单三单价：</label> <input type="text" readOnly="true"
												value="${poundNote.getThreeApplication().list[0].taxprice }">
										</div>
										<div class="daohuo_add_solo">
											<label>订单一金额：</label> <input type="text" readOnly="true"
												value="${poundNote.getOneApplication().list[0].margin * poundNote.getOneApplication().list[0].taxprice }">
										</div>
										<div class="daohuo_add_solo">
											<label>订单二金额：</label> <input type="text" readOnly="true"
												value="${poundNote.getTwoApplication().list[0].margin * poundNote.getTwoApplication().list[0].taxprice }">
										</div>
										<div class="daohuo_add_solo">
											<label>订单三金额：</label> <input type="text" readOnly="true"
												value="${poundNote.getThreeApplication().list[0].margin * poundNote.getThreeApplication().list[0].taxprice }">
										</div>
									</div>
								</div>
							</div>
							<div class="cg_tabcont hide">2</div>
							<div class="cg_tabcont hide">2</div>
							<!--tab切换的内容end-->
						</div>
					</div>
				</div>
			</div>
			<!--到货通知单end-->
		</div>
	</div>
	<script type="text/javascript">
		// 录入、参照tab切换菜单
		var cg_li = $('.cg_tabtit ul li');
		cg_li.click(function() {
			$(this).addClass('select').siblings().removeClass('select');
			var index = cg_li.index(this);
			$('.cg_tabbox > .cg_tabcont').eq(index).show().siblings().hide();
		});
	</script>
</body>
</html>