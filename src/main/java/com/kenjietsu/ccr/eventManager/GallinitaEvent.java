package com.kenjietsu.ccr.eventManager;

import com.kenjietsu.ccr.Ccr;
import com.kenjietsu.ccr.eventManager.utils.GallinitaTeam;
import com.kenjietsu.ccr.eventManager.utils.Lists;
import com.kenjietsu.ccr.eventManager.utils.TimerID;
import com.kenjietsu.ccr.eventManager.utils.Timers;
import com.kenjietsu.ccr.items.ItemManager;
import com.kenjietsu.ccr.utils.MVCoreUtils;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GallinitaEvent {
    private static GallinitaEvent instance;
    private List<GallinitaTeam> gallinitaTeams;
    private Timers timer;

    public static GallinitaEvent getGallinitaEvent() {
        if (instance == null) {
            instance = new GallinitaEvent();
        }
        return instance;
    }

    public void startEvent() {
        List<Player> onlinePlayers = MVCoreUtils.getMVWorld("MidnightCastle").getCBWorld().getPlayers();
        //onlinePlayers.removeIf(ServerOperator::isOp);
        if (onlinePlayers.size() > 10 || onlinePlayers.size() < 2 || onlinePlayers.size()%2 != 0) {
            Bukkit.getLogger().warning("GallinitaEvent: Muchos jugadores, o muy pocos, o no son pares");
            return;
        }
        int i;
        for (i= 0; i < onlinePlayers.size()/2; i++) {
            Random rnd = new Random();
            int j = rnd.nextInt(onlinePlayers.size());
            int k = rnd.nextInt(onlinePlayers.size());
            while (j == k) {
                k = rnd.nextInt(onlinePlayers.size());
            }
            initList();
            gallinitaTeams.add(new GallinitaTeam((Player) onlinePlayers.toArray()[j], (Player) onlinePlayers.toArray()[k]));
            onlinePlayers.remove(j);
            onlinePlayers.remove(k);
        }
        timer = new Timers(30, 0, TimerID.GALLINA);  // IDK, cambiar el tiempo porsi
        startGalliniters();
        startHints();
    }
    private void startHints() {
        int i;
        for (List<Location> locations : Lists.getGallinitaFinalHints()) {
            i = 0;
            for (Location location : locations) {
                Bukkit.getScheduler().runTaskTimer(Ccr.getPlugin(Ccr.class), () -> {
                    location.getWorld().spawnParticle(Particle.REDSTONE, location, 10, 0.1, 0.1, 0.1, 0, new Particle.DustOptions(Color.RED, 1));
                }, i* 10L, 120);

                i++;
            }
        }
    }
    private void initList() {
        gallinitaTeams = new ArrayList<GallinitaTeam>();
    }
    private void startGalliniters(){
        int i = 0;
        for (GallinitaTeam gallinitaTeam : gallinitaTeams) {
            // first location -130 99 -11 next locations + 15
            gallinitaTeam.primeraGallina().teleport(Bukkit.getWorld("MidnightCastle").getBlockAt(-130, 102, -11+ i*15).getLocation());
            gallinitaTeam.segundaGallina().teleport(Bukkit.getWorld("MidnightCastle").getBlockAt(-130, 102, -11+ i*15).getLocation());
            gallinerItem(0, gallinitaTeam);
            i++;
        }

    }
    public void gallinerItem(int i, Player player) {
        GallinitaTeam currentGallinitaTeam = null;
        for (GallinitaTeam gallinitaTeam : gallinitaTeams) {
            if (gallinitaTeam.primeraGallina().equals(player) || gallinitaTeam.segundaGallina().equals(player)) {
                currentGallinitaTeam = gallinitaTeam;
                break;
            }
        }
        if (currentGallinitaTeam == null) {
            Bukkit.getLogger().warning("GallinitaEvent: Equipo no encontrado :(?");
            return;
        }
        gallinerItem(i, currentGallinitaTeam);
    }
    public void gallinerItem(int i , GallinitaTeam team) {
        if (i == 0) {
            team.primeraGallina().getInventory().setHelmet(ItemManager.blindFold);
            team.segundaGallina().getInventory().setHelmet(null);
        }
        if (i == 1) {
            team.primeraGallina().getInventory().setHelmet(null);
            team.segundaGallina().getInventory().setHelmet(ItemManager.blindFold);
        }
        if (i == 2){
            team.primeraGallina().getInventory().setHelmet(null);
            team.segundaGallina().getInventory().setHelmet(null);
            gallinitaTeams.remove(team);
        }
        else {
            Bukkit.getLogger().warning("GallinitaEvent: Como mierda hemos llegado aqui?");
        }
    }
    public List<GallinitaTeam> getGallinitaTeams() {
        return gallinitaTeams;
    }

}
