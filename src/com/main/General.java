package com.main;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.main.TimePlugin.Time;
import com.main.TimePlugin.TimeData; // Time Plugin

/**
 * The <b>General</b> class is the main class of the <b>McGeneral</b> plugin.
 * <p>
 * This is where the plugin is initialized and all data initially created.
 */
public class General extends JavaPlugin {
	
	/** The {@link #log} variable is used to output information to the command prompt */
	private static Logger log = Logger.getLogger("Minecraft");
	
	/** The {@link #timeData} variable holds the class that stores all the time data */
	TimeData timeData;
	
	/** The {@link #config} variable is used to access the <b>Config</b> class */
	// private static Config config; <--- moved to each individual data class

	/**
	 * The {@link #onDisable()} method is called moments before the plugin
	 * is shut down. 
	 * <p>
	 * It is used to cancel tasks or save data before the plugin shuts down.
	 */
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
	}

	/**
	 * The {@link #onEnable()} method is called the moment the plugin is
	 * initialized. Many different functions are performed in this method.
	 * <p>
	 * 
	 */
	@Override
	public void onEnable() {
		dataReaderInitializer();
		registerEvents();
		getCommands();
		
	}
	
	/**
	 * The {@link #dataReaderInitializer()} method is called by the {@link #onEnable()}
	 * method to manage the external files.
	 * <p>
	 * <b>First:</b> This method checks to see if the external files exist. It
	 * will create them if they are not found.
	 * <br>
	 * <b>Second:</b> A new class is created for each data file to store data.
	 */
	public void dataReaderInitializer() {
		File file = getDataFolder();
		
		if (!file.exists()) {
			if (!file.mkdirs()) {
				log.log(Level.SEVERE, "The external files could not be created. Operation aborted.");
				return;
			}
		}
		timeData = new TimeData(new Config(new File(file, "time-settings.yml")));
		
		log.info("[McGeneral]: All data has been loaded from the data files");
	}
	
	/**
	 *	The {@link #registerEvents()} method is called by the {@link #onEnable()}
	 *	method to register all the <b>player</b> event listeners, <b>block</b> 
	 *  event listeners, and <b>weather</b> event listeners.
	 */
	private void registerEvents() {
		PlayerListener pL = new McPlayerListener(this);
		
		PluginManager pm = getServer().getPluginManager();
    	
    	pm.registerEvent(Event.Type.PLAYER_JOIN, pL, Priority.Normal, this);
		
	}
	
	private void getCommands() {
		getCommand("time").setExecutor(new Time(this, timeData));
	}
}