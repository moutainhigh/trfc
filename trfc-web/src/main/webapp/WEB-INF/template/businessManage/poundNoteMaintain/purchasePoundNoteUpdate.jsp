<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购计量单修改</title>
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
								<li id="refresh"><i class="iconfont colorlv">&#xe61b;</i>
									<h5>刷新</h5></li>
								<li id="save" data-id="${poundNote.id }"><i class="iconfont colorblue">&#xe61d;</i>
									<h5>保存</h5></li>
							</ul>
						</div>
					</div>
					<div class="daohuo_add">
						<h5>采购计量单修改</h5>
						<div class="daohuo_add_div">
							<div class="daohuo_add_solo">
								<label>过磅单号：</label> <input type="text"
									value="${poundNote.code }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>收货单位：</label> <input type="text"
									value="${poundNote.receivedepartmentname }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>供应商：</label> <input type="text"
									value="${poundNote.suppliername }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>订单号：</label> <input type="text"
									value="${poundNote.billcode }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>物料：</label> <input type="text"
									value="${poundNote.materielname }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>车号：</label> <input type="text"
									value="${poundNote.vehicleno }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>毛重：</label> <input type="text"
									value="${poundNote.grossweight }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>皮重：</label> <input type="text"
									value="${poundNote.tareweight }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>净重：</label> <input type="text"
									value="${poundNote.netweight }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>司机：</label> <input type="text"
									value="${poundNote.drivername }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>轻车时间：</label> <input type="text"
									value="${poundNote.lighttimeStr }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>重车时间：</label> <input type="text"
									value="${poundNote.weighttimeStr }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>到货通知单：</label> <input type="text"
									value="${poundNote.noticecode }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>收料员：</label> <input id="signPerson" type="text"
									value="${poundNote.signPersonName }" userId="${poundNote.signPerson }">
							</div>
							<div class="daohuo_add_solo">
								<label>收货时间：</label> <input type="text"
									value="${poundNote.signTimeStr }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>原发数量：</label> <input type="text"
									value="${poundNote.originalnetweight }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>扣重：</label> <input type="text"
									value="${poundNote.deductionweight }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>扣杂：</label> <input type="text"
									value="${poundNote.deductionother }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>矿口：</label> <input type="text"
									value="${poundNote.minemouthname }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>堆场：</label> <input id="yard" type="text"
									value="${poundNote.yardname }" yardid="${poundNote.yardid }">
							</div>
							<div class="daohuo_add_solo">
								<label>供应商备注：</label> <input type="text" value="${poundNote.supplierremark }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>采矿点：</label> <input id="miningpoint" type="text" value="${poundNote.miningpointName }" miningpointId="${poundNote.miningpointId }" 
										materialId="${poundNote.materialid }" supplierId="${poundNote.supplierid }">
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--到货通知单end-->
			<!--tab切换的内容end-->
		</div>
	</div>
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/businessManage/poundNoteMaintain/purchasePoundNoteUpdate.js"></script>
</body>
</html>