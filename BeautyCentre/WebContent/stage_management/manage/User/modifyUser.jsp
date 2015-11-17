<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>角色管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
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
        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }
    </style>
</head>
<script type="text/javascript">
function Back()
{
	if (confirm("您确定要返回吗？"))
	window.location.href="user.jsp";
}
function setOption()
{     
	var roleId=${user.roleId};
	var id ="#option"+roleId;
	$(id).attr("selected","selected");
}
function setRadio()
{
	var value=${user.type};
	if(value=="1")
	{
		$("#radio1").attr("checked", "checked"); 
	}
	if(value=="0")
	{
		$("#radio2").attr("checked", "checked"); 
	}
}
$(document).ready(function(){
	setRadio();
	setOption();
});
</script>
<body>
<form action="modifyUser" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">登录名</td>
        <td><input type="text" name="username" value="${user.name}"/></td>
    </tr>
    <tr>
        <td class="tableleft">密码</td>
        <td><input type="password" name="password" value="${user.password}"/></td>
    </tr>
    <tr>
        <td class="tableleft">真实姓名</td>
        <td><input type="text" name="realname" value="${user.realName}"/></td>
    </tr>
    <tr>
        <td class="tableleft">状态</td>
        <td>
			<input type="radio" name="status" value="1" id="radio1" /> 启用
			<input type="radio" name="status" value="0" id="radio2" /> 禁用
        </td>
    </tr>
    <tr>
        <td class="tableleft">角色</td>
        <td>  
        	<select name="role" id="mySelect">
        	  <c:forEach var="role"  items="${sessionScope.rolelist}" begin="0"> 
        	  	<option id="option${role[0]}" value='${role[0]}'>${role[1]}</option>
        	  </c:forEach>
           </select>
		</td>
    </tr>
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" type="button">保存</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" onclick="Back()" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form>
</body>
</html>