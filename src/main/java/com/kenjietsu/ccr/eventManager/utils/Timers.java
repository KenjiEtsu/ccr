package com.kenjietsu.ccr.eventManager.utils;

import com.kenjietsu.ccr.Ccr;
import com.kenjietsu.ccr.event.TimeoutEvent;
import com.kenjietsu.ccr.eventManager.EsconditeEvent;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class Timers {

    private int minutes;
    private int seconds;
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

    private int eventID;




    public Timers(int minutes, int seconds, int eventID) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.eventID = eventID;
        startTimer(eventID);
    }

    public void startTimer(int eventID) {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(Ccr.getPlugin(Ccr.class), () -> {
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

}
