package com.kenjietsu.ccr.eventManager;

import com.kenjietsu.ccr.eventManager.utils.Timers;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static com.kenjietsu.ccr.eventManager.utils.LocationList.getLocations;

public class EventoFinalEvent {
    private static EventoFinalEvent instance;
    private List<Player> players;
    private Timers timer;

    public static EventoFinalEvent getInstance() {
        if (instance == null) {
            instance = new EventoFinalEvent();
        }
        return instance;
    }

    public void startEvent() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.isOp()) {
                players.add(player);
            }
        }
        ItemStack itemStack = new ItemStack(Material.GOLD_INGOT);
        ItemMeta meta = itemStack.getItemMeta();

        Component name = Component.text("Alma de Cáliz").color(TextColor.color(0xFF001D)).decorate(TextDecoration.BOLD);

        meta.displayName(name);
        itemStack.setItemMeta(meta);

        MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        MVWorldManager worldManager = core.getMVWorldManager();
        MultiverseWorld world = worldManager.getMVWorld("esplosion");

        for (Entity entity : world.getCBWorld().getEntities()) {
            if (entity instanceof Item) {
                entity.remove();
            }
        }

        for (Location location : getLocations()) {



            Item dItem = world.getCBWorld().dropItem(location, itemStack);
            dItem.setPickupDelay(0);
            dItem.setCanMobPickup(false);
            dItem.setUnlimitedLifetime(true);
            dItem.setInvulnerable(true);
            dItem.setGlowing(true);
        }

        timer = new Timers(10, 0, 6);

    }
    public List<Player> getPlayers() {
        return players;
    }

}
