package commandline;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
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


	public static void main(String[] args) {
		
		// Welcome message
		printWelcomeMessage();
				
		// Set up scanner for UI and file writer
		Scanner s = new Scanner(System.in);
		FileWriter fw = null;
		
		
		boolean writeGameLogsToFile = true; // Should we write game logs to file? REMOVE FOR PRODUCTION
		//DatabaseCommunication.connectToDatabase();
		//if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command
		
		boolean running = true;

		while (running) {
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
		System.out.println("Placeholder for testing when not connected to the database");
		//DatabaseCommunication.getPreviousStatistics(); USED IN PRODUCTION OR WHEN CONNECTED TO DATABASE.
	}
	
	public static void playGame(FileWriter fw, boolean writeGameLogsToFile, Scanner s) {
		
		//Game log stuff
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
	
		Player.resetPlayerIDCount();		
		numberOfPlayers = checkForNumberOfPlayers(getNoPlayers(s), s);
		System.out.println("Number of players chosen: " + numberOfPlayers);

		// Creating deck, players and shuffling deck
		ArrayList<Card> deck = new ArrayList<Card>();
		deck = readAndCreateDeck(); // deck is created from .txt file
		
		if(writeGameLogsToFile) {
			writeDeckToLog(deck, fw);
		}

		createPlayers(players, numberOfPlayers);

		deck = shuffleDeck(deck);
		Collections.shuffle(players);//shuffle the players so the starting player is random
		
		if(writeGameLogsToFile) {
			writeDeckToLog(deck, fw);
		}
		
		// runs through each player giving them cards until the deck runs out
		distributeCards(deck, players, numberOfPlayers);
		//**********print to log here
		if(writeGameLogsToFile) {
			//print each players deck here
			for(Player p: players) {
				writePlayerDeckToLog(p, fw);
			}			
		}

		resetStatistics(); //sets all the  statistics counters back to 0

		// *** GAMEPLAY BELOW ***		
		
		boolean gameInProgress = true; // flag to check whether a game is in progress or not

		// Loop until the current game is over
		while (gameInProgress) {				
			
			if(gameWon) {
				//logStatistics();
				if(writeGameLogsToFile) {
					writeWinnerToLog(players, fw);
					try {
						fw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				gameInProgress = false;
			}
			
			noRounds++; //increment the number of rounds after the check for a winner

			// there is no longer a game in progress
			if (!gameInProgress) {
				System.out.println("\nEnd of game.");
				s.nextLine(); //stops the menue interface from displaying twice
				break;
			}
			//System.out.println(cardSelection);
			cardSelection = getTopCards(players, cardSelection);
			//System.out.println(cardSelection);


			// keeps winningIndex under players arrayList size
			// As players are removed from the game, the index must be reduced
			while (winningIndex >= players.size()) {
				winningIndex--;
			}

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
				//System.out.println(cardSelection);
				//System.out.println(catChoice);
				//System.out.println(fw);
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
				
				//print communal pile to log here
				if(writeGameLogsToFile) {
					//write the contents of the communal pile
					writeCommunalPile(communalPile, fw);
				}
				
			
				printDraw(players, catChoice, winningIndex);
			} else {
				// if there is not a draw1
				winningIndex = getWinningIndex(cardSelection, catChoice);
				winnerCount(winningIndex, players); //calls the method to increment the database counter for the winner

								
				
				// Giving the winner the cards they won for this round
				for (int i = 0; i < cardSelection.size(); i++) {
					players.get(winningIndex).givePlayerCard(cardSelection.get(i));
				}
				
				//if there are cards in the communalPile give the player those
				if(communalPile.size() > 0) {
					for(int i = 0; i < communalPile.size(); i++){
						players.get(winningIndex).givePlayerCard(communalPile.get(i));
					}
					communalPile.clear();
					//print communal pile to log here
					if(writeGameLogsToFile) {
						//write the contents of the communal pile
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
				// print each players deck here
				for (Player p : players) {
					writePlayerDeckToLog(p, fw);
				}
			}

			gameWon = checkForOverallGameWin(players);
		}
	}
	

	
	
	private static void writeCatAndValues(ArrayList<Card> cardSelection, int catChoice, FileWriter fw) {
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
		String stringToPrint = "Round number: " + noRounds + "\nCards in Play: \n\n";
		
		for(Card c: cardSelection) {		
			stringToPrint += c.toString() + "\n\n";
		}
		writeToLog(stringToPrint, fw);
	}
	

	
	private static void writeWinnerToLog(ArrayList<Player> players, FileWriter fw) {
		String stringToPrint = "The winner of this game is: ";
		
		int winnerID = players.get(0).getPlayerID();
		if(winnerID == 1) {
			stringToPrint += "the human player!";
		}else {
			stringToPrint += "AI Player " + Integer.toString(winnerID-1);
		}
		
		writeToLog(stringToPrint, fw);

	}


	
	private static void writeCommunalPile(ArrayList<Card> communalPile, FileWriter fw) {
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
		String stringToPrint = "";
		
		//Whos deck is it
		int playerID = p.getPlayerID();
		if(playerID == 1) {
			stringToPrint += "Human Players Deck:\n\n";
		}else {
			stringToPrint += "AI Player Number " + Integer.toString(playerID-1) + "'s deck:\n\n";
		}
		
		//Print Their deck
		for(Card c: p.getPersonalDeck()) {
			stringToPrint += c.getCardName() + "\n";
		}
		
		writeToLog(stringToPrint, fw);
	
	}
	
	private static void writeDeckToLog(ArrayList<Card> deck, FileWriter fw) {
		String stringToPrint = "";
		for(Card c: deck) {		
			stringToPrint += c.getCardName() + "\n";
		}
		writeToLog(stringToPrint, fw);
	}
	
	private static void writeToLog(String text, FileWriter fw) {
		try {
			fw.write(text + "\n---------------------------------------------\n\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void winnerCount(int winningIndex, ArrayList<Player> players) {
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
		System.out.println("Player " + players.get(winningIndex).getPlayerID() + " has selected "+ getCategory(catChoice) + ". Multiple"
				+ " player's cards had the winning value so the round is a draw.");
		System.out.println();
	}

	private static boolean testForDraw(ArrayList<Card> cardSelection, int catChoice) {
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
	
	private static ArrayList<Card> getTopCards(ArrayList<Player> players, ArrayList<Card> cardSelection) {

		try {
			// running through each player and storing their top card in the dealers deck
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getPersonalDeckSize() > 0) {
					cardSelection.add(players.get(i).getTopCard()); // adding to cardSelection
				} else {
					// if they do not have a top card, they are removed from the game
					System.out
							.println("Player " + players.get(i).getPlayerID() + " has been removed from the game.");
					players.remove(i);
				}

			}
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return cardSelection;
	}
	
	private static void distributeCards(ArrayList<Card> deck, ArrayList<Player> players, int numberOfPlayers) {
		for (int i = 0; i < deck.size(); i++) {
			players.get(i % numberOfPlayers).givePlayerCard(deck.get(i));
		}
	}

	private static void createPlayers(ArrayList<Player> players, int numberOfPlayers) {
		Player human = new Player();
		players.add(human);
		for (int i = 1; i < numberOfPlayers; i++) {
			Player AIPlayer = new Player();
			players.add(AIPlayer);
		}
	}

	private static void removeCards(ArrayList<Player> players) {
		for (int i = 0; i < players.size(); i++) {
			try {
				players.get(i).removeTopCard();
			} catch (IndexOutOfBoundsException e) {

			}
		}
	}

	private static int getWinningIndex(ArrayList<Card> cardSelection, int catChoice) {
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
		System.out.println("Player " + players.get(winningIndex).getPlayerID() + " has selected "
				+ getCategory(catChoice) + " at " + players.get(winningIndex).getTopCard().getRequestedCat(catChoice));
	}

	private static void printWinStatement(ArrayList<Player> players, int catChoice, int winningIndex) {
		System.out.println("Player " + players.get(winningIndex).getPlayerID() + " has won with "
				+ players.get(winningIndex).getTopCard().getCardName() + "'s " + getCategory(catChoice) + " at "
				+ players.get(winningIndex).getTopCard().getRequestedCat(catChoice));
		System.out.println();
	}

	public static ArrayList<Card> readAndCreateDeck() {
		// method to tiy up reading the files and getting the deck
		FileHandler filehandler = new FileHandler();
		filehandler.getFileData();
		return  filehandler.getDeck();
	}

	private static ArrayList<Card> shuffleDeck(ArrayList<Card> deck) {
		Collections.shuffle(deck);
		return deck;
	}

	private static int getNoPlayers(Scanner s) {
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
		String category = "";
		switch (i) {
		case 1:
			category = "Size";
			break;
		case 2:
			category = "Speed";
			break;
		case 3:
			category = "Range";
			break;
		case 4:
			category = "Firepower";
			break;
		case 5:
			category = "Cargo";
			break;

		}
		return category;
	}

	private static boolean checkForOverallGameWin(ArrayList<Player> players) {
		if (players.size() == 1) {
			System.out.println();
			System.out.println("Player " + players.get(0).getPlayerID() + " has won the game.");
			winnerID = players.get(0).getPlayerID();
			return true;
		}
		return false;
	}
	
	private static void logStatistics() {
		//can only test this on university computers
		System.out.println(String.format("%d %d %d %d %d %d %d %d", winnerID, draws, noRounds, playerWins, AI1Wins, AI2Wins, AI3Wins, AI4Wins));
		DatabaseCommunication.writeGameResults(winnerID, draws, noRounds, playerWins, AI1Wins, AI2Wins, AI3Wins, AI4Wins);
	}
	
	private static void resetStatistics() {
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
				"████████╗ ██████╗ ██████╗     ████████╗██████╗ ██╗   ██╗███╗   ███╗██████╗ ███████╗\n"
						+ "╚��██╔���██╔���██╗██╔��██╗    ╚��██╔���██╔��██╗██║   ██║████╗ ████║██╔��██╗██╔�����\n"
						+ "   ██║   ██║   ██║██████╔�       ██║   ██████╔�██║   ██║██╔████╔██║██████╔�███████╗\n"
						+ "   ██║   ██║   ██║██╔����        ██║   ██╔��██╗██║   ██║██║╚██╔�██║██╔���� ╚����██║\n"
						+ "   ██║   ╚██████╔�██║            ██║   ██║  ██║╚██████╔�██║ ╚�� ██║██║     ███████║\n"
						+ "   ╚��    ╚������ ╚��            ╚��   ╚��  ╚�� ╚������ ╚��     ╚��╚��     ╚�������\n"
						+ "                                                                                   \n" + "\n"
						+ "");
	}
}
