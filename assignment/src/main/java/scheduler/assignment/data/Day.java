package scheduler.assignment.data;

public class Day { //data class for Day

	private String dayName; //name of the Day
	private Game[] games; //games that are scheduled on this Day
	
	public Day() { //default constructor
		dayName = "";
		games = null;
	}
	
	public Day(String name, Game[] games) {
		dayName = name;
		this.games = games;
	}

	public String getName() { //getter method for name
		return dayName;
	}

	public void setName(String name) { //setter method for name
		this.dayName = name;
	}

	public Game[] getGames() { //getter method for games
		return games;
	}

	public void setGames(Game[] games) { //setter method for games
		this.games = games;
	}
	
}
