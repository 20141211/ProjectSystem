package action;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;


public  class AdvancedQueryInvocation {
	AdvancedQueryInvocation next;
	private String sql="select * from ps_client where 1=1";
	private String [] list={"projectName","moduleName","funName","name","period1","period2","work1","work2","foundTime1","foundTime2","priority","state","delay","lastPlan","foundTime"};
	Iterator<Query>iterator;
	private LinkedList<Query> linkedList=new LinkedList<AdvancedQueryInvocation.Query>();
	private static abstract class Query{
		protected String sql;
		public Query(String sql){
			this.sql=sql;
		}
		public abstract String query(AdvancedQueryInvocation invocation);
	}

	public String getSql(Map<String, String> map){
		this.init(map);
		String ret=sql+invoke();
		System.out.println(ret);
		return ret ;
	}
	
	
	public void init(Map<String, String> map){
		for(int i=0;i<list.length;i++){
			String value=map.get(list[i]);
			String sql="";
			System.out.println(value+"fffffffffffffffffffff3");
			if(value!=null&&!value.equals("")){
			
				if(list[i].substring(value.length()-1).equals("1")){
					String value1=map.get(value);
					String value2=map.get(list[++i]);
					sql+=" and "+list[i].substring(0, value.length()-1)+" between '"+value1+"' and '"+value2+"'";
					
				}else{
					sql+=" and "+list[i]+"='"+value+"'";
					
				}
				linkedList.add(new Query(sql) {
					
					@Override
					public String query(AdvancedQueryInvocation invocation) {
						// TODO Auto-generated method stub
						
						return this.sql+invocation.invoke();
					}
				});
				
			}
		}
		iterator=linkedList.iterator();
	}
	public String invoke(){
		if(iterator.hasNext()){
			Query query=iterator.next();
			return query.query(this);
		}
		return "";
	}
}
