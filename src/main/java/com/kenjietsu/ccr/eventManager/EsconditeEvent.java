package com.kenjietsu.ccr.eventManager;

import com.kenjietsu.ccr.eventManager.utils.Timers;
import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

public class EsconditeEvent {

    private static EsconditeEvent instance;
    private Player catcher;
    private Timers timer;


    public static EsconditeEvent getEsconditeEvent() {
        if (instance == null) {
            instance = new EsconditeEvent();
        }
        return instance;
    }
    public void startEvent(Player player) {
        instance.catcher = player;

        player.addPotionEffect(PotionEffectType.BLINDNESS.createEffect(3600, 1));
        player.setGameMode(GameMode.ADVENTURE);
        this.timer = new Timers(0, 20, 0);  //3 Minutes
    }
    public void releaseCatcher() {
        ItemStack item = getCatcherItem();
        catcher.getInventory().addItem(item);

        timer.restartTimer(5, 0, 9);
    }
    public void tickCatcher() {
        catcher.addPotionEffect(PotionEffectType.GLOWING.createEffect(100, 1));
    }

    public static ItemStack getCatcherItem() {
        ItemStack item = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta meta = item.getItemMeta();
        Component name = Component.text("Aniquilador de Entidades").color(net.kyori.adventure.text.format.TextColor.color(0x42FFE4));

        meta.displayName(name);
        meta.setCustomModelData(1);

        item.setItemMeta(meta);
        return item;
    }
    public void endEvent() {
        instance = null;
        this.catcher.getInventory().clear();
        this.catcher.setGameMode(GameMode.SPECTATOR);
        this.catcher = null;

    }

}
