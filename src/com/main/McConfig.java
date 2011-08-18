package com.main;

import java.io.File;
import java.util.List;

import org.bukkit.util.config.Configuration;

/**
 * The <b>Config</b> class extends the <b>Configuration</b> class in order to write 
 * data to the files when it is not found.
 */
public class McConfig extends Configuration {
	
	/**
	 * The {@link #Config(File)} constructor is called to store the file data in 
	 * the parent class.
	 * 
	 * @param file the file data used to access and retrieve/alter data
	 */
	public McConfig(File file) {
        super(file);
    }
	
	/**
	 * The {@link #getInt(String, int)} method is used to retrieve an integer value
	 * from the configuration file. If no value exists, the <b>defaultvalue</b> is
	 * put into the file.
	 * 
	 * @param path the location in the file to look for the value
	 * @param defaultValue the value to put in the file if none exists
	 */
    @Override
    public int getInt(String path, int defaultValue) {
        if (getProperty(path) == null) {
            setProperty(path, defaultValue);
        }
        return super.getInt(path, defaultValue);
    }
    
	/**
	 * The {@link #getString(String, String)} method is used to retrieve a string value
	 * from the configuration file. If no value exists, the <b>defaultvalue</b> is
	 * put into the file.
	 * 
	 * @param path the location in the file to look for the value
	 * @param defaultValue the value to put in the file if none exists
	 */
    @Override
    public String getString(String path, String defaultValue) {
        if (getProperty(path) == null) {
            setProperty(path, defaultValue);
        }
        return super.getString(path, defaultValue);
    }
    
	/**
	 * The {@link #getBoolean(String, boolean)} method is used to retrieve a boolean value
	 * from the configuration file. If no value exists, the <b>defaultvalue</b> is
	 * put into the file.
	 * 
	 * @param path the location in the file to look for the value
	 * @param defaultValue the value to put in the file if none exists
	 */
    @Override
    public boolean getBoolean(String path, boolean defaultValue) {
        if (getProperty(path) == null) {
            setProperty(path, defaultValue);
        }
        return super.getBoolean(path, defaultValue);
    }
    
	/**
	 * The {@link #getKeys(String)} method is used to retrieve all the keys from
	 * the configuration file. If no keys exist, by default, a blank path will be
	 * put into the file.
	 * 
	 * @param path the location in the file to look for the keys
	 */
    @Override
    public List<String> getKeys(String path) {
    	if (getProperty(path) == null) {
    		setProperty(path, "");
    	}
    	return super.getKeys(path);
    }
}