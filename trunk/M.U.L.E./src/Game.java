
public class Game {

	private Drive drive;
	private Start start;
	private Map map;
	private Player[] players;
	private GameState gState;
	private TurnState tState;
	
	private int currentPlayer;
	private int numOfTurn;
	
	public enum GameState {LandGrant, LandPurchase, PlayerTurns};
	public enum TurnState {};
	
	private boolean passLandPurchase = true;
	
	/**
	 * This is the constructor for Game class
	 * @param d is the one instance of drive class
	 */
	public Game(Drive d){
		drive = d;
		start = new Start(this);
		d.add(start);
	}
	
	/**
	 * This method sets up the map
	 */
	public void setUpMap(){
		String mapType = start.getMapType();
		players = start.getPlayers();
		map = new Map(this, mapType);
		drive.removeAll();
		drive.add(map);
		currentPlayer = 0;
		numOfTurn = 0;
		gState = GameState.LandGrant;
	}
	
	/**
	 * This method return current player
	 * @return the player in current turn
	 */
	public Player getCurrentPlayer(){
		return players[currentPlayer];
	}
	
	/**
	 * This method is used to do land purchase, price of the land will be decided by current game state, 0 for land grand,
	 * 300 for land purchase period, and during players' turns, which means that player buy land from land office, the price
	 * of the land is decided by game turn, and at last it will return a boolean value to indicate whether the deal is successful
	 * @param land is the tile selected by player;
	 */
	public boolean playerBuyLand(Tile land){
		Player currentPlayer = getCurrentPlayer();
		int landprice = 0;
		if(gState == GameState.LandPurchase) landprice = 300;
		else if(gState == GameState.PlayerTurns) landprice = 300;// need to be changed later 
		return currentPlayer.buyLand(landprice, land);
	}
	
	/**
	 * This method decided what to do after player click mouse
	 * @param land is the tile people clicked on
	 */
	public void mouseClicked(Tile land){
		if(gState == GameState.LandGrant){
			if(playerBuyLand(land)){
				nextPlayer();
			}
		}else if(gState == GameState.LandPurchase){
			if(playerBuyLand(land)){
				passLandPurchase = false;
				nextPlayer();
			}else if(land.getClass() == TownT.class){
				nextPlayer();
			}
		}
	}
	
	/**
	 * This method will be called, when one player finishes his turn, and process to next player
	 */
	private void nextPlayer(){
		currentPlayer ++;
		if(currentPlayer == players.length){
			currentPlayer = 0;
			nextTurn();
		}
	}
	
	/**
	 * This method will be called, after all players finish their turns in a round, and process to next round
	 */
	private void nextTurn(){
		numOfTurn ++;
		if(gState == GameState.LandGrant){
			if(numOfTurn == 2) nextGameState();
		}else if(gState == GameState.LandPurchase){
			if(passLandPurchase) nextGameState();
			else passLandPurchase = true;
		}else if(gState == GameState.PlayerTurns){
			if(numOfTurn == 12){
				// end of game!
			}
		}
	}
	
	/**
	 * This method will be called if the game should move on to next game state.
	 */
	private void nextGameState(){
		numOfTurn = 0;
		if(gState == GameState.LandGrant) gState = GameState.LandPurchase;
		else if(gState == GameState.LandPurchase) gState = GameState.PlayerTurns;
	}
}
