package io.github.rubendalebout.refinery.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;

public abstract class RCommand implements CommandExecutor, TabCompleter {
    public abstract String name();
}
