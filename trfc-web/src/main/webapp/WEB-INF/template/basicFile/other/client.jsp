<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>index</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="/trfc-web/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/trfc-web/resources/css/base.css" rel="stylesheet">
    <link href="/trfc-web/resources/css/style.css" rel="stylesheet">
    <link href="/trfc-web/resources/css/pagination.css" rel="stylesheet">
    <!--这个日历控件js必须放头部-->
    <script language="javascript" type="text/javascript" src="/trfc-web/resources/js/My97DatePicker/WdatePicker.js"></script>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
<div class="it_admin">
<div class="left ">
    <div class="user">
        <a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle">
            <img src="/trfc-web/resources/images/tx.jpg" class="img-circle">
            <label>超级管理员</label>
            <i class="iconfont">&#xe602;</i>
        </a>
        <ul class="dropdown-menu">
            <li><a data-toggle="modal" data-target="#account"><i class="iconfont">&#xe60e;</i>个人资料</a></li>
            <li class="divider"></li>
            <li><a data-toggle="modal" data-target="#password"><i class="iconfont">&#xe60d;</i> 设置</a></li>
        </ul>
    </div>
    <div class="menu">
        <label>菜单</label>
        <i class="iconfont fr">&#xe61a;</i>
    </div>
    <div class="menu_collap">
        <ul class="typelist ">
        <a href="#ityewu" data-toggle="collapse" class="menu_collap_tit">
            <label>业务管理</label>
            <span><i class="iconfont">&#xe604;</i></span>
        </a>
        <div class="in" id="ityewu">
            <li>
                <a href="../cg/cg_index.html">
                    <i class="iconfont">&#xe617;</i>
                    <label>采购管理</label>
                </a>
            </li>
            <li>
                <a href="../sell/sell_apply.html">
                    <i class="iconfont">&#xe615;</i>
                    <label>销售管理</label>
                </a>
            </li>
            <li>
                <a>
                    <i class="iconfont">&#xe614;</i>
                    <label>其他业务</label>
                </a>
            </li>
            <li>
                <a>
                    <i class="iconfont">&#xe618;</i>
                    <label>质控管理</label>
                </a>
            </li>
        </div>
        <a href="#itdangan" data-toggle="collapse" class="menu_collap_tit">
            <label>基础档案</label>
            <span><i class="iconfont">&#xe604;</i></span>
        </a>
        <div class="in" id="itdangan">
            <li>
                <a href="../file_nc/client.html">
                    <i class="iconfont">&#xe617;</i>
                    <label>NC档案</label>
                </a>
            </li>
            <li>
                <a href="../file_jil/car.html">
                    <i class="iconfont">&#xe617;</i>
                    <label>计量档案</label>
                </a>
            </li>
            <li class="active">
                <a href="../file-other/car.html">
                    <i class="iconfont">&#xe617;</i>
                    <label>其他档案</label>
                </a>
            </li>
         </div>
        </ul>
    </div>

</div>
<div class="leftmini hide">
    <div class="user">
        <a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle">
            <img src="/trfc-web/resources/images/tx.jpg" class="img-circle">
        </a>
        <ul class="dropdown-menu">
            <li><a data-toggle="modal" data-target="#account"><i class="iconfont">&#xe60e;</i>个人资料</a></li>
            <li class="divider"></li>
            <li><a data-toggle="modal" data-target="#password"><i class="iconfont">&#xe60d;</i> 设置</a></li>
        </ul>
    </div>
    <div class="menu2">
        <i class="iconfont">&#xe635;</i>
    </div>
    <ul class="typelist">
        <li class="active" data-toggle="tooltip" data-placement="right" title="采购管理">
            <i class="iconfont">&#xe617;</i>
        </li>
        <li data-toggle="tooltip" data-placement="right" title="销售管理">
            <i class="iconfont">&#xe615;</i>
        </li>
        <li data-toggle="tooltip" data-placement="right" title=" 其他">
            <i class="iconfont">&#xe614;</i>
        </li>
        <li>
            <i class="iconfont">&#xe618;</i>
        </li>
        <li>
            <i class="iconfont">&#xe619;</i>
        </li>
        <li>
            <i class="iconfont">&#xe613;</i>
        </li>
        <li>
            <i class="iconfont">&#xe612;</i>
        </li>
        <li>
            <i class="iconfont">&#xe610;</i>
        </li>
        <li>
            <i class="iconfont">&#xe60f;</i>
        </li>
        <li>
            <i class="iconfont">&#xe611;</i>
        </li>
    </ul>
