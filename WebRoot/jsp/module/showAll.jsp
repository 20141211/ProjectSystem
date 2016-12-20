<%@page import="po.Module"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		var names=["id","name","projectName","foundTime","priority"];
		action="module!edit.action";
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

     <table class="datatable">
     	<tr><td></td><td>模块名称</td><td>项目名称</td><td>创建时间</td><td>优先级</td><td>操作</td><tr>
     	<s:iterator value="modules" id="module" >
     		<tr>
     			<td style="visibility:collapse"><s:property value="#module.id"/></td>
     			<td><s:property value="#module.name"/></td>
     			<td><s:property value="#module.projectName"/></td>
     			<td><s:property value="#module.foundTime"/></td>
     			<td><s:property value="#module.priority"/></td>
     			<td><a href="module_remove.action?id=${module.id }">删除|</a><button name="edit">修改</button></td>
     		</tr>
     	</s:iterator>
		<tr>
    		<form action="module_add.action">
    			<td></td>
    			<td><input type="text" name="name"></td>
    			<td><input type="text" name="projectName"></td>
    			
    			<td><input type="text" name="foundTime"></td>
    			<td><input type="text" name="priority"></td>
    			<td><input type="submit" value="添加"/></td>
    		
    		</form>
    	</tr>
     </table>
  </body>
</html>
