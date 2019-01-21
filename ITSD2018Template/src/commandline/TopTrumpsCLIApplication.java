package commandline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/***
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that
	 * they want to run in command line mode. The contents of args[0] is whether we
	 * should write game logs to a file.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		String fileName;
		String textReadFromFile;
		int numberOfPlayers = 0; // players inc human
		int numberOfAI; // AI players
		ArrayList<AbsPlayer> players = new ArrayList<>();
		HumanPlayer human = new HumanPlayer();

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
//		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection

		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application

		if (!userWantsToQuit) {
			System.out.println(
					"████████╗ ██████╗ ██████╗     ████████╗██████╗ ██╗   ██╗███╗   ███╗██████╗ ███████╗\n"
							+ "╚��██╔���██╔���██╗██╔��██╗    ╚��██╔���██╔��██╗██║   ██║████╗ ████║██╔��██╗██╔�����\n"
							+ "   ██║   ██║   ██║██████╔�       ██║   ██████╔�██║   ██║██╔████╔██║██████╔�███████╗\n"
							+ "   ██║   ██║   ██║██╔����        ██║   ██╔��██╗██║   ██║██║╚██╔�██║██╔���� ╚����██║\n"
							+ "   ██║   ╚██████╔�██║            ██║   ██║  ██║╚██████╔�██║ ╚�� ██║██║     ███████║\n"
							+ "   ╚��    ╚������ ╚��            ╚��   ╚��  ╚�� ╚������ ╚��     ╚��╚��     ╚�������\n"
							+ "                                                                                   \n"
							+ "\n" + "");
			// Loop until the user wants to exit the game
			FileHandler filehandler = new FileHandler();
			filehandler.getFileData();
			// Getting the number of players from the human
			Scanner s = new Scanner(System.in);
			System.out.println("How many players? Type a number 2 --> 5");
			numberOfPlayers = s.nextInt();
			numberOfAI = (numberOfPlayers - 1);
			System.out.println("Number of players chosen: " + numberOfPlayers);

			while (numberOfPlayers < 2 || numberOfPlayers > 5) {
				System.out
						.println("Incorrect number of players entered.  \b" + "Please enter a number between 2 and 5");
				numberOfPlayers = s.nextInt();
			}

			players.add(human);
			for (int i = 1; i < numberOfPlayers; i++) {
				AIPlayer ai = new AIPlayer();
				players.add(ai);
			}

			// maybe make deck a constant?
			ArrayList<Card> deck = filehandler.getDeck();
			// Need to shuffle between rounds too if user restarts?
			// Maybe just recall this main if user selects restart
			Collections.shuffle(deck); // shuffling deck before gameplay
			Collections.shuffle(players);
			for (int i = 0; i < deck.size(); i++) {
				// runs through each player giving them cards until the deck runs out
				players.get(i % numberOfPlayers).givePlayerCard(deck.get(i));

			}

		}

		ArrayList<Card> cardSelection = new ArrayList<Card>();

		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

			boolean isRoundInPlay = true;
			int winIndex = 0;
			while (isRoundInPlay) {

				// Random Player selected
				cardSelection.add(players.get(winIndex).getTopCard());
				// Player chooses category 
				int catChoice = players.get(winIndex).getPlayersCatChoice();
				
				// other playes give their top card to the dealers deck
				for (AbsPlayer currentPlayerGivingCard : players) {
					if (currentPlayerGivingCard != players.get(winIndex)) {
						cardSelection.add(currentPlayerGivingCard.getTopCard());
					}
				}
				
				// Need to calculate highest category
				int winningValue = 0;
				int winningIndex = -1;
				for (int j=0; j<cardSelection.size(); j++) {
					System.out.println("Card " + j + ": " + cardSelection.get(j).getRequestedCat(catChoice));
					if (cardSelection.get(j).getRequestedCat(catChoice) > winningValue) {
						winningValue = cardSelection.get(j).getRequestedCat(catChoice);
						winningIndex = j;
					}
				}
				
				winIndex = winningIndex;
				if (winIndex == 1) {
					System.out.println("human winner: " + winningValue + " at " + winningIndex);
				}
				else {
					System.out.println("winner: " + winningValue + " at " + winningIndex);
				}
				cardSelection.clear();
				// use player id to check who has won
			}

		}

		// ----------------------------------------------------
		// Add your game logic here based on the requirements
		// ----------------------------------------------------

		userWantsToQuit = true; // use this when the user wants to exit the game

	}

	public static void compareCards(ArrayList<AbsPlayer> players) {

	}

}
