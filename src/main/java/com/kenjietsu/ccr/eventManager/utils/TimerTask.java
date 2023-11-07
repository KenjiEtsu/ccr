package com.kenjietsu.ccr.eventManager.utils;

import com.kenjietsu.ccr.event.TimeoutEvent;
import com.kenjietsu.ccr.eventManager.EsconditeEvent;
import com.kenjietsu.ccr.eventManager.EventoFinalEvent;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.function.Consumer;

public class TimerTask  implements Consumer<BukkitTask> {
    private int minutes;
    private int seconds;
    private final TimerID eventID;
    public TimerTask(int minutes, int seconds, TimerID eventID) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.eventID = eventID;
    }

    @Override
    public void accept(BukkitTask bukkitTask) {
        if (minutes == 0 && eventID == TimerID.POTATO) {
            if (seconds <= 6 && seconds > 1) {
                Sound sound = Sound.sound(org.bukkit.Sound.BLOCK_NOTE_BLOCK_BANJO.key(), Sound.Source.MASTER, 1, 1);
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.playSound(sound, Sound.Emitter.self());
                }
            }
            if (seconds == 1) {
                Sound soundF = Sound.sound(org.bukkit.Sound.BLOCK_NOTE_BLOCK_FLUTE.key(), Sound.Source.MASTER, 1, 1);
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.playSound(soundF, Sound.Emitter.self());
                }
            }
        }
        if (seconds % 20 == 0 && eventID == TimerID.ESCONDITE_P2) {
            EsconditeEvent esconditeEvent = EsconditeEvent.getEsconditeEvent();
            esconditeEvent.tickCatcher();
        }
        if (seconds % 30 == 0 && eventID == TimerID.FINAL) {
            EventoFinalEvent eventoFinalEvent = EventoFinalEvent.getEventoFinalEvent();
            eventoFinalEvent.countPoints();
        }
        if (seconds == 0) {
            if (minutes == 0) {
                TimeoutEvent timeoutEvent = new TimeoutEvent(eventID);
                Bukkit.getPluginManager().callEvent(timeoutEvent);
                bukkitTask.cancel();
            } else {
                minutes--;
                seconds = 59;
            }
        } else {
            seconds--;
        }
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Score scoreS = scoreboard.getObjective("Timer").getScore("Segundos");
        Score scoreM = scoreboard.getObjective("Timer").getScore("Minutos");
        scoreS.setScore(seconds);
        scoreM.setScore(minutes);
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendActionBar(Component.text(String.format("%02d", minutes) + ":" + String.format("%02d", seconds)));
        }
    }
}
