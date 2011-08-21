package com.main.PvpPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.main.General;

/**
 * The <b>PvpCommand</b> class is called whenever a player or the console uses any pvp 
 * command.
 */
public class PvpCommand implements CommandExecutor {

	/** The {@link #plugin} variable holds the instance of the <b>McGeneral</b> plugin */
	private General plugin;
	
	/** The {@link #pvpData} variable holds the class that stores all the pvp data */
	private PvpData pvpData;
	
	/**
	 * The {@link #PvpCommand(General, PvpData)} constructor is called whenever a player
	 * uses any pvp command. 
	 * <p>
	 * The constructor stores the {@link #plugin} data and {@link #pvpData} data locally
	 * for easy access by the methods in this class.
	 * 
	 * @param plugin the plugin data for the <b>McGeneral</b> class
	 * @param pvpData the <b>PvpData</b> class that holds the pvp data from the yaml
	 */
	public PvpCommand(General plugin, PvpData pvpData) {
		this.plugin = plugin;
		this.pvpData = pvpData;
	}

	/**
	 * The {@link #onCommand(CommandSender, Command, String, String[])} method is used to
	 * analize the pvp command that was entered and send it to the appropriate method.
	 * <p>
	 * If <b>'v'</b> or <b>'pvp'</b> is entered, {@link #v(Player)} is called to
	 * handle the entered command.
	 * <br>
	 * If <b>'vc'</b> is entered, {@link #vc(Player)} is called to handle
	 * the entered command.
	 * <br>
	 * If <b>'vs'</b> is entered, {@link #vs(Player)} is called to handle
	 * the entered command.
	 * <br>
	 * If <b>'vr'</b> is entered, {@link #vr(CommandSender)} is called to handle
	 * the entered command.
	 * <br>
	 * If <b>'vh'</b> is entered, the usage data from the <b>plugin.yml</b> file is shown
	 * to the sender.
	 * 
	 * @param sender the sender who entered the pvp command
	 * @param command the overhead command that was entered(always 'pvp')
	 * @param label the command actually typed in by the sender
	 * @param args anything typed in after the original command
	 * @return true if the entered command is valid, otherwise false
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equals("vr")) {
			vr(sender);
			return true;
		}
		if (sender instanceof Player) {
			if (label.equals("v") || label.equals("pvp")) {
				v((Player)sender);
			} else if (label.equals("vc")) {
				vc((Player)sender);
			} else if (label.equals("vs")) {
				vs((Player)sender);
			} else if (label.equals("vh")) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	/**
	 * The {@link #v(Player)} method is called to show the player his/her pvp status.
	 * 
	 * @param player the player who entered the command
	 */
	private void v(Player player) {
		plugin.sendMessage(player, "Pvp is currently " + (Pvp.getPvpStatus(player, pvpData) ? "enabled" : "disabled"));
	}

	/**
	 * The {@link #vc(Player)} method is called to show the player his/her pvp cooldown.
	 * 
	 * @param player the player who entered the command
	 */
	private void vc(Player player) {
		long time = pvpData.getPvpCooldown(player) - System.currentTimeMillis();
		
		if (time > 0) {
			plugin.sendMessage(player, "You have " + (time / 60000) + " minutes left before you can change your pvp status");
		} else {
			plugin.sendMessage(player, "You have no pvp cooldown left. You can change your pvp status whenever.");
		}
	}
	
	/**
	 * The {@link #vs(Player)} method is called to change the player's pvp status.
	 * 
	 * @param player the player who entered the command
	 */
	private void vs(Player player) {
		pvpData.switchPvpStatus(player);
		plugin.sendMessage(player, "Pvp status is now " + (Pvp.getPvpStatus(player, pvpData) ? "enabled" : "disabled"));
	}
	
	/**
	 * The {@link #vr(CommandSender)} method is called to reload the pvp data from
	 * the yaml file.
	 * 
	 * @param sender the sender who entered the command
	 */
	private void vr(CommandSender sender) {
		pvpData.readPvpData();
		plugin.sendMessage(sender, "Pvp data has been reloaded!");
	}
}