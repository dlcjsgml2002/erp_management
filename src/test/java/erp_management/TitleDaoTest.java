package erp_management;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp_management.jdbc.LogUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TitleDaoTest {
	static TitleDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("Start TitleDaoTest");
		dao = new TitleDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("End TitleDaoTest");
		dao = null;
	}

	@Before
	public void setUp() throws Exception {
		System.out.println();
	}

	@Test
	public void test01SelectDepartmentByAll() {
		List<Title> lists = dao.selectDepartmentByAll();
		LogUtil.prnLog(lists.toString());
		Assert.assertNotNull(lists);
	}

	@Test
	public void test02InsertDepartment() {
		try {
			Title newDept = new Title("D005", "경영", 4);
			int rowAffected = dao.insertDepartment(newDept);
			LogUtil.prnLog(String.format("rowAffected %d", rowAffected));
			Assert.assertEquals(1, rowAffected);
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				LogUtil.prnLog("");
			} else {
				LogUtil.prnLog(e);
			}
		}
	}

	@Test
	public void test04DeleteDepartment() {
		try {
			Title delDept = new Department();
			delDept.setDeptNo("D005");
			int rowAffected = dao.deleteDepartment(delDept);
			LogUtil.prnLog(String.format("rowAffected %d", rowAffected));
			Assert.assertEquals(1, rowAffected);
		} catch (SQLException e) {
			if (e.getErrorCode() == 1451) {
				LogUtil.prnLog("");
			} else {
				LogUtil.prnLog(e);
			}
		}
	}

	@Test
	public void test03UpdateDepartment() {
		try {
			Department updateDept = new Department("D005", "경영", 4);
			int rowAffected = dao.updateDepartment(updateDept);
			LogUtil.prnLog(String.format("rowAffected %d", rowAffected));
			Assert.assertEquals(1, rowAffected);
		} catch (SQLException e) {
			LogUtil.prnLog(e);
		}
	}

	@Test
	public void test05SelectDepartmentByNo() {
		try {
			Department selDept = new Department();
			selDept.setDeptNo("D005");
			Department department = dao.selectDepartmentByNo(selDept);
			LogUtil.prnLog(String.format("%s - %s", department.getClass().getSimpleName(), department));
			Assert.assertNotNull(department);
		} catch (SQLException e) {
			LogUtil.prnLog(e);
		}
	}
}
