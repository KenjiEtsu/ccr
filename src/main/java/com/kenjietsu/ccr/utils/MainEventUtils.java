package com.kenjietsu.ccr.utils;

import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import static com.kenjietsu.ccr.utils.MVCoreUtils.getMVWorld;

public class MainEventUtils {
    public static void sacrificar(Player player) {
        Location location = player.getLocation();
        player.getWorld().spawnParticle(Particle.FLASH, location, 1, 0, 0, 0, 0);
        Component message = Component.text("◆ ").color(TextColor.color(0xFF0027)).decorate(TextDecoration.BOLD)
                .append(Component.text(player.getName()).color(TextColor.color(0xFFFFFF)).decorate(TextDecoration.BOLD))
                .append(Component.text(" fue llevado a ").color(TextColor.color(0xFF0027)).decorate(TextDecoration.BOLD))
                .append(Component.text("sacrificar").color(TextColor.color(0xFF0027)).decorate(TextDecoration.BOLD));

        for (Player players : Bukkit.getOnlinePlayers()) {
            players.sendMessage(message);
        }

        MultiverseWorld world = getMVWorld("void");
        world.getCBWorld().spawnParticle(Particle.ASH, location, 100, 0.25, 1, 0.25, 0.1);
        player.teleport(new Location(world.getCBWorld(), 168.5, 24, 666.5));
        player.removePotionEffect(PotionEffectType.GLOWING);
        player.getInventory().clear();
        player.setFireTicks(0);
    }

}
