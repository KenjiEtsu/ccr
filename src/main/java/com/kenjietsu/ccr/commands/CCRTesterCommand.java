package com.kenjietsu.ccr.commands;

import com.kenjietsu.ccr.eventManager.EuroCristalesEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static com.kenjietsu.ccr.utils.MainEventUtils.endSacrifice;

public class CCRTesterCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args[0].equals("a")) {
            endSacrifice();
            return true;
        }
        EuroCristalesEvent euroCristalesEvent = EuroCristalesEvent.getEuroCristalesEvent();
        euroCristalesEvent.startEvent();
        return true;
    }
}
