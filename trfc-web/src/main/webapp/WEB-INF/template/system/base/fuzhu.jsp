<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>index</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${basePath }/css/bootstrap.css" rel="stylesheet">
    <link href="${basePath }/css/base.css" rel="stylesheet">
    <link href="${basePath }/css/style.css" rel="stylesheet">
    <link href="${basePath }/css/simpleTree.css" rel="stylesheet">
    <link href="${basePath }/css/iconfont.css" rel="stylesheet">
    <link href="${basePath }/css/mycollapse.css" rel="stylesheet">
    <!--这个日历控件js必须放头部-->
    <script language="javascript" type="text/javascript" src="${basePath }/js/My97DatePicker/WdatePicker.js"></script>
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
            <img src="${basePath }/images/tx.jpg" class="img-circle">
            <label>超级管理员</label>
        </a>
    </div>
    <div class="menu">
        <label>菜单</label>
        <i class="iconfont fr">&#xe61a;</i>
    </div>
    <div class="menu_collap">
        <ul class="typelist ">
            <a href="#ityewu" data-toggle="collapse" class="menu_collap_tit">
                <label>业务管理</label>
                <span><i class="iconfont">&#xe61f;</i></span>
            </a>

            <div class="collapse" id="ityewu">
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
                <span><i class="iconfont">&#xe61f;</i></span>
            </a>

            <div class="collapse" id="itdangan">
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
                <li>
                    <a href="../file-other/car.html">
                        <i class="iconfont">&#xe617;</i>
                        <label>其他档案</label>
                    </a>
                </li>
            </div>
            <a href="#sys" data-toggle="collapse" class="menu_collap_tit">
                <label>系统设置</label>
                <span><i class="iconfont">&#xe604;</i></span>
            </a>

            <div class="in" id="sys">
                <li>
                    <a href="#">
                        <i class="iconfont">&#xe617;</i>
                        <label>系统权限</label>
                    </a>
                </li>
                <li class="active">
                    <a href="../system-yewu/bianhao.html">
                        <i class="iconfont">&#xe617;</i>
                        <label>系统业务</label>
                    </a>
                </li>
            </div>
        </ul>
    </div>

