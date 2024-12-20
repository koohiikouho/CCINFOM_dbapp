import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class GUI extends JFrame{
	
	//main menu
	private JButton btnRecordManagement,btnReports, btnEXIT, btnTransactions;
	//records
	private JButton btnMovieRecord,btnUserRecord,btnAdminRecord, btnMediaTypeRecord, btnReturntoMain;
	
	// reports
	// Declare buttons as instance variables
	private JButton btnMoviesBorrowed,btnMostBorrowedMovies, btnPopularGenres;
	private JButton btnApprovedRequests, btnMostRequestedMovies, btnRentalHistory;
	private JButton btnPolicyViolations, btnRevenueReport, btnTopRevenueUsers,btnReturntoMainFromReport;
	
	// Declare buttons for Transaction Management
	private JButton btnBorrowMovie;
	private JButton btnReturnMovie;
	private JButton btnReviewMovieRequests;
	private JButton btnFormalizeMovieRequests;
	private JButton btnReturntoMainFromTransaction;
	
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

	private JButton btnReturnToUserRecord,btnReturnFromMediaManagement;
	
	final private JPanel MainMenu = new JPanel(), RecordManagement = new JPanel();
	final private JPanel TableInput = new JPanel();
	final private JPanel ReportManagement = new JPanel();
	final private JPanel Transactions = new JPanel();
	//tables
	final private JPanel 	AdminTable = new JPanel(), GenreTypeTable = new JPanel(), 
					Media_TypeTable = new JPanel(), Movie_reqTable = new JPanel(),	
					MoviesTable = new JPanel(), ReviewTable = new JPanel(), TransactionsTable = new JPanel(), UsersTable = new JPanel();
	
	final private JPanel UserRecord = new JPanel();
	final private JPanel UserProfile = new JPanel();
	final private JPanel MovieRecord = new JPanel();
	final private JPanel MediaTypeRecord = new JPanel();
	final private JPanel ADDINGMOVIEREQ = new JPanel();
	final private JPanel FileMovieReq = new JPanel();
	
	// admin table text fields
	private JTextField ATAdminNo, ATFirst_Name, ATLast_Name, ATPass, ATAdminLevel;
	//genre table 
	private JTextField GTGenre_Num;
	private JTextArea GTDesc;
	//media type
	private JTextField MTproduct_id, MTmovie_code, MTrelease, MTcopies, MTrentprice;
	private JComboBox MTmedia_type;
	//movie req
	private JTextField MRrequest_no, MRmovie_name, MRdate_filled, MRuser_no;
	private JComboBox MRmedia_type, MRapproved;//MRin_stock
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
	
	
	private JTextField Ttransaction_no, Tmovie_code, Tuser_no, Tdate_borrowed, Tdate_toreturn, Tdate_returned,Tpayment, Tadmin_bo,Tadmin_re,Tproduct_id;
	private JScrollPane scrollerTransactionTable;
	private JTable tableTransactionTable;
	private DefaultTableModel tableModelTransaction;
	
	private JScrollPane scrollerUserProfile;
	private JTable tableUserProfile;
	private DefaultTableModel tableModelUserProfile;
	
	private JScrollPane scrollerMediaRecord;
	private JTable tableMediaRecord;
	private DefaultTableModel tableModelMediaRecord;
	
	JLabel UPuserno ,UPfirstname, UPlastName,UPpass,UPmembership;

	//storing admin number for transactions
	private String loggedInAdmin;

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

		Transactions.setLayout(new BorderLayout());
		transactionmanagement();
		
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
		
		MediaTypeRecord.setLayout(new BorderLayout());
		showMediaRecordTable();
		MediaRecordTablePanel();
		
		ADDINGMOVIEREQ.setLayout(new BorderLayout());
		showMovie_reqTransactionTable();
		Movie_reqTransactionTablePanel();

		FileMovieReq.setLayout(new BorderLayout());
		showFileRequestPanel();
		fileMovieRequestPanel();
		
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
		                // Safely retrieve values from the table
		                Object admin_number = tableAdminTable.getValueAt(row, 0);
		                Object first_name = tableAdminTable.getValueAt(row, 1);
		                Object last_name = tableAdminTable.getValueAt(row, 2);
		                Object password = tableAdminTable.getValueAt(row, 3);
		                Object admin_lvl = tableAdminTable.getValueAt(row, 4);

		                // Set values with null checks
		                ATAdminNo.setText(admin_number != null ? admin_number.toString() : "");
		                ATFirst_Name.setText(first_name != null ? first_name.toString() : "");
		                ATLast_Name.setText(last_name != null ? last_name.toString() : "");
		                ATPass.setText(password != null ? password.toString() : "");
		                ATAdminLevel.setText(admin_lvl != null ? admin_lvl.toString() : "");
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
	                // Safely retrieve values from the table
	                Object genre_number = tableGenreTable.getValueAt(row, 0);
	                Object description = tableGenreTable.getValueAt(row, 1);

	                // Set values with null checks
	                GTGenre_Num.setText(genre_number != null ? genre_number.toString() : "");
	                GTDesc.setText(description != null ? description.toString() : "");
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

	
	
	
	
	public void showMediaRecordTable() {
		  String[] col = {"product_id", "movie_name", "movie_code","release_date","media_type","copies_available","rental_price"};	    
		  tableModelMediaRecord = new DefaultTableModel(getMedia(), col) {
           @Override
           public boolean isCellEditable(int row, int column) {
               return false; // Disable editing for all cells
           }
       };
;
	    tableMediaRecord = new JTable(tableModelMediaRecord);
	    tableMediaRecord.setEnabled(true); // Enable selection
	    
	   
	    scrollerMediaRecord = new JScrollPane(tableMediaRecord);
	    scrollerMediaRecord.setPreferredSize(new Dimension(450, 200)); // Set preferred size
	    
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
	    panelCenter.add(scrollerMediaRecord, gbc);
	    moreCenter.add(panelCenter, BorderLayout.CENTER);
	    
	    MediaTypeRecord.add(moreCenter, BorderLayout.CENTER);
	    MediaTypeRecord.revalidate(); // Refresh the UI
	    MediaTypeRecord.repaint(); // Ensure it's redrawn
	}


	public void createMediaRecordTablePanel() {		
		setContentPane(MediaTypeRecord);
        revalidate();
        repaint();
	}
	
	
	public void MediaRecordTablePanel() {
	     // NORTH PANEL
	        JPanel panelNorth = new JPanel();
	        panelNorth.setLayout(new FlowLayout());
	        panelNorth.setBackground(Color.decode("#0A285f"));

	        JLabel label = new JLabel("MEDIA RECORD MANAGEMENT");
	        label.setForeground(Color.WHITE);
	        label.setFont(new Font("Gaegu", Font.BOLD, 18));
	        panelNorth.add(label);
	        
	        MediaTypeRecord.add(panelNorth, BorderLayout.NORTH);
	    	
    		//SOUTH PANEL
    		JPanel panelSouth = new JPanel();
    		panelSouth.setLayout(new FlowLayout());
    		panelSouth.setBackground(Color.decode("#fdfdfd"));
    		
    		btnReturnFromMediaManagement = new JButton("Return");
       		panelSouth.add(btnReturnFromMediaManagement);
    		btnReturnFromMediaManagement.setActionCommand("RecordManagement");
    		
    		MediaTypeRecord.add(panelSouth, BorderLayout.SOUTH);
		}
	
	
	public Object[][] getMedia() {


	    ArrayList<Object[]> list = new ArrayList<>();

	    try {
	        // Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

	        // Establish connection
	        try (
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery("""
		
				 SELECT mt.product_id, m.movie_name, mt.movie_code, mt.release_date, mt.media_type, mt.copies_available, mt.rental_price
				 FROM media_type mt JOIN movies m ON m.movie_code = mt.movie_code"""
						
					
				 )) {

	            // Process the ResultSet
	            while (resultSet.next()) {
	                Object[] row = new Object[7];
	                row[0] = resultSet.getInt(1); // Assuming column 1 is int
					row[1] = resultSet.getString(2);
	                row[2] = resultSet.getInt(3); // Assuming column 2 is String
	                row[3] = resultSet.getString(4); // Assuming column 4 is String
	                row[4] = resultSet.getString(5); // Assuming column 5 is String
	                row[5] = resultSet.getInt(6); // Assuming column 6 is String
	                row[6] = resultSet.getFloat(7); // Assuming column 7 is float          
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
  		
  		JLabel releasedate = new JLabel("Release Date");
  		releasedate.setForeground(Color.BLACK);
  		releasedate.setFont(new Font("Verdana", Font.BOLD, 19));
  		gbc.gridx = 1;
          gbc.gridy = 3;
          centerPanel.add(releasedate , gbc);
          MTrelease = new JTextField(15);
  		gbc.gridx = 2;
          gbc.gridy = 3;
          centerPanel.add(MTrelease, gbc);
          
          JLabel mediaType = new JLabel("Media Type");
          mediaType.setForeground(Color.BLACK);
          mediaType.setFont(new Font("Verdana", Font.BOLD, 19));
  		gbc.gridx = 1;
          gbc.gridy = 4;
          centerPanel.add(mediaType , gbc);
          String[] mediachoice = {"", "VHS", "CD", "DVD", "Blu-Ray","Online"};
          MTmedia_type = new JComboBox(mediachoice);
         //MTmedia_type = new JTextField(15);
  		gbc.gridx = 2;
          gbc.gridy = 4;
          centerPanel.add(MTmedia_type, gbc);
          
          JLabel copies = new JLabel("Copies Available");
          copies.setForeground(Color.BLACK);
          copies.setFont(new Font("Verdana", Font.BOLD, 19));
  		gbc.gridx = 1;
          gbc.gridy = 5;
          centerPanel.add(copies , gbc);
          MTcopies = new JTextField(15);
  		gbc.gridx = 2;
          gbc.gridy = 5;
          centerPanel.add(MTcopies, gbc);
          
          JLabel rentprice = new JLabel("Rental Price");
          rentprice.setForeground(Color.BLACK);
          rentprice.setFont(new Font("Verdana", Font.BOLD, 19));
  		gbc.gridx = 1;
          gbc.gridy = 6;
          centerPanel.add(rentprice , gbc);
          MTrentprice = new JTextField(15);
  		gbc.gridx = 2;
          gbc.gridy = 6;
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
	    String[] col = {"product_id","movie_code", "release_date","media_type","copies_available","rental_price"};
	    tableModelMedia = new DefaultTableModel(getMediaTypesforcrud(), col) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable editing for all cells
            }
        };

	    refreshAdminTable();
	    tableMediaTable = new JTable(tableModelMedia);
	    tableMediaTable.setEnabled(true); // Enable selection
	    
	    // Add a mouse click listener to the table
	    tableMediaTable.addMouseListener(new java.awt.event.MouseAdapter() {
	       
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	            int row = tableMediaTable.getSelectedRow(); // Get selected row index
	           
	            
	            if (row != -1) { // Ensure a valid cell is selected
	                // Safely retrieve values from the table
	                Object product_id = tableMediaTable.getValueAt(row, 0);
	                Object movie_code = tableMediaTable.getValueAt(row, 1);
	                Object release_date = tableMediaTable.getValueAt(row, 2);
	                Object media_type = tableMediaTable.getValueAt(row, 3);
	                Object copies_available = tableMediaTable.getValueAt(row, 4);
	                Object rental_price = tableMediaTable.getValueAt(row, 5);

	                // Set values with null checks
	                MTproduct_id.setText(product_id != null ? product_id.toString() : "");
	                MTmovie_code.setText(movie_code != null ? movie_code.toString() : "");
	                
	                // Handle release_date safely
	                if (release_date != null && release_date.toString().length() >= 4) {
	                    MTrelease.setText(release_date.toString().substring(0, 4)); // Extract year
	                } else {
	                    MTrelease.setText(""); // Default to empty string
	                }
	                
	                MTmedia_type.setSelectedItem(media_type != null ? media_type.toString() : ""); // For JComboBox
	                MTcopies.setText(copies_available != null ? copies_available.toString() : "");
	                MTrentprice.setText(rental_price != null ? rental_price.toString() : "");
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
	}	//getting data from db
	
	//getting data from db
	
	public Object[][] getMediaTypesforcrud() {
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
            	 Object[] row = new Object[6];
                 row[0] = resultSet.getInt(1); // Assuming column 1 is int
                 row[1] = resultSet.getInt(2); // Assuming column 2 is String
                 row[2] = resultSet.getString(3); // Assuming column 4 is String
                 row[3] = resultSet.getString(4); // Assuming column 5 is String
                 row[4] = resultSet.getInt(5); // Assuming column 6 is String
                 row[5] = resultSet.getFloat(6); // Assuming column 7 is float          
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
            
	public void refreshMediaTable() {
	tableModelMedia.setDataVector(getMediaTypesforcrud(), new String[]{"product_id", "movie_code", "release_date", "media_type","copies_available","rental_price"});
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
					    // Safely retrieve values from the table
					    Object movie_code = tableMoviesTable.getValueAt(row, 0);
					    Object movie_name = tableMoviesTable.getValueAt(row, 1);
					    Object year = tableMoviesTable.getValueAt(row, 2);
					    Object rating = tableMoviesTable.getValueAt(row, 3);
					    Object language = tableMoviesTable.getValueAt(row, 4);
					    Object genre_id = tableMoviesTable.getValueAt(row, 5);

					    // Set values with null checks
					    Mmovie_code.setText(movie_code != null ? movie_code.toString() : "");
					    Mmovie_name.setText(movie_name != null ? movie_name.toString() : "");
					    Myear.setText(year != null ? year.toString() : "");
					    Mrating.setSelectedItem(rating != null ? rating.toString() : ""); // Assuming it's a JComboBox
					    Mlanguage.setText(language != null ? language.toString() : "");
					    Mgenre_id.setText(genre_id != null ? genre_id.toString() : "");
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
				    // Safely retrieve values from the table
				    Object review_no = tableReviewTable.getValueAt(row, 0);
				    Object stars = tableReviewTable.getValueAt(row, 1);
				    Object review = tableReviewTable.getValueAt(row, 2);
				    Object movie_code = tableReviewTable.getValueAt(row, 3);
				    Object user_no = tableReviewTable.getValueAt(row, 4);

				    // Set values in text fields and combo boxes with null checks
				    Rreview_no.setText(review_no != null ? review_no.toString() : "");
				    Rstars.setSelectedItem(stars != null ? stars.toString() : ""); // Assuming Rstars is a JComboBox
				    Rreview.setText(review != null ? review.toString() : "");
				    Rmovie_code.setText(movie_code != null ? movie_code.toString() : "");
				    Ruser_no.setText(user_no != null ? user_no.toString() : "");
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
		   
		   JLabel product_id = new JLabel("Product ID");
		   product_id.setForeground(Color.BLACK);
		   product_id.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 3;
		   centerPanel.add(product_id, gbc);
		   Tproduct_id = new JTextField(20);
		   gbc.gridx = 2;
		   gbc.gridy = 3;
		   centerPanel.add(Tproduct_id,gbc);
		   
		   JLabel user_no = new JLabel("User No.");
		   user_no.setForeground(Color.BLACK);
		   user_no.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 4;
		   centerPanel.add(user_no,gbc);
		   Tuser_no = new JTextField(20);
		   gbc.gridx = 2;
		   gbc.gridy = 4;
		   centerPanel.add(Tuser_no, gbc);
		   
		   JLabel date_borrowed = new JLabel("Date Borrowed");
		   date_borrowed.setForeground(Color.BLACK);
		   date_borrowed.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 5;
		   centerPanel.add(date_borrowed , gbc);
		   Tdate_borrowed = new JTextField(20);
		   gbc.gridx = 2;
		   gbc.gridy = 5;
		   centerPanel.add(Tdate_borrowed, gbc);
		   
		   JLabel date_toreturn = new JLabel("Date to Return");
		   date_toreturn.setForeground(Color.BLACK);
		   date_toreturn.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 6;
		   centerPanel.add(date_toreturn , gbc);
		   Tdate_toreturn = new JTextField(20);
		   gbc.gridx = 2;
		   gbc.gridy = 6;
		   centerPanel.add(Tdate_toreturn, gbc);
		   
	   
		   JLabel date_returned = new JLabel("Date Returned");
		   date_returned.setForeground(Color.BLACK);
		   date_returned.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 7;
		   centerPanel.add(date_returned , gbc);
		   Tdate_returned = new JTextField(20);
		   gbc.gridx = 2;
		   gbc.gridy = 7;
		   centerPanel.add(Tdate_returned, gbc);
		   
		   JLabel payment = new JLabel("Payment");
		   payment.setForeground(Color.BLACK);
		   payment.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 8;
		   centerPanel.add(payment , gbc);
		   Tpayment =  new JTextField(20);
		   gbc.gridx = 2;
		   gbc.gridy = 8;
		   centerPanel.add(Tpayment, gbc);
		   
		   JLabel admin_no = new JLabel("Admin Borrow");
		   admin_no.setForeground(Color.BLACK);
		   admin_no.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 9;
		   centerPanel.add(admin_no , gbc);
		   Tadmin_bo =  new JTextField(20);
		   gbc.gridx = 2;
		   gbc.gridy = 9;
		   centerPanel.add(Tadmin_bo, gbc);
		   
		   JLabel admin_re = new JLabel("Admin Return");
		   admin_re.setForeground(Color.BLACK);
		   admin_re.setFont(new Font("Verdana", Font.BOLD, 19));
		   gbc.gridx = 1;
		   gbc.gridy = 10;
		   centerPanel.add(admin_re , gbc);
		   Tadmin_re =  new JTextField(20);
		   gbc.gridx = 2;
		   gbc.gridy = 10;
		   centerPanel.add(Tadmin_re, gbc);
		   
		   
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
   String[] col = {"transaction_no", "movie_code","product_id", "user_no", "date_borrowed", "date_toreturn","date_returned","payment", "admin_no","admin_return"};
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
        // Safely retrieve and assign values from the table
        Object transaction_no = tableTransactionTable.getValueAt(row, 0);
        Object movie_code = tableTransactionTable.getValueAt(row, 1);
        Object product_id = tableTransactionTable.getValueAt(row, 2);
        Object user_no = tableTransactionTable.getValueAt(row, 3);
        Object date_borrowed = tableTransactionTable.getValueAt(row, 4);
        Object date_toreturn = tableTransactionTable.getValueAt(row, 5);
        Object date_returned = tableTransactionTable.getValueAt(row, 6);
        Object payment = tableTransactionTable.getValueAt(row, 7);
        Object admin_bo = tableTransactionTable.getValueAt(row, 8);
        Object admin_re = tableTransactionTable.getValueAt(row, 9);

        // Assign values to text fields with null checks
        Ttransaction_no.setText(transaction_no != null ? transaction_no.toString() : "");
        Tmovie_code.setText(movie_code != null ? movie_code.toString() : "");
        Tproduct_id.setText(product_id != null ? product_id.toString() : "");
        Tuser_no.setText(user_no != null ? user_no.toString() : "");
        Tdate_borrowed.setText(date_borrowed != null ? date_borrowed.toString() : "");
        Tdate_toreturn.setText(date_toreturn != null ? date_toreturn.toString() : "");
        Tdate_returned.setText(date_returned != null ? date_returned.toString() : "");
        Tpayment.setText(payment != null ? payment.toString() : "");
        Tadmin_bo.setText(admin_bo != null ? admin_bo.toString() : "");
        Tadmin_re.setText(admin_re != null ? admin_re.toString() : "");
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
		   Object[] row = new Object[10];
		   row[0] = resultSet.getInt(1); 
		   row[1] = resultSet.getInt(2);            
		   row[2] = resultSet.getInt(3);
		   row[3] = resultSet.getInt(4);
		   row[4] = resultSet.getString(5);
		   row[5] = resultSet.getString(6);
		   row[6] = resultSet.getString(7); 
		   row[7] = resultSet.getString(8);
		   row[8] = resultSet.getInt(9);
		   row[9] = resultSet.getInt(10); 
		   if (resultSet.wasNull()) {
			    row[9] = ""; // Assign default value for null
			}
		   list.add(row);
	   }
   }

   // Convert the list to a 2D array
   return list.toArray(new Object[0][10]);

} catch (Exception e) {
   e.printStackTrace(); // Print stack trace for debugging
   return null;
}
}

//refreshing transaction table
	public void refreshTransactionTable() {
   tableModelTransaction.setDataVector(getTransaction(), new String[]{"transaction_no", "movie_code","product_id","user_no", "date_borrowed", "date_toreturn","date_returned", "payment", "admin_borrow","admin_return"});
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
		   
		   JLabel bday = new JLabel("Member Since");
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
	   String[] col = {"user_no", "first_name","last_name", "email", "member since","password"};
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
				    // Safely retrieve values from the table
				    Object user_no = tableUserTable.getValueAt(row, 0);
				    Object first_name = tableUserTable.getValueAt(row, 1);
				    Object last_name = tableUserTable.getValueAt(row, 2);
				    Object email = tableUserTable.getValueAt(row, 3);
				    Object birthday = tableUserTable.getValueAt(row, 4);
				    Object password = tableUserTable.getValueAt(row, 5);

				    // Set values with null checks
				    Uuser_no.setText(user_no != null ? user_no.toString() : "");
				    Ufirst_name.setText(first_name != null ? first_name.toString() : "");
				    Ulast_name.setText(last_name != null ? last_name.toString() : "");
				    Uemail.setText(email != null ? email.toString() : "");
				    Ubirthday.setText(birthday != null ? birthday.toString() : "");
				    Upassword.setText(password != null ? password.toString() : "");
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
	   tableModelUser.setDataVector(getUser(), new String[]{"user_no", "first_name","last_name", "email", "member since","password"});
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
	   
	   JLabel datefilled = new JLabel("Date Filed");
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
	   
	   JLabel approve = new JLabel("Approved");
	   approve.setForeground(Color.BLACK);
	   approve.setFont(new Font("Verdana", Font.BOLD, 19));
	   gbc.gridx = 1;
	   gbc.gridy = 5;
	   centerPanel.add(approve , gbc);
	   String[] isApproved = {"", "YES", "NO"};
	   MRapproved = new JComboBox(isApproved);
	   gbc.gridx = 2;
	   gbc.gridy = 5;
	   centerPanel.add(MRapproved, gbc);
	   
	   JLabel mediaType = new JLabel("Media Type");
	   mediaType.setForeground(Color.BLACK);
	   mediaType.setFont(new Font("Verdana", Font.BOLD, 19));
	   gbc.gridx = 1;
	   gbc.gridy = 6;
	   centerPanel.add(mediaType , gbc);
	   String[] mediachoice = {"", "VHS", "CD", "DVD", "Blu-Ray","Online"};
	   MRmedia_type = new JComboBox(mediachoice);
	   gbc.gridx = 2;
	   gbc.gridy = 6;
	   centerPanel.add(MRmedia_type, gbc);
	   
	   
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
   String[] col = {"request_number", "movie_name","date_filled", "user_no", "approved","media_type"};
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
			    // Safely retrieve values from the table
			    Object request_number = tableMovieReqTable.getValueAt(row, 0);
			    Object movie_name = tableMovieReqTable.getValueAt(row, 1);
			    Object date_filled = tableMovieReqTable.getValueAt(row, 2);
			    Object user_no = tableMovieReqTable.getValueAt(row, 3);
			    Object approved = tableMovieReqTable.getValueAt(row, 4);
			    Object media_type = tableMovieReqTable.getValueAt(row, 5);

			    // Set values with null checks
			    MRrequest_no.setText(request_number != null ? request_number.toString() : "");
			    MRmovie_name.setText(movie_name != null ? movie_name.toString() : "");
			    MRdate_filled.setText(date_filled != null ? date_filled.toString() : "");
			    MRuser_no.setText(user_no != null ? user_no.toString() : "");
			    MRapproved.setSelectedItem(approved != null ? approved.toString() : ""); // Assuming MRapproved is a JComboBox
			    MRmedia_type.setSelectedItem(media_type != null ? media_type.toString() : ""); // Assuming MRmedia_type is a JComboBox
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
		   Object[] row = new Object[6];
		   row[0] = resultSet.getInt(1); 
		   row[1] = resultSet.getString(2);            
		   row[2] = resultSet.getString(3);
		   row[3] = resultSet.getInt(4); 
		   String transmute;
		   
		   if(resultSet.getObject(5) == null) {
			   transmute = "";
		   }else if(resultSet.getInt(5) == 0 ){
			   transmute = "NO";
		   }else transmute = "YES";
		   row[4] = transmute;
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

//refreshing admin table
	public void refreshMovieReqTable() {
   tableModelMovieReq.setDataVector(getMovieReq(), new String[]{"request_number", "movie_name","date_filled", "user_no", "approved","media_type"});
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
						    // Safely retrieve values from the table
						    Object movie_code = tableMovieRecord.getValueAt(row, 0);
						    Object movie_name = tableMovieRecord.getValueAt(row, 1);

						    // Set values with null checks
						    MRMmovie_code.setText(movie_code != null ? movie_code.toString() : "");
						    MRMmovie_name.setText(movie_name != null ? movie_name.toString() : "");
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
		   UPuserno.setFont(new Font("Verdana", Font.BOLD, 17));
		   gbc.gridx = 1;
		   gbc.gridy = 1;
		   centerPanel.add(UPuserno, gbc);
		   
		   UPfirstname = new JLabel("First Name");
		   UPfirstname.setForeground(Color.BLACK);
		   UPfirstname.setFont(new Font("Verdana", Font.BOLD, 17));
		   gbc.gridx = 1;
		   gbc.gridy = 2;
		   centerPanel.add(UPfirstname, gbc);
		   
		   
		   UPlastName = new JLabel("Last Name");
		   UPlastName.setForeground(Color.BLACK);
		   UPlastName.setFont(new Font("Verdana", Font.BOLD, 17));
		   gbc.gridx = 1;
		   gbc.gridy = 3;
		   centerPanel.add(UPlastName,gbc);
		   
		   UPmembership = new JLabel("Member Since");
		   UPmembership.setForeground(Color.BLACK);
		   UPmembership.setFont(new Font("Verdana", Font.BOLD, 17));
		   gbc.gridx = 1;
		   gbc.gridy = 4;
		   centerPanel.add(UPmembership,gbc);
		   
		   UPpass = new JLabel("Password");
		   UPpass.setForeground(Color.BLACK);
		   UPpass.setFont(new Font("Verdana", Font.BOLD, 17));
		   gbc.gridx = 1;
		   gbc.gridy = 5;
		   centerPanel.add(UPpass,gbc);

		   UserProfile.add(centerPanel , BorderLayout.CENTER);
	}
	public void showUserProfile() {
		   String[] col = {"movie_name", "date_borrowed","date_toreturn", "date_returned"};
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
	                "SELECT m.movie_name, t.date_borrowed,t.date_toreturn, t.date_returned " +
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
	                    Object[] row = new Object[4];
	                    row[0] = resultSet.getString(1); // movie_name (String)
	                    row[1] = resultSet.getDate(2);  // date_borrowed (Date)
	                    row[2] = resultSet.getDate(3);  // date_to return (Date)
	                    row[3] = resultSet.getDate(4);  // date_returned (Date)
	                    list.add(row);
	                }
	            }
	        }

	        // Convert the list to a 2D array
	        return list.toArray(new Object[0][4]);

	    } catch (Exception e) {
	        e.printStackTrace(); // Print stack trace for debugging
	        return null;
	    }
	}

	
	//refreshing admin table
	public void refreshUserProfileTable() {
		tableModelUserProfile.setDataVector(getUserProfileTable(), new String[]{"movie_name", "date_borrowed","date_toreturn", "date_returned"});
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
	
	private String genreSelector() {
    List<String> genresList = new ArrayList<>();
    String query = "SELECT description FROM genre_type";
    
    try (PreparedStatement pstmt = connection.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
            genresList.add(rs.getString(1)); // Add genre description to the list
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error retrieving genres: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
        return null;
    }

    if (genresList.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No genres available. Please add genres first.", "Info", JOptionPane.INFORMATION_MESSAGE);
        return null;
    }

    String[] genresArray = genresList.toArray(new String[0]);
    String selectedGenre = (String) JOptionPane.showInputDialog(
            null,
            "Choose a Genre",
            "Genre Selector",
            JOptionPane.PLAIN_MESSAGE,
            null,
            genresArray,
            genresArray[0]);

    if (selectedGenre == null) {
        JOptionPane.showMessageDialog(null, "No genre selected.", "Warning", JOptionPane.WARNING_MESSAGE);
    }
    
    return selectedGenre;
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
	                    WHERE YEAR(t.date_borrowed) = ? AND MONTH(t.date_borrowed) = ? AND gt.description LIKE ?
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

	            String genreId = genreSelector();
	            if (genreId == null) {
	                return; // User cancelled
	            }
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
								
					SELECT YEAR(t.date_returned) AS `YEAR`,u.first_name,u.last_name,SUM(t.payment) AS `Total Payments`,
					SUM(TIMESTAMPDIFF(DAY, t.date_borrowed, t.date_toreturn) * mt.rental_price) AS `Rental Fees`,
					SUM(t.payment - (TIMESTAMPDIFF(DAY, t.date_borrowed, 
					t.date_toreturn) * mt.rental_price)) AS `Late and Additional Fees`
					FROM users u
					JOIN transactions t ON u.user_no = t.user_no
					JOIN media_type mt ON t.product_id = mt.product_id
					WHERE t.date_returned IS NOT NULL AND YEAR(t.date_returned) = ?
					GROUP BY u.user_no,`YEAR`
					ORDER BY `Total Payments` DESC;
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
	                
	                String monthname = "";
	                switch(month){
	                
	  
	                case 1: monthname = "January";
	                	break;
	                case 2:
	                	 monthname = "Febuary";
	 	                break;
	                case 3:
	                	 monthname = "March";
	 	                break;
	                case 4:
	                	 monthname = "April";
	 	                break;
	                case 5:
	                	 monthname = "May";
	 	                break;
	                case 6:
	                	 monthname = "June";
	 	                break;
	                case 7:
	                	 monthname = "July";
	 	                break;
	                case 8:
	                	 monthname = "August";
	 	                break;
	                case 9:
	                	 monthname = "September";
	 	                break;
	                case 10:
	                	 monthname = "October";
	 	                break;
	                case 11:
	                	 monthname = "November";
	 	                break;
	                case 12:
	                	 monthname = "December";
	 	                break;
	                
	                }
	                
	                rows.add(new Object[]{year, monthname, totalRev});
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

	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error retrieving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        ex.printStackTrace();
	    }
	}
	//END OF REPORTS

	
	public void transactionmanagement() {
	    // NORTH PANEL
	    JPanel panelNorth = new JPanel();
	    panelNorth.setLayout(new FlowLayout());
	    panelNorth.setBackground(Color.decode("#0A285f"));

	    JLabel label = new JLabel("Welcome To Transaction Management");
	    label.setForeground(Color.WHITE);
	    label.setFont(new Font("Gaegu", Font.BOLD, 18));
	    panelNorth.add(label);

	    Transactions.add(panelNorth, BorderLayout.NORTH);

	    // SOUTH PANEL
	    JPanel panelSouth = new JPanel();
	    panelSouth.setBackground(Color.BLACK);
	    Transactions.add(panelSouth, BorderLayout.SOUTH);

	    // CENTER PANEL
	    JPanel panelCenter = new JPanel();
	    panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
	    panelCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	    // Button size
	    Dimension buttonSize = new Dimension(350, 100);

	    // Buttons for Transactions
	    btnBorrowMovie = new JButton("Borrow Movie");
	    btnBorrowMovie.setPreferredSize(buttonSize);
	    btnBorrowMovie.setMaximumSize(buttonSize);
	    btnBorrowMovie.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
	    panelCenter.add(btnBorrowMovie);

	    btnReturnMovie = new JButton("Return Movie");
	    btnReturnMovie.setPreferredSize(buttonSize);
	    btnReturnMovie.setMaximumSize(buttonSize);
	    btnReturnMovie.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
	    panelCenter.add(btnReturnMovie);

	    btnReviewMovieRequests = new JButton("File Movie Request");
	    btnReviewMovieRequests.setPreferredSize(buttonSize);
	    btnReviewMovieRequests.setMaximumSize(buttonSize);
	    btnReviewMovieRequests.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
	    panelCenter.add(btnReviewMovieRequests);

	    btnFormalizeMovieRequests = new JButton("Formalize Movie Requests");
	    btnFormalizeMovieRequests.setPreferredSize(buttonSize);
	    btnFormalizeMovieRequests.setMaximumSize(buttonSize);
	    btnFormalizeMovieRequests.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
	    panelCenter.add(btnFormalizeMovieRequests);

	    btnReturntoMainFromTransaction = new JButton("Home");
	    btnReturntoMainFromTransaction.setPreferredSize(buttonSize);
	    btnReturntoMainFromTransaction.setMaximumSize(buttonSize);
	    btnReturntoMainFromTransaction.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
	    panelCenter.add(btnReturntoMainFromTransaction);
	    panelCenter.add(Box.createVerticalGlue());


	    // Set Action Commands
	    btnBorrowMovie.setActionCommand("BorrowMovie");
	    btnReturnMovie.setActionCommand("ReturnMovie");
	    btnReviewMovieRequests.setActionCommand("ReviewMovieRequests");
	    btnFormalizeMovieRequests.setActionCommand("FormalizeMovieRequests");
	    btnReturntoMainFromTransaction.setActionCommand("Home");
		btnReviewMovieRequests.setActionCommand("File Movie Request");
		
	    Transactions.add(panelCenter, BorderLayout.CENTER);
	}
	
	public void createTransactionsPanel() {
		setContentPane(Transactions);
        revalidate();
        repaint();
		
	}

	private void borrowMovieGUI() {
		String value,copiesavail;
	    // Step 1: Get User ID from the user
	    String userIdInput = JOptionPane.showInputDialog(null, "Enter User ID to borrow movie:", "Borrow Movie", JOptionPane.PLAIN_MESSAGE);
	    if (userIdInput == null || userIdInput.trim().isEmpty()) {
	        return; // If user cancels or doesn't enter a user ID
	    }

	    // Convert the user input to the correct type
	    int userId;
	    try {
	        userId = Integer.parseInt(userIdInput);
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Invalid User ID. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    // Step 2: Check if the user is currently borrowing a movie (except for User ID 1)
	    String checkUserQuery = """
	        SELECT 
	            u.first_name,
	            u.last_name,
	            CASE 
	                WHEN COUNT(t.transaction_no) > 0 THEN 'Currently Borrowing'
	                ELSE 'Not Currently Borrowing'
	            END AS `Status`
	        FROM 
	            users u
	        LEFT JOIN 
	            transactions t 
	            ON u.user_no = t.user_no 
	            AND t.date_returned IS NULL  -- Only consider transactions that are still ongoing (not returned)
	        WHERE 
	            u.user_no = ?  -- Placeholder for user_id parameter
	        GROUP BY 
	            u.user_no, u.first_name, u.last_name;
	    """;

	    try (PreparedStatement pstmt = connection.prepareStatement(checkUserQuery)) {
	        pstmt.setInt(1, userId);
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            // If the user is not ID 1 and is already borrowing, show a warning
	            if (rs.getString("Status").equals("Currently Borrowing")) {
	                JOptionPane.showMessageDialog(null, "You are already borrowing a movie. Please return the current movie first.", "Error", JOptionPane.ERROR_MESSAGE);
	                return; // Prevent borrowing if already borrowing
	            }
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error checking user borrowing status: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Step 3: Ask the user for the movie name they want to borrow
	    String movieNameInput = JOptionPane.showInputDialog(null, "Enter Movie Name to borrow:", "Borrow Movie", JOptionPane.PLAIN_MESSAGE);
	    if (movieNameInput == null || movieNameInput.trim().isEmpty()) {
	        return; // If user cancels or doesn't enter a movie name
	    }

	    // Step 4: Check the movie's availability and other details using the updated SQL query with movie name
	    String checkMovieQuery = """
	        SELECT m.movie_code, m.movie_name, m.year, m.rating, gt.description AS `Genre`, mt.media_type, mt.rental_price, 
	            mt.copies_available,
	            CASE 
	                WHEN mt.copies_available > 0 THEN 'Available' ELSE 'Unavailable' END AS 'Movie Availability'
	        FROM movies m
	        JOIN media_type mt ON mt.movie_code = m.movie_code
	        JOIN genre_type gt ON gt.genre_id = m.genre_id
	        WHERE m.movie_name LIKE ?;  -- Search for movie name
	    """;

	    List<String> availableMediaTypesWithPrice = new ArrayList<>();
	    List<String> unavailableMediaTypes = new ArrayList<>();
	    String movieCode = null; // We'll capture the movie code for later use if needed
	    try (PreparedStatement pstmt = connection.prepareStatement(checkMovieQuery)) {
	        pstmt.setString(1, "%" + movieNameInput.trim() + "%");  // Using LIKE for partial match
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            // Display the movie details
	            movieCode = rs.getString("movie_code");
	            String movieDetails = String.format("Movie: %s\nYear: %d\nRating: %s\nGenre: %s\nAvailability: %s",
	                    rs.getString("movie_name"), rs.getInt("year"), rs.getString("rating"),
	                    rs.getString("Genre"), rs.getString("Movie Availability"));
	            JOptionPane.showMessageDialog(null, movieDetails, "Movie Details", JOptionPane.INFORMATION_MESSAGE);

	            // Step 5: Collect available and unavailable media types
	            do {
	                String mediaType = rs.getString("media_type");
	                double price = rs.getDouble("mt.rental_price");
	                String availability = rs.getString("Movie Availability");

	                if ("Available".equals(availability)) {
	                    availableMediaTypesWithPrice.add(mediaType + " - ₱" + price);  // Combine media type and price
	                } else {
	                    unavailableMediaTypes.add(mediaType + " - ₱" + price); // Store unavailable media types separately
	                }
	            } while (rs.next());

	            // If no available media types, show an error message
	            if (availableMediaTypesWithPrice.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "All media types for this movie are currently unavailable.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Movie not found.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error checking movie details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Step 6: Ask the user to select an available media type from the list
	    String[] availableMediaTypeArray = availableMediaTypesWithPrice.toArray(new String[0]);
	    String selectedMediaType = (String) JOptionPane.showInputDialog(null, "Select the media type and price to borrow:",
	            "Select Media Type", JOptionPane.QUESTION_MESSAGE, null, availableMediaTypeArray, availableMediaTypeArray[0]);

	    if (selectedMediaType == null) {
	        return; // If the user cancels the selection
	    }

	    // Step 7: Confirm borrowing the movie with the selected media type and price
	    String confirmBorrow = JOptionPane.showInputDialog(null, "Do you want to borrow this movie? (yes/no)", "Confirm Borrow", JOptionPane.PLAIN_MESSAGE);
	    if (confirmBorrow == null || !confirmBorrow.equalsIgnoreCase("yes")) {
	        return; // If the user cancels or doesn't want to borrow
	    }

	    

	    String checkProductID = """
	    	    SELECT mt.product_id, mt.copies_available
	    	    FROM media_type mt
	    	    WHERE mt.movie_code = ? AND mt.media_type LIKE ?;
	    	""";

	    	// Initialize the variable

	    	try (PreparedStatement pstmt = connection.prepareStatement(checkProductID)) {
	    	    pstmt.setString(1, movieCode);
	    	    String result = selectedMediaType.split(" - ")[0];
	    	    pstmt.setString(2, result);

	    	    try (ResultSet rs = pstmt.executeQuery()) {
	    	        if (rs.next()) { // Check if a row exists
	    	            value = rs.getString("product_id");
	    	            copiesavail = rs.getString("copies_available");
	    	        } else {
	    	            JOptionPane.showMessageDialog(null, "Media type not found for the selected movie.", "Error", JOptionPane.ERROR_MESSAGE);
	    	            return; // Exit if no product_id is found
	    	        }
	    	    }

	    	    String[] numOfDaysArray = new String[31];
	    	    for (int i = 0; i < 31; i++) {
	    	        numOfDaysArray[i] = String.valueOf(i + 1);
	    	    }
	    	    
	    	    String numofdaystoborrow = (String) JOptionPane.showInputDialog(null, "For how many days do you want to borrow this movie?",
	    	            "Days of Rental", JOptionPane.QUESTION_MESSAGE, null, numOfDaysArray, numOfDaysArray[0]);

	    	    if (numofdaystoborrow == null) {
	    	        return; // If the user cancels the selection
	    	    }

	  

	    	    // Record the borrowing transaction
	    	    String insertTransactionQuery = """
	    	        INSERT INTO transactions (user_no, movie_code, product_id, date_toreturn, date_borrowed, admin_bo)
	    	        VALUES (?, ?, ?, DATE_ADD(CURDATE(), INTERVAL ? DAY), CURDATE(), ?);
	    	    """;

	    	    try{
					PreparedStatement pstmt1 = connection.prepareStatement(insertTransactionQuery);
	    	        pstmt1.setInt(1, userId);
	    	        pstmt1.setInt(2, Integer.parseInt(movieCode));
	    	        pstmt1.setInt(3, Integer.parseInt(value));
	    	        pstmt1.setInt(4, Integer.parseInt(numofdaystoborrow));
					pstmt1.setInt(5, Integer.parseInt(loggedInAdmin));
					
	    	        pstmt1.execute();
//	    	        System.out.println("Executing query: " + pstmt1.toString());
//	    	        System.out.println("Number of days to borrow: " + numofdaystoborrow);
	    	                
	    	        String updatecopiesavailable = "UPDATE media_type SET copies_available = CASE WHEN ? > 0 THEN ? - 1 ELSE 0 END WHERE product_id = ?";
	    	        		// + "	    	            availability = CASE \n"
	    	        		// + "	    	                WHEN ? - 1 <= 0 THEN 0 \n"
	    	        		// + "	    	                ELSE 1 \n"
	    	        		// + "	    	            END\n"
	    	    	try {
	    				PreparedStatement pstmt3 = connection.prepareStatement(updatecopiesavailable);
	    				pstmt3.setInt(1, Integer.parseInt(copiesavail));
	    				pstmt3.setInt(2, Integer.parseInt(copiesavail));
	    				//pstmt3.setInt(3, Integer.parseInt(copiesavail));
	    				pstmt3.setInt(3, Integer.parseInt(value));
	    				pstmt3.executeUpdate();
//	    				System.out.println(copiesavail);
//	    				System.out.println("Executing query: " + pstmt3.toString());
//	    				
	    			} catch (SQLException e) {
	    				
	    				e.printStackTrace();
	    			}
	    	    	
	    	       
	    	        JOptionPane.showMessageDialog(null, "Transaction recorded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
	    	    } catch (Exception e) {
	    	        
	    	        JOptionPane.showMessageDialog(null, "Error recording the transaction: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
	    	    } 
	    	} catch (Exception e) {
	    	    JOptionPane.showMessageDialog(null, "Error checking movie details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    	    e.printStackTrace();
	    	}

	    // Step 9: Show success message (without database update for now)
	    JOptionPane.showMessageDialog(null, "Movie borrowed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
	}

	public void storeUser(String admin_no){
		loggedInAdmin = admin_no;
	}

	private void returnMovieGUI(){
	
		String value,copiesavail;
	    // Step 1: Get User ID from the user
	    String userIdInput = JOptionPane.showInputDialog(null, "Enter UserID of movie returnee", "Return Movie", JOptionPane.PLAIN_MESSAGE);
	    if (userIdInput == null || userIdInput.trim().isEmpty()) {
	        return; // If user cancels or doesn't enter a user ID
	    }

	    // Convert the user input to the correct type
	    int userId;
	    try {
	        userId = Integer.parseInt(userIdInput);
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Invalid User ID. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

		String checkUserQuery = """
	        SELECT 
	            u.first_name,
	            u.last_name,
	            CASE 
	                WHEN COUNT(t.transaction_no) > 0 THEN 'Currently Borrowing'
	                ELSE 'Not Currently Borrowing'
	            END AS `Status`
	        FROM 
	            users u
	        LEFT JOIN 
	            transactions t 
	            ON u.user_no = t.user_no 
	            AND t.date_returned IS NULL  -- Only consider transactions that are still ongoing (not returned)
	        WHERE 
	            u.user_no = ?  -- Placeholder for user_id parameter
	        GROUP BY 
	            u.user_no, u.first_name, u.last_name;
	    """;
		
	    try (PreparedStatement pstmt = connection.prepareStatement(checkUserQuery)) {
	        pstmt.setInt(1, userId);
	        ResultSet rs = pstmt.executeQuery();	
	        if (rs.next()) {
	            // If the user is not ID 1 and is already borrowing, show a warning
	            if (rs.getString("Status").equals("Not Currently Borrowing")) {
	                JOptionPane.showMessageDialog(null, "You are not borrowing a movie. Please borrow a movie first", "Error", JOptionPane.ERROR_MESSAGE);
	                return; // Prevent borrowing if already borrowing
	            }
	        }	    
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error checking user borrowing status: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String checkTransactions = """
	        SELECT 	u.first_name, 
					u.last_name, 
                    m.movie_name, 
                    mt.media_type, 
                    t.date_toreturn, 
                    current_date(),  
                    timestampdiff(day, t.date_borrowed, current_date()) * mt.rental_price AS "normal fees", 
					timestampdiff(day, t.date_toreturn, current_date()) * 1.2 *  mt.rental_price AS "late fees",
					t.transaction_no,
					t.product_id,
					mt.copies_available
	        FROM 
				transactions t
            JOIN 	users u 
					ON u.user_no = t.user_no
            JOIN 	movies m 
					ON m.movie_code = t.movie_code
            JOIN 	media_type mt 
					ON t.product_id = mt.product_id
	        WHERE 	t.user_no = ? 
					AND date_returned IS NULL;
	    """;

		try(PreparedStatement returnQuery = connection.prepareStatement(checkTransactions)){
			returnQuery.setInt(1, userId);
			ResultSet rs = returnQuery.executeQuery();
			if(rs.next()){
			Float lateFees = rs.getFloat("late fees");
			Float normalFees = rs.getFloat("normal fees");
			Integer transactionNo = rs.getInt("transaction_no");
			Integer productId = rs.getInt("product_id");
			Integer copiesAvailable = rs.getInt("copies_available");
			String displayResult = String.format(
			"""
			Customer name: %s %s\n
			Movie name: %s\n
			Media type to return: %s\n
			Date to return: %s\n
			Current Date: %s\n
			Customer has to pay for normal fees: %2f PHP\n
			""",
			rs.getString("first_name"),
			rs.getString("last_name"),
			rs.getString("movie_name"),
			rs.getString("media_type"),
			rs.getString("date_toreturn"),
			rs.getString("current_date()"),
			normalFees);
			JOptionPane.showMessageDialog(null, displayResult, "Return Details", JOptionPane.INFORMATION_MESSAGE);
			
			if(lateFees > 0){
				displayResult = String.format("""
					Customer is returning the rental LATE\n
					customer has to pay an additional: %2f PHP
						""", lateFees);
			JOptionPane.showMessageDialog(null, displayResult, "LATE RETURN", JOptionPane.INFORMATION_MESSAGE);
			normalFees += lateFees;
			}
			
			displayResult = String.format("""
					Customer pays: %2f
						""", normalFees);
			JOptionPane.showMessageDialog(null, displayResult, "HAS TO PAY", JOptionPane.INFORMATION_MESSAGE);

			String updateAvailable = "UPDATE media_type SET copies_available = CASE WHEN ? > 0 THEN ? + 1 ELSE 1 END WHERE product_id = ?";
			String updateTransaction = "UPDATE transactions SET date_returned = CURDATE(), payment = ?, admin_re = ? WHERE transaction_no = ?";

			try {
				PreparedStatement returnSt = connection.prepareStatement(updateAvailable);
				returnSt.setInt(1, copiesAvailable);
				returnSt.setInt(2, copiesAvailable);
				returnSt.setInt(3, productId);
				
				returnSt.executeUpdate();

				returnSt = connection.prepareStatement(updateTransaction);
				
				returnSt.setFloat(1, normalFees);
				returnSt.setInt(2, Integer.parseInt(loggedInAdmin) );
				returnSt.setInt(3, transactionNo);
				returnSt.executeUpdate();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Something went wrong!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			displayResult = String.format("""
					Transaction Completed!\n
					Product returned to inventory
						""");
			JOptionPane.showMessageDialog(null, displayResult, "Transaction Success", JOptionPane.INFORMATION_MESSAGE);

			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error checking user borrowing status: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}


	}

	private JScrollPane scrollerMovie_reqTransaction;
	private JTable tableMovie_reqTransaction;
	private DefaultTableModel tableModelMovie_reqTransaction;
	private JTextField MRTTrequest_no,MRTTmovie_name, MRTTdate_filled, MRTTuser_no;
	private JComboBox MRTTapproved,MRTTmedia_type;
	private JButton btnUpdateMovie_reqTransactionTable,btnreturntohomefromMRTT;
	

	public void createMovie_reqTransactionTablePanel() {		
		setContentPane(ADDINGMOVIEREQ);
        revalidate();
        repaint();
	}

	 public void Movie_reqTransactionTablePanel() {
			// NORTH PANEL
			   JPanel panelNorth = new JPanel();
			   panelNorth.setLayout(new FlowLayout());
			   panelNorth.setBackground(Color.decode("#0A285f"));

			   JLabel label = new JLabel("MOVIE REQUEST FOR APPROVAL");
			   label.setForeground(Color.WHITE);
			   label.setFont(new Font("Gaegu", Font.BOLD, 18));
			   panelNorth.add(label);
			   
			   ADDINGMOVIEREQ.add(panelNorth, BorderLayout.NORTH);


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
			   MRTTrequest_no = new JTextField(20);
			   gbc.gridx = 2;
			   gbc.gridy = 1;
			   centerPanel.add(MRTTrequest_no, gbc);
			   
			   JLabel movname = new JLabel("Movie Name");
			   movname.setForeground(Color.BLACK);
			   movname.setFont(new Font("Verdana", Font.BOLD, 19));
			   gbc.gridx = 1;
			   gbc.gridy = 2;
			   centerPanel.add(movname, gbc);
			   MRTTmovie_name = new JTextField(20);
			   gbc.gridx = 2;
			   gbc.gridy = 2;
			   centerPanel.add(MRTTmovie_name,gbc);
			   
			   JLabel datefilled = new JLabel("Date Filed");
			   datefilled.setForeground(Color.BLACK);
			   datefilled.setFont(new Font("Verdana", Font.BOLD, 19));
			   gbc.gridx = 1;
			   gbc.gridy = 3;
			   centerPanel.add(datefilled,gbc);
			   MRTTdate_filled = new JTextField(20);
			   gbc.gridx = 2;
			   gbc.gridy = 3;
			   centerPanel.add(MRTTdate_filled, gbc);
			   
			   JLabel userno = new JLabel("User no.");
			   userno.setForeground(Color.BLACK);
			   userno.setFont(new Font("Verdana", Font.BOLD, 19));
			   gbc.gridx = 1;
			   gbc.gridy = 4;
			   centerPanel.add(userno , gbc);
			   MRTTuser_no = new JTextField(15);
			   gbc.gridx = 2;
			   gbc.gridy = 4;
			   centerPanel.add(MRTTuser_no, gbc);
			   
			   JLabel approve = new JLabel("Approved");
			   approve.setForeground(Color.BLACK);
			   approve.setFont(new Font("Verdana", Font.BOLD, 19));
			   gbc.gridx = 1;
			   gbc.gridy = 5;
			   centerPanel.add(approve , gbc);
			   String[] isApproved = {"", "YES", "NO"};
			   MRTTapproved = new JComboBox(isApproved);
			   gbc.gridx = 2;
			   gbc.gridy = 5;
			   centerPanel.add(MRTTapproved, gbc);
			   
			   JLabel mediaType = new JLabel("Media Type");
			   mediaType.setForeground(Color.BLACK);
			   mediaType.setFont(new Font("Verdana", Font.BOLD, 19));
			   gbc.gridx = 1;
			   gbc.gridy = 6;
			   centerPanel.add(mediaType , gbc);
			   String[] mediachoice = {"", "VHS", "CD", "DVD", "Blu-Ray","Online"};
			   MRTTmedia_type = new JComboBox(mediachoice);
			   gbc.gridx = 2;
			   gbc.gridy = 6;
			   centerPanel.add(MRTTmedia_type, gbc);
			   
			   btnUpdateMovie_reqTransactionTable = new JButton("Update");
			   gbc.gridx = 1;
			   gbc.gridy = 7;
			   gbc.gridwidth = 2;
			   gbc.anchor = GridBagConstraints.CENTER;
			   centerPanel.add(btnUpdateMovie_reqTransactionTable, gbc);
			   
			   ADDINGMOVIEREQ.add(centerPanel , BorderLayout.EAST);

			   //SOUTH PANEL
			   JPanel panelSouth = new JPanel();
			   panelSouth.setLayout(new FlowLayout());
			   panelSouth.setBackground(Color.decode("#fdfdfd"));
			   
			   
			   btnreturntohomefromMRTT  = new JButton("Return");
			   panelSouth.add(btnreturntohomefromMRTT);
			   
			   
			   btnUpdateMovie_reqTransactionTable.setActionCommand("UpdateMovie_reqTransactionTable");
			   btnreturntohomefromMRTT.setActionCommand("returntoTransaction");
			   
			   ADDINGMOVIEREQ.add(panelSouth, BorderLayout.SOUTH);
		   }
		   
	 public void showMovie_reqTransactionTable() {
		 String showUnapprovedQuery = "SELECT * FROM movie_req WHERE approved IS NULL";
		   String[] col = {"request_number", "movie_name","date_filled", "user_no", "approved","media_type"};
		   tableModelMovie_reqTransaction = new DefaultTableModel(getMovie_reqTransaction(showUnapprovedQuery), col){
			   @Override
			   public boolean isCellEditable(int row, int column) {
				   return false; // Disable editing for all cells
			   }
		   };
		   
		   refreshAdminTable();
		   tableMovie_reqTransaction = new JTable(tableModelMovie_reqTransaction);
		   tableMovie_reqTransaction.setEnabled(true); // Enable selection
		   
		  
		   // Add a mouse click listener to the table
		   tableMovie_reqTransaction.addMouseListener(new java.awt.event.MouseAdapter() {
			  
			   public void mouseClicked(java.awt.event.MouseEvent evt) {
				   int row = tableMovie_reqTransaction.getSelectedRow(); // Get selected row index
				  
				   
				   if (row != -1) { // Ensure a valid cell is selected
					   int request_number = (int)tableMovie_reqTransaction.getValueAt(row,0);
					   String movie_name = (String)tableMovie_reqTransaction.getValueAt(row,1);
					   String date_filled = (String)tableMovie_reqTransaction.getValueAt(row,2);
					   int user_no = (int)tableMovie_reqTransaction.getValueAt(row,3);
					   String approved = (String)tableMovie_reqTransaction.getValueAt(row,4);             
					   String media_type = (String)tableMovie_reqTransaction.getValueAt(row,5); 
					   
					   MRTTrequest_no.setText(String.valueOf(request_number));
					   MRTTmovie_name.setText(movie_name);
					   MRTTdate_filled.setText(date_filled);
					   MRTTuser_no.setText(String.valueOf(user_no));
					   MRTTapproved.setSelectedItem(approved);
					   MRTTmedia_type.setSelectedItem(media_type);	
										  
				   }
			   }
		   });
		   
		   scrollerMovie_reqTransaction = new JScrollPane(tableMovie_reqTransaction);
		   scrollerMovie_reqTransaction.setPreferredSize(new Dimension(450, 200)); // Set preferred size
		   
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
		   panelCenter.add(scrollerMovie_reqTransaction, gbc);
		   moreCenter.add(panelCenter, BorderLayout.CENTER);
		   
		   ADDINGMOVIEREQ.add(moreCenter, BorderLayout.WEST);
		   ADDINGMOVIEREQ.revalidate(); // Refresh the UI
		   ADDINGMOVIEREQ.repaint(); // Ensure it's redrawn
		}

		//getting data from db
	 public Object[][] getMovie_reqTransaction(String query) {

			ArrayList<Object[]> list = new ArrayList<>();

			try {
			   // Load the JDBC driver
			   // Establish connection
			   try (
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(query)) {

				   // Process the ResultSet
				   
				   
				   while (resultSet.next()) {
					   Object[] row = new Object[6];
					   row[0] = resultSet.getInt(1); 
					   row[1] = resultSet.getString(2);            
					   row[2] = resultSet.getString(3);
					   row[3] = resultSet.getInt(4); 
					   String transmute;
					   
					   if(resultSet.getObject(5) == null) {
						   transmute = "";
					   }else if(resultSet.getInt(5) == 0 ){
						   transmute = "NO";
					   }else transmute = "YES";
					   row[4] = transmute;
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
		//refreshing admin table
	public void refreshMovie_reqTransactionTable() {
		String showUnapproved = "SELECT * FROM movie_req WHERE approved IS NULL";
		String showAll = "SELECT * FROM movie_req";
		tableMovieFileModel.setDataVector(getMovie_reqTransaction(showAll), new String[]{"request_number", "movie_name","date_filled", "user_no", "approved","media_type"});
		tableModelMovie_reqTransaction.setDataVector(getMovie_reqTransaction(showUnapproved), new String[]{"request_number", "movie_name","date_filled", "user_no", "approved","media_type"});
	}

	

	private JScrollPane scrollerFileReq;
	private JTable tableMovieFile;
	private DefaultTableModel tableMovieFileModel;
	private JTextField reqMovieName, reqUserName;
	private JButton btnFileReq, btnFileReqReturn;
	private JComboBox mediachoices;
	
	public void createMovieRequestPanel(){
		setContentPane(FileMovieReq);
		revalidate();
		repaint();
	}

	public void showFileRequestPanel() {
		String showAll = "SELECT * FROM movie_req";
		String[] col = {"request_number", "movie_name","date_filled", "user_no", "approved","media_type"};
		   tableMovieFileModel = new DefaultTableModel(getMovie_reqTransaction(showAll), col){
			   @Override
			   public boolean isCellEditable(int row, int column) {
				   return false; // Disable editing for all cells
			   }
		   };

		   refreshAdminTable();
		   tableMovieFile = new JTable(tableMovieFileModel);
		   tableMovieFile.setEnabled(true); // Enable selection
		   
		  
		   // Add a mouse click listener to the table
		  
	scrollerFileReq= new JScrollPane(tableMovieFile);
	scrollerFileReq.setPreferredSize(new Dimension(450, 200)); // Set preferred size
	
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
	panelCenter.add(scrollerFileReq, gbc);
	moreCenter.add(panelCenter, BorderLayout.CENTER);
	
	FileMovieReq.add(moreCenter, BorderLayout.WEST);
	FileMovieReq.revalidate(); // Refresh the UI
	FileMovieReq.repaint(); // Ensure it's redrawn

	}

	public void fileMovieRequestPanel() {

			// NORTH PANEL
			JPanel panelNorth = new JPanel();
			panelNorth.setLayout(new FlowLayout());
			panelNorth.setBackground(Color.decode("#0A285f"));

			JLabel label = new JLabel("REQUEST MOVIE");
			label.setForeground(Color.WHITE);
			label.setFont(new Font("Gaegu", Font.BOLD, 18));
			panelNorth.add(label);
			
			FileMovieReq.add(panelNorth, BorderLayout.NORTH);

		  //center panel
			JPanel centerPanel = new JPanel();
			centerPanel.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();

			gbc.insets = new Insets(6, 6, 6, 6);
			gbc.anchor = GridBagConstraints.WEST;
			
			JLabel movname = new JLabel("Movie Name");
			movname.setForeground(Color.BLACK);
			movname.setFont(new Font("Verdana", Font.BOLD, 19));
			gbc.gridx = 1;
			gbc.gridy = 2;
			centerPanel.add(movname, gbc);
			reqMovieName = new JTextField(20);
			gbc.gridx = 2;
			gbc.gridy = 2;
			centerPanel.add(reqMovieName,gbc);
			
			JLabel userno = new JLabel("User no.");
			userno.setForeground(Color.BLACK);
			userno.setFont(new Font("Verdana", Font.BOLD, 19));
			gbc.gridx = 1;
			gbc.gridy = 4;
			centerPanel.add(userno , gbc);
			reqUserName = new JTextField(10);
			gbc.gridx = 2;
			gbc.gridy = 4;
			centerPanel.add(reqUserName, gbc);
			
			JLabel mediaType = new JLabel("Media Type");
			mediaType.setForeground(Color.BLACK);
			mediaType.setFont(new Font("Verdana", Font.BOLD, 19));
			gbc.gridx = 1;
			gbc.gridy = 6;
			centerPanel.add(mediaType , gbc);
			String[] mediachoice = {"", "VHS", "CD", "DVD", "Blu-Ray","Online"};
			mediachoices = new JComboBox(mediachoice);
			gbc.gridx = 2;
			gbc.gridy = 6;
			centerPanel.add(mediachoices, gbc);
			
			btnFileReq = new JButton("Add");
			gbc.gridx = 1;
			gbc.gridy = 7;
			gbc.gridwidth = 2;
			gbc.anchor = GridBagConstraints.CENTER;
			centerPanel.add(btnFileReq, gbc);
			
			FileMovieReq.add(centerPanel , BorderLayout.EAST);

			//SOUTH PANEL
			JPanel panelSouth = new JPanel();
			panelSouth.setLayout(new FlowLayout());
			panelSouth.setBackground(Color.decode("#fdfdfd"));
			
			btnFileReqReturn = new JButton("Return");
			panelSouth.add(btnFileReqReturn);
			
			
			btnFileReq.setActionCommand("AddMovieRequest");
			btnFileReqReturn.setActionCommand("returntoTransaction");
			
			FileMovieReq.add(panelSouth, BorderLayout.SOUTH);
		
	}

	public Object[][] getFiling(String query) {

		ArrayList<Object[]> list = new ArrayList<>();

		try {
		   // Load the JDBC driver
		   // Establish connection
		   try (
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query)) {

			   // Process the ResultSet
			   
			   
			   while (resultSet.next()) {
				   Object[] row = new Object[6];
				   row[0] = resultSet.getInt(1); 
				   row[1] = resultSet.getString(2);            
				   row[2] = resultSet.getString(3);
				   row[3] = resultSet.getInt(4); 
				   String transmute;
				   
				   if(resultSet.getObject(5) == null) {
					   transmute = "";
				   }else if(resultSet.getInt(5) == 0 ){
					   transmute = "NO";
				   }else transmute = "YES";
				   row[4] = transmute;
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

	public String getReqMovieName() {
		return reqMovieName.getText();
	}
	
	public Integer getReqUserNum(){
		return Integer.parseInt(reqUserName.getText()) ;
	}

	public String getReqMediaType() {
	    return mediachoices.getSelectedItem().toString();
	}

	public void movieDetails() {
		

	}

	
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

		btnReturntoMainFromTransaction.addActionListener(listener);

		btnBorrowMovie.addActionListener(e -> borrowMovieGUI());
		btnReturnMovie.addActionListener(e -> returnMovieGUI());

		btnReviewMovieRequests.addActionListener(listener); //change name later

		btnFileReq.addActionListener(listener);
		
		btnReturnFromMediaManagement.addActionListener(listener);
		btnUpdateMovie_reqTransactionTable.addActionListener(listener);
		btnreturntohomefromMRTT.addActionListener(listener);
		btnFileReqReturn.addActionListener(listener);
		btnFormalizeMovieRequests.addActionListener(listener);

	
		
	}
	
	public String getMRTTmedia_type() {
		return MRTTmedia_type.getSelectedItem().toString();
		}
			
	public void setMRTTmedia_type(String num) {
		MRTTmedia_type.setSelectedItem(num);
		}
	public String getMRTTmovie_name() {
		return MRTTmovie_name.getText();
		}
				
	public void setMRTTmovie_name(String num) {
		MRTTmovie_name.setText(num);
				}
	
	public String getMRTTdate_filled() {
	    return MRTTdate_filled.getText();
	}
	
	public void setMRTTdate_filled(String num) {
		MRTTdate_filled.setText(num);
	}
	
	public String getMRTTuser_no() {
	    return MRTTuser_no.getText();
	}
	
	public void setMRTTuser_no(String num) {
		MRTTuser_no.setText(num);
	}


	public void setMRTTrequest_no(String num) {
		MRTTrequest_no.setText(num);
	}
	
	public String getMRTTrequest_no() {
	    return MRTTrequest_no.getText();
	}
	
	public void setMRTTapproved(String num) {
		MRTTapproved.setSelectedItem(num);
	}
	
	public String getMRTTapproved() {
	    return MRTTapproved.getSelectedItem().toString();
	}

	public String getLoggedInAdmin(){
		return loggedInAdmin;
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
	
	public int getTadmin_bo() {
	    return Integer.parseInt(Tadmin_bo.getText());
	}
	
	public void setTadmin_bo(String num) {
		Tadmin_bo.setText(num);
	}
	
	public int getTadmin_re() {
	    return Integer.parseInt(Tadmin_re.getText());
	}
	
	public void setTadmin_re(String num) {
		Tadmin_re.setText(num);
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
	
	public int getTproduct_id() {
	    return Integer.parseInt(Tproduct_id.getText());
	}
	
	public void setTproduct_id(String num) {
		Tproduct_id.setText(num);
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
	 
	 public void setUPpass(String name) {
		 UPpass.setText("   Password: " + name);
		 System.out.println(name);
		}
	 
	 public void setUPmembership(String name) {
		 UPmembership.setText("   Membership Date: " + name);
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
		setMediaRelease("");
		setMmedia_type("");
		setMediaCopies("");
		setRentalPrice("");
		setMRapproved("");
		setMRdate_filled("");
		setMRmedia_type("");
		setMRmoviename("");
		setMRMovieReqNo("");
		setMRUserno("");
		setMediaRelease("");
		
		setTadmin_bo("");
		setTdate_borrowed("");
		setTdate_returned("");
		setTdate_toreturn("");
		setTmovie_code("");
		setTpayment("");
		setTtransaction_no("");
		setTuser_no("");
		setTproduct_id("");

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