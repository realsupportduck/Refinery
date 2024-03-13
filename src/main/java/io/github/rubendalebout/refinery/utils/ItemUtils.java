package io.github.rubendalebout.refinery.utils;

import io.github.rubendalebout.refinery.Refinery;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;

public class ItemUtils {

    /**
     * Check if an ItemStack is empty.
     *
     * @param itemStack The ItemStack to check.
     * @return True if the ItemStack is empty, otherwise false.
     */
    public boolean isEmpty(ItemStack itemStack) {
        return itemStack == null || itemStack.getType() == Material.AIR || itemStack.getAmount() == 0;
    }

    /**
     * Check if two ItemStacks are equal.
     *
     * @param itemStack1 The first ItemStack.
     * @param itemStack2 The second ItemStack.
     * @return True if the ItemStacks are equal, otherwise false.
     */
    public boolean areEqual(ItemStack itemStack1, ItemStack itemStack2) {
        if (isEmpty(itemStack1) || isEmpty(itemStack2)) {
            return false;
        }
        return itemStack1.isSimilar(itemStack2);
    }

    /**
     * Check if an ItemStack has a certain amount.
     *
     * @param itemStack The ItemStack to check.
     * @param amount    The amount to check for.
     * @return True if the ItemStack has at least the specified amount, otherwise false.
     */
    public boolean hasAmount(ItemStack itemStack, int amount) {
        return !isEmpty(itemStack) && itemStack.getAmount() >= amount;
    }

    /**
     * Compare the amount of two ItemStacks.
     *
     * @param itemStack1 The first ItemStack.
     * @param itemStack2 The second ItemStack.
     * @return 0 if the amounts are equal, -1 if the first ItemStack has less, 1 if the second ItemStack has less.
     */
    public int compareAmount(ItemStack itemStack1, ItemStack itemStack2) {
        if (isEmpty(itemStack1) && isEmpty(itemStack2)) {
            return 0;
        } else if (isEmpty(itemStack1)) {
            return -1;
        } else if (isEmpty(itemStack2)) {
            return 1;
        }
        return Integer.compare(itemStack1.getAmount(), itemStack2.getAmount());
    }

    /**
     * Merge two ItemStacks.
     *
     * @param itemStack1 The first ItemStack.
     * @param itemStack2 The second ItemStack.
     * @return A new ItemStack representing the merged stack.
     */
    public ItemStack merge(ItemStack itemStack1, ItemStack itemStack2) {
        if (isEmpty(itemStack1)) {
            return itemStack2.clone();
        } else if (isEmpty(itemStack2)) {
            return itemStack1.clone();
        }
        if (!itemStack1.isSimilar(itemStack2)) {
            return itemStack1.clone();
        }
        ItemStack mergedStack = itemStack1.clone();
        mergedStack.setAmount(itemStack1.getAmount() + itemStack2.getAmount());
        return mergedStack;
    }

    /**
     * Check if an ItemStack has a specific durability.
     *
     * @param itemStack  The ItemStack to check.
     * @param durability The durability to check for.
     * @return True if the ItemStack has the specified durability, otherwise false.
     */
    public boolean hasDurability(ItemStack itemStack, int durability) {
        return !isEmpty(itemStack) && itemStack.getDurability() == durability;
    }

    /**
     * Check if two ItemStacks have the same durability.
     *
     * @param itemStack1 The first ItemStack.
     * @param itemStack2 The second ItemStack.
     * @return True if the ItemStacks have the same durability, otherwise false.
     */
    public boolean haveSameDurability(ItemStack itemStack1, ItemStack itemStack2) {
        if (isEmpty(itemStack1) || isEmpty(itemStack2)) {
            return false;
        }
        return itemStack1.getDurability() == itemStack2.getDurability();
    }

    /**
     * Check if an ItemStack has a specific display name.
     *
     * @param itemStack   The ItemStack to check.
     * @param displayName The display name to check for.
     * @return True if the ItemStack has the specified display name, otherwise false.
     */
    public boolean hasDisplayName(ItemStack itemStack, String displayName) {
        return !isEmpty(itemStack) && itemStack.hasItemMeta() && itemStack.getItemMeta().hasDisplayName()
                && itemStack.getItemMeta().getDisplayName().equals(displayName);
    }

