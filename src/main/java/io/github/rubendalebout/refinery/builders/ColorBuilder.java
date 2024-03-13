package io.github.rubendalebout.refinery.builders;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorBuilder {
    // String main variable
    private String string = "";

    /**
     * Constructor of the color builder
     * @param string Enter the string you want to use in the builder
     */
    public ColorBuilder(String string) {
        this.string = string;
    }

    /**
     * Convert the string with the default color palette
     * @return ColorBuilder or null
     */
    public ColorBuilder defaultPalette() {
        this.string = ChatColor.translateAlternateColorCodes('&', this.string);

        return this;
    }

    /**
     * Convert the string with the rgb color palette
     * @return ColorBuilder or null
     */
    public ColorBuilder rgbPalette() {
        // Loop through the versions
        for (double i = 1.16; i < 1.20; i += 0.01) {
            // Check if Bukkit version contains the current version
            if (Bukkit.getServer().getVersion().contains(String.valueOf(i))) {
                Matcher m = Pattern.compile("#[a-fA-F0-9]{6}").matcher(this.string);
                while (m.find()) {
                    String color = this.string.substring(m.start(), m.end());
                    this.string = this.string.replace(color, net.md_5.bungee.api.ChatColor.of(color) + "").replace("&", "");
                    m = Pattern.compile("#[a-fA-F0-9]{6}").matcher(this.string);
                }
                // Break out of the loop once a matching version is found
                break;
            }
        }

        return this;
    }

    /**
     * Strip the colored string
     * @return ColorBuilder or null
     */
    public ColorBuilder strip() {
        this.string = ChatColor.stripColor(this.string);

        return this;
    }

    /**
     * Get the last colors used in the string
     * @return ColorBuilder or null
     */
    public ColorBuilder lastColors() {
        this.string = ChatColor.getLastColors(this.string);

        return this;
    }

    /**
     * Finishing function to give the String
     * @return String or null
     */
    public String build() {
        return this.string;
    }
}