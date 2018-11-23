package erp_management.ui;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import erp_management.dto.Department;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.util.List;

public class DepartmentListPanel extends JPanel {
	private JTable table;
	private List<Department> list;
	private JScrollPane scrollPane;

	public void setLists(List<Department> list) {
		this.list = list;
	}

	public DepartmentListPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());

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

	public void setPopupMenu(JPopupMenu popupMenu) {
		scrollPane.setComponentPopupMenu(popupMenu);
		table.setComponentPopupMenu(popupMenu);
	}

	public Department getSelectedDepartment() {
		int selectedIndex = table.getSelectedRow();
		String deptNo = (String) table.getValueAt(selectedIndex, 0);
		String deptName = (String) table.getValueAt(selectedIndex, 1);
		int floor = (int) table.getValueAt(selectedIndex, 2);
		return new Department(deptNo, deptName, floor);
	}

}
