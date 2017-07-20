package scheduler.assignment.interfaces;

import scheduler.assignment.data.Game;

public interface IGameRepo { //interface for Game Repository

	public String save(Game g); //method to save game to repository
	public Game findOne(String name); //method to find Game using name of the day
	public Game[] findAll(); //method to get all games in repository
	
}
