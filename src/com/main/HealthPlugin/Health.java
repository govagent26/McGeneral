package com.main.HealthPlugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.main.General;

/**
 * The <b>HealthPlugin</b> is used to format the health of a player in the form of a
 * string and announce near death messages to players with low health.
 */
public class Health {

	/**
	 * The {@link #getHealthChat(Player)} method is called to turn a player's health
	 * into a string.
	 * <p>
	 * The method formats the health into a fractional number with a denominator of 10.
	 * The color of the numbers is based on the numerator. If the numerator is high, it
	 * is close to dark green. If the numerator is low, it is close to dark red.
	 * 
	 * @param player the player whose health is being converted
	 * @return the health converted into a string with colors
	 */
	public static String getHealthChat(Player player) {
		String health = "[";
		
		switch ((int)(player.getHealth() / 4)) {
			case 0:
				health = (ChatColor.DARK_RED.toString() + (double)player.getHealth() / 2);
				break;
			case 1:
				health = (ChatColor.RED.toString() + (double)player.getHealth() / 2);
				break;
			case 2:
				health = (ChatColor.YELLOW.toString() + (double)player.getHealth() / 2);
				break;
			case 3:
				health = (ChatColor.GREEN.toString() + (double)player.getHealth() / 2);
				break;
			default:
				health = (ChatColor.DARK_GREEN.toString() + (double)player.getHealth() / 2);
				break;
		}
		return (health + "/10" + ChatColor.WHITE.toString() + "]: ");
	}
	
	/**
	 * The {@link #processTick(General, HealthData, int)} method is called to check the current
	 * second against the near death announcing interval.
	 * <p>
	 * This method generates a list of players on the server and checks the health of each one.
	 * If the health is below the set near death ammount, then a random message will be
	 * displayed to that player and all players near him/her.
	 * 
	 * @param plugin the plugin data for the <b>McGeneral</b> class
	 * @param healthData the <b>HealthData</b> class that holds the health settings from the yaml
	 * @param second the current second to compare with the interval
	 */
	public static void processTick(General plugin, HealthData healthData, int second) {
		if (second % healthData.getInterval() == 0) {
			Player[] players = plugin.getServer().getOnlinePlayers();
			int health = healthData.getAmmount();
			
			for (Player player : players) {
				if (player.getHealth() <= health) {
					plugin.sendMessage(player, healthData.getRandomMessage(), 20);
				}
			}
		}
	}
}