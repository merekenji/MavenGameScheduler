package scheduler.assignment.repositories;

import scheduler.assignment.data.Player;
import scheduler.assignment.interfaces.IPlayerRepo;

public class PlayerRepo implements IPlayerRepo { //implementation class for Player Repository

	private Player[] players; //array to store Player data

	public PlayerRepo() { //default constructor
		players = new Player[5];
	}

	public PlayerRepo(Player[] players) { //constructor with parameters
		this.players = players;
	}

	public String save(Player p) { //method to save player into repository
		if ("".equals(p.getName()) || p.getName() == null) { //if player name is empty
			return "Error: The Player name should not be empty";
		} else {
			if (checkExist(p)) { //check if Player has already exist
				return "Error: Player has already exist";
			}
			if (checkFilled()) { //check if array is filled (no null Player)
				addSpace(); //call addSpace method to increase length of the array
			}
			addPlayer(p); //call addPlayer method to add Player into repository
			return "Success: Player has been saved successfully";
		}
	}

	public boolean checkExist(Player p) { //method to check if Player exist in repository
		boolean exist = false; //assuming Player does not exist in repository
		for (Player player : players) { //looping through players in repository
			if (player != null && player.getName().equals(p.getName())) { //check if Player is in repository
				exist = true;
			}
		}
		return exist;
	}

	public boolean checkFilled() { //method to check if array is filled (no null Player)
		boolean filled = true; //assuming array is filled
		for (Player player : players) { //looping through players in repository
			if (player == null) { //if player is null
				filled = false;
			}
		}
		return filled;
	}

	public void addSpace() { //method to increase length of the array
		Player[] temp = players; //store existing data into temporary array
		players = new Player[temp.length + 5]; //increase length of players array by 5
		for (int i = 0; i < temp.length; i++) { //looping through temporary array
			players[i] = temp[i]; //store existing data into players array with larger length
		}
	}

	public void addPlayer(Player p) { //method to add player into repository
		for (int i = 0; i < players.length; i++) { //looping through players in repository
			if (players[i] == null) { //if empty slot is found
				players[i] = p; //store Player into repository
				break; //break out of loop to prevent duplicate adding
			}
		}
	}

	public Player findOne(String name) { //method to get Player using name of the player
		for (Player player : players) { //looping through players in repository
			if (player != null && player.getName().equals(name)) { //if Player matches in repository
				return player;
			}
		}
		return null;
	}

	public Player[] findAll() { //method to return all players in repository
		return players;
	}

}
