package erp_management;

import java.awt.EventQueue;

import erp_management.ui.ERPManagementUI;

public class ERPManagementMain {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ERPManagementUI frame = new ERPManagementUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
