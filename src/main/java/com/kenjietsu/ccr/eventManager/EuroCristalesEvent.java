package com.kenjietsu.ccr.eventManager;

import com.kenjietsu.ccr.Ccr;
import com.kenjietsu.ccr.eventManager.utils.Lists;
import com.kenjietsu.ccr.eventManager.utils.TimerID;
import com.kenjietsu.ccr.eventManager.utils.Timers;
import com.kenjietsu.ccr.utils.MVCoreUtils;
import com.kenjietsu.ccr.utils.MainEventUtils;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.permissions.ServerOperator;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.time.Duration;
import java.util.*;

import static com.kenjietsu.ccr.utils.MVCoreUtils.getMVWorld;

public class EuroCristalesEvent {

    private static EuroCristalesEvent instance;

    private static List<Boolean> lightGrayCristales;
    private static List<Boolean> lightGrayCristalesFallen;
    private static Player lightGrayPlayer;
    private static Boolean lightGrayAnsweredCorrectly;
    private static List<Boolean> yellowCristales;
    private static List<Boolean> yellowCristalesFallen;
    private static Player yellowPlayer;
    private static Boolean yellowAnsweredCorrectly;
    private static List<Boolean> pinkCristales;
    private static List<Boolean> pinkCristalesFallen;
    private static Player pinkPlayer;
    private static Boolean pinkAnsweredCorrectly;
    private static List<Boolean> magentaCristales;
    private static List<Boolean> magentaCristalesFallen;
    private static Player magentaPlayer;
    private static Boolean magentaAnsweredCorrectly;
    private static List<Boolean> orangeCristales;
    private static List<Boolean> orangeCristalesFallen;
    private static Player orangePlayer;
    private static Boolean orangeAnsweredCorrectly;
    private static List<Boolean> grayCristales;
    private static List<Boolean> grayCristalesFallen;
    private static Player grayPlayer;
    private static Boolean grayAnsweredCorrectly;
    private static List<Boolean> limeCristales;
    private static List<Boolean> limeCristalesFallen;
    private static Player limePlayer;
    private static Boolean limeAnsweredCorrectly;
    private static List<Boolean> lightBlueCristales;
    private static List<Boolean> lightBlueCristalesFallen;
    private static Player lightBluePlayer;
    private static Boolean lightBlueAnsweredCorrectly;


    private static List<Player> gamePlayers;
    private static Map<Integer, String> questions;
    private static int currentQuestion;
    private static List<Integer> questionListIndex;

    private static Timers timer;

    public List<Player> getPlayers() {
        return gamePlayers;
    }
    public void removePlayer(Player player) {
        try {
            gamePlayers.remove(player);
        } catch (Exception ignored) {
            Bukkit.getLogger().warning("Error removing player from list(Not in list?)");
        }
    }

    public List<Boolean> getLightGrayCristales() {
        return lightGrayCristales;
    }
    public  List<Boolean> getYellowCristales() {
        return yellowCristales;
    }
    public  List<Boolean> getPinkCristales() {
        return pinkCristales;
    }
    public  List<Boolean> getMagentaCristales() {
        return magentaCristales;
    }
    public List<Boolean> getOrangeCristales() {
        return orangeCristales;
    }
    public List<Boolean> getGrayCristales() {
        return grayCristales;
    }
    public List<Boolean> getLimeCristales() {
        return limeCristales;
    }
    public List<Boolean> getLightBlueCristales() {
        return lightBlueCristales;
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
        initValues();
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

        List<Player> players = MVCoreUtils.getMVWorld("voidd").getCBWorld().getPlayers();
        Sound sound = Sound.sound(org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING.key(), Sound.Source.MASTER, 1F, 0.71F);
        Sound sound1 = Sound.sound(org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING.key(), Sound.Source.MASTER, 1F, 0.75F);
        Sound sound2 = Sound.sound(org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING.key(), Sound.Source.MASTER, 1F, 0.84F);
        Sound sound3 = Sound.sound(org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING.key(), Sound.Source.MASTER, 1F, 0.89F);

        for (Player player : players) {
            player.playSound(sound);
            Bukkit.getScheduler().runTaskLater(Ccr.getPlugin(Ccr.class), () -> {
                player.playSound(sound1);
                Bukkit.getScheduler().runTaskLater(Ccr.getPlugin(Ccr.class), () -> {
                    player.playSound(sound2);
                    Bukkit.getScheduler().runTaskLater(Ccr.getPlugin(Ccr.class), () -> {
                        player.playSound(sound3);}, 2);
                    }, 2);}, 2);


        }
        players.removeIf(ServerOperator::isOp);

        if (players.size() > 8) {
            return;
        }
        //select random players
        for (int i = 0; i < 7; i++) {
            int j = (int) (Math.random() * players.size());
            if (players.isEmpty() && !gamePlayers.isEmpty()) {
                break;
            }
            selectPLayer(players.get(j));
            players.remove(j);
        }
        tpPlayers();
        Map<Integer, String> allQuestionList = Lists.getQuestionList();
        List<Integer> transIndex = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        Collections.shuffle(transIndex);
        for (int i = 0; i < 6; i++) {
            questions.put(transIndex.get(i), allQuestionList.get(transIndex.get(i)));
            allQuestionList.remove(transIndex.get(i));
        }
        questionListIndex = new ArrayList<>(questions.keySet());
        Collections.shuffle(questionListIndex);
        timer = new Timers(0, 20, TimerID.EUROCRISTALES_P2);
    }

