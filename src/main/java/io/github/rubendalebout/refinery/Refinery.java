package io.github.rubendalebout.refinery;

import io.github.rubendalebout.refinery.builders.ColorBuilder;
import io.github.rubendalebout.refinery.events.inventoryclick.InventoryClick;
import io.github.rubendalebout.refinery.events.inventoryopen.InventoryOpen;
import io.github.rubendalebout.refinery.manager.CommandManager;
import io.github.rubendalebout.refinery.manager.ConfigsManager;
import io.github.rubendalebout.refinery.manager.EventsManager;
import io.github.rubendalebout.refinery.manager.RefineryManager;
import io.github.rubendalebout.refinery.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Refinery extends JavaPlugin {
    /*
     * *****************************************************************************
     *  Start of variables
     * *****************************************************************************
     */
    private static Refinery instance;
    /*
     * *****************************************************************************
     *  End of variables
     * *****************************************************************************
     */

    /*
     * *****************************************************************************
     *  Start of manager variables
     * *****************************************************************************
     */
    private ConfigsManager configsManager;
    private CommandManager commandManager;
    private EventsManager eventsManager;
    private RefineryManager refineryManager;
    /*
     * *****************************************************************************
     *  End of manager variables
     * *****************************************************************************
     */

    @Override
    public void onEnable() {
        // Load instance
        instance = this;

        // Send message if version is below 1.14
        if (!Bukkit.getServer().getVersion().contains("1.14") && !Bukkit.getServer().getVersion().contains("1.15") && !Bukkit.getServer().getVersion().contains("1.16") && !Bukkit.getServer().getVersion().contains("1.17") && !Bukkit.getServer().getVersion().contains("1.18") && !Bukkit.getServer().getVersion().contains("1.19") && !Bukkit.getServer().getVersion().contains("1.20")) {
            Bukkit.getServer().getConsoleSender().sendMessage(new ColorBuilder(new StringUtils().replaceAll("\n" +
                    "__________        _____.__                            \n" +
                    "\\______   \\ _____/ ____\\__| ____   ___________ ___.__.\n" +
                    " |       _// __ \\   __\\|  |/    \\_/ __ \\_  __ <   |  |\n" +
                    " |    |   \\  ___/|  |  |  |   |  \\  ___/|  | \\/\\___  |\n" +
                    " |____|_  /\\___  >__|  |__|___|  /\\___  >__|   / ____|\n" +
                    "        \\/     \\/              \\/     \\/       \\/     \n\n" +
                    String.format("&cMade with &4<3 &cBy &4%s &cSorry but we &4do not support &cyour server version!\n", String.join("&c, &4", this.getDescription().getAuthors())), "\n", "&7\n&4")).defaultPalette().build());

            Bukkit.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Load managers
        this.configsManager = new ConfigsManager(this);
        this.commandManager = new CommandManager(this);
        this.eventsManager = new EventsManager(this);
        this.refineryManager = new RefineryManager(this);

        // Add commands
        this.getCommandManager().addCommand(new io.github.rubendalebout.refinery.commands.refinery.Refinery(this));
        this.getCommandManager().register();

        // Add events
        this.getEventsManager().addEvent(new InventoryClick(this));
        this.getEventsManager().addEvent(new InventoryOpen(this));
        this.getEventsManager().register();

        // Plugin startup message
        Bukkit.getServer().getConsoleSender().sendMessage(new ColorBuilder(new StringUtils().replaceAll("\n" +
                "__________        _____.__                            \n" +
                "\\______   \\ _____/ ____\\__| ____   ___________ ___.__.\n" +
                " |       _// __ \\   __\\|  |/    \\_/ __ \\_  __ <   |  |\n" +
                " |    |   \\  ___/|  |  |  |   |  \\  ___/|  | \\/\\___  |\n" +
                " |____|_  /\\___  >__|  |__|___|  /\\___  >__|   / ____|\n" +
                "        \\/     \\/              \\/     \\/       \\/     \n\n" +
                String.format("&aMade with &4<3 &aBy &2%s &aVersion &2%s and Factory &21.1.5&7\n", String.join("&a, &2", this.getDescription().getAuthors()), this.getDescription().getVersion()), "\n", "&7\n&a")).defaultPalette().build());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for (Player player : Bukkit.getServer().getOnlinePlayers())
            if (this.getRefineryManager() != null && this.getRefineryManager().isMenu(player.getOpenInventory().getTopInventory()))
                player.closeInventory();

        Bukkit.getServer().getConsoleSender().sendMessage(new ColorBuilder(String.format("&cDisabled &4%s", this.getDescription().getName())).defaultPalette().build());
    }

    /*
     * *****************************************************************************
     *  Start of getters
     * *****************************************************************************
     */
    public static Refinery getInstance() {
        return instance;
    }
    /*
     * *****************************************************************************
     *  End of getters
     * *****************************************************************************
     */

    /*
     * *****************************************************************************
     *  Start of manager getters
     * *****************************************************************************
     */
    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    public ConfigsManager getConfigsManager() {
        return this.configsManager;
    }

    public EventsManager getEventsManager() {
        return this.eventsManager;
    }

    public RefineryManager getRefineryManager() {
        return this.refineryManager;
    }
    /*
     * *****************************************************************************
     *  End of manager getters
     * *****************************************************************************
     */
}
