package com.kenjietsu.ccr.commands;

import com.kenjietsu.ccr.Ccr;
import com.kenjietsu.ccr.eventManager.utils.Timers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

import static com.kenjietsu.ccr.commands.CCRestartCommand.keyCancel;

public class CCTimerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage("No tienes permisos para usar este comando");
            return true;
        }
        if (args.length == 4 ) {
            if (args[0].equals("reset")) {
                BukkitScheduler scheduler = sender.getServer().getScheduler();
                scheduler.cancelTasks(Ccr.getPlugin(Ccr.class));
            }
            if (args[0].equals("start")) {


                if (args[3].equals("true")) {
                    Timers timer = new Timers(Integer.parseInt(args[1]), Integer.parseInt(args[2]), 3);
                }
                else if (args[3].equals("false")) {
                    Timers timer = new Timers(Integer.parseInt(args[1]), Integer.parseInt(args[2]), 2);
                }
            }

        }
        if (args[0].equals("unfreeze")) {
            keyCancel("freeze");
        }
        return true;

    }
}
