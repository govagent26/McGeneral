package com.main.TimePlugin;

import com.main.Config;

/**
 * The <b>TimeData</b> class is used to store all the time data that is in the
 * external time data file.
 */
public class TimeData {
	
	/** The {@link #nodes} variable holds the stings for accessing the data file nodes */
	private String nodes[] = {"time.announce.status", "time.announce.interval",
			"time.message", "time.format"};

	/** The {@link #config} variable is used to access the <b>Config</b> class */
	private Config config;
	
	/** The {@link #announce} variable that holds whether or not to announce the time */
	private boolean announce;
	
	/** The {@link #interval} variable that holds time announcing interval */
	private int interval;
	
	/** The {@link #message} variable that holds what message should be displayed */
	private String message;
	
	/** The {@link #format} variable that holds the time format(12 or 24 hour) */
	private int format;
	
	/**
	 * The {@link #TimeData(Config)} constructor is called when the plugin is
	 * initialized.
	 * <p>
	 * This constructor stores the {@link #config} variable for easy future access.
	 * Also, data is initially read from the external yaml file and stored in
	 * many variable in this class.
	 * <p>
	 * It is stored here for easy future access when determining what the user
	 * settings are.
	 * 
	 * @param config the configuration variable that can access the time data
	 */
	public TimeData(Config config) {
		this.config = config;
		config.load();
		this.announce = config.getBoolean(nodes[0], true);
		this.interval = config.getInt(nodes[1], 15);
        this.message = config.getString(nodes[2], "The current time is:");
        this.format = config.getInt(nodes[3], 12);
        config.save();
        checkInterval();
        checkFormat();
	}
	
	/**
	 * This {@link #readTimeData(Config)} method is called when the data from the
	 * external files needs to be re-read.
	 * <p>
	 * The data is stored in this class for easy future access when determining what 
	 * the user settings are.
	 */
	public void readTimeData() {
		config.load();
		this.announce = config.getBoolean(nodes[0], true);
		this.interval = config.getInt(nodes[1], 15);
        this.message = config.getString(nodes[2], "The current time is:");
        this.format = config.getInt(nodes[3], 12);
        config.save();
        checkInterval();
        checkFormat();
	}
	
	/** 
	 * The {@link #getAnnounceStatus()} method returns the time anounce status.
	 * 
	 * @return true if time announcements are on, otherwise false
	 */
	public boolean getAnnounceStatus() {
		return this.announce;
	}
	
	/**
	 * The {@link #setAnnounceStatus()} method sets the time announce status.
	 * 
	 * @param announce whether to announce the time at certain intervals.
	 */
	public void setAnnounceStatus(boolean announce) {
		this.announce = announce;
		config.load();
		config.setProperty(nodes[0], announce);
		config.save();
	}
	
	/** 
	 * The {@link #getInterval()} method returns the time announcing interval.
	 * 
	 * @return the interval at which the time is announced on the server
	 */
	public int getInterval() {
		return this.interval;
	}
	
	/**
	 * The {@link #setInterval()} method sets the time announcing interval.
	 * 
	 * @param interval the interval at which the time is announced on the server
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
	 * The {@link #getMessage()} method returns the time message.
	 * 
	 * @return the message shown to players when the time is announced
	 */
	public String getMessage() {
		return this.message;
	}
	
	/**
	 * The {@link #setMessage()} method sets the time message.
	 * 
	 * @param message the message displayed when showing the time
	 */
	public void setMessage(String message) {
		this.message = message;
		config.load();
		config.setProperty(nodes[2], message);
		config.save();
	}
	
	/** 
	 * The {@link #getFormat()} method returns the time format.
	 * 
	 * @return the time format(12 or 24) used to display the time
	 */
	public int getFormat() {
		return this.format;
	}
	
	/**
	 * The {@link #setFormat(int)} method sets the time format. It also calls
	 * the {@link #checkFormat()} method to verify that the format is set
	 * appropriately.
	 * 
	 * @param format
	 * @return true is the format value is changed, false otherwise
	 */
	public boolean setFormat(int format) {
		this.format = format;
		if (!checkFormat()) {
			return false;
		}
		config.load();
		config.setProperty(nodes[3], format);
		config.save();
		return true;
	}
	
	/**
	 * The {@link #checkInterval()} method is called to chekc to make sure that
	 * the time interval is within bounds. If it is greater than 60 or less than
	 * 1, then the {@link #interval} variable is assigned the value of 15.
	 * 
	 * @return true if the interval value is appropriate, otherwise false
	 */
	private boolean checkInterval() {
		if (interval > 60 || interval < 1) {
			this.interval = 15;
			return false;
		}
		return true;
	}
	
	/**
	 * The {@link #checkFormat()} method is called to check to make sure that
	 * the time format is either 12 or 24. If it is not, then the {@link #format}
	 * variable is assigned a value of 12.
	 * 
	 * @return true if the format value is appropriate, otherwise false
	 */
	private boolean checkFormat() {
		if (format != 12 && format != 24) {
			this.format = 12;
			return false;
		}
		return true;
	}
}