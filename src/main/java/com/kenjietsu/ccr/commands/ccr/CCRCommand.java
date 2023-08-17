package com.kenjietsu.ccr.commands.ccr;

import com.kenjietsu.ccr.eventManager.DodgeBallEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CCRCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            DodgeBallEvent dodgeBallEvent = DodgeBallEvent.getDodgeBallEvent();
            dodgeBallEvent.selectPlayers();

        }
        return true;
    }
}
