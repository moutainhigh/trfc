<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>质检方案项目</title>
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
					<li class="select">质检方案-项目</li>
					<li>质检方案-质检标准</li>
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
								<h5 id="headline">质检方案项目 - 济源博盈商贸有限公司-干粉煤灰(GH15)</h5>

							</div>
						</div>
					</div>
					<div class="intel_opera">
						<div class="intel_operasolo" id="fresh">
							<i class="iconfont colorlv">&#xe61b;</i>
							<h5>刷新</h5>
						</div>
						<div class="intel_operasolo">
							<a data-toggle="modal" data-target="#add" id="addBtn"> <i
								class="iconfont coloradd">&#xe627;</i>
								<h5>新增</h5>
							</a>
						</div>
						<div class="intel_operasolo">
							<a data-toggle="modal" data-target="#BatchDiv" id="addBatch">
								<i class="iconfont coloradd">&#xe627;</i>
								<h5>批量新增</h5>
							</a>
						</div>
						<div class="intel_operasolo" id="toback">
						<a href="/trfc/quality/sales/file/qualityScheme/main">
							<i class="iconfont colorlv">&#xe61e;</i>
							<h5>返回</h5>
							</a>
						</div>
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover">
							<thead>
								<tr>
									<th>行号</th>
									<th>方案编码</th>
									<th>方案名称</th>
									<th>项目编号</th>
									<th>项目名称</th>
									<th>有效</th>
									<th>类型</th>
									<th>备注</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="list">
								<tr>
									<td>1</td>
									<td>审核中</td>
									<td>客商APP</td>
									<td>未入厂</td>
									<td>供应商标准方案</td>
									<td><input type="checkbox" checked="true" disabled="true"></td>
									<td>供应商标准方案</td>
									<td></td>
									<td><span> <a data-toggle="modal"
											data-target="#edit"><i class="iconfont"
												data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>
									</span> <span> <a data-toggle="modal" data-target="#dele"><i
												class="iconfont" data-toggle="tooltip" data-placement="left"
												title="删除">&#xe63d;</i></a>
									</span> <span> <a data-toggle="modal" data-target="#dele"><i
												class="iconfont" data-toggle="tooltip" data-placement="left"
												title="清除">&#xe636;</i></a>
									</span></td>
								</tr>
							</tbody>
						</table>
						<!--用户表格end-->
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
		<!--新增begin-->
		<div class="modal fade" id="add" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width: 750px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="alt_head">
							<h5>质检方案项目-新增</h5>
						</div>
					</div>
					<div class="modal-body">
						<div class="alt_edit">
							<div class="alt_onerow_div">
								<label>方案：</label> <input type="text" readonly="true" value="11"
									id="add_name">
							</div>
							<div class="alt_edit_div">
								<label>项目名称：</label>
								<div class="selct2_alt_div">
									<input id="add_item" type="text"
										class="itemSelect">
								</div>
							</div>
							<div class="alt_onerow_div">
								<label>类型：</label> <input type="text" readonly="true"
									id="add_schemetype">
							</div>
							<div class="alt_onerow_div">
								<label>有效性：</label> <input type="checkbox" id="add_invalid"><span>有效</span>
							</div>
							<div class="alt_onerow_div">
								<label>备注： </label>
								<textarea class="form-control" rows="2" id="add_remark"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="add_sure">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="add_cancel">取消</button>
					</div>
				</div>
			</div>
		</div>
		<!--新增end-->
		<!--批量新增begin-->
		<div class="modal fade" id="BatchDiv" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width: 750px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="alt_head">
							<h5>质量标准-批量新增</h5>
						</div>
					</div>
					<div class="modal-body">
						<div class="alt_edit">
							<div class="alt_onerow_div">
								<label>质检方案：</label> <input type="text" value="11"
									id="addBatch_name">
							</div>
							<div class="alt_onerow_div">
								<label>类型：</label> <input type="text" id="addBatch_schemetype">
							</div>
							<div class="alt_onerow_div">
								<label>有效性：</label> <input type="checkbox" id="addBatch_invalid"><span>有效</span>
							</div>
						</div>
						<div class="zj_biaozhun_table">
							<div class="zj_biaozhun_tablepl">
								<table class="table table-hover table-bordered">
									<thead>
										<tr>
											<th>行号</th>
											<th>项目</th>
											<th>备注</th>

										</tr>
									</thead>
									<tbody id="addBatch_list">
										
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="addBatch_sure">保存</button>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="addBatch_cancel">取消</button>
					</div>
				</div>
			</div>
		</div>

		<!--批量新增end-->
		<!--编辑begin-->
		<div class="modal fade" id="edit" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width: 750px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="alt_head">
							<h5>质检方案项目-编辑</h5>
						</div>
					</div>
					<div class="modal-body">
						<div class="alt_edit">
							<div class="alt_onerow_div">
								<input type="hidden" id="edit_id"> <label>方案：</label> <input
									type="text" readonly="readonly" value="11" id="edit_name">
							</div>
							<div class="alt_edit_div">
								<label>项目名称：</label>
								<div class=selct2_alt_div>
									<input id="edit_item" type="text"
										class="itemSelect">
								</div>
							</div>
							<div class="alt_onerow_div">
								<label>类型：</label> <input type="text" readonly="readonly"
									id="edit_schemetype">
							</div>
							<div class="alt_onerow_div">
								<label>有效性：</label> <input type="checkbox" id="edit_invalid"><span>有效</span>
							</div>
							<div class="alt_onerow_div">
								<label>备注： </label>
								<textarea class="form-control" rows="2" id="edit_remark"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="edit_sure">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="edit_cancel">取消</button>
					</div>
				</div>
			</div>
		</div>
		<!--编辑end-->

		<!-- 引用公共footer部分 -->
		<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
		<script type="text/javascript"
			src="/javascript/quality/file/qualityScheme_item.js"></script>
	</div>
</body>
</html>