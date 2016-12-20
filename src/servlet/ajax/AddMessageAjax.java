package servlet.ajax;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xml.internal.serializer.utils.Messages;

import po.Message;
import service.MessageService;
import servlet.ParameterUtil;

public class AddMessageAjax extends HttpServlet{
	MessageService messageService=new MessageService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		//System.out.println(111);
		int senderId=(Integer)request.getSession().getAttribute("empno");
		//System.out.println(senderId);
		String receiverId=request.getParameter("receiverId");
		String message=request.getParameter("message");
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String time=format.format(calendar.getTime());
		Message messageInstance=new Message();
		messageInstance.setMessage(message);
		messageInstance.setReceiverId(Integer.parseInt(receiverId));
		messageInstance.setSenderId(senderId);
		messageInstance.setTime(time);
		messageService.add(messageInstance);
	}
	

}
