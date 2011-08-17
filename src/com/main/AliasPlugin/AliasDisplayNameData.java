package com.main.AliasPlugin;

import org.bukkit.ChatColor;

/**
 * The <b>AliasDisplayNameData</b> class is used to store each player's alias and
 * name color.
 */
public class AliasDisplayNameData {
	
	/** The {@link #chatcolor} variable stores the color of the display name */
	private ChatColor chatcolor;
	
	/** The {@link #alias} variable stores the alias of the display name */
	private String alias;

	/**
	 * The {@link #AliasDisplayNameData(ChatColor, String)} constructor is called
	 * to store a player's display name data.
	 * <p>
	 * This constructor stores the {@link #chatcolor} and {@link #alias} variables
	 * for future easy access.
	 * 
	 * @param chatcolor the color of the display name
	 * @param alias the alias of the display name
	 */
	public AliasDisplayNameData(ChatColor chatcolor, String alias) {
		this.chatcolor = chatcolor;
		this.alias = alias;
	}
	
	/**
	 * The {@link #getColor()} method is called to retrieve the color of the display
	 * name.
	 * 
	 * @return the color of the display name
	 */
	public ChatColor getColor() {
		return chatcolor;
	}
	
	/**
	 * The {@link #setColor(ChatColor)} method is called to change the color of the
	 * display name.
	 * 
	 * @param color the color that the display name is changed to
	 */
	public void setColor(ChatColor color) {
		this.chatcolor = color;
	}
	
	/**
	 * The {@link #getAlias()} method is called to retrieve the alias of the display
	 * name.
	 * 
	 * @return the alias of the display name(without color)
	 */
	public String getAlias() {
		return alias;
	}
	
	/**
	 * The {@link #setAlias(String)} method is called to change the alias of the
	 * display name.
	 * 
	 * @param alias the alias that the display name is changed to
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	/**
	 * The {@link #getDisplayName()} method is called to retrieve a player's display
	 * name. This method combines the {@link #chatcolor} and {@link #alias} variables
	 * together to create the display name.
	 * 
	 * @return the display name of the player(name and color)
	 */
	public String getDisplayName() {
		return chatcolor + alias;
	}
}