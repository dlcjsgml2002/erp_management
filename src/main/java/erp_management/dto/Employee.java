package erp_management.dto;

import java.sql.Date;

public class Employee {
	private String empNo;
	private String empName;
	private Title title;
	private int salary;
	private String gender;
	private Department department;
	private Date date;

	public Employee() {

	}

	public Employee(String empNo, String empName, Title title, int salary, String gender, Department department,
			Date date) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.salary = salary;
		this.gender = gender;
		this.department = department;
		this.date = date;
	}

	public Employee(String empNo) {
		this.empNo = empNo;
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

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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
				empName, title, salary, gender, department, date);
	}
}
