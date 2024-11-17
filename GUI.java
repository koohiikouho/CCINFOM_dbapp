import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GUI extends JFrame{
	
	//main menu
	private JButton btnTableInput,btnRecordManagement,btnReports, btnEXIT;
	//records
	private JButton btnMovieRecord,btnUserRecord,btnAdminRecord, btnMediaTypeRecord, btnReturntoMain;
	//table input
	private JButton btnAdminsTable, btnGenre_TypeTable, btnMedia_TypeTable, 
					btnMovie_reqTable, btnMoviesTable, btnReviewTable, btnTransactionsTable, 
					btnUsersTable, btnHome;
	//admin table
	private JButton btnUpdateAdminTable, btnDeleteInAdminTable, btnAddInAdminTable;
	//genre table
	private JButton btnUpdateGenreTable, btnDeleteInGenreTable, btnAddInGenreTable;
	//media type table
   	private JButton btnUpdateMediaTable, btnDeleteInMediaTable, btnAddInMediaTable;
	//transaction table
   	private JButton btnUpdateTransactionTable, btnDeleteInTransactionTable, btnAddInTransactionTable;
   	//user table
   	private JButton btnUpdateUserTable, btnDeleteInUserTable, btnAddInUserTable;
   	//movie req table
	private JButton btnUpdateMovieReqTable,  btnDeleteInMovieReqTable, btnAddInMovieReqTable;
	//movie table
	private JButton btnUpdateMoviesTable, btnDeleteInMoviesTable, btnAddInMoviesTable;
	private JButton btnUpdateReviewTable, btnDeleteInReviewTable, btnAddInReviewTable;

	private JPanel MainMenu = new JPanel(), RecordManagement = new JPanel();
	private JPanel TableInput = new JPanel();
	//tables
	private JPanel 	AdminTable = new JPanel(), GenreTypeTable = new JPanel(), 
					Media_TypeTable = new JPanel(), Movie_reqTable = new JPanel(),	
					MoviesTable = new JPanel(), ReviewTable = new JPanel(), TransactionsTable = new JPanel(), UsersTable = new JPanel();
	// admin table text fields
	private JTextField ATAdminNo, ATFirst_Name, ATLast_Name, ATPass, ATAdminLevel;
	//genre table 
	private JTextField GTGenre_Num;
	private JTextArea GTDesc;
	//media type
	private JTextField MTproduct_id, MTmovie_code, MTrelease, MTcopies, MTrentprice;
	private JComboBox MTmedia_type, MTavailability;
	//movie req
	private JTextField MRrequest_no, MRmovie_name, MRdate_filled, MRuser_no;
	private JComboBox MRmedia_type, MRin_stock, MRapproved;
	//movies
	private JTextField Mmovie_code, Mmovie_name, Myear, Mrating, Mlanguage, Mgenre_id;
	//reviews
	private JTextField Rreview_no, Rstars, Rreview, Rmovie_code, Ruser_no;

	
	//review table
	private JScrollPane scrollerReviewTable;
	private JTable tableReviewTable;
	private DefaultTableModel tableModelReview;
	//admin table
	private JScrollPane scrollerAdminTable;
	private JTable tableAdminTable;
	private DefaultTableModel tableModelAdmin;
	//genre type table
	private JScrollPane scrollerGenreTable;
	private JTable tableGenreTable;
	private DefaultTableModel tableModelGenre;
		
	//media table
	private JScrollPane scrollerMediaTable;
	private JTable tableMediaTable;
	private DefaultTableModel tableModelMedia;
	
	
	public static Connection connection;
	public GUI(Connection connections) {
		super("DB APP"); //frame name
		
		connection = connections;
		setContentPane(MainMenu);
		MainMenu.setLayout(new BorderLayout());
		setSize(900,700);//size of window
		mainmenu();
		
		//to show and make window fixed
		setVisible(true);
		setResizable(false);
		
		TableInput.setLayout(new BorderLayout());
		TableInput();
		
		RecordManagement.setLayout(new BorderLayout());
		recordmanagement();
		
		AdminTable.setLayout(new BorderLayout());
		showAdminTable();
		AdminTablePanel();
	
		GenreTypeTable.setLayout(new BorderLayout());
		showGenreTable();
		GenreTypeTablePanel();
		
		Media_TypeTable.setLayout(new BorderLayout());
		showMediaTable();
		Media_TypeTablePanel();
		
		Movie_reqTable.setLayout(new BorderLayout());
		showMovieReqTable();
		Movie_reqTablePanel();
		
		MoviesTable.setLayout(new BorderLayout());
		showMoviesTable();
		MoviesTablePanel();
		
		ReviewTable.setLayout(new BorderLayout());
		showReviewTable();
		ReviewTablePanel();
		
		TransactionsTable.setLayout(new BorderLayout());
		showTransactionTable();
		TransactionsTablePanel();
		
		UsersTable.setLayout(new BorderLayout());
		showUserTable();
		UsersTablePanel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void createMainMenuPanel() {		
		setContentPane(MainMenu);
        revalidate();
        repaint();
	}
	
	public void mainmenu() {
		
        // NORTH PANEL
        JPanel panelNorth = new JPanel();
        panelNorth.setLayout(new FlowLayout());
        panelNorth.setBackground(Color.decode("#0A285f"));

        JLabel label = new JLabel("Welcome To Movie Application");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Gaegu", Font.BOLD, 18));
        panelNorth.add(label);

        MainMenu.add(panelNorth, BorderLayout.NORTH);

        // SOUTH PANEL
        JPanel panelSouth = new JPanel();
        panelSouth.setBackground(Color.BLACK);
        MainMenu.add(panelSouth, BorderLayout.SOUTH);

        // CENTER PANEL
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Button size
        Dimension buttonSize = new Dimension(350, 100);

        btnTableInput = new JButton("Table Input");
        btnTableInput.setPreferredSize(buttonSize);
        btnTableInput.setMaximumSize(buttonSize);
        btnTableInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCenter.add(btnTableInput);

        btnRecordManagement = new JButton("Record Management");
        btnRecordManagement.setPreferredSize(buttonSize);
        btnRecordManagement.setMaximumSize(buttonSize);
        btnRecordManagement.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCenter.add(btnRecordManagement);

        btnReports = new JButton("Reports");
        btnReports.setPreferredSize(buttonSize);
        btnReports.setMaximumSize(buttonSize);
        btnReports.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCenter.add(btnReports);

        btnEXIT = new JButton("EXIT");
        btnEXIT.setPreferredSize(buttonSize);
        btnEXIT.setMaximumSize(buttonSize);
        btnEXIT.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCenter.add(btnEXIT);
        panelCenter.add(Box.createVerticalGlue());

        btnTableInput.setActionCommand("TableInput");
        btnRecordManagement.setActionCommand("RecordManagement");
        btnReports.setActionCommand("Reports");
        btnEXIT.setActionCommand("EXIT");
        
        MainMenu.add(panelCenter, BorderLayout.CENTER);
	}

	public void createRecordmanagementPanel() {		
		setContentPane(RecordManagement);
        revalidate();
        repaint();
	}
	
	public void recordmanagement() {
		
        // NORTH PANEL
        JPanel panelNorth = new JPanel();
        panelNorth.setLayout(new FlowLayout());
        panelNorth.setBackground(Color.decode("#0A285f"));

        JLabel label = new JLabel("Welcome To Record Management");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Gaegu", Font.BOLD, 18));
        panelNorth.add(label);

        RecordManagement.add(panelNorth, BorderLayout.NORTH);

        // SOUTH PANEL
        JPanel panelSouth = new JPanel();
        panelSouth.setBackground(Color.BLACK);
        RecordManagement.add(panelSouth, BorderLayout.SOUTH);
 	
        // CENTER PANEL
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Button size
        Dimension buttonSize = new Dimension(350, 100);

        btnMovieRecord = new JButton("Movie Record");
        btnMovieRecord.setPreferredSize(buttonSize);
        btnMovieRecord.setMaximumSize(buttonSize);
        btnMovieRecord.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCenter.add(btnMovieRecord);

        btnUserRecord = new JButton("User Record");
        btnUserRecord.setPreferredSize(buttonSize);
        btnUserRecord.setMaximumSize(buttonSize);
        btnUserRecord.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCenter.add(btnUserRecord);

        btnAdminRecord = new JButton("Admin Record");
        btnAdminRecord.setPreferredSize(buttonSize);
        btnAdminRecord.setMaximumSize(buttonSize);
        btnAdminRecord.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCenter.add(btnAdminRecord);
        
        btnMediaTypeRecord = new JButton("Media Type Record");
        btnMediaTypeRecord.setPreferredSize(buttonSize);
        btnMediaTypeRecord.setMaximumSize(buttonSize);
        btnMediaTypeRecord.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCenter.add(btnMediaTypeRecord);


        btnReturntoMain = new JButton("Home");
        btnReturntoMain.setPreferredSize(buttonSize);
        btnReturntoMain.setMaximumSize(buttonSize);
        btnReturntoMain.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCenter.add(btnReturntoMain);
        panelCenter.add(Box.createVerticalGlue());

        btnMovieRecord.setActionCommand("MovieRecord");
        btnUserRecord.setActionCommand("UserRecord");
        btnAdminRecord.setActionCommand("AdminRecord");
        btnMediaTypeRecord.setActionCommand("MediaTypeRecord");
        btnReturntoMain.setActionCommand("Home");
        
        RecordManagement.add(panelCenter, BorderLayout.CENTER);
	}

	
	public void createTableInputPanel() {		
		setContentPane(TableInput);
        revalidate();
        repaint();
	}
	
	public void TableInput() {
		
		 // NORTH PANEL
        JPanel panelNorth = new JPanel();
        panelNorth.setLayout(new FlowLayout());
        panelNorth.setBackground(Color.decode("#0A285f"));

        JLabel label = new JLabel("Welcome To Movie Application");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Gaegu", Font.BOLD, 18));
        panelNorth.add(label);

        TableInput.add(panelNorth, BorderLayout.NORTH);

        // SOUTH PANEL
        JPanel panelSouth = new JPanel();
        panelSouth.setBackground(Color.BLACK);
        TableInput.add(panelSouth, BorderLayout.SOUTH);

        // CENTER PANEL
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());
        
     // Create the main panel with specific size
        JPanel panelCenterNorth = new JPanel();
        panelCenterNorth.setLayout(new GridLayout(2, 4, 10, 10)); 

        // Button Initialization with preferred size
        btnAdminsTable = new JButton("Admins Table");
        panelCenterNorth.add(btnAdminsTable);
        btnGenre_TypeTable = new JButton("Genre Type Table");
        panelCenterNorth.add(btnGenre_TypeTable);
        btnMedia_TypeTable = new JButton("Media Type Table");
        panelCenterNorth.add(btnMedia_TypeTable);
        btnMovie_reqTable = new JButton("Movie Request Table");
        panelCenterNorth.add(btnMovie_reqTable);
        btnMoviesTable = new JButton("Movies Table");
        panelCenterNorth.add(btnMoviesTable);
        btnReviewTable = new JButton("Review Table");
        panelCenterNorth.add(btnReviewTable);
        btnTransactionsTable = new JButton("Transactions Table");
        panelCenterNorth.add(btnTransactionsTable);
        btnUsersTable = new JButton("Users Table");
        panelCenterNorth.add(btnUsersTable);
        
        // center south panel
        JPanel panelCenterSouth = new JPanel();
        panelCenterSouth.setLayout(new FlowLayout()); 
        btnHome = new JButton("Home");
        panelCenterSouth.add(btnHome);
        
        // Add the panel to the center of BorderLayout
        panelCenter.add(panelCenterNorth, BorderLayout.NORTH);
        panelCenter.add(panelCenterSouth, BorderLayout.SOUTH);
        
        //sub panel
        CardLayout cardLayout = new CardLayout();
        JPanel panelCenterCenter = new JPanel(cardLayout);
        
        panelCenterCenter.add(AdminTable, "AdminTable"); 
        panelCenterCenter.add(GenreTypeTable, "GenreTypeTable"); 
        panelCenterCenter.add(Media_TypeTable, "Media_TypeTable");
        panelCenterCenter.add(Movie_reqTable, "Movie_reqTable");
        panelCenterCenter.add(MoviesTable, "MoviesTable"); 
        panelCenterCenter.add(ReviewTable, "ReviewTable"); 
        panelCenterCenter.add(TransactionsTable, "TransactionsTable");
        panelCenterCenter.add(UsersTable, "UsersTable");
        
        btnAdminsTable.addActionListener(e -> {
        cardLayout.show(panelCenterCenter,"AdminTable");
        panelCenterCenter.setVisible(true);
        ClearAllTableInputs();
        });
        
        btnGenre_TypeTable.addActionListener(e -> {
           cardLayout.show(panelCenterCenter,"GenreTypeTable");
           panelCenterCenter.setVisible(true);
           ClearAllTableInputs();
           });
   
        btnMedia_TypeTable.addActionListener(e -> {
           cardLayout.show(panelCenterCenter,"Media_TypeTable");
           panelCenterCenter.setVisible(true);
           ClearAllTableInputs();
           });
        
        btnMovie_reqTable.addActionListener(e -> {
           cardLayout.show(panelCenterCenter,"Movie_reqTable");
           panelCenterCenter.setVisible(true);
           ClearAllTableInputs();
           });
        
        btnMoviesTable.addActionListener(e -> {
           cardLayout.show(panelCenterCenter,"MoviesTable");
           panelCenterCenter.setVisible(true);
           ClearAllTableInputs();
           });
        
        btnReviewTable.addActionListener(e -> {
           cardLayout.show(panelCenterCenter,"ReviewTable");
           panelCenterCenter.setVisible(true);
           ClearAllTableInputs();
           });
        
        btnTransactionsTable.addActionListener(e -> {
           cardLayout.show(panelCenterCenter,"TransactionsTable");
           panelCenterCenter.setVisible(true);
           ClearAllTableInputs();
           });
        
        btnUsersTable.addActionListener(e -> {
           cardLayout.show(panelCenterCenter,"UsersTable");
           panelCenterCenter.setVisible(true);
           ClearAllTableInputs();
           });
     
        panelCenter.add(panelCenterCenter, BorderLayout.CENTER);
        
        TableInput.add(panelCenter, BorderLayout.CENTER);
	}
	
	public void AdminTablePanel() {
     // NORTH PANEL
        JPanel panelNorth = new JPanel();
        panelNorth.setLayout(new FlowLayout());
        panelNorth.setBackground(Color.decode("#0A285f"));

        JLabel label = new JLabel("ADMIN TABLE");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Gaegu", Font.BOLD, 18));
        panelNorth.add(label);
        
        AdminTable.add(panelNorth, BorderLayout.NORTH);
 		
    		//center panel
    		JPanel centerPanel = new JPanel();
    		centerPanel.setLayout(new GridBagLayout());
    		GridBagConstraints gbc = new GridBagConstraints();

            gbc.insets = new Insets(6, 6, 6, 6);
            gbc.anchor = GridBagConstraints.WEST;

            
    		JLabel admin_no = new JLabel("Admin No.");
    		admin_no.setForeground(Color.BLACK);
    		admin_no.setFont(new Font("Verdana", Font.BOLD, 19));
    		gbc.gridx = 1;
            gbc.gridy = 1;
            centerPanel.add(admin_no, gbc);
            ATAdminNo = new JTextField(20);
    		gbc.gridx = 2;
            gbc.gridy = 1;
            centerPanel.add(ATAdminNo, gbc);
    		
    		JLabel first_name = new JLabel("First Name");
    		first_name.setForeground(Color.BLACK);
    		first_name.setFont(new Font("Verdana", Font.BOLD, 19));
    		gbc.gridx = 1;
            gbc.gridy = 2;
            centerPanel.add(first_name, gbc);
            ATFirst_Name = new JTextField(20);
    		gbc.gridx = 2;
            gbc.gridy = 2;
            centerPanel.add(ATFirst_Name,gbc);
    		
    		JLabel last_name = new JLabel("Last Name");
    		last_name.setForeground(Color.BLACK);
    		last_name.setFont(new Font("Verdana", Font.BOLD, 19));
    		gbc.gridx = 1;
            gbc.gridy = 3;
            centerPanel.add(last_name,gbc);
            ATLast_Name = new JTextField(20);
    		gbc.gridx = 2;
            gbc.gridy = 3;
            centerPanel.add(ATLast_Name, gbc);
    		
    		JLabel password = new JLabel("Password");
    		password.setForeground(Color.BLACK);
    		password.setFont(new Font("Verdana", Font.BOLD, 19));
    		gbc.gridx = 1;
            gbc.gridy = 4;
            centerPanel.add(password , gbc);
            ATPass = new JTextField(20);
    		gbc.gridx = 2;
            gbc.gridy = 4;
            centerPanel.add(ATPass, gbc);
            
            JLabel adminlvl = new JLabel("Admin Level");
            adminlvl.setForeground(Color.BLACK);
            adminlvl.setFont(new Font("Verdana", Font.BOLD, 19));
    		gbc.gridx = 1;
            gbc.gridy = 5;
            centerPanel.add(adminlvl , gbc);
            ATAdminLevel = new JTextField(20);
    		gbc.gridx = 2;
            gbc.gridy = 5;
            centerPanel.add(ATAdminLevel, gbc);
            
            AdminTable.add(centerPanel , BorderLayout.EAST);
    		
    		        	
    		//SOUTH PANEL
    		JPanel panelSouth = new JPanel();
    		panelSouth.setLayout(new FlowLayout());
    		panelSouth.setBackground(Color.decode("#fdfdfd"));
    		
    		btnAddInAdminTable = new JButton("Add");
    		btnUpdateAdminTable = new JButton("Update");
    		btnDeleteInAdminTable = new JButton("Delete");
       		panelSouth.add(btnAddInAdminTable);
    		panelSouth.add(btnUpdateAdminTable);
    		panelSouth.add(btnDeleteInAdminTable);
    		
    		btnUpdateAdminTable.setActionCommand("UpdateAdminTable");
    		btnDeleteInAdminTable.setActionCommand("DeleteInAdminTable");
    		btnAddInAdminTable.setActionCommand("AddInAdminTable");
    		
    		AdminTable.add(panelSouth, BorderLayout.SOUTH);
    		
    	}
	//admin table
	public void showAdminTable() {
		    String[] col = {"admin_no", "first_name", "last_name", "password", "admin_level"};
		    tableModelAdmin = new DefaultTableModel(getAdmin(), col){
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false; // Disable editing for all cells
	            }
	        };
	        
		    refreshAdminTable();
		    tableAdminTable = new JTable(tableModelAdmin);
		    tableAdminTable.setEnabled(true); // Enable selection
		    
		    // Add a mouse click listener to the table
		    tableAdminTable.addMouseListener(new java.awt.event.MouseAdapter() {
		       
		        public void mouseClicked(java.awt.event.MouseEvent evt) {
		            int row = tableAdminTable.getSelectedRow(); // Get selected row index
		           
		            
		            if (row != -1) { // Ensure a valid cell is selected
		                int admin_number = (int)tableAdminTable.getValueAt(row,0);
		                String first_name = (String)tableAdminTable.getValueAt(row,1);
		                String last_name = (String)tableAdminTable.getValueAt(row,2);
		                String password = (String)tableAdminTable.getValueAt(row,3);             
		                int admin_lvl = (int)tableAdminTable.getValueAt(row,4); 
		                
		                ATAdminNo.setText(String.valueOf(admin_number));
		                ATFirst_Name.setText(first_name);
		            	ATLast_Name.setText(last_name);
		            	ATPass.setText(password);
		            	ATAdminLevel.setText(String.valueOf(admin_lvl));		               
		            }
		        }
		    });
		    
		    scrollerAdminTable = new JScrollPane(tableAdminTable);
		    scrollerAdminTable.setPreferredSize(new Dimension(450, 200)); // Set preferred size
		    
		    // Center panel
		    JPanel moreCenter = new JPanel(new BorderLayout());
		    
		    // CENTER PANEL center panel
		    JPanel panelCenter = new JPanel(new GridBagLayout());
		    GridBagConstraints gbc = new GridBagConstraints();
		    gbc.gridx = 0;
		    gbc.gridy = 0;
		    gbc.fill = GridBagConstraints.BOTH; // Make the table expand both horizontally and vertically
		    gbc.weightx = 1.0; // Give more weight to the x-axis for expansion
		    gbc.weighty = 1.0; // Give more weight to the y-axis for expansion
		    gbc.insets = new Insets(10, 10, 10, 10);
		    panelCenter.add(scrollerAdminTable, gbc);
		    moreCenter.add(panelCenter, BorderLayout.CENTER);
		    
		    AdminTable.add(moreCenter, BorderLayout.WEST);
		    AdminTable.revalidate(); // Refresh the UI
		    AdminTable.repaint(); // Ensure it's redrawn
		}

		//getting data from db
	public Object[][] getAdmin() {
		String url = "jdbc:mysql://192.168.1.41:3306/dbmovieRental";
		String username = "user";
		String password= "12345";
//		String url = "jdbc:mysql://localhost:3306/dbmovieRental";
//	    String username = "root";
//	    String password = "dl_MySQL_su";

	    ArrayList<Object[]> list = new ArrayList<>();

	    try {
	        // Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

	        // Establish connection
	        try (
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery("SELECT * FROM admins")) {

	            // Process the ResultSet
	            while (resultSet.next()) {
	                Object[] row = new Object[5];
	                row[0] = resultSet.getInt(1); // Assuming column 1 is int
	                row[1] = resultSet.getString(2); // Assuming column 2 is String
	                row[2] = resultSet.getString(3); // Assuming column 3 is int
	                row[3] = resultSet.getString(4); // Assuming column 4 is String
	                row[4] = resultSet.getInt(5); // Assuming column 5 is String
	                
	                list.add(row);
	            }
	        }

	        // Convert the list to a 2D array
	        return list.toArray(new Object[0][5]);

	    } catch (Exception e) {
	        e.printStackTrace(); // Print stack trace for debugging
	        return null;
	    }
	}

		//refreshing admin table
	public void refreshAdminTable() {
			tableModelAdmin.setDataVector(getAdmin(), new String[]{"admin_no", "first_name", "last_name", "password", "admin_level"});
	    }

	public void GenreTypeTablePanel() {
	    // NORTH PANEL
	    JPanel panelNorth = new JPanel();
	    panelNorth.setLayout(new FlowLayout());
	    panelNorth.setBackground(Color.decode("#0A285f"));

	    JLabel label = new JLabel("GENRE TYPE TABLE");
	    label.setForeground(Color.WHITE);
	    label.setFont(new Font("Gaegu", Font.BOLD, 18));
	    panelNorth.add(label);
	    GenreTypeTable.add(panelNorth, BorderLayout.NORTH);

	    // CENTER PANEL
	    JPanel centerPanel = new JPanel(new GridBagLayout());
	    centerPanel.setBackground(Color.decode("#f5f5f5")); // Light background for better readability
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(10, 10, 10, 10); // Add padding between components
	    gbc.fill = GridBagConstraints.HORIZONTAL;

	    // Genre ID
	    gbc.gridx = 0; // Column 0
	    gbc.gridy = 0; // Row 0
	    JLabel lblGenreID = new JLabel("Genre ID:");
	    lblGenreID.setForeground(Color.BLACK);
	    lblGenreID.setFont(new Font("Verdana", Font.BOLD, 14));
	    centerPanel.add(lblGenreID, gbc);

	    gbc.gridx = 1; // Column 1
	    GTGenre_Num = new JTextField(20);
	    centerPanel.add(GTGenre_Num, gbc);

	    // Description
	    gbc.gridx = 0; // Column 0
	    gbc.gridy = 1; // Row 1
	    JLabel lblDescription = new JLabel("Description:");
	    lblDescription.setForeground(Color.BLACK);
	    lblDescription.setFont(new Font("Verdana", Font.BOLD, 14));
	    centerPanel.add(lblDescription, gbc);

	    gbc.gridx = 1; // Column 1
	    gbc.gridy = 1; // Row 1
	    GTDesc = new JTextArea(3, 20);
	    JScrollPane scrollPane = new JScrollPane(GTDesc); // Add scroll pane for better usability
	    centerPanel.add(scrollPane, gbc);

	    GenreTypeTable.add(centerPanel, BorderLayout.CENTER);

	    // SOUTH PANEL
	    JPanel panelSouth = new JPanel(new FlowLayout());
	    panelSouth.setBackground(Color.decode("#fdfdfd"));

	    btnAddInGenreTable = new JButton("Add");
	    btnUpdateGenreTable = new JButton("Update");
	    btnDeleteInGenreTable = new JButton("Delete");

	    panelSouth.add(btnAddInGenreTable);
	    panelSouth.add(btnUpdateGenreTable);
	    panelSouth.add(btnDeleteInGenreTable);

	    // Assign Action Commands
	    btnUpdateGenreTable.setActionCommand("UpdateGenreTable");
	    btnDeleteInGenreTable.setActionCommand("DeleteInGenreTable");
	    btnAddInGenreTable.setActionCommand("AddInGenreTable");

	    GenreTypeTable.add(panelSouth, BorderLayout.SOUTH);
	}

	public void showGenreTable() {
	    String[] col = {"genre_id", "description"};
	    tableModelGenre = new DefaultTableModel(getGenre(), col){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable editing for all cells
            }
        };
	    refreshGenreTable();
	    tableGenreTable = new JTable(tableModelGenre);
	    tableGenreTable.setEnabled(true); // Enable selection
	    
	    // Add a mouse click listener to the table
	    tableGenreTable.addMouseListener(new java.awt.event.MouseAdapter() {
	       
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	            int row = tableGenreTable.getSelectedRow(); // Get selected row index
	           
	            
	            if (row != -1) { // Ensure a valid cell is selected
	                int genre_number = (int)tableGenreTable.getValueAt(row,0);
	                String description = (String)tableGenreTable.getValueAt(row,1);
	                 
	                GTGenre_Num.setText(String.valueOf(genre_number));
	            	GTDesc.setText(description);  			            
	            }
	        }
	    });
	    
	    scrollerGenreTable = new JScrollPane(tableGenreTable);
	    scrollerGenreTable.setPreferredSize(new Dimension(450,200)); // Set preferred size
	    
	    // Center panel
	    JPanel moreCenter = new JPanel(new BorderLayout());
	    
	    // CENTER PANEL center panel
	    JPanel panelCenter = new JPanel(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.fill = GridBagConstraints.BOTH; // Make the table expand both horizontally and vertically
	    gbc.weightx = 1.0; // Give more weight to the x-axis for expansion
	    gbc.weighty = 1.0; // Give more weight to the y-axis for expansion
	    gbc.insets = new Insets(10, 10, 10, 10);
	    panelCenter.add(scrollerGenreTable, gbc);
	    moreCenter.add(panelCenter, BorderLayout.CENTER);
	    
	    GenreTypeTable.add(moreCenter, BorderLayout.WEST);
	    GenreTypeTable.revalidate(); // Refresh the UI
	    GenreTypeTable.repaint(); // Ensure it's redrawn
	}

	//getting data from db
	public Object[][] getGenre() {
		String url = "jdbc:mysql://192.168.1.41:3306/dbmovieRental";
		String username = "user";
		String password= "12345";
//	String url = "jdbc:mysql://localhost:3306/dbmovieRental";
 //   String username = "root";
 //   String password = "dl_MySQL_su";

    ArrayList<Object[]> list = new ArrayList<>();

    try {
        // Load the JDBC driver
		Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish connection
        try (
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM genre_type")) {

            // Process the ResultSet
            while (resultSet.next()) {
                Object[] row = new Object[2];
                row[0] = resultSet.getInt(1); // Assuming column 1 is int
                row[1] = resultSet.getString(2); // Assuming column 2 is String           
                list.add(row);

                // Debug print
//                System.out.println(
//                    row[0] + " " + row[1] + " " + row[2] + " " + row[3] + " " + row[4]
              //  );
            }
        }

        // Convert the list to a 2D array
        return list.toArray(new Object[0][2]);

    } catch (Exception e) {
        e.printStackTrace(); // Print stack trace for debugging
        return null;
    }
}

	//refreshing genre type table
	public void refreshGenreTable() {
		tableModelGenre.setDataVector(getGenre(), new String[]{"genre_id", "description"});
    }

	public void Media_TypeTablePanel() {
	     // NORTH PANEL
	        JPanel panelNorth = new JPanel();
	        panelNorth.setLayout(new FlowLayout());
	        panelNorth.setBackground(Color.decode("#0A285f"));

	        JLabel label = new JLabel("MEDIA TYPE TABLE");
	        label.setForeground(Color.WHITE);
	        label.setFont(new Font("Gaegu", Font.BOLD, 18));
	        panelNorth.add(label);
	        
	        Media_TypeTable.add(panelNorth, BorderLayout.NORTH);
	    	
    		//center panel
    		JPanel centerPanel = new JPanel();
    		centerPanel.setLayout(new GridBagLayout());
    		GridBagConstraints gbc = new GridBagConstraints();

            gbc.insets = new Insets(6, 6, 6, 6);
            gbc.anchor = GridBagConstraints.WEST;

            
    		JLabel pID = new JLabel("Product ID");
    		pID.setForeground(Color.BLACK);
    		pID.setFont(new Font("Verdana", Font.BOLD, 19));
    		gbc.gridx = 1;
            gbc.gridy = 1;
            centerPanel.add(pID, gbc);
            MTproduct_id = new JTextField(15);
    		gbc.gridx = 2;
            gbc.gridy = 1;
            centerPanel.add(MTproduct_id, gbc);
    		
    		JLabel mcode = new JLabel("Movie Code");
    		mcode.setForeground(Color.BLACK);
    		mcode.setFont(new Font("Verdana", Font.BOLD, 19));
    		gbc.gridx = 1;
            gbc.gridy = 2;
            centerPanel.add(mcode, gbc);
            MTmovie_code = new JTextField(15);
    		gbc.gridx = 2;
            gbc.gridy = 2;
            centerPanel.add(MTmovie_code,gbc);
    		
    		JLabel avail = new JLabel("Availability");
    		avail.setForeground(Color.BLACK);
    		avail.setFont(new Font("Verdana", Font.BOLD, 19));
    		gbc.gridx = 1;
            gbc.gridy = 3;
            centerPanel.add(avail,gbc);
            String[] availability = {"", "YES", "NO"};
            MTavailability = new JComboBox(availability);
    		gbc.gridx = 2;
            gbc.gridy = 3;
            centerPanel.add(MTavailability, gbc);
    		
    		JLabel releasedate = new JLabel("Release Date");
    		releasedate.setForeground(Color.BLACK);
    		releasedate.setFont(new Font("Verdana", Font.BOLD, 19));
    		gbc.gridx = 1;
            gbc.gridy = 4;
            centerPanel.add(releasedate , gbc);
            MTrelease = new JTextField(15);
    		gbc.gridx = 2;
            gbc.gridy = 4;
            centerPanel.add(MTrelease, gbc);
            
            JLabel mediaType = new JLabel("Media Type");
            mediaType.setForeground(Color.BLACK);
            mediaType.setFont(new Font("Verdana", Font.BOLD, 19));
    		gbc.gridx = 1;
            gbc.gridy = 5;
            centerPanel.add(mediaType , gbc);
            String[] mediachoice = {"", "VHS", "CD", "DVD", "Blu-Ray","Online"};
            MTmedia_type = new JComboBox(mediachoice);
           //MTmedia_type = new JTextField(15);
    		gbc.gridx = 2;
            gbc.gridy = 5;
            centerPanel.add(MTmedia_type, gbc);
            
            JLabel copies = new JLabel("Copies Available");
            copies.setForeground(Color.BLACK);
            copies.setFont(new Font("Verdana", Font.BOLD, 19));
    		gbc.gridx = 1;
            gbc.gridy = 6;
            centerPanel.add(copies , gbc);
            MTcopies = new JTextField(15);
    		gbc.gridx = 2;
            gbc.gridy = 6;
            centerPanel.add(MTcopies, gbc);
            
            JLabel rentprice = new JLabel("Rental Price");
            rentprice.setForeground(Color.BLACK);
            rentprice.setFont(new Font("Verdana", Font.BOLD, 19));
    		gbc.gridx = 1;
            gbc.gridy = 7;
            centerPanel.add(rentprice , gbc);
            MTrentprice = new JTextField(15);
    		gbc.gridx = 2;
            gbc.gridy = 7;
            centerPanel.add(MTrentprice, gbc);
            
            
            Media_TypeTable.add(centerPanel , BorderLayout.EAST);
    		
            
    		//SOUTH PANEL
    		JPanel panelSouth = new JPanel();
    		panelSouth.setLayout(new FlowLayout());
    		panelSouth.setBackground(Color.decode("#fdfdfd"));
    		
    		btnAddInMediaTable = new JButton("Add");
    		btnUpdateMediaTable = new JButton("Update");
    		btnDeleteInMediaTable = new JButton("Delete");
       		panelSouth.add(btnAddInMediaTable);
    		panelSouth.add(btnUpdateMediaTable);
    		panelSouth.add(btnDeleteInMediaTable);
    		
    		btnUpdateMediaTable.setActionCommand("UpdateMediaTable");
    		btnDeleteInMediaTable.setActionCommand("DeleteInMediaTable");
    		btnAddInMediaTable.setActionCommand("AddInMediaTable");
    		
    		Media_TypeTable.add(panelSouth, BorderLayout.SOUTH);
		}
	
	public void showMediaTable() {
	    String[] col = {"product_id", "movie_code","availability", "release_date","media_type","copies_available","rental_price"};
	    tableModelMedia = new DefaultTableModel(getMedia(), col) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable editing for all cells
            }
        };
