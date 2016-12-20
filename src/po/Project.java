package po;

import java.text.SimpleDateFormat;

public class Project {
	//ps_project(id,clientId,name,clientName,managerName,startTime,updateTime,priority,state)
	private int id;
	private int clientId;
	private String name;
	private String clientName;
	private String managerName;
	private String startTime;
	private String updateTime;
	private String priority;
	private String state;
	private int count;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		if(startTime!=null){
			startTime=startTime.split(" ")[0];
		}
		this.startTime = startTime;
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
}
