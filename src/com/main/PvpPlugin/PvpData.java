package com.main.PvpPlugin;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.main.McConfig;

/**
 * The <b>PvpData</b> class is used to store all the pvp data from the external yaml
 * file and each player's pvp status.
 */
public class PvpData {
	
	/** The {@link #nodes} variable holds the strings for accessing the data file nodes */
	private String[] nodes = {"pvp.enabled.cooldown", "pvp.disabled.cooldown"};
	
	/** The {@link #config} variable is used to access the <b>Config</b> class */
	private McConfig config;
	
	/** The {@link #players} variable holds each player's pvp status and cooldowns */
	private HashMap<Player, PvpInfoData> players;
	
	/** The {@link #enabledcooldown} variable stores the enabled cooldown set by the external file */
	private long enabledcooldown;
	
	/** The {@link #disabledcooldown} variable stores the disabled cooldown set by the external file */
	private long disabledcooldown;
	
	/**
	 * The {@link #PvpData(McConfig)} constructor is called when the plugin is
	 * initialized.
	 * <p>
	 * The constructor stores the {@link #config} variable for easy future access.
	 * It also initializes the {@link #players} HashMap variable. It then calls 
	 * the {@link #readPvpData()} method to read data from the external yaml file 
	 * and store it in this class.
	 * 
	 * @param config the configuration variable that can access the prefix data
	 */
	public PvpData(McConfig config) {
		players = new HashMap<Player, PvpInfoData>();
		this.config = config;
		readPvpData();
	}
	
	/**
	 * This {@link #readPvpData()} method is called when the data from the external 
	 * files needs to be read.
	 * <p>
	 * Data is read from the external yaml file and stored in many variables in this 
	 * class. It is stored here for easy future access when determining what the 
	 * user settings are.
	 * <p>
	 * The variable data is stored in {@link #enabledcooldown},
	 * {@link #disabledcooldown}.
	 */
	public void readPvpData() {
		config.load();
		enabledcooldown = (config.getInt(nodes[0], 0) * 60000);
		disabledcooldown = (config.getInt(nodes[1], 0) * 60000);
		config.save();
	}
	
	/**
	 * The {@link #addPlayer(Player)} method is called by methods outside of this
	 * class in order to add a player to the {@link #players} list.
	 * 
	 * @param player the player who needs to be added to the {@link #players} list
	 */
	public void addPlayer(Player player) {
		if (!players.containsKey(player)) {
			players.put(player, new PvpInfoData());
		}
	}
	
	/**
	 * The {@link #switchPvpStatus(Player)} method is called to switch the player's pvp
	 * status.
	 * 
	 * @param player the player whose pvp status is being switched
	 */
	public void switchPvpStatus(Player player) {
		players.get(player).switchPvp();
		setPvpCooldown(player, players.get(player).getPvp());
	}
	
	/**
	 * The {@link #getPvpStatus(Player)} method is called to retrieve the pvp status of
	 * the player.
	 * 
	 * @param player the player whose pvp status is being retrieved
	 * @return true if pvp is enabled for this player, otherwise false
	 */
	public boolean getPvpStatus(Player player) {
		return players.get(player).getPvp();
	}
	
	/**
	 * The {@link #setPvpStatus(Player, boolean)} method is called to set the pvp status
	 * of the player.
	 * 
	 * @param player the player whose pvp status is being changed
	 * @param pvp the player's pvp status(true or false)
	 */
	public void setPvpStatus(Player player, boolean pvp) {
		players.get(player).setPvp(pvp);
		setPvpCooldown(player, pvp);
	}

	/**
	 * The {@link #getPvpCooldown(Player)} method is called to retrieve the time when the
	 * player's cooldown is finished.
	 * 
	 * @param player the player whose pvp cooldown is being retrieved
	 * @return the player's pvp cooldown
	 */
	public long getPvpCooldown(Player player) {
		return players.get(player).getCooldown();
	}
	
	/**
	 * The {@link #setPvpCooldown(Player, boolean)} method is called to set the player's
	 * pvp cooldown.
	 * 
	 * @param player the player whose pvp cooldown is being retrieved
	 * @param enabled whether pvp was enabled or disabled
	 */
	public void setPvpCooldown(Player player, boolean enabled) {
		if (enabled) {
			players.get(player).setCooldown(System.currentTimeMillis() + enabledcooldown);
		} else {
			players.get(player).setCooldown(System.currentTimeMillis() + disabledcooldown);
		}
	}
}