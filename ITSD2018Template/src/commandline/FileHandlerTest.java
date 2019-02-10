package commandline;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FileHandlerTest {
	private static ArrayList<Card> cardSelection = new ArrayList<Card>();
	
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		FileHandler filehandler = new FileHandler();
		filehandler.getFileData();
		cardSelection = filehandler.getDeck();
	}


	@Test
	void testTopCard() {
		// test top card name 
		assertEquals("350r", cardSelection.get(0).getCardName(), "Test card name");
		// test top card details from test deck
		assertEquals(1, cardSelection.get(0).getCat1Value(), "Test value 1");
		assertEquals(9, cardSelection.get(0).getCat2Value(), "Test value 2");
		assertEquals(2, cardSelection.get(0).getCat3Value(), "test value 3");
		assertEquals(3, cardSelection.get(0).getCat4Value(), "Test value 4");
		assertEquals(0, cardSelection.get(0).getCat5Value(), "Test value 5");
		
	}

	@Test
	void testDeckSize() {
		// check if deck is empty
		if(cardSelection.isEmpty()) {
			fail("The deck is empty");
		}
		// test deck "StarCitizenDeck" is 40 cards in size
		if(cardSelection.size() == 40) {
			assert true;
		}else {
			assert false;
			if(cardSelection.size()< 40) {
				System.out.println("Not all cards loaded");
			}else {
				System.out.println("Trying to load too many cards");
			}
		}
		
	}
	
	@Test
	void testLastcard() {
		// test last card name 
		assertEquals("Avenger", cardSelection.get(39).getCardName(), "Test card name");
		// test last card details from test deck
		assertEquals(2, cardSelection.get(39).getCat1Value(), "Test value 1");
		assertEquals(5, cardSelection.get(39).getCat2Value(), "Test value 2");
		assertEquals(4, cardSelection.get(39).getCat3Value(), "test value 3");
		assertEquals(3, cardSelection.get(39).getCat4Value(), "Test value 4");
		assertEquals(2, cardSelection.get(39).getCat5Value(), "Test value 5");
		
	}
	
	

}
