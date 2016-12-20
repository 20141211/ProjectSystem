package action;

import java.util.List;
import com.opensymphony.xwork2.ModelDriven;
import po.Module;
import service.ModuleService;

public class ModuleAction implements ModelDriven<Module>{
	ModuleService service=new ModuleService();
	private Module module=new Module();
	private List<Module> modules;
	public List<Module> getModules(){
		return this.modules;
	}
	//show all
	public String showAll(){
		modules=service.getAll();
		return "go";
	}
	//add
	public String add(){
		//目前还没添加需求id的接口
		module.setDemandId(1211);
		service.add(module);
		return "add";
	}
	//remove
	public String remove(){
		service.remove(module.getId());
		return "remove";
	}
	//edit
	public String edit(){
		service.remove(module.getId());
		module.setDemandId(1211);
		System.out.println(module.getName());
		service.add(module);
		return "edit";
	}
	@Override
	public Module getModel() {
		// TODO Auto-generated method stub
		return module;
	}
}
