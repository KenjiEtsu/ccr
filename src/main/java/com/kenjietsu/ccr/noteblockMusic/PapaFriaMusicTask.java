package com.kenjietsu.ccr.noteblockMusic;

import com.kenjietsu.ccr.Ccr;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.function.Consumer;

public class PapaFriaMusicTask implements Consumer<BukkitTask> {
    @Override
    public void accept(BukkitTask bukkitTask) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (int i = 0; i < 7; i++) {
                Bukkit.getScheduler().runTaskLater(Ccr.getPlugin(Ccr.class), () -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1F, 0.71F), i*4);
            }
            Bukkit.getScheduler().runTaskLater(Ccr.getPlugin(Ccr.class), () -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1F, 0.71F), 4L);
            Bukkit.getScheduler().runTaskLater(Ccr.getPlugin(Ccr.class), () -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1F, 0.71F), 12L);
            Bukkit.getScheduler().runTaskLater(Ccr.getPlugin(Ccr.class), () -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1F, 0.71F), 14L);
            Bukkit.getScheduler().runTaskLater(Ccr.getPlugin(Ccr.class), () -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1F, 0.71F), 20L);
            Bukkit.getScheduler().runTaskLater(Ccr.getPlugin(Ccr.class), () -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1F, 0.71F), 28L);
            Bukkit.getScheduler().runTaskLater(Ccr.getPlugin(Ccr.class), () -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1F, 0.71F), 30L);
            Bukkit.getScheduler().runTaskLater(Ccr.getPlugin(Ccr.class), () -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1F, 0.71F), 16L);
            Bukkit.getScheduler().runTaskLater(Ccr.getPlugin(Ccr.class), () -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1F, 0.53F), 8L);
            Bukkit.getScheduler().runTaskLater(Ccr.getPlugin(Ccr.class), () -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1F, 0.53F), 24L);

        }

    }
}
