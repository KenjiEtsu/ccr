package com.kenjietsu.ccr.eventManager.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EuroCristalesPlayerData {
    public List<Boolean> cristales;
    public List<Boolean> cristalesFallen;
    public Player player;
    public Boolean answeredCorrectly;
    public EuroCristalesPlayerData(Player player) {
        this.player = player;
        cristales = new ArrayList<>();
        cristalesFallen = new ArrayList<>(List.of(false, false, false, false, false, false));
        answeredCorrectly = false;
    }


}
