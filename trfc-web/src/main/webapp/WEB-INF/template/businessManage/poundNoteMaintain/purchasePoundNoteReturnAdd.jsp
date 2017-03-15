<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
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
								<li id="refreshBtn"><i class="iconfont colorlv">&#xe61b;</i>
									<h5>刷新</h5></li>
								<li id="addBtn"><i class="iconfont colorblue">&#xe61d;</i>
									<h5>保存</h5></li>
							</ul>
						</div>
					</div>
					<div class="daohuo_add">
						<h5>采购退货计量单新增</h5>
						<input id="poundNoteId" type="hidden" value="${poundNote.id }"/>
						<div class="daohuo_add_div">
							<div class="daohuo_add_solo">
								<label>订单号：</label> <input type="text" readonly="true" value="${poundNote.billcode }">
							</div>
							<div class="daohuo_add_solo">
								<label>计量单号：</label> <input type="text" readonly="true" value="${code }">
							</div>
							<div class="daohuo_add_solo">
								<label>退货单位：</label> <input type="text" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>供应商：</label> <input type="text" readonly="true" value="${poundNote.suppliername }">
							</div>
							<div class="daohuo_add_solo">
								<label>订单数量：</label> <input type="text" readonly="true" value="${poundNote.billsum }">
							</div>
							<div class="daohuo_add_solo">
								<label>余量：</label> <input type="text" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>到货单号：</label> <input type="text" readonly="true" value="${poundNote.noticecode }">
							</div>
							<div class="daohuo_add_solo">
								<label>到货磅单：</label> <input type="text" readonly="true" value="${poundNote.code }">
							</div>
							<div class="daohuo_add_solo">
								<label>到货净重：</label> <input type="text" readonly="true" value="${poundNote.netweight }">
							</div>
							<div class="daohuo_add_solo">
								<label>毛重：</label> <input id="grossweight" type="text" value="${poundNote.grossweight }" placeholder="请输入毛重">
							</div>
							<div class="daohuo_add_solo">
								<label>皮重：</label> <input id="tareweight" type="text" value="${poundNote.tareweight }" placeholder="请输入皮重">
							</div>
							<div class="daohuo_add_solo">
								<label>净重：</label> <input id="netweight" type="text" readonly="true" value="${poundNote.netweight }">
							</div>
							<div class="daohuo_add_solo">
								<label>物料：</label> <input type="text" readonly="true" value="${poundNote.materielname }">
							</div>
							<div class="daohuo_add_solo">
								<label>司机：</label> <input id="driver" type="text" placeholder="请选择司机">
							</div>
							<div class="daohuo_add_solo">
								<label>身份证：</label> <input id="driveridentityno" type="text" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label class="colorred">*车号：</label> <input id="vehicle"
									type="text" placeholder="请选择车号">
							</div>
							<div class="daohuo_add_solo">
								<label class="colorred">*重车时间：</label> <input id="weighttime"
									type="text" readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
									class="Wdate" value="${nowDate }" placeholder="请选择重车时间"/>
							</div>
							<div class="daohuo_add_solo">
								<label class="colorred">*轻车时间：</label> <input id="lighttime"
									type="text" readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
									class="Wdate" value="${nowDate }" placeholder="请选择轻车时间"/>
							</div>
							<div class="daohuo_add_solo">
								<label>仓库：</label> <input id="warehouse" type="text" placeholder="请选择仓库">
							</div>
							<div class="daohuo_add_solo">
								<label class="colorred">*制单日期：</label> <input id="makebilltime"
									type="text" readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
									class="Wdate" value="${nowDate }" placeholder="请选择制单日期"/>
							</div>
							<div class="daohuo_add_solo">
								<label class="colorred">*制单人：</label> <input
									type="text" readonly="true" value="${makebillname }">
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--到货通知单end-->
			<!--tab切换的内容end-->
		</div>
	</div>
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/businessManage/poundNoteMaintain/purchasePoundNoteReturnAdd.js"></script>
</body>
</html>