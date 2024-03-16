package io.github.rubendalebout.refinery.utils;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Colorable;

public class InventoryUtils {
    private Inventory inventory;
    public InventoryUtils(Inventory inventory) {
        this.inventory = inventory;
    }

    public int countWool() {
        if (this.inventory == null) return 0;
        int amount = 0;

        for (ItemStack item : this.inventory) {
            if (item != null) {
                if (item.getType().name().contains("wool"))
                    amount++;
            }
        }

        return amount;
    }

    public int countWool(String skipMaterial) {
        if (this.inventory == null) return 0;
        int amount = 0;

        for (ItemStack item : this.inventory) {
            if (item != null) {
                if (item.getType().name().contains("wool"))
                    if (!item.getType().name().contains(skipMaterial) || ((Colorable) item.getData()).getColor().name().equalsIgnoreCase(skipMaterial))
                        amount++;
            }
        }

        return amount;
    }
}
