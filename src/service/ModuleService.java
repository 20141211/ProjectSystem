package service;

import java.util.List;

import po.Module;

import dao.ModuleDao;

public class ModuleService {
	ModuleDao dao=new ModuleDao();
	//get all
	public List<Module> getAll(){
		return dao.selectAll();
	}
	//add
	public void add(Module module){
		dao.insert(module);
	}
	//remove
	public void remove(int id){
		dao.delete(id);
	}
}
