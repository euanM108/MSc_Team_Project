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
		ArrayList<Player> players = new ArrayList<>();

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
//		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection

		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application

		// Welcome message
		System.out.println(
				"████████╗ ██████╗ ██████╗     ████████╗██████╗ ██╗   ██╗███╗   ███╗██████╗ ███████╗\n"
						+ "╚��██╔���██╔���██╗██╔��██╗    ╚��██╔���██╔��██╗██║   ██║████╗ ████║██╔��██╗██╔�����\n"
						+ "   ██║   ██║   ██║██████╔�       ██║   ██████╔�██║   ██║██╔████╔██║██████╔�███████╗\n"
						+ "   ██║   ██║   ██║██╔����        ██║   ██╔��██╗██║   ██║██║╚██╔�██║██╔���� ╚����██║\n"
						+ "   ██║   ╚██████╔�██║            ██║   ██║  ██║╚██████╔�██║ ╚�� ██║██║     ███████║\n"
						+ "   ╚��    ╚������ ╚��            ╚��   ╚��  ╚�� ╚������ ╚��     ╚��╚��     ╚�������\n"
						+ "                                                                                   \n" + "\n"
						+ "");

		// read the deck form the txt files and create the deck ArrayList
		ArrayList<Card> deck = new ArrayList<Card>();
		deck = readAndCreateDeck();

		// Set up scanner for UI
		Scanner s = new Scanner(System.in);

		numberOfPlayers = checkForNumberOfPlayers(getNoPlayers(s), s);
		System.out.println("Number of players chosen: " + numberOfPlayers);

		Player human = new Player();
		players.add(human);
		for (int i = 1; i < numberOfPlayers; i++) {
			Player AIPlayer = new Player();
			players.add(AIPlayer);
		}

		deck = shuffleDeck(deck);

		Collections.shuffle(players);

		for (int i = 0; i < deck.size(); i++) {
			// runs through each player giving them cards until the deck runs out
			players.get(i % numberOfPlayers).givePlayerCard(deck.get(i));
		}

		ArrayList<Card> cardSelection = new ArrayList<Card>();
		int catChoice = 0;
		int winningIndex = 0;

		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {
			System.out.println("Winning Index is     : " + winningIndex);
			for (int i = 0; i < players.size(); i++) {
				System.out.println("PLayer " + players.get(i).getPlayerID() + " is index : " + i);
			}

			try {
				for (int i = 0; i < players.size(); i++) {
					try {
						cardSelection.add(players.get(i).getTopCard());
					} catch (IndexOutOfBoundsException e) {
						System.out
								.println("Player " + players.get(i).getPlayerID() + " has been removed from the game.");
						players.remove(i);
					}
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Not Happy");
			}
			userWantsToQuit = checkForWin(players);

			if (userWantsToQuit) {
				System.out.println("\nEnd of game.");
				break;
			}

			while (winningIndex >= players.size()) {
				winningIndex--;
			}
			
			try {
				if (players.get(winningIndex).getPlayerID() != 1) {
					catChoice = players.get(winningIndex).getAIPlayersCatChoice();
					System.out.println("Player " + players.get(winningIndex).getPlayerID() + " has selected "
							+ players.get(winningIndex).getTopCard().getCardName() + "'s " + getCategory(catChoice)
							+ " at " + players.get(winningIndex).getTopCard().getRequestedCat(catChoice));		
				} else {
					catChoice = players.get(winningIndex).getHumanPlayersCatChoice();
					System.out.println("Player " + players.get(winningIndex).getPlayerID() + " has selected "
							+ getCategory(catChoice) + " at "
							+ players.get(winningIndex).getTopCard().getRequestedCat(catChoice));
				}

				winningIndex = getWinningIndex(cardSelection, catChoice);

				while (winningIndex >= players.size()) {
					winningIndex--;
				}
				
				System.out.println("Player " + players.get(winningIndex).getPlayerID() + " has won with "
						+ players.get(winningIndex).getTopCard().getCardName() + "'s " + getCategory(catChoice) + " at "
						+ players.get(winningIndex).getTopCard().getRequestedCat(catChoice));
				System.out.println();

			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}

			try {
				for (int i = 0; i < cardSelection.size(); i++) {
					players.get(winningIndex).givePlayerCard(cardSelection.get(i));
				}
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}
			
			removeCards(players);

			cardSelection.clear();
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

	private static boolean checkForWin(ArrayList<Player> players) {
		if (players.size() == 1) {
			System.out.println();
			System.out.println("Player " + players.get(0).getPlayerID() + " has won the game.");
			return true;
		}
		return false;
	}

	public static int checkForNumberOfPlayers(int numberOfPlayers, Scanner s) {
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
}
