package service;

import java.util.List;

import dao.DemandDao;
import po.Demand;

public class DemandService {
	private DemandDao dao=new DemandDao();
	//get all
	public List<Demand> getAll(){
		return dao.selectAll();
	}
	//add
	public void add(Demand demand){
		dao.insert(demand);
	}
	//remove
	public void remove(int id){
		dao.delete(id);
	}
}