    /**
     * Check if an ItemStack has lore.
     *
     * @param itemStack The ItemStack to check.
     * @return True if the ItemStack has lore, otherwise false.
     */
    public boolean hasLore(ItemStack itemStack) {
        return !isEmpty(itemStack) && itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore();
    }

    /**
     * Check if an ItemStack is of a specific type.
     *
     * @param itemStack The ItemStack to check.
     * @param material  The Material to compare against.
     * @return True if the ItemStack is of the specified type, otherwise false.
     */
    public boolean isType(ItemStack itemStack, Material material) {
        return !isEmpty(itemStack) && itemStack.getType() == material;
    }

    /**
     * Check if two ItemStacks have the same type.
     *
     * @param itemStack1 The first ItemStack.
     * @param itemStack2 The second ItemStack.
     * @return True if the ItemStacks have the same type, otherwise false.
     */
    public boolean haveSameType(ItemStack itemStack1, ItemStack itemStack2) {
        if (isEmpty(itemStack1) || isEmpty(itemStack2)) {
            return false;
        }
        return itemStack1.getType() == itemStack2.getType();
    }

    /**
     * Check if an ItemStack has a specific enchantment.
     *
     * @param itemStack   The ItemStack to check.
     * @param enchantment The enchantment to check for.
     * @return True if the ItemStack has the specified enchantment, otherwise false.
     */
    public boolean hasEnchantment(ItemStack itemStack, Enchantment enchantment) {
        return !isEmpty(itemStack) && itemStack.getEnchantments().containsKey(enchantment);
    }

    /**
     * Check if two ItemStacks have the same enchantments.
     *
     * @param itemStack1 The first ItemStack.
     * @param itemStack2 The second ItemStack.
     * @return True if the ItemStacks have the same enchantments, otherwise false.
     */
    public boolean haveSameEnchantments(ItemStack itemStack1, ItemStack itemStack2) {
        if (isEmpty(itemStack1) || isEmpty(itemStack2)) {
            return false;
        }
        return itemStack1.getEnchantments().equals(itemStack2.getEnchantments());
    }

    /**
     * Check if two ItemStacks have the same item meta.
     *
     * @param itemStack1 The first ItemStack.
     * @param itemStack2 The second ItemStack.
     * @return True if the ItemStacks have the same item meta, otherwise false.
     */
    public boolean haveSameItemMeta(ItemStack itemStack1, ItemStack itemStack2) {
        if (isEmpty(itemStack1) || isEmpty(itemStack2)) {
            return false;
        }
        if (itemStack1.hasItemMeta() != itemStack2.hasItemMeta()) {
            return false;
        }
        if (!itemStack1.hasItemMeta()) {
            return true;
        }
        return itemStack1.getItemMeta().equals(itemStack2.getItemMeta());
    }

    /**
     * Check if an ItemStack has a specific item flag.
     *
     * @param itemStack The ItemStack to check.
     * @param itemFlag  The item flag to check for.
     * @return True if the ItemStack has the specified item flag, otherwise false.
     */
    public boolean hasItemFlag(ItemStack itemStack, ItemFlag itemFlag) {
        return !isEmpty(itemStack) && itemStack.getItemMeta().hasItemFlag(itemFlag);
    }

    /**
     * Check if an ItemStack has a specific potion effect.
     *
     * @param itemStack         The ItemStack to check.
     * @param potionEffectType  The PotionEffectType to check for.
     * @return True if the ItemStack has the specified potion effect, otherwise false.
     */
    public boolean hasPotionEffect(ItemStack itemStack, PotionEffectType potionEffectType) {
        return !isEmpty(itemStack) && itemStack.getType() == Material.POTION
                && ((PotionMeta) itemStack.getItemMeta()).hasCustomEffect(potionEffectType);
    }

    /**
     * Check if an ItemStack has a specific skull owner.
     *
     * @param itemStack   The ItemStack to check.
     * @param skullOwner The skull owner to check for.
     * @return True if the ItemStack has the specified skull owner, otherwise false.
     */
    public boolean hasSkullOwner(ItemStack itemStack, String skullOwner) {
        return !isEmpty(itemStack) && itemStack.getType() == Material.PLAYER_HEAD
                && ((SkullMeta) itemStack.getItemMeta()).getOwningPlayer().getName().equals(skullOwner);
    }