</div>
<div class="right">
    <div class="intel_tab">
        <!--tab切换标题-->
        <ul class="intel_menu">
            <li>其他供应商</li>
            <li class="select">其他客户</li>
            <li>其他物料</li>
            <li>其他车辆</li>
            <li>其他司机</li>
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
                        <div class="intel_solo">
                            <label>查询条件：</label>
                            <select class="form-control" id="customer_query">
                                <option value='name'>名称</option>
                                <option value='innercode'>内码</option>
                            </select>
                        </div>
                        <div class="intel_solo">
                            <label>关键字：</label>
                            <input type="text"  id="customer_keyword">
                        </div>
                        <div class="intel_solo">
                            <label>所属组织：</label>
                            <input type="text" id="customer_orgname1">
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
                <div class="intel_operasolo" id="freshButton">
                    <i class="iconfont colorlv" >&#xe61b;</i>
                    <h5>刷新</h5>
                </div>
                <div class="intel_operasolo" id="showAddCustomer">
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
                    <tbody id="customer_list">
                  
                    </tbody>
                </table>
                <!--用户表格end-->
            </div>

            <!--分页效果开始-->
            <div class=" row fr">
                <div class="page_date">
                    <label>数据共：</label><i class="colorred" id="customers_total">100</i><label>条</label>
                </div>
                <div class="page_date">
                    <label>跳到第：</label>
                    <input type="text" id="jumpPageNo">
                    <label>页</label>
                    <button class="btn btn-default" id="jumpButton">确定</button>
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
</div>
<!--新增begin-->
<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <div class="alt_head">
                    <h5>其他客户编辑</h5>
                </div>
            </div>
            <div class="modal-body">
                <div class="alt_edit">
                    <div class="alt_edit_div">
                        <label>编号：</label>
                        <input type="text" value="121221" readonly="true" id="customer_code">
                    </div>
                    <div class="alt_edit_div">
                        <label>内码：</label>
                        <input type="text" value="121221" readonly="true" id="customer_innercode">
                    </div>
                    <div class="alt_edit_div">
                        <label>名称：</label>
                        <input type="text" id="customer_name">
                    </div>
                    <div class="alt_edit_div">
                        <label>简称：</label>
                        <input type="text" id="customer_info">
                    </div>
                    <div class="alt_edit_div">
                        <label>所属组织：</label>
                        <input type="text" id="customer_orgname">
                    </div>
                    <div class="alt_edit_div">
                        <label>有效性：</label>
                        <input type="checkbox" id="customer_isvalid"><span>有效</span>
                    </div>
                    <div class="alt_edit_textarea">
                        <label>备注： </label>
                        <textarea class="form-control" rows="1" id="customer_remark"></textarea>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="customer_add">确定</button>
                <button type="button" class="btn btn-default" id="customer_add_hide" data-dismiss="modal">取消</button>
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
                    <h5>修改客户信息</h5>
                </div>
            </div>
            <div class="modal-body">
                <div class="alt_edit">
                    <div class="alt_edit_div">
                        <label>编号：</label>
                        <input type="text" value="121221" readonly="true" id="customer_modify_code">
                    </div>
                    <div class="alt_edit_div">
                        <label>内码：</label>
                        <input type="text" value="121221" readonly="true" id="customer_modify_innercode">
                    </div>
                    <div class="alt_edit_div">
                        <label>名称：</label>
                        <input type="text" id="customer_modify_name">
                    </div>
                    <div class="alt_edit_div">
                        <label>简称：</label>
                        <input type="text" id="customer_modify_info">
                    </div>
                    <div class="alt_edit_div">
                        <label>所属组织：</label>
                        <input type="text" id="customer_modify_orgname">
                    </div>
                    <div class="alt_edit_div">
                        <label>有效性：</label>
                        <input type="checkbox" id="customer_modify_isvalid"><span>有效</span>
                    </div>
                    <div class="alt_edit_textarea">
                        <label>备注： </label>
                        <textarea class="form-control" rows="1" id="customer_modify_remark"></textarea>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="customer_modify">确定</button>
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
                    <img src="/trfc-web/resources/images/tishi.png"><label>注：删除操作不可恢复，您确定要继续么？</label>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="customer_delete">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--删除end-->
<script type="text/javascript" src="/trfc-web/resources/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="/trfc-web/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="/trfc-web/resources/js/basicFile/other/other-client.js"></script>
<script type="text/javascript" src="/trfc-web/resources/js/jquery.pagination.js"></script>

<script type="text/javascript">
    // 顶部tab切换菜单
    var $tab_li = $('.intel_menu li');
    $tab_li.click(function () {
        $(this).addClass('select').siblings().removeClass('select');
        var index = $tab_li.index(this);
        $('.intel_tabbox > .intel_tabcont').eq(index).show().siblings().hide();
    });
    // 二级折叠菜单的加减号显示隐藏
    var menu_a = $('.left .menu_collap a');
    var menu_i = $('.left .menu_collap i');
    menu_a.on("click", function () {
        if ($(this).hasClass("collapsed")) {
            $(this).find("span i").html("&#xe604;")
        }
        else {
            $(this).find("span i").html("&#xe61f;")
        }
    });
    //二级折叠菜单内容选中添加背景,未被选中的除去背景
    var menu_li = $('.left .menu_collap ul li');
    menu_li.on("click", function () {
        $(this).parent().parent("ul").find("li").removeClass("active");
        $(this).addClass("active");
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
    var menu_ctrl = $(".left .menu");
    var menu_ctrlmini = $(".leftmini .menu2");
    var leftall = $(".left");
    var leftmini = $(".leftmini");
    menu_ctrl.on("click", function () {
        $(leftall).css("display", "none");
        leftmini.css("display", "block");
        $(".right").css("margin-left", "100px");
    });
    menu_ctrlmini.on("click", function () {
        $(leftmini).css("display", "none");
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


</script>
</body>
</html>