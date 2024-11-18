import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class GUI extends JFrame{
	
	//main menu
	private JButton btnTableInput,btnRecordManagement,btnReports, btnEXIT, btnTransactions;
	//records
	private JButton btnMovieRecord,btnUserRecord,btnAdminRecord, btnMediaTypeRecord, btnReturntoMain;
	
	// reports
	// Declare buttons as instance variables
	private JButton btnMoviesBorrowed,btnMostBorrowedMovies, btnPopularGenres;
	private JButton btnApprovedRequests, btnMostRequestedMovies, btnRentalHistory;
	private JButton btnPolicyViolations, btnRevenueReport, btnTopRevenueUsers,btnReturntoMainFromReport;
	
	
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
	//review table
	private JButton btnUpdateReviewTable, btnDeleteInReviewTable, btnAddInReviewTable;
	private JButton btnSelectuserRecord, btnReturnRecordManagementfromUR;

	private JButton btnReturnToUserRecord;
	
	private JPanel MainMenu = new JPanel(), RecordManagement = new JPanel();
	private JPanel TableInput = new JPanel();
	private JPanel ReportManagement = new JPanel();
	//tables
	private JPanel 	AdminTable = new JPanel(), GenreTypeTable = new JPanel(), 
					Media_TypeTable = new JPanel(), Movie_reqTable = new JPanel(),	
					MoviesTable = new JPanel(), ReviewTable = new JPanel(), TransactionsTable = new JPanel(), UsersTable = new JPanel();
	
	private JPanel UserRecord = new JPanel();
	private JPanel UserProfile = new JPanel();
	private JPanel MovieRecord = new JPanel();
	
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
	private JTextField Mmovie_code, Mmovie_name, Myear, Mlanguage, Mgenre_id;
	private JComboBox Mrating;
	//reviews
	private JTextField Rreview_no, Rmovie_code, Ruser_no;
	private JComboBox Rstars;
	private JTextArea Rreview;
	private JTextField URuser_no, URfirst_name, URlast_name;
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
	//movie table
	private JScrollPane scrollerMoviesTable;
	private JTable tableMoviesTable;
	private DefaultTableModel tableModelMovies;
	//media table
	private JScrollPane scrollerMediaTable;
	private JTable tableMediaTable;
	private DefaultTableModel tableModelMedia;
	//movie req table
	private JScrollPane scrollerMovieReqTable;
	private JTable tableMovieReqTable;
	private DefaultTableModel tableModelMovieReq;
	
	private JScrollPane scrollerUserRecord;
	private JTable tableUserRecord;
	private DefaultTableModel tableModelUserRecord;
	
	private JTextField Uuser_no, Ufirst_name, Ulast_name, Uemail, Ubirthday, Upassword;
	private JScrollPane scrollerUserTable;
	private JTable tableUserTable;
	private DefaultTableModel tableModelUser;
	
	
	private JTextField Ttransaction_no, Tmovie_code, Tuser_no, Tdate_borrowed, Tdate_toreturn, Tdate_returned,Tpayment, Tadmin_no;
	private JScrollPane scrollerTransactionTable;
	private JTable tableTransactionTable;
	private DefaultTableModel tableModelTransaction;
	
	private JScrollPane scrollerUserProfile;
	private JTable tableUserProfile;
	private DefaultTableModel tableModelUserProfile;
	
	JLabel UPuserno ,UPfirstname, UPlastName;

	//MovieRecord
		private JScrollPane scrollerMovieRecord;
		private JTable tableMovieRecord;
		private DefaultTableModel tableModelMovieRecord;
		private JTextField MRMmovie_code,MRMmovie_name;
		private JButton btnMRMselect,btnMRMreturn;
	
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
		
		ReportManagement.setLayout(new BorderLayout());
		reportmanagement();
		
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
		
		UserRecord.setLayout(new BorderLayout());
		showUserRecord();
		UserRecordPanel();
		
		UserProfile.setLayout(new BorderLayout());
		showUserProfile();
		UserProfilePanel();
		
		MovieRecord.setLayout(new BorderLayout());
		showMovieRecord();
		MovieRecordPanel();
		
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

        // Add vertical glue at the top for centering
        panelCenter.add(Box.createVerticalGlue());

        // Button size
        Dimension buttonSize = new Dimension(350, 100);

        btnRecordManagement = new JButton("Record Management");
        btnRecordManagement.setPreferredSize(buttonSize);
        btnRecordManagement.setMaximumSize(buttonSize);
        btnRecordManagement.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCenter.add(btnRecordManagement);
        panelCenter.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing

        btnReports = new JButton("Reports");
        btnReports.setPreferredSize(buttonSize);
        btnReports.setMaximumSize(buttonSize);
        btnReports.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCenter.add(btnReports);
        panelCenter.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing

        btnTransactions = new JButton("Transactions");
        btnTransactions.setPreferredSize(buttonSize);
        btnTransactions.setMaximumSize(buttonSize);
        btnTransactions.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCenter.add(btnTransactions);
        panelCenter.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing

        btnEXIT = new JButton("EXIT");
        btnEXIT.setPreferredSize(buttonSize);
        btnEXIT.setMaximumSize(buttonSize);
        btnEXIT.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCenter.add(btnEXIT);

        // Add vertical glue at the bottom for centering
        panelCenter.add(Box.createVerticalGlue());

        // Set action commands
        btnRecordManagement.setActionCommand("RecordManagement");
        btnReports.setActionCommand("Reports");
        btnTransactions.setActionCommand("Transactions");
        btnEXIT.setActionCommand("EXIT");

        // Add panel to the main menu
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

	public void createReportmanagementPanel() {
		setContentPane(ReportManagement);
        revalidate();
        repaint();
		
	}
	
	
	public void reportmanagement() {
	    // NORTH PANEL
	    JPanel panelNorth = new JPanel();
	    panelNorth.setLayout(new FlowLayout());
	    panelNorth.setBackground(Color.decode("#0A285f"));

	    JLabel label = new JLabel("Welcome To Report Management");
	    label.setForeground(Color.WHITE);
	    label.setFont(new Font("Gaegu", Font.BOLD, 18));
	    panelNorth.add(label);

	    ReportManagement.add(panelNorth, BorderLayout.NORTH);

	    // SOUTH PANEL
	    JPanel panelSouth = new JPanel();
	    panelSouth.setBackground(Color.BLACK);
	    ReportManagement.add(panelSouth, BorderLayout.SOUTH);

	    // CENTER PANEL
	    JPanel panelCenter = new JPanel();
	    panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
	    panelCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	    // Button size
	    Dimension buttonSize = new Dimension(350, 100);

	    // Buttons for Movies Borrowed Reports
	    btnMoviesBorrowed = new JButton("Movies Borrowed Report");
	    btnMoviesBorrowed.setPreferredSize(buttonSize);
	    btnMoviesBorrowed.setMaximumSize(buttonSize);
	    btnMoviesBorrowed.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
	    panelCenter.add(btnMoviesBorrowed);

	    btnMostBorrowedMovies = new JButton("Most Borrowed Movies");
	    btnMostBorrowedMovies.setPreferredSize(buttonSize);
	    btnMostBorrowedMovies.setMaximumSize(buttonSize);
	    btnMostBorrowedMovies.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
	    panelCenter.add(btnMostBorrowedMovies);

	    btnPopularGenres = new JButton("Popular Genres Report");
	    btnPopularGenres.setPreferredSize(buttonSize);
	    btnPopularGenres.setMaximumSize(buttonSize);
	    btnPopularGenres.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
	    panelCenter.add(btnPopularGenres);

	    // Buttons for Movie Request Reports
	    btnApprovedRequests = new JButton("Approved/Disapproved Requests");
	    btnApprovedRequests.setPreferredSize(buttonSize);
	    btnApprovedRequests.setMaximumSize(buttonSize);
	    btnApprovedRequests.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
	    panelCenter.add(btnApprovedRequests);

	    btnMostRequestedMovies = new JButton("Most Requested Movies");
	    btnMostRequestedMovies.setPreferredSize(buttonSize);
	    btnMostRequestedMovies.setMaximumSize(buttonSize);
	    btnMostRequestedMovies.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
	    panelCenter.add(btnMostRequestedMovies);

	    // Buttons for User Rentals Reports
	    btnRentalHistory = new JButton("Rental History Report");
	    btnRentalHistory.setPreferredSize(buttonSize);
	    btnRentalHistory.setMaximumSize(buttonSize);
	    btnRentalHistory.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
	    panelCenter.add(btnRentalHistory);

	    btnPolicyViolations = new JButton("Policy Violations Report");
	    btnPolicyViolations.setPreferredSize(buttonSize);
	    btnPolicyViolations.setMaximumSize(buttonSize);
	    btnPolicyViolations.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
	    panelCenter.add(btnPolicyViolations);

	    // Buttons for Sales Reports
	    btnRevenueReport = new JButton("Revenue Report");
	    btnRevenueReport.setPreferredSize(buttonSize);
	    btnRevenueReport.setMaximumSize(buttonSize);
	    btnRevenueReport.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
	    panelCenter.add(btnRevenueReport);

	    btnTopRevenueUsers = new JButton("Top Revenue Users");
	    btnTopRevenueUsers.setPreferredSize(buttonSize);
	    btnTopRevenueUsers.setMaximumSize(buttonSize);
	    btnTopRevenueUsers.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
	    panelCenter.add(btnTopRevenueUsers);

	    btnReturntoMainFromReport = new JButton("Home");
	    btnReturntoMainFromReport.setPreferredSize(buttonSize);
	    btnReturntoMainFromReport.setMaximumSize(buttonSize);
	    btnReturntoMainFromReport.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
	    panelCenter.add(btnReturntoMainFromReport);
	    panelCenter.add(Box.createVerticalGlue());

	    // Set Action Commands
	    btnMoviesBorrowed.setActionCommand("MoviesBorrowedReport");
	    btnMostBorrowedMovies.setActionCommand("MostBorrowedMoviesReport");
	    btnPopularGenres.setActionCommand("PopularGenresReport");
	    btnApprovedRequests.setActionCommand("ApprovedRequestsReport");
	    btnMostRequestedMovies.setActionCommand("MostRequestedMoviesReport");
	    btnRentalHistory.setActionCommand("RentalHistoryReport");
	    btnPolicyViolations.setActionCommand("PolicyViolationsReport");
	    btnRevenueReport.setActionCommand("RevenueReport");
	    btnTopRevenueUsers.setActionCommand("TopRevenueUsersReport");
	    btnReturntoMainFromReport.setActionCommand("Home");

	    ReportManagement.add(panelCenter, BorderLayout.CENTER);
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
        btnHome.setActionCommand("RecordManagement");
        
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

	public void showMoviesTable() {
			String[] col = {"movie_code", "movie_name", "year","rating","language","genre_id"};
			tableModelMovies = new DefaultTableModel(getMovies(), col){
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false; // Disable editing for all cells
	            }
	        };
			refreshAdminTable();
			tableMoviesTable = new JTable(tableModelMovies);
			tableMoviesTable.setEnabled(true); // Enable selection
			
			// Add a mouse click listener to the table
			tableMoviesTable.addMouseListener(new java.awt.event.MouseAdapter() {
			   
				@Override
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					int row = tableMoviesTable.getSelectedRow(); // Get selected row index
				   
					
					if (row != -1) { // Ensure a valid cell is selected
						int movie_code = (int)tableMoviesTable.getValueAt(row,0);
						String movie_name = (String)tableMoviesTable.getValueAt(row,1);
						int year = (int)tableMoviesTable.getValueAt(row,2);
						String rating = (String)tableMoviesTable.getValueAt(row,3);
						String language = (String)tableMoviesTable.getValueAt(row,4);             
						int genre_id = (int)tableMoviesTable.getValueAt(row,5); 
						
						Mmovie_code.setText(String.valueOf(movie_code));
						Mmovie_name.setText(movie_name);
						Myear.setText(String.valueOf(year));
						Mrating.setSelectedItem(rating);
						Mlanguage.setText(language);
						Mgenre_id.setText(String.valueOf(genre_id));	               
					}
				}
			});
			
			scrollerMoviesTable = new JScrollPane(tableMoviesTable);
			scrollerMoviesTable.setPreferredSize(new Dimension(500, 200)); // Set preferred size
			
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
			panelCenter.add(scrollerMoviesTable, gbc);
			moreCenter.add(panelCenter, BorderLayout.CENTER);
			
			MoviesTable.add(moreCenter, BorderLayout.WEST);
			MoviesTable.revalidate(); // Refresh the UI
			MoviesTable.repaint(); // Ensure it's redrawn
		}
	
	public Object[][] getMovies() {
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
				 ResultSet resultSet = statement.executeQuery("SELECT * FROM movies")) {
	
				// Process the ResultSet
				while (resultSet.next()) {
					Object[] row = new Object[7];
					row[0] = resultSet.getInt(1); // Assuming column 1 is int
					row[1] = resultSet.getString(2); // Assuming column 2 is String
					row[2] = resultSet.getInt(3); // Assuming column 3 is int
					row[3] = resultSet.getString(4); // Assuming column 4 is String
					row[4] = resultSet.getString(5); // Assuming column 5 is String
					row[5] = resultSet.getInt(6); // Assuming column 6 is String     
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
		
	public void refreshMoviesTable() {
			tableModelMovies.setDataVector(getMovies(), new String[]{"movie_code", "movie_name", "year","rating","language","genre_id"});
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
			 Mmovie_code = new JTextField(17);
			 gbc.gridx = 2;
			 gbc.gridy = 1;
			 centerPanel.add(Mmovie_code, gbc);
			 
			 JLabel movieName = new JLabel("Movie Name");
			 movieName.setForeground(Color.BLACK);
			 movieName.setFont(new Font("Verdana", Font.BOLD, 19));
			 gbc.gridx = 1;
			 gbc.gridy = 2;
			 centerPanel.add(movieName, gbc);
			 Mmovie_name = new JTextField(17);
			 gbc.gridx = 2;
			 gbc.gridy = 2;
			 centerPanel.add(Mmovie_name,gbc);
			 
			 JLabel releaseDate = new JLabel("Year");
			 releaseDate.setForeground(Color.BLACK);
			 releaseDate.setFont(new Font("Verdana", Font.BOLD, 19));
			 gbc.gridx = 1;
			 gbc.gridy = 3;
			 centerPanel.add(releaseDate,gbc);
			 Myear = new JTextField(17);
			 gbc.gridx = 2;
			 gbc.gridy = 3;
			 centerPanel.add(Myear, gbc);
			 
			 JLabel rating = new JLabel("Rating");
			 rating.setForeground(Color.BLACK);
			 rating.setFont(new Font("Verdana", Font.BOLD, 19));
			 gbc.gridx = 1;
			 gbc.gridy = 4;
			 centerPanel.add(rating , gbc);
			 String[] movrating = {"", "G", "PG-13", "R", "PG","A"};
			 Mrating = new JComboBox(movrating);
			  
			 gbc.gridx = 2;
			 gbc.gridy = 4;
			 centerPanel.add(Mrating, gbc);
			 
			 JLabel language = new JLabel("Language");
			 language.setForeground(Color.BLACK);
			 language.setFont(new Font("Verdana", Font.BOLD, 19));
			 gbc.gridx = 1;
			 gbc.gridy = 5;
			 centerPanel.add(language , gbc);
			 Mlanguage = new JTextField(17);
			 gbc.gridx = 2;
			 gbc.gridy = 5;
			 centerPanel.add(Mlanguage, gbc);
			 
			 JLabel genre = new JLabel("Genre");
			 genre.setForeground(Color.BLACK);
			 genre.setFont(new Font("Verdana", Font.BOLD, 19));
			 gbc.gridx = 1;
			 gbc.gridy = 6;
			 centerPanel.add(genre, gbc);
			 Mgenre_id = new JTextField(17);
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

///////////////////////////////////////////////////THIS CONCERNS THE REVIEW TABLE///////////

	public Object[][] getReview() {

	ArrayList<Object[]> list = new ArrayList<>();
	
	try {
		// Establish connection
		try (
			 Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery("SELECT * FROM review")) {
	
			// Process the ResultSet
			while (resultSet.next()) {
				Object[] row = new Object[5];
				row[0] = resultSet.getInt(1); // Assuming column 1 is int
				row[1] = resultSet.getInt(2); // Assuming column 2 is String
				row[2] = resultSet.getString(3); // Assuming column 3 is int
				row[3] = resultSet.getInt(4); // Assuming column 4 is String
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
	
	public void showReviewTable() {
		String[] col = {"review_no", "stars", "review","movie_code","user_no"};
		tableModelReview = new DefaultTableModel(getReview(), col){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable editing for all cells
            }
        };
		refreshAdminTable();
		tableReviewTable = new JTable(tableModelReview);
		tableReviewTable.setEnabled(true); // Enable selection
		
		// Add a mouse click listener to the table
		tableReviewTable.addMouseListener(new java.awt.event.MouseAdapter() {
		   
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = tableReviewTable.getSelectedRow(); // Get selected row index
			   
				
				if (row != -1) { // Ensure a valid cell is selected
					int review_no = (int)tableReviewTable.getValueAt(row,0);
					int stars = (int)tableReviewTable.getValueAt(row,1);
					String review = (String)tableReviewTable.getValueAt(row,2);
					int movie_code = (int)tableReviewTable.getValueAt(row,3);
					int user_no = (int)tableReviewTable.getValueAt(row,4);             
					
					Rreview_no.setText(String.valueOf(review_no));
					Rstars.setSelectedItem(Integer.toString(stars));
					Rreview.setText(review);
					Rmovie_code.setText(String.valueOf(movie_code));
					Ruser_no.setText(String.valueOf(user_no));               
				}
			}
		});
	
			scrollerReviewTable = new JScrollPane(tableReviewTable);
			scrollerReviewTable.setPreferredSize(new Dimension(500, 200)); // Set preferred size
			
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
			panelCenter.add(scrollerReviewTable, gbc);
			moreCenter.add(panelCenter, BorderLayout.CENTER);
			
			ReviewTable.add(moreCenter, BorderLayout.WEST);
			ReviewTable.revalidate(); // Refresh the UI
			ReviewTable.repaint(); // Ensure it's redrawn
	}
	
	public void refreshReviewTable() {
		tableModelReview.setDataVector(getReview(), new String[]{"review_no", "stars", "review","movie_code","user_no"});
		}
	
	public void ReviewTablePanel() {
		 // NORTH PANEL
		 JPanel panelNorth = new JPanel();
		 panelNorth.setLayout(new FlowLayout());
		 panelNorth.setBackground(Color.decode("#0A285f"));
	
		 JLabel label = new JLabel("REVIEWS TABLE");
		 label.setForeground(Color.WHITE);
		 label.setFont(new Font("Gaegu", Font.BOLD, 18));
		 panelNorth.add(label);
		 
		 ReviewTable.add(panelNorth, BorderLayout.NORTH);
		 
		 //center panel
		 JPanel centerPanel = new JPanel();
		 centerPanel.setLayout(new GridBagLayout());
		 GridBagConstraints gbc = new GridBagConstraints();
	
		 gbc.insets = new Insets(6, 6, 6, 6);
		 gbc.anchor = GridBagConstraints.WEST;
	
		 JLabel review_no = new JLabel("Review #");
		 review_no.setForeground(Color.BLACK);
		 review_no.setFont(new Font("Verdana", Font.BOLD, 19));
		 gbc.gridx = 1;
		 gbc.gridy = 1;
		 centerPanel.add(review_no, gbc);
		 Rreview_no = new JTextField(15);
		 gbc.gridx = 2;
		 gbc.gridy = 1;
		 centerPanel.add(Rreview_no, gbc);
		 
		 JLabel stars = new JLabel("Stars");
		 stars.setForeground(Color.BLACK);
		 stars.setFont(new Font("Verdana", Font.BOLD, 19));
		 gbc.gridx = 1;
		 gbc.gridy = 2;
		 centerPanel.add(stars, gbc);
		 String[] Star = {"", "1", "2", "3", "4","5","6","7","8","9","10"};
		 Rstars = new JComboBox(Star);
		 gbc.gridx = 2;
		 gbc.gridy = 2;
		 centerPanel.add(Rstars,gbc);
		 
		 JLabel review = new JLabel("Review");
		 review.setForeground(Color.BLACK);
		 review.setFont(new Font("Verdana", Font.BOLD, 19));
		 gbc.gridx = 1;
		 gbc.gridy = 3;
		 centerPanel.add(review,gbc);
		 Rreview = new JTextArea(5, 15);
		 Rreview.setLineWrap(true); // Enables line wrapping
		 Rreview.setWrapStyleWord(true); // Wraps at word boundaries
		 gbc.gridx = 2;
		 gbc.gridy = 3;
		 centerPanel.add(Rreview, gbc);
		 
		 JLabel movie_code = new JLabel("Movie Code");
		 movie_code.setForeground(Color.BLACK);
		 movie_code.setFont(new Font("Verdana", Font.BOLD, 19));
		 gbc.gridx = 1;
		 gbc.gridy = 4;
		 centerPanel.add(movie_code, gbc);
		 Rmovie_code = new JTextField(15);
		 gbc.gridx = 2;
		 gbc.gridy = 4;
		 centerPanel.add(Rmovie_code, gbc);
		 
		 //user number 
		 JLabel user_no = new JLabel("User no.");
		 user_no.setForeground(Color.BLACK);
		 user_no.setFont(new Font("Verdana", Font.BOLD, 19));
		 gbc.gridx = 1;
		 gbc.gridy = 5;
		 centerPanel.add(user_no , gbc);
		 Ruser_no = new JTextField(15);
		 gbc.gridx = 2;
		 gbc.gridy = 5;
		 centerPanel.add(Ruser_no, gbc);
		 
		 ReviewTable.add(centerPanel , BorderLayout.EAST);
		 
		 //SOUTH PANEL
		 JPanel panelSouth = new JPanel();
		 panelSouth.setLayout(new FlowLayout());
		 panelSouth.setBackground(Color.decode("#fdfdfd"));
		 
		 btnAddInReviewTable = new JButton("Add");
		 btnUpdateReviewTable = new JButton("Update");
		 btnDeleteInReviewTable = new JButton("Delete");
		 panelSouth.add(btnAddInReviewTable);
		 panelSouth.add(btnUpdateReviewTable);
		 panelSouth.add(btnDeleteInReviewTable);
		 
		 btnUpdateReviewTable.setActionCommand("UpdateReviewTable");
		 btnDeleteInReviewTable.setActionCommand("DeleteInReviewTable");
		 btnAddInReviewTable.setActionCommand("AddInReviewTable");
		 
		 ReviewTable.add(panelSouth, BorderLayout.SOUTH);
		}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////// START OF TRANSACTIONS
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


		 //center panel
		   JPanel centerPanel = new JPanel();
		   centerPanel.setLayout(new GridBagLayout());
		   GridBagConstraints gbc = new GridBagConstraints();

		   gbc.insets = new Insets(6, 6, 6, 6);
		   gbc.anchor = GridBagConstraints.WEST;
		   
		
		   JLabel trans_no = new JLabel("Transaction no.");
		   trans_no.setForeground(Color.BLACK);
		   trans_no.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 1;
		   centerPanel.add(trans_no, gbc);
		   Ttransaction_no = new JTextField(20);
		   gbc.gridx = 2;
		   gbc.gridy = 1;
		   centerPanel.add(Ttransaction_no, gbc);
		   
		   JLabel movcode = new JLabel("Movie Code");
		   movcode.setForeground(Color.BLACK);
		   movcode.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 2;
		   centerPanel.add(movcode, gbc);
		   Tmovie_code = new JTextField(20);
		   gbc.gridx = 2;
		   gbc.gridy = 2;
		   centerPanel.add(Tmovie_code,gbc);
		   
		   JLabel user_no = new JLabel("User No.");
		   user_no.setForeground(Color.BLACK);
		   user_no.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 3;
		   centerPanel.add(user_no,gbc);
		   Tuser_no = new JTextField(20);
		   gbc.gridx = 2;
		   gbc.gridy = 3;
		   centerPanel.add(Tuser_no, gbc);
		   
		   JLabel date_borrowed = new JLabel("Date Borrowed");
		   date_borrowed.setForeground(Color.BLACK);
		   date_borrowed.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 4;
		   centerPanel.add(date_borrowed , gbc);
		   Tdate_borrowed = new JTextField(20);
		   gbc.gridx = 2;
		   gbc.gridy = 4;
		   centerPanel.add(Tdate_borrowed, gbc);
		   
		   JLabel date_toreturn = new JLabel("Date to Return");
		   date_toreturn.setForeground(Color.BLACK);
		   date_toreturn.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 5;
		   centerPanel.add(date_toreturn , gbc);
		   Tdate_toreturn = new JTextField(20);
		   gbc.gridx = 2;
		   gbc.gridy = 5;
		   centerPanel.add(Tdate_toreturn, gbc);
		   
	   
		   JLabel date_returned = new JLabel("Date Returned");
		   date_returned.setForeground(Color.BLACK);
		   date_returned.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 6;
		   centerPanel.add(date_returned , gbc);
		   String[] isInStock = {"", "YES", "NO"};
		   Tdate_returned = new JTextField(20);
		   gbc.gridx = 2;
		   gbc.gridy = 6;
		   centerPanel.add(Tdate_returned, gbc);
		   
		   JLabel payment = new JLabel("Payment");
		   payment.setForeground(Color.BLACK);
		   payment.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 7;
		   centerPanel.add(payment , gbc);
		   Tpayment =  new JTextField(20);
		   gbc.gridx = 2;
		   gbc.gridy = 7;
		   centerPanel.add(Tpayment, gbc);
		   
		   JLabel admin_no = new JLabel("Admin no.");
		   admin_no.setForeground(Color.BLACK);
		   admin_no.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 8;
		   centerPanel.add(admin_no , gbc);
		   Tadmin_no =  new JTextField(20);
		   gbc.gridx = 2;
		   gbc.gridy = 8;
		   centerPanel.add(Tadmin_no, gbc);
		   
		   TransactionsTable.add(centerPanel , BorderLayout.EAST);

		   //SOUTH PANEL
		   JPanel panelSouth = new JPanel();
		   panelSouth.setLayout(new FlowLayout());
		   panelSouth.setBackground(Color.decode("#fdfdfd"));
		   
		   btnAddInTransactionTable = new JButton("Add");
		   btnUpdateTransactionTable = new JButton("Update");
		   btnDeleteInTransactionTable = new JButton("Delete");
			  panelSouth.add(btnAddInTransactionTable);
		   panelSouth.add(btnUpdateTransactionTable);
		   panelSouth.add(btnDeleteInTransactionTable);
		   
		   btnUpdateTransactionTable.setActionCommand("UpdateTransactionTable");
		   btnDeleteInTransactionTable.setActionCommand("DeleteInTransactionTable");
		   btnAddInTransactionTable.setActionCommand("AddInTransactionTable");
		   
		   TransactionsTable.add(panelSouth, BorderLayout.SOUTH);
   }


	public void showTransactionTable() {
   String[] col = {"transaction_no", "movie_code","user_no", "date_borrowed", "date_toreturn","date_returned", "payment", "admin_no"};
   tableModelTransaction = new DefaultTableModel(getTransaction(), col){
	   @Override
	   public boolean isCellEditable(int row, int column) {
		   return false; // Disable editing for all cells
	   }
   };
   
   refreshAdminTable();
   tableTransactionTable = new JTable(tableModelTransaction);
   tableTransactionTable.setEnabled(true); // Enable selection
   
  
   // Add a mouse click listener to the table
   tableTransactionTable.addMouseListener(new java.awt.event.MouseAdapter() {
	  
	   public void mouseClicked(java.awt.event.MouseEvent evt) {
		   int row = tableTransactionTable.getSelectedRow(); // Get selected row index
		  
		   
		   if (row != -1) { // Ensure a valid cell is selected
			   int transaction_no = (int)tableTransactionTable.getValueAt(row,0);
			   int movie_code = (int)tableTransactionTable.getValueAt(row,1);
			   int user_no = (int)tableTransactionTable.getValueAt(row,2);
			   String date_borrowed = (String)tableTransactionTable.getValueAt(row,3);
			   String date_toreturn = (String)tableTransactionTable.getValueAt(row,4);             
			   String date_returned = (String)tableTransactionTable.getValueAt(row,5); 
			   String payment = (String)tableTransactionTable.getValueAt(row,6);             
			   int admin_no = (int)tableTransactionTable.getValueAt(row,7); 
	   
			   Ttransaction_no.setText(String.valueOf(transaction_no));
			   Tmovie_code.setText(String.valueOf(movie_code));
			   Tuser_no.setText(String.valueOf(user_no));
			   Tdate_borrowed.setText(date_borrowed);
			   Tdate_toreturn.setText(date_toreturn);
			   Tdate_returned.setText(date_returned);
			   Tpayment.setText(payment);
			   Tadmin_no.setText(String.valueOf(admin_no));
							  
		   }
	   }
   });
   
   scrollerTransactionTable = new JScrollPane(tableTransactionTable);
   scrollerTransactionTable.setPreferredSize(new Dimension(425, 200)); // Set preferred size
   
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
   panelCenter.add(scrollerTransactionTable, gbc);
   moreCenter.add(panelCenter, BorderLayout.CENTER);
   
   TransactionsTable.add(moreCenter, BorderLayout.WEST);
   TransactionsTable.revalidate(); // Refresh the UI
   TransactionsTable.repaint(); // Ensure it's redrawn
}

//getting data from db
	public Object[][] getTransaction() {

ArrayList<Object[]> list = new ArrayList<>();

try {

   // Establish connection
   try (
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM transactions")) {

	   // Process the ResultSet
	   
	   
	   while (resultSet.next()) {
		   Object[] row = new Object[8];
		   row[0] = resultSet.getInt(1); 
		   row[1] = resultSet.getInt(2);            
		   row[2] = resultSet.getInt(3);
		   row[3] = resultSet.getString(4);
		   row[4] = resultSet.getString(5);
		   row[5] = resultSet.getString(6); 
		   row[6] = resultSet.getString(7);
		   row[7] = resultSet.getInt(8);          
		   list.add(row);
	   }
   }

   // Convert the list to a 2D array
   return list.toArray(new Object[0][8]);

} catch (Exception e) {
   e.printStackTrace(); // Print stack trace for debugging
   return null;
}
}

//refreshing transaction table
public void refreshTransactionTable() {
   tableModelTransaction.setDataVector(getTransaction(), new String[]{"transaction_no", "movie_code","user_no", "date_borrowed", "date_toreturn","date_returned", "payment", "admin_no"});
}
	////////////////////////////////////////////////////////////////////////////////////////////////// END OF TRANSACTIONS
	/// 
////////////////////////////////////////////////////////////////////////////////////////////////// START OF USERS
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

		   //center panel
		   JPanel centerPanel = new JPanel();
		   centerPanel.setLayout(new GridBagLayout());
		   GridBagConstraints gbc = new GridBagConstraints();

		   gbc.insets = new Insets(6, 6, 6, 6);
		   gbc.anchor = GridBagConstraints.WEST;

		   
		   JLabel uuserno = new JLabel("User no.");
		   uuserno.setForeground(Color.BLACK);
		   uuserno.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 1;
		   centerPanel.add(uuserno, gbc);
		   Uuser_no = new JTextField(20);
		   gbc.gridx = 2;
		   gbc.gridy = 1;
		   centerPanel.add(Uuser_no, gbc);
		   
		   JLabel firstname = new JLabel("First Name");
		   firstname.setForeground(Color.BLACK);
		   firstname.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 2;
		   centerPanel.add(firstname, gbc);
		   Ufirst_name = new JTextField(20);
		   gbc.gridx = 2;
		   gbc.gridy = 2;
		   centerPanel.add(Ufirst_name,gbc);
		   
		   JLabel lastname = new JLabel("Last Name");
		   lastname.setForeground(Color.BLACK);
		   lastname.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 3;
		   centerPanel.add(lastname,gbc);
		   Ulast_name = new JTextField(20);
		   gbc.gridx = 2;
		   gbc.gridy = 3;
		   centerPanel.add(Ulast_name, gbc);
		   
		   JLabel email = new JLabel("Email");
		   email.setForeground(Color.BLACK);
		   email.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 4;
		   centerPanel.add(email , gbc);
		   Uemail = new JTextField(20);
		   gbc.gridx = 2;
		   gbc.gridy = 4;
		   centerPanel.add(Uemail, gbc);
		   
		   JLabel bday = new JLabel("Birthday");
		   bday.setForeground(Color.BLACK);
		   bday.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 5;
		   centerPanel.add(bday , gbc);
		   Ubirthday = new JTextField(20);
		   gbc.gridx = 2;
		   gbc.gridy = 5;
		   centerPanel.add(Ubirthday, gbc);
		   
		   JLabel pass = new JLabel("Password");
		   pass.setForeground(Color.BLACK);
		   pass.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 6;
		   centerPanel.add(pass , gbc);
		   Upassword = new JTextField(20);
		   gbc.gridx = 2;
		   gbc.gridy = 6;
		   centerPanel.add(Upassword, gbc);
		   
		  
		   UsersTable.add(centerPanel , BorderLayout.EAST);

		   //SOUTH PANEL
		   JPanel panelSouth = new JPanel();
		   panelSouth.setLayout(new FlowLayout());
		   panelSouth.setBackground(Color.decode("#fdfdfd"));
		   
		   btnAddInUserTable = new JButton("Add");
		   btnUpdateUserTable = new JButton("Update");
		   btnDeleteInUserTable = new JButton("Delete");
			  panelSouth.add(btnAddInUserTable);
		   panelSouth.add(btnUpdateUserTable);
		   panelSouth.add(btnDeleteInUserTable);
		   
		   
		   btnUpdateUserTable.setActionCommand("UpdateUserTable");
		   btnDeleteInUserTable.setActionCommand("DeleteInUserTable");
		   btnAddInUserTable.setActionCommand("AddInUserTable");
		   
		   UsersTable.add(panelSouth, BorderLayout.SOUTH);
	   }
   
   
   public void showUserTable() {
	   String[] col = {"user_no", "first_name","last_name", "email", "birthday","password"};
	   tableModelUser = new DefaultTableModel(getUser(), col){
		   @Override
		   public boolean isCellEditable(int row, int column) {
			   return false; // Disable editing for all cells
		   }
	   };
	   
	   refreshAdminTable();
	   tableUserTable = new JTable(tableModelUser);
	   tableUserTable.setEnabled(true); // Enable selection
	   
	  
	   // Add a mouse click listener to the table
	   tableUserTable.addMouseListener(new java.awt.event.MouseAdapter() {
		  
		   public void mouseClicked(java.awt.event.MouseEvent evt) {
			   int row = tableUserTable.getSelectedRow(); // Get selected row index
			  
			   
			   if (row != -1) { // Ensure a valid cell is selected
				   int user_no = (int)tableUserTable.getValueAt(row,0);
				   String first_name = (String)tableUserTable.getValueAt(row,1);
				   String last_name = (String)tableUserTable.getValueAt(row,2);
				   String email = (String)tableUserTable.getValueAt(row,3);
				   String birthday = (String)tableUserTable.getValueAt(row,4);             
				   String password = (String)tableUserTable.getValueAt(row,5); 
		   
				   Uuser_no.setText(String.valueOf(user_no));
				   Ufirst_name.setText(first_name);
				   Ulast_name.setText(last_name);
				   Uemail.setText(email);
				   Ubirthday.setText(birthday);
				   Upassword.setText(password);
								  
			   }
		   }
	   });
	   
	   scrollerUserTable = new JScrollPane(tableUserTable);
	   scrollerUserTable.setPreferredSize(new Dimension(450, 200)); // Set preferred size
	   
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
	   panelCenter.add(scrollerUserTable, gbc);
	   moreCenter.add(panelCenter, BorderLayout.CENTER);
	   
	   UsersTable.add(moreCenter, BorderLayout.WEST);
	   UsersTable.revalidate(); // Refresh the UI
	   UsersTable.repaint(); // Ensure it's redrawn
   }

   //getting data from db
   public Object[][] getUser() {
   ArrayList<Object[]> list = new ArrayList<>();

   try {
	   // Load the JDBC driver

	   // Establish connection
	   try (
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {

		   // Process the ResultSet
		   
		   
		   while (resultSet.next()) {
			   Object[] row = new Object[6];
			   row[0] = resultSet.getInt(1); 
			   row[1] = resultSet.getString(2);            
			   row[2] = resultSet.getString(3);
			   row[3] = resultSet.getString(4);
			   row[4] = resultSet.getString(5);
			   row[5] = resultSet.getString(6);          
			   list.add(row);
		   }
	   }

	   // Convert the list to a 2D array
	   return list.toArray(new Object[0][6]);

   } catch (Exception e) {
	   e.printStackTrace(); // Print stack trace for debugging
	   return null;
   }
}

   //refreshing user table
   public void refreshUserTable() {
	   tableModelUser.setDataVector(getUser(), new String[]{"user_no", "first_name","last_name", "email", "birthday","password"});
   }

////////////////////////////////////////////////////////////////////////////////////////////////// END OF USERS
/// 
/// 
/// 
/// START OF MOVIE REQ
/// 

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


	 //center panel
	   JPanel centerPanel = new JPanel();
	   centerPanel.setLayout(new GridBagLayout());
	   GridBagConstraints gbc = new GridBagConstraints();

	   gbc.insets = new Insets(6, 6, 6, 6);
	   gbc.anchor = GridBagConstraints.WEST;

	   
	   JLabel mrequest_no = new JLabel("Request no.");
	   mrequest_no.setForeground(Color.BLACK);
	   mrequest_no.setFont(new Font("Verdana", Font.BOLD, 19));
	   gbc.gridx = 1;
	   gbc.gridy = 1;
	   centerPanel.add(mrequest_no, gbc);
	   MRrequest_no = new JTextField(20);
	   gbc.gridx = 2;
	   gbc.gridy = 1;
	   centerPanel.add(MRrequest_no, gbc);
	   
	   JLabel movname = new JLabel("Movie Name");
	   movname.setForeground(Color.BLACK);
	   movname.setFont(new Font("Verdana", Font.BOLD, 19));
	   gbc.gridx = 1;
	   gbc.gridy = 2;
	   centerPanel.add(movname, gbc);
	   MRmovie_name = new JTextField(20);
	   gbc.gridx = 2;
	   gbc.gridy = 2;
	   centerPanel.add(MRmovie_name,gbc);
	   
	   JLabel datefilled = new JLabel("Date Filled");
	   datefilled.setForeground(Color.BLACK);
	   datefilled.setFont(new Font("Verdana", Font.BOLD, 19));
	   gbc.gridx = 1;
	   gbc.gridy = 3;
	   centerPanel.add(datefilled,gbc);
	   MRdate_filled = new JTextField(20);
	   gbc.gridx = 2;
	   gbc.gridy = 3;
	   centerPanel.add(MRdate_filled, gbc);
	   
	   JLabel userno = new JLabel("User no.");
	   userno.setForeground(Color.BLACK);
	   userno.setFont(new Font("Verdana", Font.BOLD, 19));
	   gbc.gridx = 1;
	   gbc.gridy = 4;
	   centerPanel.add(userno , gbc);
	   MRuser_no = new JTextField(15);
	   gbc.gridx = 2;
	   gbc.gridy = 4;
	   centerPanel.add(MRuser_no, gbc);
	   
	   JLabel mediaType = new JLabel("Media Type");
	   mediaType.setForeground(Color.BLACK);
	   mediaType.setFont(new Font("Verdana", Font.BOLD, 19));
	   gbc.gridx = 1;
	   gbc.gridy = 5;
	   centerPanel.add(mediaType , gbc);
	   String[] mediachoice = {"", "VHS", "CD", "DVD", "Blu-Ray","Online"};
	   MRmedia_type = new JComboBox(mediachoice);
	   gbc.gridx = 2;
	   gbc.gridy = 5;
	   centerPanel.add(MRmedia_type, gbc);
	   
	   JLabel stock = new JLabel("In Stock");
	   stock.setForeground(Color.BLACK);
	   stock.setFont(new Font("Verdana", Font.BOLD, 19));
	   gbc.gridx = 1;
	   gbc.gridy = 6;
	   centerPanel.add(stock , gbc);
	   String[] isInStock = {"", "YES", "NO"};
	   MRin_stock = new JComboBox(isInStock);
	   gbc.gridx = 2;
	   gbc.gridy = 6;
	   centerPanel.add(MRin_stock, gbc);
	   
	   JLabel approve = new JLabel("Approved");
	   approve.setForeground(Color.BLACK);
	   approve.setFont(new Font("Verdana", Font.BOLD, 19));
	   gbc.gridx = 1;
	   gbc.gridy = 7;
	   centerPanel.add(approve , gbc);
	   String[] isApproved = {"", "YES", "NO"};
	   MRapproved = new JComboBox(isApproved);
	   gbc.gridx = 2;
	   gbc.gridy = 7;
	   centerPanel.add(MRapproved, gbc);
	   
	   Movie_reqTable.add(centerPanel , BorderLayout.EAST);

	   //SOUTH PANEL
	   JPanel panelSouth = new JPanel();
	   panelSouth.setLayout(new FlowLayout());
	   panelSouth.setBackground(Color.decode("#fdfdfd"));
	   
	   btnAddInMovieReqTable = new JButton("Add");
	   btnUpdateMovieReqTable = new JButton("Update");
	   btnDeleteInMovieReqTable = new JButton("Delete");
		  panelSouth.add(btnAddInMovieReqTable);
	   panelSouth.add(btnUpdateMovieReqTable);
	   panelSouth.add(btnDeleteInMovieReqTable);
	   
	   btnUpdateMovieReqTable.setActionCommand("UpdateMovieReqTable");
	   btnDeleteInMovieReqTable.setActionCommand("DeleteInMovieReqTable");
	   btnAddInMovieReqTable.setActionCommand("AddInMovieReqTable");
	   
	   Movie_reqTable.add(panelSouth, BorderLayout.SOUTH);
   }
   
   public void showMovieReqTable() {
   String[] col = {"request_number", "movie_name","date_filled", "user_no", "approved","in_stock","media_type"};
   tableModelMovieReq = new DefaultTableModel(getMovieReq(), col){
	   @Override
	   public boolean isCellEditable(int row, int column) {
		   return false; // Disable editing for all cells
	   }
   };
   
   refreshAdminTable();
   tableMovieReqTable = new JTable(tableModelMovieReq);
   tableMovieReqTable.setEnabled(true); // Enable selection
   
  
   // Add a mouse click listener to the table
   tableMovieReqTable.addMouseListener(new java.awt.event.MouseAdapter() {
	  
	   public void mouseClicked(java.awt.event.MouseEvent evt) {
		   int row = tableMovieReqTable.getSelectedRow(); // Get selected row index
		  
		   
		   if (row != -1) { // Ensure a valid cell is selected
			   int request_number = (int)tableMovieReqTable.getValueAt(row,0);
			   String movie_name = (String)tableMovieReqTable.getValueAt(row,1);
			   String date_filled = (String)tableMovieReqTable.getValueAt(row,2);
			   int user_no = (int)tableMovieReqTable.getValueAt(row,3);
			   String approved = (String)tableMovieReqTable.getValueAt(row,4);             
			   String in_stock = (String)tableMovieReqTable.getValueAt(row,5); 
			   String media_type = (String)tableMovieReqTable.getValueAt(row,6); 
			   
			   MRrequest_no.setText(String.valueOf(request_number));
			   MRmovie_name.setText(movie_name);
			   MRdate_filled.setText(date_filled);
			   MRuser_no.setText(String.valueOf(user_no));
			   MRapproved.setSelectedItem(approved);
			   MRin_stock.setSelectedItem(in_stock);
			   MRmedia_type.setSelectedItem(media_type);
								  
		   }
	   }
   });
   
   scrollerMovieReqTable = new JScrollPane(tableMovieReqTable);
   scrollerMovieReqTable.setPreferredSize(new Dimension(450, 200)); // Set preferred size
   
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
   panelCenter.add(scrollerMovieReqTable, gbc);
   moreCenter.add(panelCenter, BorderLayout.CENTER);
   
   Movie_reqTable.add(moreCenter, BorderLayout.WEST);
   Movie_reqTable.revalidate(); // Refresh the UI
   Movie_reqTable.repaint(); // Ensure it's redrawn
}

//getting data from db
	public Object[][] getMovieReq() {
//		String url = "jdbc:mysql://147.185.221.23:51100/dbmovieRental";
//		String username = "user";
//		String password= "12345";

ArrayList<Object[]> list = new ArrayList<>();

try {
   // Load the JDBC driver
   Class.forName("com.mysql.cj.jdbc.Driver");

   // Establish connection
   try (
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM movie_req")) {

	   // Process the ResultSet
	   
	   
	   while (resultSet.next()) {
		   Object[] row = new Object[7];
		   row[0] = resultSet.getInt(1); 
		   row[1] = resultSet.getString(2);            
		   row[2] = resultSet.getString(3);
		   row[3] = resultSet.getInt(4); 
		   String transmute;
		   if(resultSet.getInt(5) == 1) {
			   transmute = "YES";
		   }else if(resultSet.getInt(5) == 0){
			   transmute = "NO";
		   }else transmute = "";
		   row[4] = transmute;
		   if(resultSet.getInt(6) == 1) {
			   transmute = "YES";
		   }else if(resultSet.getInt(6) == 0){
			   transmute = "NO";
		   }else transmute = "";
		   row[5] = transmute;
		   row[6] = resultSet.getString(7);           
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
	public void refreshMovieReqTable() {
   tableModelMovieReq.setDataVector(getMovieReq(), new String[]{"request_number", "movie_name","date_filled", "user_no", "approved","in_stock","media_type"});
}

//END OF MOVIE REQ
	
	public void createMovieRecordPanel() {		
		setContentPane(MovieRecord);
	    revalidate();
	    repaint();
	}

	
		public void MovieRecordPanel() {
		// NORTH PANEL
		   JPanel panelNorth = new JPanel();
		   panelNorth.setLayout(new FlowLayout());
		   panelNorth.setBackground(Color.decode("#0A285f"));

		   JLabel label = new JLabel("MOVIE RECORD MANAGEMENT");
		   label.setForeground(Color.WHITE);
		   label.setFont(new Font("Gaegu", Font.BOLD, 18));
		   panelNorth.add(label);
		   
		   MovieRecord.add(panelNorth, BorderLayout.NORTH);

		// SOUTH PANEL
		   JPanel panelSouth = new JPanel();
		   panelSouth.setLayout(new FlowLayout());
		   panelSouth.setBackground(Color.decode("#fdfdfd"));
		   
		   btnMRMreturn = new JButton("Return");
		   panelSouth.add(btnMRMreturn);
		   btnMRMreturn.setActionCommand("RecordManagement");
		   MovieRecord.add(panelSouth, BorderLayout.SOUTH);
	       
	     //center panel
		   JPanel centerPanel = new JPanel();
		   centerPanel.setLayout(new GridBagLayout());
		   GridBagConstraints gbc = new GridBagConstraints();

		   gbc.insets = new Insets(6, 6, 6, 6);
		   gbc.anchor = GridBagConstraints.WEST;
		   
		   
		   JLabel movcode = new JLabel("Movie Code");
		   movcode.setForeground(Color.BLACK);
		   movcode.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 1;
		   centerPanel.add(movcode, gbc);
		   MRMmovie_code = new JTextField(20);
		   gbc.gridx = 2;
		   gbc.gridy = 1;
		   centerPanel.add(MRMmovie_code, gbc);
		   
		   JLabel movname = new JLabel("Movie Name");
		   movname.setForeground(Color.BLACK);
		   movname.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 2;
		   centerPanel.add(movname, gbc);
		   MRMmovie_name = new JTextField(20);
		   gbc.gridx = 2;
		   gbc.gridy = 2;
		   centerPanel.add(MRMmovie_name,gbc);
		   
		   btnMRMselect = new JButton("Select");
		   gbc.gridx = 1;
		   gbc.gridy = 3;
		   gbc.gridwidth = 2; // Make the button span across two columns
		   gbc.anchor = GridBagConstraints.CENTER; // Center-align the button
		   centerPanel.add(btnMRMselect,gbc);
		   btnMRMselect.setActionCommand("MRMselect");

		   MovieRecord.add(centerPanel , BorderLayout.EAST);
	}

		
		public void showMovieRecord() {
			   String[] col = {"movie_code", "movie_name"};
			   tableModelMovieRecord = new DefaultTableModel(getUserRecord(), col){
				   @Override
				   public boolean isCellEditable(int row, int column) {
					   return false; // Disable editing for all cells
				   }
			   };
			   
			   tableMovieRecord = new JTable(tableModelMovieRecord);
			   tableMovieRecord.setEnabled(true); // Enable selection
			   
			  
			   // Add a mouse click listener to the table
			   tableMovieRecord.addMouseListener(new java.awt.event.MouseAdapter() {
				  
				   public void mouseClicked(java.awt.event.MouseEvent evt) {
					   int row = tableMovieRecord.getSelectedRow(); // Get selected row index
					  
					   
					   if (row != -1) { // Ensure a valid cell is selected
						   int movie_code = (int)tableMovieRecord.getValueAt(row,0);
						   String movie_name = (String)tableMovieRecord.getValueAt(row,1);
						   
						   
						   MRMmovie_code.setText(String.valueOf(movie_code));
						   MRMmovie_name.setText(movie_name);				  
					   }
				   }
			   });
			   
			   scrollerMovieRecord = new JScrollPane(tableMovieRecord);
			   scrollerMovieRecord.setPreferredSize(new Dimension(450, 200)); // Set preferred size
			   
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
			   panelCenter.add(scrollerMovieRecord, gbc);
			   moreCenter.add(panelCenter, BorderLayout.CENTER);
			   
			   MovieRecord.add(moreCenter, BorderLayout.WEST);
			   MovieRecord.revalidate(); // Refresh the UI
			   MovieRecord.repaint(); // Ensure it's redrawn
			}

			//getting data from db
		public Object[][] getMovieRecord() {

			ArrayList<Object[]> list = new ArrayList<>();

			try {
			   // Load the JDBC driver
			   Class.forName("com.mysql.cj.jdbc.Driver");

			   // Establish connection
			   try (
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("SELECT m.movie_code, m.movie_name\n"
							+ "FROM movies m\n"
							+ "ORDER BY m.movie_code;")) {


				   while (resultSet.next()) {
					   Object[] row = new Object[2];
					   row[0] = resultSet.getInt(1); 
					   row[1] = resultSet.getString(2);            
					   list.add(row);
				   }
			   }

			   // Convert the list to a 2D array
			   return list.toArray(new Object[0][2]);

			} catch (Exception e) {
			   e.printStackTrace(); // Print stack trace for debugging
			   return null;
			}
			}

			//refreshing admin table
		public void refreshMovieRecord() {
			tableModelMovieRecord.setDataVector(getMovieRecord(), new String[]{"movie_code", "movie_name"});
			}

		
	
	public void createUserRecordPanel() {		
	setContentPane(UserRecord);
    revalidate();
    repaint();
}

	//user record
	public void UserRecordPanel() {
	// NORTH PANEL
	   JPanel panelNorth = new JPanel();
	   panelNorth.setLayout(new FlowLayout());
	   panelNorth.setBackground(Color.decode("#0A285f"));

	   JLabel label = new JLabel("USER RECORD MANAGEMENT");
	   label.setForeground(Color.WHITE);
	   label.setFont(new Font("Gaegu", Font.BOLD, 18));
	   panelNorth.add(label);
	   
	   UserRecord.add(panelNorth, BorderLayout.NORTH);

	// SOUTH PANEL
	   JPanel panelSouth = new JPanel();
	   panelSouth.setLayout(new FlowLayout());
	   panelSouth.setBackground(Color.decode("#fdfdfd"));
	   
	   btnReturnRecordManagementfromUR = new JButton("Return");
	   panelSouth.add(btnReturnRecordManagementfromUR);
	   btnReturnRecordManagementfromUR.setActionCommand("RecordManagement");
       UserRecord.add(panelSouth, BorderLayout.SOUTH);
       
     //center panel
	   JPanel centerPanel = new JPanel();
	   centerPanel.setLayout(new GridBagLayout());
	   GridBagConstraints gbc = new GridBagConstraints();

	   gbc.insets = new Insets(6, 6, 6, 6);
	   gbc.anchor = GridBagConstraints.WEST;
	   
	   JLabel user_no = new JLabel("User no.");
	   user_no.setForeground(Color.BLACK);
	   user_no.setFont(new Font("Verdana", Font.BOLD, 19));
	   gbc.gridx = 1;
	   gbc.gridy = 1;
	   centerPanel.add(user_no, gbc);
	   URuser_no = new JTextField(20);
	   gbc.gridx = 2;
	   gbc.gridy = 1;
	   centerPanel.add(URuser_no, gbc);
	   
	   JLabel firstname = new JLabel("First Name");
	   firstname.setForeground(Color.BLACK);
	   firstname.setFont(new Font("Verdana", Font.BOLD, 19));
	   gbc.gridx = 1;
	   gbc.gridy = 2;
	   centerPanel.add(firstname, gbc);
	   URfirst_name = new JTextField(20);
	   gbc.gridx = 2;
	   gbc.gridy = 2;
	   centerPanel.add(URfirst_name,gbc);
	   
	   JLabel lastname = new JLabel("Last Name");
	   lastname.setForeground(Color.BLACK);
	   lastname.setFont(new Font("Verdana", Font.BOLD, 19));
	   gbc.gridx = 1;
	   gbc.gridy = 3;
	   centerPanel.add(lastname,gbc);
	   URlast_name = new JTextField(20);
	   gbc.gridx = 2;
	   gbc.gridy = 3;
	   centerPanel.add(URlast_name, gbc);
	   
	   btnSelectuserRecord = new JButton("Select");
	   gbc.gridx = 1;
	   gbc.gridy = 4;
	   gbc.gridwidth = 2; // Make the button span across two columns
	   gbc.anchor = GridBagConstraints.CENTER; // Center-align the button
	   centerPanel.add(btnSelectuserRecord,gbc);
	   btnSelectuserRecord.setActionCommand("SelectUserRecord");

	   UserRecord.add(centerPanel , BorderLayout.EAST);
}

	
	public void showUserRecord() {
		   String[] col = {"user_no", "first_name", "last_name"};
		   tableModelUserRecord = new DefaultTableModel(getUserRecord(), col){
			   @Override
			   public boolean isCellEditable(int row, int column) {
				   return false; // Disable editing for all cells
			   }
		   };
		   
		   tableUserRecord = new JTable(tableModelUserRecord);
		   tableUserRecord.setEnabled(true); // Enable selection
		   
		  
		   // Add a mouse click listener to the table
		   tableUserRecord.addMouseListener(new java.awt.event.MouseAdapter() {
			  
			   public void mouseClicked(java.awt.event.MouseEvent evt) {
				   int row = tableUserRecord.getSelectedRow(); // Get selected row index
				  
				   
				   if (row != -1) { // Ensure a valid cell is selected
					   int user_no = (int)tableUserRecord.getValueAt(row,0);
					   String first_name = (String)tableUserRecord.getValueAt(row,1);
					   String last_name = (String)tableUserRecord.getValueAt(row,2);
					   
					   URuser_no.setText(String.valueOf(user_no));
					   URfirst_name.setText(first_name);
					   URlast_name.setText(last_name);
					 
										  
				   }
			   }
		   });
		   
		   scrollerUserRecord = new JScrollPane(tableUserRecord);
		   scrollerUserRecord.setPreferredSize(new Dimension(450, 200)); // Set preferred size
		   
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
		   panelCenter.add(scrollerUserRecord, gbc);
		   moreCenter.add(panelCenter, BorderLayout.CENTER);
		   
		   UserRecord.add(moreCenter, BorderLayout.WEST);
		   UserRecord.revalidate(); // Refresh the UI
		   UserRecord.repaint(); // Ensure it's redrawn
		}

		//getting data from db
	public Object[][] getUserRecord() {

		ArrayList<Object[]> list = new ArrayList<>();

		try {
		   // Load the JDBC driver
		   Class.forName("com.mysql.cj.jdbc.Driver");

		   // Establish connection
		   try (
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT DISTINCT u.user_no,u.first_name,u.last_name\n"
						+ "FROM users u \n"
						+ "ORDER BY u.user_no;")) {

			   // Process the ResultSet
			   
			   
			   while (resultSet.next()) {
				   Object[] row = new Object[3];
				   row[0] = resultSet.getInt(1); 
				   row[1] = resultSet.getString(2);            
				   row[2] = resultSet.getString(3);
				   list.add(row);
			   }
		   }

		   // Convert the list to a 2D array
		   return list.toArray(new Object[0][3]);

		} catch (Exception e) {
		   e.printStackTrace(); // Print stack trace for debugging
		   return null;
		}
		}

		//refreshing admin table
	public void refreshUserRecord() {
			tableModelUserRecord.setDataVector(getUserRecord(), new String[]{"user_no", "first_name", "last_name"});
		}

	public void createUserProfilePanel() {		
		setContentPane(UserProfile);
	    revalidate();
	    repaint();
	}
	
	public void UserProfilePanel() {
		// NORTH PANEL
		   JPanel panelNorth = new JPanel();
		   panelNorth.setLayout(new FlowLayout());
		   panelNorth.setBackground(Color.decode("#0A285f"));

		   JLabel label = new JLabel("USER PROFILE");
		   label.setForeground(Color.WHITE);
		   label.setFont(new Font("Gaegu", Font.BOLD, 18));
		   panelNorth.add(label);
		   
		   UserProfile.add(panelNorth, BorderLayout.NORTH);

		// SOUTH PANEL
		   JPanel panelSouth = new JPanel();
		   panelSouth.setLayout(new FlowLayout());
		   panelSouth.setBackground(Color.decode("#fdfdfd"));
		   
		   btnReturnToUserRecord = new JButton("Return");
		   panelSouth.add(btnReturnToUserRecord);
		   btnReturnToUserRecord.setActionCommand("ReturnUserRecordManagement");
		   UserProfile.add(panelSouth, BorderLayout.SOUTH);
		   
		   JPanel centerPanel = new JPanel();
		   centerPanel.setLayout(new GridBagLayout());
		   GridBagConstraints gbc = new GridBagConstraints();

		   gbc.insets = new Insets(6, 6, 6, 6);
		   gbc.anchor = GridBagConstraints.WEST;
		   
		   UPuserno = new JLabel("User no.");
		   UPuserno.setForeground(Color.BLACK);
		   UPuserno.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 1;
		   centerPanel.add(UPuserno, gbc);
		   
		   UPfirstname = new JLabel("First Name");
		   UPfirstname.setForeground(Color.BLACK);
		   UPfirstname.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 2;
		   centerPanel.add(UPfirstname, gbc);
		   
		   
		   UPlastName = new JLabel("Last Name");
		   UPlastName.setForeground(Color.BLACK);
		   UPlastName.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 3;
		   centerPanel.add(UPlastName,gbc);
		  
		   
		   UserProfile.add(centerPanel , BorderLayout.CENTER);
	}
	public void showUserProfile() {
		   String[] col = {"movie_name", "date_borrowed", "date_returned"};
		   tableModelUserProfile = new DefaultTableModel(getUserProfileTable(), col){
			   @Override
			   public boolean isCellEditable(int row, int column) {
				   return false; // Disable editing for all cells
			   }
		   };
		   
		   tableUserProfile = new JTable(tableModelUserProfile);
		   tableUserProfile.setEnabled(true); // Enable selection
		   scrollerUserProfile = new JScrollPane(tableUserProfile);
		   scrollerUserProfile.setPreferredSize(new Dimension(450, 200)); // Set preferred size
		   
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
		   panelCenter.add(scrollerUserProfile, gbc);
		   moreCenter.add(panelCenter, BorderLayout.CENTER);
		   
		   UserProfile.add(moreCenter, BorderLayout.WEST);
		   UserProfile.revalidate(); // Refresh the UI
		   UserProfile.repaint(); // Ensure it's redrawn
		}

		//getting data from db
	
	public Object[][] getUserProfileTable() {
	    ArrayList<Object[]> list = new ArrayList<>();

	    try {
	        // Load the JDBC driver
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        // Establish connection
	        try (
	            PreparedStatement pstmt = connection.prepareStatement(
	                "SELECT m.movie_name, t.date_borrowed, t.date_returned " +
	                "FROM transactions t " +
	                "JOIN users u ON u.user_no = t.user_no " +
	                "JOIN movies m ON m.movie_code = t.movie_code " +
	                "WHERE u.user_no = ? " +
	                "ORDER BY t.date_borrowed");
	        ) {
	            // Set the user number parameter
	            pstmt.setString(1, getURuser_no());

	            // Execute the query
	            try (ResultSet resultSet = pstmt.executeQuery()) {
	                while (resultSet.next()) {
	                    Object[] row = new Object[3];
	                    row[0] = resultSet.getString(1); // movie_name (String)
	                    row[1] = resultSet.getDate(2);  // date_borrowed (Date)
	                    row[2] = resultSet.getDate(3);  // date_returned (Date)
	                    list.add(row);
	                }
	            }
	        }

	        // Convert the list to a 2D array
	        return list.toArray(new Object[0][3]);

	    } catch (Exception e) {
	        e.printStackTrace(); // Print stack trace for debugging
	        return null;
	    }
	}

	
	//refreshing admin table
	public void refreshUserProfileTable() {
		tableModelUserProfile.setDataVector(getUserProfileTable(), new String[]{"movie_name", "date_borrowed", "date_returned"});
		}

	//REPORTS
	private void generateMoviesBorrowedReport() {
	    // Options to choose the type of report
	    String[] options = {"Day", "Month", "Year"};
	    String choice = (String) JOptionPane.showInputDialog(
	            null,
	            "Select the type of report:",
	            "Movies Borrowed Report",
	            JOptionPane.PLAIN_MESSAGE,
	            null,
	            options,
	            options[0]);

	    if (choice == null) {
	        return; // User cancelled
	    }

	    // SQL queries based on the choice
	    String query = "";
	    switch (choice) {
	        case "Day":
	            query = """
	                    SELECT DATE(t.date_borrowed) AS `Date Borrowed`, COUNT(t.movie_code) AS `Number of Movies Borrowed`
	                    FROM transactions t
	                    GROUP BY `Date Borrowed`
	                    HAVING `Date Borrowed` IS NOT NULL
	                    ORDER BY `Date Borrowed` ASC;  -- Sort by Date in ascending order
	                    """;
	            break;
	        case "Month":
	            query = """
	                    SELECT YEAR(t.date_borrowed) AS `Year`, MONTH(t.date_borrowed) AS `Month`, COUNT(t.movie_code) AS `Number of Movies Borrowed`
	                    FROM transactions t
	                    GROUP BY `Year`, `Month`
	                    HAVING `Year` != 0 AND `Month` != 0
	                    ORDER BY `Year` ASC, `Month` ASC;  -- Sort by Year and Month in ascending order
	                    """;
	            break;
	        case "Year":
	            query = """
	                    SELECT YEAR(t.date_borrowed) AS `Year`, COUNT(t.movie_code) AS `Number of Movies Borrowed`
	                    FROM transactions t
	                    GROUP BY `Year`
	                    HAVING `Year` != 0
	                    ORDER BY `Year` ASC;  -- Sort by Year in ascending order
	                    """;
	            break;
	        default:
	            JOptionPane.showMessageDialog(null, "Invalid option selected.");
	            return;
	    }

	    // Database Connection and Execution
	    try (
	         PreparedStatement pstmt = connection.prepareStatement(query);
	         ResultSet rs = pstmt.executeQuery()) {

	        // Generate the report
	        StringBuilder report = new StringBuilder();
	        if (choice.equals("Day")) {
	            report.append("Date Borrowed\t\tNumber of Movies Borrowed\n");
	            while (rs.next()) {
	                java.sql.Date dateBorrowed = rs.getDate("Date Borrowed");
	                int numMoviesBorrowed = rs.getInt("Number of Movies Borrowed");

	                // Check for valid date and valid number of movies borrowed
	                if (dateBorrowed != null && numMoviesBorrowed > 0) {
	                    report.append(dateBorrowed).append("\t\t").append(numMoviesBorrowed).append("\n");
	                }
	            }
	        } else if (choice.equals("Month")) {
	            report.append("Year\tMonth\tNumber of Movies Borrowed\n");
	            while (rs.next()) {
	                int year = rs.getInt("Year");
	                int month = rs.getInt("Month");
	                int numMoviesBorrowed = rs.getInt("Number of Movies Borrowed");

	                // Skip rows where year or month is 0
	                if (year != 0 && month != 0 && numMoviesBorrowed > 0) {
	                    report.append(year).append("\t").append(month).append("\t").append(numMoviesBorrowed).append("\n");
	                }
	            }
	        } else if (choice.equals("Year")) {
	            report.append("Year\tNumber of Movies Borrowed\n");
	            while (rs.next()) {
	                int year = rs.getInt("Year");
	                int numMoviesBorrowed = rs.getInt("Number of Movies Borrowed");

	                // Skip rows where year is 0
	                if (year != 0 && numMoviesBorrowed > 0) {
	                    report.append(year).append("\t").append(numMoviesBorrowed).append("\n");
	                }
	            }
	        }

	        // Display the report
	        JTextArea textArea = new JTextArea(report.toString());
	        textArea.setEditable(false);
	        textArea.setFont(new Font("Serif", Font.PLAIN, 14));  // Set a readable font
	        textArea.setBackground(new Color(245, 245, 245));  // Soft background color
	        textArea.setMargin(new Insets(10, 10, 10, 10));  // Add padding
	        JScrollPane scrollPane = new JScrollPane(textArea);
	        scrollPane.setPreferredSize(new Dimension(600, 400));

	        // Customize the window (Optional)
	        JPanel panel = new JPanel(new BorderLayout());
	        panel.setBackground(new Color(240, 240, 240));  // Soft background color
	        panel.add(scrollPane, BorderLayout.CENTER);

	        JOptionPane.showMessageDialog(null, panel, "Movies Borrowed Report (" + choice + ")", JOptionPane.INFORMATION_MESSAGE);

	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error retrieving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        ex.printStackTrace();
	    }
	}
	
	private void generateMostBorrowedMovies() {
	    String[] options = {"Monthly", "Yearly", "In General"};
	    String choice = (String) JOptionPane.showInputDialog(
	            null,
	            "Select report type:",
	            "Most Borrowed Movies",
	            JOptionPane.PLAIN_MESSAGE,
	            null,
	            options,
	            options[0]);

	    if (choice == null) {
	        return;
	    }

	    String query = "";
	    switch (choice) {
	        case "Monthly":
	            query = """
	                    SELECT YEAR(t.date_borrowed) AS `Year`, MONTH(t.date_borrowed) AS `Month`, 
	                           m.movie_code, m.movie_name, gt.description AS `Genre`, 
	                           COUNT(t.movie_code) AS `Times Borrowed`
	                    FROM transactions t
	                    JOIN movies m ON m.movie_code = t.movie_code
	                    JOIN genre_type gt ON gt.genre_id = m.genre_id
	                    WHERE YEAR(t.date_borrowed) = ? AND MONTH(t.date_borrowed) = ? AND gt.genre_id = ?
	                    GROUP BY YEAR(t.date_borrowed), MONTH(t.date_borrowed), m.movie_code
	                    ORDER BY `Times Borrowed` DESC;
	                    """;
	            break;
	        case "Yearly":
	            query = """
	                    SELECT YEAR(t.date_borrowed) AS `Year`, 
	                           m.movie_code, m.movie_name, gt.description AS `Genre`, 
	                           COUNT(t.movie_code) AS `Times Borrowed`
	                    FROM transactions t
	                    JOIN movies m ON m.movie_code = t.movie_code
	                    JOIN genre_type gt ON gt.genre_id = m.genre_id
	                    WHERE YEAR(t.date_borrowed) = ? AND gt.genre_id = ?
	                    GROUP BY YEAR(t.date_borrowed), m.movie_code
	                    ORDER BY `Times Borrowed` DESC;
	                    """;
	            break;
	        case "In General":
	            query = """
	                    SELECT m.movie_code, m.movie_name, gt.description AS `Genre`, 
	                           COUNT(t.movie_code) AS `Times Borrowed`
	                    FROM transactions t
	                    JOIN movies m ON m.movie_code = t.movie_code
	                    JOIN genre_type gt ON gt.genre_id = m.genre_id
	                    GROUP BY m.movie_code
	                    ORDER BY `Times Borrowed` DESC;
	                    """;
	            break;
	    }

	    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	        if (!choice.equals("In General")) {
	            String year = JOptionPane.showInputDialog("Enter the Year (YYYY):");
	            pstmt.setString(1, year);

	            if (choice.equals("Monthly")) {
	                String month = monthSelector();
	                pstmt.setString(2, month);
	            }

	            String genreId = JOptionPane.showInputDialog("Enter the Genre ID:");
	            pstmt.setString(choice.equals("Monthly") ? 3 : 2, genreId);
	        }

	        ResultSet rs = pstmt.executeQuery();

	        String[] columnNames = choice.equals("In General")
	                ? new String[]{"Movie Code", "Movie Name", "Genre", "Times Borrowed"}
	                : choice.equals("Monthly")
	                ? new String[]{"Year", "Month", "Movie Code", "Movie Name", "Genre", "Times Borrowed"}
	                : new String[]{"Year", "Movie Code", "Movie Name", "Genre", "Times Borrowed"};
	        
	        List<Object[]> rows = new ArrayList<>();

	        while (rs.next()) {
	            if (choice.equals("Monthly")) {
	                rows.add(new Object[]{
	                        rs.getInt("Year"),
	                        rs.getInt("Month"),
	                        rs.getString("movie_code"),
	                        rs.getString("movie_name"),
	                        rs.getString("Genre"),
	                        rs.getInt("Times Borrowed")
	                });
	            } else if (choice.equals("Yearly")) {
	                rows.add(new Object[]{
	                        rs.getInt("Year"),
	                        rs.getString("movie_code"),
	                        rs.getString("movie_name"),
	                        rs.getString("Genre"),
	                        rs.getInt("Times Borrowed")
	                });
	            } else {
	                rows.add(new Object[]{
	                        rs.getString("movie_code"),
	                        rs.getString("movie_name"),
	                        rs.getString("Genre"),
	                        rs.getInt("Times Borrowed")
	                });
	            }
	        }

	        displayResultsInTable(columnNames, rows, "Most Borrowed Movies (" + choice + ")");
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error retrieving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        ex.printStackTrace();
	    }
	}

	private void displayResultsInTable(String[] columnNames, List<Object[]> rows, String title) {
	    DefaultTableModel tableModel = new DefaultTableModel(rows.toArray(new Object[0][0]), columnNames) {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };

	    JTable table = new JTable(tableModel);
	    JScrollPane scrollPane = new JScrollPane(table);

	    JPanel panel = new JPanel(new BorderLayout());
	    panel.add(scrollPane, BorderLayout.CENTER);

	    JOptionPane.showMessageDialog(null, panel, title, JOptionPane.INFORMATION_MESSAGE);
	}

	private void generatePopularGenresReport() {
	    // Options to choose the type of report, removing "Day" option
	    String[] options = {"Month", "Year"};
	    String choice = (String) JOptionPane.showInputDialog(
	            null,
	            "Select the type of report:",
	            "Movies Borrowed Report",
	            JOptionPane.PLAIN_MESSAGE,
	            null,
	            options,
	            options[0]);

	    if (choice == null) {
	        return; // User cancelled
	    }

	    // SQL queries based on the choice
	    String query = "";
	    switch (choice) {
	        case "Month":
	            query = """
	                    SELECT YEAR(t.date_borrowed) AS `Year`, MONTH(t.date_borrowed) AS `Month`, 
	                           gt.genre_id, gt.description AS `Genre`, COUNT(t.movie_code) AS `Times Borrowed`
	                    FROM transactions t
	                    JOIN movies m ON m.movie_code = t.movie_code
	                    JOIN genre_type gt ON gt.genre_id = m.genre_id
	                    WHERE YEAR(t.date_borrowed) = ? AND MONTH(t.date_borrowed) = ?
	                    GROUP BY YEAR(t.date_borrowed), MONTH(t.date_borrowed), gt.genre_id
	                    ORDER BY `Times Borrowed` DESC;
	                    """;
	            break;
	        case "Year":
	            query = """
	                    SELECT YEAR(t.date_borrowed) AS `Year`, gt.genre_id, gt.description AS `Genre`, 
	                           COUNT(t.movie_code) AS `Times Borrowed`
	                    FROM transactions t
	                    JOIN movies m ON m.movie_code = t.movie_code
	                    JOIN genre_type gt ON gt.genre_id = m.genre_id
	                    WHERE YEAR(t.date_borrowed) = ?
	                    GROUP BY YEAR(t.date_borrowed), gt.genre_id
	                    ORDER BY `Times Borrowed` DESC;
	                    """;
	            break;
	        default:
	            JOptionPane.showMessageDialog(null, "Invalid option selected.");
	            return;
	    }

	    // Database Connection and Execution
	    try (
	         PreparedStatement pstmt = connection.prepareStatement(query)) {

	        // Prompt user for the month and/or year
	        if (choice.equals("Month")) {
	            String month = monthSelector();
	            String year = JOptionPane.showInputDialog("Enter the Year (YYYY):");
	            pstmt.setString(1, year);
	            pstmt.setString(2, month);
	        } else if (choice.equals("Year")) {
	            String year = JOptionPane.showInputDialog("Enter the Year (YYYY):");
	            pstmt.setString(1, year);
	        }

	        ResultSet rs = pstmt.executeQuery();

	        // Define column names for the table
	        String[] columnNames = (choice.equals("Month"))
	            ? new String[]{"Year", "Month", "Genre", "Times Borrowed"}
	            : new String[]{"Year", "Genre", "Times Borrowed"};

	        // Collect the rows for the table
	        List<Object[]> rows = new ArrayList<>();
	        while (rs.next()) {
	            int year = rs.getInt("Year");
	            String genre = rs.getString("Genre");
	            int timesBorrowed = rs.getInt("Times Borrowed");

	            if (timesBorrowed > 0) {
	                if (choice.equals("Month")) {
	                    int month = rs.getInt("Month");
	                    rows.add(new Object[]{year, month, genre, timesBorrowed});
	                } else {
	                    rows.add(new Object[]{year, genre, timesBorrowed});
	                }
	            }
	        }

			jTableInstantiate(columnNames, rows, "Popular Genres");

	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error retrieving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        ex.printStackTrace();
	    }
	}

	private void generateMovieRequestsReport() {
	    // Query for fetching the movie requests and their approval statuses
	    String query = """
	            SELECT mr.date_filed,mr.movie_name,mr.media_type,
	    		CASE 
			        WHEN mr.approved = 1 THEN 'Approved'
			        WHEN mr.approved = 0 THEN 'Rejected'
			        WHEN mr.approved IS NULL THEN 'Processing'
			    END AS `Status`
				FROM movie_req mr
				JOIN users u ON u.user_no = mr.user_no
				WHERE mr.date_filed IS NOT NULL
				ORDER BY mr.date_filed;
	            """;

	    // Database Connection and Execution
	    try (PreparedStatement pstmt = connection.prepareStatement(query)) {

	        ResultSet rs = pstmt.executeQuery();

	        // Column names for the table
	        String[] columnNames = {"Date Filed", "Movie Name", "Media Type", "Status"};

	        // Collect the rows for the table
	        List<Object[]> rows = new ArrayList<>();
	        while (rs.next()) {
	            String dateFiled = rs.getString("date_filed");
	            String movieName = rs.getString("movie_name");
	            String mediaType = rs.getString("media_type");
	            String status = rs.getString("Status");

	            // Add the row to the list
	            rows.add(new Object[]{dateFiled, movieName, mediaType, status});
	        }

	        // Create a DefaultTableModel with the fetched data
	        DefaultTableModel tableModel = new DefaultTableModel(rows.toArray(new Object[0][0]), columnNames) {
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false; // Make all cells non-editable
	            }
	        };

	        // Create the JTable using the DefaultTableModel
	        JTable table = new JTable(tableModel);
	        table.setEnabled(true); // Enable row selection
	        table.setFont(new Font("Serif", Font.PLAIN, 14)); // Set font for readability

	        // Set up the JScrollPane for the table
	        JScrollPane scrollPane = new JScrollPane(table);
	        scrollPane.setPreferredSize(new Dimension(600, 300)); // Set preferred size

	        // Panel to contain the JScrollPane
	        JPanel panel = new JPanel(new BorderLayout());
	        panel.setBackground(new Color(240, 240, 240)); // Soft background color
	        panel.add(scrollPane, BorderLayout.CENTER);

	        // Show the table in a message dialog
	        JOptionPane.showMessageDialog(null, panel, "Movie Requests Report", JOptionPane.INFORMATION_MESSAGE);

	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error retrieving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        ex.printStackTrace();
	    }
	}


	private void generateMostRequestedMoviesReport() {
	    // Options to choose the type of report
	    String[] options = {"General", "Monthly", "Yearly"};
	    String choice = (String) JOptionPane.showInputDialog(
	            null,
	            "Select the type of report:",
	            "Most Requested Movies Report",
	            JOptionPane.PLAIN_MESSAGE,
	            null,
	            options,
	            options[0]);

	    if (choice == null) {
	        return; // User cancelled
	    }

	    // SQL queries based on the choice
	    String query = "";
	    switch (choice) {
	        case "General":
	            query = """
	                    SELECT mr.movie_name, COUNT(mr.movie_name) AS `Number of Requests`
	                    FROM movie_req mr
	                    JOIN users u ON u.user_no = mr.user_no
	                    GROUP BY mr.movie_name
	                    ORDER BY `Number of Requests` DESC;
	                    """;
	            break;
	        case "Monthly":
	            query = """
	                    SELECT YEAR(mr.date_filed) AS `Year`, MONTH(mr.date_filed) AS `Month`,
	                           mr.movie_name, COUNT(mr.movie_name) AS `Number of Requests`
	                    FROM movie_req mr
	                    JOIN users u ON u.user_no = mr.user_no
	                    WHERE YEAR(mr.date_filed) = ? AND MONTH(mr.date_filed) = ?
	                    GROUP BY YEAR(mr.date_filed), MONTH(mr.date_filed), mr.movie_name
	                    ORDER BY `Number of Requests` DESC;
	                    """;
	            break;
	        case "Yearly":
	            query = """
	                    SELECT YEAR(mr.date_filed) AS `Year`, mr.movie_name, COUNT(mr.movie_name) AS `Number of Requests`
	                    FROM movie_req mr
	                    JOIN users u ON u.user_no = mr.user_no
	                    WHERE YEAR(mr.date_filed) = ?
	                    GROUP BY YEAR(mr.date_filed), mr.movie_name
	                    ORDER BY `Number of Requests` DESC;
	                    """;
	            break;
	        default:
	            JOptionPane.showMessageDialog(null, "Invalid option selected.");
	            return;
	    }

	    // Database Connection and Execution
	    try (
	         PreparedStatement pstmt = connection.prepareStatement(query)) {

	        // Prompt user for the month and/or year if necessary
	        if (choice.equals("Monthly")) {
	            String month = monthSelector();
	            String year = JOptionPane.showInputDialog("Enter the Year (YYYY):");
	            pstmt.setString(1, year);
	            pstmt.setString(2, month);
	        } else if (choice.equals("Yearly")) {
	            String year = JOptionPane.showInputDialog("Enter the Year (YYYY):");
	            pstmt.setString(1, year);
	        }

	        ResultSet rs = pstmt.executeQuery();

	        // Define column names for the table
	        String[] columnNames = switch (choice) {
	            case "General" -> new String[]{"Movie Name", "Number of Requests"};
	            case "Monthly" -> new String[]{"Year", "Month", "Movie Name", "Number of Requests"};
	            case "Yearly" -> new String[]{"Year", "Movie Name", "Number of Requests"};
	            default -> throw new IllegalStateException("Unexpected value: " + choice);
	        };

	        // Collect the rows for the table
	        List<Object[]> rows = new ArrayList<>();
	        while (rs.next()) {
	            if (choice.equals("General")) {
	                String movieName = rs.getString("movie_name");
	                int numberOfRequests = rs.getInt("Number of Requests");
	                rows.add(new Object[]{movieName, numberOfRequests});
	            } else if (choice.equals("Monthly")) {
	                int year = rs.getInt("Year");
	                int month = rs.getInt("Month");
	                String movieName = rs.getString("movie_name");
	                int numberOfRequests = rs.getInt("Number of Requests");
	                rows.add(new Object[]{year, month, movieName, numberOfRequests});
	            } else if (choice.equals("Yearly")) {
	                int year = rs.getInt("Year");
	                String movieName = rs.getString("movie_name");
	                int numberOfRequests = rs.getInt("Number of Requests");
	                rows.add(new Object[]{year, movieName, numberOfRequests});
	            }
	        }

	        jTableInstantiate(columnNames, rows, "Most Requested Movies");
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error retrieving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        ex.printStackTrace();
	    }
	}

	
	private void generatePolicyViolationReport(){

		String query = """
			SELECT u.user_no,u.first_name,u.last_name,m.movie_name,t.date_borrowed,t.date_toreturn,t.date_returned,t.payment,IF(t.date_returned IS NOT NULL, 'Resolved', 'Unresolved') AS `Status`
			FROM transactions t
			JOIN users u ON t.user_no = u.user_no
			JOIN movies m ON t.movie_code = m.movie_code
			WHERE t.date_toreturn < t.date_returned OR (t.date_toreturn < CURDATE() AND t.date_returned IS NULL)
			ORDER BY t.transaction_no;

		""";

		

		try (
	         PreparedStatement pstmt = connection.prepareStatement(query)) {

	        ResultSet rs = pstmt.executeQuery();

			String[] columnNames = new String[]{"User Number", "First Name", "Last Name", "Movie Name", "Date Borrowed", "Set Return Date", "Actual Return Date", "Payment", "Status"};

	        // Collect the rows for the table
	        List<Object[]> rows = new ArrayList<>();
	        while (rs.next()) {

				Integer userNum = rs.getInt("user_no"),
						payment = rs.getInt("payment");
				String 	firstName = rs.getString("first_name"),
						lastName  = rs.getString("last_name"),
						movie_name = rs.getString("movie_name"),
						status = rs.getString("Status");
				Date dateBorrowed = rs.getDate("date_borrowed"),
					dateToReturn = rs.getDate("date_toreturn"),
					dateReturned = rs.getDate("date_returned");
					rows.add(new Object[]{userNum, firstName, lastName, movie_name, dateBorrowed, dateToReturn, dateReturned, payment, status});

	        }

	        // Create the DefaultTableModel with the fetched data
			jTableInstantiate(columnNames, rows, "Policy Violators");

	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error retrieving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        ex.printStackTrace();
	    }

	}

	private String monthSelector(){

		String[] months = {	"January", 
							"Febuary", 
							"March", 
							"April", 
							"May", 
							"June", 
							"July", 
							"August", 
							"September", 
							"October", 
							"November", 
							"December"};
		String month = (String) JOptionPane.showInputDialog(
	            null,
	            "Choose Month",
	            "Revenue from Movie Rentals",
	            JOptionPane.PLAIN_MESSAGE,
	            null,
	            months,
	            months[0]);

				switch(month){
					case "January" -> month = "1";
					case "Febuary" -> month = "2";
					case "March" -> month = "3";
					case "April" -> month = "4";
					case "May" -> month = "5";
					case "June" -> month = "6";
					case "July" -> month = "7";
					case "August" -> month = "8";
					case "September" -> month = "9";
					case "October" -> month = "10";
					case "November" -> month = "11";
					case "December" -> month = "12";
				}
			return month;	

	}

	private void generateTopRevenueUsers(){

		String[] options = {"Month", "Year"};
		String choice = (String) JOptionPane.showInputDialog(
	            null,
	            "Choose Top Revenue Timespan",
	            "Top Revenue Users",
	            JOptionPane.PLAIN_MESSAGE,
	            null,
	            options,
	            options[0]);
				if (choice == null) {
					return; // User cancelled
				}
				String query = "";
				switch (choice) {
					case "Month":
						query = """
								SELECT YEAR(t.date_returned) 
										AS `YEAR`,MONTH(t.date_returned) AS `MONTH`,u.first_name,u.last_name,SUM(t.payment) 
										AS `Total Payments`,SUM(TIMESTAMPDIFF(DAY, t.date_borrowed, t.date_toreturn) * mt.rental_price)
										AS `Rental Fees`,SUM(t.payment - (TIMESTAMPDIFF(DAY, t.date_borrowed, t.date_toreturn) * mt.rental_price)) 
										AS `Late and Additional Fees`
								FROM users u
								JOIN transactions t ON u.user_no = t.user_no
								JOIN media_type mt ON t.product_id = mt.product_id
								WHERE t.date_returned IS NOT NULL AND YEAR(t.date_returned) = ? AND MONTH(t.date_returned) = ?
								GROUP BY u.user_no,`YEAR`,`MONTH`
								ORDER BY `Total Payments` DESC;
								""";
						break;
					case "Year":
						query = """
								SELECT YEAR(t.date_borrowed) AS `Year`, gt.genre_id, gt.description AS `Genre`, 
									   COUNT(t.movie_code) AS `Times Borrowed`
								FROM transactions t
								JOIN movies m ON m.movie_code = t.movie_code
								JOIN genre_type gt ON gt.genre_id = m.genre_id
								WHERE YEAR(t.date_borrowed) = ?
								GROUP BY YEAR(t.date_borrowed), gt.genre_id
								ORDER BY `Times Borrowed` DESC;
								""";
						break;
					default:
						JOptionPane.showMessageDialog(null, "Invalid option selected.");
						return;
				}

				try (
	         PreparedStatement pstmt = connection.prepareStatement(query)) {
	        
	        // Prompt user for the month and/or year
	        if (choice.equals("Month")) {
	            String month = monthSelector();
	            String year = JOptionPane.showInputDialog("Enter the Year (YYYY):");
	            pstmt.setString(1, year);
	            pstmt.setString(2, month);
	        } else if (choice.equals("Year")) {
	            String year = JOptionPane.showInputDialog("Enter the Year (YYYY):");
	            pstmt.setString(1, year);
	        }

	        ResultSet rs = pstmt.executeQuery();
			List<Object[]> rows = new ArrayList<>();
	        // Generate the report
	        StringBuilder report = new StringBuilder();
			String[] columnNames = switch (choice) {
				case "Year" -> new String[]{"Year", "First Name", "Last Name", "Total Payments", "Rental Fees", "Late Fees"};
				case "Month" -> new String[]{"Year", "Month", "First Name", "Last Name", "Total Payments", "Rental Fees", "Late Fees"};
				default -> throw new IllegalStateException("Unexpected value: " + choice);
				};

	        if (choice.equals("Month")) {
	            while (rs.next()) {
	                int year = rs.getInt("Year"),
	                	month = rs.getInt("Month"),
	                	totalPayments = rs.getInt("Total Payments"),
						rentalFees = rs.getInt("Rental fees"),
						lateFees= rs.getInt("Late and Additional Fees");
					String 	firstName = rs.getString("first_Name"),
	            			lastName = rs.getString("last_Name");
					rows.add(new Object[]{year, month, firstName, lastName, totalPayments, rentalFees, lateFees});
	            }
	        } else if (choice.equals("Year")) {
	            while (rs.next()) {
	                int year = rs.getInt("Year"),
	                	totalPayments = rs.getInt("Total Payments"),
						rentalFees = rs.getInt("Rental fees"),
						lateFees= rs.getInt("Late and Additional Fees");
					String 	firstName = rs.getString("first_Name"),
	            			lastName = rs.getString("last_Name");
					rows.add(new Object[]{year,  firstName, lastName, totalPayments, rentalFees, lateFees});
	            }
	        }

			 jTableInstantiate(columnNames, rows, "Most Profitable Users");
			

	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error retrieving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        ex.printStackTrace();
	    }
	

	}
	
        @SuppressWarnings("empty-statement")
	private void generateRevenueReport() {
	    String[] options = {"Month", "Year"};
		//String[] months = {"January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	    String choice = (String) JOptionPane.showInputDialog(
	            null,
	            "Choose Fiscal Timespan",
	            "Revenue from Movie Rentals",
	            JOptionPane.PLAIN_MESSAGE,
	            null,
	            options,
	            options[0]);
				if (choice == null) {
					return; // User cancelled
				}
				
				String query = "";
				switch (choice) {
					case "Month":
						query = """
								SELECT 
									YEAR(t.date_returned) AS `Year`,
									MONTH(t.date_returned) AS `Month`,
									SUM(t.payment) AS `Total Revenue`
								FROM
									transactions t
								WHERE 
									YEAR(t.date_returned) = ? AND 
									MONTH(t.date_returned) = ? AND 
									t.date_returned IS NOT NULL
								GROUP BY 
									`Year`,
									`Month`;
								""";
						break;
					case "Year":
						query = """
								SELECT 
									YEAR(t.date_returned) AS `Year`,
									SUM(t.payment) AS `Total Revenue`
								FROM 
									transactions t
								WHERE 
									YEAR(t.date_returned) = ?
								GROUP BY 
									`Year`;
								""";
						break;
					default:
						JOptionPane.showMessageDialog(null, "Invalid option selected.");
						return;
				}
		try (
	         PreparedStatement pstmt = connection.prepareStatement(query)) {
	        
	        // Prompt user for the month and/or year
	        if (choice.equals("Month")) {

				String month = monthSelector();
	            String year = JOptionPane.showInputDialog("Enter the Year (YYYY):");
				
	            pstmt.setString(1, year);
	            pstmt.setString(2, month);
	        } else if (choice.equals("Year")) {
	            String year = JOptionPane.showInputDialog("Enter the Year (YYYY):");
	            pstmt.setString(1, year);
	        }

			
	        ResultSet rs = pstmt.executeQuery();
			List<Object[]> rows = new ArrayList<>();
	        // Generate the report
	        StringBuilder report = new StringBuilder();

			String[] columnNames = switch (choice) {
				case "Year" -> new String[]{"Year", "Total Revenue"};
				case "Month" -> new String[]{"Year", "Month", "Total Revenue"};
				default -> throw new IllegalStateException("Unexpected value: " + choice);
				};
			
	        if (choice.equals("Year")) {
				
			
	            while (rs.next()) {
					
	                int year = rs.getInt("Year");
	                int totalRev = rs.getInt("Total Revenue");
	                rows.add(new Object[]{year, totalRev});
	            }
	        } else if (choice.equals("Month")) {
				
	            while (rs.next()) {
					
	                int year = rs.getInt("Year");
					int month = rs.getInt("Month");
	                int totalRev = rs.getInt("Total Revenue");
	                rows.add(new Object[]{year, month, totalRev});
	            }
	        }
			 
	        // Display the report
			DefaultTableModel tableModel = new DefaultTableModel(rows.toArray(new Object[0][0]), columnNames) {
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false; // Make all cells non-editable
	            }
	        };
	        jTableInstantiate(columnNames, rows, "Revenue Report");
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error retrieving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        ex.printStackTrace();
	    }
	}

	private void jTableInstantiate(String[] columnNames,List<Object[]> rows, String dialogueName){

		DefaultTableModel tableModel = new DefaultTableModel(rows.toArray(new Object[0][0]), columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Make all cells non-editable
			}
		};

		JTable table = new JTable(tableModel);
	        table.setFont(new Font("Serif", Font.PLAIN, 14)); // Set font for readability

	        // Set up the JScrollPane for the table
	        JScrollPane scrollPane = new JScrollPane(table);
	        scrollPane.setPreferredSize(new Dimension(1000, 300)); // Set preferred size

	        // Panel to contain the JScrollPane
	        JPanel panel = new JPanel(new BorderLayout());
	        panel.setBackground(new Color(240, 240, 240)); // Soft background color
	        panel.add(scrollPane, BorderLayout.CENTER);

	        // Show the table in a message dialog
	        JOptionPane.showMessageDialog(null, panel, dialogueName, JOptionPane.INFORMATION_MESSAGE);
	}

	private void generateRentalHistoryReport(){
		String query = """
			SELECT m.movie_code,m.movie_name,u.user_no,CONCAT(u.first_name,' ',u.last_name) AS `Borrower Name`,t.date_borrowed,t.date_toreturn,t.date_returned
			FROM transactions t
			JOIN users u ON t.user_no = u.user_no
			JOIN movies m ON m.movie_code = t.movie_code
			WHERE m.movie_code = ?
			ORDER BY m.movie_code;

		""";
		try (
	         PreparedStatement pstmt = connection.prepareStatement(query)) {

			String movieCodeInput = JOptionPane.showInputDialog("Enter Movie Code");
	        pstmt.setString(1, movieCodeInput);
	        ResultSet rs = pstmt.executeQuery();

	        // Collect the rows for the table
	        List<Object[]> rows = new ArrayList<>();
	        while (rs.next()) {
				Integer userNum = rs.getInt("user_no"),
						movieCode = rs.getInt("movie_code");
				String 	borrowerName = rs.getString("Borrower Name"),
						movie_name = rs.getString("movie_name");
				Date dateBorrowed = rs.getDate("date_borrowed"),
					dateToReturn = rs.getDate("date_toreturn"),
					dateReturned = rs.getDate("date_returned");
					rows.add(new Object[]{movieCode, movie_name, userNum, borrowerName, dateBorrowed, dateToReturn, dateReturned});
	        }

			String[] columnNames = new String[]{"Movie Code", "Movie Name", "User Number", "Borrower Name", "Date Borrowed", "Set Return Date", "Actual Return Date"};
	        // Create the DefaultTableModel with the fetched data

			jTableInstantiate(columnNames, rows, "Rental History Report");
	        // DefaultTableModel tableModel = new DefaultTableModel(rows.toArray(new Object[0][0]), columnNames) {
	        //     @Override
	        //     public boolean isCellEditable(int row, int column) {
	        //         return false; // Make all cells non-editable
	        //     }
	        // };

	        // // Create the JTable using the DefaultTableModel
	        // JTable table = new JTable(tableModel);
	        // table.setFont(new Font("Serif", Font.PLAIN, 14)); // Set font for readability

	        // // Set up the JScrollPane for the table
	        // JScrollPane scrollPane = new JScrollPane(table);
	        // scrollPane.setPreferredSize(new Dimension(1000, 300)); // Set preferred size

	        // // Panel to contain the JScrollPane
	        // JPanel panel = new JPanel(new BorderLayout());
	        // panel.setBackground(new Color(240, 240, 240)); // Soft background color
	        // panel.add(scrollPane, BorderLayout.CENTER);

	        // // Show the table in a message dialog
	        // JOptionPane.showMessageDialog(null, panel, "Rental History", JOptionPane.INFORMATION_MESSAGE);

	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error retrieving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        ex.printStackTrace();
	    }
	}
	//END OF REPORTS
	
	public void setActionListener(ActionListener listener) {
		//btnTableInput.addActionListener(listener);
		btnRecordManagement.addActionListener(listener);
		btnReports.addActionListener(listener);
		btnTransactions.addActionListener(listener);
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
       	
       	btnUpdateMoviesTable.addActionListener(listener);
       	btnDeleteInMoviesTable.addActionListener(listener); 
       	btnAddInMoviesTable.addActionListener(listener);
    	//review table
    	btnUpdateReviewTable.addActionListener(listener); 
    	btnDeleteInReviewTable.addActionListener(listener);
    	btnAddInReviewTable.addActionListener(listener);

       	
    	btnMovieRecord.addActionListener(listener);
    	btnUserRecord.addActionListener(listener);
    	btnAdminRecord.addActionListener(listener);
    	btnMediaTypeRecord.addActionListener(listener);
    	btnReturntoMain.addActionListener(listener);
    	
    	btnUpdateUserTable.addActionListener(listener);
    	btnDeleteInUserTable.addActionListener(listener);
    	btnAddInUserTable.addActionListener(listener);
    	
    	btnUpdateTransactionTable.addActionListener(listener);
    	btnDeleteInTransactionTable.addActionListener(listener);
    	btnAddInTransactionTable.addActionListener(listener);
 
    	btnMoviesBorrowed.addActionListener(listener);
	    btnMostBorrowedMovies.addActionListener(listener);
	    btnPopularGenres.addActionListener(listener);
	    btnApprovedRequests.addActionListener(listener);
	    btnMostRequestedMovies.addActionListener(listener);
	    btnRentalHistory.addActionListener(listener);
	    btnPolicyViolations.addActionListener(listener);
	    btnRevenueReport.addActionListener(listener);
	    btnTopRevenueUsers.addActionListener(listener);
	    btnReturntoMainFromReport.addActionListener(listener);
	    
	    btnReturnRecordManagementfromUR.addActionListener(listener);
		btnSelectuserRecord.addActionListener(listener);
		
		btnReturnToUserRecord.addActionListener(listener);
		btnMRMreturn.addActionListener(listener);
		btnMRMselect.addActionListener(listener);

	    btnMoviesBorrowed.addActionListener(e -> generateMoviesBorrowedReport());
	    btnMostBorrowedMovies.addActionListener(e -> generateMostBorrowedMovies());
	    btnPopularGenres.addActionListener(e -> generatePopularGenresReport());
	    btnApprovedRequests.addActionListener(e -> generateMovieRequestsReport());
	    btnMostRequestedMovies.addActionListener(e -> generateMostRequestedMoviesReport());
	    btnPolicyViolations.addActionListener(e -> generatePolicyViolationReport());
		btnRentalHistory.addActionListener(e-> generateRentalHistoryReport());
		btnRevenueReport.addActionListener(e -> generateRevenueReport());
		btnTopRevenueUsers.addActionListener(e -> generateTopRevenueUsers());
	}
	
	
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
	
	public int getMmovie_code() {
	    return Integer.parseInt(Mmovie_code.getText());
	}
	
	public void setMmovie_code(String num) {
		Mmovie_code.setText(num);
	}

	public int getMyear() {
	    return Integer.parseInt(Myear.getText());
	}
	
	public void setMyear(String num) {
		Myear.setText(num);
	}
	
	public int getMgenre_id() {
	    return Integer.parseInt(Mgenre_id.getText());
	}
	
	public void setMgenre_id(String num) {
		Mgenre_id.setText(num);
	}

	public String getMmovie_name() {
	    return Mmovie_name.getText();
	}
	
	public void setMmovie_name(String num) {
		Mmovie_name.setText(num);
	}
	
	public String getMlanguage() {
	    return Mlanguage.getText();
	}
	
	public void setMlanguage(String num) {
		Mlanguage.setText(num);
	}

	public String getMrating() {
	    return Mrating.getSelectedItem().toString();
	}
	
	public void setMrating(String num) {
		Mrating.setSelectedItem(num);
	}
	
	public int getRreview_no() {
	    return Integer.parseInt(Rreview_no.getText());
	}
	
	public void setRreview_no(String num) {
		Rreview_no.setText(num);
	}

	public int getRmovie_code() {
	    return Integer.parseInt(Rmovie_code.getText());
	}
	
	public void setRmovie_code(String num) {
		Rmovie_code.setText(num);
	}

	public int getRuser_no() {
	    return Integer.parseInt(Ruser_no.getText());
	}
	
	public void setRuser_no(String num) {
		Ruser_no.setText(num);
	}

	public String getRreview() {
	    return Rreview.getText();
	}
	
	public void setRreview(String num) {
		Rreview.setText(num);
	}

	public int getRstars() {
	    return Integer.parseInt(Rstars.getSelectedItem().toString());
	}
	
	public void setRstars(String num) {
		Rstars.setSelectedItem(num);
	}
	
	public String getURuser_no() {
	    return URuser_no.getText();
	}
	
	public void setURuser_no(String num) {
		URuser_no.setText(num);
	}
	
	public String getURfirst_name() {
	    return URfirst_name.getText();
	}
	
	public void setURfirst_name(String num) {
		URfirst_name.setText(num);
	}

	public String getURlast_name() {
	    return URlast_name.getText();
	}
	
	public void setURlast_name(String num) {
		URlast_name.setText(num);
	}
	
	 public void setUPfirstname(String name) {
		   UPfirstname.setText("   First Name: " + name);
		}

	 public void setUPuserno(int name) {
		 UPuserno.setText("   User No: " + name);
		}
	 
	 public void setUPlastName(String name) {
		 UPlastName.setText("   Last Name: " + name);
		}

	 public String getMRMmovie_code() {
		    return MRMmovie_code.getText();
		}
		
		public void setMRMmovie_code(String num) {
			MRMmovie_code.setText(num);
		}

		
	 public String getMRMmovie_name() {
		    return MRMmovie_name.getText();
		}
		
		public void setMRMmovie_name(String num) {
			MRMmovie_name.setText(num);
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
		
		setMmovie_code("");
		setMmovie_name("");
		setMyear("");
		setMrating("");
		setMlanguage("");
		setMgenre_id("");
		
		setRmovie_code("");
		setRreview("");
		setRstars("");
		setRreview_no("");
		setRuser_no("");
		
	}
	
	
}