</div>
   <div class="right">
       <div class="intel_tab">
            <!--tab切换标题-->
            <ul class="intel_menu">
                <li><a href="bianhao.html">自定义编号</a></li>
                <li class="select"><a href="bianhao.html">辅助资料</a></li>
            </ul>
            <!--tab切换标题end-->
            <div class="top_opera">
                <a><i class="iconfont" data-toggle="tooltip" data-placement="left" title="首页">&#xe605;</i></a>
                <a><i class="iconfont" data-toggle="tooltip" data-placement="left" title="控制面板">&#xe606;</i></a>
                <a><i class="iconfont" data-toggle="tooltip" data-placement="left" title="退出">&#xe607;</i></a>
            </div>
       </div>
       <div class="intel_tabbox">
        <div class="intel_tabcont">
            <div class="fuzhu_list width_fuzhu">
                <h5>辅助资料类别</h5>
                <div class="sys_fuzhu">
                    <div class="sys_fuzhusolo" id="showAddDict">
                        <a data-toggle="modal" data-target="#adddict">
                            <i class="iconfont coloradd">&#xe627;</i>
                            <label>新增</label>
                        </a>
                    </div>
                    <div class="sys_fuzhusolo" id="showEditDict">
                        <a data-toggle="modal" data-target="#editdict">
                            <i class="iconfont coloradd">&#xe627;</i>
                            <label>编辑</label>
                        </a>
                    </div>
                    <div class="sys_fuzhusolo" id="showDeleteDict">
                        <a data-toggle="modal" data-target="#deledict">
                            <i class="iconfont colorred">&#xe627;</i>
                            <label>删除</label>
                        </a>
                    </div>
                </div>
                <div class="sys_collap">
                    <ul class="typelist " id="dicts">
                        <a href="#sys_data" data-toggle="collapse" class="menu_collap_tit">
                            <span><i class="iconfont">&#xe6c8;</i></span>
                            <label><i class="iconfont">&#xe60a;</i>系统资料</label>
                        </a>
                        <div class="in" id="sys_data">
                            <li>
                                <a href="#">
                                    <i class="iconfont">&#xe60b;</i>
                                    <label>自定义表单类别</label>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <i class="iconfont">&#xe60b;</i>
                                    <label>民族</label>
                                </a>
                            </li>
                            <li>
                                <a>
                                    <i class="iconfont">&#xe60b;</i>
                                    <label>国籍</label>
                                </a>
                            </li>
                            <li>
                                <a>
                                    <i class="iconfont">&#xe60b;</i>
                                    <label>角色分类</label>
                                </a>
                            </li>
                        </div>
                        <a href="#sys_yewu" data-toggle="collapse" class="menu_collap_tit">
                            <span><i class="iconfont">&#xe6c8;</i></span>
                            <label><i class="iconfont">&#xe60a;</i>业务资料</label>
                        </a>
                        <div class="in" id="sys_yewu">
                            <li>
                                <a href="#">
                                    <i class="iconfont">&#xe60b;</i>
                                    <label>煤层</label>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <i class="iconfont">&#xe60b;</i>
                                    <label>等级</label>
                                </a>
                            </li>
                            <li>
                                <a>
                                    <i class="iconfont">&#xe60b;</i>
                                    <label>发票类型</label>
                                </a>
                            </li>
                        </div>
                    </ul>
                </div>
             </div>
             <div class="fuzhu_dtail">
                 <div class="fuzhu_dtile">
                     <h5 id="fuzhu_dtile_name">辅助资料明细 - 自定义表单类别 </h5>
                 </div>
                <div class="intel_opera">
                    <div class="intel_operasolo" id="refresh">
                        <i class="iconfont colorlv">&#xe61b;</i>
                        <h5>刷新</h5>
                    </div>
                    <div class="intel_operasolo" id="showAddItem">
                        <a data-toggle="modal" data-target="#additem">
                            <i class="iconfont coloradd">&#xe627;</i>
                            <h5>新增</h5>
                        </a>
                    </div>
                </div>
                 <div class="intel_table">
                     <!--角色成员表格begin-->
                     <table class="table table-hover">
                         <thead>
                         <tr>
                             <th> </th>
                             <th>代码</th>
                             <th>名称</th>
                             <th>有效</th>
                             <th>允许编辑</th>
                             <th>允许删除</th>
                             <th>排序</th>
                             <th>描述</th>
                             <th>操作</th>
                         </tr>
                         </thead>
                         <tbody id="items">
                         <tr>
                             <td>1</td>
                             <td>客商APP</td>
                             <td> 201601010138</td>
                             <td><input type="checkbox" disabled checked="true"></td>
                             <td><input type="checkbox" disabled checked="true"></td>
                             <td><input type="checkbox" disabled></td>
                             <td>6</td>
                             <td>客商APP</td>
                             <td>
                                <span class="update_item">
                <a data-toggle="modal" data-target="#edititem"><i class="iconfont" data-toggle="tooltip"
                                                              data-placement="left" title="编辑">&#xe600;</i></a></span>
                                           <span class="dele_item">
                <a data-toggle="modal" data-target="#deleitem"><i class="iconfont" data-toggle="tooltip"
                                                              data-placement="left" title="删除">&#xe63d;</i></a>
            </span>
                             </td>
                         </tr>
                         <tr>
                             <td>2</td>
                             <td>客商APP</td>
                             <td> 201601010138</td>
                             <td><input type="checkbox" disabled checked="true"></td>
                             <td><input type="checkbox" disabled checked="true"></td>
                             <td><input type="checkbox" disabled></td>
                             <td>6</td>
                             <td>客商APP</td>
                             <td>
                                <span>
                <a data-toggle="modal" data-target="#edititem"><i class="iconfont" data-toggle="tooltip"
                                                              data-placement="left" title="编辑">&#xe600;</i></a></span>
                                           <span>
                <a data-toggle="modal" data-target="#deleitem"><i class="iconfont" data-toggle="tooltip"
                                                              data-placement="left" title="删除">&#xe63d;</i></a>
            </span>
                             </td>
                         </tr>
                         </tbody>
                     </table>
                     <!--角色成员表格end-->
                 </div>
             </div>
        </div>
    </div>
