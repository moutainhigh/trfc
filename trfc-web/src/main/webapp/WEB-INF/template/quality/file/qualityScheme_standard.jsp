<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>质检方案-质检标准</title>
  <link href="${staticBasePath}/css/select2.css" rel="stylesheet">
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>

</head>
<body>
	<div class="it_admin">
		<jsp:include page="../../common/base/left_busi.jsp"></jsp:include>
		<div class="right">
			<jsp:include page="../../common/base/right_head_busi.jsp"></jsp:include>
			<div class="intel_tab">
    <!--tab切换标题-->
   <ul class="intel_menu">
        <li>质检方案-项目</li>
        <li class="select">质检方案-质检标准</li>
    </ul>
    <!--tab切换标题end-->
    <div class="top_opera">
        <a><i class="iconfont" data-toggle="tooltip" data-placement="left" title="首页">&#xe605;</i></a>
        <a><i class="iconfont" data-toggle="tooltip" data-placement="left" title="控制面板">&#xe606;</i></a>
        <a><i class="iconfont" data-toggle="tooltip" data-placement="left" title="退出">&#xe607;</i></a>
    </div>
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
        <div class="intel_sline">
            <h5>质量标准方案 - 郑州向鼎贸易有限公司-脱硫石膏（LS6）</h5>
        </div>
    </div>
</div>
<div class="intel_opera">
    <div class="intel_operasolo">
        <i class="iconfont colorlv">&#xe61b;</i>
        <h5>刷新</h5>
    </div>
    <div class="intel_operasolo">
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
            <th>行号</th>
            <th>方案名称</th>
            <th>物料</th>
            <th>质检标号</th>
            <th>质检项目</th>
            <th>单位</th>
            <th>比较符</th>
            <th>标准值</th>
            <th>比较符</th>
            <th>下限</th>
            <th>比较符</th>
            <th>上限</th>
            <th>基准值</th>
            <th>浮动值</th>
            <th>扣价</th>
            <th>扣吨</th>
            <th>有效</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td> 1</td>
            <td>审核中</td>
            <td>客商APP</td>
            <td>未入厂</td>
            <td>%</td>
            <td></td>
            <td>0</td>
            <td>1</td>
            <td><</td>
            <td></td>
            <td>0</td>
            <td>1</td>
            <td><</td>
            <td>1</td>
            <td>1</td>
            <td>1</td>
            <td><input type="checkbox" checked="true" disabled="true"></td>
            <td>
                <span >
                <a data-toggle="modal" data-target="#edit"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>
                </span>
                <span >
                    <a data-toggle="modal" data-target="#dele"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>
                </span>
                <span >
                    <a data-toggle="modal" data-target="#dele"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="清除">&#xe636;</i></a>
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
        <input type="text">
        <label>页</label>
        <button class="btn btn-default">确定</button>
    </div>
    <div class="page_btn">
        <ul class="pagination">
            <li><a href="#">&laquo;上一页</a></li>
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">...</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">下一页&raquo;</a></li>
        </ul>
    </div>
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
                    <h5>质量标准-新增</h5>
                </div>
            </div>
            <div class="modal-body">
                <div class="alt_edit">
                    <div class="alt_onerow_div">
                        <label>质检方案：</label>
                        <input type="text"  value="11">
                    </div>
                    <div class="alt_onerow_div">
                        <label>物料：</label>
                        <input type="text">
                    </div>
                    <div class="alt_onerow_div">
                        <label>有效性：</label>
                        <input type="checkbox"><span>有效</span>
                    </div>
                </div>
                <div class="zj_biaozhun_table">
                    <div class="zj_biaozhun_table2">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>行号</th>
                            <th>方案名称</th>
                            <th>物料</th>
                            <th>质检标号</th>
                            <th>质检项目</th>
                            <th>单位</th>
                            <th>比较符</th>
                            <th>标准值</th>
                            <th>比较符</th>
                            <th>下限</th>
                            <th>比较符</th>
                            <th>上限</th>
                            <th>基准值</th>
                            <th>浮动值</th>
                            <th>扣价</th>
                            <th>扣吨</th>
                            <th>有效</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td> 1</td>
                            <td>审核中</td>
                            <td>客商APP</td>
                            <td>未入厂</td>
                            <td>%</td>
                            <td></td>
                            <td>0</td>
                            <td>1</td>
                            <td><</td>
                            <td></td>
                            <td>0</td>
                            <td>1</td>
                            <td><</td>
                            <td>1</td>
                            <td>1</td>
                            <td>1</td>
                            <td><input type="checkbox" checked="true" disabled="true"></td>
                            <td>
                <span >
                <a data-toggle="modal" data-target="#edit"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>
                </span>
                <span >
                    <a data-toggle="modal" data-target="#dele"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>
                </span>
                <span >
                    <a data-toggle="modal" data-target="#dele"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="清除">&#xe636;</i></a>
                </span>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                        </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">保存</button>
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
                    <h5>质检方案项目-编辑</h5>
                </div>
            </div>
            <div class="modal-body">
                <div class="alt_edit">
                    <div class="alt_edit_div">
                        <label >方案名称：</label>
                        <input type="text" readonly="true" value="郑州向鼎贸易有限公司-脱硫石膏（LS6）">
                    </div>
                    <div class="alt_edit_div">
                        <label>物料：</label>
                        <input type="text" readonly="true" value="郑州向鼎贸易有限公司-脱硫石膏（LS6）">
                    </div>
                    <div class="alt_edit_div">
                        <label>质检项目：</label>
                        <input type="text" readonly="true" value="郑州向鼎贸易有限公司-脱硫石膏（LS6）">
                    </div>
                    <div class="alt_edit_div">
                        <label>单位：</label>
                        <input type="text">
                    </div>
                    <div class="alt_edit_div">
                        <label>比较符：</label>
                        <input type="text">
                    </div>
                    <div class="alt_edit_div">
                        <label>标准值：</label>
                        <input type="text">
                    </div>
                    <div class="alt_edit_div">
                        <label>比较符：</label>
                        <input type="text">
                    </div>
                    <div class="alt_edit_div">
                        <label>下限：</label>
                        <input type="text">
                    </div>
                    <div class="alt_edit_div">
                        <label>比较符：</label>
                        <input type="text">
                    </div>
                    <div class="alt_edit_div">
                        <label>上限：</label>
                        <input type="text">
                    </div>
                    <div class="alt_edit_div">
                        <label>基准值：</label>
                        <input type="text">
                    </div>
                    <div class="alt_edit_div">
                        <label>浮动值：</label>
                        <input type="text">
                    </div>
                    <div class="alt_edit_div">
                        <label>扣价：</label>
                        <input type="text">
                    </div>
                    <div class="alt_edit_div">
                    <label>扣吨：</label>
                    <input type="text">
                    </div>
                    <div class="alt_edit_div">
                        <label>有效性：</label>
                        <input type="checkbox"><span>有效</span>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--编辑end-->
<!-- 引用公共footer部分 -->
		<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
		<script type="text/javascript" src="/resources/js/select2.js"></script>
		<script type="text/javascript"
			src="/javascript/quality/file/qualityScheme_standard.js"></script>
		</script>
</body>
</html>