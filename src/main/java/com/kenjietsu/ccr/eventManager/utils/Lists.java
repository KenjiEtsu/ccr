package com.kenjietsu.ccr.eventManager.utils;

import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static com.kenjietsu.ccr.utils.MVCoreUtils.getMVWorld;

public class Lists {

    public static @NotNull Map<Integer, String> getQuestionList() {
        Map<Integer, String> questions = new HashMap<>();

        // 4 opciones
        questions.put(1, "Cual es la estrella mas cercana a la tierra?");
        questions.put(2, "Cual es el pais mas grande del mundo?");
        questions.put(3, "Cual es el pais menos poblado del mundo?");
        questions.put(4, "Cual es el pais mas poblado del mundo?");
        questions.put(5, "Cuando acabo la primera guerra mundial?");
        questions.put(6, "Cuando acabo la segunda guerra mundial?");
        questions.put(7, "Cuantas veces ha estado el hombre en la luna?");
        // questions.add("¿A parte de la Casa Blanca, ?");
        questions.put(8, "Cual es el rio mas largo del mundo?");
        questions.put(9, "Cuantos huesos tiene el cuerpo humano?");
        questions.put(10, "Como se llama el proceso por el cual las plantas se alimentan?");
        questions.put(11, "Cuanto vale el numero pi?(truncando)");
        questions.put(12, "Quien pinto La ultima cena?");



        return questions;
    }
    public static @NotNull List<String> getResponses(Integer i) {
        List<String> responses = new ArrayList<>();
        switch (i) {
            case (1) -> {
                responses.add("Sol");
                responses.add("Sirio");
                responses.add("Alfa Centauri");
                responses.add("Proxima Centauri");
            }
            case (2) -> {
                responses.add("Rusia");
                responses.add("Canada");
                responses.add("China");
                responses.add("Estados Unidos");
            }
            case (3) -> {
                responses.add("Vaticano");
                responses.add("Peru");
                responses.add("Andorra");
                responses.add("San Marino");
            }
            case (4) -> {
                responses.add("India");
                responses.add("China");
                responses.add("Estados Unidos");
                responses.add("Indonesia");
            }
            case (5) -> {
                responses.add("1918");
                responses.add("1919");
                responses.add("1920");
                responses.add("1921");
            }
            case (6) -> {
                responses.add("1945");
                responses.add("1946");
                responses.add("1947");
                responses.add("1948");
            }
            case (7) -> {
                responses.add("6");
                responses.add("1");
                responses.add("12");
                responses.add("3");
            }
            case (8) -> {
                responses.add("Amazonas");
                responses.add("Nilo");
                responses.add("Yangtse");
                responses.add("Misisipi");
            }
            case (9) -> {
                responses.add("206");
                responses.add("300");
                responses.add("196");
                responses.add("200");
            }
            case (10) -> {
                responses.add("Fotosintesis");
                responses.add("Mitosis");
                responses.add("Meiosis");
                responses.add("Metamorfosis");
            }
            case (11) -> {
                responses.add("3,1415");
                responses.add("3,1416");
                responses.add("3,1414");
                responses.add("3,1413");
            }
            case (12) -> {
                responses.add("Leonardo Da Vinci");
                responses.add("Miguel Angel");
                responses.add("Rafael");
                responses.add("Donatello");
            }
            default -> {
                responses.add("Error");
                responses.add("Error");
                responses.add("Error");
                responses.add("Error");
            }
        }
        return responses;


    }

    public static @NotNull TextComponent getEurocristalesGameInfo() {
        return Component.text()
                .append(Component.text("         EuroCristales ").color(TextColor.color(0xFF0027)))
                .append(Component.newline())
                .append(Component.newline())
                .append(Component.text("Responde las preguntas dando click en el chat a la respuesta que crees la correcta.").color(TextColor.color(0xFFFFFF)))
                .append(Component.newline())
                .append(Component.newline())
                .append(Component.text("Si no aciertas, tendras que probar tu suerte :))))").color(TextColor.color(0xFFFFFF)))
                .append(Component.newline())
                .append(Component.newline())
                .append(Component.text("Tienes 5 segundos para responder cada pregunta").color(TextColor.color(0xFFFFFF)))
                .append(Component.newline())
                .append(Component.newline())
                .append(Component.text("Mucha Suerte, la necesitais :)").color(TextColor.color(0xFFFFFF)))
                .build();
    }

