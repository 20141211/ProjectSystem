<%@page import="po.Position"%>
<%@ page language="java" import="java.util.*,po.Emp" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showAll.jsp' starting page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	

<link href="css/table/table.css" rel="stylesheet" type="text/css" />
	<script>
	window.onload=function(){
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
		
		//获取posiid
		var posiid=false;
		var select=document.getElementById("posiid");
		select.onchange=function(){
			var posiid=this.options[this.selectedIndex].value;
			//alert(posiid);
			//alert(xmlHttp);
			var url="SelectPositionServlet?posiid="+posiid;
			//alert(url);
			xmlHttp.open("get",url,true);
			//alert(1);
			xmlHttp.onreadystatechange=function(){
				//alert(2);
				if(xmlHttp.readyState==4){
					
					var json=eval("("+xmlHttp.responseText+")");
					//alert(json[0].id);
					//alert(json[0].empname);
					
					prior=document.getElementById("prior");
					prior.options.length=json.length+1;
					//prior.options[0].value="选择上司";
					prior.options[0].innerHTML="选择上司";
					prior.options[0].disabled=true;
					prior.options[0].selected="selected";
					prior.onclick=function(){
						prior.options[0].innerHTML="上司有：";
					};
					//alert(prior.options.length);<option disabled="true" selected="selected">选择职位</option>
					for (var i = 0;i < json.length;i++){
						
						prior.options[i+1].value=json[i].id;
						prior.options[i+1].innerHTML=json[i].empname;
						if(prior.options[i+1].value==0){
							prior.options[i+1].innerHTML="你是爹，你没上司";
						}
						//alert(json[i].id)
						//alert(json[i].empname)
					}
					
				}
			};
			xmlHttp.send();
			
		};
	};
	</script>
	
  </head>
  
  <body>
  	<table class="datatable">
  		<tr><td >id</td> <td>职位id</td> <td>上司</td> <td width="40px">员工编号</td>  <td>姓名</td>  <td>身份证号</td>
  			 <td>性别</td> <td>年龄</td> <td>邮件</td><td>电话</td> <td>月薪</td> <td>账户金额</td> <td>操作</td>
  		</tr>
    <%
    //员工表（id,职位id，上司id，员工编号,密码,姓名，员工类型，身份证号，性别，年龄，电子邮件，联系电话，月薪,账户）
    //ps_employee(id,posiid,priorid,empno,password,empname,emptype,idCard,sex,age,email,phone,salary,account)
    	List<Emp> list=(List<Emp>)request.getAttribute("list");
    	List<Position>positions=(List<Position>)request.getAttribute("positions");
    	int total=0;
    	int maxid=0;
    	for(Emp emp:list){
    		if(emp.getPosiid()==-1){
    			total=emp.getId();
    			maxid=emp.getEmpno();
    			continue;
    		}
    %>
    	<tr>
    		<td><%=emp.getId() %></td>
    		<td>
    		<%
    			for(Position position:positions){
    				if(position.getId()==emp.getPosiid()){
    					out.print(position.getPosiname());
    				}
    				
    			}
    		 %>
    		</td>
    		
    		<td>
    			<%
    				for(Emp employee:list){
    					if(employee.getId()==emp.getPriorid()){
    						out.println(employee.getEmpname());
    					}
    				}
    			 %>
				
			</td>
    		
    		<td width="40px"><%=emp.getEmpno() %></td>
    		<%-- <td><%=emp.getPassword() %></td> --%>
    		<td><%=emp.getEmpname() %></td> 	
    		<td><%=emp.getIdCard() %></td>
    		<td><%=emp.getSex() %></td>
    		<td><%=emp.getAge() %></td>
    		<td><%=emp.getEmail() %></td>
    		<td><%=emp.getPhone() %></td>
    		<td ><%=emp.getSalary() %></td>
    		<td><%=emp.getAccount() %></td>
    		<td>
    			<a href="<%=path%>/EmpAction.do?method=remove&id=<%=emp.getId()%>">删除</a>
    		</td>
    	</tr>
    <%	
    	}
    	
     %>
     
     
     	<tr>
     		<form action="<%=path %>/EmpAction.do?method=add" method="post">
     			<td><input type="hidden" name="id" value="<%=maxid%>"><%=maxid+1 %></td>
     			<td>
	     			<select name="posiid" id="posiid">
	     			
	     			<option disabled="true" selected="selected">选择职位</option>
					<%
						
						for(Position position:positions){
					%>
						<option value=<%=position.getId() %> id="option"><%=position.getPosiname() %></option>
					<%
						}//ps_employee(id,posiid,priorid,empno,password,empname,emptype,idCard,sex,age,email,phone,salary,account)
					 %>
					</select>
				</td>
				<td>
					<select name="priorid" id="prior">
						
					</select>
				</td>
				<td width="40px"><input type="text" name="empno" style="width:120px"></td>
				<input type="hidden" name="password" value="1">
				<td><input type="text" name="empname" style="width:50px"></td>
				<td><input type="text" name="idcard" style="width:140px"></td>
				<td>
					<select name="sex">
						<option value="男">男</option>
						<option value="女">女</option>
					</select>
				</td>
				<td><input type="text" name="age" style="width:30px"></td>
				<td><input type="text" name="email" style="width:140px"></td>
				<td><input type="text" name="phone" style="width:100px"></td>
				<td><input type="text" name="salary" style="width:100px"></td>
				<td><input type="text" name="account" style="width:100px"></td>
				<td><input type="submit" value="添加"></td>
     		</form>
     	</tr>
     	
     </table>
  </body>
</html>
