package JDBCUtils;

import java.sql.Connection;

import javax.transaction.xa.XAException;

import oracle.jdbc.driver.T4CXAConnection;

public class Test extends T4CXAConnection{
	private int i=0;
	public Test(Connection arg0) throws XAException {
		super(arg0);
		// TODO Auto-generated constructor stub
		
	}
	public void f(Test t){
		t.i=2;
	}

}
