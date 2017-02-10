<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售批号维护</title>
<link href="${staticBasePath}/css/select2.css" rel="stylesheet">
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
					<li class="select">销售批号维护</li>
					<li>销售化验报告</li>
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
				<div class="intel_tabcont">
					<div class="intel_search">
						<div class="intel_bggray">
							<div class="intel_bgblue"></div>
						</div>
						<div class="intel_sconditon">
							<div class="intel_sline">
								<div class="intel_solo">
									<label>开始时间：</label> <input type="text" id="seek_starttime"
										readonly="true"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
										class="Wdate" style="width: 160px" />
								</div>
								<div class="intel_solo">
									<label>结束时间：</label> <input type="text" id="seek_endtime"
										readonly="true"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
										class="Wdate" style="width: 160px" />
								</div>
								<div class="intel_solo">
									<label>单据编号：</label><input type="text" id="seek_code" />
								</div>
								<div class="intel_solo">
									<label>出厂编号：</label> <input type="text" id="seek_factorycode">
								</div>
								<div class="intel_solo">
									<label>物料：</label>
									<select id="seek_material"
									class="material_select2"
								>
								</select>
								</div>
								<div class="intel_solo">
									<label>单据状态：</label> <select class="form-control"
										id="seek_billsstate">
										<option></option>
										<option value="1">启用</option>
										<option value="0">停用</option>
									</select>
								</div>
								<div class="intel_solo">
									<button class="btn btnblue " id="seek">搜索</button>
								</div>
								<div class="clear"></div>
							</div>
						</div>
					</div>
					<div class="intel_opera">
						<div class="intel_operasolo">
							<i class="iconfont colorlv">&#xe61b;</i>
							<h5>刷新</h5>
						</div>
						<div class="intel_operasolo">
							<a href="/trfc/quality/sales/batchnum/addMain"> <i
								class="iconfont coloradd">&#xe627;</i>
								<h5>新增</h5>
							</a>
						</div>

					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover">
							<thead>
								<tr>
									<th>行号</th>
									<th>单据编号</th>
									<th>审核状态</th>
									<th>单据状态</th>
									<th>化验状态</th>
									<th>物料名称</th>
									<th>批号</th>
									<th>数量</th>
									<th>过磅量</th>
									<th>余量</th>
									<th>化验日期</th>
									<th>化验人</th>
									<th>开始时间</th>
									<th>结束时间</th>
									<th>审核时间</th>
									<th>审核人</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="list">
								<tr>
									<td>1</td>
									<td class="colorred">审核中</td>
									<td class="colorblue">客商APP</td>
									<td class="colorred">未化验</td>
									<td>未入厂</td>
									<td>粉煤灰1</td>
									<td>粉煤灰1</td>
									<td>未入厂</td>
									<td>未入厂</td>
									<td>未入厂</td>
									<td>未入厂</td>
									<td>未入厂</td>
									<td>未入厂</td>
									<td>未入厂</td>
									<td>未入厂</td>
									<td>未入厂</td>
									<td><span> <a data-toggle="modal"
											data-target="#dele"><i class="iconfont"
												data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>
									</span> <span> <a data-toggle="modal" data-target="#dele"><i
												class="iconfont" data-toggle="tooltip" data-placement="left"
												title="停用">&#xe624;</i></a>
									</span> <span> <a data-toggle="modal" data-target="#dele"><i
												class="iconfont" data-toggle="tooltip" data-placement="left"
												title="审核">&#xe692;</i></a>
									</span> <span> <a data-toggle="modal" data-target="#dele"><i
												class="iconfont" data-toggle="tooltip" data-placement="left"
												title="反审">&#xe651;</i></a>
									</span> </span> <span> <a data-toggle="modal" data-target="#dele"><i
												class="iconfont" data-toggle="tooltip" data-placement="left"
												title="复制">&#xe61c;</i></a>
									</span></td>
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
		<!-- 引用公共footer部分 -->
		<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	</div>
	<script type="text/javascript"
		src="/resources/js/select2.js"></script>
	<script type="text/javascript"
		src="/javascript/quality/sales/batchnum_common.js"></script>
	<script type="text/javascript"
		src="/javascript/quality/sales/batchnum.js"></script>
	</script>
	</div>
</body>
</html>