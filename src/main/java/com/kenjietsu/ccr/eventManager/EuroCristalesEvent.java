package com.kenjietsu.ccr.eventManager;

import com.kenjietsu.ccr.Ccr;
import com.kenjietsu.ccr.eventManager.utils.EuroCristalesPlayerData;
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

    private static EuroCristalesPlayerData lightGrayPlayer;
    private static EuroCristalesPlayerData yellowPlayer;
    private static EuroCristalesPlayerData pinkPlayer;
    private static EuroCristalesPlayerData magentaPlayer;
    private static EuroCristalesPlayerData orangePlayer;
    private static EuroCristalesPlayerData grayPlayer;
    private static EuroCristalesPlayerData limePlayer;
    private static EuroCristalesPlayerData lightBluePlayer;


    private static List<EuroCristalesPlayerData> gamePlayers;
    private static Map<Integer, String> questions;
    private static int currentQuestion;
    private static List<Integer> questionListIndex;

    private static Timers timer;

    public List<EuroCristalesPlayerData> getPlayers() {
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
        return lightGrayPlayer.cristales;
    }
    public  List<Boolean> getYellowCristales() {
        return yellowPlayer.cristales;
    }
    public  List<Boolean> getPinkCristales() {
        return pinkPlayer.cristales;
    }
    public  List<Boolean> getMagentaCristales() {
        return magentaPlayer.cristales;
    }
    public List<Boolean> getOrangeCristales() {
        return orangePlayer.cristales;
    }
    public List<Boolean> getGrayCristales() {
        return grayPlayer.cristales;
    }
    public List<Boolean> getLimeCristales() {
        return limePlayer.cristales;
    }
    public List<Boolean> getLightBlueCristales() {
        return lightBluePlayer.cristales;
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
            lightGrayPlayer.cristales.add(Math.random() < 0.5);
            yellowPlayer.cristales.add(Math.random() < 0.5);
            pinkPlayer.cristales.add(Math.random() < 0.5);
            magentaPlayer.cristales.add(Math.random() < 0.5);
            orangePlayer.cristales.add(Math.random() < 0.5);
            grayPlayer.cristales.add(Math.random() < 0.5);
            limePlayer.cristales.add(Math.random() < 0.5);
            lightBluePlayer.cristales.add(Math.random() < 0.5);
        }

        //duplicate lists but if true false... at the end

        List<Player> players = MVCoreUtils.getMVWorld("voidd").getCBWorld().getPlayers();
        Sound sound = Sound.sound(org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING.key(), Sound.Source.MASTER, 1F, 0.71F);
        Sound sound1 = Sound.sound(org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING.key(), Sound.Source.MASTER, 1F, 0.75F);
        Sound sound2 = Sound.sound(org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING.key(), Sound.Source.MASTER, 1F, 0.84F);
        Sound sound3 = Sound.sound(org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING.key(), Sound.Source.MASTER, 1F, 0.89F);

        TextComponent component = Lists.getEurocristalesGameInfo();

        for (Player player : players) {
            player.sendMessage(component);
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
        timer = new Timers(0, 1, TimerID.EUROCRISTALES_P2); //20s
    }

    public List<Boolean> getLightGrayCristalesFallen() {
        return lightGrayPlayer.cristalesFallen;
    }

    public List<Boolean> getYellowCristalesFallen() {
        return yellowPlayer.cristalesFallen;
    }

    public List<Boolean> getPinkCristalesFallen() {
        return pinkPlayer.cristalesFallen;
    }

    public List<Boolean> getMagentaCristalesFallen() {
        return magentaPlayer.cristalesFallen;
    }

    public List<Boolean> getOrangeCristalesFallen() {
        return orangePlayer.cristalesFallen;
    }

    public List<Boolean> getGrayCristalesFallen() {
        return grayPlayer.cristalesFallen;
    }

    public List<Boolean> getLimeCristalesFallen() {
        return limePlayer.cristalesFallen;
    }

    public List<Boolean> getLightBlueCristalesFallen() {
        return lightBluePlayer.cristalesFallen;
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
        gamePlayers = new ArrayList<>();
        questions = new HashMap<>();
        currentQuestion = 0;
    }
    public void endEvent() {
        for (EuroCristalesPlayerData player : gamePlayers) {

            MainEventUtils.sacrificar(player.player);
        }
        instance = null;
    }

    public void flipItemInList(int list, int index) {
        switch (list) {
            case 0 -> lightGrayPlayer.cristalesFallen.set(index, true);
            case 1 -> yellowPlayer.cristalesFallen.set(index, true);
            case 2 -> pinkPlayer.cristalesFallen.set(index, true);
            case 3 -> magentaPlayer.cristalesFallen.set(index, true);
            case 4 -> orangePlayer.cristalesFallen.set(index, true);
            case 5 -> grayPlayer.cristalesFallen.set(index, true);
            case 6 -> limePlayer.cristalesFallen.set(index, true);
            case 7 -> lightBluePlayer.cristalesFallen.set(index, true);
        }
    }

    private void selectPLayer(Player player) {
        if (lightGrayPlayer == null) {
            lightGrayPlayer = new EuroCristalesPlayerData(player);
        } else if (yellowPlayer == null) {
            yellowPlayer = new EuroCristalesPlayerData(player);
        } else if (pinkPlayer == null) {
            pinkPlayer = new EuroCristalesPlayerData(player);
        } else if (magentaPlayer == null) {
            magentaPlayer = new EuroCristalesPlayerData(player);
        } else if (orangePlayer == null) {
            orangePlayer = new EuroCristalesPlayerData(player);
        } else if (grayPlayer == null) {
            grayPlayer = new EuroCristalesPlayerData(player);
        } else if (limePlayer == null) {
            limePlayer = new EuroCristalesPlayerData(player);
        } else if (lightBluePlayer == null) {
            lightBluePlayer = new EuroCristalesPlayerData(player);
        }

    }

    private void tpPlayers() {
        World world = getMVWorld("voidd").getCBWorld();
        if (lightGrayPlayer != null) {
            lightGrayPlayer.player.teleport(new Location(world, -837.5, 105.1, 373));
        }
        if (yellowPlayer != null) {
            yellowPlayer.player.teleport(new Location(world, -837.5, 105.1, 591));
        }
        if (pinkPlayer != null) {
            pinkPlayer.player.teleport(new Location(world, -945.5, 105.1, 481));
        }
        if (magentaPlayer != null) {
            magentaPlayer.player.teleport(new Location(world, -728, 105.1, 481.5));
        }
        if (orangePlayer != null) {
            orangePlayer.player.teleport(new Location(world, -760.5, 105.1, 405.5));
        }
        if (grayPlayer != null) {
            grayPlayer.player.teleport(new Location(world, -913.5, 105.1, 405.5));
        }
        if (limePlayer != null) {
            limePlayer.player.teleport(new Location(world, -913.5, 105.1, 558.5));
        }
        if (lightBluePlayer != null) {
            lightBluePlayer.player.teleport(new Location(world, -759.5, 105.1, 559.5));
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
        int currentTitleQuestion = currentQuestion + 1;
        Title title = Title.title(Component.empty(),  Component.text()
                .append(Component.text("a ").decorate(TextDecoration.OBFUSCATED).color(TextColor.color(0xFF0002)))
                .append(Component.text("Pregunta [#" + currentTitleQuestion + "]"))
                .append(Component.text(" a").decorate(TextDecoration.OBFUSCATED).color(TextColor.color(0xFF0002))).build(), Title.Times.times(Duration.ofSeconds(1), Duration.ofSeconds(3), Duration.ofMillis(500)));
        for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage(component);
                    player.showTitle(title);
        }

        timer.restartTimer(0 , 7, TimerID.EUROCRISTALES);

    }

    private void finalTime() {
        timer.restartTimer(3, 0, TimerID.EUROCRISTALES_P3);

    }

    public void endQuestion() {
        TextComponent resultados = Component.text("Resultados:").color(TextColor.color(0xFFFFFF)).decorate(TextDecoration.BOLD)
                .append(Component.newline())
                .append(Component.text("LightGray: ").color(TextColor.color(0xFFFFFF)).decorate(TextDecoration.BOLD))
                .append(Component.text(lightGrayPlayer.answeredCorrectly ? "Correcto" : "Incorrecto").color(TextColor.color(0xFFFFFF)))
                .append(Component.newline())
                .append(Component.text("Yellow: ").color(TextColor.color(0xFFFFFF)).decorate(TextDecoration.BOLD))
                .append(Component.text(yellowPlayer.answeredCorrectly ? "Correcto" : "Incorrecto").color(TextColor.color(0xFFFFFF)))
                .append(Component.newline())
                .append(Component.text("Pink: ").color(TextColor.color(0xFFFFFF)).decorate(TextDecoration.BOLD))
                .append(Component.text(pinkPlayer.answeredCorrectly ? "Correcto" : "Incorrecto").color(TextColor.color(0xFFFFFF)))
                .append(Component.newline())
                .append(Component.text("Magenta: ").color(TextColor.color(0xFFFFFF)).decorate(TextDecoration.BOLD))
                .append(Component.text(magentaPlayer.answeredCorrectly ? "Correcto" : "Incorrecto").color(TextColor.color(0xFFFFFF)))
                .append(Component.newline())
                .append(Component.text("Orange: ").color(TextColor.color(0xFFFFFF)).decorate(TextDecoration.BOLD))
                .append(Component.text(orangePlayer.answeredCorrectly ? "Correcto" : "Incorrecto").color(TextColor.color(0xFFFFFF)))
                .append(Component.newline())
                .append(Component.text("Gray: ").color(TextColor.color(0xFFFFFF)).decorate(TextDecoration.BOLD))
                .append(Component.text(grayPlayer.answeredCorrectly ? "Correcto" : "Incorrecto").color(TextColor.color(0xFFFFFF)))
                .append(Component.newline())
                .append(Component.text("Lime: ").color(TextColor.color(0xFFFFFF)).decorate(TextDecoration.BOLD))
                .append(Component.text(limePlayer.answeredCorrectly ? "Correcto" : "Incorrecto").color(TextColor.color(0xFFFFFF)))
                .append(Component.newline())
                .append(Component.text("LightBlue: ").color(TextColor.color(0xFFFFFF)).decorate(TextDecoration.BOLD))
                .append(Component.text(lightBluePlayer.answeredCorrectly ? "Correcto" : "Incorrecto").color(TextColor.color(0xFFFFFF)));

        lightGrayPlayer.answeredCorrectly = sendResponse(lightGrayPlayer.player, lightGrayPlayer.answeredCorrectly, lightGrayPlayer.cristales);
        yellowPlayer.answeredCorrectly = sendResponse(yellowPlayer.player, yellowPlayer.answeredCorrectly, yellowPlayer.cristales);
        pinkPlayer.answeredCorrectly = sendResponse(pinkPlayer.player, pinkPlayer.answeredCorrectly, pinkPlayer.cristales);
        magentaPlayer.answeredCorrectly = sendResponse(magentaPlayer.player, magentaPlayer.answeredCorrectly, magentaPlayer.cristales);
        orangePlayer.answeredCorrectly = sendResponse(orangePlayer.player, orangePlayer.answeredCorrectly, orangePlayer.cristales);
        grayPlayer.answeredCorrectly = sendResponse(grayPlayer.player, grayPlayer.answeredCorrectly, grayPlayer.cristales);
        limePlayer.answeredCorrectly = sendResponse(limePlayer.player, limePlayer.answeredCorrectly, limePlayer.cristales);
        lightBluePlayer.answeredCorrectly = sendResponse(lightBluePlayer.player, lightBluePlayer.answeredCorrectly, lightBluePlayer.cristales);
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        for (Player player : players) {
            if (!player.isOp()) {
                continue;
            }
            player.sendMessage(resultados);
        }
        currentQuestion++;
        timer.restartTimer(0, 1, TimerID.EUROCRISTALES_P2); // 20s
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
            player.sendMessage(Component.text("Respuesta correcta").color(TextColor.color(0xFF06)).decorate(TextDecoration.BOLD));
            if (!cristales.get(currentQuestion)) {
                player.sendMessage(derecha);
                player.showTitle(titleDerecha);
            }
            else {
                player.sendMessage(izquierda);

                player.showTitle(titleIzquierda);
            }
            Sound sound = Sound.sound(org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, Sound.Source.MASTER, 1F, 2F);
            player.playSound(sound);
        } else {
            player.sendMessage(Component.text("Respuesta incorrecta").color(TextColor.color(0xFF001D)).decorate(TextDecoration.BOLD));
            Title titleLose = Title.title(Component.empty(), Component.text()
                    .append(Component.text("a ").decorate(TextDecoration.OBFUSCATED).color(TextColor.color(0xFF001D)))
                    .append(Component.text("Respuesta incorrecta"))
                    .append(Component.text(" a").decorate(TextDecoration.OBFUSCATED).color(TextColor.color(0xFF001D))).build(),
                    Title.Times.times(Duration.ofSeconds(1), Duration.ofSeconds(3), Duration.ofMillis(500)));
            player.showTitle(titleLose);
            player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 20*5, 1));
            Sound sound = Sound.sound(org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, Sound.Source.MASTER, 1F, 0.5F);
            player.playSound(sound);
        }
        return false;
    }
    public void answerQuestion(Player player, Integer i) {
        if (player == lightGrayPlayer) {
            lightGrayPlayer.answeredCorrectly = i == 0;
            player.sendMessage("Respuesta enviada");
        } else if (player == yellowPlayer) {
            yellowPlayer.answeredCorrectly = i == 0;
            player.sendMessage("Respuesta enviada");
        } else if (player == pinkPlayer) {
            pinkPlayer.answeredCorrectly = i == 0;
            player.sendMessage("Respuesta enviada");
        } else if (player == magentaPlayer) {
            magentaPlayer.answeredCorrectly = i == 0;
            player.sendMessage("Respuesta enviada");
        } else if (player == orangePlayer) {
            orangePlayer.answeredCorrectly = i == 0;
            player.sendMessage("Respuesta enviada");
        } else if (player == grayPlayer) {
            grayPlayer.answeredCorrectly = i == 0;
            player.sendMessage("Respuesta enviada");
        } else if (player == limePlayer) {
            limePlayer.answeredCorrectly = i == 0;
            player.sendMessage("Respuesta enviada");
        } else if (player == lightBluePlayer) {
            lightBluePlayer.answeredCorrectly = i == 0;
            player.sendMessage("Respuesta enviada");
        }
        Sound sound = Sound.sound(org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, Sound.Source.MASTER, 1F, 1.06f);
        player.playSound(sound);
    }
}
