package servlet.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import po.Emp;
import service.EmpService;

public class SelectPositionServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//根据posiid找employee
		EmpService service=new EmpService();
		resp.setCharacterEncoding("utf-8");
		//System.out.println(req.getParameter("posiid"));
		int posiid=Integer.parseInt(req.getParameter("posiid"));
		List<Emp> list=service.getPriorByPosiid(posiid);
		JSONArray json=JSONArray.fromObject(list);
		//System.out.println(json);
		PrintWriter out=resp.getWriter();
		out.print(json);
		out.flush();
	}

}
