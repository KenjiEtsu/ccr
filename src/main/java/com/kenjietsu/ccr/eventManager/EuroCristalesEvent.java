package com.kenjietsu.ccr.eventManager;

import com.kenjietsu.ccr.eventManager.utils.Lists;
import com.kenjietsu.ccr.utils.MVCoreUtils;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.permissions.ServerOperator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kenjietsu.ccr.utils.MVCoreUtils.getMVWorld;

public class EuroCristalesEvent {

    private static EuroCristalesEvent instance;

    private static List<Boolean> lightGrayCristales;
    private static Player lightGrayPlayer;
    private static List<Boolean> yellowCristales;
    private static Player yellowPlayer;
    private static List<Boolean> pinkCristales;
    private static Player pinkPlayer;
    private static List<Boolean> magentaCristales;
    private static Player magentaPlayer;
    private static List<Boolean> orangeCristales;
    private static Player orangePlayer;
    private static List<Boolean> grayCristales;
    private static Player grayPlayer;
    private static List<Boolean> limeCristales;
    private static Player limePlayer;
    private static List<Boolean> lightBlueCristales;
    private static Player lightBluePlayer;

    private static Map<Integer, String> questions;

    public List<Boolean> getLightGrayCristales() {
        return lightGrayCristales;
    }
    public Player getLightGrayPlayer() {
        return lightGrayPlayer;
    }

    public  List<Boolean> getYellowCristales() {
        return yellowCristales;
    }
    public Player getYellowPlayer() {
        return yellowPlayer;
    }

    public  List<Boolean> getPinkCristales() {
        return pinkCristales;
    }
    public Player getPinkPlayer() {
        return pinkPlayer;
    }

    public  List<Boolean> getMagentaCristales() {
        return magentaCristales;
    }
    public Player getMagentaPlayer() {
        return magentaPlayer;
    }

    public List<Boolean> getOrangeCristales() {
        return orangeCristales;
    }
    public Player getOrangePlayer() {
        return orangePlayer;
    }

    public List<Boolean> getGrayCristales() {
        return grayCristales;
    }
    public Player getGrayPlayer() {
        return grayPlayer;
    }

    public List<Boolean> getLimeCristales() {
        return limeCristales;
    }
    public Player getLimePlayer() {
        return limePlayer;
    }

    public List<Boolean> getLightBlueCristales() {
        return lightBlueCristales;
    }
    public Player getLightBluePlayer() {
        return lightBluePlayer;
    }


    public static EuroCristalesEvent getEuroCristalesEvent() {
        if (instance == null) {
            instance = new EuroCristalesEvent();
        }
        return instance;
    }
    public static boolean isEuroCristalesEventOn() {
        return instance != null;
    }
    public void startEvent() {
        initLists();
        refillCristals();
        for (int i = 0; i < 6; i++) {
            //generate random bool
            lightGrayCristales.add(Math.random() < 0.5);
            yellowCristales.add(Math.random() < 0.5);
            pinkCristales.add(Math.random() < 0.5);
            magentaCristales.add(Math.random() < 0.5);
            orangeCristales.add(Math.random() < 0.5);
            grayCristales.add(Math.random() < 0.5);
            limeCristales.add(Math.random() < 0.5);
            lightBlueCristales.add(Math.random() < 0.5);
        }

        //duplicate lists but if true false... at the end
        duplicateAtTheEnd(lightGrayCristales);
        duplicateAtTheEnd(yellowCristales);
        duplicateAtTheEnd(pinkCristales);
        duplicateAtTheEnd(magentaCristales);
        duplicateAtTheEnd(orangeCristales);
        duplicateAtTheEnd(grayCristales);
        duplicateAtTheEnd(limeCristales);
        duplicateAtTheEnd(lightBlueCristales);

        List<Player> players = MVCoreUtils.getMVWorld("voidd").getCBWorld().getPlayers();
        players.removeIf(ServerOperator::isOp);

        if (players.size() > 8) {
            return;
        }
        //select random players
        for (int i = 0; i < 7; i++) {
            int j = (int) (Math.random() * players.size());
            if (players.isEmpty()) {
                break;
            }
            selectPLayer(players.get(j));
            players.remove(j);
        }

        for (int i = 0; i < 6; i++) {
            int j = (int) (Math.random() * 12);
            Map<Integer, String> allQuestionList = Lists.getQuestionList();
            questions.put(j, allQuestionList.get(j));
        }
    }

    private static void duplicateAtTheEnd(List<Boolean> booleanList) {
        List<Boolean> tempList = new ArrayList<>();
        for (Boolean bool : booleanList) {
            if (bool) {
                tempList.add(false);
            } else {
                tempList.add(true);
            }
        }
        booleanList.addAll(tempList);
    }

