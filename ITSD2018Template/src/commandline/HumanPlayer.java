package commandline;

import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends AbsPlayer{
	
	private ArrayList<Card> personalDeck = new ArrayList<Card>();
	private int catChoice;
	
	
	// call methods from super class 
	// for getTopCard
	// and givePlayerCard
	
	public int getCatChoice() {
		return catChoice;
	}

//	public int turn() {
//		return getPlayersCatChoice(getTopCard());
//	}
	
	public int getPlayersCatChoice() {
		//this will need system input for the human player
		Card c = personalDeck.get(0);
		c.viewCard();
		int tempCatChoice = 0;
		System.out.println("Please select your category choice between 1 and 5");
		Scanner keyboard = new Scanner(System.in);
		tempCatChoice = keyboard.nextInt();
		/*
		 * More exception handling can be added
		 */
		while(tempCatChoice < 1 || tempCatChoice > 5) {
			System.err.println("Not a valid category choice");
			System.out.println("Please enter a category choice between 1 and 5");
			tempCatChoice = keyboard.nextInt();
		}
		catChoice = tempCatChoice;
		System.out.println("You have selected category " + catChoice);
	
		return catChoice;
	}
	
	
	public Card getTopCard() {
		Card topCard = personalDeck.get(0);
	//	System.out.println(topCard.getCardName() + " is your top card");
		personalDeck.remove(0);
		return topCard;
	}

	public void givePlayerCard(Card c) {
		//System.out.println(c.getCardName() + " added to HumanPlayer");
		personalDeck.add(c);
	}

}
