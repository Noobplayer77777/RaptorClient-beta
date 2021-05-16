package me.ritomg.raptor.client.module.mods.world;

import me.ritomg.raptor.client.events.PacketEvent;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import me.ritomg.raptor.mixin.mixins.accessor.AccessorCPacketCloseWindow;
import net.minecraft.network.play.client.CPacketCloseWindow;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class XCarry extends Module {
    public XCarry() {
        super("XCarry", Category.Misc);
    }

    @SubscribeEvent
    public void onPacket(PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketCloseWindow) {
            CPacketCloseWindow packet = (CPacketCloseWindow) event.getPacket();
            if (((AccessorCPacketCloseWindow) packet).getWindowId() == mc.player.inventoryContainer.windowId)
                event.setCanceled(true);
        }
    }
}
