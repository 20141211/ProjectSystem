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
	<meta http-equiv="c；ache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	

<link href="css/table/table.css" rel="stylesheet" type="text/css" />
	<script>
	var div=false;
	var receiverName="";
	var senderId=<%=session.getAttribute("empno")%>;
	var senderName="<%=session.getAttribute("empname")%>";
	var receiverId=false;
	var refresh=false;
	var logCount=0;
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
	
	//通过点击职位的分类同步更新相应的类目下的人
	function findReceiver(){
		var posiid=false;
		var select=document.getElementById("posiid");
		select.onchange=function(){
			var posiid=this.options[this.selectedIndex].value;
			var url="SelectEmpByPosition?posiid="+posiid;
			var empSelect=document.getElementById("empno");
			empSelect.options.length=1;
			div=document.getElementById("div");
			var t="";
	 		div.innerHTML=t;
	 		receiverId=false;
	 		
			xmlHttp.open("get",url,true);
			//alert(1);
			xmlHttp.onreadystatechange=function(){
				//alert(2);
				if(xmlHttp.readyState==4){
					var json=eval("("+xmlHttp.responseText+")");
					empSelect=document.getElementById("empno");
					empSelect.options.length=json.length;
					empSelect.options.length=json.length+1;
					empSelect.options[0].value=0;
					empSelect.options[0].innerHTML="请选择";
					for (var i = 0;i < json.length;i++){
						
						empSelect.options[i+1].value=json[i].id;
						empSelect.options[i+1].innerHTML=json[i].empname;
					}
					
				}
			};
			xmlHttp.send();	
		};
	
	};
		
		
		
		
	window.onload=function(){
		document.getElementById("button").onmousedown=function(){
			//console.log(<%=session.getAttribute("empname")%>)
			//alert("<%=session.getAttribute("empname")%>");
			sendMessage();
			
			//setInterval(chatWindow(),50);
			//refresh=true;
			//logCount++;
		};
		
		/* text=document.getElementById("message");
		textarea=document.getElementById("textarea");
		textarea.onblur=function(){
			text.value=textarea.value;
			//alert(text.value);
		}; */
		
		findReceiver();
		
		receiver=document.getElementById("empno");
		receiver.onchange=function(){
		 	receiverName=this.options[receiver.selectedIndex].innerHTML;
		 	receiverId=this.options[receiver.selectedIndex].value;
		 	//alert(receiverName)
			//setInterval(function(){chatWindow()},50);//套一层function才好使，为啥呢？？？,好像不套function只执行一次
		};
		setInterval(function(){timer()},1000);//觉得更新数据应该由数据库在真正更新了数据的时候去触发，而不是在客户端被动的无休止的触发
		//alert(4);先alert的4才alert timer里的5；意味着先执行完再所有方法之后再执行setInterval函数
	};
	
	
	function timer(){
		//alert(5)
		if(refresh==true){
				chatWindow();
				refresh=false;
		}else if(receiverId!=false){
		//查看聊天记录总数logCount
			newLogCount=refreshLogCount();
		//如果总数发生变化，让refresh=true并更新logCountt
			if(logCount!=newLogCount){
				refresh=true;
				logCount=newLogCount;
			}
		}
	}
	
	
	function refreshLogCount(){
		url="GetLogCountAjax?receiverId="+receiverId;
		xmlHttp.open("get",url,true);
		xmlHttp.onreadystatechange=function(){
			if(xmlHttp.readyState==4){
				//console.log(xmlHttp.responseText);
				ret=xmlHttp.responseText;
			};
		};
		xmlHttp.send();
		//alert(ret)
		return ret;
	}
	
	
	function sendMessage(){
		//alert(123);
		var textarea=document.getElementById("textarea");
		url="AddMessageAjax?receiverId="+receiverId+"&message="+textarea.value;
		xmlHttp.open("get",url,true);
		xmlHttp.onreadystatechange=function(){
			if(xmlHttp.readyState==4){
				
			}
		};
		xmlHttp.send();	
		textarea.value="";
		//alert("<%=session.getAttribute("empname")%>");
	}
		
	
		
	function chatWindow(){
		//console.log(12345)
		div=document.getElementById("div");
		//var t=new Date();
		var t="";
 		div.innerHTML=t;
 		//alert(1)
		receiver=document.getElementById("empno");
		var url="ChatAjax?receiverId="+receiverId+"&senderId="+senderId;
			xmlHttp.open("get",url,true);
			//console(url)
			
			xmlHttp.onreadystatechange=function(){
				if(xmlHttp.readyState==4){
					
					var json=eval("("+xmlHttp.responseText+")");
					//alert(json)
					for (var i = 0;i < json.length;i++){
						//alert(json[i].message);
						var name=receiverName;
						color="red";
						if(json[i].senderId==<%=session.getAttribute("empno")%>){
							name="<%=session.getAttribute("empname")%>";
							color="blue";
						}
						t+="<div style=color:"+color+">"+name+" "+json[i].time+"<div/>"+"&nbsp"+json[i].message+"<br/>";
						div.innerHTML=t;
						div.scrollTop=9999;
					}
				}
			};
			//alert(2)
			xmlHttp.send();	
			//alert(3)
			
	}
	
	</script>
	
  </head>
  
  <body>
  	<div style="width:100%;height:500px;background-color:Wheat;overflow:auto" id="div">
     			hello<br/>
    </div>
    <form action="<%=path %>/MessageAction.do?method=send" method="post">
  	<table class="datatable">
  		<tr><td style="width:10%">分组</td> <td style="width:10%">接收人</td> <td>内容</td> <td>操作</td>
  		</tr>
    <%
    //员工表（id,职位id，上司id，员工编号,密码,姓名，员工类型，身份证号，性别，年龄，电子邮件，联系电话，月薪,账户）
    //ps_employee(id,posiid,priorid,empno,password,empname,emptype,idCard,sex,age,email,phone,salary,account)
    	List<Emp> list=(List<Emp>)request.getAttribute("emps");
    	List<Position>positions=(List<Position>)request.getAttribute("positions");	
    %>
     	<tr>
     		
     			
     			<td>
	     			<select name="posiid" id="posiid" style="width:100%;height:80%">
	     			
	     			<option disabled="true" selected="selected" >按职位筛选</option>
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
					<select name="empno" id="empno" style="width:100%;height:80%">
						
					</select>
				</td>
				<td>
					
					<textarea style="width:100%;height:100px;background-color:Wheat" id="textarea"></textarea>
				</td>
				<td>
					
					<input type="button" value="ajax发送" id="button"	 style="width:50%;height:50%;border-radius: 10px;">
				</td>
				
     		
     	</tr>
     	
     </table>
   </form>
  </body>
</html>
