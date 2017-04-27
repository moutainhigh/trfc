<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>index</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/base.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">
    <link href="../css/mycollapse.css" rel="stylesheet">
    <link href="../css/iconfont.css" rel="stylesheet">
    <!--这个日历控件js必须放头部-->
    <script language="javascript" type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
<div class="it_admin">
<div class="right">
<div class="intel_tab">
    <!--tab切换标题-->
    <ul class="intel_menu">
        <li>菜单管理</li>
        <li>角色管理</li>
        <li>角色权限</li>
        <li class="select">用户权限</li>
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
        <div class="juese_qx">
            <div class="juese_company">
                <h5>组织机构</h5>
                <div class="sys_collap">
                    <ul class="typelist ">
                        <a href="#jsqx_comp" data-toggle="collapse" class="menu_collap_tit">
                            <span><i class="iconfont">&#xe6c8;</i></span>
                            <label><i class="iconfont">&#xe60a;</i>天瑞集团</label>
                        </a>
                        <div class="in" id="jsqx_comp">
                            <li>
                                <a href="#">
                                    <i class="iconfont">&#xe60b;</i>
                                    <label>卫辉市天瑞水泥有限公司11</label>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <i class="iconfont">&#xe60b;</i>
                                    <label>卫辉市天瑞水泥有限公司11</label>
                                </a>
                            </li>
                            <li>
                                <a>
                                    <i class="iconfont">&#xe60b;</i>
                                    <label>卫辉市天瑞水泥有限公司11</label>
                                </a>
                            </li>
                            <li>
                                <a>
                                    <i class="iconfont">&#xe60b;</i>
                                    <label>质控管理</label>
                                </a>
                            </li>
                        </div>
                    </ul>
                </div>
            </div>
            <div class="juese_user">
                <h5>用户列表</h5>
                <div class="user_listsearch">
                    <label>关键字</label>
                    <input type="text" style="width:90px;">
                    <button class="btn btndartblue ">搜索</button>
                </div>

                <div class="sys_collap">
                    <ul class="typelist ">
                        <a href="#jsqx_juese" data-toggle="collapse" class="menu_collap_tit">
                            <span><i class="iconfont">&#xe6c8;</i></span>
                            <label><i class="iconfont">&#xe60a;</i>卫辉市天瑞水泥有限公</label>
                        </a>
                        <div class="in" id="jsqx_juese">
                            <li>
                                <a href="#">
                                    <i class="iconfont">&#xe620;</i>
                                    <label>卫辉市天瑞水</label>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <i class="iconfont">&#xe620;</i>
                                    <label> 门岗(018)</label>
                                </a>
                            </li>
                            <li>
                                <a>
                                    <i class="iconfont">&#xe620;</i>
                                    <label> 门岗(018)</label>
                                </a>
                            </li>
                            <li>
                                <a>
                                    <i class="iconfont">&#xe620;</i>
                                    <label>质控管理</label>
                                </a>
                            </li>
                        </div>
                    </ul>
                </div>
            </div>
            <div class="juese_opera">
                <div class="juese_title">
                    <h5>用户授权 - 张三（001）</h5>
                </div>
                <!--角色授权切换标题-->
                <div class="juese_usertab">
                    <ul>
                        <li class="select">模块权限</li>
                        <li>工作平台权限</li>
                    </ul>
                </div>
                <!--角色授权切换标题-->

                <!--tab切换的内容-->
                <div class="juese_tabbox">
                    <!--模块权限begin-->
                    <div class="juese_tabcont ">
                        <div class="intel_opera">
                            <div class="intel_operasolo">
                                <i class="iconfont colorlv">&#xe61b;</i>
                                <h5>刷新</h5>
                            </div>
                            <div class="intel_operasolo">
                                <a data-toggle="modal" data-target="#module_reset">
                                    <i class="iconfont coloradd" style="font-weight: bold">&#xe623;</i>
                                    <h5>重置</h5>
                                </a>
                            </div>
                            <div class="intel_operasolo">
                                <a data-toggle="modal" data-target="#module_sq">
                                    <i class="iconfont" style="font-weight: bold">&#xe668;</i>
                                    <h5>授权</h5>
                                </a>
                            </div>
                        </div>
                        <div class="intel_table">
                            <table id="juese_module" class="table table-bordered" data-options="">
                                <thead>
                                <tr>
                                    <th></th>
                                    <th style="width: 200px;">模块(菜单)名称</th>
                                    <th>模块编码</th>
                                    <th>模块分类</th>
                                    <th>排序</th>
                                    <th>说明</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr id="1">
                                    <td>1</td>
                                    <td style="width: 200px;"><input type="checkbox" checked>
                                        <span controller="true">天瑞集团智能物流业务平台</span></td>
                                    <td>01</td>
                                    <td></td>
                                    <td>1</td>
                                    <td></td>
                                </tr>
                                <tr id="2" pid="1">
                                    <td>2</td>
                                    <td style="width: 200px;"><input type="checkbox" checked><span controller="true">业务管理</span>
                                    </td>
                                    <td>0101</td>
                                    <td>天瑞集团智能物流业务平台</td>
                                    <td>3</td>
                                    <td></td>
                                </tr>
                                <tr id="3" pid="2">
                                    <td>3</td>
                                    <td style="width: 200px;"><input type="checkbox" checked><span controller="true">采购管理</span>
                                    </td>
                                    <td>010101</td>
                                    <td>业务管理</td>
                                    <td>3</td>
                                    <td></td>
                                </tr>
                                <tr id="4" pid="3">
                                    <td>4</td>
                                    <td style="width: 200px;"><input type="checkbox" checked><span controller="true">采购申请单</span>
                                    </td>
                                    <td>01010101</td>
                                    <td>3</td>
                                    <td>3</td>
                                    <td></td>
                                </tr>
                                <tr id="5" pid="3">
                                    <td>5</td>
                                    <td style="width: 200px;"><input type="checkbox"><span
                                            controller="true">到货通知单</span></td>
                                    <td>01010102</td>
                                    <td>2</td>
                                    <td>3</td>
                                    <td></td>
                                </tr>
                                <tr id="6" pid="2">
                                    <td>6</td>
                                    <td style="width: 200px;"><input type="checkbox"><span controller="true">销售管理</span>
                                    </td>
                                    <td>01010101</td>
                                    <td>1</td>
                                    <td>3</td>
                                </tr>
                                <tr id="7" pid="1">
                                    <td>7</td>
                                    <td style="width: 200px;"><input type="checkbox"><span controller="true">基础档案</span>
                                    </td>
                                    <td>0102</td>
                                    <td>天瑞集团智能物流业务平台</td>
                                    <td>8</td>
                                    <td></td>
                                </tr>
                                <tr id="8" pid="7">
                                    <td>8</td>
                                    <td style="width: 200px;"><input type="checkbox"><span controller="true">NC档案</span>
                                    </td>
                                    <td>010201</td>
                                    <td>基础档案</td>
                                    <td>8</td>
                                    <td></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <!--分页效果开始-->
                                <div class="page_middle">
                                    <div class="page_date">
                                        <label>数据共：</label><i class="colorred">15500</i><label>条</label>
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
                                            <li><a href="#">441</a></li>
                                            <li><a href="#">255</a></li>
                                            <li><a href="#">553</a></li>
                                            <li><a href="#">...</a></li>
                                            <li><a href="#">4444</a></li>
                                            <li><a href="#">53333</a></li>
                                            <li><a href="#">下一页&raquo;</a></li>
                                        </ul>
                                    </div>
                                </div>

                        <!--分页效果结束-->
                    </div>
                    <!--模块权限end-->
                    <div class="juese_tabcont hide">
                        <div class="intel_opera">
                            <div class="intel_operasolo">
                                <i class="iconfont colorlv">&#xe61b;</i>
                                <h5>刷新</h5>
                            </div>
                            <div class="intel_operasolo">
                                <a data-toggle="modal" data-target="#module_reset">
                                    <i class="iconfont coloradd" style="font-weight: bold">&#xe623;</i>
                                    <h5>重置</h5>
                                </a>
                            </div>
                            <div class="intel_operasolo">
                                <a data-toggle="modal" data-target="#module_sq">
                                    <i class="iconfont" style="font-weight: bold">&#xe668;</i>
                                    <h5>授权</h5>
                                </a>
                            </div>
                        </div>
                        <div class="juese_work">
                            <div class="juese_sysplat">
                                <div class="juese_plattit">
                                    <h5>想拥有工作平台数据权限（请点击勾选）</h5>
                                    <span class="qx_seltall "><input type="checkbox" >全选</span>
                                    <span class="qx_cancelall "><input type="checkbox" >反选</span>
                                </div>
                                <div class="juese_altuser_list">
                                    <ul>
                                        <li class="select"><i class="iconfont">&#xe620;</i>三丰</li>
                                        <li><i class="iconfont">&#xe620;</i>张三丰</li>
                                        <li><i class="iconfont">&#xe620;</i>00000</li>
                                        <li><i class="iconfont">&#xe620;</i>张三丰</li>
                                        <li><i class="iconfont">&#xe620;</i>张三丰</li>
                                        <li><i class="iconfont">&#xe620;</i>张三丰</li>
                                        <li><i class="iconfont">&#xe620;</i>张三丰</li>
                                        <li><i class="iconfont">&#xe620;</i>张三丰</li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <!--tab切换的内容end-->
            </div>
        </div>

    </div>
