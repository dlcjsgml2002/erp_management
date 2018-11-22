package erp_management.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import erp_management.dao.DepartmentDao;
import erp_management.dao.DepartmentDaoImpl;
import erp_management.dto.Department;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DepartmentManagementUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfDeptNo;
	private JTextField tfDeptName;
	private JTextField tfFloor;

	private DepartmentDao deptDao;
	private DepartmentListPanel deptListPanel;
	private JButton btnOk;
	private JButton btnCancel;

	public DepartmentManagementUI() throws SQLException {
		deptDao = new DepartmentDaoImpl();
		initComponents();
	}

	private void initComponents() throws SQLException {
		setTitle("부서 관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(450, 300));
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 10, 10));

		JLabel lblDeptNo = new JLabel("번호");
		lblDeptNo.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblDeptNo);

		tfDeptNo = new JTextField();
		panel.add(tfDeptNo);
		tfDeptNo.setColumns(10);

		JLabel lblDeptName = new JLabel("부서명");
		lblDeptName.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblDeptName);

		tfDeptName = new JTextField();
		panel.add(tfDeptName);
		tfDeptName.setColumns(10);

		JLabel lblFloor = new JLabel("위치");
		lblFloor.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblFloor);

		tfFloor = new JTextField();
		panel.add(tfFloor);
		tfFloor.setColumns(10);

		btnOk = new JButton("추가");
		btnOk.addActionListener(this);
		panel.add(btnOk);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		panel.add(btnCancel);

		deptListPanel = new DepartmentListPanel();
		deptListPanel.setLists(deptDao.selectDepartmentByAll());
		deptListPanel.loadDatas();

		contentPane.add(deptListPanel);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			do_btnCancel_actionPerformed(e);
		}
		if (e.getSource() == btnOk) {
			do_btnOk_actionPerformed(e);
		}
	}
	protected void do_btnOk_actionPerformed(ActionEvent e) {
		
	}
	
	protected void do_btnCancel_actionPerformed(ActionEvent e) {
		
	}
}
