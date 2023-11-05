package com.kenjietsu.ccr;

import com.kenjietsu.ccr.commands.*;
import com.kenjietsu.ccr.commands.tabComplete.*;
import com.kenjietsu.ccr.items.ItemManager;
import com.kenjietsu.ccr.listeners.ListenersManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Ccr extends JavaPlugin {

    @Override
    public void onEnable() {

        ItemManager.init();

        ListenersManager.init(this);

        getCommand("ccr").setExecutor(new CCRCommand());
        getCommand("ccr").setTabCompleter(new CCRCompleter());

        getCommand("ccrestart").setExecutor(new CCRestartCommand());
        getCommand("ccrestart").setTabCompleter(new CCrestartCompleter());

        getCommand("ccaddnonplayer").setExecutor(new CCRAddNonPlayerCommand());
        getCommand("ccaddnonplayer").setTabCompleter(new CCRAddNonPlayerCompleter());

        getCommand("CCRGallinita").setExecutor(new CCRGallinitaCommand());
        getCommand("CCRGallinita").setTabCompleter(new CCRGallinitaCompleter());

        getCommand("CCAote").setExecutor(new CCRAoteCommand());

        getCommand("CCTimer").setExecutor(new CCTimerCommand());
        getCommand("CCTimer").setTabCompleter(new CCTimerCompleter());

        getCommand("CCTester").setExecutor(new CCRTester());

        getCommand("CCRanswer").setExecutor(new CCRanswer());
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
