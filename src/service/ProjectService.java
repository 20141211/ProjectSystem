package service;

import java.util.List;

import po.Project;
import dao.ProjectDao;

public class ProjectService {
	private ProjectDao dao=new ProjectDao();
	//get all project
	public List<Project> getList(){
		return dao.select();
	}
	//add project
	public void add(Project project){
		dao.insert(project);
	}
	//remove
	public void remove(int id){
		dao.delete(id);
	}
	//edit
	public void edit(Project project){
		dao.update(project);
	}
}
