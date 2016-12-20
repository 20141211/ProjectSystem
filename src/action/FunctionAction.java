package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

import po.Function;
import service.FunctionService;
import servlet.ParameterUtil;

public class FunctionAction implements ModelDriven<Function>{
	FunctionService service=new FunctionService();
	//HttpServletRequest request=ServletActionContext.getRequest();
	private List<Function> functions;
	private Function function=new Function();
	public List<Function> getFunctions(){
		return this.functions;
	}
	//show all
	public String showAll(){
		functions=service.getAll();
		
		return "go";
	}
	//add
	public String add(){
		function.setModuleId(1);//-----------------
		service.add(function);
		return "add";
	}
	//remove
	public String remove(){
		service.remove(function.getId());
		return "remove";
	}
	//edit
	public String edit(){
		service.remove(function.getId());
		function.setModuleId(1);//----------------
		service.add(function);
		return "edit";
	}
	@Override
	public Function getModel() {
		// TODO Auto-generated method stub
		return function;
	}
	
}






