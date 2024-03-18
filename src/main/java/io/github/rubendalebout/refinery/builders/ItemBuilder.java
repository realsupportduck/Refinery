package io.github.rubendalebout.refinery.builders;

import io.github.rubendalebout.refinery.Refinery;
import io.github.rubendalebout.refinery.utils.ColorUtils;
import io.github.rubendalebout.refinery.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBuilder {
    // ItemStack main variable
    private final ItemStack itemStack;

    /**
     * Constructor of the item builder
     * @param material Give the exact material you want to use for this item
     */
    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
    }

    public ItemBuilder(String material) {
        this.itemStack = new ItemStack(Material.valueOf(new StringUtils().capitalize(material)));
    }

    public ItemBuilder(String material, short color) {
        if (Bukkit.getServer().getVersion().contains("1.14") || Bukkit.getServer().getVersion().contains("1.15") || Bukkit.getServer().getVersion().contains("1.16") || Bukkit.getServer().getVersion().contains("1.17") || Bukkit.getServer().getVersion().contains("1.18") || Bukkit.getServer().getVersion().contains("1.19") || Bukkit.getServer().getVersion().contains("1.20")) {
            this.itemStack = new ItemStack(Material.valueOf(new StringUtils().capitalize(String.format("%s_%s", new ColorUtils().getColorName(color), material))));
        } else {
            this.itemStack = new ItemStack(Material.valueOf(new StringUtils().capitalize(material)), color);
        }
    }

    public ItemBuilder(String material, String color) {
        if (Bukkit.getServer().getVersion().contains("1.14") || Bukkit.getServer().getVersion().contains("1.15") || Bukkit.getServer().getVersion().contains("1.16") || Bukkit.getServer().getVersion().contains("1.17") || Bukkit.getServer().getVersion().contains("1.18") || Bukkit.getServer().getVersion().contains("1.19") || Bukkit.getServer().getVersion().contains("1.20")) {
            this.itemStack = new ItemStack(Material.valueOf(new StringUtils().capitalize(String.format("%s_%s", color, material))));
        } else {
            this.itemStack = new ItemStack(Material.valueOf(new StringUtils().capitalize(material)), new ColorUtils().getColorShort(color));
        }
    }

    /**
     * Set the amount of your item
     * @param amount The amount
     * @return ItemBuilder or null
     */
    public ItemBuilder amount(int amount) {
        this.itemStack.setAmount(amount);

        return this;
    }

    /**
     * Set the damage for your item
     * @param damage The damage
     * @return ItemBuilder or null
     */
    public ItemBuilder damage(int damage) {
        Damageable damageable = (Damageable) this.itemStack.getItemMeta();

        if (damageable != null) {
            damageable.setDamage(damage);

            this.itemStack.setItemMeta((ItemMeta) damageable);
        }

        return this;
    }

    /**
     * Set the name of the item
     * @param name The name
     * @return ItemBuilder or null
     */
    public ItemBuilder name(String name) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();

        if (itemMeta != null) {
            itemMeta.setDisplayName(name);
            this.itemStack.setItemMeta(itemMeta);
        }

        return this;
    }

    /**
     * Reset the lore of the item
     * @return ItemBuilder or null
     */
    public ItemBuilder resetLore() {
        ItemMeta itemMeta = this.itemStack.getItemMeta();

        if (itemMeta != null) {
            itemMeta.setLore(new ArrayList<>());
            this.itemStack.setItemMeta(itemMeta);
        }

        return this;
    }

    /**
     * Add lore to the item
     * @param lore Enter your string or strings for the lore
     * @return ItemBuilder or null
     */
    public ItemBuilder lore(String... lore) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();

        if (itemMeta != null) {
            List<String> list = (itemMeta.getLore() == null) ? new ArrayList<>() : itemMeta.getLore();

            list.addAll(Arrays.asList(lore));

            itemMeta.setLore(list);

            this.itemStack.setItemMeta(itemMeta);
        }

        return this;
    }

    /**
     * Reset the enchants of your item
     * @return ItemBuilder or null
     */
    public ItemBuilder resetEnchants() {
        ItemMeta itemMeta = this.itemStack.getItemMeta();

        if (itemMeta != null) {
            for (Enchantment e : itemMeta.getEnchants().keySet())
                itemMeta.removeEnchant(e);

            this.itemStack.setItemMeta(itemMeta);
        }

        return this;
    }

    /**
     * Add an enchantment to your item
     * @param enchantment The enchantment
     * @param level The level of your enchantment
     * @param b Apply true or false
     * @return ItemBuilder or null
     */
    public ItemBuilder enchant(Enchantment[] enchantment, int[] level, boolean[] b) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();

        if (itemMeta != null) {
            for (int x = 0; x < enchantment.length; x++)
                if (x < level.length && x < b.length)
                    itemMeta.addEnchant(enchantment[x], level[x], b[x]);

            this.itemStack.setItemMeta(itemMeta);
        }

        return this;
    }

    /**
     * Add an enchantment to your item
     * @param enchantment The enchantment
     * @param level The level of your enchantment
     * @param b Apply true or false
     * @return ItemBuilder or null
     */
    public ItemBuilder enchant(Enchantment enchantment, int level, boolean b) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();

        if (itemMeta != null) {
            itemMeta.addEnchant(enchantment, level, b);

            this.itemStack.setItemMeta(itemMeta);
        }

        return this;
    }

    /**
     * Remove specified enchantments from your item
     * @param enchantment The enchantments to remove
     * @return ItemBuilder or null
     */
    public ItemBuilder removeEnchant(Enchantment... enchantment) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();

        if (itemMeta != null) {
            for (Enchantment e : enchantment)
                itemMeta.removeEnchant(e);

            this.itemStack.setItemMeta(itemMeta);
        }

        return this;
    }

    /**
     * Reset the flags on the item
     * @return ItemBuilder or null
     */
    public ItemBuilder resetFlags() {
        ItemMeta itemMeta = this.itemStack.getItemMeta();

        if (itemMeta != null) {
            for (ItemFlag f : itemMeta.getItemFlags())
                itemMeta.removeItemFlags(f);

            this.itemStack.setItemMeta(itemMeta);
        }

        return this;
    }

    /**
     * Add flag(s) to your item
     * @param itemFlag The item flag(s)
     * @return ItemBuilder or null
     */
    public ItemBuilder flag(ItemFlag... itemFlag) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();

        if (itemMeta != null) {
            itemMeta.addItemFlags(itemFlag);

            this.itemStack.setItemMeta(itemMeta);
        }

        return this;
    }

    /**
     * Remove item flag(s) from your item
     * @param itemFlag The item flag(s)
     * @return ItemBuilder or null
     */
    public ItemBuilder removeFlag(ItemFlag... itemFlag) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();

        if (itemMeta != null) {
            itemMeta.removeItemFlags(itemFlag);

            this.itemStack.setItemMeta(itemMeta);
        }

        return this;
    }

    /**
     * Add custom data to the item
     * @param key The key
     * @param value The value
     * @return ItemBuilder or null
     */
    public ItemBuilder addKey(String key, String value) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta != null) {
            PersistentDataContainer dataContainer = itemMeta.getPersistentDataContainer();

            dataContainer.set(new NamespacedKey(Refinery.getInstance(), key), PersistentDataType.STRING, value);

            this.itemStack.setItemMeta(itemMeta);
        }
        return this;
    }

    /**
     * Make an item movable (check inventory click event)
     * @param bool True or false
     * @return ItemBuilder or null
     */
    public ItemBuilder movable(boolean bool) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta != null) {
            PersistentDataContainer dataContainer = itemMeta.getPersistentDataContainer();

            dataContainer.set(new NamespacedKey(Refinery.getInstance(), "ACTION_MOVABLE"), PersistentDataType.BOOLEAN, bool);

            this.itemStack.setItemMeta(itemMeta);
        }
        return this;
    }


    /**
     * Make an item button (check inventory click event)
     * @param bool True or false
     * @return ItemBuilder or null
     */
    public ItemBuilder button(boolean bool) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta != null) {
            PersistentDataContainer dataContainer = itemMeta.getPersistentDataContainer();

            dataContainer.set(new NamespacedKey(Refinery.getInstance(), "ACTION_BUTTON"), PersistentDataType.BOOLEAN, bool);

            this.itemStack.setItemMeta(itemMeta);
        }
        return this;
    }

    /**
     * Finishing function to give the ItemStack
     * @return ItemStack or null
     */
    public ItemStack build() {
        return this.itemStack;
    }
}
