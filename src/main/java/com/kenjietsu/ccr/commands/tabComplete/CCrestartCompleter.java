package com.kenjietsu.ccr.commands.tabComplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CCrestartCompleter implements TabCompleter {
    private static final String[] COMMANDS = {"balon", "spleef", "eventoFinal"};
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.isOp()) {
            return null;
        }
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], List.of(COMMANDS), completions);
            completions.sort(String.CASE_INSENSITIVE_ORDER);
            return completions;
        }
        return new ArrayList<>();
    }
}
