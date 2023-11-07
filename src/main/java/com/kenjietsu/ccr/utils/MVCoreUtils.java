package com.kenjietsu.ccr.utils;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import org.bukkit.Bukkit;

public class MVCoreUtils {
    public static MultiverseWorld getMVWorld(String worldName) {
        MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        MVWorldManager worldManager = core.getMVWorldManager();
        return worldManager.getMVWorld(worldName);
    }
    public static boolean hasDecimalPart(double floatNum) {
        return floatNum != (int) floatNum;
    }
}
