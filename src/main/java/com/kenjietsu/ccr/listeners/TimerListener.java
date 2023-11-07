package com.kenjietsu.ccr.listeners;

import com.kenjietsu.ccr.Ccr;
import com.kenjietsu.ccr.event.TimeoutEvent;
import com.kenjietsu.ccr.eventManager.*;
import com.kenjietsu.ccr.eventManager.utils.GallinitaTeam;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import static com.kenjietsu.ccr.utils.MainEventUtils.sacrificar;

public class TimerListener implements Listener {
    @EventHandler
    public void timerEnd(TimeoutEvent event) {
        switch (event.getEventID()) {
            case BALON -> {
                DodgeBallEvent dodgeBallEvent = DodgeBallEvent.getDodgeBallEvent();
                if (dodgeBallEvent.getRedPlayers().size() > dodgeBallEvent.getBluePlayers().size()) {
                    for (Player player : dodgeBallEvent.getBluePlayers()) {
                        sacrificar(player);
                    }
                }
                if (dodgeBallEvent.getBluePlayers().size() > dodgeBallEvent.getRedPlayers().size()) {
                    for (Player player : dodgeBallEvent.getRedPlayers()) {
                        sacrificar(player);
                    }
                }
                if (dodgeBallEvent.getBluePlayers().size() == dodgeBallEvent.getRedPlayers().size()) {
                    Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
                    Team redTeam = scoreboard.getTeam("redTeam");
                    Team blueTeam = scoreboard.getTeam("blueTeam");
                    for (Player player : dodgeBallEvent.getRedPlayers()) {
                        redTeam.removeEntry(player.getName());
                        dodgeBallEvent.winnerFinish(player);
                    }
                    for (Player player : dodgeBallEvent.getBluePlayers()) {
                        blueTeam.removeEntry(player.getName());
                        dodgeBallEvent.winnerFinish(player);
                    }
                }
            }
            case SPLEEF -> {
            }
            case FINAL -> {
                EventoFinalEvent eventoFinalEvent = EventoFinalEvent.getEventoFinalEvent();
                eventoFinalEvent.winnGame();
            }
            case EUROCRISTALES -> {
                EuroCristalesEvent euroCristalesEvent = EuroCristalesEvent.getEuroCristalesEvent();
                euroCristalesEvent.endQuestion();
            }
            case GALLINA -> {
                GallinitaEvent gallinitaEvent = GallinitaEvent.getGallinitaEvent();
                for (GallinitaTeam team : gallinitaEvent.getGallinitaTeams()) {
                    sacrificar(team.primeraGallina());
                    sacrificar(team.segundaGallina());
                }
            }
            case NORMAL_TIMER_FREEZE -> {
                NamespacedKey key = new NamespacedKey(Ccr.getPlugin(Ccr.class), "freeze");
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!player.isOp()) {
                        PersistentDataContainer container = player.getPersistentDataContainer();
                        container.set(key, PersistentDataType.STRING, "freeze");
                    }
                }
            }
            case NORMAL_TIMER -> Bukkit.getLogger().info("Timer out");
            case POTATO -> {
                PapaFriaEvent papaFriaEvent = PapaFriaEvent.getPapaFriaEvent();
                papaFriaEvent.finishEvent();
                Bukkit.getScheduler().cancelTasks(Ccr.getPlugin(Ccr.class));
                sacrificar(papaFriaEvent.getPotater());
            }
            case ESCONDITE -> {
                Sound witherSpawn = Sound.sound(org.bukkit.Sound.ENTITY_WITHER_SPAWN.key(), Sound.Source.MASTER, 1, 1);
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.playSound(witherSpawn, Sound.Emitter.self());
                }
                EsconditeEvent.getEsconditeEvent().releaseCatcher();
            }
            case ESCONDITE_P2 -> EsconditeEvent.getEsconditeEvent().endEvent();
            case EUROCRISTALES_P2 -> EuroCristalesEvent.getEuroCristalesEvent().nextQuestion();
            case EUROCRISTALES_P3 -> EuroCristalesEvent.getEuroCristalesEvent().endEvent();
        }
    }
}
