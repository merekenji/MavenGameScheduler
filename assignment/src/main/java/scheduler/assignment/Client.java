package scheduler.assignment;

import java.util.logging.Level;
import java.util.logging.Logger;

import scheduler.assignment.data.Day;
import scheduler.assignment.data.Game;
import scheduler.assignment.data.Player;
import scheduler.assignment.interfaces.ISchedulerService;

public class Client {
	
	private static final Logger LOGGER = Logger.getLogger(Client.class.getName());
	
	private Client() {
		//This is the main program
	}

	public static void main(String[] args) { //main method
		ISchedulerService service = new SchedulerService(); 
		
		Game game1 = new Game();
		game1.setName("Football");
		game1.setNoOfPlayers(22);
		Game game2 = new Game();
		game2.setName("Basketball");
		game2.setNoOfPlayers(22);
		Game game3 = new Game();
		game3.setName("Tennis");
		game3.setNoOfPlayers(2);
		
		service.createGame(game1);
		service.createGame(game2);
		service.createGame(game3);
		
		Game[] games1 = {game1, game2};
		Game[] games2 = {game2, game3};
		
		Player player1 = new Player();
		player1.setName("Tom");
		player1.setGames(games1);
		Player player2 = new Player();
		player2.setName("Jerry");
		player2.setGames(games2);
		
		service.createPlayer(player1);
		service.createPlayer(player2);
		
		Day day1 = new Day();
		day1.setName("Day One");
		day1.setGames(games1);
		Day day2 = new Day();
		day2.setName("Day Two");
		day2.setGames(games2);
		
		service.createDay(day1);
		service.createDay(day2);
		
		LOGGER.log(Level.INFO, service.gameWiseReport("Basketball").toString());
		LOGGER.log(Level.INFO, service.playerWiseReport("Tom").toString());
		LOGGER.log(Level.INFO, service.dayWiseReport("Day Two").toString());
	}

}
