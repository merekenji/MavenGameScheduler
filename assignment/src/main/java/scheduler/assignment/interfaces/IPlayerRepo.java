package scheduler.assignment.interfaces;

import scheduler.assignment.data.Player;

public interface IPlayerRepo { //interface for Player Repository

	public String save(Player p); //method to save player to repository
	public Player findOne(String name); //method to find Player using name of the player
	public Player[] findAll(); //method to get all players in repository
	
}