    public static void refillCristals() {

        MultiverseWorld world = getMVWorld("voidd");
        for (Location location : Lists.getLightGrayEuroLocations()) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 8; j++) {
                        location.getBlock().setType(Material.LIGHT_GRAY_STAINED_GLASS);
                        location.add(0, 0, 1);
                    }
                    location.add(-1, 0, -8);
                }
        }
        for (Location location : Lists.getYellowEuroLocations()) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 8; j++) {
                        location.getBlock().setType(Material.YELLOW_STAINED_GLASS);
                        location.add(0, 0, 1);
                    }
                    location.add(-1, 0, -8);
                }
        }
        for (Location location : Lists.getPinkEuroLocations()) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 8; j++) {
                        location.getBlock().setType(Material.PINK_STAINED_GLASS);
                        location.add(1, 0, 0);
                    }
                    location.add(-8, 0, 1);
                }
        }
        for (Location location : Lists.getMagentaEuroLocations()) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 8; j++) {
                        location.getBlock().setType(Material.MAGENTA_STAINED_GLASS);
                        location.add(1, 0, 0);
                    }
                    location.add(-8, 0, 1);
                }
        }
        for (Location location : Lists.getLightBlueLocations()) {
            location.add(1.5, 0, -0.5);
            location.getBlock().setType(Material.LIGHT_BLUE_STAINED_GLASS);
            location.add(-1, 0, 1);
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == 5 && j == 2) {
                        continue;
                    }
                    location.getBlock().setType(Material.LIGHT_BLUE_STAINED_GLASS);
                    location.add(0, 0, -1);
                }
                location.add(-1, 0, 2);
            }
        }
        for (Location location : Lists.getOrangeEuroLocations()) {
            location.add(-0.5, 0, -0.5);
            location.getBlock().setType(Material.ORANGE_STAINED_GLASS);
            location.add(1, 0, 1);
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == 5 && j == 2) {
                        continue;
                    }
                    location.getBlock().setType(Material.ORANGE_STAINED_GLASS);
                    location.add(-1, 0, 0);
                }
                location.add(2, 0, 1);
            }
        }
        for (Location location : Lists.getGrayEuroLocations()) {
            location.add(-0.5, 0, 1.5);
            location.getBlock().setType(Material.GRAY_STAINED_GLASS);
            location.add(1, 0, -1);
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == 5 && j == 2) {
                        continue;
                    }
                    location.getBlock().setType(Material.GRAY_STAINED_GLASS);
                    location.add(0, 0, 1);
                }
                location.add(1, 0, -2);
            }
        }
        for (Location location : Lists.getLimeEuroLocations()) {
            location.add(1.5, 0, 1.5);
            location.getBlock().setType(Material.LIME_STAINED_GLASS);
            location.add(-1, 0, -1);
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == 5 && j == 2) {
                        continue;
                    }
                    location.getBlock().setType(Material.LIME_STAINED_GLASS);
                    location.add(1, 0, 0);
                }
                location.add(-2, 0, -1);
            }
        }

    }
    private void initLists() {
        lightGrayCristales = new ArrayList<Boolean>();
        yellowCristales = new ArrayList<Boolean>();
        pinkCristales = new ArrayList<Boolean>();
        magentaCristales = new ArrayList<Boolean>();
        orangeCristales = new ArrayList<Boolean>();
        grayCristales = new ArrayList<Boolean>();
        limeCristales = new ArrayList<Boolean>();
        lightBlueCristales = new ArrayList<Boolean>();
        questions = new HashMap<>();
    }
    public void endEvent() {
        instance = null;
    }

    public void flipItemInList(int list, int index) {
        switch (list) {
            case 0 -> lightGrayCristales.set(index, !lightGrayCristales.get(index));
            case 1 -> yellowCristales.set(index, !yellowCristales.get(index));
            case 2 -> pinkCristales.set(index, !pinkCristales.get(index));
            case 3 -> magentaCristales.set(index, !magentaCristales.get(index));
            case 4 -> orangeCristales.set(index, !orangeCristales.get(index));
            case 5 -> grayCristales.set(index, !grayCristales.get(index));
            case 6 -> limeCristales.set(index, !limeCristales.get(index));
            case 7 -> lightBlueCristales.set(index, !lightBlueCristales.get(index));
        }
    }
    private void selectPLayer(Player player) {
        if (lightGrayPlayer == null) {
            lightGrayPlayer = player;
        } else if (yellowPlayer == null) {
            yellowPlayer = player;
        } else if (pinkPlayer == null) {
            pinkPlayer = player;
        } else if (magentaPlayer == null) {
            magentaPlayer = player;
        } else if (orangePlayer == null) {
            orangePlayer = player;
        } else if (grayPlayer == null) {
            grayPlayer = player;
        } else if (limePlayer == null) {
            limePlayer = player;
        } else if (lightBluePlayer == null) {
            lightBluePlayer = player;
        }
        tpPlayers();
    }
    private void tpPlayers() {
        World world = getMVWorld("voidd").getCBWorld();
        if (lightGrayPlayer != null) {
            lightGrayPlayer.teleport(new Location(world, -837.5, 105.1, 373));
        }
        if (yellowPlayer != null) {
            yellowPlayer.teleport(new Location(world, -837.5, 105.1, 591));
        }
        if (pinkPlayer != null) {
            pinkPlayer.teleport(new Location(world, -945.5, 105.1, 481));
        }
        if (magentaPlayer != null) {
            magentaPlayer.teleport(new Location(world, -728, 105.1, 481.5));
        }
        if (orangePlayer != null) {
            orangePlayer.teleport(new Location(world, -760.5, 105.1, 405.5));
        }
        if (grayPlayer != null) {
            grayPlayer.teleport(new Location(world, -913.5, 105.1, 405.5));
        }
        if (limePlayer != null) {
            limePlayer.teleport(new Location(world, -913.5, 105.1, 558.5));
        }
        if (lightBluePlayer != null) {
            lightBluePlayer.teleport(new Location(world, -759.5, 105.1, 559.5));
        }
    }
}
