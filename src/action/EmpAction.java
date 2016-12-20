package action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.bcel.internal.generic.NEW;

import po.Emp;
import service.EmpService;
import service.PositionService;
import servlet.ParameterUtil;
import servlet.ReqAndResp;

public class EmpAction {
	private EmpService service=new EmpService();
	private HttpServletRequest request=ParameterUtil.getRequest();//request有必要成为属性
	PositionService positionService=new PositionService();
	//login
	public String login(){
		
		ReqAndResp reqResp=(ReqAndResp)ParameterUtil.get();
		//HttpServletRequest request=reqResp.getReq();
		
		HttpSession session=request.getSession();
		session.setAttribute("token",System.currentTimeMillis());
		System.out.println(session.getAttribute("checkCode")+"-----------------------------------------");
		System.out.println(session.getAttribute("empno")+"-----------------------------------------");
		String checkCode=(String) session.getAttribute("checkCode");
		if(!checkCode.equals(request.getParameter("checkCode"))){
			return "/login.jsp?flag=1";
		}
		int empno=Integer.parseInt(request.getParameter("username"));
		String password=request.getParameter("password");
		boolean sign=service.login(empno, password);
		if(sign){
			
			Emp emp=(Emp)ParameterUtil.get();
			session.setAttribute("empname", emp.getEmpname());
			session.setAttribute("empno", empno);
			return "/index.jsp";
		}else{
			return "/login.jsp?flag=2";
		}
	}
	
	//exit
	public String exit(){
		ReqAndResp reqResp=(ReqAndResp)ParameterUtil.get();
		//HttpServletRequest request=reqResp.getReq();
		HttpSession session=request.getSession();
		session.invalidate();
		return "/login.jsp";
	}
	
	//get all
	//感觉从dao层，到service，到action，很多代码都是固话的，例如增删改查相关的一些操作每一套流程都要重写，应该可以通过什么机制来达到复用的目的
	public String showAll(){
		//HttpServletRequest request=ParameterUtil.getRequest();//request有必要成为属性
		List<Emp> list=service.getAll();
		request.setAttribute("list", list);
		request.setAttribute("positions", positionService.get());
		return "/jsp/emp/showAll.jsp";
	}
	//show by page
	public String showByPage(){
		int page=Integer.parseInt(request.getParameter("page"));
		List<Emp>list=service.getByPage(page);
		request.setAttribute("list", list);
		request.setAttribute("positions", positionService.get());//每次请求都会重复这一套流程
		return "/jsp/emp/showAll.jsp";
	}
	//showByPosi
	public String showByPosi(){
		int page=Integer.parseInt(request.getParameter("page"));
		List<Emp>list=service.getByPage(page);
		request.setAttribute("list", list);
		request.setAttribute("positions", positionService.get());
		return "/jsp/position/position.jsp";
	}
	//add //ps_employee(id,posiid,priorid,empno,password,empname,emptype,idCard,sex,age,email,phone,salary,account)
	public String add() {
		//HttpServletRequest request=ParameterUtil.getRequest();
		//request.setCharacterEncoding("utf-8");
		Emp emp=new Emp();
		emp.setId(Integer.parseInt(request.getParameter("id")));
		emp.setPosiid(Integer.parseInt(request.getParameter("posiid")));
		emp.setPriorid(Integer.parseInt(request.getParameter("priorid")));
		emp.setEmpno(Integer.parseInt(request.getParameter("empno")));
		emp.setPassword(request.getParameter("password"));
		emp.setEmpname(request.getParameter("empname"));
		emp.setIdCard(request.getParameter("idcard"));
		emp.setSex(request.getParameter("sex"));
		emp.setAge(Integer.parseInt(request.getParameter("age")));
		emp.setEmail(request.getParameter("email"));
		emp.setPhone(Long.parseLong(request.getParameter("phone")));
		emp.setSalary(Integer.parseInt(request.getParameter("salary")));
		emp.setAccount(Integer.parseInt(request.getParameter("account")));
		service.add(emp);
		return "EmpAction.do?method=showAll";
	}
	//remove
	public String remove(){
		HttpServletRequest request=ParameterUtil.getRequest();
		int id=Integer.parseInt(request.getParameter("id"));
		service.remove(id);
		return "EmpAction.do?method=showAll";
	}
}
