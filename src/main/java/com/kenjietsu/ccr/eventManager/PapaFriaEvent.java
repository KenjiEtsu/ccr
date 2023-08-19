package com.kenjietsu.ccr.eventManager;

import com.kenjietsu.ccr.eventManager.utils.Timers;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Random;

import static com.kenjietsu.ccr.utils.MVCoreUtils.getMVWorld;


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

        MultiverseWorld world = getMVWorld("Snow_Spawn");
        List<Player> onlinePlayers =  world.getCBWorld().getPlayers();

        Random rnd = new Random();
        int j = rnd.nextInt(onlinePlayers.size());
        setPotater((Player) onlinePlayers.toArray()[j]);

        this.timer = new Timers(5, 0, 1);
    }



    public static ItemStack getPapaFriaItem() {
        ItemStack item = new ItemStack(Material.POISONOUS_POTATO);
        ItemMeta meta = item.getItemMeta();

        TextComponent itemName = Component.text("a").decorate(TextDecoration.BOLD).decorate(TextDecoration.OBFUSCATED).color(net.kyori.adventure.text.format.TextColor.color(0x232EFF))
                .append(Component.text("Papa Fria").decorate(TextDecoration.BOLD).color(net.kyori.adventure.text.format.TextColor.color(0x6AADFF))).decorate(TextDecoration.ITALIC)
                .append(Component.text("a").decorate(TextDecoration.BOLD).decorate(TextDecoration.OBFUSCATED).color(net.kyori.adventure.text.format.TextColor.color(0x232EFF)));

        meta.displayName(itemName);
        meta.setCustomModelData(1);

        item.setItemMeta(meta);
        return item;
    }

    public Player getPotater() {
        return potater;
    }

    public void setPotater(Player potaters) {
        potaters.getInventory().addItem(getPapaFriaItem());
        potaters.addPotionEffect(new org.bukkit.potion.PotionEffect(PotionEffectType.SPEED, 100000, 0, false, false, false));
        potaters.addPotionEffect(new org.bukkit.potion.PotionEffect(PotionEffectType.GLOWING, 100000, 0, false, false, false));
        this.potater = potaters;
    }

    public void finishEvent() {
        instance = null;

    }

}
