package com.kenjietsu.ccr.commands.tabComplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CCTimerCompleter implements TabCompleter {
    private static final String[] COMMANDS = {"reset", "start", "unfreeze"};
    private static final String[] COMMANDS1 = {"5", "10", "20"};
    private static final String[] COMMANDS2 = {"0"};
    private static final String[] COMMANDS3 = {"true", "false"};
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.isOp()) {
            return new ArrayList<>();
        }
        if (args.length == 1) {
            List<String> completions = new ArrayList<>();
            StringUtil.copyPartialMatches(args[0], List.of(COMMANDS), completions);
            return completions;
        }
        if (args[0].equals("start")) {
            if (args.length == 2) {
                List<String> completions = new ArrayList<>();
                StringUtil.copyPartialMatches(args[1], List.of(COMMANDS1), completions);
                return completions;
            }
            if (args.length == 3) {
                List<String> completions = new ArrayList<>();
                StringUtil.copyPartialMatches(args[2], List.of(COMMANDS2), completions);
                return completions;
            }
            if (args.length == 4) {
                List<String> completions = new ArrayList<>();
                StringUtil.copyPartialMatches(args[3], List.of(COMMANDS3), completions);
                return completions;
            }
        }
        return new ArrayList<>();
    }
}
