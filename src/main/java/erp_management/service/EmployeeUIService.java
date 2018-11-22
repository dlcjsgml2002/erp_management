package erp_management.service;

import java.sql.SQLException;
import java.util.List;

import erp_management.dao.DepartmentDao;
import erp_management.dao.DepartmentDaoImpl;
import erp_management.dao.EmployeeDao;
import erp_management.dao.EmployeeDaoImpl;
import erp_management.dao.TitleDao;
import erp_management.dao.TitleDaoImpl;
import erp_management.dto.Department;
import erp_management.dto.Employee;
import erp_management.dto.Title;

public class EmployeeUIService {
	private EmployeeDao empDao;
	private TitleDao titleDao;
	private DepartmentDao deptDao;

	public EmployeeUIService() {
		empDao = new EmployeeDaoImpl();
		titleDao = new TitleDaoImpl();
		deptDao = new DepartmentDaoImpl();
	}

	public List<Employee> selectEmployeeByAll() throws SQLException {
		return empDao.selectEmployeeByAll();
	}

	public List<Title> selectTitleByAll() throws SQLException {
		return titleDao.selectTitleByAll();
	}

	public List<Department> selectDepartmentByAll() throws SQLException {
		return deptDao.selectDepartmentByAll();
	}

	public int insertEmployee(Employee emp) throws SQLException {
		return empDao.insertEmployee(emp);
	}
}
