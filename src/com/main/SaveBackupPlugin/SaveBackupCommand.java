package com.main.SaveBackupPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.main.General;

/**
 * The <b>SaveBackupCommand</b> class is called whenever a player or the console uses any save
 * or backup command.
 */
public class SaveBackupCommand implements CommandExecutor {
	
	/** The {@link #plugin} variable holds the instance of the <b>McGeneral</b> plugin */
	private General plugin;
	
	/** The {@link #savebackupData} variable holds the class that stores all the save backup data */
	private SaveBackupData savebackupData;
	
	/**
	 * The {@link #SaveBackupCommand(General, SaveBackupData)} constructor is called whenever a player
	 * uses any save or backup command. 
	 * <p>
	 * The constructor stores the {@link #plugin} data and {@link #savebackupData} data locally
	 * for easy access by the methods in this class.
	 * 
	 * @param plugin the plugin data for the <b>McGeneral</b> class
	 * @param savebackupData the <b>SaveBackupData</b> class that holds the save backup settings from the yaml
	 */
	public SaveBackupCommand (General plugin, SaveBackupData savebackupData) {
		this.plugin = plugin;
		this.savebackupData = savebackupData;
	}
	
	/**
	 * The {@link #onCommand(CommandSender, Command, String, String[])} method is used to
	 * analize the save backup command that was entered and send it to the appropriate method.
	 * <p>
	 * If <b>'s'</b> or <b>'savebackup'</b> is entered, {@link #s()} is called to
	 * handle the entered command.
	 * <br>
	 * If <b>'sa'</b> is entered, {@link #sa(CommandSender, String[])} is called to handle
	 * the entered command.
	 * <br>
	 * If <b>'si'</b> is entered, {@link #si(CommandSender, String[])} is called to handle
	 * the entered command.
	 * <br>
	 * If <b>'sr'</b> is entered, {@link #sr(CommandSender, String[])} is called to handle
	 * the entered command.
	 * <br>
	 * If <b>'sh'</b> is entered, the usage data from the <b>plugin.yml</b> file is shown
	 * to the sender.
	 * 
	 * @param sender the sender who entered the save backup command
	 * @param command the overhead command that was entered(always 'savebackup')
	 * @param label the command actually typed in by the sender
	 * @param args anything typed in after the original command
	 * @return true if the entered command is valid, otherwise false
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equals("s") || label.equals("savebackup")) {
			s();
		} else if (label.equals("sa")) {
			sa(sender, args);
		} else if (label.equals("si")) {
			si(sender, args);
		} else if (label.equals("sr")) {
			sr(sender);
		} else if (label.equals("sh")) {
			return false;
		}
		return true;
	}
	
	/**
	 * The {@link #s(CommandSender)} method forces an immediate save and backup of all
	 * worlds.
	 */
	private void s() {
		SaveBackup.saveWithBackups(plugin);
	}
	
	/**
	 * The {@link #sa(CommandSender, String[])} method displays or edits the save backups
	 * activated status.
	 * <p>
	 * If <b>'sa'</b> is entered, then a message is sent to the sender displaying the save
	 * backups activated status('on' or 'off').
	 * <br>
	 * If <b>'sa on/off'</b> is entered, then the save backup activated status is changed
	 * ( to 'on' or 'off').
	 * <br>
	 * If anything else is entered, a message is sent to the sender informing them that they
	 * entered an invalid command.
	 * 
	 * @param sender the sender who entered the command
	 * @param args anything typed in after the original command
	 */
	private void sa(CommandSender sender, String[] args) {
		if (args.length == 0) {
			plugin.sendMessage(sender, "Save Backup activated status is " + (savebackupData.isActivated() ? "on." : "off."));
		} else if (args[0].equals("on")) {
			savebackupData.setActivated(true);
			plugin.sendMessage(sender, "Save Backup is now activated.");
		} else if (args[0].equals("off")) {
			savebackupData.setActivated(false);
			plugin.sendMessage(sender, "Save Backup is now de-activated.");
		} else {
			plugin.sendMessage(sender, "nda " + args[0] + " is not a valid save backup command!");
		}
	}

	/**
	 * The {@link #si(CommandSender, String[])} method diplays the save backup interval
	 * data or edits that data.
	 * <p>
	 * If <b>'si'</b> is entered, then a message is sent to the sender displaying the save
	 * backup interval in minutes.
	 * <br>
	 * If anything is entered after <b>'si'</b>, then it is parsed as an integer and the
	 * save backup interval is changed.
	 * 
	 * @param sender the sender who entered the command
	 * @param args anything typed in after the original command
	 */
	private void si(CommandSender sender, String[] args) {
		try {
			if (args.length > 0) {
				savebackupData.setInterval(Integer.parseInt(args[0]));
			}
		} catch (Exception e) {
			
		}
		plugin.sendMessage(sender, "Save Backup interval is every " + savebackupData.getInterval() + " minutes.");
	}
	
	/**
	 * The {@link #sr(CommandSender)} method reloads the save backup data from
	 * the yaml file.
	 * 
	 * @param sender the sender who entered the command
	 */
	private void sr(CommandSender sender) {
		savebackupData.readSaveBackupData();
		plugin.sendMessage(sender, "Save Backup data has been reloaded!");
	}
}