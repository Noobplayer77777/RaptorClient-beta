package me.ritomg.raptor.mixin.mixins;

import me.ritomg.raptor.RaptorClient;
import me.ritomg.raptor.client.setting.impl.BooleanSetting;
import net.minecraft.block.BlockSoulSand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockSoulSand.class)
public class MixinBlockSoulSand {

    @Inject(method = "onEntityCollidedWithBlock", at = @At("HEAD"), cancellable = true)
    public void soulSandCollisionPatch(World worldIn, BlockPos pos, IBlockState state, Entity entityIn, CallbackInfo ci) {
        if (entityIn == Minecraft.getMinecraft().player) {
            if (RaptorClient.modManager.getMod("NoSlow").isEnabled()
                    && ((BooleanSetting)(RaptorClient.modManager.getMod("NoSlow").getSetting("SoulSand"))).enabled) {
                ci.cancel();
            }
        }
    }
}
