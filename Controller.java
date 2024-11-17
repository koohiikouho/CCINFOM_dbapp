import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Controller implements ActionListener, DocumentListener{
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
		String url = "jdbc:mysql://147.185.221.23:51100/dbmovieRental";
		String username = "user";
		String password= "12345";
//			String url = "jdbc:mysql://localhost:3306/dbmovieRental";
//		    String username = "root";
//		    String password = "dl_MySQL_su";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connections = DriverManager.getConnection(url,username,password);
		
		switch(command) {
		
		case "TableInput":
			System.out.println("You clicked TableInput");
			gui.createTableInputPanel();
			gui.refreshAdminTable();
			ClearAdminInputs();
			break;
			
		case "RecordManagement":
			System.out.println("You clicked TableManagement");
		
			break;
		
		case "Reports":
			System.out.println("You clicked Reports");
			
			break;
			
		case "EXIT":
			System.exit(0);
			break;
			
		case "Home":
			System.out.println("return to main");
			gui.createMainMenuPanel();
			break;
		
		case "AddInAdminTable":
			System.out.println("add clicked");
			
			//admin table
			int admin_no1, admin_level1;
			String first_name1, last_name1, admin_password1;
			
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
		break;
		
		case "AddInGenreTable":
			System.out.println("add clicked");
			
			//admin table
			int genreid2;
			String description2;
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
			
						
			String updateGenre = "UPDATE genre_type SET description = ? WHERE genre_id = ?";
			pstmt = connections.prepareStatement(updateGenre);
			pstmt.setString(1, description2);
			pstmt.setInt(2, genreid2);
			pstmt.execute();
			gui.refreshGenreTable();
			ClearGenreInputs();
			break;
		
		case "AddInMediaTable":
			//admin table
			
			int copies_available3,movie_code3,product_id3;
			float rental_price3;
			String release_date3,media_type3,availability3;
			
			
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
			 int transmute = 1;
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
