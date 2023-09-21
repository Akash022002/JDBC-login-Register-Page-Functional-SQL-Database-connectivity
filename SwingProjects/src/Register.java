import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.awt.event.ActionEvent;
//Import packege for the perform the actual SQL operation
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Register {

	public JFrame frame;
	private JTextField t1;
	private JTextField t2;
	String username;
	String password;
	

	// Database credentials
    //private static final String DB_URL = "jdbc:mysql://localhost:3306/login";
    //private static final String DB_USER = "root";
    //private static final String DB_PASSWORD = "Akash@2002";
    
    // that was the database connection was make or established in the user registration page and database for data store
    private static final String DB_URL = "jdbc:mysql://localhost:3306/akash";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Akash@2002";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
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
	public Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(224, 255, 255));
		frame.setBounds(100, 100, 579, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel main = new JLabel("Registration");
		main.setForeground(new Color(255, 0, 255));
		main.setHorizontalAlignment(SwingConstants.CENTER);
		main.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 40));
		main.setBounds(148, 29, 283, 49);
		frame.getContentPane().add(main);
		
		JLabel l1 = new JLabel("Username");
		l1.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 30));
		l1.setForeground(new Color(47, 79, 79));
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setBounds(150, 139, 283, 38);
		frame.getContentPane().add(l1);
		
		t1 = new JTextField();
		t1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 23));
		t1.setHorizontalAlignment(SwingConstants.CENTER);
		t1.setBounds(148, 187, 285, 38);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		JLabel l2 = new JLabel("Password");
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		l2.setForeground(new Color(47, 79, 79));
		l2.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 30));
		l2.setBounds(150, 235, 283, 38);
		frame.getContentPane().add(l2);
		
		t2 = new JTextField();
		t2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		t2.setHorizontalAlignment(SwingConstants.CENTER);
		t2.setColumns(10);
		t2.setBounds(148, 283, 285, 38);
		frame.getContentPane().add(t2);
		
		JButton b1 = new JButton("Submit");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Here the code for the button for store the all the register information of the user at the time of registration.
				username = t1.getText();
				password = t2.getText();
				if(username.length()>5 && password.length()>8)
				{
					StoreData(username,password);
					JOptionPane.showMessageDialog(null, "Register Sucessfully......");
					t1.setText("");
					t2.setText("");
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Enter the Strong username or password");
				}
				
				
			}
		});
		b1.setBackground(new Color(250, 250, 210));
		b1.setForeground(new Color(255, 69, 0));
		b1.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 34));
		b1.setBounds(219, 331, 154, 46);
		frame.getContentPane().add(b1);
	}
	private void StoreData(String username,String password)
	{
		// This is the code for Store your actual data into your database
		try {
			// That was the field was accept the access the MySQl database of the offline data.
			//String url = "jdbc:mysql://localhost/Akash";
			//String username = "root";
			//String password = "Akash@2002";
			
			// That was code used when you want get the input manually by the user on the console.
			// Accept the data from the user dynamically.
			//int id1;
			//String sname1;
			//String semail;
			//System.out.println("Enter the student id,name and email:");
			//Scanner s1 = new Scanner(System.in);
			//id1 = s1.nextInt();
			//sname1 = s1.next();
			//semail = s1.next();
			
			// That was establish the connection between the code and the database.
			Connection conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
			
			// Query for the add the new databases in the your databases.
			// Normal query for add an the data into database.
			//String query = "insert into studentnew (sid,sname,semail) values (11,'Akash','akashsawant020202@gmail.com')";
			// New SQL query for add the data dynamically in database.
			String query = "insert into login (username,password) values (?,?)";
			// We are create the statement of the query which was fired.
			PreparedStatement pstm = conn.prepareStatement(query);
			// get the data dynamically.
			pstm.setString(1, username);
			pstm.setString(2, password);
			pstm.executeUpdate();
			System.out.println("Data inserted Sucessfully");
			
			// Here the connection was closed by the coder for the secure connection of the database.
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
