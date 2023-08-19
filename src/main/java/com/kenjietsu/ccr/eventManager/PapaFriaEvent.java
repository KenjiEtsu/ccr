package com.kenjietsu.ccr.eventManager;

import com.kenjietsu.ccr.eventManager.utils.Timers;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Random;


public class PapaFriaEvent {
    private static PapaFriaEvent instance;
    private Player potater;

    private Timers timer;

    public static PapaFriaEvent getPapaFriaEvent() {
        if (instance == null) {
            instance = new PapaFriaEvent();
        }
        return instance;
    }

    public void startEvent() {
        MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        MVWorldManager worldManager = core.getMVWorldManager();
        MultiverseWorld world = worldManager.getMVWorld("Snow_Spawn");
        List<Player> onlinePlayers =  world.getCBWorld().getPlayers();

        Random rnd = new Random();
        int j = rnd.nextInt(onlinePlayers.size());
        potater = (Player) onlinePlayers.toArray()[j];

        potater.getInventory().addItem(getPapaFriaItem());

        this.timer = new Timers(5, 0, 1);
    }



    public static ItemStack getPapaFriaItem() {
        ItemStack item = new ItemStack(Material.POISONOUS_POTATO);
        ItemMeta meta = item.getItemMeta();

        TextComponent itemName = Component.text("a").decorate(TextDecoration.BOLD).decorate(TextDecoration.OBFUSCATED).color(net.kyori.adventure.text.format.TextColor.color(0x232EFF))
                .append(Component.text("Papa Fria").decorate(TextDecoration.BOLD).color(net.kyori.adventure.text.format.TextColor.color(0x6AADFF)))
                .append(Component.text("a").decorate(TextDecoration.BOLD).decorate(TextDecoration.OBFUSCATED).color(net.kyori.adventure.text.format.TextColor.color(0x232EFF)));

        meta.displayName(itemName);
        item.setItemMeta(meta);
        return item;
    }

    public Player getPotater() {
        return potater;
    }

    private void finishEvent() {
        instance = null;
    }

}