    public static @NotNull TextComponent getComponent(String question, List<String> responses) {
        if (responses.size() != 4) {
            Bukkit.getLogger().warning("Error en la lista de respuestas");
            return Component.text("Error");
        }
        if ( question == null) {
            Bukkit.getLogger().warning("Error en la pregunta");
            return Component.text("Error");
        }
        List<Integer> numbers = new ArrayList<>(List.of(0, 1, 2, 3));
        Collections.shuffle(numbers);
        return Component.text("\n\n\n\n____________________________")
                .color(TextColor.color(0xFF0027))
                .append(Component.text(" \n\n"))
                .append(Component.text(question).color(TextColor.color(0x00FFFF)).decorate(TextDecoration.BOLD))
                .append(Component.text(" \n\n\n"))
                .append(Component.text("[A:"+ responses.get(numbers.get(0)) +"]  ").color(TextColor.color(0xFFFFFF)).clickEvent(ClickEvent.runCommand("/ccranswer " + numbers.get(0))))
                .append(Component.text("[B:"+ responses.get(numbers.get(1)) +"]  ").color(TextColor.color(0xFFFFFF)).clickEvent(ClickEvent.runCommand("/ccranswer " + numbers.get(1))))
                .append(Component.text("[C:"+ responses.get(numbers.get(2)) +"]  ").color(TextColor.color(0xFFFFFF)).clickEvent(ClickEvent.runCommand("/ccranswer " + numbers.get(2))))
                .append(Component.text("[D:"+ responses.get(numbers.get(3)) +"]  ").color(TextColor.color(0xFFFFFF)).clickEvent(ClickEvent.runCommand("/ccranswer " + numbers.get(3))))
                .append(Component.text(" \n"))
                .append(Component.text("____________________________\n\n\n\n").color(TextColor.color(0xFF0027)));
    }

