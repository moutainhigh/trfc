<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购到货通知单修改</title>
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
								<li id="refreshBtn"><i class="iconfont colorlv">&#xe61b;</i>
									<h5>刷新</h5></li>
								<li id="updateBtn"><i class="iconfont colorblue">&#xe61d;</i>
									<h5>保存</h5></li>
								<li id="backBtn"><a> <i class="iconfont colorblue">&#xe61e;</i>
										<h5>返回</h5>
								</a></li>
							</ul>
						</div>
					</div>
					<div class="daohuo_add">
						<h5>到货通知单修改</h5>
						<input id="purchaseArriveId" type="hidden"
							value="${purchaseArrive.id }" />
						<div class="daohuo_add_div">
							<div class="daohuo_add_solo">
								<label class="colorred"><em class="colorred">*</em>订单号：</label>
								<div class="input_withlogo">
									<input id="billcode" type="text"
										value="${purchaseArrive.billcode }"
										billid="${purchaseArrive.billid }"
										billdetailid="${purchaseArrive.billdetailid }" readonly="readonly" placeholder="请选择订单">
									<span class="form-control-feedback"><i class="iconfont">&#xe608;</i></span>
								</div>
							</div>
							<div class="daohuo_add_solo">
								<label>通知单号：</label> <input id="code" type="text"
									value="${purchaseArrive.code }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>业务日期：</label> <input id="makebilltime"
									value="${nowDate }" type="text" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>采购组织：</label> <input id="orgname" type="text"
									value="${orgname }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>供应商：</label> <input id="suppliername" type="text"
									value="${purchaseArrive.purchaseApplicationResp.suppliername }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>物料：</label> <input id="materielname" type="text"
									value="${purchaseArrive.purchaseApplicationDetailResp.materielname }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>订单数量：</label> <input id="purchasesum" type="text"
									value="${purchaseArrive.purchaseApplicationDetailResp.purchasesum }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>余量：</label> <input id="margin" type="text"
									value="${purchaseArrive.margin }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>单位：</label> <input id="unit" type="text"
									value="${purchaseArrive.unit }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>部门：</label> <input id="departmentname" type="text"
									value="${purchaseArrive.purchaseApplicationResp.departmentname }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>供应商备注：</label> <input id="supplierremark" type="text"
									value="${purchaseArrive.purchaseApplicationResp.supplierremark }"
									readonly="true">
							</div>
						</div>
					</div>
					<div class="clear"></div>
					<div>
						<div class="cg_dhadd">
							<div class="cg_tabtit">
								<ul>
									<li class="select">录入信息</li>
								</ul>
							</div>
						</div>
						<div class="cg_tabbox">
							<!--tab切换的内容-->
							<div class="cg_tabcont">
								<div class="daohuo_add">
									<div class="daohuo_add_div">
										<div class="daohuo_add_solo">
											<label class="colorred"><em class="colorred">*</em>车号：</label>
											<input id="vehicle" type="text" placeholder="请选择车辆" vehicleid="${purchaseArrive.vehicleid }" value="${purchaseArrive.vehicleno }" /> <a
												data-toggle="modal" data-target="#vehicleAddView"><i
												class="iconfont">&#xe680;</i></a>
										</div>
										<div class="daohuo_add_solo">
											<label>司机：</label> <input id="driver" type="text" placeholder="请选择司机" driverid="${purchaseArrive.driverid }" value="${purchaseArrive.drivername }" />
											<a data-toggle="modal" data-target="#driverAddView"><i
												class="iconfont">&#xe680;</i></a>
										</div>
										<div class="daohuo_add_solo">
											<label>身份证号：</label> <input id="identityno" type="text"
												value="${purchaseArrive.driveridentityno }"
												readonly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>RFID：</label> <input id="rfid" type="text"
												value="${purchaseArrive.vehiclerfid }" readonly="true">
										</div>
										<div class="daohuo_add_solo">
											<label class="colorred"><em class="colorred">*</em>到货量：</label>
											<input id="arrivalamount" type="text"
												value="${purchaseArrive.arrivalamount }" placeholder="请输入到货量"> <span>吨</span>
										</div>
										<div class="daohuo_add_solo">
											<label>备注：</label> <input id="remark" type="text"
												value="${purchaseArrive.remark }">
										</div>
									</div>
								</div>
							</div>
							<!--tab切换的内容end-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--订单号弹出begin-->
	<div class="modal fade" id="altbill" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document"
			style="width: 75%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="alt_head">
						<h5>选采购订单</h5>
					</div>
				</div>
				<div class="modal-body">
					<div class="dhadd_search">
						<div class="dhsearch_solo">
							<label>物料：</label> <input id="materiel" type="text" placeholder="请选择物料" />
							</select>
						</div>
						<div class="dhsearch_solo">
							<label>供应商：</label> <input id="supplier" type="text" placeholder="请选择供应商" />
						</div>
						<div class="dhsearch_solo">
							<label>订单号：</label> <input id="purchaseApplicationCode"
								type="text" placeholder="请输入订单号">
						</div>
						<div class="dhsearch_solo">
							<label>开始时间：</label> <input id="starttime" type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'endtime\')}'})"
								class="Wdate" style="width: 160px" readonly placeholder="请选择开始时间"/>
						</div>
						<div class="dhsearch_solo">
							<label>结束时间：</label> <input id="endtime" type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'starttime\')}'})"
								class="Wdate" style="width: 160px" readonly placeholder="请选择结束时间"/>
						</div>
						<div class="dhsearch_solo">
							<button id="PurchaseApplicationSearchBtn" class="btn btnblue ">搜索</button>
						</div>
					</div>
					<div style="width: 100%;">
						<div class="dh_alttable">
							<table class="table table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>采购订单号</th>
										<th>供应商名称</th>
										<th>物料</th>
										<th>物料规格</th>
										<th>物料类型</th>
										<th>订单数量</th>
										<th>余量</th>
										<th>入库占用量</th>
										<th>未入库占用量</th>
										<th>到货占用量</th>
										<th>采购组织</th>
										<th>订单日期</th>
										<th>采购部门</th>
										<th>矿口</th>
										<th>司机身份验证</th>
										<th>备注</th>
									</tr>
								</thead>
								<tbody id="purchaseApplicationBody">

								</tbody>
							</table>
						</div>
					</div>
					<!--分页效果开始-->
					<div class="page">
						<div class="page_date">
							<label>数据共：</label><i id="total" class="colorred">100</i><label>条</label>
						</div>
						<div class="page_date">
							<label>跳到第：</label> <input id="jumpPageNo" type="text"> <label>页</label>
							<button id="jumpPageNoBtn" class="btn btn-default">确定</button>
						</div>
						<div class="page_date">
							<label>每页记录：</label> <select id="pageSize" class="form-control">
								<option value="10">10</option>
								<option value="20">20</option>
								<option value="30">30</option>
							</select>
						</div>
						<div class="page_btn" id="pagination"></div>
					</div>
					<!--分页效果结束-->
				</div>
			</div>
		</div>
	</div>
	<!--订单号弹出end-->
	<!--车号新增begin-->
	<div class="modal fade" id="vehicleAddView" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width: 750px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="alt_head">
						<h5>车辆管理--新增</h5>
					</div>
				</div>
				<div class="modal-body">
					<div class="alt_edit">
						<div class="alt_edit_div">
							<label>车辆编号：</label> <input id="v_code" type="text"
								value="${v_code }" readonly="true">
						</div>
						<div class="alt_edit_div">
							<label>运输类型：</label> <select id="v_transporttype"
								class="form-control">
								<option value="0">非倒运</option>
								<option value="1">倒运</option>
							</select>

						</div>
						<div class="alt_edit_div">
							<label class="colorred">*车辆号码：</label> <input id="v_vehicleno" type="text" placeholder="请输入车牌号码">
						</div>
						<div class="alt_edit_div">
							<label>车辆类型：</label> <input id="v_vehicletype" type="text" placeholder="请输入车辆类型">
						</div>
						<div class="alt_edit_div">
							<label>运输单位：</label> <input id="v_transportunit" type="text" placeholder="请输入运输单位">
						</div>
						<div class="alt_edit_div">
							<label>最大载重： </label> <input id="v_maxweight" type="text" placeholder="请输入最大载重">
						</div>
						<div class="alt_edit_div">
							<label>皮重：</label> <input id="v_tareweight" type="text" placeholder="请输入皮重">
						</div>
						<div class="alt_edit_div">
							<label>车主： </label> <input id="v_ownername" type="text" placeholder="请输入车主">
						</div>
						<div class="alt_edit_div">
							<label>电话：</label> <input id="v_telephone" type="text" placeholder="请输入电话">
						</div>
						<div class="alt_edit_div">
							<label>地址： </label> <input id="v_address" type="text" placeholder="请输入地址">
						</div>
						<div class="alt_edit_div">
							<label>所属组织：</label> <input id="v_orgname" orgid="${orgid }"
								value="${orgname }" type="text" readonly="true">
						</div>
						<div class="alt_edit_div">
							<label>有效性： </label> <input id="v_isvalid" type="checkbox"
								checked>
						</div>
						<div class="alt_edit_textarea">
							<label>备注： </label>
							<textarea id="v_remarks" class="form-control" rows="1"
								style="height: 60px;"></textarea>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button id="addVehicleCommitBtn" type="button"
						class="btn btn-primary">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!--车号新增end-->
	<!--司机新增begin-->
	<div class="modal fade" id="driverAddView" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width: 750px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="alt_head">
						<h5>司机管理--新增</h5>
					</div>
				</div>
				<div class="modal-body">
					<div class="alt_caradd">
						<div class="alt_edit_div">
							<label>司机编号：</label> <input id="d_code" type="text"
								value="${d_code }" readonly="true">
						</div>
						<div class="alt_edit_div">
							<label>司机内码：</label> <input id="d_internalcode" type="text"
								value="${d_internalcode }" readonly="true">
						</div>
						<div class="alt_edit_div">
							<label class="colorred"> *司机名称：</label> <input id="d_name"
								type="text" placeholder="请输入司机名称">
						</div>
						<div class="alt_edit_div">
							<label>司机简称：</label> <input id="d_abbrname" type="text" placeholder="请输入司机简称">
						</div>
						<div class="alt_edit_div">
							<label>地址：</label> <input id="d_address" type="text" placeholder="请输入地址">
						</div>
						<div class="alt_edit_div">
							<label>所属组织：</label> <input id="d_orgname" orgid="${orgid }"
								value="${orgname }" type="text">
						</div>
						<div class="alt_edit_div">
							<label class="colorred"> *电话：</label> <input id="d_telephone"
								type="text" placeholder="请输入电话">
						</div>
						<div class="alt_edit_div">
							<label class="colorred"> *身份证号：</label> <input id="d_identityno"
								type="text" placeholder="请输入身份证号" maxlength="18">
						</div>
						<div class="alt_edit_div">
							<label>有效性： </label> <input id="d_isvalid" type="checkbox"
								checked>
						</div>
						<div class="alt_edit_textarea">
							<label>备注： </label>
							<textarea id="d_remarks" class="form-control" rows="1"
								style="height: 60px;"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button id="addDriverCommitBtn" type="button"
						class="btn btn-primary">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!--司机新增end-->
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/businessManage/purchaseManage/purchaseArriveUpdate.js"></script>
</body>
</html>