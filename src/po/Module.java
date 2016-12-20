package po;

public class Module {
	//ps_module(id,demandId,name,projectName,foundTime,priority)
	private int id;
	private int demandId;
	private String name;
	private String projectName;
	private String foundTime;
	private String priority;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDemandId() {
		return demandId;
	}
	public void setDemandId(int demandId) {
		this.demandId = demandId;
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
	public String getFoundTime() {
		return foundTime;
	}
	public void setFoundTime(String foundTime) {
		if(foundTime!=null){
			foundTime=foundTime.split(" ")[0];
		}
		this.foundTime = foundTime;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
}
