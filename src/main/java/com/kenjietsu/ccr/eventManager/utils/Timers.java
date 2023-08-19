package com.kenjietsu.ccr.eventManager.utils;

import com.kenjietsu.ccr.Ccr;
import com.kenjietsu.ccr.event.TimeoutEvent;
import com.kenjietsu.ccr.eventManager.EsconditeEvent;
import com.kenjietsu.ccr.eventManager.EventoFinalEvent;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class Timers {

    private int minutes;
    private int seconds;


    public Timers(int minutes, int seconds, int eventID) {
        this.minutes = minutes;
        this.seconds = seconds;
        /** 0 = Escondite
         *  1 = Potato
         *  2 = parkur
         *  3 = meis
         *  4 = gallina
         *  5 = eurocristales
         *  6 = final
         *  7 = spleef
         *  8 = balon
         *  9 = esconditeP2
         **/
        startTimer(eventID);
    }

    public void startTimer(int eventID) {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(Ccr.getPlugin(Ccr.class), () -> {
            if (minutes == 0) {
                if (eventID == 1) {

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
            }
            if (seconds == 0) {
                if (minutes == 0) {
                    TimeoutEvent timeoutEvent = new TimeoutEvent(eventID);
                    Bukkit.getPluginManager().callEvent(timeoutEvent);
                    scheduler.cancelTasks(Ccr.getPlugin(Ccr.class));
                } else {
                    minutes--;
                    seconds = 59;
                }
            } else {
                seconds--;
            }
            if (seconds % 20 == 0 && eventID == 9) {
                EsconditeEvent esconditeEvent = EsconditeEvent.getEsconditeEvent();
                esconditeEvent.tickCatcher();
            }
            if (seconds % 30 == 0 && eventID == 6) {
                EventoFinalEvent eventoFinalEvent = EventoFinalEvent.getEventoFinalEvent();
                eventoFinalEvent.countPoints();
            }
            Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
            Score scoreS = scoreboard.getObjective("Timer").getScore("Segundos");
            Score scoreM = scoreboard.getObjective("Timer").getScore("Minutos");
            scoreS.setScore(seconds);
            scoreM.setScore(minutes);

        }, 0L, 20L);
    }
    public void stopTimer() {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.cancelTasks(Ccr.getPlugin(Ccr.class));
        minutes = 0;
        seconds = 0;
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Score scoreS = scoreboard.getObjective("Timer").getScore("Segundos");
        Score scoreM = scoreboard.getObjective("Timer").getScore("Minutos");
        scoreS.setScore(seconds);
        scoreM.setScore(minutes);
    }
    public void restartTimer(int minutes, int seconds, int eventID) {
        this.minutes = minutes;
        this.seconds = seconds;
        startTimer(eventID);
    }

}
