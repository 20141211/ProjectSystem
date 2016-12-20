package action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;

import po.Emp;
import po.Message;
import po.Position;
import service.EmpService;
import service.MessageService;
import service.PositionService;
import servlet.ParameterUtil;
	
public class MessageAction {
	HttpServletRequest request=ParameterUtil.getRequest();
	MessageService messageService=new MessageService();
	EmpService empService=new EmpService();
	PositionService positionService=new PositionService();
	//initChatRoom
	public String initChatRoom(){
		List<Emp >emps=empService.getAll();
		List<Position>positions=positionService.get();
		request.setAttribute("emps", emps);
		request.setAttribute("positions", positions);
		return "/jsp/message/message.jsp";
	}
	//send
	public String send(){
		System.out.println(111);
		int senderId=(Integer)request.getSession().getAttribute("empno");
		System.out.println(senderId);
		String receiverId=request.getParameter("empno");
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
		System.out.println(123);
		return "MessageAction.do?method=initChatRoom";
	}
}
