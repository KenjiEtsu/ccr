package com.kenjietsu.ccr.listeners;

import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;

public class ListenersManager {
    public static void init(Plugin plugin) {
        getServer().getPluginManager().registerEvents(new DodgeBallListener(), plugin);
        getServer().getPluginManager().registerEvents(new EsconditeListener(), plugin);
        getServer().getPluginManager().registerEvents(new PapaListener(), plugin);
        getServer().getPluginManager().registerEvents(new SpleefListener(), plugin);
        getServer().getPluginManager().registerEvents(new TimerListener(), plugin);
        getServer().getPluginManager().registerEvents(new ItemListener(), plugin);
        getServer().getPluginManager().registerEvents(new FreezeEvent(), plugin);
        getServer().getPluginManager().registerEvents(new NoBatListener(), plugin);
        getServer().getPluginManager().registerEvents(new EuroCristalesListener(), plugin);
    }
}
