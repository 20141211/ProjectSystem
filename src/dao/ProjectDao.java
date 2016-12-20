package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import po.Project;
import JDBCUtils.JDBCUtils;

public class ProjectDao implements JDBCUtils.RowMapper{

	@Override
	public Object mapRow(ResultSet rs) throws SQLException {
		//ps_project(id,clientId,name,clientName,managerName,startTime,updateTime,priority,state,count)
		Project project=new Project();
		project.setId(rs.getInt(1));
		project.setClientId(rs.getInt(2));
		project.setName(rs.getString(3));
		project.setClientName(rs.getString(4));
		project.setManagerName(rs.getString(5));
		project.setStartTime(rs.getString(6));
		project.setUpdateTime(rs.getString(7));
		project.setPriority(rs.getString(8));
		project.setState(rs.getString(9));
		project.setCount(rs.getInt(10));
		return project;
	}
	
	//select all
	public List<Project> select(){
		String sql="select * from ps_project order by startTime";
		return JDBCUtils.queryForList(sql, this);
	}
	//insert
	public void insert(Project project){
		String sql="insert into ps_project values(null,?,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?,0)";
//ps_project(id,clientId,name,clientName,managerName,startTime,updateTime,priority,state,count)
		
		JDBCUtils.updateAll(sql, new Object[]{project.getClientId(),project.getName(),project.getClientName(),project.getManagerName(),project.getStartTime(),project.getUpdateTime(),project.getPriority(),project.getState()});
	}
	//delete
	public void delete(int id){
		String sql="delete from ps_project where id=?";
		JDBCUtils.updateAll(sql, new Object[]{id});
	}
	//update
	public void update (Project project){
		String sql="delete from ps_project where id=?";
		JDBCUtils.updateAll(sql,new Object[]{project.getId()});
		insert(project);
	}
	
}
