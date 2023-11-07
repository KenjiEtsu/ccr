package com.kenjietsu.ccr.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CCSpeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.isOp()) {
            return false;
        }
        if (args.length == 0) {
            sender.sendMessage("§c/ccspeed <fly/ground> <speed>");
            return false;
        }
        if (args.length > 2) {
            sender.sendMessage("§c/ccspeed <fly/ground> <speed>");
            return false;
        }
        if (sender instanceof Player player) {

            if (args.length == 1) {
                if (player.isFlying()) {
                    player.setFlySpeed(Float.parseFloat(args[0])/10);
                } else {
                    player.setWalkSpeed(Float.parseFloat(args[0])/5);
                }
            }
            if (args[0].equalsIgnoreCase("fly")) {
                player.setFlySpeed(Float.parseFloat(args[1])/10);
            } else if (args[0].equalsIgnoreCase("ground")) {
                player.setWalkSpeed(Float.parseFloat(args[1])/5);
            } else {
                sender.sendMessage("§c/ccspeed <fly/ground> <speed>");
                return false;
            }
            return true;
        }
        return false;
    }
}