</div>
</div>
</div>
<!--角色用户新增begin-->
<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 760px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <div class="alt_head">
                    <h5>添加用户到角色</h5>
                </div>
            </div>
            <div class="modal-body">
                <div class="juese_qxsearch">
                    <div class="js_solo">
                        <label>关键字：</label>
                        <input type="text">
                    </div>
                    <div class="js_solo">
                        <button class="btn btnblue ">搜索</button>
                    </div>
                </div>
                <div class="juese_altuser">
                    <label>成员</label>
                    <span class="fr"><input type="checkbox">全选/反选</span>
                </div>
                <div class="juese_altuser_list">
                    <ul>
                        <li class="select"><i class="iconfont">&#xe620;</i>三丰</li>
                        <li><i class="iconfont">&#xe620;</i>张三丰</li>
                        <li><i class="iconfont">&#xe620;</i>00000</li>
                        <li><i class="iconfont">&#xe620;</i>张三丰</li>
                        <li><i class="iconfont">&#xe620;</i>张三丰</li>
                        <li><i class="iconfont">&#xe620;</i>张三丰</li>
                        <li><i class="iconfont">&#xe620;</i>张三丰</li>
                        <li><i class="iconfont">&#xe620;</i>张三丰</li>
                    </ul>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--角色用户新增end-->
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
                    <img src="../images/tishi.png"><label>注：删除操作不可恢复，您确定要继续么？</label>
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
<!--模块权限重置begin-->
<div class="modal fade" id="module_reset" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">系统提示</h4>
            </div>
            <div class="modal-body">
                <div class="alert_qf">
                    <img src="../images/tishi.png"><label>注：重置操作将会清空用户的所有模块权限，是否继续？</label>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--模块权限重置end-->
