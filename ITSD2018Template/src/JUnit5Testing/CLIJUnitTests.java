package JUnit5Testing;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.*;

import commandline.Card;
import commandline.Player;
import commandline.TopTrumpsCLIApplication;


public class CLIJUnitTests {
	
	public ArrayList<Card> createSampleDeck() { 
		
		ArrayList<Card> deck = new ArrayList<Card>();

		ArrayList<String> catNames = new ArrayList<String>(getCatNames());
		
		String cardName = "testCardName";
		
		for(int i = 0; i < 10; i++) {
			Card testCard = new Card(cardName, i, i, i, i, i, catNames);
			deck.add(testCard);
		}
			
		return deck;
				
	}
	
	public ArrayList<String> getCatNames(){
		ArrayList<String> catNames = new ArrayList<String>();
		catNames.add("name1");
		catNames.add("name2");
		catNames.add("name3");
		catNames.add("name4");
		catNames.add("name5");
		return catNames;
	}
	
	public ArrayList<Player> getPlayersList(){
		ArrayList<Player> players = new ArrayList<Player>();
		Player player1 = new Player();
		Player player2 = new Player();
		players.add(player1);
		players.add(player2);
		
		return players;
	}
	
	@Test
	public void distributeCardsTest() {
		//test that cards are evenly distributed.
		//we use player1 (index 0) in the assertion as they will always receive
		//the max number of cards in the event of uneven distribution
		
		ArrayList<Card> deck = new ArrayList<Card>(createSampleDeck());	
		
		ArrayList<Player> players = new ArrayList<Player>(getPlayersList());

		TopTrumpsCLIApplication.distributeCards(deck, players, 2);
		
		assertEquals(deck.size()/players.size(), players.get(0).getPersonalDeckSize());
	}
	
	
	@Test
	public void getTopCardsTestEmptyDeck() {
		//test to ensure we are removing players with no cards left
		//two players are in the players list to begin with, one has no cards
		//once the method is called the player with no cards should be removed

		
		ArrayList<Player> players = new ArrayList<Player>(getPlayersList());
				
		ArrayList<String> catNames = new ArrayList<String>(getCatNames());
		
		Card testCard = new Card("test",1,1,1,1,1, catNames);

		players.get(0).givePlayerCard(testCard);
		
		ArrayList<Card> cardSelection = new ArrayList<Card>();
		
		TopTrumpsCLIApplication.getTopCards(players, cardSelection);
				
		assertEquals(1, players.size());
	}
	
	@Test
	public void getTopCardsRegular() {
		//checks that the getTopCards method gets
		//the cards at the top of each players deck
		
		ArrayList<Player> players = new ArrayList<Player>(getPlayersList());
				
		ArrayList<String> catNames = new ArrayList<String>(getCatNames());
		
		Card topCard1 = new Card("test",1,1,1,1,1, catNames);
		Card topCard2 = new Card("test",1,1,1,1,1, catNames);
		Card bottomCard1 = new Card("test",1,1,1,1,1, catNames);
		Card bottomCard2 = new Card("test",1,1,1,1,1, catNames);

		players.get(0).givePlayerCard(topCard1);
		players.get(0).givePlayerCard(bottomCard1);
		
		players.get(1).givePlayerCard(topCard2);
		players.get(1).givePlayerCard(bottomCard2);
				
		ArrayList<Card> cardSelection = new ArrayList<Card>();

		TopTrumpsCLIApplication.getTopCards(players, cardSelection);
		
		Boolean top1Present = cardSelection.contains(topCard1);
		Boolean top2Present = cardSelection.contains(topCard2);
		
		assertTrue(top1Present && top2Present);

	}
	
	@Test
	public void testForDrawFalseTest() {
		// situation where there is not a draw
		// in the createSampleDeck method no cards are set up to allow draws
		
		ArrayList<Card> cardSelection = new ArrayList<Card>(createSampleDeck());
		int catChoice = 1;
		
		Boolean draw = TopTrumpsCLIApplication.testForDraw(cardSelection, catChoice);
		
		assertFalse(draw);
	}
	
	@Test
	public void testForDrawTrueTest() {
		// Test for the situation where there is a draw
		// The same card is given used to guarantee a draw
		
		ArrayList<Card> cardSelection = new ArrayList<Card>();
		
		ArrayList<String> catNames = new ArrayList<String>(getCatNames());
		
		Card drawCard = new Card("test",1,1,1,1,1, catNames);
		
		cardSelection.add(drawCard);
		cardSelection.add(drawCard);
		
		int catChoice = 1;
		
		Boolean draw = TopTrumpsCLIApplication.testForDraw(cardSelection, catChoice);
		
		assertTrue(draw);
	}
	
	@Test
	public void removeCardsTestSize() {
		// test to ensure that the a card in each players
		// deck is removed

		ArrayList<Card> deck = new ArrayList<Card>(createSampleDeck());	
		
		ArrayList<Player> players = new ArrayList<Player>(getPlayersList());

		TopTrumpsCLIApplication.distributeCards(deck, players, 2);
		
		int sizeBeforeRemove = players.get(0).getPersonalDeckSize();
		
		TopTrumpsCLIApplication.removeCards(players);
		
		assertEquals(sizeBeforeRemove-1, players.get(0).getPersonalDeckSize());
	}
	
	@Test
	public void removeCardsTestTop() {
		// test to ensure that the card that is removed is
		// the TOP card

		ArrayList<Player> players = new ArrayList<Player>(getPlayersList());
		
		ArrayList<String> catNames = new ArrayList<String>(getCatNames());
		
		Card topCard1 = new Card("test",1,1,1,1,1, catNames);
		Card topCard2 = new Card("test",1,1,1,1,1, catNames);
		Card bottomCard1 = new Card("test",1,1,1,1,1, catNames);
		Card bottomCard2 = new Card("test",1,1,1,1,1, catNames);

		players.get(0).givePlayerCard(topCard1);
		players.get(0).givePlayerCard(bottomCard1);
		
		players.get(1).givePlayerCard(topCard2);
		players.get(1).givePlayerCard(bottomCard2);
				
		ArrayList<Card> cardSelection = new ArrayList<Card>();

		TopTrumpsCLIApplication.getTopCards(players, cardSelection);

		
		TopTrumpsCLIApplication.removeCards(players);
		
		assertTrue(players.get(0).getTopCard() != cardSelection.get(0));

	}
	
	@Test
	public void winningIndexTest() {
		//test that the winning index returned is correct.
		
		ArrayList<Card> cardSelection = new ArrayList<Card>(createSampleDeck());
		int catChoice = 1;
		
		int winningIndex = TopTrumpsCLIApplication.getWinningIndex(cardSelection, catChoice);
		

		//due to the way the createSampleDeck method works, the last card will always win
		assertEquals(winningIndex, cardSelection.size()-1);
		
	}

}
