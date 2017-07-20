package scheduler.assignment.data;

public class Game { //data class for Game
	
	private String gameName; //name of Game
	private int noOfPlayers; //number of players to play this game
	
	public Game() { //default constructor
		gameName = "";
		noOfPlayers = 0;
	}
	
	public Game(String name, int noOfPlayers) {
		gameName = name;
		this.noOfPlayers = noOfPlayers;
	}

	public String getName() { //getter method for name
		return gameName;
	}

	public void setName(String name) { //setter method for name
		this.gameName = name;
	}

	public int getNoOfPlayers() { //getter method for noOfPlayers
		return noOfPlayers;
	}

	public void setNoOfPlayers(int noOfPlayers) { //setter method for noOfPlayers
		this.noOfPlayers = noOfPlayers;
	}
	
}
