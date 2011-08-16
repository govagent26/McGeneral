package com.main.WhoPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

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
	 * The {@link #onCommand(CommandSender, Command, String, String[])} method is called 
	 * when a sender requests a list of online players.
	 * <br>
	 * This method calls the <b>tellWho</b> method from the <b>Who</b> class to display the
	 * online list of players to the sender.
	 * 
	 * @param sender the sender who entered the who command
	 * @param command the overhead command that was entered(always 'who')
	 * @param label the command actually typed in by the sender
	 * @param args anything typed in after the original command
	 * @return true, always
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Who.tellWho(plugin, sender);
		return true;
	}
}