;
	    refreshAdminTable();
	    tableMediaTable = new JTable(tableModelMedia);
	    tableMediaTable.setEnabled(true); // Enable selection
	    
	    // Add a mouse click listener to the table
	    tableMediaTable.addMouseListener(new java.awt.event.MouseAdapter() {
	       
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	            int row = tableMediaTable.getSelectedRow(); // Get selected row index
	           
	            
	            if (row != -1) { // Ensure a valid cell is selected
	                int product_id = (int)tableMediaTable.getValueAt(row,0);
	                int movie_code = (int)tableMediaTable.getValueAt(row,1);
	                String availability = (String)tableMediaTable.getValueAt(row,2);
	                String release_date = (String)tableMediaTable.getValueAt(row,3);
	                String media_type = (String)tableMediaTable.getValueAt(row,4);             
	                int copies_available = (int)tableMediaTable.getValueAt(row,5); 
	                float rental_price = (float)tableMediaTable.getValueAt(row,6); 
	                
	                MTproduct_id.setText(String.valueOf(product_id));
	            	MTmovie_code.setText(String.valueOf(movie_code));
	            	MTavailability.setSelectedItem(availability);
	            	MTrelease.setText(release_date.substring(0, 4));
	            	MTmedia_type.setSelectedItem(media_type);
	            	MTcopies.setText(String.valueOf(copies_available));
	            	MTrentprice.setText(String.valueOf(rental_price));		               
	            }
	        }
	    });
	    
	    scrollerMediaTable = new JScrollPane(tableMediaTable);
	    scrollerMediaTable.setPreferredSize(new Dimension(450, 200)); // Set preferred size
	    
	    // Center panel
	    JPanel moreCenter = new JPanel(new BorderLayout());
	    
	    // CENTER PANEL center panel
	    JPanel panelCenter = new JPanel(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.fill = GridBagConstraints.BOTH; // Make the table expand both horizontally and vertically
	    gbc.weightx = 1.0; // Give more weight to the x-axis for expansion
	    gbc.weighty = 1.0; // Give more weight to the y-axis for expansion
	    gbc.insets = new Insets(10, 10, 10, 10);
	    panelCenter.add(scrollerMediaTable, gbc);
	    moreCenter.add(panelCenter, BorderLayout.CENTER);
	    
	    Media_TypeTable.add(moreCenter, BorderLayout.WEST);
	    Media_TypeTable.revalidate(); // Refresh the UI
	    Media_TypeTable.repaint(); // Ensure it's redrawn
	}

	//getting data from db
	public Object[][] getMedia() {
		String url = "jdbc:mysql://192.168.1.41:3306/dbmovieRental";
		String username = "user";
		String password= "12345";
//	String url = "jdbc:mysql://localhost:3306/dbmovieRental";
//    String username = "root";
//    String password = "dl_MySQL_su";

    ArrayList<Object[]> list = new ArrayList<>();

    try {
        // Load the JDBC driver
		Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish connection
        try (
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM media_type")) {

            // Process the ResultSet
            while (resultSet.next()) {
                Object[] row = new Object[7];
                row[0] = resultSet.getInt(1); // Assuming column 1 is int
                row[1] = resultSet.getInt(2); // Assuming column 2 is String
                row[2] = resultSet.getString(3); // Assuming column 3 is int
                row[3] = resultSet.getString(4); // Assuming column 4 is String
                row[4] = resultSet.getString(5); // Assuming column 5 is String
                row[5] = resultSet.getInt(6); // Assuming column 6 is String
                row[6] = resultSet.getFloat(7); // Assuming column 7 is float          
                list.add(row);
            }
        }

        // Convert the list to a 2D array
        return list.toArray(new Object[0][7]);

    } catch (Exception e) {
        e.printStackTrace(); // Print stack trace for debugging
        return null;
    }
}

	//refreshing admin table
	public void refreshMediaTable() {
	tableModelMedia.setDataVector(getMedia(), new String[]{"product_id", "movie_code","availability", "release_date", "media_type","copies_available","rental_price"});
    }








	public void Movie_reqTablePanel() {
	     // NORTH PANEL
	        JPanel panelNorth = new JPanel();
	        panelNorth.setLayout(new FlowLayout());
	        panelNorth.setBackground(Color.decode("#0A285f"));

	        JLabel label = new JLabel("MOVIE REQUEST TABLE");
	        label.setForeground(Color.WHITE);
	        label.setFont(new Font("Gaegu", Font.BOLD, 18));
	        panelNorth.add(label);
	        
	        Movie_reqTable.add(panelNorth, BorderLayout.NORTH);

		}
	
	public void MoviesTablePanel() {
	     // NORTH PANEL
		 JPanel panelNorth = new JPanel();
		 panelNorth.setLayout(new FlowLayout());
		 panelNorth.setBackground(Color.decode("#0A285f"));

		 JLabel label = new JLabel("MOVIES TABLE");
		 label.setForeground(Color.WHITE);
		 label.setFont(new Font("Gaegu", Font.BOLD, 18));
		 panelNorth.add(label);
		 
		 MoviesTable.add(panelNorth, BorderLayout.NORTH);
		 
		 //center panel
		 JPanel centerPanel = new JPanel();
		 centerPanel.setLayout(new GridBagLayout());
		 GridBagConstraints gbc = new GridBagConstraints();

		 gbc.insets = new Insets(6, 6, 6, 6);
		 gbc.anchor = GridBagConstraints.WEST;

		 JLabel movieCode = new JLabel("Movie Code");
		 movieCode.setForeground(Color.BLACK);
		 movieCode.setFont(new Font("Verdana", Font.BOLD, 19));
		 gbc.gridx = 1;
		 gbc.gridy = 1;
		 centerPanel.add(movieCode, gbc);
		 Mmovie_code = new JTextField(15);
		 gbc.gridx = 2;
		 gbc.gridy = 1;
		 centerPanel.add(Mmovie_code, gbc);
		 
		 JLabel movieName = new JLabel("Movie Name");
		 movieName.setForeground(Color.BLACK);
		 movieName.setFont(new Font("Verdana", Font.BOLD, 19));
		 gbc.gridx = 1;
		 gbc.gridy = 2;
		 centerPanel.add(movieName, gbc);
		 Mmovie_name = new JTextField(15);
		 gbc.gridx = 2;
		 gbc.gridy = 2;
		 centerPanel.add(Mmovie_name,gbc);
		 
		 JLabel releaseDate = new JLabel("Year");
		 releaseDate.setForeground(Color.BLACK);
		 releaseDate.setFont(new Font("Verdana", Font.BOLD, 19));
		 gbc.gridx = 1;
		 gbc.gridy = 3;
		 centerPanel.add(releaseDate,gbc);
		 Myear = new JTextField(15);
		 gbc.gridx = 2;
		 gbc.gridy = 3;
		 centerPanel.add(Myear, gbc);
		 
		 JLabel rating = new JLabel("Rating");
		 rating.setForeground(Color.BLACK);
		 rating.setFont(new Font("Verdana", Font.BOLD, 19));
		 gbc.gridx = 1;
		 gbc.gridy = 4;
		 centerPanel.add(rating , gbc);
		 Mrating = new JTextField(15);
		 gbc.gridx = 2;
		 gbc.gridy = 4;
		 centerPanel.add(Mrating, gbc);
		 
		 JLabel language = new JLabel("Language");
		 language.setForeground(Color.BLACK);
		 language.setFont(new Font("Verdana", Font.BOLD, 19));
		 gbc.gridx = 1;
		 gbc.gridy = 5;
		 centerPanel.add(language , gbc);
		 Mlanguage = new JTextField(15);
		 gbc.gridx = 2;
		 gbc.gridy = 5;
		 centerPanel.add(Mlanguage, gbc);
		 
		 JLabel genre = new JLabel("Genre");
		 genre.setForeground(Color.BLACK);
		 genre.setFont(new Font("Verdana", Font.BOLD, 19));
		 gbc.gridx = 1;
		 gbc.gridy = 6;
		 centerPanel.add(genre, gbc);
		 Mgenre_id = new JTextField(15);
		 gbc.gridx = 2;
		 gbc.gridy = 6;
		 centerPanel.add(Mgenre_id, gbc);
		 
		 MoviesTable.add(centerPanel , BorderLayout.EAST);
		 
		 //SOUTH PANEL
		 JPanel panelSouth = new JPanel();
		 panelSouth.setLayout(new FlowLayout());
		 panelSouth.setBackground(Color.decode("#fdfdfd"));
		 
		 btnAddInMoviesTable = new JButton("Add");
		 btnUpdateMoviesTable = new JButton("Update");
		 btnDeleteInMoviesTable = new JButton("Delete");
		 panelSouth.add(btnAddInMoviesTable);
		 panelSouth.add(btnUpdateMoviesTable);
		 panelSouth.add(btnDeleteInMoviesTable);
		 
		 btnUpdateMoviesTable.setActionCommand("UpdateMoviesTable");
		 btnDeleteInMoviesTable.setActionCommand("DeleteInMoviesTable");
		 btnAddInMoviesTable.setActionCommand("AddInMoviesTable");
		 
		 MoviesTable.add(panelSouth, BorderLayout.SOUTH);

		}

	public void TransactionsTablePanel() {
	     // NORTH PANEL
	        JPanel panelNorth = new JPanel();
	        panelNorth.setLayout(new FlowLayout());
	        panelNorth.setBackground(Color.decode("#0A285f"));

	        JLabel label = new JLabel("TRANSACTIONS TABLE");
	        label.setForeground(Color.WHITE);
	        label.setFont(new Font("Gaegu", Font.BOLD, 18));
	        panelNorth.add(label);
	        
	        TransactionsTable.add(panelNorth, BorderLayout.NORTH);

		}

	public void TransactionsTablePanel() {
	     // NORTH PANEL
	        JPanel panelNorth = new JPanel();
	        panelNorth.setLayout(new FlowLayout());
	        panelNorth.setBackground(Color.decode("#0A285f"));

	        JLabel label = new JLabel("USER TABLE");
	        label.setForeground(Color.WHITE);
	        label.setFont(new Font("Gaegu", Font.BOLD, 18));
	        panelNorth.add(label);
	        
	        TransactionsTable.add(panelNorth, BorderLayout.NORTH);

		}

	public void UsersTablePanel() {
	     // NORTH PANEL
	        JPanel panelNorth = new JPanel();
	        panelNorth.setLayout(new FlowLayout());
	        panelNorth.setBackground(Color.decode("#0A285f"));

	        JLabel label = new JLabel("USER TABLE");
	        label.setForeground(Color.WHITE);
	        label.setFont(new Font("Gaegu", Font.BOLD, 18));
	        panelNorth.add(label);
	        
	        UsersTable.add(panelNorth, BorderLayout.NORTH);

		}
	
	public void setActionListener(ActionListener listener) {
		btnTableInput.addActionListener(listener);
		btnRecordManagement.addActionListener(listener);
		btnReports.addActionListener(listener);
		btnEXIT.addActionListener(listener);
		btnHome.addActionListener(listener);
		
		btnUpdateAdminTable.addActionListener(listener);
		btnDeleteInAdminTable.addActionListener(listener);
		btnAddInAdminTable.addActionListener(listener);

		btnUpdateGenreTable.addActionListener(listener);
       	btnDeleteInGenreTable.addActionListener(listener);
       	btnAddInGenreTable.addActionListener(listener);
       	
       	btnUpdateMediaTable.addActionListener(listener);
     	btnDeleteInMediaTable.addActionListener(listener);
       	btnAddInMediaTable.addActionListener(listener);
       	
       	btnUpdateMovieReqTable.addActionListener(listener);
       	btnDeleteInMovieReqTable.addActionListener(listener);
       	btnAddInMovieReqTable.addActionListener(listener);
       	
    	btnMovieRecord.addActionListener(listener);;
    	btnUserRecord.addActionListener(listener);;
    	btnAdminRecord.addActionListener(listener);;
    	btnMediaTypeRecord.addActionListener(listener);
    	btnReturntoMain.addActionListener(listener);
    	
    	btnUpdateUserTable.addActionListener(listener);
    	btnDeleteInUserTable.addActionListener(listener);
    	btnAddInUserTable.addActionListener(listener);
    	
    	btnUpdateTransactionTable.addActionListener(listener);
    	btnDeleteInTransactionTable.addActionListener(listener);
    	btnAddInTransactionTable.addActionListener(listener);
 

	}
	

	
