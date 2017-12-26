<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	.layui-table td, .layui-table th{
		position: relative;
	    padding: 9px 12px;
	    min-height: 20px;
	    line-height: 20px;
	    font-size: 10px;
	    border: 1px solid #e2e2e2;
	}
	.layui-table .td_title{
		   padding: 5px 5px;
		   -webkit-transform:scale(0.8);
	}
	.layui-table .td_contend{
		   -webkit-transform:scale(0.7);
		   padding: 5px 1px;  
		   text-align: left
	}


</style>
<script id="demo" type="text/html">
    <table class="layui-table print"
        style="width: calc(100% - 1px) !important; margin: 0px !important;">
        <tbody>
            <tr>
                <td colspan="8" align="center"
                    style="font-size: 15px; font-weight: bold; border: none;">天瑞集团裕泰水泥有限公司    {{d.type || ''}}</td>
            </tr>
            <tr>
                <td class="td_title">{{d.billName || ''}}</td>
                <td class="td_contend">{{d.billCode || ''}}</td>
                <td class="td_title">{{d.noticeName || ''}}</td>
                <td class="td_contend">{{d.noticeCode || ''}}</td>
                <td class="td_title">计量单号</td>
                <td class="td_contend">{{d.poundNoteCode || ''}}</td>
                <td class="td_title">计量单位</td>
                <td class="td_contend">{{d.unit || ''}}</td>
            </tr>
            <tr>
                <td class="td_title">客户名称</td>
                <td class="td_contend" colspan="3">{{d.customer || ''}}</td>
                <td class="td_title">承运单位</td>
                <td class="td_contend" colspan="3">{{d.carrierUnit || ''}}</td>
            </tr>
            <tr>
                <td class="td_title">发货单位</td>
                <td class="td_contend" colspan="3">{{d.sendDept || ''}}</td>
                <td class="td_title">车牌号码</td>
                <td class="td_contend">{{d.vehicle || ''}}</td>
                <td class="td_title">皮重时间</td>
                <td class="td_contend">{{d.tareTime || ''}}</td>
            </tr>
            <tr>
                <td class="td_title">产品名称</td>
                <td class="td_contend" colspan="3">{{d.material || ''}}</td>
                <td class="td_title">业务类型</td>
                <td class="td_contend">{{d.billType || ''}}</td>
                <td class="td_title">毛重时间</td>
                <td class="td_contend">{{d.grossTime || ''}}</td>
            </tr>
            <tr>
                <td class="td_title">皮重</td>
                <td class="td_contend">{{d.tareWeight || ''}}</td>
                <td class="td_title">毛重</td>
                <td class="td_contend">{{d.grossWeight || ''}}</td>
                <td class="td_title">净重</td>
                <td class="td_contend" colspan="3">{{d.netWeight || ''}}</td>
            </tr>
            <tr>
                <td class="td_title" >净重大写</td>
                <td class="td_contend" colspan="3">{{d.netWeightBig}}{{d.unit || ''}}</td>
                <td class="td_title" >备注</td>
                <td class="td_contend" colspan="3"></td>
            </tr>
            <tr>
                <td class="td_title">毛重计量员</td>
                <td class="td_contend">计量系统</td>
                <td class="td_title">皮重计量员</td>
                <td class="td_contend">计量系统</td>
                <td class="td_title">司机签字</td>
                <td class="td_contend" colspan="2"></td>
                <td class="td_title">盖章有效</td>
            </tr>
        </tbody>
    </table>
</script>
<script type="text/javascript">
//打印
$('#print').off('click').on('click', function(e){
    e.stopPropagation();
    var obj = $('table.maintable tbody tr.active').data();
    if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
    $.post('/trfc/poundNote/print', {id: obj.id}, function (result){
        if (result.code = '000000') {
        	var data = result.data || {};
        	switch (data.type) {
			case '0':
				data.type = '采购计量单';
				data.billName = '采购订单';
				data.noticeName = '收货单号';
				break;
			case '1':
				data.type = '采购计量单';
				data.billName = '采购订单';
				data.noticeName = '收货单号';
				break;
			case '2':
				data.type = '销售计量单';
				data.billName = '销售订单';
				data.noticeName = '发货单号';
				break;
			default:
				break;
			}
            print(data);
        } else {
            layer.msg(result.error, {icon: 5});
        }
    });
});
function print(data) {
    layer.open({
          type: 1,
          title: false, //不显示标题栏
          closeBtn: false,
          area : [ '670px', '400px' ],
          shade: 0.8,
          id: 'LAY_layuipro', //设定一个id，防止重复弹出
          resize: false,
          btn: ['打印', '关闭'],
          btnAlign: 'c',
          moveType: 1, //拖拽模式，0或者1
          shadeClose : true, //开启遮罩关闭
          content: '<div id="view"></div>',
          success: function(layero){
              layui.use('laytpl', function(){
                    var laytpl = layui.laytpl;

                    var getTpl = demo.innerHTML,
                    view = document.getElementById('view');
                    
                    laytpl(getTpl).render(data, function(html){
                       view.innerHTML = html;
                    })
                });
          },
          yes: function(index, layero) {
//            console.info(index)
//            console.info(layero)
              layero.find('table').jqprint();
          }
        });
}
</script>