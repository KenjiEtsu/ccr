package com.kenjietsu.ccr.eventManager.utils;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationList {

    public static List<Location> getLocations() {
        List<Location> locations = new ArrayList<>();
        MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        MVWorldManager worldManager = core.getMVWorldManager();
        MultiverseWorld world = worldManager.getMVWorld("esplosion");
        locations.add(new Location(world.getCBWorld(), -250.2, 28.1, -704.5));
        locations.add(new Location(world.getCBWorld(), -249.3, 10, -679));
        locations.add(new Location(world.getCBWorld(), -268.66, 3, -639.1));
        locations.add(new Location(world.getCBWorld(), -215.7, 9, -633.34));
        locations.add(new Location(world.getCBWorld(), -235.7, 7, -756.0));
        locations.add(new Location(world.getCBWorld(), -236.6, 27, -725.4));
        locations.add(new Location(world.getCBWorld(), -212.3, 9, -705.44));
        locations.add(new Location(world.getCBWorld(), -211.66, 29, -680.59));
        locations.add(new Location(world.getCBWorld(), -196.7, 37, -868.16));
        locations.add(new Location(world.getCBWorld(), -197.8, 12, -782.781));
        locations.add(new Location(world.getCBWorld(), -202.5, 24, -747.73));
        locations.add(new Location(world.getCBWorld(), -293.5, 34, -766.5));
        locations.add(new Location(world.getCBWorld(), -177.8, 14, -857.57));
        locations.add(new Location(world.getCBWorld(), -179.5, 39, -811.4));
        locations.add(new Location(world.getCBWorld(), -153.4, 3, -639.26));
        locations.add(new Location(world.getCBWorld(), -159.6, 28.6, -639.52));
        locations.add(new Location(world.getCBWorld(), -169.8, 29, -908.54));
        locations.add(new Location(world.getCBWorld(), -172.5, 17, -895.17));
        locations.add(new Location(world.getCBWorld(), -166.3, 50, -820.66));
        locations.add(new Location(world.getCBWorld(), -170.5, 54, -784.36));
        locations.add(new Location(world.getCBWorld(), -133.3, 39, -639.65));
        locations.add(new Location(world.getCBWorld(), -149.6, 8, -753.48));
        locations.add(new Location(world.getCBWorld(), -157.45, 4, -726.84));
        locations.add(new Location(world.getCBWorld(), -144.47, 6.1, -692.5));
        locations.add(new Location(world.getCBWorld(), -145.7, 60, -694.7));
        locations.add(new Location(world.getCBWorld(), -147.87, 3, -670.33));
        locations.add(new Location(world.getCBWorld(), -119.68, 10, -630.5));
        locations.add(new Location(world.getCBWorld(), -125.85, 94, -639.45));
        locations.add(new Location(world.getCBWorld(), -136.5, 12, -834.52));
        locations.add(new Location(world.getCBWorld(), -138.1, 25, -836.5));
        locations.add(new Location(world.getCBWorld(), -131.4, 3, -666.14));
        locations.add(new Location(world.getCBWorld(), -141.3, 32.6, -667.6));
        locations.add(new Location(world.getCBWorld(), -104.63, 4, -639.8));
        locations.add(new Location(world.getCBWorld(), -104.7, 64.1, -624.7));
        locations.add(new Location(world.getCBWorld(), -132.6, 25.1, -680.46));
        locations.add(new Location(world.getCBWorld(), -131.1, 45, -686.1));
        locations.add(new Location(world.getCBWorld(), -141.4, 60, -673.1));
        locations.add(new Location(world.getCBWorld(), -122.3, 41, -935.87));
        locations.add(new Location(world.getCBWorld(), -123.66, 39, -685.31));
        locations.add(new Location(world.getCBWorld(), -118.6, 119.5, -672.35));
        locations.add(new Location(world.getCBWorld(), -89.2, 10, -629.5));
        locations.add(new Location(world.getCBWorld(), -82.5, 94, -639.5));
        locations.add(new Location(world.getCBWorld(), -104.5, 73, -942.6));
        locations.add(new Location(world.getCBWorld(), -104.3, 72, -927.2));
        locations.add(new Location(world.getCBWorld(), -99.4, 43, -837.8));
        locations.add(new Location(world.getCBWorld(), -104.4, 8, -792.7));
        locations.add(new Location(world.getCBWorld(), -104.5, 0.5, -761.4));
        locations.add(new Location(world.getCBWorld(), -100.5, 14, -769.5));
        locations.add(new Location(world.getCBWorld(), -108.5, 14, -769.5));
        locations.add(new Location(world.getCBWorld(), -74.5, 39.1, -639.77));
        locations.add(new Location(world.getCBWorld(), -87.7, 42, -936.9));
        locations.add(new Location(world.getCBWorld(), -85.6, 39, -685.6));
        locations.add(new Location(world.getCBWorld(), -91, 75, -679));
        locations.add(new Location(world.getCBWorld(), -55.3, 3, -639.7));
        locations.add(new Location(world.getCBWorld(), -55.5, 64.1, -624.5));
        locations.add(new Location(world.getCBWorld(), -49.5, 28.5, -639.6));
        locations.add(new Location(world.getCBWorld(), -77.5, 30, -850.6));
        locations.add(new Location(world.getCBWorld(), -64.5, 6.1, -692.6));
        locations.add(new Location(world.getCBWorld(), -67.5, 59, -694.7));
        locations.add(new Location(world.getCBWorld(), -77.3, 3, -670.7));
        locations.add(new Location(world.getCBWorld(), -67.6, 34.5, -667.14));
        locations.add(new Location(world.getCBWorld(), -76.3, 25.1, -681.3));
        locations.add(new Location(world.getCBWorld(), -77.3, 45, -686.7));
        locations.add(new Location(world.getCBWorld(), -67.5, 60, -672.8));
        locations.add(new Location(world.getCBWorld(), -51.5, 17, -817.6));
        locations.add(new Location(world.getCBWorld(), -54.7, 87, -933.3));
        locations.add(new Location(world.getCBWorld(), -54.5, 119.5, -672.5));
        locations.add(new Location(world.getCBWorld(), -59.2, 8, -753.3));
        locations.add(new Location(world.getCBWorld(), -56.5, 34, -774.5));
        locations.add(new Location(world.getCBWorld(), -39.5, 29, -908.8));
        locations.add(new Location(world.getCBWorld(), -28.5, 10, -854.5));
        locations.add(new Location(world.getCBWorld(), -26.5, 6, -762.1));
        locations.add(new Location(world.getCBWorld(), -24.3, 9, -687.4));
        locations.add(new Location(world.getCBWorld(), -4.1, 9, -828.5));
        locations.add(new Location(world.getCBWorld(), -5.3, 22, -730.3));
        locations.add(new Location(world.getCBWorld(), 10.2, 12, -750.3));
        locations.add(new Location(world.getCBWorld(), 22.5, 3, -775.6));
        locations.add(new Location(world.getCBWorld(), 34.8, 38, -682.3));
        locations.add(new Location(world.getCBWorld(), 37.7, 38, -679.3));
        locations.add(new Location(world.getCBWorld(), 37.7, 41, -683.5));
        locations.add(new Location(world.getCBWorld(), -154.7, 92, -965.6));
        locations.add(new Location(world.getCBWorld(), -140.6, 75, -640.5));
        locations.add(new Location(world.getCBWorld(), -136.5, 73, -650.5));
        locations.add(new Location(world.getCBWorld(), -128.3, 75, -652.5));
        locations.add(new Location(world.getCBWorld(), -104.5, 64.1, -654.3));
        locations.add(new Location(world.getCBWorld(), -68.7, 79, -655.4));
        locations.add(new Location(world.getCBWorld(), -71.6, 119, -653.5));
        locations.add(new Location(world.getCBWorld(), -54.3, 92, -965.5));
        locations.add(new Location(world.getCBWorld(), 19.3, 39, -655.5));
        locations.add(new Location(world.getCBWorld(), 23.2, 33, -619.5));
        locations.add(new Location(world.getCBWorld(), 43.8, 31, -602.6));
        locations.add(new Location(world.getCBWorld(), -30.6, 23, -620.7));
        locations.add(new Location(world.getCBWorld(), -9, 10, -604.1));
        locations.add(new Location(world.getCBWorld(), -29.99, 54, -592.5));
        locations.add(new Location(world.getCBWorld(), 84, 27, -590.3));
        locations.add(new Location(world.getCBWorld(), -58.3, 26, -596.3));
        locations.add(new Location(world.getCBWorld(), -75.5, 45, -599.6));
        locations.add(new Location(world.getCBWorld(), -56.7, 3, -586.4));
        locations.add(new Location(world.getCBWorld(), -89.56, 25, -596.1));
        locations.add(new Location(world.getCBWorld(), -91.9, 68, -599));
        locations.add(new Location(world.getCBWorld(), -90.4, 119.5, -606.4));
        locations.add(new Location(world.getCBWorld(), -64.4, 6.1, -586.5));
        locations.add(new Location(world.getCBWorld(), -53.5, 25, -575.5));
        locations.add(new Location(world.getCBWorld(), 47.7, 21, -499.25));
        locations.add(new Location(world.getCBWorld(), -53.3, 8, -547.3));
        locations.add(new Location(world.getCBWorld(), 73.8, 18, -518.5));
        locations.add(new Location(world.getCBWorld(), 100, 8, -554.1));
        locations.add(new Location(world.getCBWorld(), -120.4, 25, -596.2));
        locations.add(new Location(world.getCBWorld(), -116.5, 78, -598.8));
        locations.add(new Location(world.getCBWorld(), -123.7, 78, -601.4));
        locations.add(new Location(world.getCBWorld(), -68.6, 51, -557));
        locations.add(new Location(world.getCBWorld(), -133.3, 45, -599.5));
        locations.add(new Location(world.getCBWorld(), -123.4, 10.1, -586.84));
        locations.add(new Location(world.getCBWorld(), -103.7, 59, -565.5));
        locations.add(new Location(world.getCBWorld(), -103.7, 119.5, -606.5));
        locations.add(new Location(world.getCBWorld(), -73.4, 21, -519.7));
        locations.add(new Location(world.getCBWorld(), -123.7, 5, -540.4));
        locations.add(new Location(world.getCBWorld(), -126.2, 49, -534.7));
        locations.add(new Location(world.getCBWorld(), -69.7, 31, -461));
        locations.add(new Location(world.getCBWorld(), -136.2, 9, -472.7));
        locations.add(new Location(world.getCBWorld(), -145.8, 3, -609.4));
        locations.add(new Location(world.getCBWorld(), -151.7, 26, -597.3));
        locations.add(new Location(world.getCBWorld(), -144.5, 6.1, -586.5));
        locations.add(new Location(world.getCBWorld(), -152.5, 3, -585.5));
        locations.add(new Location(world.getCBWorld(), -171.5, 147, -582.4));
        locations.add(new Location(world.getCBWorld(), -155.5, 25, -575.5));
        locations.add(new Location(world.getCBWorld(), -147.4, 115, -555.4));
        locations.add(new Location(world.getCBWorld(), -178.7, 23, -620.7));
        locations.add(new Location(world.getCBWorld(), -187.6, 61, -600.1));
        locations.add(new Location(world.getCBWorld(), -181.3, 74, -583.8));
        locations.add(new Location(world.getCBWorld(), -204.2, 12, -595.4));
        locations.add(new Location(world.getCBWorld(), -230.9, 51, -607.7));
        locations.add(new Location(world.getCBWorld(), -185.1, 69.8, -525.3));
        locations.add(new Location(world.getCBWorld(), -172.5, 74.9, -498.4));
        locations.add(new Location(world.getCBWorld(), -347.7, 75, -553.3));
        locations.add(new Location(world.getCBWorld(), -278.7, 32, -579.3));
        locations.add(new Location(world.getCBWorld(), -245.5, 30, -559.3));
        locations.add(new Location(world.getCBWorld(), -297.1, 16, -605.3));
        locations.add(new Location(world.getCBWorld(), -218.1, 104, -497.5));
        locations.add(new Location(world.getCBWorld(), -316.8, 4, -623.6));
        locations.add(new Location(world.getCBWorld(), -309.7, 9, -576.7));
        locations.add(new Location(world.getCBWorld(), -250.5, 28, -518.5));
        locations.add(new Location(world.getCBWorld(), -189.5, 31, -453.4));
        locations.add(new Location(world.getCBWorld(), -151.3, 4, -420.4));
        locations.add(new Location(world.getCBWorld(), -311.3, 7, -575.3));
        locations.add(new Location(world.getCBWorld(), -302.3, 5, -648.3));
        locations.add(new Location(world.getCBWorld(), -305.3, 9, -545.3));
        locations.add(new Location(world.getCBWorld(), -297.9, 20, -530.9));
        locations.add(new Location(world.getCBWorld(), -251.7, 44, -474.1));
        locations.add(new Location(world.getCBWorld(), -257.1, 37, -450.5));
        locations.add(new Location(world.getCBWorld(), -252.5, 37, -445.5));
        locations.add(new Location(world.getCBWorld(), -257.4, 37, -440.3));
        locations.add(new Location(world.getCBWorld(), -262.5, 37, -445.5));
        locations.add(new Location(world.getCBWorld(), -304, 7, -472.2));
        locations.add(new Location(world.getCBWorld(), -304.5, 7, -477.5));
        locations.add(new Location(world.getCBWorld(), -293.6, 3, -399.5));
        locations.add(new Location(world.getCBWorld(), -293.5, 3, -404.5));
        return locations;
    }

    public static Location getCenterLocation() {
        MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        MVWorldManager worldManager = core.getMVWorldManager();
        MultiverseWorld world = worldManager.getMVWorld("esplosion");
        return new Location(world.getCBWorld(), -104.5, 51, -639.5);
    }
}
