package po;

public class Demand {
	//ps_demand(id，projectId,name,projectName,foundTime,updateTime)
	private int id;
	private int projectId;
	private String name;
	private String projectName;
	private String foundTime;
	private String updateTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
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
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		if(updateTime!=null){
			updateTime=updateTime.split(" ")[0];
		}
		this.updateTime = updateTime;
	}
	
}
