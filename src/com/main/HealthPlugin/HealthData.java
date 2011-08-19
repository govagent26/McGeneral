package com.main.HealthPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.main.McConfig;

/**
 * The <b>HealthData</b> class is used to store all the health data that is in the
 * external health data file.
 */
public class HealthData {
	
	/** The {@link #nodes} variable holds the stings for accessing the data file nodes */
	private String[] nodes = {"health.near-death.ammount", "health.near-death.interval",
			"health.near-death.messages"};
	
	/** The {@link #config} variable is used to access the <b>Config</b> class */
	private McConfig config;
	
	/** The {@link #ammount} variable that holds at what health to announce near death messages */
	private int ammount;
	
	/** The {@link #interval} variable that holds time announcing interval(in seconds) */
	private int interval;
	
	/** The {@link #messages} variable that holds all near death messages */
	private List<String> messages;
	
	/**
	 * The {@link #HealthData(McConfig)} constructor is called when the plugin is
	 * initialized.
	 * <p>
	 * The constructor stores the {@link #config} variable for easy future access.
	 * It then calls the {@link #readHealthData()} method to read data from the
	 * external yaml file and store it in this class.
	 * 
	 * @param config the configuration variable that can access the time data
	 */
	public HealthData (McConfig config) {
		this.config = config;
		readHealthData();
	}
	
	/**
	 * The {@link #readHealthData()} method is called when the data from the external 
	 * files needs to be read.
	 * <p>
	 * Data is read from the external yaml file and stored in many variables in this 
	 * class. It is stored here for easy future access when determining what the 
	 * user settings are.
	 * <p>
	 * The variable data is stored in {@link #ammount}, {@link #interval}, and
	 * {@link #messages}.
	 */
	public void readHealthData() {
		config.load();
		ammount = config.getInt(nodes[0], 2);
		interval = config.getInt(nodes[1], 10);
		messages = config.getStringList(nodes[2], new ArrayList<String>());
		config.save();
		checkMessages();
	}
	
	/**
	 * The {@link #getInterval()} method is called to retrieve the interval at which
	 * near death messages are announced to near death players.
	 * 
	 * @return the interval at which to announce near death messages
	 */
	public int getInterval() {
		return interval;
	}
	
	/**
	 * The {@link #getAmmount()} method is called to retrieve the health ammount required
	 * to send a near death message.
	 * 
	 * @return the ammount of health needed to recieve a near death message
	 */
	public int getAmmount() {
		return ammount;
	}
	
	/**
	 * The {@link #getRandomMessage()} method is called to randomly pick a message from
	 * the {@link #messages} variable to return.
	 * 
	 * @return a random near death message
	 */
	public String getRandomMessage() {
		Random random = new Random();
		
		return messages.get(random.nextInt(messages.size()) - 1);
	}
	
	/**
	 * The {@link #checkMessages()} method is called to make sure that the {@link #messages}
	 * variable is populated with at least one message to display. If it is empty, then a
	 * default message is placed in it.
	 */
	private void checkMessages() {
		if (messages.size() == 0) {
			messages.add("I'm Dying....");
		}
	}
} 