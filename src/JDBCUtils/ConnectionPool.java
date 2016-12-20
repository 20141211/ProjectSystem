package JDBCUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class ConnectionPool {
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static List<MyConnection> connections=new ArrayList<MyConnection>();
	private static int min=5;
	private static int max=100;
	private static String username="ps";
	private static String password="ps";
	//private ReentrantLock lock=new ReentrantLock();
	private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private static  WriteLock writeLock = lock.writeLock();
	private static  ReadLock readLock = lock.readLock();
	private static Lock lock2=new ReentrantLock();
	static{
		for(int i=0;i<min;i++){
			try {
				Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",username,password);
				connections.add(new MyConnection(connection));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private static  Connection getConnection(int n){
		for(int i=0;i<n;i++){
			writeLock.lock();
			try{
				if(!connections.get(i).isUsed()){
					connections.get(i).setUsed(true);
					return connections.get(i);
				}
			}finally{
				writeLock.unlock();
			}
		}
		return null;
	}
	public static  Connection getConnection(){
		Connection connection=null;
		while(connection==null){
			connection=getConnection(connections.size());
		}
		return connection;
	}
	public static void setMax(int max){
		ConnectionPool.max=max;
	}
	public static void setMin(int min){
		ConnectionPool.min=min;
	}
	public static void setUsername(String username){
		ConnectionPool.username=username;
	}
	public static void setPassword(String password){
		ConnectionPool.password=password;
	}
}
