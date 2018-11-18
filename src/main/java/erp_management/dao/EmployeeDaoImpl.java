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
	public List<Employee> selectDepartmentByAll() throws SQLException {
		LogUtil.prnLog("selectEmployeeByAll()");
		String sql = "select empno, empname, title, manager, salary, dno from employee";
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
		return new Employee(rs.getInt("empno"), rs.getString("empName"), rs.getString("title"),
				new Employee(rs.getInt("manager")), rs.getInt("salary"), new Department(rs.getInt("dno")));
	}

	@Override
	public int insertDepartment(Employee emp) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteDepartment(Employee emp) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateDepartment(Employee emp) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Employee selectDepartmentByNo(Employee emp) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
