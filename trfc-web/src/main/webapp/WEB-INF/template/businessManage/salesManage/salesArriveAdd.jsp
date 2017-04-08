<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增提货通知单</title>
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
					<li><a href="/trfc/salesApplication/main">销售申请单</a></li>
					<li class="select"><a href="/trfc/salesArrive/main">提货通知单</a></li>
					<li><a href="/trfc/salesVehicle/main">销售车辆状态</a></li>
				</ul>
			</div>
			<!--tab切换的内容-->
			<div class="intel_tabbox">
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
								<li id="addAndAddCardBtn"><i class="iconfont colorblue">&#xe601;</i>
									<h5>保存写卡</h5></li>
								<li id="backBtn"><a> <i class="iconfont colorblue">&#xe61e;</i>
										<h5>返回</h5>
								</a></li>
							</ul>
						</div>
					</div>
					<div class="daohuo_add">
						<h5>提货通知单新增</h5>

						<div class="daohuo_add_div">
							<div class="daohuo_add_solo">
								<label class="colorred"><em class="colorred">*</em>订单号：</label>
								<div class="input_withlogo">
									<input id="billcode" type="text" readonly="readonly"
										placeholder="请选择订单"> <span
										class="form-control-feedback"><i class="iconfont">&#xe608;</i></span>
								</div>
								<input id="maindeduction" type="checkbox"><span
									class="ml5">主单扣量 </span>
							</div>
							<div class="daohuo_add_solo">
								<label>通知单号：</label> <input id="code" type="text"
									value="${code }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>制单日期：</label> <input id="createtimeStr" type="text"
									value="${createtimeStr }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>客户：</label> <input id="customername" type="text"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>区域码：</label> <input id="channelcode" type="text"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>销售组织：</label> <input id="orgname" orgid="${orgid }"
									value="${orgname }" type="text" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>物料：</label> <input id="materielname" type="text"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>部门：</label> <input id="departmentname" type="text"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>单位：</label> <input id="unit" type="text" value="吨"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>订单数量：</label> <input id="salessum" type="text"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>余量：</label> <input id="margin" type="text"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>业务日期：</label> <input id="billtime" type="text"
									readonly="true">
							</div>
						</div>
					</div>
					<div>
						<div class="cg_dhadd">
							<div class="cg_tabtit">
								<ul>
									<li class="select">录入信息</li>
									<li>参照信息</li>
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
											<input id="vehicle" type="text" placeholder="请选择车辆" /> <a
												data-toggle="modal" data-target="#vehicleAddView"><i
												class="iconfont">&#xe680;</i></a>
										</div>
										<div class="daohuo_add_solo">
											<label>司机：</label> <input id="driver" type="text"
												placeholder="请选择司机" /> <a data-toggle="modal"
												data-target="#driverAddView"><i class="iconfont">&#xe680;</i></a>
										</div>
										<div class="daohuo_add_solo">
											<label>身份证号：</label> <input id="identityno" type="text"
												readonly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>RFID：</label> <input id="rfid" type="text"
												readonly="true">
										</div>

										<div class="daohuo_add_solo">
											<label class="colorred"><em class="colorred">*</em>提货量：</label>
											<input id="takeamount" type="text" placeholder="请输入提货量" /> <span>吨</span>

										</div>
										<div class="daohuo_add_solo">
											<label>备注：</label> <input id="remarks" type="text">
										</div>
										<div class="daohuo_add_solo">
											<label>喷码：</label> <input id="spraycode" type="text"
												readonly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>出厂编号：</label> <input id="serialnumber" type="text"
												readonly="true">
										</div>
										<div class="daohuo_add_solo"></div>
									</div>
								</div>
							</div>
							<div class="cg_tabcont hide">
								<div class=" ">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th>序号</th>
												<th>销售订单号</th>
												<th>订单类型</th>
												<th>订单日期</th>
												<th>物料</th>
												<th>单位</th>
												<th>订单数量</th>
												<th>余量</th>
												<th>预提量</th>
												<th>销售组织</th>
												<th>客户名称</th>
												<th>部门</th>
												<th>业务员</th>
												<th>制单人</th>
											</tr>
										</thead>
										<tbody id="salesApplicationDetailBody">
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--订单号弹出begin-->
	<div class="modal fade" id="salesApplication" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document"
			style="width: 1500px; height: 800px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="alt_head">
						<h5>选销售订单</h5>
					</div>
				</div>
				<div class="modal-body">
					<div class="dhadd_search">
						<div class="dhsearch_solo">
							<label>物料：</label> <input id="materiel" type="text"
								placeholder="请选择物料" />
						</div>
						<div class="dhsearch_solo">
							<label>客户：</label> <input id="customer" type="text"
								placeholder="请选择客户" />
						</div>
						<div class="dhsearch_solo">
							<label>订单号：</label> <input id="applicationcode" type="text"
								placeholder="请输入订单号">
						</div>
						<div class="dhsearch_solo">
							<label>开始时间：</label> <input id="starttime" type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00'})"
								class="Wdate" style="width: 160px" readonly
								placeholder="请选择开始时间" />
						</div>
						<div class="dhsearch_solo">
							<label>结束时间：</label> <input id="endtime" type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00'})"
								class="Wdate" style="width: 160px" readonly
								placeholder="请选择结束时间" />
						</div>
						<div class="dhsearch_solo">
							<button id="searchBtn" class="btn btnblue ">搜索</button>
							<button id="clearBtn" class="btn btnblue ">清空</button>
						</div>
					</div>
					<div>
						<div class="dh_alttable" style="width: 100%;">
							<table class="table table-hover" style="width: 100%;">
								<thead>
									<tr>
										<th></th>
										<th>销售订单号</th>
										<th>订单类型</th>
										<th>客户名称</th>
										<th>物料名称</th>
										<th>订单数量</th>
										<th>余量</th>
										<th>出库占用量</th>
										<th>未出库占用量</th>
										<th>预提占用量</th>
										<th>销售组织</th>
										<th>订单日期</th>
										<th>部门</th>
										<th>业务员</th>
										<th>制单人</th>
										<th>区域码</th>
									</tr>
								</thead>
								<tbody id="salesApplicationBody">
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
							<label>跳到第：</label> <input id="jumpPageNo" type="text" value="1">
							<label>页</label>
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
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						id="returnApplication">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
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
								<option value="">请选择</option>
								<option value="0">非倒运</option>
								<option value="1">倒运</option>
							</select>

						</div>
						<div class="alt_edit_div">
							<label class="colorred">*车辆号码：</label> <input id="v_vehicleno"
								type="text" placeholder="请输入车牌号码">
						</div>
						<div class="alt_edit_div">
							<label>车辆类型：</label> <input id="v_vehicletype" type="text"
								placeholder="请输入车辆类型">
						</div>
						<div class="alt_edit_div">
							<label>运输单位：</label> <input id="v_transportunit" type="text"
								placeholder="请输入运输单位">
						</div>
						<div class="alt_edit_div">
							<label>最大载重： </label> <input id="v_maxweight" type="text"
								placeholder="请输入最大载重">
						</div>
						<div class="alt_edit_div">
							<label>皮重：</label> <input id="v_tareweight" type="text"
								placeholder="请输入皮重">
						</div>
						<div class="alt_edit_div">
							<label>车主： </label> <input id="v_ownername" type="text"
								placeholder="请输入车主">
						</div>
						<div class="alt_edit_div">
							<label>电话：</label> <input id="v_telephone" type="text"
								placeholder="请输入电话">
						</div>
						<div class="alt_edit_div">
							<label>地址： </label> <input id="v_address" type="text"
								placeholder="请输入地址">
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
							<label>司机简称：</label> <input id="d_abbrname" type="text"
								placeholder="请输入司机简称">
						</div>
						<div class="alt_edit_div">
							<label>地址：</label> <input id="d_address" type="text"
								placeholder="请输入地址">
						</div>
						<div class="alt_edit_div">
							<label>所属组织：</label> <input id="d_orgname" orgid="${orgid }"
								value="${orgname }" type="text" readonly="true">
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
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/quality/purchase/cardReader.js"></script>
	<script type="text/javascript"
		src="/javascript/businessManage/salesManage/salesArriveAdd.js"></script>
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