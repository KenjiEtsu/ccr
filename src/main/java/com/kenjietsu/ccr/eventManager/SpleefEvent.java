package com.kenjietsu.ccr.eventManager;

import com.kenjietsu.ccr.Ccr;
import com.kenjietsu.ccr.eventManager.utils.TimerID;
import com.kenjietsu.ccr.eventManager.utils.Timers;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Random;
import java.util.Set;

import static com.kenjietsu.ccr.utils.MVCoreUtils.getMVWorld;

public class SpleefEvent {
    private Player player1;
    private Player player2;
    private static SpleefEvent instance;

    private Timers timer;

    private BossBar bossBar;

    public static SpleefEvent getSpleefEvent() {
        if (instance == null) {
            instance = new SpleefEvent();
        }
        return instance;
    }

    public static void customSpleefEvent(Player player1, Player player2) {
        if (instance == null) {
            instance = new SpleefEvent();
        }
        instance.player1 = player1;
        instance.player2 = player2;

        instance.startPlayers();
    }
    public static boolean isSpleefEventOn() {
        return instance != null;
    }

    public void selectPlayers() {

        MultiverseWorld world = getMVWorld("voidd");
        List<Player> onlinePlayers =  world.getCBWorld().getPlayers();

        NamespacedKey key = new NamespacedKey(Ccr.getPlugin(Ccr.class), "spleef");

        onlinePlayers.removeIf(player -> player.getPersistentDataContainer().has(key, PersistentDataType.STRING));

        if (onlinePlayers.size() < 2) {
            Component message = Component.text("No hay suficientes jugadores");
            Bukkit.broadcast(message);
            return;
        }

        Random rnd = new Random();
        int j = rnd.nextInt(onlinePlayers.size());
        player1 = (Player) onlinePlayers.toArray()[j];
        onlinePlayers.remove(j);

        j = rnd.nextInt(onlinePlayers.size());
        player2 = (Player) onlinePlayers.toArray()[j];
        onlinePlayers.remove(j);

        startPlayers();
    }

    private void startPlayers() {
        ItemStack item = new ItemStack(Material.DIAMOND_SHOVEL, 1);
        item.addEnchantment(Enchantment.DIG_SPEED, 5);
        ItemMeta meta = item.getItemMeta();

        Component component = Component.text("Spleef").color(TextColor.color(0x00bfff));

        meta.displayName(component);
        meta.setUnbreakable(true);
        meta.setDestroyableKeys(Set.of(NamespacedKey.fromString(Material.SNOW_BLOCK.getKey().toString().toLowerCase().replace(" ", "_"))));

        item.setItemMeta(meta);

        restartArena();

        player1.teleport(new Location(player1.getWorld(), 176.5, 256.3, -505.5));
        player1.getInventory().clear();
        player1.getInventory().addItem(item);
        player1.getInventory().setHeldItemSlot(0);
        player1.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 6000,1 ,false ,false));

        player2.teleport(new Location(player2.getWorld(), 176.5, 256.3, -481.5, 180, 0));
        player2.getInventory().clear();
        player2.getInventory().addItem(item);
        player2.getInventory().setHeldItemSlot(0);
        player2.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 6000,1 ,false ,false));

        this.timer = new Timers(3, 0, TimerID.SPLEEF);

        String message = player1.getName() + " vs " + player2.getName();
        this.bossBar = Bukkit.createBossBar(message, org.bukkit.boss.BarColor.BLUE, org.bukkit.boss.BarStyle.SOLID);

        for (Player player : Bukkit.getOnlinePlayers()) {
            bossBar.addPlayer(player);
        }
        bossBar.setVisible(true);
        bossBar.setProgress(1);


    }


    private void restartArena() {
        MultiverseWorld world = getMVWorld("voidd");
        int i, j;
        for (i = 0; i<29; i++) {
            for (j = 0; j<29; j++) {
                Location location = new Location(world.getCBWorld(), 162, 255, -481);
                location.add(j, 0, -i);
                if (location.getBlock().getBlockData().getMaterial().equals(Material.AIR)) {
                    location.getBlock().setType(Material.SNOW_BLOCK);
                }
            }
        }
    }
    public void killPlayer(Player player) throws Exception{
        if (player1 == player) {
            player2.getPersistentDataContainer().set(new NamespacedKey(Ccr.getPlugin(Ccr.class), "spleef"), PersistentDataType.STRING, "spleef");
            player1.getPersistentDataContainer().set(new NamespacedKey(Ccr.getPlugin(Ccr.class), "spleef"), PersistentDataType.STRING, "spleef");
            player2.teleport(new Location(player2.getWorld(), 166.5, 256.3, -506.5));
            player2.removePotionEffect(PotionEffectType.GLOWING);
            player2.getInventory().clear();
            finishEvent();

        } else if (player2 == player) {
            player1.getPersistentDataContainer().set(new NamespacedKey(Ccr.getPlugin(Ccr.class), "spleef"), PersistentDataType.STRING, "spleef");
            player2.getPersistentDataContainer().set(new NamespacedKey(Ccr.getPlugin(Ccr.class), "spleef"), PersistentDataType.STRING, "spleef");
            player1.teleport(new Location(player1.getWorld(), 166.5, 256.3, -506.5));
            player1.removePotionEffect(PotionEffectType.GLOWING);
            player1.getInventory().clear();
            finishEvent();
        }
        throw new Exception("No se encontro el jugador");


    }

    private void finishEvent() {

        instance = null;

        bossBar.removeAll();
        bossBar.setVisible(false);
        bossBar = null;

        timer.stopTimer();
    }


}