    public static @NotNull List<Location> getLightGrayEuroLocations(){
        List<Location> locations = new ArrayList<>();
        MultiverseWorld world = getMVWorld("voidd");
        locations.add(new Location(world.getCBWorld(), -834, 105, 382));
        locations.add(new Location(world.getCBWorld(), -839, 105, 382));

        locations.add(new Location(world.getCBWorld(), -834, 105, 392));
        locations.add(new Location(world.getCBWorld(), -839, 105, 392));

        locations.add(new Location(world.getCBWorld(), -834, 105, 402));
        locations.add(new Location(world.getCBWorld(), -839, 105, 402));

        locations.add(new Location(world.getCBWorld(), -834, 105, 412));
        locations.add(new Location(world.getCBWorld(), -839, 105, 412));

        locations.add(new Location(world.getCBWorld(), -834, 105, 422));
        locations.add(new Location(world.getCBWorld(), -839, 105, 422));

        locations.add(new Location(world.getCBWorld(), -834, 105, 432));
        locations.add(new Location(world.getCBWorld(), -839, 105, 432));

        return locations;
    }
    public static @NotNull List<Location> getYellowEuroLocations() {
        List<Location> locations = new ArrayList<>();
        MultiverseWorld world = getMVWorld("voidd");
        locations.add(new Location(world.getCBWorld(), -834, 105, 524));
        locations.add(new Location(world.getCBWorld(), -839, 105, 524));

        locations.add(new Location(world.getCBWorld(), -834, 105, 534));
        locations.add(new Location(world.getCBWorld(), -839, 105, 534));

        locations.add(new Location(world.getCBWorld(), -834, 105, 544));
        locations.add(new Location(world.getCBWorld(), -839, 105, 544));

        locations.add(new Location(world.getCBWorld(), -834, 105, 554));
        locations.add(new Location(world.getCBWorld(), -839, 105, 554));

        locations.add(new Location(world.getCBWorld(), -834, 105, 564));
        locations.add(new Location(world.getCBWorld(), -839, 105, 564));

        locations.add(new Location(world.getCBWorld(), -834, 105, 574));
        locations.add(new Location(world.getCBWorld(), -839, 105, 574));

        return locations;
    }
    public static @NotNull List<Location> getPinkEuroLocations() {
        List<Location> locations = new ArrayList<>();
        MultiverseWorld world = getMVWorld("voidd");

        locations.add(new Location(world.getCBWorld(), -937, 105, 478));
        locations.add(new Location(world.getCBWorld(), -937, 105, 483));

        locations.add(new Location(world.getCBWorld(), -927, 105, 478));
        locations.add(new Location(world.getCBWorld(), -927, 105, 483));

        locations.add(new Location(world.getCBWorld(), -917, 105, 478));
        locations.add(new Location(world.getCBWorld(), -917, 105, 483));

        locations.add(new Location(world.getCBWorld(), -907, 105, 478));
        locations.add(new Location(world.getCBWorld(), -907, 105, 483));

        locations.add(new Location(world.getCBWorld(), -897, 105, 478));
        locations.add(new Location(world.getCBWorld(), -897, 105, 483));

        locations.add(new Location(world.getCBWorld(), -887, 105, 478));
        locations.add(new Location(world.getCBWorld(), -887, 105, 483));

        return locations;
    }
    public static @NotNull List<Location> getMagentaEuroLocations() {
        List<Location> locations = new ArrayList<>();
        MultiverseWorld world = getMVWorld("voidd");

        locations.add(new Location(world.getCBWorld(), -795, 105, 478));
        locations.add(new Location(world.getCBWorld(), -795, 105, 483));

        locations.add(new Location(world.getCBWorld(), -785, 105, 478));
        locations.add(new Location(world.getCBWorld(), -785, 105, 483));

        locations.add(new Location(world.getCBWorld(), -775, 105, 478));
        locations.add(new Location(world.getCBWorld(), -775, 105, 483));

        locations.add(new Location(world.getCBWorld(), -765, 105, 478));
        locations.add(new Location(world.getCBWorld(), -765, 105, 483));

        locations.add(new Location(world.getCBWorld(), -755, 105, 478));
        locations.add(new Location(world.getCBWorld(), -755, 105, 483));

        locations.add(new Location(world.getCBWorld(), -745, 105, 478));
        locations.add(new Location(world.getCBWorld(), -745, 105, 483));

        return locations;
    }
    public static @NotNull List<Location> getOrangeEuroLocations() {
        List<Location> locations = new ArrayList<>();
        MultiverseWorld world = getMVWorld("voidd");

        locations.add(new Location(world.getCBWorld(), -764, 105, 413));
        locations.add(new Location(world.getCBWorld(), -768, 105, 409));

        locations.add(new Location(world.getCBWorld(), -772, 105, 421));
        locations.add(new Location(world.getCBWorld(), -776, 105, 417));

        locations.add(new Location(world.getCBWorld(), -780, 105, 429));
        locations.add(new Location(world.getCBWorld(), -784, 105, 425));

        locations.add(new Location(world.getCBWorld(), -788, 105, 437));
        locations.add(new Location(world.getCBWorld(), -792, 105, 433));

        locations.add(new Location(world.getCBWorld(), -796, 105, 445));
        locations.add(new Location(world.getCBWorld(), -800, 105, 441));

        locations.add(new Location(world.getCBWorld(), -804, 105, 453));
        locations.add(new Location(world.getCBWorld(), -808, 105, 449));

        return locations;
    }
    public static @NotNull List<Location> getGrayEuroLocations() {
        ArrayList<Location> locations = new ArrayList<>();
        MultiverseWorld world = getMVWorld("voidd");

        locations.add(new Location(world.getCBWorld(), -906, 105, 408));
        locations.add(new Location(world.getCBWorld(), -910, 105, 412));

        locations.add(new Location(world.getCBWorld(), -898, 105, 416));
        locations.add(new Location(world.getCBWorld(), -902, 105, 420));

        locations.add(new Location(world.getCBWorld(), -890, 105, 424));
        locations.add(new Location(world.getCBWorld(), -894, 105, 428));

        locations.add(new Location(world.getCBWorld(), -882, 105, 432));
        locations.add(new Location(world.getCBWorld(), -886, 105, 436));

        locations.add(new Location(world.getCBWorld(), -874, 105, 440));
        locations.add(new Location(world.getCBWorld(), -878, 105, 444));

        locations.add(new Location(world.getCBWorld(), -866, 105, 448));
        locations.add(new Location(world.getCBWorld(), -870, 105, 452));

        return locations;
    }
    public static @NotNull List<Location> getLimeEuroLocations() {
        List<Location> locations = new ArrayList<>();
        MultiverseWorld world = getMVWorld("voidd");

        locations.add(new Location(world.getCBWorld(), -911, 105, 550));
        locations.add(new Location(world.getCBWorld(), -907, 105, 554));

        locations.add(new Location(world.getCBWorld(), -903, 105, 542));
        locations.add(new Location(world.getCBWorld(), -899, 105, 546));

        locations.add(new Location(world.getCBWorld(), -895, 105, 534));
        locations.add(new Location(world.getCBWorld(), -891, 105, 538));

        locations.add(new Location(world.getCBWorld(), -887, 105, 526));
        locations.add(new Location(world.getCBWorld(), -883, 105, 530));

        locations.add(new Location(world.getCBWorld(), -879, 105, 518));
        locations.add(new Location(world.getCBWorld(), -875, 105, 522));

        locations.add(new Location(world.getCBWorld(), -871, 105, 510));
        locations.add(new Location(world.getCBWorld(), -867, 105, 514));

        return locations;
    }
    public static @NotNull List<Location> getLightBlueLocations() {
        List<Location> locations = new ArrayList<>();
        MultiverseWorld world = getMVWorld("voidd");

        locations.add(new Location(world.getCBWorld(), -769, 105, 555));
        locations.add(new Location(world.getCBWorld(), -765, 105, 551));

        locations.add(new Location(world.getCBWorld(), -777, 105, 547));
        locations.add(new Location(world.getCBWorld(), -773, 105, 543));

        locations.add(new Location(world.getCBWorld(), -785, 105, 539));
        locations.add(new Location(world.getCBWorld(), -781, 105, 535));

        locations.add(new Location(world.getCBWorld(), -793, 105, 531));
        locations.add(new Location(world.getCBWorld(), -789, 105, 527));

        locations.add(new Location(world.getCBWorld(), -801, 105, 523));
        locations.add(new Location(world.getCBWorld(), -797, 105, 519));

        locations.add(new Location(world.getCBWorld(), -809, 105, 515));
        locations.add(new Location(world.getCBWorld(), -805, 105, 511));

        return locations;
    }

