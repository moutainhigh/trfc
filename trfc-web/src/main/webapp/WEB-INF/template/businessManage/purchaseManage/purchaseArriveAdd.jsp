<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"
	language="java"%>
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
					<li><a href="/trfc/purchaseApplication/main">采购申请单</a></li>
				</ul>
				<ul class="intel_menu">
					<li class="select"><a href="/trfc/purchaseArrive/main">到货通知单</a></li>
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
								<li id="addAndAddCardBtn"><i class="iconfont colorblue">&#xe601;</i>
									<h5>保存写卡</h5></li>
								<li id="backBtn"><a> <i class="iconfont colorblue">&#xe61e;</i>
										<h5>返回</h5>
								</a></li>
							</ul>
						</div>
					</div>
					<div class="daohuo_add">
						<h5>到货通知单新增</h5>

						<div class="daohuo_add_div">
							<div class="daohuo_add_solo">
								<label class="colorred"><em class="colorred">*</em>订单号：</label>
								<div class="input_withlogo">
									<input id="billcode" type="text" readonly> <span
										class="form-control-feedback"><i class="iconfont">&#xe608;</i></span>
								</div>
							</div>
							<div class="daohuo_add_solo">
								<label>通知单号：</label> <input id="code" type="text"
									value="${code }" readonly="readonly">
							</div>
							<div class="daohuo_add_solo">
								<label>业务日期：</label> <input id="makebilltime"
									value="${nowDate }" type="text" readonly="readonly">
							</div>
							<div class="daohuo_add_solo">
								<label>采购组织：</label> <input id="orgname" type="text"
									orgid="${orgid }" value="${orgname }" readonly="readonly">
							</div>
							<div class="daohuo_add_solo">
								<label>供应商：</label> <input id="suppliername" type="text"
									readonly="readonly">
							</div>
							<div class="daohuo_add_solo">
								<label>物料：</label> <input id="materielname" type="text"
									readonly="readonly">
							</div>
							<div class="daohuo_add_solo">
								<label>订单数量：</label> <input id="purchasesum" type="text"
									readonly="readonly">
							</div>
							<div class="daohuo_add_solo">
								<label>余量：</label> <input id="margin" type="text"
									readonly="readonly">
							</div>
							<div class="daohuo_add_solo">
								<label>单位：</label> <input id="unit" type="text"
									readonly="readonly">
							</div>
							<div class="daohuo_add_solo">
								<label>部门：</label> <input id="departmentname" type="text"
									readonly="readonly">
							</div>
							<div class="daohuo_add_solo">
								<label>供应商备注：</label> <input id="supplierremark" type="text"
									readonly="readonly">
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
											<label class="colorred"><em class="colorred">*</em>车号：</label> <select id="vehicleid"
												class="form-control">
												<option value="">请选择</option>
												<c:forEach items="${vehicle }" var="v">
													<option value="${v.id }" rfid="${v.rfid }">${v.vehicleno }</option>
												</c:forEach>
											</select> <a data-toggle="modal" data-target="#vehicleAddView"><i
												class="iconfont">&#xe680;</i></a>
										</div>
										<div class="daohuo_add_solo">
											<label>司机：</label> <select id="driverid" class="form-control">
												<option value="">请选择</option>
												<c:forEach items="${driver }" var="d">
													<option value="${d.id }" identityno="${d.identityno }">${d.name }</option>
												</c:forEach>
											</select> <a data-toggle="modal" data-target="#driverAddView"><i
												class="iconfont">&#xe680;</i></a>
										</div>
										<div class="daohuo_add_solo">
											<label>身份证号：</label> <input id="identityno" type="text" readonly="readonly">
										</div>
										<div class="daohuo_add_solo">
											<label>RFID：</label> <input id="rfid" type="text" readonly="readonly">
										</div>
										<div class="daohuo_add_solo">
											<label class="colorred"><em class="colorred">*</em>到货量：</label> <input id="arrivalamount" type="text"> <span>吨</span>
										</div>
										<div class="daohuo_add_solo">
											<label>备注：</label> <input id="remark" type="text">
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

	</div>
	<!--订单号弹出begin-->
	<div class="modal fade" id="altbill" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document"
			style="width: 1500px; height: 800px;">
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
							<label>物料：</label> <select id="materiel" class="form-control">
								<option value="">请选择</option>
								<c:forEach items="${materiel }" var="m">
									<option value="${m.id }">${m.name }</option>
								</c:forEach>
							</select>
						</div>
						<div class="dhsearch_solo">
							<label>供应商：</label> <select id="supplier" class="form-control">
								<option value="">请选择</option>
								<c:forEach items="${supplier }" var="s">
									<option value="${s.id }">${s.name }</option>
								</c:forEach>
							</select>
						</div>
						<div class="dhsearch_solo">
							<label>订单号：</label> <input id="purchaseApplicationCode"
								type="text">
						</div>
						<div class="dhsearch_solo">
							<label>开始时间：</label> <input id="starttime" type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'endtime\')}'})"
								class="Wdate" style="width: 160px" readonly />
						</div>
						<div class="dhsearch_solo">
							<label>结束时间：</label> <input id="endtime" type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'starttime\')}'})"
								class="Wdate" style="width: 160px" readonly />
						</div>
						<div class="dhsearch_solo">
							<button id="PurchaseApplicationSearchBtn" class="btn btnblue ">搜索</button>
						</div>
					</div>
					<div>
						<div class="dh_alttable" style="width: 100%;">
							<table class="table table-hover" style="width: 100%;">
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
								value="${v_code }" class="readOnlyText" readonly>
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
							<label>车辆号码：</label> <input id="v_vehicleno" type="text">
						</div>
						<div class="alt_edit_div">
							<label>车辆类型：</label> <input id="v_vehicletype" type="text">
						</div>
						<div class="alt_edit_div">
							<label>运输单位：</label> <input id="v_transportunit" type="text">
						</div>
						<div class="alt_edit_div">
							<label>最大载重： </label> <input id="v_maxweight" type="text">
						</div>
						<div class="alt_edit_div">
							<label>皮重：</label> <input id="v_tareweight" type="text">
						</div>
						<div class="alt_edit_div">
							<label>车主： </label> <input id="v_ownername" type="text">
						</div>
						<div class="alt_edit_div">
							<label>电话：</label> <input id="v_telephone" type="text">
						</div>
						<div class="alt_edit_div">
							<label>地址： </label> <input id="v_address" type="text">
						</div>
						<div class="alt_edit_div">
							<label>所属组织：</label> <input id="v_orgname" orgid="${orgid }"
								value="${orgname }" type="text" class="readOnlyText" readonly>
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
								value="${d_code }" class="readOnlyText" readonly>
						</div>
						<div class="alt_edit_div">
							<label>司机内码：</label> <input id="d_internalcode" type="text"
								value="${d_internalcode }" class="readOnlyText" readonly>
						</div>
						<div class="alt_edit_div">
							<label class="colorred"> *司机名称：</label> <input id="d_name"
								type="text">
						</div>
						<div class="alt_edit_div">
							<label>司机简称：</label> <input id="d_abbrname" type="text">
						</div>
						<div class="alt_edit_div">
							<label>地址：</label> <input id="d_address" type="text">
						</div>
						<div class="alt_edit_div">
							<label>所属组织：</label> <input id="d_orgname" orgid="${orgid }"
								value="${orgname }" type="text">
						</div>
						<div class="alt_edit_div">
							<label class="colorred"> *电话：</label> <input id="d_telephone"
								type="text">
						</div>
						<div class="alt_edit_div">
							<label class="colorred"> *身份证号：</label> <input id="d_identityno"
								type="text">
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
		src="/javascript/businessManage/purchaseManage/purchaseArriveAdd.js"></script>
	<script type="text/javascript">
		// 录入信息的车号输入框点击，出来下面的选项内容
		var daohuo_add_input = $(".daohuo_add_solo .dh_carnum input");
		daohuo_add_input.on("click", function() {
			$(".dh_carnum_sele").show();
		});
		// 录入信息的车号 下面的选项内容选中，input赋值
		var daohuo_add_li = $(".dh_carnum_sele ul li");
		daohuo_add_li.on("click", function() {
			daohuo_add_input.val($(this).text());
			$(".dh_carnum_sele").hide();
		});
		// 顶部tab切换菜单
		var $tab_li = $('.intel_menu li');
		$tab_li.click(function() {
			$(this).addClass('select').siblings().removeClass('select');
			var index = $tab_li.index(this);
			$('.intel_tabbox > .intel_tabcont').eq(index).show().siblings()
					.hide();
		});
		// 左侧宽度改变 右边改变
		var menu_ctrl = $(".left .menu");
		var menu_ctrlmini = $(".leftmini .menu2");
		var leftall = $(".left");
		var leftmini = $(".leftmini");
		menu_ctrl.on("click", function() {
			$(leftall).css("display", "none");
			leftmini.css("display", "block");
			$(".right").css("margin-left", "100px");
		});
		menu_ctrlmini.on("click", function() {
			$(leftmini).css("display", "none");
			leftall.css("display", "block");
			$(".right").css("margin-left", "200px");
		});
		// 底部tab切换菜单
		var cg_li = $('.cg_tabtit ul li');
		cg_li.click(function() {
			$(this).addClass('select').siblings().removeClass('select');
			var index = cg_li.index(this);
			$('.cg_tabbox > .cg_tabcont').eq(index).show().siblings().hide();
		});
	</script>
</body>
</html>