package io.github.rubendalebout.refinery.builders;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MenuBuilder {
    // Menu main variable
    private final Inventory menu;


    /**
     * @param name The visible name of the menu
     * @param rows The amount of rows you want in your menu
     **/
    public MenuBuilder(String name, int rows) {
        this.menu = Bukkit.createInventory(null, 9*rows, name);
    }

    /**
     * @param item The item you want to use as background
     * @return MenuBuilder or null
     **/
    public MenuBuilder background(ItemStack item) {
        for (int x = 0; x < this.menu.getSize()/9; x++)
            for (int i = 0; i < 9; i++)
                this.menu.setItem(i + (9*x), item);

        return this;
    }

    /**
     * @param row The exact row number you want the item in, starts from 1
     * @param slot The exact slot number you want the item in, starts from 1
     * @param item The item you want to paste
     * @return MenuBuilder or null
     **/
    public MenuBuilder setItem(int row, int slot, ItemStack item) {
        this.menu.setItem((slot-1) + 9*(row < 2 ? 0 : row-1), item);
        return this;
    }

    public MenuBuilder setItems(int row, int slot, ItemStack[] items, boolean borders) {
        int place = (slot - 1) + 9 * (row < 2 ? 0 : row - 1);
        for (ItemStack item : items) {
            if (place >= 53) continue;
            if (borders)
                while ((place <= 8) ||
                        (place == 9 || place == 17) ||
                        (place == 18 || place == 26) ||
                        (place == 27 || place == 35) ||
                        (place == 36 || place == 44) ||
                        (place >= 45))
                    place++;
            this.menu.setItem(place, item);
            place++;
        }
        return this;
    }

    /**
     * Build the menu
     * @return Menu or null
     */
    public Inventory build() {
        return this.menu;
    }
}
