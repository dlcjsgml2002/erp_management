package erp_management.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import erp_management.dto.Department;

public class DepartmentPanel extends JPanel {
	private JTable table;
	private List<Department> list;

	public void setLists(List<Department> list) {
		this.list = list;
	}

	public DepartmentPanel() {
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
			datas[i] = getDepartmentRow(list.get(i));
		}

		return datas;
	}

	private Object[] getDepartmentRow(Department department) {
		return new Object[] { department.getDeptNo(), department.getDeptName(), department.getFloor() };
	}

	private String[] getColumnNames() {
		return new String[] { "번호", "부서명", "위치" };
	}

}
