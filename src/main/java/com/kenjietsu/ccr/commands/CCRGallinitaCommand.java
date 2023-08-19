package com.kenjietsu.ccr.commands;

import com.kenjietsu.ccr.eventManager.GallinitaEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CCRGallinitaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.isOp()) {
            return true;
        }
        if (args.length != 2) {
            sender.sendMessage("Faltan argumentos(o sobran)");
            return true;
        }
        if (args[0].equals("1")) {
            GallinitaEvent gallinitaEvent = GallinitaEvent.getGallinitaEvent();
            gallinitaEvent.gallinerItem(0, Bukkit.getPlayer(args[1]));
            return true;
        }
        if (args[0].equals("2")) {
            GallinitaEvent gallinitaEvent = GallinitaEvent.getGallinitaEvent();
            gallinitaEvent.gallinerItem(1, Bukkit.getPlayer(args[1]));
            return true;
        }
        if (args[0].equals("3")) {
            GallinitaEvent gallinitaEvent = GallinitaEvent.getGallinitaEvent();
            gallinitaEvent.gallinerItem(2, Bukkit.getPlayer(args[1]));
            return true;
        }
        else {
            sender.sendMessage("Argumento invalido");
        }
        return true;
    }
}