//	public void setDocumentListener(DocumentListener listener) {
//		tfName.getDocument().addDocumentListener(listener);
//		taDesc.getDocument().addDocumentListener(listener);
//	}
	
	
	 // Retrieves the text from the JTextField.
	public int getAdminNumber() {
	    return Integer.parseInt(ATAdminNo.getText());
	}
	
	public void setAdminNumber(String num) {
		ATAdminNo.setText(num);
	}
	
	public String getAdminFirstName() {
	    return ATFirst_Name.getText();
	}
	
	public void setAdminFirstName(String name) {
		ATFirst_Name.setText(name);
	}

	public String getAdminLastName() {
	    return ATLast_Name.getText();
	}
	
	public void setAdminLastName(String name) {
		ATLast_Name.setText(name);
	}

	public String getAdminPassword() {
	    return ATPass.getText();
	}
	
	public void setAdminPassword(String name) {
		ATPass.setText(name);
	}

	public int getAdminLevel() {
	    return Integer.parseInt(ATAdminLevel.getText());
	}
	
	public void setAdminLevel(String num) {
		ATAdminLevel.setText(num);
	}
	
	public int getGenreID() {
	    return Integer.parseInt(GTGenre_Num.getText());
	}
	
	public void setGenreID(String num) {
		GTGenre_Num.setText(num);
	}
	
	public String getGenreDesc() {
	    return GTDesc.getText();
	}
	
	public void setGenreDesc(String desc) {
		GTDesc.setText(desc);
	}
	
	public int getMProductID() {
	    return Integer.parseInt(MTproduct_id.getText());
	}
	
	public void setMProductID(String num) {
		MTproduct_id.setText(num);
	}
	
	public int getMmovieCode() {
	    return Integer.parseInt(MTmovie_code.getText());
	}
	
	public void setMmovieCode(String num) {
		MTmovie_code.setText(num);
	}
	
	public String getMediaRelease() {
	    return MTrelease.getText();
	}
	
	public void setMediaRelease(String desc) {
		MTrelease.setText(desc);
	}
	
	public int getMediaCopies() {
	    return Integer.parseInt(MTcopies.getText());
	}
	
	public void setMediaCopies(String desc) {
			MTcopies.setText(desc);
		}
		
	public float getRentalPrice() {
	    return Float.parseFloat(MTrentprice.getText());
	}
	
	public void setRentalPrice(String num) {
		MTrentprice.setText(num);
	}
	
	public String getMavailability() {
	    return MTavailability.getSelectedItem().toString();
	}
	
	public void setMavailability(String num) {
		MTavailability.setSelectedItem(num);
	}
	
	public String getMmedia_type() {
	    return MTmedia_type.getSelectedItem().toString();
	}
	
	public void setMmedia_type(String num) {
		MTmedia_type.setSelectedItem(num);
	}
	
	public int getMRMovieReqNo() {
	    return Integer.parseInt(MRrequest_no.getText());
	}
	
	public void setMRMovieReqNo(String desc) {
		MRrequest_no.setText(desc);
	}
	
	public String getMRmoviename() {
	    return MRmovie_name.getText();
	}
	
	public void setMRmoviename(String num) {
		MRmovie_name.setText(num);
	}

	public String getMRdate_filled() {
	    return MRdate_filled.getText();
	}
	
	public void setMRdate_filled(String num) {
		MRdate_filled.setText(num);
	}
	
	public int getMRUserno() {
	    return Integer.parseInt(MRuser_no.getText());
	}
	
	public void setMRUserno(String desc) {
		MRuser_no.setText(desc);
	}
	
	public String getMRmedia_type() {
	    return MRmedia_type.getSelectedItem().toString();
	}
	
	public void setMRmedia_type(String num) {
		MRmedia_type.setSelectedItem(num);
	}

	public String getMRin_stock() {
	    return MRin_stock.getSelectedItem().toString();
	}
	
	public void setMRin_stock(String num) {
		MRin_stock.setSelectedItem(num);
	}
	
	public String getMRapproved() {
	    return MRapproved.getSelectedItem().toString();
	}
	
	public void setMRapproved(String num) {
		MRapproved.setSelectedItem(num);
	}
	
	public int getUuser_no() {
	    return Integer.parseInt(Uuser_no.getText());
	}
	
	public void setUuser_no(String num) {
		Uuser_no.setText(num);
	}

	public String getUfirst_name() {
	    return Ufirst_name.getText();
	}
	
	public void setUfirst_name(String num) {
		Ufirst_name.setText(num);
	}
	
	public String getUlast_name() {
	    return Ulast_name.getText();
	}
	
	public void setUlast_name(String num) {
		Ulast_name.setText(num);
	}
	
	public String getUemail() {
	    return Uemail.getText();
	}
	
	public void setUemail(String num) {
		Uemail.setText(num);
	}
	
	public String getUbirthday() {
	    return Ubirthday.getText();
	}
	
	public void setUbirthday(String num) {
		Ubirthday.setText(num);
	}

	public String getUpassword() {
	    return Upassword.getText();
	}
	
	public void setUpassword(String num) {
		Upassword.setText(num);
	}

	public int getTtransaction_no() {
	    return Integer.parseInt(Ttransaction_no.getText());
	}
	
	public void setTtransaction_no(String num) {
		Ttransaction_no.setText(num);
	}
	
	public int getTmovie_code() {
	    return Integer.parseInt(Tmovie_code.getText());
	}
	
	public void setTmovie_code(String num) {
		Tmovie_code.setText(num);
	}
	
	public int getTuser_no() {
	    return Integer.parseInt(Tuser_no.getText());
	}
	
	public void setTuser_no(String num) {
		Tuser_no.setText(num);
	}

	public String getTdate_borrowed() {
	    return Tdate_borrowed.getText();
	}
	
	public void setTdate_borrowed(String num) {
		Tdate_borrowed.setText(num);
	}

	public String getTdate_toreturn() {
	    return Tdate_toreturn.getText();
	}
	
	public void setTdate_toreturn(String num) {
		Tdate_toreturn.setText(num);
	}

	public String getTdate_returned() {
	    return Tdate_returned.getText();
	}
	
	public void setTdate_returned(String num) {
		Tdate_returned.setText(num);
	}
	
	public float getTpayment() {
	    return Float.parseFloat(Tpayment.getText());
	}
	
	public void setTpayment(String num) {
		Tpayment.setText(num);
	}
	
	public int getTadmin_no() {
	    return Integer.parseInt(Tadmin_no.getText());
	}
	
	public void setTadmin_no(String num) {
		Tadmin_no.setText(num);
	}
	
	public void ClearAllTableInputs() {
		setAdminFirstName("");
		setAdminLastName("");
		setAdminNumber("");
		setAdminPassword("");
		setAdminLevel("");
		setGenreID("");
		setGenreDesc("");
		setMProductID("");
		setMmovieCode("");
		setMavailability("");
		setMediaRelease("");
		setMmedia_type("");
		setMediaCopies("");
		setRentalPrice("");
		setMRapproved("");
		setMRdate_filled("");
		setMRin_stock("");
		setMRmedia_type("");
		setMRmoviename("");
		setMRMovieReqNo("");
		setMRUserno("");
		setMediaRelease("");
		
		setTadmin_no("");
		setTdate_borrowed("");
		setTdate_returned("");
		setTdate_toreturn("");
		setTmovie_code("");
		setTpayment("");
		setTtransaction_no("");
		setTuser_no("");

		setUbirthday("");
		setUemail("");
		setUfirst_name("");
		setUlast_name("");
		setUpassword("");
		setUuser_no("");
		
		
	}
	
	
}
