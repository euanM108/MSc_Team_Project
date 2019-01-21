package commandline;

import java.util.ArrayList;

public class AIPlayer extends AbsPlayer{
	
	private static int playerIDCount = 2;

	private ArrayList<Card> personalDeck = new ArrayList<Card>();
	private int catChoice;

	public int getCatChoice() {
		return catChoice;
	}

	public AIPlayer() {
		
	}
	// call methods from super class 
	// for getTopCard
	// and givePlayerCard
	
//	public int turn() {
//		return getPlayersCatChoice(getTopCard());
//	}
	
	public int getPlayersCatChoice(Card c) {
		//this will be calculated using IF statements
		//could use temporary arrays and find the highest value on the card
		int tempBestChoice = 1;
		int highestValue = c.getCat1Value();
		if (highestValue<c.getCat2Value()) {
			highestValue = c.getCat2Value();
			tempBestChoice = 2;
		}
		if (highestValue<c.getCat3Value()) {
			highestValue = c.getCat3Value();
			tempBestChoice = 3;
		}
		if (highestValue<c.getCat4Value()) {
			highestValue = c.getCat4Value();
			tempBestChoice = 4;
		}
		if (highestValue<c.getCat5Value()) {
			highestValue = c.getCat5Value();
			tempBestChoice = 5;
		}
		catChoice = tempBestChoice;
		return catChoice;
	}
	
	public void givePlayerCard(Card c) {
		//System.out.println(c.getCardName() + " added to AIPlayer " + + getPlayerID());
		personalDeck.add(c);
	}
	

	
	public Card getTopCard() {
		Card topCard = personalDeck.get(0);
		personalDeck.remove(0);
		return topCard;
	}

}
