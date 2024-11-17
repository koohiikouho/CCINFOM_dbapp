import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class newController implements ActionListener, DocumentListener{
	private GUI gui;
	private Model model;
	
	public Controller(GUI gui, Model model) {
		this.gui = gui;
		this.model = model;
		gui.setActionListener(this);
		//gui.setDocumentListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		PreparedStatement pstmt;
		
		try {
//		String url = "jdbc:mysql://147.185.221.23:51100/dbmovieRental";
//		String username = "user";
//		String password= "12345";
			String url = "jdbc:mysql://localhost:3306/dbmovieRental";
		    String username = "root";
		    String password = "115978HxDC!rvd";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connections = DriverManager.getConnection(url,username,password);
		
		switch(command) {
		
		case "TableInput":
			System.out.println("You clicked TableInput");
			gui.createTableInputPanel();
			gui.refreshAdminTable();
			gui.ClearAllTableInputs();
			break;
			
		case "RecordManagement":
			System.out.println("You clicked TableManagement");
			gui.createRecordmanagementPanel();
		
			break;
		
		case "Reports":
			System.out.println("You clicked Reports");
			gui.createReportmanagementPanel();
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
			break;
			
		case"UserRecord":
			System.out.println("UserRecord");
			break;
			
		case "AdminRecord" :
			System.out.println("AdminRecord");
			break;
			
		case "MediaTypeRecord" :
			System.out.println("MediaTypeRecord");
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
			int transaction_no7,movie_code7, user_no7, admin_no7;
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
			payment7 = gui.getTpayment();

			
			pstmt.setInt(1,transaction_no7);
			pstmt.setInt(2,movie_code7);
			pstmt.setInt(3,user_no7);
			pstmt.setString(4,date_borrowed7);
			pstmt.setString(5, date_toreturn7);
			pstmt.setString(6, date_returned7);
			pstmt.setFloat(7, payment7);
			pstmt.setInt(8, admin_no7);
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
				payment7 = gui.getTpayment();

				String updateTransaction = "UPDATE transactions SET movie_code = ?, user_no = ?, admin_no = ?,date_borrowed =?, date_toreturn =?, date_returned =?,payment =? WHERE transaction_no = ?";
				pstmt = connections.prepareStatement(updateTransaction);
				pstmt.setInt(1, movie_code7);
				pstmt.setInt(2, user_no7);
				pstmt.setInt(3, admin_no7);
				pstmt.setString(4, date_borrowed7);
				pstmt.setString(5, date_toreturn7);
				pstmt.setString(6, date_returned7);
				pstmt.setFloat(7, payment7);
				pstmt.setInt(8, transaction_no7);
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
			
		

		
		}

	}
	catch (Exception ex) {
		System.out.println(ex);
	}
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