<%@ page language="java" import="java.util.*,po.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags" %>
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
<%-- 	<script src="js/myjs/edit.js"></script> --%>
	<script>
		var names=["id","name","projectName","moduleName","funName","foundTime","earliest","latest","period","work","priority","state","delay","lastId"];
		action="plan!edit.action";
		function editfun(edit,names,action){
			var td=edit.parentNode;
			var tr=td.parentNode;
			//添加一个form表单，提交form表单
			var form=document.createElement("form");
			edit.style.visibility="collapse";
			var submit=document.createElement("input");
			submit.type="submit";
			submit.value="确认修改";
			form.appendChild(submit);
			form.action=action;
			form.method="post";
			var i;
			for(i=0;i<tr.cells.length-1;i++){
				var input=document.createElement("input");
				input.type="text";
				input.value=tr.cells[i].innerHTML;
				input.id=i;
				input.name=names[i];
				//alert(names[i]);
				input.style.width="100%";
				//alert(input.style.width)
				if(i!=0){
					tr.cells[i].innerHTML="";
					tr.cells[i].appendChild(input);
				}
				var inputClone=input.cloneNode(true);
				inputClone.type="hidden";
				
				input.onblur=function(){
					var inputs=document.getElementsByName(this.name);
					for(var i=0;i<inputs.length;i++){
						if(inputs[i].type=="hidden"){
							inputs[i].value=this.value;
						}
					}
					
				};
				form.appendChild(inputClone);
				
			}
			td.innerHTML="";
			td.appendChild(form);  			
		}
		
		
		
		window.onload=function(){
			var edits=document.getElementsByName("edit");
	  		for(var i=0;i<edits.length;i++){
	  			edits[i].onmousedown=function(){
		  			editfun(this,names,action);
		  			
	  			};
	  		};
		};
	
	</script>
	<style>
	.textwidth{width:100%}
	</style>
  </head>
  
  <body>
    <%
    	////ps_plan(id,funId,empId，name,projectName,moduleName,funName,numOfEmp,foundTime,earlist,latest,period,work,priority,state,delay,lastid)
     %>
     
     
     <div style="background:#FFE4C4;border-style: solid;border-color:red green blue pink;height:70px;">
     	<form action="plan!query.action" >
     		<span style="font-weight: bold">关键字：</span>
     		计划:<input type="text" name="name">
     		项目：<input type="text" name="projectName">
     		模块：<input type="text" name="moduleName">
     		功能：<input type="text" name="funName">
     		<br>
     		<span style="font-weight: bold">范围：&nbsp</span>
     		工期(月)：<input type="text" name="period1">-<input type="text" name="period2">
     		工作量(人月):<input type="text" name="work1">-<input type="text" name="work2">
     		创建时间：<input type="date" name="foundTime1">-<input type="date" name="foundTime2">
     		<br>
     		优先级：<input type="text" name="priority">
     		状态:<input type="text" name="state">
     		失期与否:<input type="text" name="delay">
     		紧前计划:<input type="text" name="lastPlan">
     		创建日期：<input type="date" name="foundTime">
     		
     		<input type="submit" value="提交">
     	</form>
     </div>
     
     <form action="plan!add.action" >
     <table  class="datatable">
     <tr><td></td><td>计划描述</td><td>项目</td><td>模块</td><td>功能</td><td>创建时间</td><td>最早开始时间</td><td>最晚开始时间</td><td>工期</td><td>工作量</td>
         <td>优先级</td><td>状态</td><td>是否超期</td><td>紧前计划</td><td>操作</td>
     </tr>
   	 <s:iterator value="plans" id="plan">
     <tr>
   		<td style="visibility:hidden"><s:property value="#plan.id"/></td>
   		<td><s:property value="#plan.name"/></td>
   		<td><s:property value="#plan.projectName"/></td>
   		<td><s:property value="#plan.moduleName"/></td>
   		<td><s:property value="#plan.funName"/></td>
   		<td><s:property value="#plan.foundTime"/></td>
   		<td><s:property value="#plan.earliest"/></td>
   		<td><s:property value="#plan.latest"/></td>
   		<td><s:property value="#plan.period"/></td>
   		<td><s:property value="#plan.work"/></td>
   		<td><s:property value="#plan.priority"/></td>
   		<td><s:property value="#plan.state"/></td>
   		<td><s:property value="#plan.delay"/></td>
   		<td><s:property value="#plan.lastId"/>	</td>
   		<td><a href="plan!remove.action?id=${plan.id }">删除|</a><button name="edit">修改</button></td>
   	</tr>	
   	</s:iterator>
	<tr >
   		
   			<td ></td>
   			<td ><input type="text" name="name" style="width:100%"></td>
   			<td><input type="text" name="projectName" style="width:100%"></td>
   			<td><input type="text" name="moduleName" style="width:100%"></td>
   			<td><input type="text" name="funName" style="width:100%"></td>
   			<td><input type="text" name="foundTime" style="width:100%"></td>
   			<td><input type="text" name="earliest" style="width:100%"></td>
   			<td><input type="text" name="latest" style="width:100%"></td>
   			<td><input type="text" name="period" style="width:100%"></td>
   			<td><input type="text" name="work" style="width:100%"></td>
   			<td><input type="text" name="priority" style="width:100%"></td>
   			<td><input type="text" name="state" style="width:100%"></td>
   			<td><input type="text" name="delay" style="width:100%"></td>
   			<td><input type="text" name="lastId" style="width:100%"></td>
   			<td><input type="submit" value="添加"/></td>   		
   		
    </tr>
     </table>
     </form>
  </body>
</html>
