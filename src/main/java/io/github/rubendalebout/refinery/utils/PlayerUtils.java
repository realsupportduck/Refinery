package io.github.rubendalebout.refinery.utils;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class PlayerUtils {
    /**
     * Checks if a player is online.
     *
     * @param player The player whose online status is being checked.
     * @return true if the player is online, otherwise false.
     */
    public boolean isOnline(Player player) {
        return player.isOnline();
    }

    /**
     * Sends a message to a specific player.
     *
     * @param player  The player to whom the message is being sent.
     * @param message The message being sent.
     */
    public void sendMessage(Player player, String message) {
        player.sendMessage(message);
    }

    /**
     * Retrieves a player based on their username.
     *
     * @param name The username of the player.
     * @return The found player, or null if there's no player with the given name.
     */
    public Player getPlayerByName(String name) {
        return Bukkit.getServer().getPlayer(name);
    }

    /**
     * Retrieves a player based on their UUID.
     *
     * @param uuid The UUID of the player.
     * @return The found player, or null if there's no player with the given UUID.
     */
    public Player getPlayerByUUID(UUID uuid) {
        return Bukkit.getServer().getPlayer(uuid);
    }

    /**
     * Checks if a player is flying.
     *
     * @param player The player whose flying status is being checked.
     * @return true if the player is flying, otherwise false.
     */
    public boolean isFlying(Player player) {
        return player.isFlying();
    }

    /**
     * Sets the flying status of a player.
     *
     * @param player The player whose flying status is being set.
     * @param flying true to set the player flying, false to set them grounded.
     */
    public void setFlying(Player player, boolean flying) {
        player.setFlying(flying);
    }

    /**
     * Checks if a player is sneaking.
     *
     * @param player The player whose sneaking status is being checked.
     * @return true if the player is sneaking, otherwise false.
     */
    public boolean isSneaking(Player player) {
        return player.isSneaking();
    }

    /**
     * Checks if a player is an operator.
     *
     * @param player The player whose operator status is being checked.
     * @return true if the player is an operator, otherwise false.
     */
    public boolean isOp(Player player) {
        return player.isOp();
    }

    /**
     * Sets the operator status of a player.
     *
     * @param player The player whose operator status is being set.
     * @param op true to make the player an operator, false to revoke operator status.
     */
    public void setOp(Player player, boolean op) {
        player.setOp(op);
    }

    /**
     * Retrieves the gamemode of a player.
     *
     * @param player The player whose gamemode is being retrieved.
     * @return The gamemode of the player.
     */
    public GameMode getGamemode(Player player) {
        return player.getGameMode();
    }

    /**
     * Sets the gamemode of a player.
     *
     * @param player The player whose gamemode is being set.
     * @param mode   The gamemode to set.
     */
    public void setGamemode(Player player, GameMode mode) {
        player.setGameMode(mode);
    }

    /**
     * Teleports a player to a specified location.
     *
     * @param player   The player to teleport.
     * @param location The location to which the player will be teleported.
     */
    public void teleport(Player player, Location location) {
        player.teleport(location);
    }

    /**
     * Heals a player.
     *
     * @param player The player to heal.
     */
    public void heal(Player player) {
        player.setHealth(player.getMaxHealth());
    }

    /**
     * Feeds a player.
     *
     * @param player The player to feed.
     */
    public void feed(Player player) {
        player.setFoodLevel(20);
        player.setSaturation(10);
    }

    /**
     * Clears the inventory of a player.
     *
     * @param player The player whose inventory is being cleared.
     */
    public void clearInventory(Player player) {
        player.getInventory().clear();
    }

    /**
     * Checks if a player has a full inventory.
     *
     * @param player The player whose inventory is being checked.
     * @return true if the player's inventory is full, otherwise false.
     */
    public boolean fullInventory(Player player) {
        return player.getInventory().firstEmpty() == -1;
    }

    /**
     * Gives an item to a player.
     *
     * @param player The player to whom the item is being given.
     * @param item   The item to give.
     */
    public void giveItem(Player player, ItemStack item) {
        player.getInventory().addItem(item);
    }

    /**
     * Removes an item from a player's inventory.
     *
     * @param player The player from whose inventory the item is being removed.
     * @param item   The item to remove.
     */
    public void removeItem(Player player, ItemStack item) {
        player.getInventory().remove(item);
    }

    /**
     * Checks if a player has a specific item in their inventory.
     *
     * @param player The player whose inventory is being checked.
     * @param item   The item to check for.
     * @return true if the player has the item, otherwise false.
     */
    public boolean hasItem(Player player, ItemStack item) {
        return player.getInventory().contains(item);
    }

    /**
     * Retrieves the contents of a player's inventory.
     *
     * @param player The player whose inventory contents are being retrieved.
     * @return An array of ItemStacks representing the player's inventory contents.
     */
    public ItemStack[] getInventoryContents(Player player) {
        return player.getInventory().getContents();
    }

    /**
     * Retrieves the contents of a player's armor slots.
     *
     * @param player The player whose armor contents are being retrieved.
     * @return An array of ItemStacks representing the player's armor contents.
     */
    public ItemStack[] getArmorContents(Player player) {
        return player.getInventory().getArmorContents();
    }

    /**
     * Sets the armor contents of a player.
     *
     * @param player          The player whose armor contents are being set.
     * @param armorContents   The ItemStack array representing the armor contents.
     */
    public void setArmorContents(Player player, ItemStack[] armorContents) {
        player.getInventory().setArmorContents(armorContents);
    }

    /**
     * Retrieves the item held by a player.
     *
     * @param player The player whose held item is being retrieved.
     * @return The ItemStack representing the item held by the player.
     */
    public ItemStack getHeldItem(Player player) {
        return player.getInventory().getItemInMainHand();
    }

    /**
     * Sets the item held by a player.
     *
     * @param player The player whose held item is being set.
     * @param item   The ItemStack to set as the held item for the player.
     */
    public void setHeldItem(Player player, ItemStack item) {
        player.getInventory().setItemInMainHand(item);
    }

    /**
     * Retrieves the health of a player.
     *
     * @param player The player whose health is being retrieved.
     * @return The current health of the player.
     */
    public double getHealth(Player player) {
        return player.getHealth();
    }

    /**
     * Sets the health of a player.
     *
     * @param player The player whose health is being set.
     * @param health The health value to set for the player.
     */
    public void setHealth(Player player, double health) {
        player.setHealth(health);
    }

    /**
     * Retrieves the maximum health of a player.
     *
     * @param player The player whose maximum health is being retrieved.
     * @return The maximum health of the player.
     */
    public double getMaxHealth(Player player) {
        return player.getMaxHealth();
    }

    /**
     * Sets the maximum health of a player.
     *
     * @param player     The player whose maximum health is being set.
     * @param maxHealth The maximum health value to set for the player.
     */
    public void setMaxHealth(Player player, double maxHealth) {
        player.setMaxHealth(maxHealth);
    }

    /**
     * Applies damage to a player.
     *
     * @param player The player who is taking damage.
     * @param amount The amount of damage to apply.
     */
    public void damage(Player player, double amount) {
        player.damage(amount);
    }

    /**
     * Kills a player.
     *
     * @param player The player to kill.
     */
    public void kill(Player player) {
        player.setHealth(0);
    }

    /**
     * Retrieves the experience of a player.
     *
     * @param player The player whose experience is being retrieved.
     * @return The experience level of the player.
     */
    public int getExperience(Player player) {
        return player.getTotalExperience();
    }

    /**
     * Sets the experience of a player.
     *
     * @param player     The player whose experience is being set.
     * @param experience The experience level to set for the player.
     */
    public void setExperience(Player player, int experience) {
        player.setTotalExperience(experience);
    }

    /**
     * Gives experience to a player.
     *
     * @param player The player to give experience to.
     * @param amount The amount of experience to give.
     */
    public void giveExperience(Player player, int amount) {
        player.giveExp(amount);
    }

    /**
     * Clears the experience of a player.
     *
     * @param player The player whose experience is being cleared.
     */
    public void clearExperience(Player player) {
        player.setTotalExperience(0);
    }

    /**
     * Retrieves the level of a player.
     *
     * @param player The player whose level is being retrieved.
     * @return The level of the player.
     */
    public int getLevel(Player player) {
        return player.getLevel();
    }

    /**
     * Sets the level of a player.
     *
     * @param player The player whose level is being set.
     * @param level  The level to set for the player.
     */
    public void setLevel(Player player, int level) {
        player.setLevel(level);
    }

    /**
     * Increases the level of a player.
     *
     * @param player The player whose level is being increased.
     * @param amount The amount by which to increase the player's level.
     */
    public void increaseLevel(Player player, int amount) {
        player.setLevel(player.getLevel() + amount);
    }

    /**
     * Decreases the level of a player.
     *
     * @param player The player whose level is being decreased.
     * @param amount The amount by which to decrease the player's level.
     */
    public void decreaseLevel(Player player, int amount) {
        player.setLevel(player.getLevel() - amount);
    }

    /**
     * Retrieves the food level of a player.
     *
     * @param player The player whose food level is being retrieved.
     * @return The food level of the player.
     */
    public int getFoodLevel(Player player) {
        return player.getFoodLevel();
    }

    /**
     * Sets the food level of a player.
     *
     * @param player    The player whose food level is being set.
     * @param foodLevel The food level to set for the player.
     */
    public void setFoodLevel(Player player, int foodLevel) {
        player.setFoodLevel(foodLevel);
    }

    /**
     * Retrieves the saturation of a player.
     *
     * @param player The player whose saturation is being retrieved.
     * @return The saturation of the player.
     */
    public float getSaturation(Player player) {
        return player.getSaturation();
    }

    /**
     * Sets the saturation of a player.
     *
     * @param player     The player whose saturation is being set.
     * @param saturation The saturation value to set for the player.
     */
    public void setSaturation(Player player, float saturation) {
        player.setSaturation(saturation);
    }

    /**
     * Retrieves the bed spawn location of a player.
     *
     * @param player The player whose bed spawn location is being retrieved.
     * @return The bed spawn location of the player.
     */
    public Location getBedSpawnLocation(Player player) {
        return player.getBedSpawnLocation();
    }

    /**
     * Sets the bed spawn location of a player.
     *
     * @param player  The player whose bed spawn location is being set.
     * @param location The bed spawn location to set for the player.
     */
    public void setBedSpawnLocation(Player player, Location location) {
        player.setBedSpawnLocation(location);
    }

    /**
     * Checks if a player has played on the server before.
     *
     * @param player The player whose play status is being checked.
     * @return true if the player has played before, otherwise false.
     */
    public boolean hasPlayedBefore(Player player) {
        return player.hasPlayedBefore();
    }

    /**
     * Retrieves the first time a player played on the server.
     *
     * @param player The player whose first play time is being retrieved.
     * @return The timestamp of when the player first played on the server.
     */
    public long getFirstPlayed(Player player) {
        return player.getFirstPlayed();
    }
}
