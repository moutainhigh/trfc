<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>

<!-- 引用公共header部分 -->
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>
<script type="text/javascript" src="${staticBasePath }/js/dateutil.js"></script>

</head>
<body>
	<div class="it_admin">
		<div class="left ">
			<div class="user">
				<a href="#" data-toggle="dropdown" data-target="#menu-messages"
					class="dropdown-toggle"> <img src="${basePath }/images/tx.jpg"
					class="img-circle"> <label>超级管理员</label> <i class="iconfont">&#xe602;</i>
				</a>
				<ul class="dropdown-menu">
					<li><a data-toggle="modal" data-target="#account"><i
							class="iconfont">&#xe60e;</i>个人资料</a></li>
					<li class="divider"></li>
					<li><a data-toggle="modal" data-target="#password"><i
							class="iconfont">&#xe60d;</i> 设置</a></li>
				</ul>
			</div>
			<div class="menu">
				<label>菜单</label> <i class="iconfont fr">&#xe61a;</i>
			</div>
			<div class="menu_collap">
				<ul class="typelist ">
					<a href="#ityewu" data-toggle="collapse" class="menu_collap_tit">
						<label>业务管理</label> <span><i class="iconfont">&#xe604;</i></span>
					</a>

					<div class="in" id="ityewu">
						<li class="active"><a href="../cg/cg_index.html"> <i
								class="iconfont">&#xe617;</i> <label>采购管理</label>
						</a></li>
						<li><a href="../sell/sell_apply.html"> <i
								class="iconfont">&#xe615;</i> <label>销售管理</label>
						</a></li>
						<li><a> <i class="iconfont">&#xe614;</i> <label>其他业务</label>
						</a></li>
						<li><a> <i class="iconfont">&#xe618;</i> <label>质控管理</label>
						</a></li>
					</div>
					<a href="#itdangan" data-toggle="collapse" class="menu_collap_tit">
						<label>基础档案</label> <span><i class="iconfont">&#xe604;</i></span>
					</a>

					<div class="in" id="itdangan">
						<li><a href="../file_nc/client.html"> <i class="iconfont">&#xe617;</i>
								<label>NC档案</label>
						</a></li>
						<li><a href="../file_jil/car.html"> <i class="iconfont">&#xe617;</i>
								<label>计量档案</label>
						</a></li>
						<li><a href="../file-other/car.html"> <i class="iconfont">&#xe617;</i>
								<label>其他档案</label>
						</a></li>
					</div>
				</ul>
			</div>
		</div>
		<div class="leftmini hide">
			<div class="user">
				<a href="#" data-toggle="dropdown" data-target="#menu-messages"
					class="dropdown-toggle"> <img src="${basePath }/images/tx.jpg"
					class="img-circle">
				</a>
				<ul class="dropdown-menu">
					<li><a data-toggle="modal" data-target="#account"><i
							class="iconfont">&#xe60e;</i>个人资料</a></li>
					<li class="divider"></li>
					<li><a data-toggle="modal" data-target="#password"><i
							class="iconfont">&#xe60d;</i> 设置</a></li>
				</ul>
			</div>
			<div class="menu2">
				<i class="iconfont">&#xe635;</i>
			</div>
			<ul class="typelist">
				<li class="active" data-toggle="tooltip" data-placement="right"
					title="Tooltip on left"><i class="iconfont">&#xe617;</i></li>
				<li><i class="iconfont">&#xe615;</i></li>
				<li><i class="iconfont">&#xe614;</i></li>
				<li><i class="iconfont">&#xe618;</i></li>
				<li><i class="iconfont">&#xe619;</i></li>
				<li><i class="iconfont">&#xe613;</i></li>
				<li><i class="iconfont">&#xe612;</i></li>
				<li><i class="iconfont">&#xe610;</i></li>
				<li><i class="iconfont">&#xe60f;</i></li>
				<li><i class="iconfont">&#xe611;</i></li>
			</ul>
		</div>
		<div class="right">
			<div class="intel_tab">
				<!--tab切换标题-->
				<ul class="intel_menu">
					<li class="select">销售申请单</li>
					<li>提货通知单</li>
					<li>销售车辆状态</li>
				</ul>
				<!--tab切换标题end-->
				<div class="top_opera">
					<a><i class="iconfont" data-toggle="tooltip"
						data-placement="left" title="首页">&#xe605;</i></a> <a><i
						class="iconfont" data-toggle="tooltip" data-placement="left"
						title="控制面板">&#xe606;</i></a> <a><i class="iconfont"
						data-toggle="tooltip" data-placement="left" title="退出">&#xe607;</i></a>
				</div>
			</div>
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
								<label><em class="colorred">*</em>订单号：</label>
								<div class="input_withlogo">
									<input id="billcode" type="text" readonly class="readOnlyText">
									<span class="form-control-feedback"><i class="iconfont">&#xe608;</i></span>
								</div>
								<input type="checkbox"><span class="ml5">主单扣量 </span>
							</div>
							<div class="daohuo_add_solo">
								<label>通知单号：</label> <input id="code" type="text"
									value="${code }" readonly class="readOnlyText">
							</div>
							<div class="daohuo_add_solo">
								<label>制单日期：</label> <input id="createtimeStr" type="text" value="${createtimeStr }" readonly
									class="readOnlyText">
							</div>
							<div class="daohuo_add_solo">
								<label>客户：</label> <input id="customername" type="text" readonly
									class="readOnlyText">
							</div>
							<div class="daohuo_add_solo">
								<label>区域码：</label> <input id="channelcode" type="text" readonly
									class="readOnlyText">
							</div>
							<div class="daohuo_add_solo">
								<label>销售组织：</label> <input id="orgname" orgid="${orgid }" value="${orgname }" type="text" readonly
									class="readOnlyText">
							</div>
							<div class="daohuo_add_solo">
								<label>物料：</label> <input id="materielname" type="text" readonly
									class="readOnlyText">
							</div>
							<div class="daohuo_add_solo">
								<label>部门：</label> <input id="departmentname" type="text"
									readonly class="readOnlyText">
							</div>
							<div class="daohuo_add_solo">
								<label>单位：</label> <input id="unit" type="text" value="吨" readonly
									class="readOnlyText">
							</div>
							<div class="daohuo_add_solo">
								<label>订单数量：</label> <input id="salessum" type="text" readonly
									class="readOnlyText">
							</div>
							<div class="daohuo_add_solo">
								<label>余量：</label> <input id="" type="text" readonly
									class="readOnlyText">
							</div>
							<div class="daohuo_add_solo">
								<label>业务日期：</label> <input id="billtime" type="text" readonly
									class="readOnlyText">
							</div>
						</div>
					</div>
					<div class="clear"></div>
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
											<label class="colorred">车号：</label> <select id="vehicleid"
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
											<label>身份证号：</label> <input id="identityno" type="text" readonly
												class="readOnlyText">
										</div>
										<div class="daohuo_add_solo">
											<label>RFID：</label> <input id="rfid" type="text" readonly
												class="readOnlyText">
										</div>

										<div class="daohuo_add_solo">
											<label>提货量：</label> <input id="takeamount" type="text"> <span>吨</span>

										</div>
										<div class="daohuo_add_solo">
											<label>备注：</label> <input id="remarks" type="text">
										</div>
										<div class="daohuo_add_solo">
											<label>喷码：</label> <input id="spraycode" type="text" readonly
												class="readOnlyText">
										</div>
										<div class="daohuo_add_solo">
											<label>出厂编号：</label> <input id="serialnumber" type="text" readonly
												class="readOnlyText">
										</div>
										<div class="daohuo_add_solo">
											<label>IC卡号：</label> <input type="text" readonly
												class="readOnlyText">
										</div>
									</div>
								</div>
							</div>
							<div class="cg_tabcont hide">
								<div class=" ">
									<table class="table table-bordered">
										<thead>
											<tr>
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

	</div>
	<!--订单号弹出begin-->
	<div class="modal fade" id="salesApplication" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document"
			style="width: 1000px; height: 800px;">
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
							<label>物料：</label> <select id="materielid" class="form-control">
								<option value="">请选择</option>
								<c:forEach items="${materiel }" var="m">
									<option value="${m.id }">${m.name }</option>
								</c:forEach>
							</select>
						</div>
						<div class="dhsearch_solo">
							<label>客户：</label> <select id="customerid" class="form-control">
								<option value="">请选择</option>
								<c:forEach items="${customer }" var="c">
									<option value="${c.id }">${c.name }</option>
								</c:forEach>
							</select>
						</div>
						<div class="dhsearch_solo">
							<label>订单号：</label> <input id="billcode" type="text">
						</div>
						<div class="dhsearch_solo">
							<label>开始时间：</label> <input id="starttime" type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00'})"
								class="Wdate" style="width: 160px" readonly />
						</div>
						<div class="dhsearch_solo">
							<label>结束时间：</label> <input id="endtime" type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00'})"
								class="Wdate" style="width: 160px" readonly />
						</div>
						<div class="dhsearch_solo">
							<button id="searchBtn" class="btn btnblue ">搜索</button>
						</div>
					</div>
					<div>
						<div class="dh_alttable">
							<table class="table table-hover">
								<thead>
									<tr>
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
							<label>车辆编号：</label> <input id="v_code" type="text" value="${v_code }" class="readOnlyText" readonly>
						</div>
						<div class="alt_edit_div">
							<label>运输类型：</label> <select id="v_transporttype" class="form-control">
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
							<label>所属组织：</label> <input id="v_orgname" orgid="${orgid }" value="${orgname }" type="text" class="readOnlyText" readonly>
						</div>
						<div class="alt_edit_div">
							<label>有效性： </label> <input id="v_isvalid" type="checkbox" checked>
						</div>
						<div class="alt_edit_textarea">
							<label>备注： </label>
							<textarea id="v_remarks" class="form-control" rows="1" style="height: 60px;"></textarea>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button id="addVehicleCommitBtn" type="button" class="btn btn-primary">确定</button>
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
							<label>司机编号：</label> <input id="d_code" type="text" value="${d_code }" class="readOnlyText" readonly>
						</div>
						<div class="alt_edit_div">
							<label>司机内码：</label> <input id="d_internalcode" type="text" value="${d_internalcode }" class="readOnlyText" readonly>
						</div>
						<div class="alt_edit_div">
							<label class="colorred"> *司机名称：</label> <input id="d_name" type="text">
						</div>
						<div class="alt_edit_div">
							<label>司机简称：</label> <input id="d_abbrname" type="text">
						</div>
						<div class="alt_edit_div">
							<label>地址：</label> <input id="d_address" type="text">
						</div>
						<div class="alt_edit_div">
							<label>所属组织：</label> <input id="d_orgname" orgid="${orgid }" value="${orgname }" type="text">
						</div>
						<div class="alt_edit_div">
							<label class="colorred"> *电话：</label> <input id="d_telephone" type="text">
						</div>

						<div class="alt_edit_div">
							<label class="colorred"> *身份证号：</label> <input id="d_identityno" type="text">
						</div>
						<div class="alt_edit_div">
							<label>有效性： </label> <input id="d_isvalid" type="checkbox" checked>
						</div>

						<div class="alt_edit_textarea">
							<label>备注： </label>
							<textarea id="d_remarks" class="form-control" rows="1" style="height: 60px;"></textarea>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button id="addDriverCommitBtn" type="button" class="btn btn-primary">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!--司机新增end-->
	<script type="text/javascript" src="${basePath }/js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="${basePath }/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${basePath }/js/bootstrap-treeview.js"></script>
	<script type="text/javascript"
		src="${basePath }/js/jquery.pagination.js"></script>
	<script type="text/javascript" src="${basePath }/js/layer/layer.js"></script>
	<script type="text/javascript"
		src="${basePath }/js/businessManage/salesManage/salesArriveAdd.js"></script>
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