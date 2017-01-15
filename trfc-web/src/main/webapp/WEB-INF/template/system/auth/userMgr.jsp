<%@ page language="java"  pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无人值守-系统管理-用户管理</title>
	<!-- 引用公共header部分 -->
	<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>
</head>
<body>
<div class="it_admin">
<!-- 引用公共left部分 -->
<jsp:include page="../../common/base/left_busi.jsp"></jsp:include>
<div class="right">
	<!-- 引用公共right_head部分 -->
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
            <select class="form-control">
                <option>用户编号</option>
                <option>登陆账号</option>
                <option>用户名称</option>
            </select>
        </div>

            <div class="intel_solo">
                <label>关键字：</label>
                <input type="text">
            </div>
            <div class="intel_solo">
                <label>所属组织：</label>
                <input type="text">
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
    <div class="intel_operasolo">
        <i class="iconfont colorlv">&#xe61b;</i>
        <h5>刷新</h5>
    </div>
    <div class="intel_operasolo">
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
            <th>用户编号</th>
            <th>登录账号</th>
            <th>用户名称</th>
            <th>所属组织</th>
            <th>有效</th>
            <th>来源</th>
            <th>登陆次数</th>
            <th>最后登录时间</th>
            <th>说明</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td> 1</td>
            <td>000</td>
            <td>lkkjk</td>
            <td>未入厂</td>
            <td>粉煤灰1</td>
            <td><input type="checkbox" disabled></td>
            <td> APP</td>
            <td>22</td>
            <td>2016-10-12</td>
            <td></td>
            <td><span >
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
    <div class="row">
        <div class="col-md-12">
            <div class="page_middle">
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
<!--查看详情begin-->
<div class="modal fade" id="caigoubill" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 900px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <div class="alt_head">
                    <h5>用户信息详情</h5>
                </div>
            </div>
            <div class="modal-body">
                <div class="">
                    <div class="sys_user">
                        <h4> 河南杰仁环保工程有限公司(河南杰仁环保工程有限公司) </h4>
                    </div>
                    <div class="cg_tabtit">
                        <ul>
                            <li class="select">基本信息</li>
                            <li>拥有角色</li>
                            <li>拥有权限</li>
                        </ul>
                    </div>
                    <div class="cg_tabbox">
                        <!--tab切换的内容-->
                        <div class="cg_tabcont">
                            <div class="sys_userdt">
                                <ul>
                                    <li>
                                    <label>编号：</label><span>河南杰仁环保工程有限公司 </span>
                                    </li>
                                    <li>
                                        <label>用户名称：</label><span>河南杰仁环保工程有限公司 </span>
                                    </li>
                                </ul>

                            </div>
                        </div>
                        <div class="cg_tabcont hide">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th> 采购组织</th>
                                    <th>物料</th>
                                    <th>质检方案</th>
                                    <th>数量</th>
                                    <th>备注</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td> 卫辉市天瑞水泥有限公司</td>
                                    <td>粉煤炭</td>
                                    <td>方案2</td>
                                    <td>1000</td>
                                    <td>豫GA1783</td>
                                </tr>
                                <tr>
                                    <td>合计</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="cg_tabcont hide">
                            1
                        </div>
                        <!--tab切换的内容end-->
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--查看详情end-->
<!--新增begin-->
<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <div class="alt_head">
                    <h5>用户信息-新增</h5>
                </div>
            </div>
            <div class="modal-body">
                <div class="alt_edit">
                    <div class="alt_edit_div">
                        <label>用户编号：</label>
                        <input type="text">
                    </div>
                    <div class="alt_edit_div">
                        <label>登录账户：</label>
                        <input type="text">
                    </div>
                    <div class="alt_edit_div">
                        <label>用户名称：</label>
                        <input type="text">
                    </div>
                    <div class="alt_edit_div">
                        <label>登录密码：</label>
                        <input type="password">
                    </div>
                    <div class="alt_edit_div">
                        <label>所属组织：</label>
                        <input type="text">
                    </div>
                    <div class="alt_edit_div">
                        <label>说明：</label>
                        <input type="text">
                    </div>
                    <div class="alt_edit_div">
                        <label>选项：</label>
                        <input type="checkbox"><span>有效</span>
                        <em class="colorred">注：无效用户不能登录。</em>
                    </div>

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
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
                    <h5>用户信息-新增</h5>
                </div>
            </div>
            <div class="modal-body">
                <div class="alt_edit">
                    <div class="alt_edit_div">
                        <label>用户编号：</label>
                        <input type="text" value=" 000" readonly="true">
                    </div>
                    <div class="alt_edit_div">
                        <label>登录账户：</label>
                        <input type="text" value=" 000" readonly="true">
                    </div>
                    <div class="alt_edit_div">
                        <label>用户名称：</label>
                        <input type="text" value=" 000" readonly="true">
                    </div>
                    <div class="alt_edit_div">
                        <label>登录密码：</label>
                        <input type="password" value=" 000" readonly="true">
                    </div>
                    <div class="alt_edit_div">
                        <label>所属组织：</label>
                        <input type="text">
                    </div>
                    <div class="alt_edit_div">
                        <label>说明：</label>
                        <input type="text">
                    </div>
                    <div class="alt_edit_div">
                        <label>选项：</label>
                        <input type="checkbox"><span>有效</span>
                        <em class="colorred">注：无效用户不能登录。</em>
                    </div>

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--编辑end-->
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
<!--删除end-->
</div>
</body>
</html>