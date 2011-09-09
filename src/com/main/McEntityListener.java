package com.main;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;

import com.main.DeathAnnouncingPlugin.DeathAnnouncing;
import com.main.PvpPlugin.PvpData;
import com.main.ReincarnationPlugin.Reincarnation;

/**
 * The <b>McEntityListener</b> class is used to handle all registered <b>entity
 * events</b> and process them accordingly.
 */
public class McEntityListener extends EntityListener {
	
	/** The {@link #plugin} variable holds the instance of the <b>McGeneral</b> plugin */
	private General plugin;
	
	/** The {@link #pvpData} variable holds the class that stores all the pvp data */
	private PvpData pvpData;
	
	public McEntityListener(General plugin, PvpData pvpData) {
		this.plugin = plugin;
		this.pvpData = pvpData;
	}
	
	/**
	 * The {@link #onEntityDamage(EntityDamageEvent)} method is called when any entity has taken
	 * damage.
	 * <br>
	 * It checks to see if the attacking entity and defending entity were players. If both were,
	 * pvp status is checked to see if the damage is cancelled.
	 * <br>
	 * It also checks to see if the player died during the attack and checks to see if the player
	 * will be reincarnated.
	 * 
	 * @param event the {@link #EntityDamageEvent} that stores all event and entity data
	 */
	@Override
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player defender = (Player)event.getEntity();
			
			// PVP PLUGIN
			if (event instanceof EntityDamageByEntityEvent) {
				EntityDamageByEntityEvent EDBEE = (EntityDamageByEntityEvent)event;
				
				if (EDBEE.getDamager() instanceof Player) {
					Player attacker = (Player)EDBEE.getDamager();
					
					if (!pvpData.getPvpStatus(attacker)) {
						event.setCancelled(true);
						plugin.sendMessage(attacker, "PvP is not allowed, you have it set 'off'");
					} else if (!pvpData.getPvpStatus(defender)) {
						event.setCancelled(true);
						plugin.sendMessage(attacker, "PvP is not allowed, your opponent has it set 'off'");
					}
				}
			}
			// REINCARNATION PLUGIN
			if (!event.isCancelled() && defender.getHealth() <= 0 && !Reincarnation.checkAddPlayer(defender)) {
				event.setCancelled(true);
				defender.setHealth(20);
				plugin.sendMessage(defender, defender.getDisplayName() + " has reincarnated for the day!!!!", 20);
			}
		}
	}
	
	/**
	 * The {@link #onEntityDeath(EntityDeathEvent)} method is called when any entity has died
	 * on the server.
	 * <br>
	 * It checks to see if the entity was a player. If it was, then the death is announced to
	 * the server.
	 * 
	 * @param event the {@link #EntityDeathEvent} that stores all event and entity data
	 */
	@Override
	public void onEntityDeath(EntityDeathEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player)event.getEntity();
			
			// DEATH ANNOUNCING PLUGIN
			DeathAnnouncing.announceDeath(plugin, player);
		}
	}
}