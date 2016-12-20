<%@page import="po.Demand"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		var names=["id","name","projectName","foundTime","updateTime"];
		var action="Demand!edit.action";
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
    <form action="Demand!add.action">
     <table class="datatable">
     	<tr><td></td><td>需求主题</td><td>所属项目</td><td>创建时间</td><td>更新时间</td><td>操作</td></tr>

   		<c:forEach items="${demands }" var="demand">
   			<tr>
   				<td style="visibility:collapse">${demand.id}</td>
	   			<td>${demand.name}</td>
	   			<td>${demand.projectName }</td>
	   			<td>${demand.foundTime }</td>
	   			<td>${demand.updateTime }</td>
	   			<td><a href="Demand!remove.action?id=${demand.id }">删除|</a><button name="edit">修改</button></td>
   			</tr>
   		</c:forEach>
		<tr>
    		
    			<td></td>
    			<td><input type="text" name="name"></td>
    			<td><input type="text" name="projectName"></td>
    			
    			<td><input type="text" name="foundTime"></td>
    			<td><input type="text" name="updateTime"></td>
    			<td><input type="submit" value="添加"/></td>
    		
    		
    	</tr>
     	
     </table>
   </form>
  </body>
</html>
