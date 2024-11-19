import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.mysql.cj.result.Row;

public class Controller implements ActionListener, DocumentListener{
	private GUI gui;
	private Model model;
	private static Connection connections;
	
	public Controller(GUI gui, Model model, Connection connection) {
		this.gui = gui;
		this.model = model;
		gui.setActionListener(this);
		connections = connection;
		//gui.setDocumentListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		PreparedStatement pstmt;
		
		try {

		switch(command) {
			
		case "RecordManagement":
			System.out.println("You clicked TableManagement");
			ClearUserManagementnputs();
			gui.createRecordmanagementPanel();
			gui.setMRMmovie_name("");
			gui.setMRMmovie_code("");
			break;
		
		case "Reports":
			System.out.println("You clicked Reports");
			gui.createReportmanagementPanel();
			break;
		case "Transactions":
			System.out.println("Transactions");
			gui.createTransactionsPanel();
			break;
			
		case "EXIT":
			System.exit(0);
			break;
			
		case "Home":
			gui.createMainMenuPanel();
			gui.ClearAllTableInputs();
			break;
			
		case "MovieRecord" :
			System.out.println("MovieRecord");
			gui.refreshMovieRecord();
			gui.createMovieRecordPanel();
			break;
		case "MRMselect":
		    ArrayList<Object[]> list = new ArrayList<>();

		    try {
		        // Ensure the JDBC driver is loaded
		        Class.forName("com.mysql.cj.jdbc.Driver");

		        // Prepare the SQL query
		        String movieInfos = "SELECT m.movie_code, m.movie_name, COUNT(t.movie_code) AS `Times Borrowed`\n"
		        		+ "	FROM movies m\n"
		        		+ "	LEFT JOIN transactions t  ON m.movie_code = t.movie_code\n"
		        		+ "	WHERE m.movie_code = ?\n"
		        		+ " GROUP BY m.movie_name, m.movie_code\n"
		        		+ " ORDER BY m.movie_code;";

		        // Use try-with-resources for JDBC resources
		        try (PreparedStatement pstmt1 = connections.prepareStatement(movieInfos)) {
		            // Set the parameter value
		        	pstmt1.setString(1, gui.getMRMmovie_code());

		            try (ResultSet resultSet = pstmt1.executeQuery()) {
		                // Process the ResultSet
		                while (resultSet.next()) {
		                    Object[] row = new Object[3];
		                    row[0] = resultSet.getInt(1);   // Movie Code
		                    row[1] = resultSet.getString(2);   // Movie Name
		                    row[2] = resultSet.getInt(3);  // Times Borrowed
		                    list.add(row); // Add the row to the list

		                    // Print the extracted values (for debugging)
		                    System.out.println("Movie Code: " + row[0]);
		                    System.out.println("Movie Name: " + row[1]);
		                    System.out.println("Times Borrowed: " + row[2]);
		                }
		            }
		        }

		        // Check if the list is not empty
		        if (!list.isEmpty()) {
		            Object[] firstRow = list.get(0); // Get the first row
		            int MRMmovie_code = Integer.parseInt(firstRow[0].toString());
		            String MRMmoviename = firstRow[1].toString();
		            int count = Integer.parseInt(firstRow[2].toString());

		            // Show the details in a dialog
		            JOptionPane.showMessageDialog(null, 
		                "Movie Code: " + MRMmovie_code + "\n" +
		                "Movie Name: " + MRMmoviename + "\n" +
		                "Count: " + count);
		        } else {
		            JOptionPane.showMessageDialog(null, "No records found for the specified movie code.");
		        }

		    } catch (Exception ex) {
		        ex.printStackTrace(); // Log the error for debugging
		    }
	
		    gui.setMRMmovie_name("");
		    gui.setMRMmovie_code("");

			break;
			
		case"UserRecord":
			if(loginHandlerAdmin()) {
			gui.refreshUserRecord();
			gui.createUserRecordPanel();
			break;
			}else break;
			
		case "AdminRecord" :
			if(loginHandlerAdmin()){
			gui.createTableInputPanel();
			gui.refreshAdminTable();
			gui.refreshGenreTable();
			gui.refreshMediaTable();
			gui.refreshMovieReqTable();
			gui.refreshMoviesTable();
			gui.refreshReviewTable();
			gui.refreshTransactionTable();
			gui.refreshUserTable();
			gui.ClearAllTableInputs();
			System.out.println("AdminRecord");
			break;
			}
			else
				break;
			
		case "MediaTypeRecord" :
			System.out.println("MediaTypeRecord");
			gui.refreshMediaTable();
			gui.createMediaRecordTablePanel();
			break;

	        
		case "AddInAdminTable":
			//admin table
			int admin_no1, admin_level1;
			String first_name1, last_name1, admin_password1;
			try {
			String InsertAdmin = " insert into admins (admin_no, first_name, last_name, password, admin_level)"
				    + " values (?, ?, ?, ?, ?)";
			pstmt = connections.prepareStatement(InsertAdmin);
			
			admin_no1 = gui.getAdminNumber();
			first_name1 = gui.getAdminFirstName();
			last_name1 = gui.getAdminLastName();
			admin_password1 = gui.getAdminPassword();
			admin_level1 = gui.getAdminLevel();
			
			pstmt.setInt(1,admin_no1);
			pstmt.setString(2, first_name1);
			pstmt.setString(3,last_name1);
			pstmt.setString(4, admin_password1);
			pstmt.setInt(5, admin_level1);
			pstmt.execute();
			gui.refreshAdminTable();
			ClearAdminInputs();
			}catch(Exception ex){
				JOptionPane.showMessageDialog(null, "Invalid Inputs");
			}
			break;
			
		case "DeleteInAdminTable":
			
			admin_no1 = gui.getAdminNumber();
			String DeleteAdmin = " DELETE FROM admins WHERE admin_no = " + admin_no1 ;
			pstmt = connections.prepareStatement(DeleteAdmin);
			pstmt.execute();
			gui.refreshAdminTable();
			ClearAdminInputs();
			break;
			
		case "UpdateAdminTable":
			try {
			admin_no1 = gui.getAdminNumber();
			first_name1 = gui.getAdminFirstName();
			last_name1 = gui.getAdminLastName();
			admin_password1 = gui.getAdminPassword();
			admin_level1 = gui.getAdminLevel();
						
			String updateAdmin = "UPDATE admins SET first_name = ?, last_name = ?, password = ?, admin_level = ? WHERE admin_no = ?";
			pstmt = connections.prepareStatement(updateAdmin);
			pstmt.setString(1, first_name1);
			pstmt.setString(2, last_name1);
			pstmt.setString(3, admin_password1);
			pstmt.setInt(4, admin_level1);
			pstmt.setInt(5, admin_no1);
			pstmt.execute();
			gui.refreshAdminTable();
			ClearAdminInputs();
			}catch(Exception ex) { 
				JOptionPane.showMessageDialog(null, "Invalid Inputs");
			}
			break;
		
		case "AddInGenreTable":
			
			int genreid2;
			String description2;
			try {
			String InsertGenre = " insert into genre_type (genre_id,description)"
				    + " values (?, ?)";
			pstmt = connections.prepareStatement(InsertGenre);
			genreid2 = gui.getGenreID();
			description2 = gui.getGenreDesc();
			pstmt.setInt(1,genreid2);
			pstmt.setString(2, description2);
			pstmt.execute();
			gui.refreshGenreTable();
			ClearGenreInputs();
			}catch(Exception ex){
				JOptionPane.showMessageDialog(null, "Invalid Inputs");
			}
			break;
			
		case "DeleteInGenreTable":
			genreid2 = gui.getGenreID();
			String DeleteGenre = " DELETE FROM genre_type WHERE genre_id = " + genreid2;
			pstmt = connections.prepareStatement(DeleteGenre);
			pstmt.execute();
			gui.refreshGenreTable();
			ClearGenreInputs();
			break;
			
		case "UpdateGenreTable":
			genreid2 = gui.getGenreID();
			description2 = gui.getGenreDesc();
			try {
			String updateGenre = "UPDATE genre_type SET description = ? WHERE genre_id = ?";
			pstmt = connections.prepareStatement(updateGenre);
			pstmt.setString(1, description2);
			pstmt.setInt(2, genreid2);
			pstmt.execute();
			gui.refreshGenreTable();
			ClearGenreInputs();
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Invalid Inputs");
			}
			break;
		
			case "AddInMediaTable":
			//admin table
			
			int copies_available3,movie_code3,product_id3;
			float rental_price3;
			String release_date3,media_type3,availability3;
			int transmute = 1;
			
			try {
			String InsertMedia = " insert into media_type (product_id,movie_code, availability, release_date,media_type,copies_available,rental_price)"
				    + " values (?, ?, ?, ?, ?, ?, ?)";
			pstmt = connections.prepareStatement(InsertMedia);
			
			copies_available3 = gui.getMediaCopies();
			movie_code3 = gui.getMmovieCode();
			product_id3 = gui.getMProductID();
			rental_price3 = gui.getRentalPrice();
			release_date3 = gui.getMediaRelease();
			media_type3 = gui.getMmedia_type();
			availability3 = gui.getMavailability();
			 
             if(availability3.equals("NO")) {
             	transmute = 0;
             }
			pstmt.setInt(1,product_id3);
			pstmt.setInt(2,movie_code3);
			pstmt.setInt(3, transmute);
			pstmt.setInt(4, Integer.parseInt(release_date3));
			pstmt.setString(5, media_type3);
			pstmt.setInt(6, copies_available3);
			pstmt.setFloat(7, rental_price3);
			pstmt.execute();
			gui.refreshMediaTable();
			ClearMediaInputs();
			}catch(Exception ex){
				JOptionPane.showMessageDialog(null, "Invalid Inputs");
			}
			break;
			
		case "DeleteInMediaTable":
			
			movie_code3 = gui.getMmovieCode();
			media_type3 = gui.getMmedia_type();

			String DeleteMedia = " DELETE FROM media_type WHERE movie_code = ? AND media_type = ?";
			pstmt = connections.prepareStatement(DeleteMedia);
			pstmt.setInt(1, movie_code3);
			pstmt.setString(2, media_type3);
			pstmt.execute();
			gui.refreshMediaTable();
			ClearMediaInputs();
			
			break;
			
		case "UpdateMediaTable":
			
			try {
			copies_available3 = gui.getMediaCopies();
			movie_code3 = gui.getMmovieCode();
			product_id3 = gui.getMProductID();
			rental_price3 = gui.getRentalPrice();
			release_date3 = gui.getMediaRelease();
			media_type3 = gui.getMmedia_type();
			availability3 = gui.getMavailability();

			transmute = 1;
            if(availability3.equals("NO")) {
            	transmute = 0;
            }
            
			String updateMedia = "UPDATE media_type SET movie_code = ?, availability = ?, release_date = ?,media_type =?, copies_available =?, rental_price =? WHERE product_id = ?";
			pstmt = connections.prepareStatement(updateMedia);
			pstmt.setInt(1, movie_code3);
			pstmt.setInt(2, transmute);
			pstmt.setInt(3, Integer.parseInt(release_date3));
			pstmt.setString(4, media_type3);
			pstmt.setInt(5, copies_available3);
			pstmt.setFloat(6, rental_price3);
			pstmt.setInt(7, product_id3);
			
			pstmt.execute();
			gui.refreshMediaTable();
			ClearMediaInputs();
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Invalid Inputs");
			}
			break;
			
		case "AddInMovieReqTable":			
			int request_number4, user_no4; 
			String movie_name4, date_filled4,approved4,in_stock4, media_type4;
			
			try {
			String InsertMovieReq = " insert into movie_req (request_number, movie_name, date_filed, user_no,approved,in_stock,media_type)"
				    + " values (?, ?, ?, ?, ?, ?, ?)";
			pstmt = connections.prepareStatement(InsertMovieReq);
			
			request_number4 = gui.getMRMovieReqNo();
			user_no4 = gui.getMRUserno(); 
			movie_name4 = gui.getMRmoviename();
			date_filled4 = gui.getMRdate_filled();
			approved4 = gui.getMRapproved();
			in_stock4 = gui.getMRin_stock();
			media_type4 = gui.getMRmedia_type();

			pstmt.setInt(1,request_number4);
			pstmt.setString(2,movie_name4);
			pstmt.setString(3, date_filled4);
			pstmt.setInt(4, user_no4);
			 transmute = 1;
             if(approved4.equals("NO")) {
             	transmute = 0;
             }
			pstmt.setInt(5, transmute);
			 transmute = 1;
             if(in_stock4.equals("NO")) {
             	transmute = 0;
             }
			pstmt.setInt(6, transmute);
			pstmt.setString(7, media_type4);
			pstmt.execute();
			gui.refreshMovieReqTable();
			ClearMovieReqInputs();
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Invalid Inputs");
			}
			break;
			
		case "UpdateMovieReqTable":

            try {
			request_number4 = gui.getMRMovieReqNo();
			user_no4 = gui.getMRUserno(); 
			movie_name4 = gui.getMRmoviename();
			date_filled4 = gui.getMRdate_filled();
			approved4 = gui.getMRapproved();
			in_stock4 = gui.getMRin_stock();
			media_type4 = gui.getMRmedia_type();
			String updateMovieReq = "UPDATE movie_req SET movie_name = ?, date_filed = ?, user_no = ?,approved =?, in_stock =?, media_type =? WHERE request_number = ?";
			pstmt = connections.prepareStatement(updateMovieReq);
			pstmt.setString(1, movie_name4);
			pstmt.setString(2, date_filled4);
			pstmt.setInt(3, user_no4);
			
			transmute =1;
            if(approved4.equals("NO")) {
            	transmute = 0;
            }
			pstmt.setInt(4, transmute);
			transmute =1;
            if(approved4.equals("NO")) {
            	transmute = 0;
            }
			pstmt.setInt(5, transmute);
			pstmt.setString(6, media_type4);
			pstmt.setInt(7, request_number4);
			
			pstmt.execute();
			gui.refreshMovieReqTable();
			ClearMovieReqInputs();
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Invalid Inputs");
		}
			break;
			
		case "DeleteInMovieReqTable":
			request_number4 = gui.getMRMovieReqNo();
			String DeleteMovieReq = " DELETE FROM movie_req WHERE request_number = ?";
			pstmt = connections.prepareStatement(DeleteMovieReq);
			pstmt.setInt(1, request_number4);
			pstmt.execute();
			gui.refreshMovieReqTable();
			ClearMovieReqInputs();
			break;

		case "AddInTransactionTable":
			int transaction_no7,movie_code7, user_no7, admin_no7,product_id;
			String date_borrowed7, date_toreturn7, date_returned7;
			float payment7;

			try {
			String InsertTransaction = " insert into transactions (transaction_no, movie_code, user_no, date_borrowed, date_toreturn, date_returned, payment, admin_no)"
				    + " values (?, ?, ?, ?, ?, ?,?,?)";
			pstmt = connections.prepareStatement(InsertTransaction);
			
			transaction_no7 = gui.getTtransaction_no();
			movie_code7 = gui.getTmovie_code();
			user_no7 = gui.getTuser_no();
			admin_no7 = gui.getTadmin_no();
			date_borrowed7 = gui.getTdate_borrowed();
			date_toreturn7 = gui.getTdate_toreturn();
			date_returned7 =  gui.getTdate_returned();
			product_id = gui.getTproduct_id();	
			payment7 = gui.getTpayment();
			
			pstmt.setInt(1,transaction_no7);
			pstmt.setInt(2,movie_code7);
			pstmt.setInt(3,product_id);
			pstmt.setInt(4,user_no7);
			pstmt.setString(5,date_borrowed7);
			pstmt.setString(6, date_toreturn7);
			pstmt.setString(7, date_returned7);
			pstmt.setFloat(8, payment7);
			pstmt.setInt(9, admin_no7);
			pstmt.execute();
			gui.refreshTransactionTable();
			gui.ClearAllTableInputs();
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Invalid Inputs");
			}
			break;
			
		case "UpdateTransactionTable":
			try {
				transaction_no7 = gui.getTtransaction_no();
				movie_code7 = gui.getTmovie_code();
				user_no7 = gui.getTuser_no();
				admin_no7 = gui.getTadmin_no();
				date_borrowed7 = gui.getTdate_borrowed();
				date_toreturn7 = gui.getTdate_toreturn();
				date_returned7 =  gui.getTdate_returned();
				product_id = gui.getTproduct_id();	
				payment7 = gui.getTpayment();

				String updateTransaction = "UPDATE transactions SET movie_code = ?, user_no = ?, admin_no = ?,date_borrowed =?, date_toreturn =?, date_returned =?,payment =? WHERE transaction_no = ?";
				pstmt = connections.prepareStatement(updateTransaction);
				pstmt.setInt(1,transaction_no7);
				pstmt.setInt(2,movie_code7);
				pstmt.setInt(3,product_id);
				pstmt.setInt(4,user_no7);
				pstmt.setString(5,date_borrowed7);
				pstmt.setString(6, date_toreturn7);
				pstmt.setString(7, date_returned7);
				pstmt.setFloat(8, payment7);
				pstmt.setInt(9, admin_no7);
				pstmt.execute();
				gui.refreshTransactionTable();;
				gui.ClearAllTableInputs();
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Invalid Inputs");
			}
			break;
		case "DeleteInTransactionTable":
			transaction_no7 = gui.getTtransaction_no();
			String Deletetransaction = " DELETE FROM transactions WHERE transaction_no = ?";
			pstmt = connections.prepareStatement(Deletetransaction);
			pstmt.setInt(1, transaction_no7);
			pstmt.execute();
			gui.refreshTransactionTable();
			gui.ClearAllTableInputs();
			break;
		
			
		case "AddInUserTable":
			int user_no8;
			String first_name8, last_name8, email8, birthday8, password8;
			
			try {
			String InsertUser = " insert into users (user_no, first_name, last_name, email,birthday,password)"
				    + " values (?, ?, ?, ?, ?, ?)";
			pstmt = connections.prepareStatement(InsertUser);
			
			user_no8 = gui.getUuser_no();
			first_name8 = gui.getUfirst_name();
			last_name8 = gui.getUlast_name();
			email8 = gui.getUemail();
			birthday8 = gui.getUbirthday();
			password8 = gui.getUpassword();
			
			pstmt.setInt(1,user_no8);
			pstmt.setString(2,first_name8);
			pstmt.setString(3, last_name8);
			pstmt.setString(4,email8);
			pstmt.setString(5, birthday8);
			pstmt.setString(6, password8);
			pstmt.execute();
			gui.refreshUserTable();
			ClearMovieReqInputs();
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Invalid Inputs");
			}
			break;
		case "UpdateUserTable":
			 try {
				 user_no8 = gui.getUuser_no();
					first_name8 = gui.getUfirst_name();
					last_name8 = gui.getUlast_name();
					email8 = gui.getUemail();
					birthday8 = gui.getUbirthday();
					password8 = gui.getUpassword();
					
					String updateMovieReq = "UPDATE users SET first_name = ?, last_name = ?, email = ?,birthday =?, password =? WHERE user_no = ?";
					pstmt = connections.prepareStatement(updateMovieReq);
					
					pstmt.setString(1,first_name8);
					pstmt.setString(2, last_name8);
					pstmt.setString(3,email8);
					pstmt.setString(4, birthday8);
					pstmt.setString(5, password8);
					pstmt.setInt(6,user_no8);
					
					pstmt.execute();
					gui.refreshUserTable();
					gui.ClearAllTableInputs();
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Invalid Inputs");
				}
			break;
		case "DeleteInUserTable":
			user_no8 = gui.getUuser_no();
			String DeleteUser = " DELETE FROM users WHERE user_no = ?";
			pstmt = connections.prepareStatement(DeleteUser);
			pstmt.setInt(1, user_no8);
			pstmt.execute();
			gui.refreshUserTable();
			gui.ClearAllTableInputs();
			break;
			
		case "AddInMoviesTable":
			int moviecode5,year5,genreid5;
			String moviename5,rating5,language5;
			
			try {
				String InsertMovie = " insert into movies (movie_code, movie_name, year,rating,language, genre_id)"
					    + " values (?, ?, ?, ?, ?, ?)";
				pstmt = connections.prepareStatement(InsertMovie);
				
				moviecode5 = gui.getMmovie_code();
				year5 = gui.getMyear();
				genreid5 = gui.getMgenre_id();
				moviename5 = gui.getMmovie_name();
				rating5 = gui.getMrating();
				language5 = gui.getMlanguage();
				
				pstmt.setInt(1,moviecode5);
				pstmt.setString(2,moviename5);
				pstmt.setInt(3, year5);
				pstmt.setString(4,rating5);
				pstmt.setString(5, language5);
				pstmt.setInt(6, genreid5);
				pstmt.execute();
				gui.refreshMoviesTable();
				gui.ClearAllTableInputs();
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Invalid Inputs");
				}
			break;
		
			
		case "UpdateMoviesTable":
			try {
				moviecode5 = gui.getMmovie_code();
				year5 = gui.getMyear();
				genreid5 = gui.getMgenre_id();
				moviename5 = gui.getMmovie_name();
				rating5 = gui.getMrating();
				language5 = gui.getMlanguage();
				String updateMovie = "UPDATE movies SET genre_id = ?, movie_name = ?, year = ?,rating =?, language =? WHERE movie_code = ?";
				pstmt = connections.prepareStatement(updateMovie);
				
				pstmt.setInt(1,genreid5);
				pstmt.setString(2,moviename5);
				pstmt.setInt(3, year5);
				pstmt.setString(4,rating5);
				pstmt.setString(5, language5);
				pstmt.setInt(6, moviecode5);
				
				pstmt.execute();
				gui.refreshMoviesTable();
				gui.ClearAllTableInputs();
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Invalid Inputs");
				}
			break;
			
		case "DeleteInMoviesTable":
			moviecode5 = gui.getMmovie_code();
			String DeleteMovie = " DELETE FROM movies WHERE movie_code = ?";
			pstmt = connections.prepareStatement(DeleteMovie);
			pstmt.setInt(1, moviecode5);
			pstmt.execute();
			gui.refreshMoviesTable();
			gui.ClearAllTableInputs();
			break;
			
		case "AddInReviewTable":
			int review_no6,movie_code6,user_no6,stars6;
			String review6;
			
			try {
				String InsertMovie = " insert into review (review_no, stars, review,movie_code,user_no)"
					    + " values (?, ?, ?, ?, ?)";
				pstmt = connections.prepareStatement(InsertMovie);
				
				review_no6 = gui.getRreview_no();
				stars6 = gui.getRstars();
				movie_code6 = gui.getRmovie_code();
				user_no6 = gui.getRuser_no();
				review6 = gui.getRreview();
				
				pstmt.setInt(1,review_no6);
				pstmt.setInt(2,stars6);
				pstmt.setString(3, review6);
				pstmt.setInt(4,movie_code6);
				pstmt.setInt(5, user_no6);
				pstmt.execute();
				gui.refreshReviewTable();
				gui.ClearAllTableInputs();
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Invalid Inputs");
				}
			break;

		case "UpdateReviewTable":
			try {
				review_no6 = gui.getRreview_no();
				stars6 = gui.getRstars();
				movie_code6 = gui.getRmovie_code();
				user_no6 = gui.getRuser_no();
				review6 = gui.getRreview();
				
				
				String updateMovie = "UPDATE review SET stars = ?, review = ?, movie_code = ?,user_no =? WHERE review_no = ?";
				pstmt = connections.prepareStatement(updateMovie);
				
				pstmt.setInt(1,stars6);
				pstmt.setString(2,review6);
				pstmt.setInt(3, movie_code6);
				pstmt.setInt(4,user_no6);
				pstmt.setInt(5, review_no6);
				pstmt.execute();
				gui.refreshReviewTable();
				gui.ClearAllTableInputs();
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Invalid Inputs");
				}
			break;
			
		case "DeleteInReviewTable":
			review_no6 = gui.getRreview_no();
			String DeleteReview = " DELETE FROM review WHERE review_no = ?";
			pstmt = connections.prepareStatement(DeleteReview);
			pstmt.setInt(1, review_no6);
			pstmt.execute();
			gui.refreshReviewTable();
			gui.ClearAllTableInputs();
			break;
		case "ReturnUserRecordManagement":
			ClearUserManagementnputs();
			gui.createUserRecordPanel();
			break;
		case "SelectUserRecord":
			gui.refreshUserProfileTable();
			gui.setUPuserno(Integer.valueOf(gui.getURuser_no()));
			gui.setUPfirstname(gui.getURfirst_name());
			gui.setUPlastName(gui.getURlast_name());
			gui.createUserProfilePanel();
			break;
			
		}

	}
	catch (Exception ex) {
		System.out.println(ex);
	}
	}
	

