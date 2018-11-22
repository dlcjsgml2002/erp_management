package erp_management.service;

import java.sql.SQLException;
import java.util.List;

import erp_management.dao.EmployeeDao;
import erp_management.dao.EmployeeDaoImpl;
import erp_management.dto.Employee;

public class OutputService {
	private EmployeeDao empDao;
	

	public OutputService() {
		empDao = new EmployeeDaoImpl();
	}


	public List<Employee> selectEmployeeByAll() throws SQLException {
		return empDao.selectEmployeeByAll();
	}
}
