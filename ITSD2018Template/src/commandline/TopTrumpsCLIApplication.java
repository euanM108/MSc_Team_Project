package commandline;

import java.util.ArrayList;
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

	public static void main(String[] args) {

		int numberOfPlayers = 0; // players inc human
		int catChoice = 0; // current category choice
		int winningIndex = 0; // current winning index
		ArrayList<Player> players = new ArrayList<>();
		ArrayList<Card> cardSelection = new ArrayList<Card>();
		
		// Welcome message
				printWelcomeMessage();
				
				
		// Set up scanner for UI
		Scanner s = new Scanner(System.in);
		numberOfPlayers = checkForNumberOfPlayers(getNoPlayers(s), s);
		System.out.println("Number of players chosen: " + numberOfPlayers);

		// Creating deck, players and shuffling deck
		ArrayList<Card> deck = new ArrayList<Card>();
		deck = readAndCreateDeck(); // deck is created from .txt file

		createPlayers(players, numberOfPlayers);
		deck = shuffleDeck(deck);
		Collections.shuffle(players);

		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		// if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command
		
		// runs through each player giving them cards until the deck runs out
		for (int i = 0; i < deck.size(); i++) {
			players.get(i % numberOfPlayers).givePlayerCard(deck.get(i));
		}

		// *** GAMEPLAY BELOW ***

		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

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

			// checking for a winner
			// If there is more than one players left, returns true and game continues
			// If there is only one player left, return false: the game is over and that
			// players wins
			userWantsToQuit = checkForOverallGameWin(players);

			// if true end game
			if (userWantsToQuit) {
				System.out.println("\nEnd of game.");
				break;
			}

			// keeps winningIndex under players arrayList size
			// As players are removed from the game, the index must be reduced
			while (winningIndex >= players.size()) {
				winningIndex--;
			}

			// If human player, call human method getHumanPlayersCatChoice()
			// If AI Player, call AI method getAIPLayersCatChoice()
			// Printing out a statement regarding their category choice
			try {
				if (players.get(winningIndex).getPlayerID() != 1) {
					catChoice = players.get(winningIndex).getAIPlayersCatChoice();
					printCatSelectedStatement(players, catChoice, winningIndex);
				} else {
					catChoice = players.get(winningIndex).getHumanPlayersCatChoice();
					printCatSelectedStatement(players, catChoice, winningIndex);
				}
				
				// Calculates the winning player's index in the ArrayList players
				winningIndex = getWinningIndex(cardSelection, catChoice);

				// ensures winningIndex is always less than player size
				while (winningIndex >= players.size()) {
					winningIndex--;
				}

				// Prints who wins the round 
				printWinStatement(players, catChoice, winningIndex);

			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}

			// Giving the winner the cards they won
			try {
				for (int i = 0; i < cardSelection.size(); i++) {
					players.get(winningIndex).givePlayerCard(cardSelection.get(i));
				}
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}

			// remove top cards after round is finished
			removeCards(players);

			// Clears the dealers deck for a new round
			cardSelection.clear();
		}

	}
	
	// *** METHODS BELOW ***

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

	private static ArrayList<Card> readAndCreateDeck() {
		ArrayList<Card> deck = new ArrayList<Card>();
		// method to tiy up reading the files and getting the deck
		FileHandler filehandler = new FileHandler();
		filehandler.getFileData();
		return deck = filehandler.getDeck();
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
			return true;
		}
		return false;
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
