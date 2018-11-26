package erp_management.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import erp_management.dto.Title;
import erp_management.service.TitleUIService;
import erp_management.ui.list.TitleListPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TitleManagementUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfTitleNo;
	private JTextField tfTitleName;
	private TitleListPanel titleListPanel;
	private TitleUIService service; // 서비스로 변경할 것
	private JButton btnOk;
	private JButton btnCancel;

	public TitleManagementUI() throws SQLException {
		service = new TitleUIService();
		initComponents();
	}

	private void initComponents() throws SQLException {
		setTitle("직책 관리");
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

		JLabel lblTitleNo = new JLabel("번호");
		lblTitleNo.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblTitleNo);

		tfTitleNo = new JTextField();
		tfTitleNo.setEnabled(false);
		try {
			tfTitleNo.setText(service.nextTitleNo());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		panel.add(tfTitleNo);
		tfTitleNo.setColumns(10);

		JLabel lblTitleName = new JLabel("직책명");
		lblTitleName.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblTitleName);

		tfTitleName = new JTextField();
		panel.add(tfTitleName);
		tfTitleName.setColumns(10);

		btnOk = new JButton("추가");
		btnOk.addActionListener(this);
		panel.add(btnOk);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		panel.add(btnCancel);

		titleListPanel = new TitleListPanel();
		titleListPanel.setList(service.selectTitleByAll());
		titleListPanel.loadDatas();

		titleListPanel.setPopupMenu(getPopupMenu());

		contentPane.add(titleListPanel);
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
		if (e.getActionCommand() == "취소") {
			do_btnCancel_actionPerformed(e);
		}
		if (e.getActionCommand() == "추가") {
			do_btnOk_actionPerformed(e);
		}
	}

	private void do_btnUpdate_actionPerfromed(ActionEvent e) {
		Title selTitle = getTitleByAll();
		int res = 0;

		try {
			res = service.updateTitle(selTitle);
			titleListPanel.setList(service.selectTitleByAll());
			titleListPanel.loadDatas();
			if (res == 1) {
				JOptionPane.showMessageDialog(null, "수정되었습니다.");
			}
			clearTf();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		btnOk.setText("추가");
	}

	private void do_mntmUpdate_actionPerformed(ActionEvent e) {
		Title selTitle = titleListPanel.getSelectedTitle();
		setTitle(selTitle);
		try {
			titleListPanel.setList(service.selectTitleByAll());
			titleListPanel.loadDatas();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		btnOk.setText("수정");
	}

	private void setTitle(Title title) {
		tfTitleNo.setText(title.getTitleNo());
		tfTitleName.setText(title.getTitleName());
	}

	protected void do_mntmDelete_actionPerformed(ActionEvent e) {
		Title selTitle = titleListPanel.getSelectedTitle();
		int res = 0;

		try {
			res = service.deleteTitle(selTitle);
			titleListPanel.setList(service.selectTitleByAll());
			titleListPanel.loadDatas();
			if (res == 1) {
				JOptionPane.showMessageDialog(null, "삭제되었습니다.");
			}
		} catch (MySQLIntegrityConstraintViolationException e1) {
			if (e1.getErrorCode() == 1451) {
				JOptionPane.showMessageDialog(null, "해당 직급에 해당하는 사원이 존재합니다.");
			} else {
				e1.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	protected void do_btnOk_actionPerformed(ActionEvent e) {
		Title title = getTitleByAll();
		int res = 0;

		try {
			res = service.insertTitle(title);
			titleListPanel.setList(service.selectTitleByAll());
			titleListPanel.loadDatas();
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
			tfTitleNo.setText(service.nextTitleNo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tfTitleName.setText("");
	}

	private Title getTitleByAll() {
		String titleNo = tfTitleNo.getText().trim();
		String titleName = tfTitleName.getText().trim();
		return new Title(titleNo, titleName);
	}

	protected void do_btnCancel_actionPerformed(ActionEvent e) {
		dispose();
	}
}
