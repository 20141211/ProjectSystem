package action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sun.xml.internal.bind.v2.model.core.ID;

import po.Client;

import service.ClientService;
import servlet.ParameterUtil;


public class ClientAction {
	private ClientService service=new ClientService();
	
	
	//showAll
	public String showAll(){
		HttpServletRequest request=ParameterUtil.getRequest();//û����,meimaobing
		List<Client> list=service.getAll();
		request.setAttribute("list", list);
		return "/jsp/client/client.jsp";
	}
	//showByPage
	public String showByPage(){
		HttpServletRequest request=ParameterUtil.getRequest();
		int page=Integer.parseInt(request.getParameter("page"));
		List<Client> list=service.getClientsByPage(page);
		request.setAttribute("list", list);//�᷵��11����ݣ����һ����ݵ�num����ҳ��
		return "/jsp/client/client.jsp";
	}
	//remove
	public String remove(){
		HttpServletRequest request=ParameterUtil.getRequest();
		int page=(Integer)request.getSession().getAttribute("page");
		//System.out.println(page);
		int id=Integer.parseInt(request.getParameter("id"));
		service.remove(id);
		return "ClientAction.do?method=showByPage&page="+page;
	}
	//add
	public String add(){
		HttpServletRequest request=ParameterUtil.getRequest();
		
		int page=(Integer)request.getSession().getAttribute("page");
		Client client=new Client();
		client.setId(Integer.parseInt(request.getParameter("id")));
		client.setLinkman(request.getParameter("linkman"));
		client.setFirmName(request.getParameter("firmname"));
		System.out.println(request.getParameter("firmname"));//
		client.setFirmSumm(request.getParameter("firmsumm"));
		client.setLinkPhone(request.getParameter("linkphone"));
		client.setCoCount(Integer.parseInt(request.getParameter("cocount")));
		service.add(client);
		return "/ClientAction.do?method=showByPage&page="+page;
	}
	
	//edit
	public String edit() throws UnsupportedEncodingException{
		HttpServletRequest request=ParameterUtil.getRequest();
		//request.setCharacterEncoding("UTF-8");--------------
		Client client=new Client();
		client.setId(Integer.parseInt(request.getParameter("id")));
		client.setLinkman(request.getParameter("linkman"));
		client.setFirmName(request.getParameter("firmname"));
		client.setFirmSumm(request.getParameter("firmsumm"));
		client.setLinkPhone(request.getParameter("linkphone"));
		client.setCoCount(Integer.parseInt(request.getParameter("cocount")));
		service.edit(client);
		return "/ClientAction.do?method=showByPage&page=1";
	}
}
