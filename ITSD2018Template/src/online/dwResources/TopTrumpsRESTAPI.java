package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import commandline.Card;
import commandline.DatabaseCommunication;
import commandline.FileHandler;
import commandline.Player;
import online.configuration.TopTrumpsJSONConfiguration;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands to
 * the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create REST API
 * methods in Dropwizard. You will need to replace these with methods that allow
 * a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {
	private static ArrayList<Card> deck = new ArrayList<Card>();
	
	private int numberOfPlayers;
	private ArrayList<Player> players;
	private ArrayList<Card> cardSelection = new ArrayList<Card>();
	private ArrayList<Card> communalPile = new ArrayList<Card>();
	private static String categories;
	private int winnerID;
	private int winningIndex = 0; // current winning index
	private int catChoice = 0; // current category choice
	private int currentRoundNumber = 1;
	private boolean hasPlayerSubmitted;
	private boolean draw = false;

	
	//variables used in database
	private static int draws;
	private static int noRounds;
	private static int playerWins;
	private static int AI1Wins;
	private static int AI2Wins;
	private static int AI3Wins;
	private static int AI4Wins;
	
	
	
	/**
	 * A Jackson Object writer. It allows us to turn Java objects into JSON strings
	 * easily.
	 */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

	/**
	 * Contructor method for the REST API. This is called first. It provides a
	 * TopTrumpsJSONConfiguration from which you can get the location of the deck
	 * file and the number of AI players.
	 * 
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
			
	}

	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------

	@GET
	@Path("/helloJSONList")
	/**
	 * Here is an example of a simple REST get request that returns a String. We
	 * also illustrate here how we can convert Java objects to JSON strings.
	 * 
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String helloJSONList() throws IOException {

		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add("Hello");
		listOfWords.add("World!");

		// We can turn arbatory Java objects directly into JSON strings using
		// Jackson seralization, assuming that the Java objects are not too complex.
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}

	@GET
	@Path("/launchGame")
	/**
	 * Setting up the game when game launched
	 */
	public void launchGame() throws IOException {
		DatabaseCommunication.connectToDatabase();
	}
	
	@GET
	@Path("/getRoundNumber")
	public String getRoundNumber() throws IOException {
		return ""+currentRoundNumber;
	}
	
	
	@GET
	@Path("/numberOfPlayers")
	/**
	 * Setting the number of players
	 */
	public String numberOfPlayers(@QueryParam("Number") String Number) throws IOException {
		currentRoundNumber=1;
		ArrayList<Player> players = new ArrayList<Player>();
		players.clear();
		for (int i=0; i<players.size(); i++) {
			players.get(i).getPersonalDeck().clear();
		}
		Player.resetPlayerIDCount();
		// cast number received as an int
		numberOfPlayers = Integer.parseInt(Number);

		// create the players
		Player human = new Player();
		players.add(human);
		for (int i = 0; i < numberOfPlayers; i++) {
			Player AIPlayer = new Player();
			players.add(AIPlayer);
		}
		this.players = players;
		// Commented out until gameplay is complete
		// Collections.shuffle(players);
		return "total number of players is " + numberOfPlayers;
	}
	
	@GET
	@Path("/getDeck")
	// Method that will return deck 
	public ArrayList<Card> getDeck()throws IOException{
		FileHandler fl = new FileHandler();
		// Create File Handler reference
		// to create deck, players and shuffle
		fl.getFileData();
		deck = fl.getDeck();
		categories = fl.getCats();
		//Collections.shuffle(deck);

		return deck;
	}
		
	@GET
	@Path("/getPlayers")
	public ArrayList<Player> getPlayers() throws IOException{
		return players;
	}
	
	@GET
	@Path("/distributeCards")
	public String distributeCards() throws IOException{	
		for (int i = 0; i < players.size(); i++) {
			this.players.get(i).getPersonalDeck().clear();
		}
		
		for (int i = 0; i < deck.size(); i++) {
			this.players.get(i % players.size()).givePlayerCard(deck.get(i));
			System.out.println(players.get(i % players.size()).getPlayerID() + " has been given " + deck.get(i));
		}
		
		return "cards distributed";
	}
	

	@GET
	@Path("/getCardName")
	public String getCardName() throws IOException{
		if (players.get(0).getPersonalDeckSize() > 0) {
			return players.get(0).getTopCard().getCardName();
		}
		else {
			return "removed";
		}
	}
	
	@GET
	@Path("/getPlayer1CardValue")
	public int getPlayer1CardValue(@QueryParam("i") String i) throws IOException {
		if (players.get(0).getPersonalDeckSize() > 0) {
			int category = Integer.parseInt(i);
		
			if (category == 1) {
				return players.get(0).getTopCard().getCat1Value();
			}
			else if (category ==2) {
				return players.get(0).getTopCard().getCat2Value();
			}
			else if (category ==3) {
				return players.get(0).getTopCard().getCat3Value();
			}
			else if(category ==4) {
				return players.get(0).getTopCard().getCat4Value();
			}
			else if(category ==5) {
				return players.get(0).getTopCard().getCat5Value();
			}
		}
		return 0;
	}
	
	@GET
	@Path("/getPlayer2CardName")
	public String getPlayer2CardName() throws IOException{
		if (players.get(1).getPersonalDeckSize() > 0) {
			return players.get(1).getTopCard().getCardName();
		}
		else {
			return "removed";
		}
	}
	
	
	@GET
	@Path("/getPlayer2CardValue")
	public int getPlayer2CardValue(@QueryParam("i") String i) throws IOException {
		if (players.get(1).getPersonalDeckSize() > 0) {
			int category = Integer.parseInt(i);
			
			if (category == 1) {
				return players.get(1).getTopCard().getCat1Value();
			}
			else if (category ==2) {
				return players.get(1).getTopCard().getCat2Value();
			}
			else if (category ==3) {
				return players.get(1).getTopCard().getCat3Value();
			}
			else if(category ==4) {
				return players.get(1).getTopCard().getCat4Value();
			}
			else if(category ==5) {
				return players.get(1).getTopCard().getCat5Value();
			}
		}
		return 0;
	}
	

	@GET
	@Path("/getPlayer3CardName")
	public String getPlayer3CardName() throws IOException{
		if (players.get(2).getPersonalDeckSize() > 0) {
			return players.get(2).getTopCard().getCardName();
		}
		return "removed";
	}
	
	@GET
	@Path("/getCatDescriptions")
	public String getCategories() throws IOException{
		return categories;
	}
	
	@GET
	@Path("/getPlayer3CardValue")
	public int getPlayer3CardValue(@QueryParam("i") String i) throws IOException {
		if (players.get(2).getPersonalDeckSize() > 0) {
			int category = Integer.parseInt(i);
		
			if (category == 1) {
				return players.get(2).getTopCard().getCat1Value();
			}
			else if (category ==2) {
				return players.get(2).getTopCard().getCat2Value();
			}
			else if (category ==3) {
				return players.get(2).getTopCard().getCat3Value();
			}
			else if(category ==4) {
				return players.get(2).getTopCard().getCat4Value();
			}
			else if(category ==5) {
				return players.get(2).getTopCard().getCat5Value();
			}
		}
		return 0;
	}
	

	@GET
	@Path("/getPlayer4CardName")
	public String getPlayer4CardName() throws IOException{
		if (players.get(3).getPersonalDeckSize() > 0) {
			return players.get(3).getTopCard().getCardName();
		}
		return "removed";
	}
	
	@GET
	@Path("/getPlayer4CardValue")
	public int getPlayer4CardValue(@QueryParam("i") String i) throws IOException {
		if (players.get(3).getPersonalDeckSize() > 0) {
			int category = Integer.parseInt(i);
		
			if (category == 1) {
				return players.get(3).getTopCard().getCat1Value();
			}
			else if (category ==2) {
				return players.get(3).getTopCard().getCat2Value();
			}
			else if (category ==3) {
				return players.get(3).getTopCard().getCat3Value();
			}
			else if(category ==4) {
				return players.get(3).getTopCard().getCat4Value();
			}
			else if(category ==5) {
				return players.get(3).getTopCard().getCat5Value();
			}
		}
		return 0;
	}
	
	
	
	
	@GET
	@Path("/getPlayer5CardName")
	public String getPlayer5CardName() throws IOException{
		if (players.get(4).getPersonalDeckSize() > 0) {
			return players.get(4).getTopCard().getCardName();
		}
		return "removed";
	}
	
	
	@GET
	@Path("/getPlayer5CardValue")
	public int getPlayer5CardValue(@QueryParam("i") String i) throws IOException {
		if (players.get(4).getPersonalDeckSize() > 0) {
			int category = Integer.parseInt(i);
				
			if (category == 1) {
				return players.get(4).getTopCard().getCat1Value();
			}
			else if (category ==2) {
				return players.get(4).getTopCard().getCat2Value();
			}
			else if (category ==3) {
				return players.get(4).getTopCard().getCat3Value();
			}
			else if(category ==4) {
				return players.get(4).getTopCard().getCat4Value();
			}
			else if(category ==5) {
				return players.get(4).getTopCard().getCat5Value();
			}
		}
		return 0;
	}
	
	
	@GET
	@Path("/submit_category")
	public void submit_category(@QueryParam("categoryChoice") String categoryChoice) {
		
		catChoice = Integer.parseInt(categoryChoice);
		
		hasPlayerSubmitted = true;
		
		if (players.get(winningIndex).getPlayerID() != 1) {
			catChoice = players.get(winningIndex).getAIPlayersCatChoice();
			System.out.println("\n\n\n\n\n");
			printCatSelectedStatement(players, catChoice, winningIndex);
			System.out.println("\n\n\n\n\n");
			
		} 	else {			
			System.out.println("\n\n\n\n\n");
			// need to wait for players category submission
			printCatSelectedStatement(players, catChoice, winningIndex);
			System.out.println("\n\n\n\n\n");
		}
		
		draw = testForDraw(cardSelection, catChoice);
		
		if (draw) {
			
			// if the round is a draw
			// winning index stays the same
			// cards are moved to communal pile
			draws++; //add 1 to the count of draws for the database
			for (Card tempCard : cardSelection) {
				communalPile.add(tempCard);
			}
			printDraw(players, catChoice, winningIndex);

		} else {
			// if there is not a draw
			winningIndex = getWinningIndex(cardSelection, catChoice);
			winnerCount(winningIndex, players); //calls the method to increment the database counter for the winner

			// Giving the winner the cards they won for this round
			for (int i = 0; i < cardSelection.size(); i++) {
				players.get(winningIndex).givePlayerCard(cardSelection.get(i));
			}
			
			//if there are cards in the communalPile give the player those
			if(communalPile.size() > 0) {
				for(int i = 0; i < communalPile.size(); i++){
					players.get(winningIndex).givePlayerCard(communalPile.get(i));
				}
				communalPile.clear();
			}
		}
		
		
//		if(cardSelection.size() > 0) {
//			for(int i = 0; i < cardSelection.size(); i++){
//				players.get(winningIndex).givePlayerCard(cardSelection.get(i));
//			}
//			cardSelection.clear();			
//		}
		cardSelection.clear();
		// remove top cards after round is finished
		removeCards(players);
		
		System.out.println("winning index is : " + winningIndex);
		// check players submission against others here
	}
	
	
	private static void winnerCount(int winningIndex, ArrayList<Player> players) {
		int roundWinnerID = players.get(winningIndex).getPlayerID(); //get the ID of the winning player
		
		if(roundWinnerID == 1) {
			playerWins++;
		}else if(roundWinnerID == 2) {
			AI1Wins++;
		}else if(roundWinnerID == 3) {
			AI2Wins++;
		}else if(roundWinnerID == 4){
			AI3Wins++;
		}else {
			AI4Wins++;
		}
	}
	
	private static void printDraw(ArrayList<Player> players, int catChoice, int winningIndex) {
		System.out.println("\n\n\n\n");
		System.out.println("Player " + players.get(winningIndex).getPlayerID() + " has selected "+ getCategory(catChoice) + ". Multiple"
				+ " player's cards had the winning value so the round is a draw.");
		System.out.println("The cards have been addedd to the communal pile for the next winner!");
		System.out.println("\n\n\n\n");
	}
	
	private static void removeCards(ArrayList<Player> players) {
		for (int i = 0; i < players.size(); i++) {
			try {if (players.get(i).getPersonalDeckSize() > 0) {
				players.get(i).removeTopCard();
			}
			} catch (IndexOutOfBoundsException e) {

			}
		}
	}
	
	
	private static boolean testForDraw(ArrayList<Card> cardSelection, int catChoice) {
		int winningValue = 0;
		int numberOfHighCards = 0; // counts the number of times the highest cat is seen

		// find the winning Value
		for (int j = 0; j < cardSelection.size(); j++) {
			if (cardSelection.get(j).getRequestedCat(catChoice) > winningValue) {
				winningValue = cardSelection.get(j).getRequestedCat(catChoice);
			}
		}

		// check if the winning value occurs more than once
		for (int i = 0; i < cardSelection.size(); i++) {
			if (cardSelection.get(i).getRequestedCat(catChoice) == winningValue) {
				numberOfHighCards++;
			}
		}

		if (numberOfHighCards > 1) {
			return true;
		} else {
			return false;
		}
	}
	
	@GET
	@Path("/getRoundWinner")
	public String getRoundWinner() throws IOException{
		
		if (players.get(winningIndex).getPlayerID()==1) {
			return "You won round " + (currentRoundNumber -1) + "! You get to choose the category!";
		}
		else {
		return "Player " + players.get(winningIndex).getPlayerID() +" was the winner of round " + (currentRoundNumber -1) + ". Click to reveal the winner of round " + currentRoundNumber + "!";
		}
		
	}
	
	@GET
	@Path("/getWinningIndex")
	public int getWinningIndex() throws IOException{
		return winningIndex;
	}
	
	@GET
	@Path("/nextRound")
	public void nextRound() throws IOException{
			cardSelection = getTopCards(this.players);
	}
	
	@GET
	@Path("/getHumanCardCount")
	public String getHumanCardCount() throws IOException{
	
		int humanCardCount = 0;
		humanCardCount = players.get(0).getPersonalDeck().size();
		if (humanCardCount == 0) {
			return "outTheGame";
		}
		return ""+humanCardCount;
	}
	
	@GET
	@Path("/getP2CardCount")
	public String getP2CardCount() throws IOException{
	
		int cardCount2 = 0;
		cardCount2 = players.get(1).getPersonalDeck().size();
		return ""+cardCount2;
	}
	
	@GET
	@Path("/getP3CardCount")
	public String getP3CardCount() throws IOException{
	
		int cardCount3 = 0;
		cardCount3 = players.get(2).getPersonalDeck().size();
		return ""+cardCount3;
	}
	
	@GET
	@Path("/getP4CardCount")
	public String getP4CardCount() throws IOException{
	
		int cardCount4 = 0;
		cardCount4 = players.get(3).getPersonalDeck().size();
		return ""+cardCount4;
	}
	
	@GET
	@Path("/getPlayersDeckSize")
	public String getPlayersDeckSize(@QueryParam("i") String i) throws IOException{
		int index = Integer.parseInt(i);
		return ""+players.get(index).getPersonalDeckSize();
		
	}
	
	@GET
	@Path("/getP5CardCount")
	public String getP5CardCount() throws IOException{
	
		int cardCount5 = 0;
		cardCount5 = players.get(4).getPersonalDeck().size();
		return ""+cardCount5;
	}
	
	
	@GET
	@Path("/finishGame")
	public void finishGame() throws IOException {
		while ((!checkForOverallGameWin(players))) {
			submit_category("0");
	
			nextRound();

			checkForGameWin();
		}
		
	
	}
	
	
	private int getWinningIndex(ArrayList<Card> cardSelection, int catChoice) {
		int winningValue = 0;
		int winningIndex = 0;
		
		for (int j = 0; j < players.size(); j++) {
			if (players.get(j).getPersonalDeckSize() > 0) {
				if (players.get(j).getTopCard().getRequestedCat(catChoice) > winningValue) {
					winningValue = players.get(j).getTopCard().getRequestedCat(catChoice);
					winningIndex = j;
				}
				else if (players.get(j).getTopCard().getRequestedCat(catChoice) == winningValue) {
					
				}
			}
		}
		return winningIndex;
	}
	
	
	
	private static void printCatSelectedStatement(ArrayList<Player> players, int catChoice, int winningIndex) {
		System.out.println("Player " + players.get(winningIndex).getPlayerID() + " has selected "
				+ getCategory(catChoice) + " at " + players.get(winningIndex).getTopCard().getRequestedCat(catChoice));
	}
	
	private static String getCategory(int i) {
		String category = "";
		switch (i) {
		case 1:
			category = "Size";
			break;
		case 2:
			category = "Speed";
			break;
		case 3:
			category = "Range";
			break;
		case 4:
			category = "Firepower";
			break;
		case 5:
			category = "Cargo";
			break;

		}
		return category;
	}
	
	
	
	private static boolean checkForOverallGameWin(ArrayList<Player> players) {
		int count = 0;
		int winner = -1;
		for (int i=0; i<players.size(); i++) {
			if (players.get(i).getPersonalDeckSize() > 0) {
				count++;
				winner = i;
			}
		}
		
		if (count ==1) {
			System.out.println("\n\n\n\n\n");
			System.out.println("Player " + players.get(winner).getPlayerID() + " has won the game.");
			System.out.println("\n\n\n\n\n");
			return true;
		}
		count = 0;
		return false;
	}
	
	private ArrayList<Card> getTopCards(ArrayList<Player> players) {
		currentRoundNumber++;
		ArrayList<Card> cardSelection = new ArrayList<Card>();
		try {
			// running through each player and storing their top card in the dealers deck
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getPersonalDeckSize() > 0) {
					cardSelection.add(players.get(i).getTopCard()); // adding to cardSelection
				} else {
					// if they do not have a top card, they are removed from the game
					System.out
							.println("Player " + players.get(i).getPlayerID() + " has been removed from the game.");
		//		    players.remove(i);
					
				}
				
				if (checkForOverallGameWin(players)) {
					// FINISH GAME AND DO FINAL STUFF HERE
					
					// write to database
					// notify winner 
					
				}
			}
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return cardSelection;
	} 
	
	@GET
	@Path("/checkForGameWin")
	public boolean checkForGameWin() throws IOException {
			if (checkForOverallGameWin(players)) {
				return true;
			}
		return false;
	}
	
	
	@GET
	@Path("/helloWord")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * 
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String helloWord(@QueryParam("Word") String Word) throws IOException {
		return "Hello " + Word;
	}
	
	///******** Database API methods ********///
	@GET
	@Path("/getTotalGames")
	public String getTotalGames() throws IOException {
		//int games = DatabaseCommunication.getNoGames();
		int games = 6;
		return ""+games;
	}
	
	@GET
	@Path("/getCompWins")
	public String getCompWins() throws IOException {
		//int AIwins = DatabaseCommunication.getNoAIWins();
		int AIwins = 7;
		return ""+AIwins;
	}
	
	@GET
	@Path("/getHumanWins")
	public String getHumanWins() throws IOException {
		//int humanWins = DatabaseCommunication.getNoHumanWins();
		int humanWins = 8;
		return ""+humanWins;
	}
	
	@GET
	@Path("/getAveDraws")
	public String getAveDraws() throws IOException {
		//double drawAvg = DatabaseCommunication.getAVGDraws();
		double drawAvg = 9;
		return ""+drawAvg;
	}
	
	@GET
	@Path("/getBigRound")
	public String getBigRound() throws IOException {
		//int roundMax = DatabaseCommunication.getLargestNoRounds();
		int roundMax = 10;
		return ""+roundMax;
	}
	
	@GET
	@Path("/wipeDatabase")
	public void wipeDatabase() throws IOException {
		DatabaseCommunication.clearHistory();
	}

}
