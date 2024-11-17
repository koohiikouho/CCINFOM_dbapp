import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class GUI extends JFrame{
	
	//main menu
	private JButton btnTableInput;
	private JButton btnRecordManagement;
	private JButton btnReports;
	private JButton btnEXIT;
	
	//table input
	private JButton btnAdminsTable;
	private JButton btnGenre_TypeTable;
	private JButton btnMedia_TypeTable;
	private JButton btnMovie_reqTable;
	private JButton btnMoviesTable;
	private JButton btnReviewTable;
	private JButton btnTransactionsTable;
	private JButton btnUsersTable;
	private JButton btnHome;
	//admin table
	private JButton btnUpdateAdminTable;
	private JButton btnDeleteInAdminTable;
	private JButton btnAddInAdminTable;
	
	private JButton btnUpdateGenreTable;
   	private JButton btnDeleteInGenreTable;
   	private JButton btnAddInGenreTable;
   	
   	private JButton btnUpdateMediaTable;
   	private JButton btnDeleteInMediaTable;
   	private JButton btnAddInMediaTable;
   	
	private JPanel MainMenu = new JPanel();
	private JPanel TableInput = new JPanel();
	private JPanel AdminTable = new JPanel();
	private JPanel GenreTypeTable = new JPanel();
	private JPanel Media_TypeTable = new JPanel();
	private JPanel Movie_reqTable = new JPanel();
	private JPanel MoviesTable = new JPanel();
	private JPanel ReviewTable = new JPanel();
	private JPanel TransactionsTable = new JPanel();
	private JPanel UsersTable = new JPanel();
	
	private JTextField ATAdminNo;
	private JTextField ATFirst_Name;
	private JTextField ATLast_Name;
	private JTextField ATPass;
	private JTextField ATAdminLevel;

	private JTextField GTGenre_Num;
	private JTextArea GTDesc;
	
	private JTextField MTproduct_id;
	private JTextField MTmovie_code;
	private JTextField MTrelease;
	private JTextField MTcopies;
	private JTextField MTrentprice;
	
	private JComboBox MTmedia_type;
	private JComboBox MTavailability;
	
	//admin table
	private JScrollPane scrollerAdminTable;
	private JTable tableAdminTable;
	private DefaultTableModel tableModelAdmin;
		
	//genre type table
	private JScrollPane scrollerGenreTable;
	private JTable tableGenreTable;
	private DefaultTableModel tableModelGenre;
		
	
	private JScrollPane scrollerMediaTable;
	private JTable tableMediaTable;
	private DefaultTableModel tableModelMedia;
	
	
	public GUI() {
		super("DB APP"); //frame name
		
		setContentPane(MainMenu);
		MainMenu.setLayout(new BorderLayout());
		setSize(900,700);//size of window
		mainmenu();
		
		//to show and make window fixed
		setVisible(true);
		setResizable(false);
		
		TableInput.setLayout(new BorderLayout());
		TableInput();
		
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
		Movie_reqTablePanel();
		
		MoviesTable.setLayout(new BorderLayout());
		MoviesTablePanel();
		
		ReviewTable.setLayout(new BorderLayout());
		ReviewTablePanel();
		
		TransactionsTable.setLayout(new BorderLayout());
		TransactionsTablePanel();
		
		UsersTable.setLayout(new BorderLayout());
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
         System.out.println("hi");
        cardLayout.show(panelCenterCenter,"AdminTable");
        panelCenterCenter.setVisible(true);
      
        });
        
        btnGenre_TypeTable.addActionListener(e -> {
            System.out.println("hi");
           cardLayout.show(panelCenterCenter,"GenreTypeTable");
           panelCenterCenter.setVisible(true);
           });
   
        btnMedia_TypeTable.addActionListener(e -> {
            System.out.println("hi");
           cardLayout.show(panelCenterCenter,"Media_TypeTable");
           panelCenterCenter.setVisible(true);
           });
        
        btnMovie_reqTable.addActionListener(e -> {
            System.out.println("hi");
           cardLayout.show(panelCenterCenter,"Movie_reqTable");
           panelCenterCenter.setVisible(true);
           });
        
        btnMoviesTable.addActionListener(e -> {
            System.out.println("hi");
           cardLayout.show(panelCenterCenter,"MoviesTable");
           panelCenterCenter.setVisible(true);
           });
        
        btnReviewTable.addActionListener(e -> {
            System.out.println("hi");
           cardLayout.show(panelCenterCenter,"ReviewTable");
           panelCenterCenter.setVisible(true);
           });
        
        btnTransactionsTable.addActionListener(e -> {
            System.out.println("hi");
           cardLayout.show(panelCenterCenter,"TransactionsTable");
           panelCenterCenter.setVisible(true);
           });
        
        btnUsersTable.addActionListener(e -> {
            System.out.println("hi");
           cardLayout.show(panelCenterCenter,"UsersTable");
           panelCenterCenter.setVisible(true);
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
		    tableModelAdmin = new DefaultTableModel(getAdmin(), col);
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
		    scrollerAdminTable.setPreferredSize(new Dimension(400, 200)); // Set preferred size
		    
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
//			String url = "jdbc:mysql://147.185.221.23:51100/dbmovieRental";
//			String username = "user";
//			String password= "12345";
		String url = "jdbc:mysql://localhost:3306/dbmovieRental";
	    String username = "root";
	    String password = "dl_MySQL_su";

	    ArrayList<Object[]> list = new ArrayList<>();

	    try {
	        // Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

	        // Establish connection
	        try (Connection connection = DriverManager.getConnection(url, username, password);
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

	                // Debug print
//	                System.out.println(
//	                    row[0] + " " + row[1] + " " + row[2] + " " + row[3] + " " + row[4]
	              //  );
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
	    tableModelGenre = new DefaultTableModel(getGenre(), col);
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
	    scrollerGenreTable.setPreferredSize(new Dimension(400, 200)); // Set preferred size
	    
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
//		String url = "jdbc:mysql://147.185.221.23:51100/dbmovieRental";
//		String username = "user";
//		String password= "12345";
	String url = "jdbc:mysql://localhost:3306/dbmovieRental";
    String username = "root";
    String password = "dl_MySQL_su";

    ArrayList<Object[]> list = new ArrayList<>();

    try {
        // Load the JDBC driver
		Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish connection
        try (Connection connection = DriverManager.getConnection(url, username, password);
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
            
            JLabel rentprice = new JLabel("RentalPrice");
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
	    tableModelMedia = new DefaultTableModel(getMedia(), col);
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
	    scrollerMediaTable.setPreferredSize(new Dimension(500, 200)); // Set preferred size
	    
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
//		String url = "jdbc:mysql://147.185.221.23:51100/dbmovieRental";
//		String username = "user";
//		String password= "12345";
	String url = "jdbc:mysql://localhost:3306/dbmovieRental";
    String username = "root";
    String password = "dl_MySQL_su";

    ArrayList<Object[]> list = new ArrayList<>();

    try {
        // Load the JDBC driver
		Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish connection
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM media_type")) {

            // Process the ResultSet
            while (resultSet.next()) {
                Object[] row = new Object[7];
                row[0] = resultSet.getInt(1); // Assuming column 1 is int
                row[1] = resultSet.getInt(2); // Assuming column 2 is String
                String transmute = "NO";
                if(resultSet.getString(3).equals("1")) {
                	transmute = "YES";
                }
                row[2] = transmute;
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

		}

	public void ReviewTablePanel() {
	     // NORTH PANEL
	        JPanel panelNorth = new JPanel();
	        panelNorth.setLayout(new FlowLayout());
	        panelNorth.setBackground(Color.decode("#0A285f"));

	        JLabel label = new JLabel("REVIEW TABLE");
	        label.setForeground(Color.WHITE);
	        label.setFont(new Font("Gaegu", Font.BOLD, 18));
	        panelNorth.add(label);
	        
	        ReviewTable.add(panelNorth, BorderLayout.NORTH);

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
	
	
}
