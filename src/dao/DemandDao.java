package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import po.Demand;

import JDBCUtils.JDBCUtils;

public class DemandDao implements JDBCUtils.RowMapper {

	@Override
	public Object mapRow(ResultSet rs) throws SQLException {
		//ps_demand(id，projectId,name,projectName,foundTime,updateTime)
		Demand demand=new Demand();
		demand.setId(rs.getInt(1));
		demand.setProjectId(rs.getInt(2));
		demand.setName(rs.getString(3));
		demand.setProjectName(rs.getString(4));
		demand.setFoundTime(rs.getString(5));
		demand.setUpdateTime(rs.getString(6));
		return demand;
	}
	//select all
	public List<Demand> selectAll(){
		String sql="select * from ps_demand order by foundTime";
		return JDBCUtils.queryForList(sql, this);
	}
	//insert
	public void insert(Demand demand){
		String sql="insert into ps_demand values(null,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'))";
		JDBCUtils.updateAll(sql, new Object[]{demand.getProjectId(),demand.getName(),demand.getProjectName(),demand.getFoundTime(),demand.getUpdateTime()});
	}
	//delete
	public void delete(int id){
		String sql="delete from ps_demand where id=?";
		JDBCUtils.updateAll(sql,new Object[]{id});
	}
	
	
}
