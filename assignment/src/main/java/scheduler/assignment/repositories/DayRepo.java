package scheduler.assignment.repositories;

import scheduler.assignment.data.Day;
import scheduler.assignment.interfaces.IDayRepo;

public class DayRepo implements IDayRepo { //implementation class for Day Repository

	private Day[] days; //array to store Day data
	
	public DayRepo() { //no argument constructor
		days = new Day[5];
	}
	
	public DayRepo(Day[] days) { //constructor with parameters
		this.days = days;
	}
	
	public String save(Day d) { //method to save day into repository
		if("".equals(d.getName()) || d.getName() == null) { //check if name is blank
			return "Error: The Day name should not be empty";
		} else {
			if(checkExist(d)) { //check if Day already exist
				return "Error: Day has already exist";
			}
			if(checkFilled()) { //check if array is filled (no null Day)
				addSpace(); //call addSpace method to increase length of the array
			}
			addDay(d); //call addDay method to add Day into array
			return "Success: Day has been saved successfully";
		}
	}
	
	public boolean checkExist(Day d) { //method to check if Day exist in repository
		boolean exist = false; //assuming Day does not exist in repository
		for(Day day : days) { //looping through days in repository
			if(day != null && day.getName().equals(d.getName())) { //check if Day is in repository
				exist = true;
			}
		}
		return exist;
	}
	
	public boolean checkFilled() { //method to check if array is filled (no null Day)
		boolean filled = true; //assuming array is filled
		for(Day day : days) { //looping through days in repository
			if(day == null) { //if day is null
				filled = false;
			}
		}
		return filled;
	}
	
	public void addSpace() { //method to increase length of the array
		Day[] temp = days; //store existing data into temporary array
		days = new Day[temp.length+5]; //increase length of days array by 5
		for(int i=0; i<temp.length; i++) { //looping through temporary array
			days[i] = temp[i]; //store existing data into days array with larger length
		}
	}
	
	public void addDay(Day d) { //method to add day into repository
		for(int i=0; i<days.length; i++) { //looping through days in repository
			if(days[i] == null) { //if empty slot is found
				days[i] = d; //store Day into repository
				break; //break out of loop to prevent duplicate adding
			}
		}
	}

	public Day findOne(String name) { //method to get Day using name of the day
		for(Day day : days) { //looping through days in repository
			if(day != null && day.getName().equals(name)) { //if Day matches in repository
				return day;
			}
		}
		return null;
	}

	public Day[] findAll() { //method to get all days in repository
		return days;
	}

}
