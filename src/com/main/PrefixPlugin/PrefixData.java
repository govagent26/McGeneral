package com.main.PrefixPlugin;

import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.main.McConfig;

/**
 * The <b>PrefixData</b> class is used to store all the prefix data that is in the
 * external prefix data file.
 */
public class PrefixData {
	
	/** The {@link #nodes} variable holds the strings for accessing the data file nodes */
	private String[] nodes = {"players", "players.", ".color", ".prefix"};
	
	/** The {@link #config} variable is used to access the <b>Config</b> class */
	private McConfig config;
	
	/** The {@link #prefixes} variable holds each player's prefix and class that stores the prefix data */
	private HashMap<String, PrefixInfoData> prefixes;
	
	/**
	 * The {@link #PrefixData(McConfig)} constructor is called when the plugin is
	 * initialized.
	 * <p>
	 * The constructor stores the {@link #config} variable for easy future access.
	 * It also initializes the {@link #prefixes} HashMap variable. It then calls 
	 * the {@link #readPrefixData()} method to read data from the external yaml file 
	 * and store it in this class.
	 * 
	 * @param config the configuration variable that can access the prefix data
	 */
	public PrefixData(McConfig config) {
		prefixes = new HashMap<String, PrefixInfoData>();
		this.config = config;
		readPrefixData();
	}
	
	/**
	 * This {@link #readPrefixData()} method is called when the data from the
	 * external files needs to be read.
	 * <p>
	 * This method begins by retrieving a list of all players from the external yaml
	 * file. It then individually adds each player, if there are any, to 
	 * {@link #prefixes} for easy future access.
	 */
	public void readPrefixData() {
		config.load();
		List<String> list = config.getKeys(nodes[0]);
		config.save();
		if (list != null) {
			for (int x = 0; x < list.size(); x++) {
				addPlayer(list.get(x));
			}
		}
	}
	
	/**
	 * The {@link #addPlayer(Player)} method is called by methods outside of this
	 * class in order to add a player to the {@link #prefixes} list.
	 * <br>
	 * This method calls {@link #addPlayer(String)} to add the player to the list.
	 * 
	 * @param player the player who needs to be added to the {@link #prefixes} list
	 * @see #addPlayer(String)
	 */
	public void addPlayer(Player player) {
		String name = player.getName();
		
		if (!prefixes.containsKey(name)) {
			addPlayer(name);
		}
	}
	
	/**
	 * The {@link #addPlayer(String)} method is called whenever a player needs to be
	 * added to the {@link #prefixes} list of players.
	 * <p>
	 * This method accesses the external yaml in an attempt to read the player data. If
	 * none exists, defaults are entered. Then the player information is stored in
	 * {@link #prefixes} for easy future access.
	 * 
	 * @param player the name of the player to be added to the {@link #prefixes} list
	 */
	private void addPlayer(String player) {
		ChatColor color;
		String prefix;
		
		config.load();
		try {
			color = ChatColor.valueOf(config.getString(nodes[1] + player + nodes[2], "white").toUpperCase());
		} catch (Exception e) {
			color = ChatColor.WHITE;
		}
		prefix = config.getString(nodes[1] + player + nodes[3], null);
		prefixes.put(player, new PrefixInfoData(color, prefix));
		config.save();
	}
	
	/**
	 * The {@link #getPrefix(Player)} method retrieves the prefix of the
	 * player without color.
	 * 
	 * @param player the player whose prefix is being retrieved
	 * @return the prefix of the player(without color)
	 */
	public String getPrefix(Player player) {
		return prefixes.get(player.getName()).getPrefix();
	}
	
	/**
	 * The {@link #setPrefix(Player, String)} method changes the {@link #prefixes}
	 * list and edits the external yaml file.
	 * 
	 * @param player the player whose prefix is being changed
	 * @param prefix what the player's prefix is being changed to
	 */
	public void setPrefix(Player player, String prefix) {
		String name = player.getName();
		
		config.load();
		config.setProperty(nodes[1] + name + nodes[3], prefix);
		config.save();
		prefixes.get(name).setPrefix(prefix);
	}
	
	/**
	 * The {@link #getColor(Player)} method retrieves the color of the
	 * player from the {@link #prefixes} list.
	 * 
	 * @param player the player whose color is being retrieved
	 * @return the color of the player
	 */
	public String getColor(Player player) {
		return prefixes.get(player.getName()).getColor().name();
	}
	
	/**
	 * The {@link #setColor(Player, String)} method changes the {@link #prefixes}
	 * list and edits the external yaml file.
	 * 
	 * @param player the player whose prefix color is being changed
	 * @param color what the player's color is being changed to
	 */
	public boolean setColor(Player player, String color) {
		ChatColor chatcolor;
		String name = player.getName();
		
		try {
			chatcolor = ChatColor.valueOf(color.toUpperCase());
		} catch (Exception e) {
			return false;
		}
		config.load();
		config.setProperty(nodes[1] + name + nodes[2], color);
		config.save();
		prefixes.get(name).setColor(chatcolor);
		return true;
	}
	
	/**
	 * The {@link #getPrefixWithColor(Player)} method retrieves a player's
	 * prefix. If none has been entered, then null is returned. Otherwise the
	 * prefix(including color) is returned.
	 * 
	 * @param player the player whose prefix is being retrieved
	 * @return null if no prefix exists, otherwise the player's prefix with color
	 */
	public String getPrefixWithColor(Player player) {
		String prefix = getPrefix(player);
		
		if (prefix == null || prefix.isEmpty()) {
			return null;
		}
		return prefixes.get(player.getName()).getPrefixWithColor();
	}
}