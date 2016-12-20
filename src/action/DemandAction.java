package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import po.Demand;
import service.DemandService;

public class DemandAction {
	DemandService service=new DemandService();
	private List<Demand> demands;
	HttpServletRequest request=ServletActionContext.getRequest();
	public List<Demand> getDemands(){
		return this.demands;
	}
	//show all
	public  String showAll(){
		demands=service.getAll();
		return "go";
	}
	//add
	public String add(){
		//name projectName foundTime updateTime 
		Demand demand=new Demand();
		demand.setFoundTime(request.getParameter("foundTime"));
		demand.setId(0);
		demand.setName(request.getParameter("name"));
		demand.setProjectId(111);//------------------------
		demand.setProjectName(request.getParameter("projectName"));
		demand.setUpdateTime(request.getParameter("updateTime"));
		service.add(demand);
		return "add";
	}
	//remove
	public String remove(){
		service.remove(Integer.parseInt(request.getParameter("id")));
		return "remove";
	}
	//edit
	public String edit(){
		Demand demand=new Demand();
		demand.setFoundTime(request.getParameter("foundTime"));
		demand.setId(Integer.parseInt(request.getParameter("id")));
		demand.setName(request.getParameter("name"));
		demand.setProjectId(111);//------------------------
		demand.setProjectName(request.getParameter("projectName"));
		demand.setUpdateTime(request.getParameter("updateTime"));
		service.remove(demand.getId());
		service.add(demand);
		return "edit";
	}
}
