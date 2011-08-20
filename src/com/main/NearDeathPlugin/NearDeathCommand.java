package com.main.NearDeathPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.main.General;

public class NearDeathCommand implements CommandExecutor {
	
	/** The {@link #plugin} variable holds the instance of the <b>McGeneral</b> plugin */
	private General plugin;
	
	/** The {@link #neardeathData} variable holds the class that stores all the near death data */
	private NearDeathData neardeathData;
	
	public NearDeathCommand(General plugin, NearDeathData neardeathData) {
		this.plugin = plugin;
		this.neardeathData = neardeathData;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
	}
}
