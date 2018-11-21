package erp_management.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import erp_management.dto.Employee;

public class EmployeeListPanel extends JPanel {
	private JTable table;
	private List<Employee> list;

	public EmployeeListPanel() {
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
			datas[i] = getTitleRow(list.get(i));
		}

		return datas;
	}

	private Object[] getTitleRow(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	private String[] getColumnNames() {
		return new String[] { "New column", "New column", "New column", "New column", "New column", "New column",
				"New column" };
	}

}
