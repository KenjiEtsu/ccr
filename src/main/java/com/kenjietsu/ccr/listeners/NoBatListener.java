package com.kenjietsu.ccr.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class NoBatListener implements Listener {
    @EventHandler
    public void onBatSpawn(EntitySpawnEvent event) {
        if (event.getEntity() instanceof org.bukkit.entity.Bat) {
            event.setCancelled(true);
        }
    }
}
