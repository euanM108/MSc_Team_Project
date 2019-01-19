package commandline;

import java.sql.*;

//this class will handle inputting and retrieving relevant information from the postgreSQL database 
//Option 1: make everything static and check if we are connected to database for each method
//Option 2: use a constructor which connects to the database.
//Option 1 is better because it limits the risk of errors.

public class DatabaseCommunication {

	private static Connection connection = null;

	private static void connectToDatabase() {
		// check for the driver
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Could not fine JDBC Driver");
			e.printStackTrace();
			return;
		}

		try {
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/DTAAE1", "postgres", "password");
		} catch (SQLException e) {
			System.err.println("Connection Failed!");
			e.printStackTrace();
			return;
		}
	}

	public static void writeGameResults(int draws, int winnerID, int noRounds, int AI1Wins, int AI2Wins, int AI3Wins, int AI4Wins) {
		// writes the results of the game to the database
		if (connection != null) {
			//sql statement to write game results goes here
		} else {
			connectToDatabase();
			writeGameResults(draws, winnerID, noRounds, AI1Wins, AI2Wins, AI3Wins, AI4Wins);
		}
	}
	
	public static void getPreviousGameStatistics() {
		//use 5 different methods then combine to 1 string or return 5 strings
		if (connection != null) {
			//sql statement to write game results goes here
		} else {
			connectToDatabase();
			getPreviousGameStatistics();
		}
	}
	
	private static String getNoGames() {
		String noGames = null;
		try {
			Statement noGamesStatement = connection.createStatement();
			String SQLQuery = "query placeholder";
			ResultSet queryResult = noGamesStatement.executeQuery(SQLQuery); 
			noGames = queryResult.getString("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noGames;
	}
	
	private static String getNoAIWins() {
		return "placeholder";
	}
	
	private static String getNoHumanWins() {
		return "placeholder";
	}
	
	private static String getAVGDraws() {
		return "placeholder";
	}
	
	private static String getLargestNoRounds() {
		return "placeholder";
	}

	public static void main(String[] args) {
		//the main can be used for testing and will be removed when 
		//into the full programme
	}

}
