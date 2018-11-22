package erp_management.ui;

import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import erp_management.dto.Department;
import erp_management.service.DepartmentUIService;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DepartmentManagementUI extends JFrame implements ActionListener {
	private static final String ADD = "추가";
	private static final String UPDATE = "수정";
	private static final String DELETE = "삭제";

	private JPanel contentPane;
	private JTextField tfDeptNo;
	private JTextField tfDeptName;
	private JTextField tfFloor;

	private DepartmentUIService service;
	private DepartmentListPanel deptListPanel;
	private JButton btnOk;
	private JButton btnCancel;

	public DepartmentManagementUI() throws SQLException {
		service = new DepartmentUIService();
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
		deptListPanel.setLists(service.selectDepartmentByAll());
		deptListPanel.loadDatas();

		deptListPanel.setPopupMenu(getPopupMenu());

		contentPane.add(deptListPanel);
	}

	private JPopupMenu getPopupMenu() {
		JPopupMenu popupMenu = new JPopupMenu();

		JMenuItem mntmUpdate = new JMenuItem(UPDATE);
		mntmUpdate.addActionListener(this);
		popupMenu.add(mntmUpdate);

		JMenuItem mntmDelete = new JMenuItem(DELETE);
		mntmDelete.addActionListener(this);
		popupMenu.add(mntmDelete);

		return popupMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == DELETE) {
			do_mntmDelete_actionPerformed(e);
		}
		if (e.getSource() == btnCancel) {
			do_btnCancel_actionPerformed(e);
		}
		if (e.getSource() == btnOk) {
			do_btnOk_actionPerformed(e);
		}
	}

	protected void do_mntmDelete_actionPerformed(ActionEvent e) {
		try {
			Department selectedDept = deptListPanel.getSelectedDepartment();
			Department searchDept = service.;
			service.deleteDepartment(selectedDept);
			deptListPanel.setLists(service.selectDepartmentByAll());
			deptListPanel.loadDatas();
		} catch(Exception e1) {
			if (deptListPanel.size() == null) {
				JOptionPane.showMessageDialog(null, "학생 정보가 없습니다.");
				return;
			}
			JOptionPane.showMessageDialog(null, "삭제하고자");
		}
	}

	protected void do_btnOk_actionPerformed(ActionEvent e) {
		Department dept = getDepartment();
		int res = 0;
		try {
			res = service.insertDepartment(dept);
			deptListPanel.setLists(service.selectDepartmentByAll());
			deptListPanel.loadDatas();
			if (res == 1) {
				JOptionPane.showMessageDialog(null, "추가되었습니다.");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private Department getDepartment() {
		String deptNo = tfDeptNo.getText().trim();
		String deptName = tfDeptName.getText().trim();
		int floor = Integer.parseInt(tfFloor.getText().trim());
		return new Department(deptNo, deptName, floor);
	}

	protected void do_btnCancel_actionPerformed(ActionEvent e) {

	}
}
