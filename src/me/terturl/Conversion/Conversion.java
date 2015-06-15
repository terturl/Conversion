package me.terturl.Conversion;

import me.terturl.Conversion.GameLogic.Cache;
import me.terturl.Conversion.GameLogic.Countdown;
import me.terturl.Conversion.GameLogic.EndCount;
import me.terturl.Conversion.GameLogic.GameManager;
import me.terturl.Conversion.GameLogic.GameState;
import me.terturl.Conversion.GameLogic.MapManager;
import me.terturl.Conversion.GameLogic.PlayerMovement;
import me.terturl.Conversion.GameLogic.StateManager;
import me.terturl.Conversion.GameLogic.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Conversion extends JavaPlugin implements Listener {

	public static Conversion plugin;

	@Override
	public void onDisable() {
		Bukkit.broadcastMessage(ChatColor.GOLD + "Conversion by terturl -"
				+ ChatColor.BOLD + ChatColor.DARK_BLUE + "DISABLED!");
		Bukkit.broadcastMessage(ChatColor.GREEN + "- - - - - - - - - - - - - -");
		Bukkit.broadcastMessage(ChatColor.DARK_RED + "Version 0.1!");
		Bukkit.broadcastMessage(ChatColor.GREEN + "- - - - - - - - - - - - - -");

		ScoreboardManager manager = Bukkit.getScoreboardManager();

		for (Player player : Bukkit.getServer().getOnlinePlayers()) {

			Scoreboard board = manager.getNewScoreboard();

			player.setScoreboard(board);
		}

		Cache.clearAll();
	}

	@Override
	public void onEnable() {
		Bukkit.broadcastMessage(ChatColor.GOLD + "Conversion by terturl -"
				+ ChatColor.BOLD + ChatColor.AQUA + "ENABLED!");
		Bukkit.broadcastMessage(ChatColor.GREEN + "- - - - - - - - - - - - - -");
		Bukkit.broadcastMessage(ChatColor.DARK_RED + "Version 0.1!");
		Bukkit.broadcastMessage(ChatColor.GREEN + "- - - - - - - - - - - - - -");

		StateManager.setState(GameState.WAITING);

		plugin = this;

		System.out.println("Registering listeners...");
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getPluginManager()
				.registerEvents(new PlayerMovement(), this);
		Bukkit.getServer().getPluginManager()
				.registerEvents(new Transfer(), this);

		System.out.println("DONE!");

		GameManager gm = new GameManager();
		gm.setTaskID(Bukkit.getServer().getScheduler()
				.scheduleSyncRepeatingTask(this, gm, 0L, 20L));

		MapManager.getWorld().setAutoSave(false);

		plugin = this;

		MapManager.getWorld().setMonsterSpawnLimit(0);
		
		for (Player players : Bukkit.getServer().getOnlinePlayers()) {

			players.setGameMode(GameMode.ADVENTURE);
			Inventory inventory = players.getInventory();
			World world = MapManager.getWorld();
			Location lobbyspawn = new Location(world, 0.5, 155, -2.5);
			world.setSpawnLocation(0, 155, -3);
			inventory.clear();
			
			inventory.addItem(Items.getBook());
			players.teleport(lobbyspawn);
			Cache.clearAll();
			((PlayerInventory) inventory).setArmorContents(null);
		}


	}
	
	@EventHandler
	public void playerJoin(PlayerJoinEvent event) {
		
		Inventory inv = event.getPlayer().getInventory();
		
		inv.clear();
		
		inv.addItem(Items.getBook());
		
		if(StateManager.getState() == GameState.IN_PROGRESS) {
			
			Utils.msg(event.getPlayer(), ChatColor.GREEN + "The game has started, but another will start shortly!");
			
			Player player = event.getPlayer();
			
			World world = MapManager.getWorld();
			
			
			Location loc = new Location(world, 0, 155, -3, 2, -180);
			
			player.teleport(loc);
			
			world.setSpawnLocation(0, 105, 0);

			((PlayerInventory) inv).setArmorContents(null);

		}
	}
	
	@EventHandler
	public void weatherChange(WeatherChangeEvent event) {
		@SuppressWarnings("unused")
		World world = MapManager.getWorld();
		event.setCancelled(true);
		
	}

	
	@EventHandler
	public void playerLeave(PlayerQuitEvent event) {
		if(Cache.Red.containsKey(event.getPlayer())){
			Cache.Red.remove(event.getPlayer());
			
		}
		
		if(Cache.Blue.containsKey(event.getPlayer())){
			Cache.Blue.remove(event.getPlayer());
			
		}
		
		if(Cache.RSPlayers.containsKey(event.getPlayer())){
			Cache.RSPlayers.remove(event.getPlayer());
			
		}
		Inventory inv = event.getPlayer().getInventory();
		inv.clear();
		((PlayerInventory) inv).setArmorContents(null);
		
		if(Cache.Red.size() == 0) {
			EndCount ec = new EndCount();
			ec.setTaskID(Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, ec,  0L,  20L));
		}
		
		if(Cache.Blue.size() == 0) {
			EndCount ec = new EndCount();
			ec.setTaskID(Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, ec,  0L,  20L));
		}

		
	}

	public boolean onCommand(CommandSender sender, Command cmd,
		String commandLabel, String[] args) {
		Player player = (Player) sender;

		if(commandLabel.equalsIgnoreCase("con")) {

			if (args[0].equalsIgnoreCase("start")) {
	
				// prepGame();
	
				Countdown cd = new Countdown();
				cd.setTaskID(Bukkit.getServer().getScheduler()
						.scheduleSyncRepeatingTask(this, cd, 0L, 20L));
	
				GameManager.cancelTask();
			}
			
			if (args[0].equalsIgnoreCase("mobs")) {
	
				for (Entity e : player.getWorld().getEntities()) {
	
					if (e instanceof Player) {
						continue;
					}
					e.remove();
				}
	
				Bukkit.broadcastMessage("Entities removed");
			}
	
			if (args[0].equalsIgnoreCase("day")) {
	
				World world = player.getWorld();
				world.setTime(1);
			}
	
			if (args[0].equalsIgnoreCase("heal")) {
	
				player.setHealth(20);
				player.setFoodLevel(20);
			}
			// if (commandLabel.equalsIgnoreCase("map")) {
			// int map = Integer.parseInt(args[0]);
			// MapManager.map = map;
			// Utils.msg(player, ChatColor.GOLD + "Map changed!");
			// }
	
			if (args[0].equalsIgnoreCase("warmup")) {
	
				prepGame();
	
			}
	
			if (args[0].equalsIgnoreCase("halt")) {
	
				GameManager.cancelTask();
	
				Utils.broadcast(ChatColor.RED + "GameManager STOPPED!");
	
			}
	
			if (args[0].equalsIgnoreCase("startcountdown")) {
	
				GameManager gm = new GameManager();
				gm.setTaskID(Bukkit.getServer().getScheduler()
						.scheduleSyncRepeatingTask(this, gm, 0L, 20L));
	
				Utils.broadcast(ChatColor.GREEN + "GameManager STARTED!");
	
			}
	
			if (args[0].equalsIgnoreCase("finish")) {
	
				EndCount ec = new EndCount();
				ec.setTaskID(Bukkit.getServer().getScheduler()
						.scheduleSyncRepeatingTask(this, ec, 0L, 20L));
	
			}
		}
		
		return false;

	}

	@EventHandler
	public void playerDrops(PlayerDeathEvent e) {
		e.getDrops().clear();
	}

	@EventHandler
	public void fallDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			@SuppressWarnings("unused")
			Player player = (Player) event.getEntity();
			if (event.getCause() == DamageCause.FALL) {
				event.setDamage(0);
			}
		}
	}

	@EventHandler
	public void playerThrow(PlayerDropItemEvent e) {

		e.setCancelled(true);

	}

	@EventHandler
	public void playerHunger(PlayerMoveEvent e) {

		Player player = e.getPlayer();
		player.setFoodLevel(20);
	}

	@EventHandler
	public void breakBlock(BlockBreakEvent event) {
		if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
			return;
		} else if (!event.getPlayer().isOp()) {
			event.setCancelled(true);
		}
	}

	public static void teleportPlayers(boolean debug) {
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			if (player == null || player.isDead())
				continue;

			if (Cache.Red.containsKey(player)) {

				int i = 0;

				Location location = new Location(MapManager.getWorld(),
						MapManager.getRedSpawns()[i][0],
						MapManager.getRedSpawns()[i][1],
						MapManager.getRedSpawns()[i][2],
						(float) MapManager.getRedSpawns()[i][3], (float) 0);
				player.teleport(location);
				Cache.usedSpawns.put(player.getName(), i);
				//if (debug)
				//	Utils.broadcast("Player " + player.getName()
				//			+ " teleported to Red spawn: " + i);
				i++;
			}

			if (Cache.Blue.containsKey(player)) {

				int i = 0;

				Location location = new Location(MapManager.getWorld(),
						MapManager.getBlueSpawns()[i][0],
						MapManager.getBlueSpawns()[i][1],
						MapManager.getBlueSpawns()[i][2],
						(float) MapManager.getBlueSpawns()[i][3], (float) 0);
				player.teleport(location);
				Cache.usedSpawns.put(player.getName(), i);
				if (debug)
					Utils.broadcast("Player " + player.getName()
							+ " teleported to Blue spawn: " + i);
				i++;

				if (!Cache.Blue.containsKey(player)
						&& !Cache.Red.containsKey(player)) {
					Cache.Blue.put(player, player.getName());

					player.teleport(location);
					Cache.usedSpawns.put(player.getName(), i);
					if (debug)
						Utils.broadcast("Player " + player.getName()
								+ " teleported to Blue spawn: " + i);
					i++;
				}
			}
		}
	}

	public static void prepGame() {


		for (Player player : Bukkit.getServer().getOnlinePlayers()) {



			if (Cache.Red.size() >= Cache.Blue.size()) {
				// in blue

				Cache.Blue.put(player, player.getName());

				@SuppressWarnings("unused")
				int i = Cache.Blue.size();

				Utils.msg(player, ChatColor.GREEN + "You are in the "
						+ ChatColor.BLUE + "BLUE " + ChatColor.GREEN + "team!");


			} else if (Cache.Blue.size() > Cache.Red.size()) {
				// in red

				Cache.Red.put(player, player.getName());


				@SuppressWarnings("unused")
				int i = Cache.Red.size();

				Utils.msg(player, ChatColor.GREEN + "You are in the "
						+ ChatColor.RED + "RED " + ChatColor.GREEN + "team!");


			}

			// Inventory Management

			Inventory inventory = player.getInventory();
			inventory.clear();
			player.setGameMode(GameMode.ADVENTURE);

			player.setHealth(20);
			player.setFoodLevel(20);
			for (Entity e : player.getWorld().getEntities()) {

				if (e instanceof Player) {
					continue;
				}
				e.remove();
			}
		}

		teleportPlayers(true);
		StateManager.setState(GameState.WARMUP);

	}
	


	public static void finishGame() {

		for (Player players : Bukkit.getServer().getOnlinePlayers()) {

			players.setGameMode(GameMode.ADVENTURE);
			Inventory inventory = players.getInventory();
			World world = MapManager.getWorld();
			Location lobbyspawn = new Location(world, 0.5, 155, -3.5, 0, 0);
			inventory.clear();
			players.teleport(lobbyspawn);
			Cache.DeadPlayers.clear();
			((PlayerInventory) inventory).setArmorContents(null);
			String p = Cache.AlivePlayers.get(players);

			if (p != null && !p.isEmpty()) {
				Utils.broadcast(ChatColor.GOLD

				+ p + ChatColor.YELLOW + " has won the game!");

				for (CVPlayer feplayer : Cache.RSPlayers.values()) {
					Utils.broadcast(ChatColor.GOLD + feplayer.getName()
							+ ChatColor.LIGHT_PURPLE + " scored "
							+ ChatColor.RED + feplayer.getKills()
							+ ChatColor.LIGHT_PURPLE + " kills and "
							+ ChatColor.RED + feplayer.getDeaths()
							+ ChatColor.LIGHT_PURPLE + " deaths!");

				}
			}

			MapManager.map = 0;

			GameManager gm = new GameManager();
			gm.setTaskID(Bukkit.getServer().getScheduler()
					.scheduleSyncRepeatingTask(Conversion.plugin, gm, 0L, 20L));

			Cache.clearAll();

			StateManager.setState(GameState.ENDED);

			StateManager.setState(GameState.WAITING);

			Countdown.cancelTask();

		}
	}

	public static void startGame() {

		for (Player players : Bukkit.getServer().getOnlinePlayers()) {

			CVPlayer feplayer = new CVPlayer(players.getName());
			Cache.RSPlayers.put(feplayer.getName(), feplayer);


			players.setHealth(20);
			players.setFoodLevel(20);

			StateManager.setState(GameState.IN_PROGRESS);

			ItemStack snowballs = new ItemStack(Material.SNOW_BALL, 32);

			Inventory inv = players.getInventory();
			inv.addItem(snowballs);
			inv.addItem(snowballs);
			inv.addItem(snowballs);
			inv.addItem(snowballs);
			inv.addItem(snowballs);
			inv.addItem(snowballs);
			inv.addItem(snowballs);
			inv.addItem(snowballs);
			inv.addItem(snowballs);

		}

	}
}
