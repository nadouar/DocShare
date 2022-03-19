package Interface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;

public class UserAccount {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserAccount window = new UserAccount();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserAccount() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Britannic Bold", Font.BOLD, 17));
		frame.getContentPane().setBackground(new Color(0, 51, 51));
		frame.setBounds(100, 100, 632, 681);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 140, 0));
		panel.setBounds(0, 0, 616, 81);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblfileshare = new JLabel("USER ACCOUNT");
		lblfileshare.setFont(new Font("Britannic Bold", Font.BOLD, 29));
		lblfileshare.setForeground(new Color(255, 255, 255));
		lblfileshare.setHorizontalAlignment(SwingConstants.LEFT);
		lblfileshare.setBounds(195, 25, 213, 27);
		panel.add(lblfileshare);
		
		JLabel lblWelcome = new JLabel("Welcome To Your Account");
		lblWelcome.setForeground(Color.WHITE);
		lblWelcome.setFont(new Font("Britannic Bold", Font.BOLD, 20));
		lblWelcome.setBounds(187, 137, 228, 14);
		frame.getContentPane().add(lblWelcome);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(SystemColor.controlHighlight);
		separator.setBounds(162, 162, 287, 2);
		frame.getContentPane().add(separator);
		
		JButton btnsendemail = new JButton("Send An Email");
		btnsendemail.setForeground(Color.DARK_GRAY);
		btnsendemail.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnsendemail.setBackground(Color.WHITE);
		btnsendemail.setBounds(418, 199, 138, 38);
		frame.getContentPane().add(btnsendemail);
		
		JList list = new JList();
		list.setBounds(29, 273, 556, 286);
		frame.getContentPane().add(list);
		
		JButton btndeleteAcc = new JButton("Delete Account");
		btndeleteAcc.setForeground(Color.DARK_GRAY);
		btndeleteAcc.setFont(new Font("Tahoma", Font.BOLD, 12));
		btndeleteAcc.setBackground(Color.WHITE);
		btndeleteAcc.setBounds(29, 593, 138, 38);
		frame.getContentPane().add(btndeleteAcc);
		
		JButton btnupdate = new JButton("Modifie Account");
		btnupdate.setForeground(Color.DARK_GRAY);
		btnupdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnupdate.setBackground(Color.WHITE);
		btnupdate.setBounds(212, 593, 138, 38);
		frame.getContentPane().add(btnupdate);
	}
}
