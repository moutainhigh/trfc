<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
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
					<li><a href="/trfc/purchaseArrive/main">到货通知单</a></li>
					<li class="select"><a href="/trfc/purchaseReturn/main">退货通知单</a></li>
				</ul>
			</div>
			<!--tab切换的内容-->
			<div class="intel_tabbox">
				<!--采购申请单begin-->
				<div class="intel_tabcont">
					<div class="intel_search">
						<div class="intel_bggray">
							<div class="intel_bgblue"></div>
						</div>
						<div class="intel_sconditon">
							<div class="intel_sline">
								<div class="intel_solo">
									<label>订单号：</label> <input id="billcode" type="text">
								</div>
								<div class="intel_solo">
									<label>通知单号：</label> <input id="code" type="text">
								</div>
								<div class="intel_solo">
									<label>供应商：</label> <select id="supplier" class="form-control">
										<option value="">请选择</option>
										<c:forEach items="${supplier }" var="s">
											<option value="${s.id }">${s.name }</option>
										</c:forEach>
									</select>
								</div>
								<div class="intel_solo">
									<label>车号：</label> <select idi="vehicle" class="form-control">
										<option value="">请选择</option>
										<c:forEach items="${vehicle }" var="v">
											<option value="${v.id }">${v.vehicleno }</option>
										</c:forEach>
									</select>
								</div>
								<div class="intel_solo">
									<label>审核状态：</label> <select id="auditstatus" class="form-control">
										<option value="">请选择</option>
										<option value="0">未审核</option>
										<option value="1">已审核</option>
									</select>
								</div>
								<div class="intel_solo">
									<label>司机：</label> <select id="driver" class="form-control">
										<option value="">请选择</option>
										<c:forEach items="${driver }" var="d">
											<option value="${d.id }">${d.name }</option>
										</c:forEach>
									</select>
								</div>
								<div class="intel_solo">
									<label>单据来源：</label> <select id="source" class="form-control">
										<option value="">请选择</option>
										<option value="0">业务平台</option>
										<option value="1">客商平台</option>
										<option value="2">客商APP</option>
									</select>
								</div>
								<div class="intel_solo">
									<label>状态：</label> <select id="status" class="form-control">
										<option value="">请选择</option>
										<option value="0">未入厂</option>
										<option value="1">空车</option>
										<option value="2">重车</option>
										<option value="3">作废</option>
										<option value="4">发卡</option>
										<option value="5">出厂</option>
										<option value="6">入厂</option>
										<option value="7">装车</option>
									</select>
								</div>
								<div class="intel_solo">
									<label>创建时间：</label> <input id="starttime" type="text" readonly
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'endtime\')}'})"
										class="Wdate" style="width: 160px" /> <i>-</i> <input
										 id="endtime" type="text" readonly
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'starttime\')}'})"
										class="Wdate" style="width: 160px" />
								</div>
								<div class="intel_solo">
									<div class="intel_sbtn">
										<button id="searchBtn" class="btn btnblue ">搜索</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="intel_opera">
						<div class="intel_operasolo">
							<i class="iconfont colorlv">&#xe61b;</i>
							<h5>刷新</h5>
						</div>
						<div class="intel_operasolo">
							<a> <i class="iconfont coloradd">&#xe627;</i>
								<h5>新增</h5>
							</a>
						</div>
						<div class="intel_operasolo">
							<i class="iconfont coloradd">&#xe61c;</i>
							<h5>复制</h5>
						</div>
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover">
							<thead>
								<tr>
									<th>退货单号</th>
									<th>审核</th>
									<th>来源</th>
									<th>退货状态</th>
									<th>订单编号</th>
									<th>供应商</th>
									<th>物料</th>
									<th>到货磅单</th>
									<th>重车日期</th>
									<th>备注</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>CD201601010138</td>
									<td class="colorred">审核中</td>
									<td>客商APP</td>
									<td>未入厂</td>
									<td>豫GA1783</td>
									<td>CD201601010138</td>
									<td>卫辉市润晨商贸有限公司</td>
									<td>粉煤灰1</td>
									<td>2016-01-01 07:59:39</td>
									<td>2016-01-01 07:59:39</td>
									<td><span> <a data-toggle="modal"
											data-target="#edit"><i class="iconfont"
												data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>
									</span> <span> <i class="iconfont " data-toggle="tooltip"
											data-placement="left" title="审核">&#xe692;</i></span> <span> <i
											class="iconfont" data-toggle="tooltip" data-placement="left"
											title=" 作废">&#xe60c;</i></span> <span> <i class="iconfont"
											data-toggle="tooltip" data-placement="left" title=" 反审">&#xe651;</i></span>
										<span> <i class="iconfont" data-toggle="tooltip"
											data-placement="left" title=" 出厂">&#xe63c;</i></span> <span>
											<i class="iconfont" data-toggle="tooltip"
											data-placement="left" title=" 删除">&#xe63d;</i>
									</span></td>
								</tr>
							</tbody>
						</table>
						<!--用户表格end-->
					</div>
					<div class="intel_result" id="ind_tab">
						<div class="cg_tabtit">
							<ul>
								<li class="select">物料信息</li>
								<li>订单信息</li>
								<li>过磅信息</li>
							</ul>
						</div>
						<div class="cg_tabbox">
							<!--tab切换的内容-->
							<div class="cg_tabcont">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>车号</th>
											<th>退货数量</th>
											<th>司机</th>
											<th>身份证号</th>
											<th>制单日期</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>000000</td>
											<td>11221</td>
											<td>方案2</td>
											<td>410001000</td>
											<td>豫GA1783</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="cg_tabcont hide">1</div>
							<div class="cg_tabcont hide">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>磅单号</th>
											<th>车号</th>
											<th>毛重</th>
											<th>皮重</th>
											<th>净重</th>
											<th>轻车时间</th>
											<th>重车时间</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>000000</td>
											<td>11221</td>
											<td>方案2</td>
											<td>410001000</td>
											<td>豫GA1783</td>
											<td>410001000</td>
											<td>豫GA1783</td>
										</tr>
									</tbody>
								</table>
							</div>
							<!--tab切换的内容end-->
						</div>
					</div>

				</div>
				<!--采购申请单end-->

				<!--到货通知单begin-->
				<div class="intel_tabcont hide">2</div>
				<!--到货通知单end-->

				<!--退货通知单begin-->
				<div class="intel_tabcont hide">3</div>
				<!--退货通知单end-->

				<!--到货通知单begin-->
				<div class="intel_tabcont hide">4</div>
				<!--到货通知单end-->
				<!--到货通知单begin-->
				<div class="intel_tabcont hide">5</div>
				<!--到货通知单end-->
				<!--tab切换的内容end-->
			</div>
		</div>
		<!--查看详情begin-->
		<div class="modal fade" id="caigoubill" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width: 900px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="alt_head">
							<h5>采购申请单详细信息</h5>
							<img src="../images/sh.png">
						</div>
					</div>
					<div class="modal-body">
						<div class="">
							<div class="cg_div">
								<div class="cg_solo">
									<label>单据编号：</label> <input type="text">
								</div>
								<div class="cg_solo">
									<label>单据来源：</label> <input type="text">
								</div>
								<div class="cg_solo">
									<label>订单类型：</label> <input type="text">
								</div>
							</div>
							<div class="cg_div">
								<div class="cg_solo">
									<label>订单日期：</label> <input type="text">
								</div>
								<div class="cg_solo">
									<label>供应商：</label> <input type="text">
								</div>
								<div class="cg_solo">
									<label>总数量：</label> <input type="text">
								</div>
							</div>
							<div class="cg_div">
								<div class="cg_solo">
									<label>采购员：</label> <input type="text">
								</div>
								<div class="cg_solo">
									<label>制单人： </label> <input type="text">
								</div>
								<div class="cg_solo">
									<label> 制单日期：</label> <input type="text">
								</div>
							</div>
							<div class="cg_div">
								<div class="cg_bz">
									<label>备注：</label> <input type="text">
								</div>
							</div>
							<div class="cg_tabtit">
								<ul>
									<li class="select">订单明细</li>
									<li>质检信息</li>
									<li>质检信息</li>
								</ul>
							</div>
							<div class="cg_tabbox">
								<!--tab切换的内容-->
								<div class="cg_tabcont">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th>采购组织</th>
												<th>物料</th>
												<th>质检方案</th>
												<th>数量</th>
												<th>备注</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>卫辉市天瑞水泥有限公司</td>
												<td>粉煤炭</td>
												<td>方案2</td>
												<td>1000</td>
												<td>豫GA1783</td>
											</tr>
											<tr>
												<td>合计</td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="cg_tabcont hide">1</div>
								<!--tab切换的内容end-->
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--查看详情end-->
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/businessManage/purchaseManage/purchaseReturn.js"></script>
	<script type="text/javascript">
		// 顶部tab切换菜单
		var $tab_li = $('.intel_menu li');
		$tab_li.click(function() {
			$(this).addClass('select').siblings().removeClass('select');
			var index = $tab_li.index(this);
			$('.intel_tabbox > .intel_tabcont').eq(index).show().siblings()
					.hide();
		});
		// 表格内容每行单击出来下面的详细信息
		var tabledata = $('.intel_table table tbody tr');
		tabledata.on("click", function() {
			$(".intel_result").css("display", "block");
		})
		// 表格内容每行双击出来下面的详细信息
		tabledata.on("dblclick", function() {
			$('#caigoubill').modal('show');
		})
		// 左侧宽度改变 右边改变
		var leftall = $(".left");
		var leftmini = $(".leftmini");
		leftall.on("click", function() {
			$(this).css("display", "none");
			leftmini.css("display", "block");
			$(".right").css("margin-left", "100px");
		});
		leftmini.on("click", function() {
			$(this).css("display", "none");
			leftall.css("display", "block");
			$(".right").css("margin-left", "200px");
		});
		// 首页底部的tab切换菜单
		var ind_li = $('#ind_tab ul li');
		ind_li.click(function() {
			$(this).addClass('select').siblings().removeClass('select');
			var index_li = ind_li.index(this);
			$('#ind_tab .cg_tabbox > .cg_tabcont').eq(index_li).show()
					.siblings().hide();
		});

		// 弹出信息的tab切换菜单
		var alt_li = $('#alt_tab ul li');
		alt_li.click(function() {
			$(this).addClass('select').siblings().removeClass('select');
			var index_alt = alt_li.index(this);
			$('#alt_tab .cg_tabbox > .cg_tabcont').eq(index_alt).show()
					.siblings().hide();
		});
	</script>
</body>
</html>