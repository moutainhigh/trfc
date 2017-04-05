<%@ page language="java" pageEncoding="UTF-8"%>
<div class="left ">
	<div class="user" userid=${sessionScope.systemUser.id }>
		<a href="#" data-toggle="dropdown" data-target="#menu-messages"
			class="dropdown-toggle"> <img
			src="${staticBasePath}/images/tx.jpg" class="img-circle"> <label>${sessionScope.systemUser.name }</label>
		</a>
	</div>
	<div class="menu">
		<label>菜单</label> <i class="iconfont fr">&#xe61a;</i>
	</div>
	<div class="menu_collap">
		<ul class="typelist " id="menulist">
			<a href="#ityewu" data-toggle="collapse" class="menu_collap_tit">
				<label>业务管理</label> <span><i class="iconfont">&#xe604;</i></span>
			</a>

			<div class="in" id="ityewu">
				<!-- <li><a href="../cg/cg_index.html"> <i class="iconfont">&#xe617;</i>
						<label>采购管理</label>
				</a></li> -->
				<li><a href="/trfc/purchaseApplication/main"> <i
						class="iconfont">&#xe617;</i> <label>采购管理</label>
				</a></li>
				<li><a href="/trfc/salesApplication/main"> <i
						class="iconfont">&#xe615;</i> <label>销售管理</label>
				</a></li>
				<li><a href="/trfc/card/main"> <i class="iconfont">&#xe613;</i>
						<label>卡务管理</label>
				</a></li>
				<li><a href="/trfc/quality/sales/batchnum/main"> <i
						class="iconfont">&#xe618;</i> <label>质控管理</label>
				</a></li>
				<li><a href="/trfc/customerbegin/main"> <i class="iconfont">&#xe612;</i>
						<label>财务管理</label>
				</a></li>
				<li><a href="/trfc/poundNote/purchase/main"> <i
						class="iconfont">&#xe631;</i> <label>磅单维护</label>
				</a></li>
				<li><a href="/trfc/accessRecord/main"> <i class="iconfont">&#xe619;</i>
						<label>厂区物流</label>
				</a></li>
				<!-- <li><a> <i class="iconfont">&#xe614;</i> <label>其他业务</label>
				</a></li>
				 -->
			</div>
			<a href="#itdangan" data-toggle="collapse" class="menu_collap_tit">
				<label>基础档案</label> <span><i class="iconfont">&#xe604;</i></span>
			</a>

			<div class="in" id="itdangan">
				<li><a href="/trfc/customer/main"> <i class="iconfont">&#xe611;</i>
						<label>NC档案</label>
				</a></li>
				<li><a href="/trfc/transport/main"> <i class="iconfont">&#xe611;</i>
						<label>计量档案</label>
				</a></li>
				<li><a href="/trfc/quality/sales/file/MaterialScheme/main">
						<i class="iconfont">&#xe611;</i> <label>质检档案</label>
				</a></li>
				<li><a href="/trfc/basicFile/other/customer/main"> <i
						class="iconfont">&#xe611;</i> <label>其他档案</label>
				</a></li>
			</div>
			<a href="#sys" data-toggle="collapse" class="menu_collap_tit"> <label>系统设置</label>
				<span><i class="iconfont">&#xe604;</i></span>
			</a>
			<div class="in" id="sys">
				<li><a href="/trfc/system/auth/menu/main"> <i
						class="iconfont">&#xe668;</i> <label>系统权限</label>
				</a></li>
				<li><a href="/trfc/system/base/code/main"> <i
						class="iconfont">&#xe614;</i> <label>系统业务</label>
				</a></li>
			</div>
		</ul>
	</div>
