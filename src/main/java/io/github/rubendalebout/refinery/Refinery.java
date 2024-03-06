package io.github.rubendalebout.refinery;

import io.github.rubendalebout.factory.builders.ColorBuilder;
import io.github.rubendalebout.factory.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Refinery extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getConsoleSender().sendMessage(new ColorBuilder(new StringUtils().replaceAll("\n" +
                "__________        _____.__                            \n" +
                "\\______   \\ _____/ ____\\__| ____   ___________ ___.__.\n" +
                " |       _// __ \\   __\\|  |/    \\_/ __ \\_  __ <   |  |\n" +
                " |    |   \\  ___/|  |  |  |   |  \\  ___/|  | \\/\\___  |\n" +
                " |____|_  /\\___  >__|  |__|___|  /\\___  >__|   / ____|\n" +
                "        \\/     \\/              \\/     \\/       \\/     \n\n" +
                String.format("&aMade with &4<3 &aBy &2%s &aVersion &2%s\n", String.join("&a, &2", this.getDescription().getAuthors()), this.getDescription().getVersion()), "\n", "&7\n&a")).defaultPalette().build());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
