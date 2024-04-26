package com.lunar.tutorial;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tutorial implements ModInitializer {
	public static final String MOD_ID = "tutorial";
    public static final Logger LOGGER = LoggerFactory.getLogger("tutorial");

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
	}
}