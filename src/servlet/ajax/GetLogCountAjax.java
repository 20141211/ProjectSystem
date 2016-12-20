package servlet.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MessageService;

public class GetLogCountAjax extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int receiverId=Integer.parseInt(req.getParameter("receiverId"));
		int senderId=(Integer)req.getSession().getAttribute("empno");
		long count=new MessageService().getCount(senderId, receiverId);
		PrintWriter out=resp.getWriter();
		out.println(count);
		//System.out.println(count+"GetLogCountAjax");
		out.flush();
	}

}
