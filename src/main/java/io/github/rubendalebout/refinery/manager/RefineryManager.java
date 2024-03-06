package io.github.rubendalebout.refinery.manager;

import org.bukkit.Material;

import java.util.HashMap;

public class RefineryManager {
    private HashMap<String, Material> materialList = new HashMap<String, Material>();

    public RefineryManager() {
        this.materialList.put("&bGlass", Material.GLASS);
    }

    public HashMap<String, Material> getMaterialList() {
        return this.materialList;
    }
}
