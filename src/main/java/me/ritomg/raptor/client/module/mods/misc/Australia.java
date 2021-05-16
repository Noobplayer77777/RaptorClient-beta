package me.ritomg.raptor.client.module.mods.misc;

import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

// from Xulu
public class Australia extends Module {
    public Australia() {
        super("Australia", Category.Misc);
    }

    @Override
    public void onEnable() {
        mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/flip.json"));
    }

    @Override
    public void onDisable() {
        mc.entityRenderer.getShaderGroup().deleteShaderGroup();
    }
}
