package commandline;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.ListIterator;
import java.util.Scanner;

/***
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	// private static ArrayList<Card> deck = new ArrayList<Card>();

	/**
	 * This main method is called by TopTrumps.java when the user specifies that
	 * they want to run in command line mode. The contents of args[0] is whether we
	 * should write game logs to a file.
	 * 
	 * @param args
	 */
	
	//variables used in database
	private static int winnerID;
	private static int draws;
	private static int noRounds;
	private static int playerWins;
	private static int AI1Wins;
	private static int AI2Wins;
	private static int AI3Wins;
	private static int AI4Wins;
	
	//cat names used globally
	private static String cat1Name;
	private static String cat2Name;
	private static String cat3Name;
	private static String cat4Name;
	private static String cat5Name;


	public static void main(String[] args) {
		
		// Welcome message
		printWelcomeMessage();
				
		//set up scanner for UI
		//set up file writer for game log
		Scanner s = new Scanner(System.in);
		FileWriter fw = null;
		
		
		boolean writeGameLogsToFile = true; // Should we write game logs to file? REMOVE FOR PRODUCTION
		//if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; //uncomment for production
		
		//connect to the database so we can access and write stats
		DatabaseCommunication.connectToDatabase();
		
		boolean running = true; //main loop for whole system

		while (running) {
			//loop that displays the selection options
			//we return to this screen after viewing stats or when a game ends
			
			System.out.println("\n\nPlease select from the following options by entering 1, 2, or 3:\n");
			System.out.println("\t1: Play a new game.");
			System.out.println("\t2: View statistics about previous games.");
			System.out.println("\t3: Quit Programme.");
			String statOrGameChoice = s.nextLine();
			if (statOrGameChoice.equals("1")) {
				playGame(fw, writeGameLogsToFile, s);
			} else if (statOrGameChoice.equals("2")) {
				viewStats();
			} else if (statOrGameChoice.equals("3")) {
				System.out.println("\nThank you for using our Top Trumps application");
				running = false;
			} else {
				System.out.println("\nPlease select a valid option.");
			}
		}
	}
	
	// *** METHODS BELOW ***
	
	public static void viewStats() {
		//prints the stats from the database to the console. 
		
		//System.out.println("Placeholder for testing when not connected to the database");
		DatabaseCommunication.getPreviousStatistics();
	}
	
	public static void playGame(FileWriter fw, boolean writeGameLogsToFile, Scanner s) {
		//the main loop for setting up the game. (contains the code to play the game)
		
		//initialise the TomTrumps.log file if we have selected to write to the game log
		if(writeGameLogsToFile) {
			try {
				fw = new FileWriter("TomTrumps.log");
				fw.write("Gameplay log for Top Trumps.\n---------------------------------------------\n");			
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		//variables
		int numberOfPlayers = 0; // players inc human
		int catChoice = 0; // current category choice
		int winningIndex = 0; // current winning index
		boolean draw = false;
		boolean gameWon = false;
		
		ArrayList<Player> players = new ArrayList<>();
		ArrayList<Card> cardSelection = new ArrayList<Card>();
		ArrayList<Card> communalPile = new ArrayList<Card>();
		ArrayList<Card> deck = new ArrayList<Card>();

		
	
		Player.resetPlayerIDCount(); //since player ID is static and incremented we need to reset it for new games.	
		numberOfPlayers = checkForNumberOfPlayers(getNoPlayers(s), s); //get the number of players from the user
		System.out.println("Number of players chosen: " + numberOfPlayers);
		
		

		// Creating deck, players, and shuffling the deck
		deck = readAndCreateDeck(); // deck is created from .txt file
		
		//if we are writing the game log, write the deck here.
		if(writeGameLogsToFile) {
			writeDeckToLog(deck, fw);
		}

		createPlayers(players, numberOfPlayers); //create players based on the users selected number of players
		
		ArrayList<String> catNames = new ArrayList<String>(deck.get(0).getCatNames());//get description and cat names from card	
		setCatNames(catNames); //set up the card names for the switch case
		
		deck = shuffleDeck(deck); //shuffle the deck
		Collections.shuffle(players);//shuffle the players so the starting player is random
		
		//if writing to the log, write the shuffled deck
		if(writeGameLogsToFile) {
			writeDeckToLog(deck, fw);
		}
		
		// runs through each player giving them cards until the deck runs out
		distributeCards(deck, players, numberOfPlayers);
		
		
		//if writing to the log, write every individual players deck.
		if(writeGameLogsToFile) {
			//print each players deck here
			for(Player p: players) {
				writePlayerDeckToLog(p, fw);
			}			
		}

		resetStatistics(); //sets all the  statistics counters back to 0 every new game

		// *** GAMEPLAY BELOW ***		
		
		boolean gameInProgress = true; // flag to check whether a game is in progress or not

		// Loop until the current game is over
		while (gameInProgress) {		
			
			System.out.println("\n\n======================================================================\n\n");
			
			//get the top cards from players. !!!This method also removes players without top cards!!!
			cardSelection = getTopCards(players, cardSelection); 
			gameWon = checkForOverallGameWin(players); //if only one player is left then the game is won

			
			//if the game is won then do the following to close out this game
			if(gameWon) { 
				logStatistics(); //log the statistics to the database
				if(writeGameLogsToFile) {
					writeWinnerToLog(players, fw); //write the winner to the log 
					try {
						fw.close(); //close the log file
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				gameInProgress = false; //set this to false to end the loop
			}
			
			noRounds++; //increment the number of rounds after the check for a winner

			// there is no longer a game in progress
			if (!gameInProgress) {
				System.out.println("\nEnd of game.");
				System.out.println("\n\n======================================================================\n\n");
				s.nextLine(); //stops the menue interface from displaying twice
				break;
			}
			

			// keeps winningIndex under players arrayList size
			// if the winning index was 4 but player 2 was removed from the game then the players would shift down
			// so we must lower the index (more than one player can be removed at once so while loop is used)
			while(winningIndex > players.size() - 1) {
				winningIndex--;
			}


			
			//method to get the category choice for the current round and print it to the console
			// If human player, call human method getHumanPlayersCatChoice()
			// If AI Player, call AI method getAIPLayersCatChoice()
			// Printing out a statement regarding their category choice
			System.out.println("Round number:  " + noRounds + "\tActive Player:  " + players.get(winningIndex).getPlayerID() + "\tCard Drawn:  " + players.get(winningIndex).getTopCard().getCardName());
			if (players.get(winningIndex).getPlayerID() != 1) {
				catChoice = players.get(winningIndex).getAIPlayersCatChoice();
				printCatSelectedStatement(players, catChoice, winningIndex);
			} else {
				catChoice = players.get(winningIndex).getHumanPlayersCatChoice();
				printCatSelectedStatement(players, catChoice, winningIndex);
			}

			
			//print current cards in play to log
			if(writeGameLogsToFile) {
				writeCurrentCards(cardSelection, fw);
			}

			
			//print selected category to log
			if(writeGameLogsToFile && !cardSelection.isEmpty()) {
				writeCatAndValues(cardSelection, catChoice, fw);
			}

			// find out if the game results in a win or a draw
			draw = testForDraw(cardSelection, catChoice);

			if (draw) {
				// if the round is a draw
				// winning index stays the same
				// cards are moved to communal pile
				draws++; //add 1 to the count of draws for the database
				
				for (Card tempCard : cardSelection) {
					communalPile.add(tempCard);
				}
				
				
				//write the contents of the communal pile to the log
				if(writeGameLogsToFile) {
					writeCommunalPile(communalPile, fw);
				}
				
				printDraw(players, catChoice, winningIndex); //print the draw message in the console
				
			} else {
				// if there is not a draw
				
				//get the index of the player who won
				winningIndex = getWinningIndex(cardSelection, catChoice);
				
				//calls the method to increment the database counter for the appropriate winner
				winnerCount(winningIndex, players); 

												
				// Giving the winner the cards they won for this round
				for (int i = 0; i < cardSelection.size(); i++) {
					players.get(winningIndex).givePlayerCard(cardSelection.get(i));
				}
				
				//if there are cards in the communalPile give the player those
				if(communalPile.size() > 0) {
					for(int i = 0; i < communalPile.size(); i++){
						players.get(winningIndex).givePlayerCard(communalPile.get(i));
					}
					communalPile.clear(); //clear the communal pile after giving the cards
					
					//write the contents of the communal pile (it will always be cleared here)
					if(writeGameLogsToFile) {
						writeCommunalPile(communalPile, fw);
					}
				}
				
				// Prints who wins the round
				printWinStatement(players, catChoice, winningIndex);
			}

			// clear the current cardSelection
			cardSelection.clear();

			// remove top cards after round is finished
			removeCards(players);

			// at the end of each round we write each players deck
			if (writeGameLogsToFile) {
				for (Player p : players) {
					writePlayerDeckToLog(p, fw);
				}
			}

		}
	}
	

	private static void setCatNames(ArrayList<String> catNames){
		//method which calls setters for the cat names
		
		setCat1Name(catNames.get(1));
		setCat2Name(catNames.get(2));
		setCat3Name(catNames.get(3));
		setCat4Name(catNames.get(4));
		setCat5Name(catNames.get(5));
		
	}
	


	
	

	
	
	//====================	METHODS FOR WRITING TO THE GAMEPLAY LOG ==========================
	
	private static void writeToLog(String text, FileWriter fw) {
		//This method is used by all the other methods that write to the log.
		//It seperates print outs and writes to the file
		
		
		try {
			fw.write(text + "\n---------------------------------------------\n\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeCatAndValues(ArrayList<Card> cardSelection, int catChoice, FileWriter fw) {
		//writes the category chosen for the round.
		//writes each cards in plays value for the categroy
		
		String stringToPrint = "The selected Category was: ";
		ArrayList<String> catNames = cardSelection.get(0).getCatNames();
		String selectedCatName = catNames.get(catChoice);
		stringToPrint += selectedCatName + ".\n\n";
		for(Card c: cardSelection) {
			stringToPrint += c.getCardName() + " has a " + selectedCatName + " value of " + c.getRequestedCat(catChoice) + "\n";
		}
		
		writeToLog(stringToPrint, fw);
	}
	
	

	private static void writeCurrentCards(ArrayList<Card> cardSelection, FileWriter fw) {
		//writes the current round number and the cards in play
		
		String stringToPrint = "Round number: " + noRounds + "\nCards in Play: \n\n";
		
		for(Card c: cardSelection) {		
			stringToPrint += c.toString() + "\n\n";
		}
		writeToLog(stringToPrint, fw);
	}
	

	
	private static void writeWinnerToLog(ArrayList<Player> players, FileWriter fw) {
		//writes the game winner the the log.
		
		String stringToPrint = "The winner of this game is: ";
		
		int winnerID = players.get(0).getPlayerID();
		if(winnerID == 1) {
			stringToPrint += "the human player!";
		}else {
			stringToPrint += "(AI) Player " + Integer.toString(winnerID);
		}
		
		writeToLog(stringToPrint, fw);

	}


	
	private static void writeCommunalPile(ArrayList<Card> communalPile, FileWriter fw) {
		//writes the contents (or lack of) to the log whenever its updated 
		
		String stringToPrint = "";
		
		if(communalPile.size()>0) {
			stringToPrint += "Communal Pile Updated:\n\n";
			for(Card c: communalPile) {
				stringToPrint += c.getCardName() + "\n";
			}
		} else {
			stringToPrint = "\nThe communal Pile was emptied.\n\n";
		}
		
		writeToLog(stringToPrint, fw);

	}

	
	private static void writePlayerDeckToLog(Player p, FileWriter fw) {
		//writes each players deck to the log.
		//if a players deck is empty it will write the empty deck once on the next turn
		
		String stringToPrint = "";
		
		//Whos deck is it
		int playerID = p.getPlayerID();
		if(playerID == 1) {
			stringToPrint += "(Human) Player 1's Deck:\n\n";
		}else {
			stringToPrint += "(AI) Player " + Integer.toString(playerID) + "'s Deck:\n\n";
		}
		
		//Print Their deck
		for(Card c: p.getPersonalDeck()) {
			stringToPrint += c.getCardName() + "\n";
		}
		
		writeToLog(stringToPrint, fw);
	
	}
	
	private static void writeDeckToLog(ArrayList<Card> deck, FileWriter fw) {
		//writes the full deck to the log 
		
		
		String stringToPrint = "";
		for(Card c: deck) {		
			stringToPrint += c.getCardName() + "\n";
		}
		writeToLog(stringToPrint, fw);
	}
	

	
	//====================================================================================
	
	
	
	
	
	
	private static void winnerCount(int winningIndex, ArrayList<Player> players) {
		//method to update the counter for winning players. These are used in the database
		
		int roundWinnerID = players.get(winningIndex).getPlayerID(); //get the ID of the winning player
		
		if(roundWinnerID == 1) {
			playerWins++;
		}else if(roundWinnerID == 2) {
			AI1Wins++;
		}else if(roundWinnerID == 3) {
			AI2Wins++;
		}else if(roundWinnerID == 4){
			AI3Wins++;
		}else {
			AI4Wins++;
		}
	}

	private static void printDraw(ArrayList<Player> players, int catChoice, int winningIndex) {
		//if there is a draw print this to the console 
		
		System.out.println("Player " + players.get(winningIndex).getPlayerID() + " has selected "+ getCategory(catChoice) + ". Multiple"
				+ " player's cards had the winning value so the round is a draw.");
		System.out.println("The cards have been addedd to the communal pile for the next winner!");
	}

	public static boolean testForDraw(ArrayList<Card> cardSelection, int catChoice) {
		//method to test for draws.
		
		
		int winningValue = 0;
		int numberOfHighCards = 0; // counts the number of times the highest cat is seen

		// find the winning Value
		for (int j = 0; j < cardSelection.size(); j++) {
			if (cardSelection.get(j).getRequestedCat(catChoice) > winningValue) {
				winningValue = cardSelection.get(j).getRequestedCat(catChoice);
			}
		}

		// check if the winning value occurs more than once
		for (int i = 0; i < cardSelection.size(); i++) {
			if (cardSelection.get(i).getRequestedCat(catChoice) == winningValue) {
				numberOfHighCards++;
			}
		}

		if (numberOfHighCards > 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public static ArrayList<Card> getTopCards(ArrayList<Player> players, ArrayList<Card> cardSelection) {
		//this method gets the top cards from each of the players decks
		//it is also responsible for removing players from the game if they are out of cards.
		
		ArrayList<Integer> toBeRemoved = new ArrayList<Integer>();

		try {
			// running through each player and storing their top card in the dealers deck
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getPersonalDeckSize() > 0) {
					cardSelection.add(players.get(i).getTopCard()); // adding to cardSelection
				} else {
					// if they do not have a top card, they are removed from the game
					System.out.println("Player " + players.get(i).getPlayerID() + " has been removed from the game.");
					toBeRemoved.add(i); //we have to do this in a seperate loop otherwise it messes up the indexing!
				}
			}
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		
		//remove all of the players with no cards left from the players list
		for(int i = players.size()-1; i >= 0; i--) {
			if(toBeRemoved.contains(i)) {
				players.remove(i);
			}
		}
		return cardSelection;
	}
	
	public static void distributeCards(ArrayList<Card> deck, ArrayList<Player> players, int numberOfPlayers) {
		//deal the cards out 
		
		for (int i = 0; i < deck.size(); i++) {
			players.get(i % numberOfPlayers).givePlayerCard(deck.get(i));
		}
	}

	private static void createPlayers(ArrayList<Player> players, int numberOfPlayers) {
		//create a player based on the users number of players choice
		
		Player human = new Player(); //there is always a human player
		players.add(human);
		for (int i = 1; i < numberOfPlayers; i++) {
			Player AIPlayer = new Player();
			players.add(AIPlayer);
		}
	}

	public static void removeCards(ArrayList<Player> players) {
		//removes the top cards from the players personal decks at the end of each round 
		
		for (int i = 0; i < players.size(); i++) {
			try {
				players.get(i).removeTopCard();
			} catch (IndexOutOfBoundsException e) {

			}
		}
	}

	public static int getWinningIndex(ArrayList<Card> cardSelection, int catChoice) {
		//gets the index of the winning player
		
		int winningValue = 0;
		int winningIndex = 0;

		for (int j = 0; j < cardSelection.size(); j++) {
			if (cardSelection.get(j).getRequestedCat(catChoice) > winningValue) {
				winningValue = cardSelection.get(j).getRequestedCat(catChoice);
				winningIndex = j;
			}
		}
		return winningIndex;
	}

	private static void printCatSelectedStatement(ArrayList<Player> players, int catChoice, int winningIndex) {
		//statement to print the selected category in the console
		
		System.out.println("Player " + players.get(winningIndex).getPlayerID() + " has selected "
				+ getCategory(catChoice) + " at " + players.get(winningIndex).getTopCard().getRequestedCat(catChoice));
	}

	private static void printWinStatement(ArrayList<Player> players, int catChoice, int winningIndex) {
		//print the winner, the card they had, the category selected, and its value
		
		System.out.println("Player " + players.get(winningIndex).getPlayerID() + " has won with "
				+ players.get(winningIndex).getTopCard().getCardName() + "'s " + getCategory(catChoice) + " at "
				+ players.get(winningIndex).getTopCard().getRequestedCat(catChoice));
		System.out.println();
	}

	public static ArrayList<Card> readAndCreateDeck() {
		// method to tidy up reading the files and getting the deck
		
		FileHandler filehandler = new FileHandler();
		filehandler.getFileData();
		return  filehandler.getDeck();
	}

	private static ArrayList<Card> shuffleDeck(ArrayList<Card> deck) {
		//shuffles the deck
		//this was made a method so that it could be accessed by other classes
		
		Collections.shuffle(deck);
		return deck;
	}

	private static int getNoPlayers(Scanner s) {
		//promt the user for the number of players
		
		int noPlayers = 0;
		boolean inputOk = false;
		while (!inputOk) {
			// Getting the number of players from the human
			System.out.println("How many players? Type a number 2 --> 5");
			try {
				noPlayers = s.nextInt();
				inputOk = true;
			} catch (InputMismatchException e) { // Catching invalid input exception if user enters a letter
				System.err.println("Error! Please try again.");
				s.next();
			}
		}

		return noPlayers;
	}

	private static String getCategory(int i) {
		//get the categoryies
		//we do this so that any deck can be used.
		
		String category = "";
		switch (i) {
		case 1:
			category = getCat1Name();
			break;
		case 2:
			category = getCat2Name();
			break;
		case 3:
			category = getCat3Name();
			break;
		case 4:
			category = getCat4Name();
			break;
		case 5:
			category = getCat5Name();
			break;

		}
		return category;
	}

	public static boolean checkForOverallGameWin(ArrayList<Player> players) {
		//checks if there is a winner

		
		if (players.size() == 1) { //if there is only one player left then we have a game winner
			System.out.println();
			System.out.println("Player " + players.get(0).getPlayerID() + " has won the game.");
			winnerID = players.get(0).getPlayerID();
			return true;
		}
		return false;
	}
	
	private static void logStatistics() {
		//can only test this on university computers
		//writes all the stats the the database at the end of the game
		
		//System.out.println(String.format("%d %d %d %d %d %d %d %d", winnerID, draws, noRounds, playerWins, AI1Wins, AI2Wins, AI3Wins, AI4Wins));
		DatabaseCommunication.writeGameResults(winnerID, draws, noRounds, playerWins, AI1Wins, AI2Wins, AI3Wins, AI4Wins);
	}
	
	private static void resetStatistics() {
		//we need to reset the statistics when a new game is initialised
		
		winnerID = 0;
		draws = 0;
		noRounds = 0;
		playerWins = 0;
		AI1Wins = 0;
		AI2Wins = 0;
		AI3Wins = 0;
		AI4Wins = 0;

	}

	private static int checkForNumberOfPlayers(int numberOfPlayers, Scanner s) {
		//checks that the number of players is valid
		
		while (numberOfPlayers < 2 || numberOfPlayers > 5) {
			System.out.println("Incorrect number of players entered.  \b" + "Please enter a number between 2 and 5");
			try {
				numberOfPlayers = s.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Error! Please try again.");
				s.next();
			}
		}
		return numberOfPlayers;
	}
	

	private static void printWelcomeMessage() {
		System.out.println(
				".------..------..------.     .------..------..------..------..------..------.\r\n" + 
				"|T.--. ||O.--. ||P.--. |.-.  |T.--. ||R.--. ||U.--. ||M.--. ||P.--. ||S.--. |\r\n" + 
				"| :/\\: || :/\\: || :/\\: ((5)) | :/\\: || :(): || (\\/) || (\\/) || :/\\: || :/\\: |\r\n" + 
				"| (__) || :\\/: || (__) |'-.-.| (__) || ()() || :\\/: || :\\/: || (__) || :\\/: |\r\n" + 
				"| '--'T|| '--'O|| '--'P| ((1)) '--'T|| '--'R|| '--'U|| '--'M|| '--'P|| '--'S|\r\n" + 
				"`------'`------'`------'  '-'`------'`------'`------'`------'`------'`------'");
	}

	public static String getCat1Name() {
		return cat1Name;
	}

	public static void setCat1Name(String cat1Name) {
		TopTrumpsCLIApplication.cat1Name = cat1Name;
	}

	public static String getCat2Name() {
		return cat2Name;
	}

	public static void setCat2Name(String cat2Name) {
		TopTrumpsCLIApplication.cat2Name = cat2Name;
	}

	public static String getCat3Name() {
		return cat3Name;
	}

	public static void setCat3Name(String cat3Name) {
		TopTrumpsCLIApplication.cat3Name = cat3Name;
	}

	public static String getCat4Name() {
		return cat4Name;
	}

	public static void setCat4Name(String cat4Name) {
		TopTrumpsCLIApplication.cat4Name = cat4Name;
	}

	public static String getCat5Name() {
		return cat5Name;
	}

	public static void setCat5Name(String cat5Name) {
		TopTrumpsCLIApplication.cat5Name = cat5Name;
	}
}
