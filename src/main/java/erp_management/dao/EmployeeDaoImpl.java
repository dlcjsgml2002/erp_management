package erp_management.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import erp_management.dto.Employee;
import erp_management.jdbc.LogUtil;
import erp_management.jdbc.MySQLJdbcUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public List<Employee> selectEmployeeByAll() throws SQLException {
		LogUtil.prnLog("selectEmployeeByAll()");
		String sql = "select empno, empname, emptitle, salary, gender, empdept, joindate from employee";
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

	private Employee getEmployee(ResultSet rs) {
		return new Employee();
	}

	@Override
	public int insertEmployee(Employee emp) throws SQLException {
		LogUtil.prnLog("insertEmployee()");
		String sql = "insert into employee values(?, ?, ?, ?, ?, ?)";
		try (Connection conn = MySQLJdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, emp.getEmpNo());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getTitle());
			pstmt.setInt(4, emp.getManager().getEmpNo());
			pstmt.setInt(5, emp.getSalary());
			pstmt.setInt(6, emp.getDept().getDeptNo());
			LogUtil.prnLog(pstmt);
			return pstmt.executeUpdate();
		}
	}

	@Override
	public int deleteEmployee(Employee emp) throws SQLException {
		LogUtil.prnLog("deleteEmployee()");
		String sql = "delete from employee where empno = ?";
		try (Connection conn = MySQLJdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, emp.getEmpNo());
			LogUtil.prnLog(pstmt);
			return pstmt.executeUpdate();
		}
	}

	@Override
	public int updateEmployee(Employee emp) throws SQLException {
		LogUtil.prnLog("updateEmployee()");
		String sql = "update employee set empname=?, title=?, manager=?, salary=?, dno=? where empno=?";
		try (Connection conn = MySQLJdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, emp.getEmpName());
			pstmt.setString(2, emp.getTitle());
			pstmt.setInt(3, emp.getManager().getEmpNo());
			pstmt.setInt(4, emp.getSalary());
			pstmt.setInt(5, emp.getDept().getDeptNo());
			pstmt.setInt(6, emp.getEmpNo());
			LogUtil.prnLog(pstmt);
			return pstmt.executeUpdate();
		}
	}

	@Override
	public Employee selectEmployeeByNo(Employee emp) throws SQLException {
		LogUtil.prnLog("selectEmployeeByNo()");
		String sql = "select empno, empname, title, manager, salary, dno from employee where empno = ?";
		Employee employee = null;
		try (Connection conn = MySQLJdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, emp.getEmpNo());
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
