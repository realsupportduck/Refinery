package io.github.rubendalebout.refinery.events.inventoryopen;

import io.github.rubendalebout.refinery.Refinery;
import io.github.rubendalebout.refinery.builders.ColorBuilder;
import io.github.rubendalebout.refinery.builders.ItemBuilder;
import io.github.rubendalebout.refinery.enums.action.Action;
import io.github.rubendalebout.refinery.events.REvents;
import io.github.rubendalebout.refinery.utils.ColorUtils;
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

                // Loop through the inventory
                for (int i = 0; i < menu.getSize(); i++) {
                    ItemStack item = menu.getItem(i);
                    int equalsAmount = 0;

                    if (item != null && new ItemUtils().isButton(item)) {
                        Action action = Action.valueOf(new ItemUtils().getCustomTag(item, "ACTION"));

                        if (!action.name().isEmpty() && action.name().equalsIgnoreCase("get_item")) {
                            for (ItemStack p : player.getInventory().getContents()) {
                                if (p != null && p.getType().name().toLowerCase().contains(material)) {
                                    if (!new ColorUtils().getColor(p).equalsIgnoreCase(new ColorUtils().getColor(item))) {
                                        equalsAmount += p.getAmount();
                                    }
                                }
                            }
                            if (item.getAmount() > equalsAmount) {
                                menu.setItem(i, new ItemBuilder(Material.BARRIER)
                                        .name(new ColorBuilder("&4✘").defaultPalette().rgbPalette().build())
                                        .amount(item.getAmount())
                                        .build());
                            }
                        }
                    }
                }
            }
        }
    }
}
