package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import po.Message;

import JDBCUtils.JDBCUtils;

public class MessageDao implements JDBCUtils.RowMapper{
	//ps_message(senderId,receiverId,message,time)
	@Override
	public Object mapRow(ResultSet rs) throws SQLException {
		Message message=new Message();
		message.setSenderId(rs.getInt(1));
		message.setReceiverId(rs.getInt(2));
		message.setMessage(rs.getString(3));
		message.setTime(rs.getString(4));
		return message;
	}
	//insert message
	public void insert(int senderId,int receiverId,String message,String time){
		String sql="insert into ps_message values(?,?,?,to_date(?,'yyyy-mm-dd HH24:mi:ss'))";
		Calendar c=Calendar.getInstance();    
	    time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
		JDBCUtils.updateAll(sql, new Object[]{senderId,receiverId,message,time});
	}
	//select by senderId receiverid
	public List<Message>select(int senderId,int receiverId){
		String sql="select * from ps_message where senderId=? and receiverId=? or senderId=? and receiverId=? order by time";
		return JDBCUtils.queryForList(sql, new Object[]{senderId,receiverId,receiverId,senderId}, this);
	}
	//select count   如果表结构有一个自增的流水号id，那么这时候就可以查id的最大值了，这样即使删除了数据也不会有影响;而依靠记录总数的话，删除操作的扩展性会受到影响
	//如果按照时间排序的话就要精确都毫秒，因为从数据库的数据就能看出秒级别的重复数据是很大的;然而oracle的to_date方法只支持到秒，若想到毫秒就要用到时间戳
	//而oracle的伪劣rowid是一个6字节存储的行的地址
	/*
	 * 我们在创建表时，可以为列指定为rowid数据类型，但oracle并不保证列中的数据是合法的rowid值,必须由应用程序来保证,另外,类型为rowid的列需要6 bytes存储数据
	 * 
	 */
	public long selectCount(int senderId,int receiverId){
		String sql="select * from ps_message where senderId=? and receiverId=? or senderId=? and receiverId=?";
		return JDBCUtils.queryForList(sql,new Object[]{senderId,receiverId,receiverId,senderId}, this).size();
	}
	
	public static void main(String args[]){
		 Calendar c=Calendar.getInstance();    
	     SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");    
	     System.out.println(time.format(c.getTime()));    
	     //System.out.println(Timestamp.valueOf(time.format(c.getTime())));    
	     //System.out.println(Timestamp.valueOf(System.currentTimeMillis()+""));    
		 MessageDao dao=new MessageDao();
		 dao.insert(1, 1, "1",time.format(c.getTime()));
		 
	}
}
