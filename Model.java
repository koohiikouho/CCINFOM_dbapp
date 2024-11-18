import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Model {

	static Connection connections;

	public Model(Connection connection){
		connections = connection;
	}
	
	public boolean checkAdminPassCorrect(String adminname, String pass) {
	    ArrayList<Object[]> list = new ArrayList<>();
		try {
		Statement statement = connections.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT first_name, password FROM admins");
		
		while(resultSet.next()) {
			 Object[] row = new Object[3];
             row[0] = resultSet.getString(1);   // Movie Code
             row[1] = resultSet.getString(2);   // Movie Name
             list.add(row); // Add the row to the list

            if(pass.equals(row[1]) && adminname.equals(row[0])){
            	return true;
            }
//             System.out.println("first_name: " + row[0]);
//             System.out.println("password: " + row[1]);
       }
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	public static void main(String[] args) {
		PreparedStatement pstmt;
		int choice;//for condition
		
		Scanner sc = new Scanner(System.in);
		try {
		
		// for searching and printing from table in db
//		Statement statement = connections.createStatement();
//		ResultSet resultSet = statement.executeQuery("Select * from movies");
//		
//		while(resultSet.next()) {
//			System.out.println(resultSet.getInt(1) +" "+ resultSet.getString(2) +" "+ resultSet.getInt(3)+" "+ resultSet.getString(4)+" "+ resultSet.getString(5) +" "+ resultSet.getInt(6));
//		}
		choice=0;
		
		do {
			System.out.println("[1] Insert in Admin Table");
			System.out.println("[2] Insert in Genre Type Table");
			System.out.println("[3] Insert in Media Type Table");
			System.out.println("[4] Insert in Movie Request Table");
			System.out.println("[5] Insert in Movies Table");
			System.out.println("[6] Insert in Review Table");
			System.out.println("[7] Insert in Transactions Table");
			System.out.println("[8] Insert in Users Table");
			System.out.println("[9] EXIT");
			System.out.print("Enter your choice:");
			choice = sc.nextInt();
			
			switch(choice) {
			case 1: 
				//admin table
				int admin_no1, admin_level1;
				String first_name1, last_name1, admin_password1;
				
				String InsertAdmin = " insert into admins (admin_no, first_name, last_name, password, admin_level)"
					    + " values (?, ?, ?, ?, ?)";
				pstmt = connections.prepareStatement(InsertAdmin);
				
				System.out.print("Enter admin number:");
				admin_no1 = sc.nextInt();
				sc.nextLine();
				System.out.print("Enter admin first name:");
				first_name1 = sc.nextLine();
				System.out.print("Enter admin last name:");
				last_name1 = sc.nextLine();
				System.out.print("Enter admin password:");
				admin_password1 = sc.nextLine();
				System.out.print("Enter admin level:");
				admin_level1 = sc.nextInt();
				
				pstmt.setInt(1,admin_no1);
				pstmt.setString(2, first_name1);
				pstmt.setString(3,last_name1);
				pstmt.setString(4, admin_password1);
				pstmt.setInt(5, admin_level1);
				pstmt.execute();
				break; 
				
				
			case 2: 
				//genre type table 
				int genre_id2;
				String description2;
				
				String InsertGenreType = " insert into genre_type (genre_id, description)"
					    + " values (?, ?)";
				pstmt = connections.prepareStatement(InsertGenreType);
				
				System.out.print("Enter genre id:");
				genre_id2 = sc.nextInt();
				sc.nextLine();
				System.out.print("Enter description:");
				description2 = sc.nextLine();
				
				pstmt.setInt(1,genre_id2);
				pstmt.setString(2, description2);
				pstmt.execute();
				
				break;
				
				
			case 3: 
				//media type 
				int product_id3,movie_code3; 
				
				
				String InsertMediaType = " insert into media_type (product_id, movie_code, availability, release_date, media_type, copies_available, rental_price)"
					    + " values (?, ?, ?, ?, ?, ?, ?)";
				pstmt = connections.prepareStatement(InsertMediaType);
				
				System.out.print("Enter admin number:");
				admin_no1 = sc.nextInt();
				sc.nextLine();
				System.out.print("Enter admin first name:");
				first_name1 = sc.nextLine();
				System.out.print("Enter admin last name:");
				last_name1 = sc.nextLine();
				System.out.print("Enter admin password:");
				admin_password1 = sc.nextLine();
				System.out.print("Enter admin level:");
				admin_level1 = sc.nextInt();
				
				pstmt.setInt(1,admin_no1);
				pstmt.setString(2, first_name1);
				pstmt.setString(3,last_name1);
				pstmt.setString(4, admin_password1);
				pstmt.setInt(5, admin_level1);
				pstmt.execute();
				
				break;
				
			case 4:
				//movie req table
				String InsertMovieReq = " insert into movie_req (request_number, movie_name, date_filed, user_no, approved, in_stock, media_type)"
					    + " values (?, ?, ?, ?, ?, ?, ?)";
				pstmt = connections.prepareStatement(InsertMovieReq);
				
				pstmt.execute();
				break;
				
			case 5: 
				// movies table
				String InsertMovies = " insert into movies (movie_code, movie_name, year, rating, language, genre_id)"
					    + " values (?, ?, ?, ?, ?, ?)";
				 pstmt = connections.prepareStatement(InsertMovies);
			//		pstmt.setInt(1,3);
			//		pstmt.setString(2, "Avengers");
			//		pstmt.setInt(3,2012);
			//		pstmt.setString(4, "E");
			//		pstmt.setString(5, "en");
			//		pstmt.setInt(6, 2);
				 
				 pstmt.execute();
				 
				 break;
				 
			case 6: 
				// review table
				String InsertReview = " insert into review (review_no, stars, review, movie_code, user_no)"
					    + " values (?, ?, ?, ?, ?)";
				pstmt = connections.prepareStatement(InsertReview);
				
				pstmt.execute();
				
				break;
				
			case 7:
				//transaction table
				String InsertTransaction = " insert into transactions (transaction_no, movie_code, date_borrowed, date_toreturn, date_returned, payment, admin_no)"
					    + " values (?, ?, ?, ?, ?, ?, ?)";
				 pstmt = connections.prepareStatement(InsertTransaction);
				 
				 pstmt.execute();
				 
				 break;
				 
			case 8: 
				 //user table
				 String InsertUser = " insert into users (user_no, first_name, last_name, birthday, password)"
						    + " values (?, ?, ?, ?, ?)";
				 pstmt = connections.prepareStatement(InsertUser);
					 
				 pstmt.execute();
					 
				break;
				
			case 9: break;
			}

		}while(choice!=9);


	        
		
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
