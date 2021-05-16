package me.ritomg.raptor.mixin.mixins;

import me.ritomg.raptor.client.module.mods.render.NoCaveCulling;
import me.ritomg.raptor.client.module.mods.render.XRay;
import net.minecraft.client.renderer.chunk.VisGraph;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VisGraph.class)
public class MixinVisGraph {

    @Inject(method = "setOpaqueCube", at = @At("HEAD"), cancellable = true)
    public void setOpaqueCubePatch(BlockPos pos, CallbackInfo ci) {
        if (NoCaveCulling.stopCaveCulling || XRay.doXray)
            ci.cancel();
    }

}
