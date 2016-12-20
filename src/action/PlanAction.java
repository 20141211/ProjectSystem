package action;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.faces.lifecycle.InvokeApplicationPhase;

import po.Plan;
import service.PlanService;

public class PlanAction implements ModelDriven<Plan>{
	PlanService service=new PlanService();
	//HttpServletRequest request=ParameterUtil.getRequest();
	private List<Plan> plans;
	private Plan plan=new Plan();
	public List<Plan> getPlans(){
		return this.plans;
	}
	//show all
	public String showAll(){
		plans=service.getAll();
		return "go";
	}
	//plan //id name projectName moduleName funName foundTime earliest lastest period work priority state delay lastId 
	public String add(){
		plan.setFunId(1);
		service.add(plan);
		return "add";
	}
	//remove
	public String remove(){
		service.remove(plan.getId());
		return "remove";
	}
	//edit
	public String edit(){
		service.remove(plan.getId());
		plan.setFunId(1);//-------------
		service.add(plan);
		return "edit";
	}
	//query
	/* map
	 * ServletActionContext.getRequest.getParameterNames()
	 * enumeration.hasMoreElements(),enu.nextElement(),ServletActionContext.getRequest.getParamenter(),if(value!="") map.put(name,value)
	 * 
	 * 
	 */
	public String query(){
		Map<String, String > map=new HashMap<String, String>();//存储sql语句where条件的属性名(key)，和属性值(value)
		String sql="select * from ps_plan where 1=1 ";//妙用1=1
		Enumeration<String> enu= ServletActionContext.getRequest().getParameterNames();//获取请求标签上的所有name属性值得枚举
		
		while(enu.hasMoreElements()){
			String name=enu.nextElement();
			String value=ServletActionContext.getRequest().getParameter(name);
			if(value!=""){
				map.put(name, value);
			}
		}
		//应用责任链模式将map中值按照
		
		//读取配置文件中的where条件
		/*Set<String>set=map.keySet();
		
		String [] listSift={"projectName","moduleName","funName","name","period1","period2","work1","work2","foundTime1","foundTime2","priority","state","delay","lastPlan","foundTime"};
		for(int i=0;i<listSift.length;i++){
			if(set.contains(listSift[i])){//如果map里有这个筛选条件
				String name=listSift[i];
				
				if(name.substring(name.length()-1).equals("1")){
					String value1=map.get(name);
					String value2=map.get(listSift[++i]);
					sql+=" and "+name.substring(0, name.length()-1)+" between '"+value1+"' and '"+value2+"'";
					
				}else{
					sql+=" and "+name+"='"+map.get(name)+"'";
				}
			}
		}//还可以直接遍历key进行拼接
*/		
		
		AdvancedQueryInvocation advancedQueryInvocation=new AdvancedQueryInvocation() ;
		sql=advancedQueryInvocation.getSql(map);
		sql+=" order by foundTime";
		System.out.println(sql);
		plans=service.query(sql);
		return "query";
	}
	private abstract  class AdvancedQuery{
		private AdvancedQuery next;
		public void setNext(AdvancedQuery advancedQuery){
			this.next=advancedQuery;
		}
		public abstract void invoke();		
	}
	
	
	
	
	@Override
	public Plan getModel() {
		// TODO Auto-generated method stub
		return plan;
	}
}
