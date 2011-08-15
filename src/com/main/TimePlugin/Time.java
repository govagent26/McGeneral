package com.main.TimePlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.main.General;

/**
 * The <b>Time</b> class is called whenever a player uses any time command.
 */
public class Time implements CommandExecutor {
	
	/** The {@link #plugin} variable holds the instance of the <b>McGeneral</b> plugin */
	General plugin;
	
	/** The {@link #timeData} variable holds the class that stores all the time data */
	private TimeData timeData;

	public Time (General plugin, TimeData timeData) {
		this.plugin = plugin;
		this.timeData = timeData;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// TODO Auto-generated method stub
		return false;
	}
}
