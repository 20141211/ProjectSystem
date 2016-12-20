package dao;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sun.corba.se.impl.orbutil.ObjectWriter;

import po.Emp;

import JDBCUtils.JDBCUtils;

public class EmpDao implements JDBCUtils.RowMapper{

	@Override
	public Object mapRow(ResultSet rs) throws SQLException  {
		//ps_employee(id,posiid,priorid,empno,password,empname,emptype,idCard,sex,age,email,phone,salary,account)
		Emp emp=new Emp();
		
		emp.setAge(rs.getInt("age"));
		emp.setEmail(rs.getString("email"));
		emp.setEmpname(rs.getString("empname"));
		emp.setEmpno(rs.getInt("empno"));
		//emp.setEmptype(rs.getString("emptype"));
		emp.setId(rs.getInt("id"));
		emp.setIdCard(rs.getString("idcard"));
		emp.setPassword(rs.getString("password"));
		emp.setPhone(rs.getLong("phone"));
		emp.setPosiid(rs.getInt("posiid"));
		emp.setPriorid(rs.getInt("priorid"));
		emp.setSex(rs.getString("sex"));
		emp.setSalary(rs.getInt("salary"));
		emp.setAccount(rs.getInt("account"));
		return emp;		
	}
	//select
	public Emp select(int empno){
		String sql="select * from ps_employee where empno=?";
		return (Emp)JDBCUtils.queryForObject(sql, new Object[]{empno}, this);
	}
	//select
	public List<Emp> select(){
		String sql="select * from ps_employee order by id";
		return JDBCUtils.queryForList(sql, this);
	}
	//select range
	public List<Emp> select(int start,int end){
		StringBuffer sql=new StringBuffer("select * from(select rownum num,t.* from (select * from ps_employee order by id)t) where num between ? and ? "); 
		sql.append("union select 0,count(*),-1,max(id),'','','',0,'',0,'',0,0,0 from ps_employee");
		return JDBCUtils.queryForList(sql.toString(), new Object[]{start,end}, this);
	}
	//select by posiid
	public List<Emp> selectByPosiid(int posiid){
		String sql="select * from ps_employee where posiid=?";
		return JDBCUtils.queryForList(sql, new Object[]{posiid}, this);
	}
	//select prior by posiid
	public List<Emp> selectPriorByPosiid(int posiid){
		String sql="select * from ps_employee where posiid in(select priorid from ps_employee where posiid=?) ";
		return JDBCUtils.queryForList(sql, new Object[]{posiid}, this);
	}
	//select all manager 项目经理
	public List<Emp> selectAllManager(){
		String sql="select * from ps_employee where posiid=2";
		return JDBCUtils.queryForList(sql, this);
	}
	
	//insert ps_employee(id,posiid,priorid,empno,password,empname,idCard,sex,age,email,phone,salary,account)
	public void inert(Emp emp){
		String sql="insert into ps_employee values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] objects=new Object[13];
		int i=0;
		for(Object object:emp.getAttributes()){
			objects[i]=object;
			i++;
		}		
		JDBCUtils.updateAll(sql,objects);
	}
	//delete
	public void delete(int id){
		String sql="delete from ps_employee where id=?";
		JDBCUtils.updateAll(sql, new Object[]{id});
	}
	
	
	
	public static void main(String args[]) throws UnsupportedEncodingException{
		/*Emp emp=new Emp();
		EmpDao dao=new EmpDao();
		dao.inert(emp);*/
		String string="好";
		/*System.out.println(new String(string.getBytes("iso-8859-1"),"utf-8"));
		System.out.println("人".getBytes("iso-8859-1").length);*/
		System.out.println(new String(string.getBytes("gbk")));
		System.out.println(string.getBytes().length);
		//String string="好";
		byte[] b=string.getBytes();
		System.out.println(new String(b,"gbk"));
		System.out.println(new String(b,"utf-8"));
	}

}
