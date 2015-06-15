package me.terturl.Conversion.GameLogic;

import me.terturl.Conversion.Conversion;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class GameManager implements Runnable {

	private static int taskID;
	private static int tick = 0;
	private static GameState gameState = GameState.WAITING;
	private static int numberOfPlayers;
	private static int numberInArena;
	private static int min = 2;
	private static int max = Bukkit.getServer().getMaxPlayers();
	private static boolean force = false;
	private static boolean preforce = false;
	private static boolean preforceEngaged = false;
	private static boolean won = false;
	private static boolean lose = false;

	/**
	 * @return the numberOfPlayers
	 */
	public static int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	/**
	 * @param aNumberOfPlayers
	 *            the numberOfPlayers to set
	 */
	public static void setNumberOfPlayers(int aNumberOfPlayers) {
		numberOfPlayers = aNumberOfPlayers;
	}

	/**
	 * @return the numberInArena
	 */
	public static int getNumberInArena() {
		return numberInArena;
	}

	/**
	 * @param aNumberInArena
	 *            the numberInArena to set
	 */
	public static void setNumberInArena(int aNumberInArena) {
		numberInArena = aNumberInArena;
	}

	/**
	 * @return the min
	 */
	public static int getMin() {
		return min;
	}

	/**
	 * @param aMin
	 *            the min to set
	 */
	public static void setMin(int aMin) {
		min = aMin;
	}

	/**
	 * @return the max
	 */
	public static int getMax() {
		return max;
	}

	/**
	 * @param aMax
	 *            the max to set
	 */
	public static void setMax(int aMax) {
		max = aMax;
	}

	/**
	 * @return the force
	 */
	public static boolean isForce() {
		return force;
	}

	/**
	 * @param aForce
	 *            the force to set
	 */
	public static void setForce(boolean aForce) {
		force = aForce;
	}

	/**
	 * @return the preforce
	 */
	public static boolean isPreforce() {
		return preforce;
	}

	/**
	 * @param aPreforce
	 *            the preforce to set
	 */
	public static void setPreforce(boolean aPreforce) {
		preforce = aPreforce;
	}

	/**
	 * @return the preforceEngaged
	 */
	public static boolean isPreforceEngaged() {
		return preforceEngaged;
	}

	/**
	 * @param aPreforceEngaged
	 *            the preforceEngaged to set
	 */
	public static void setPreforceEngaged(boolean aPreforceEngaged) {
		preforceEngaged = aPreforceEngaged;
	}

	public static void setGameState(GameState state) {
		gameState = state;
	}

	public static GameState getGameState() {
		return gameState;
	}

	public static int getTick() {
		return tick;
	}

	public static void setWon(boolean b) {
		won = b;
	}

	public static void setLose(boolean b) {
		lose = b;
	}

	public static boolean getWon() {
		return won;
	}

	public static boolean getLose() {
		return lose;
	}

	public static boolean waiting() {
		return getGameState() == GameState.WAITING;
	}

	public static boolean inProgress() {
		return getGameState() == GameState.IN_PROGRESS;
	}

	public static boolean ending() {
		return getGameState() == GameState.ENDED;
	}

	public static boolean readyToPlay() {
		return StateManager.getState() == GameState.WARMUP;
	}

	public void setTaskID(int i) {
		taskID = i;
	}

	public static void cancelTask() {
		Bukkit.getServer().getScheduler().cancelTask(taskID);
	}

	@Override
	public void run() {
		// Bukkit.broadcastMessage("FIRED"+tick);
		setNumberOfPlayers(Bukkit.getOnlinePlayers().size());

		if (getNumberOfPlayers() < 1) {
			return;
		}

		tick++;

		if (tick == 30) {
			setGameState(GameState.WAITING);
			broadcastMessage(ChatColor.BLUE + "3 Minutes");
			return;
		}

		if (tick == 60) {
			setGameState(GameState.WAITING);
			broadcastMessage(ChatColor.BLUE + "2 min 30s");
			return;
		}

		if (tick == 90) {
			setGameState(GameState.WAITING);
			broadcastMessage(ChatColor.BLUE + "2 Minutes");
			return;
		}

		if (tick == 120) {
			setGameState(GameState.WAITING);
			broadcastMessage(ChatColor.BLUE + "1 min 30s");
			return;
		}

		if (tick == 150) {
			setGameState(GameState.WAITING);
			broadcastMessage(ChatColor.BLUE + "1 minute");
			return;
		}

		if (tick == 179) {
			if (Bukkit.getOnlinePlayers().size() < getMin()) {
				Utils.broadcast(ChatColor.RED
						+ "Not enough players, restarting countdown...");
				tick = 0;
				return;
			}
		}

		if (tick == 180) {

			broadcastMessage(ChatColor.RED + "30 Seconds");

			// Spacer
			Utils.broadcast(ChatColor.GOLD + "-------------------------");

			Utils.broadcast(ChatColor.GOLD + "GET READY! "
					+ ChatColor.LIGHT_PURPLE + "The game is about to "
					+ ChatColor.RED + "START!");
		}

		if (tick == 190) {
			Countdown cd = new Countdown();
			cd.setTaskID(Bukkit.getServer().getScheduler()
					.scheduleSyncRepeatingTask(Conversion.plugin, cd, 0L, 20L));
			
			
		}

	}

	public static void broadcastMessage(String time) {
		Utils.broadcast(ChatColor.GREEN + "---------------------------");
		Utils.broadcast(ChatColor.GREEN + "Map: " + ChatColor.YELLOW
				+ MapManager.getWorld().getName());
		Utils.broadcast(ChatColor.GREEN + "Players: " + ChatColor.DARK_GREEN
				+ getNumberOfPlayers() + "/" + getMax()
				+ ChatColor.DARK_RED + " (min: " + getMin() + ")");
		Utils.broadcast(ChatColor.YELLOW + "Match Starts In: "
				+ ChatColor.GREEN + time);
		Utils.broadcast(ChatColor.GREEN + "---------------------------");
	}
}
