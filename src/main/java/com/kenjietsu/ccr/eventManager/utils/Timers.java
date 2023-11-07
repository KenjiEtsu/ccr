package com.kenjietsu.ccr.eventManager.utils;

import com.kenjietsu.ccr.Ccr;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class Timers {

    private int minutes;
    private int seconds;
    private int taskID;


    public Timers(int minutes, int seconds, TimerID eventID) {
        this.minutes = minutes;
        this.seconds = seconds;
        startTimer(eventID);
    }

    public void startTimer(TimerID eventID) {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.runTaskTimer(Ccr.getPlugin(Ccr.class), new TimerTask(minutes, seconds, eventID), 0L, 20L);
    }
    public void stopTimer() {
        Bukkit.getServer().getScheduler().cancelTasks(Ccr.getPlugin(Ccr.class));
        minutes = 0;
        seconds = 0;
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Score scoreS = scoreboard.getObjective("Timer").getScore("Segundos");
        Score scoreM = scoreboard.getObjective("Timer").getScore("Minutos");
        scoreS.setScore(seconds);
        scoreM.setScore(minutes);
    }
    public void restartTimer(int minutes, int seconds, TimerID eventID) {
        stopTimer();
        this.minutes = minutes;
        this.seconds = seconds;
        this.taskID = 0;
        startTimer(eventID);
    }
    public int getTaskID() {
        return this.taskID;
    }
}
