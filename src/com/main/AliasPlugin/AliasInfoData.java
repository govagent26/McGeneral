package com.main.AliasPlugin;

import org.bukkit.ChatColor;

/**
 * The <b>AliasInfoData</b> class is used to store each player's alias and
 * name color.
 */
public class AliasInfoData {
	
	/** The {@link #chatcolor} variable stores the display name color */
	private ChatColor chatcolor;
	
	/** The {@link #alias} variable stores the display name without color */
	private String alias;

	/**
	 * The {@link #AliasInfoData(ChatColor, String)} constructor is called
	 * to store a player's display name data.
	 * <p>
	 * This constructor stores the {@link #chatcolor} and {@link #alias} variables
	 * for future easy access.
	 * 
	 * @param chatcolor the display name color
	 * @param alias the display name without color
	 */
	public AliasInfoData(ChatColor chatcolor, String alias) {
		this.chatcolor = chatcolor;
		this.alias = alias;
	}
	
	/**
	 * The {@link #getColor()} method retrieves the color of the display name.
	 * 
	 * @return the display name color
	 */
	public ChatColor getColor() {
		return chatcolor;
	}
	
	/**
	 * The {@link #setColor(ChatColor)} method changes the color of the display name.
	 * 
	 * @param color what the display name color is being changed to
	 */
	public void setColor(ChatColor color) {
		this.chatcolor = color;
	}
	
	/**
	 * The {@link #getAlias()} method retrieves the alias of the display name.
	 * 
	 * @return the display name without color
	 */
	public String getAlias() {
		return alias;
	}
	
	/**
	 * The {@link #setAlias(String)} method changes the alias of the display name.
	 * 
	 * @param alias what the display name is being changed to(without color)
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	/**
	 * The {@link #getDisplayName()} method retrieves a player's display name. This
	 * method combines the {@link #chatcolor} and {@link #alias} variables
	 * together to create the display name.
	 * 
	 * @return the display name of the player(name with color)
	 */
	public String getDisplayName() {
		return chatcolor.toString() + alias;
	}
}