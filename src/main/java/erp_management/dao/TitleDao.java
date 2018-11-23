package erp_management.dao;

import java.sql.SQLException;
import java.util.List;

import erp_management.dto.Title;

public interface TitleDao {
	List<Title> selectTitleByAll() throws SQLException;

	int insertTitle(Title title) throws SQLException;

	int deleteTitle(Title title) throws SQLException;

	int updateTitle(Title title) throws SQLException;

	String nextTitleNo() throws SQLException;
}