<!--模块权限授权begin-->
<div class="modal fade" id="module_sq" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">系统提示</h4>
            </div>
            <div class="modal-body">
                <div class="alert_qf">
                    <i class="iconfont"> &#xe638;</i><label>授权成功</label>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--模块权限授权end-->

<script type="text/javascript" src="../js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="../js/bootstrap.js"></script>
<script type="text/javascript" src="../js/treeTable.js"></script>
<script type="text/javascript" src="../js/bootstrap-treeview.js"></script>
<script type="text/javascript" src="../js/myself.js"></script>
<script type="text/javascript">
    // 角色添加用户弹出框选中取消
//    var juese_altuser_list = $('.juese_altuser_list ul li');
//    juese_altuser_list.on("click", function () {
//        $(this).toggleClass("select")
//    });
    // 模块权限表格每行选中背景变白
    var tabletr = $('#juese_module tr');
    tabletr.on("click", function () {
        $(this).addClass("select").siblings().removeClass("select");
    });

    //菜单表格中的分级别表格js调用
    var option = {
        theme: 'vsStyle',
        expandLevel: 10,
        column: 1,
        beforeExpand: function ($treeTable, id) {
            //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
            if ($('.' + id, $treeTable).length) {
                return;
            }
        },
        onSelect: function ($treeTable, id) {
            window.console && console.log('onSelect:' + id);
        }
    };
    $('#juese_module').treeTable(option);

    // 顶部tab切换菜单
    var $tab_li = $('.intel_menu li');
    $tab_li.click(function () {
        $(this).addClass('select').siblings().removeClass('select');
        var index = $tab_li.index(this);
        $('.intel_tabbox > .intel_tabcont').eq(index).show().siblings().hide();
    });
    // 角色权限的权限按钮tab切换菜单
    var js_li = $('.juese_tab ul li');
    js_li.click(function () {
        $(this).addClass('select').siblings().removeClass('select');
        var index = js_li.index(this);
        $('.juese_tabbox > .juese_tabcont').eq(index).show().siblings().hide();
    });
    // 用户权限的权限按钮tab切换菜单
    var js_userli = $('.juese_usertab ul li');
    js_userli.click(function () {
        $(this).addClass('select').siblings().removeClass('select');
        var index = js_userli.index(this);
        $('.juese_tabbox > .juese_tabcont').eq(index).show().siblings().hide();
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