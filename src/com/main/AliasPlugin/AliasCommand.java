package com.main.AliasPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.main.General;

/**
 * The <b>AliasCommand</b> class is called whenever a player or the console uses any alias 
 * command.
 */
public class AliasCommand implements CommandExecutor {
	
	/** The {@link #plugin} variable holds the instance of the <b>McGeneral</b> plugin */
	private General plugin;
	
	/** The {@link #aliasData} variable holds the class that stores all the alias data */
	private AliasData aliasData;
	
	/**
	 * The {@link #AliasCommand(General, AliasData)} constructor is called whenever a player
	 * uses any alias command. 
	 * <p>
	 * The constructor stores the {@link #plugin} data and {@link #aliasData} data locally
	 * for easy access by the methods in this class.
	 * 
	 * @param plugin the plugin data for the <b>McGeneral</b> class
	 * @param aliasData the <b>AliasData</b> class that holds the alias data from the yaml
	 */
	public AliasCommand(General plugin, AliasData aliasData) {
		this.plugin = plugin;
		this.aliasData = aliasData;
	}
	
	/**
	 * The {@link #onCommand(CommandSender, Command, String, String[])} method is used to
	 * analize the alias command that was entered and send it to the appropriate method.
	 * <p>
	 * If <b>'a'</b> or <b>'alias'</b> is entered, {@link #a(Player)} is called to
	 * handle the entered command.
	 * <br>
	 * If <b>'ac'</b> is entered, {@link #ac(Player, String[])} is called to handle
	 * the entered command.
	 * <br>
	 * If <b>'an'</b> is entered, {@link #an(Player, String[])} is called to handle
	 * the entered command.
	 * <br>
	 * If <b>'ar'</b> is entered, {@link #ar(CommandSender)} is called to handle
	 * the entered command.
	 * <br>
	 * If <b>'ah'</b> is entered, the usage data from the <b>plugin.yml</b> file is shown
	 * to the sender.
	 * 
	 * @param sender the sender who entered the alias command
	 * @param command the overhead command that was entered(always 'alias')
	 * @param label the command actually typed in by the sender
	 * @param args anything typed in after the original command
	 * @return true if the entered command is valid, otherwise false
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equals("ar")) {
			ar(sender);
			return true;
		}
		if (sender instanceof Player) {
			if (label.equals("a") || label.equals("alias")) {
				a((Player)sender);
			} else if (label.equals("ac")) {
				ac((Player)sender, args);
			} else if (label.equals("an")) {
				an((Player)sender, args);
			} else if (label.equals("ah")) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	/**
	 * The {@link #a(Player)} method is called to show the player his/her display name.
	 * 
	 * @param player the player who entered the command
	 */
	private void a(Player player) {
		plugin.sendMessage(player, "Your display name is " + player.getDisplayName());
	}
	
	/**
	 * The {@link #ac(Player, String[])} method is called to diplay the color of the
	 * player's display name to the player or edit that color.
	 * <p>
	 * If <b>'ac'</b> is entered, then a message is sent to the player displaying the color.
	 * <br>
	 * If anything is entered after <b>'ac'</b>, then it changes the color of the player's
	 * display name.
	 * 
	 * @param player the player who entered the command
	 * @param args anything typed in after the original command
	 */
	private void ac(Player player, String[] args) {
		if (args.length > 0) {
			String color = "";
			
			for (String arg : args) {
				color += arg + "_";
			}
			aliasData.setColor(player, color.substring(0, color.length() - 1));
		}
		plugin.sendMessage(player, "The color of your display name is " + aliasData.getColor(player));
	}
	
	/**
	 * The {@link #an(Player, String[])} method is called to diplay the name of the
	 * player's display name without color to the player or edit that name.
	 * <p>
	 * If <b>'an'</b> is entered, then a message is sent to the player displaying the name.
	 * <br>
	 * If anything is entered after <b>'an'</b>, then it changes the name of the player's
	 * display name.
	 * 
	 * @param player the player who entered the command
	 * @param args anything typed in after the original command
	 */
	private void an(Player player, String[] args) {
		if (args.length > 0) {
			String alias = "";
			
			for (String arg : args) {
				alias += arg + " ";
			}
			aliasData.setAlias(player, alias);
		}
		plugin.sendMessage(player, "Your display name is now " + aliasData.getAlias(player));
	}
	
	/**
	 * The {@link #ar(CommandSender)} method is called to reload the alias data from
	 * the yaml file.
	 * 
	 * @param sender the sender who entered the command
	 */
	private void ar(CommandSender sender) {
		aliasData.readAliasData();
		plugin.sendMessage(sender, "Alias data has been reloaded!");
	}
}