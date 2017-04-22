<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>其他入库通知单</title>
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
									<label>供应商：</label> <input type="text" id="seek_supplier" placeholder="请选择供应商">
								</div>
								<div class="intel_solo">
									<label>货物：</label> <input type="text" id="seek_cargo" placeholder="请输入货物">
								</div>
								<div class="intel_solo">
									<label>仓库：</label> <input type="text" id="seek_warehouse" placeholder="请选择仓库">
								</div>
								<div class="intel_solo">
									<label>车号：</label> <input type="text" id="seek_vehicleno" placeholder="请选择车号">
								</div>
								<div class="intel_solo">
									<label>通知单号：</label> <input type="text" id="seek_code" placeholder="请输入通知单号">
								</div>
								<div class="intel_solo">
									<label>审核状态：</label> <select class="form-control" id="seek_auditstatus">
										<option value="">请选择</option>
										<option value="0">未审核</option>
										<option value="1">已审核</option>
									</select>
								</div>
								<div class="intel_solo">
									<label>开始时间：</label> <input type="text" readonly="readonly" placeholder="请选择开始时间"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'seek_endtime\')}'})"
										class="Wdate" style="width: 160px" id="seek_starttime"/>
								</div>
								<div class="intel_solo">
									<label>结束时间：</label> <input type="text" readonly="readonly" placeholder="请选择结束时间"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'seek_starttime\')}'})"
										class="Wdate" style="width: 160px" id="seek_endtime"/>
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
									<th>通知单号</th>
									<th>审核状态</th>
									<th>提单状态</th>
									<th>供应商</th>
									<th>单位</th>
									<th>物料</th>
									<th>货物</th>
									<th>仓库</th>
									<th>收货单位</th>
									<th>制单日期</th>
									<th>备注</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="tbody_list">
								<tr>
									<td>1</td>
									<td>dsjkljgksksnmn1</td>
									<td class="colorred">审核中</td>
									<td class="colorred">作废</td>
									<td>未入厂</td>
									<td>豫GA1783</td>
									<td>CD201601010138</td>
									<td>豫GA1783</td>
									<td>CD201601010138</td>
									<td>卫辉市润晨商贸有限公司</td>
									<td>2016-01-01 07:59:39</td>
									<td></td>
									<td><span> <i class="iconfont"
											data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></span>
										<span> <i class="iconfont " data-toggle="tooltip"
											data-placement="left" title="审核">&#xe692;</i></span> <span> <i
											class="iconfont" data-toggle="tooltip" data-placement="left"
											title=" 作废">&#xe60c;</i></span> <span> <i class="iconfont"
											data-toggle="tooltip" data-placement="left" title=" 反审">&#xe651;</i></span>
										<span> <i class="iconfont" data-toggle="tooltip"
											data-placement="left" title=" 出厂">&#xe63c;</i></span></td>
								</tr>
								<tr>
									<td>1</td>
									<td>dsjkljgksksnmn1</td>
									<td class="colorblue">已审核</td>
									<td class="colorblue">出厂</td>
									<td>未入厂</td>
									<td>豫GA1783</td>
									<td>CD201601010138</td>
									<td>豫GA1783</td>
									<td>CD201601010138</td>
									<td>卫辉市润晨商贸有限公司</td>
									<td>2016-01-01 07:59:39</td>
									<td></td>
									<td><span> <i class="iconfont"
											data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></span>
										<span> <i class="iconfont " data-toggle="tooltip"
											data-placement="left" title="审核">&#xe692;</i></span> <span> <i
											class="iconfont" data-toggle="tooltip" data-placement="left"
											title=" 作废">&#xe60c;</i></span> <span> <i class="iconfont"
											data-toggle="tooltip" data-placement="left" title=" 反审">&#xe651;</i></span>
										<span> <i class="iconfont" data-toggle="tooltip"
											data-placement="left" title=" 出厂">&#xe63c;</i></span></td>
								</tr>
							</tbody>
						</table>
						<!--用户表格end-->
					</div>
					<div class="intel_result" id="ind_tab">
						<div class="cg_tabtit">
							<ul>
								<li class="select">车号信息</li>
								<li>过磅信息</li>
							</ul>
						</div>
						<div class="cg_tabbox">
							<!--tab切换的内容-->
							<div class="cg_tabcont">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th></th>
											<th>车号</th>
											<th>数量</th>
											<th>司机</th>
											<th>身份证号</th>
											<th>制单人</th>
										</tr>
									</thead>
									<tbody id="tbody_vehicle">
										<tr>
											<td>1</td>
											<td>粉煤炭</td>
											<td>方案2</td>
											<td>1000</td>
											<td>豫GA1783</td>
											<td>豫GA1783</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="cg_tabcont hide">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>磅单号</th>
											<th>车号</th>
											<th>毛重</th>
											<th>皮重</th>
											<th>净重</th>
											<th>轻车时间</th>
											<th>重车时间</th>
										</tr>
									</thead>
									<tbody id="tbody_pound">
										<tr>
											<td>卫辉市天瑞水泥有限公司</td>
											<td>粉煤炭</td>
											<td>方案2</td>
											<td>1000</td>
											<td>豫GA1783</td>
											<td>豫GA1783</td>
											<td>豫GA1783</td>
											<td>豫GA1783</td>
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
							<h5>其他入库通知单详细信息</h5>
							<img src="/resources/images/sh.png" id="shImg">
						</div>
					</div>
					<div class="modal-body">
						<div class="">
							<div class="cg_div">
								<div class="cg_solo">
									<label>通知单号：</label> <input type="text" id="detail_code">
								</div>
								<div class="cg_solo">
									<label>供应商：</label> <input type="text" id="detail_supplier">
								</div>
								<div class="cg_solo">
									<label>单位：</label> <input type="text" id="detail_datasource">
								</div>
								<div class="cg_solo">
									<label>物料：</label> <input type="text" id="detail_materiel">
								</div>
								<div class="cg_solo">
									<label>货物：</label> <input type="text" id="detail_cargo">
								</div>
								<div class="cg_solo">
									<label>收货单位：</label> <input type="text" id="detail_receivedepartment">
								</div>
								<div class="cg_solo">
									<label>车号：</label> <input type="text" id="detail_vehicleno">
								</div>
								<div class="cg_solo">
									<label>RFID： </label> <input type="text" id="detail_rfid">
								</div>
								<div class="cg_solo">
									<label>仓库： </label> <input type="text" id="detail_warehouse">
								</div>
								<div class="cg_solo">
									<label>司机： </label> <input type="text" id="detail_driver">
								</div>
								<div class="cg_solo">
									<label>身份证号： </label> <input type="text" id="detail_identityno">
								</div>
								<div class="cg_solo">
									<label>数量： </label> <input type="text" id="detail_count">
								</div>
								<div class="cg_solo">
									<label>单位： </label> <input type="text" id="detail_unit" value="吨">
								</div>
								<div class="cg_solo">
									<label>制单人： </label> <input type="text" id="detail_creator">
								</div>
								<div class="cg_solo">
									<label> 制单日期：</label> <input type="text" id="detail_createtime">
								</div>
								<div class="cg_solo">
									<label> 状态：</label> <input type="text" id="detail_status">
								</div>
								<div class="cg_solo" style="width: 540px;">
									<label> 备注：</label> <input type="text" style="width: 400px;" id="detail_remark">
								</div>
								<div class="cg_solo">
									<label> 射频卡号：</label> <input type="text" id="detail_cardno">
								</div>
								<div class="cg_solo">
									<label> 车辆编号：</label> <input type="text" id="detail_card_vehicleno">
								</div>
								<div class="cg_solo">
									<label> 车辆名称：</label> <input type="text" id="detail_card_vehiclename">
								</div>
								<div class="cg_solo">
									<label> 车辆编号：</label> <input type="text" id="detail_card_vehiclecode">
								</div>
								<div class="cg_solo">
									<label> 供应商：</label> <input type="text" id="detail_card_supplier">
								</div>
								<div class="cg_solo">
									<label> 物料：</label> <input type="text" id="detail_card_materiel">
								</div>
								<div class="cg_solo">
									<label> 物料状态：</label> <input type="text" id="detail_card_status">
								</div>
								<div class="cg_solo">
									<label> 通知单号：</label> <input type="text" id="detail_card_note">
								</div>
								<div class="cg_solo">
									<label> 数量：</label> <input type="text" id="detail_card_count">
								</div>
								<div class="cg_solo">
									<label> 发卡状态：</label> <input type="text" id="detail_card_bussnesstype">
								</div>
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<!--查看详情end-->


<!-- 引用公共footer部分 -->
<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
<script type="text/javascript"
		src="/javascript/businessManage/otherManage/otherRKArrive.js"></script>
</body>
</html>