package com.main.ReincarnationPlugin;

import java.util.ArrayList;

import org.bukkit.entity.Player;

/**
 * The <b>Reincarnation</b> class holds the list of players that used their reincarnation
 * for the day.
 */
public class Reincarnation {
	
	/** The {@link #players} variable stores all the players who have reincarnated */
	private static ArrayList<Player> players = new ArrayList<Player>();
	
	/**
	 * The {@link #clearList()} method clears the list of players who have reincarnated.
	 */
	public static void clearList() {
		players.clear();
	}
	
	/**
	 * The {@link #checkAddPlayer(Player)} method checks to see if a player has reincarnated.
	 * If the player did not, the player is added to the list.
	 * 
	 * @param player the player whose reincarnation status is being checked
	 * @return true if the player has reincarnated, otherwise false
	 */
	public static boolean checkAddPlayer(Player player) {
		if (players.contains(player)) {
			return true;
		} else {
			players.add(player);
			return false;
		}
	}

	/**
	 * The {@link #processTick(int)} method checks to see if it is midnight in order to clear
	 * the list of reincarnated players.
	 * 
	 * @param hour the current hour of the day
	 */
	public static void processTick(int hour) {
		if (hour == 0) {
			clearList();
		}
	}
}