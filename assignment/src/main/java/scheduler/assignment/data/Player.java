package scheduler.assignment.data;

public class Player { //data class for Player

	private String name; //name of player
	private Game[] games; //games the player is playing in
	
	public Player() { //default constructor
		name = "";
		games = null;
	}
	
	public Player(String name, Game[] games) { //constructor with parameters
		this.name = name;
		this.games = games;
	}

	public String getName() { //getter method for name
		return name;
	}

	public void setName(String name) { //setter method for name
		this.name = name;
	}

	public Game[] getGames() { //getter method for games
		return games;
	}

	public void setGames(Game[] games) { //setter method for games
		this.games = games;
	}
	
}
