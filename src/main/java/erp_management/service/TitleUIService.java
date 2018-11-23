package erp_management.service;

import java.sql.SQLException;
import java.util.List;

import erp_management.dao.TitleDao;
import erp_management.dao.TitleDaoImpl;
import erp_management.dto.Title;

public class TitleUIService {
	private TitleDao titledao;

	public TitleUIService() {
		titledao = new TitleDaoImpl();
	}
	
	public List<Title> selectTitleByAll() throws SQLException {
		return titledao.selectTitleByAll();
	}
	
	public int insertTitle(Title title) throws SQLException {
		return titledao.insertTitle(title);
	}
	
	public int deleteTitle(Title title) throws SQLException {
		return titledao.deleteTitle(title);
	}
	
	public int updateTitle(Title title) throws SQLException {
		return titledao.updateTitle(title);
	}
}
