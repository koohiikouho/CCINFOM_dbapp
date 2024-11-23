import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Model {

	static Connection connections;
	private GUI storeGUI;

	public Model(Connection connection, GUI guiIn){
		connections = connection;
		storeGUI = guiIn;

	}
	
	public boolean checkAdminPassCorrect(String adminname, String pass) {
	    ArrayList<Object[]> list = new ArrayList<>();
		try {
		Statement statement = connections.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT first_name, password, admin_no FROM admins");
		
		while(resultSet.next()) {
			 Object[] row = new Object[3];
             row[0] = resultSet.getString(1);   // first_name
             row[1] = resultSet.getString(2);   // password
			 row[2] = resultSet.getInt(3);	//user_no
             list.add(row); // Add the row to the list

            if(pass.equals(row[1]) && adminname.equals(row[0])){
				storeGUI.storeUser( row[2].toString() );
            	return true;
            }
       }
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
}
