package io.github.rubendalebout.refinery.manager;

import io.github.rubendalebout.refinery.Refinery;
import io.github.rubendalebout.refinery.builders.ColorBuilder;
import io.github.rubendalebout.refinery.builders.ItemBuilder;
import io.github.rubendalebout.refinery.builders.MenuBuilder;
import io.github.rubendalebout.refinery.objects.RefineryMenu;
import io.github.rubendalebout.refinery.utils.ColorUtils;
import io.github.rubendalebout.refinery.utils.IntegerUtils;
import io.github.rubendalebout.refinery.utils.ItemUtils;
import io.github.rubendalebout.refinery.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public class RefineryManager {
    private final Refinery plugin;

    private HashMap<String, RefineryMenu> menuList = new HashMap<>();
    private HashMap<String, ItemStack> menuItems = new HashMap<>();

    private HashMap<String, Material> materialList = new HashMap<String, Material>();

    public RefineryManager(Refinery plugin) {
        this.plugin = plugin;

        this.register();
    }

    public void reload() {
        this.menuList = new HashMap<>();
        this.menuItems = new HashMap<>();
        this.materialList = new HashMap<>();

        this.register();
    }

    private void register() {
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
                    .addKey("REFINE_ITEM", materialName)
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
                                .addKey("REFINE_ITEM", materialName)
                                .build());
                    }
                }

                this.menuList.put(String.format("%s_menu_%s", String.join("_", materialName), x), new RefineryMenu(key, new ColorBuilder(plugin.getConfigsManager().getFileConfiguration("configuration").getString(String.format("refinery.%s.name", key))).rgbPalette().defaultPalette().build(), new MenuBuilder(new ColorBuilder(plugin.getConfigsManager().getFileConfiguration("configuration").getString(String.format("refinery.%s.name", key))).rgbPalette().defaultPalette().build(), 6)
                        .background(new ItemBuilder("STAINED_GLASS_PANE", "BLACK")
                                .name(" ")
                                .flag(ItemFlag.HIDE_ENCHANTS)
                                .flag(ItemFlag.HIDE_ATTRIBUTES)
                                .addKey("REFINE_ITEM", materialName)
                                .build())
                        .setItems(1, 1, menuItems.toArray(new ItemStack[0]), true)
                        .setItem(6, 1, (x == 1 && x < totalPages) ? new ItemBuilder("STAINED_GLASS_PANE", "BLACK")
                                .name(" ")
                                .flag(ItemFlag.HIDE_ENCHANTS)
                                .flag(ItemFlag.HIDE_ATTRIBUTES)
                                .addKey("REFINE_ITEM", materialName)
                                .build() : new ItemBuilder("STAINED_GLASS_PANE", "BLUE")
                                .name(new ColorBuilder("&1Previous page").defaultPalette().build())
                                .flag(ItemFlag.HIDE_ENCHANTS)
                                .flag(ItemFlag.HIDE_ATTRIBUTES)
                                .button(true)
                                .addKey("ACTION", "OPEN_INVENTORY")
                                .addKey("OPEN_INVENTORY", String.format("%s_menu_%s", materialName, x-1))
                                .addKey("REFINE_ITEM", materialName)
                                .build())
                        .setItem(6, 9, (x == totalPages) ? new ItemBuilder("STAINED_GLASS_PANE", "BLACK")
                                .name(" ")
                                .flag(ItemFlag.HIDE_ENCHANTS)
                                .flag(ItemFlag.HIDE_ATTRIBUTES)
                                .addKey("REFINE_ITEM", materialName)
                                .build() : new ItemBuilder("STAINED_GLASS_PANE", "BLUE")
                                .name(new ColorBuilder("&1Next page").defaultPalette().build())
                                .flag(ItemFlag.HIDE_ENCHANTS)
                                .flag(ItemFlag.HIDE_ATTRIBUTES)
                                .button(true)
                                .addKey("ACTION", "OPEN_INVENTORY")
                                .addKey("OPEN_INVENTORY", String.format("%s_menu_%s", materialName, x+1))
                                .addKey("REFINE_ITEM", materialName)
                                .build())
                        .setItem(1, 9, new ItemBuilder("STAINED_GLASS_PANE", "RED")
                                .name(new ColorBuilder("&4&lClose").defaultPalette().build())
                                .flag(ItemFlag.HIDE_ENCHANTS)
                                .flag(ItemFlag.HIDE_ATTRIBUTES)
                                .button(true)
                                .addKey("ACTION", "CLOSE_INVENTORY")
                                .addKey("REFINE_ITEM", materialName)
                                .addKey("CURRENT_MENU", String.format("%s_menu_%s", materialName, x))
                                .build())
                        .setItem(1, 1, new ItemBuilder("STAINED_GLASS_PANE", "CYAN")
                                .name(new ColorBuilder("&3&lOpen Refinery Menu").defaultPalette().build())
                                .flag(ItemFlag.HIDE_ENCHANTS)
                                .flag(ItemFlag.HIDE_ATTRIBUTES)
                                .button(true)
                                .addKey("ACTION", "OPEN_INVENTORY")
                                .addKey("OPEN_INVENTORY", "main")
                                .addKey("REFINE_ITEM", materialName)
                                .build())
                        .build()));
            }
        }

        // Create Refinery menu
        Inventory menu = new MenuBuilder(new ColorBuilder("&a&lRefinery").defaultPalette().build(), Math.min(3 + this.menuItems.size() / 7, 6))
                .background(new ItemBuilder("STAINED_GLASS_PANE", "BLACK")
                        .name(" ")
                        .flag(ItemFlag.HIDE_ENCHANTS)
                        .flag(ItemFlag.HIDE_ATTRIBUTES)
                        .addKey("REFINE_ITEM", "main")
                        .build())
                .setItems(1, 1, this.menuItems.values().toArray(new ItemStack[0]), true)
                .setItem(1, 9, new ItemBuilder("STAINED_GLASS_PANE", "RED")
                        .name(new ColorBuilder("&4&lClose").defaultPalette().build())
                        .flag(ItemFlag.HIDE_ENCHANTS)
                        .flag(ItemFlag.HIDE_ATTRIBUTES)
                        .button(true)
                        .addKey("REFINE_ITEM", "main")
                        .addKey("ACTION", "CLOSE_INVENTORY")
                        .build())
                .build();

        this.menuList.put("main", new RefineryMenu("", new ColorBuilder("&a&lRefinery").defaultPalette().build(), menu));
    }

    public void removeMenu(String name) {
        this.menuList.remove(name);
    }

    public boolean isMenu(Inventory menu) {
        if (menu == null) return false;

        return Arrays.stream(menu.getContents())
                .filter(Objects::nonNull)
                .filter(item -> new ItemUtils().hasCustomTag(item, "REFINE_ITEM"))
                .anyMatch(item -> new ItemUtils().hasCustomTag(item,"REFINE_ITEM"));
    }

    public Inventory getMenu(String name) {
        if (this.menuList.containsKey(name)) {
            return this.menuList.get(name).getMenu();
        }
        return null;
    }

    public String getTypeMenu(Inventory menu) {
        Optional<String> key = Arrays.stream(menu.getContents())
                .filter(Objects::nonNull)
                .filter(item -> new ItemUtils().hasCustomTag(item, "REFINE_ITEM"))
                .map(item -> new ItemUtils().getCustomTag(item, "REFINE_ITEM"))
                .findFirst();

        // Check if key exists for the value
        return key.orElse(null);
    }

    public String getCurrentMenu(Inventory menu) {
        Optional<String> key = Arrays.stream(menu.getContents())
                .filter(Objects::nonNull)
                .filter(item -> new ItemUtils().hasCustomTag(item, "CURRENT_MENU"))
                .map(item -> new ItemUtils().getCustomTag(item, "CURRENT_MENU"))
                .findFirst();

        // Check if key exists for the value
        return key.orElse(null);
    }

    public HashMap<String, Material> getMaterialList() {
        return this.materialList;
    }
}
