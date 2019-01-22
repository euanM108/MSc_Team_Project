package commandline;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
	            }catch (InputMismatchException e) {
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
	//	System.out.println(topCard.getCardName() + " is your top card");
		personalDeck.remove(0);
		return topCard;
	}

	public void givePlayerCard(Card c) {
		//System.out.println(c.getCardName() + " added to HumanPlayer");
		personalDeck.add(c);
	}

}
