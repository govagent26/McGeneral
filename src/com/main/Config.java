package com.main;

import java.io.File;

import org.bukkit.util.config.Configuration;

/**
 * The <b>Config</b> class extends the <b>Configuration</b> class in
 * order to write data to the files when it is not found.
 */
public class Config extends Configuration {
	
	/**
	 * The {@link #Config(File)} constructor is called to store the
	 * file data in the parent class.
	 * 
	 * @param file the file data used to access and retrieve/alter data
	 */
	public Config(File file) {
        super(file);
    }
	
    @Override
    public int getInt(String path, int defaultValue) {
        if (getProperty(path) == null) {
            setProperty(path, defaultValue);
        }
        return super.getInt(path, defaultValue);
    }
    
    @Override
    public String getString(String path, String defaultValue) {
        if (getProperty(path) == null) {
            setProperty(path, defaultValue);
        }
        return super.getString(path, defaultValue);
    }
    
    @Override
    public boolean getBoolean(String path, boolean defaultValue) {
        if (getProperty(path) == null) {
            setProperty(path, defaultValue);
        }
        return super.getBoolean(path, defaultValue);
    }
}