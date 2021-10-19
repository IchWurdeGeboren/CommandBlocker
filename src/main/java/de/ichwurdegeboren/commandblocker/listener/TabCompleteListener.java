package de.ichwurdegeboren.commandblocker.listener;

import de.ichwurdegeboren.commandblocker.CommandBlockPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;

public class TabCompleteListener implements Listener {

    private final CommandBlockPlugin plugin;

    public TabCompleteListener(CommandBlockPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onTabComplete(TabCompleteEvent event) {
        if (!(event.getSender() instanceof Player)) return;
        Player player = (Player) event.getSender();
        if (event.isCancelled() || player.hasPermission("commandblock.ignore")) return;
        if (plugin.getConfig().getStringList("BlockedCommands").stream().anyMatch(event.getBuffer()::startsWith))
            event.setCancelled(true);
    }

}
