<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb18030" />
<title>项目管理系统 by www.eyingda.com</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<link href="http://localhost:8080/ProjectSystem/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="http://localhost:8080/ProjectSystem/jquery.js"></script>

<script type="text/javascript">
	
	$(document).ready(function (){
		//alert("nice");
		$("#dengru").click(function(){
			//alert("hello");
			var username=$("#textfield").val();
			var password=$("#textfield2").val();
			var yanzheng=$("#textfield3").val();
			if(username ==""){
				alert("请输入用户名！");
				return;
			}
			if(password ==""){
				alert("请输入密码！");
				return;
			}
			if(yanzheng==""){
				alert("请输入验证码！");
				return;
			}
			$("#fom").attr("action","http://localhost:8080/ProjectSystem/EmpAction.do?method=login");
		});
	});
</script>
<script>

	$(document).ready(function (){
		$("#lin").click(function (){
			
			$("#imag").attr("src","<%=basePath %>CheckCodeServlet?haha="+Math.random());
		});
	});
	
</script>
<script>
	var flag=${param.flag};
	if(flag==1){
		flag=0;
		alert("验证码输入错误！");
	}
	if(flag==2){
		flag=0;
		alert("用户名或密码错误")
	}
</script>
</head>

<body>
<form  method='post' name='form1' id='fom' action="EmpAction.do?method=login">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="147" background="http://localhost:8080/ProjectSystem/images/top02.gif"><img src="http://localhost:8080/ProjectSystem/images/top03.gif" width="776" height="147" /></td>
  </tr>
</table>
<table width="562" border="0" align="center" cellpadding="0" cellspacing="0" class="right-table03">
  <tr>
    <td width="221"><table width="95%" border="0" cellpadding="0" cellspacing="0" class="login-text01">
      
      <tr>
        <td><table width="100%" border="0" cellpadding="0" cellspacing="0" class="login-text01">
          <tr>
            <td align="center"><img src="http://localhost:8080/ProjectSystem/images/ico13.gif" width="107" height="97" /></td>
          </tr>
          <tr>
            <td height="40" align="center">&nbsp;</td>
          </tr>
          
        </table></td>
        <td><img src="http://localhost:8080/ProjectSystem/images/line01.gif" width="5" height="292" /></td>
      </tr>
    </table></td>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="31%" height="35" class="login-text02">用户名称：<br /></td>
        <td width="69%"><input name="username" id='textfield' type="text" size="30" /></td>
      </tr>
      <tr>
        <td height="35" class="login-text02">密　码：<br /></td>
        <td><input name="password" type="password" id='textfield2' size="33" /></td>
      </tr>
      <tr>
        <td height="35" class="login-text02">验证图片：<br /></td>
        <td><img id='imag' src="http://localhost:8080/ProjectSystem/CheckCodeServlet" width="109" height="40" /> <a href="#" id='lin' class="login-text03">看不清楚，换张图片</a></td>
      </tr>
      <tr>
        <td height="35" class="login-text02">请输入验证码：</td>
        <td><input id='textfield3' name="checkCode" type="text" size="30" /></td>
      </tr>
      <tr>
        <td height="35">&nbsp;</td>
        <td><input id="dengru" type="submit" class="right-button01" value="确认登陆"  />
          <input name="Submit232" type="button" class="right-button02" value="重 置" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>