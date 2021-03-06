package scheduler.assignment.interfaces;

import scheduler.assignment.data.Day;
import scheduler.assignment.data.Game;
import scheduler.assignment.data.Player;

public interface ISchedulerService { //interface for Scheduler Service

	public String createGame(Game g); //method to create game
	public String createPlayer(Player p); //method to create player
	public String createDay(Day d); //method to create day
	public StringBuilder gameWiseReport(String gameName); //method to generate game wise report
	public StringBuilder playerWiseReport(String playerName); //method to generate player wise report
	public StringBuilder dayWiseReport(String dayName); //method to generate day wise report
	
}
