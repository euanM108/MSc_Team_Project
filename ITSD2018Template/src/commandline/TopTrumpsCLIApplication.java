package commandline;

import SharedFunctionality.Dealer;

/***
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 */
	
	private static String fileName;
	private static String textReadFromFile;
	
	public static void main(String[] args) {

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		if (!userWantsToQuit) {
			System.out.println("Welcome To The Game");
			// Loop until the user wants to exit the game
		}
		
		// Scan for number of players the user wants
		// a temporary number of players until commandLine is set up
		int numberOfPlayers = 2; 
		Dealer dealer = new Dealer(numberOfPlayers);
		
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			
			userWantsToQuit=true; // use this when the user wants to exit the game
			
		}


	}

}
