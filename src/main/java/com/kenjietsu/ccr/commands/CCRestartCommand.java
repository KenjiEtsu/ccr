package com.kenjietsu.ccr.commands;

import com.kenjietsu.ccr.Ccr;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

import static com.kenjietsu.ccr.utils.MVCoreUtils.getMVWorld;

public class CCRestartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage("No hay argumentos");
            return true;
        }
        if (args[0].equals("balon")) {
            keyCancel(args[0]);

        } else if (args[0].equals("spleef")) {
            keyCancel(args[0]);
        }
        else  if (args[0].equals("eventoFinal")){

            MultiverseWorld world = getMVWorld("esplosion");
            for (Entity entity : world.getCBWorld().getEntities()) {
                if (entity instanceof Item) {
                    entity.remove();
                }
            }
        }

        TextComponent message = Component.text("El evento ha sido reiniciado");
        Bukkit.broadcast(message);
        return true;
    }
    public static void keyCancel(String keyn){
        for (Player player: Bukkit.getOnlinePlayers()) {
            NamespacedKey key = new NamespacedKey(Ccr.getPlugin(Ccr.class), keyn);
            PersistentDataContainer container = player.getPersistentDataContainer();
            container.remove(key);
        }
    }
}
