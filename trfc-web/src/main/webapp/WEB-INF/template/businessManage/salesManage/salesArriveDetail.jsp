<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售提货通知单详情</title>
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>
</head>
<body>
	<div class="it_admin">
		<jsp:include page="../../common/base/left_busi.jsp"></jsp:include>
		<div class="right">
			<jsp:include page="../../common/base/right_head_busi.jsp"></jsp:include>
			<div class="intel_tab">
				<!--tab切换标题-->
				<ul class="intel_menu">
					<li><a href="/trfc/getMainApplication()/main">销售申请单</a></li>
					<li class="select"><a href="/trfc/salesArrive/main">提货通知单</a></li>
					<li><a href="/trfc/salesVehicle/main">销售车辆状态</a></li>
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
								<li id="readCardBtn"><i class="iconfont colorblue">&#xe61d;</i>
									<h5>读卡</h5></li>
								<li id="backBtn"><a> <i class="iconfont colorblue">&#xe61e;</i>
										<h5>返回</h5>
								</a></li>
							</ul>
						</div>
					</div>
					<div class="daohuo_add">
						<h5>提货通知单详细信息</h5>
						<div class="daohuo_add_div">
							<div class="daohuo_add_solo">
								<label>订单编号：</label> <input value="${salesArrive.billcode }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>通知单号：</label> <input value="${salesArrive.code }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>制单日期：</label> <input
									value="${salesArrive.makebilltimeStr }" type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>客户：</label> <input
									value="${salesArrive.getMainApplication().customername }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>区域码：</label> <input
									value="${salesArrive.getMainApplication().channelcode }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>销售组织：</label> <input
									value="${salesArrive.getMainApplication().orgname }" type="text"
									readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>物料：</label> <input
									value="${salesArrive.getMainApplicationDetail().materielname }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>部门：</label> <input
									value="${salesArrive.getMainApplication().departmentname }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>单位：</label> <input
									value="${salesArrive.getMainApplicationDetail().unit }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>订单数量：</label> <input
									value="${salesArrive.getMainApplicationDetail().salessum }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>车号：</label> <input
									value="${salesArrive.vehicleno }" type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>RFID：</label> <input value="${salesArrive.vehiclerfid }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>司机：</label> <input value="${salesArrive.drivername }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>身份证号：</label> <input
									value="${salesArrive.driveridentityno }" type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>提货量：</label> <input value="${salesArrive.takeamount }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>业务日期：</label> <input
									value="${salesArrive.getMainApplication().makebilltimeStr }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>制单人：</label> <input value="${salesArrive.makebillname }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>备注：</label> <input value="${salesArrive.remarks }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<c:set var="s" value="${salesArrive.status }"></c:set>
								<label>状态：</label> <input
									value="${s eq '0' ? '未入厂' : s eq '1' ? '空车' : s eq '2' ? '重车' : s eq '3' ? '作废' : s eq '4' ? '发卡' : s eq '5' ? '出厂' : s eq '6' ? '入厂' : s eq '7' ? '装车' : '' }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<c:set var="sc" value="${salesArrive.source }"></c:set>
								<label>来源：</label> <input
									value="${sc eq '0' ? '业务平台' : sc eq '1' ? '客商平台' : sc eq '2' ? '客商APP' : '' }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>作废/出场时间：</label> <input
									value="${salesArrive.abnormaltimeStr }" type="text" readonly>
							</div>
						</div>
					</div>
					<div>
						<div class="cg_dhadd">
							<div class="cg_tabtit">
								<ul>
									<li class="select">订单明细</li>
									<li>磅单明细</li>
									<li>读卡信息</li>
								</ul>
							</div>
						</div>
						<div class="cg_tabbox">
							<div class="cg_tabcont">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>序号</th>
											<th>订单编号</th>
											<th>客户</th>
											<th>物料</th>
											<th>组织机构</th>
											<th>预提量</th>
											<th>订单量</th>
											<th>订单日期</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${salesArrive.listApplication }" var="application" varStatus="status">
											<tr>
												<td>${status.index + 1 }</td>
												<td>${application.code }</td>
												<td>${application.customername }</td>
												<td>${application.list[0].materielname }</td>
												<td>${application.orgname }</td>
												<td>${salesArrive.takeamount }</td>
												<td>${application.list[0].salessum }</td>
												<td>${application.billtimeStr }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div class="cg_tabcont hide"></div>
							<div class="cg_tabcont hide"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/businessManage/salesManage/salesArriveDetail.js"></script>
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