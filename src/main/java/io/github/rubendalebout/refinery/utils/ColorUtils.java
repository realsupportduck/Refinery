package io.github.rubendalebout.refinery.utils;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ColorUtils {
    public HashMap<Short, DyeColor> colors = new HashMap<>();

    public ColorUtils() {
        this.colors.put((short) 0, DyeColor.WHITE);
        this.colors.put((short) 1, DyeColor.BLACK);
        this.colors.put((short) 2, DyeColor.BLUE);
        this.colors.put((short) 3, DyeColor.BROWN);
        this.colors.put((short) 4, DyeColor.CYAN);
        this.colors.put((short) 5, DyeColor.GRAY);
        this.colors.put((short) 6, DyeColor.GREEN);
        this.colors.put((short) 7, DyeColor.LIGHT_BLUE);
        this.colors.put((short) 8, DyeColor.LIGHT_GRAY);
        this.colors.put((short) 9, DyeColor.LIME);
        this.colors.put((short) 10, DyeColor.MAGENTA);
        this.colors.put((short) 11, DyeColor.ORANGE);
        this.colors.put((short) 12, DyeColor.PINK);
        this.colors.put((short) 13, DyeColor.PURPLE);
        this.colors.put((short) 14, DyeColor.RED);
        this.colors.put((short) 15, DyeColor.WHITE);
        this.colors.put((short) 16, DyeColor.YELLOW);

    }

    public String getColorName(short color) {
        return (this.colors.get(color) != null) ? this.colors.get(color).name() : DyeColor.WHITE.name();
    }

    public Short getColorShort(String color) {
        Optional<Map.Entry<Short, DyeColor>> entry = colors.entrySet().stream()
                .filter(e -> e.getValue().name().equals(color))
                .findFirst();
        return (entry.map(Map.Entry::getKey).orElse(null) != null) ? entry.map(Map.Entry::getKey).orElse(null) : 0;
    }

    public boolean containsColor(String materialName) {
        return this.colors.entrySet().stream()
                .anyMatch(e -> materialName.contains(e.getValue().name()));
    }

    public String getColor(ItemStack item) {
        if (Bukkit.getServer().getVersion().contains("1.13") || Bukkit.getServer().getVersion().contains("1.14") || Bukkit.getServer().getVersion().contains("1.15") || Bukkit.getServer().getVersion().contains("1.16") || Bukkit.getServer().getVersion().contains("1.17") || Bukkit.getServer().getVersion().contains("1.18") || Bukkit.getServer().getVersion().contains("1.19") || Bukkit.getServer().getVersion().contains("1.20")) {
            return (this.containsColor(item.getType().name().split("_")[0])) ? item.getType().name().split("_")[0] : "";
        } else {
            short durability = item.getDurability();

            return this.getColorName(durability).toUpperCase();
        }
    }
}
