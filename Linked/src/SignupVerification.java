import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SignupVerification extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField codeField;
	private String email;
	private String Vcode;
	/**
	 * Create the frame.
	 */
	SignupVerification() {
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
		
		JLabel lblEnterSecurityCode = new JLabel("Enter Security Code");
		lblEnterSecurityCode.setFont(new Font("Arial", Font.BOLD, 25));
		lblEnterSecurityCode.setBounds(164, 222, 247, 51);
		getContentPane().add(lblEnterSecurityCode);
		
		JLabel lbltext = new JLabel();
		lbltext.setFont(new Font("Arial", Font.PLAIN, 20));
		lbltext.setText("<html>"+"Please check your email for a message with your code. Your code is 6 numbers long."+"</html>");
		lbltext.setBounds(164, 310, 632, 72);
		lbltext.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160)));
		getContentPane().add(lbltext);
		
		codeField = new HintTextField("Enter Code");
		codeField.setFont(new Font("Arial", Font.PLAIN, 18));
		codeField.setBounds(164, 425, 139, 40);
		codeField.setColumns(6);
		codeField.setOpaque(false);
		getContentPane().add(codeField);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.setOpaque(false);
		btnContinue.setContentAreaFilled(false);
		btnContinue.setForeground(new Color(0, 0, 204));
		btnContinue.setFont(new Font("Arial", Font.PLAIN, 25));
		btnContinue.setBackground(new Color(0, 0, 204));
		btnContinue.setBounds(370, 424, 139, 41);
		getContentPane().add(btnContinue);
		btnContinue.addActionListener(new ContinueListener());
		
		JLabel lblimg = new JLabel("");
		lblimg.setForeground(new Color(0, 0, 0));
		lblimg.setIcon(new ImageIcon(SignupVerification.class.getResource("/img/signupVerification.jpg")));
		lblimg.setBounds(0, 0, 1920, 1080);
		getContentPane().add(lblimg);
		
	}
	
	public class ContinueListener implements ActionListener{
		
		public void actionPerformed(ActionEvent ev){
				
			try {
				
				if(codeField.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Please enter confirmation code");
				
				else{
					
					if(codeField.getText().equals(Vcode)){
					
						try {
							
							Connection connector = MySQLconnector.getconnected();
							String query = "Update user set verification = '1' where email= '" + email + "'";
							PreparedStatement statement = connector.prepareStatement(query);
							statement.executeUpdate();
							statement.close();
						
						} catch(Exception e) {
							JOptionPane.showMessageDialog(null, e);
						}
						
						dispose();
						Login login = new Login();
						login.setVisible(true);
					}
			
					else
						JOptionPane.showMessageDialog(null, "Please enter correct confirmation code"); 
				} 
			
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, e);
			} 
		}
	}
	
	public void setEmail(String e){
		email = e;
	}
		
	public void setCode(String c){
		Vcode = c;
	}
}