package com.main.DeathAnnouncingPlugin;

import org.bukkit.entity.Player;

import com.main.General;

/**
 * The <b>DeathAnnouncing</b> class is used to broadcast when a player has died on the server.
 */
public class DeathAnnouncing {

	/**
	 * The {@link #announceDeath(General, Player)} method announces that a player has died to all
	 * players on the server.
	 * 
	 * @param plugin the plugin data for the <b>McGeneral</b> class
	 * @param player the player who died
	 */
	public static void announceDeath(General plugin, Player player) {
		plugin.broadcast(player.getDisplayName() + " has died a terrible and painful death....");
	}
}