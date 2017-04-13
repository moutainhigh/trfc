<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无人值守-其他档案-其他车辆</title>
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
                <select class="form-control" id="vehicle_query">
                    <option value="name">名称</option>
                    <option value="innercode">内码</option>
                </select>
            </div>
            <div class="intel_solo">
                <label>关键字：</label>
                <input type="text" id="vehicle_keyword">
            </div>
            <div class="intel_solo">
                <div class="intel_sbtn">
                    <button class="btn btnblue ">搜索</button>
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
    <div class="intel_operasolo" id="showAddVehicle">
        <a data-toggle="modal" data-target="#add">
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
            <th>描述</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="vehicles">
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
        <button class="btn btn-default">确定</button>
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


<!-- 新增begin -->
<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <div class="alt_head">
                    <h5>其他车辆信息-新增</h5>
                </div>
            </div>
            <div class="modal-body">
                <div class="alt_edit">
                    <div class="alt_edit_div">
                        <label>编号：</label>
                        <input type="text" value="121221" readonly="readonly" id="vehicle_code">
                    </div>
                    <div class="alt_edit_div">
                        <label>内码：</label>
                        <input type="text" readonly="readonly" id="vehicle_innercode">
                    </div>
                    <div class="alt_edit_div">
                        <label>名称：</label>
                        <input type="text" id="vehicle_name">
                    </div>
                    <div class="alt_edit_div">
                        <label>简称：</label>
                        <input type="text" id="vehicle_info">
                    </div>
                    <div class="alt_edit_div">
                        <label>所属组织：</label>
                        <input type="text" id="vehicle_orgname">
                    </div>
                    <div class="alt_edit_div">
                        <label>有效性：</label>
                        <input type="checkbox" id="vehicle_isvalid"><span>有效</span>
                    </div>
                    <div class="alt_edit_textarea">
                        <label>备注： </label>
                        <textarea class="form-control" rows="1" id="vehicle_remark"></textarea>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="add_vehicle">确定</button>
                <button type="button" class="btn btn-default" id="add_vehicle_hide" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 新增end -->


<!--编辑begin-->
<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <div class="alt_head">
                    <h5>其他车辆信息-编辑</h5>
                </div>
            </div>
            <div class="modal-body">
                <div class="alt_edit">
                    <div class="alt_edit_div">
                        <label>编号：</label>
                        <input type="text"  readonly="readonly"  id="update_vehicle_code">
                    </div>
                    <div class="alt_edit_div">
                        <label>内码：</label>
                        <input type="text"  readonly="readonly"  id="update_vehicle_innercode">
                    </div>
                    <div class="alt_edit_div">
                        <label>名称：</label>
                        <input type="text" id="update_vehicle_name">
                    </div>
                    <div class="alt_edit_div">
                        <label>简称：</label>
                        <input type="text" id="update_vehicle_info">
                    </div>
                    <div class="alt_edit_div">
                        <label>所属组织：</label>
                        <input type="text" id="update_vehicle_orgname">
                    </div>
                    <div class="alt_edit_div">
                        <label>有效性：</label>
                        <input type="checkbox" id="update_vehicle_isvalid"><span>有效</span>
                    </div>
                    <div class="alt_edit_textarea">
                        <label>备注： </label>
                        <textarea class="form-control" rows="1" id="update_vehicle_remark"></textarea>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="update_vehicle">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--编辑end-->
<!--删除begin-->

<!--删除end-->


<script type="text/javascript" src="/javascript/basicFile/other/other_car.js"></script>
		
<!--  
<script type="text/javascript">
    // 顶部tab切换菜单
    var $tab_li = $('.intel_menu li');
    $tab_li.click(function () {
        $(this).addClass('select').siblings().removeClass('select');
        var index = $tab_li.index(this);
        $('.intel_tabbox > .intel_tabcont').eq(index).show().siblings().hide();
    });
    // 表格内容每行单击出来下面的详细信息
    var tabledata = $('.intel_table table tbody tr');
    tabledata.on("click", function () {
        $(".intel_result").css("display", "block");
    })
    // 表格内容每行双击出来下面的详细信息
    tabledata.on("dblclick", function () {
        $('#caigoubill').modal('show');
    })
    // 左侧宽度改变 右边改变
    var leftall = $(".left");
    var leftmini = $(".leftmini");
    leftall.on("click", function () {
        $(this).css("display", "none");
        leftmini.css("display", "block");
        $(".right").css("margin-left", "100px");
    });
    leftmini.on("click", function () {
        $(this).css("display", "none");
        leftall.css("display", "block");
        $(".right").css("margin-left", "200px");
    });
    // 首页底部的tab切换菜单
    var ind_li = $('#ind_tab ul li');
    ind_li.click(function () {
        $(this).addClass('select').siblings().removeClass('select');
        var index_li = cg_li.index(this);
        $('#ind_tab .cg_tabbox > .cg_tabcont').eq(index_li).show().siblings().hide();
    });

    // 弹出信息的tab切换菜单
    var alt_li = $('#alt_tab ul li');
    alt_li.click(function () {
        $(this).addClass('select').siblings().removeClass('select');
        var index_alt = alt_li.index(this);
        $('#alt_tab .cg_tabbox > .cg_tabcont').eq(index_alt).show().siblings().hide();
    });
</script>-->

<!-- 引用公共footer部分 -->
<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>

	
</body>
</html>