package me.ritomg.raptor.client.module.mods.render;

import me.ritomg.raptor.RaptorClient;
import me.ritomg.raptor.client.misc.util.RenderUtil;
import me.ritomg.raptor.client.misc.util.Utils;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ImposterESP extends Module {
    public ImposterESP() {
        super("ImposterESP", Category.Render);
    }

    @SubscribeEvent
    public void onRender(RenderWorldLastEvent event) {
        for (EntityPlayer player : mc.world.playerEntities) {
            if (player.equals(mc.player))
                continue;

            double x = MathHelper.clampedLerp(player.lastTickPosX, player.posX, event.getPartialTicks() - mc.renderManager.renderPosX);
            double y = MathHelper.clampedLerp(player.lastTickPosY, player.posY, event.getPartialTicks() - mc.renderManager.renderPosY);
            double z = MathHelper.clampedLerp(player.lastTickPosZ, player.posZ, event.getPartialTicks() - mc.renderManager.renderPosZ);

            drawImposter(x, y, z);
        }
    }

    private void drawImposter(double x, double y, double z) {
        double distance = mc.renderViewEntity.getDistance(
                x - mc.renderManager.viewerPosX,
                y - mc.renderManager.viewerPosY,
                z - mc.renderManager.viewerPosZ
        );
        final double scale = (0.0125 + (distance * 0.0035)) * (2.0 / 10.0);

        RenderUtil.prepareGL();
        Utils.interpolateNametagPos(x, y, z, scale);
        GlStateManager.enableTexture2D();
        mc.getTextureManager().bindTexture(new ResourceLocation(RaptorClient.MODID,"imposter.png"));
        GlStateManager.color(1, 1, 1, 1);
        Gui.drawModalRectWithCustomSizedTexture(-2, -8, -2, -8, 2, 1, 2, 1);
        GlStateManager.disableTexture2D();
        RenderUtil.releaseGL();
    }
}
