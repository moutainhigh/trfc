<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
   <title>其他入库通知单-新增</title>
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
        <div class="intel_tabcont hide">
        </div>
        <!--采购申请单end-->

        <!--到货通知单begin-->
        <div class="intel_tabcont ">
            <div class="intel_search">
                <div class="intel_bggray">
                    <div class="intel_bgblue"></div>
                </div>
                <div class="alt_opera">
                    <ul>
                        <li id="refresh">
                            <i class="iconfont colorlv">&#xe61b;</i>
                            <h5>刷新</h5>
                        </li>
                        <li id="saveBtn">
                            <i class="iconfont colorblue">&#xe61d;</i>
                            <h5>保存</h5>
                        </li>
                        <li id="writeCard">
                            <i class="iconfont colorblue">&#xe601;</i>
                            <h5>保存写卡</h5>
                        </li>
                        <li id="goback">
                            <a>
                                <i class="iconfont colorblue">&#xe61e;</i>
                                <h5>返回</h5>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="daohuo_add">
                <h5>其他入库通知单新增 </h5>
                <div class="daohuo_add_div">
                    <div class="daohuo_add_solo">
                        <label class="colorred">*通知单号：</label>
                        <input type="text" readonly="true" id="add_code">
                    </div>
                    <div class="daohuo_add_solo">
                        <label class="colorred">*供应商：</label>
                        <input type="text" id="add_supplier" placeholder="请选择供应商">
                    </div>
                    <div class="daohuo_add_solo">
                        <label class="colorred">*单位：</label>
                        <input type="text" id="add_datasource" placeholder="请输入单位(货源)">
                    </div>
                    <div class="daohuo_add_solo">
                        <label class="colorred">*物料：</label>
                        <input type="text" id="add_materiel" placeholder="请选择物料">
                    </div>
                    <div class="daohuo_add_solo">
                        <label class="colorred">*货物：</label>
                        <input type="text" id="add_cargo" placeholder="请输入货物名称">
                    </div>
                    <div class="daohuo_add_solo">
                        <label class="colorred">*收货单位：</label>
                        <input type="text" id="add_receivedepartment" readonly="true" value="天瑞集团水泥有限公司（裕泰公司）">
                    </div>
                    <div class="daohuo_add_solo">
                        <label class="colorred">*车号：</label>
                        <input type="text" id="add_vehicle"  placeholder="请选择车号">
                        <a data-toggle="modal" data-target="#carnum"><i class="iconfont">
                            &#xe680;</i></a>
                    </div>
                    <div class="daohuo_add_solo">
                        <label>RFID：</label>
                        <input type="text" id="add_rfid" readonly="true">
                    </div>
                    <div class="daohuo_add_solo">
                        <label>仓库：</label>
                        <input type="text" id="add_warehouse" placeholder="请选择仓库">
                    </div>
                    <div class="daohuo_add_solo">
                        <label>司机： </label>
                        <input type="text" id="add_driver" placeholder="请选择司机">
                        <a data-toggle="modal" data-target="#driver"><i class="iconfont">
                            &#xe680;</i></a>
                    </div>
                    <div class="daohuo_add_solo">
                        <label>身份证号： </label>
                        <input type="text" id="add_identityno" readonly="true">
                    </div>
                    <div class="daohuo_add_solo">
                        <label>数量： </label>
                        <input type="text" id="add_count"  placeholder="请输入货物数量">吨
                    </div>
                    <div class="daohuo_add_solo">
                        <label>单位： </label>
                        <input type="text" value="吨">
                    </div>
                    <div class="daohuo_add_solo">
                        <label>制单人： </label>
                        <input type="text" id="add_creator" readonly="true">
                    </div>
                    <div class="daohuo_add_solo">
                        <label>制单日期： </label>
                        <input type="text" id="add_createtime" readonly="true">
                    </div>
                    <div class="daohuo_add_solo">
                        <label>备注： </label>
                        <input type="text" id="add_remark">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</div>

