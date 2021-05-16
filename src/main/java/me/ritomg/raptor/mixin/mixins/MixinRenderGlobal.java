package me.ritomg.raptor.mixin.mixins;

import me.ritomg.raptor.RaptorClient;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderGlobal.class)
public class MixinRenderGlobal {

    @Inject(method = "drawSelectionBox", at = @At("HEAD"), cancellable = true)
    private void drawBoxHook(EntityPlayer player, RayTraceResult movingObjectPositionIn, int execute, float partialTicks, CallbackInfo ci) {
        if (RaptorClient.modManager.getMod("BlockOutline").isEnabled())
            ci.cancel();
    }
}
