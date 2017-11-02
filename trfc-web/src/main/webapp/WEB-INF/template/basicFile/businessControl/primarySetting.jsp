<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购原发设置</title>
<!-- 引用公共header部分 -->
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>
</head>
<body>
	<div class="it_admin">
		<!-- 引用公共left部分 -->
		<jsp:include page="../../common/base/left_busi.jsp"></jsp:include>
		<div class="right">
			<!-- 引用公共right部分 -->
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
									<label>单据编号：</label> <input id="code" type="text" placeholder="请输入单据编号">
								</div>
								<div class="intel_solo">
									<label>供应商：</label> <input id="supplier" type="text" placeholder="请选择供应商">
								</div>
								<div class="intel_solo">
									<label>物料：</label> <input id="material" type="text" placeholder="请选择物料">
								</div>
								<div class="intel_solo">
									<label>开始时间：</label> <input id="starttime" type="text" readonly
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'endtime\')}'})"
										class="Wdate" style="width: 160px" placeholder="请选择开始时间" />
								</div>
								<div class="intel_solo">
									<label>结束时间：</label> <input id="endtime" type="text" readonly
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'starttime\')}'})"
										class="Wdate" style="width: 160px" placeholder="请选择结束时间" />
								</div>
								<div class="clear"></div>
								<div class="intel_solo">
									<label>状态：</label> <select id="isvalid" class="form-control">
										<option value="">请选择</option>
										<option value="1">启用</option>
										<option value="0">禁用</option>
									</select>
								</div>
								<div class="intel_solo">
									<div class="intel_sbtn">
										<button id="search" class="btn btnblue ">搜索</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="intel_opera">
						<!-- <div id="refresh" class="intel_operasolo">
							<a> <i class="iconfont colorlv">&#xe61b;</i>
							    <span>刷新</span>
                            </a>
						</div>
						<div id="add" class="intel_operasolo">
							<a> <i
								class="iconfont coloradd">&#xe627;</i>
								<span>新增</span>
							</a>
						</div> -->
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover">
							<thead>
								<tr>
									<th>行数</th>
									<th>编号</th>
									<th>供应商</th>
									<th>物料</th>
									<th>有效</th>
									<th>描述</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="dataBody">
							</tbody>
						</table>
						<!--用户表格end-->
					</div>
					<!--分页效果开始-->
					<div class="page">
						<div class="page_date">
							<label>数据共：</label><i id="total" class="colorred">100</i><label>条</label>
						</div>
						<div class="page_date">
							<label>跳到第：</label> <input id="jumpPageNo" type="text"> <label>页</label>
							<button id="jumpPageNoBtn" class="btn btn-default">确定</button>
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
			</div>
		</div>
		<!--新增begin-->
		<div class="modal fade" id="addView" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width: 750px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="alt_head">
							<h5>采购原发设置录入</h5>
						</div>
					</div>
					<div class="modal-body">
						<div class="alt_edit">
							<div class="alt_edit_div">
								<label class="colorred">单据编号*：</label> <input id="a_code" type="text" readonly="true">
							</div>
							<div class="alt_edit_div">
								<label class="colorred">供应商*：</label> <input id="a_supplier" type="text" placeholder="请选择供应商">
							</div>
							<div class="alt_edit_div">
								<label class="colorred">物料*：</label> <input id="a_material" type="text" placeholder="请选择物料">
							</div>
							<div class="alt_edit_div">
								<label>制单人：</label> <input id="a_creator" type="text" readonly="true">
							</div>
							<div class="alt_edit_div">
								<label>制单日期：</label> <input id="a_createtime" type="text" readonly="true">
							</div>
							<div class="alt_edit_div">
								<label>选项：</label> <input id="a_isvalid" type="checkbox" checked="checked"><span>有效</span>
							</div>
							<div class="alt_edit_textarea">
								<label>备注： </label>
								<textarea id="a_remark" class="form-control" rows="1"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button id="addCommit" type="button" class="btn btn-primary">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
		<!--新增end-->
		<!--编辑begin-->
		<div class="modal fade" id="updateView" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width: 750px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="alt_head">
							<h5>采购原发设置录入</h5>
						</div>
					</div>
					<div class="modal-body">
						<input id="primarySettingId" type="hidden">
						<div class="alt_edit">
							<div class="alt_edit_div">
								<label class="colorred">单据编号*：</label> <input id="u_code" type="text" readonly="true">
							</div>
							<div class="alt_edit_div">
								<label class="colorred">供应商*：</label> <input id="u_supplier" type="text" placeholder="请选择供应商">
							</div>
							<div class="alt_edit_div">
								<label class="colorred">物料*：</label> <input id="u_material" type="text" placeholder="请选择物料">
							</div>
							<div class="alt_edit_div">
								<label>制单人：</label> <input id="u_creator" type="text" readonly="true">
							</div>
							<div class="alt_edit_div">
								<label>制单日期：</label> <input id="u_createtime" type="text" readonly="true">
							</div>
							<div class="alt_edit_div">
								<label>选项：</label> <input id="u_isvalid" type="checkbox" checked="checked"><span>有效</span>
							</div>
							<div class="alt_edit_textarea">
								<label>备注： </label>
								<textarea id="u_remark" class="form-control" rows="1"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button id="updateCommit" type="button" class="btn btn-primary">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--编辑end-->
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript" src="/javascript/basicFile/businessControl/primarySetting.js"></script>
</body>
</html>