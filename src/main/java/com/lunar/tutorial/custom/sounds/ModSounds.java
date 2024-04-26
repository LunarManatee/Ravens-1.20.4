package com.lunar.tutorial.custom.sounds;

import com.lunar.tutorial.Ravens;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent RAVEN_TAG_FILL = registerSoundEvents("quill_raven");

    private static SoundEvent registerSoundEvents(String name) {
        Identifier id = new Identifier(Ravens.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerModSounds() {
        Ravens.LOGGER.info("Registering Sounds For " + Ravens.MOD_ID);
    }
}
