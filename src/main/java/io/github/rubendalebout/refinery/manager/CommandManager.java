package io.github.rubendalebout.refinery.manager;

import io.github.rubendalebout.refinery.Refinery;
import io.github.rubendalebout.refinery.commands.RCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandManager {
    private final Refinery plugin;
    private List<RCommand> commandList = new ArrayList<>();

    public CommandManager(Refinery plugin) {
        this.plugin = plugin;
    }

    public void register() {
        for (RCommand c : this.commandList) {
            Objects.requireNonNull(plugin.getCommand(c.name())).setExecutor(c);
            Objects.requireNonNull(plugin.getCommand(c.name())).setTabCompleter(c);
        }
    }

    public void addCommand(RCommand command) {
        if (!this.commandList.contains(command))
            this.commandList.add(command);
    }
}
