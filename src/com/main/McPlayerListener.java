package com.main;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

import com.main.AliasPlugin.Alias;
import com.main.AliasPlugin.AliasData;
import com.main.HealthPlugin.Health;
import com.main.PrefixPlugin.Prefix;
import com.main.PrefixPlugin.PrefixData;
import com.main.PvpPlugin.Pvp;
import com.main.PvpPlugin.PvpData;

/**
 * The <b>McPlayerListener</b> class is used to handle all registered <b>player
 * events</b> and process them accordingly.
 */
public class McPlayerListener extends PlayerListener {
	
	/** The {@link #plugin} variable holds the instance of the <b>McGeneral</b> plugin */
	private General plugin;
	
	/** The {@link #aliasData} variable holds the class that stores all the alias data */
	private AliasData aliasData;
	
	/** The {@link #prefixData} variable holds the class that stores all the prefix data */
	private PrefixData prefixData;
	
	/** The {@link #pvpData} variable holds the class that stores all the pvp data */
	private PvpData pvpData;
	
	/**
	 * The {@link #McPlayerListener(General plugin)} constructor is called to
	 * transfer the {@link #plugin} data to this class.
	 * 
	 * @param plugin the instance of the <b>McGeneral</b> plugin
	 * @param aliasData the <b>AliasData</b> class that holds the alias data from the yaml
	 */
	public McPlayerListener(General plugin, AliasData aliasData, PrefixData prefixData, PvpData pvpData) {
		this.plugin = plugin;
		this.aliasData = aliasData;
		this.prefixData = prefixData;
		this.pvpData = pvpData;
	}
	
	/**
	 * The {@link #onPlayerJoin(PlayerJoinEvent)} method is called whenever a
	 * player joins the game. 
	 * <p>
	 * The data from this event is then sent to multiple other classes
	 * for analysis and processing.
	 * 
	 * @param event the {@link #PlayerJoinEvent} that stores all event and player data
	 */
	@Override
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		Alias.setDisplayName(player, aliasData);
		Pvp.setPvp(player, pvpData);
	}
	
	public void onPlayerChat(PlayerChatEvent event) {
		Player player = event.getPlayer();
		String prefix = Prefix.getPrefix(player, prefixData);
		
		event.setFormat((prefix == null ? "" : prefix) + event.getFormat());
		event.setMessage(Health.getHealthChat(player) + event.getMessage());
	}
} 