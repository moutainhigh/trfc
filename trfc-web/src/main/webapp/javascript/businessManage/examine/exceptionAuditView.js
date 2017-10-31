;(function($) {
	
	$('#audit').off('click').on('click', function() {
		layer.open({
			type: 1,
			title: '审批意见',
			btn: ["&#x786E;&#x5B9A;", "&#x53D6;&#x6D88;"],
			content: '<textarea class="layui-layer-input"></textarea>',
			skin: "layui-layer-prompt",
			resize: !1,
			yes: function(index,layero) {
				var value = layero.find('textarea').val();value = $.trim(value);
				var id = $('#exceptionAuditId').val();
				$.post('/trfc/exceptionAudit/audit', {id: id, auditOpinion: value}, function(result) {
					if(result.code == '000000'){
						window.location.href = '/trfc/exceptionAudit/main';
					}else{
						layer.msg(result.error,{icon:5});
					}
					layer.close(index);
				});
			}
		});
	});
	
})(jQuery);