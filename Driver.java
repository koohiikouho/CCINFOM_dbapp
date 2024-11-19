import java.sql.*;

public class Driver{

	static Connection runConnection(){
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		//this controls which server to connect to, either use localhost (127.0.0.1:3306) or the tunnel (147.185.221.23:51100)
		Connection connections = DriverManager.getConnection("jdbc:mysql://192.168.1.41:3306/dbmovieRental", "user", "12345");
			return connections;
			} catch (Exception e) {
				e.printStackTrace(); // Print stack trace for debugging
				return null;
			}
		}

		static Connection connection = runConnection();
	public static void main(String arg[]) {
		
		GUI gui = new GUI(connection);
		Model model = new Model(connection, gui);
		Controller controller = new Controller(gui, model, connection);
		
	}
}
