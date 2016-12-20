package service;

import java.util.List;

import po.Emp;
import servlet.ParameterUtil;
import sun.awt.geom.AreaOp.AddOp;
import dao.EmpDao;

public class EmpService {
	public static int numberOfPerPage=10;
	private EmpDao dao=new EmpDao();
	//login
	//登录是高频率操作，emp表有14个列，但登录的查询操作只用了两个列的内容，所以这部分表的设计有必要单提出来；空间换时间；eg建立视图
	public boolean login(int empno,String password){
		Emp emp=dao.select(empno);
		ParameterUtil.set(emp);
		if(password.equals(emp.getPassword())){
			return true;
		}
		return false;		
	}
	//select all
	public List<Emp> getAll(){
		return dao.select();
	}
	//select by posiid
	public List<Emp> getByPosiid(int posiid){
		return dao.selectByPosiid(posiid);
	}
	//select prior by posiid
	public List<Emp> getPriorByPosiid(int posiid){
		return dao.selectPriorByPosiid(posiid);
	}
	//get all manager
	public List<Emp> getAllManagers(){
		return dao.selectAllManager();
	}
	//get by page
	public List<Emp> getByPage(int page){
		int end=numberOfPerPage*page;
		int start=end-9;
		return dao.select(start, end);
	}
	//add emp
	public void add(Emp emp){
		dao.inert(emp);
	}
	//remove
	public void remove(int id){
		dao.delete(id);
	}
}
