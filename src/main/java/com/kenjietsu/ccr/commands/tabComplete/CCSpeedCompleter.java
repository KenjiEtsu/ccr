package com.kenjietsu.ccr.commands.tabComplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CCSpeedCompleter implements TabCompleter {
    private static final String[] COMMANDS = {"fly", "ground", "0", "1", "2", "5"};
    private static final String[] COMMANDS2 = {"0", "1", "2", "5"};
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.isOp()) {
            return null;
        }
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], List.of(COMMANDS), completions);
            return completions;
        }
        if (args.length == 2 && (args[0].equalsIgnoreCase("ground") || args[0].equalsIgnoreCase("fly"))) {
            StringUtil.copyPartialMatches(args[1], List.of(COMMANDS2), completions);
            return completions;
        }
        return null;
    }
}
