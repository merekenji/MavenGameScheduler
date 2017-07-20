package scheduler.assignment.interfaces;

import scheduler.assignment.data.Day;

public interface IDayRepo { //interface for Day Repository

	public String save(Day d); //method to save day to repository
	public Day findOne(String name); //method to find day using name of the day
	public Day[] findAll(); //method to get all days in repository
	
}
