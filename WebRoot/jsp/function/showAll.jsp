<%@ page language="java" import="java.util.*,po.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showAll.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/table/table.css" rel="stylesheet" type="text/css" />
	<script src="js/myjs/edit.js"></script>
	<script>
		var names=["id","name","foundTime","priority"];
		action="function!edit.action";
		window.onload=function(){
			var edits=document.getElementsByName("edit");
	  		for(var i=0;i<edits.length;i++){
	  			edits[i].onmousedown=function(){
		  			editfun(this,names,action);
		  			
	  			};
	  		};
		};
	
	</script>
  </head>
  
  <body>
  	<form action="function!add.action">
     <table class="datatable">
     	<tr><td></td><td>功能名称</td><td>开始时间</td><td>优先级1</td><td>操作</td></tr>
		<s:iterator value="functions" id="function">
			<tr>
				<td style="visibility:collapse"><s:property value="#function.id"/></td>
				<td><s:property value="#function.name"/></td>
				<td><s:property value="#function.foundTime"/></td>
				<td><s:property value="#function.priority"/></td>
				<td><a href="function!remove.action?id=${function.id }">删除|</a><button name="edit">修改</button></td>
			</tr>
		</s:iterator>
     	<tr>
    		
    			<td></td>
    			<td><input type="text" name="name"></td>
    			<td><input type="text" name="foundTime"></td>
    			<td><input type="text" name="priority"></td>
    			<td><input type="submit" value="添加"/></td>
    		
    		
    	</tr>

     </table>
   </form>
  </body>
</html>
