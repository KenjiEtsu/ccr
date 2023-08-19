package com.kenjietsu.ccr.listeners;

import com.kenjietsu.ccr.eventManager.EsconditeEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import static com.kenjietsu.ccr.utils.MainEventUtils.sacrificar;

public class EsconditeListener implements Listener {
    @EventHandler
    public void onBlockClick(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Player damaged) {
            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == EsconditeEvent.getCatcherItem().getItemMeta().getCustomModelData()) {
                sacrificar(damaged);
            }
        }
    }
    @EventHandler
    public void onItemThrow(PlayerDropItemEvent event) {
        if (event.getItemDrop().getItemStack().getItemMeta().getCustomModelData() == EsconditeEvent.getCatcherItem().getItemMeta().getCustomModelData()) {
            event.setCancelled(true);
        }
    }
}
