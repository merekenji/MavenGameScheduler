package scheduler.assignment.data;

public class Day { //data class for Day

	private String name; //name of the Day
	private Game[] games; //games that are scheduled on this Day
	
	public Day() { //default constructor
		name = "";
		games = null;
	}
	
	public Day(String name, Game[] games) { //constructor with parameter
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
