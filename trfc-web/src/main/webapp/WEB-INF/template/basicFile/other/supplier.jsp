<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
			<div class="intel_tab">
				<!--tab切换标题-->
				<ul class="intel_menu">
					<li><a href="/trfc/basicFile/other/customer/main">其他客戶</a></li>
					<li><a href="/trfc/other/otherVehicle/main">其他车辆</a></li>
					<li><a href="/trfc/basicFile/other/material/main">其他物料</a></li>
					<li><a href="/trfc/basicFile/other/driver/main">其他司机</a></li>
					<li class="select"><a href="/trfc/basicFile/other/supplier/main">其他供应商</a></li>
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
									<label>查询条件：</label> <select class="form-control" id='keytype'>
										<option value="name">名称</option>
										<option value="code">内码</option>
									</select>
								</div>
								<div class="intel_solo">
									<label>关键字：</label> <input type="text" id='key'>
								</div>
								<div class="intel_solo">
									<label>所属组织：</label> <input type="text" id='orgname'>
								</div>
								<div class="intel_solo">
									<div class="intel_sbtn">
										<button class="btn btnblue">搜索</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="intel_opera">
						<div class="intel_operasolo" id="freshButton">
							<i class="iconfont colorlv">&#xe61b;</i>
							<h5>刷新</h5>
						</div>
						<div class="intel_operasolo">
							<a data-toggle="modal" data-target="#add" id="showAddSupplier"> <i
								class="iconfont coloradd">&#xe627;</i>
								<h5>新增</h5>
							</a>
						</div>
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover" id="supplier_list">
							<thead>
								<tr>
									<th>序号</th>
									<th>编号</th>
									<th>内码</th>
									<th>名称</th>
									<th>所属组织</th>
									<th>描述</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>CD201601010138</td>
									<td>审核中</td>
									<td>客商APP</td>
									<td>未入厂</td>
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
							<label>数据共：</label><i class="colorred" id="supplier_total">100</i><label>条</label>
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
						<div class="page_btn">
							<ul class="pagination" id="pagination">
								
							</ul>
						</div>
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
						<h5>其他供应商编辑</h5>
					</div>
				</div>
				<div class="modal-body">
					<div class="alt_edit">
						<div class="alt_edit_div">
							<label>编号：</label> <input type="text" value="121221" id="add_code"
								readonly="true">
						</div>
						<div class="alt_edit_div">
							<label>内码：</label> <input type="text" readonly="true" id="add_innercode"
								value="2100">
						</div>
						
						<div class="alt_edit_div">
							<label>名称：</label> <input type="text" id="add_name">
						</div>
						<div class="alt_edit_div">
							<label>简称：</label> <input type="text" id="add_info">
						</div>
							<div class="alt_edit_div">
							<label>所属组织：</label> <input type="text" class="input_zuzhialt " id="add_orgname">
							<div id="tree" class="zuzhi_alt"></div>
						</div>
						<div class="alt_edit_div">
							<label>有效性：</label> <input type="checkbox" id="add_isvalid"><span>有效</span>
						</div>
						<div class="alt_edit_textarea">
							<label>备注： </label>
							<textarea class="form-control" rows="1" id="add_remark"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal" id="supplier_add">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
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
						<h5>其他供应商编辑</h5>
					</div>
				</div>
				<div class="modal-body">
					<div class="alt_edit">
						<div class="alt_edit_div">
							<label>编号：</label> <input type="text" value="121221" id="modify_code"
								readonly="true">
						</div>
						<div class="alt_edit_div">
							<label>内码：</label> <input type="text" readonly="true" id="modify_innercode"
								value="2100">
						</div>
						<div class="alt_edit_div">
							<label>所属组织：</label> <input type="text" id="modify_orgname">
						</div>
						<div class="alt_edit_div">
							<label>名称：</label> <input type="text" id="modify_name">
						</div>
						<div class="alt_edit_div">
							<label>简称：</label> <input type="text" id="modify_info">
						</div>

						<div class="alt_edit_div">
							<label>有效性：</label> <input type="checkbox" id="modify_isvalid"><span>有效</span>
						</div>
						<div class="alt_edit_textarea">
							<label>备注： </label>
							<textarea class="form-control" rows="1" id="modify_remark"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal" id="supplier_modify">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!--编辑end-->



	<script type="text/javascript"
		src="/javascript/basicFile/other/supplier.js"></script>

</body>
</html>


