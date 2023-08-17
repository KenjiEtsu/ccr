package com.kenjietsu.ccr.listeners;

import com.kenjietsu.ccr.eventManager.DodgeBallEvent;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class HitListener implements Listener {

    @EventHandler
    public void onArrowHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow) {
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
    public void onLavaHit(EntityDamageByBlockEvent event) {
        if (event.getDamager().getType() == Material.LAVA) {
            DodgeBallEvent dodgeBallEvent = DodgeBallEvent.getDodgeBallEvent();




            if (event.getEntity() instanceof Player player) {

                if(dodgeBallEvent.getRedPlayers().contains(player)) {
                    dodgeBallEvent.deleteRedPlayers(player);
                }
                if(dodgeBallEvent.getBluePlayers().contains(player)) {
                    dodgeBallEvent.deleteBluePlayers(player);
                }

                sacrificar(player);
                event.setCancelled(true);
            }


        }
    }

    private void sacrificar(Player player) {
        Location location = player.getLocation();
        player.getWorld().spawnParticle(Particle.FLASH, location, 1);
        for (Player players : Bukkit.getOnlinePlayers()) {
            players.sendMessage("§c" + player.getName() + "§m Fue llevado a sacrificar");
        }

        MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        MVWorldManager worldManager = core.getMVWorldManager();
        MultiverseWorld world = worldManager.getMVWorld("void");
        player.teleport(new Location(world.getCBWorld(), 168.5, 24, 666.5));
    }

}
