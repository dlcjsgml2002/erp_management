package erp_management.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.util.Date;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import erp_management.EmployeeDaoTest;
import erp_management.dto.Department;
import erp_management.dto.Employee;
import erp_management.dto.Title;
import javafx.scene.control.ComboBox;

import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

public class EmployeeListPanel extends JPanel {
	private JTable table;
	private List<Employee> list;
	private JScrollPane scrollPane;

	public void setLists(List<Employee> list) {
		this.list = list;
	}

	public EmployeeListPanel() {
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

	public void setPopupMenu(JPopupMenu popupMenu) {
		scrollPane.setComponentPopupMenu(popupMenu);
		table.setComponentPopupMenu(popupMenu);
	}

	public Employee getSelectedEmployee() {
		int selectedIndex = table.getSelectedRow();
		String empNo = (String) table.getValueAt(selectedIndex, 0);
		Employee emp = new Employee(empNo);
		return list.get(list.indexOf(emp));
		/*String empName = (String) table.getValueAt(selectedIndex, 1);
		Title title = (Title) table.getValueAt(selectedIndex, 2);
		int salary = (int) table.getValueAt(selectedIndex, 3);
		String gender = (String) table.getValueAt(selectedIndex, 4);
		Department department = (Department) table.getValueAt(selectedIndex, 5);
		Date date = (Date) table.getValueAt(selectedIndex, 6);
		return new Employee(empNo, empName, title, salary, gender, department, date);*/
	}
}
