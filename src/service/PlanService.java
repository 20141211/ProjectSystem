package service;

import java.util.List;

import po.Plan;

import dao.PlanDao;

public class PlanService {
	PlanDao dao=new PlanDao();
	//get all
	public List<Plan> getAll(){
		return dao.selectAll();
	}
	//add
	public void add(Plan plan){
		dao.insert(plan);
	}
	//remove
	public void remove(int id){
		dao.delete(id);
	}
	//query
	public List<Plan> query(String sql){
		return dao.query(sql);
	}
}
