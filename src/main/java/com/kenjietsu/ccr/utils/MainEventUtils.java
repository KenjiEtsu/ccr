package com.kenjietsu.ccr.utils;

import com.kenjietsu.ccr.Ccr;
import com.kenjietsu.ccr.eventManager.EuroCristalesEvent;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import io.papermc.paper.entity.TeleportFlag;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

import static com.kenjietsu.ccr.utils.MVCoreUtils.getMVWorld;

public class MainEventUtils {
    public static void sacrificar(Player player) {
        Location location = player.getLocation();
        player.getWorld().spawnParticle(Particle.FLASH, location, 1, 0, 0, 0, 0);
        Component message = Component.text("◆ ").color(TextColor.color(0xFF0027)).decorate(TextDecoration.BOLD)
                .append(Component.text(" " + player.getName()).color(TextColor.color(0xFFFFFF)).decorate(TextDecoration.BOLD))
                .append(Component.text("  fue llevado a ").color(TextColor.color(0xFF0027)).decorate(TextDecoration.BOLD))
                .append(Component.text("sacrificar").color(TextColor.color(0xFF0027)).decorate(TextDecoration.BOLD));

        for (Player players : Bukkit.getOnlinePlayers()) {
            players.sendMessage(message);
        }

        MultiverseWorld world = getMVWorld("void");
        world.getCBWorld().spawnParticle(Particle.ASH, location, 100, 0.25, 1, 0.25, 0.1);
        player.playSound(location, Sound.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 1, 0.5F);
        player.teleport(new Location(world.getCBWorld(), 168.5, 24, 666.5));
        player.removePotionEffect(PotionEffectType.GLOWING);
        player.getInventory().clear();
        player.setFireTicks(0);
    }

    public static void save(Player player) {
        World world = getMVWorld("void").getCBWorld();
        Location location = new Location(world, 168.593, -6, 704.636);
        player.teleport(location);
        player.setRotation(180, -20);
        player.playSound(location, Sound.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 1, 1);
        if (EuroCristalesEvent.isEuroCristalesEventOn()) {
            EuroCristalesEvent.getEuroCristalesEvent().removePlayer(player);
        }
        Component message = Component.text("◆ ").color(TextColor.color(0xFF0027)).decorate(TextDecoration.BOLD)
                .append(Component.text(" " +player.getName()).color(TextColor.color(0xFFFFFF)).decorate(TextDecoration.BOLD))
                .append(Component.text("  ha entrado al caliz").color(TextColor.color(0xFF0027)).decorate(TextDecoration.BOLD));

        for (Player players : Bukkit.getOnlinePlayers()) {
            players.sendMessage(message);
        }
    }
    public static void endSacrifice() {
        World world = getMVWorld("void").getCBWorld();
        Location location = new Location(world, 168.5, 7, 666.5);
        Location testLocation = new Location(world, 168.5, 23, 666.5);
        Collection<Player> players = testLocation.getNearbyPlayers(3);
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER, 1F, 0.5f);
        }
        for(int i = 0; i < 32; i++) {
            Location currentLocation = location.clone();
            Bukkit.getScheduler().runTaskLater(Ccr.getPlugin(Ccr.class), () -> world.spawnParticle(Particle.REDSTONE,
                    currentLocation, 100, 0.1, 0.3, 0.1, 0.01, new Particle.DustOptions(Color.RED, 1)), 2*i);
            location.add(0, 0.5, 0);
            if (i == 31) {
                Bukkit.getScheduler().runTaskLater(Ccr.getPlugin(Ccr.class), () -> {
                    world.strikeLightningEffect(location).setSilent(true);
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.playSound(player.getLocation(), Sound.ENTITY_SKELETON_HORSE_DEATH, SoundCategory.MASTER, 1F, 0.5f);
                    }
                    int l = 0;
                    for (Player player : players) {
                        ArmorStand armorStand = location.getWorld().spawn(location, ArmorStand.class);
                        armorStand.setInvisible(true);
                        armorStand.setInvulnerable(true);
                        armorStand.setGravity(false);
                        armorStand.setCustomNameVisible(false);
                        armorStand.setSmall(true);
                        armorStand.setCollidable(false);
                        armorStand.setSilent(true);
                        armorStand.setRemoveWhenFarAway(false);
                        armorStand.addPassenger(player);

                        Location location1 = new Location(world, 168, 23, 666);
                        location1.getBlock().setType(Material.AIR);
                        location1.add(0, -1, 0).getBlock().setType(Material.AIR);
                        location1.add(1, 1, 0).getBlock().setType(Material.AIR);
                        location1.add(-2, 0, 0).getBlock().setType(Material.AIR);
                        location1.add(1, 0, 1).getBlock().setType(Material.AIR);
                        location1.add(0, 0, -2).getBlock().setType(Material.AIR);

                        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.MASTER, 1F, 0.5f);
                        double k = 0;
                        double epsilon = 0.0001;
                        for (double j = 0; j < 2; j+=0.05) {
                            Location currentLocation2 = location.clone();
                            double finalJ = j;
                            double finalK = k;
                            Bukkit.getScheduler().runTaskLater(Ccr.getPlugin(Ccr.class), () -> {
                                armorStand.teleport(currentLocation2.add(1.5 * Math.cos(finalJ * Math.PI), -finalK, 1.5 * Math.sin(finalJ * Math.PI)), TeleportFlag.EntityState.RETAIN_PASSENGERS);
                            }, (long) (j * 35) + l);
                                if (Math.abs(j - 1.95) < epsilon) {
                                    Bukkit.getScheduler().runTaskLater(Ccr.getPlugin(Ccr.class), () -> {
                                        armorStand.remove();
                                        player.damage(Double.MAX_VALUE);
                                        // player.ban("Gracias por jugar, suerte en la proxima", (Date) null, "Sacrificio");
                                        Location location3 = new Location(world, 168, 23, 666);
                                        location3.getBlock().setType(Material.NETHER_WART_BLOCK);
                                        location3.add(0, -1, 0).getBlock().setType(Material.NETHER_WART_BLOCK);
                                        location3.add(1, 1, 0).getBlock().setType(Material.NETHER_WART_BLOCK);
                                        location3.add(-2, 0, 0).getBlock().setType(Material.NETHER_WART_BLOCK);
                                        location3.add(1, 0, 1).getBlock().setType(Material.NETHER_WART_BLOCK);
                                        location3.add(0, 0, -2).getBlock().setType(Material.NETHER_WART_BLOCK);

                                    }, (long) (j * 35 + 10) + l);
                                }
                                k+= 0.40;

                            }
                        l += 10;
                        }
                    Bukkit.getScheduler().runTaskLater(Ccr.getPlugin(Ccr.class), () -> {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.playSound(player.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER, 1F, 0.5f);
                        }

                    },80);


                }, 2*i+2);
            }
        }
    }
}
