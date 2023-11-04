package com.kenjietsu.ccr.listeners;

import com.kenjietsu.ccr.items.ItemManager;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemListener implements Listener {
    @EventHandler
    public void onItemUse(PlayerInteractEvent event) {
        if (!event.getAction().isRightClick() || event.getItem() == null || !event.getItem().getItemMeta().equals(ItemManager.aote.getItemMeta())) {
            return;
        }

        Sound sound = Sound.sound(org.bukkit.Sound.ENTITY_ENDERMAN_TELEPORT, Sound.Source.PLAYER, 1, 1);
        //raytrace to find the first non-air block
        Location location;
        Location lastAir = null;
        for (int i = 0; i < 12; i++) {
            location = event.getPlayer().getLocation().add(event.getPlayer().getLocation().getDirection().multiply(i));
            if (location.getBlock().getType().isAir()) {
                lastAir = location;
            } else {
                break;
            }
        }
        if (lastAir != null) {
            event.getPlayer().teleport(lastAir);
            event.getPlayer().playSound(sound, Sound.Emitter.self());
        }

    }
}
