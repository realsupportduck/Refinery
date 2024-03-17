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
    private static Refinery instance;
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
        instance = this;

        this.configsManager = new ConfigsManager(this);
        this.commandManager = new CommandManager(this);
        this.eventsManager = new EventsManager(this);

        this.refineryManager = new RefineryManager(this);

        // Add commands
        this.commandManager.addCommand(new io.github.rubendalebout.refinery.commands.refinery.Refinery(this));
        this.commandManager.register();

        // Add events
        this.eventsManager.addEvent(new InventoryClick(this));
        this.eventsManager.addEvent(new InventoryOpen(this));
        this.eventsManager.register();

        // Plugin startup logic
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
    }

    public static Refinery getInstance() {
        return instance;
    }

    public ConfigsManager getConfigsManager() {
        return this.configsManager;
    }

    public RefineryManager getRefineryManager() {
        return this.refineryManager;
    }
}
