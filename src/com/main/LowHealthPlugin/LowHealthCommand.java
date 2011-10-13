package com.main.LowHealthPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.main.General;

/**
 * The <b>LowHealthCommand</b> class is called whenever a player or the console uses any near 
 * death command.
 */
public class LowHealthCommand implements CommandExecutor {
	
	/** The {@link #plugin} variable holds the instance of the <b>McGeneral</b> plugin */
	private General plugin;
	
	/** The {@link #lowhealthData} variable holds the class that stores all the low health data */
	private LowHealthData lowhealthData;
	
	/**
	 * The {@link #LowHealthCommand(General, LowHealthData)} constructor is called whenever a player
	 * uses any low health command. 
	 * <p>
	 * The constructor stores the {@link #plugin} data and {@link #lowhealthData} data locally
	 * for easy access by the methods in this class.
	 * 
	 * @param plugin the plugin data for the <b>McGeneral</b> class
	 * @param lowhealthData the <b>LowHealthData</b> class that holds the low health settings from the yaml
	 */
	public LowHealthCommand(General plugin, LowHealthData lowhealthData) {
		this.plugin = plugin;
		this.lowhealthData = lowhealthData;
	}
	
	/**
	 * The {@link #onCommand(CommandSender, Command, String, String[])} method is used to
	 * analize the low health command that was entered and send it to the appropriate method.
	 * <p>
	 * If <b>'lh'</b> or <b>'lowhealth'</b> is entered, {@link #lh(CommandSender)} is called to
	 * handle the entered command.
	 * <br>
	 * If <b>'lha'</b> is entered, {@link #lha(CommandSender, String[])} is called to handle
	 * the entered command.
	 * <br>
	 * If <b>'lhs'</b> is entered, {@link #lhs(CommandSender, String[])} is called to handle
	 * the entered command.
	 * <br>
	 * If <b>'lhi'</b> is entered, {@link #lhi(CommandSender, String[])} is called to handle
	 * the entered command.
	 * <br>
	 * If <b>'lhr'</b> is entered, {@link #lhr(CommandSender, String[])} is called to handle
	 * the entered command.
	 * <br>
	 * If <b>'lhh'</b> is entered, the usage data from the <b>plugin.yml</b> file is shown
	 * to the sender.
	 * 
	 * @param sender the sender who entered the low health command
	 * @param command the overhead command that was entered(always 'lowhealth')
	 * @param label the command actually typed in by the sender
	 * @param args anything typed in after the original command
	 * @return true if the entered command is valid, otherwise false
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equals("lh") || label.equals("lowhealth")) {
			lh(sender);
		} else if (label.equals("lha")) {
			lha(sender, args);
		} else if (label.equals("lhs")) {
			lhs(sender, args);
		} else if (label.equals("lhi")) {
			lhi(sender, args);
		} else if (label.equals("lhr")) {
			lhr(sender);
		} else if (label.equals("lhh")) {
			return false;
		}
		return true;
	}
	
	/**
	 * The {@link #lh(CommandSender)} method displays the ammount of low health messages
	 * stored in the {@link #lowhealthData} class to the sender.
	 * 
	 * @param sender the sender who entered the command
	 */
	private void lh(CommandSender sender) {
		plugin.sendMessage(sender,  "There are " + lowhealthData.getMessages().size() + " low health messages currently entered");
	}
	
	/**
	 * The {@link #lha(CommandSender, String[])} method displays or edits the low health
	 * announcing status.
	 * <p>
	 * If <b>'lha'</b> is entered, then a message is sent to the sender displaying the low
	 * health announcing status('on' or 'off').
	 * <br>
	 * If <b>'lha on/off'</b> is entered, then the low health announcing status is changed
	 * ( to 'on' or 'off').
	 * <br>
	 * If anything else is entered, a message is sent to the sender informing them that they
	 * entered an invalid command.
	 * 
	 * @param sender the sender who entered the command
	 * @param args anything typed in after the original command
	 */
	private void lha(CommandSender sender, String[] args) {
		if (args.length == 0) {
			plugin.sendMessage(sender, "Low Health announcing is " + (lowhealthData.getAnnounceStatus() ? "on." : "off."));
		} else if (args[0].equals("on")) {
			lowhealthData.setAnnounceStatus(true);
			plugin.sendMessage(sender, "Low Health announcing is now on.");
		} else if (args[0].equals("off")) {
			lowhealthData.setAnnounceStatus(false);
			plugin.sendMessage(sender, "Low Health announcing is now off.");
		} else {
			plugin.sendMessage(sender, "lhda " + args[0] + " is not a valid low health command!");
		}
	}

	/**
	 * The {@link #lhs(CommandSender, String[])} method diplays the low health data or
	 * edits that data.
	 * <p>
	 * If <b>'lhs'</b> is entered, then a message is sent to the sender displaying the low
	 * health level.
	 * <br>
	 * If anything is entered after <b>'lhs'</b>, then it is parsed as an integer and the
	 * low health requirement is changed.
	 * 
	 * @param sender the sender who entered the command
	 * @param args anything typed in after the original command
	 */
	private void lhs(CommandSender sender, String[] args) {
		try {
			if (args.length > 0) {
				lowhealthData.setHealth(Integer.parseInt(args[0]));
			}
		} catch (Exception e) {
			
		}
		plugin.sendMessage(sender, "Low Health level is set at " + lowhealthData.getInterval() + " health.");
	}

	/**
	 * The {@link #lhi(CommandSender, String[])} method diplays the low health interval
	 * data or edits that data.
	 * <p>
	 * If <b>'lhi'</b> is entered, then a message is sent to the sender displaying the low
	 * health announcing interval in seconds.
	 * <br>
	 * If anything is entered after <b>'lhi'</b>, then it is parsed as an integer and the
	 * low health announcing interval is changed.
	 * 
	 * @param sender the sender who entered the command
	 * @param args anything typed in after the original command
	 */
	private void lhi(CommandSender sender, String[] args) {
		try {
			if (args.length > 0) {
				lowhealthData.setInterval(Integer.parseInt(args[0]));
			}
		} catch (Exception e) {
			
		}
		plugin.sendMessage(sender, "Low Health interval is every " + lowhealthData.getInterval() + " seconds.");
	}
	
	/**
	 * The {@link #lhr(CommandSender)} method reloads the low health data from
	 * the yaml file.
	 * 
	 * @param sender the sender who entered the command
	 */
	private void lhr(CommandSender sender) {
		lowhealthData.readLowHealthData();
		plugin.sendMessage(sender, "Low Health data has been reloaded!");
	}
}