package com.kenjietsu.ccr.commands.ccr;

import com.kenjietsu.ccr.eventManager.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.kenjietsu.ccr.eventManager.SpleefEvent.customSpleefEvent;
import static com.kenjietsu.ccr.eventManager.SpleefEvent.getSpleefEvent;


public class CCRCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                player.sendMessage("No hay argumentos");
                return true;
            }
            if (args[0].equals("balon")) {
                DodgeBallEvent dodgeBallEvent = DodgeBallEvent.getDodgeBallEvent();
                dodgeBallEvent.selectPlayers();
            }
            else if (args[0].equals("spleef")) {
                if (args.length == 3) {
                    customSpleefEvent(Bukkit.getPlayer(args[1]), Bukkit.getPlayer(args[2]));
                    return true;
                }
                if (args.length != 1) {
                    player.sendMessage("Faltan argumentos(o sobran)");
                    return true;
                }

                SpleefEvent spleefEvent = SpleefEvent.getSpleefEvent();
                spleefEvent.selectPlayers();
            }
            else if(args[0].equals("escondite")) {
                if (args.length != 2) {
                    player.sendMessage("Faltan argumentos(o sobran)");
                    return true;
                }
                EsconditeEvent esconditeEvent = EsconditeEvent.getEsconditeEvent();

                Player player1;
                try {
                    player1 = Bukkit.getPlayer(args[1]);
                } catch (Exception e) {
                    player.sendMessage("El jugador no existe");
                    return true;
                }

                esconditeEvent.startEvent(player1);

            }
            else if (args[0].equals("papafria")) {
                PapaFriaEvent papaFriaEvent = PapaFriaEvent.getPapaFriaEvent();
                papaFriaEvent.startEvent();
            }
            else if (args[0].equals("eventoFinal")) {
                EventoFinalEvent eventoFinal = EventoFinalEvent.getInstance();
                eventoFinal.startEvent();
            }


        }
        return true;
    }
}
