package com.main;

import java.io.File;

/**
 * The <b>Tick</b> class is used to schedule repeating tasks while the server is
 * running.
 */
public class Tick extends Thread {
	
	/** The {@link #plugin} variable holds the instance of the <b>McGeneral</b> plugin */
	private General plugin;
	
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
	 */
	public Tick(General plugin, File file) {
		this.plugin = plugin;
		this.file = file;
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
        			
        		}
        	});
        	try {
            	Thread.sleep(1000);
            } catch (Exception e){
            	logErrors(e);
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
	
	private void logErrors(Exception e) {
		//TODO figure out how to put data into a .txt file
	}
}