package erp_management.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import erp_management.dto.Employee;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

public class EmployeeListPanel extends JPanel {
	private JTable table;
	private List<Employee> list;

	public void setLists(List<Employee> list) {
		this.list = list;
	}

	public EmployeeListPanel() {
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
			datas[i] = getTitleRow(list.get(i));
		}

		return datas;
	}

	private Object[] getTitleRow(Employee employee) {
		return new Object[] { employee.getEmpNo(), employee.getEmpName(), employee.getTitle().getTitleName(),
				employee.getSalary(), employee.getGender(),
				String.format("%s(%s)", employee.getDepartment().getDeptName(), employee.getDepartment().getFloor()),
				employee.getDate() };
	}

	private String[] getColumnNames() {
		return new String[] { "번호", "사원명", "직책", "급여", "성별", "부서", "입사일" };
	}
}
