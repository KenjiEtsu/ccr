package com.kenjietsu.ccr.event;

import com.kenjietsu.ccr.eventManager.utils.TimerID;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class TimeoutEvent extends Event implements Cancellable {
    private final TimerID eventID;
    private boolean isCancelled;
    public TimeoutEvent(TimerID eventID) {
        this.eventID = eventID;
        this.isCancelled = false;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }

    private static final HandlerList handlers = new HandlerList();
    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }

    public TimerID getEventID() {
        return eventID;
    }
}
