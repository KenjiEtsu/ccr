package com.kenjietsu.ccr.listeners;

import com.kenjietsu.ccr.eventManager.DodgeBallEvent;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import static com.kenjietsu.ccr.eventManager.DodgeBallEvent.isDodgeEventOn;
import static com.kenjietsu.ccr.utils.MainEventUtils.sacrificar;

public class DodgeBallListener implements Listener {
    @EventHandler
    public void onArrowHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow) {
            if (!isDodgeEventOn()) {
                return;
            }

            DodgeBallEvent dodgeBallEvent = DodgeBallEvent.getDodgeBallEvent();
            if (event.getHitEntity() instanceof Player player) {
                if(dodgeBallEvent.getRedPlayers().contains(player)) {
                    dodgeBallEvent.deleteRedPlayers(player);
                    sacrificar(player);
                }
                if(dodgeBallEvent.getBluePlayers().contains(player)) {
                    dodgeBallEvent.deleteBluePlayers(player);
                    sacrificar(player);
                }

            }

            if (event.getHitBlock() != null) {
                event.getEntity().remove();
            }

            if (event.getEntity().getShooter() instanceof Player player) {
                if (dodgeBallEvent.getRedPlayers().contains(player)) {
                    dodgeBallEvent.spawnArrow(1);
                } else if (dodgeBallEvent.getBluePlayers().contains(player)) {
                    dodgeBallEvent.spawnArrow(0);
                }
            }
        }

    }
    @EventHandler
    public void onBlockHit(EntityDamageByBlockEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (event.getDamager().getType() == Material.LAVA) {
                if (!isDodgeEventOn()) {
                    return;
                }
                DodgeBallEvent dodgeBallEvent = DodgeBallEvent.getDodgeBallEvent();

                if(dodgeBallEvent.getRedPlayers().contains(player)) {
                    dodgeBallEvent.deleteRedPlayers(player);
                    sacrificar(player);

                }
                if(dodgeBallEvent.getBluePlayers().contains(player)) {
                    dodgeBallEvent.deleteBluePlayers(player);
                    sacrificar(player);
                }

                event.setCancelled(true);
            }
        }
    }

}
