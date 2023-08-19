package com.kenjietsu.ccr.listeners;

import com.kenjietsu.ccr.eventManager.SpleefEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;

import static com.kenjietsu.ccr.eventManager.SpleefEvent.isSpleefEventOn;
import static com.kenjietsu.ccr.utils.MainEventUtils.sacrificar;

public class SpleefListener implements Listener {
    @EventHandler
    public void onBlockHit(EntityDamageByBlockEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (event.getDamager().getType() == Material.LAVA) {
                if (isSpleefEventOn()) {
                    SpleefEvent spleefEvent = SpleefEvent.getSpleefEvent();
                    try {
                        sacrificar(player);
                        spleefEvent.killPlayer(player);
                    } catch (Exception e) {
                        Bukkit.getLogger().warning("Jugador no encontrado");
                    }
                }




                event.setCancelled(true);
            }
        }
    }
}
