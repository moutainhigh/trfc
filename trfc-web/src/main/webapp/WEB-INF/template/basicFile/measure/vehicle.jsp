<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>车辆管理</title>
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
								<label>查询条件：</label>
								<select id="qtp" class="form-control">
									<option value="mc">名称</option>
									<option value="nm">内码</option>
								</select>
							</div>
							<div class="intel_solo">
								<label>关键字：</label> <input id="keyword" type="text">
							</div>
							<div class="intel_solo">
								<label>车辆属性：</label> <select id="transporttype" class="form-control">
									<option value="">请选择</option>
									<option value="0">非倒运</option>
									<option value="1">倒运</option>
								</select>
							</div>
							<div class="intel_solo">
								<label>黑名单：</label> <select id="isblacklist" class="form-control">
									<option value="">请选择</option>
									<option value="0">否</option>
									<option value="1">是</option>
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
					<div id="refresh" class="intel_operasolo">
						<a> <i class="iconfont colorlv">&#xe61b;</i>
						  <span>刷新</span>
                        </a>
					</div>
					<div id="add" class="intel_operasolo">
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
					<div id="black" class="intel_operasolo">
						<a> <i class="iconfont black">&#xe717;</i>
							<span>黑名单</span>
						</a>
					</div>
					<div id="white" class="intel_operasolo">
						<a> <i class="iconfont white">&#xe637;</i>
							<span>白名单</span>
						</a>
					</div>
				</div>
				<div class="intel_table">
					<!--用户表格begin-->
					<table class="table table-hover maintable">
						<thead>
							<tr>
								<th>序号</th>
								<th>编号</th>
								<th>RFID</th>
								<th>名称</th>
								<th>所属组织</th>
								<th>运输单位</th>
								<th>是否黑名单</th>
								<th>创建人</th>
								<th>描述</th>
							</tr>
						</thead>
						<tbody id="vehicleBody">
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
						<label>跳到第：</label> 
						<input id="jumpPageNo" type="text"> <label>页</label>
						<button id="jumpPageNoBtn" class="btn btn-default">确定</button>
					</div>
					<div class="page_date">
						<label>每页记录：</label>
						<select id="pageSize" class="form-control">
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
						<h5>车辆管理</h5>
					</div>
				</div>
				<div class="modal-body">
					<input id="vehicleid" type="hidden"/>
					<form class="layui-form">
						<div class="alt_edit_div">
							<label>车辆编号：</label> <input id="add_code" type="text" readonly>
						</div>
						<div class="alt_edit_div">
							<label>运输类型：</label>
							<select id="add_transporttype" class="form-control">
								<option value="0">非倒运</option>
								<option value="1">倒运</option>
							</select>
						</div>
						<div class="alt_edit_div">
							<label class="colorred">车辆号码 *：</label> <input id="add_vehicleno" lay-verify="vehicle" type="text" maxlength="7">
						</div>
						<div class="alt_edit_div">
							<label>车辆类型：</label> <input id="add_vehicletype" type="text">
						</div>
						<div class="alt_edit_div">
							<label>运输单位：</label> <input id="add_transportunit" type="text">
						</div>
						<div class="alt_edit_div">
							<label>最大载重：</label> <input id="add_maxweight" type="text">
						</div>
						<div class="alt_edit_div">
							<label>皮重：</label> <input id="add_tareweight" type="text">
						</div>
						<div class="alt_edit_div">
							<label>车主：</label> <input id="add_ownername" type="text">
						</div>
						<div class="alt_edit_div">
							<label>电话：</label> <input id="add_telephone" type="text">
						</div>
						<div class="alt_edit_div">
							<label>地址：</label> <input id="add_address" type="text">
						</div>
						<div class="alt_edit_div">
							<label>所属组织：</label> <input id="add_orgname" type="text" value="${orgname }" orgid="${orgid }" readonly>
						</div>
						<div class="alt_edit_div">
							<label>有效性：</label> <input id="add_isvalid" type="checkbox" checked="checked"><span>有效</span>
						</div>
						<div class="alt_edit_textarea">
							<label>备注： </label>
							<textarea id="add_remarks" class="form-control" rows="1"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="addCommit">确定</button>
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
						<h5>车辆管理</h5>
					</div>
				</div>
				<div class="modal-body">
					<input id="vehicleid" type="hidden"/>
					<div class="alt_edit">
						<div class="alt_edit_div">
							<label>车辆编号：</label> <input id="_code" type="text" readonly>
						</div>
						<div class="alt_edit_div">
							<label>运输类型：</label>
							<select id="_transporttype" class="form-control">
								<option value="0">非倒运</option>
								<option value="1">倒运</option>
							</select>
						</div>
						<div class="alt_edit_div">
							<label>车辆号码：</label> <input id="_vehicleno" type="text">
						</div>
						<div class="alt_edit_div">
							<label>车辆类型：</label> <input id="_vehicletype" type="text">
						</div>
						<div class="alt_edit_div">
							<label>运输单位：</label> <input id="_transportunit" type="text">
						</div>
						<div class="alt_edit_div">
							<label>最大载重：</label> <input id="_maxweight" type="text">
						</div>
						<div class="alt_edit_div">
							<label>皮重：</label> <input id="_tareweight" type="text">
						</div>
						<div class="alt_edit_div">
							<label>车主：</label> <input id="_ownername" type="text">
						</div>
						<div class="alt_edit_div">
							<label>电话：</label> <input id="_telephone" type="text">
						</div>
						<div class="alt_edit_div">
							<label>地址：</label> <input id="_address" type="text">
						</div>
						<div class="alt_edit_div">
							<label>所属组织：</label> <input id="_orgname" type="text" readonly>
						</div>
						<div class="alt_edit_div">
							<label>有效性：</label> <input id="_isvalid" type="checkbox"><span>有效</span>
						</div>
						<div class="alt_edit_textarea">
							<label>备注： </label>
							<textarea id="_remarks" class="form-control" rows="1"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="updateCommit">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!--编辑end-->
    <!--添加黑名单begin-->
    <div class="modal fade" id="blackView" tabindex="-1" role="dialog"
        aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document" style="width: 750px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <div class="alt_head">
                        <h5>添加黑名单</h5>
                    </div>
                </div>
                <div class="modal-body">
                    <input id="b_vehicleid" type="hidden"/>
                    <div class="alt_edit">
                        <div class="alt_edit_div">
                            <label>车号：</label>
                            <input id="b_vehicleno" type="text" readonly>
                        </div>
                        <div class="alt_edit_textarea">
                            <label>描述： </label>
                            <textarea id="b_remarks" class="form-control" rows="1"></textarea>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="addBlack">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
    <!--添加黑名单end-->
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript" src="/javascript/basicFile/measure/vehicle.js"></script>
</body>
</html>