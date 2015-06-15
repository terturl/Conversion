package me.terturl.Conversion;

import me.terturl.Conversion.GameLogic.Cache;
import me.terturl.Conversion.GameLogic.EndCount;
import me.terturl.Conversion.GameLogic.GameState;
import me.terturl.Conversion.GameLogic.MapManager;
import me.terturl.Conversion.GameLogic.StateManager;
import me.terturl.Conversion.GameLogic.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Transfer implements Listener {

	public static Conversion plugin;

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

		if (StateManager.getState() == GameState.IN_PROGRESS) {

			if (event.getDamager() instanceof Snowball) {

				Snowball snowball = (Snowball) event.getDamager();

				Entity hitBySnowball = event.getEntity();

				LivingEntity shooter = (LivingEntity) snowball.getShooter();

				if (hitBySnowball instanceof Player) {

					Player player = (Player) hitBySnowball;

					if (Cache.Red.containsKey(shooter)) {

						if (Cache.Red.containsKey(player)) {
							event.setCancelled(true);
						}

						if (Cache.Blue.containsKey(player)) {
							Cache.Blue.remove(player);
							Cache.Red.put(player, player.getName());

							Utils.msg(player, ChatColor.GREEN
									+ "You were converted to the "
									+ ChatColor.RED + "RED " + ChatColor.GREEN
									+ "team!");

							Location location = new Location(
									MapManager.getWorld(),
									MapManager.getRedSpawns()[0][0],
									MapManager.getRedSpawns()[0][1],
									MapManager.getRedSpawns()[0][2],
									(float) MapManager.getRedSpawns()[0][3],
									(float) 0);
							player.teleport(location);

							Inventory inv = player.getInventory();
							inv.clear();

							ItemStack snowball1 = new ItemStack(
									Material.SNOW_BALL, 32);

							inv.addItem(snowball1);
							inv.addItem(snowball1);
							inv.addItem(snowball1);
							inv.addItem(snowball1);
							inv.addItem(snowball1);
							inv.addItem(snowball1);
							inv.addItem(snowball1);
							inv.addItem(snowball1);
							inv.addItem(snowball1);

							Utils.broadcast(ChatColor.GOLD + player.getName()
									+ ChatColor.GREEN
									+ " has been converted to the "
									+ ChatColor.RED + "RED " + ChatColor.GREEN
									+ "team!");

							if (Cache.Blue.size() == 0) {

								EndCount cd1 = new EndCount();
								cd1.setTaskID(Bukkit
										.getServer()
										.getScheduler()
										.scheduleSyncRepeatingTask(plugin, cd1,
												0L, 20L));

							}

							@SuppressWarnings("unused")
							int i = Cache.Blue.size();

							Utils.broadcast(ChatColor.GOLD
									+ "Cache.Blue.size()" + ChatColor.BLUE
									+ "BLUE " + ChatColor.GREEN
									+ "players need to be converted to win!");
							Utils.broadcast(ChatColor.GOLD + "Cache.Red.size()"
									+ ChatColor.RED + "RED " + ChatColor.GREEN
									+ "players need to be converted to win!");

						}

					}

					if (Cache.Blue.containsKey(shooter)) {

						if (Cache.Blue.containsKey(player)) {
							event.setCancelled(true);
						}

						if (Cache.Red.containsKey(player)) {
							Cache.Red.remove(player);
							Cache.Blue.put(player, player.getName());

							Utils.msg(player, ChatColor.GREEN
									+ "You were converted to the "
									+ ChatColor.BLUE + "BLUE "
									+ ChatColor.GREEN + "team!");

							Location location = new Location(
									MapManager.getWorld(),
									MapManager.getBlueSpawns()[0][0],
									MapManager.getBlueSpawns()[0][1],
									MapManager.getBlueSpawns()[0][2],
									(float) MapManager.getBlueSpawns()[0][3],
									(float) 0);
							player.teleport(location);

							Inventory inv = player.getInventory();
							inv.clear();

							ItemStack snowball1 = new ItemStack(
									Material.SNOW_BALL, 32);

							inv.addItem(snowball1);
							inv.addItem(snowball1);
							inv.addItem(snowball1);
							inv.addItem(snowball1);
							inv.addItem(snowball1);
							inv.addItem(snowball1);
							inv.addItem(snowball1);
							inv.addItem(snowball1);
							inv.addItem(snowball1);

							Utils.broadcast(ChatColor.GOLD + player.getName()
									+ ChatColor.GREEN
									+ " has been converted to the "
									+ ChatColor.BLUE + "BLUE "
									+ ChatColor.GREEN + "team!");

							if (Cache.Red.size() == 0) {

								EndCount cd1 = new EndCount();
								cd1.setTaskID(Bukkit
										.getServer()
										.getScheduler()
										.scheduleSyncRepeatingTask(plugin, cd1,
												0L, 20L));

							}

							Utils.broadcast(ChatColor.GOLD
									+ "Cache.Blue.size()" + ChatColor.BLUE
									+ "BLUE " + ChatColor.GREEN
									+ "players need to be converted to win!");
							Utils.broadcast(ChatColor.GOLD + "Cache.Red.size()"
									+ ChatColor.RED + "RED " + ChatColor.GREEN
									+ "players need to be converted to win!");

						}

					}
				}

			}
		}

	}
}
