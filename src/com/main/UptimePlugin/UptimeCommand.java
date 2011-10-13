package com.main.Uptime;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.main.General;

/**
 * The <b>UptimeCommand</b> class is called whenever a player or the console uses the 
 * uptime command.
 */
public class UptimeCommand implements CommandExecutor {
	
	/** The {@link #plugin} variable holds the instance of the <b>McGeneral</b> plugin */
	private General plugin;
	
	/** The {@link #uptime} variable holds the time when the server was started */
	private long uptime;
	
	/**
	 * The {@link #UptimeCommand(General, long)} constructor is called whenever a player
	 * uses the uptime command.
	 * <p>
	 * The constructor stores the {@link #plugin} data and {@link #uptime} data locally
	 * for easy access by the methods in this class.
	 * 
	 * @param plugin the plugin data for the <b>McGeneral</b> class
	 * @param uptime the time when this plugin was initialized(when the server was started)
	 */
	public UptimeCommand(General plugin, long uptime) {
		this.plugin = plugin;
		this.uptime = uptime;
	}
	
	/**
	 * The {@link #onCommand(CommandSender, Command, String, String[])} method is called 
	 * when a sender requests how long the server has been running.
	 * <br>
	 * This method calls the <b>reportTime</b> method from the <b>Uptime</b> class to
	 * display the how long the server has been running to the sender.
	 * 
	 * @param sender the sender who entered the uptime command
	 * @param command the overhead command that was entered(always 'uptime')
	 * @param label the command actually typed in by the sender
	 * @param args anything typed in after the original command
	 * @return true, always
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Uptime.reportUptime(plugin, uptime, sender);
		return true;
	}
}