package UserRegistration;

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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import UserLogin.LoginPage;
import Interface.HomePage;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class RegistrationPage {

	public JFrame frmregister;
	private Connection conn = null;
	private PreparedStatement pst;
	private JTextField txtFirstName;
	private JPasswordField txtpwd;
	private JPasswordField txtConfpwd;
	private JTextField txtLastName;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationPage window = new RegistrationPage();
					window.frmregister.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegistrationPage() {
		initialize();
		connect ();
		
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
        	}//* Creating Connection

        }
	
	public boolean checkemail(String email)
    {
        PreparedStatement pst;
        ResultSet rs;
        boolean checkemail = false;
        
        try {
        	pst = conn.prepareStatement("SELECT `email` FROM `register` WHERE `email`= '"+email+"';" );	
			rs = pst.executeQuery("SELECT `email` FROM `register` WHERE `email`= '"+email+"'; ");
			
            if(rs.next())
            {
                checkemail = true;
                JOptionPane.showMessageDialog(null, "This Email is Already Taken, Choose Another One", "Email Failed", 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationPage.class.getName()).log(Level.SEVERE, null, ex);
        }
         return checkemail;
         }
	
    
	

	/**
	 * Initialize the contents of the frmregister.
	 */
	private void initialize() {
		frmregister = new JFrame();
		frmregister.setTitle("REGISTER");
		frmregister.getContentPane().setBackground(new Color(0, 51, 51));
		frmregister.setBounds(100, 100, 549, 569);
		frmregister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmregister.getContentPane().setLayout(null);
		frmregister.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 140, 0));
		panel.setBounds(0, 0, 533, 81);
		frmregister.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblRegister = new JLabel("REGISTER");
		lblRegister.setFont(new Font("Britannic Bold", Font.BOLD, 27));
		lblRegister.setForeground(new Color(255, 255, 255));
		lblRegister.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegister.setBounds(187, 27, 143, 27);
		panel.add(lblRegister);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setForeground(new Color(255, 255, 255));
		lblFirstName.setFont(new Font("Britannic Bold", Font.PLAIN, 16));
		lblFirstName.setBounds(66, 147, 88, 14);
		frmregister.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setFont(new Font("Britannic Bold", Font.PLAIN, 16));
		lblLastName.setBounds(66, 199, 88, 14);
		frmregister.getContentPane().add(lblLastName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Britannic Bold", Font.PLAIN, 16));
		lblEmail.setBounds(66, 252, 72, 14);
		frmregister.getContentPane().add(lblEmail);
		
		JLabel lblpwd = new JLabel("Password");
		lblpwd.setForeground(Color.WHITE);
		lblpwd.setFont(new Font("Britannic Bold", Font.PLAIN, 16));
		lblpwd.setBounds(66, 306, 72, 14);
		frmregister.getContentPane().add(lblpwd);
		
		JLabel lblConfirmPwd = new JLabel("Confirm password");
		lblConfirmPwd.setForeground(Color.WHITE);
		lblConfirmPwd.setFont(new Font("Britannic Bold", Font.PLAIN, 16));
		lblConfirmPwd.setBounds(66, 354, 139, 14);
		frmregister.getContentPane().add(lblConfirmPwd);
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(208, 147, 235, 25);
		frmregister.getContentPane().add(txtFirstName);
		txtFirstName.setColumns(10);
		
		txtpwd = new JPasswordField();
		txtpwd.setBounds(208, 303, 235, 25);
		frmregister.getContentPane().add(txtpwd);
		
		txtConfpwd = new JPasswordField();
		txtConfpwd.setBounds(208, 351, 235, 25);
		frmregister.getContentPane().add(txtConfpwd);
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(208, 199, 235, 25);
		frmregister.getContentPane().add(txtLastName);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(208, 249, 235, 25);
		frmregister.getContentPane().add(txtEmail);
		
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.setFont(new Font("Arial", Font.BOLD, 11));
		btnRegister.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				//SUBMIT BUTTON
				String firstname, lastname,email, password, re_pass;
				firstname = txtFirstName.getText();
				lastname = txtLastName.getText();
				email = txtEmail.getText();
				password = txtpwd.getText();
	 	        re_pass = String.valueOf(txtConfpwd.getPassword());

				if(	txtFirstName.getText().equals("") || 
						txtLastName.getText().equals("") ||
						txtEmail.getText().equals("") || checkemail(email) ||
						txtpwd.getText().equals("") || 
						txtConfpwd.getText().equals("")
						) {
							JOptionPane.showMessageDialog(null,"Please check or fill the form");
					}else {

					        if(password.equals(re_pass)){
			
								try {
									pst = conn.prepareStatement("INSERT INTO `register` (`firstname`, `lastname`, `email`, `pwd`) VALUES ('"+firstname+"','"+lastname +"','"+email+"','"+password+"');");	
									pst.executeUpdate("INSERT INTO `register` (`firstname`, `lastname`, `email`, `pwd`) VALUES ('"+firstname+"','"+lastname+"','"+email+"','"+password+"');");
									JOptionPane.showMessageDialog(null,"User added successfully");
									HomePage main = new HomePage();
									main.frame.setVisible(true);
									frmregister.setVisible(false);
									
									
								}
								catch( SQLException e1) {
									System.out.println(e1);
								}
					        }
					        else
					        {
					        	JOptionPane.showMessageDialog(null,"Password does not match ! Please Confirm the password.");
					        	txtpwd.setText("");
					        	txtConfpwd.setText("");
								txtpwd.requestFocus();
					        }
							
						}
			}
		}); 
		btnRegister.setBackground(new Color(255, 140, 0));
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setFont(new Font("Britannic Bold", Font.PLAIN, 14));
		btnRegister.setBounds(201, 421, 121, 31);
		frmregister.getContentPane().add(btnRegister);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(31, 478, 476, 2);
		frmregister.getContentPane().add(separator);
		
		JLabel lblgologin = new JLabel("You Already Have An Account? click here >");
		lblgologin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginPage main = new LoginPage();
				main.frame.setVisible(true);
				frmregister.setVisible(false);
				
			}
		});
		lblgologin.setForeground(new Color(255, 140, 0));
		lblgologin.setBounds(150, 500, 216, 14);
		frmregister.getContentPane().add(lblgologin);
		
			
		}
	}


