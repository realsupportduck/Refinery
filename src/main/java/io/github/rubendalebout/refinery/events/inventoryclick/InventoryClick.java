package io.github.rubendalebout.refinery.events.inventoryclick;

import io.github.rubendalebout.refinery.Refinery;
import io.github.rubendalebout.refinery.enums.action.Action;
import io.github.rubendalebout.refinery.events.REvents;
import io.github.rubendalebout.refinery.utils.ItemUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClick extends REvents {
    private final Refinery plugin;

    public InventoryClick(Refinery plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        // Check if it is a menu
        if (plugin.getRefineryManager().isMenu(e.getClickedInventory())) {
            ItemStack item = e.getCurrentItem();

            // Check if item is null
            if (item == null) return;

            // Check if item is a button
            if (new ItemUtils().isButton(item)) {
                // Get value
                if (new ItemUtils().hasCustomTag(item, "ACTION")) {
                    Action action = null;

                    try {
                        action = Action.valueOf(new ItemUtils().getCustomTag(item, "ACTION"));
                    } catch (IllegalArgumentException ex) {
                        // No legal action
                        return;
                    }

                    switch (action) {
                        case CLOSE_INVENTORY:
                            player.getOpenInventory().close();
                            break;
                    }
                }
            }
            e.setCancelled(true);
        }
    }
}
