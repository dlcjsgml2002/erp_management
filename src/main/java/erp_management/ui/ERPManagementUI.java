package erp_management.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;

public class ERPManagementUI extends JFrame {

	private JPanel contentPane;

	public ERPManagementUI() {
		setTitle("ERP 관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 3, 10, 10));
		
		JButton btnNewButton = new JButton("사원 관리");
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("부서 관리");
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("직책 관리");
		contentPane.add(btnNewButton_2);
	}

}
