package SharedFunctionality;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer {

	private int numberOfPlayers;
	private ArrayList<Card> deck = new ArrayList<Card>();
	//test
	public Dealer(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
		System.out.println("Dealer created with " + numberOfPlayers + " players.");

		/*
		 * TODO:file reader to populate 'deck' shuffle deck send deck to players
		 */

	}

	public void requestCategory() {

	}

	public void sendGameLogStats() {

	}

	public List<Integer> shuffleCards(List<Integer> deck) {

		Collections.shuffle(deck);

		for (int i = 0; i < 6; i++) {
			System.out.println("after: " + deck.get(i));
		}

		return deck;
	}
}
