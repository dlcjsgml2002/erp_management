package erp_management;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp_management.dao.EmployeeDao;
import erp_management.dao.EmployeeDaoImpl;
import erp_management.dto.Department;
import erp_management.dto.Employee;
import erp_management.dto.Title;
import erp_management.jdbc.LogUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {
	static EmployeeDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("Start EmployeeDaoTest");
		dao = new EmployeeDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("End EmployeeDaoTest");
		dao = null;
	}

	@Before
	public void setUp() throws Exception {
		System.out.println();
	}

	@Test
	public void test01SelectEmployeeByAll() throws SQLException {
		List<Employee> lists = dao.selectEmployeeByAll();
		LogUtil.prnLog(lists.toString());
		Assert.assertNotNull(lists);
	}

	@Test
	public void test02InsertEmployee() {
		try {
			Employee newEmp = new Employee("E017010", "나인턴", new Title("T005"), 1500000, "남자", new Department("D001"),
					new Date(2017 - 11 - 21));
			int rowAffected = dao.insertEmployee(newEmp);
			LogUtil.prnLog(String.format("rowAffected %d", rowAffected));
			Assert.assertEquals(1, rowAffected);
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				LogUtil.prnLog("이미 존재하는 사원입니다.");
			} else {
				LogUtil.prnLog(e);
			}
		}
	}

/*	@Test
	public void test05DeleteEmployee() {
		try {
			Employee deEmp = new Employee("E017010");
			int rowAffected = dao.deleteEmployee(deEmp);
			LogUtil.prnLog(String.format("rowAffected", rowAffected));
			Assert.assertEquals(1, rowAffected);
		} catch (SQLException e) {
			if (e.getErrorCode() == 1451) {
				LogUtil.prnLog("존재하지 않는 사원입니다.");
			} else {
				LogUtil.prnLog(e);
			}
		}
	}*/

	@Test
	public void test03UpdateEmployee() throws SQLException {
		Employee updateEmp = new Employee("E017010", "너인턴", new Title("T003"), 2000000, "여자", new Department("D003"),
				new Date(2017 - 11 - 22));
		int rowAffected = dao.updateEmployee(updateEmp);
		LogUtil.prnLog(String.format("rowAffected", rowAffected));
		Assert.assertEquals(1, rowAffected);
	}

	@Test
	public void test04SelectEmployeeByNo() throws SQLException {
		Employee selEmp = dao.selectEmployeeByNo(new Employee("E017010"));
		LogUtil.prnLog(selEmp.toString());
		Assert.assertNotNull(selEmp);
	}

}
