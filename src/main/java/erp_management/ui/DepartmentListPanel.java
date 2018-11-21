package erp_management.ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import erp_management.dto.Department;

import java.awt.BorderLayout;
import java.util.List;

public class DepartmentListPanel extends JPanel {
	private JTable table;
	private List<Department> list;

	public void setLists(List<Department> list) {
		this.list = list;
	}

	public DepartmentListPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);
	}

	public void loadDatas() {
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
