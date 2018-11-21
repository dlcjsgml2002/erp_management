package erp_management.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class EmployeeManagementUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_6;
	private JTable table;

	public EmployeeManagementUI() {
		initComponents();
	}
	private void initComponents() {
		setTitle("사원 관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel label = new JLabel("번호");
		panel.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		panel.add(textField);
		
		JLabel label_1 = new JLabel("사원명");
		panel.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel.add(textField_1);
		
		JLabel label_2 = new JLabel("직책");
		panel.add(label_2);
		
		JComboBox comboBox = new JComboBox();
		panel.add(comboBox);
		
		JLabel label_3 = new JLabel("급여");
		panel.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		panel.add(textField_3);
		
		JLabel label_4 = new JLabel("성별");
		panel.add(label_4);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("남");
		rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("여");
		rdbtnNewRadioButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(rdbtnNewRadioButton_1);
		
		JLabel label_5 = new JLabel("부서");
		panel.add(label_5);
		
		JComboBox comboBox_1 = new JComboBox();
		panel.add(comboBox_1);
		
		JLabel label_6 = new JLabel("입사일");
		panel.add(label_6);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		panel.add(textField_6);
		
		JButton button = new JButton("추가");
		panel.add(button);
		
		JButton button_1 = new JButton("취소");
		panel.add(button_1);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		table = new JTable();
		panel_1.add(table);
	}

}
