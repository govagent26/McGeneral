package com.main.PrefixPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.main.General;
import com.main.PrefixPlugin.PrefixData;

/**
 * The <b>PrefixCommand</b> class is called whenever a player or the console uses any prefix 
 * command.
 */
public class PrefixCommand implements CommandExecutor {
	
	/** The {@link #plugin} variable holds the instance of the <b>McGeneral</b> plugin */
	private General plugin;
	
	/** The {@link #prefixData} variable holds the class that stores all the prefix data */
	private PrefixData prefixData;
	
	/**
	 * The {@link #PrefixCommand(General, PrefixData)} constructor is called whenever a player
	 * uses any prefix command. 
	 * <p>
	 * The constructor stores the {@link #plugin} data and {@link #prefixData} data locally
	 * for easy access by the methods in this class.
	 * 
	 * @param plugin the plugin data for the <b>McGeneral</b> class
	 * @param prefixData the <b>PrefixData</b> class that holds the prefix data from the yaml
	 */
	public PrefixCommand(General plugin, PrefixData prefixData) {
		this.plugin = plugin;
		this.prefixData = prefixData;
	}
	
	/**
	 * The {@link #onCommand(CommandSender, Command, String, String[])} method is used to
	 * analize the prefix command that was entered and send it to the appropriate method.
	 * <p>
	 * If <b>'p'</b> or <b>'prefix'</b> is entered, {@link #p(Player)} is called to
	 * handle the entered command.
	 * <br>
	 * If <b>'pc'</b> is entered, {@link #pc(Player, String[])} is called to handle
	 * the entered command.
	 * <br>
	 * If <b>'pn'</b> is entered, {@link #pn(Player, String[])} is called to handle
	 * the entered command.
	 * <br>
	 * If <b>'pr'</b> is entered, {@link #pr(CommandSender)} is called to handle
	 * the entered command.
	 * <br>
	 * If <b>'ph'</b> is entered, the usage data from the <b>plugin.yml</b> file is shown
	 * to the sender.
	 * 
	 * @param sender the sender who entered the prefix command
	 * @param command the overhead command that was entered(always 'prefix')
	 * @param label the command actually typed in by the sender
	 * @param args anything typed in after the original command
	 * @return true if the entered command is valid, otherwise false
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equals("pr")) {
			pr(sender);
			return true;
		}
		if (sender instanceof Player) {
			if (label.equals("p") || label.equals("prefix")) {
				p((Player)sender);
			} else if (label.equals("pc")) {
				pc((Player)sender, args);
			} else if (label.equals("pn")) {
				pn((Player)sender, args);
			} else if (label.equals("ph")) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	/**
	 * The {@link #p(Player)} method is called to show the player his/her prefix, if one
	 * exists.
	 * 
	 * @param player the player who entered the command
	 */
	private void p(Player player) {
		String prefix = Prefix.getPrefix(player, prefixData);
		
		plugin.sendMessage(player, (prefix == null ? "You have no prefix." : "Your prefix is " + prefix));
	}
	
	/**
	 * The {@link #pc(Player, String[])} method is called to diplay the color of the
	 * player's prefix to the player or edit that color.
	 * <p>
	 * If <b>'ac'</b> is entered, then a message is sent to the player displaying the color.
	 * <br>
	 * If anything is entered after <b>'ac'</b>, then it changes the color of the player's
	 * prefix.
	 * 
	 * @param player the player who entered the command
	 * @param args anything typed in after the original command
	 */
	private void pc(Player player, String[] args) {
		if (args.length > 0) {
			String color = "";
			
			for (String arg : args) {
				color += arg + "_";
			}
			prefixData.setColor(player, color.substring(0, color.length() - 1));
		}
		plugin.sendMessage(player, "The color of your prefix is " + prefixData.getColor(player));
	}
	
	/**
	 * The {@link #pn(Player, String[])} method is called to diplay the player's prefix
	 * without color to the player or edit that prefix.
	 * <p>
	 * If <b>'an'</b> is entered, then a message is sent to the player displaying the prefix.
	 * <br>
	 * If anything is entered after <b>'an'</b>, then it changes the player's prefix.
	 * 
	 * @param player the player who entered the command
	 * @param args anything typed in after the original command
	 */
	private void pn(Player player, String[] args) {
		if (args.length > 0) {
			String prefix = "";
			
			for (String arg : args) {
				prefix += arg + " ";
			}
			prefixData.setPrefix(player, prefix);
		}
		plugin.sendMessage(player, "Your prefix is now " + prefixData.getPrefix(player));
	}
	
	/**
	 * The {@link #pr(CommandSender)} method is called to reload the prefix data from
	 * the yaml file.
	 * 
	 * @param sender the sender who entered the command
	 */
	private void pr(CommandSender sender) {
		prefixData.readPrefixData();
		plugin.sendMessage(sender, "Prefix data has been reloaded!");
	}
}