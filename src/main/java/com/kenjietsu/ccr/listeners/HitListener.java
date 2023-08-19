package com.kenjietsu.ccr.listeners;

import com.kenjietsu.ccr.event.TimeoutEvent;
import com.kenjietsu.ccr.eventManager.DodgeBallEvent;
import com.kenjietsu.ccr.eventManager.EsconditeEvent;
import com.kenjietsu.ccr.eventManager.SpleefEvent;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import static com.kenjietsu.ccr.eventManager.DodgeBallEvent.isDodgeEventOn;
import static com.kenjietsu.ccr.eventManager.SpleefEvent.isSpleefEventOn;

public class HitListener implements Listener {

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
                    sacrificarDodge(player);
                }
                if(dodgeBallEvent.getBluePlayers().contains(player)) {
                    dodgeBallEvent.deleteBluePlayers(player);
                    sacrificarDodge(player);
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
                if (isDodgeEventOn()) {
                    DodgeBallEvent dodgeBallEvent = DodgeBallEvent.getDodgeBallEvent();


                    if(dodgeBallEvent.getRedPlayers().contains(player)) {
                        dodgeBallEvent.deleteRedPlayers(player);
                        sacrificarDodge(player);

                    }
                    if(dodgeBallEvent.getBluePlayers().contains(player)) {
                        dodgeBallEvent.deleteBluePlayers(player);
                        sacrificarDodge(player);
                    }
                }
                if (isSpleefEventOn()) {
                    SpleefEvent spleefEvent = SpleefEvent.getSpleefEvent();
                    try {
                        sacrificarDodge(player);
                        spleefEvent.killPlayer(player);
                    } catch (Exception e) {
                        Bukkit.getLogger().warning("Jugador no encontrado");
                    }
                }




                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player damager) {
            if (damager.getInventory().getItemInMainHand() == EsconditeEvent.getCatcherItem()) {
                sacrificarDodge((Player) event.getEntity());
            }
        }
    }

    @EventHandler
    public void timerEnd(TimeoutEvent event) {
        switch (event.getEventID()) {
            case 8:
                DodgeBallEvent dodgeBallEvent = DodgeBallEvent.getDodgeBallEvent();
                if (dodgeBallEvent.getRedPlayers().size() > dodgeBallEvent.getBluePlayers().size()) {
                    for (Player player : dodgeBallEvent.getBluePlayers() ) {
                        sacrificarDodge(player);
                        }
                }
                if (dodgeBallEvent.getBluePlayers().size() > dodgeBallEvent.getRedPlayers().size()) {
                    for (Player player : dodgeBallEvent.getRedPlayers() ) {
                        sacrificarDodge(player);
                    }
                }
                if (dodgeBallEvent.getBluePlayers().size() == dodgeBallEvent.getRedPlayers().size()) {
                    Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
                    Team redTeam = scoreboard.getTeam("redTeam");
                    Team blueTeam = scoreboard.getTeam("blueTeam");
                    for (Player player : dodgeBallEvent.getRedPlayers() ) {
                        redTeam.removeEntry(player.getName());
                        dodgeBallEvent.winnerFinish(player);
                    }
                    for (Player player : dodgeBallEvent.getBluePlayers() ) {
                        blueTeam.removeEntry(player.getName());
                        dodgeBallEvent.winnerFinish(player);
                    }
                }
                break;
            case 7:
                break;
            case 6:
                break;
            case 5:
                break;
            case 4:
                break;
            case 3:
                break;
            case 2:
                break;
            case 1:
                break;
            case 0:
                Sound witherSpawn = Sound.sound(org.bukkit.Sound.ENTITY_WITHER_SPAWN.key(), Sound.Source.MASTER, 1, 1);
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.playSound(witherSpawn, Sound.Emitter.self());
                }
                EsconditeEvent.getEsconditeEvent().releaseCatcher();
            case 9:
                EsconditeEvent.getEsconditeEvent().endEvent();
                break;

        }
    }

    private void sacrificarDodge(Player player) {
        Location location = player.getLocation();
        player.getWorld().spawnParticle(Particle.FLASH, location, 1, 0, 0, 0, 0);
        Component message = Component.text("◆ ").color(TextColor.color(0xFF0027)).decorate(TextDecoration.BOLD)
                .append(Component.text(player.getName()).color(TextColor.color(0xFFFFFF)).decorate(TextDecoration.BOLD))
                .append(Component.text(" fue llevado a ").color(TextColor.color(0xFF0027)).decorate(TextDecoration.BOLD))
                .append(Component.text("sacrificar").color(TextColor.color(0xFF0027)).decorate(TextDecoration.BOLD));

        for (Player players : Bukkit.getOnlinePlayers()) {
            players.sendMessage(message);
        }

        MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        MVWorldManager worldManager = core.getMVWorldManager();
        MultiverseWorld world = worldManager.getMVWorld("void");
        player.teleport(new Location(world.getCBWorld(), 168.5, 24, 666.5));
        player.removePotionEffect(PotionEffectType.GLOWING);
        player.getInventory().clear();
        player.setFireTicks(0);
    }

}
