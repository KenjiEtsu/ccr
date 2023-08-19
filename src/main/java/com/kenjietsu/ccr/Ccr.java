package com.kenjietsu.ccr;

import com.kenjietsu.ccr.commands.ccr.CCRAddNonPlayerCommand;
import com.kenjietsu.ccr.commands.ccr.CCRCommand;
import com.kenjietsu.ccr.commands.ccr.CCRestartCommand;
import com.kenjietsu.ccr.commands.ccr.tabComplete.CCRAddNonPlayerCompleter;
import com.kenjietsu.ccr.commands.ccr.tabComplete.CCRTabCompleter;
import com.kenjietsu.ccr.commands.ccr.tabComplete.CCrestartCompleter;
import com.kenjietsu.ccr.eventManager.utils.LocationList;
import com.kenjietsu.ccr.listeners.HitListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Ccr extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new HitListener(), this);
        getCommand("ccr").setExecutor(new CCRCommand());
        getCommand("ccr").setTabCompleter(new CCRTabCompleter());

        getCommand("ccrestart").setExecutor(new CCRestartCommand());
        getCommand("ccrestart").setTabCompleter(new CCrestartCompleter());

        getCommand("ccaddnonplayer").setExecutor(new CCRAddNonPlayerCommand());
        getCommand("ccaddnonplayer").setTabCompleter(new CCRAddNonPlayerCompleter());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
