package com.main.EmotePlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.main.General;

/**
 * The <b>EmoteCommand</b> class is called whenever a player or console uses the emote
 * command. If the console uses the command, the call will be cancelled.
 */
public class EmoteCommand implements CommandExecutor {

	/** The {@link #plugin} variable holds the instance of the <b>McGeneral</b> plugin */
	private General plugin;
	
	/**
	 * The {@link #EmoteCommand(General)} constructor is called whenever a player uses an 
	 * emote command. 
	 * <p>
	 * The constructor stores the {@link #plugin} data locally for easy access by the 
	 * methods in this class.
	 * 
	 * @param plugin the plugin data for the <b>McGeneral</b> class
	 */
	public EmoteCommand (General plugin) {
		this.plugin = plugin;
	}
	
	/**
	 * The {@link #onCommand(CommandSender, Command, String, String[])} method is called to
	 * send an emote message to certain players.
	 * <br>
	 * This method calls the <b>tellEmote</b> method from the <b>Emote</b> class to send the
	 * emote message to all players within a certain radius.
	 * 
	 * @param sender the sender who entered the emote command
	 * @param command the overhead command that was entered(always 'emote')
	 * @param label the command actually typed in by the sender
	 * @param args anything typed in after the original command
	 * @return true if the sender is a player, otherwise false
	 */
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			String message = "";
			
			for (String arg : args) {
				message += arg + " ";
			}
    		Emote.tellEmote(plugin, message.trim(), 30, (Player)sender);
    		return true;
    	}
    	return false;    	
    }
}