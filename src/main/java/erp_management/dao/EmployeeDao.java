package erp_management.dao;

import java.sql.SQLException;
import java.util.List;

import erp_management.dto.Employee;

public interface EmployeeDao {
	List<Employee> selectDepartmentByAll();

	int insertDepartment(Employee emp) throws SQLException;

	int deleteDepartment(Employee emp) throws SQLException;

	int updateDepartment(Employee emp) throws SQLException;

	Employee selectDepartmentByNo(Employee emp) throws SQLException;
}
