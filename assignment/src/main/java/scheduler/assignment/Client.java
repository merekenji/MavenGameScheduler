package scheduler.assignment;

import scheduler.assignment.data.Day;
import scheduler.assignment.data.Game;
import scheduler.assignment.data.Player;
import scheduler.assignment.interfaces.ISchedulerService;

public class Client {
	
	private Client() {
		//This is the main program
	}

	public static void main(String[] args) { //main method
		ISchedulerService service = new SchedulerService(); 
		
		Game game1 = new Game("Football", 22);
		Game game2 = new Game("Basketball", 10);
		Game game3 = new Game("Tennis", 2);
		
		service.createGame(game1);
		service.createGame(game2);
		service.createGame(game3);
		
		Game[] games1 = {game1, game2};
		Game[] games2 = {game2, game3};
		
		Player player1 = new Player("Tom", games1);
		Player player2 = new Player("Jerry", games2);
		
		service.createPlayer(player1);
		service.createPlayer(player2);
		
		Day day1 = new Day("Day One", games1);
		Day day2 = new Day("Day Two", games2);
		
		service.createDay(day1);
		service.createDay(day2);
		
		System.out.println(service.gameWiseReport("Basketball").toString());
		System.out.println(service.playerWiseReport("Tom").toString());
		System.out.println(service.dayWiseReport("Day Two").toString());
	}

}
