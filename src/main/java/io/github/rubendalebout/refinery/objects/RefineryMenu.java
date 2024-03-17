package io.github.rubendalebout.refinery.objects;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RefineryMenu {
    String configKey = "";
    String menuTitle = "";
    Inventory inventory = null;

    public RefineryMenu(String config, String title, Inventory inv) {
        this.configKey = config;
        this.menuTitle = title;
        this.inventory = inv;
    }

    public Inventory getMenu() {
        Inventory inv = Bukkit.createInventory(null, inventory.getSize(), menuTitle);

        ItemStack[] contents = inventory.getContents();
        for (int i = 0; i < contents.length; i++) {
            ItemStack item = contents[i];
            if (item != null) {
                inv.setItem(i, item.clone());
            }
        }

        return inv;
    }
}
