package me.terturl.Conversion.GameLogic;

import me.terturl.Conversion.CVPlayer;
import me.terturl.Conversion.Conversion;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;


public class EndCount implements Runnable {

	public static Conversion plugin;

	private static int taskID;
	private int tick = 0;

	@Override
	public void run() {

		if (tick == 1) {

			if (Cache.Red.size() == 0) {
				Utils.broadcast(ChatColor.GREEN + "------------------");
				Utils.broadcast(ChatColor.BLUE + "BLUE TEAM HAS WON THE GAME");

				Utils.broadcast(ChatColor.GOLD
						+ "Returning to lobby in 10 seconds!");
				Utils.broadcast(ChatColor.GREEN + "------------------");
			}

			if (Cache.Blue.size() == 0) {
				Utils.broadcast(ChatColor.GREEN + "------------------");
				Utils.broadcast(ChatColor.RED + "RED TEAM HAS WON THE GAME");

				Utils.broadcast(ChatColor.GOLD
						+ "Returning to lobby in 10 seconds!");
				Utils.broadcast(ChatColor.GREEN + "------------------");
			}

			for (Player players : Bukkit.getServer().getOnlinePlayers()) {
				Inventory inv = players.getInventory();

				inv.clear();
				StateManager.setState(GameState.ENDED);
				players.setGameMode(GameMode.ADVENTURE);
				players.setHealth(20);
				players.setFoodLevel(20);

				Countdown.cancelTask();

				Location loc = players.getLocation();

				World world = players.getWorld();

				world.playSound(loc, Sound.NOTE_PIANO, 1F, 1F);
			}

			if (tick == 2) {
				for (Player players : Bukkit.getServer().getOnlinePlayers()) {
					@SuppressWarnings("unused")
					String p = Cache.AlivePlayers.get(players);

					World world = players.getWorld();

					Location loc = players.getLocation();

					world.playSound(loc, Sound.NOTE_PIANO, 1F, 1F);

					for (CVPlayer feplayer : Cache.RSPlayers.values()) {
						Utils.broadcast(ChatColor.GOLD + feplayer.getName()
								+ ChatColor.LIGHT_PURPLE + " scored "
								+ ChatColor.RED + feplayer.getConversions()
								+ ChatColor.LIGHT_PURPLE
								+ " conversions and was converted "
								+ ChatColor.RED + feplayer.getConverts()
								+ ChatColor.LIGHT_PURPLE + " times!");

					}

				}

				if (tick == 3) {
					for (Player players : Bukkit.getServer().getOnlinePlayers()) {
						World world = players.getWorld();

						Location loc = players.getLocation();

						world.playSound(loc, Sound.NOTE_PIANO, 1F, 1F);

					}
				}

				if (tick == 4) {
					for (Player players : Bukkit.getServer().getOnlinePlayers()) {
						World world = players.getWorld();
						Location loc = players.getLocation();

						world.playSound(loc, Sound.NOTE_PIANO, 1F, 1F);

					}
				}

				if (tick == 5) {
					for (Player players : Bukkit.getServer().getOnlinePlayers()) {
						World world = players.getWorld();
						Location loc = players.getLocation();

						world.playSound(loc, Sound.NOTE_PIANO, 1F, 1F);

					}
				}

				if (tick == 6) {
					for (Player players : Bukkit.getServer().getOnlinePlayers()) {
						World world = players.getWorld();
						Location loc = players.getLocation();

						world.playSound(loc, Sound.NOTE_PIANO, 1F, 1F);

					}
				}

				if (tick == 7) {
					for (Player players : Bukkit.getServer().getOnlinePlayers()) {
						World world = players.getWorld();
						Location loc = players.getLocation();

						world.playSound(loc, Sound.NOTE_PIANO, 1F, 1F);

					}
				}

				if (tick == 8) {
					for (Player players : Bukkit.getServer().getOnlinePlayers()) {
						World world = players.getWorld();
						Location loc = players.getLocation();

						world.playSound(loc, Sound.NOTE_PIANO, 1F, 1F);

					}
				}

				if (tick == 9) {
					for (Player players : Bukkit.getServer().getOnlinePlayers()) {

						players.setGameMode(GameMode.ADVENTURE);
						Inventory inventory = players.getInventory();
						World world = MapManager.getWorld();
						Location lobbyspawn = new Location(world, 0.5, 155,
								-2.5, 2, -180);
						inventory.clear();
						players.teleport(lobbyspawn);
						Cache.DeadPlayers.clear();
						((PlayerInventory) inventory).setArmorContents(null);

						World world1 = players.getWorld();

						Location loc = players.getLocation();

						world1.playSound(loc, Sound.NOTE_PIANO, 1F, 1F);

					}

					Utils.broadcast(ChatColor.GREEN
							+ "Returning to lobby mode!");

					StateManager.setState(GameState.WAITING);

					GameManager gm = new GameManager();
					gm.setTaskID(Bukkit
							.getServer()
							.getScheduler()
							.scheduleSyncRepeatingTask(Conversion.plugin, gm,
									0L, 20L));
				}

				if (tick == 10) {

					cancelTask();

				}

				tick++;
			}
		}

	}

	public void setTaskID(int i) {
		taskID = i;
	}

	public static void cancelTask() {
		Bukkit.getServer().getScheduler().cancelTask(taskID);
	}

}