    public static @NotNull List<List<Location>> getGallinitaFinalHints() {
        List<List<Location>> locations = new ArrayList<>();
        MultiverseWorld world = getMVWorld("MidnightCastle");
        List<Location> locations1 = new ArrayList<>();
        locations1.add(new Location(world.getCBWorld(), 8.5, 99.5, 48.5));
        locations1.add(new Location(world.getCBWorld(), 11.5, 99.5, 47.5));
        locations1.add(new Location(world.getCBWorld(), 13.5, 99.5, 45.5));
        locations1.add(new Location(world.getCBWorld(), 15.5, 100.5, 42.5));
        locations1.add(new Location(world.getCBWorld(), 16.5, 100.5, 39.5));
        locations1.add(new Location(world.getCBWorld(), 17.5, 100.5, 35.5));
        locations1.add(new Location(world.getCBWorld(), 18.5, 100.5, 31.5));
        locations1.add(new Location(world.getCBWorld(), 19.5, 100.5, 27.5));
        locations1.add(new Location(world.getCBWorld(), 19.5, 100.5, 23.5));
        locations1.add(new Location(world.getCBWorld(), 19.5, 100.5, 19.5));
        locations1.add(new Location(world.getCBWorld(), 21.5, 100.5, 17.5));
        locations1.add(new Location(world.getCBWorld(), 23.5, 100.5, 20.5));
        locations.add(locations1);
        List<Location> locations2 = new ArrayList<>();
        locations2.add(new Location(world.getCBWorld(), 8.5, 101.5, 33.5));
        locations2.add(new Location(world.getCBWorld(), 11.5, 101.5, 33.5));
        locations2.add(new Location(world.getCBWorld(), 13.5, 100.5, 30.5));
        locations2.add(new Location(world.getCBWorld(), 15.5, 100.5, 27.5));
        locations2.add(new Location(world.getCBWorld(), 17.5, 100.5, 25.5));
        locations2.add(new Location(world.getCBWorld(), 19.5, 100.5, 23.5));
        locations2.add(new Location(world.getCBWorld(), 19.5, 100.5,  19.5));
        locations2.add(new Location(world.getCBWorld(), 21.5, 100.5, 17.5));
        locations2.add(new Location(world.getCBWorld(), 23.5, 100.5, 20.5));
        locations.add(locations2);
        List<Location> locations3 = new ArrayList<>();
        locations3.add(new Location(world.getCBWorld(), 8.5, 99.5, 20.5));
        locations3.add(new Location(world.getCBWorld(), 12.5, 100.5, 20.5));
        locations3.add(new Location(world.getCBWorld(), 16.5, 100.5, 20.5));
        locations3.add(new Location(world.getCBWorld(), 19.5, 100.5, 19.5));
        locations3.add(new Location(world.getCBWorld(), 21.5, 100.5, 17.5));
        locations3.add(new Location(world.getCBWorld(), 23.5, 100.5, 20.5));
        locations.add(locations3);
        List<Location> locations4 = new ArrayList<>();
        locations4.add(new Location(world.getCBWorld(), 10.5, 99.5, 5.5));
        locations4.add(new Location(world.getCBWorld(), 13.5, 99.5, 8.5));
        locations4.add(new Location(world.getCBWorld(), 15.5, 99.5, 10.5));
        locations4.add(new Location(world.getCBWorld(), 17.5, 99.5, 13.5));
        locations4.add(new Location(world.getCBWorld(), 19.5, 99.5, 15.5));
        locations4.add(new Location(world.getCBWorld(), 21.5, 99.5, 17.5));
        locations4.add(new Location(world.getCBWorld(), 23.5, 100.5, 20.5));
        locations.add(locations4);
        List<Location> locations5 = new ArrayList<>();
        locations5.add(new Location(world.getCBWorld(), 8.5, 99.5, -10.5));
        locations5.add(new Location(world.getCBWorld(), 11.5, 99.5, -9.5));
        locations5.add(new Location(world.getCBWorld(), 13.5, 99.5, -6.5));
        locations5.add(new Location(world.getCBWorld(), 15.5, 99.5, -3.5));
        locations5.add(new Location(world.getCBWorld(), 16.5, 99.5, 0.5));
        locations5.add(new Location(world.getCBWorld(), 17.5, 99.5, 3.5));
        locations5.add(new Location(world.getCBWorld(), 18.5, 99.5, 6.5));
        locations5.add(new Location(world.getCBWorld(), 19.5, 99.5, 8.5));
        locations5.add(new Location(world.getCBWorld(), 19.5, 100.5, 11.5));
        locations5.add(new Location(world.getCBWorld(), 19.5, 100.5, 15.5));
        locations5.add(new Location(world.getCBWorld(), 21.5, 100.5, 17.5));
        locations5.add(new Location(world.getCBWorld(), 23.5, 100.5, 20.5));
        locations.add(locations5);
        List<Location> locations6 = new ArrayList<>();
        return locations;
    }
    public static @NotNull List<Location> getFinalLocations() {
        List<Location> locations = new ArrayList<>();
        MultiverseWorld world = getMVWorld("esplosion");
        locations.add(new Location(world.getCBWorld(), -250.2, 28.1, -704.5));
        locations.add(new Location(world.getCBWorld(), -249.3, 10, -679));
        locations.add(new Location(world.getCBWorld(), -268.66, 3, -639.1));
        locations.add(new Location(world.getCBWorld(), -215.7, 9, -633.34));
        locations.add(new Location(world.getCBWorld(), -235.7, 7, -756.0));
        locations.add(new Location(world.getCBWorld(), -236.6, 27, -725.4));
        locations.add(new Location(world.getCBWorld(), -212.3, 9, -705.44));
        locations.add(new Location(world.getCBWorld(), -211.66, 29, -680.59));
        locations.add(new Location(world.getCBWorld(), -196.7, 37, -868.16));
        locations.add(new Location(world.getCBWorld(), -197.8, 12, -782.781));
        locations.add(new Location(world.getCBWorld(), -202.5, 24, -747.73));
        locations.add(new Location(world.getCBWorld(), -293.5, 34, -766.5));
        locations.add(new Location(world.getCBWorld(), -177.8, 14, -857.57));
        locations.add(new Location(world.getCBWorld(), -179.5, 39, -811.4));
        locations.add(new Location(world.getCBWorld(), -153.4, 3, -639.26));
        locations.add(new Location(world.getCBWorld(), -159.6, 28.6, -639.52));
        locations.add(new Location(world.getCBWorld(), -169.8, 29, -908.54));
        locations.add(new Location(world.getCBWorld(), -172.5, 17, -895.17));
        locations.add(new Location(world.getCBWorld(), -166.3, 50, -820.66));
        locations.add(new Location(world.getCBWorld(), -170.5, 54, -784.36));
        locations.add(new Location(world.getCBWorld(), -133.3, 39, -639.65));
        locations.add(new Location(world.getCBWorld(), -149.6, 8, -753.48));
        locations.add(new Location(world.getCBWorld(), -157.45, 4, -726.84));
        locations.add(new Location(world.getCBWorld(), -144.47, 6.1, -692.5));
        locations.add(new Location(world.getCBWorld(), -145.7, 60, -694.7));
        locations.add(new Location(world.getCBWorld(), -147.87, 3, -670.33));
        locations.add(new Location(world.getCBWorld(), -119.68, 10, -630.5));
        locations.add(new Location(world.getCBWorld(), -125.85, 94, -639.45));
        locations.add(new Location(world.getCBWorld(), -136.5, 12, -834.52));
        locations.add(new Location(world.getCBWorld(), -138.1, 25, -836.5));
        locations.add(new Location(world.getCBWorld(), -131.4, 3, -666.14));
        locations.add(new Location(world.getCBWorld(), -141.3, 32.6, -667.6));
        locations.add(new Location(world.getCBWorld(), -104.63, 4, -639.8));
        locations.add(new Location(world.getCBWorld(), -104.7, 64.1, -624.7));
        locations.add(new Location(world.getCBWorld(), -132.6, 25.1, -680.46));
        locations.add(new Location(world.getCBWorld(), -131.1, 45, -686.1));
        locations.add(new Location(world.getCBWorld(), -141.4, 60, -673.1));
        locations.add(new Location(world.getCBWorld(), -122.3, 41, -935.87));
        locations.add(new Location(world.getCBWorld(), -123.66, 39, -685.31));
        locations.add(new Location(world.getCBWorld(), -118.6, 119.5, -672.35));
        locations.add(new Location(world.getCBWorld(), -89.2, 10, -629.5));
        locations.add(new Location(world.getCBWorld(), -82.5, 94, -639.5));
        locations.add(new Location(world.getCBWorld(), -104.5, 73, -942.6));
        locations.add(new Location(world.getCBWorld(), -104.3, 72, -927.2));
        locations.add(new Location(world.getCBWorld(), -99.4, 43, -837.8));
        locations.add(new Location(world.getCBWorld(), -104.4, 8, -792.7));
        locations.add(new Location(world.getCBWorld(), -104.5, 0.5, -761.4));
        locations.add(new Location(world.getCBWorld(), -100.5, 14, -769.5));
        locations.add(new Location(world.getCBWorld(), -108.5, 14, -769.5));
        locations.add(new Location(world.getCBWorld(), -74.5, 39.1, -639.77));
        locations.add(new Location(world.getCBWorld(), -87.7, 42, -936.9));
        locations.add(new Location(world.getCBWorld(), -85.6, 39, -685.6));
        locations.add(new Location(world.getCBWorld(), -91, 75, -679));
        locations.add(new Location(world.getCBWorld(), -55.3, 3, -639.7));
        locations.add(new Location(world.getCBWorld(), -55.5, 64.1, -624.5));
        locations.add(new Location(world.getCBWorld(), -49.5, 28.5, -639.6));
        locations.add(new Location(world.getCBWorld(), -77.5, 30, -850.6));
        locations.add(new Location(world.getCBWorld(), -64.5, 6.1, -692.6));
        locations.add(new Location(world.getCBWorld(), -67.5, 59, -694.7));
        locations.add(new Location(world.getCBWorld(), -77.3, 3, -670.7));
        locations.add(new Location(world.getCBWorld(), -67.6, 34.5, -667.14));
        locations.add(new Location(world.getCBWorld(), -76.3, 25.1, -681.3));
        locations.add(new Location(world.getCBWorld(), -77.3, 45, -686.7));
        locations.add(new Location(world.getCBWorld(), -67.5, 60, -672.8));
        locations.add(new Location(world.getCBWorld(), -51.5, 17, -817.6));
        locations.add(new Location(world.getCBWorld(), -54.7, 87, -933.3));
        locations.add(new Location(world.getCBWorld(), -54.5, 119.5, -672.5));
        locations.add(new Location(world.getCBWorld(), -59.2, 8, -753.3));
        locations.add(new Location(world.getCBWorld(), -56.5, 34, -774.5));
        locations.add(new Location(world.getCBWorld(), -39.5, 29, -908.8));
        locations.add(new Location(world.getCBWorld(), -28.5, 10, -854.5));
        locations.add(new Location(world.getCBWorld(), -26.5, 6, -762.1));
        locations.add(new Location(world.getCBWorld(), -24.3, 9, -687.4));
        locations.add(new Location(world.getCBWorld(), -4.1, 9, -828.5));
        locations.add(new Location(world.getCBWorld(), -5.3, 22, -730.3));
        locations.add(new Location(world.getCBWorld(), 10.2, 12, -750.3));
        locations.add(new Location(world.getCBWorld(), 22.5, 3, -775.6));
        locations.add(new Location(world.getCBWorld(), 34.8, 38, -682.3));
        locations.add(new Location(world.getCBWorld(), 37.7, 38, -679.3));
        locations.add(new Location(world.getCBWorld(), 37.7, 41, -683.5));
        locations.add(new Location(world.getCBWorld(), -154.7, 92, -965.6));
        locations.add(new Location(world.getCBWorld(), -140.6, 75, -640.5));
        locations.add(new Location(world.getCBWorld(), -136.5, 73, -650.5));
        locations.add(new Location(world.getCBWorld(), -128.3, 75, -652.5));
        locations.add(new Location(world.getCBWorld(), -104.5, 64.1, -654.3));
        locations.add(new Location(world.getCBWorld(), -68.7, 79, -655.4));
        locations.add(new Location(world.getCBWorld(), -71.6, 119, -653.5));
        locations.add(new Location(world.getCBWorld(), -54.3, 92, -965.5));
        locations.add(new Location(world.getCBWorld(), 19.3, 39, -655.5));
        locations.add(new Location(world.getCBWorld(), 23.2, 33, -619.5));
        locations.add(new Location(world.getCBWorld(), 43.8, 31, -602.6));
        locations.add(new Location(world.getCBWorld(), -30.6, 23, -620.7));
        locations.add(new Location(world.getCBWorld(), -9, 10, -604.1));
        locations.add(new Location(world.getCBWorld(), -29.99, 54, -592.5));
        locations.add(new Location(world.getCBWorld(), 84, 27, -590.3));
        locations.add(new Location(world.getCBWorld(), -58.3, 26, -596.3));
        locations.add(new Location(world.getCBWorld(), -75.5, 45, -599.6));
        locations.add(new Location(world.getCBWorld(), -56.7, 3, -586.4));
        locations.add(new Location(world.getCBWorld(), -89.56, 25, -596.1));
        locations.add(new Location(world.getCBWorld(), -91.9, 68, -599));
        locations.add(new Location(world.getCBWorld(), -90.4, 119.5, -606.4));
        locations.add(new Location(world.getCBWorld(), -64.4, 6.1, -586.5));
        locations.add(new Location(world.getCBWorld(), -53.5, 25, -575.5));
        locations.add(new Location(world.getCBWorld(), 47.7, 21, -499.25));
        locations.add(new Location(world.getCBWorld(), -53.3, 8, -547.3));
        locations.add(new Location(world.getCBWorld(), 73.8, 18, -518.5));
        locations.add(new Location(world.getCBWorld(), 100, 8, -554.1));
        locations.add(new Location(world.getCBWorld(), -120.4, 25, -596.2));
        locations.add(new Location(world.getCBWorld(), -116.5, 78, -598.8));
        locations.add(new Location(world.getCBWorld(), -123.7, 78, -601.4));
        locations.add(new Location(world.getCBWorld(), -68.6, 51, -557));
        locations.add(new Location(world.getCBWorld(), -133.3, 45, -599.5));
        locations.add(new Location(world.getCBWorld(), -123.4, 10.1, -586.84));
        locations.add(new Location(world.getCBWorld(), -103.7, 59, -565.5));
        locations.add(new Location(world.getCBWorld(), -103.7, 119.5, -606.5));
        locations.add(new Location(world.getCBWorld(), -73.4, 21, -519.7));
        locations.add(new Location(world.getCBWorld(), -123.7, 5, -540.4));
        locations.add(new Location(world.getCBWorld(), -126.2, 49, -534.7));
        locations.add(new Location(world.getCBWorld(), -69.7, 31, -461));
        locations.add(new Location(world.getCBWorld(), -136.2, 9, -472.7));
        locations.add(new Location(world.getCBWorld(), -145.8, 3, -609.4));
        locations.add(new Location(world.getCBWorld(), -151.7, 26, -597.3));
        locations.add(new Location(world.getCBWorld(), -144.5, 6.1, -586.5));
        locations.add(new Location(world.getCBWorld(), -152.5, 3, -585.5));
        locations.add(new Location(world.getCBWorld(), -171.5, 147, -582.4));
        locations.add(new Location(world.getCBWorld(), -155.5, 25, -575.5));
        locations.add(new Location(world.getCBWorld(), -147.4, 115, -555.4));
        locations.add(new Location(world.getCBWorld(), -178.7, 23, -620.7));
        locations.add(new Location(world.getCBWorld(), -187.6, 61, -600.1));
        locations.add(new Location(world.getCBWorld(), -181.3, 74, -583.8));
        locations.add(new Location(world.getCBWorld(), -204.2, 12, -595.4));
        locations.add(new Location(world.getCBWorld(), -230.9, 51, -607.7));
        locations.add(new Location(world.getCBWorld(), -185.1, 69.8, -525.3));
        locations.add(new Location(world.getCBWorld(), -172.5, 74.9, -498.4));
        locations.add(new Location(world.getCBWorld(), -347.7, 75, -553.3));
        locations.add(new Location(world.getCBWorld(), -278.7, 32, -579.3));
        locations.add(new Location(world.getCBWorld(), -245.5, 30, -559.3));
        locations.add(new Location(world.getCBWorld(), -297.1, 16, -605.3));
        locations.add(new Location(world.getCBWorld(), -218.1, 104, -497.5));
        locations.add(new Location(world.getCBWorld(), -316.8, 4, -623.6));
        locations.add(new Location(world.getCBWorld(), -309.7, 9, -576.7));
        locations.add(new Location(world.getCBWorld(), -250.5, 28, -518.5));
        locations.add(new Location(world.getCBWorld(), -189.5, 31, -453.4));
        locations.add(new Location(world.getCBWorld(), -151.3, 4, -420.4));
        locations.add(new Location(world.getCBWorld(), -311.3, 7, -575.3));
        locations.add(new Location(world.getCBWorld(), -302.3, 5, -648.3));
        locations.add(new Location(world.getCBWorld(), -305.3, 9, -545.3));
        locations.add(new Location(world.getCBWorld(), -297.9, 20, -530.9));
        locations.add(new Location(world.getCBWorld(), -251.7, 44, -474.1));
        locations.add(new Location(world.getCBWorld(), -257.1, 37, -450.5));
        locations.add(new Location(world.getCBWorld(), -252.5, 37, -445.5));
        locations.add(new Location(world.getCBWorld(), -257.4, 37, -440.3));
        locations.add(new Location(world.getCBWorld(), -262.5, 37, -445.5));
        locations.add(new Location(world.getCBWorld(), -304, 7, -472.2));
        locations.add(new Location(world.getCBWorld(), -304.5, 7, -477.5));
        locations.add(new Location(world.getCBWorld(), -293.6, 3, -399.5));
        locations.add(new Location(world.getCBWorld(), -293.5, 3, -404.5));
        return locations;
    }

    public static @NotNull Location getFinalCenterLocation() {
        MultiverseWorld world = getMVWorld("esplosion");
        return new Location(world.getCBWorld(), -104.5, 51, -639.5);
    }
}
