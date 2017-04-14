<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>菜单管理</title>
<style type="text/css">
	.menu{
		padding: 15px 20px 15px 28px;
	}

</style>
<!-- 引用公共header部分 -->
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>
<script type="text/javascript" src="${staticBasePath }/js/jquery.easyui.min.js"></script>
<link href="${staticBasePath}/css/easyui.css" rel="stylesheet">
<link href="${staticBasePath}/css/icon.css" rel="stylesheet">
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
            <div class="intel_opera">
                <div class="intel_operasolo" id="refreshBtn">
                    <i class="iconfont colorlv">&#xe61b;</i>
                    <h5>刷新</h5>
                </div>
                <div class="intel_operasolo" id="initAdd">
                    <a data-toggle="modal" data-target="#add">
                        <i class="iconfont coloradd">&#xe627;</i>
                        <h5>新增</h5>
                    </a>
                </div>
            </div>
            <div class="intel_table">
                <!--用户表格begin-->
                <table id="table_menu" class="table table-bordered" data-options="">
                    <thead>
                    <tr>
                        <th style="width: 200px;">模块(菜单)名称</th>
                        <th>模块编码</th>
                        <th>上级模块</th>
                        <th>连接目标</th>
                        <th>Web连接地址</th>
                        <th>有效</th>
                        <th>排序</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="menus">
                    
                    <tr id="2" pid="1">
                        <td style="width: 200px;"><span controller="true">业务管理</span></td>
                        <td>0101</td>
                        <td>天瑞集团智能物流业务平台</td>
                        <td>click</td>
                        <td></td>
                        <td><input type="checkbox" checked disabled="disabled"></td>
                        <td>1</td>
                        <td>
                        <span><a data-toggle="modal" data-target="#edit">
                            <i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>
                        </span>
						<span> <a data-toggle="modal" data-target="#add"><i
												class="iconfont" data-toggle="tooltip" data-placement="left"
												title="复制">&#xe61c;</i></a>
						</span>
                       <span><a data-toggle="modal" data-target="#dele">
                           <i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>
                       </span>
                        </td>
                    </tr>
                    <tr id="3" pid="2">
                        <td>3</td>
                        <td style="width: 200px;"><span controller="true">采购管理</span></td>
                        <td>010101</td>
                        <td>业务管理</td>
                        <td>click</td>
                        <td></td>
                        <td><input type="checkbox" checked disabled="disabled"></td>
                        <td>1</td>
                        <td>
                        <span><a data-toggle="modal" data-target="#edit">
                            <i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>
                        </span>
						<span> <a data-toggle="modal" data-target="#add"><i
												class="iconfont" data-toggle="tooltip" data-placement="left"
												title="复制">&#xe61c;</i></a>
						</span>
                       <span><a data-toggle="modal" data-target="#dele">
                           <i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>
                       </span>
                        </td>
                    </tr>
                    <tr id="4" pid="3">
                        <td>4</td>
                        <td style="width: 200px;"><span controller="true">采购申请单</span></td>
                        <td>01010101</td>
                        <td>采购管理</td>
                        <td>Iframe</td>
                        <td>/ExampleModule/PCOrderNew/PCOrderList.aspx</td>
                        <td><input type="checkbox" checked disabled="disabled"></td>
                        <td>1</td>
                        <td>
                        <span><a data-toggle="modal" data-target="#edit">
                            <i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>
                        </span>
						<span> <a data-toggle="modal" data-target="#add"><i
												class="iconfont" data-toggle="tooltip" data-placement="left"
												title="复制">&#xe61c;</i></a>
						</span>
                       <span><a data-toggle="modal" data-target="#dele">
                           <i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>
                       </span>
                        </td>
                    </tr>
                    <tr id="5" pid="3">
                        <td>5</td>
                        <td style="width: 200px;"><span controller="true">到货通知单</span></td>
                        <td>01010102</td>
                        <td>采购管理</td>
                        <td>Iframe</td>
                        <td>/ExampleModule/PCOrderNew/PCOrderList.aspx</td>
                        <td><input type="checkbox" checked disabled="disabled"></td>
                        <td>1</td>
                        <td>
                        <span><a data-toggle="modal" data-target="#edit">
                            <i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>
                        </span>
						<span> <a data-toggle="modal" data-target="#add"><i
												class="iconfont" data-toggle="tooltip" data-placement="left"
												title="复制">&#xe61c;</i></a>
						</span>
                       <span><a data-toggle="modal" data-target="#dele">
                           <i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>
                       </span>
                        </td>
                    </tr>
                    <tr id="6" pid="2">
                        <td>6</td>
                        <td style="width: 200px;"><span controller="true">质控管理</span></td>
                        <td>010102</td>
                        <td>业务管理</td>
                        <td>Click</td>
                        <td></td>
                        <td><input type="checkbox" checked disabled="disabled"></td>
                        <td>1</td>
                        <td>
                        <span><a data-toggle="modal" data-target="#edit">
                            <i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>
                        </span>
						<span> <a data-toggle="modal" data-target="#add"><i
												class="iconfont" data-toggle="tooltip" data-placement="left"
												title="复制">&#xe61c;</i></a>
						</span>
                       <span><a data-toggle="modal" data-target="#dele">
                           <i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>
                       </span>
                        </td>
                    </tr>
					<tr id="7" pid="6">
                        <td>7</td>
                        <td style="width: 200px;"><span controller="true">采购采样管理</span></td>
                        <td>01010201</td>
                        <td>质控管理</td>
                        <td>Iframe</td>
                        <td>/ExampleModule/ZJManager/GoodsZJ/GoodsZJList.aspx</td>
                        <td><input type="checkbox" checked disabled="disabled"></td>
                        <td>1</td>
                        <td>
                        <span><a data-toggle="modal" data-target="#edit">
                            <i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>
                        </span>
						<span> <a data-toggle="modal" data-target="#add"><i
												class="iconfont" data-toggle="tooltip" data-placement="left"
												title="复制">&#xe61c;</i></a>
						</span>
                       <span><a data-toggle="modal" data-target="#dele">
                           <i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>
                       </span>
                        </td>
                    </tr>
					<tr id="8" pid="6">
                        <td>8</td>
                        <td style="width: 200px;"><span controller="true">销售批号维护</span></td>
                        <td>01010202</td>
                        <td>质控管理</td>
                        <td>Iframe</td>
                        <td>/ExampleModule/ZJManager/BatchMaintenance/BatchMaintenanceList.aspx</td>
                        <td><input type="checkbox" checked disabled="disabled"></td>
                        <td>1</td>
                        <td>
                        <span><a data-toggle="modal" data-target="#edit">
                            <i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>
                        </span>
						<span> <a data-toggle="modal" data-target="#add"><i
												class="iconfont" data-toggle="tooltip" data-placement="left"
												title="复制">&#xe61c;</i></a>
						</span>
                       <span><a data-toggle="modal" data-target="#dele">
                           <i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>
                       </span>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <!--用户表格end-->
            </div>
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
    <div class="modal-dialog" role="document" style="width: 790px;">
        <div class="modal-content" style="height: 435px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <div class="alt_head">
                    <h5>定义模块(菜单)--添加</h5>
                </div>
            </div>
            <div class="modal-body">
                <div class="alt_edit">
                    <div class="alt_edit_div">
                        <label>模块名称：</label>
                        <input type="text" id="name">
                    </div>
                    <div class="alt_edit_div">
                        <label>模块编号：</label>
                        <input type="text" id="code">
                    </div>
                    <div class="alt_edit_div">
                        <label>上级模块：</label>
                        <input type="text" id="role_type" class="easyui-combotree"  required="true"  style="width: 220px;">
                    </div>
                    <div class="alt_edit_div">
                        <label>连接目标：</label>
                        <select class="form-control" id="linkgoal">
                        	<option value="1" name="type">Click</option>
                        	<option value="2" name="type">Iframe</option>
                        	<option value="3" name="type">Open</option>
                        	<option value="4" name="type">href</option>
                        </select>
                    </div>
                    <div class="alt_edit_div"  style="width: 100%">
                        <label>Web连接地址：</label>
                        <input type="text" style="width: 500px" id="uri">
                    </div>
					<div class="alt_edit_div" style="width: 100%">
                        <label>选项：</label>
                        <input type="checkbox" id="isvalid" checked="checked"><span>有效</span>
                        <input type="checkbox" checked="checked" disabled="disabled"><span>展开</span>
                        <input type="checkbox" checked="checked" disabled="disabled"><span>允许编辑</span>
                        <input type="checkbox" checked="checked" disabled="disabled"><span>允许删除</span>
                        <input type="checkbox" disabled="disabled"><span>审核模型</span>
                        <input type="checkbox" disabled="disabled"><span>质检方案 </span>
                    </div>
                    <div class="alt_edit_div">
                        <label>显示顺序：</label>
                        <input type="text" id="order_by">
                    </div>
                    <div class="alt_edit_div">
                        <label>描述：</label>
                        <input type="text" id="info">
                    </div>
                    <div class="alt_edit_div">
                        <label>参数：</label>
                        <input type="text" id="param">
                    </div>
                    <div class="alt_edit_div">
                        <label>图标：</label>
                        <i class="iconfont" id="iconid"> </i>
                        <button class="btn btnblue btn_little" data-toggle="modal" data-target="#iconselect">选择图标</button>
                    </div>
                    <div class="alt_edit_div">
                        <label>分组行：</label>
                        <select class="form-control" id="group">
							<option value="0" name="type">==请选择==</option>
                        	<option value="1" name="type">1</option>
                        	<option value="2" name="type">2</option>
                        	<option value="3" name="type">3</option>
                        	<option value="4" name="type">4</option>
                        	<option value="5" name="type">5</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="ensureAdd">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--新增end-->