    /**
     * Check if an ItemStack has a specific potion type.
     *
     * @param itemStack      The ItemStack to check.
     * @param potionEffectType The PotionEffectType to check for.
     * @return True if the ItemStack has the specified potion type, otherwise false.
     */
    public boolean hasPotionType(ItemStack itemStack, PotionEffectType potionEffectType) {
        return !isEmpty(itemStack) && itemStack.getType() == Material.POTION
                && ((PotionMeta) itemStack.getItemMeta()).hasCustomEffect(potionEffectType);
    }

    /**
     * Check if two ItemStacks have the same potion type.
     *
     * @param itemStack1 The first ItemStack.
     * @param itemStack2 The second ItemStack.
     * @return True if the ItemStacks have the same potion type, otherwise false.
     */
    public boolean haveSamePotionType(ItemStack itemStack1, ItemStack itemStack2) {
        if (isEmpty(itemStack1) || isEmpty(itemStack2)) {
            return false;
        }
        return ((PotionMeta) itemStack1.getItemMeta()).getBasePotionData().equals(
                ((PotionMeta) itemStack2.getItemMeta()).getBasePotionData());
    }

    /**
     * Check if two ItemStacks are similar ignoring lore.
     *
     * @param itemStack1 The first ItemStack.
     * @param itemStack2 The second ItemStack.
     * @return True if the ItemStacks are similar ignoring lore, otherwise false.
     */
    public boolean areSimilarIgnoreLore(ItemStack itemStack1, ItemStack itemStack2) {
        if (isEmpty(itemStack1) || isEmpty(itemStack2)) {
            return false;
        }
        ItemMeta meta1 = itemStack1.getItemMeta();
        ItemMeta meta2 = itemStack2.getItemMeta();
        if (meta1 == null || meta2 == null) {
            return meta1 == meta2;
        }
        return itemStack1.getType() == itemStack2.getType() &&
                meta1.getDisplayName().equals(meta2.getDisplayName()) &&
                meta1.getEnchants().equals(meta2.getEnchants()) &&
                meta1.getItemFlags().equals(meta2.getItemFlags()) &&
                meta1.getCustomModelData() == meta2.getCustomModelData() &&
                hasPotionType(itemStack1, PotionEffectType.getById(0)) == hasPotionType(itemStack2, PotionEffectType.getById(0));
    }

    /**
     * Check if two ItemStacks are similar ignoring amount.
     *
     * @param itemStack1 The first ItemStack.
     * @param itemStack2 The second ItemStack.
     * @return True if the ItemStacks are similar ignoring amount, otherwise false.
     */
    public boolean areSimilarIgnoreAmount(ItemStack itemStack1, ItemStack itemStack2) {
        if (isEmpty(itemStack1) || isEmpty(itemStack2)) {
            return false;
        }
        return itemStack1.isSimilar(itemStack2);
    }

    /**
     * Check if two ItemStacks are similar ignoring enchantments.
     *
     * @param itemStack1 The first ItemStack.
     * @param itemStack2 The second ItemStack.
     * @return True if the ItemStacks are similar ignoring enchantments, otherwise false.
     */
    public boolean areSimilarIgnoreEnchantments(ItemStack itemStack1, ItemStack itemStack2) {
        if (isEmpty(itemStack1) || isEmpty(itemStack2)) {
            return false;
        }
        ItemMeta meta1 = itemStack1.getItemMeta();
        ItemMeta meta2 = itemStack2.getItemMeta();
        if (meta1 == null || meta2 == null) {
            return meta1 == meta2;
        }
        return itemStack1.getType() == itemStack2.getType() &&
                meta1.getDisplayName().equals(meta2.getDisplayName()) &&
                meta1.getLore().equals(meta2.getLore()) &&
                meta1.getItemFlags().equals(meta2.getItemFlags()) &&
                meta1.getCustomModelData() == meta2.getCustomModelData() &&
                hasPotionType(itemStack1, PotionEffectType.getById(0)) == hasPotionType(itemStack2, PotionEffectType.getById(0));
    }

