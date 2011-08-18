package com.main.PrefixPlugin;

import org.bukkit.ChatColor;

/**
* The <b>PrefixInfoData</b> class is used to store each player's prefix
* and prefix color.
* */
public class PrefixInfoData {
		
	/** The {@link #chatcolor} variable stores the prefix color */
	private ChatColor chatcolor;
	
	/** The {@link #prefix} variable stores the prefix without color */
	private String prefix;

	/**
	 * The {@link #PrefixInfoData(ChatColor, String)} constructor is called
	 * to store a player's prefix data.
	 * <p>
	 * This constructor stores the {@link #chatcolor} and {@link #prefix} variables
	 * for future easy access.
	 * 
	 * @param chatcolor the prefix color
	 * @param prefix the prefix(without color)
	 */
	public PrefixInfoData(ChatColor chatcolor, String prefix) {
		this.chatcolor = chatcolor;
		this.prefix = prefix;
	}
	
	/**
	 * The {@link #getColor()} method is called to retrieve the color of the prefix.
	 * 
	 * @return the prefix color
	 */
	public ChatColor getColor() {
		return chatcolor;
	}
	
	/**
	 * The {@link #setColor(ChatColor)} method is called to change the color of the
	 * prefix.
	 * 
	 * @param color what the prefix color is being changed to
	 */
	public void setColor(ChatColor color) {
		this.chatcolor = color;
	}
	
	/**
	 * The {@link #getPrefix()} method is called to retrieve the prefix without color.
	 * 
	 * @return the prefix without color
	 */
	public String getPrefix() {
		return prefix;
	}
	
	/**
	 * The {@link #setPrefix(String)} method is called to change the prefix.
	 * 
	 * @param prefix what the prefix is being changed to
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	/**
	 * The {@link #getPrefixWithColor()} method is called to retrieve a player's
	 * prefix(includes color). This method combines the {@link #chatcolor} and 
	 * {@link #prefix} variables together to create the prefix.
	 * 
	 * @return the prefix of the player(prefix with color)
	 */
	public String getPrefixWithColor() {
		return chatcolor.toString() + prefix;
	}
}