package commandline;

import java.util.ArrayList;

public class AIPlayer extends AbsPlayer{
	
	private static int playerIDCount = 2;
	private int playerID;
	private ArrayList<Card> personalDeck = new ArrayList<Card>();

	public AIPlayer() {
		playerID = playerIDCount;
		this.playerIDCount++;
		
	}
	// call methods from super class 
	// for getTopCard
	// and givePlayerCard
	
	public void getPlayersCatChoice() {
		//this will be calculated using IF statements
	}
	
	public void givePlayerCard(Card c) {
		System.out.println(c.getCardName() + " added to AIPlayer " + + getPlayerID());
		personalDeck.add(c);
	}
	
	public int getPlayerID() {
		return playerID;
	}
	
}
