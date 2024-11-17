import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class transactionTest {

    public static void main(String[] args) {
		String url = "jdbc:mysql://192.168.1.41:3306/dbmovieRental";
		String username = "user";
		String password= "12345";
        PreparedStatement pstmt;
        int choice; // for condition

        Scanner sc = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connections = DriverManager.getConnection(url, username, password);

            choice = 0;

            do {
                System.out.println("[1] Insert in Admin Table");
                System.out.println("[2] Insert in Genre Type Table");
                System.out.println("[3] Insert in Media Type Table");
                System.out.println("[4] Insert in Movie Request Table");
                System.out.println("[5] Insert in Movies Table");
                System.out.println("[6] Insert in Review Table");
                System.out.println("[7] Insert in Transactions Table");
                System.out.println("[8] Insert in Users Table");
                System.out.println("[9] Borrow a Movie");
                System.out.println("[10] Return a Movie");
                System.out.println("[11] EXIT");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 9: 
                        // Borrow a movie (as implemented before)
                        // (Refer to previous code for borrowing implementation)
                        break;

                    case 10: 
                        // Return a movie
                        System.out.print("Enter your user number: ");
                        int userNumber = sc.nextInt();

                        System.out.print("Enter the movie code to return: ");
                        int movieCode = sc.nextInt();

                        // Check if the user is currently borrowing the movie
                        String checkUserBorrowingQuery = "SELECT COUNT(*) FROM transactions WHERE user_no = ? AND movie_code = ? AND date_returned IS NULL";
                        pstmt = connections.prepareStatement(checkUserBorrowingQuery);
                        pstmt.setInt(1, userNumber);
                        pstmt.setInt(2, movieCode);
                        ResultSet rs = pstmt.executeQuery();
                        rs.next();
                        int activeBorrowCount = rs.getInt(1);

                        if (activeBorrowCount == 0) {
                            System.out.println("You are not currently borrowing this movie.");
                            break;
                        }

                        // Proceed with returning the movie
                        // Update the transaction with the return date
                        String returnMovieQuery = "UPDATE transactions SET date_returned = ? WHERE user_no = ? AND movie_code = ? AND date_returned IS NULL";
                        pstmt = connections.prepareStatement(returnMovieQuery);

                        // Get the current date as the return date
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String currentDate = dateFormat.format(new Date());

                        // Set values for the return transaction
                        pstmt.setString(1, currentDate); // Set the date_returned to the current date
                        pstmt.setInt(2, userNumber);
                        pstmt.setInt(3, movieCode);

                        pstmt.executeUpdate();

                        // Update the movie's availability by increasing the available copies
                        String updateMovieAvailabilityQuery = "UPDATE media_type SET copies_available = copies_available + 1 WHERE movie_code = ?";
                        pstmt = connections.prepareStatement(updateMovieAvailabilityQuery);
                        pstmt.setInt(1, movieCode);
                        pstmt.executeUpdate();

                        System.out.println("Movie returned successfully!");

                        break;

                    case 11:
                        break;
                }

            } while (choice != 11);

        } catch (Exception e) {
            System.out.println("An error occurred: " + e);
        }
    }
}