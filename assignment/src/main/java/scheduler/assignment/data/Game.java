package scheduler.assignment.data;

public class Game { //data class for Game
	
	private String name; //name of Game
	private int noOfPlayers; //number of players to play this game
	
	private Game() { //default constructor
		name = "";
		noOfPlayers = 0;
	}
	
	public Game(String name, int noOfPlayers) { //constructor with parameters
		this.name = name;
		this.noOfPlayers = noOfPlayers;
	}

	public String getName() { //getter method for name
		return name;
	}

	public void setName(String name) { //setter method for name
		this.name = name;
	}

	public int getNoOfPlayers() { //getter method for noOfPlayers
		return noOfPlayers;
	}

	public void setNoOfPlayers(int noOfPlayers) { //setter method for noOfPlayers
		this.noOfPlayers = noOfPlayers;
	}
	
}
