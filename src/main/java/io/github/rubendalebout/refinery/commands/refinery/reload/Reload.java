package io.github.rubendalebout.refinery.commands.refinery.reload;

import io.github.rubendalebout.refinery.Refinery;
import io.github.rubendalebout.refinery.builders.ColorBuilder;
import io.github.rubendalebout.refinery.commands.RCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Reload extends RCommand {
    private final Refinery plugin;

    public Reload(Refinery plugin) {
        this.plugin = plugin;
    }

    @Override
    public String name() {
        return "reload";
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission(String.format("refinery.%s", name()))) {
            commandSender.sendMessage(new ColorBuilder(String.format("&a&l%s &cYou do not have permission!", plugin.getDescription().getName())).defaultPalette().build());
            return true;
        }

        try {
            plugin.getConfigsManager().reloadConfiguration("configuration");
            plugin.getRefineryManager().reload();

            for (Player player : plugin.getServer().getOnlinePlayers())
                if (plugin.getRefineryManager().isMenu(player.getOpenInventory().getTopInventory()))
                    player.closeInventory();

            commandSender.sendMessage(new ColorBuilder(String.format("&a&l%s &aSuccessfully reloaded the configuration!", plugin.getDescription().getName())).defaultPalette().build());
        } catch (Exception e) {
            commandSender.sendMessage(new ColorBuilder(String.format("&a&l%s &cSomething went wrong while reloading the configuration!", plugin.getDescription().getName())).defaultPalette().build());
            return true;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> collection = new ArrayList<>();

        if (strings.length == 2) {
            if (commandSender.hasPermission(String.format("refinery.%s", name())))
                if ("reload".toLowerCase().startsWith(strings[0].toLowerCase()))
                    collection.add("reload");
        }

        return collection;
    }
}
