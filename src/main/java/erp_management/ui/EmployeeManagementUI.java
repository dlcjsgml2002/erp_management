package erp_management.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import erp_management.dto.Department;
import erp_management.dto.Employee;
import erp_management.dto.Title;
import erp_management.service.EmployeeUIService;
import javafx.scene.control.RadioButton;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class EmployeeManagementUI extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JTextField tfEmpNo;
	private JTextField tfEmpName;
	private JTextField tfJoinDate;
	private EmployeeUIService service;
	private EmployeeListPanel empList;
	private JButton btnOk;
	private JComboBox<Title> cbEmpTitle;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnFemale;
	private JRadioButton rdbtnMale;
	private JComboBox<Department> cbEmpDept;
	private JButton btnCancel;
	private JSpinner spSalary;

	public EmployeeManagementUI() throws SQLException {
		service = new EmployeeUIService();
		initComponents();
/*		getTitleList();
		getDepartmentList();*/
	}

/*	private void getDepartmentList() throws SQLException {
		List<Department> list = service.selectDepartmentByAll();
		for (Department d : list) {
			cbEmpDept.addItem(d);
		}
	}

	private void getTitleList() throws SQLException {
		List<Title> list = service.selectTitleByAll();
		for (Title t : list) {
			cbEmpTitle.addItem(t);
		}
	}*/

	private void initComponents() throws SQLException {
		setTitle("사원 관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 10, 10));

		JLabel lblEmpNo = new JLabel("번호");
		panel.add(lblEmpNo);

		tfEmpNo = new JTextField();
		tfEmpNo.setColumns(10);
		panel.add(tfEmpNo);

		JLabel lblEmpName = new JLabel("사원명");
		panel.add(lblEmpName);

		tfEmpName = new JTextField();
		tfEmpName.setColumns(10);
		panel.add(tfEmpName);

		JLabel lblEmpTitle = new JLabel("직책");
		panel.add(lblEmpTitle);

		DefaultComboBoxModel<Title> titleModel = new DefaultComboBoxModel<>(new Vector<>(service.selectTitleByAll()));
		cbEmpTitle = new JComboBox<>(titleModel);
		panel.add(cbEmpTitle);

		JLabel lblSalary = new JLabel("급여");
		panel.add(lblSalary);

		spSalary = new JSpinner();
		spSalary.setModel(new SpinnerNumberModel(1500000, 1000000, 5000000, 100000));
		panel.add(spSalary);

		JLabel lblGender = new JLabel("성별");
		panel.add(lblGender);

		JPanel pGender = new JPanel();
		panel.add(pGender);
		pGender.setLayout(new GridLayout(0, 2, 0, 0));

		rdbtnMale = new JRadioButton("남");
		rdbtnMale.setSelected(true);
		buttonGroup.add(rdbtnMale);
		rdbtnMale.setHorizontalAlignment(SwingConstants.RIGHT);
		pGender.add(rdbtnMale);

		rdbtnFemale = new JRadioButton("여");
		buttonGroup.add(rdbtnFemale);
		rdbtnFemale.setHorizontalAlignment(SwingConstants.LEFT);
		pGender.add(rdbtnFemale);

		JLabel lblEmpDept = new JLabel("부서");
		panel.add(lblEmpDept);

		DefaultComboBoxModel<Department> deptModel = new DefaultComboBoxModel<>(new Vector<>(service.selectDepartmentByAll()));
		cbEmpDept = new JComboBox<>(deptModel);
		panel.add(cbEmpDept);

		JLabel lblJoinDate = new JLabel("입사일");
		panel.add(lblJoinDate);

		tfJoinDate = new JTextField();
		tfJoinDate.setColumns(10);
		tfJoinDate.setText(String.format("%tF", new Date()));
		panel.add(tfJoinDate);

		btnOk = new JButton("추가");
		btnOk.addActionListener(this);
		panel.add(btnOk);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		panel.add(btnCancel);

		empList = new EmployeeListPanel();
		empList.setLists(service.selectEmployeeByAll());
		empList.loadDatas();

		empList.setPopupMenu(getPopupMenu());

		contentPane.add(empList);
	}

	private JPopupMenu getPopupMenu() {
		JPopupMenu popupMenu = new JPopupMenu();

		JMenuItem mntmUpdate = new JMenuItem("수정");
		mntmUpdate.addActionListener(this);
		popupMenu.add(mntmUpdate);

		JMenuItem mntmDelete = new JMenuItem("삭제");
		mntmDelete.addActionListener(this);
		popupMenu.add(mntmDelete);

		return popupMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("삭제")) {
			do_mntmDelete_actionPerformed(e);
		}
		if (e.getActionCommand().equals("수정")) {
			if (e.getSource() == btnOk) {
				do_btnUpdate_actionPerfromed(e);
			} else {
				do_mntmUpdate_actionPerformed(e);
			}
		}
		if (e.getActionCommand().equals("취소")) {
			do_btnCancel_actionPerformed(e);
		}
		if (e.getActionCommand().equals("추가")) {
			do_btnOk_actionPerformed(e);
		}
	}

	private void do_mntmUpdate_actionPerformed(ActionEvent e) {
		Employee emp = empList.getSelectedEmployee();
		setEmployee(emp);
		
		btnOk.setText("수정");
	}

	private void setEmployee(Employee emp) {
		
		tfEmpNo.setText(emp.getEmpNo());
		tfEmpName.setText(emp.getEmpName());
		cbEmpTitle.setSelectedItem(emp.getTitle());
		spSalary.setValue(emp.getSalary());
		/*buttonGroup.setSelected(RadioButton, true);*/
		if (emp.getGender().equals("남자")) {
			rdbtnMale.setSelected(true);
		} else {
			rdbtnFemale.setSelected(true);
		}
		cbEmpDept.setSelectedItem(emp.getDepartment());
		tfJoinDate.setText(emp.getDate()+"");
	}

	private void do_btnUpdate_actionPerfromed(ActionEvent e) {
		Employee emp = getEmployee();
		int res = 0;
		
		try {
			res = service.updateEmployee(emp);
			empList.setLists(service.selectEmployeeByAll());
			empList.loadDatas();
			if (res == 1) {
				JOptionPane.showMessageDialog(null, "수정되었습니다.");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		btnOk.setText("추가");
	}

	private void do_mntmDelete_actionPerformed(ActionEvent e) {
		Employee emp = empList.getSelectedEmployee();
		int res = 0;

		try {
			res = service.deleteEmployee(emp);
			empList.setLists(service.selectEmployeeByAll());
			empList.loadDatas();
			if (res == 1) {
				JOptionPane.showMessageDialog(null, "삭제되었습니다.");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	protected void do_btnOk_actionPerformed(ActionEvent e) {
		Employee emp = getEmployee();
		int res = 0;

		try {
			res = service.insertEmployee(emp);
			empList.setLists(service.selectEmployeeByAll());
			empList.loadDatas();
			if (res == 1) {
				JOptionPane.showMessageDialog(null, "추가되었습니다.");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private Employee getEmployee() {
		String empNo = tfEmpNo.getText().trim();
		String empName = tfEmpName.getText().trim();
		Title title = (Title) cbEmpTitle.getSelectedItem();
		int salary = (int) spSalary.getValue();
		String gender = null;
		if (rdbtnMale.isSelected()) {
			gender = "남자";
		} else {
			gender = "여자";
		}
		Department department = (Department) cbEmpDept.getSelectedItem();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(tfJoinDate.getText().trim());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Employee(empNo, empName, title, salary, gender, department, date);
	}

	protected void do_btnCancel_actionPerformed(ActionEvent e) {
		dispose();
	}
}