</div>
<!--车号新增begin-->
<div class="modal fade" id="carnum" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <div class="alt_head">
                    <h5>车辆管理--新增</h5>
                </div>
            </div>
            <div class="modal-body">
                <div class="alt_edit">
                    <div class="alt_edit_div">
                        <label>车辆编号：</label>
                        <input type="text" id="vehicle_code">
                    </div>
                    <div class="alt_edit_div">
                        <label>运输类型：</label>
                        <select class="form-control"  id="vehicle_transporttype">
                            <option value="">请选择</option>
                            <option value="1">倒运</option>
                            <option value="0">非倒运</option>
                        </select>
                    </div>
                    <div class="alt_edit_div">
                        <label>车辆号码：</label>
                        <input type="text"  id="vehicle_vehicleno">
                    </div>
                    <div class="alt_edit_div">
                        <label>车辆类型：</label>
                        <input type="text"  id="vehicle_vehicletype">
                    </div>
                    <div class="alt_edit_div">
                        <label>运输单位：</label>
                        <input type="text"  id="vehicle_transportunit">
                    </div>
                    <div class="alt_edit_div">
                        <label>最大载重： </label>
                        <input type="text"  id="vehicle_maxweight">
                    </div>
                    <div class="alt_edit_div">
                        <label>皮重：</label>
                        <input type="text"  id="vehicle_tareweight">
                    </div>
                    <div class="alt_edit_div">
                        <label>车主： </label>
                        <input type="text"  id="vehicle_owner">
                    </div>
                    <div class="alt_edit_div">
                        <label>电话：</label>
                        <input type="text"  id="vehicle_telephone">
                    </div>
                    <div class="alt_edit_div">
                        <label>地址： </label>
                        <input type="text"  id="vehicle_address">
                    </div>
                    <div class="alt_edit_div">
                        <label>所属组织：</label>
                        <input type="text"  id="vehicle_org">
                    </div>
                    <div class="alt_edit_div">
                        <label>有效性： </label>
                        <input type="checkbox"  id="vehicle_isvalid">
                    </div>
                    <div class="alt_edit_textarea" >
                        <label>备注： </label>
                        <textarea class="form-control" rows="1"  id="vehicle_remarks"></textarea>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="vehicle_sure">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="vehicle_cancel">取消</button>
            </div>
        </div>
    </div>
</div>
<!--车号新增end-->
<!--司机新增begin-->
<div class="modal fade" id="driver" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <div class="alt_head">
                    <h5>司机管理--新增</h5>
                </div>
            </div>
            <div class="modal-body">
                <div class="alt_caradd">
                    <div class="alt_edit_div">
                        <label>司机编号：</label>
                        <input type="text" value="111" readonly="true" id="driver_code">
                    </div>
                    <div class="alt_edit_div">
                        <label>司机内码：</label>
                        <input type="text" value="000" id="driver_internalcode">
                    </div>
                    <div class="alt_edit_div">
                        <label class="colorred"> *司机名称：</label>
                        <input type="text" id="driver_name">
                    </div>
                    <div class="alt_edit_div">
                        <label>司机简称：</label>
                        <input type="text" id="driver_abbrname">
                    </div>
                    <div class="alt_edit_div">
                        <label>地址：</label>
                        <input type="text" id="driver_address">
                    </div>
                    <div class="alt_edit_div">
                        <label>所属组织：</label>
                        <input type="text" id="driver_org">
                    </div>
                    <div class="alt_edit_div">
                        <label class="colorred"> *电话：</label>
                        <input type="text" id="driver_telephone">
                    </div>

                    <div class="alt_edit_div">
                        <label class="colorred"> *身份证号：</label>
                        <input type="text" id="driver_identityno">
                    </div>
                    <div class="alt_edit_div">
                        <label>有效性： </label>
                        <input type="checkbox" id="driver_isvalid">
                    </div>

                    <div class="alt_edit_textarea">
                        <label>备注： </label>
                        <textarea class="form-control" rows="1" id="driver_remarks"></textarea>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="driver_sure">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="driver_cancel">取消</button>
            </div>
        </div>
    </div>
</div>
<!--司机新增end-->

<script type="text/javascript">
    $( function() {
        var availableTags = [
            "上海欧瑞仪器设备有限公司",
            "上海对接口",
            "尾矿进口量为几个鸡",
            "我德歌功"
        ];
        $( "#gongying" ).autocomplete({
            source: availableTags
        });
    } );

</script>
<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
<script type="text/javascript" src="${staticBasePath}/js/cardReader.js"></script>
<script type="text/javascript"
		src="/javascript/businessManage/otherManage/otherRKArrive_add.js?20171213"></script>
</body>
</html>