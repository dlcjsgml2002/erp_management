package erp_management.dto;

import java.sql.Date;

public class Employee {
	private String empNo;
	private String empName;
	private Title titleName;
	private int salary;
	private String gender;
	private Department deptName;
	private Date date;

	public Employee() {
		
	}

	public Employee(String empNo, String empName, Title titleName, int salary, String gender, Department deptName,
			Date date) {
		this.empNo = empNo;
		this.empName = empName;
		this.titleName = titleName;
		this.salary = salary;
		this.gender = gender;
		this.deptName = deptName;
		this.date = date;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Title getTitleName() {
		return titleName;
	}

	public void setTitleName(Title titleName) {
		this.titleName = titleName;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Department getDeptName() {
		return deptName;
	}

	public void setDeptName(Department deptName) {
		this.deptName = deptName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return String.format(
				"Employee [empNo=%s, empName=%s, titleName=%s, salary=%s, gender=%s, deptName=%s, date=%s]", empNo,
				empName, titleName, salary, gender, deptName, date);
	}
}
