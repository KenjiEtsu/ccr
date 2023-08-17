package com.kenjietsu.ccr.eventManager;

import com.kenjietsu.ccr.Ccr;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
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

public class DodgeBallEvent {
    private static DodgeBallEvent instance;
    private List<Player> redPlayers;
    private List<Player> bluePlayers;

    public static DodgeBallEvent getDodgeBallEvent() {
        if (instance == null) {
            instance = new DodgeBallEvent();
        }
        return instance;
    }
    public void selectPlayers() {
        redPlayers = new ArrayList<>();
        bluePlayers = new ArrayList<>();
        int i;
        MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        MVWorldManager worldManager = core.getMVWorldManager();
        MultiverseWorld world = worldManager.getMVWorld("voidd");
        List<Player> onlinePlayers =  world.getCBWorld().getPlayers();

        NamespacedKey key = new NamespacedKey(Ccr.getPlugin(Ccr.class), "winner");

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
        }
        initPlayers(scoreboard, redTeam, redPlayers);
        Team blueTeam = scoreboard.getTeam("blueTeam");
        if (blueTeam == null) {
            blueTeam = scoreboard.registerNewTeam("blueTeam");
            blueTeam.setAllowFriendlyFire(false);

        }
        initPlayers(scoreboard, blueTeam, bluePlayers);
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
                player.teleport(new Location(player.getWorld(), -836.5, 148.5, 486.5));
            } else {
                player.teleport(new Location(player.getWorld(), -836.5, 148.5, 478.5));
            }
        }

    }
    public void changeTeamColor(Team team, ChatColor color) {
        // Loop through the players in the team and set their display names with the new color
        for (String playerName : team.getEntries()) {
            Bukkit.getPlayerExact(playerName).setDisplayName(color + playerName);
        }
    }
    public void resetTeamColor(Team team) {
        // Loop through the players in the team and set their display names with the new color
        for (String playerName : team.getEntries()) {
            Bukkit.getPlayerExact(playerName).setDisplayName(playerName);
        }
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

    private void winnerFinish(Player player) {
        NamespacedKey key = new NamespacedKey(Ccr.getPlugin(Ccr.class), "winner");
        PersistentDataContainer container = player.getPersistentDataContainer();
        container.set(key, PersistentDataType.STRING, "winner");

        player.teleport(new Location(player.getWorld(), -837, 156, 487));


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
