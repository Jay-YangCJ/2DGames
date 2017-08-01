import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class Account extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	Account() {
		
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
		
		JButton btnMessaging = new JButton("Messaging");
		buttonDecoration(btnMessaging, 250);
		btnMessaging.addActionListener(new messagingListener());
		
		JButton btnNetworking = new JButton("Friends");
		buttonDecoration(btnNetworking, 433);
		btnNetworking.addActionListener(new networkingListener());
		
		JButton btnMe = new JButton("Me");
		buttonDecoration(btnMe, 616);
		btnMe.addActionListener(new meListener());
		
		JTextArea textArea = new JTextArea();
		Border border = BorderFactory.createStrokeBorder(new BasicStroke(1.5f));
		border = BorderFactory.createLineBorder(new Color(0, 51, 255));
	    textArea.setBorder(border);
	    textArea.setForeground(new Color(0, 0, 0));
	    textArea.setBackground(new Color(255, 255, 255));
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textArea.setBounds(167, 235, 671, 515);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setOpaque(false);
		textArea.setEditable(false);
		getContentPane().add(textArea);
		
		JLabel lblimg = new JLabel("");
		lblimg.setForeground(new Color(0, 0, 0));
		lblimg.setIcon(new ImageIcon(Login.class.getResource("/img/person.jpg")));
		lblimg.setBounds(0, 0, 1920, 1080);
		getContentPane().add(lblimg);

	}
	
	private void buttonDecoration(JButton btn, int x){
		
		btn.setBackground(new Color(255, 102, 255));
		btn.setFont(new Font("Arial", Font.PLAIN, 20));
		btn.setForeground(new Color(153, 0, 102));
		btn.setBounds(x, 165, 135, 35);
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		getContentPane().add(btn);
	}
	
	public class messagingListener implements ActionListener{
		
		public void actionPerformed(ActionEvent ev){
			
		}
	}
	
	public class networkingListener implements ActionListener{
		
		public void actionPerformed(ActionEvent ev){
			
		}
	}

	public class meListener implements ActionListener{
	
		public void actionPerformed(ActionEvent ev){
		
		}
	}
}
