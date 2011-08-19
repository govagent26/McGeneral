package com.main.PvpPlugin;

import org.bukkit.entity.Player;

/**
 * The <b>Pvp</b> class is called to register a player with the <b>PvpData</b> class
 * or retrieve player pvp data.
 */
public class Pvp {

	/**
	 * The {@link #setPvp(Player, PvpData)} method is called to set the pvp data in the 
	 * <b>PvpData</b> class.
	 * 
	 * @param player the player who needs to be registered in the <b>PvpData</b> class
	 * @param pvpData the <b>PvpData</b> class that holds the pvp data from the yaml
	 */
	public static void setPvp(Player player, PvpData pvpData) {
		pvpData.addPlayer(player);
	}
	
	/**
	 * The {@link #getPvpStatus(Player, PvpData)} method is called to retrieve the pvp
	 * status of the player from the <b>PvpData</b> class.
	 * 
	 * @param player the player whose pvp status is being retrieved
	 * @param pvpData the <b>PvpData</b> class that holds the pvp data from the yaml
	 * @return the player's pvp status(true or false)
	 */
	public static boolean getPvpStatus(Player player, PvpData pvpData) {
		return pvpData.getPvpStatus(player);
	}
	
	/**
	 * The {@link #setPvpStatus(Player, PvpData, boolean)} method is called to set the
	 * pvp status of the player in the <b>PvpData</b> class.
	 * 
	 * @param player the player whose pvp status is being changed
	 * @param pvpData the <b>PvpData</b> class that holds the pvp data from the yaml
	 * @param pvp the player's pvp status(true or false)
	 */
	public static void setPvpStatus(Player player, PvpData pvpData, boolean pvp) {
		pvpData.setPvpStatus(player, pvp);
	}
}