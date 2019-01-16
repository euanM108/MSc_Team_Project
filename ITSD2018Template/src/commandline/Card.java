package commandline;

import java.util.ArrayList;

public class Card {
	private String cardName;
	private int cat1Value;
	private int cat2Value;
	private int cat3Value;
	private int cat4Value;
	private int cat5Value;
	

	
	public Card(String cardName, int value1, int value2, int value3, int value4, int value5) {
		
		// This will contain constructor info
		// to form new card object
		this.cardName = cardName;
		this.cat1Value = value1;
		this.cat2Value = value2;
		this.cat3Value = value3;
		this.cat4Value = value4;
		this.cat5Value = value5;
	}
	
	public String getCardName() {
		return cardName;
	}

	public int getCat1Value() {
		return cat1Value;
	}

	public int getCat2Value() {
		return cat2Value;
	}

	public int getCat3Value() {
		return cat3Value;
	}

	public int getCat4Value() {
		return cat4Value;
	}

	public int getCat5Value() {
		return cat5Value;
	}


	
	

}
