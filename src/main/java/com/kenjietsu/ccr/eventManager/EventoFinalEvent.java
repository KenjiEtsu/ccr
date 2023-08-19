package com.kenjietsu.ccr.eventManager;

import com.kenjietsu.ccr.eventManager.utils.LocationList;
import com.kenjietsu.ccr.eventManager.utils.Timers;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import net.kyori.adventure.sound.Sound;
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
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.ArrayList;
import java.util.List;

import static com.kenjietsu.ccr.eventManager.utils.LocationList.getLocations;
import static com.kenjietsu.ccr.utils.MVCoreUtils.getMVWorld;
import static com.kenjietsu.ccr.utils.MainEventUtils.sacrificar;

public class EventoFinalEvent {
    private static EventoFinalEvent instance;
    private List<Player> players;
    private Timers timer;

    public static EventoFinalEvent getEventoFinalEvent() {
        if (instance == null) {
            instance = new EventoFinalEvent();
        }
        return instance;
    }

    public void startEvent() {
        players = new ArrayList<Player>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            //if (!player.isOp()) {
                players.add(player);
            //}
        }
        ItemStack itemStack = new ItemStack(Material.GOLD_INGOT);
        ItemMeta meta = itemStack.getItemMeta();

        Component name = Component.text("Alma de Caliz").color(TextColor.color(0xFF001D)).decorate(TextDecoration.BOLD);

        meta.displayName(name);
        itemStack.setItemMeta(meta);


        MultiverseWorld world = getMVWorld("esplosion");

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

    public void countPoints() {
        Player closestPlayer = null;
        double closestDistance = Double.MAX_VALUE;

        for (Player player : players) {
            double distance = player.getLocation().distance(LocationList.getCenterLocation());
            if (distance < closestDistance && distance < 13) {
                closestDistance = distance;
                closestPlayer = player;
            }
        }
        if (closestPlayer == null) {
            return;
        }
        int count = 0;
        for (ItemStack item : closestPlayer.getInventory().getContents()) {
            if (item != null && item.getType() == Material.GOLD_INGOT) {
                count += item.getAmount();
                closestPlayer.getInventory().remove(item);
            }
        }
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        int score = scoreboardManager.getMainScoreboard().getObjective("Caliz")
                .getScore(closestPlayer.getName()).getScore();
        scoreboardManager.getMainScoreboard().getObjective("Caliz")
                .getScore(closestPlayer.getName()).setScore(score + count);
    }
    public void winnGame() {
        Player winner = null;
        int score = 0;
        boolean empate = false;
        for (Player player : players) {
            int playerScore = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Caliz")
                    .getScore(player.getName()).getScore();
            if (playerScore > score) {
                score = playerScore;
                winner = player;
            } else if (playerScore == score) {
                Bukkit.getLogger().warning("Empate");
                empate = true;
            } else {
                sacrificar(player);
            }
            Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Caliz")
                    .getScore(player.getName()).setScore(0);
        }
        if (empate) {
            return;
        }
        Component message = Component.text("El ganador es ").color(TextColor.color(0xFF001D))
                .append(Component.text(winner.getName()).color(TextColor.color(0xFF001D)).decorate(TextDecoration.BOLD));
        Bukkit.broadcast(message);
        Sound sound = Sound.sound(org.bukkit.Sound.ENTITY_ENDER_DRAGON_DEATH.key(), Sound.Source.MASTER, 0.3f, 1);
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(sound, Sound.Emitter.self());
        }


    }
    public List<Player> getPlayers() {
        return players;
    }

}
