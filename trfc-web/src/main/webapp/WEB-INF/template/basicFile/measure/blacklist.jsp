<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>车辆黑名单</title>
<script type="text/javascript" src="${staticBasePath }/js/menu_list.js"></script>
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
                            <label>车辆：</label>
                            <input type="text" id="a_vehicle" placeholder="请输入车牌号">
                        </div>
                        <div class="intel_solo">
                            <div class="intel_sbtn">
                                <button class="btn btnblue " id="searshBtn">搜索</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="intel_opera">
                <div class="intel_operasolo" id="refreshBtn">
                    <i class="iconfont colorlv">&#xe61b;</i>
                    <h5>刷新</h5>
                </div>
                <div class="intel_operasolo" id="addBtn">
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
                        <th>车号</th>
                        <th>创建时间</th>
                        <th>创建人</th>
                        <th>描述</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="blacklists">
                    <tr>
                        <td> CD201601010138</td>
                        <td class="colorred">审核中</td>
                        <td>客商APP</td>
                        <td>是</td>
                        <td>是</td>
                        <td>
            <span>
                <a data-toggle="modal" data-target="#dele"><i class="iconfont" data-toggle="tooltip"
                                                              data-placement="left" title="删除">&#xe63d;</i></a>
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
                <div class="page_btn" id="pagination"></div>
            </div>
            <!--分页效果结束-->
        </div>
        <!--采购申请单end-->
    </div>
</div>
</div>
<!--新增begin-->
<div class="modal fade" id="addView" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <div class="alt_head">
                    <h5>车辆黑名单-新增</h5>
                </div>
            </div>
            <div class="modal-body">
                <div class="alt_edit">
                    <div class="alt_edit_div">
                        <label>车号：</label>
                        <input type="text" id="vehicleno" placeholder="请选择车牌号">
                    </div>
                    <div class="alt_edit_div">
                        <label>操作人：</label>
                        <input type="text" value="管理员" readonly="true" id="creator">
                    </div>
                    <div class="alt_edit_div">
                        <label>操作日期：</label>
                        <input type="text" value="管理员" readonly="true" id="createtime">
                    </div>
                    <div class="alt_edit_textarea">
                        <label>描述： </label>
                        <textarea class="form-control" rows="1" id="remarks"></textarea>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="addCommit">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--新增end-->
<!--删除begin-->
<div class="modal fade" id="dele" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 400px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">提示</h4>
            </div>
            <div class="modal-body">
                <div class="alert_qf">
                    <img src="${staticBasePath}/images/tishi.png"><label>注：删除操作不可恢复，您确定要继续么？</label>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<!--删除end-->
	<script type="text/javascript"
		src="/javascript/basicFile/measure/blacklist.js"></script>
		
	
</body>
</html>