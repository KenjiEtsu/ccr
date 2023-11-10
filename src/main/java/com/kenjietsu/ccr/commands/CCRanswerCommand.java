package com.kenjietsu.ccr.commands;

import com.kenjietsu.ccr.eventManager.EuroCristalesEvent;
import com.kenjietsu.ccr.eventManager.utils.EuroCristalesPlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CCRanswerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        EuroCristalesEvent euroCristalesEvent = EuroCristalesEvent.getEuroCristalesEvent();
        List<EuroCristalesPlayerData> players = euroCristalesEvent.getPlayers();
        for (EuroCristalesPlayerData player : players) {
            if (player.player.equals(commandSender)) {
                commandSender.sendMessage("Que haces intentando esto?");
                return true;
            }
        }
        if (strings.length != 1) {
            commandSender.sendMessage("Que haces intentando esto mientras el evento esta activo?");
            return true;
        }
        if (commandSender instanceof Player player) {
            euroCristalesEvent.answerQuestion((Player) commandSender, Integer.parseInt(strings[0]));
        }


        return true;
    }
}
