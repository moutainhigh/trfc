<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>IC卡注册</title>
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
									<label>卡面编号：</label> <input id="cardcode" placeholder="请输入卡面编号" type="text">
								</div>
								<div class="intel_solo">
									<label>卡状态：</label> <select id="cardstatus"
										class="form-control">
										<option value="">请选择</option>
										<option value="0">无效</option>
										<option value="1">有效</option>
									</select>
								</div>
								<div class="intel_solo">
									<label>卡类型：</label> <select id="cardtype" class="form-control">
										<option value="">请选择</option>
										<option value="0">IC采样卡</option>
										<option value="1">IC过磅卡</option>
									</select>
								</div>
								<div class="intel_solo">
									<div class="intel_sbtn">
										<button id="searchBtn" class="btn btnblue">搜索</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="intel_opera">
						<div id="refreshBtn" class="intel_operasolo">
							<a> <i class="iconfont colorlv">&#xe61b;</i>
								<span>刷新</span>
							</a>
						</div>
						<div id="addBtn" class="intel_operasolo">
							<a> <i class="iconfont coloradd">&#xe627;</i>
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
									<th>序号</th>
									<th>单据编号</th>
									<th>卡序号</th>
									<th>卡面编号</th>
									<th>卡状态</th>
									<th>卡类型</th>
									<th>注册时间</th>
									<th>登记人</th>
									<th>备注</th>
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
			</div>
			<!--新增begin-->
			<div class="modal fade" id="addCardView" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 750px;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<div class="alt_head">
								<h5>IC卡注册管理-新增</h5>
							</div>
						</div>
						<div class="modal-body">
							<div class="alt_opera mb20">
								<ul>
									<li id="add_reset"><i class="iconfont colorlv">&#xe61b;</i>
										<h5>刷新</h5></li>
									<li id="add_save"><i class="iconfont colorblue">&#xe61d;</i>
										<h5>保存</h5></li>
									<li id="add_read"><i class="iconfont colorblue">&#xe601;</i>
										<h5>读卡</h5></li>
								</ul>
							</div>
							<div class="alt_edit">
								<div class="alt_edit_div">
									<label><em class="colorred">*</em>单据编号：</label> <input
										id="add_code" type="text" readonly>
								</div>
								<div class="alt_edit_div">
									<label><em class="colorred">*</em>IC卡序号：</label> <input
										id="add_cardno" type="text" readonly>
								</div>
								<div class="alt_edit_div">
									<label><em class="colorred">*</em>卡面编号：</label> <input
										id="add_cardcode" type="text">
								</div>
								<div class="alt_edit_div">
									<label><em class="colorred">*</em>登记人：</label> <input
										id="add_registrar" type="text" readonly>
								</div>
								<div class="alt_edit_div">
									<label><em class="colorred">*</em>注册时间：</label> <input
										id="add_createtime" type="text" readonly>
								</div>
								<div class="alt_edit_div">
									<label><em class="colorred">*</em>卡类型：</label> <select
										id="add_cardtype" class="form-control">
										<option value="">请选择</option>
										<option value="0">IC采购卡</option>
										<option value="1">IC过磅卡</option>
									</select>
								</div>
								<div class="alt_edit_div">
									<label><em class="colorred">*</em>有效性：</label> <input
										id="add_cardstatus" type="checkbox" checked><span>有效</span>
								</div>
								<div class="alt_edit_textarea">
									<label>备注： </label>
									<textarea id="add_remarks" class="form-control" rows="1"></textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--新增end-->
			<!--编辑begin-->
			<div class="modal fade" id="updateCardView" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 750px;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<div class="alt_head">
								<h5>IC卡注册管理-修改</h5>
							</div>
						</div>
						<div class="modal-body">
							<div class="alt_opera mb20">
								<ul>
									<li id="update_reset"><i class="iconfont colorlv">&#xe61b;</i>
										<h5>刷新</h5></li>
									<li id="update_save"><i class="iconfont colorblue">&#xe61d;</i>
										<h5>保存</h5></li>
									<li id="update_read"><i class="iconfont colorblue">&#xe601;</i>
										<h5>读卡</h5></li>
								</ul>
							</div>
							<input id="update_id" type="hidden">
							<div class="alt_edit">
								<div class="alt_edit_div">
									<label>单据编号：</label> <input id="update_code" type="text"
										readonly>
								</div>
								<div class="alt_edit_div">
									<label>IC卡序号：</label> <input id="update_cardno" type="text"
										readonly>
								</div>
								<div class="alt_edit_div">
									<label>卡面编号：</label> <input id="update_cardcode" type="text">
								</div>
								<div class="alt_edit_div">
									<label>登记人：</label> <input id="update_registrar" type="text"
										readonly>
								</div>
								<div class="alt_edit_div">
									<label>注册时间：</label> <input id="update_createtime" type="text"
										readonly>
								</div>
								<div class="alt_edit_div">
									<label>卡类型：</label> <select id="update_cardtype"
										class="form-control">
										<option value="">请选择</option>
										<option value="0">IC采购卡</option>
										<option value="1">IC过磅卡</option>
									</select>
								</div>
								<div class="alt_edit_div">
									<label>有效性：</label> <input id="update_cardstatus"
										type="checkbox"><span>有效</span>
								</div>
								<div class="alt_edit_textarea">
									<label>备注： </label>
									<textarea id="update_remarks" class="form-control" rows="1"></textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--编辑end-->
		</div>
	</div>
	<!--删除end-->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript" src="${staticBasePath}/js/cardReader.js"></script>
	<script type="text/javascript"
		src="/javascript/businessManage/cardManage/card.js"></script>
</body>
</html>