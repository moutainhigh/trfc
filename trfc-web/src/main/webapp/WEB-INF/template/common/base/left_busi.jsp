<%@ page language="java" pageEncoding="UTF-8"%>
<div class="left ">
	<div class="user">
		<a href="#" data-toggle="dropdown" data-target="#menu-messages"
			class="dropdown-toggle"> <img
			src="${staticBasePath}/images/tx.jpg" class="img-circle"> <label>${sessionScope.userName }</label>
		</a>
	</div>
	<div class="menu">
		<label>菜单</label> <i class="iconfont fr">&#xe61a;</i>
	</div>
	<div class="menu_collap">
		<ul class="typelist ">
			<a href="#ityewu" data-toggle="collapse" class="menu_collap_tit">
				<label>业务管理</label> <span><i class="iconfont">&#xe604;</i></span>
			</a>

			<div class="in" id="ityewu">
				<!-- <li><a href="../cg/cg_index.html"> <i class="iconfont">&#xe617;</i>
						<label>采购管理</label>
				</a></li> -->
				<li><a href="/trfc/salesApplication/main"> <i
						class="iconfont">&#xe615;</i> <label>销售管理</label>
				</a></li>
				<li><a href="/trfc/card/main"> <i
						class="iconfont">&#xe615;</i> <label>卡务管理</label>
				</a></li>
				<!-- <li><a> <i class="iconfont">&#xe614;</i> <label>其他业务</label>
				</a></li>
				<li><a> <i class="iconfont">&#xe618;</i> <label>质控管理</label>
				</a></li> -->
			</div>
			<a href="#itdangan" data-toggle="collapse" class="menu_collap_tit">
				<label>基础档案</label> <span><i class="iconfont">&#xe604;</i></span>
			</a>

			<div class="in" id="itdangan">
				<li><a href="/trfc/customer/main"> <i class="iconfont">&#xe617;</i>
						<label>NC档案</label>
				</a></li>
				<li><a href="/trfc/vehicle/main"> <i class="iconfont">&#xe617;</i>
						<label>计量档案</label>
				</a></li>
				<li><a href="/trfc/basicFile/other/customer/main"> <i class="iconfont">&#xe617;</i>
						<label>其他档案</label>
				</a></li>
			</div>
			<a href="#sys" data-toggle="collapse" class="menu_collap_tit"> <label>系统权限</label>
				<span><i class="iconfont">&#xe604;</i></span>
			</a>
			<div class="in" id="sys">
				<li><a href="/trfc/system/auth/user/main"> <i
						class="iconfont">&#xe617;</i> <label>系统权限</label>
				</a></li>
				<li><a href="/trfc/system/base/code/main"> <i
						class="iconfont">&#xe617;</i> <label>系统业务</label>
				</a></li>
			</div>
		</ul>
	</div>
</div>
<div class="leftmini hide">
	<div class="user">
		<a href="#" data-toggle="dropdown" data-target="#menu-messages"
			class="dropdown-toggle"> <img
			src="${staticBasePath}/images/tx.jpg" class="img-circle">
		</a>
	</div>
	<div class="menu2">
		<i class="iconfont">&#xe635;</i>
	</div>
	<ul class="typelist">
		<li class="active" data-toggle="tooltip" data-placement="right"
			title="采购管理"><i class="iconfont">&#xe617;</i></li>
		<li data-toggle="tooltip" data-placement="right" title="销售管理"><i
			class="iconfont">&#xe615;</i></li>
		<li data-toggle="tooltip" data-placement="right" title=" 其他"><i
			class="iconfont">&#xe614;</i></li>
		<li><i class="iconfont">&#xe618;</i></li>
		<li><i class="iconfont">&#xe619;</i></li>
		<li><i class="iconfont">&#xe613;</i></li>
		<li><i class="iconfont">&#xe612;</i></li>
		<li><i class="iconfont">&#xe610;</i></li>
		<li><i class="iconfont">&#xe60f;</i></li>
		<li><i class="iconfont">&#xe611;</i></li>
	</ul>
</div>
