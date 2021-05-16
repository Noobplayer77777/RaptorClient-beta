package me.ritomg.raptor.mixin.mixins.accessor;

import net.minecraft.network.play.client.CPacketCloseWindow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CPacketCloseWindow.class)
public interface AccessorCPacketCloseWindow {

    @Accessor("windowId")
    public int getWindowId();
}
