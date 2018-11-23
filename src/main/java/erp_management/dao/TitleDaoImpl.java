package erp_management.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import erp_management.dto.Title;
import erp_management.jdbc.ConnectionProvider;
import erp_management.jdbc.LogUtil;
import erp_management.jdbc.MySQLJdbcUtil;

public class TitleDaoImpl implements TitleDao {

	@Override
	public List<Title> selectTitleByAll() throws SQLException {
		List<Title> list = new ArrayList<>();
		String sql = "select titleno, titlename from title";

		try (Connection conn = MySQLJdbcUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			LogUtil.prnLog(pstmt);
			while (rs.next()) {
				list.add(getTitle(rs));
			}
		}

		return list;
	}

	private Title getTitle(ResultSet rs) throws SQLException {
		String titleNo = rs.getString("titleno");
		String titleName = rs.getString("titlename");

		return new Title(titleNo, titleName);
	}

	@Override
	public int insertTitle(Title title) throws SQLException {
		LogUtil.prnLog("insertTitle()");
		String sql = "insert into title values(?, ?)";
		int rowAffected = 0;

		try (Connection conn = MySQLJdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, title.getTitleNo());
			pstmt.setString(2, title.getTitleName());
			LogUtil.prnLog(pstmt);
			rowAffected = pstmt.executeUpdate();
		}

		return rowAffected;
	}

	@Override
	public int deleteTitle(Title title) throws SQLException {
		LogUtil.prnLog("deletTitle()");
		String sql = "delete from title where titleno=?";
		int rowAffected = 0;

		try (Connection conn = MySQLJdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, title.getTitleNo());
			LogUtil.prnLog(pstmt);
			rowAffected = pstmt.executeUpdate();
		}

		return rowAffected;
	}

	@Override
	public int updateTitle(Title title) throws SQLException {
		LogUtil.prnLog("updateTitle()");
		String sql = "update title set titlename = ? where titleno = ?";
		int rowAffected = 0;

		try (Connection conn = MySQLJdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(2, title.getTitleNo());
			pstmt.setString(1, title.getTitleName());
			LogUtil.prnLog(pstmt);
			rowAffected = pstmt.executeUpdate();
		}

		return rowAffected;
	}

	@Override
	public String nextTitleNo() throws SQLException {
		String sql = "select max(titleno) as nextno from title";
		String nextStr = null;
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			LogUtil.prnLog(pstmt);
			if(rs.next()) {
				nextStr = String.format("T%03d", Integer.parseInt(rs.getString("nextno").substring(1)) + 1);
			}
		} catch(SQLException e1) {
			e1.printStackTrace();
		}
		return nextStr;
	}

}
