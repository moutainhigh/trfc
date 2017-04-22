<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售计量单出厂编号编辑</title>
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
				<!--到货通知单begin-->
				<div class="intel_tabcont ">
					<div class="intel_search">
						<div class="intel_bggray">
							<div class="intel_bgblue"></div>
						</div>
						<div class="alt_opera">
							<ul>
								<li id="refreshBtn"><i class="iconfont colorlv">&#xe61b;</i>
									<h5>刷新</h5></li>
								<li id="addBtn"><i class="iconfont colorblue">&#xe61d;</i>
									<h5>保存</h5></li>
							</ul>
						</div>
					</div>
					<div class="daohuo_add">
						<h5>销售计量单出厂编号编辑</h5>
						<input id="poundNoteId" value="${poundNote.id }" type="hidden"/>
						<div class="daohuo_add_div">
							<div class="daohuo_add_solo">
								<label>计量单号：</label> <input type="text" value="${poundNote.code }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>发货单位：</label> <input type="text" value="${poundNote.senddepartmentname }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>客户：</label> <input type="text" value="${poundNote.getMainApplication().customername }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>车号：</label> <input type="text" value="${poundNote.vehicleno }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>物料：</label> <input type="text" value="${poundNote.getMainApplicationDetail().materielname }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>司机：</label> <input type="text" value="${poundNote.drivername }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>重车时间：</label> <input type="text" value="${poundNote.weighttimeStr }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>轻车时间：</label> <input type="text" value="${poundNote.lighttimeStr }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>身份证：</label> <input type="text" value="${poundNote.driveridentityno }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>毛重：</label> <input type="text" value="${poundNote.grossweight }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>皮重：</label> <input type="text" value="${poundNote.tareweight }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>净重：</label> <input type="text" value="${poundNote.netweight }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>通知单号：</label> <input type="text" value="${poundNote.noticecode }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>预提量：</label> <input type="text" value="${poundNote.pickupquantity }"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>过磅员：</label> <input type="text" value="${poundNote.weighername }" 
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>出厂编号：</label> 
								<div class="input_withlogo">
									<input id="batchnumber" type="text" value="${poundNote.serialnumber }" batchnumberid="${poundNote.batchnumberid }" 
										placeholder="请选择出厂编号" readonly="readonly"><span class="form-control-feedback"><i class="iconfont">&#xe608;</i></span>
								</div>
							</div>
						</div>
					</div>
					<div class="clear"></div>
					<div>
						<div class="cg_dhadd">
							<div class="cg_tabtit">
								<ul>
									<li class="select">订单信息</li>
								</ul>
							</div>
						</div>
						<div class="cg_tabbox">
							<!--tab切换的内容-->
							<div class="cg_tabcont">
								<div class="daohuo_add">
									<div class="daohuo_add_div">
										<div class="daohuo_add_solo">
											<label>订单一：</label> <input type="text" readOnly="true"
												value="${poundNote.getOneApplication().code }">
										</div>
										<div class="daohuo_add_solo">
											<label>订单二：</label> <input type="text" readOnly="true"
												value="${poundNote.getTwoApplication().code }">
										</div>
										<div class="daohuo_add_solo">
											<label>订单三：</label> <input type="text" readOnly="true"
												value="${poundNote.getThreeApplication().code }">
										</div>
										<div class="daohuo_add_solo">
											<label>订单一量：</label> <input type="text" readOnly="true"
												value="${poundNote.getOneApplication().list[0].margin }">
										</div>
										<div class="daohuo_add_solo">
											<label>订单二量：</label> <input type="text" readOnly="true"
												value="${poundNote.getTwoApplication().list[0].margin }">
										</div>
										<div class="daohuo_add_solo">
											<label>订单三量：</label> <input type="text" readOnly="true"
												value="${poundNote.getThreeApplication().list[0].margin }">
										</div>
										<div class="daohuo_add_solo">
											<label>订单一单价：</label> <input type="text" readOnly="true"
												value="${poundNote.getOneApplication().list[0].taxprice }">
										</div>
										<div class="daohuo_add_solo">
											<label>订单二单价：</label> <input type="text" readOnly="true"
												value="${poundNote.getTwoApplication().list[0].taxprice }">
										</div>
										<div class="daohuo_add_solo">
											<label>订单三单价：</label> <input type="text" readOnly="true"
												value="${poundNote.getThreeApplication().list[0].taxprice }">
										</div>
										<div class="daohuo_add_solo">
											<label>订单一金额：</label> <input type="text" readOnly="true"
												value="${poundNote.getOneApplication().list[0].margin * poundNote.getOneApplication().list[0].taxprice }">
										</div>
										<div class="daohuo_add_solo">
											<label>订单二金额：</label> <input type="text" readOnly="true"
												value="${poundNote.getTwoApplication().list[0].margin * poundNote.getTwoApplication().list[0].taxprice }">
										</div>
										<div class="daohuo_add_solo">
											<label>订单三金额：</label> <input type="text" readOnly="true"
												value="${poundNote.getThreeApplication().list[0].margin * poundNote.getThreeApplication().list[0].taxprice }">
										</div>
									</div>
								</div>
							</div>
							<!--tab切换的内容end-->
						</div>
					</div>
				</div>
			</div>
			<!--到货通知单end-->
		</div>
	</div>
	<!--出厂号弹出begin-->
	<div class="modal fade" id="altchuchang" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document"
			style="width: 70%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="alt_head">
						<h5>出厂编号</h5>
					</div>
				</div>
				<div class="modal-body">
					<div class="dhadd_search">

						<div class="dhsearch_solo">
							<label>批号：</label> <input id="factorycode" type="text" placeholder="请输入批号">
						</div>
						<div class="dhsearch_solo">
							<label>物料名称：</label> <input id="materiel" type="text" placeholder="请选择物料">
						</div>
						<div class="dhsearch_solo">
							<label>开始时间：</label> <input id="starttime" type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00'})"
								class="Wdate" style="width: 160px" readonly
								placeholder="请选择开始时间" />
						</div>
						<div class="dhsearch_solo">
							<label>结束时间：</label> <input id="endtime" type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00'})"
								class="Wdate" style="width: 160px" readonly
								placeholder="请选择结束时间" />
						</div>
						<div class="dhsearch_solo">
							<button id="searchBtn1" class="btn btnblue ">搜索</button>
							<button id="clearBtn1" class="btn btnblue ">清空</button>
						</div>
					</div>
					<div class="dh_alttable">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>单据编号</th>
									<th>物料</th>
									<th>批号</th>
									<th>数量</th>
									<th>过磅量</th>
									<th>余量</th>
									<th>生产日期</th>
									<th>试验日期</th>
									<th>化验日期</th>
									<th>化验人</th>
									<th>化验单位</th>
								</tr>
							</thead>
							<tbody id="batchNumberBody">
							</tbody>
						</table>
					</div>
					<!--分页效果开始-->
					<div class="page">
						<div class="page_date">
							<label>数据共：</label><i id="total" class="colorred">100</i><label>条</label>
						</div>
						<div class="page_date">
							<label>跳到第：</label> <input id="jumpPageNo" type="text" value="1">
							<label>页</label>
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
			</div>
		</div>
	</div>
	<!--出厂号弹出end-->
	<script type="text/javascript" src="/javascript/businessManage/poundNoteMaintain/salesPoundNoteUpdateSerialNumber.js"></script>
</body>
</html>