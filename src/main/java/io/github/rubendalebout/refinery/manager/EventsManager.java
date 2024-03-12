package io.github.rubendalebout.refinery.manager;

import io.github.rubendalebout.refinery.Refinery;
import io.github.rubendalebout.refinery.events.REvents;

import java.util.ArrayList;
import java.util.List;

public class EventsManager {
    private final Refinery plugin;
    private List<REvents> eventList = new ArrayList<>();

    public EventsManager(Refinery plugin) {
        this.plugin = plugin;
    }

    public void register() {
        for (REvents e : this.eventList)
            plugin.getServer().getPluginManager().registerEvents(e, plugin);
    }

    public void addEvent(REvents event) {
        if (!this.eventList.contains(event))
            this.eventList.add(event);
    }
}
