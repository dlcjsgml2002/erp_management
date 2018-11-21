package erp_management.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp_management.dao.TitleDao;
import erp_management.dao.TitleDaoImpl;

import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class TitleManagementUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfTitleNo;
	private JTextField tfTitleName;
	private TitleListPanel titleListPanel;
	private TitleDao titleDao;

	public TitleManagementUI() {
		titleDao = new TitleDaoImpl();
		initComponents();
	}

	private void initComponents() {
		setTitle("직책 관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 618);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 10, 10));

		JLabel lblTitleNo = new JLabel("번호");
		lblTitleNo.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblTitleNo);

		tfTitleNo = new JTextField();
		panel.add(tfTitleNo);
		tfTitleNo.setColumns(10);

		JLabel lblTitleName = new JLabel("직책명");
		lblTitleName.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblTitleName);

		tfTitleName = new JTextField();
		panel.add(tfTitleName);
		tfTitleName.setColumns(10);

		JButton btnOk = new JButton("추가");
		panel.add(btnOk);

		JButton btnCancel = new JButton("취소");
		panel.add(btnCancel);

		titleListPanel = new TitleListPanel();
		titleListPanel.setList(titleDao.selectTitleByAll());
		titleListPanel.loadDatas();

		contentPane.add(titleListPanel, BorderLayout.SOUTH);
	}

}
