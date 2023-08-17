package com.kenjietsu.ccr.commands.ccr;

import com.kenjietsu.ccr.Ccr;
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
            NamespacedKey key = new NamespacedKey(Ccr.getPlugin(Ccr.class), "winner");
            PersistentDataContainer container = player.getPersistentDataContainer();
            container.set(key, PersistentDataType.STRING, "winner");

        } else {
            sender.sendMessage("Solo jugadores");
        }
        return true;
    }
}