</div>
<div class="leftmini hide">
	 <div class="user">
        <a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle">
            <img src="${staticBasePath}/images/tx.jpg" class="img-circle">
        </a>
    </div>
    <div class="menu2">
        <i class="iconfont">&#xe635;</i>
    </div>
    <ul class="typelist">
        <li class="active" data-toggle="tooltip" data-placement="right" title="采购管理">
            <a href="/trfc/purchaseApplication/main">
                <i class="iconfont">&#xe617;</i>
            </a>
        </li>
        <li data-toggle="tooltip" data-placement="right" title="卡务管理">
            <a href="/trfc/card/main">
                <i class="iconfont">&#xe613;</i>
            </a>
        </li>
        <li data-toggle="tooltip" data-placement="right" title="质控管理">
            <a href="/trfc/quality/sales/batchnum/main">
                <i class="iconfont">&#xe618;</i>
            </a>
        </li>
        <li data-toggle="tooltip" data-placement="right" title="财务管理">
            <a href="/trfc/customerbegin/main">
                <i class="iconfont">&#xe612;</i>
            </a>
        </li>
        <li data-toggle="tooltip" data-placement="right" title="磅单维护">
            <a href="/trfc/poundNote/purchase/main">
                <i class="iconfont">&#xe631;</i>
            </a>
        </li>
        <li data-toggle="tooltip" data-placement="right" title="厂区物流">
            <a href="/trfc/accessRecord/main">
                <i class="iconfont">&#xe619;</i>
            </a>
        </li>
        <li data-toggle="tooltip" data-placement="right" title="NC档案">
            <a href="/trfc/customer/main">
                <i class="iconfont">&#xe611;</i>
            </a>
        </li>
        <li data-toggle="tooltip" data-placement="right" title="计量档案">
            <a href="/trfc/transport/main">
                <i class="iconfont">&#xe611;</i>
            </a>
        </li>
        <li data-toggle="tooltip" data-placement="right" title="质检档案">
            <a href="/trfc/quality/sales/file/MaterialScheme/main">
                <i class="iconfont">&#xe611;</i>
            </a>
        </li>
        <li data-toggle="tooltip" data-placement="right" title="其他档案">
            <a href="/trfc/basicFile/other/customer/main">
                <i class="iconfont">&#xe611;</i>
            </a>
        </li>
        <li data-toggle="tooltip" data-placement="right" title="系统权限">
            <a href="/trfc/system/auth/menu/main">
                <i class="iconfont">&#xe668;</i>
            </a>
        </li>
        <li data-toggle="tooltip" data-placement="right" title="系统业务">
            <a href="/trfc/system/base/code/main">
                <i class="iconfont">&#xe614;</i>
            </a>
        </li>
    </ul> 
</div>
<script type="text/javascript">
	var URLData = {
		'0':['purchaseApplication','purchaseArrive','purchaseReturn','purchaseVehicle'],	
		'1':['salesApplication','salesArrive','salesVehicle'],
		'2':['card','cardReissue'],
		'3':['quality/sales/batchnum','quality/sales/report','quality/purchase/sampling','quality/purchase/assay'],
		'4':['customerbegin','salescharge','customerback','salesledger','salesdetail'],
		'5':['poundNote/purchase','poundNote/sales'],
		'6':['accessRecord'],
		'7':['customer','warehouse','supplier','materiel'],
		'8':['transport','vehicle','driver','minemouth','yard'],
		'9':['quality/sales/file/MaterialScheme','quality/sales/file/qualityScheme','quality/sales/file/qualityItem','quality/sales/file/supplierScheme','quality/sales/file/certification'],
		'10':['basicFile/other/customer','other/otherVehicle','basicFile/other/material','basicFile/other/driver','basicFile/other/supplier'],
		'11':['system/auth/menu','system/auth/user','system/auth/role'],
		'12':['system/base/code','system/base/dataDict']
	};
	var href = window.location.href;
	href = href.split('/trfc/')[1];
	urlstr = href.substring(0,href.lastIndexOf('/'));
	console.log(urlstr);
	for(var i=0;i<13;i++){
		if(URLData[i].indexOf(urlstr)>=0){
			$('#menulist >div >li').eq(i).attr('class','active');
			break;
		}
	}
</script>