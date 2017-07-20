package scheduler.assignment;

import static org.junit.Assert.*;
import org.junit.Test;

import scheduler.assignment.data.Day;
import scheduler.assignment.data.Game;
import scheduler.assignment.data.Player;
import scheduler.assignment.interfaces.ISchedulerService;

public class SchedulerTest {
	
	private static final String TENNIS = "Tennis";
	private static final String BASKETBALL = "Basketball";
	private static final String GOLF = "Golf";
	private static final String FOOTBALL = "Football";
	private static final String BADMINTON = "Badminton";
	
	private static final String TOM = "Tom";
	private static final String JERRY = "Jerry";
	
	private static final String DAYONE = "Day One";
	private static final String DAYTWO = "Day Two";

	@Test
	public void createGameSuccessfully() {
		ISchedulerService service = new SchedulerService();

		Game game = new Game();
		game.setName(TENNIS);
		game.setNoOfPlayers(2);
		assertEquals("Success: Game has been saved successfully", service.createGame(game));
	}

	@Test
	public void createGameWithNullGameName() {
		ISchedulerService service = new SchedulerService();

		Game game = new Game();
		game.setName("");
		game.setNoOfPlayers(5);
		assertEquals("Error: The Game name should not be empty", service.createGame(game));
	}

	@Test
	public void createGameWithNoPlayers() {
		ISchedulerService service = new SchedulerService();

		Game game = new Game();
		game.setName(GOLF);
		game.setNoOfPlayers(0);
		assertEquals("Error: There should at least be 1 player playing in the game", service.createGame(game));
	}

	@Test
	public void createDuplicateGame() {
		ISchedulerService service = new SchedulerService();

		Game game = new Game();
		game.setName(TENNIS);
		game.setNoOfPlayers(2);
		service.createGame(game);
		assertEquals("Error: Game has already exist", service.createGame(game));
	}

