package com.kenjietsu.ccr.commands.tabComplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CCRGallinitaCompleter implements TabCompleter {
    private static final String[] COMMANDS = {"1", "2", "3"};
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
        if (args.length == 2) {
            return null;
        }
        return new ArrayList<>();
    }

}
