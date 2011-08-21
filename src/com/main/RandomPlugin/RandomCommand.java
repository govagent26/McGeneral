package com.main.RandomPlugin;

import java.util.Random;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.main.General;

/**
 * The <b>RandomCommand</b> class is called whenever a player or the console uses a random 
 * command.
 */
public class RandomCommand implements CommandExecutor {

	/** The {@link #plugin} variable holds the instance of the <b>McGeneral</b> plugin */
	private General plugin;
	
	/**
	 * The {@link #RandomCommand(General)} constructor is called whenever a player
	 * uses a random command. 
	 * <p>
	 * The constructor stores the {@link #plugin} data locally for easy access by the 
	 * methods in this class.
	 * 
	 * @param plugin the plugin data for the <b>McGeneral</b> class
	 */
	public RandomCommand(General plugin) {
		this.plugin = plugin;
	}

	/**
	 * The {@link #onCommand(CommandSender, Command, String, String[])} method is used to
	 * send a randomized number to the sender.
	 * 
	 * @param sender the sender who entered the random command
	 * @param command the overhead command that was entered(always 'random')
	 * @param label the command actually typed in by the sender
	 * @param args anything typed in after the original command
	 * @return true if the entered command is valid, otherwise false
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Random random = new Random();
		int max = 100;
		
		if (args.length > 0) {
			try {
				max = Integer.parseInt(args[0]);
			} catch (Exception e) {
				
			}
		}
		if (sender instanceof Player) {
			plugin.sendMessage((Player)sender, ((Player)sender).getDisplayName() + " rolled a " + (random.nextInt(max) + 1), 30);
		} else {
			plugin.sendMessage(sender, "You rolled a " + (random.nextInt(max) + 1));
		}
		return true;
	}
}