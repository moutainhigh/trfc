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
									<label>开始时间：</label> <input type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'seek_endtime\')}'})" class="Wdate"
										style="width: 160px" id="seek_starttime" placeholder="请选择开始时间"/>
								</div>
								<div class="intel_solo">
									<label>结束时间：</label> <input type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'seek_starttime\')}'})" class="Wdate"
										style="width: 160px" id="seek_endtime" placeholder="请选择结束时间" />
								</div>
								<div class="intel_solo">
									<label>单据编号：</label><input type="text" id="seek_code" placeholder="请输入单据编号"/>
								</div>
								<div class="intel_solo">
									<label>出厂编号：</label> <input type="text" id="seek_factorycode" placeholder="请输入出厂编号">
								</div>
								<div class="intel_solo">
									<label>物料：</label>
									<input type = "text" id="seek_material" placeholder="请选择物料">
								</div>
								<div class="intel_solo">
									<label>单据状态：</label> <select class="form-control"
										id="seek_billsstate">
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
						<!-- <div class="intel_operasolo" id="fresh">
							<a> <i class="iconfont colorlv">&#xe61b;</i>
								<span>刷新</span>
							</a>
						</div>
						<div class="intel_operasolo">
							<a href="/trfc/quality/sales/batchnum/addMain"> <i
								class="iconfont coloradd">&#xe627;</i>
								<span>新增</span>
							</a>
						</div>
						<div id="delete" class="intel_operasolo">
							<a> <i class="iconfont delete">&#xe63d;</i>
								<span>删除</span>
							</a>
						</div>
						<div id="stop" class="intel_operasolo">
							<a> <i class="iconfont stop">&#xe624;</i>
								<span>停用</span>
							</a>
						</div>
						<div id="audit" class="intel_operasolo">
							<a> <i class="iconfont audit">&#xe692;</i>
								<span>审核</span>
							</a>
						</div>
						<div id="unaudit" class="intel_operasolo">
							<a> <i class="iconfont unaudit">&#xe651;</i>
								<span>反审</span>
							</a>
						</div>
						<div id="copy" class="intel_operasolo">
							<a> <i class="iconfont copy">&#xe61c;</i>
								<span>复制</span>
							</a>
						</div>
						<div id="update" class="intel_operasolo">
							<a> <i class="iconfont update">&#xe600;</i>
								<span>编辑</span>
							</a>
						</div> -->
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover maintable">
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
								</tr>
							</thead>
							<tbody id="list">
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
		src="/javascript/quality/sales/batchnum.js?20180105"></script>
</body>
</html>