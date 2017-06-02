<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" src="${staticBasePath}/js/menu_list.js"></script>
<script type="text/javascript">
	if (!Array.prototype.forEach) {

		Array.prototype.forEach = function forEach(callback, thisArg) {

			var T, k;

			if (this == null) {
				throw new TypeError("this is null or not defined");
			}
			var O = Object(this);
			var len = O.length >>> 0;
			if (typeof callback !== "function") {
				throw new TypeError(callback + " is not a function");
			}
			if (arguments.length > 1) {
				T = thisArg;
			}
			k = 0;

			while (k < len) {

				var kValue;
				if (k in O) {

					kValue = O[k];
					callback.call(T, kValue, k, O);
				}
				k++;
			}
		};
	}
	if (!Array.prototype.filter) {
	    Array.prototype.filter = function(fun /*, thisp*/){
	        var len = this.length;
	        if (typeof fun != "function"){
	            throw new TypeError();
	        }
	        var res = new Array();
	        var thisp = arguments[1];
	        for (var i = 0; i < len; i++){
	            if (i in this){
	                var val = this[i]; // in case fun mutates this
	                if (fun.call(thisp, val, i, this)) {
	                    res.push(val);
	                }
	            }
	        }
	        return res;
	    };
	}
	var loginOut = document.getElementById('loginOut');
	loginOut.onclick = function(){
		layer.confirm('确认退出登录?', {
			btn: ['确定', '取消'],
			area: '600px'
		}, function(){
			window.location.href = '/trfc/loginOut';
		});
	};
</script>