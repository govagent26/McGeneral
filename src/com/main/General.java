package com.main;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.main.AliasPlugin.AliasCommand; // Alias Plugin
import com.main.AliasPlugin.AliasData; // "          "
import com.main.EmotePlugin.EmoteCommand; // Emote Plugin
import com.main.NearDeathPlugin.NearDeathCommand; // Near Death Plugin
import com.main.NearDeathPlugin.NearDeathData; // "          "
import com.main.PrefixPlugin.PrefixCommand; // Prefix Plugin
import com.main.PrefixPlugin.PrefixData; // "          "
import com.main.PvpPlugin.PvpCommand; // Pvp Plugin
import com.main.PvpPlugin.PvpData; // "          "
import com.main.RandomPlugin.RandomCommand; // Random Plugin
import com.main.SaveBackupPlugin.SaveBackupCommand; // Save Backup Data
import com.main.SaveBackupPlugin.SaveBackupData; // "          "
import com.main.TimePlugin.TimeCommand; // Time Plugin
import com.main.TimePlugin.TimeData; // "          "
import com.main.Uptime.Uptime; // Uptime Plugin
import com.main.Uptime.UptimeCommand; // "          "
import com.main.WhoPlugin.WhoCommand; // Who Plugin

/**
 * The <b>General</b> class is the main class of the <b>McGeneral</b> plugin.
 * <p>
 * This is where the plugin is initialized and all data initially created.
 */
public class General extends JavaPlugin {
	
	/** The {@link #log} variable is used to output information to the command prompt */
	private static Logger log = Logger.getLogger("Minecraft");
	
	/** The {@link #timeData} variable holds the class that stores all the time data */
	private TimeData timeData;
	
	/** The {@link #neardeathData} variable holds the class that stores all the near death data */
	private NearDeathData neardeathData;
	
	/** The {@link #aliasData} variable holds the class that stores all the alias data */
	private AliasData aliasData;
	
	/** The {@link #prefixData} variable holds the class that stores all the prefix data */
	private PrefixData prefixData;
	
	/** The {@link #pvpData} variable holds the class that stores all the pvp data */
	private PvpData pvpData;
	
	/** The {@link #savebackupData} variable holds the class that stores all the save backup data */
	private SaveBackupData savebackupData;
	
	/** The {@link #uptime} variable holds the time when the server was started */
	private long uptime;
	
	/** The {@link #tick} variable holds the class that runs repeating tasks */
	private Tick tick;

	/**
	 * The {@link #onDisable()} method is called moments before the plugin
	 * is shut down. 
	 * <p>
	 * It is used to cancel tasks or save data before the plugin shuts down.
	 */
	@Override
	public void onDisable() {
		Uptime.storeUptimeData(getDataFolder(), uptime);
		tick.setRunning(false);
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
		
		uptime = System.currentTimeMillis();
		tick = new Tick(this, getDataFolder(), timeData, neardeathData, savebackupData);
	}
	
	/**
	 * The {@link #dataReaderInitializer()} method is called by the {@link #onEnable()}
	 * method to manage the external files.
	 * <p>
	 * <b>First:</b> This method checks to see if the external files exist. It
	 * will create them if they are not found.
	 * <br>
	 * <b>Second:</b> A new class is created for each data file to store data. Variables
	 * that store external plugin data.
	 * <br>
	 * {@link #timeData}, {@link #neardeathData}, {@link #aliasData}, {@link #prefixData},
	 * {@link #pvpData}, {@link #savebackupData}
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
		timeData = new TimeData(new McConfig(new File(file, "time-settings.yml")));
		neardeathData = new NearDeathData(new McConfig(new File(file, "neardeath-settings.yml")));
		aliasData = new AliasData(this, new McConfig(new File(file, "alias-settings.yml")));
		prefixData = new PrefixData(new McConfig(new File(file, "prefix-settings.yml")));
		pvpData = new PvpData(new McConfig(new File(file, "pvp-settings.yml")));
		savebackupData = new SaveBackupData(new McConfig(new File(file, "savebackups-settings.yml")));
		log.info("[McGeneral]: All data has been loaded from the data files");
	}
	
	/**
	 *	The {@link #registerEvents()} method is called by the {@link #onEnable()}
	 *	method to register all the <b>player</b> event listeners, <b>block</b> 
	 *  event listeners, and <b>weather</b> event listeners.
	 */
	private void registerEvents() {
		PlayerListener pL = new McPlayerListener(this, aliasData, prefixData, pvpData);
		
		PluginManager pm = getServer().getPluginManager();
    	
		pm.registerEvent(Event.Type.PLAYER_JOIN, pL, Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_CHAT, pL, Priority.Normal, this);
		
	}
	
