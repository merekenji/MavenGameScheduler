package scheduler.assignment.data;

public class Player { //data class for Player

	private String playerName; //name of player
	private Game[] games; //games the player is playing in
	
	public Player() { //default constructor
		playerName = "";
		games = null;
	}
	
	public String getName() { //getter method for name
		return playerName;
	}

	public void setName(String name) { //setter method for name
		this.playerName = name;
	}

	public Game[] getGames() { //getter method for games
		return games;
	}

	public void setGames(Game[] games) { //setter method for games
		this.games = games;
	}
	
}
