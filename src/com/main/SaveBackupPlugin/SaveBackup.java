package com.main.SaveBackupPlugin;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;

import com.main.McFileUtil;
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
    public static void saveWithBackups(General plugin) {
    	ConsoleCommandSender ccs = new ConsoleCommandSender(plugin.getServer());
    	List<World> worlds = plugin.getServer().getWorlds();
    	String date = String.format(" - %1$tm-%1$td-%1$tY %1$tH %1$tM %1$tS", Calendar.getInstance());
    	
        try {
    		plugin.getServer().dispatchCommand(ccs, "save-off");
    		plugin.getServer().savePlayers();
    		
        	for (World world : worlds) {
                world.save();
                McFileUtil.copyDirectory(new File(world.getName()), new File("backups", (world.getName() + date)));
            }
        	plugin.broadcast("[McGeneral]: has successfully backed up all world data.", true);
        } catch (Exception e) {
        	plugin.sendMessage("[McGeneral]: Error occured during backup saving process.");
        } finally {
        	plugin.getServer().dispatchCommand(ccs, "save-on");
        }
    }
    
	/**
	 * The {@link #processTick(General, SaveBackupData, int)} method is called to check a time against
	 * the save backup interval. If the time is evenly divisible by the interval, then all world
	 * data is saved by calling the {@link #saveWithBackups(General)} method.
	 * 
	 * @param plugin the plugin data for the <b>McGeneral</b> class
	 * @param savebackupData the <b>SaveBackupData</b> class that holds the save backup settings from the yaml
	 * @param minute the current minute to compare with the interval
	 */
	public static void processTick(General plugin, SaveBackupData savebackupData, int minute) {
		if (savebackupData.isActivated() && ((minute % savebackupData.getInterval()) == 0)) {
			saveWithBackups(plugin);
		}
	}
}