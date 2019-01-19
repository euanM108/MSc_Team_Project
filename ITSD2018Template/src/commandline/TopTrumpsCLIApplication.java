package commandline;

import java.util.Scanner;

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
	private static int numberOfPlayers; 	// players inc human
	private static int numberOfAI; 			// AI players
	
	public static void main(String[] args) {

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
//		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		if (!userWantsToQuit) {
			System.out.println(
					"████████╗ ██████╗ ██████╗     ████████╗██████╗ ██╗   ██╗███╗   ███╗██████╗ ███████╗\n" + 
					"╚══██╔══╝██╔═══██╗██╔══██╗    ╚══██╔══╝██╔══██╗██║   ██║████╗ ████║██╔══██╗██╔════╝\n" + 
					"   ██║   ██║   ██║██████╔╝       ██║   ██████╔╝██║   ██║██╔████╔██║██████╔╝███████╗\n" + 
					"   ██║   ██║   ██║██╔═══╝        ██║   ██╔══██╗██║   ██║██║╚██╔╝██║██╔═══╝ ╚════██║\n" + 
					"   ██║   ╚██████╔╝██║            ██║   ██║  ██║╚██████╔╝██║ ╚═╝ ██║██║     ███████║\n" + 
					"   ╚═╝    ╚═════╝ ╚═╝            ╚═╝   ╚═╝  ╚═╝ ╚═════╝ ╚═╝     ╚═╝╚═╝     ╚══════╝\n" + 
					"                                                                                   \n" + 
					"\n" + 
					"");
			// Loop until the user wants to exit the game
			FileHandler filehandler = new FileHandler();
			filehandler.getFileData();
			
			// Getting the number of players from the human
		       Scanner s = new Scanner(System.in);
		        System.out.println("How many players? Type a number 2 --> 5");
		        numberOfPlayers = s.nextInt();
		        numberOfAI = (numberOfPlayers - 1);
		        System.out.println("Number of players chosen: " + numberOfPlayers);

		        while(numberOfPlayers < 2 || numberOfPlayers >5){
		            System.out.println("Incorrect number of players entered.  \b" +
		                    "Please enter a number between 1 and 5");
		            numberOfPlayers = s.nextInt();
		        }
		}
		
		
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			
			userWantsToQuit=true; // use this when the user wants to exit the game
			
		}


	}

}
