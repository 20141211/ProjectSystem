package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import po.Plan;
import JDBCUtils.JDBCUtils;

public class PlanDao implements JDBCUtils.RowMapper{
//ps_plan(id,funId,empId，name,projectName,moduleName,funName,numOfEmp,foundTime,earlist,latest,period,work,priority,state,delay,lastid)

	@Override
	public Object mapRow(ResultSet rs) throws SQLException {
		Plan plan=new Plan();
		plan.setId(rs.getInt(1));
		plan.setFunId(rs.getInt(2));

		plan.setName(rs.getString(3));
		plan.setProjectName(rs.getString(4));
		plan.setModuleName(rs.getString(5));
		plan.setFunName(rs.getString(6));

		plan.setFoundTime(rs.getString(7));
		plan.setEarliest(rs.getString(8));
		plan.setLatest(rs.getString(9));
		plan.setPeriod(rs.getString(10));
		plan.setWork(rs.getString(11));
		plan.setPriority(rs.getString(12));
		plan.setState(rs.getString(13));
		plan.setDelay(rs.getString(14));
		plan.setLastId(rs.getInt(15));
		return plan;
	}
	//select all
	public List<Plan> selectAll(){
		String sql="select * from ps_plan order by priority";
		return JDBCUtils.queryForList(sql, this);
	}
	//insert//id funid name projectName moduleName funName foundTime earliest lastest period work priority state delay lastId 
	public void insert(Plan plan){
		String sql="insert into ps_plan values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[]objects={plan.getFunId(),plan.getName(),plan.getProjectName(),plan.getModuleName(),plan.getFunName(),plan.getFoundTime(),plan.getEarliest(),plan.getLatest(),plan.getPeriod(),plan.getWork(),plan.getPriority(),plan.getState(),plan.getDelay(),plan.getLastId()};
		JDBCUtils.updateAll(sql, objects);
	}
	//delete
	public void delete (int id){
		String sql="delete from ps_plan where id=?";
		JDBCUtils.updateAll(sql,new Object[]{id});
	}
	//query 
	public List<Plan> query(String sql){
		return JDBCUtils.queryForList(sql, this);
	}

}
