import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Login extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextField firstnameField;
	private JTextField lastnameField;
	private JTextField EphoneField;
	private JPasswordField npasswordField;
	private Connection connector = null;
	
	/**
	 * Create the frame.
	 */
	Login() {
		
		initialize();
	}
	
	private void initialize(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/icon.jpg")));
		setBounds(100, 100, 1300, 800);
		getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Linked");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 70));
		lblTitle.setBounds(97, 63, 226, 72);
		getContentPane().add(lblTitle);
		
		JLabel lblC = new JLabel("c:");
		lblC.setForeground(new Color(255, 153, 255));
		lblC.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblC.setBounds(320, 63, 18, 17);
		getContentPane().add(lblC);
		
		JLabel lblRegister = new JLabel("Sign Up");
		lblRegister.setForeground(new Color(51, 255, 153));
		lblRegister.setFont(new Font("Arial", Font.BOLD, 45));
		lblRegister.setBounds(252, 190, 168, 53);
		getContentPane().add(lblRegister);
		
		JLabel lblsubtitle = new JLabel("It's free and always be");
		lblsubtitle.setForeground(new Color(0, 0, 0));
		lblsubtitle.setFont(new Font("Arial", Font.PLAIN, 20));
		lblsubtitle.setBounds(252, 254, 200, 45);
		getContentPane().add(lblsubtitle);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(51, 255, 153));
		lblUsername.setFont(new Font("Arial", Font.BOLD, 20));
		lblUsername.setBounds(425, 69, 100, 24);
		getContentPane().add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setBackground(new Color(255, 255, 204));
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		usernameField.setBounds(425, 94, 176, 31);
		getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(51, 255, 153));
		lblPassword.setFont(new Font("Arial", Font.BOLD, 20));
		lblPassword.setBounds(664, 69, 100, 24);
		getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setColumns(10);
		passwordField.setBackground(new Color(255, 255, 204));
		passwordField.setBounds(664, 94, 176, 31);
		getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Log In");
		btnLogin.setForeground(new Color(255, 51, 255));
		btnLogin.setFont(new Font("Arial", Font.PLAIN, 22));
		btnLogin.setBackground(new Color(255, 51, 255));
		btnLogin.setBounds(883, 92, 100, 35);
		btnLogin.setOpaque(false);
		btnLogin.setContentAreaFilled(false);
		getContentPane().add(btnLogin);
		btnLogin.addActionListener(new LoginListener());
		
		firstnameField = new HintTextField("First Name");
		firstnameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		firstnameField.setColumns(10);
		firstnameField.setBackground(new Color(255, 255, 204));
		firstnameField.setBounds(252, 326, 176, 35);
		getContentPane().add(firstnameField);
		
		lastnameField = new HintTextField("Last Name");
		lastnameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lastnameField.setColumns(10);
		lastnameField.setBackground(new Color(255, 255, 204));
		lastnameField.setBounds(484, 326, 176, 35);
		getContentPane().add(lastnameField);
		
		EphoneField = new HintTextField("Email address or Mobile Phone number");
		EphoneField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		EphoneField.setColumns(10);
		EphoneField.setBackground(new Color(255, 255, 204));
		EphoneField.setBounds(252, 388, 409, 35);
		getContentPane().add(EphoneField);
		
		npasswordField = new HintPasswordField("New Password");
		npasswordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		npasswordField.setColumns(10);
		npasswordField.setBackground(new Color(255, 255, 204));
		npasswordField.setBounds(252, 455, 409, 35);
		getContentPane().add(npasswordField);
		
		JButton btnSignUp = new JButton("Create Account");
		btnSignUp.setOpaque(false);
		btnSignUp.setContentAreaFilled(false);
		btnSignUp.setForeground(new Color(255, 51, 255));
		btnSignUp.setFont(new Font("Arial", Font.PLAIN, 25));
		btnSignUp.setBackground(new Color(255, 51, 255));
		btnSignUp.setBounds(355, 513, 215, 42);
		getContentPane().add(btnSignUp);
		btnSignUp.addActionListener(new SignupListener());
		
		JLabel lblimg = new JLabel("");
		lblimg.setIcon(new ImageIcon(Login.class.getResource("/img/login.jpg")));
		lblimg.setBounds(0, 0, 1920, 1080);
		getContentPane().add(lblimg);
		
	}
	
	public class LoginListener implements ActionListener{
		
		public void actionPerformed(ActionEvent ev){
			
			try {
				
				connector = MySQLconnector.getconnected();
				String query = "select * from user where email=? and password=?";
				PreparedStatement statement = connector.prepareStatement(query);
				statement.setString(1, usernameField.getText());
				statement.setString(2, String.valueOf(passwordField.getPassword()));
				ResultSet result = statement.executeQuery();
				
				int count = 0;
				while(result.next())
					count++;
				
				if(usernameField.getText().isEmpty() || passwordField.getPassword().length == 0)
					
					JOptionPane.showMessageDialog(null, "Please fill out all required fields");
				
				else{
					
					if(count == 1){
						
						query = "select verification from user where Email= '" + usernameField.getText() + "'";
						statement = connector.prepareStatement(query);
						result = statement.executeQuery();
						result.next();
						
						if(result.getInt(1) == 0)
							JOptionPane.showMessageDialog(null, "Your account is not activated yet");
							
						else{
							
							dispose();
							Account account = new Account();
							account.setVisible(true);
						}
					}
					
					else
						JOptionPane.showMessageDialog(null, "Your username or password does not match what we have on file");
				}
				
				result.close();
				statement.close();
				
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}
	
	public class SignupListener implements ActionListener{
		
		public void actionPerformed(ActionEvent ev){
			
			connector = MySQLconnector.getconnected();
			PreparedStatement statement;
			String query;
			ResultSet result;
			
			try{
				
				query = "select * from user where Email=?";
				statement = connector.prepareStatement(query);
				statement.setString(1, EphoneField.getText());
				result = statement.executeQuery();
				int count = 0;
				
				while(result.next())
					count++;
				
				if(npasswordField.getPassword().length == 0 || firstnameField.getText().isEmpty() 
					|| lastnameField.getText().isEmpty() || EphoneField.getText().isEmpty())
					
					JOptionPane.showMessageDialog(null, "Please fill out all required fields");
				
				else{
					
					if(count == 0){
						
						query = "insert into user (email, password, firstname, lastname)" + "value(?,?,?,?)";
						statement = connector.prepareStatement(query);
						statement.setString(1, EphoneField.getText());
						statement.setString(2, String.valueOf(npasswordField.getPassword()));
						statement.setString(3, firstnameField.getText());
						statement.setString(4, lastnameField.getText());
						statement.execute();
						
						sendEmail sendemail = new sendEmail();
						sendemail.send(EphoneField.getText(), firstnameField.getText());
						
						dispose();
						SignupVerification signupVerification = new SignupVerification();
						signupVerification.setVisible(true);						
						signupVerification.setEmail(EphoneField.getText());
						signupVerification.setCode(sendemail.getVCode());
						
					}
					
					else
						JOptionPane.showMessageDialog(null, "Your Email address is already registered");
				}
					
				result.close();
				statement.close();
				
			} catch (Exception e){
				
				JOptionPane.showMessageDialog(null, e);
			}  
		}
	}
}
