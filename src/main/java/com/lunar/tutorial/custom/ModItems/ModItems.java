package com.lunar.tutorial.custom.ModItems;

import com.lunar.tutorial.Ravens;
import com.lunar.tutorial.custom.ModItems.Items.RavenTagBlank;
import com.lunar.tutorial.custom.ModItems.Items.RavenTagFilled;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static Item RAVEN_TAG_F = registerItem("raven_tag_filled", new RavenTagFilled(new FabricItemSettings().maxCount(1)));
    public static Item RAVEN_TAG_B = registerItem("raven_tag_blank", new RavenTagBlank(new FabricItemSettings().maxCount(1)));

    public static void registerModItems() {
        Ravens.LOGGER.info("Registering Mod Items For " + Ravens.MOD_ID);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Ravens.MOD_ID, name), item);
    }

}
