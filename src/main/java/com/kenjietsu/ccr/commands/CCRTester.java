package com.kenjietsu.ccr.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.kenjietsu.ccr.eventManager.EuroCristalesEvent.refillCristals;

public class CCRTester implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage("No tienes permisos para usar este comando");
            return true;
        }
        if (!(sender instanceof Player player)) {
            sender.sendMessage("No puedes usar este comando desde la consola");
            return true;
        }
        refillCristals();
        return true;
    }
}
