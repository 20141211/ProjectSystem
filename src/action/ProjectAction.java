package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import po.Project;
import service.ProjectService;

public class ProjectAction {
	private ProjectService service=new ProjectService();
	private List<Project> list;
	private Project project;
	private HttpServletRequest request=ServletActionContext.getRequest();
	
	public List<Project> getList() {
		return list;
	}
	public Project getProject(){
		return project;
	}
	public void setProject(Project project){
		this.project=project;
	}
	//show all
	public String showAll(){
		list=service.getList();
		return "go";
	}
	//add
	public String add(){
		//System.out.println(project.getManagerName());
		//project.setClientId(Integer.parseInt(request.getParameter("clientId")));
		Project project=new Project();
		project.setClientId(11);//----------------------------
		project.setClientName(request.getParameter("clientName"));
		project.setCount(0);
		project.setManagerName(request.getParameter("managerName"));
		project.setName(request.getParameter("name"));
		project.setPriority(request.getParameter("priority"));
		project.setStartTime(request.getParameter("startTime"));
		project.setState(request.getParameter("state"));
		project.setUpdateTime(request.getParameter("updateTime"));
		service.add(project);
		return "add";
	}
	//delete
	public String delete(){
		service.remove(Integer.parseInt(request.getParameter("id")));
		return "delete";
	}
	//edit
	public String edit(){
		Project project=new Project();
		project.setClientId(11);//----------------------------
		project.setClientName(request.getParameter("clientName"));
		project.setId(Integer.parseInt(request.getParameter("id")));
		project.setCount(0);
		project.setManagerName(request.getParameter("managerName"));
		project.setName(request.getParameter("name"));
		project.setPriority(request.getParameter("priority"));
		project.setStartTime(request.getParameter("startTime"));
		project.setState(request.getParameter("state"));
		project.setUpdateTime(request.getParameter("updateTime"));
		//service.edit(project);
		service.remove(project.getId());
		service.add(project);
		return "edit";
	}
}
