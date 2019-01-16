package commandline;

import java.util.ArrayList;

//this will contain stuff that the AI player and human will complain

public abstract class AbsPlayer {
	
	private ArrayList<Card> personalDeck = new ArrayList<Card>();
	
	//method names will be the same for both human and ai players
	//but the code within them will be different 
	
	public void getTopCard() {
		
	}
	
	public void givePlayerCard() {
		
	}
	
	public void getPlayersCatChoice() {
		
	}
	
}
