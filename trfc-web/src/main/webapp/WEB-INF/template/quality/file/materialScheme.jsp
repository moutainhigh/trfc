<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物料方案</title>
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
					<li class="select">物料方案</li>
					<li><a href="/trfc/quality/sales/file/qualityScheme/main">质检方案</a></li>
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
									<label>物料：</label> <input id="seek_material"
										class="materialSelect" type="text" placeholder="请选择物料">
								</div>
								<div class="intel_solo">
									<label>物料品种：</label> <input type="text" id="seek_materialtype" placeholder="请输入物料品种">
								</div>
								<div class="intel_solo">
									<button class="btn btnblue" id="seek">搜索</button>
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
									<th>物料</th>
									<th>物料品种</th>
									<th>强度等级</th>
									<th>混合材品种</th>
									<th>混合材掺加量</th>
									<th>石膏种类</th>
									<th>石膏掺加量</th>
									<th>助磨剂种类</th>
									<th>助磨掺加量</th>
									<th>有效</th>
									<th>结论</th>
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
									<td>客商APP</td>
									<td>未入厂</td>
									<td>粉煤灰1</td>
									<td>粉煤灰1</td>
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
		<div class="modal fade" id="add"  role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width: 750px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="alt_head">
							<h5>物料方案新增</h5>
						</div>
					</div>
					<div class="modal-body">
						<div class="alt_edit">
							<div class="alt_edit_div">
								<label class="colorred">*物料：</label>
								<div class=selct2_alt_div>
									<input id="add_material" type = "text"
										class="materialSelect" placeholder="请选择物料">
								</div>
							</div>
							<div class="alt_edit_div">
								<label class="colorred">*物料品种：</label> <input type="text"
									id="add_materialtype" placeholder="请输入物料品种">
							</div>
							<div class="alt_edit_div">
								<label class="colorred">*强度等级：</label> <input type="text"
									id="add_strength" placeholder="请输入强度等级">
							</div>
							<div class="alt_edit_div">
								<label>混合材品种：</label> <input type="text" id="add_admixture">
							</div>
							<div class="alt_edit_div">
								<label>混合材掺加量：</label> <input type="text" id="add_admixtureadd">
							</div>
							<div class="alt_edit_div">
								<label>石膏种类：</label> <input type="text" id="add_gypsum">
							</div>
							<div class="alt_edit_div">
								<label>石膏掺加量：</label> <input type="text" id="add_gypsumadd">
							</div>
							<div class="alt_edit_div">
								<label>助磨剂种类：</label> <input type="text" id="add_aid">
							</div>
							<div class="alt_edit_div">
								<label>助磨掺加量：</label> <input type="text" id="add_aidadd">
							</div>
							<div class="alt_edit_div">
								<label>有效性：</label> <input type="checkbox" id="add_invalid"><span>有效</span>
							</div>
							<div class="alt_edit_div">
								<label>是否显示：</label> <select class="form-control" id="add_shows">
									<option value="0">全部显示</option>
									<option value="1">部分显示</option>
									<option value="2">不显示</option>
								</select>
							</div>
							<div class="alt_edit_textarea">
								<label>结论： </label>
								<textarea class="form-control" rows="1" id="add_verdict"></textarea>
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
								<label class="colorred">*物料：</label>
									<input id="edit_material" type = "text"
										class="materialSelect">
							</div>
							<div class="alt_edit_div">
								<label class="colorred">*物料品种：</label> <input type="text"
									id="edit_materialtype">
							</div>
							<div class="alt_edit_div">
								<label class="colorred">*强度等级：</label> <input type="text"
									id="edit_strength">
							</div>
							<div class="alt_edit_div">
								<label>混合材品种：</label> <input type="text" id="edit_admixture">
							</div>
							<div class="alt_edit_div">
								<label>混合材掺加量：</label> <input type="text" id="edit_admixtureadd">
							</div>
							<div class="alt_edit_div">
								<label>石膏种类：</label> <input type="text" id="edit_gypsum">
							</div>
							<div class="alt_edit_div">
								<label>石膏掺加量：</label> <input type="text" id="edit_gypsumadd">
							</div>
							<div class="alt_edit_div">
								<label>助磨剂种类：</label> <input type="text" id="edit_aid">
							</div>
							<div class="alt_edit_div">
								<label>助磨掺加量：</label> <input type="text" id="edit_aidadd">
							</div>
							<div class="alt_edit_div">
								<label>有效性：</label> <input type="checkbox" id="edit_invalid"><span>有效</span>
							</div>
							<div class="alt_edit_div">
								<label>是否显示：</label> <select class="form-control"
									id="edit_shows">
									<option value="0">全部显示</option>
									<option value="1">部分显示</option>
									<option value="2">不显示</option>
								</select>
							</div>
							<div class="alt_edit_textarea">
								<label>结论： </label>
								<textarea class="form-control" rows="1" id="edit_verdict"></textarea>
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
		</div>
		<!--编辑end-->
		<!-- 引用公共footer部分 -->
		<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
		<script type="text/javascript"
			src="/javascript/quality/file/materialScheme.js"></script>
			</div>
</body>
</html>

