<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售化验报告-新增</title>
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>
</head>
<body>

	<div class="it_admin">
		<jsp:include page="../../common/base/left_busi.jsp"></jsp:include>
		<div class="right">
			<jsp:include page="../../common/base/right_head_busi.jsp"></jsp:include>
			<div class="intel_tabbox">
				<!--采购申请单begin-->
				<div class="intel_tabcont hide"></div>
				<!--采购申请单end-->

				<!--到货通知单begin-->
				<div class="intel_tabcont ">
					<div class="intel_search">
						<div class="intel_bggray">
							<div class="intel_bgblue"></div>
						</div>
						<div class="alt_opera">
							<ul>
								<li  id="fresh"><i class="iconfont colorlv">&#xe61b;</i>
									<h5>刷新</h5></li>
								<li  id="save"><i class="iconfont colorblue">&#xe61d;</i>
									<h5>保存</h5></li>
								<li id="goBack"><a> <i class="iconfont colorblue">&#xe61e;</i>
										<h5>返回</h5>
								</a></li>
							</ul>
						</div>
					</div>
					<div class="daohuo_add">
						<h5>化验报告单录入</h5>
						<div class="daohuo_add_div">
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>单据编号：</label> <input
									type="text" readonly="readonly" value="011" id="add_code">
								<input type="checkbox" id="add_pstate"> <em>打印</em>
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>批号：</label>
								<div class="input_withlogo">
									<input type="text" data-toggle="modal" data-target="#altbill"
										id="add_batchcode" placeholder="请选择批号"> <span
										class="form-control-feedback"><i class="iconfont">&#xe608;</i></span>
								</div>
							</div>
							<div class="daohuo_add_solo">
								<label>生产日期：</label> <input type="text" readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
									class="Wdate" id="add_producedtime"  placeholder="请选择生产日期"/>
							</div>
							<div class="daohuo_add_solo">
								<label>试验日期：</label> <input type="text" readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
									class="Wdate" id="add_testtime"  placeholder="请选择试验日期"/>
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>水泥品种：</label> <input
									type="text" id="add_materialtype" placeholder="请选择水泥品种">
							</div>
							<div class="daohuo_add_solo">
								<label>强度等级：</label> <input type="text" readonly="readonly"
									id="add_strength">
							</div>
							<div class="daohuo_add_solo">
								<label>混合材品种：</label> <input type="text" readonly="readonly"
									id="add_admixture">
							</div>
							<div class="daohuo_add_solo">
								<label>混合材掺加量：</label> <input type="text" readonly="readonly"
									id="add_admixtureadd">
							</div>
							<div class="daohuo_add_solo">
								<label>石膏种类：</label> <input type="text" readonly="readonly"
									id="add_gypsum">
							</div>
							<div class="daohuo_add_solo">
								<label>石膏掺加量：</label> <input type="text" readonly="readonly"
									id="add_gypsumadd">
							</div>
							<div class="daohuo_add_solo">
								<label>助磨剂种类：</label> <input type="text" readonly="readonly"
									id="add_aid">
							</div>
							<div class="daohuo_add_solo">
								<label>助磨剂掺加量：</label> <input type="text" readonly="readonly"
									id="add_aidadd">
							</div>
							<div class="daohuo_add_solo">
								<label>质检方案：</label> <input type="text" placeholder="请选择质检方案" id="add_qscheme">
							</div>
							<div class="daohuo_add_solo">
								<label>报告单位：</label> <input type="text" readonly="readonly"
									id="add_reportorg">
							</div>
							<div class="daohuo_add_solo">
								<label>报告人：</label> <input type="text" id="add_reporter">
							</div>
							<div class="daohuo_add_solo">
								<label>地址：</label> <input type="text" id="add_addr">
							</div>
							<div class="daohuo_add_solo">
								<label>制单日期：</label> <input type="text" readonly="readonly"
									id="add_creattime">
							</div>
							<div class="daohuo_add_solo">
								<label>制单人：</label> <input type="text" readonly="readonly"
									id="add_creator">
							</div>
							<div class="daohuo_add_solo">
								<label>销售日期：</label> <input type="text" readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
									class="Wdate" id="add_selldate" placeholder="请选择销售日期"/>
							</div>
							<div class="daohuo_add_solo">
								<label>备注：</label> <input type="text" id="add_remark">
							</div>
						</div>
					</div>
					<div class="clear"></div>
					<div>
						<div class="cg_dhadd">
							<div class="cg_tabtit">
								<ul>
									<li class="select">检验明细</li>
								</ul>
							</div>
						</div>
						<div class="cg_tabbox">
							<!--tab切换的内容-->
							<div class="cg_tabcont">
								<div class="total_addscroll">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th>行号</th>
												<th>品种指标</th>
												<th>单位</th>
												<th>标准值</th>
												<th>检测值</th>
											</tr>
										</thead>
										<tbody id="detail_list">
											
										</tbody>
									</table>
								</div>
							</div>
							<!--tab切换的内容end-->
						</div>
					</div>
				</div>


			</div>
		</div>

	</div>
	<!--订单号弹出begin-->
	<div class="modal fade" id="altbill" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width: 800px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" id="closeBth">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="alt_head">
						<h5>选销售批号</h5>
					</div>
				</div>
				<div class="modal-body">
					<div class="dhadd_search">
						<div class="dhsearch_solo">
							<label>开始时间：</label> <input type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
								class="Wdate" style="width: 160px" id="select_starttime" />
						</div>
						<div class="dhsearch_solo">
							<label>结束时间：</label> <input type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
								class="Wdate" style="width: 160px" id="select_endtime" />
						</div>
						<div class="dhsearch_solo">
							<label>状态：</label> <select id="select_billsstate">
							<option value="">请选择</option>
							<option value="1">启用</option>
							<option value="0">停用</option>
							</select>
						</div>
						<div class="dhsearch_solo">
							<label>批号：</label> <input type="text" id="select_factorycode">
						</div>
						<div class="dhsearch_solo">
							<label>物料名称：</label> <input type="text" id="select_material">
						</div>
						<div class="dhsearch_solo">
							<button class="btn btnblue " id="select_seek">搜索</button>
						</div>
					</div>
					<div style="width: 750px;overflow-x: scroll">
						<div class="dh_alttable">
							<table class="table table-hover">
								<thead>
									<tr>
										<th>单据编号</th>
										<th>物料</th>
										<th>批号</th>
										<th>数量</th>
										<th>生产日期</th>
										<th>实验日期</th>
									</tr>
								</thead>
								<tbody id="select_list">
									<tr>
										<td>CD201601010138</td>
										<td class="colorred">审核中</td>
										<td>客商APP</td>
										<td>未入厂</td>
										<td>豫GA1783</td>
										<td>CD201601010138</td>
									</tr>
								</tbody>
							</table>
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
	</div>




	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/quality/sales/assayReport_add.js"></script>
</body>
</html>
