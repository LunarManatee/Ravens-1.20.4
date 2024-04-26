package com.lunar.tutorial;

import com.lunar.tutorial.custom.ModBlocks.ModBlocks;
import com.lunar.tutorial.custom.ModItems.ModItemGroups;
import com.lunar.tutorial.custom.ModItems.ModItems;
import com.lunar.tutorial.custom.sounds.ModSounds;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ravens implements ModInitializer {

	public static final String MOD_ID = "ravens";
    public static final Logger LOGGER = LoggerFactory.getLogger("ravens");

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerModItemGroups();

		ModBlocks.registerModBlocks();

		ModSounds.registerModSounds();
	}
}