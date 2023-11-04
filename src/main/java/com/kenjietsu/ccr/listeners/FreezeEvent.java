package com.kenjietsu.ccr.listeners;

import com.kenjietsu.ccr.Ccr;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class FreezeEvent implements Listener {
    @EventHandler
    public void onFreeze(PlayerMoveEvent event) {
        NamespacedKey key = new NamespacedKey(Ccr.getPlugin(Ccr.class), "freeze");
        PersistentDataContainer container = event.getPlayer().getPersistentDataContainer();
        if (container.has(key, PersistentDataType.STRING)) {
            if (event.hasChangedBlock()) {
                event.setCancelled(true);
            }

        }
    }
}
