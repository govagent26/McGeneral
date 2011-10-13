package com.main.LowHealthPlugin;

import org.bukkit.entity.Player;

import com.main.General;

/**
 * The <b>LowHealth</b> class is used to send low health messages to players at
 * certain time intervals.
 */
public class LowHealth {

	/**
	 * The {@link #processTick(General, LowHealthData, int)} method is called
	 * to check the current second against the low health announcing interval.
	 * <p>
	 * This method generates a list of players on the server and checks the
	 * health of each one. If the health is below the set low health level
	 * ammount, then a random message will be displayed to that player and
	 * all players near him/her.
	 * 
	 * @param plugin the plugin data for the <b>McGeneral</b> class
	 * @param lowhealthData the <b>LowHealthData</b> class that holds the health settings from the yaml
	 * @param second the current second to compare with the interval
	 */
	public static void processTick(General plugin, LowHealthData lowhealthData, int second) {
		if (lowhealthData.getAnnounceStatus() && second % lowhealthData.getInterval() == 0) {
			Player[] players = plugin.getServer().getOnlinePlayers();
			int health = lowhealthData.getHealth();
			
			for (Player player : players) {
				if (player.getHealth() <= health) {
					plugin.sendMessage(player, lowhealthData.getRandomMessage(), 20);
				}
			}
		}
	}
}