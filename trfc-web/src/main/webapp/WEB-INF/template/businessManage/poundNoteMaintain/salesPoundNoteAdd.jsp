<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售计量单新增</title>
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
							</ul>
						</div>
					</div>
					<div class="daohuo_add">
						<h5>销售计量单新增</h5>
						<div class="daohuo_add_div">
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>订单号：</label>
								<div class="input_withlogo">
									<input id="billcode" type="text" placeholder="请选择订单"
										readonly="readonly"> <span
										class="form-control-feedback"><i class="iconfont">&#xe608;</i></span>
								</div>
								<input id="maindeduction" type="checkbox"><span>主单扣量</span>
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>客户：</label> <input
									id="customername" type="text" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>发货单位：</label> <input
									id="orgname" type="text" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>过磅单号：</label> <input
									type="text" value="${code }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>车号：</label> <input
									id="vehicle" type="text" placeholder="请选择车号">
							</div>
							<div class="daohuo_add_solo">
								<label>订单数量：</label> <input id="salessum" type="text"
									readonly="true" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>物料：</label> <input
									id="materielname" type="text" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>预提量：</label> <input
									id="pickupquantity" type="text" placeholder="请输入预提量">
							</div>
							<div class="daohuo_add_solo">
								<label>余量：</label> <input id="margin" type="text"
									readonly="true">
							</div>

							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>毛重：</label> <input
									id="grossweight" type="text" placeholder="请输入毛重">
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>皮重：</label> <input
									id="tareweight" type="text" placeholder="请输入皮重">
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>净重：</label> <input
									id="netweight" type="text" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>出厂编号：</label>
								<div class="input_withlogo">
									<input id="batchnumber" type="text" placeholder="请选择出厂编号" readonly="readonly"><span
										class="form-control-feedback"><i class="iconfont">&#xe608;</i></span>
								</div>
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>重车时间：</label> <input
									id="weighttime" type="text" value="${nowDate }"
									readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
									class="Wdate" placeholder="请选择重车时间" />
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>轻车时间：</label> <input
									id="lighttime" type="text" value="${nowDate }"
									readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
									class="Wdate" placeholder="请选择轻车时间" />
							</div>
							<div class="daohuo_add_solo">
								<label>司机：</label> <input id="driver" type="text"
									placeholder="请选择司机">
							</div>
							<div class="daohuo_add_solo">
								<label>身份证：</label> <input id="identityno" type="text"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>制单日期：</label> <input
									id="makebilltime" type="text" value="${nowDate }"
									readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
									class="Wdate" placeholder="请选择制单日期" />
							</div>
							<div class="daohuo_add_solo">
								<label>制单人：</label> <input id="makebillname" type="text"
									value="${makebillname }" readonly="true">
							</div>
						</div>
					</div>
					<div class="clear"></div>
					<div>
						<div class="cg_dhadd">
							<div class="cg_tabtit">
								<ul>
									<li class="select">订单信息</li>
								</ul>
							</div>
						</div>

						<div class="cg_tabbox">
							<!--tab切换的内容-->
							<div class="cg_tabcont">
								<div class="zk_table_int">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th>行号</th>
												<th>销售订单号</th>
												<th>订单类型</th>
												<th>订单日期</th>
												<th>物料</th>
												<th>单位</th>
												<th>订单数量</th>
												<th>余量</th>
												<th>预提量</th>
												<th>单价</th>
												<th>销售组织</th>
												<th>客户名称</th>
											</tr>
										</thead>
										<tbody id="salesApplicationDetailBody">
										</tbody>
									</table>
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
	<!--订单号弹出begin-->
	<div class="modal fade" id="salesApplication" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document"
			style="width: 75%;">
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
					<div style="width: 100%">
						<div class="dh_alttable">
							<table class="table table-hover">
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
	<!--出厂号弹出begin-->
	<div class="modal fade" id="altchuchang" tabindex="-1" role="dialog"
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
						<h5>出厂编号</h5>
					</div>
				</div>
				<div class="modal-body">
					<div class="dhadd_search">

						<div class="dhsearch_solo">
							<label>批号：</label> <input id="factorycode" type="text" placeholder="请输入批号">
						</div>
						<div class="dhsearch_solo">
							<label>物料名称：</label> <input id="materiel1" type="text" placeholder="请选择物料">
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
							<button id="searchBtn1" class="btn btnblue ">搜索</button>
							<button id="clearBtn1" class="btn btnblue ">清空</button>
						</div>
					</div>
					<div class="dh_alttable">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>单据编号</th>
									<th>物料</th>
									<th>批号</th>
									<th>数量</th>
									<th>过磅量</th>
									<th>余量</th>
									<th>生产日期</th>
									<th>试验日期</th>
									<th>化验日期</th>
									<th>化验人</th>
									<th>化验单位</th>
								</tr>
							</thead>
							<tbody id="batchNumberBody">
							</tbody>
						</table>
					</div>
					<!--分页效果开始-->
					<div class="page">
						<div class="page_date">
							<label>数据共：</label><i id="total1" class="colorred">100</i><label>条</label>
						</div>
						<div class="page_date">
							<label>跳到第：</label> <input id="jumpPageNo1" type="text" value="1">
							<label>页</label>
							<button id="jumpPageNoBtn1" class="btn btn-default">确定</button>
						</div>
						<div class="page_date">
							<label>每页记录：</label> <select id="pageSize1" class="form-control">
								<option value="10">10</option>
								<option value="20">20</option>
								<option value="30">30</option>
							</select>
						</div>
						<div class="page_btn" id="pagination1"></div>
					</div>
					<!--分页效果结束-->
				</div>
			</div>
		</div>
	</div>
	<!--出厂号弹出end-->
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/businessManage/poundNoteMaintain/salesPoundNoteAdd.js?20171223"></script>
</body>
</html>