package scheduler.assignment;

import scheduler.assignment.repositories.DayRepo;
import scheduler.assignment.repositories.GameRepo;
import scheduler.assignment.repositories.PlayerRepo;
import scheduler.assignment.data.Day;
import scheduler.assignment.data.Game;
import scheduler.assignment.data.Player;
import scheduler.assignment.interfaces.IDayRepo;
import scheduler.assignment.interfaces.IGameRepo;
import scheduler.assignment.interfaces.IPlayerRepo;
import scheduler.assignment.interfaces.ISchedulerService;

public class SchedulerService implements ISchedulerService {

	private IGameRepo gameRepo; //Game Repository object to store data of Games
	private IPlayerRepo playerRepo; //Player Repository object to store data of Players
	private IDayRepo dayRepo; //Day Repository object to store data of Days

	public SchedulerService() { //default constructor
		gameRepo = new GameRepo();
		playerRepo = new PlayerRepo();
		dayRepo = new DayRepo();
	}

	public SchedulerService(GameRepo gameRepo, PlayerRepo playerRepo, DayRepo dayRepo) { //constructor with parameters
		this.gameRepo = gameRepo;
		this.playerRepo = playerRepo;
		this.dayRepo = dayRepo;
	}

	public String createGame(Game g) { //method to create game
		return gameRepo.save(g);
	}

	public String createPlayer(Player p) { //method to create player
		boolean exist = false; //assuming games Player is playing does not exist
		if (p != null) { //checking if Player is not null
			Game[] games = p.getGames();
			for (Game g : games) { //looping through games Player is playing
				if (gameRepo.findOne(g.getName()) != null) { //checking if game exist in repository
					exist = true; 
					break;
				}
			}
		} else {
			return "Error: The Player object is null";
		}
		if (!exist) { //checking if at least 1 game exist in repository
			return "Error: At least 1 game should exist in game repo";
		}
		return playerRepo.save(p);
	}

	public String createDay(Day d) { //method to create day
		boolean exist = true; //assuming games that is scheduled on Day does not exist
		if (d != null) { //checking if Day is not null
			Game[] games = d.getGames(); 
			for (Game g : games) { //looping through games that is scheduled on Day
				if (gameRepo.findOne(g.getName()) == null) { //checking if game exist in repository
					exist = false;
					break;
				}
			}
		} else {
			return "Error: The Day object is null";
		}
		if (!exist) { //checking if all game exist in repository
			return "Error: All game should exist in game repo";
		}
		return dayRepo.save(d);
	}

	public StringBuffer gameWiseReport(String gameName) { //method to generate game wise report
		StringBuffer sb = new StringBuffer();
		if (gameName.equals("")) { //check if game name is empty
			return sb.append("Error: Game name should not be empty");
		} else {
			Game game = gameRepo.findOne(gameName); //get game from repository using game name
			if (game == null) {  //check if game is null
				return sb.append("Error: Game does not exist");
			} else {
				sb.append("Game Report for " + game.getName() + "\n");
				sb.append("No. of Players: " + game.getNoOfPlayers() + "\n\n");

				int count = 0;
				sb.append("Players playing in this game\n");
				for (Player p : playerRepo.findAll()) { //get all players in repository
					if (p != null) { //ensure player is not null
						for (Game g : p.getGames()) { //looping games player is playing in
							if (g.getName().equals(gameName)) { //check if game name matches
								sb.append(p.getName() + "\n");
								count++;
								break;
							}
						}
					}
				}
				if(count == 0) { //if game does not have any players
					return new StringBuffer("Error: Game does not have any players");
				}

				count = 0;
				sb.append("Days game is scheduled on\n");
				for (Day d : dayRepo.findAll()) { //get all days in repository
					if (d != null) { //ensure day is not null
						for (Game g : d.getGames()) { //looping games scheduled on day
							if (g.getName().equals(gameName)) { //check if game name matches
								sb.append(d.getName() + "\n");
								count++;
								break;
							}
						}
					}
				}
				
				if(count == 0) { //if game was not scheduled on any days
					return new StringBuffer("Error: Game not scheduled on any day");
				}
			}
		}
		return sb;
	}

	public StringBuffer playerWiseReport(String playerName) { //method to generate player wise report
		StringBuffer sb = new StringBuffer();
		//comment
		if (playerName.equals("")) { //check if player name is empty
			return sb.append("Error: Player name should not be empty");
		} else {
			Player player = playerRepo.findOne(playerName); //get player from repository using player name
			if (player == null) { //check if player is null
				return sb.append("Error: Player does not exist");
			} else {
				sb.append("Player Report for " + player.getName() + "\n\n");
				sb.append("Games player is playing in:\n");
				for(Game g : player.getGames()) { //looping games player is playing in
					if(g != null) { //check if game is not null
						Game game = gameRepo.findOne(g.getName()); //get game from repository using game name
						
						if(game != null) { //ensure game is not null
							sb.append(game.getName() + "\n");
							sb.append("Days Game is scheduled on\n");
							for(Day d : dayRepo.findAll()) { //get all days in repository
								if(d != null) { //ensure day is not null
									for(Game dayGame : d.getGames()) { //looping games that is scheduled on day
										if(dayGame.getName().equals(game.getName())) { //check if game name matches
											sb.append(d.getName() + "\n");
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return sb;
	}

	public StringBuffer dayWiseReport(String dayName) { //method to generate day wise report
		StringBuffer sb = new StringBuffer();
		if (dayName.equals("")) { //check if day name is empty
			return sb.append("Error: Day name should not be empty");
		} else {
			Day day = dayRepo.findOne(dayName); //get day from repository using day name
			if (day == null) { //check if day is null
				return sb.append("Error: Day does not exist");
			} else {
				sb.append("Day Report for " + day.getName() + "\n\n");
				sb.append("Games played on this day\n");
				for(Game g : day.getGames()) { //looping games scheduled on day
					if(g != null) { //ensure game is not null
						Game game = gameRepo.findOne(g.getName()); //get game from repository using game name
						
						if(game != null) { //ensure game is not null
							sb.append(game.getName() + "\n");
							sb.append("Players playing in this game\n");
							for(Player p : playerRepo.findAll()) { //get all players in repository
								if(p != null) { //ensure player is not null
									for(Game playerGame : p.getGames()) { //looping all games player is playing in
										if(playerGame.getName().equals(game.getName())) { //check if game name matches
											sb.append(p.getName() + "\n");
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return sb;
	}

}
