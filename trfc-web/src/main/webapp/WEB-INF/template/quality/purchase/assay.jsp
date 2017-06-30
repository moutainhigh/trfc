<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购化验管理</title>
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
									<label>开始时间：</label> <input type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'seek_endtime\')}'})"
										class="Wdate" style="width: 160px" id="seek_starttime"
										placeholder="请选择开始时间" />
								</div>
								<div class="intel_solo">
									<label>结束时间：</label> <input type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'seek_starttime\')}'})"
										class="Wdate" style="width: 160px" id="seek_endtime"
										placeholder="请选择结束时间" />
								</div>
								<div class="intel_solo">
									<label>单据编号：</label> <input type="text" id="seek_code"
										placeholder="请输入单据编号">
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
							<a> <i class="iconfont colorlv">&#xe61b;</i>
								<span>刷新</span>
							</a>
						</div>
						<div class="intel_operasolo">
							<a data-toggle="modal" data-target="#add" id="addBtn"> <i
								class="iconfont coloradd">&#xe627;</i>
								<span>新增</span>
							</a>
						</div>
						<div id="update" class="intel_operasolo">
							<a> <i class="iconfont update">&#xe600;</i>
								<span>编辑</span>
							</a>
						</div>
						<div id="delete" class="intel_operasolo">
							<a> <i class="iconfont delete">&#xe63d;</i>
								<span>删除</span>
							</a>
						</div>
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover maintable">
							<thead>
								<tr>
									<th>行号</th>
									<th>单据编号</th>
									<th>质检产品方案</th>
									<th>采样单号</th>
									<th>化验日期</th>
									<th>烧失量</th>
									<th>质量系数</th>
									<th>水分</th>
									<th>外水分</th>
									<th>分析基水分</th>
									<th>分析基灰分</th>
									<th>分析基挥发分</th>
									<th>固定碳</th>
									<th>焦渣特征</th>
									<th>空气干燥基全硫</th>
									<th>分析基低位发热量</th>
									<th>收到基低位发热量</th>
									<th>低位热量</th>
									<th>制单人</th>
									<th>制单日期</th>
									<th>备注</th>
								</tr>
							</thead>
							<tbody id="list">
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
				<!--tab切换的内容end-->
			</div>
		</div>

		<!--新增begin-->
		<div class="modal fade" id="add" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width: 1000px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="alt_head">
							<h5>采购化验管理新增</h5>
						</div>
					</div>
					<div class="modal-body">

						<div class="alt_edit">
							<div class="alt_edit_div">
								<label>质检产品：</label> <input type="text" id="add_qscheme"
									class="qschemeSel" placeholder="请输入质检产品">
							</div>
							<div class="alt_edit_div">
								<label>单据编号：</label> <input type="text" id="add_code"
									readOnly="true">
							</div>
							<div class="alt_edit_div">
								<label>采样单号：</label> <input type="text" id="add_sampling" placeholder="请输入采样单号">
							</div>
							<div class="alt_edit_div">
								<label>化验日期：</label> <input type="text"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate_cg"
									style="width: 220px" id="add_assaytime" readOnly="true" />
							</div>
							<div class="alt_edit_div">
								<label> 制单时间：</label> <input type="text" id="add_createtime"
									readOnly="true">
							</div>
							<div class="alt_edit_div">
								<label>制单人：</label> <input type="text" id="add_creator"
									readOnly="true">
							</div>
							<div class="alt_edit_div">
								<label>备注： </label> <input type="text" id="add_remark">
							</div>
						</div>
						<div id="alt_tab">
							<div class="cg_tabtit">
								<ul>
									<li class="select">质检项目</li>
									<li>采样项目</li>
								</ul>
							</div>
							<div class="cg_tabbox">
								<!--tab切换的内容-->
								<div class="cg_tabcont">
									<div class="alt_edit" id="add_qschemeitem">
										<div class="alt_edit_div">
											<label>烧失量：</label> <input type="text">
										</div>
									</div>

								</div>
								<div class="cg_tabcont hide">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th>采样编号</th>
												<th>化验类型</th>
											</tr>
										</thead>
										<tbody id="add_sampinglist">
											<tr>
												<td>合计：</td>
												<td>100</td>
											</tr>
										</tbody>
									</table>
								</div>
								<!--tab切换的内容end-->
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
			<div class="modal-dialog" role="document" style="width: 1000px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="alt_head">
							<h5>采购化验管理编辑</h5>
						</div>
					</div>
					<div class="modal-body">
						<div class="alt_edit">
							<div class="alt_edit_div">
								<input type="hidden" id="edit_id" /> <label>质检产品：</label> <input
									type="text" id="edit_qscheme" class="qschemeSel">
							</div>
							<div class="alt_edit_div">
								<label>单据编号：</label> <input type="text" id="edit_code"
									readOnly="true">
							</div>
							<div class="alt_edit_div">
								<label>采样单号：</label> <input type="text" id="edit_sampling">
							</div>
							<div class="alt_edit_div">
								<label>化验日期：</label> <input type="text"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate_cg"
									style="width: 220px" id="edit_assaytime" readOnly="true" />
							</div>
							<div class="alt_edit_div">
								<label> 制单时间：</label> <input type="text" id="edit_createtime"
									readOnly="true">
							</div>
							<div class="alt_edit_div">
								<label>制单人：</label> <input type="text" id="edit_creator"
									readOnly="true">
							</div>
							<div class="alt_edit_div">
								<label>备注： </label> <input type="text" id="edit_remark">
							</div>
						</div>
						<div id="alt_tab">
							<div class="cg_tabtit">
								<ul>
									<li class="select">质检项目</li>
									<li>采样项目</li>
								</ul>
							</div>
							<div class="cg_tabbox">
								<!--tab切换的内容-->
								<div class="cg_tabcont">
									<div class="alt_edit" id="edit_qschemeitem">
										<div class="alt_edit_div">
											<label>烧失量：</label> <input type="text">
										</div>
									</div>

								</div>
								<div class="cg_tabcont hide">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th>采样编号</th>
												<th>化验类型</th>
											</tr>
										</thead>
										<tbody id="edit_sampinglist">
											<tr>
												<td>合计：</td>
												<td>100</td>
											</tr>
										</tbody>
									</table>
								</div>
								<!--tab切换的内容end-->
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
		<!--详情begin-->
		<div class="modal fade" id="detail" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width: 1000px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="alt_head">
							<h5>采购化验管理详情</h5>
						</div>
					</div>
					<div class="modal-body">
						<div class="alt_edit">
							<div class="alt_edit_div">
								<label>质检产品：</label> <input type="text" id="detail_qscheme"
									readOnly="true">
							</div>
							<div class="alt_edit_div">
								<label>单据编号：</label> <input type="text" id="detail_code"
									readOnly="true">
							</div>
							<div class="alt_edit_div">
								<label>采样单号：</label> <input type="text" id="detail_sampling"
									readOnly="true">
							</div>
							<div class="alt_edit_div">
								<label>化验日期：</label> <input type="text" id="detail_assaytime"
									readOnly="true" />
							</div>
							<div class="alt_edit_div">
								<label> 制单时间：</label> <input type="text" id="detail_createtime"
									readOnly="true">
							</div>
							<div class="alt_edit_div">
								<label>制单人：</label> <input type="text" id="detail_creator"
									readOnly="true">
							</div>
							<div class="alt_edit_div">
								<label>备注： </label> <input type="text" id="detail_remark"
									readOnly="true">
							</div>
						</div>
						<div id="alt_tab">
							<div class="cg_tabtit">
								<ul>
									<li class="select">质检项目</li>
									<li>采样项目</li>
								</ul>
							</div>
							<div class="cg_tabbox">
								<!--tab切换的内容-->
								<div class="cg_tabcont">
									<div class="alt_edit" id="detail_qschemeitem"></div>

								</div>
								<div class="cg_tabcont hide">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th>采样编号</th>
												<th>化验类型</th>
											</tr>
										</thead>
										<tbody id="detail_sampinglist">
											<tr>
											</tr>
										</tbody>
									</table>
								</div>
								<!--tab切换的内容end-->
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
		<!--编辑end-->
		<script type="text/javascript">
    // 表格内容每行单击出来下面的详细信息
    var tabledata = $('.intel_table table tbody tr');
    tabledata.on("click", function () {
        $(".intel_result").css("display", "block");
    })
    // 表格内容每行双击出来下面的详细信息
    tabledata.on("dblclick", function () {
        $('#caigoubill').modal('show');
    })
      // 弹出信息的tab切换菜单
    var alt_li = $('#alt_tab .cg_tabtit ul li');
    alt_li.click(function () {
        $(this).addClass('select').siblings().removeClass('select');
        var index_alt = alt_li.index(this);
        $('#alt_tab .cg_tabbox > .cg_tabcont').eq(index_alt).show().siblings().hide();
    });

</script>
		<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
		<script type="text/javascript"
			src="/javascript/quality/purchase/assay.js"></script>
	</div>
</body>
</html>
