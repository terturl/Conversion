package me.terturl.Conversion;

import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;


public class Items implements Listener {

	public static Conversion plugin;
	
	public static ItemStack getBook() {
		ItemStack is = new ItemStack(Material.WRITTEN_BOOK, 1);
		is.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		BookMeta bm = (BookMeta) is.getItemMeta();
		bm.setAuthor("the Creators");
		bm.setTitle(ChatColor.GREEN + "CONVERSION!");
		ArrayList<String> pages = new ArrayList<String>();
		pages.add(ChatColor.DARK_PURPLE + "Conversion" + ChatColor.BLUE
				+ " is a custom server gametype created by terturl"
				+ ChatColor.GREEN + "\n\n\n\nConversion" + ChatColor.BLUE
				+ " is a game of skill and strategy!");
		pages.add(ChatColor.BLUE
				+ "The way to win "
				+ ChatColor.DARK_PURPLE
				+ "Conversion "
				+ ChatColor.BLUE
				+ "is to convert all players to your team!" + ChatColor.GOLD.toString() + "\n\nYou do this by hitting opponents with snowballs, which converts them to your team! \nThey can then start helping you to win!");

		bm.setPages(pages);
		is.setItemMeta(bm);
		return is;
	}
}
