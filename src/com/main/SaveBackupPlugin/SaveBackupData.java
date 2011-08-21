package com.main.SaveBackupPlugin;

import com.main.McConfig;

/**
 * The <b>SaveBackupData</b> class is used to store all the save backup data that is in the
 * external save backup data file.
 */
public class SaveBackupData {
	
	/** The {@link #nodes} variable holds the stings for accessing the data file nodes */
	private String[] nodes = {"saving.activated", "saving.interval"};
	
	/** The {@link #config} variable is used to access the <b>Config</b> class */
	private McConfig config;
	
	/** The {@link #activated} variable that holds whether or not to save data at certan intervals */
	private boolean activated;
	
	/** The {@link #interval} variable that holds the saving interval(in minutes) */
	private int interval;
	
	/**
	 * The {@link #SaveBackupData(McConfig)} constructor is called when the plugin is
	 * initialized.
	 * <p>
	 * The constructor stores the {@link #config} variable for easy future access.
	 * It then calls the {@link #readSaveBackupData()} method to read data from the
	 * external yaml file and store it in this class.
	 * 
	 * @param config the configuration variable that can access the save backup data
	 */
	public SaveBackupData (McConfig config) {
		this.config = config;
		readSaveBackupData();
	}
	
	/**
	 * The {@link #readSaveBackupData()} method is called when the data from the external 
	 * files needs to be read.
	 * <p>
	 * Data is read from the external yaml file and stored in many variables in this 
	 * class. It is stored here for easy future access when determining what the 
	 * user settings are.
	 * <p>
	 * The variable data is stored in {@link #activated} and {@link #interval}.
	 */
	public void readSaveBackupData() {
		config.load();
		activated = config.getBoolean(nodes[0], true);
		interval = config.getInt(nodes[1], 2);
		config.save();
	}
	
	/**
	 * The {@link #isActivated()} method returns the save backup activated status.
	 * 
	 * @return whether or not to save backups at a set interval
	 */
	public boolean isActivated() {
		return activated;
	}
	
	/**
	 * The {@link #setActivated()} method sets the save backup activated status by
	 * changing the {@link #activated} variable data and editing the external
	 * yaml file.
	 * 
	 * @param announce whether or not to save backups at a set interval
	 */
	public void setActivated(boolean activated) {
		this.activated = activated;
		config.load();
		config.setProperty(nodes[0], activated);
		config.save();
	}
	
	/**
	 * The {@link #getInterval()} method returns the save backup interval.
	 * 
	 * @return the interval at which saves and backups occur
	 */
	public int getInterval() {
		return interval;
	}
	
	/**
	 * The {@link #setInterval()} method sets the save backup interval by
	 * changing the {@link #interval} variable data and editing the external
	 * yaml file. It also calls the {@link #checkInterval()} method to verify 
	 * that the interval is set appropriately.
	 * 
	 * @param interval the interval at which saves and backups are to occur
	 * @return true is the interval value is changed, otherwise false
	 */
	public boolean setInterval(int interval) {
		this.interval = interval;
		if (!checkInterval()) {
			return false;
		}
		config.load();
		config.setProperty(nodes[1], interval);
		config.save();
		return true;
	}
	
	/**
	 * The {@link #checkInterval()} method checks to make sure that the save backup interval
	 * is within bounds. If it is greater than 60 or less than 1, then the {@link #interval}
	 * variable is assigned the value of 30.
	 * 
	 * @return true if the interval value is appropriate, otherwise false
	 */
	private boolean checkInterval() {
		if (interval > 60 || interval < 1) {
			this.interval = 30;
			return false;
		}
		return true;
	}
}