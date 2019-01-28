package commandline;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {

	private ArrayList<Card> personalDeck = new ArrayList<Card>();
	private int catChoice;
	private int playerID;
	private static int playerIDCount = 1;

	public Player() {
		playerID = playerIDCount;
		playerIDCount++;
	}

	public int getPlayerID() {
		return playerID;
	}

	public int getCatChoice() {
		return catChoice;
	}

	public int getAIPlayersCatChoice() {
		// this will be calculated using IF statements
		// could use temporary arrays and find the highest value on the card
		Card c = personalDeck.get(0);
		int tempBestChoice = 1;
		int highestValue = c.getCat1Value();
		if (highestValue < c.getCat2Value()) {
			highestValue = c.getCat2Value();
			tempBestChoice = 2;
		}
		if (highestValue < c.getCat3Value()) {
			highestValue = c.getCat3Value();
			tempBestChoice = 3;
		}
		if (highestValue < c.getCat4Value()) {
			highestValue = c.getCat4Value();
			tempBestChoice = 4;
		}
		if (highestValue < c.getCat5Value()) {
			highestValue = c.getCat5Value();
			tempBestChoice = 5;
		}
		catChoice = tempBestChoice;
		return catChoice;
	}

	public int getHumanPlayersCatChoice() {
		// this will need system input for the human player
		Card c = personalDeck.get(0);
		c.viewCard();
		int tempCatChoice = 0;
		Scanner keyboard = new Scanner(System.in);
		// Exception handling for incorrect input by user
		boolean inputOk = false;
		while (!inputOk) {
			System.out.println("Please select your category choice between 1 and 5");
			try {
				tempCatChoice = keyboard.nextInt();
				inputOk = true;
			} catch (InputMismatchException e) {
				System.err.println("Error! Please try again.");
				keyboard.next();
			}
			while (tempCatChoice < 1 || tempCatChoice > 5) {
				System.err.println("Not a valid category choice");
				System.out.println("Please enter a category choice between 1 and 5");
				try {
					tempCatChoice = keyboard.nextInt();
				} catch (InputMismatchException e) {
					System.err.println("Error! Please try again.");
					keyboard.next();
				}
			}
			catChoice = tempCatChoice;
			System.out.println("You have selected category " + catChoice);
		}
		return catChoice;
	}

	public Card getTopCard() {
		Card topCard = personalDeck.get(0);
		return topCard;
	}

	public void removeTopCard() {
		personalDeck.remove(0);
	}

	public void givePlayerCard(Card c) {
		if (playerID==1) {
		}
		personalDeck.add(c);
	}

	public int getPersonalDeckSize() {
		return personalDeck.size();
	}
	
	public ArrayList<Card> getPersonalDeck() {
		return personalDeck;
	}
	

}
