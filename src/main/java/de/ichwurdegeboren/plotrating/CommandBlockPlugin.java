package de.ichwurdegeboren.plotrating;

import de.ichwurdegeboren.plotrating.listener.PlayerCommandPreprocessListener;
import de.ichwurdegeboren.plotrating.listener.TabCompleteListener;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;

public class CommandBlockPlugin extends JavaPlugin {

    private File configFile;
    private FileConfiguration fileConfiguration;

    @Override
    public void onEnable() {
        initConfig();

        getServer().getPluginManager().registerEvents(new PlayerCommandPreprocessListener(this), this);
        getServer().getPluginManager().registerEvents(new TabCompleteListener(this), this);
    }

    @SneakyThrows
    private void initConfig() {
        this.configFile = new File(getDataFolder(), "config.yml");
        this.fileConfiguration = YamlConfiguration.loadConfiguration(configFile);

        fileConfiguration.addDefault("BlockedCommandUsedMessage", "&cDieser Befehl wurde blockiert.");
        fileConfiguration.addDefault("BlockedCommands", Arrays.asList("/plugins", "/pl", "/about", "/bukkit", "/version", "/help", "/?"));
        fileConfiguration.options().copyDefaults(true);
        fileConfiguration.save(configFile);
    }
}
