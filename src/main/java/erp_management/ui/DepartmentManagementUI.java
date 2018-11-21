package erp_management.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import erp_management.dao.DepartmentDao;
import erp_management.dto.Department;

public class DepartmentManagementUI extends JFrame {

	private JPanel contentPane;
	private JTextField tfDeptNo;
	private JTextField tfDeptName;
	private JTextField tfFloor;
	
	private DepartmentDao deptDao;
	private DepartmentListPanel deptListPanel;
	
	private List<Department> deptLists;

	public DepartmentManagementUI() {
		initComponents();
	}
	private void initComponents() {
		setTitle("부서 관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
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
		
		JButton btnOk = new JButton("추가");
		panel.add(btnOk);
		
		JButton btnCancel = new JButton("취소");
		panel.add(btnCancel);
		
		deptListPanel = new DepartmentListPanel();
		deptListPanel.setLists(deptLists);
		deptListPanel.loadDatas();
		
		contentPane.add(deptListPanel, BorderLayout.SOUTH);
	}

}
