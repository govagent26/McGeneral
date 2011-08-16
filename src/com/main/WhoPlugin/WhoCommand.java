package com.main.WhoPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.main.General;

/**
 * The <b>WhoCommand</b> class is called whenever a player or the console uses the who 
 * command.
 */
public class WhoCommand implements CommandExecutor {
	
	/** The {@link #plugin} variable holds the instance of the <b>McGeneral</b> plugin */
	private General plugin;
	
	/**
	 * The {@link #WhoCommand(General)} constructor is called whenever a player uses any 
	 * who command. 
	 * <p>
	 * The constructor stores the {@link #plugin} data locally for easy access by the methods 
	 * in this class.
	 * 
	 * @param plugin the plugin data for the <b>McGeneral</b> class
	 */
	public WhoCommand(General plugin) {
		this.plugin = plugin;
	}

	/**
	 * The {@link #onCommand(CommandSender, Command, String, String[])} method is used to
	 * find out who is online on the server and display a message to the sender.
	 * 
	 * @param sender the sender who entered the time command
	 * @param command the overhead command that was entered(always 'time')
	 * @param label the command actually typed in by the sender
	 * @param args anything typed in after the original command
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player[] players = plugin.getServer().getOnlinePlayers();
		int playercount = players.length;
    	
		if (playercount == 0) {
			plugin.sendMessage(sender, "There is no one online at this time.");
		} else {
			String message = "Player list: ";
			
			for (Player player : players) {
				message += player.getDisplayName() + ", ";
			}
			message = message.substring(0, message.length() - 2);
			
			plugin.sendMessage(sender, "There are " + playercount + " players online.");
			plugin.sendMessage(sender, message);
		}
		return true;
	}
}