package io.github.rubendalebout.refinery.manager;

import io.github.rubendalebout.factory.builders.ColorBuilder;
import io.github.rubendalebout.factory.builders.ItemBuilder;
import io.github.rubendalebout.factory.builders.MenuBuilder;
import io.github.rubendalebout.factory.utils.StringUtils;
import io.github.rubendalebout.refinery.Refinery;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RefineryManager {
    private final Refinery plugin;

    private HashMap<String, Inventory> menuList = new HashMap<>();
    private HashMap<String, ItemStack> menuItems = new HashMap<>();

    private HashMap<String, Material> materialList = new HashMap<String, Material>();

    public RefineryManager(Refinery plugin) {
        this.plugin = plugin;

        for (String key : plugin.getConfigsManager().getFileConfiguration("configuration").getConfigurationSection("refinery").getKeys(false)) {
            this.menuItems.put(key, new ItemBuilder(Material.valueOf(plugin.getConfigsManager().getFileConfiguration("configuration").getString(String.format("refinery.%s.material", key))))
                            .name(plugin.getConfigsManager().getFileConfiguration("configuration").getString(String.format("refinery.%s.name", key)))
                    .build());
        }

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

    public Inventory createMenu(String item) {
        return new MenuBuilder(new ColorBuilder("&bRefinery Menu").defaultPalette().build(), 6)
                .background(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
                        .name(" ")
                        .flag(ItemFlag.HIDE_ENCHANTS)
                        .flag(ItemFlag.HIDE_ATTRIBUTES)
                        .movable(false)
                        .build())
                .setItem(1, 1, new ItemBuilder(Material.valueOf(new StringUtils().capitalize(String.format("%s_%s", this.color(0), "wool"))))
                        .build())
                .build();
    }

    public String color(int number) {
        switch (number) {
            default:
                return "white";
        }
    }

    public int color(String name) {
        switch (name) {
            default:
                return 0;
        }
    }

    public HashMap<String, Material> getMaterialList() {
        return this.materialList;
    }
}
