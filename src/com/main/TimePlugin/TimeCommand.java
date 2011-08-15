package com.main.TimePlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.main.General;

/**
 * The <b>TimeCommand</b> class is called whenever a player uses any time command.
 */
public class TimeCommand implements CommandExecutor {
	
	/** The {@link #plugin} variable holds the instance of the <b>McGeneral</b> plugin */
	private General plugin;
	
	/** The {@link #timeData} variable holds the class that stores all the time data */
	private TimeData timeData;

	public TimeCommand(General plugin, TimeData timeData) {
		this.plugin = plugin;
		this.timeData = timeData;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equals("t") || label.equals("time")) {
			Time.tellTime(plugin, timeData, sender);
		} else if () {
			
		}
		return false;
	}
}
