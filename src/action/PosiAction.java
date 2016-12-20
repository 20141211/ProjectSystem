package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import po.Position;

import service.PositionService;
import servlet.ParameterUtil;

public class PosiAction {
	PositionService service=new PositionService();
	private HttpServletRequest request=ParameterUtil.getRequest();
	public String showAll(){
		List<Position> list=service.get();
		request.setAttribute("list", list);
		return "/jsp/position/position.jsp";
	}
}
