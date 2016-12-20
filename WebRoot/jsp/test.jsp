<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <script>
  	window.onload=function(){
  		var text=document.getElementById("text");
  		text.onclick=function(){
  			alert(1);
  		};
  		var td=document.getElementById("td");
  		td.onclick=function(){
  			text.value="12324";
  			text.style.width="50";
  		};
  	};
  
  </script>
  
  <body>
    <form >
    	<table border=1>
    		<tr><td>列1</td><td>列2</td></tr>
    		<tr>
    			<td ><input type="text" id="text" style="width:100%"></td>
    			<td id="td"><input type="text" name="t"></td>
    		</tr>
    		<tr>
				<td><input type="text" name="t"></td>
    			<td><input type="text" name="t"></td>
			</tr>
    	</table>
    </form>
  </body>
</html>
