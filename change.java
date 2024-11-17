import java.sql.*;
import java.util.Scanner;

public class change {

	public static void main(String[] args) {
		String url = "jdbc:mysql://192.168.1.41:3306/dbmovieRental";
		String username = "user";
		String password= "12345";
//		String url = "jdbc:mysql://localhost:3306/dbmovieRental";
//		String username = "root";
//		String password= "dl_MySQL_su";
		
		Scanner sc = new Scanner(System.in);
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connections = DriverManager.getConnection(url,username,password);
		
		// for searching and printing from table in db
		Statement statement = connections.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT movie_name FROM movies where movie_name IS NOT NULL ");
		
		while(resultSet.next()) {
			System.out.println(resultSet.getString(1));
		}
		
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
