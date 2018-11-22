package erp_management.service;

import java.sql.SQLException;
import java.util.List;

import erp_management.dao.DepartmentDao;
import erp_management.dao.DepartmentDaoImpl;
import erp_management.dto.Department;

public class DepartmentUIService {
	private DepartmentDao deptDao;

	public DepartmentUIService() {
		deptDao = new DepartmentDaoImpl();
	}

	public List<Department> selectDepartmentByAll() throws SQLException {
		return deptDao.selectDepartmentByAll();
	}

	public int insertDepartment(Department dept) throws SQLException {
		return deptDao.insertDepartment(dept);
	}

	public int deleteDepartment(Department dept) throws SQLException {
		return deptDao.deleteDepartment(dept);
	}
}
