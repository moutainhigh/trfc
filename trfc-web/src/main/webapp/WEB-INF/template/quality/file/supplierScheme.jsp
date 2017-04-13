<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>供应商标准方案</title>
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
				<!--begin-->
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
									<label>物料：</label> <input id="seek_material" class="materialSel"  placeholder="请选择物料"
										type = "text">
								</div>
								<div class="intel_solo">
									<label>供应商：</label> <input id="seek_supplier" class="supplierSel"  placeholder="请选择供应商"
										type = "text"/>
								</div>
								<div class="intel_solo">
									<label>单据编号：</label> <input type="text" id="seek_code" placeholder="请输入单据编号">
								</div>
								<div class="intel_solo">
									<label>状态：</label> <select class="form-control"
										id="seek_invalid">
										<option value="0">启用</option>
										<option value="1">停用</option>
									</select>
								</div>

								<div class="intel_solo">
										<button class="btn btnblue " id="seek">搜索</button>
								</div>
							</div>
						</div>
					</div>
					<div class="intel_opera">
						<div class="intel_operasolo" id="fresh">
							<i class="iconfont colorlv">&#xe61b;</i>
							<h5>刷新</h5>
						</div>
						<div class="intel_operasolo" id="addBtn">
							<i class="iconfont coloradd">&#xe627;</i>
							<h5>新增</h5>

						</div>
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover">
							<thead>
								<tr>
									<th>行号</th>
									<th>编号</th>
									<th>名称</th>
									<th>供应商</th>
									<th>物料</th>
									<th>供应商备注</th>
									<th>开始时间</th>
									<th>结束时间</th>
									<th>有效</th>
									<th>方案名称</th>
									<th>描述</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="list">
								<tr>
									<td>CD201601010138</td>
									<td>未审核</td>
									<td>客商APP</td>
									<td>原燃材料采购订单</td>
									<td>郑州祥鼎贸易有限公司</td>
									<td>2016-11-14 10:09:47</td>
									<td>卫辉市润晨商贸有限公司</td>
									<td>李志刚</td>
									<td>2016-01-01 07:59:39</td>
									<td>李志刚</td>
									<td>2016-01-01 07:59:39</td>
									<td><span> <a data-toggle="modal"
											data-target="#edit"><i class="iconfont"
												data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>
									</span> <span> <a data-toggle="modal" data-target="#dele"><i
												class="iconfont" data-toggle="tooltip" data-placement="left"
												title="删除">&#xe63d;</i></a>
									</span></td>
								</tr>
								<tr>
									<td>CD201601010138</td>
									<td>未审核</td>
									<td>客商APP</td>
									<td>原燃材料采购订单</td>
									<td>郑州祥鼎贸易有限公司</td>
									<td>2016-11-14 10:09:47</td>
									<td>卫辉市润晨商贸有限公司</td>
									<td>李志刚</td>
									<td>2016-01-01 07:59:39</td>
									<td>李志刚</td>
									<td>2016-01-01 07:59:39</td>
									<td><span> <a data-toggle="modal"
											data-target="#edit"><i class="iconfont"
												data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>
									</span> <span> <a data-toggle="modal" data-target="#dele"><i
												class="iconfont" data-toggle="tooltip" data-placement="left"
												title="删除">&#xe63d;</i></a>
									</span></td>
								</tr>
							</tbody>
						</table>
						<!--用户表格end-->
					</div>
					<div class="intel_result">
						<div class="cg_tabbox">
							<!--tab切换的内容-->
							<div class="cg_tabcont">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>行号</th>
											<th>检验项目</th>
											<th>比较符</th>
											<th>下限</th>
											<th>比较符</th>
											<th>上限</th>
											<th>基准值</th>
											<th>浮动值</th>
											<th>扣价</th>
											<th>扣吨</th>
										</tr>
									</thead>
									<tbody id="detail_list">
										<tr>
											<td>1</td>
											<td>粉煤炭</td>
											<td>></td>
											<td>1000</td>
											<td>></td>
											<td>1000</td>
											<td>></td>
											<td>1000</td>
											<td>></td>
											<td>1000</td>
										</tr>
									</tbody>
								</table>
							</div>
							<!--tab切换的内容end-->
						</div>
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
		<script type="text/javascript" src="/resources/js/select2.js"></script>
		<script type="text/javascript"
			src="/javascript/quality/file/supplierScheme_common.js"></script>
		<script type="text/javascript"
			src="/javascript/quality/file/supplierScheme.js"></script>
	</div>
</body>
</html>