    public List<Boolean> getLightGrayCristalesFallen() {
        return lightGrayCristalesFallen;
    }

    public List<Boolean> getYellowCristalesFallen() {
        return yellowCristalesFallen;
    }

    public List<Boolean> getPinkCristalesFallen() {
        return pinkCristalesFallen;
    }

    public List<Boolean> getMagentaCristalesFallen() {
        return magentaCristalesFallen;
    }

    public List<Boolean> getOrangeCristalesFallen() {
        return orangeCristalesFallen;
    }

    public List<Boolean> getGrayCristalesFallen() {
        return grayCristalesFallen;
    }

    public List<Boolean> getLimeCristalesFallen() {
        return limeCristalesFallen;
    }

    public List<Boolean> getLightBlueCristalesFallen() {
        return lightBlueCristalesFallen;
    }

    public static void refillCristals() {
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
    private void initValues() {
        lightGrayCristales = new ArrayList<>();
        lightGrayCristalesFallen = new ArrayList<>(List.of(false, false, false, false, false, false));
        yellowCristales = new ArrayList<>();
        yellowCristalesFallen = new ArrayList<>(List.of(false, false, false, false, false, false));
        pinkCristales = new ArrayList<>();
        pinkCristalesFallen = new ArrayList<>(List.of(false, false, false, false, false, false));
        magentaCristales = new ArrayList<>();
        magentaCristalesFallen = new ArrayList<>(List.of(false, false, false, false, false, false));
        orangeCristales = new ArrayList<>();
        orangeCristalesFallen = new ArrayList<>(List.of(false, false, false, false, false, false));
        grayCristales = new ArrayList<>();
        grayCristalesFallen = new ArrayList<>(List.of(false, false, false, false, false, false));
        limeCristales = new ArrayList<>();
        limeCristalesFallen = new ArrayList<>(List.of(false, false, false, false, false, false));
        lightBlueCristales = new ArrayList<>();
        lightBlueCristalesFallen = new ArrayList<>(List.of(false, false, false, false, false, false));
        gamePlayers = new ArrayList<>();
        questions = new HashMap<>();
        currentQuestion = 0;
        lightGrayAnsweredCorrectly = false;
        yellowAnsweredCorrectly = false;
        pinkAnsweredCorrectly = false;
        magentaAnsweredCorrectly = false;
        orangeAnsweredCorrectly = false;
        grayAnsweredCorrectly = false;
        limeAnsweredCorrectly = false;
        lightBlueAnsweredCorrectly = false;
    }
    public void endEvent() {
        for (Player player : gamePlayers) {
            MainEventUtils.sacrificar(player);
        }
        instance = null;
    }

    public void flipItemInList(int list, int index) {
        switch (list) {
            case 0 -> lightGrayCristalesFallen.set(index, true);
            case 1 -> yellowCristalesFallen.set(index, true);
            case 2 -> pinkCristalesFallen.set(index, true);
            case 3 -> magentaCristalesFallen.set(index, true);
            case 4 -> orangeCristalesFallen.set(index, true);
            case 5 -> grayCristalesFallen.set(index, true);
            case 6 -> limeCristalesFallen.set(index, true);
            case 7 -> lightBlueCristalesFallen.set(index, true);
        }
    }
    private void selectPLayer(Player player) {
        if (lightGrayPlayer == null) {
            lightGrayPlayer = player;
            gamePlayers.add(player);
        } else if (yellowPlayer == null) {
            yellowPlayer = player;
            gamePlayers.add(player);
        } else if (pinkPlayer == null) {
            pinkPlayer = player;
            gamePlayers.add(player);
        } else if (magentaPlayer == null) {
            magentaPlayer = player;
            gamePlayers.add(player);
        } else if (orangePlayer == null) {
            orangePlayer = player;
            gamePlayers.add(player);
        } else if (grayPlayer == null) {
            grayPlayer = player;
            gamePlayers.add(player);
        } else if (limePlayer == null) {
            limePlayer = player;
            gamePlayers.add(player);
        } else if (lightBluePlayer == null) {
            lightBluePlayer = player;
            gamePlayers.add(player);
        }

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
    public void nextQuestion() {
        if (questionListIndex.isEmpty()) {
            finalTime();
            return;
        }
        int listIndex = (int) (questionListIndex.size()*Math.random());
        int index = questionListIndex.get(listIndex);

        TextComponent component = Lists.getComponent(questions.get(index), Lists.getResponses(index));
        questionListIndex.remove(listIndex);
        for (Player player : gamePlayers) {
            player.sendMessage(component);
        }

        timer.restartTimer(0 , 20, TimerID.EUROCRISTALES);

    }

    private void finalTime() {
        timer.restartTimer(3, 0, TimerID.EUROCRISTALES_P3);

    }

    public void endQuestion() {
        lightGrayAnsweredCorrectly = sendResponse(lightGrayPlayer, lightGrayAnsweredCorrectly, lightGrayCristales);
        yellowAnsweredCorrectly = sendResponse(yellowPlayer, yellowAnsweredCorrectly, yellowCristales);
        pinkAnsweredCorrectly = sendResponse(pinkPlayer, pinkAnsweredCorrectly, pinkCristales);
        magentaAnsweredCorrectly = sendResponse(magentaPlayer, magentaAnsweredCorrectly, magentaCristales);
        orangeAnsweredCorrectly = sendResponse(orangePlayer, orangeAnsweredCorrectly, orangeCristales);
        grayAnsweredCorrectly = sendResponse(grayPlayer, grayAnsweredCorrectly, grayCristales);
        limeAnsweredCorrectly = sendResponse(limePlayer, limeAnsweredCorrectly, limeCristales);
        lightBlueAnsweredCorrectly = sendResponse(lightBluePlayer, lightBlueAnsweredCorrectly, lightBlueCristales);
        currentQuestion++;
        timer.restartTimer(0, 20, TimerID.EUROCRISTALES_P2);
    }
    private boolean sendResponse(Player player, boolean correct, List<Boolean> cristales) {
        if (player == null) {
            return false;
        }
        TextComponent izquierda = Component.text("El cristal correcto es el de la: ").color(TextColor.color(0x524FF))
                .append(Component.text("Izquierda").color(TextColor.color(0x524FF)).decorate(TextDecoration.BOLD));
        Title titleIzquierda = Title.title(Component.empty(), izquierda, Title.Times.times(Duration.ofSeconds(1), Duration.ofSeconds(3), Duration.ofMillis(500)));
        TextComponent derecha = Component.text("El cristal correcto es el de la: ").color(TextColor.color(0x524FF))
                .append(Component.text("Derecha").color(TextColor.color(0x524FF)).decorate(TextDecoration.BOLD));
        Title titleDerecha = Title.title(Component.empty(), derecha, Title.Times.times(Duration.ofSeconds(1), Duration.ofSeconds(3), Duration.ofMillis(500)));
        if(correct) {
            player.sendMessage("Respuesta correcta");
            if (!cristales.get(currentQuestion)) {
                player.sendMessage(derecha);
                player.showTitle(titleDerecha);
            }
            else {
                player.sendMessage(izquierda);

                player.showTitle(titleIzquierda);
            }
            Sound sound = Sound.sound(org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING.key(), Sound.Source.MASTER, 1F, 2F);
            player.playSound(sound);
        } else {
            player.sendMessage("Respuesta incorrecta");
            player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 20*5, 1));
            Sound sound = Sound.sound(org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING.key(), Sound.Source.MASTER, 1F, 0.5F);
            player.playSound(sound);
        }
        return false;
    }
    public void answerQuestion(Player player, Integer i) {
        if (player == lightGrayPlayer) {
            lightGrayAnsweredCorrectly = i == 0;
            player.sendMessage("Respuesta enviada");
        } else if (player == yellowPlayer) {
            yellowAnsweredCorrectly = i == 0;
            player.sendMessage("Respuesta enviada");
        } else if (player == pinkPlayer) {
            pinkAnsweredCorrectly = i == 0;
            player.sendMessage("Respuesta enviada");
        } else if (player == magentaPlayer) {
            magentaAnsweredCorrectly = i == 0;
            player.sendMessage("Respuesta enviada");
        } else if (player == orangePlayer) {
            orangeAnsweredCorrectly = i == 0;
            player.sendMessage("Respuesta enviada");
        } else if (player == grayPlayer) {
            grayAnsweredCorrectly = i == 0;
            player.sendMessage("Respuesta enviada");
        } else if (player == limePlayer) {
            limeAnsweredCorrectly = i == 0;
            player.sendMessage("Respuesta enviada");
        } else if (player == lightBluePlayer) {
            lightBlueAnsweredCorrectly = i == 0;
            player.sendMessage("Respuesta enviada");
        }

    }
}
