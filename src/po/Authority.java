package po;

public class Authority {
	//ps_authority(id,priorid,authName)
	private int id;
	private int priorid;
	private String authName;
	private String href;
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public int getPriorid() {
		return priorid;
	}
	public void setPriorid(int priorid) {
		this.priorid = priorid;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthName() {
		return authName;
	}
	public void setAuthName(String authName) {
		this.authName = authName;
	}
	
}
