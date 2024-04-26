package com.lunar.tutorial.custom.ModBlocks;

import com.lunar.tutorial.Ravens;
import com.lunar.tutorial.custom.ModBlocks.customBlocks.RavenPerch;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block RAVEN_PERCH = registerBlock("raven_perch",
            new RavenPerch(FabricBlockSettings.copyOf(Blocks.SPRUCE_PLANKS).nonOpaque()));

    public static void registerModBlocks() {
        Ravens.LOGGER.info("Registering Mod Blocks For " + Ravens.MOD_ID);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Ravens.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Ravens.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }
}
