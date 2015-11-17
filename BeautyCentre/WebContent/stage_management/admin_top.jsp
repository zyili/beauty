<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv=Content-Type content=text/html;charset=utf-8>
<title> 管理页面</title>
<script language=JavaScript>
function logout(){
	if (confirm("您确定要退出控制面板吗？"))
	window.top.location.href="unlogin";
	return false;
}
</script>
<script language=JavaScript1.2>
function showsubmenu(sid) {
	var whichEl = eval("submenu" + sid);
	var menuTitle = eval("menuTitle" + sid);
	if (whichEl.style.display == "none"){
		eval("submenu" + sid + ".style.display=\"\";");
	}else{
		eval("submenu" + sid + ".style.display=\"none\";");
	}
}
</script>
<meta http-equiv="refresh" content="60">
<script language=JavaScript1.2>
function showsubmenu(sid) {
	var whichEl = eval("submenu" + sid);
	var menuTitle = eval("menuTitle" + sid);
	if (whichEl.style.display == "none"){
		eval("submenu" + sid + ".style.display=\"\";");
	}else{
		eval("submenu" + sid + ".style.display=\"none\";");
	}
}
</script>
<link rel="stylesheet" href="css/common.css" type="text/css"/>
<script type="text/javascript">
function showtime(){
	var now=new Date();
	var year=now.getFullYear();
	var month=now.getMonth()+1;
	var day=now.getDate();
	var hours=now.getHours();
	var minutes=now.getMinutes();
	var seconds=now.getSeconds();
	var week=new Array(); <!--显示几天为星期几-->
    week[0]="星期天 ";
    week[1]="星期一 ";
    week[2]="星期二 ";
    week[3]="星期三 ";
    week[4]="星期四 ";
    week[5]="星期五 ";
    week[6]="星期六 ";
// 	time=year+'年'+month+'月'+day +'日'+"&nbsp;"+week[now.getDay()]+hours+':'+minutes+':'+seconds;
	time=year+'年'+month+'月'+day +'日'+"&nbsp;"+week[now.getDay()];
	var li=document.getElementById('time');
	li.innerHTML="<span style='font-size:16px; color:blanchedalmond;;font-weight: bold;'><img src='images/time.gif' width='20px' height='18px' padding-top='15px'/>&nbsp;"+time+"&nbsp;&nbsp;&nbsp;</span>";
	}
	function letstart(){
	taskId=setInterval(showtime,500);
	}
	window.onload=function(){
		/*var div1=document.getElementById('div1');
		div1.onclick=letstart;*/
		letstart();
	}
</script>
<base target="main">
<link href="image/skin.css" rel="stylesheet" type="text/css">
</head>
<body leftmargin="0" topmargin="0">
<table width="100%" height="64" border="0" cellpadding="0" cellspacing="0" class="admin_topbg">
  <tr>
    <td width="21%" height="64"><img src="image/logo.gif" width="262" height="64"></td>
    <td width="79%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
      	<td width="54%"  id="time">  </td>
        <td width="30%" height="38" class="admin_txt">管理员：<b>${user.username}</b> 您好,感谢登陆使用！</td>
		
        <td width="12%"><a href="#" target="_self" onClick="logout();"><img src="image/out.gif" alt="安全退出" width="46" height="20" border="0"></a></td>
        <td width="4%">&nbsp;</td>
      </tr>
      <tr>
        <td height="19" colspan="3">&nbsp;</td>
        </tr>
    </table>
    </td>
  </tr>
</table>
</body>
</html>