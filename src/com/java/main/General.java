package com.java.main;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class General extends JavaPlugin {

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onEnable() {
		
		registerEvents();
		
	}
	
	private void registerEvents() {
		PlayerListener pL = new McPlayerListener(this);
		
		PluginManager pm = getServer().getPluginManager();
    	
    	pm.registerEvent(Event.Type.PLAYER_JOIN, pL, Priority.Normal, this);
		
	}
}