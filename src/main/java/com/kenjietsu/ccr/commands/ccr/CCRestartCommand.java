package com.kenjietsu.ccr.commands.ccr;

import com.kenjietsu.ccr.Ccr;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import static com.kenjietsu.ccr.eventManager.utils.LocationList.getLocations;

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
            MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
            MVWorldManager worldManager = core.getMVWorldManager();
            MultiverseWorld world = worldManager.getMVWorld("esplosion");
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
    private static void keyCancel(String keyn){
        for (Player player: Bukkit.getOnlinePlayers()) {
            NamespacedKey key = new NamespacedKey(Ccr.getPlugin(Ccr.class), keyn);
            PersistentDataContainer container = player.getPersistentDataContainer();
            container.remove(key);
        }
    }
}
