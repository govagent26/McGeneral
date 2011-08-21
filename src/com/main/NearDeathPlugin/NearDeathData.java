package com.main.NearDeathPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.main.McConfig;

/**
 * The <b>NearDeathData</b> class is used to store all the near death data that is in the
 * external near death data file.
 */
public class NearDeathData {
	
	/** The {@link #nodes} variable holds the stings for accessing the data file nodes */
	private String[] nodes = {"health.near-death.annouce", "health.near-death.health",
			"health.near-death.interval", "health.near-death.messages"};
	
	/** The {@link #config} variable is used to access the <b>Config</b> class */
	private McConfig config;
	
	/** The {@link #annouce} variable that holds whether or not to announce the messages */
	private boolean announce;
	
	/** The {@link #health} variable that holds at what health to announce near death messages */
	private int health;
	
	/** The {@link #interval} variable that holds near death announcing interval(in seconds) */
	private int interval;
	
	/** The {@link #messages} variable that holds all near death messages */
	private List<String> messages;
	
	/**
	 * The {@link #NearDeathData(McConfig)} constructor is called when the plugin is
	 * initialized.
	 * <p>
	 * The constructor stores the {@link #config} variable for easy future access.
	 * It then calls the {@link #readNearDeathData()} method to read data from the
	 * external yaml file and store it in this class.
	 * 
	 * @param config the configuration variable that can access the near death data
	 */
	public NearDeathData (McConfig config) {
		this.config = config;
		readNearDeathData();
	}
	
	/**
	 * The {@link #readNearDeathData()} method is called when the data from the external 
	 * files needs to be read.
	 * <p>
	 * Data is read from the external yaml file and stored in many variables in this 
	 * class. It is stored here for easy future access when determining what the 
	 * user settings are.
	 * <p>
	 * The variable data is stored in {@link #announce}, {@link #health},
	 * {@link #interval}, and {@link #messages}.
	 */
	public void readNearDeathData() {
		config.load();
		announce = config.getBoolean(nodes[0], true);
		health = config.getInt(nodes[1], 2);
		interval = config.getInt(nodes[2], 10);
		messages = config.getStringList(nodes[3], new ArrayList<String>());
		config.save();
		checkInterval();
		checkMessages();
	}
	
	/**
	 * The {@link #getAnnounceStatus()} method returns the near death announcing status.
	 * 
	 * @return whether or not to announce near get messages
	 */
	public boolean getAnnounceStatus() {
		return announce;
	}
	
	/**
	 * The {@link #setAnnounceStatus()} method sets the near death announce status by
	 * changing the {@link #announce} variable data and editing the external
	 * yaml file.
	 * 
	 * @param announce whether to announce near death messages at certain intervals
	 */
	public void setAnnounceStatus(boolean announce) {
		this.announce = announce;
		config.load();
		config.setProperty(nodes[0], announce);
		config.save();
	}
	
	/**
	 * The {@link #getHealth()} method returns the near death health requirement.
	 * 
	 * @return the health of health needed to recieve a near death message
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * The {@link #setHealth(int)} method sets the near death health ammount by changing
	 * the {@link #health} varaible data and editing the external yaml file.
	 * 
	 * @param health the near death health requirement
	 */
	public void setHealth(int health) {
		this.health = health;
		config.load();
		config.setProperty(nodes[1], health);
		config.save();
	}
	
	/**
	 * The {@link #getInterval()} method returns the near death announcing interval.
	 * 
	 * @return the interval at which to announce near death messages
	 */
	public int getInterval() {
		return interval;
	}
	
	/**
	 * The {@link #setInterval()} method sets the near death announcing interval by
	 * changing the {@link #interval} variable data and editing the external
	 * yaml file. It also calls the {@link #checkInterval()} method to verify 
	 * that the interval is set appropriately.
	 * 
	 * @param interval the interval at which near death messages are announced on the server
	 * @return true is the interval value is changed, otherwise false
	 */
	public boolean setInterval(int interval) {
		this.interval = interval;
		if (!checkInterval()) {
			return false;
		}
		config.load();
		config.setProperty(nodes[2], interval);
		config.save();
		return true;
	}
	
	/**
	 * The {@link #getMessages()} method returns all the near death message from the
	 * {@link #messages} variable.
	 * 
	 * @return all the near death messages
	 */
	public List<String> getMessages() {
		return messages;
	}
	
	/**
	 * The {@link #getRandomMessage()} method randomly picks a message from
	 * the {@link #messages} variable to return.
	 * 
	 * @return a random near death message
	 */
	public String getRandomMessage() {
		Random random = new Random();
		
		return messages.get(random.nextInt(messages.size()) - 1);
	}
	
	/**
	 * The {@link #checkInterval()} method checks to make sure that the near death interval
	 * is within bounds. If it is greater than 60 or less than 1, then the {@link #interval}
	 * variable is assigned the value of 10.
	 * 
	 * @return true if the interval value is appropriate, otherwise false
	 */
	private boolean checkInterval() {
		if (interval > 60 || interval < 1) {
			this.interval = 10;
			return false;
		}
		return true;
	}
	
	/**
	 * The {@link #checkMessages()} method makes sure that the {@link #messages}
	 * variable is populated with at least one message to display. If it is empty, then a
	 * default message is placed in it.
	 * 
	 * @return true if messages is populated, otherwise false
	 */
	private boolean checkMessages() {
		if (messages.size() == 0) {
			messages.add("I'm Dying....");
			return false;
		}
		return true;
	}
} 