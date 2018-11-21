package erp_management.ui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import erp_management.dto.Title;

public class TitleListPanel extends JPanel {
	private JTable table;
	private List<Title> list;

	public void setList(List<Title> list) {
		this.list = list;
	}

	public TitleListPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		loadDatas();
		scrollPane.setViewportView(table);
	}

	private void loadDatas() {
		table.setModel(new DefaultTableModel(getDatas(), getColumnNames()));
	}

	private Object[][] getDatas() {
		Object[][] datas = new Object[list.size()][];

		for (int i = 0; i < list.size(); i++) {
			datas[i] = getTitleRow(list.get(i));
		}

		return datas;
	}

	private Object[] getTitleRow(Title title) {
		return new Object[] { title.getTitleNo(), title.getTitleName() };
	}

	private String[] getColumnNames() {
		return new String[] { "번호", "직책" };
	}

}
