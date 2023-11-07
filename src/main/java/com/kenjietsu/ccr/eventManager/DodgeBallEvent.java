package com.kenjietsu.ccr.eventManager;

import com.kenjietsu.ccr.Ccr;
import com.kenjietsu.ccr.eventManager.utils.TimerID;
import com.kenjietsu.ccr.eventManager.utils.Timers;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.kenjietsu.ccr.utils.MVCoreUtils.getMVWorld;

public class DodgeBallEvent {
    // -447 149 736 NEW LOCATION
    private static DodgeBallEvent instance;
    private List<Player> redPlayers;
    private List<Player> bluePlayers;
    private Timers timer;

    public static DodgeBallEvent getDodgeBallEvent() {
        if (instance == null) {
            instance = new DodgeBallEvent();
        }
        return instance;
    }
    public static boolean isDodgeEventOn() {
        return instance != null;
    }
    public void selectPlayers() {
        redPlayers = new ArrayList<>();
        bluePlayers = new ArrayList<>();
        int i;
        MultiverseWorld world = getMVWorld("voidd");
        List<Player> onlinePlayers =  world.getCBWorld().getPlayers();

        NamespacedKey key = new NamespacedKey(Ccr.getPlugin(Ccr.class), "balon");

        for (Player player : onlinePlayers) {
            PersistentDataContainer container = player.getPersistentDataContainer();
            if (container.has(key, PersistentDataType.STRING)) {
                onlinePlayers.remove(player);
            }
        }
        if (onlinePlayers.size() < 2) {
            Component message = Component.text("No hay suficientes jugadores");
            Bukkit.broadcast(message);
            return;
        }
        for (i= 0; i<1; i++) {
            Random rnd = new Random();
            int j = rnd.nextInt(onlinePlayers.size());
            redPlayers.add((Player) onlinePlayers.toArray()[j]);
            onlinePlayers.remove(j);
        }
        for (i= 0; i<1; i++) {
            Random rnd = new Random();
            int j = rnd.nextInt(onlinePlayers.size());
            bluePlayers.add((Player) onlinePlayers.toArray()[j]);
        }
        startPlayers();
    }
    private void startPlayers(){
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team redTeam = scoreboard.getTeam("redTeam");
        if (redTeam == null) {
            redTeam = scoreboard.registerNewTeam("redTeam");
            redTeam.setAllowFriendlyFire(false);
        }
        initPlayers(scoreboard, redTeam, redPlayers);
        Team blueTeam = scoreboard.getTeam("blueTeam");
        if (blueTeam == null) {
            blueTeam = scoreboard.registerNewTeam("blueTeam");
            blueTeam.setAllowFriendlyFire(false);

        }
        initPlayers(scoreboard, blueTeam, bluePlayers);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:voidd run kill " +
                "@e[type=minecraft:item,nbt={Item:{id:\"minecraft:tipped_arrow\"}}]");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:voidd positioned " +
                "-836.5 149.30 486.5 run summon minecraft:item ~ ~ ~ {Item:{id:tipped_arrow,Count:1},PickupDelay:20}");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:voidd positioned " +
                "-836.5 149.30 478.5 run summon minecraft:item ~ ~ ~ {Item:{id:tipped_arrow,Count:1},PickupDelay:20}");
    }

    private void initPlayers(Scoreboard scoreboard, Team team, List<Player> players) {
        for (Player player: players){
            Team currentTeam = scoreboard.getPlayerTeam(player);

            if (currentTeam != null) {
                currentTeam.removeEntry(player.getName());
            }

            team.addEntry(player.getName());
            ItemStack item = new ItemStack(Material.BOW);
            player.getInventory().addItem(item);
            player.updateInventory();
            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 6000,1 ,false ,false));
            if (team.getName().equals("redTeam")) {
                player.teleport(new Location(player.getWorld(), -836.5, 148.5, 486.5, 180, 0));
            } else {
                player.teleport(new Location(player.getWorld(), -836.5, 148.5, 478.5));
            }
        }
        this.timer = new Timers(5, 0, TimerID.BALON);

    }


    public List<Player> getRedPlayers() {
        return redPlayers;
    }
    public void deleteRedPlayers(Player player) {
        this.redPlayers.remove(player);
        checkPlayers();
    }
    public void deleteBluePlayers(Player player) {
        this.bluePlayers.remove(player);
        checkPlayers();
    }

    private void checkPlayers(){
        if (redPlayers.isEmpty()) {
            for (Player player : bluePlayers) {
                winnerFinish(player);
                Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
                Team blueTeam = scoreboard.getTeam("blueTeam");
                blueTeam.removeEntry(player.getName());
            }
        }
        if (bluePlayers.isEmpty()) {
            for (Player player : redPlayers) {
                winnerFinish(player);
                Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
                Team redTeam = scoreboard.getTeam("redTeam");
                redTeam.removeEntry(player.getName());
            }
        }
    }

    public void winnerFinish(Player player) {
        NamespacedKey key = new NamespacedKey(Ccr.getPlugin(Ccr.class), "balon");
        PersistentDataContainer container = player.getPersistentDataContainer();
        container.set(key, PersistentDataType.STRING, "balon");

        player.removePotionEffect(PotionEffectType.GLOWING);

        instance = null;

        timer.stopTimer();

        player.teleport(new Location(player.getWorld(), -837, 156, 487));
        player.getInventory().clear();


    }

    public List<Player> getBluePlayers() {
        return bluePlayers;
    }

    public void spawnArrow(int team){
        if (team == 0) {
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.scheduleSyncDelayedTask(Ccr.getPlugin(Ccr.class), () -> {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:voidd positioned " +
                        "-836.5 149.30 478.5 run summon minecraft:item ~ ~ ~ {Item:{id:tipped_arrow,Count:1},PickupDelay:20}");

            }, 200L);
        } else {
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.scheduleSyncDelayedTask(Ccr.getPlugin(Ccr.class), () -> {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:voidd positioned " +
                        "-836.5 149.30 486.5 run summon minecraft:item ~ ~ ~ {Item:{id:tipped_arrow,Count:1},PickupDelay:20}");

            }, 200L);
        }
    }
}
