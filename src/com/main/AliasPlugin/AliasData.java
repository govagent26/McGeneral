package com.main.AliasPlugin;

import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.main.McConfig;

/**
 * The <b>AliasData</b> class is used to store all the alias data that is in the
 * external alias data file.
 */
public class AliasData {
	
	/** The {@link #nodes} variable holds the strings for accessing the data file nodes */
	private String[] nodes = {"players", "players.", ".color", ".alias"};
	
	/** The {@link #config} variable is used to access the <b>Config</b> class */
	private McConfig config;
	
	/** The {@link #displaynames} variable holds each player's name and class that stores the alias data */
	private HashMap<String, AliasDisplayNameData> displaynames;
	
	/**
	 * The {@link #AliasData(McConfig)} constructor is called when the plugin is
	 * initialized.
	 * <p>
	 * The constructor stores the {@link #config} variable for easy future access.
	 * It also initializes the {@link #displaynames} HashMap variable. It then calls 
	 * the {@link #readAliasData()} method to read data from the external yaml file 
	 * and store it in this class.
	 * 
	 * @param config the configuration variable that can access the time data
	 */
	public AliasData(McConfig config) {
		displaynames = new HashMap<String, AliasDisplayNameData>();
		this.config = config;
		readAliasData();
	}
	
	/**
	 * This {@link #readAliasData(McConfig)} method is called when the data from the
	 * external files needs to be read.
	 * <p>
	 * This method begins by retrieving a list of all players from the external yaml
	 * file. It then individually adds each player, if there are any, to 
	 * {@link #displaynames} for easy future access.
	 */
	public void readAliasData() {
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
	 * The {@link #addPlayer(String)} method is called whenever a player needs to be
	 * added to the {@link #displaynames} list of players.
	 * <p>
	 * This method accesses the external yaml in an attempt to read the player data. If
	 * none exists, defaults are entered. Then the player information is stored in
	 * {@link #displaynames} for easy future access.
	 * 
	 * @param player the name of the player to be added to the {@link #displaynames} list
	 */
	public void addPlayer(String player) {
		ChatColor color;
		String alias;
		
		config.load();
		try {
			color = ChatColor.valueOf(config.getString(nodes[1] + player + nodes[2], "white").toUpperCase());
		} catch (Exception e) {
			color = ChatColor.WHITE;
		}
		alias = config.getString(nodes[1] + player + nodes[3], null);
		displaynames.put(player, new AliasDisplayNameData(color, alias));
		config.save();
	}
	
	/**
	 * The {@link #getAlias(Player)} method is called to retrieve the alias of the
	 * player from the {@link #displaynames} list.
	 * 
	 * @param player the player whose alias is being retrieved
	 * @return the alias of the player(without color)
	 */
	public String getAlias(Player player) {
		return displaynames.get(player.getName()).getAlias();
	}
	
	/**
	 * The {@link #setAlias(Player, String)} method changes the {@link #displaynames}
	 * list and edits the external yaml file. It then sets the player's display name
	 * on the server.
	 * 
	 * @param player the player whose alias is being changed
	 * @param alias what the player's alias is being changed to
	 */
	public void setAlias(Player player, String alias) {
		String name = player.getName();
		
		config.load();
		config.setProperty(nodes[1] + name + nodes[3], alias);
		config.save();
		displaynames.get(name).setAlias(alias);
		player.setDisplayName(displaynames.get(name).getDisplayName());
	}
	
	/**
	 * The {@link #getColor(Player)} method is called to retrieve the color of the
	 * player from the {@link #displaynames} list.
	 * 
	 * @param player the player whose color is being retrieved
	 * @return the color of the player
	 */
	public String getColor(Player player) {
		return displaynames.get(player.getName()).getColor().toString();
	}
	
	/**
	 * The {@link #setColor(Player, String)} method changes the {@link #displaynames}
	 * list and edits the external yaml file. It then sets the player's display name
	 * on the server.
	 * 
	 * @param player the player whose alias is being changed
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
		displaynames.get(name).setColor(chatcolor);
		player.setDisplayName(displaynames.get(name).getDisplayName());
		return true;
	}
}