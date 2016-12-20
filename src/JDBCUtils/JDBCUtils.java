package JDBCUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.driver.T4CXAConnection;


public class JDBCUtils {
	public static interface RowMapper {
		//通过rowmapper 
		public Object mapRow(ResultSet rs) throws SQLException;
		
	}
	
	public static void updateAll(String sql){
		JDBCUtils.updateAll(sql, new Object[]{});
	}
	
	public static void updateAll(String sql,Object[] values){
		//jdbc������
		//�������Ͳ�ȷ��	
		
		
		Connection conn=null;
		PreparedStatement stat=null;		
		try {
			conn=ConnectionPool.getConnection();
			stat=conn.prepareStatement(sql);
			//��̬����ֵ
			for(int i=0;i<values.length;i++){
				
				stat.setObject(i+1, values[i]);
			}
			//System.out.println(sql);
			stat.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(stat!=null){
					stat.close();
				}				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn!=null){
					conn.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		//ִ�в���
	}
	
	//����
	public static Object queryForObject(String sql,Object[] values,RowMapper rm){
		Object ret=null;
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet rs=null;
		
		try {
			/*Class.forName("oracle.jdbc.driver.OracleDriver");
			connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",username,password);
			*/
			connection=ConnectionPool.getConnection();
			statement=connection.prepareStatement(sql);	
			
			for(int i=0;i<values.length;i++){
				//System.out.println(values[i]);
				statement.setObject(i+1, values[i]);
			}
			//System.out.println(sql);
			
			rs=statement.executeQuery();
			if(rs.next()){
				//����һ��
				//System.out.println(rm);
				
				ret=rm.mapRow(rs);
			}
			//System.out.println(((Atm)ret).getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)
					rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if(statement!=null)
					statement.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if(connection!=null)
					connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}	
		
		return ret;
	}
	
	//public List queryForList()
	public static <E> List<E> queryForList(String sql,RowMapper rm){
		Object[] values=new Object[]{};
		return JDBCUtils.queryForList(sql, values, rm);
	}
	public static <E> List<E> queryForList(String sql,Object[] values,RowMapper rm){
		E object=null;
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet rs=null;
		List<E> list=new ArrayList<E>();
		try {
			connection=ConnectionPool.getConnection();
			statement=connection.prepareStatement(sql);		
			for(int i=0;i<values.length;i++){
				//System.out.println(values[i]);
				statement.setObject(i+1, values[i]);
			}
			//System.out.println(sql);
			rs=statement.executeQuery();		
			while(rs.next()){
				//����һ��
				
				object=(E) rm.mapRow(rs);
				list.add(object);
			}
			//System.out.println(((Atm)ret).getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)
					rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if(statement!=null)
					statement.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if(connection!=null)
					connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}	
		return list;
	}
	
	
	
	public static <E> List<E> queryForList2(String sql,Object[] values,RowMapper rm){
		E object=null;
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet rs=null;
		List<E> list=new ArrayList<E>();
		try {
			connection=ConnectionPool.getConnection();
			statement=connection.prepareStatement(sql);		
			for(int i=0;i<values.length;i++){
				//System.out.println(values[i]);
				statement.setObject(i+1, values[i]);
			}
			//System.out.println(sql);
			rs=statement.executeQuery();		
			while(rs.next()){
				//����һ��
				
				object=(E) rm.mapRow(rs);
				list.add(object);
			}
			//System.out.println(((Atm)ret).getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)
					rs.close();
			
				if(statement!=null)
					statement.close();
			
				if(connection!=null)
					connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}	
		return list;
	}
}
