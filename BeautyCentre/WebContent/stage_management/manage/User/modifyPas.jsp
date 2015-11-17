<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>密码修改</title>
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
<script>
    $(function () {       
		$('#backid').click(function(){
				//window.location.href="user.jsp";
			document.getElementById("oldPass").value="";
			document.getElementById("newPass").value="";
			document.getElementById("surePass").value="";
		 });

    });
    function checkPass()
	{
    	var pass=${user.password};
    	var oldPass=document.getElementById('oldPass').value;
    	var newPass=document.getElementById('newPass').value;
    	var surePass=document.getElementById('surePass').value;
    	if(pass==oldPass)
    	{
    		if(newPass==surePass)
    		{
    			//alert('可以申请后台改变密码');
    			return true;
    		}
    		else
    		{
    			alert('两次密码输入不一致,请重新输入');
    			document.getElementById("newPass").value="";
    			document.getElementById("surePass").value="";
    		}
    	}
    	else
    	{
    		alert('原始密码输入不正确');
    		document.getElementById('oldPass').value="";
    	}
		return false;
	}
</script>
<body>
<form action="modifyPas" method="post" class="definewidth m20" onsubmit="return checkPass();">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">原始密码:</td>
        <td><input type="text" id="oldPass" name="oldPass"/></td>
    </tr>
    <tr>
        <td class="tableleft">新 密 码:</td>
        <td><input type="password" id="newPass" name="newPass"/></td>
    </tr>
    <tr>
        <td class="tableleft">确认密码:</td>
        <td><input type="text" id="surePass" name="surePass"/></td>
    </tr>
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" type="button">提交</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">清空</button>
        </td>
    </tr>
</table>
</form>
</body>
</html>