package me.ritomg.raptor.client.module.mods.world;

import me.ritomg.raptor.client.events.PacketEvent;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import me.ritomg.raptor.mixin.mixins.accessor.AccessorCPacketPlayerTryToUseItemOnBlock;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BuildHeight extends Module {
    public BuildHeight() {
        super("BuildHeight", Category.Misc);
    }

    @SubscribeEvent
    public void onPacket(PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
            CPacketPlayerTryUseItemOnBlock packet = (CPacketPlayerTryUseItemOnBlock) event.getPacket();

            if (packet.getPos().getY() >= 255
                    && ((AccessorCPacketPlayerTryToUseItemOnBlock) packet).getPlacedBlockDirection() == EnumFacing.UP) {
                ((AccessorCPacketPlayerTryToUseItemOnBlock) packet).setPlacedBlockDirection(EnumFacing.DOWN);
            }
        }
    }
}
