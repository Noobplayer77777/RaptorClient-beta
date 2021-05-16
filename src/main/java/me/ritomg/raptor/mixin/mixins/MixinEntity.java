package me.ritomg.raptor.mixin.mixins;

import me.ritomg.raptor.RaptorClient;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Entity.class)
public class MixinEntity {

    // lolroflmaokekpogger
    @Redirect(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;isSneaking()Z"))
    public boolean movePatch(Entity entity) {
        return RaptorClient.modManager.getMod("SafeWalk").isEnabled() || entity.isSneaking();
    }
}
