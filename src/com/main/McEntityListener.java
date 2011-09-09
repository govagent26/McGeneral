package com.main;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;

import com.main.DeathAnnouncingPlugin.DeathAnnouncing;
import com.main.PvpPlugin.PvpData;

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
	 * 
	 * @param event the {@link #EntityDamageEvent} that stores all event and entity data
	 */
	@Override
	public void onEntityDamage(EntityDamageEvent event) { 
		if (event instanceof EntityDamageByEntityEvent) {
			EntityDamageByEntityEvent EDBEE = (EntityDamageByEntityEvent)event;
			
			if (EDBEE.getDamager() instanceof Player && EDBEE.getEntity() instanceof Player) {
				Player attacker = (Player)EDBEE.getEntity();
				Player defender = (Player)EDBEE.getDamager();
				
				if (!pvpData.getPvpStatus(attacker)) {
					event.setCancelled(true);
					plugin.sendMessage(attacker, "PvP is not allowed, you have it set 'off'");
				} else if (!pvpData.getPvpStatus(defender)) {
					event.setCancelled(true);
					plugin.sendMessage(attacker, "PvP is not allowed, your opponent has it set 'off'");
				}
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
			DeathAnnouncing.announceDeath(plugin, (Player)event.getEntity());
		}
	}
}