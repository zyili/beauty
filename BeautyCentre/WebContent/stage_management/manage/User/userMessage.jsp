<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>帐号信息查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="../Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="../Css/style.css" />
<script type="text/javascript" src="../Js/jquery.js"></script>
<script type="text/javascript" src="../Js/jquery.sorted.js"></script>
<script type="text/javascript" src="../Js/bootstrap.js"></script>
<script type="text/javascript" src="../Js/ckform.js"></script>
<script type="text/javascript" src="../Js/common.js"></script>
<style type="text/css">
body {
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
</style>
</head>
</body>
<script type="text/javascript">
	$(function() {
		$('#addnew').click(function() {
			window.location.href = "addUser";
		});
	});
	function delcfm() {
		if (!confirm("确认要修改？")) {
			return false;
		}
	}
	$(document).ready(function() {
		showPage();
	});
	function showPage() {
		var totalPage = ${sessionScope.totalPage};
		var page = ${page};
		var S1 = document.getElementById('s1'); //总页数
		var S2 = document.getElementById('s2'); //当前页数
		S1.innerHTML = '';
		S2.innerHTML = '';
		S1.appendChild(document.createTextNode(totalPage));
		S2.appendChild(document.createTextNode(page));
	}
	function First() {
		var page = 1;
		window.location.href = 'listUser?page=' + page;
	}
	function Next() {
		var nextpage = ${page}+1;
		var totalPage = ${sessionScope.totalPage};
		if (nextpage <= totalPage) {
			window.location.href = 'listUser?page=' + nextpage;
		} else {
			alert('错误,已到最后一页');
		}
	}
	function Prepage() {
		var prepage = ${page}-1;
		if (prepage > 0) {
			window.location.href = 'listUser?page=' + prepage;
		} else {
			alert('错误,已到第一页');
		}
	}
	function Lpage() {
		var page = ${sessionScope.totalPage};
		window.location.href = 'listUser?page=' + page;
	}
</script>
<form class="form-inline definewidth m20" action="queryUser"
	method="post">
	用户名称或ID： <input type="text" name="username" value="请输入内容" onfocus="javascript:if(this.value=='请输入内容')this.value='';" id="username"
		class="abc input-default">&nbsp;&nbsp;
	<button type="submit" class="btn btn-primary">查询</button>
</form>
<table id="tb_data"
	class="table table-bordered table-hover definewidth m10">
	<thead>
		<tr>
			<th>用户id</th>
			<th>用户名称</th>
			<th>真实姓名</th>
			<th>用户类型</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="user" items="${sessionScope.allUsers}" begin="0">
			<tr>
				<td>${user.userid}</td>
				<td>${user.username}</td>
				<td>${user.realname}</td>
				<td><c:if test="${user.type==1}">
						个人
						</c:if> <c:if test="${user.type==0}">
						企业
						</c:if> <c:if test="${user.type==100}">
						管理员
						</c:if></td>
				<td><a href="findUserMessage?userid=${user.userid}">详细信息&nbsp;&nbsp;&nbsp;</a>
					<a href="modifyUserPass?userid=${user.userid}"
					onclick="return delcfm();">&nbsp;&nbsp;&nbsp;修改密码</a></td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="5">总共<span id="s1"></span>页
				&nbsp;&nbsp;&nbsp;&nbsp;当前第<span id="s2"></span>页
				<div id="div-button">
					<input type="button" onClick="First();" value="首页" id="F-page">
					&nbsp;&nbsp; <input type="button" onClick="Next();" value="下一页"
						id="Nex-page"> &nbsp;&nbsp; <input type="button"
						onClick="Prepage();" value="上一页" id="Pre-page">
					&nbsp;&nbsp; <input type="button" onClick="Lpage();" value="尾页"
						id="L-page">
				</div>
			</td>
		</tr>
	</tfoot>
</table>
</body>
</html>