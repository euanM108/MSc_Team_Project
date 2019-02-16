package commandline;

import java.sql.*;

//this class will handle inputting and retrieving relevant information from the postgreSQL database 
//need to connect to the university database server. 

public class DatabaseCommunication {

	private static Connection connection = null;
	private static boolean connectionFailure = false;

	public static void connectToDatabase() {
		// check for the driver
		if (!connectionFailure) {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				System.err.println("Could not fine JDBC Driver");
				e.printStackTrace();
				return;
			}

			try {
				connection = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/",
						"m_18_2426628r", "2426628r");
			} catch (SQLException e) {
				System.err.println("Connection Failed! Unable to read or write statistics.");
				connectionFailure = true;
				e.printStackTrace(); //do we want to remove this line for production?
				return;
			}
		} else { //stops it from trying to connect to the database repeatedly if it fails.
			System.out.println("Connection attempts failed, please check credentials or database status.");
		}
	}

	public static void writeGameResults(int winnerID, int draws, int noRounds, int playerWins, int AI1Wins, int AI2Wins,
			int AI3Wins, int AI4Wins) {
		// writes the results of the game to the database
		// winner id is 1 for player and then 2-5 for each AI player in ascending order
		if (connection == null) {
			System.out.println("No database connection, attempting to connect.");
			connectToDatabase();
		} else {
			try {
				Statement SQLStatement = connection.createStatement();
				String SQLQuery = String.format(
						"INSERT INTO game_statistics (winnerid, nodraws, totalrounds, playerwins, aiplayeronewins, aiplayertwowins, aiplayerthreewins, aiplayerfourwins)\r\n"
								+ "VALUES (%d , %d, %d, %d, %d, %d, %d, %d)",
						winnerID, draws, noRounds, playerWins, AI1Wins, AI2Wins, AI3Wins, AI4Wins);
				SQLStatement.executeUpdate(SQLQuery);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String getPreviousStatistics() {
		//use 5 different methods then combine to 1 string or return 5 strings
		if (connection == null) {
			System.out.println("No database connection, attempting to connect.");
			connectToDatabase();
		} else {
			int noGames = getNoGames();
			int AIWins = getNoAIWins();
			int humanWins = getNoHumanWins();
			double AVGDraws = getAVGDraws();
			int largestNoRounds = getLargestNoRounds();
			//basic return string for now, can be altered as required.
			String statistics = String.format("\n\nTotal number of games played:\t%d\n"
					+ "Number of times the AI has won:\t%d\n"
					+ "Number of times the Human Player has won:\t%d\n"
					+ "The average number of draws in a game:\t%.2f\n"
					+ "The largest number of rounds played in a game:\t%d\n\n", noGames, AIWins, humanWins, AVGDraws, largestNoRounds);
			System.out.println(statistics);
			return statistics;
		}
		return null;
	}
	
	public static int getNoGames() {
		int noGames = 0;
		try {
			Statement SQLStatement = connection.createStatement();
			String SQLQuery = "SELECT MAX(id)\r\n" + 
					"FROM game_statistics";
			ResultSet queryResult = SQLStatement.executeQuery(SQLQuery);
			queryResult.next();
			noGames = queryResult.getInt(1);
			return noGames;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noGames;
	}

	public static int getNoAIWins() {
		int noAIWins = 0;
		try {
			Statement SQLStatement = connection.createStatement();
			String SQLQuery = "Select COUNT(winnerID)\r\n" + 
					"FROM game_statistics\r\n" + 
					"WHERE winnerID > 1";
			ResultSet queryResult = SQLStatement.executeQuery(SQLQuery);
			queryResult.next();
			noAIWins = queryResult.getInt(1);
			return noAIWins;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noAIWins;
	}
	
	public static int getNoHumanWins() {
		int noHumanWins = 0;
		try {
			Statement SQLStatement = connection.createStatement();
			String SQLQuery = "SELECT COUNT(winnerID)\r\n" + 
					"FROM game_statistics\r\n" + 
					"WHERE winnerID = 1";
			ResultSet queryResult = SQLStatement.executeQuery(SQLQuery);
			queryResult.next();
			noHumanWins = queryResult.getInt(1);
			return noHumanWins;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noHumanWins;
	}
	
	public static double getAVGDraws() {
		double AVGDraws = 0;
		try {
			Statement SQLStatement = connection.createStatement();
			String SQLQuery = "SELECT AVG(nodraws)\r\n" + 
					"FROM game_statistics";
			ResultSet queryResult = SQLStatement.executeQuery(SQLQuery);
			queryResult.next();
			AVGDraws = queryResult.getDouble(1);
			return AVGDraws;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return AVGDraws;
	}
	
	public static int getLargestNoRounds() {
		int largestNoRounds = 0;
		try {
			Statement SQLStatement = connection.createStatement();
			String SQLQuery = "SELECT MAX(totalrounds)\r\n" + 
					"FROM game_statistics";
			ResultSet queryResult = SQLStatement.executeQuery(SQLQuery);
			queryResult.next();
			largestNoRounds = queryResult.getInt(1);
			return largestNoRounds;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return largestNoRounds;
	}
	
	public static void clearHistory() {
		// this method completely clears the database table
		if (connection == null) {
			System.out.println("No database connection, attempting to connect.");
			connectToDatabase();
		} else {
			try {
				Statement SQLStatement = connection.createStatement();
				String SQLQuery = "DELETE FROM game_statistics";
				SQLStatement.executeUpdate(SQLQuery);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

/*	public static void main(String[] args) {
		//the main can be used for testing and will be removed when 
		//implemented into the full programme
		System.out.println("1");
		writeGameResults(3, 1, 1, 4, 6, 1, 4, 8);
		System.out.println("2");
		getPreviousStatistics();
		System.out.println("3");
		clearHistory();
		System.out.println("4");
		getPreviousStatistics();
	}*/

}
