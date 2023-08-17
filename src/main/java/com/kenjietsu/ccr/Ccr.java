package com.kenjietsu.ccr;

import com.kenjietsu.ccr.commands.ccr.CCRCommand;
import com.kenjietsu.ccr.commands.ccr.CCRestartCommand;
import com.kenjietsu.ccr.listeners.HitListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Ccr extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new HitListener(), this);
        getCommand("ccr").setExecutor(new CCRCommand());
        getCommand("ccrestart").setExecutor(new CCRestartCommand());
        getCommand("ccaddnonplayer").setExecutor(new CCRestartCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
