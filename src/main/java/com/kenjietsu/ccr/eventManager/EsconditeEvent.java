package com.kenjietsu.ccr.eventManager;

import com.kenjietsu.ccr.eventManager.utils.TimerID;
import com.kenjietsu.ccr.eventManager.utils.Timers;
import com.kenjietsu.ccr.items.ItemManager;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class EsconditeEvent {

    private static EsconditeEvent instance;
    private Player catcher;
    private Timers timer, timer2;


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
        this.timer = new Timers(0, 20, TimerID.ESCONDITE);  //3 Minutes
    }
    public void releaseCatcher() {
        ItemStack item = ItemManager.catcherItem;
        catcher.getInventory().addItem(item);
        for (Player player : catcher.getWorld().getPlayers()) {
            player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 0.5F);
        }

        this.timer2 = new Timers(5, 0, TimerID.ESCONDITE_P2);



    }
    public void tickCatcher() {
        catcher.addPotionEffect(PotionEffectType.GLOWING.createEffect(100, 1));
    }


    public void endEvent() {
        instance = null;
        this.catcher.getInventory().clear();
        this.catcher.setGameMode(GameMode.SPECTATOR);
        this.catcher = null;

    }

}
