package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import po.Position;
import JDBCUtils.JDBCUtils;

public class PositionDao implements JDBCUtils.RowMapper{
	//ps_position(id,posiname)
	@Override
	public Object mapRow(ResultSet rs) throws SQLException {
		Position position=new Position();
		position.setId(rs.getInt(1));
		position.setPosiname(rs.getString(2));
		return position;
	}
	//select all
	public List<Position> select(){
		String sql="select * from ps_position";
		return JDBCUtils.queryForList(sql, this);//这个泛型是怎么自动匹配的
	}
	
}
