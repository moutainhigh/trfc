<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>质检方案</title>
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
					<li><a href="/trfc/quality/sales/file/MaterialScheme/main">物料方案</a></li>
					<li class="select">质检方案</li>
					<li><a href="/trfc/quality/sales/file/qualityItem/main">质检项目</a></li>
					<li><a href="/trfc/quality/sales/file/supplierScheme/main">供应商标准方案</a></li>
					<li><a href="/trfc/quality/sales/file/certification/main">合格证维护</a></li>
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
									<label>物料：</label> <select class="form-control materialSelect"
										id="seek_material">
									</select>
								</div>
								<div class="intel_solo">
									<label>类型：</label> <select class="form-control" id="seek_type">
										<option></option>
										<option value="0">采购</option>
										<option value="1">销售</option>
									</select>
								</div>
								<div class="intel_solo">
									<label>名称：</label> <input type="text" id="seek_namelike">
								</div>
								<div class="intel_solo">
									<div class="intel_sbtn">
										<input type="checkbox" id="seek_invalid" checked><span>只显示有效的</span>
										<button class="btn btnblue" id="seek">搜索</button>

									</div>
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
									<th>方案编码</th>
									<th>方案名称</th>
									<th>物料</th>
									<th>单据</th>
									<th>有效</th>
									<th>默认</th>
									<th>类型</th>
									<th>质量标准</th>
									<th>描述</th>
									<th>操作</th>
									<th>查看</th>
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
									<td><input type="checkbox" checked="true" disabled="true"></td>
									<td>供应商标准方案</td>
									<td><input type="checkbox" checked="true" disabled="true"></td>
									<td>粉煤灰1</td>
									<td><span> <a data-toggle="modal"
											data-target="#edit"><i class="iconfont"
												data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>
									</span> <span> <a data-toggle="modal" data-target="#dele"><i
												class="iconfont" data-toggle="tooltip" data-placement="left"
												title="删除">&#xe63d;</i></a>
									</span></td>
									<td><a>方案项目</a> <a>质量标准</a></td>
								</tr>
								<tr>
									<td>2</td>
									<td>审核中</td>
									<td>客商APP</td>
									<td>未入厂</td>
									<td>供应商标准方案</td>
									<td><input type="checkbox" checked="true" disabled="true"></td>
									<td><input type="checkbox" checked="true" disabled="true"></td>
									<td>供应商标准方案</td>
									<td><input type="checkbox" checked="true" disabled="true"></td>
									<td>粉煤灰1</td>
									<td><span> <a data-toggle="modal"
											data-target="#edit"><i class="iconfont"
												data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>
									</span> <span> <a data-toggle="modal" data-target="#dele"><i
												class="iconfont" data-toggle="tooltip" data-placement="left"
												title="删除">&#xe63d;</i></a>
									</span></td>
									<td><a>方案项目</a> <a>质量标准</a></td>
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
							<h5>质检方案新增</h5>
						</div>
					</div>
					<div class="modal-body">
						<div class="alt_edit">
							<div class="alt_edit_div">
								<label>编号：</label> <input type="text" readonly="true" value="11" id="add_code">
							</div>
							<div class="alt_edit_div">
								<label>名称：</label> <input type="text" id="add_name">
							</div>
							<div class="alt_edit_div">
								<label>单据：</label><div class="selct2_alt_div">
									<input id="add_bills" type="text"
										class="billsSelect">
								</div>
							</div>
							<div class="alt_edit_div">
								<label>物料：</label> 
								<div class=selct2_alt_div>
									<input id="add_material" type="text"
										class="materialSelect">
								</div>
							</div>
							<div class="alt_edit_div">
								<label>类型：</label> <select class="form-control" id="add_type">
									<option></option>
									<option value="0">采购项目</option>
									<option value="1">销售项目</option>
								</select>
							</div>
							<div class="alt_edit_div">
								<label>选项：</label> <input type="checkbox" id="add_invalid"><span>有效</span>
								<input type="checkbox" id="add_def"><span>默认</span> <input
									type="checkbox" id="add_standard"><span>质量标准</span>
							</div>
							<div class="alt_edit_textarea">
								<label>备注： </label>
								<textarea class="form-control" rows="1" id="add_describe"></textarea>
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
							<h5>质检方案编辑</h5>
						</div>
					</div>
					<div class="modal-body">
						<div class="alt_edit">
							<div class="alt_edit_div">
							 <input type="hidden" id="edit_id"> 
								<label>编号：</label> <input type="text" readonly="true" value="11" id="edit_code">
							</div>
							<div class="alt_edit_div">
								<label>名称：</label> <input type="text" id="edit_name">
							</div>
							<div class="alt_edit_div">
								<label>单据：</label> 
								<div class=selct2_alt_div>
								<input id="edit_bills" type="text"
										class="billsSelect">
								</div>
							</div>
							<div class="alt_edit_div">
								<label>物料：</label> 
								<div class=selct2_alt_div>
									<input id="edit_material" type = "text"
										class="materialSelect">
								</div>
							</div>
							<div class="alt_edit_div">
								<label>类型：</label> <select class="form-control" id="edit_type">
									<option></option>
									<option value="0">采购项目</option>
									<option value="1">销售项目</option>
								</select>
							</div>
							<div class="alt_edit_div">
								<label>选项：</label> <input type="checkbox" id="edit_invalid"><span>有效</span>
								<input type="checkbox" id="edit_def"><span>默认</span> <input
									type="checkbox" id="edit_standard"><span>质量标准</span>
							</div>
							<div class="alt_edit_textarea">
								<label>备注： </label>
								<textarea class="form-control" rows="1" id="edit_describe"></textarea>
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
			src="/javascript/quality/file/qualityScheme.js"></script>
		</div>
</body>
</html>

