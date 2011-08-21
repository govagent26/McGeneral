package com.main.PvpPlugin;

/**
* The <b>PvpInfoData</b> class is used to store each player's pvp data.
* */
public class PvpInfoData {
	
	/** The {@link #pvp} variables stores whether or not pvp is active for this player */
	private boolean pvp;
	
	/** The {@link #cooldown} variable stores the player's cooldown */
	private long cooldown;

	/**
	 * The {@link #PvpInfoData()} constructor is called to store a player's pvp data.
	 * <p>
	 * This constructor gives the {@link #cooldown} variable an intial value of 0 and
	 * the {@link #pvp} variable an intial value of false.
	 */
	public PvpInfoData() {
		this.pvp = false;
		this.cooldown = 0;
	}
	
	/**
	 * The {@link #switchPvp()} method switches the current player's pvp
	 * status.
	 */
	public void switchPvp() {
		this.pvp = !pvp;
	}
	
	/**
	 * The {@link #getPvp()} method retrieves the current player's pvp
	 * status.
	 * 
	 * @return true if pvp is enabled, otherwise false
	 */
	public boolean getPvp() {
		return pvp;
	}
	
	/**
	 * The {@link #setPvp(boolean)} method sets the current player's pvp
	 * status.
	 * 
	 * @param pvp the player's pvp status
	 */
	public void setPvp(boolean pvp) {
		this.pvp = pvp;
	}
	
	/**
	 * The {@link #getCooldown()} method retrieves the current player's
	 * cooldown.
	 * 
	 * @return the cooldown
	 */
	public long getCooldown() {
		return cooldown;
	}
	
	/**
	 * The {@link #setCooldown(long)} method sets the current player's
	 * cooldown.
	 * 
	 * @param cooldown the cooldown to set
	 */
	public void setCooldown(long cooldown) {
		this.cooldown = cooldown;
	}
}