package com.main.NearDeathPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.main.General;

/**
 * The <b>NearDeathCommand</b> class is called whenever a player or the console uses any near 
 * death command.
 */
public class NearDeathCommand implements CommandExecutor {
	
	/** The {@link #plugin} variable holds the instance of the <b>McGeneral</b> plugin */
	private General plugin;
	
	/** The {@link #neardeathData} variable holds the class that stores all the near death data */
	private NearDeathData neardeathData;
	
	/**
	 * The {@link #NearDeathCommand(General, NearDeathData)} constructor is called whenever a player
	 * uses any near death command. 
	 * <p>
	 * The constructor stores the {@link #plugin} data and {@link #neardeathData} data locally
	 * for easy access by the methods in this class.
	 * 
	 * @param plugin the plugin data for the <b>McGeneral</b> class
	 * @param neardeathData the <b>NearDeathData</b> class that holds the near death settings from the yaml
	 */
	public NearDeathCommand(General plugin, NearDeathData neardeathData) {
		this.plugin = plugin;
		this.neardeathData = neardeathData;
	}
	
	/**
	 * The {@link #onCommand(CommandSender, Command, String, String[])} method is used to
	 * analize the near death command that was entered and send it to the appropriate method.
	 * <p>
	 * If <b>'nd'</b> or <b>'neardeath'</b> is entered, {@link #nd(CommandSender)} is called to
	 * handle the entered command.
	 * <br>
	 * If <b>'nda'</b> is entered, {@link #nda(CommandSender, String[])} is called to handle
	 * the entered command.
	 * <br>
	 * If <b>'nds'</b> is entered, {@link #nds(CommandSender, String[])} is called to handle
	 * the entered command.
	 * <br>
	 * If <b>'ndi'</b> is entered, {@link #ndi(CommandSender, String[])} is called to handle
	 * the entered command.
	 * <br>
	 * If <b>'ndr'</b> is entered, {@link #ndr(CommandSender, String[])} is called to handle
	 * the entered command.
	 * <br>
	 * If <b>'ndh'</b> is entered, the usage data from the <b>plugin.yml</b> file is shown
	 * to the sender.
	 * 
	 * @param sender the sender who entered the near death command
	 * @param command the overhead command that was entered(always 'neardeath')
	 * @param label the command actually typed in by the sender
	 * @param args anything typed in after the original command
	 * @return true if the entered command is valid, otherwise false
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equals("nd") || label.equals("neardeath")) {
			nd(sender);
		} else if (label.equals("nda")) {
			nda(sender, args);
		} else if (label.equals("nds")) {
			nds(sender, args);
		} else if (label.equals("ndi")) {
			ndi(sender, args);
		} else if (label.equals("ndr")) {
			ndr(sender);
		} else if (label.equals("ndh")) {
			return false;
		}
		return true;
	}
	
	/**
	 * The {@link #nd(CommandSender)} method displays the ammount of near death messages
	 * stored in the {@link #neardeathData} class to the sender.
	 * 
	 * @param sender the sender who entered the command
	 */
	private void nd(CommandSender sender) {
		plugin.sendMessage(sender,  "There are " + neardeathData.getMessages().size() + " near death messages currently entered");
	}
	
	/**
	 * The {@link #nda(CommandSender, String[])} method displays or edits the near death
	 * announcing status.
	 * <p>
	 * If <b>'nda'</b> is entered, then a message is sent to the sender displaying the near
	 * death announcing status('on' or 'off').
	 * <br>
	 * If <b>'nda on/off'</b> is entered, then the near death announcing status is changed
	 * ( to 'on' or 'off').
	 * <br>
	 * If anything else is entered, a message is sent to the sender informing them that they
	 * entered an invalid command.
	 * 
	 * @param sender the sender who entered the command
	 * @param args anything typed in after the original command
	 */
	private void nda(CommandSender sender, String[] args) {
		if (args.length == 0) {
			plugin.sendMessage(sender, "Near Death announcing is " + (neardeathData.getAnnounceStatus() ? "on." : "off."));
		} else if (args[0].equals("on")) {
			neardeathData.setAnnounceStatus(true);
			plugin.sendMessage(sender, "Near Death announcing is now on.");
		} else if (args[0].equals("off")) {
			neardeathData.setAnnounceStatus(false);
			plugin.sendMessage(sender, "Near Death announcing is now off.");
		} else {
			plugin.sendMessage(sender, "nda " + args[0] + " is not a valid near death command!");
		}
	}

	/**
	 * The {@link #nds(CommandSender, String[])} method diplays the near death health
	 * data or edits that data.
	 * <p>
	 * If <b>'nds'</b> is entered, then a message is sent to the sender displaying the near
	 * death health.
	 * <br>
	 * If anything is entered after <b>'nds'</b>, then it is parsed as an integer and the
	 * near death health requirement is changed.
	 * 
	 * @param sender the sender who entered the command
	 * @param args anything typed in after the original command
	 */
	private void nds(CommandSender sender, String[] args) {
		try {
			if (args.length > 0) {
				neardeathData.setHealth(Integer.parseInt(args[0]));
			}
		} catch (Exception e) {
			
		}
		plugin.sendMessage(sender, "Near Death health is set at " + neardeathData.getInterval() + " health.");
	}

	/**
	 * The {@link #ndi(CommandSender, String[])} method diplays the near death interval
	 * data or edits that data.
	 * <p>
	 * If <b>'ndi'</b> is entered, then a message is sent to the sender displaying the near
	 * death announcing interval in seconds.
	 * <br>
	 * If anything is entered after <b>'ndi'</b>, then it is parsed as an integer and the
	 * near death announcing interval is changed.
	 * 
	 * @param sender the sender who entered the command
	 * @param args anything typed in after the original command
	 */
	private void ndi(CommandSender sender, String[] args) {
		try {
			if (args.length > 0) {
				neardeathData.setInterval(Integer.parseInt(args[0]));
			}
		} catch (Exception e) {
			
		}
		plugin.sendMessage(sender, "Near Death interval is every " + neardeathData.getInterval() + " seconds.");
	}
	
	/**
	 * The {@link #ndr(CommandSender)} method reloads the near death data from
	 * the yaml file.
	 * 
	 * @param sender the sender who entered the command
	 */
	private void ndr(CommandSender sender) {
		neardeathData.readNearDeathData();
		plugin.sendMessage(sender, "Near Death data has been reloaded!");
	}
}