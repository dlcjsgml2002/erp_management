package erp_management.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ERPManagementUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnEmp;
	private JButton btnDept;
	private JButton btnTitle;

	public ERPManagementUI() {
		setTitle("ERP 관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 3, 10, 10));
		
		btnEmp = new JButton("사원 관리");
		btnEmp.addActionListener(this);
		contentPane.add(btnEmp);
		
		btnDept = new JButton("부서 관리");
		btnDept.addActionListener(this);
		contentPane.add(btnDept);
		
		btnTitle = new JButton("직책 관리");
		btnTitle.addActionListener(this);
		contentPane.add(btnTitle);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnTitle) {
			do_btnTitle_actionPerformed(e);
		}
		if (e.getSource() == btnDept) {
			try {
				do_btnDept_actionPerformed(e);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == btnEmp) {
			try {
				do_btnEmp_actionPerformed(e);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	protected void do_btnEmp_actionPerformed(ActionEvent e) throws SQLException {
		EmployeeManagementUI employeeManagementUI = new EmployeeManagementUI();
		employeeManagementUI.setVisible(true);
	}
	protected void do_btnDept_actionPerformed(ActionEvent e) throws SQLException {
		DepartmentManagementUI departmentManagementUI = new DepartmentManagementUI();
		departmentManagementUI.setVisible(true);
	}
	protected void do_btnTitle_actionPerformed(ActionEvent e) {
		TitleManagementUI titleManagementUI = new TitleManagementUI();
		titleManagementUI.setVisible(true);
	}
}
