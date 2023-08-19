package com.kenjietsu.ccr.listeners;

import com.kenjietsu.ccr.event.TimeoutEvent;
import com.kenjietsu.ccr.eventManager.*;
import com.kenjietsu.ccr.eventManager.utils.GallinitaTeam;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import static com.kenjietsu.ccr.utils.MainEventUtils.sacrificar;

public class TimerListener implements Listener {
    @EventHandler
    public void timerEnd(TimeoutEvent event) {
        switch (event.getEventID()) {
            case 8:
                DodgeBallEvent dodgeBallEvent = DodgeBallEvent.getDodgeBallEvent();
                if (dodgeBallEvent.getRedPlayers().size() > dodgeBallEvent.getBluePlayers().size()) {
                    for (Player player : dodgeBallEvent.getBluePlayers() ) {
                        sacrificar(player);
                    }
                }
                if (dodgeBallEvent.getBluePlayers().size() > dodgeBallEvent.getRedPlayers().size()) {
                    for (Player player : dodgeBallEvent.getRedPlayers() ) {
                        sacrificar(player);
                    }
                }
                if (dodgeBallEvent.getBluePlayers().size() == dodgeBallEvent.getRedPlayers().size()) {
                    Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
                    Team redTeam = scoreboard.getTeam("redTeam");
                    Team blueTeam = scoreboard.getTeam("blueTeam");
                    for (Player player : dodgeBallEvent.getRedPlayers() ) {
                        redTeam.removeEntry(player.getName());
                        dodgeBallEvent.winnerFinish(player);
                    }
                    for (Player player : dodgeBallEvent.getBluePlayers() ) {
                        blueTeam.removeEntry(player.getName());
                        dodgeBallEvent.winnerFinish(player);
                    }
                }
                break;
            case 7:
                break;
            case 6:
                EventoFinalEvent eventoFinalEvent = EventoFinalEvent.getEventoFinalEvent();
                eventoFinalEvent.winnGame();
                break;
            case 5:
                break;
            case 4:
                GallinitaEvent gallinitaEvent = GallinitaEvent.getGallinitaEvent();
                for (GallinitaTeam team : gallinitaEvent.getGallinitaTeams()) {
                    sacrificar(team.primeraGallina());
                    sacrificar(team.segundaGallina());
                }
                break;
            case 3:
                break;
            case 2:
                break;
            case 1:
                PapaFriaEvent papaFriaEvent = PapaFriaEvent.getPapaFriaEvent();
                papaFriaEvent.finishEvent();
                sacrificar(papaFriaEvent.getPotater());
                break;
            case 0:
                Sound witherSpawn = Sound.sound(org.bukkit.Sound.ENTITY_WITHER_SPAWN.key(), Sound.Source.MASTER, 1, 1);
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.playSound(witherSpawn, Sound.Emitter.self());
                }
                EsconditeEvent.getEsconditeEvent().releaseCatcher();
                break;
            case 9:
                EsconditeEvent.getEsconditeEvent().endEvent();
                break;

        }
    }
}
