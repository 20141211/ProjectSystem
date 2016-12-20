package service;

import java.util.List;

import po.Function;
import dao.FunctionDao;

public class FunctionService {
	FunctionDao dao=new FunctionDao();
	//get all
	public List<Function> getAll(){
		return dao.selectAll();
	}
	//add
	public void add(Function function){
		dao.insert(function);
	}
	//remove
	public void remove(int id ){
		dao.delete(id);
	}
}
