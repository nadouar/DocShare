package Interface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import net.proteanit.sql.DbUtils;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import UserRegistration.RegistrationPage;
import Interface.User;
import javax.swing.JTextArea;

public class HomePage {

	public JFrame frame;
	private JTextField txtFrom;
	private JTextField txtSub;
    final File[] fileToSend = new File[1];
    private JTextField txtchoosefile;
 	private Connection conn = null;
	private PreparedStatement pst;
	private ResultSet rs;
	private JTable tableshare;
	private DefaultTableModel model;
	private JTextField txtsearsh;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage();
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
	
	public HomePage() {
		initialize();
		connect();
		findUsers();

	}
	
    String query = "SELECT `firstname`, `lastname`, `email` FROM register";

	
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

	public ArrayList<User> ListUsers(String query)
    {
        ArrayList<User> usersList = new ArrayList<User>();
        
        try{
        	pst = conn.prepareStatement("SELECT `firstname`, `lastname`, `email` FROM register");
			rs = pst.executeQuery();
            User user;
            while(rs.next())
            {
                user = new User(
                             rs.getString("firstname"),
                             rs.getString("lastname"),
                             rs.getString("email")
                               );
                usersList.add(user);
            }
        }catch(Exception ex)
        {

            System.out.println(ex.getMessage());
        }
        
        return usersList;
        
    }
    
	 public void findUsers()
	    {
	        ArrayList<User> users = ListUsers(query);
	        DefaultTableModel model = new DefaultTableModel();
	        model.setColumnIdentifiers(new Object[]{"firstname","lastname","email"});
	        Object[] row = new Object[3];
	        
	        for(int i = 0; i < users.size(); i++)
	        {
	            row[0] = users.get(i).getfirstname();
	            row[1] = users.get(i).getlastname();
	            row[2] = users.get(i).getemail();
	            model.addRow(row);
	        }
	        tableshare.setModel(model);
	       
	    }
  
	 
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Britannic Bold", Font.BOLD, 17));
		frame.getContentPane().setBackground(new Color(0, 51, 51));
		frame.setBounds(100, 100, 632, 697);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 140, 0));
		panel.setBounds(0, 0, 616, 81);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblfileshare = new JLabel("FILE SHARE");
		lblfileshare.setFont(new Font("Britannic Bold", Font.BOLD, 29));
		lblfileshare.setForeground(new Color(255, 255, 255));
		lblfileshare.setHorizontalAlignment(SwingConstants.LEFT);
		lblfileshare.setBounds(226, 26, 175, 27);
		panel.add(lblfileshare);
		
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setForeground(Color.WHITE);
		lblFrom.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblFrom.setBackground(Color.WHITE);
		lblFrom.setBounds(65, 121, 46, 14);
		frame.getContentPane().add(lblFrom);
		
		JLabel lblSub = new JLabel("Subject");
		lblSub.setForeground(Color.WHITE);
		lblSub.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSub.setBackground(Color.WHITE);
		lblSub.setBounds(48, 173, 80, 14);
		frame.getContentPane().add(lblSub);
		
		JLabel lblDiscription = new JLabel("discription");
		lblDiscription.setForeground(Color.WHITE);
		lblDiscription.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDiscription.setBackground(Color.WHITE);
		lblDiscription.setBounds(48, 248, 97, 14);
		frame.getContentPane().add(lblDiscription);
		
		txtFrom = new JTextField();
		txtFrom.setColumns(10);
		txtFrom.setBounds(177, 115, 377, 30);
		frame.getContentPane().add(txtFrom);
		
		txtSub = new JTextField();
		txtSub.setColumns(10);
		txtSub.setBounds(177, 167, 377, 30);
		frame.getContentPane().add(txtSub);
		
		txtchoosefile = new JTextField();
		txtchoosefile.setBounds(177, 370, 262, 30);
		frame.getContentPane().add(txtchoosefile);
		txtchoosefile.setColumns(10);
		
		JTextPane txtpaneDiscription = new JTextPane();
		txtpaneDiscription.setBounds(177, 217, 375, 130);
		frame.getContentPane().add(txtpaneDiscription);
			
		JButton jbChooseFile = new JButton("Choose File");
		  jbChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		                JFileChooser jFileChooser = new JFileChooser();
		                jFileChooser.setDialogTitle("Choose File");
		                if (jFileChooser.showOpenDialog(null)== JFileChooser.APPROVE_OPTION) {
		                    fileToSend[0] = jFileChooser.getSelectedFile();
		                    txtchoosefile.setText(fileToSend[0].getName());
		                }
            }
        });
		  jbChooseFile.setFont(new Font("Tahoma", Font.BOLD, 12));
		  jbChooseFile.setForeground(Color.DARK_GRAY);
		  jbChooseFile.setBackground(Color.WHITE);
		  jbChooseFile.setBounds(452, 373, 102, 23);
		frame.getContentPane().add(jbChooseFile);
		
		JButton btnShareWith = new JButton("Share with ...");
		btnShareWith.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnShareWith.setForeground(Color.DARK_GRAY);
		btnShareWith.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnShareWith.setBackground(Color.WHITE);
		btnShareWith.setBounds(477, 607, 115, 30);
		frame.getContentPane().add(btnShareWith);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 507, 422, 130);
		frame.getContentPane().add(scrollPane);
		
		tableshare = new JTable();
		tableshare.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				tableshare.setModel(model);
				findUsers();
			}
		});

		tableshare.setBackground(Color.WHITE);
		
	scrollPane.setViewportView(tableshare);
		
		txtsearsh = new JTextField();
		txtsearsh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        DefaultTableModel table = (DefaultTableModel)tableshare.getModel();
		        String search =txtsearsh.getText().toLowerCase();
		        TableRowSorter<DefaultTableModel> tr= new TableRowSorter<DefaultTableModel>(table);
		        tableshare.setRowSorter(tr);
		        tr.setRowFilter(RowFilter.regexFilter(search));

			}
		});
		txtsearsh.setBounds(155, 465, 302, 20);
		frame.getContentPane().add(txtsearsh);
		txtsearsh.setColumns(10);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setForeground(Color.WHITE);
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblSearch.setBackground(Color.WHITE);
		lblSearch.setBounds(48, 456, 80, 30);
		frame.getContentPane().add(lblSearch);

			
	}
}