package com.kenjietsu.ccr;

import com.kenjietsu.ccr.commands.*;
import com.kenjietsu.ccr.commands.tabComplete.*;
import com.kenjietsu.ccr.items.ItemManager;
import com.kenjietsu.ccr.listeners.ListenersManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public final class Ccr extends JavaPlugin {

    @Override
    public void onEnable() {

        ItemManager.init();

        ListenersManager.init(this);

        registerCommand("ccr", new CCRCommand(), new CCRCompleter());
        registerCommand("ccrestart", new CCRestartCommand(), new CCrestartCompleter());
        registerCommand("ccaddnonplayer", new CCRAddNonPlayerCommand(), new CCRAddNonPlayerCompleter());
        registerCommand("CCRGallinita", new CCRGallinitaCommand(), new CCRGallinitaCompleter());
        registerCommand("CCAote", new CCRAoteCommand());
        registerCommand("CCTimer", new CCTimerCommand(), new CCTimerCompleter());
        registerCommand("CCTester", new CCRTesterCommand());
        registerCommand("CCRanswer", new CCRanswerCommand());
        registerCommand("CCSpeed", new CCSpeedCommand(), new CCSpeedCompleter());
        registerCommand("CCSacrificar", new CCSacrificarCommand(), new CCSacrificarCompleter());
    }

    private void registerCommand(String commandName, @NotNull CommandExecutor commandClass) {
        registerCommand(commandName, commandClass, null);
    }
    private void registerCommand(String commandName, @NotNull CommandExecutor commandClass, @Nullable TabCompleter tabCompleter) {
        getCommand(commandName).setExecutor(commandClass);
        if (tabCompleter != null) {
            getCommand(commandName).setTabCompleter(tabCompleter);
        }
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
