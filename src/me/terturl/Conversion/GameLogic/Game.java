package me.terturl.Conversion.GameLogic;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class Game {
	{
		if (StateManager.getState() == GameState.WAITING) {
			for (Player players : Bukkit.getServer().getOnlinePlayers()) {
				players.setGameMode(GameMode.ADVENTURE);
				players.setHealth(20);
				players.getInventory().clear();

			}
		}
		if (StateManager.getState() == GameState.WAITING);
	}
}