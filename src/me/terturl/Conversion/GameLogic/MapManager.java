package me.terturl.Conversion.GameLogic;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class MapManager {

	public static int map = 0;
	public static String worldList[] = { "world" };

	public static World getWorld() {
		switch (map) {
		case 0:
			return Bukkit.getServer().getWorld(worldList[0]);
		}
		return null;
	}

	public static double[][] getRedSpawns() {
		double redspawns[][] = {

				{40, 132, 31, 1, 90},
				{44, 132, 35, 1, 90},
				{40, 132, 39, 1, 90},
				{36, 132, 35, 1, 90},
				
				{-36, 132, 35, 1, 90},
				{-40, 132, 39, 1, 90},
				{-44, 132, 35, 1, 90},
				{-40, 132, 31, 1, 90},
				
				};
		
		switch (map) {
		case 0:
			return redspawns;

		}
		return null;
	}
	
		public static double[][] getBlueSpawns() {
		double bluespawns[][] = {
				{-40, 132, -41, 1, 90},
				{-44, 132, -45, 1, 90},
				{-40, 132, -49, 1, 90},
				{-36, 132, -46, 1, 90},
				
				{36, 132, -45, 1, 90},
				{40, 132, -49, 1, 90},
				{44, 132, -45, 1, 90},
				{40, 132, -41, 1, 90},
		};

		switch (map) {
		case 0:
			return bluespawns;

		}
		return null;
	}

}
