package io.github.rubendalebout.refinery.events.inventoryopen;

import io.github.rubendalebout.refinery.Refinery;
import io.github.rubendalebout.refinery.enums.action.Action;
import io.github.rubendalebout.refinery.events.REvents;
import io.github.rubendalebout.refinery.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Colorable;

public class InventoryOpen extends REvents {
    private final Refinery plugin;

    public InventoryOpen(Refinery plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {
        Player player = (Player) e.getPlayer();
        Inventory menu = e.getInventory();

        if (plugin.getRefineryManager().isMenu(menu)) {
            if (!plugin.getRefineryManager().getMenu("main").equals(menu)) {
                String material = plugin.getRefineryManager().getTypeMenu(menu).toLowerCase();
                int equalsAmount = 0;

                for (ItemStack item : player.getInventory().getContents()) {
                    if (item != null && item.getType().name().toLowerCase().contains(material)) {
                        equalsAmount += item.getAmount();
                    }
                }

                // Loop through the inventory
                for (int i = 0; i < menu.getSize(); i++) {
                    ItemStack item = menu.getItem(i);

                    if (item != null && new ItemUtils().isButton(item)) {
                        Action action = Action.valueOf(new ItemUtils().getCustomTag(item, "ACTION"));

                        if (!action.name().isEmpty() && action.name().equalsIgnoreCase("get_item")) {
                            if (item.getAmount() > equalsAmount) {
                                item.setType(Material.BARRIER);
                                menu.setItem(i, item);
                            }
                        }
                    }
                }
            }
        }
    }
}
