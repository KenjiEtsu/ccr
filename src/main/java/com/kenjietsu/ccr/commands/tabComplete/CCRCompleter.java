package com.kenjietsu.ccr.commands.tabComplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CCRCompleter implements TabCompleter {
    private static final String[] COMMANDS = {"balon", "spleef", "escondite", "papafria", "eventoFinal", "getSacrificador", "eurocristales", "gallinita"};
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.isOp()) {
            return null;
        }
        final List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], List.of(COMMANDS), completions);
            completions.sort(String.CASE_INSENSITIVE_ORDER);
            return completions;
        }
        if (args[0].equals("spleef")) {
            if (args.length <= 3) {
                return null;
            }
        }
        if (args[0].equals("escondite") || args[0].equals("papafria")) {
            if (args.length == 2) {
                return null;
            }
        }
            return new ArrayList<>();
        }
}
