package com.kenjietsu.ccr.listeners;

import com.kenjietsu.ccr.Ccr;
import com.kenjietsu.ccr.eventManager.EuroCristalesEvent;
import com.kenjietsu.ccr.eventManager.utils.Lists;
import org.bukkit.*;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import static com.kenjietsu.ccr.utils.MVCoreUtils.getMVWorld;

public class EuroCristalesListener implements Listener {
    @EventHandler
    public void onPLayerMove(PlayerMoveEvent event) {
        if (!event.hasChangedPosition()) {
            return;
        }
        if (event.getPlayer().getLocation().clone().add(0, -1, 0).getBlock() == Material.AIR.createBlockData()) {
            return;
        }

        Location playerLocation = event.getPlayer().getLocation();
        World world = getMVWorld("voidd").getCBWorld();
        if (!playerLocation.getWorld().equals(world)) {
            return;
        }
        if (!EuroCristalesEvent.isEuroCristalesEventOn()) {
            return;
        }
        EuroCristalesEvent euroCristalesEvent = EuroCristalesEvent.getEuroCristalesEvent();
        if (playerLocation.getX() < -833 && playerLocation.getX() > -842 && playerLocation.getZ() < 440 && playerLocation.getZ() > 381) {
            int i = 0;
            for (Location location : Lists.getLightGrayEuroLocations()) {
                location.add(1, 0, 0);
                Location location2 = location.clone().add(-3, 1.5, 8);
                if (playerLocation.getY() > location.getY() && playerLocation.getY() < location2.getY()) {
                    if (playerLocation.getX() < location.getX() && playerLocation.getX() > location2.getX()) {
                        if (playerLocation.getZ() > location.getZ() && playerLocation.getZ() < location2.getZ()) {
                            if (!euroCristalesEvent.getLightGrayCristales().get(i)) {
                                continue;
                            }
                            spawnCardinalFallingGlass(location, Material.LIGHT_GRAY_STAINED_GLASS, world, 0);
                            euroCristalesEvent.flipItemInList(0, i);
                        }
                    }
                }
                i++;
            }
        }
        if (playerLocation.getX() < -833 && playerLocation.getX() > -842 && playerLocation.getZ() < 583 && playerLocation.getZ() > 523) {
            int i = 0;
            for (Location location : Lists.getYellowEuroLocations()) {
                location.add(1, 0, 0);
                Location location2 = location.clone().add(-3, 1.5, 8);
                if (playerLocation.getY() > location.getY() && playerLocation.getY() < location2.getY()) {
                    if (playerLocation.getX() < location.getX() && playerLocation.getX() > location2.getX()) {
                        if (playerLocation.getZ() > location.getZ() && playerLocation.getZ() < location2.getZ()) {
                            if (!euroCristalesEvent.getYellowCristales().get(i)) {
                                continue;
                            }
                            spawnCardinalFallingGlass(location, Material.YELLOW_STAINED_GLASS, world, 0);
                            euroCristalesEvent.flipItemInList(1, i);
                        }
                    }

                }
                i++;
            }
        }
        if (playerLocation.getX() < -878 && playerLocation.getX() > -938 && playerLocation.getZ() < 486 && playerLocation.getZ() > 477) {
            int i = 0;
            for (Location location : Lists.getPinkEuroLocations()) {
                Location location2 = location.clone().add(8, 1.5, 3);
                if (playerLocation.getY() > location.getY() && playerLocation.getY() < location2.getY()) {
                    if (playerLocation.getX() > location.getX() && playerLocation.getX() < location2.getX()) {
                        if (playerLocation.getZ() > location.getZ() && playerLocation.getZ() < location2.getZ()) {
                            if (!euroCristalesEvent.getPinkCristales().get(i)) {
                                continue;
                            }
                            spawnCardinalFallingGlass(location, Material.PINK_STAINED_GLASS, world, 1);
                            euroCristalesEvent.flipItemInList(2, i);
                        }
                    }
                }
                i++;
            }
        }
        if (playerLocation.getX() < -736 || playerLocation.getX() > -796 && playerLocation.getZ() < 486 ||
                playerLocation.getZ() > 477) {
            int i = 0;
            for (Location location : Lists.getMagentaEuroLocations()) {
                Location location2 = location.clone().add(8, 1.5, 3);
                if (playerLocation.getY() > location.getY() && playerLocation.getY() < location2.getY()) {
                    if (playerLocation.getX() > location.getX() && playerLocation.getX() < location2.getX()) {
                        if (playerLocation.getZ() > location.getZ() && playerLocation.getZ() < location2.getZ()) {
                            if (!euroCristalesEvent.getMagentaCristales().get(i)) {
                                continue;
                            }
                            spawnCardinalFallingGlass(location, Material.MAGENTA_STAINED_GLASS, world,1);
                            euroCristalesEvent.flipItemInList(3, i);
                        }
                    }
                }
                i++;
            }
        }
        if (playerLocation.getX() < -764 && playerLocation.getX() > -814 && playerLocation.getZ() < 458 && playerLocation.getZ() > 408) {
            int k = 0;
            for (Location location : Lists.getOrangeEuroLocations()) {
                location.add(1, 0, -1);
                Location location2 = location.clone().add(-6, 1.5, 6);
                if (playerLocation.getY() > location.getY() && playerLocation.getY() < location2.getY()) {
                    if (playerLocation.getX() < location.getX() && playerLocation.getX() > location2.getX()) {
                        if (playerLocation.getZ() > location.getZ() && playerLocation.getZ() < location2.getZ()) {
                            if (!euroCristalesEvent.getOrangeCristales().get(k)) {
                                continue;
                            }
                            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                            scheduler.scheduleSyncDelayedTask(Ccr.getPlugin(Ccr.class), () -> {
                                location.add(-1.5, 0, 0.5);
                                spawnFallingGlass(location, Material.ORANGE_STAINED_GLASS, world);
                                location.add(1, 0, 1);
                                world.playSound(location, Sound.BLOCK_GLASS_BREAK, 1, 1);
                                for (int i = 0; i < 6; i++) {
                                    for (int j = 0; j < 3; j++) {
                                        if (i == 5 && j == 2) {
                                            continue;
                                        }
                                        spawnFallingGlass(location, Material.ORANGE_STAINED_GLASS, world);
                                        location.add(-1, 0, 0);
                                        }
                                    location.add(2, 0, 1);
                                    }
                            }, 7L);
                            euroCristalesEvent.flipItemInList(4, k);
                        }
                    }
                }
                k++;
            }
        }
        // -764 555, -814 505
        if (playerLocation.getX() < -764 && playerLocation.getX() > -814 && playerLocation.getZ() < 555 && playerLocation.getZ() > 505) {
            int k = 0;
            for (Location location : Lists.getLightBlueLocations()) {
                location.add(2, 0, 1);
                Location location2 = location.clone().add(-7, 1.5, -7);
                if (playerLocation.getY() > location.getY() && playerLocation.getY() < location2.getY()) {
                    if (playerLocation.getX() < location.getX() && playerLocation.getX() > location2.getX()) {
                        if (playerLocation.getZ() < location.getZ() && playerLocation.getZ() > location2.getZ()) {
                            if (!euroCristalesEvent.getLightBlueCristales().get(k)) {
                                continue;
                            }
                            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                            scheduler.scheduleSyncDelayedTask(Ccr.getPlugin(Ccr.class), () -> {

                                location.add(-0.5, 0, -1.5);
                                spawnFallingGlass(location, Material.LIGHT_BLUE_STAINED_GLASS, world);
                                location.add(-1, 0, 1);
                                world.playSound(location, Sound.BLOCK_GLASS_BREAK, 1, 1);
                                for (int i = 0; i < 6; i++) {
                                    for (int j = 0; j < 3; j++) {
                                        if (i == 5 && j == 2) {
                                            continue;
                                        }
                                        spawnFallingGlass(location, Material.LIGHT_BLUE_STAINED_GLASS, world);
                                        location.add(0, 0, -1);
                                    }
                                    location.add(-1, 0, 2);
                                }
                            }, 7L);
                            euroCristalesEvent.flipItemInList(7, k);
                        }
                    }
                }
                k++;
            }
        }
        if ( playerLocation.getX() < -861 && playerLocation.getX() > -911 && playerLocation.getZ() < 458 && playerLocation.getZ() > 408) {
            int k = 0;
            for (Location location : Lists.getGrayEuroLocations()) {
                location.add(-1, 0, 0);
                Location location2 = location.clone().add(7, 1.5, 7);
                if (playerLocation.getY() > location.getY() && playerLocation.getY() < location2.getY()) {
                    if (playerLocation.getX() > location.getX() && playerLocation.getX() < location2.getX()) {
                        if (playerLocation.getZ() > location.getZ() && playerLocation.getZ() < location2.getZ()) {
                            if (!euroCristalesEvent.getGrayCristales().get(k)) {
                                continue;
                            }

                            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                            scheduler.scheduleSyncDelayedTask(Ccr.getPlugin(Ccr.class), () -> {
                                location.add(0.5, 0, 1.5);
                                spawnFallingGlass(location, Material.GRAY_STAINED_GLASS, world);
                                location.add(1, 0, -1);
                                world.playSound(location, Sound.BLOCK_GLASS_BREAK, 1, 1);
                                for (int i = 0; i < 6; i++) {
                                    for (int j = 0; j < 3; j++) {
                                        if (i == 5 && j == 2) {
                                            continue;
                                        }
                                        spawnFallingGlass(location, Material.GRAY_STAINED_GLASS, world);
                                        location.add(0, 0, 1);
                                    }
                                    location.add(1, 0, -2);
                                }
                            }, 7L);
                            euroCristalesEvent.flipItemInList(5, k);
                        }
                    }
                }
                k++;
            }
        }
        if (playerLocation.getX() > -911 && playerLocation.getX() < -861 && playerLocation.getZ() < 555 && playerLocation.getZ() > 505) {
            int k = 0;
            for (Location location : Lists.getLimeEuroLocations()) {
                location.add(0, 0, 2);
                Location location2 = location.clone().add(7, 1.5, -7);
                if (playerLocation.getY() > location.getY() && playerLocation.getY() < location2.getY()) {
                    if (playerLocation.getX() > location.getX() && playerLocation.getX() < location2.getX()) {
                        if (playerLocation.getZ() < location.getZ() && playerLocation.getZ() > location2.getZ()) {
                            if (!euroCristalesEvent.getLimeCristales().get(k)) {
                                continue;
                            }
                            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                            scheduler.scheduleSyncDelayedTask(Ccr.getPlugin(Ccr.class), () -> {
                                location.add(1.5, 0, -0.5);
                                spawnFallingGlass(location, Material.LIME_STAINED_GLASS, world);
                                location.add(-1, 0, -1);
                                world.playSound(location, Sound.BLOCK_GLASS_BREAK, 1, 1);
                                for (int i = 0; i < 6; i++) {
                                    for (int j = 0; j < 3; j++) {
                                        if (i == 5 && j == 2) {
                                            continue;
                                        }
                                        spawnFallingGlass(location, Material.LIME_STAINED_GLASS, world);
                                        location.add(1, 0, 0);
                                    }
                                    location.add(-2, 0, -1);
                                }
                            }, 7L);
                            euroCristalesEvent.flipItemInList(6, k);
                        }
                    }
                }
                k++;
            }
        }

    }
    private void spawnCardinalFallingGlass(Location location, Material material, World world, int direction) {
        if (direction == 0) {
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.scheduleSyncDelayedTask(Ccr.getPlugin(Ccr.class), () -> {
                location.add(-0.5, 0, 1.5);
                world.playSound(location, Sound.BLOCK_GLASS_BREAK, 1, 1);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 8; j++) {
                        spawnFallingGlass(location, material, world);
                        location.add(0, 0, 1);
                    }
                    location.add(-1, 0, -8);
                }
            }, 7L);
        } else {
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.scheduleSyncDelayedTask(Ccr.getPlugin(Ccr.class), () -> {
                location.add(0.5, 0, 0.5);
                world.playSound(location, Sound.BLOCK_GLASS_BREAK, 1, 1);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 8; j++) {
                        spawnFallingGlass(location, material, world);
                        location.add(1, 0, 0);
                    }
                    location.add(-8, 0, 1);
                }
            }, 7L);
        }

    }

    public static void spawnFallingGlass(Location location, Material material, World world) {
        location.getBlock().setType(Material.AIR);
        FallingBlock fallingBlock = world.spawnFallingBlock(location, material.createBlockData());
        fallingBlock.setDropItem(false);
        fallingBlock.setVelocity(new Vector(0, -0.25, 0));

        world.spawnParticle(Particle.BLOCK_CRACK, location, 10, 0.5, 0.5, 0.5, material.createBlockData());
    }
}
