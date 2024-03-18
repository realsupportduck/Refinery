package io.github.rubendalebout.refinery.events.inventoryclick;

import io.github.rubendalebout.refinery.Refinery;
import io.github.rubendalebout.refinery.builders.ItemBuilder;
import io.github.rubendalebout.refinery.enums.action.Action;
import io.github.rubendalebout.refinery.events.REvents;
import io.github.rubendalebout.refinery.utils.ColorUtils;
import io.github.rubendalebout.refinery.utils.ItemUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Colorable;

import java.util.ArrayList;
import java.util.List;

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
            e.setCancelled(true);

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
                        case OPEN_INVENTORY:
                            player.openInventory(plugin.getRefineryManager().getMenu(new ItemUtils().getCustomTag(item, "OPEN_INVENTORY")));
                            break;
                        case CLOSE_INVENTORY:
                            player.getOpenInventory().close();
                            break;
                        case GET_ITEM:
                            ItemStack newItem;

                            if (item.getData() instanceof Colorable) {
                                Colorable colorable = (Colorable) item.getData();
                                newItem = new ItemBuilder(item.getType().name(), colorable.getColor().name())
                                        .amount(item.getAmount())
                                        .build();
                            } else {
                                newItem = new ItemBuilder(item.getType().name())
                                        .amount(item.getAmount())
                                        .build();
                            }

                            if (newItem == null || e.getClickedInventory() == null) return;

                            if (this.removeItem(player.getInventory(), plugin.getRefineryManager().getTypeMenu(e.getClickedInventory()), new ColorUtils().getColor(item), item.getAmount())) {
                                player.getInventory().addItem(newItem);

                                player.closeInventory();
                                player.openInventory(plugin.getRefineryManager().getMenu(plugin.getRefineryManager().getCurrentMenu(e.getClickedInventory())));
                            }
                            break;
                    }
                }
            }
        }
    }

    public boolean removeItem(Inventory inventory, String material, String color, int amount) {
        int amountLeftToRemove = amount;
        List<ItemStack> itemsRemoved = new ArrayList<>();

        // Loop door alle items in de inventory
        for (ItemStack item : inventory.getContents()) {
            if (item == null || !item.getType().name().contains(material.toUpperCase())) {
                continue; // Ga naar het volgende item als het geen wol is
            }

            // Controleer de kleur van de wol
            String woolColor = new ColorUtils().getColor(item);
            if (!woolColor.equalsIgnoreCase(color)) {
                // Bereken hoeveel van deze kleur wol we kunnen verwijderen
                int amountToRemoveFromStack = Math.min(item.getAmount(), amountLeftToRemove);

                itemsRemoved.add(item.clone());
                // Verwijder de wol van de stapel
                item.setAmount(item.getAmount() - amountToRemoveFromStack);

                // Update de hoeveelheid die nog verwijderd moet worden
                amountLeftToRemove -= amountToRemoveFromStack;

                // Als we alle wol hebben verwijderd die we wilden, stop dan met de loop
                if (amountLeftToRemove <= 0) {
                    break;
                }
            }
        }

        if (amountLeftToRemove != 0) {
            for (ItemStack item : itemsRemoved)
                inventory.addItem(item);
        }

        return amountLeftToRemove == 0;
    }
}
