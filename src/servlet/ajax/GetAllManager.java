package servlet.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import po.Emp;
import service.EmpService;

public class GetAllManager extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		List<Emp> emps=new EmpService().getAllManagers();
		JSONArray jsonArray=JSONArray.fromObject(emps);
		System.out.println(emps.size());
		PrintWriter out=resp.getWriter();
		out.println(jsonArray);
		//System.out.println(count+"GetLogCountAjax");
		out.flush();
	}

}
