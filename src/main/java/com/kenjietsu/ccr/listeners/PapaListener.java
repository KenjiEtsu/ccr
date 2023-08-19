package com.kenjietsu.ccr.listeners;

import com.kenjietsu.ccr.eventManager.PapaFriaEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.potion.PotionEffectType;

public class PapaListener implements Listener {
    @EventHandler
    public void onItemThrow(PlayerDropItemEvent event) {
        if (event.getItemDrop().getItemStack().getItemMeta().getCustomModelData() == PapaFriaEvent.getPapaFriaItem().getItemMeta().getCustomModelData()) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player damaged && event.getDamager() instanceof Player damager) {
            if (damager.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == PapaFriaEvent.getPapaFriaItem().getItemMeta().getCustomModelData()) {
                damager.getInventory().clear();
                damager.removePotionEffect(PotionEffectType.GLOWING);
                damager.removePotionEffect(PotionEffectType.SPEED);

                PapaFriaEvent papaFriaEvent = PapaFriaEvent.getPapaFriaEvent();
                papaFriaEvent.setPotater(damaged);
            }
        }
    }
}
