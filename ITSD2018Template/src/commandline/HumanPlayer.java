package commandline;

import java.util.ArrayList;

public class HumanPlayer extends AbsPlayer{
	
	private ArrayList<Card> personalDeck = new ArrayList<Card>();

	
	// call methods from super class 
	// for getTopCard
	// and givePlayerCard
	
	
	public void getPlayersCatChoice() {
		//this will need system input for the human player
	}

	public void givePlayerCard(Card c) {
		System.out.println(c.getCardName() + " added to HumanPlayer");
		personalDeck.add(c);
	}
}
