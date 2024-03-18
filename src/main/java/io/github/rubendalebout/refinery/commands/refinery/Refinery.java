package io.github.rubendalebout.refinery.commands.refinery;

import io.github.rubendalebout.refinery.builders.ColorBuilder;
import io.github.rubendalebout.refinery.commands.RCommand;
import io.github.rubendalebout.refinery.commands.refinery.reload.Reload;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Refinery extends RCommand {
    private final io.github.rubendalebout.refinery.Refinery plugin;
    private final List<RCommand> subCommands = new ArrayList<>();

    public Refinery(io.github.rubendalebout.refinery.Refinery plugin) {
        this.plugin = plugin;

        this.subCommands.add(new Reload(plugin));
    }

    @Override
    public String name() {
        return "refinery";
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission(String.format("refinery.%s", name()))) {
            commandSender.sendMessage(new ColorBuilder(String.format("&a&l%s &cYou do not have permission!", plugin.getDescription().getName())).defaultPalette().build());
            return true;
        }

        if (strings.length > 0) {
            if (this.subCommands.stream().anyMatch(subCommand -> subCommand.name().equals(strings[0]))) {
                Objects.requireNonNull(this.subCommands.stream()
                        .filter(subCommand -> subCommand.name().equals(strings[0]))
                        .findFirst().orElse(null)).onCommand(commandSender, command, s, strings);
                return true;
            }
        }

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(new ColorBuilder("&cConsole is not allowed to use this command").defaultPalette().build());
            return true;
        }

        Player player = (Player) commandSender;
        player.openInventory(plugin.getRefineryManager().getMenu("main"));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> collection = new ArrayList<>();

        // Check if first argument
        if (strings.length == 1) {
            for (RCommand subCommand : this.subCommands) {
                if ((subCommand.name().toLowerCase().startsWith(strings[0].toLowerCase()))
                        && commandSender.hasPermission(String.format("refinery.%s", subCommand.name())))
                    collection.add(subCommand.name().toLowerCase());
            }
        }

        return collection;
    }
}
