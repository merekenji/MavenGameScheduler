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
	
	private static final Game GAME1 = new Game(TENNIS, 2);
	private static final Game GAME2 = new Game(FOOTBALL, 11);
	private static final Game GAME3 = new Game(BASKETBALL, 5);
	
	private static final Game[] GAMES = { GAME1, GAME2, GAME3 };
	
	private static final Player PLAYER1 = new Player(TOM, GAMES);
	
	private static final Day DAY1 = new Day(DAYONE, GAMES);
	
	@Test
	public void createGameSuccessfully() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Success: Game has been saved successfully", service.createGame(GAME1));
	}

	@Test
	public void createGameWithNullGameName() {
		ISchedulerService service = new SchedulerService();

		Game game = new Game("", 5);
		assertEquals("Error: The Game name should not be empty", service.createGame(game));
	}

	@Test
	public void createGameWithNoPlayers() {
		ISchedulerService service = new SchedulerService();

		Game game = new Game(GOLF, 0);
		assertEquals("Error: There should at least be 1 player playing in the game", service.createGame(game));
	}

	@Test
	public void createDuplicateGame() {
		ISchedulerService service = new SchedulerService();

		service.createGame(GAME1);
		assertEquals("Error: Game has already exist", service.createGame(GAME1));
	}

	@Test
	public void createNullGame() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: The Game object is null", service.createGame(null));
	}

	@Test
	public void createPlayerSuccessfully() {
		ISchedulerService service = new SchedulerService();
		
		service.createGame(GAME1);
		assertEquals("Success: Player has been saved successfully", service.createPlayer(PLAYER1));
	}

	@Test
	public void createPlayerThatPlayNoGames() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: At least 1 game should exist in game repo", service.createPlayer(PLAYER1));
	}

	@Test
	public void createPlayerWithNoName() {
		ISchedulerService service = new SchedulerService();

		service.createGame(GAME1);
		Player player = new Player("", GAMES);
		assertEquals("Error: The Player name should not be empty", service.createPlayer(player));
	}

	@Test
	public void createDuplicatePlayer() {
		ISchedulerService service = new SchedulerService();

		service.createGame(GAME1);
		service.createPlayer(PLAYER1);
		assertEquals("Error: Player has already exist", service.createPlayer(PLAYER1));
	}

	@Test
	public void createNullPlayer() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: The Player object is null", service.createPlayer(null));
	}

	@Test
	public void createDaySuccessfully() {
		ISchedulerService service = new SchedulerService();

		service.createGame(GAME1);
		service.createGame(GAME2);
		service.createGame(GAME3);
		assertEquals("Success: Day has been saved successfully", service.createDay(DAY1));
	}

	@Test
	public void createDayWithNoGamesInRepo() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: All game should exist in game repo", service.createDay(DAY1));
	}

	@Test
	public void createDayWithoutName() {
		ISchedulerService service = new SchedulerService();

		service.createGame(GAME1);
		service.createGame(GAME2);
		service.createGame(GAME3);
		Day day = new Day("", GAMES);
		assertEquals("Error: The Day name should not be empty", service.createDay(day));
	}

	@Test
	public void createDuplicateDay() {
		ISchedulerService service = new SchedulerService();

		service.createGame(GAME1);
		service.createGame(GAME2);
		service.createGame(GAME3);
		service.createDay(DAY1);
		assertEquals("Error: Day has already exist", service.createDay(DAY1));
	}

	@Test
	public void createNullDay() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: The Day object is null", service.createDay(null));
	}

	@Test
	public void generateGameReportSuccessfully() {
		ISchedulerService service = new SchedulerService();

		service.createGame(GAME1);
		Game[] games = { GAME1 };
		Player player1 = new Player(TOM, games);
		Player player2 = new Player(JERRY, games);
		service.createPlayer(player1);
		service.createPlayer(player2);
		Day day = new Day(DAYONE, games);
		service.createDay(day);

		StringBuilder sb = new StringBuilder();
		sb.append("Game Report for " + TENNIS + "\n");
		sb.append("No. of Players: 2\n\n");
		sb.append("Players playing in this game\n");
		sb.append(TOM + "\n");
		sb.append(JERRY + "\n");
		sb.append("Days game is scheduled on\n");
		sb.append(DAYONE + "\n");
		assertEquals(sb.toString(), service.gameWiseReport(TENNIS).toString());
	}

	@Test
	public void generateNonExistantGameReport() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: Game name should not be empty or Game does not exist", service.gameWiseReport(TENNIS).toString());
	}

	@Test
	public void generateEmptyGameReport() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: Game name should not be empty or Game does not exist", service.gameWiseReport("").toString());
	}

	@Test
	public void generatePlayerReportSuccessfully() {
		ISchedulerService service = new SchedulerService();
		
		service.createGame(GAME1);
		Game[] games = { GAME1 };
		Player player1 = new Player(TOM, games);
		service.createPlayer(player1);
		Day day = new Day(DAYONE, games);
		
		service.createDay(day);
		
		StringBuilder sb = new StringBuilder();
		sb.append("Player Report for " + TOM + "\n\n");
		sb.append("Games player is playing in:\n");
		sb.append(TENNIS + "\n");
		sb.append("Days Game is scheduled on\n");
		sb.append(DAYONE + "\n");
		
		assertEquals(sb.toString(), service.playerWiseReport(TOM).toString());
	}

	@Test
	public void generateNonExistantPlayerReport() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: Player name should not be empty or Player does not exist", service.playerWiseReport(TOM).toString());
	}

	@Test
	public void generateEmptyPlayerReport() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: Player name should not be empty or Player does not exist", service.playerWiseReport("").toString());
	}

	@Test
	public void generateDayReportSuccessfully() {
		ISchedulerService service = new SchedulerService();
		
		service.createGame(GAME1);
		Game[] games = { GAME1 };
		Player player1 = new Player(TOM, games);
		service.createPlayer(player1);
		Day day = new Day(DAYONE, games);
		service.createDay(day);
		
		StringBuilder sb = new StringBuilder();
		sb.append("Day Report for " + DAYONE + "\n\n");
		sb.append("Games played on this day\n");
		sb.append(TENNIS + "\n");
		sb.append("Players playing in this game\n");
		sb.append(TOM + "\n");
		
		assertEquals(sb.toString(), service.dayWiseReport(DAYONE).toString());
	}

	@Test
	public void generateNonExistantDayReport() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: Day name should not be empty or Day does not exist", service.dayWiseReport(DAYONE).toString());
	}

	@Test
	public void generateEmptyDayReport() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: Day name should not be empty or Day does not exist", service.dayWiseReport("").toString());
	}
	
}