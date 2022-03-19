package UserLogin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Interface.HomePage;
import UserRegistration.RegistrationPage;


public class LoginPage {

	public JFrame frame;
	private Connection conn = null;
	public PreparedStatement pst;
	public ResultSet rs;
	private JTextField txtemail;
	private JPasswordField txtpwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
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
	public LoginPage() {
		initialize();
		connect();
	}
		private void connect () {

		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            System.out.println("Success to find Driver");
		        }catch(ClassNotFoundException e) {
		            System.err.println("error = Failed to find driver");
		        }

		        try {
		            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/docshare", "root", "");
		            System.out.println("Connected to MySql");
		        } catch (SQLException e) {
		            	System.err.println("error = Failed to Create connection");
		        	}

		        }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("LOGIN");
		frame.getContentPane().setBackground(new Color(0, 51, 51));
		frame.setBounds(100, 100, 549, 417);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 140, 0));
		panel.setBounds(0, 0, 533, 81);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblemail = new JLabel("Email");
		lblemail.setForeground(Color.WHITE);
		lblemail.setFont(new Font("Britannic Bold", Font.PLAIN, 16));
		lblemail.setBounds(77, 148, 72, 14);
		frame.getContentPane().add(lblemail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Britannic Bold", Font.PLAIN, 16));
		lblPassword.setBounds(60, 200, 89, 14);
		frame.getContentPane().add(lblPassword);
		
		txtemail = new JTextField();
		txtemail.setBounds(156, 146, 234, 20);
		frame.getContentPane().add(txtemail);
		txtemail.setColumns(10);
		
		txtpwd = new JPasswordField();
		txtpwd.setBounds(159, 198, 234, 20);
		frame.getContentPane().add(txtpwd);
		
		JLabel lblRegister = new JLabel("LOGIN");
		lblRegister.setFont(new Font("Britannic Bold", Font.BOLD, 27));
		lblRegister.setForeground(new Color(255, 255, 255));
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setBounds(210, 25, 78, 27);
		panel.add(lblRegister);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String email, pwd;
				email = txtemail.getText();
				pwd = txtpwd.getText();
				
				if(email.isEmpty()||pwd.isEmpty()) {
					JOptionPane.showMessageDialog(null, "the email and/or the password should not be empty", "error", JOptionPane.ERROR_MESSAGE);
				}else {
					try {
						pst = conn.prepareStatement("SELECT `email`, `pwd` FROM `register` WHERE `email`= '"+email+"' AND `pwd`= '"+pwd+"';" );	
						rs = pst.executeQuery("SELECT `email`, `pwd` FROM `register` WHERE `email`= '"+email+"' AND `pwd`= '"+pwd+"';");
						rs.next();
						if((rs.getString("pwd").equals(pwd))) {	
							
							
							HomePage main = new HomePage();
							main.frame.setVisible(true);
							frame.setVisible(false);
					}}
					catch( SQLException e1) {
						System.out.println(e1);
						JOptionPane.showMessageDialog(null, "Failed to login ! Please retry");
						txtemail.setText("");
						txtpwd.setText("");
						txtemail.requestFocus();
					} 				} 
				
				
		
			}

			
				
				
			
		});
		btnLogin.setBackground(new Color(255, 140, 0));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setFont(new Font("Britannic Bold", Font.PLAIN, 14));
		btnLogin.setBounds(222, 257, 89, 31);
		frame.getContentPane().add(btnLogin);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(26, 310, 476, 2);
		frame.getContentPane().add(separator);
		
		//to REGISTRATION
		JLabel lblNoAcc = new JLabel("You Don't Have An Account? Click here >");
		lblNoAcc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegistrationPage userregister = new RegistrationPage ();
				userregister.frmregister.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		lblNoAcc.setForeground(new Color(255, 140, 0));
		lblNoAcc.setBounds(157, 340, 203, 14);
		frame.getContentPane().add(lblNoAcc);
		

	}

}