	@Test
	public void createNullGame() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: The Game object is null", service.createGame(null));
	}

	@Test
	public void createPlayerSuccessfully() {
		ISchedulerService service = new SchedulerService();
		
		Game game1 = new Game();
		game1.setName(TENNIS);
		game1.setNoOfPlayers(2);
		Game game2 = new Game();
		game2.setName(FOOTBALL);
		game2.setNoOfPlayers(11);
		Game game3 = new Game();
		game3.setName(BADMINTON);
		game3.setNoOfPlayers(2);
		service.createGame(game1);
		Game[] games = { game1, game2, game3 };
		Player player = new Player();
		player.setName(TOM);
		player.setGames(games);
		assertEquals("Success: Player has been saved successfully", service.createPlayer(player));
	}

	@Test
	public void createPlayerThatPlayNoGames() {
		ISchedulerService service = new SchedulerService();

		Game game1 = new Game();
		game1.setName(TENNIS);
		game1.setNoOfPlayers(2);
		Game game2 = new Game();
		game2.setName(FOOTBALL);
		game2.setNoOfPlayers(11);
		Game game3 = new Game();
		game3.setName(BADMINTON);
		game3.setNoOfPlayers(2);
		Game[] games = { game1, game2, game3 };
		Player player = new Player();
		player.setName(TOM);
		player.setGames(games);
		assertEquals("Error: At least 1 game should exist in game repo", service.createPlayer(player));
	}

	@Test
	public void createPlayerWithNoName() {
		ISchedulerService service = new SchedulerService();

		Game game = new Game();
		game.setName(TENNIS);
		game.setNoOfPlayers(2);
		service.createGame(game);
		Game[] games = { game };
		Player player = new Player();
		player.setName("");
		player.setGames(games);
		assertEquals("Error: The Player name should not be empty", service.createPlayer(player));
	}

	@Test
	public void createDuplicatePlayer() {
		ISchedulerService service = new SchedulerService();

		Game game = new Game();
		game.setName(TENNIS);
		game.setNoOfPlayers(2);
		service.createGame(game);
		Game[] games = { game };
		Player player = new Player();
		player.setName(TOM);
		player.setGames(games);
		service.createPlayer(player);
		assertEquals("Error: Player has already exist", service.createPlayer(player));
	}

	@Test
	public void createNullPlayer() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: The Player object is null", service.createPlayer(null));
	}

	@Test
	public void createDaySuccessfully() {
		ISchedulerService service = new SchedulerService();

		Game game1 = new Game();
		game1.setName(TENNIS);
		game1.setNoOfPlayers(2);
		Game game2 = new Game();
		game2.setName(BASKETBALL);
		game2.setNoOfPlayers(5);
		service.createGame(game1);
		service.createGame(game2);
		Game[] games = { game1, game2 };
		Day day = new Day();
		day.setName(DAYONE);
		day.setGames(games);
		assertEquals("Success: Day has been saved successfully", service.createDay(day));
	}

	@Test
	public void createDayWithNoGamesInRepo() {
		ISchedulerService service = new SchedulerService();

		Game game1 = new Game();
		game1.setName(TENNIS);
		game1.setNoOfPlayers(2);
		Game game2 = new Game();
		game2.setName(BASKETBALL);
		game2.setNoOfPlayers(5);
		Game[] games = { game1, game2 };
		Day day = new Day();
		day.setName(DAYONE);
		day.setGames(games);
		assertEquals("Error: All game should exist in game repo", service.createDay(day));
	}

	@Test
	public void createDayWithoutName() {
		ISchedulerService service = new SchedulerService();

		Game game = new Game();
		game.setName(TENNIS);
		game.setNoOfPlayers(2);
		service.createGame(game);
		Game[] games = { game };
		Day day = new Day();
		day.setName("");
		day.setGames(games);
		assertEquals("Error: The Day name should not be empty", service.createDay(day));
	}

	@Test
	public void createDuplicateDay() {
		ISchedulerService service = new SchedulerService();

		Game game = new Game();
		game.setName(TENNIS);
		game.setNoOfPlayers(2);
		service.createGame(game);
		Game[] games = { game };
		Day day = new Day();
		day.setName(DAYONE);
		day.setGames(games);
		service.createDay(day);
		assertEquals("Error: Day has already exist", service.createDay(day));
	}

	@Test
	public void createNullDay() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: The Day object is null", service.createDay(null));
	}

	@Test
	public void generateGameReportSuccessfully() {
		ISchedulerService service = new SchedulerService();

		Game game = new Game();
		game.setName(BASKETBALL);
		game.setNoOfPlayers(5);
		service.createGame(game);
		Game[] games = { game };
		Player player1 = new Player();
		player1.setName(TOM);
		player1.setGames(games);
		Player player2 = new Player();
		player2.setName(JERRY);
		player2.setGames(games);
		service.createPlayer(player1);
		service.createPlayer(player2);
		Day day = new Day();
		day.setName(DAYONE);
		day.setGames(games);
		service.createDay(day);

		StringBuilder sb = new StringBuilder();
		sb.append("Game Report for " + BASKETBALL + "\n");
		sb.append("No. of Players: 5\n\n");
		sb.append("Players playing in this game\n");
		sb.append(TOM + "\n");
		sb.append(JERRY + "\n");
		sb.append("Days game is scheduled on\n");
		sb.append(DAYONE + "\n");
		assertEquals(sb.toString(), service.gameWiseReport(BASKETBALL).toString());
	}

	@Test
	public void generateNonExistantGameReport() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: Game does not exist", service.gameWiseReport(TENNIS).toString());
	}

	@Test
	public void generateEmptyGameReport() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: Game name should not be empty", service.gameWiseReport("").toString());
	}

	@Test
	public void generatePlayerReportSuccessfully() {
		ISchedulerService service = new SchedulerService();
		
		Game game = new Game();
		game.setName(BASKETBALL);
		game.setNoOfPlayers(5);
		service.createGame(game);
		Game[] games = { game };
		Player player1 = new Player();
		player1.setName(TOM);
		player1.setGames(games);
		service.createPlayer(player1);
		Day day = new Day();
		day.setName(DAYONE);
		day.setGames(games);
		
		service.createDay(day);
		
		StringBuilder sb = new StringBuilder();
		sb.append("Player Report for " + TOM + "\n\n");
		sb.append("Games player is playing in:\n");
		sb.append(BASKETBALL + "\n");
		sb.append("Days Game is scheduled on\n");
		sb.append(DAYONE + "\n");
		
		assertEquals(sb.toString(), service.playerWiseReport(TOM).toString());
	}

	@Test
	public void generateNonExistantPlayerReport() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: Player does not exist", service.playerWiseReport(TOM).toString());
	}

	@Test
	public void generateEmptyPlayerReport() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: Player name should not be empty", service.playerWiseReport("").toString());
	}

	@Test
	public void generateDayReportSuccessfully() {
		ISchedulerService service = new SchedulerService();
		
		Game game = new Game();
		game.setName(BASKETBALL);
		game.setNoOfPlayers(5);
		service.createGame(game);
		Game[] games = { game };
		Player player1 = new Player();
		player1.setName(TOM);
		player1.setGames(games);
		service.createPlayer(player1);
		Day day = new Day();
		day.setName(DAYONE);
		day.setGames(games);
		service.createDay(day);
		
		StringBuilder sb = new StringBuilder();
		sb.append("Day Report for " + DAYONE + "\n\n");
		sb.append("Games played on this day\n");
		sb.append(BASKETBALL + "\n");
		sb.append("Players playing in this game\n");
		sb.append(TOM + "\n");
		
		assertEquals(sb.toString(), service.dayWiseReport(DAYONE).toString());
	}

	@Test
	public void generateNonExistantDayReport() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: Day name should not be empty", service.dayWiseReport(DAYONE).toString());
	}

	@Test
	public void generateEmptyDayReport() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: Day name should not be empty", service.dayWiseReport("").toString());
	}
	
	@Test
	public void generateGameReportThatAreNotScheduledOnAnyDays() {
		ISchedulerService service = new SchedulerService();
		
		Game game = new Game();
		game.setName(BASKETBALL);
		game.setNoOfPlayers(5);
		service.createGame(game);
		Game[] games = { game };
		Player player1 = new Player();
		player1.setName(TOM);
		player1.setGames(games);
		Player player2 = new Player();
		player2.setName(JERRY);
		player2.setGames(games);
		service.createPlayer(player1);
		service.createPlayer(player2);
		
		assertEquals("Error: Game not scheduled on any day", service.gameWiseReport(BASKETBALL).toString());
	}
	
	@Test
	public void generateGameReportThatHasNoPlayers() {
		ISchedulerService service = new SchedulerService();
		
		Game game = new Game();
		game.setName(BASKETBALL);
		game.setNoOfPlayers(5);
		service.createGame(game);
		Game[] games = { game };
		Day day = new Day();
		day.setName(DAYONE);
		day.setGames(games);
		service.createDay(day);
		
		assertEquals("Error: Game does not have any players", service.gameWiseReport(BASKETBALL).toString());
	}
	
}