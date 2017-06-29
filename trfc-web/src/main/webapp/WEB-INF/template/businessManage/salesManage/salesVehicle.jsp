<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购车辆状态</title>
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>
<style type="text/css">
table.table-bordered th, td {
	text-align: center;
}
</style>
</head>
<body>
	<div class="it_admin">
		<jsp:include page="../../common/base/left_busi.jsp"></jsp:include>
		<div class="right">
			<jsp:include page="../../common/base/right_head_busi.jsp"></jsp:include>
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
									<label>订单号：</label> <input id="billcode" type="text"
										placeholder="请输入订单号" />
								</div>
								<div class="intel_solo">
									<label>客户：</label> <input id="customer" type="text"
										placeholder="请选择客户" />
								</div>
								<div class="intel_solo">
									<label>车号：</label> <input id="vehicle" type="text"
										placeholder="请选择车辆" />
								</div>
								<div class="intel_solo">
									<label>物料：</label> <input id="materiel" type="text"
										placeholder="请选择物料" />
								</div>
								<div class="intel_solo">
									<label>司机：</label> <input id="driver" type="text"
										placeholder="请选择司机" />
								</div>
								<div class="intel_solo">
									<label>通知单号：</label> <input id="code" type="text"
										placeholder="请输入通知单号" />
								</div>
								<div class="intel_solo">
									<label>审核状态：</label> <select id="auditstatus"
										class="form-control">
										<option value="">请选择</option>
										<option value="0">未审核</option>
										<option value="1">已审核</option>
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
									<label>创建时间：</label> <input type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'seek_endtime\')}'})"
										class="Wdate" style="width: 160px" id="seek_starttime"  placeholder="请选择开始时间"/> <i>-</i>
									<input type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'seek_starttime\')}'})"
										class="Wdate" style="width: 160px" id="seek_endtime"  placeholder="请选择结束时间"/>
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
						<div class="intel_operasolo" id="refreshBtn">
							<a> <i class="iconfont colorlv">&#xe61b;</i>
								<span>刷新</span>
							</a>
						</div>
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>车号</th>
									<th>车辆状态</th>
									<th>派车时间</th>
									<th>入厂时间</th>
									<th>轻车时间</th>
									<th>重车时间</th>
									<th>开始装车</th>
									<th>结束装车</th>
									<th>铅封时间</th>
									<th>出厂时间</th>
									<th>提货单号</th>
									<th>订单号</th>
									<th>客户</th>
									<th>物料</th>
								</tr>
							</thead>
							<tbody id="list">
								<tr>
								<td>1</td>
									<td>CD201601010138</td>
									<td class="colorred">审核中</td>
									<td>客商APP</td>
									<td>未入厂</td>
									<td>豫GA1783</td>
									<td>CD201601010138</td>
									<td>卫辉市润晨商贸有限公司</td>
									<td>粉煤灰1</td>
									<td>2016-01-01 07:59:39</td>
									<td>粉煤灰1</td>
									<td>粉煤灰1</td>
									<td>粉煤灰1</td>
									<td>粉煤灰1</td>
									<td>粉煤灰1</td>
								</tr>
							</tbody>
						</table>
						<!--用户表格end-->
					</div>


					<!--分页效果开始-->
				<div class=" row fr">
						<div class="page_date">
							<label>数据共：</label><i class="colorred" id="total">100</i><label>条</label>
						</div>
						<div class="page_date">
							<label>跳到第：</label> <input type="text" id="jumpPageNo"> <label>页</label>
							<button class="btn btn-default" id="jumpButton">确定</button>
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
				var index_li = cg_li.index(this);
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
		<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
		<script type="text/javascript"
			src="/javascript/businessManage/salesManage/salesVehicle.js"></script>
</body>
</html>
