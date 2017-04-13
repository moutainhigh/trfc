<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>补卡业务</title>
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
									<label>物料：</label> <input type="text" id="seek_material" placeholder="请选择物料">
								</div>
								<div class="intel_solo">
									<label>车号：</label> <input type="text" id="seek_vehicle" placeholder="请选择车号">
								</div>
								<div class="intel_solo">
									<label>门禁单号：</label> <input type="text" id="seek_access" placeholder="请输入门禁单号">
								</div>
								<div class="intel_solo">
									<label>业务类型：</label> <select class="form-control"
										id="seek_business">
										<option value="">请选择</option>
										<option value="1">采购</option>
										<option value="2">销售</option>
										<option value="3">其他入库</option>
										<option value="4">其他出库</option>
									</select>
								</div>
								<div class="intel_solo">
									<label>状态：</label> <select class="form-control" id="seek_type">
										<option value="">请选择</option>
										<option value="1">入厂</option>
										<option value="2">出厂</option>
									</select>
								</div>
								<div class="intel_solo">
									<label>时间：</label> <input type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'seek_endtime\')}'})"
										class="Wdate" style="width: 160px" id="seek_starttime"  placeholder="请选择开始时间"/> <i>-</i>
									<input type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'seek_starttime\')}'})"
										class="Wdate" style="width: 160px" id="seek_endtime"  placeholder="请选择结束时间"/>
								</div>
								<div class="intel_solo">
									<div class="intel_sbtn">
										<button class="btn btnblue" id="seek">搜索</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="intel_opera">
						<div class="intel_operasolo" id="fresh">
							<i class="iconfont colorlv">&#xe61b;</i>
							<h5>刷新</h5>
						</div>
						<div class="intel_operasolo" id="seek_reader">
						<input type="hidden" id="seek_icardno">
							<i class="iconfont colorlv">&#xe601;</i>
							<h5>读卡查找</h5>
						</div>
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>门禁单号</th>
									<th>类型</th>
									<th>状态</th>
									<th>车号</th>
									<th>物料</th>
									<th>派车单号</th>
									<th>公司名称</th>
									<th>RFID</th>
									<th>IC卡号</th>
									<th>IC卡面编号</th>
									<th>门禁时间</th>
									<th>卡类型</th>
									<th>矿口</th>
									<th>备注</th>
								</tr>
							</thead>
							<tbody id="list">
								<tr
									ondblclick="javascript:window.location.href='card_dtail.html'">
									<td>CD201601010138</td>
									<td class="colorred">销售</td>
									<td class="colorred">入厂</td>
									<td>CD20160</td>
									<td>郑州祥鼎贸易有限公司</td>
									<td>Rsdjkj</td>
									<td>卫辉市润晨商贸有限公司</td>
									<td>e2135241321231415413211</td>
									<td>45411215452</td>
									<td>45411215452</td>
									<td>2016-01-01 07:59:39</td>
									<td>IC ka</td>
									<td></td>
									<td>22</td>
								</tr>
								<tr
									ondblclick="javascript:window.location.href='card_dtail.html'">
									<td>CD201601010138</td>
									<td class="colorred">销售</td>
									<td class="colorred">入厂</td>
									<td>CD20160</td>
									<td>郑州祥鼎贸易有限公司</td>
									<td>Rsdjkj</td>
									<td>卫辉市润晨商贸有限公司</td>
									<td>e2135241321231415413211</td>
									<td>45411215452</td>
									<td>45411215452</td>
									<td>2016-01-01 07:59:39</td>
									<td>IC ka</td>
									<td></td>
									<td>22</td>
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

				
			</div>
		</div>
	</div>


	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/quality/purchase/cardReader.js"></script>
	<script type="text/javascript"
		src="/javascript/businessManage/cardManage/card_reissue.js"></script>
</body>
</html>