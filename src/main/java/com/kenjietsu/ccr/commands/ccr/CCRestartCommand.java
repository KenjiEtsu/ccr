package com.kenjietsu.ccr.commands.ccr;

import com.kenjietsu.ccr.Ccr;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

public class CCRestartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        for (Player player: Bukkit.getOnlinePlayers()) {
            NamespacedKey key = new NamespacedKey(Ccr.getPlugin(Ccr.class), "winner");
            PersistentDataContainer container = player.getPersistentDataContainer();
            container.remove(key);
        }
        TextComponent message = Component.text("El evento ha sido reiniciado");
        Bukkit.broadcast(message);
        return true;
    }
}
