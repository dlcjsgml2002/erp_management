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

import erp_management.dao.TitleDao;
import erp_management.dao.TitleDaoImpl;
import erp_management.dto.Title;
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
	public void test01SelectTitleByAll() throws SQLException {
		List<Title> lists = dao.selectTitleByAll();
		LogUtil.prnLog(lists.toString());
		Assert.assertNotNull(lists);
	}

	@Test
	public void test02InsertTitle() {
		try {
			Title newTitle = new Title("T006", "인턴");
			int rowAffected = dao.insertTitle(newTitle);
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
	public void test05DeleteTitle() {
		try {
			Title delTitle = new Title();
			delTitle.setTitleNo("T006");
			int rowAffected = dao.deleteTitle(delTitle);
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
	public void test03UpdateTitle() {
		try {
			Title updateTitle = new Title("T006", "노예");
			int rowAffected = dao.updateTitle(updateTitle);
			LogUtil.prnLog(String.format("rowAffected %d", rowAffected));
			Assert.assertEquals(1, rowAffected);
		} catch (SQLException e) {
			LogUtil.prnLog(e);
		}
	}
}
