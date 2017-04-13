<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>合格证维护</title>
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
								<label>产品名称：</label> <input id="seek_material" class="materialSelect" type="text" placeholder="请选择产品名称">
							</div>
							<div class="intel_solo">
								<button class="btn btnblue " id="seek">搜索</button>
							</div>
							<div class="clear"></div>
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

				</div>
				<div class="intel_table">
					<!--用户表格begin-->
					<table class="table table-hover">
						<thead>
							<tr>
								<th>行号</th>
								<th>产品名称</th>
								<th>注册商标</th>
								<th>执行标准</th>
								<th>生产许可证号</th>
								<th>厂址</th>
								<th>销售电话</th>
								<th>有效</th>
								<th>简介</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="list">
							<tr>
								<td>CD201601010138</td>
								<td class="colorred">审核中</td>
								<td>客商APP</td>
								<td>未入厂</td>
								<td>粉煤灰1</td>
								<td>粉煤灰1</td>
								<td>未入厂</td>
								<td><input type="checkbox" checked="true" disabled="true"></td>
								<td>粉煤灰1</td>
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
						<h5>合格证新增</h5>
					</div>
				</div>
				<div class="modal-body">
					<div class="alt_edit">
						<div class="alt_edit_div">
								<label class="colorred">*产品名称：</label>
								<div class=selct2_alt_div>
									<input id="add_material" type="text"
										class="materialSelect" placeholder="请选择产品名称">
								</div>
							</div>
						<div class="alt_edit_div">
							<label class="colorred">*注册商标：</label> <input type="text" id="add_trademark">
						</div>
						<div class="alt_edit_div">
							<label class="colorred">*执行标准：</label> <input type="text" id="add_norm">
						</div>
						<div class="alt_edit_div">
							<label class="colorred">*生产许可证号：</label> <input type="text" id="add_certificate">
						</div>
						<div class="alt_edit_div">
							<label>厂址：</label> <input type="text" id="add_factorysite">
						</div>
						<div class="alt_edit_div">
							<label>销售电话：</label> <input type="text" id="add_salestel">
						</div>
						<div class="alt_edit_div">
							<label>有效性：</label> <input type="checkbox" id="add_invalid"><span>有效</span>
						</div>
						<div class="alt_edit_textarea">
							<label>简介： </label>
							<textarea class="form-control" rows="1" id="add_intro"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="add_sure">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" id="add_cancel">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!--新增end-->
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
						<h5>物料方案编辑</h5>
					</div>
				</div>
				<div class="modal-body">
					<div class="alt_edit">
						<div class="alt_edit_div">
						<input type="hidden" id="edit_id">
								<label class="colorred">*产品名称：</label>
								<div class=selct2_alt_div>
									<input id="edit_material"
										class="materialSelect" type = "text">
								</div>
							</div>
						<div class="alt_edit_div">
							<label class="colorred">*注册商标：</label> <input type="text" id="edit_trademark">
						</div>
						<div class="alt_edit_div">
							<label class="colorred">*执行标准：</label> <input type="text" id="edit_norm">
						</div>
						<div class="alt_edit_div">
							<label class="colorred">*生产许可证号：</label> <input type="text" id="edit_certificate">
						</div>
						<div class="alt_edit_div">
							<label>厂址：</label> <input type="text" id="edit_factorysite">
						</div>
						<div class="alt_edit_div">
							<label>销售电话：</label> <input type="text" id="edit_salestel">
						</div>
						<div class="alt_edit_div">
							<label>有效性：</label> <input type="checkbox" id="edit_invalid"><span>有效</span>
						</div>
						<div class="alt_edit_textarea">
							<label>简介： </label>
							<textarea class="form-control" rows="1" id="edit_intro"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="edit_sure">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" id="edit_cancel">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!--编辑end-->
		<!-- 引用公共footer部分 -->
		<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
		<script type="text/javascript"
			src="/javascript/quality/file/certification.js"></script>
		</div>
</body>
</html>
