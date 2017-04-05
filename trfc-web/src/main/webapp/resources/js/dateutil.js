//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.format = function (fmt) { //author: meizz 
	var o = {
			"M+": this.getMonth() + 1, //月份 
			"d+": this.getDate(), //日 
			"H+": this.getHours(), //小时 
			"m+": this.getMinutes(), //分 
			"s+": this.getSeconds(), //秒 
			"q+": Math.floor((this.getMonth() + 3) / 3), //季度 
			"S": this.getMilliseconds() //毫秒 
	};
	if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

Date.parseYMD_HMS = function (dateStr) {
	if(dateStr){
		dateStr = dateStr.split(' ');
		var date = dateStr[0];
		var time = dateStr[1];
		var year = date.split('-')[0];
		var month = date.split('-')[1];
		var day = date.split('-')[2];
		var hour = '';
		var minutes = '';
		var seconds = '';
		if(time){
			hour = time.split(':')[0];
			minutes = time.split(':')[1];
			seconds = time.split(':')[2];
		}else{
			return new Date(date);
		}
		function isNumber(n){
			return /^\+?[0-9][0-9]*$/.test(n);
		}
		if(isNumber(year) && isNumber(month) && isNumber(day) 
				&& isNumber(hour) && isNumber(minutes) && isNumber(seconds)){
			return new Date(year, month, day, hour, minutes-1, seconds);
		}else{
			console.error('The format is incorrect!');
		}
	}else{
		console.error('Parameter is empty!');
	}
}

