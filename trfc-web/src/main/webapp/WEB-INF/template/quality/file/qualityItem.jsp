<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>质检项目</title>
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
									<label>查询条件：</label> <select class="form-control"
										id="seek_condition">
										<option value="name">名称</option>
										<option value="code">编号</option>
									</select>
								</div>
								<div class="intel_solo">
									<label>类型：</label> <select class="form-control" id="seek_type">
										<option value="0">采购</option>
										<option value="1">销售</option>
									</select>
								</div>
								<div class="intel_solo">
									<label>关键字：</label> <input type="text" placeholder="请输入关键字" id="seek_key">
								</div>
								<div class="intel_solo">
									<div class="intel_sbtn">
										<input type="checkbox" checked="true" id="seek_invlid"><span>只显示有效的</span>
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
						<div class="intel_operasolo" id="addBtn">
							<a data-toggle="modal" data-target="#add"> <i
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
									<th>质检项目编号</th>
									<th>质检项目名称</th>
									<th>质检项目英文名称</th>
									<th>单位名称</th>
									<th>对应表例</th>
									<th>类型</th>
									<th>公式</th>
									<th>值分组</th>
									<th>值类型</th>
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
									<td><input type="checkbox" checked="true" disabled="true"></td>
									<td>供应商标准方案</td>
									<td><input type="checkbox" checked="true" disabled="true"></td>
									<td>粉煤灰1</td>
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
		<div class="modal fade" id="add" tabindex="-1" role="dialog"
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
								<label class="colorred">*质检项目编号：</label> <input type="text"
									readonly="true" value="z51512" id="add_code">
							</div>
							<div class="alt_edit_div">
								<label class="colorred">*质检项目名称：</label> <input type="text"
									id="add_name">
							</div>
							<div class="alt_edit_div">
								<label class="colorred">*符号表示：</label> <input type="text"
									id="add_ename">
							</div>
							<div class="alt_edit_div">
								<label>单位名称：</label> <input type="text" id="add_units">
							</div>
							<div class="alt_edit_div">
								<label class="colorred">*类型：</label> <select
									class="form-control" id="add_type">
									<option value="0">采购项目</option>
									<option value="1">销售项目</option>
								</select>
							</div>
							<div class="alt_edit_div">
								<label class="colorred">*对应列：</label> <input type="text"
									id="add_line" class="columnSel">
							</div>
							<div class="alt_edit_div">
								<label>公式：</label> <input type="text" id="add_formula">
							</div>
							<div class="alt_edit_div">
								<label>有效性：</label> <input type="checkbox" id="add_invlid"><span>有效</span>
							</div>
							<div class="alt_edit_div">
								<label class="colorred">*值分组：</label> <input type="text"
									id="add_vgroups">
							</div>
							<div class="alt_edit_div">
								<label class="colorred">*值类型：</label> <select
									class="form-control" id="add_vtype">
									<option value="0">单值</option>
									<option value="1">平均</option>
									<option value="2">其他</option>
								</select>
							</div>
							<div class="alt_edit_div">
								<label class="colorred">*值天数：</label> <select
									class="form-control" id="add_vdays">
									<option value="0">单天</option>
									<option value="1">3天</option>
									<option value="2">28天</option>
								</select>
							</div>
							<div class="alt_edit_textarea">
								<label>备注： </label>
								<textarea class="form-control" rows="1" id="add_remark"></textarea>
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
		<!--编辑begin-->
		<div class="modal fade" id="edit" tabindex="-1" role="dialog"
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
								<input type="hidden" id="edit_id"> <label
									class="colorred">*质检项目编号：</label> <input type="text"
									readonly="true" value="z51512" id="edit_code">
							</div>
							<div class="alt_edit_div">
								<label class="colorred">*质检项目名称：</label> <input type="text"
									id="edit_name">
							</div>
							<div class="alt_edit_div">
								<label class="colorred">*符号表示：</label> <input type="text"
									id="edit_ename">
							</div>
							<div class="alt_edit_div">
								<label>单位名称：</label> <input type="text" id="edit_units">
							</div>
							<div class="alt_edit_div">
								<label class="colorred">*类型：</label> <select
									class="form-control" id="edit_type">
									<option value="0">采购项目</option>
									<option value="1">销售项目</option>
								</select>
							</div>
							<div class="alt_edit_div">
								<label class="colorred">*对应列：</label> <input type="text"
									id="edit_line" class="columnSel">
							</div>
							<div class="alt_edit_div">
								<label>公式：</label> <input type="text" id="edit_formula">
							</div>
							<div class="alt_edit_div">
								<label>有效性：</label> <input type="checkbox" id="edit_invlid"><span>有效</span>
							</div>
							<div class="alt_edit_div">
								<label class="colorred">*值分组：</label> <input type="text"
									id="edit_vgroups">
							</div>
							<div class="alt_edit_div">
								<label class="colorred">*值类型：</label> <select
									class="form-control" id="edit_vtype">
									<option value="0">单值</option>
									<option value="1">平均</option>
									<option value="2">其他</option>
								</select>
							</div>
							<div class="alt_edit_div">
								<label class="colorred">*值天数：</label> <select
									class="form-control" id="edit_vdays">
									<option value="0">单天</option>
									<option value="1">3天</option>
									<option value="2">28天</option>
								</select>
							</div>
							<div class="alt_edit_textarea">
								<label>备注： </label>
								<textarea class="form-control" rows="1" id="edit_remark"></textarea>
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
			src="/javascript/quality/file/qualityItem.js"></script>
	</div>
</body>
</html>