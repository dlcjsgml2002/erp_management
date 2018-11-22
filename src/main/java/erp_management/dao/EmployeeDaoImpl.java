package erp_management.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import erp_management.dto.Department;
import erp_management.dto.Employee;
import erp_management.dto.Title;
import erp_management.jdbc.LogUtil;
import erp_management.jdbc.MySQLJdbcUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public List<Employee> selectEmployeeByAll() throws SQLException {
		LogUtil.prnLog("selectEmployeeByAll()");
		String sql = "select empno, empname, emptitle, titlename, salary, gender, empdept, deptname, floor, joindate"
				+ " from employee as e join title as t on e.emptitle = t.titleno join department as d on e.empdept = d.deptno";
		List<Employee> list = new ArrayList<>();

		try (Connection conn = MySQLJdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			LogUtil.prnLog(pstmt);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					list.add(getEmployee(rs));
				}
			}
		}

		return list;
	}

	private Employee getEmployee(ResultSet rs) throws SQLException {
		String empNo = rs.getString("empno");
		String empName = rs.getString("empName");
		Title title = new Title(rs.getString("emptitle"), rs.getString("titlename"));
		int salary = rs.getInt("salary");
		String gender = rs.getString("gender");
		Department department = new Department(rs.getString("empdept"), rs.getString("deptname"), rs.getInt("floor"));
		Date date = rs.getDate("joindate");
		return new Employee(empNo, empName, title, salary, gender, department, date);
	}

	@Override
	public int insertEmployee(Employee emp) throws SQLException {
		LogUtil.prnLog("insertEmployee()");
		String sql = "insert into employee values(?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = MySQLJdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, emp.getEmpNo());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getTitle().getTitleNo());
			pstmt.setInt(4, emp.getSalary());
			pstmt.setString(5, emp.getGender());
			pstmt.setString(6, emp.getDepartment().getDeptNo());
			pstmt.setDate(7, emp.getDate());
			LogUtil.prnLog(pstmt);

			return pstmt.executeUpdate();
		}
	}

	@Override
	public int deleteEmployee(Employee emp) throws SQLException {
		LogUtil.prnLog("deleteEmployee()");
		String sql = "delete from employee where empno = ?";

		try (Connection conn = MySQLJdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, emp.getEmpNo());
			LogUtil.prnLog(pstmt);

			return pstmt.executeUpdate();
		}
	}

	@Override
	public int updateEmployee(Employee emp) throws SQLException {
		LogUtil.prnLog("updateEmployee()");
		String sql = "update employee set empname = ?, emptitle = ?, salary = ?, gender = ?, empdept = ?, joindate = ? where empno = ?";

		try (Connection conn = MySQLJdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(7, emp.getEmpNo());
			pstmt.setString(1, emp.getEmpName());
			pstmt.setString(2, emp.getTitle().getTitleNo());
			pstmt.setInt(3, emp.getSalary());
			pstmt.setString(4, emp.getGender());
			pstmt.setString(5, emp.getDepartment().getDeptNo());
			pstmt.setDate(6, emp.getDate());
			LogUtil.prnLog(pstmt);

			return pstmt.executeUpdate();
		}
	}

	@Override
	public Employee selectEmployeeByNo(Employee emp) throws SQLException {
		LogUtil.prnLog("selectEmployeeByNo()");
		String sql = "select empno, empname, emptitle, salary, gender, empdept, joindate from employee where empno = ?";
		Employee employee = null;

		try (Connection conn = MySQLJdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, emp.getEmpNo());
			LogUtil.prnLog(pstmt);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					employee = getEmployee(rs);
				}
			}
		}

		return employee;
	}

}
