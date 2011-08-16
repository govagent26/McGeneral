package com.main.EmotePlugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.main.General;

/**
 * The <b>Emote</b> class is called to properly format the emote message and send it to 
 * players within a certain radius.
 */
public class Emote {

	/**
	 * The {@link #tellEmote(General, String, int, Player)} method is called to prepare an 
	 * emote message and then send it to players in a certain radius.
	 * 
	 * @param plugin the plugin data for the <b>McGeneral</b> class
	 * @param message the emote message which has to be prepared
	 * @param radius the radius at which to look for players to send the emote
	 * @param player the player who sent out the emote
	 */
	public static void tellEmote(General plugin, String message, int radius, Player player) {
    	String preparedmessage = ChatColor.stripColor(player.getDisplayName()) + " " + message;
    	
    	plugin.sendMessage(player, preparedmessage, 30);
	}
}