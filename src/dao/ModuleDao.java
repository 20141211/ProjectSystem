package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import po.Module;
import JDBCUtils.JDBCUtils;

public class ModuleDao implements JDBCUtils.RowMapper{

	@Override
	public Object mapRow(ResultSet rs) throws SQLException {
		//ps_module(id,demandId,name,projectName,foundTime,priority)
		Module module=new Module();
		module.setId(rs.getInt(1));
		module.setDemandId(rs.getInt(2));
		module.setName(rs.getString(3));
		module.setProjectName(rs.getString(4));
		module.setFoundTime(rs.getString(5));
		module.setPriority(rs.getString(6));
		return module;
	}
	//select all
	public List<Module> selectAll(){
		String sql="select * from ps_module order by foundtime";
		return JDBCUtils.queryForList(sql, this);
	}
	//insert//id demandid name projectname foundtime priority
	public void insert(Module module){
		String sql="insert into ps_module values(null,?,?,?,to_date(?,'yyyy-mm-dd'),?)";
		JDBCUtils.updateAll(sql, new Object[]{module.getDemandId(),module.getName(),module.getProjectName(),module.getFoundTime(),module.getPriority()});
	}
	public void delete(int id){
		String sql="delete from ps_module where id=?";
		JDBCUtils.updateAll(sql, new Object[]{id});
	}

}
