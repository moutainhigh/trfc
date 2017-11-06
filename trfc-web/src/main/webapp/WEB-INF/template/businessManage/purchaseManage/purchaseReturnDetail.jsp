<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购退货通知单详情</title>
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>
</head>
<body>
	<div class="it_admin">
		<jsp:include page="../../common/base/left_busi.jsp"></jsp:include>
		<div class="right">
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
								<li id="refreshBtn"><i class="iconfont colorlv">&#xe61b;</i>
									<h5>刷新</h5></li>
								<li id="backBtn"><a> <i class="iconfont colorblue">&#xe61e;</i>
										<h5>返回</h5>
								</a></li>
							</ul>
						</div>
					</div>
					<div class="daohuo_add">
						<h5>退货通知单详细信息</h5>
						<div class="daohuo_add_div">
							<div class="daohuo_add_solo">
								<label>过磅单号：</label> <input value="${purchaseReturn.poundnotecode }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>退货单号：</label> <input value="${purchaseReturn.code }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>重车日期：</label> <input value="${purchaseReturn.poundNoteResp.weighttimeStr }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>供应商：</label> <input
									value="${purchaseReturn.purchaseApplicationResp.suppliername }" type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>物料：</label> <input
									value="${purchaseReturn.purchaseApplicationDetailResp.materielname }" type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>退货组织：</label> <input
									value="${purchaseReturn.purchaseApplicationResp.orgname }" type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>订单号：</label> <input
									value="${purchaseReturn.purchaseApplicationResp.code }" type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>到货单号：</label> <input
									value="${purchaseReturn.poundNoteResp.billcode }" type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>净重：</label> <input
									value="${purchaseReturn.poundNoteResp.netweight }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>车号：</label> <input
									value="${purchaseReturn.vehicleno }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>司机：</label> <input
									value="${purchaseReturn.drivername }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>身份证号：</label> <input
									value="${purchaseReturn.driveridentityno }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>退货量：</label> <input
									value="${purchaseReturn.arrivalamount }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>部门：</label> <input
									value="${purchaseReturn.purchaseApplicationResp.departmentname }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>备注：</label> <input value="${purchaseReturn.remark }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<c:set var="s" value="${purchaseReturn.status }"></c:set>
								<label>状态：</label> <input
									value="${purchaseReturn.forceOutFactory == '1' ? '强制出厂' : s eq '0' ? '未入厂' : s eq '1' ? '空车' : s eq '2' ? '重车' : s eq '3' ? '作废' : s eq '4' ? '发卡' : s eq '5' ? '出厂' : s eq '6' ? '入厂' : s eq '7' ? '装车' : '' }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<c:set var="sc" value="${purchaseReturn.source }"></c:set>
								<label>来源：</label> <input
									value="${sc eq '0' ? '业务平台' : sc eq '1' ? '客商平台' : sc eq '2' ? '客商APP' : '' }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>作废/出场时间：</label> <input
									value="${purchaseReturn.abnormaltimeStr }" type="text" readonly>
							</div>
						</div>
					</div>
					<div>
						<div class="cg_dhadd">
							<div class="cg_tabtit">
								<ul>
									<li class="select">磅单明细</li>
									<li>读卡信息</li>
								</ul>
							</div>
						</div>
						<div class="cg_tabbox">
							<div class="cg_tabcont">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>榜单号</th>
											<th>车牌号</th>
											<th>毛重</th>
											<th>皮重</th>
											<th>净重</th>
											<th>轻车时间</th>
											<th>重车时间</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${purchaseReturn.poundNoteResp.code }</td>
											<td>${purchaseReturn.vehicleno }</td>
											<td>${purchaseReturn.poundNoteResp.grossweight }</td>
											<td>${purchaseReturn.poundNoteResp.tareweight }</td>
											<td>${purchaseReturn.poundNoteResp.netweight }</td>
											<td>${purchaseReturn.poundNoteResp.lighttimeStr }</td>
											<td>${purchaseReturn.poundNoteResp.weighttimeStr }</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="cg_tabcont hide">
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/businessManage/purchaseManage/purchaseReturnDetail.js"></script>
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