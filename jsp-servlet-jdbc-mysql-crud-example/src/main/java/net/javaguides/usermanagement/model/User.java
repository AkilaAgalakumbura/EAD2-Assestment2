package net.javaguides.usermanagement.model;

public class User {
	private String Name;
	private int NIC;
	private String Department;
	private String Designation;
	private String Joined_Date;
	
	
	public User(String name, int nIC, String department, String designation, String joined_Date) {
		super();
		this.Name = name;
		this.NIC = nIC;
		this.Department = department;
		this.Designation = designation;
		this.Joined_Date = joined_Date;
	}
	
	
	public User(String name, String department, String designation, String joined_Date) {
		super();
		this.Name = name;
		this.Department = department;
		this.Designation = designation;
		this.Joined_Date = joined_Date;
	}

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getNIC() {
		return NIC;
	}
	public void setNIC(int nIC) {
		NIC = nIC;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	public String getDesignation() {
		return Designation;
	}
	public void setDesignation(String designation) {
		Designation = designation;
	}
	public String getJoined_Date() {
		return Joined_Date;
	}
	public void setJoined_Date(String joined_Date) {
		Joined_Date = joined_Date;
	}
}
