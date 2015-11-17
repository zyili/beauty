<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<script src="js/jquery-1.7.1.min.js" type="text/javascript"></script>
<link href="images/skin.css" rel="stylesheet" type="text/css">
<title>管理员登陆</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #1D3647;
}
-->
</style>
<script language="JavaScript">
function check()
{
	var name=document.getElementById('username').value;
	var password=document.getElementById('password').value;
	if(name.length>0&&password.length>0)
	{
		return true;
	}
	if(name.length===0)
	{
		alert("请输入用户名");
		return false;
	}
	if(password.length==0)
	{
		alert("请输入密码");
		return false;
	}
	return false;
}
</script>
</head>
<body>
<table width="100%" height="166" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="100" valign="top"><table width="100%" height="42" border="0" cellpadding="0" cellspacing="0" class="login_top_bg">
      <tr>
        <td width="1%" height="21">&nbsp;</td>
        <td height="42">&nbsp;</td>
        <td width="17%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td valign="top">
    <table width="100%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg">
      <tr>
        <td width="49%" align="right">
        <table width="91%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg2">
            <tr>
              <td height="138" valign="top">
              	<table width="89%" height="427" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="120">&nbsp;</td>
                </tr>
                <tr>
                  <td height="255" align="right" valign="top"><img src="images/user.png" width="255" height="255"></td>
                </tr>
                <tr>
                  <td height="52" align="right" valign="top">
                  <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="35%">&nbsp;</td>                    
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td width="30%" height="40">&nbsp;</td>
                      <td width="35%">&nbsp;</td>
                    </tr>
                  </table>
                  </td>
                </tr>
              </table></td>
            </tr>
        </table></td>
        <td width="1%" >&nbsp;</td>
        <td width="50%" valign="bottom"><table width="100%" height="59" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="4%">&nbsp;</td>
              <td width="96%" height="38"><span class="login_txt_bt">信息管理系统</span></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td height="21"><table cellSpacing="0" cellPadding="0" width="100%" border="0" id="table211" height="328">
                  <tr>
                    <td height="164" colspan="2" align="middle">
					<form  name="loginform" action="stagelogin" method="post" onsubmit="return check();">
                        <table cellSpacing="0" cellPadding="0" width="100%" border="0" height="143" id="table212">
                          <tr>
                            <td width="13%" height="38" class="top_hui_text"><span class="login_txt">管理员：&nbsp;&nbsp; </span></td>
                            <td height="38" colspan="2" class="top_hui_text" align="left">
							<input id="username" name="user.username" class="editbox4" size="20">
							<span id="msg_error" style="color:red;font-size:14px;">&nbsp;&nbsp;&nbsp;</span></td>
                          </tr>
                          <tr>
                            <td width="13%" height="35" class="top_hui_text"><span class="login_txt"> 密 码： &nbsp;&nbsp; </span></td>
                            <td height="35" colspan="2" class="top_hui_text" align="left">
							<input class="editbox4" id="password" type="password" style="size:40; width;110px;" name="user.password">
                              <img src="images/luck.gif" width="19" height="18"> </td>
                          </tr>
                         
                          <tr>
                            <td height="35" >&nbsp;</td>
                            <td width="20%" height="35" ><input name="Submit" type="submit" class="button" id="Submit"  value="登 录"> </td>
                            <td width="67%" class="top_hui_text" align="left"><input name="cs" type="button" class="button" id="cs" value="取 消" onclick="cancel()"></td>
                          </tr>
                        </table>
                        <br>
                    </form></td>
                  </tr>
                  <tr>
                    <td width="433" height="164" align="right" valign="bottom"><img src="images/login-wel.gif" width="242" height="138"></td>
                    <td width="57" align="right" valign="bottom">&nbsp;</td>
                  </tr>
              </table></td>
            </tr>
          </table>
          </td>
      </tr>
    </table>
   </td>
  </tr>
  <tr>
    <td height="100"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="login-buttom-bg">
      <tr>
        <td height="80" align="center"><span class="login-buttom-txt">Copyright &copy; 2013-2018 www.czcatv.com</span></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>