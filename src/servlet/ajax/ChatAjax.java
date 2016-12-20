package servlet.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.taglibs.standard.lang.jstl.Literal;

import po.Message;

import service.MessageService;
import sun.awt.RepaintArea;

public class ChatAjax extends HttpServlet{
	MessageService service=new MessageService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		
		String receiverId=req.getParameter("receiverId");
		String senderId=req.getParameter("senderId");
		//System.out.println(receiverId+"  " +senderId);
		
		List<Message> list=service.get(Integer.parseInt(senderId), Integer.parseInt(receiverId));
		JSONArray jsonArray=JSONArray.fromObject(list);
		
		
		PrintWriter out=resp.getWriter();
		out.println(jsonArray);//和print区别
		
		System.out.println(jsonArray);
		out.flush();
	}

}
