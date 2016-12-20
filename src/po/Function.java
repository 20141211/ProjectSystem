package po;

public class Function {
	//ps_function(id,moduleId,name,foundTime,priority)
	private int id;
	private int moduleId;
	private String name;
	private String foundTime;
	private String priority;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
