package com.main.TimePlugin;

import java.util.Calendar;

import org.bukkit.command.CommandSender;

import com.main.General;

/**
 * The <b>Time</b> class is called to properly format the time and either broadcast it to 
 * the server or display it to an individual sender.
 */
public class Time {

	/**
	 * This {@link #tellTime(General, TimeData)} method is called whenever the time should
	 * be broadcasted to the entire server. It calls the overhead method,
	 * {@link #tellTime(General, TimeData, CommandSender)}, to deal with the time.
	 * 
	 * @param plugin the plugin data for the <b>McGeneral</b> class
	 * @param timeData the <b>TimeData</b> class that holds the time settings from the yaml
	 * @see #tellTime(General, TimeData, CommandSender)
	 */
	public static void tellTime(General plugin, TimeData timeData) {
		tellTime(plugin, timeData, null);
	}
	
	/**
	 * The {@link #tellTime(General, TimeData, CommandSender)} method is called to properly
	 * display the time to an individual sender or the entire server using the data from the
	 * <b>timeData</b> variable.
	 * <br>
	 * To distinguish which, the <b>sender</b> variable will either be populated with a 
	 * legitiment sender or null. If null, the time will be broadcasted to the entire server.
	 * Otherwise, the time will only be sent to the sender.
	 * 
	 * @param plugin the plugin data for the <b>McGeneral</b> class
	 * @param timeData the <b>TimeData</b> class that holds the time settings from the yaml
	 * @param sender the sender of the command if one exists, otherwise null
	 */
	public static void tellTime(General plugin, TimeData timeData, CommandSender sender) {
		String theTime = timeData.getMessage();
		
		if (timeData.getFormat() == 12) {
			theTime += String.format("%1$tI:%1$tM %1$tp", Calendar.getInstance());
		} else {
			theTime += String.format("%1$tH:%1$tM", Calendar.getInstance());
		}
		if (sender == null) {
			plugin.broadcast(theTime);
		} else {
			plugin.sendMessage(sender, theTime);
		}
	}
}