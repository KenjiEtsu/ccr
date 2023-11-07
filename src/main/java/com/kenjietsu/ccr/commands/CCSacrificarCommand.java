package com.kenjietsu.ccr.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static com.kenjietsu.ccr.utils.MainEventUtils.sacrificar;

public class CCSacrificarCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage("No tienes permisos para ejecutar este comando");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage("Debes especificar un jugador");
            return true;
        }
        if (args.length > 1) {
            sender.sendMessage("Demasiados argumentos");
            return true;
        }
        try {
            sacrificar(Bukkit.getPlayer(args[0]));
        } catch (NullPointerException e) {
            sender.sendMessage("El jugador especificado no esta conectado o no existe");
        }
        return true;

    }
}
