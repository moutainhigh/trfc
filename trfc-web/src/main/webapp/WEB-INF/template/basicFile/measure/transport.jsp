<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>运输单位</title>
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
<div class="intel_tab">
    <!--tab切换标题-->
		<ul class="intel_menu">
			<li class="select"><a href="/trfc/transport/main">运输单位</a></li>
			<li><a href="/trfc/vehicle/main">车辆管理</a></li>
			<li><a href="/trfc/driver/main">司机管理</a></li>
			<li><a href="/trfc/minemouth/main">矿口管理</a></li>
		</ul>
</div>

<!--tab切换的内容-->
<div class="intel_tabbox">
<!--采购申请单begin-->
<div class="intel_tabcont">
<div class="intel_search">
    <div class="intel_bggray">
        <div class="intel_bgblue"></div>
    </div>
    <div class="intel_sconditon">
        <div class="intel_solo">
            <label>查询条件：</label>
            <select class="form-control" id="transport_query">
                <option value="name">名称</option>
                <option value="internalcode">内码</option>
            </select>
        </div>
        <div class="intel_sline">
            <div class="intel_solo">
                <label>关键字：</label>
                <input type="text" id="transport_keyword">
            </div>
            <div class="intel_solo">
                <label>所属组织：</label>
                <input type="text" id="transport_organizename">
            </div>

            <div class="intel_solo">
                <div class="intel_sbtn">
                    <input type="checkbox" id="show_isvalid"><span>只显示有效的</span>
                    <button id="searshBtn" class="btn btnblue ">搜索</button>

                </div>
            </div>
        </div>
    </div>
</div>
<div class="intel_opera">
    <div  id="refreshBtn"  class="intel_operasolo">
        <i class="iconfont colorlv">&#xe61b;</i>
        <h5>刷新</h5>
    </div>
    <div  id="addBtn" class="intel_operasolo">
        <a  data-toggle="modal" data-target="#add">
            <i class="iconfont coloradd">&#xe627;</i>
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
            <th>编号</th>
            <th>内码</th>
            <th>名称</th>
            <th>所属组织</th>
            <th>有效</th>
            <th>描述</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="transports">
        <tr>
            <td> CD201601010138</td>
            <td>审核中</td>
            <td>客商APP</td>
            <td>未入厂</td>
            <td>粉煤灰1</td>
            <td>粉煤灰1</td>
            <td>
                <span >
                <a data-toggle="modal" data-target="#edit"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>
            </span>
            <span >
                <a data-toggle="modal" data-target="#dele"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>
            </span>
            </td>
        </tr>
        </tbody>
    </table>
    <!--用户表格end-->
</div>

<!--分页效果开始-->
<div class=" row fr">
    <div class="page_date">
        <label>数据共：</label><i class="colorred">100</i><label>条</label>
    </div>
    <div class="page_date">
        <label>跳到第：</label>
        <input type="text" id="jumpPageNo">
        <label>页</label>
        <button class="btn btn-default" id="jumpButton">确定</button>
    </div>
    <div class="page_date">
        <label>每页记录：</label><select id="pageSize" class="form-control">
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

<!--到货通知单begin-->
<div class="intel_tabcont hide">
    2
</div>
<!--到货通知单end-->

<!--退货通知单begin-->
<div class="intel_tabcont hide">
    3
</div>
<!--退货通知单end-->

<!--到货通知单begin-->
<div class="intel_tabcont hide">
    4
</div>
<!--到货通知单end-->
<!--到货通知单begin-->
<div class="intel_tabcont hide">
    5
</div>
<!--到货通知单end-->
<!--tab切换的内容end-->
</div>
</div>
<!--新增begin-->
<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <div class="alt_head">
                    <h5>运输单位新增</h5>
                </div>
            </div>
            <div class="modal-body">
                <div class="alt_edit">
                    <div class="alt_edit_div">
                        <label>运输单位编号：</label>
                        <input type="text" readonly="true" value="11" id="transport_code">
                    </div>
                    <div class="alt_edit_div">
                        <label>运输单位内码：</label>
                        <input type="text" readonly="true" value="11" id="transport_internalcode">
                    </div>
                    <div class="alt_edit_div">
                        <label>运输单位名称：</label>
                        <input type="text" id="transport_name">
                    </div>
                    <div class="alt_edit_div">
                        <label>运输单位简称：</label>
                        <input type="text" id="transport_abbrname">
                    </div>
                    <div class="alt_edit_div">
                        <label>地址：</label>
                        <input type="text" id="transport_address">
                    </div>
                    <div class="alt_edit_div">
                        <label>电话：</label>
                        <input type="text" id="transport_telephone">
                    </div>
                    <div class="alt_edit_div">
						<label>所属组织：</label> 
						<input id="transport_orgname" type="text">
					</div>
                    <div class="alt_edit_div">
                        <label>有效性：</label>
                        <input type="checkbox" id="transport_isvalid"><span>有效</span>
                    </div>
                    <div class="alt_edit_textarea">
                        <label>备注： </label>
                        <textarea class="form-control" rows="1" id="transport_remarks"></textarea>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="addTransport">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--新增end-->
<!--编辑begin-->
<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <div class="alt_head">
                    <h5>运输单位编辑</h5>
                </div>
            </div>
            <div class="modal-body">
                <div class="alt_edit">
                    <div class="alt_edit_div">
                        <label>运输单位编号：</label>
                        <input type="text" readonly="true" value="00" id="update_transport_code">
                    </div>
                    <div class="alt_edit_div">
                        <label>运输单位内码：</label>
                        <input type="text" readonly="true" value="11" id="update_transport_internalcode">
                    </div>
                    <div class="alt_edit_div">
                        <label>运输单位名称：</label>
                        <input type="text" id="update_transport_name">
                    </div>
                    <div class="alt_edit_div">
                        <label>运输单位简称：</label>
                        <input type="text" id="update_transport_abbrname">
                    </div>
                    <div class="alt_edit_div">
                        <label>地址：</label>
                        <input type="text" id="update_transport_address">
                    </div>
                    <div class="alt_edit_div">
                        <label>电话：</label>
                        <input type="text" id="update_transport_telephone">
                    </div>
                     <div class="alt_edit_div">
						<label>所属组织：</label> 
						<input id="update_transport_orgname" type="text">
					</div>
                    <div class="alt_edit_div">
                        <label>有效性：</label>
                        <input type="checkbox" id="update_transport_isvalid"><span>有效</span>
                    </div>
                    <div class="alt_edit_textarea">
                        <label>备注： </label>
                        <textarea class="form-control" rows="1" id="update_transport_remarks"></textarea>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="update_transport">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--编辑end-->
<!--删除begin-->
<!--删除end-->
</div>
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/basicFile/measure/transport.js"></script>


</body>
</html>