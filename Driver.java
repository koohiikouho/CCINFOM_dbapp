import java.sql.*;

public class Driver{

	static Connection runConnection(){
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connections = DriverManager.getConnection("jdbc:mysql://192.168.1.41/dbmovieRental", "user", "12345");
			return connections;
			} catch (Exception e) {
				e.printStackTrace(); // Print stack trace for debugging
				return null;
			}
		}

	static public Connection connection = runConnection();
	public static void main(String arg[]) {


		GUI gui = new GUI(connection);
		Model model = new Model(connection);
		Controller controller = new Controller(gui, model, connection);
		
	}
}
