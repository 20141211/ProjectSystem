package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import po.Function;
import JDBCUtils.JDBCUtils;

public class FunctionDao implements JDBCUtils.RowMapper{

	@Override
	public Object mapRow(ResultSet rs) throws SQLException {
		//ps_function(id,moduleId,name,foundTime,priority)
		Function function=new Function();
		function.setId(rs.getInt(1));
		function.setModuleId(rs.getInt(2));
		function.setName(rs.getString(3));
		function.setFoundTime(rs.getString(4));
		function.setPriority(rs.getString(5));
		return function;
	}
	//select all
	public List<Function> selectAll(){
		String sql="select * from ps_function order by foundtime";
		return JDBCUtils.queryForList(sql, this);
	}
	//insert id moduleid name foundtime priority 
	public void insert(Function function){
		String sql="insert into ps_function values(null,?,?,to_date(?,'yyyy-mm-dd'),?)";
		JDBCUtils.updateAll(sql, new Object[]{function.getModuleId(),function.getName(),function.getFoundTime(),function.getPriority()});
	}
	//delete
	public void delete(int id){
		String sql="delete from ps_function where id =?";
		JDBCUtils.updateAll(sql, new Object[]{id});
	}
	
}
