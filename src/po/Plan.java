package po;

public class Plan {
//ps_plan(id,funId,empId，name,projectName,moduleName,funName,foundTime,earlist,latest,period,work,priority,state,delay,lastid)
	private int id;
	private int funId;
	private String name;
	private String projectName;
	private String moduleName;
	private String funName;

	private String foundTime;
	private String earliest;
	private String latest;
	private String period;
	private String work;
	private String priority;
	private String state;
	private String delay;
	private int lastId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFunId() {
		return funId;
	}
	public void setFunId(int funId) {
		this.funId = funId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getFunName() {
		return funName;
	}
	public void setFunName(String funName) {
		this.funName = funName;
	}
	public String getFoundTime() {
		return foundTime;
	}
	public void setFoundTime(String foundTime) {
		if(foundTime!=null){
			foundTime=foundTime.split(" ")[0];
		}
		this.foundTime = foundTime;
	}
	public String getEarliest() {
		
		return earliest;
	}
	public void setEarliest(String earlist) {
		if(earlist!=null){
			earlist=earlist.split(" ")[0];
		}
		this.earliest = earlist;
	}
	public String getLatest() {
		return latest;
	}
	public void setLatest(String latest) {
		if(latest!=null){
			latest=latest.split(" ")[0];
		}
		this.latest = latest;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDelay() {
		return delay;
	}
	public void setDelay(String delay) {
		this.delay = delay;
	}
	public int getLastId() {
		return lastId;
	}
	public void setLastId(int lastId) {
		this.lastId = lastId;
	}
}