</div>
<!--新增begin-->
<div class="modal fade" id="adddict" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document"  style="width: 750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <div class="alt_head">
                    <h5>辅助资料类别-添加</h5>
                </div>
            </div>
            <div class="modal-body">
                <div class="alt_edit">
                    <div class="alt_edit_div">
                        <label>编号：</label>
                        <input type="text" value="121221" id="dict_code">
                    </div>
                    <div class="alt_edit_div">
                        <label>名称：</label>
                        <input type="text" id="dict_name">
                    </div>
                    <div class="alt_edit_div">
                        <label>分类：</label>
                        <select style="width: 220px;height: 30px" id="dict_type">
                        	<option value="1">系统资料</option>
                        	<option value="2">业务资料</option>
                        </select>
                    </div>
                    <div class="alt_edit_div" style="width: 100%">
                        <label>选项：</label>
                        <input type="checkbox" checked="checked" disabled="disabled"><span>有效</span>
                        <input type="checkbox" checked="checked" disabled="disabled"><span>允许编辑</span>
                        <input type="checkbox" checked="checked" disabled="disabled"><span>允许删除</span>
                    </div>
                    <div class="alt_edit_textarea">
                        <label>描述： </label>
                        <textarea class="form-control" rows="1" id="dict_info"></textarea>
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
<div class="modal fade" id="editdict" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document"  style="width: 750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <div class="alt_head">
                    <h5>辅助资料类别-编辑</h5>
                </div>
            </div>
            <div class="modal-body">
                <div class="alt_edit">
                    <div class="alt_edit_div">
                        <label>代码：</label>
                        <input type="text" value="121221" id="update_dict_code">
                    </div>
                    <div class="alt_edit_div">
                        <label>名称：</label>
                        <input type="text" id="update_dict_name">
                    </div>
                    <div class="alt_edit_div">
                        <label>分类：</label>
                        <select style="width: 220px;height: 30px" id="update_dict_type">
                        	<option value="1">系统资料</option>
                        	<option value="2">业务资料</option>
                        </select>
                    </div>
                    <div class="alt_edit_div" style="width: 100%">
                        <label>选项：</label>
                        <input type="checkbox" checked="checked"  disabled="disabled"><span>有效</span>
                        <input type="checkbox" checked="checked"  disabled="disabled"><span>允许编辑</span>
                        <input type="checkbox" checked="checked"  disabled="disabled"><span>允许删除</span>
                    </div>
                    <div class="alt_edit_textarea">
                        <label>描述： </label>
                        <textarea class="form-control" rows="1" id="update_dict_info"></textarea>
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
<div class="modal fade" id="deledict" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 400px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">提示</h4>
            </div>
            <div class="modal-body">
                <div class="alert_qf">
                   <label>注：删除操作不可恢复，您确定要继续么？</label>
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
<!--新增begin-->
<div class="modal fade" id="additem" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document"  style="width: 750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <div class="alt_head">
                    <h5>辅助资料明细-添加</h5>
                </div>
            </div>
            <div class="modal-body">
                <div class="alt_edit">
                    <div class="alt_edit_div">
                        <label>代码：</label>
                        <input type="text" value="121221" id="item_code">
                    </div>
                    <div class="alt_edit_div">
                        <label>名称：</label>
                        <input type="text" id="item_name">
                    </div>
                    <div class="alt_edit_div" style="width: 100%">
                        <label>选项：</label>
                        <input type="checkbox" id="item_addisvalid"><span>有效</span>
                        <input type="checkbox" checked="checked" disabled="disabled"><span>允许编辑</span>
                        <input type="checkbox" checked="checked" disabled="disabled"><span>允许删除</span>
                    </div>
                    <div class="alt_edit_textarea">
                        <label>描述： </label>
                        <textarea class="form-control" rows="1" id="item_info"></textarea>
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
<div class="modal fade" id="edititem" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document"  style="width: 750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <div class="alt_head">
                    <h5>辅助资料明细-编辑</h5>
                </div>
            </div>
            <div class="modal-body">
                <div class="alt_edit">
                    <div class="alt_edit_div">
                        <label>代码：</label>
                        <input type="text" value="121221" id="update_item_code">
                    </div>
                    <div class="alt_edit_div">
                        <label>名称：</label>
                        <input type="text" id="update_item_name">
                    </div>
                    <div class="alt_edit_div" style="width: 100%">
                        <label>选项：</label>
                        <input type="checkbox" id="update_item_isvalid"><span>有效</span>
                        <input type="checkbox" disabled="disabled" checked="checked"><span>允许编辑</span>
                        <input type="checkbox" disabled="disabled" checked="checked"><span>允许删除</span>
                    </div>
                    <div class="alt_edit_textarea">
                        <label>描述： </label>
                        <textarea class="form-control" rows="1" id="update_item_info"></textarea>
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
<div class="modal fade" id="deleitem" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 400px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">提示</h4>
            </div>
            <div class="modal-body">
                <div class="alert_qf">
                   <label>注：删除操作不可恢复，您确定要继续么？</label>
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
<script type="text/javascript" src="${basePath }/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${basePath }/js/bootstrap.js"></script>
<script type="text/javascript" src="${basePath }/js/treeTable.js"></script>
<script type="text/javascript" src="${basePath }/js/simpleTree.js" charset="utf-8"></script>
<script type="text/javascript" src="${basePath }/js/myself.js"></script>
<script type="text/javascript" src="${basePath }/js/layer/layer.js"></script>
<script type="text/javascript" src="${basePath }/js/system/base/fuzhu.js">

</script>	
</body>
</html>