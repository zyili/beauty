<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>角色添加</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <link rel="stylesheet" href="../../css/demo.css" type="text/css">
	<link rel="stylesheet" href="../../css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="../../js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="../../js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="../../js/jquery.ztree.excheck-3.5.js"></script>
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
    <script type="text/javascript">
	var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onCheck: onCheck
			}
		};
		var menus=${menus};
		var zNodes =eval(menus);
		var code;
		function setCheck() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var type = { "Y":ps, "N":ps};
			zTree.setting.check.chkboxType = type;
		}
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			setCheck();
		});
		function onCheck(e, treeId, treeNode) {
			var meuns =$.fn.zTree.getZTreeObj("treeDemo").getCheckedNodes(true);
		    var last="[";
		    for(var i=0;i<meuns.length;i++)
		    {
		    	if(i==0)
		    	{
		    		last+="{id:\""+meuns[i]["id"]+"\",name:\""+meuns[i]["name"]+"\"}";
		    	}else
		    	{
		    		last+=",{id:\""+meuns[i]["id"]+"\",name:\""+meuns[i]["name"]+"\"}";
		    	}
		    }
		    last+="]";
		    document.getElementById("rmenus").value=last;
		}
		function setMenus()
		{
			 var jsonList =$.fn.zTree.getZTreeObj("treeDemo").getCheckedNodes(true);
			// document.getElementById("rolemenus").value=jsonList;
			 return true;
		}
		function Back()
		{
			if (confirm("您确定要返回吗？"))
			window.location.href="role.jsp";
		}
</script>
</head>
<body>
<form action="rolemenus"  onsubmit="return setMenus();" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">角色名称</td>
            <td><input type="text" name="title"/></td>
        </tr>
        <tr>
            <td class="tableleft">状态</td>
            <td>
                <input type="radio" name="status" value="1" checked/> 启用  <input type="radio" name="status" value="0"/> 禁用
            </td>
        </tr>
        <tr>
            <td class="tableleft">权限</td>
            <td>
                <div class="zTreeDemoBackground left">
                	<ul id="treeDemo" class="ztree">
                	</ul>
                </div>
            </td>
        </tr>
        <tr>
            <td class="tableleft">
            	<input type="hidden" id="rmenus" name="rmenus" />
            </td>
            <td>
                <button type="submit" id="savemenus" name="savemenus" class="btn btn-primary" type="button">保存</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" onclick="Back()" name="backid" id="backid">返回列表</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>