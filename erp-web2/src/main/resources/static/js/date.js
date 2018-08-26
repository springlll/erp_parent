//把毫秒数转换成指定日期格式的字符串
function to_date(dataVal) {
	if (dataVal != null) {
		var d = new Date(dataVal); 
		var s = "";           // 声明变量。
		s += d.getFullYear() + "-";   // 获取年份。
		s += (d.getMonth() + 1) + "-"; // 获取月份。
		s += d.getDate();   // 获取日。
		return(s);   // 返回日期。
	} 
	return '';
}