package com.main.PrefixPlugin;

import org.bukkit.entity.Player;

import com.main.PrefixPlugin.PrefixData;

/**
 * The <b>Prefix</b> class is called to register the prefix of a player or retrieve it
 * from the <b>PrefixData</b> class.
 */
public class Prefix {

	/**
	 * The {@link #setPrefix(Player, PrefixData)} method is called to set the
	 * prefix data in the <b>PrefixData</b> class.
	 * 
	 * @param player the player whose prefix needs to be registered
	 * @param prefixData the <b>PrefixData</b> class that holds the prefix data from the yaml
	 */
	public static void setPrefix(Player player, PrefixData prefixData) {
		prefixData.addPlayer(player);
	}
	
	/**
	 * The {@link #getPrefix(Player, PrefixData)} method is called to retrieve the prefix
	 * from the <b>PrefixData</b> class.
	 * 
	 * @param player the player whose prefix is being retrieved
	 * @param prefixData the <b>PrefixData</b> class that holds the prefix data from the yaml
	 * @return the player's prefix(can be null if none exists)
	 */
	public static String getPrefix(Player player, PrefixData prefixData) {
		return prefixData.getPrefixWithColor(player);
	}
}