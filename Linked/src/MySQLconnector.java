import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class MySQLconnector {
	
	public static Connection getconnected(){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connector = DriverManager.getConnection("jdbc:mysql://localhost:3306/linkedu?useSSL=false","root","Lickpaw7");
			return connector;
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
