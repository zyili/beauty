<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
	var start = 5;
	var step = -1;
	function count() {
		document.getElementById("div1").innerHTML = "页面将在" + start+ "秒后返回...";
		start += step;
		if (start <= 0) {
			start = 5;
			window.top.location=${backurl};
		}
		setTimeout("count()", 1000);
	}
	window.onload = count;
</script>
<style>
	.myDiv,p{
		margin:0 auto;
		margin-top:80px;
		width:300px;
		color: red;
	}
</style>
</head>
<body>
	<div class="myDiv">
		<p>${errorInfo}...</p>
		<div id="div1"></div>
	</div>
</body>
</html>