package com.github.jdill.sap;

import com.github.jdill.sap.client.ClientSetup;
import com.github.jdill.sap.entity.projectile.SapEntity;
import javax.annotation.Nonnull;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
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
        Registry.ENTITIES.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientSetup::initEarly);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            DispenserBlock.registerDispenseBehavior(Registry.SAP_ITEM.get(),
                new ProjectileDispenseBehavior() {
                    @Override
                    @Nonnull
                    protected ProjectileEntity getProjectileEntity(@Nonnull World worldIn,
                        @Nonnull IPosition pos, @Nonnull ItemStack stackIn) {
                        return Util.make(new SapEntity(Registry.SAP_ENTITY.get(), pos.getX(),
                            pos.getY(), pos.getZ(), worldIn), (sap) ->
                            sap.setItem(stackIn));
                    }
                });
        });
    }

}
