package po;

import java.util.ArrayList;
import java.util.List;

public class Emp {
	//ps_employee(id,posiid,priorid,empno,password,empname,emptype,idCard,sex,age,email,phone,salary,account)
	private int id;
	private int posiid;
	private int priorid;
	private int empno;
	private String password;
	private String empname;
	//private String emptype;
	private String idCard;
	private String sex;
	private int age;
	private String email;
	private long phone;
	private int salary;
	private int account;
	
	
	public List<Object> getAttributes(){
		List<Object> list=new ArrayList<Object>();
		list.add(id);
		list.add(posiid);
		list.add(empno);
		list.add(empname);
		list.add(idCard);
		list.add(sex);
		list.add(age);
		list.add(email);
		list.add(phone);
		
		
		list.add(password);
		list.add(priorid);
		
		//list.add(emptype);
		
		
		list.add(salary);
		list.add(account);
		return list;
	}
	
	
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPosiid() {
		return posiid;
	}
	public void setPosiid(int posiid) {
		this.posiid = posiid;
	}
	public int getPriorid() {
		return priorid;
	}
	public void setPriorid(int priorid) {
		this.priorid = priorid;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	public long getPhone() {
		return phone;
	}


	public void setPhone(long phone) {
		this.phone = phone;
	}

	
}
