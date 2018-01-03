<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>红外被挡审批</title>
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
                                    <label>开始时间：</label> <input id="starttime" type="text" readonly
                                        onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'endtime\')}'})"
                                        class="Wdate" style="width: 160px" placeholder="请选择开始时间" />
                                    <label>结束时间：</label><input id="endtime" type="text" readonly
                                        onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'starttime\')}'})"
                                        class="Wdate" style="width: 160px" placeholder="请选择结束时间" />
                                </div>
                                <div class="intel_solo">
                                    <label>通知单号：</label> <input id="pn_code" type="text"
                                        placeholder="请输入通知单号">
                                </div>
                                <div class="intel_solo">
                                    <div class="intel_sbtn">
                                        <button id="search" class="btn btnblue">搜索</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="intel_opera">
                        <div id="refresh" class="intel_operasolo">
                            <i class="iconfont colorlv">&#xe61b;</i> <span>刷新</span>
                        </div>
						<div id="audit" class="intel_operasolo">
							<i class="iconfont colorlv">&#xe630;</i> <span>审批</span>
						</div>
					</div>
                    <div class="intel_table">
                        <!--用户表格begin-->
                        <table class="table table-hover maintable">
                            <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>通知单号</th>
                                    <th>流水号</th>
                                    <th>审批状态</th>
                                    <th>审批时间</th>
                                    <th>创建时间</th>
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
                            <label>数据共：</label><i id="total" class="colorred">0</i><label>条</label>
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
        </div>
    </div>
    <!--查看详情end-->
    <!-- 引用公共footer部分 -->
    <jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
    <script type="text/javascript"
        src="/javascript/businessManage/examine/infraredBlock.js?2018010301"></script>
</body>
</html>