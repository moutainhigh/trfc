<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>补卡业务-详情</title>
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>
</head>
<body>
	<div class="it_admin">
		<jsp:include page="../../common/base/left_busi.jsp"></jsp:include>
		<div class="right">
			<jsp:include page="../../common/base/right_head_busi.jsp"></jsp:include>


			<!--tab切换的内容-->
			<div class="intel_tabbox">
				<!--采购申请单begin-->
				<div class="intel_tabcont hide"></div>
				<!--采购申请单end-->

				<!--到货通知单begin-->
				<div class="intel_tabcont ">
					<div class="intel_search">
						<div class="intel_bggray">
							<div class="intel_bgblue"></div>
						</div>
						<div class="alt_opera">
							<ul>
								<li id="fresh"><i class="iconfont colorlv">&#xe61b;</i>
									<h5>刷新</h5></li>
								<li id="cardReissue"><i class="iconfont colorblue">&#xe748;</i>
									<h5>补卡</h5></li>
								<li id="readCard"><i class="iconfont colorblue">&#xe62f;</i>
									<h5>读卡</h5></li>
								<li id="goBack"><a> <i class="iconfont colorblue">&#xe61e;</i>
										<h5>返回</h5>
								</a></li>
							</ul>
						</div>
					</div>
					<div class="daohuo_add">
						<h5>业务详细信息</h5>
						<div class="daohuo_add_div" id="business_detail">
							<div class="daohuo_add_solo">
								<label>门禁单号：</label> <input type="text" value="00"
									readOnly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>提货单号：</label> <input type="text" readOnly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>门禁时间：</label> <input type="text" readOnly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>车号：</label> <input type="text" readOnly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>物料：</label> <input type="text" readOnly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>客户：</label> <input type="text" readOnly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>预提数量：</label> <input type="text" readOnly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>RFID：</label> <input type="text" readOnly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>IC卡号：</label> <input type="text" readOnly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>喷吗：</label> <input type="text" readOnly="true">
							</div>
						</div>
					</div>
					<div class="clear"></div>
					<div>
						<div class="cg_dhadd">
							<div class="cg_tabtit">
								<ul>
									<li class="select">通知单明细</li>
									<li>磅单明细</li>
									<li>读卡信息</li>
								</ul>
							</div>
						</div>

						<div class="cg_tabbox">
							<!--tab切换的内容-->
							<div class="cg_tabcont">
								<div class="daohuo_add">
									<div class="daohuo_add_div" id="arrive_detail">
										<div class="daohuo_add_solo">
											<label>通知单号：</label> <input type="text" value="1212"
												readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>订单编号：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>业务日期：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>销售组织：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>客户：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>物料：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>车号：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>司机：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>身份证号：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>提货量：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>部门：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>状态：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>制单时间：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>制单人：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>备注：</label> <input type="text" readOnly="true">
										</div>
									</div>
								</div>
							</div>
							<div class="cg_tabcont hide">
								<div class="daohuo_add">
									<div class="daohuo_add_div" id="pound_detail">
										<div class="daohuo_add_solo">
											<label>过磅单号：</label> <input type="text" value="1212"
												readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>收货单位：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>供应商：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>物料：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>仓库：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>车号：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>毛重：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>皮重：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>净重：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>司机：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>司机身份证：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>过磅员：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>轻车时间：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>重车时间：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>提货单号：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>订单：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>订单量：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>订单单价：</label> <input type="text" readOnly="true">
										</div>
									</div>
								</div>
							</div>
							<div class="cg_tabcont hide">
								<div class="daohuo_add">
									<div class="daohuo_add_div" id="icard_detail">
										<div class="daohuo_add_solo">
											<label>射频卡号：</label> <input type="text" value="1212"
												readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>车辆编号：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>车辆名称：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>供应商：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>物料：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>物料状态：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>到货单号：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>到货量：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>发卡状态：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>业务类型：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>手持机确认：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>矿口：</label> <input type="text" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>备注：</label> <input type="text" readOnly="true">
										</div>
									</div>
								</div>
							</div>
							<!--tab切换的内容end-->
						</div>
					</div>
				</div>


			</div>
			<!--到货通知单end-->


		</div>

	</div>
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript" src="${staticBasePath}/js/cardReader.js"></script>
	<script type="text/javascript"
		src="/javascript/businessManage/cardManage/card_detail.js"></script>

</body>
</html>