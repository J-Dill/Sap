package com.github.jdill.sap;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Sap.MODID)
public class Sap {

    public static final String MODID = "sap";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public Sap() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Registry.BLOCKS.register(modEventBus);
        Registry.ITEMS.register(modEventBus);
        Registry.TILE_ENTITIES.register(modEventBus);
        Registry.FLUIDS.register(modEventBus);
    }

}
