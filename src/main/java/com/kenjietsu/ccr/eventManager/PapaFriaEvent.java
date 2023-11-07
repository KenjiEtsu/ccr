package com.kenjietsu.ccr.eventManager;

import com.kenjietsu.ccr.eventManager.utils.TimerID;
import com.kenjietsu.ccr.eventManager.utils.Timers;
import com.kenjietsu.ccr.items.ItemManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Random;

import static com.kenjietsu.ccr.utils.MVCoreUtils.getMVWorld;


public class PapaFriaEvent {
    private static PapaFriaEvent instance;
    private Player potater;
    private int TaskID;

    private Timers timer;

    public static PapaFriaEvent getPapaFriaEvent() {
        if (instance == null) {
            instance = new PapaFriaEvent();
        }
        return instance;
    }

    public void startEvent() {

        MultiverseWorld world = getMVWorld("Snow_Spawn");
        List<Player> onlinePlayers =  world.getCBWorld().getPlayers();
        onlinePlayers.removeIf(Player::isOp);

        Random rnd = new Random();
        int j = rnd.nextInt(onlinePlayers.size());
        setPotater((Player) onlinePlayers.toArray()[j]);

        // Bukkit.getScheduler().runTaskTimer(Ccr.getPlugin(Ccr.class), new PapaFriaMusicTask(), 0L, 29L);
        this.timer = new Timers(5, 0, TimerID.POTATO);
    }

    public Player getPotater() {
        return potater;
    }

    public void setPotater(Player potaters) {
        potaters.getInventory().addItem(ItemManager.papaFriaItem);
        potaters.addPotionEffect(new org.bukkit.potion.PotionEffect(PotionEffectType.SPEED, 100000, 0, false, false, false));
        potaters.addPotionEffect(new org.bukkit.potion.PotionEffect(PotionEffectType.GLOWING, 100000, 0, false, false, false));
        this.potater = potaters;

    }

    public void finishEvent() {
        instance = null;

    }

}
