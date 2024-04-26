package com.lunar.tutorial.custom.ModItems;

import com.lunar.tutorial.Ravens;
import com.lunar.tutorial.custom.ModBlocks.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static ItemGroup RAVENS = Registry.register(Registries.ITEM_GROUP, new Identifier(Ravens.MOD_ID, "ravens"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.ravens"))
                    .icon(() -> new ItemStack(ModItems.RAVEN_TAG_F))
                    .entries(((displayContext, entries) -> {
                        entries.add(ModItems.RAVEN_TAG_B);

                        entries.add(ModBlocks.RAVEN_PERCH);
                    })).build());

    public static void registerModItemGroups(){
        Ravens.LOGGER.info("Registering Item Groups For " + Ravens.MOD_ID);
    }
}
