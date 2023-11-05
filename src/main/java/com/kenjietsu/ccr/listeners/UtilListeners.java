package com.kenjietsu.ccr.listeners;

import com.kenjietsu.ccr.utils.MVCoreUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class UtilListeners implements Listener {
    @EventHandler
    public void onBatSpawn(EntitySpawnEvent event) {
        if (event.getEntity() instanceof org.bukkit.entity.Bat ) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onEntityHurtFall(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player && event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            if (player.getWorld().equals(MVCoreUtils.getMVWorld("void").getCBWorld())) {
                event.setCancelled(true);
            }
        }
    }

}