	/**
	 * The {@link #getCommands()} method registers all commands and redirect them to the
	 * individual command handler classes. 
	 * <br>
	 * Plugins with command handlers:
	 * <br>
	 * <b>AliasCommand</b>, <b>EmoteCommand</b>, <b>NearDeathCommand</b>, 
	 * <b>PrefixCommand</b>, <b>PvpCommand</b>, <b>RandomCommand</b>, <b>SaveBackupCommand</b>,
	 * <b>TimeCommand</b>, <b>UptimeCommand</b>, <b>WhoCommand</b>,
	 */
	private void getCommands() {
		getCommand("alias").setExecutor(new AliasCommand(this, aliasData));
		getCommand("emote").setExecutor(new EmoteCommand(this));
		getCommand("neardeath").setExecutor(new NearDeathCommand(this, neardeathData));
		getCommand("prefix").setExecutor(new PrefixCommand(this, prefixData));
		getCommand("pvp").setExecutor(new PvpCommand(this, pvpData));
		getCommand("random").setExecutor(new RandomCommand(this));
		getCommand("savebackup").setExecutor(new SaveBackupCommand(this, savebackupData));
		getCommand("time").setExecutor(new TimeCommand(this, timeData));
		getCommand("uptime").setExecutor(new UptimeCommand(this, uptime));
		getCommand("who").setExecutor(new WhoCommand(this));
	}
	
	/**
	 * The {@link #sendMessage(String)} method logs a message to the console.
	 * 
	 * @param message the message to be logged
	 * @see #sendMessage(CommandSender, String)
	 * @see #sendMessage(Player, String)
	 * @see #sendMessage(Player, String, int)
	 */
	public void sendMessage(String message) {
		log.info(message);
	}
	
	/**
	 * The {@link #sendMessage(CommandSender, String)} method sends a message to an individual
	 * sender.
	 * <p>
	 * This method attempts to find out who the sender is. If it is a player, then the
	 * {@link #sendMessage(Player, String)} method is called to see the message.
	 * Otherwise, the sender is assumed to be the command prompt and thus the message is
	 * logged to the console by calling the {@link #sendMessage(String)} method.
	 * 
	 * @param sender the sender to display the message to
	 * @param message the message to be displayed to the sender
	 * @see #sendMessage(String)
	 * @see #sendMessage(Player, String)
	 * @see #sendMessage(Player, String, int)
	 */
	public void sendMessage(CommandSender sender, String message) {
		if (sender instanceof Player) {
			sendMessage((Player)sender, message);
		} else {
			sendMessage(message);
		}
	}
	
	/**
	 * The {@link #sendMessage(Player, String)} method sends a message to a player.
	 * 
	 * @param player the player to display the message to
	 * @param message the message to be displayed
	 * @see #sendMessage(String)
	 * @see #sendMessage(CommandSender, String)
	 * @see #sendMessage(Player, String, int)
	 */
	public void sendMessage(Player player, String message) {
		player.sendMessage(message);
	}
	
	/**
	 * The {@link #sendMessage(Player, String, int)} method sends a message to the sender
	 * and to all players with the <b>radius</b> of the sender.
	 * <p>
	 * After generating a list of entities in the <b>radius</b>, the entity is checked
	 * to see if it is a player. If so, the {@link #sendMessage(Player, String)} method
	 * is called to send the message to that player.
	 * 
	 * @param player the player to calculate the radius around and send a message to
	 * @param message the message to send to all players within the radius
	 * @param radius the distance to check for additional players
	 * @see #sendMessage(String)
	 * @see #sendMessage(CommandSender, String)
	 * @see #sendMessage(Player, String)
	 */
	public void sendMessage(Player player, String message, int radius) {
		List<Entity> entities = player.getNearbyEntities(radius, radius, radius);
    	
    	for (Entity entity : entities) {
    		if(entity instanceof Player) {
    			sendMessage((Player)entity, message);
    		}
    	}
    	sendMessage(player, message);
	}
	
	/**
	 * The {@link #broadcast(String)} method sends a message to all players on the server.
	 * 
	 * @param message the message to be broadcasted
	 * @see #broadcast(String, boolean)
	 */
	public void broadcast(String message) {
		getServer().broadcastMessage(message);
	}
	
	/**
	 * The {@link #broadcast(String, boolean)} method sends a message to all players on the
	 * server and possibly to the console.
	 * <p>
	 * If <b>value</b> is true, then the message is logged to the console in addition to
	 * being sent to all players by calling the {@link #sendMessage(String)} method. This
	 * method called the {@link #broadcast(String)} method to send the message to all 
	 * players.
	 * 
	 * @param message the message to be broadcasted
	 * @param value whether or not to log the message to the console
	 * @see #broadcast(String)
	 */
	public void broadcast(String message, boolean value) {
		if (value) {
			sendMessage(message);
		}
		broadcast(message);
	}
}