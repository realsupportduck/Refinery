package io.github.rubendalebout.refinery.commands.refinery;

import io.github.rubendalebout.factory.builders.ColorBuilder;
import io.github.rubendalebout.factory.builders.ItemBuilder;
import io.github.rubendalebout.factory.builders.MenuBuilder;
import io.github.rubendalebout.refinery.commands.RCommand;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

import java.util.List;

public class Refinery extends RCommand {
    @Override
    public String name() {
        return "refinery";
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(new ColorBuilder("&cConsole is not allowed to use this command").defaultPalette().build());
            return true;
        }

        Inventory menu = new MenuBuilder(new ColorBuilder("&bRefinery Menu").defaultPalette().build(), 6)
                .background(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
                        .name(" ")
                        .flag(ItemFlag.HIDE_ENCHANTS)
                        .flag(ItemFlag.HIDE_ATTRIBUTES)
                        .movable(false)
                        .build())
                .build();

        Player player = (Player) commandSender;
        player.openInventory(menu);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
