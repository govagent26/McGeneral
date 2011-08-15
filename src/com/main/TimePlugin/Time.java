package com.main.TimePlugin;

import org.bukkit.command.CommandSender;

import com.main.General;

public class Time {

	public static void tellTime(General plugin, TimeData timeData) {
		tellTime(plugin, timeData, null);
	}
	
	public static void tellTime(General plugin, TimeData timeData, CommandSender sender) {
		String theTime = timeData.getMessage();
		
		if (timeData.getFormat() == 12) {
			theTime += String.format(" %", "");
		} else {
			theTime += String.format(" %", "");
		}
		if (sender == null) {
			
		} else {
			sender.sendMessage(theTime);
		}
	}
}