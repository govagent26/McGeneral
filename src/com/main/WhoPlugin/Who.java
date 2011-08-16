package com.main.WhoPlugin;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.main.General;

/**
 * The <b>Who</b> class is called to display the online list of players to an individual 
 * sender or broadcast it to the entire server.
 */
public class Who {
	
	/**
	 * This {@link #tellWho(General)} method is called whenever the time should be broadcasted
	 * to the entire server. It calls the overhead method, 
	 * {@link #tellWho(General, CommandSender)}, to deal with who is online.
	 * 
	 * @param plugin the plugin data for the <b>McGeneral</b> class
	 * @see #tellWho(General, CommandSender)
	 */
	public static void tellWho(General plugin) {
		tellWho(plugin, null);
	}
	
	/**
	 * The {@link #tellWho(General, CommandSender)} method is called to display a list of who
	 * is online to either an individual sender or the entire server.
	 * <br>
	 * To distinguish which, the <b>sender</b> variable will either be populated with a 
	 * legitiment sender or null. If null, the who list will be broadcasted to the entire 
	 * server. Otherwise, the who list will only be sent to the sender.
	 * 
	 * @param plugin the plugin data for the <b>McGeneral</b> class
	 * @param sender the sender of the command if one exists, otherwise null
	 */
	public static void tellWho(General plugin, CommandSender sender) {
		Player[] players = plugin.getServer().getOnlinePlayers();
		int playercount = players.length;
    	
		if (playercount == 0) {
			plugin.sendMessage(sender, "There is no one online at this time.");
		} else {
			String message = "Player list: ";
			
			for (Player player : players) {
				message += player.getDisplayName() + ", ";
			}
			message = message.substring(0, message.length() - 2);
			
			if (sender == null) {
				plugin.broadcast("There are " + playercount + " players online.");
				plugin.broadcast(message);
			} else {
				plugin.sendMessage(sender, "There are " + playercount + " players online.");
				plugin.sendMessage(sender, message);
			}
		}
	}
}