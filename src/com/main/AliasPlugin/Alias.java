package com.main.AliasPlugin;

import org.bukkit.entity.Player;

/**
 * The <b>Alias</b> class is called to register the display name of a player with
 * the <b>AliasData</b> class and on the server.
 */
public class Alias {

	/**
	 * The {@link #setDisplayName(Player, AliasData)} method is called to set the
	 * display name of the player on the server and in the <b>AliasData</b> class.
	 * 
	 * @param player the player whose display name needs to be registered
	 * @param aliasData the <b>AliasData</b> class that holds the alias data from the yaml
	 */
	public static void setDisplayName(Player player, AliasData aliasData) {
		aliasData.addPlayer(player);
	}
}
