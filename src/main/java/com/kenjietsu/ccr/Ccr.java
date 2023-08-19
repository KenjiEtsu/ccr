package com.kenjietsu.ccr;

import com.kenjietsu.ccr.commands.CCRAddNonPlayerCommand;
import com.kenjietsu.ccr.commands.CCRCommand;
import com.kenjietsu.ccr.commands.CCRGallinitaCommand;
import com.kenjietsu.ccr.commands.CCRestartCommand;
import com.kenjietsu.ccr.commands.tabComplete.CCRAddNonPlayerCompleter;
import com.kenjietsu.ccr.commands.tabComplete.CCRCompleter;
import com.kenjietsu.ccr.commands.tabComplete.CCRGallinitaCompleter;
import com.kenjietsu.ccr.commands.tabComplete.CCrestartCompleter;
import com.kenjietsu.ccr.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Ccr extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new DodgeBallListener(), this);
        getServer().getPluginManager().registerEvents(new EsconditeListener(), this);
        getServer().getPluginManager().registerEvents(new PapaListener(), this);
        getServer().getPluginManager().registerEvents(new SpleefListener(), this);
        getServer().getPluginManager().registerEvents(new TimerListener(), this);


        getCommand("ccr").setExecutor(new CCRCommand());
        getCommand("ccr").setTabCompleter(new CCRCompleter());

        getCommand("ccrestart").setExecutor(new CCRestartCommand());
        getCommand("ccrestart").setTabCompleter(new CCrestartCompleter());

        getCommand("ccaddnonplayer").setExecutor(new CCRAddNonPlayerCommand());
        getCommand("ccaddnonplayer").setTabCompleter(new CCRAddNonPlayerCompleter());

        getCommand("CCRGallinita").setExecutor(new CCRGallinitaCommand());
        getCommand("CCRGallinita").setTabCompleter(new CCRGallinitaCompleter());
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
