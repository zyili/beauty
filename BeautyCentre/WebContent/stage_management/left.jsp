<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理页面</title>
<base target="main"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/demo.css" type="text/css">
<link rel="stylesheet" href="css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/jquery.ztree.core-3.5.js"></script>
<style>
body {
	font:12px Arial, Helvetica, sans-serif;
	color: #000;
	background-color: #EEF2FB;
	margin: 0px;
}
#container {
	width: 235px;
	height:100%
}
H1 {
	font-size: 12px;
	margin: 0px;
	width: 182px;
	cursor: pointer;
	height: 30px;
	line-height: 20px;	
}
H1 a {
	display: block;
	width: 182px;
	color: #000;
	height: 30px;
	text-decoration: none;
	moz-outline-style: none;
	background-image: url(image/menu_bgS.gif);
	background-repeat: no-repeat;
	line-height: 30px;
	text-align: center;
	margin: 0px;
	padding: 0px;
}
.content{
	width: 182px;
	height: 26px;
	
}
.MM ul {
	list-style-type: none;
	margin: 0px;
	padding: 0px;
	display: block;
}
.MM li {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	list-style-type: none;
	display: block;
	text-decoration: none;
	height: 26px;
	width: 182px;
	padding-left: 0px;
}
.MM {
	width: 182px;
	margin: 0px;
	padding: 0px;
	left: 0px;
	top: 0px;
	right: 0px;
	bottom: 0px;
	clip: rect(0px,0px,0px,0px);
}
.MM a:link {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(image/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 26px;
	width: 182px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:visited {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(image/menu_bg1.gif);
	background-repeat: no-repeat;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 182px;
	text-decoration: none;
}
.MM a:active {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(image/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 26px;
	width: 182px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:hover {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	font-weight: bold;
	color: #006600;
	background-image: url(image/menu_bg2.gif);
	background-repeat: no-repeat;
	text-align: center;
	display: block;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 182px;
	text-decoration: none;
}
</style>
<SCRIPT type="text/javascript">
		var setting = {
				view:{
					showLine: false,
					selectedMulti: false,
					dblClickExpand: true
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				callback: {
					onNodeCreated: this.onNodeCreated,
					onClick: onClick
				}
			};
		var menus=${menus};
		var zNodes =eval(menus);

		function onClick(e,treeId, treeNode) {
			if(treeNode.pId===null)
			{
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.expandNode(treeNode);	
			}
			else
			{
				//window.top.location.href="unlogin";
				//window.top.frames["manFrame"].location="login-Ma.jsp";
				window.top.frames["main"].location=treeNode.toUrl;
			}
		}
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
	</SCRIPT>
</head>
<body>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#EEF2FB">
    <tr>
      <td width="235" height="8" valign="middle"  align="center"  background="image/bg.gif">
      <h1 class="type"><a href="javascript:void(0)">登录信息</a></h1>
      </td>
    </tr>
    <tr>
     <td height="85" >
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="image/menu_topline.gif" width="235" height="5" /></td>
          </tr>
        </table>
        <table>
        	<tr>
        		<td width="30%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        		<td width="70%">
        			<ul class="MM">
          			<li><h3>登录姓名：${user.username}</h3></li>
          			<li><h3>员工工号：${user.userid} </h3></li>
          			<li><h3>登录岗位：管理员</h3></li>
        			</ul>
        		</td>
        	</tr>
        </table>
		</div>
   </td>
  </tr>
   <tr>
      <td width="235" height="5" valign="middle"  align="center"  background="image/bg.gif">
      <h1 class="type"><a href="javascript:void(0)">主操作区</a></h1>
      </td>
    </tr>
    <tr>
     <td>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="image/menu_topline.gif" width="235" height="5" /></td>
          </tr>
        </table>
			<ul id="treeDemo" class="ztree"></ul>
		</div>
   </td>
  </tr>
</table>
</body>
</html>