package com.github.jdill.sap.client.render.entity;

import com.github.jdill.sap.entity.projectile.SapEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class SapEntityRenderer extends SpriteRenderer<SapEntity> {

    public static final IRenderFactory<SapEntity> SAP_ENTITY_FACTORY = manager ->
        new SapEntityRenderer(manager, Minecraft.getInstance().getItemRenderer());

    public SapEntityRenderer(EntityRendererManager renderManagerIn,
        ItemRenderer itemRendererIn) {
        super(renderManagerIn, itemRendererIn);
    }
}
