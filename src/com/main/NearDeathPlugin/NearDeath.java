package com.main.NearDeathPlugin;

import org.bukkit.entity.Player;

import com.main.General;

/**
 * The <b>NearDeath</b> class is used to send near death messages to players at
 * certain time intervals.
 */
public class NearDeath {

	/**
	 * The {@link #processTick(General, NearDeathData, int)} method is called
	 * to check the current second against the near death announcing interval.
	 * <p>
	 * This method generates a list of players on the server and checks the
	 * health of each one. If the health is below the set near death health
	 * ammount, then a random message will be displayed to that player and
	 * all players near him/her.
	 * 
	 * @param plugin the plugin data for the <b>McGeneral</b> class
	 * @param neardeathData the <b>NearDeathData</b> class that holds the health settings from the yaml
	 * @param second the current second to compare with the interval
	 */
	public static void processTick(General plugin, NearDeathData neardeathData, int second) {
		if (neardeathData.getAnnounceStatus() && second % neardeathData.getInterval() == 0) {
			Player[] players = plugin.getServer().getOnlinePlayers();
			int health = neardeathData.getHealth();
			
			for (Player player : players) {
				if (player.getHealth() <= health) {
					plugin.sendMessage(player, neardeathData.getRandomMessage(), 20);
				}
			}
		}
	}
}