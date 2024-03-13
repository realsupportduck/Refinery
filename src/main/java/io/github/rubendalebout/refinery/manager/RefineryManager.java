package io.github.rubendalebout.refinery.manager;

import io.github.rubendalebout.refinery.Refinery;
import io.github.rubendalebout.refinery.builders.ColorBuilder;
import io.github.rubendalebout.refinery.builders.ItemBuilder;
import io.github.rubendalebout.refinery.builders.MenuBuilder;
import io.github.rubendalebout.refinery.utils.ColorUtils;
import io.github.rubendalebout.refinery.utils.IntegerUtils;
import io.github.rubendalebout.refinery.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
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
            // Item variable
            String colorName = "";
            short colorCode = 0;
            String materialName;
            if (new ColorUtils().containsColor(plugin.getConfigsManager().getFileConfiguration("configuration").getString(String.format("refinery.%s.material", key)))) {
                // contains color in the name
                String[] split = new StringUtils().split(plugin.getConfigsManager().getFileConfiguration("configuration").getString(String.format("refinery.%s.material", key)), "_");
                materialName = String.join("_", Arrays.copyOfRange(split, 1, split.length));
                colorName = split[0];
            } else {
                // contains no color in the name
                materialName = plugin.getConfigsManager().getFileConfiguration("configuration").getString(String.format("refinery.%s.material", key));
            }

            this.menuItems.put(key, new ItemBuilder(materialName, (!colorName.isEmpty()) ? colorName : new ColorUtils().getColorName(colorCode))
                    .name(new ColorBuilder(plugin.getConfigsManager().getFileConfiguration("configuration").getString(String.format("refinery.%s.name", key))).rgbPalette().defaultPalette().build())
                    .button(true)
                    .addKey("ACTION", "OPEN_INVENTORY")
                    .addKey("OPEN_INVENTORY", String.format("%s_menu_1", materialName))
                    .build());

            int totalPages = new ColorUtils().colors.size()/4;
            for (int x = 1; x <= new ColorUtils().colors.size()/4; x++) {
                List<ItemStack> menuItems = new ArrayList<>();

                for (int y = 0; y < 4; y ++) {
                    for (int p = 1; p <= 64; p *= 2) {
                        menuItems.add(new ItemBuilder(materialName, new ColorUtils().getColorName((short) (y + ((x > 1) ? 4*(x-1) : 0))))
                                .name(new ColorBuilder(plugin.getConfigsManager().getFileConfiguration("configuration").getString(String.format("refinery.%s.name", key))).rgbPalette().defaultPalette().build())
                                .amount(p)
                                .button(true)
                                .addKey("ACTION", "GET_ITEM")
                                .build());
                    }
                }

                this.menuList.put(String.format("%s_menu_%s", String.join("_", materialName), x), new MenuBuilder(new ColorBuilder(plugin.getConfigsManager().getFileConfiguration("configuration").getString(String.format("refinery.%s.name", key))).rgbPalette().defaultPalette().build(), 6)
                        .background(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
                                .name(" ")
                                .flag(ItemFlag.HIDE_ENCHANTS)
                                .flag(ItemFlag.HIDE_ATTRIBUTES)
                                .build())
                        .setItems(1, 1, menuItems.toArray(new ItemStack[0]), true)
                        .setItem(6, 1, (x == 1 && x < totalPages) ? new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
                                .name(" ")
                                .flag(ItemFlag.HIDE_ENCHANTS)
                                .flag(ItemFlag.HIDE_ATTRIBUTES)
                                .build() : new ItemBuilder(Material.BLUE_STAINED_GLASS_PANE)
                                .name(new ColorBuilder("&1Previous page").defaultPalette().build())
                                .flag(ItemFlag.HIDE_ENCHANTS)
                                .flag(ItemFlag.HIDE_ATTRIBUTES)
                                .button(true)
                                .addKey("ACTION", "OPEN_INVENTORY")
                                .addKey("OPEN_INVENTORY", String.format("%s_menu_%s", materialName, x-1))
                                .build())
                        .setItem(6, 9, (x == totalPages) ? new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
                                .name(" ")
                                .flag(ItemFlag.HIDE_ENCHANTS)
                                .flag(ItemFlag.HIDE_ATTRIBUTES)
                                .build() : new ItemBuilder(Material.BLUE_STAINED_GLASS_PANE)
                                .name(new ColorBuilder("&1Next page").defaultPalette().build())
                                .flag(ItemFlag.HIDE_ENCHANTS)
                                .flag(ItemFlag.HIDE_ATTRIBUTES)
                                .button(true)
                                .addKey("ACTION", "OPEN_INVENTORY")
                                .addKey("OPEN_INVENTORY", String.format("%s_menu_%s", materialName, x+1))
                                .build())
                        .setItem(1, 9, new ItemBuilder(Material.RED_STAINED_GLASS_PANE)
                                .name(new ColorBuilder("&4&lClose").defaultPalette().build())
                                .flag(ItemFlag.HIDE_ENCHANTS)
                                .flag(ItemFlag.HIDE_ATTRIBUTES)
                                .button(true)
                                .addKey("ACTION", "CLOSE_INVENTORY")
                                .build())
                        .setItem(1, 1, new ItemBuilder(Material.CYAN_STAINED_GLASS_PANE)
                                .name(new ColorBuilder("&3&lOpen Refinery Menu").defaultPalette().build())
                                .flag(ItemFlag.HIDE_ENCHANTS)
                                .flag(ItemFlag.HIDE_ATTRIBUTES)
                                .button(true)
                                .addKey("ACTION", "OPEN_INVENTORY")
                                .addKey("OPEN_INVENTORY", "main")
                                .build())
                        .build());
            }
        }

        // Create Refinery menu
        Inventory menu = new MenuBuilder(new ColorBuilder("&a&lRefinery").defaultPalette().build(), Math.min(3 + this.menuItems.size() / 7, 6))
                .background(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
                        .name(" ")
                        .flag(ItemFlag.HIDE_ENCHANTS)
                        .flag(ItemFlag.HIDE_ATTRIBUTES)
                        .build())
                .setItems(1, 1, this.menuItems.values().toArray(new ItemStack[0]), true)
                .setItem(1, 9, new ItemBuilder(Material.RED_STAINED_GLASS_PANE)
                        .name(new ColorBuilder("&4&lClose").defaultPalette().build())
                        .flag(ItemFlag.HIDE_ENCHANTS)
                        .flag(ItemFlag.HIDE_ATTRIBUTES)
                        .button(true)
                        .addKey("ACTION", "CLOSE_INVENTORY")
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
                return "WHITE";
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
