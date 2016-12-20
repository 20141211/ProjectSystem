package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import po.Authority;

import JDBCUtils.JDBCUtils;


public class AuthorityDao implements JDBCUtils.RowMapper{

	@Override
	public Object mapRow(ResultSet rs) throws SQLException {
		//ps_authority(id,authName,priorid)
		Authority authority=new Authority();
		authority.setId(rs.getInt("id"));
		authority.setAuthName(rs.getString("authName"));
		authority.setPriorid(rs.getInt("priorid"));
		authority.setHref(rs.getString("href"));
		return authority;
	}
	
	//ͨ��Ա����empno���������Ȩ��
	public List<Authority> selectAllAuthoritiesByEmpno(int empno){
		String sql="select * from ps_authority where id in (select authid from posi_auth where posiid=(select posiid from ps_employee where empno=?))";
		return JDBCUtils.queryForList(sql, new Object[]{empno}, this);
	}
	//
	
	
	public static void main(String args[]){
		System.out.println(new AuthorityDao().selectAllAuthoritiesByEmpno(1).size());
	}
	
	
}
