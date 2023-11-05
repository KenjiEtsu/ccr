package com.kenjietsu.ccr.commands;

import com.kenjietsu.ccr.eventManager.EuroCristalesEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CCRTester implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //if (!sender.isOp()) {
        //            sender.sendMessage("No tienes permisos para usar este comando");
        //            return true;
        //        }
        //        if (!(sender instanceof Player player)) {
        //            sender.sendMessage("No puedes usar este comando desde la consola");
        //            return true;
        //        }
        //        player.sendMessage(Lists.getComponent( "test", new ArrayList<>(List.of("123", "456", "789", "1234"))));
        EuroCristalesEvent euroCristalesEvent = EuroCristalesEvent.getEuroCristalesEvent();
        euroCristalesEvent.startEvent();
        return true;
    }
}
