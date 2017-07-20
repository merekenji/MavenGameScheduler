package scheduler.assignment.repositories;

import scheduler.assignment.data.Game;
import scheduler.assignment.interfaces.IGameRepo;

public class GameRepo implements IGameRepo { //implementation class for Game Repository
	
	private Game[] games; //array to store Game data
	
	public GameRepo() { //default constructor
		games = new Game[5];
	}
	
	public GameRepo(Game[] games) { //constructor with parameters
		this.games = games;
	}
	
	public String save(Game g) { //method to save game into repository
		if(g == null) { //if Game is null
			return "Error: The Game object is null";
		}
		if("".equals(g.getName()) || g.getName() == null) { //if game name is empty
			return "Error: The Game name should not be empty";
		}
		if(g.getNoOfPlayers() <= 0) { //if number of players is less or equal to 0
			return "Error: There should at least be 1 player playing in the game";
		}
		if(checkExist(g)) { //check if Game has already exist
			return "Error: Game has already exist";
		}
		if(checkFilled()) { //check if array is filled (no null Game)
			addSpace(); //call addSpace method to increase length of the array
		}
		addGame(g); //call addGame method to add Game into repository
		return "Success: Game has been saved successfully";
	}
	
	public boolean checkExist(Game g) { //method to check if Game exist in repository
		boolean exist = false; //assuming Game does not exist in repository
		for(Game game : games) { //looping through games in repository
			if(game != null && game.getName().equals(g.getName())) { //check if Game is in repository
				exist = true;
			}
		}
		return exist;
	}
	
	public boolean checkFilled() { //method to check if array is filled (no null Game)
		boolean filled = true; //assuming array is filled
		for(Game game : games) { //looping through games in repository
			if(game == null) { //if game is null
				filled = false;
			}
		}
		return filled;
	}
	
	public void addSpace() { //method to increase length of the array
		Game[] temp = games; //store existing data into temporary array
		games = new Game[temp.length+5]; //increase length of games array by 5
		for(int i=0; i<temp.length; i++) { //looping through temporary array
			games[i] = temp[i]; //store existing data into games array with larger length
		}
	}
	
	public void addGame(Game g) { //method to add game into repository
		for(int i=0; i<games.length; i++) { //looping through games in repository
			if(games[i] == null) { //if empty slot is found
				games[i] = g; //store Game into repository
				break; //break out of loop to prevent duplicate adding
			}
		}
	}

	public Game findOne(String name) { //method to get Game using name of the game
		for(Game game : games) { //looping through games in repository
			if(game != null && game.getName().equals(name)) { //if Game matches in repository
				return game;
			}
		}
		return null;
	}

	public Game[] findAll() { //method to return all games in repository
		return games;
	}

}
