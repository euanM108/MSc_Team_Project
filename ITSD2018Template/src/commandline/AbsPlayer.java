package commandline;

import java.util.ArrayList;

//this will contain stuff that the AI player and human will complain

public abstract class AbsPlayer {
		
	private int playerID;
//	private static int playerIDCount = 1;
	
	public AbsPlayer() {
//		playerID = playerIDCount;
//		playerIDCount++;
	}
	
	public int getPlayerID() {
		return playerID;
	}
	
	//method names will be the same for both human and ai players
	//but the code within them will be different 
	
	protected abstract int getPlayersCatChoice();
	
	protected abstract Card getTopCard();

	protected abstract void removeTopCard();
	//protected abstract int turn();

	protected abstract void givePlayerCard(Card card);
	
}
