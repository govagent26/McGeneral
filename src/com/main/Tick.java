package com.main;

import java.io.File;
import java.util.Calendar;

import com.main.NearDeathPlugin.NearDeath;
import com.main.NearDeathPlugin.NearDeathData;
import com.main.TimePlugin.Time;
import com.main.TimePlugin.TimeData;

/**
 * The <b>Tick</b> class is used to schedule repeating tasks while the server is
 * running.
 */
public class Tick extends Thread {
	
	/** The {@link #plugin} variable holds the instance of the <b>McGeneral</b> plugin */
	private General plugin;
	
	/** The {@link #timeData} variable holds the class that stores all the time data */
	private TimeData timeData;
	
	/** The {@link #neardeathData} variable holds the class that stores all the near death data */
	private NearDeathData neardeathData;
	
	/** The {@link #file} variable stores the datafolder for this plugin */
	private File file;

	/** The {@link #running} variable determines whether or not this thread should continue to run */
	private boolean running;
	
	/**
	 * The {@link #Tick()} constructor is called when the server is started to begin
	 * running a thread for repeating tasks.
	 * <p>
	 * The constructor stores the {@link #plugin} data and the {@link #file} data 
	 * locally for easy access by the methods in this class. It then starts the thread
	 * by assigning a value of true to {@link #running}.
	 * 
	 * @param plugin the plugin data for the <b>McGeneral</b> class
	 * @param file the dataFolder for this plugin
	 * @param timeData the <b>TimeData</b> class that holds the time settings from the yaml
	 * @param neardeathData the <b>NearDeathData</b> class that holds the health settings from the yaml
	 */
	public Tick(General plugin, File file, TimeData timeData, NearDeathData neardeathData) {
		this.plugin = plugin;
		this.file = file;
		this.timeData = timeData;
		this.neardeathData = neardeathData;
		running = true;
	}
	
	/**
	 * The {@link #tick} method runs as long as this plugin is running. This method
	 * calls the bukkit scheduler to schedule tasks that are meant to repeat for as
	 * long as the server is running.
	 */
	public void run() {
		while (running) {
        	plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
        		public void run() {
        			if (plugin.getServer().getOnlinePlayers().length > 0) {
        				Calendar time = Calendar.getInstance();
        				int second = time.get(Calendar.SECOND);
        			
        				if (second == 0) {
        					int minute = time.get(Calendar.MINUTE);
        				
        					Time.processTick(plugin, timeData, minute);
        				}
        				NearDeath.processTick(plugin, neardeathData, second);
        			}
        		}
        	});
        	try {
            	Thread.sleep(1000);
            } catch (Exception e){
            	logErrors(e.getMessage());
            }
        }
	}
	
	/**
	 * The {@link #setRunning(boolean)} method is called to change the value of the
	 * {@link #running} variable. This is mostly used to set the value to false to stop
	 * the thread from running any longer.
	 * 
	 * @param value whether or not the thread should continue to run
	 */
	public void setRunning(boolean value) {
		running = value;
	}
	
	/**
	 * The {@link #logErrors(Exception)} method is called to log exception errors from
	 * this class to a specified file.
	 * 
	 * @param emessage the error message to be displayed
	 */
	private void logErrors(String emessage) {
		String message = String.format("%1$tm-%1$td-%1$tY %1$tH %1$tM %1$tS : ", Calendar.getInstance());
		
		FileUtil.writeFile(new File(file, "Thread-Errors.txt"), message + emessage);
	}
}