<!--编辑begin-->
<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 790px;">
        <div class="modal-content" style="height: 435px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <div class="alt_head">
                    <h5>定义模块(菜单)--编辑</h5>
                </div>
            </div>
            <div class="modal-body">
              <div class="alt_edit">
                    <div class="alt_edit_div">
                        <label>模块名称：</label>
                        <input type="text" id="update_name">
                    </div>
                    <div class="alt_edit_div">
                        <label>模块编号：</label>
                        <input type="text" id="update_code">
                    </div>
                    <div class="alt_edit_div">
                        <label>上级模块：</label>
                        <input type="text" id="update_role" class="easyui-combotree"  required="true"  style="width: 220px;">
                    </div>
                    <div class="alt_edit_div">
                        <label>连接目标：</label>
                        <select class="form-control" id="update_linkgoal">
                        	<option value="1" name="type">Click</option>
                        	<option value="2" name="type">Iframe</option>
                        	<option value="3" name="type">Open</option>
                        	<option value="4" name="type">href</option>
                        </select>
                    </div>
                    <div class="alt_edit_div"  style="width: 100%">
                        <label>Web连接地址：</label>
                        <input type="text" style="width: 500px" id="update_uri">
                    </div>
					<div class="alt_edit_div" style="width: 100%">
                        <label>选项：</label>
                        <input type="checkbox" id="update_isvalid"><span>有效</span>
                        <input type="checkbox" checked="checked" disabled="disabled"><span>展开</span>
                        <input type="checkbox" checked="checked" disabled="disabled"><span>允许编辑</span>
                        <input type="checkbox" checked="checked" disabled="disabled"><span>允许删除</span>
                        <input type="checkbox" disabled="disabled"><span>审核模型</span>
                        <input type="checkbox" disabled="disabled"><span>质检方案 </span>
                    </div>
                    <div class="alt_edit_div">
                        <label>显示顺序：</label>
                        <input type="text" id="update_orderBy">
                    </div>
                    <div class="alt_edit_div">
                        <label>描述：</label>
                        <input type="text" id="update_info">
                    </div>
                    <div class="alt_edit_div">
                        <label>参数：</label>
                        <input type="text" id="update_param">
                    </div>
                    <div class="alt_edit_div">
                        <label>图标：</label>
                        <i class="iconfont" id="edit_iconid"></i>
                        <button class="btn btnblue btn_little" data-toggle="modal" data-target="#iconselect">选择图标</button>
                    </div>
                    <div class="alt_edit_div">
                        <label>分组行：</label>
                        <select class="form-control" id="update_grouping">
							<option value="0" name="type">==请选择==</option>
                        	<option value="1" name="type">1</option>
                        	<option value="2" name="type">2</option>
                        	<option value="3" name="type">3</option>
                        	<option value="4" name="type">4</option>
                        	<option value="5" name="type">5</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="ensureEdit">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--编辑end-->
