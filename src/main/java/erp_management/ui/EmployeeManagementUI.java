package erp_management.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp_management.dao.EmployeeDao;
import erp_management.service.OutputService;

import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeeManagementUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfEmpNo;
	private JTextField tfEmpName;
	private JTextField tfSalary;
	private JTextField tfJoinDate;
	private JTable table;
	private OutputService service;
	private EmployeeListPanel empList;
	private EmployeeDao empDao;
	private JButton btnOk;

	public EmployeeManagementUI() throws SQLException {
		service = new OutputService();
		initComponents();
	}
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
		
		JComboBox cbEmpTitle = new JComboBox();
		panel.add(cbEmpTitle);
		
		JLabel lblSalary = new JLabel("급여");
		panel.add(lblSalary);
		
		tfSalary = new JTextField();
		tfSalary.setColumns(10);
		panel.add(tfSalary);
		
		JLabel lblGender = new JLabel("성별");
		panel.add(lblGender);
		
		JPanel pGender = new JPanel();
		panel.add(pGender);
		pGender.setLayout(new GridLayout(0, 2, 0, 0));
		
		JRadioButton rdbtnMale = new JRadioButton("남");
		rdbtnMale.setHorizontalAlignment(SwingConstants.RIGHT);
		pGender.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("여");
		rdbtnFemale.setHorizontalAlignment(SwingConstants.LEFT);
		pGender.add(rdbtnFemale);
		
		JLabel lblEmpDept = new JLabel("부서");
		panel.add(lblEmpDept);
		
		JComboBox cbEmpDept = new JComboBox();
		panel.add(cbEmpDept);
		
		JLabel lblJoinDate = new JLabel("입사일");
		panel.add(lblJoinDate);
		
		tfJoinDate = new JTextField();
		tfJoinDate.setColumns(10);
		panel.add(tfJoinDate);
		
		btnOk = new JButton("추가");
		btnOk.addActionListener(this);
		panel.add(btnOk);
		
		JButton btnCancel = new JButton("취소");
		panel.add(btnCancel);
		
		empList = new EmployeeListPanel();
		empList.setLists(service.selectEmployeeByAll());
		empList.loadDatas();
		
		contentPane.add(empList);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnOk) {
			do_btnOk_actionPerformed(e);
		}
	}
	protected void do_btnOk_actionPerformed(ActionEvent e) {
	}
}
