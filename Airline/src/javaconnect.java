import java.sql.*;
import javax.swing.*;
public class javaconnect {
	
		public static Connection Db()
		{
			Connection conn=null;
			try{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","airline","tiger");
						
								
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, e);
			}
			return conn;
		}
	

}
