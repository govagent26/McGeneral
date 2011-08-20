package com.main.SaveBackupPlugin;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;

import com.main.FileUtil;
import com.main.General;

/**
 * The <b>SaveBackup</b> class is used to save and backup all world data.
 */
public class SaveBackup {
	
	/**
	 * The {@link #saveWithBackups(General)} is called to save all data and then
	 * copy it for storage.
	 * 
	 * @param plugin the plugin data for the <b>McGeneral</b> class
	 */
    public void saveWithBackups(General plugin) {
    	ConsoleCommandSender ccs = new ConsoleCommandSender(plugin.getServer());
    	List<World> worlds = plugin.getServer().getWorlds();
    	String date = String.format(" - %1$tm-%1$td-%1$tY %1$tH %1$tM %1$tS", Calendar.getInstance());
    	
        try {
    		plugin.getServer().dispatchCommand(ccs, "save-off");
    		plugin.getServer().savePlayers();
    		
        	for (World world : worlds) {
                world.save();
                FileUtil.copyDirectory(new File(world.getName()), new File("backups", (world.getName() + date)));
            }
        	plugin.sendMessage("[McGeneral]: has successfully backed up all world data.");
        } catch (Exception e) {
        	plugin.sendMessage("[McGeneral]: Error occured during backup saving process.");
        } finally {
        	plugin.getServer().dispatchCommand(ccs, "save-on");
        }
    }
}