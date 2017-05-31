<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>工程车辆通知单</title>
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
				<!--销售申请单begin-->
				<div class="intel_tabcont">
					<div class="intel_search">
						<div class="intel_bggray">
							<div class="intel_bgblue"></div>
						</div>
						<div class="intel_sconditon">
							<div class="intel_sline">
								<div class="intel_solo">
									<label>物料：</label> <input type="text" id="seek_materiel"
										placeholder="请选择物料">
								</div>
								<div class="intel_solo">
									<label>车号：</label> <input type="text" id="seek_vehicleno"
										placeholder="请选择车号">
								</div>
								<div class="intel_solo">
									<label>司机：</label> <input type="text" id="seek_driver"
										placeholder="请选择司机">
								</div>
								<div class="intel_solo">
									<label>单据编号：</label> <input type="text" id="seek_code"
										placeholder="请输入单号">
								</div>
								<div class="intel_solo">
									<label>审核状态：</label> <select class="form-control"
										id="seek_auditstatus">
										<option value="">请选择</option>
										<option value="0">未审核</option>
										<option value="1">已审核</option>
									</select>
								</div>
								<div class="intel_solo">
									<label>开始时间：</label> <input type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'seek_endtime\')}'})"
										class="Wdate" style="width: 160px" id="seek_starttime"
										placeholder="请输入开始时间" />
								</div>
								<div class="intel_solo">
									<label>结束时间：</label> <input type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'seek_starttime\')}'})"
										class="Wdate" style="width: 160px" id="seek_endtime"
										placeholder="请输入结束时间" />
								</div>
								<div class="intel_solo">
									<div class="intel_sbtn">
										<button class="btn btnblue " id="seek">搜索</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="intel_opera">
						<div class="intel_operasolo" id="refresh">
							<i class="iconfont colorlv">&#xe61b;</i>
							<h5>刷新</h5>
						</div>
						<div class="intel_operasolo" id="addBtn">
							<a> <i class="iconfont coloradd">&#xe627;</i>
								<h5>新增</h5>
							</a>
						</div>
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>单据编号</th>
									<th>审核状态</th>
									<th>单据状态</th>
									<th>车号</th>
									<th>物料</th>
									<th>司机</th>
									<th>身份证</th>
									<th>制单人</th>
									<th>制单日期</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="tbody_list">
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
			</div>
		</div>
		<!--查看详情begin-->
		<div class="modal fade" id="caigoubill" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width: 900px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="alt_head">
							<h5>工程车辆通知单详细信息</h5>
						</div>
					</div>
					<div class="modal-body">
						<div class="cg_div">
							<div class="cg_solo">
								<label>单据编号：</label> <input type="text" readonly="true"
									id="detail_code">
							</div>
							<div class="cg_solo">
								<label>单位：</label> <input type="text" readonly="true"
									id="detail_receivedepartment">
							</div>
							<div class="cg_solo">
								<label>车号：</label> <input type="text" readonly="true"
									id="detail_vehicleno">
							</div>
							<div class="cg_solo">
								<label>物料：</label> <input type="text" readonly="true"
									id="detail_materiel">
							</div>
							<div class="cg_solo">
								<label>司机：</label> <input type="text" readonly="true"
									id="detail_driver">
							</div>
							<div class="cg_solo">
								<label>身份证：</label> <input type="text" readonly="true"
									id="detail_identityno">
							</div>
							<div class="cg_solo">
								<label>制单人：</label> <input type="text" readonly="true"
									id="detail_creator">
							</div>
							<div class="cg_solo">
								<label>制单日期： </label> <input type="text" readonly="true"
									id="detail_createtime">
							</div>
							<div class="cg_solo">
								<label>备注： </label> <input type="text" readonly="true"
									id="detail_remark">
							</div>
							<div class="cg_solo" style="width: 540px;">
								<label> 事由：</label> <input type="text" readonly="true"
									id="detail_reason" style="width: 400px;">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
					</div>
				</div>
			</div>
		</div>
		<!--查看详情end-->


		<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
		<script type="text/javascript"
			src="${staticBasePath}/js/cardReader.js"></script>
		<script type="text/javascript"
			src="/javascript/businessManage/otherManage/otherCLArrive.js"></script>
</body>
</html>