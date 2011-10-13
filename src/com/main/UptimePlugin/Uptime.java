package com.main.Uptime;

import java.io.File;
import java.util.Calendar;

import org.bukkit.command.CommandSender;

import com.main.McFileUtil;
import com.main.General;

/**
 * The <b>Uptime</b> class is used to display how long the server has been running
 * to a sender or store it in an external file.
 */
public class Uptime {

	/**
	 * The {@link #reportUptime(General, long, CommandSender)} method is called to
	 * display the uptime to the sender.
	 * 
	 * @param plugin the plugin data for the <b>McGeneral</b> class
	 * @param uptime the time when this plugin was initialized(when the server was started)
	 * @param sender the sender who requested the uptime
	 */
	public static void reportUptime(General plugin, long uptime, CommandSender sender) {
		plugin.sendMessage(sender, "The server has been running for " + getUptime(uptime));
	}
	
	/**
	 * The {@link #storeUptime(File, long)} method is called to store how long the
	 * server has been running into an external text file.
	 * 
	 * @param file the dataFolder for this plugin
	 * @param uptime the time when this plugin was initialized(when the server was started)
	 */
	public static void storeUptimeData(File file, long uptime) {
		String message = String.format(" on %1$tm-%1$td-%1$tY %1$tH %1$tM...........", Calendar.getInstance());
		McFileUtil.writeFile(new File(file, "uptime-storage.txt"), "Server ran for: " + getUptime(uptime) + message);
	}
	
	/**
	 * The {@link #getUptime(long)} method is called to turn the uptime of the server
	 * into a string composed of days, hours, and minutes.
	 * 
	 * @param uptime the time when this plugin was initialized(when the server was started)
	 * @return the uptime of the server converted into a string
	 */
	private static String getUptime(long uptime) {
		int seconds = (int)((System.currentTimeMillis() - uptime) / 1000);
		int minutes = (seconds / 60) % 60;
		int hours = (seconds / 3600) % 24;
		int days = seconds / 86400;
		
		return ((days == 0 ? "" : days + " days, ") + (hours == 0 ? "" : hours + " hours, ") + minutes + " minutes.");
	}
}