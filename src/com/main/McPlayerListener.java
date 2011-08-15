package com.main;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

/**
 * The <b>McPlayerListener</b> class is used to handle all registered <b>player
 * events</b> and process them accordingly.
 */
public class McPlayerListener extends PlayerListener {
	
	/** The {@link #plugin} variable holds the instance of the <b>McGeneral</b> plugin */
	General plugin;
	
	/**
	 * The {@link #McPlayerListener(General plugin)} constructor is called to
	 * transfer the {@link #plugin} data to this class.
	 * 
	 * @param plugin the instance of the <b>McGeneral</b> plugin
	 */
	public McPlayerListener(General plugin) {
		this.plugin = plugin;
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
		
	}
} 