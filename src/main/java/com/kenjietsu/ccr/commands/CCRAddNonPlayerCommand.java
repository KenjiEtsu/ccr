package com.kenjietsu.ccr.commands;

import com.kenjietsu.ccr.Ccr;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class CCRAddNonPlayerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length != 1 && args.length != 2) {
                player.sendMessage("Faltan argumentos(o sobran)");
                return true;
            }
            if (args.length == 1) {
                if (args[0].equals("balon")) {
                    NamespacedKey key = new NamespacedKey(Ccr.getPlugin(Ccr.class), "balon");
                    PersistentDataContainer container = player.getPersistentDataContainer();
                    container.set(key, PersistentDataType.STRING, "balon");
                    return true;
                }
                if (args[0].equals("spleef")) {
                    NamespacedKey key = new NamespacedKey(Ccr.getPlugin(Ccr.class), "spleef");
                    PersistentDataContainer container = player.getPersistentDataContainer();
                    container.set(key, PersistentDataType.STRING, "spleef");
                    return true;
                }
            }
            if (args.length == 2) {
                if (args[0].equals("balon")) {
                    NamespacedKey key = new NamespacedKey(Ccr.getPlugin(Ccr.class), "balon");
                    PersistentDataContainer container = Bukkit.getPlayer(args[1]).getPersistentDataContainer();
                    container.set(key, PersistentDataType.STRING, "balon");
                    return true;
                }
                if (args[0].equals("spleef")) {
                    NamespacedKey key = new NamespacedKey(Ccr.getPlugin(Ccr.class), "spleef");
                    PersistentDataContainer container = Bukkit.getPlayer(args[1]).getPersistentDataContainer();
                    container.set(key, PersistentDataType.STRING, "spleef");
                    return true;
                }
            }
        } else {
            sender.sendMessage("Solo jugadores");
        }
        return true;
    }
}