	private boolean loginHandlerAdmin(){
		JTextField user = new JTextField(20);
		JTextField pass = new JPasswordField(20);
		Object[] message = {
			"Username:", user,
			"Password:", pass
		};
	if(JOptionPane.showConfirmDialog(null, message, "Enter Credentials", JOptionPane.OK_CANCEL_OPTION) == 0){
		if(model.checkAdminPassCorrect(user.getText(), pass.getText()))
			return true;
		else{
			JOptionPane.showMessageDialog(new JFrame(), "Wrong Credentials", "ERROR!",JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}else{
		JOptionPane.showMessageDialog(new JFrame(), "User Cancelled", "ERROR!",JOptionPane.ERROR_MESSAGE);
		return false;
	}
			

	}

	public void ClearUserManagementnputs() {
		gui.setURfirst_name("");
		gui.setURlast_name("");
		gui.setURuser_no("");
	}
	
	public void ClearAdminInputs() {
		gui.setAdminFirstName("");
		gui.setAdminLastName("");
		gui.setAdminNumber("");
		gui.setAdminPassword("");
		gui.setAdminLevel("");
	}

	public void ClearGenreInputs() {
		gui.setGenreID("");
		gui.setGenreDesc("");
	}
	
	public void ClearMediaInputs() {
		gui.setMProductID("");
		gui.setMmovieCode("");
		gui.setMavailability("");
		gui.setMediaRelease("");
		gui.setMmedia_type("");
		gui.setMediaCopies("");
		gui.setRentalPrice("");
	}
	
	public void ClearMovieReqInputs() {
		gui.setMRapproved("");
		gui.setMRdate_filled("");
		gui.setMRin_stock("");
		gui.setMRmedia_type("");
		gui.setMRmoviename("");
		gui.setMRMovieReqNo("");
		gui.setMRUserno("");
		gui.setMediaRelease("");
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}


}
