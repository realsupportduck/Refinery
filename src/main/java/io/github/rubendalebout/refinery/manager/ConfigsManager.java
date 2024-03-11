package io.github.rubendalebout.refinery.manager;

import io.github.rubendalebout.refinery.Refinery;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ConfigsManager {
    // This is the plug-in base, from here you can retrieve everything
    private final Refinery plugin;
    // This is a local list of all file configurations
    private final HashMap<String, FileConfiguration> fileConfigs = new HashMap<>();
    // This is a local list of all files
    private final HashMap<String, File> files = new HashMap<>();
    // This is the constructor of the class
    public ConfigsManager(Refinery plugin) {
        this.plugin = plugin;

        // Init new configuration files by name
        loadConfiguration("configuration");
    }
    // This function allows you to retrieve and load a configuration
    private void loadConfiguration(String name) {
        // Put the new file in the files list
        this.files.put(name, new File(plugin.getDataFolder() + File.separator, name + ".yml"));
        // Check if file exists
        if (!this.files.get(name).exists()) {
            // Create new config if not exists
            createConfigIfNotExists(name);
        }
        // Generate config from existing file
        this.fileConfigs.put(name, YamlConfiguration.loadConfiguration(this.files.get(name)));
    }

    public FileConfiguration getFileConfiguration(String name) {
        return this.fileConfigs.get(name);
    }

    public void reloadConfiguration(String name) {
        this.files.remove(name);
        this.fileConfigs.remove(name);
        this.loadConfiguration(name);
    }

    public void saveConfiguration(String name) throws IOException {
        this.fileConfigs.get(name).save(this.files.get(name));
    }

    private void createConfigIfNotExists(String name) {
        if (!this.files.get(name).exists()) {
            this.files.get(name).getParentFile().mkdirs();
            plugin.saveResource(name + ".yml", false);
        }
    }

    public String getString(String config, String path) {
        return (this.getFileConfiguration(config) != null) ? this.getFileConfiguration(config).getString(path) : null;
    }
}