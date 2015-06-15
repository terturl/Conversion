package me.terturl.Conversion.GameLogic;

import java.util.HashMap;

import me.terturl.Conversion.CVPlayer;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Cache {

	public static HashMap<String, Integer> usedSpawns = new HashMap<>();
	public static HashMap<Player, String> AlivePlayers = new HashMap<Player, String>();
	public static HashMap<Player, String> DeadPlayers = new HashMap<Player, String>();
	public static HashMap<String, CVPlayer> RSPlayers = new HashMap<String, CVPlayer>();

	public static HashMap<Player, String> Red = new HashMap<Player, String>();
	public static HashMap<Player, String> Blue = new HashMap<Player, String>();

	public static HashMap<Integer, ItemStack> Items = new HashMap<Integer, ItemStack>();

	public static void clearAll() {
		usedSpawns.clear();
		AlivePlayers.clear();
		DeadPlayers.clear();
		RSPlayers.clear();
		Red.clear();
		Blue.clear();

	}

}
