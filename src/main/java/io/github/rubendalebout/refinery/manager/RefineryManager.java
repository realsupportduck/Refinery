package io.github.rubendalebout.refinery.manager;

import io.github.rubendalebout.factory.builders.ColorBuilder;
import io.github.rubendalebout.factory.builders.ItemBuilder;
import io.github.rubendalebout.factory.builders.MenuBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RefineryManager {
    private HashMap<String, Inventory> menuList = new HashMap<>();
    private HashMap<String, Material> materialList = new HashMap<String, Material>();

    public RefineryManager() {
        this.materialList.put("&bGlass", Material.GLASS);

        // Create Refinery menu
        Inventory menu = new MenuBuilder(new ColorBuilder("&bRefinery Menu").defaultPalette().build(), 6)
                .background(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
                        .name(" ")
                        .flag(ItemFlag.HIDE_ENCHANTS)
                        .flag(ItemFlag.HIDE_ATTRIBUTES)
                        .movable(false)
                        .build())
                .build();

        this.addMenu("main", menu);
    }

    public void addMenu(String name, Inventory menu) {
        if (!this.menuList.containsValue(menu))
            this.menuList.put(name, menu);
    }

    public void removeMenu(String name) {
        this.menuList.remove(name);
    }

    public boolean isMenu(Inventory menu) {
        return this.menuList.containsValue(menu);
    }

    public Inventory getMenu(String name) {
        return this.menuList.getOrDefault(name, null);
    }

    public HashMap<String, Material> getMaterialList() {
        return this.materialList;
    }
}
