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
<body>
<script type="text/javascript">
    $(function () {
		$('#addnew').click(function(){
				window.location.href="add.jsp";
		 });
    });
    function  delcfm()
    {
    	if (!confirm("确认要删除？")) 
    	{
    		return  false;
    	}
    }
</script>
<form class="form-inline definewidth m20" action="getRoleByName" method="post">  
    角色名称：
    <input type="text" name="rolename" id="rolename"class="abc input-default" placeholder="" value="">&nbsp;&nbsp;  
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp; <button type="button" class="btn btn-success" id="addnew">新增角色</button>
</form>

<table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
        <th>角色id</th>
        <th>角色名称</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach var="role"  items="${sessionScope.rolelist}" begin="0"> 
    <tr>
    	 <td>${role[0]}</td>
         <td>${role[1]}</td>
         <td>${role[2]}</td>
         <td>
         	<a href="editrole?editRoleId=${role[0]}">编辑&nbsp;&nbsp;&nbsp;</a>
         	<a href="deleteRole?roleId=${role[0]}" onclick="return delcfm();">&nbsp;&nbsp;&nbsp;删除</a>
         </td>
    </tr>
 	</c:forEach>
   </table>

</body>
</html>