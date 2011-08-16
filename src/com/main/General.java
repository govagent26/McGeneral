package com.main;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.main.TimePlugin.TimeCommand;
import com.main.TimePlugin.TimeData; // Time Plugin
import com.main.WhoPlugin.WhoCommand;

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
	
	// /** The {@link #config} variable is used to access the <b>Config</b> class */
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
	 * <b>Second:</b> A new class is created for each data file to store data. Variable
	 * that store external plugin data.
	 * <br>
	 * {@link #timeData}, 
	 * <br>
	 * <b>Lastly:</b> A message is logged to the command prompt to inform the
	 * server owner that all data has been successfully loaded.
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
	
	/**
	 * The {@link #getCommands()} method is called to register all commands and redirect
	 * them to the individual command handler classes.
	 */
	private void getCommands() {
		getCommand("time").setExecutor(new TimeCommand(this, timeData));
		getCommand("who").setExecutor(new WhoCommand(this));
	}
	
	/**
	 * The {@link #sendMessage(CommandSender, String)} method is called to send a message
	 * to an individual sender.
	 * 
	 * @param sender the sender to display the message to
	 * @param message the message to be displayed to the sender
	 */
	public void sendMessage(CommandSender sender, String message) {
		sender.sendMessage(message);
	}
	
	/**
	 * The {@link #broadcast(String)} method is called to send a message to all players
	 * on the server.
	 * 
	 * @param message the message to be broadcasted
	 */
	public void broadcast(String message) {
		getServer().broadcastMessage(message);
	}
}