<!--图标选择begin-->
<div class="modal fade" id="iconselect" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 450px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">菜单图标管理</h4>
            </div>
            <div class="modal-body">
                <div class="menu_icon" id="menu_icons">
                     <ul>
            
                <li>
                <i class="icon iconfont" icon_id="#xe608;">&#xe608;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe60d;">&#xe60d;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe603;">&#xe603;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe601;">&#xe601;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe61b;">&#xe61b;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe63a;">&#xe63a;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe60f;">&#xe60f;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe625;">&#xe625;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe726;">&#xe726;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe692;">&#xe692;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe619;">&#xe619;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe633;">&#xe633;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe61e;">&#xe61e;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe61d;">&#xe61d;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe61f;">&#xe61f;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe600;">&#xe600;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe680;">&#xe680;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe63d;">&#xe63d;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe622;">&#xe622;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe604;">&#xe604;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe61a;">&#xe61a;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe61c;">&#xe61c;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe853;">&#xe853;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe610;">&#xe610;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe602;">&#xe602;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe66e;">&#xe66e;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe7e6;">&#xe7e6;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe647;">&#xe647;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe611;">&#xe611;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe63c;">&#xe63c;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe628;">&#xe628;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe612;">&#xe612;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe629;">&#xe629;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe695;">&#xe695;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe62f;">&#xe62f;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe6c8;">&#xe6c8;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe605;">&#xe605;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe613;">&#xe613;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe635;">&#xe635;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe609;">&#xe609;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe654;">&#xe654;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe62b;">&#xe62b;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe621;">&#xe621;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe681;">&#xe681;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe626;">&#xe626;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe60c;">&#xe60c;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe636;">&#xe636;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe614;">&#xe614;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe66f;">&#xe66f;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe60e;">&#xe60e;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe627;">&#xe627;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe620;">&#xe620;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe606;">&#xe606;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe615;">&#xe615;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe60a;">&#xe60a;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe634;">&#xe634;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe638;">&#xe638;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe631;">&#xe631;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe748;">&#xe748;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe651;">&#xe651;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe623;">&#xe623;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe717;">&#xe717;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe616;">&#xe616;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe617;">&#xe617;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe618;">&#xe618;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe60b;">&#xe60b;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe65e;">&#xe65e;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe670;">&#xe670;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe607;">&#xe607;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe668;">&#xe668;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe624;">&#xe624;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe65a;">&#xe65a;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe637;">&#xe637;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe74b;">&#xe74b;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe652;">&#xe652;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe64e;">&#xe64e;</i>
                </li>
            
                <li>
                <i class="icon iconfont" icon_id="#xe62a;">&#xe62a;</i>
                </li>
                
        </ul>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" id="quding">确定</button>
            </div>
        </div>
    </div>
</div>
<!--图标选择end-->
<!--详细end-->

<!-- 引用公共footer部分 -->
<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
<script type="text/javascript"
		src="/javascript/system/auth/menu.js"></script>
<jsp:include page="../../common/base/footer_busi.jsp" ></jsp:include>

</body>
</html>