package de.ichwurdegeboren.commandblocker.listener;

import de.ichwurdegeboren.commandblocker.CommandBlockPlugin;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreprocessListener implements Listener {

    private final CommandBlockPlugin plugin;

    public PlayerCommandPreprocessListener(CommandBlockPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        System.out.println(event.getMessage());
        if (event.isCancelled() || event.getPlayer().hasPermission("commandblock.ignore")) return;
        if (plugin.getConfig().getStringList("BlockedCommands").stream().anyMatch(event.getMessage()::startsWith)) {
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("BlockedCommandUsedMessage")));
            event.setCancelled(true);
        }
    }

}