    /**
     * Check if an ItemStack has a custom name.
     *
     * @param itemStack The ItemStack to check.
     * @return True if the ItemStack has a custom name, otherwise false.
     */
    public boolean hasCustomName(ItemStack itemStack) {
        return !isEmpty(itemStack) && itemStack.hasItemMeta() && itemStack.getItemMeta().hasDisplayName();
    }

    /**
     * Check if two ItemStacks have the same custom name.
     *
     * @param itemStack1 The first ItemStack.
     * @param itemStack2 The second ItemStack.
     * @return True if the ItemStacks have the same custom name, otherwise false.
     */
    public boolean haveSameCustomName(ItemStack itemStack1, ItemStack itemStack2) {
        if (isEmpty(itemStack1) || isEmpty(itemStack2)) {
            return false;
        }
        return itemStack1.hasItemMeta() && itemStack2.hasItemMeta() &&
                itemStack1.getItemMeta().hasDisplayName() &&
                itemStack2.getItemMeta().hasDisplayName() &&
                itemStack1.getItemMeta().getDisplayName().equals(itemStack2.getItemMeta().getDisplayName());
    }

    /**
     * Check if an ItemStack has custom model data.
     *
     * @param itemStack The ItemStack to check.
     * @return True if the ItemStack has custom model data, otherwise false.
     */
    public boolean hasCustomModelData(ItemStack itemStack) {
        return !isEmpty(itemStack) && itemStack.hasItemMeta() && itemStack.getItemMeta().hasCustomModelData();
    }

    /**
     * Check if two ItemStacks have the same custom model data.
     *
     * @param itemStack1 The first ItemStack.
     * @param itemStack2 The second ItemStack.
     * @return True if the ItemStacks have the same custom model data, otherwise false.
     */
    public boolean haveSameCustomModelData(ItemStack itemStack1, ItemStack itemStack2) {
        if (isEmpty(itemStack1) || isEmpty(itemStack2)) {
            return false;
        }
        return itemStack1.hasItemMeta() && itemStack2.hasItemMeta() &&
                itemStack1.getItemMeta().hasCustomModelData() &&
                itemStack2.getItemMeta().hasCustomModelData() &&
                itemStack1.getItemMeta().getCustomModelData() == itemStack2.getItemMeta().getCustomModelData();
    }

    /**
     * Check if an ItemStack has a specific custom tag.
     *
     * @param itemStack The ItemStack to check.
     * @param tag       The custom tag to check for.
     * @return True if the ItemStack has the specified custom tag, otherwise false.
     */
    public boolean hasCustomTag(ItemStack itemStack, String tag) {
        return !isEmpty(itemStack) && itemStack.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Refinery.getInstance(), tag), PersistentDataType.STRING);
    }

    /**
     * Get ItemStack tag
     * @param itemStack The ItemStack
     * @param tag       The custom tag
     * @return Tag value or empty string
     */
    public String getCustomTag(ItemStack itemStack, String tag) {
        return (this.hasCustomTag(itemStack, tag)) ? itemStack.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Refinery.getInstance(), tag), PersistentDataType.STRING) : "";
    }

    /**
     * Check if an item is movable
     * @param itemStack The item
     * @return true if item is movable
     */
    public boolean isMovable(ItemStack itemStack) {
        if (itemStack.getItemMeta() != null)
            if (!itemStack.getItemMeta().getPersistentDataContainer().isEmpty())
                return Boolean.TRUE.equals(itemStack.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Refinery.getInstance(), "ACTION_MOVABLE"), PersistentDataType.BOOLEAN));
        return true;
    }

    /**
     * Check if an item is a button
     * @param itemStack The item
     * @return true if item is a button
     */
    public boolean isButton(ItemStack itemStack) {
        if (itemStack.getItemMeta() != null)
            if (!itemStack.getItemMeta().getPersistentDataContainer().isEmpty())
                return Boolean.TRUE.equals(itemStack.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Refinery.getInstance(), "ACTION_BUTTON"), PersistentDataType.BOOLEAN));
        return false;
    }
}
