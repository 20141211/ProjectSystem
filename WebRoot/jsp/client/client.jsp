<%@page import="service.ClientService"%>
<%@page import="sun.print.resources.serviceui"%>
<%@page import="po.Client"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <link href="css/table/table.css" rel="stylesheet" type="text/css" />
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>My JSP 'client.jsp' starting page</title>
    <link href="css/style_main.css" rel="stylesheet" type="text/css" />
	<!-- <meta http-equiv="pragma" content="no-cache; charset=utf-8"> -->
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
 <!--  <script src="js/myjs/edit.js"></script> -->
  <script>
  	//action="<%=path%>"+"/ClientAction.do?method=edit";
  	//var names=["id","firmname","linkman","linkphone","firmsumm","cocount"];
  	window.onload=function(){
  		//alert(1);
  		var edits=document.getElementsByName("edit");
  		for(var i=0;i<edits.length;i++){
  			//var edit=edits[i];
  			edits[i].onmousedown=function(){
	  			editfun(this);
	  			//return false;
  			};
  			
  		};
  		
  		function editfun(edit){
  			var td=edit.parentNode;
  			var tr=td.parentNode;
  			//alert(tr.cells)
  			//添加一个form表单，提交form表单
  			var form=document.createElement("form");
  			edit.style.visibility="collapse";
  			var submit=document.createElement("input");
  			submit.type="submit";
  			submit.value="确认修改";
  			form.appendChild(submit);
  			form.action="<%=path%>"+"/ClientAction.do?method=edit";
  			form.method="post";
  			//action="<%=path%>"+"/ClientAction.do?method=edit";
  			var names=["id","firmname","linkman","linkphone","firmsumm","cocount"];
  			var i;
  			
  			for(i=1;i<tr.cells.length-1;i++){
  				var input=document.createElement("input");
  				input.type="text";
  				input.value=tr.cells[i].innerHTML;
  				//alert(input.value)
  				input.id=i;
  				input.name=names[i-1];
  				tr.cells[i].innerHTML="";
  				tr.cells[i].appendChild(input);
  				var inputClone=input.cloneNode(true);
  				inputClone.type="hidden";
  				
  				input.onblur=function(){
  					var i=document.getElementsByName(this.name);
  					i[1].value=this.value;
  				};
  				form.appendChild(inputClone);
  			}
			td.innerHTML="";
  			td.appendChild(form);  			
  		}
  		
  		
  		
  		var curPage=document.getElementById("curPage");
  		var totalPages=document.getElementById("totalPages");
  		var last=document.getElementById("last");
  		if(curPage.innerHTML==1){
  			var newFirst=document.createElement("font");
  			newFirst.innerHTML="上一页";
  			newFirst.color="grey";
  			var parent=last.parentNode;
  			parent.replaceChild(newFirst.cloneNode(true),last);  			
  		};
  		
  		var next=document.getElementById("next");
  		
  		if(curPage.innerHTML==totalPages.innerHTML){
  			var newNext=document.createElement("font");
  			newNext.innerHTML="下一页";
  			newNext.color="grey";
  			var parent=next.parentNode;
  			parent.replaceChild(newNext.cloneNode(true),next);
  		};
  	};
  </script>
  
  <body>
  	<form action="<%=path %>/ClientAction.do?method=add" method="post">
	    <table border=1 width=80% align="center"  class="datatable">
	    	<tr>
	    		<td></td>
	    		<td align="center" class="back_main03">编号</td>
	    		<td align="center" class="back_main03">企业名称</td><td align="center" class="back_main03">联系人</td><td align="center" class="back_main03">联系电话</td><td align="center" class="back_main03">摘要</td><td align="center" class="back_main03">合作次数</td><td align="center" class="back_main03">操作</td>		
	    	</tr>
	    	
	    		<%
	    			List<Client> list=(List<Client>) request.getAttribute("list");
	    			int numberOfClient=ClientService.numberOfClients;
	    			int totalPages=0;
	    			int totalClients=0;
	    			for(Client client:list){
	    				if(client.getCoCount()==-1){
	    					totalClients=client.getId();//借用id字段传递实时的totalclients
	    					totalPages=(int)Math.ceil((double)totalClients/numberOfClient);
	    					//System.out.println(totalClients+" "+numberOfClient);
	    					break;
	    				}
	    		%>
	    		<tr align="center">
	    			<td><input type="radio"/></td>
	    			<td align="center" class="back_main03"><%=client.getId() %></td>
	    			<td align="center" class="back_main03"><%=client.getFirmName() %></td>
	    			<td align="center" class="back_main03"><%=client.getLinkman() %></td>
	    			<td align="center" class="back_main03"><%=client.getLinkPhone() %></td>
	    			<td align="center" class="back_main03"><%=client.getFirmSumm() %></td>
	    			<td align="center" class="back_main03"><%=client.getCoCount() %></td>
	    			<td align="center" class="back_main03" width="200px">
	    				<a href="ClientAction.do?method=remove&id=<%=client.getId()%>" id="delete">删除</a>|<button name="edit">修改</button>
	    			</td>
	    		</tr>
	    		<%
	    			}
	    		 %>
	    		 <tr>
	    		 
	    		 	<td ><input type="hidden" name="method" value="add"></td>
	    		 	<td ><input type="text" name="id" value=<%=totalClients+1 %>></td>
	    		 	<td ><input type="text" name="firmname" value="firmname"></td>
	    		 	<td ><input type="text" name="linkman" value="linkman"></td>
	    		 	<td ><input type="text" name="linkphone" value="linkphone"></td>
	    		 	<td ><input type="text" name="firmsumm" value="firmsumm"></td>
	    		 	<td ><input type="text" name="cocount" value=<%=totalClients+1 %>></td>
	    		 	<td align="center" class="back_main03"><input type="submit" value="添加"></td>
	    		 
	    		 </tr>
	    		 <tr>
	    		 	<td colspan=8>
	    		 		<%
	    		 			int curPage=Integer.parseInt(request.getParameter("page"));
	    		 			session.setAttribute("page", curPage);
	    		 			int begin=(curPage-1)/5*5+1;
	    		 			int end=begin+9;
	    		 		 %>
	    		 		共 
	    		 		<font size="3" color="red" id="curPage"><%=curPage %></font>/
	    		 		<font size="4" color="blue" id="totalPages"><%=totalPages %></font>
	    		 		页
	    		 		<a href="<%=path %>/ClientAction.do?method=showByPage&page=<%=1 %>">【首页】</a>
	    		 		<a href="<%=path %>/ClientAction.do?method=showByPage&page=<%=curPage-1 %>" id="last">【上一页】</a>
	    		 		<%
	    		 			for(int i=begin;i<=end;i++){
	    		 				if(i>totalPages)
	    		 					break;
	    		 			%>
		    		 			
		    		 			<a href="<%=path %>/ClientAction.do?method=showByPage&page=<%=i %>">【<%=i %>】</a>
		    		 			
	    		 			<%
	    		 			
	    		 			}
	    		 		 %>
	    		 		 <a href="<%=path %>/ClientAction.do?method=showByPage&page=<%=curPage+1 %>" id="next">【下一页】</a>
	    		 		 <a href="<%=path %>/ClientAction.do?method=showByPage&page=<%=totalPages %>">【尾页】</a>
	    		 	
	    		 	</td>
	    		 </tr>
	    	
	    </table>
	</form>
  </body>
</html>
