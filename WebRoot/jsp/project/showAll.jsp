<%@page import="po.Project"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<script src="js/myjs/edit.js"></script>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showAll.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/table/table.css" rel="stylesheet" type="text/css" />
	
	<script>
		//驱动的创建
		function getXmlHttp(){
	    	var xmlHttp;
	    	try{
	    	    xmlHttp=new XMLHttpRequest();
	    	}catch (e){
	    	   try{
	    	      xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	    	   }catch (e) {
	    	      try {
	    	         xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
	    	      }catch (e){
	    	         alert("您的浏览器不支持AJAX！");
	    	         return false;
	    	      }
	    	   }
	    	}
	    	return xmlHttp;
		}
		var xmlHttp=getXmlHttp();
		var names=["id","name","clientName","managerName","count","startTime","updateTime","priority","state"];
		var action="project!edit.action";
		window.onload=function(){
			manager=document.getElementById("manager");
			manager.onchange=function(){
			};
			manager.onmousedown=function(){
				this.options[0].innerHTML="没有请去人员系统添加";
				var url="GetAllManager";
				xmlHttp.open("get",url,true);
				xmlHttp.onreadystatechange=function(){
					if(xmlHttp.readyState==4){
						var json=eval("("+xmlHttp.responseText+")");
						manager.options.length=json.length+1;
						for(var i=0;i<json.length;i++){
							manager.options[i+1].value=json[i].empname;
							manager.options[i+1].innerHTML=json[i].empname;
						}
					}
				};
				xmlHttp.send();
			};
			
			
			var edits=document.getElementsByName("edit");
	  		for(var i=0;i<edits.length;i++){
	  			//var edit=edits[i];
	  			edits[i].onmousedown=function(){
		  			editfun(this,names,action);
		  			
	  			};
	  		};
	  		
	  		
	  		
		};
	
	</script>
  </head>
  
  <body>
  	<form action="project!add.action">
  	<table class="datatable">
    	<tr><td></td><td>项目名称</td><td>客户名称</td><td>项目经理</td><td>开发人数</td><td>立项时间</td><td>最近更新时间</td>
    		<td>任务优先级</td><td>状态</td><td>操作</td>
    	</tr>
    	<c:forEach items="${list}" var="v">
    		<tr>
			<td style="visibility:collapse">${v.id }</td>
    		<td>${v.name}</td>
    		<td>${v.clientName}</td>
    		<td>${v.managerName}</td>
    		<td>${v.count}</td>
    		<td>${v.startTime}</td>
    		<td>${v.updateTime}</td>
    		<td>${v.priority}</td>
    		<td>${v.state}</td>
    		<td><a href="project!delete.action?id=${v.id }">删除|</a><button name="edit">修改</button></td>
    	</tr>
    	</c:forEach>
    	<tr>
    		
    			<td></td>
    			<td><input type="text" name="name"></td>
    			<td><input type="text" name="clientName"></td>
    			<td>
    			<select name="managerName" id="manager">
    				<option disabled="disabled" selected="selected" >三击进行选择</option>
    				
    			</select>
    			
    			</td>
    			<td>暂时不用填写</td>
    			<td><input type="text" name="startTime"></td>
    			<td><input type="text" name="updateTime"></td>
    			<td><input type="text" name="priority"></td>
    			<td><input type="text" name="state"></td>
    			<td><input type="submit" value="添加"/></td>
    		
    		
    	</tr>
    </table>
    </form>
  </body>
</html>
