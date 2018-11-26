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

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import erp_management.dto.Department;
import erp_management.service.DepartmentUIService;
import erp_management.ui.list.DepartmentListPanel;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DepartmentManagementUI extends JFrame implements ActionListener {

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
		setBounds(100, 100, 350, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(450, 300));
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 10, 10));

		JLabel lblDeptNo = new JLabel("번호");
		lblDeptNo.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblDeptNo);

		tfDeptNo = new JTextField();
		tfDeptNo.setEnabled(false);
		try {
			tfDeptNo.setText(service.nextDeptNo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	// 수정
	private void do_btnUpdate_actionPerfromed(ActionEvent e) {
		Department selDept = getDepartment();
		int res = 0;

		try {
			res = service.updateDepartment(selDept);
			deptListPanel.setLists(service.selectDepartmentByAll());
			deptListPanel.loadDatas();
			if (res == 1) {
				JOptionPane.showMessageDialog(null, "수정되었습니다.");
			}
			clearTf();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		btnOk.setText("추가");
	}

	// 수정 모드로 변경
	private void do_mntmUpdate_actionPerformed(ActionEvent e) {
		Department selDept = deptListPanel.getSelectedDepartment();
		setDepartment(selDept);
		try {
			deptListPanel.setLists(service.selectDepartmentByAll());
			deptListPanel.loadDatas();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		btnOk.setText("수정");
	}

	// 값 받아서 입력창에 넣기
	private void setDepartment(Department dept) {
		tfDeptNo.setText(dept.getDeptNo());
		tfDeptName.setText(dept.getDeptName());

		tfFloor.setText(dept.getFloor() + "");
	}

	// 삭제
	protected void do_mntmDelete_actionPerformed(ActionEvent e) {
		Department selDept = deptListPanel.getSelectedDepartment();
		int res = 0;

		try {
			res = service.deleteDepartment(selDept);
			deptListPanel.setLists(service.selectDepartmentByAll());
			deptListPanel.loadDatas();
			if (res == 1) {
				JOptionPane.showMessageDialog(null, "삭제되었습니다.");
			}
		} catch (MySQLIntegrityConstraintViolationException e1) {
			if (e1.getErrorCode() == 1451) {
				JOptionPane.showMessageDialog(null, "해당 부서에 소속된 사원이 존재합니다.");
			} else {
				e1.printStackTrace();
			}
			/*
			 * JOptionPane.showMessageDialog(null, String.format("%s, %s",e1.getErrorCode(),
			 * e1.getMessage())); 에러코드를 찾아주는 마법의 코드
			 */
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	// 추가 버튼 눌렀을때
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
			clearTf();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void clearTf() {
		try {
			tfDeptNo.setText(service.nextDeptNo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tfDeptName.setText("");
		tfFloor.setText("");
	}

	private Department getDepartment() {
		String deptNo = tfDeptNo.getText().trim();
		String deptName = tfDeptName.getText().trim();
		int floor = Integer.parseInt(tfFloor.getText().trim());
		return new Department(deptNo, deptName, floor);
	}

	protected void do_btnCancel_actionPerformed(ActionEvent e) {
		dispose();
	}
}
