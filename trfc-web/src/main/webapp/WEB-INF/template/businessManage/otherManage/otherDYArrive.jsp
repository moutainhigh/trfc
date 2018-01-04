<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>厂内倒运通知单</title>
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
									<label>调入堆场：</label> <input type="text" class="yardSelect"
										id="seek_enteryard" placeholder="请选择调入堆场">
								</div>
								<div class="intel_solo">
									<label>调出堆场：</label> <input type="text" class="yardSelect"
										id="seek_leaveyard" placeholder="请选择调出堆场">
								</div>
								<div class="intel_solo">
									<label>物料：</label> <input type="text" id="seek_materiel"
										placeholder="请选择物料">
								</div>
								<div class="intel_solo">
									<label>车号：</label> <input type="text" id="seek_vehicle"
										placeholder="请选择车号">
								</div>
								<div class="intel_solo">
									<label>单据编号：</label> <input type="text" id="seek_code"
										placeholder="请输入单据编号">
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
									<label>单据状态：</label> <select class="form-control" id="seek_invalid">
										<option value="">全部</option>
										<option value="0">作废</option>
										<option value="1" selected="selected">正常</option>
									</select>
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
						<!-- <div class="intel_operasolo" id="refresh">
							<a> <i class="iconfont colorlv">&#xe61b;</i>
								<span>刷新</span>
							</a>
						</div>
						<div class="intel_operasolo" id="addBtn">
							<a> <i class="iconfont coloradd">&#xe627;</i>
								<span>新增</span>
							</a>
						</div>
						<div class="intel_operasolo" id="update">
							<a> <i class="iconfont update">&#xe600;</i>
								<span>编辑</span>
							</a>
						</div>
						<div class="intel_operasolo" id="copy">
							<a> <i class="iconfont copy">&#xe61c;</i>
								<span>复制</span>
							</a>
						</div>
						<div id="audit" class="intel_operasolo">
							<a> <i class="iconfont audit">&#xe692;</i>
								<span>审核</span>
							</a>
						</div>
						<div id="unaudit" class="intel_operasolo">
							<a> <i class="iconfont unaudit">&#xe651;</i>
								<span>反审</span>
							</a>
						</div>
						<div id="invalid" class="intel_operasolo">
							<a> <i class="iconfont invalid">&#xe60c;</i>
								<span>作废</span>
							</a>
						</div>
						<div id="outfactory" class="intel_operasolo">
							<a> <i class="iconfont outfactory">&#xe63c;</i>
								<span>出厂</span>
							</a>
						</div> -->
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover maintable">
							<thead>
								<tr>
									<th>序号</th>
									<th>单据编号</th>
									<th>审核状态</th>
									<th>单据状态</th>
									<th>车号</th>
									<th>调出堆场</th>
									<th>调入堆场</th>
									<th>物料</th>
									<th>开始日期</th>
									<th>截至日期</th>
									<th>制单人</th>
									<th>制单日期</th>
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
							<h5>厂内倒运通知单详细信息</h5>
						</div>
					</div>
					<div class="modal-body">
						<div class="cg_div">
							<div class="cg_solo">
								<label>单据编号：</label> <input type="text" readonly="true"
									id="detail_code">
							</div>
							<div class="cg_solo">
								<label>调入堆场：</label> <input type="text" readonly="true"
									id="detail_enteryard">
							</div>
							<div class="cg_solo">
								<label>调出堆场：</label> <input type="text" readonly="true"
									id="detail_leaveyard">
							</div>
							<div class="cg_solo">
								<label>倒运单位：</label> <input type="text" readonly="true"
									id="detail_org">
							</div>
							<div class="cg_solo">
								<label>车号：</label> <input type="text" readonly="true"
									id="detail_vehicleno">
							</div>
							<div class="cg_solo">
								<label>RFID：</label> <input type="text" readonly="true"
									id="detail_rfid">
							</div>
							<div class="cg_solo">
								<label>物料：</label> <input type="text" readonly="true"
									id="detail_materiel">
							</div>
							<div class="cg_solo">
								<label>开始日期：</label> <input type="text" readonly="true"
									id="detail_starttime">
							</div>
							<div class="cg_solo">
								<label>截至日期：</label> <input type="text" readonly="true"
									id="detail_endtime">
							</div>
							<div class="cg_solo">
								<label>制单人：</label> <input type="text" readonly="true"
									id="detail_creator">
							</div>
							<div class="cg_solo">
								<label>制单日期： </label> <input type="text" readonly="true"
									id="detail_createtime">
							</div>
							<div class="cg_solo" style="width: 540px;">
								<label>状态：</label> <input type="text" readonly="true"
									id="detail_status" style="width: 400px;">
							</div>
							<div class="cg_solo">
								<label>备注： </label> <input type="text" readonly="true"
									id="detail_remark">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--查看详情end-->

	<script type="text/javascript">
		// 表格内容每行单击出来下面的详细信息
		var tabledata = $('.intel_table table tbody tr');
		tabledata.on("click", function() {
			$(".intel_result").css("display", "block");
		})
		// 表格内容每行双击出来下面的详细信息
		tabledata.on("dblclick", function() {
			$('#caigoubill').modal('show');
		})
		// 首页底部的tab切换菜单
		var ind_li = $('#ind_tab ul li');
		ind_li.click(function() {
			$(this).addClass('select').siblings().removeClass('select');
			var index_li = ind_li.index(this);
			$('#ind_tab .cg_tabbox > .cg_tabcont').eq(index_li).show()
					.siblings().hide();
		});

		// 弹出信息的tab切换菜单
		var alt_li = $('#alt_tab ul li');
		alt_li.click(function() {
			$(this).addClass('select').siblings().removeClass('select');
			var index_alt = alt_li.index(this);
			$('#alt_tab .cg_tabbox > .cg_tabcont').eq(index_alt).show()
					.siblings().hide();
		});
	</script>
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript" src="${staticBasePath}/js/cardReader.js"></script>
	<script type="text/javascript"
		src="/javascript/businessManage/otherManage/otherDYArrive.js?2018010301"></script>
</body>
</html>