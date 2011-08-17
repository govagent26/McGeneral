package com.main.TimePlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.main.General;

/**
 * The <b>TimeCommand</b> class is called whenever a player or the console uses any time 
 * command.
 */
public class TimeCommand implements CommandExecutor {
	
	/** The {@link #plugin} variable holds the instance of the <b>McGeneral</b> plugin */
	private General plugin;
	
	/** The {@link #timeData} variable holds the class that stores all the time data */
	private TimeData timeData;

	/**
	 * The {@link #TimeCommand(General, TimeData)} constructor is called whenever a player
	 * uses any time command. 
	 * <p>
	 * The constructor stores the {@link #plugin} data and {@link #timeData} data locally
	 * for easy access by the methods in this class.
	 * 
	 * @param plugin the plugin data for the <b>McGeneral</b> class
	 * @param timeData the <b>TimeData</b> class that holds the time settings from the yaml
	 */
	public TimeCommand(General plugin, TimeData timeData) {
		this.plugin = plugin;
		this.timeData = timeData;
	}

	/**
	 * The {@link #onCommand(CommandSender, Command, String, String[])} method is used to
	 * analize the time command that was entered and send it to the appropriate method.
	 * <p>
	 * If <b>'t'</b> or <b>'time'</b> is entered, {@link #t(CommandSender)} is called to
	 * handle the entered command.
	 * <br>
	 * If <b>'ta'</b> is entered, {@link #ta(CommandSender, String[])} is called to handle
	 * the entered command.
	 * <br>
	 * If <b>'ti'</b> is entered, {@link #ti(CommandSender, String[])} is called to handle
	 * the entered command.
	 * <br>
	 * If <b>'tm'</b> is entered, {@link #tm(CommandSender, String[])} is called to handle
	 * the entered command.
	 * <br>
	 * If <b>'tf'</b> is entered, {@link #tf(CommandSender, String[])} is called to handle
	 * the entered command.
	 * <br>
	 * If <b>'tr'</b> is entered, {@link #tr(CommandSender)} is called to handle the
	 * entered command.
	 * <br>
	 * If <b>'th'</b> is entered, the usage data from the <b>plugin.yml</b> file is shown
	 * to the sender.
	 * 
	 * @param sender the sender who entered the time command
	 * @param command the overhead command that was entered(always 'time')
	 * @param label the command actually typed in by the sender
	 * @param args anything typed in after the original command
	 * @return true if the entered command is valid, otherwise false
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equals("t") || label.equals("time")) {
			t(sender);
		} else if (label.equals("ta")) {
			ta(sender, args);
		} else if (label.equals("ti")) {
			ti(sender, args);
		} else if (label.equals("tm")) {
			tm(sender, args);
		} else if (label.equals("tf")) {
			tf(sender, args);
		} else if (label.equals("tr")) {
			tr(sender);
		} else if (label.equals("th")) {
			return false;
		}
		return true;
	}
	
	/**
	 * The {@link #t(CommandSender)} method is called to display the time to the sender.
	 * It calls the <b>tellTime</b> method from the <b>Time</b> class to tell the time to
	 * the sender using the time settings stored in the {@link #timeData} varuable.
	 * 
	 * @param sender the sender who entered the command
	 */
	private void t(CommandSender sender) {
		Time.tellTime(plugin, timeData, sender);
	}
	
	/**
	 * The {@link #ta(CommandSender, String[])} method is called to diplay the time
	 * announcing data or edit that data.
	 * <p>
	 * If <b>'ta'</b> is entered, then a message is sent to the sender displaying the time
	 * announcing status('on' or 'off').
	 * <br>
	 * If <b>'ta on/off'</b> is entered, then the time announcing status is changed( to 'on'
	 * or 'off').
	 * <br>
	 * If anything else is entered, a message is sent to the sender informing them that they
	 * entered an invalid command.
	 * 
	 * @param sender the sender who entered the command
	 * @param args anything typed in after the original command
	 */
	private void ta(CommandSender sender, String[] args) {
		if (args.length == 0) {
			plugin.sendMessage(sender, "Time announcing is " + (timeData.getAnnounceStatus() ? "on." : "off."));
		} else if (args[0].equals("on")) {
			timeData.setAnnounceStatus(true);
			plugin.sendMessage(sender, "Time announcing is now on.");
		} else if (args[0].equals("off")) {
			timeData.setAnnounceStatus(false);
			plugin.sendMessage(sender, "Time announcing is now off.");
		} else {
			plugin.sendMessage(sender, "ta " + args[0] + " is not a valid time command!");
		}
	}
	
	/**
	 * The {@link #ti(CommandSender, String[])} method is called to diplay the time
	 * interval data or edit that data.
	 * <p>
	 * If <b>'ti'</b> is entered, then a message is sent to the sender displaying the time
	 * announcing interval in minutes.
	 * <br>
	 * If anything is entered after <b>'ti'</b>, then it is parsed as an integer and the
	 * time announcing interval is changed.
	 * 
	 * @param sender the sender who entered the command
	 * @param args anything typed in after the original command
	 */
	private void ti(CommandSender sender, String[] args) {
		if (args.length > 0) {
			timeData.setInterval(Integer.parseInt(args[0]));
		}
		plugin.sendMessage(sender, "Time interval is every " + timeData.getInterval() + " minutes.");
	}
	
	/**
	 * The {@link #tm(CommandSender, String[])} method is called to diplay the time
	 * message data or edit that data.
	 * <p>
	 * If <b>'tm'</b> is entered, then a message is sent to the sender displaying the 
	 * message shown when displaying the time.
	 * <br>
	 * If anything is entered after <b>'tm'</b>, then it changes the time message to
	 * whatever has been entered.
	 * 
	 * @param sender the sender who entered the command
	 * @param args anything typed in after the original command
	 */
	private void tm(CommandSender sender, String[] args) {
		if (args.length > 0) {
			String message = "";
			
			for (String arg : args) {
				message += arg + " ";
			}
			timeData.setMessage(message);
		}
		plugin.sendMessage(sender, "Time message is " + timeData.getMessage());
	}
	
	/**
	 * The {@link #tf(CommandSender, String[])} method is called to diplay the time
	 * format data or edit that data.
	 * <p>
	 * If <b>'tf'</b> is entered, then a message is sent to the sender displaying the time
	 * format in hours.
	 * <br>
	 * If anything is entered after <b>'tf'</b>, then it is parsed as an integer and the
	 * time format is changed.
	 * 
	 * @param sender the sender who entered the command
	 * @param args anything typed in after the original command
	 */
	private void tf(CommandSender sender, String[] args) {
		if (args.length > 0) {
			timeData.setFormat(Integer.parseInt(args[0]));
		}
		plugin.sendMessage(sender, "Time format is " + timeData.getFormat() + " hours.");
	}
	
	/**
	 * The {@link #tr(CommandSender)} method is called to reload the time data from
	 * the yaml file.
	 * 
	 * @param sender the sender who entered the command
	 */
	private void tr(CommandSender sender) {
		timeData.readTimeData();
		plugin.sendMessage(sender, "Time data has been reloaded!");
	}
}